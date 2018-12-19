package clienthandler;

import communication.messagegenerator.IMessageGenerator;
import communication.rest.IREST;
import communication.rest.RESTClientHandler;
import logger.LogLevel;
import logger.Logger;
import models.Filter;
import models.Incident;
import models.Origin;
import models.Tip;

import java.util.ArrayList;

public class ClientHandler implements IClientHandler {

    IREST restClientHandler = new RESTClientHandler();
    IMessageGenerator messageGenerator;
    ArrayList<Filter> filters = new ArrayList<>();
    ArrayList<String> searchTerms = new ArrayList<>();
    ArrayList<Incident> confirmedIncidents = new ArrayList<>();
    ArrayList<Incident> concludedIncidents = new ArrayList<>();
    ArrayList<Incident> incidents = new ArrayList<>();
    ArrayList<String> operatorIds = new ArrayList<>();

    public ClientHandler(IMessageGenerator messageGenerator){
        this.messageGenerator=messageGenerator;
        searchTerms.add("Brand");
        searchTerms.add("Bom");
        searchTerms.add("Vuur");
    }

    @Override
    public void tip(Origin origin, String sender, String message, String location) {
        //received from source, save in memory, send to operators
        Incident incident = null;
        Tip tip = new Tip(origin, sender, message, location);

        for(Incident i: incidents){
            if (i.getLocation().equals(location)){
                incident = i;
                break;
            }
        }
        if (incident == null){
            for (Incident i: confirmedIncidents){
                if (i.getLocation().equals(location)){
                    incident = i;
                    break;
                }
            }
        }

        if (incident == null){
            for (Incident i: concludedIncidents){
                if (i.getLocation().equals(location)){
                    return;
                }
            }
        }

        if (incident == null){
            //no incident found, create a new one
            incident = new Incident(location);
            incident = restClientHandler.saveIncident(incident);
            incidents.add(incident);
            incident.addTip(tip);
        }
        else{
            //incident found, add tip to incident
            incident.addTip(tip);
        }
        incident = restClientHandler.saveIncident(incident);
        Logger.getInstance().log("Done REST'ing incident", LogLevel.INFORMATION);
        messageGenerator.sendSubscribeInfo(incident.getSubscribedIds(), incident);
        messageGenerator.sendIncidentUpdate(operatorIds, incidents, confirmedIncidents);
    }

    @Override
    public void incidentConfirm(int incidentId, boolean accepted) {
        //received from operator, change incident to a confirmed one, broadcast
        Incident incident = null;
        for(Incident i : incidents){
            if (i.getIncidentId() == incidentId){
                if(accepted)
                    confirmedIncidents.add(i);
                incidents.remove(i);
                incident = i;
                break;
            }
        }
        restClientHandler.saveIncident(incident);
        messageGenerator.sendSubscribeInfo(incident.getSubscribedIds(), incident);
        messageGenerator.sendIncidentUpdate(operatorIds, incidents, confirmedIncidents);
    }

    @Override
    public void tipConfirm(int incidentId, int tipId, boolean accepted) {
        //received from operator or unit, set tip to confirm, broadcast
        Incident incident = null;
        for(Incident i: incidents){
            if (i.getIncidentId() == incidentId){
                i.confirmTip(tipId, accepted);
                incident = i;
                break;
            }
        }
        for(Incident i: confirmedIncidents) {
            if (i.getIncidentId() == incidentId) {
                i.confirmTip(tipId, accepted);
                incident = i;
                break;
            }
        }
        restClientHandler.saveIncident(incident);

        messageGenerator.sendSubscribeInfo(incident.getSubscribedIds(), incident);
        messageGenerator.sendIncidentUpdate(operatorIds, incidents, confirmedIncidents);

    }

    @Override
    public void updateSearchTerms(String searchTerm, boolean addTerm) {
        //received from operator, send to bots
        boolean exists = false;
        for(String s: searchTerms){
            if (s.equals(searchTerm)) {
                exists = true;
                break;
            }
        }
        if (exists && !addTerm)
            searchTerms.remove(searchTerm);
        else if (!exists && addTerm)
            searchTerms.add(searchTerm);

        ArrayList<String> filterIds = new ArrayList<>();
        for(Filter f: filters){
            filterIds.add(f.getFilterId());
        }
        messageGenerator.sendUpdateSearchTerm(filterIds, addTerm, searchTerm);
    }

    @Override
    public void incidentConclude(int incidentId) {
        //received from unit, remove incident from memory, broadcast
        for (Incident i: confirmedIncidents){
            if (i.getIncidentId() == incidentId) {
                confirmedIncidents.remove(i);
                concludedIncidents.add(i);
                break;
            }
        }
        //todo conclude incident: broadcast
    }

    @Override
    public void subscribe(String unitId, int incidentId, boolean subscribe){
        //received from unit, add or remove id from incident depending on subscription, then send info (if subscribed)
        Logger.getInstance().log("Subscribing " + unitId, LogLevel.ERROR);
        Incident incident = null;
        for(Incident i: incidents){
            if (i.getIncidentId() == incidentId) {
                i.subscribe(unitId, subscribe);
                incident = i;
                break;
            }
        }
        if (incident == null){
            for(Incident i: confirmedIncidents){
                if (i.getIncidentId() == incidentId) {
                    i.subscribe(unitId, subscribe);
                    incident = i;
                    break;
                }
            }
        }
        if (incident != null) {
            ArrayList<String> id = new ArrayList<>();
            id.add(unitId);
            messageGenerator.sendSubscribeInfo(id, incident);
        }
    }

    @Override
    public void connectAsOperator(String operatorId){
        //add id to list, send information to said id
        for (String id: operatorIds){
            if (id.equals(operatorId))
                return;
        }
        operatorIds.add(operatorId);
        messageGenerator.sendIncidentUpdate(operatorId, incidents, confirmedIncidents);
    }

    @Override
    public void disconnect(String id){
        //remove id from everything
        operatorIds.remove(id);
        for(Incident i: incidents){
            i.subscribe(id, false);
        }
        for(Filter f: filters){
            if (f.getFilterId().equals(id)){
                filters.remove(f);
                break;
            }
        }
    }

    @Override
    public void connectAsFilter(String filterId, String filterName){
        //add id to list, send filterInformation to said id
        for(Filter f: filters){
            if (f.getFilterId().equals(filterId))
                return;
        }
        filters.add(new Filter(filterId, filterName));
        messageGenerator.sendUpdateSearchTerms(filterId, searchTerms);
    }
}
