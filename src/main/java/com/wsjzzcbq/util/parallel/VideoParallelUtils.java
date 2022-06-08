package com.wsjzzcbq.util.parallel;

import com.wsjzzcbq.bean.VideoResult;
import com.wsjzzcbq.constant.MsgConsts;
import com.wsjzzcbq.util.SysUtils;
import com.wsjzzcbq.util.VideoUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * VideoParallelUtils 多线程视频时长计算
 *
 * @author wsjz
 * @date 2022/05/09
 */
@Slf4j
public class VideoParallelUtils {

    private static ExecutorService service = Executors.newFixedThreadPool(3);

    public static VideoResult calculation(String path, List<String> formats) {

        long start = System.currentTimeMillis();
        //cpu核心数
        int processors = SysUtils.processors();

        List<Double> list = new LinkedList<>();
        File file = new File(path);
        System.out.println(file.getTotalSpace());

        calculation(new File(path), formats, service, list);
        System.out.println("采集完: " + list.size());
        System.out.println(list);
        double sum = 0.0;
        for (Double d : list) {
            sum = sum + d;
        }

        long end = System.currentTimeMillis();
        System.out.println("计算耗时:" + (end - start));

        System.out.println("============");
        System.out.println("总时间: " + sum + "分钟");
        System.out.println("总时间: " + sum/60 + "小时");

        double time = Double.valueOf(String.valueOf((end - start))) / 1000;

        return VideoResult.builder()
                .durationMinute(String.valueOf(sum))
                .durationHour(String.valueOf(sum/60))
                .time(String.valueOf(time) + MsgConsts.SECOND)
                .build();
    }

    public static void calculation(File file, List<String> formats, ExecutorService service, List<Double> list) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                calculation(f, formats, service, list);
            }

        }
        if (formats.contains(VideoUtils.getFileType(file))) {
            //计算时长
            VideoWork videoWork = new VideoWork(file);
            Future<Double> future = service.submit(videoWork);
            try {
                list.add(future.get());
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

    }


    @AllArgsConstructor
    private static class VideoWork implements Callable<Double> {

        private File file;

        @Override
        public Double call() throws Exception {
            System.out.println(Thread.currentThread().getName() + "正在计算");
            return VideoUtils.getLength(file);
        }
    }
}
