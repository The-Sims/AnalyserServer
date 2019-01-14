package communication.messagehandlers;

import clienthandler.IClientHandler;
import communication.messages.sharedmessages.MessageUpdateIncident;

public class MessageUpdateIncidentHandler extends MessageHandler<MessageUpdateIncident>{

    public MessageUpdateIncidentHandler(IClientHandler handler){super(handler);}

    @Override
    public void handleMessageInternal(MessageUpdateIncident message, String sessionId){
        getHandler().updateIncident(message.getIncident());
    }
}