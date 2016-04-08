package uno.cod.platform.server.core.service;

import java.util.UUID;

public interface IClientPushConnection {
    void send(UUID userId, String message);
    void sendLevelCompleted(UUID userId, UUID taskId);
    void sendChallengeTimeout(UUID userId, UUID challengeId);
}
