package communication.messagehandlers;

public interface IMessageHandlerFactory {
    IMessageHandler getHandler(String simpleType, Object game);
}
