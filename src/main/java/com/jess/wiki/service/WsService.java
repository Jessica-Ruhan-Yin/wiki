package com.jess.wiki.service;

import com.jess.wiki.domain.Doc;
import com.jess.wiki.websocket.WebSocketServer;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author Jessica
 * @Version v
 * @Date 2021/9/5
 */
@Service
public class WsService {
    @Resource
    private WebSocketServer webSocketServer;

    @Async
    public void sendInfo(String message){
        webSocketServer.sendInfo(message);
    }

}
