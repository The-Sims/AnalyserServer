package communication.messageprocessor;

import communication.messagehandlers.IMessageHandler;
import communication.messagehandlers.IMessageHandlerFactory;

import java.util.ArrayList;

public class MessageProcessor extends MessageProcessorBase {

    ArrayList<String> validatedSessions = new ArrayList<>();

    private IServerHandlerMessenger serverHandler;

    public void processMessage(String sessionId, String type, String data){

        String simpleType = type.split("\\.")[type.split("\\.").length - 1];

        IMessageHandler handler = getMessageHandlerFactory().getHandler(simpleType, getHandler());
        handler.handleMessage(data, sessionId, getGson());
    }

    public void sessionValidated(String sessionId){
        validatedSessions.add(sessionId);
    }

    public MessageProcessor(IMessageHandlerFactory messageHandlerFactory, IServerHandlerMessenger serverHandler)
    {
        super(messageHandlerFactory);
        this.serverHandler = serverHandler;
    }

    public IServerHandlerMessenger getHandler(){
        return serverHandler;
    }
}
