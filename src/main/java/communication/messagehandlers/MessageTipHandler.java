package communication.messagehandlers;

import clienthandler.IClientHandler;
import communication.messages.sharedmessages.MessageTip;

public class MessageTipHandler extends MessageHandler<MessageTip>{

    public MessageTipHandler(IClientHandler handler){super(handler);}

    @Override
    public void handleMessageInternal(MessageTip message, String sessionId){
        getHandler().tip(message.getOrigin(), message.getSender(), message.getMessage(), message.getLocation());
    }
}