package uno.cod.platform.server.websocket.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import uno.cod.platform.server.core.repository.UserRepository;
import uno.cod.platform.server.core.service.IClientPushConnection;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class WebSocketService implements IClientPushConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketService.class);

    private final Map<Long, WebSocketSession> sessions;
    private final UserRepository userRepository;

    @Autowired
    public WebSocketService(UserRepository userRepository) {
        this.userRepository = userRepository;
        sessions = new HashMap<>();
    }

    public void addSession(Long userId, WebSocketSession session) {
        if(userRepository.findOne(userId) == null){
            try {
                session.close();
            } catch (IOException e) {
                LOGGER.info("can not close socket", e);
            }
            return;
        }
        sessions.put(userId, session);
    }

    public void send(Long userId, String message) {
        if(sessions.get(userId) == null){
            return;
        }
        try {
            sessions.get(userId).sendMessage(new TextMessage(message));
        } catch (IOException e) {
            LOGGER.info("can not send to socket", e);
        }
    }
}
