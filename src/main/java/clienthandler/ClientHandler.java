package clienthandler;

import communication.messagegenerator.IMessageGenerator;
import models.Filter;
import models.Incident;

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

        //todo lots of magic about incidents and finding the correct one!
    }

    @Override
    public void incidentConfirm(int incidentId, boolean accepted) {
        //received from operator, change incident to a confirmed one, broadcast
        for(Incident i : incidents){
            if (i.getIncidentId() == incidentId){
                if(accepted)
                    confirmedIncidents.add(i);
                incidents.remove(i);
                break;
            }
        }
        //todo broadcast
    }

    @Override
    public void tipConfirm(int incidentId, int tipId, boolean accepted) {
        //received from operator or unit, set tip to confirm, broadcast
        for(Incident i: incidents){
            if (i.getIncidentId() == incidentId){
                i.confirmTip(tipId, accepted);
                break;
            }
        }
        for(Incident i: confirmedIncidents){
            if (i.getIncidentId() == incidentId){
                i.confirmTip(tipId, accepted);
                break;
            }
        }
        //todo broadcast
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

        //todo send info to bots
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
        //todo broadcast
    }

    @Override
    public void subscribe(String unitId, int incidentId, boolean subscribe){
        //received from unit, add or remove id from incident depending on subscription, then send info (if subscribed)
        for(Incident i: incidents){
            if (i.getIncidentId() == incidentId) {
                i.subscribe(unitId, subscribe);
                break;
            }
        }

        //todo send info if subscribed
    }

    @Override
    public void connectAsOperator(String operatorId){
        //add id to list, send information to said id
        for (String id: operatorIds){
            if (id.equals(operatorId))
                return;
        }
        operatorIds.add(operatorId);
        //todo send info
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
        //todo send info
    }
}
