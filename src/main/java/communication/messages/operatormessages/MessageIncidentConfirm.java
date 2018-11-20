package communication.messages.operatormessages;

public class MessageIncidentConfirm {
    int incidentId;
    boolean confirmed;

    public MessageIncidentConfirm(int incidentId, boolean confirmed){
        this.incidentId = incidentId;
        this.confirmed = confirmed;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public boolean isConfirmed() {
        return confirmed;
    }
}
