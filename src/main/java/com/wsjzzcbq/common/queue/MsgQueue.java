package com.wsjzzcbq.common.queue;

import com.wsjzzcbq.web.bean.Msg;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * MsgQueue
 *
 * @author wsjz
 * @date 2022/04/24
 */
public class MsgQueue {

    public static LinkedBlockingDeque<Msg> msgLinkedBlockingDeque = new LinkedBlockingDeque<>();
}
