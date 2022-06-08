package com.wsjzzcbq.util;

import com.wsjzzcbq.bean.VideoResult;
import com.wsjzzcbq.constant.MsgConsts;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * VideoParallelUtils 带返回值递归视频时长计算
 *
 * @author wsjz
 * @date 2022/05/09
 */
@Slf4j
public class VideoUtils1 {

    private static List<String> types = new LinkedList<>();

    public static VideoResult calculation(String path, List<String> formats) throws EncoderException {

        types.clear();
        if (types.isEmpty()) {
            formats.forEach(v->{
                System.out.println(v);
                types.add(v);
            });
        }
        System.out.println("开始计算");
        long start = System.currentTimeMillis();
        System.out.println(start);

        File file = new File(path);
        double durations = 0.0;
        double sum = show(file, durations);


        System.out.println("============");
        System.out.println("总时间: " + sum + "分钟");
        System.out.println("总时间: " + sum/60 + "小时");

        long end = System.currentTimeMillis();
        System.out.println(end);

        System.out.println((end - start)/1000);
        System.out.println((end - start));

        double time = Double.valueOf(String.valueOf((end - start))) / 1000;

        return VideoResult.builder()
                .durationMinute(String.valueOf(sum))
                .durationHour(String.valueOf(sum/60))
                .time(String.valueOf(time) + MsgConsts.SECOND)
                .build();
    }

    public static double show(File file, double durations) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                durations = durations + show(f, durations);
            }
            return durations;
        }

        try {
            if (contain(getFileType(file))) {
                return getLength(file);
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return 0.0;

    }


    /**
     * 获取视频长度
     * @param file
     * @return
     * @throws EncoderException
     */
    public static double getLength(File file) throws EncoderException {
        Encoder encoder = new Encoder();

        MultimediaInfo info = null;

        long sum = 0;
        long num = 0;
        info = encoder.getInfo(file);
        //ls是获取到的秒数
        long ls = info.getDuration() / 1000;
        sum += ls;
        num++;

        double sum1 = (double) sum;
        // 转换成为了小时
        double sum2 = sum1 / 60;
        System.out.println(sum2);

        return  sum2;
    }

    /**
     * 判断文件是否在数组范围内
     * @param fileName
     * @return
     */
    public static boolean contain(String fileName) {
        for (String t : types) {
            if (fileName.equals(t)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 获取文件类型
     * @param file
     * @return
     */
    public static String getFileType(File file) {
        String fileName = file.getName();
        String type = fileName.substring(fileName.lastIndexOf(".") + 1);
        return type;
    }
}
