package uno.cod.platform.server.websocket.service;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import uno.cod.platform.server.core.repository.UserRepository;
import uno.cod.platform.server.core.service.IClientPushConnection;

import java.io.IOException;
import java.util.UUID;

@Service
public class WebSocketService implements IClientPushConnection {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketService.class);

    private final BiMap<UUID, WebSocketSession> sessions;
    private final UserRepository userRepository;

    @Autowired
    public WebSocketService(UserRepository userRepository) {
        this.userRepository = userRepository;
        sessions = Maps.synchronizedBiMap(HashBiMap.create());
    }

    public void addSession(UUID userId, WebSocketSession session) {
        if(userRepository.findOne(userId) == null){
            LOGGER.info("user {} with session id {} wanted to start a session, but we do not know him", userId, session.getId());
            try {
                session.close();
            } catch (IOException e) {
                LOGGER.warn("can not close socket", e);
            }
            return;
        }
        LOGGER.debug("user {} with session id {} connected", userId, session.getId());
        sessions.put(userId, session);
    }

    public void removeSession(WebSocketSession session) {
        UUID uuid = sessions.inverse().get(session);
        if (uuid != null) {
            LOGGER.debug("user {} with session id {} disconnected", uuid, session.getId());
            sessions.remove(uuid);
        } else {
            LOGGER.error("unknown websocket disconnected, this could be a synchronization problem");
        }
    }

    public void send(UUID userId, String message) {
        if(sessions.get(userId) == null){
            LOGGER.info("we wanted to talk to user {}, but we do not know this session, discarding message", userId);
            return;
        }
        try {
            LOGGER.debug("sending \"{}\" to user {}", message, userId);
            sessions.get(userId).sendMessage(new TextMessage(message));
        } catch (IOException e) {
            LOGGER.warn("sending \"{}\" to user {} failed", message, userId, e);
        }
    }
}
