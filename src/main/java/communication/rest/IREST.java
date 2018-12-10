package communication.rest;

import models.Incident;
import models.Tip;

public interface IREST {
    Incident saveIncident(Incident incident);
    Incident getIncident(int incidentId);
}