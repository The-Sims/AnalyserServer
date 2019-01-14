package communication.messages;

public class EncapsulatingMessage {
    private String messageType;

    private String messageData;

    public EncapsulatingMessage(String type, String data)
    {
        this.messageType = type;
        this.messageData = data;
    }

    public String getMessageType()
    {
        return messageType;
    }

    public String getMessageData(){
        return messageData;
    }

    public void setMessageData(String messageData) {
        this.messageData = messageData;
    }
}
