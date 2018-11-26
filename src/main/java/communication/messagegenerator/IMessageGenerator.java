package communication.messagegenerator;

import models.Incident;

import java.util.ArrayList;

public interface IMessageGenerator {
    void sendIncidentUpdate(ArrayList<Incident> incidents, ArrayList<Incident> confirmedIncidents);
    void sendIncidentUpdate(String operatorId, ArrayList<Incident> incidents, ArrayList<Incident> confirmedIncidents);
    void sendUpdateSearchTerms(String filterId, ArrayList<String> searchTerms);
    void sendUpdateSearchTerm(ArrayList<String> filterIds, boolean add, String searchTerm);
    void sendSubscribeInfo(ArrayList<String> ids, Incident incident);
}
