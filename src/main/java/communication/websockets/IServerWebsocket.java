package communication.websockets;

public interface IServerWebsocket {
    void sendTo(String sessionId, Object object);

    void broadcast(Object object);

    void sendToGroup(String[] sessionId, Object object);
}
