package communication.messageprocessor;

import com.google.gson.Gson;
import communication.messagehandlers.IMessageHandlerFactory;

public abstract class MessageProcessorBase implements IMessageProcessor {

    private IMessageHandlerFactory messageHandlerFactory;

    public IMessageHandlerFactory getMessageHandlerFactory() {
        return messageHandlerFactory;
    }

    public abstract void processMessage(String sessionId, String type, String data);


    private Gson gson;

    public MessageProcessorBase(IMessageHandlerFactory messageHandlerFactory)
    {
        this.messageHandlerFactory = messageHandlerFactory;
        gson = new Gson();
    }


    public Gson getGson() {
        return gson;
    }
}