package com.wsjzzcbq.web.bean;

import lombok.Data;

@Data
public class YmlConfiguration {

    private Server server;

    /**
     * 局域网ip地址格式
     */
    private String ipFormat;

    private String fileAddr;

    private String filePath;

    private Object spring;

}
