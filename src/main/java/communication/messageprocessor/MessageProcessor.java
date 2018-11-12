package communication.messageprocessor;

import clienthandler.IClientHandler;
import communication.messagehandlers.IMessageHandler;
import communication.messagehandlers.IMessageHandlerFactory;

import java.util.ArrayList;

public class MessageProcessor extends MessageProcessorBase {

    private IClientHandler clientHandler;

    public void processMessage(String sessionId, String type, String data){

        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getHandler());
        handler.handleMessage(data, sessionId, getGson());
    }

    public MessageProcessor(IMessageHandlerFactory messageHandlerFactory, IClientHandler clientHandler)
    {
        super(messageHandlerFactory);
        this.clientHandler = clientHandler;
    }

    public IClientHandler getHandler(){
        return clientHandler;
    }
}
