package communication.messagehandlers;

import com.google.gson.Gson;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public abstract class MessageHandler<T> implements IMessageHandler {
    IServerHandlerMessenger serverHandler;
    IClientHandler clientHandler;

    public MessageHandler(IServerHandlerMessenger serverHandler){
        this.serverHandler = serverHandler;
    }

    public MessageHandler(IClientHandler clientHandler){
        this.clientHandler = clientHandler;
    }

    public IClientHandler getClientHandler() {
        return clientHandler;
    }

    public IServerHandlerMessenger getServerHandler() {
        return serverHandler;
    }

    public void handleMessage(String data, String sessionId, Gson gson){
        Type type = ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        T msg = gson.fromJson(data, type);
        handleMessageInternal(msg, sessionId);
    }

    public abstract void handleMessageInternal(T message, String sessionId);
}
