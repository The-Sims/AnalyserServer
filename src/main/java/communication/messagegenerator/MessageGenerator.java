package communication.messagegenerator;

import communication.messages.filtermessages.MessageSendSearchTerms;
import communication.messages.filtermessages.MessageUpdateSearchTerm;
import communication.messages.sharedmessages.MessageUpdateIncident;
import communication.messages.sharedmessages.MessageUpdateIncidents;
import communication.messages.unitmessages.MessageIncidentConclude;
import communication.websockets.IServerWebsocket;
import models.Incident;

import java.util.ArrayList;

public class MessageGenerator implements IMessageGenerator {

    private IServerWebsocket serverSocket;

    public MessageGenerator(IServerWebsocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    @Override
    public void sendIncidentUpdate(ArrayList<Incident> incidents, ArrayList<Incident> confirmedIncidents) {
        MessageUpdateIncidents msg = new MessageUpdateIncidents(incidents, confirmedIncidents);
        serverSocket.broadcast(msg);
    }

    @Override
    public void sendIncidentUpdate(String operatorId, ArrayList<Incident> incidents, ArrayList<Incident> confirmedIncidents) {
        MessageUpdateIncidents msg = new MessageUpdateIncidents(incidents, confirmedIncidents);
        serverSocket.sendTo(operatorId, msg);
    }

    @Override
    public void sendUpdateSearchTerm(ArrayList<String> filterIds, boolean add, String searchTerm) {
        String[] temp = new String[filterIds.size()];
        temp = filterIds.toArray(temp);
        MessageUpdateSearchTerm msg = new MessageUpdateSearchTerm(searchTerm, add);
        serverSocket.sendToGroup(temp, msg);
    }

    @Override
    public void sendUpdateSearchTerms(String filterId, ArrayList<String> searchTerms){
        MessageSendSearchTerms msg = new MessageSendSearchTerms(searchTerms);
        serverSocket.sendTo(filterId, msg);
    }

    @Override
    public void sendSubscribeInfo(ArrayList<String> ids, Incident incident) {
        String[] temp = new String[ids.size()];
        temp = ids.toArray(temp);
        MessageUpdateIncident msg = new MessageUpdateIncident(incident, false);
        serverSocket.sendToGroup(temp, msg);
    }

    public void test(){
        MessageIncidentConclude msg = new MessageIncidentConclude(3);
        serverSocket.
    }
}
