package communication.messagehandlers;


import clienthandler.IClientHandler;

public class MessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object serverHandlerIn) {
        IClientHandler handler = (IClientHandler) serverHandlerIn;

        switch(simpleType){
            //case "MessageLobbyCreate":
            //    return new MessageLobbyCreateHandler(serverHandler);
            default:
                return null;
        }
    }
}
