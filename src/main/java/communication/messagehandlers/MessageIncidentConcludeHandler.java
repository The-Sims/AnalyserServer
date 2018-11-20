package communication.messagehandlers;

import clienthandler.IClientHandler;
import communication.messages.unitmessages.MessageIncidentConclude;

public class MessageIncidentConcludeHandler extends MessageHandler<MessageIncidentConclude>{

    public MessageIncidentConcludeHandler(IClientHandler handler){super(handler);}

    @Override
    public void handleMessageInternal(MessageIncidentConclude message, String sessionId){
        getHandler().incidentConclude(message.getIncidentId());
    }
}