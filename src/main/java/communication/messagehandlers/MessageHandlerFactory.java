package communication.messagehandlers;


import clienthandler.IClientHandler;
import communication.messages.filtermessages.MessageUpdateSearchTerm;
import communication.messages.operatormessages.MessageConnectAsOperator;
import communication.messages.operatormessages.MessageIncidentConfirm;
import communication.messages.operatormessages.MessageTipConfirm;
import communication.messages.unitmessages.MessageIncidentConclude;
import communication.messages.unitmessages.MessageSubscribe;

public class MessageHandlerFactory implements IMessageHandlerFactory {

    public IMessageHandler getHandler(String simpleType, Object serverHandlerIn) {
        IClientHandler handler = (IClientHandler) serverHandlerIn;

        switch(simpleType){
            case "MessageConnectAsFilter":
                return new MessageConnectAsFilterHandler(handler);
            case "MessageUpdateSearchTerm":
                return new MessageUpdateSearchTermHandler(handler);
            case "MessageConnectAsOperator":
                return new MessageConnectAsOperatorHandler(handler);
            case "MessageIncidentConfirm":
                return new MessageIncidentConfirmHandler(handler);
            case "MessageTipConfirm":
                return new MessageTipConfirmHandler(handler);
            case "MessageTip":
                return new MessageTipHandler(handler);
            case "MessageIncidentConclude":
                return new MessageIncidentConcludeHandler(handler);
            case "MessageSubscribe":
                return new MessageSubscribeHandler(handler);
            case "MessageUpdateIncident":
                return new MessageUpdateIncidentHandler(handler);
            default:
                return null;
        }
    }
}
