package com.wsjzzcbq.web.websocket;

import javax.websocket.Session;
import java.util.Map;
import java.util.Set;

/**
 * WebSocketBroadcast 广播所有在线连接用户
 *
 * @author wsjz
 * @date 2022/06/06
 */
public class WebSocketBroadcast {

    public static void send(String msg) {
        Set<Map.Entry<String, Session>> set = OnlineSessionManager.sessionSet();
        if (!set.isEmpty()) {
            set.forEach(m->m.getValue().getAsyncRemote().sendText(msg));
        }
    }
}
