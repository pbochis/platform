package uno.cod.platform.server.websocket.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import uno.cod.platform.server.core.Profiles;
import uno.cod.platform.server.core.domain.User;
import uno.cod.platform.server.websocket.service.WebSocketService;

@Configuration
@EnableWebSocket
@ComponentScan({"uno.cod.platform.server.core.service"})
public class WebSocketConfig implements WebSocketConfigurer {
    @Autowired
    Environment env;

    @Autowired
    private WebSocketService webSocketService;

    @Value("${coduno.url}")
    private String codunoUrl;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        if(env.acceptsProfiles(Profiles.PRODUCTION))
            registry.addHandler(new SocketHandler(), "/ws").setAllowedOrigins(codunoUrl);
        else
            registry.addHandler(new SocketHandler(), "/ws").setAllowedOrigins("*");
    }

    private class SocketHandler extends TextWebSocketHandler {
        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            if (!(session.getPrincipal() instanceof Authentication)) {
                return;
            }

            Authentication auth = (Authentication) session.getPrincipal();

            if (!(auth.getPrincipal() instanceof User)) {
                return;
            }

            User user = (User) auth.getPrincipal();
            webSocketService.addSession(user.getId(), session);
        }
    }
}
