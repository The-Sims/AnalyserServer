package communication.messages.sharedmessages;

import models.Incident;

import java.util.ArrayList;

public class MessageUpdateIncidents {

    ArrayList<Incident> incidents;
    ArrayList<Incident> confirmedIncidents;

    public MessageUpdateIncidents(ArrayList<Incident> incidents, ArrayList<Incident> confirmedIncidents) {
        this.incidents = incidents;
        this.confirmedIncidents = confirmedIncidents;
    }

    public ArrayList<Incident> getIncidents() {
        return incidents;
    }

    public ArrayList<Incident> getConfirmedIncidents() {
        return confirmedIncidents;
    }
}
