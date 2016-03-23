package uno.cod.platform.server.core.service;

import java.util.UUID;

public interface IClientPushConnection {
    void send(UUID userId, String message);
}
