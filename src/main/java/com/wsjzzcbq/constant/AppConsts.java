package com.wsjzzcbq.constant;

import java.io.File;

/**
 * AppConsts
 *
 * @author wsjz
 * @date 2022/04/06
 */
public class AppConsts {

    public static final String appTmpPath = "developmenttool" + File.separator;

    public static final int GLOBAL_SIZE = 650;
    /**
     * 二维码大小
     */
    public static final int QR_CODE_SIZE = 300;
    /**
     * app端页面
     */
    public static final String APP_WEB_PAGE = "/index.html";
    /**
     * PC端页面
     */
    public static final String PC_WEB_PAGE = "/web.html";
    /**
     * 默认局域网ip格式
     */
    public static final String DEFAULT_IP_FORMAT = "192.168.0";
    /**
     * websocket handler连接地址
     */
    public static final String SERVER_END_POINT = "/development/websocket/{userId}";
    /**
     * websocket连接地址
     */
    public static final String SERVER_END_POINT_URL = "/development/websocket/";
}
