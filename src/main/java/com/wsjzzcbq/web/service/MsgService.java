package com.wsjzzcbq.web.service;

import com.wsjzzcbq.web.bean.R;

/**
 * MsgService
 *
 * @author wsjz
 * @date 2022/06/06
 */
public interface MsgService {

    /**
     * 获取websocket连接地址
     * @return
     */
    R<?> getWebSocketInfo();
}
