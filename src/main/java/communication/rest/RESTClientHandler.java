package communication.rest;

import logger.LogLevel;
import logger.Logger;
import models.Incident;
import models.Tip;

public class RESTClientHandler extends BaseRestClient implements IREST {

    String url = "http://145.93.68.158:8094/";

    public String getBaseUr() {
        return url;
    }

    public Incident saveIncident(Incident incident){
        String query = "incident/save";

        Logger.getInstance().log("Saving incident", LogLevel.INFORMATION);
        Incident i = executeQueryPost(incident, query, Incident.class);

        Logger.getInstance().log("converting incident", LogLevel.INFORMATION);
        return incident;
    }

    public Incident getIncident(int incidentId){
        String query = "incident/";
        return executeQueryGet(query + incidentId, Incident.class);
        //return null;
    }
}
