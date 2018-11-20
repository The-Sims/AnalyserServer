package communication.messagehandlers;

import clienthandler.IClientHandler;
import communication.messages.operatormessages.MessageTipConfirm;

public class MessageTipConfirmHandler extends MessageHandler<MessageTipConfirm>{

    public MessageTipConfirmHandler(IClientHandler handler){super(handler);}

    @Override
    public void handleMessageInternal(MessageTipConfirm message, String sessionId){
        getHandler().tipConfirm(message.getIncidentId(), message.getTipId(), message.isConfirm());
    }
}