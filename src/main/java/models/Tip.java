package models;

public class Tip {
    int id;
    Origin origin;
    String sender;
    String message;
    String location;
    boolean confirmed;

    public Tip(int tipId, Origin origin, String sender, String message, String location, boolean accepted) {
        this.id = tipId;
        this.origin = origin;
        this.sender = sender;
        this.message = message;
        this.location = location;
        this.confirmed = accepted;
    }

    public Tip(Origin origin, String sender, String message, String location) {
        this.id = 0;
        this.origin = origin;
        this.sender = sender;
        this.message = message;
        this.location = location;
    }

    public int getTipId(){
        return id;
    }

    public void setTipId(int tipId) {
        this.id = tipId;
    }

    public void setAccepted(){
        confirmed = true;
    }

    public boolean getAccepted(){
        return confirmed;
    }
}
