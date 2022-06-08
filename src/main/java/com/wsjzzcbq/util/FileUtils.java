package com.wsjzzcbq.util;

import java.io.File;

/**
 * FileTools 文件工具类
 *
 * @author wsjz
 * @date 2021/09/26
 */
public class FileUtils {

    /**
     * 删除文件夹（文件夹下全部文件）或文件
     * @param filePath
     */
    public static void delete(String filePath) {
        File file = new File(filePath);
        deleteDir(file);
    }

    private static void deleteDir(File file) {
        if(file.isFile()) {
            System.out.println("删除文件:" + file.getName());
            file.delete();

        } else {
            for (File f : file.listFiles()) {
                deleteDir(f);
            }
            System.out.println("删除文件:" + file.getName());
            file.delete();
        }
    }

    /**
     * 获取文件类型
     * @param file
     * @return
     */
    public static String getFileType(String file) {
        return file.substring(file.lastIndexOf("."));
    }

    /**
     * 获取文件名（不带类型）
     * @param file
     * @return
     */
    public static String getFileName(String file) {
        return file.substring(0, file.lastIndexOf("."));
    }

}
