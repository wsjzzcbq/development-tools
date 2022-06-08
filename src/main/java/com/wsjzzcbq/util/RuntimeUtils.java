package com.wsjzzcbq.util;

import java.io.IOException;

/**
 * RuntimeUtils
 *
 * @author wsjz
 * @date 2022/04/27
 */
public class RuntimeUtils {

    /**
     * 打开 windows默认浏览器
     * @param url
     * @throws IOException
     */
    public static void openDefaultBrowser(String url) throws IOException {
        Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);
    }

}
