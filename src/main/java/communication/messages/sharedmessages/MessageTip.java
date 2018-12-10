package communication.messages.sharedmessages;

import models.Origin;

public class MessageTip {
    Origin origin;
    String sender;
    String message;
    String location;

    public MessageTip(Origin origin, String sender, String message, String location) {
        this.origin = origin;
        this.sender = sender;
        this.message = message;
        this.location = location;
    }

    public Origin getOrigin() {
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
