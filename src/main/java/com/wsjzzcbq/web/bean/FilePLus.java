package com.wsjzzcbq.web.bean;

import lombok.Builder;
import lombok.Data;

/**
 * FilePLus 对 java File类进行包装
 *
 * @author wsjz
 * @date 2022/06/01
 */
@Data
@Builder
public class FilePLus {

    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件本地路径地址
     */
    private String addr;
    /**
     * 文件网络请求地址
     */
    private String path;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 创建时间 字符串格式
     */
    private String createDate;

}
