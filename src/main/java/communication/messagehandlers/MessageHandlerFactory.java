package communication.messagehandlers;


public class MessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object serverHandlerIn) {
        IServerHandlerMessenger serverHandler = (IServerHandlerMessenger) serverHandlerIn;

        switch(simpleType){
            //case "MessageLobbyCreate":
            //    return new MessageLobbyCreateHandler(serverHandler);
            default:
                return null;
        }
    }
}
