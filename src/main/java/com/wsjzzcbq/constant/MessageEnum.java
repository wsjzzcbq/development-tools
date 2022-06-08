package com.wsjzzcbq.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * MessageEnum 消息类型颜色枚举类
 *
 * @author wsjz
 * @date 2022/06/08
 */
@Getter
@AllArgsConstructor
public enum MessageEnum {

    /**
     * 成功
     */
    SUCCESS("images/check-one.png", "#67C23A", "#F0F9EB"),
    /**
     * 警告
     */
    WARNING("images/attention.png", "#E6A23C", "#FDF6EC"),
    /**
     * 错误
     */
    ERROR("images/close-one.png", "#F56C6C", "#FEF0F0"),
    /**
     * 消息
     */
    INFO("images/info.png", "#909399", "#EDF2FC");

    /**
     * icon图标
     */
    private String icon;

    /**
     * 字体颜色
     */
    private String fontColor;

    /**
     * 背景颜色
     */
    private String backgroundColor;

}
