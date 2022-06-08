package com.wsjzzcbq.util;

import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.EncoderException;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.lang3.StringUtils;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

/**
 * 支持递归调用
 */
public class VideoUtils {

    private static List<String> types = new LinkedList<>();

    public static String calculation(String path, List<String> formats) throws EncoderException {
        System.out.println(SysUtils.processors());
        if (StringUtils.isBlank(path)) {
            return "请输入路径";
        }
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

        List<File> files = new LinkedList<>();
        File file = new File(path);
        show(file, files);

        double sum = 0.0;
        for (File f : files) {
            if (contain(getFileType(f))) {
                System.out.println(f.getName());
                sum = sum + getLength(f);
            }
        }

        System.out.println("============");
        System.out.println("总时间: " + sum + "分钟");
        System.out.println("总时间: " + sum/60 + "小时");

        long end = System.currentTimeMillis();
        System.out.println(end);

        System.out.println((end - start)/1000);
        System.out.println((end - start));

        return String.valueOf(sum);
    }

    public static void show(File file, List<File> fileList) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                show(f, fileList);
            }

        }
        fileList.add(file);
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
        // ls是获取到的秒数
        long ls = info.getDuration() / 1000;
        sum += ls;
        num++;

        double sum1 = (double) sum;
        // 转换成为了小时
        double sum2 = sum1 / 60;

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
