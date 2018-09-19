package communication.websockets;

import communication.messageprocessor.IMessageProcessor;

public interface IWebSocket {
    void start();

    void stop();

    void setMessageHandler(IMessageProcessor handler);
}
