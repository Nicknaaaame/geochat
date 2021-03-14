package org.example.backend.websocket;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

//@Component
public class WebSocketEventListener {
    @EventListener
    public void handleSessionConnected(SessionConnectEvent event) {
        try{
            System.out.println(event.getUser().getName());
            StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        } catch (Exception e){
            System.out.println(e);
        }
    }

    @EventListener
    public void handleSessionDisconnect(SessionDisconnectEvent event) {
        System.out.println("disconnected");
        System.out.println(event);
    }
}
