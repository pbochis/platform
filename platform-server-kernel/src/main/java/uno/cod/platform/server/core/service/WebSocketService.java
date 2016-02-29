package uno.cod.platform.server.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import uno.cod.platform.server.core.repository.UserRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vbalan on 2/23/2016.
 */
@Service
public class WebSocketService {
    private Map<Long, WebSocketSession> sessions;
    private final UserRepository userRepository;

    @Autowired
    public WebSocketService(UserRepository userRepository){
        this.userRepository = userRepository;
        sessions = new HashMap<>();
    }

    public void addSession(Long userId, WebSocketSession session){
        if(userRepository.findOne(userId)==null){
            try {
                session.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
        sessions.put(userId, session);
    }

    public void send(Long userId, String message){
        if(sessions.get(userId)==null){
            return;
        }
        try {
            sessions.get(userId).sendMessage(new TextMessage(message));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
