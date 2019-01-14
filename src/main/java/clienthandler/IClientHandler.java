package clienthandler;

import models.Incident;
import models.Origin;

public interface IClientHandler {
    void tip(Origin origin, String sender, String message, String location);
    void incidentConfirm(int incidentId, boolean accepted);
    void tipConfirm(int incidentId, int tipId, boolean accepted);
    void updateSearchTerms(String searchTerm, boolean addTerm);
    void incidentConclude(int incidentId);
    void subscribe(String unitId, int incidentId, boolean subscribe);
    void connectAsOperator(String operatorId);
    void disconnect(String id);
    void connectAsFilter(String filterId, String filterName);
    void updateIncident(Incident incident);
}
