package communication.messagehandlers;

import clienthandler.IClientHandler;
import communication.messages.operatormessages.MessageConnectAsOperator;

public class MessageConnectAsOperatorHandler extends MessageHandler<MessageConnectAsOperator>{

    public MessageConnectAsOperatorHandler(IClientHandler handler){super(handler);}

    @Override
    public void handleMessageInternal(MessageConnectAsOperator message, String sessionId){
        getHandler().connectAsOperator(sessionId);
    }
}