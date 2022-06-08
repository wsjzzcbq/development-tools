package com.wsjzzcbq.web.websocket;

import javax.websocket.Session;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * OnlineSessionManager
 *
 * @author wsjz
 * @date 2022/06/06
 */
public class OnlineSessionManager {

    private static ConcurrentHashMap<String, Session> onlineSession = new ConcurrentHashMap<>();

    public synchronized static void add(String userId, Session session) {
        onlineSession.put(userId, session);
    }

    public synchronized static Session get(String userId) {
        return onlineSession.get(userId);
    }

    public synchronized static Set<Map.Entry<String, Session>> sessionSet() {
        return onlineSession.entrySet();
    }

    public synchronized static void remove(String userId) {
        onlineSession.remove(userId);
    }

    public synchronized static void remove(Session session) {
        String userId = null;
        for (Map.Entry<String, Session> m : sessionSet()) {
            if (m.getValue().equals(session)) {
                userId = m.getKey();
                break;
            }
        }
        if (userId != null) {
            onlineSession.remove(userId);
        }
    }

}
