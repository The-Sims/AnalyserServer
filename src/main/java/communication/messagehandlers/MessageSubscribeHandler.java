package communication.messagehandlers;

import clienthandler.IClientHandler;
import communication.messages.unitmessages.MessageSubscribe;

public class MessageSubscribeHandler extends MessageHandler<MessageSubscribe>{

    public MessageSubscribeHandler(IClientHandler handler){super(handler);}

    @Override
    public void handleMessageInternal(MessageSubscribe message, String sessionId){
        getHandler().subscribe(sessionId, message.getIncidentId(), message.isSubscribe());
    }
}
