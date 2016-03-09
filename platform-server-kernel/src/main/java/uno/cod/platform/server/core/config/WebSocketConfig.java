package uno.cod.platform.server.core.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import uno.cod.platform.server.core.service.WebSocketService;

/**
 * Created by vbalan on 2/23/2016.
 */
@Configuration
@EnableWebSocket
@ComponentScan({"uno.cod.platform.server.core.service"})
public class WebSocketConfig implements WebSocketConfigurer{
    @Autowired
    private WebSocketService webSocketService;
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new SocketHandler(), "/ws").setAllowedOrigins("*").withSockJS();
    }

    private class SocketHandler extends TextWebSocketHandler{
        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            //TODO make payload be the auth header and decrypt user from there
            webSocketService.addSession(Long.parseLong(message.getPayload()), session);
        }

    }
}
