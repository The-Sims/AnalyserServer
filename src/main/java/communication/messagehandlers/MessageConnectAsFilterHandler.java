package communication.messagehandlers;

import clienthandler.IClientHandler;
import communication.messages.filtermessages.MessageConnectAsFilter;

public class MessageConnectAsFilterHandler extends MessageHandler<MessageConnectAsFilter>{

    public MessageConnectAsFilterHandler(IClientHandler handler){super(handler);}

    @Override
    public void handleMessageInternal(MessageConnectAsFilter message, String sessionId){
        getHandler().connectAsFilter(sessionId, message.getFilterName());
    }
}