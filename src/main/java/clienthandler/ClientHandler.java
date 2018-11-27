package clienthandler;

import communication.messagegenerator.IMessageGenerator;
import models.Filter;
import models.Incident;
import models.Tip;

import java.util.ArrayList;

public class ClientHandler implements IClientHandler {

    IMessageGenerator messageGenerator;
    ArrayList<Filter> filters = new ArrayList<>();
    ArrayList<String> searchTerms = new ArrayList<>();
    ArrayList<Incident> confirmedIncidents = new ArrayList<>();
    ArrayList<Incident> incidents = new ArrayList<>();
    ArrayList<String> operatorIds = new ArrayList<>();

    public ClientHandler(IMessageGenerator messageGenerator){this.messageGenerator=messageGenerator;}

    @Override
    public void tip(String origin, String sender, String message, String location) {
        //received from source, save in memory, send to operators
        Incident incident = null;
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
            //no incident found, create a new one
            int max = -1;
            for(Incident i: incidents){
                max = max>i.getIncidentId() ? max : i.getIncidentId();
            }
            for(Incident i: confirmedIncidents){
                max = max>i.getIncidentId() ? max : i.getIncidentId();
            }
            //todo tip: generate incidentName
            incident = new Incident(max+1, "??", location);
            incidents.add(incident);
            incident.addTip(new Tip(origin, sender, message, location));
        }
        else{
            //incident found, add tip to incident
            incident.addTip(new Tip(origin, sender, message, location));
        }

        messageGenerator.sendSubscribeInfo(incident.getSubscribedIds(), incident);
        messageGenerator.sendIncidentUpdate(incidents, confirmedIncidents);
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
        messageGenerator.sendSubscribeInfo(incident.getSubscribedIds(), incident);
        messageGenerator.sendIncidentUpdate(incidents, confirmedIncidents);
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
        for(Incident i: confirmedIncidents){
            if (i.getIncidentId() == incidentId){
                i.confirmTip(tipId, accepted);
                incident =i;
                break;
            }
        }
        messageGenerator.sendSubscribeInfo(incident.getSubscribedIds(), incident);
        messageGenerator.sendIncidentUpdate(incidents, confirmedIncidents);

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
                break;
            }
        }
        //todo conclude incident: broadcast
    }

    @Override
    public void subscribe(String unitId, int incidentId, boolean subscribe){
        //received from unit, add or remove id from incident depending on subscription, then send info (if subscribed)
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

        searchTerms.add("REEEEEE");
        searchTerms.add("Flikker");
        for(Filter f: filters){
            if (f.getFilterId().equals(filterId))
                return;
        }
        filters.add(new Filter(filterId, filterName));
        messageGenerator.sendUpdateSearchTerms(filterId, searchTerms);
    }
}
