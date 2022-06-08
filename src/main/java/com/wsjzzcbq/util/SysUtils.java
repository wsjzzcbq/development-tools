package com.wsjzzcbq.util;

import java.util.UUID;

/**
 * SysUtils
 *
 * @author wsjz
 * @date 2022/04/06
 */
public class SysUtils {

    public static String uuid() {
        return UUID.randomUUID().toString();
    }

    public static int location(int size, int widthOrHeight) {
        return (widthOrHeight - size)/2;
    }

    /**
     * cpu核心数
     * @return
     */
    public static int processors() {
        System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().maxMemory() / 1024 / 1024);
        System.out.println(Runtime.getRuntime().totalMemory() / 1024 / 1024);
        return Runtime.getRuntime().availableProcessors();
    }

}
