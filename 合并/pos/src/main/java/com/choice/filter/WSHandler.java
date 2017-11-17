package com.choice.filter;

import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import com.google.common.reflect.TypeToken;

public class WSHandler implements WebSocketHandler{

	@Override  
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {  
        System.out.println("connect to the websocket success......");  
        session.sendMessage(new TextMessage("Server:connected OK!"));  
    }  
  
    @Override  
    public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {  
        TextMessage returnMessage = new TextMessage(wsm.getPayload()  
                + " received at server");  
        System.out.println(wss.getHandshakeHeaders().getFirst("Cookie"));  
        wss.sendMessage(returnMessage);  
    }  
  
    @Override
    public void handleTransportError(WebSocketSession wss, Throwable thrwbl) throws Exception {  
        if(wss.isOpen()){  
            wss.close();  
        }  
       System.out.println("websocket connection closed......");  
    }  
  
    @Override  
    public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) throws Exception {  
        System.out.println("websocket connection closed......");  
    }  
  
    @Override  
    public boolean supportsPartialMessages() {  
        return false;  
    }


}
