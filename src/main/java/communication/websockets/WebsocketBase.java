package communication.websockets;

import com.google.gson.Gson;
import communication.encapsulatingmessagegenerator.EncapsulatingMessageGenerator;
import communication.encapsulatingmessagegenerator.IEncapsulatingMessageGenerator;
import communication.messageprocessor.IMessageProcessor;

public abstract class WebsocketBase implements IWebSocket {
    private IMessageProcessor handler;

    public IEncapsulatingMessageGenerator getEncapsulatingMessageGenerator() {
        return encapsulatingMessageGenerator;
    }

    private IEncapsulatingMessageGenerator encapsulatingMessageGenerator = new EncapsulatingMessageGenerator();

    private Gson gson;

    public WebsocketBase()
    {
        gson = new Gson();
    }

    public void setMessageHandler(IMessageProcessor handler)
    {
        this.handler = handler;
    }

    public abstract void start();

    public abstract void stop();

    public IMessageProcessor getHandler() {
        return handler;
    }

    public Gson getGson() {
        return gson;
    }
}
