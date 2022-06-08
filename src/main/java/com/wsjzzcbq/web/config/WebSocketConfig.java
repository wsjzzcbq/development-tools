package com.wsjzzcbq.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * WebSocketConfig
 *
 * @author wsjz
 * @date 2022/06/06
 */
@Configuration
public class WebSocketConfig {

    /**
     * 启用WebSocket的支持
     * @return
     */
    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }
}
