package communication.messagehandlers;

import clienthandler.IClientHandler;
import communication.messages.operatormessages.MessageIncidentConfirm;

public class MessageIncidentConfirmHandler extends MessageHandler<MessageIncidentConfirm>{

    public MessageIncidentConfirmHandler(IClientHandler handler){super(handler);}

    @Override
    public void handleMessageInternal(MessageIncidentConfirm message, String sessionId){
        getHandler().incidentConfirm(message.getIncidentId(), message.isConfirmed());
    }
}