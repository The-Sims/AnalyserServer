package communication.messagehandlers;

import clienthandler.IClientHandler;
import communication.messages.filtermessages.MessageConnectAsFilter;
import communication.messages.filtermessages.MessageUpdateSearchTerm;

public class MessageUpdateSearchTermHandler extends MessageHandler<MessageUpdateSearchTerm>{

    public MessageUpdateSearchTermHandler(IClientHandler handler){super(handler);}

    @Override
    public void handleMessageInternal(MessageUpdateSearchTerm message, String sessionId){
        getHandler().updateSearchTerms(message.getSearchTerm(), message.DoAddTerm());
    }
}