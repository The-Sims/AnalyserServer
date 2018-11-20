package communication.messages.sharedmessages;

public class MessageTip {
    String origin;
    String sender;
    String message;
    String location;

    public MessageTip(String origin, String sender, String message, String location) {
        this.origin = origin;
        this.sender = sender;
        this.message = message;
        this.location = location;
    }

    public String getOrigin() {
        return origin;
    }

    public String getSender() {
        return sender;
    }

    public String getMessage() {
        return message;
    }

    public String getLocation() {
        return location;
    }
}
