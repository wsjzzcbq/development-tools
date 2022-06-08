package com.wsjzzcbq.util;

import com.wsjzzcbq.constant.MessageEnum;
import com.wsjzzcbq.ui.msg.PopMessage;
/**
 * PopMessageUtils
 *
 * @author wsjz
 * @date 2022/06/08
 */
public class PopMessageUtils {

    public static void success(String content) {
        new PopMessage(MessageEnum.SUCCESS, content);
    }

    public static void warning(String content) {
        new PopMessage(MessageEnum.WARNING, content);
    }

    public static void error(String content) {
        new PopMessage(MessageEnum.ERROR, content);
    }

    public static void info(String content) {
        new PopMessage(MessageEnum.INFO, content);
    }
}
