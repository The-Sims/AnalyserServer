package communication.messages.sharedmessages;

import models.Incident;

public class MessageUpdateIncident {

    Incident incident;
    boolean concluded;

    public MessageUpdateIncident(Incident incident, boolean concluded) {
        this.incident = incident;
        this.concluded = concluded;
    }

    public Incident getIncident() {
        return incident;
    }

    public boolean isConcluded() {
        return concluded;
    }
}
