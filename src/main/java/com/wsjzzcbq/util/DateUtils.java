package com.wsjzzcbq.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * DateUtils
 *
 * @author wsjz
 * @date 2022/04/25
 */
public class DateUtils {

    /**
     * 获取当前时间字符串格式
     * @return
     */
    public static String nowDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        return sdf.format(new Date());
    }
}
