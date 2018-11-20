package communication.messages.unitmessages;

public class MessageIncidentConclude {
    int incidentId;

    public MessageIncidentConclude(int incidentId){
        this.incidentId = incidentId;
    }

    public int getIncidentId() {
        return incidentId;
    }
}
