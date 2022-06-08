package com.wsjzzcbq.web.websocket;

import com.wsjzzcbq.constant.AppConsts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

/**
 * WebSocketHandler
 *
 * @author wsjz
 * @date 2022/06/06
 */
@Slf4j
@Component
@ServerEndpoint(value = AppConsts.SERVER_END_POINT)
public class WebSocketHandler {

    @OnOpen
    public void onOpen(Session session, @PathParam(value="userId") String userId){
        System.out.println(userId);
        log.info("服务端建立连接" + userId);
        OnlineSessionManager.add(userId, session);

    }

    @OnClose
    public void onClose(Session session){
        log.info("关闭连接");
        OnlineSessionManager.remove(session);
    }
}
