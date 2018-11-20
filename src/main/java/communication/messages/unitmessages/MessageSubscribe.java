package communication.messages.unitmessages;

public class MessageSubscribe {
    int incidentId;
    boolean subscribe;

    public MessageSubscribe(int incidentId, boolean subscribe){
        this.incidentId = incidentId;
        this.subscribe = subscribe;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public boolean isSubscribe() {
        return subscribe;
    }
}
