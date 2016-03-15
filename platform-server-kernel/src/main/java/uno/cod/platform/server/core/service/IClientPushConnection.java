package uno.cod.platform.server.core.service;

public interface IClientPushConnection {
    void send(Long userId, String message);
}
