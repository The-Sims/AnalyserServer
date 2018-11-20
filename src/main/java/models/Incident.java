package models;

import java.util.ArrayList;

public class Incident {

    int incidentId;
    String incidentName;
    String location;
    ArrayList<Tip> confirmedTips = new ArrayList<>();
    ArrayList<Tip> tips = new ArrayList<>();
    ArrayList<String> subscribedIds = new ArrayList<>();

    public Incident(int incidentId, String incidentName, String location){
        this.incidentId = incidentId;
        this.incidentName = incidentName;
        this.location = location;
    }

    public void addTip(Tip tip){
        tips.add(tip);
    }

    public void subscribe(String id, boolean subscribe){
        boolean exists = false;
        for(String i: subscribedIds){
            if (i.equals(id)) {
                exists = true;
                break;
            }
        }
        if (subscribe && !exists)
            subscribedIds.add(id);
        else if (!subscribe && exists)
            subscribedIds.remove(id);
    }

    public int getIncidentId(){
        return incidentId;
    }

    public void confirmTip(int tipId, boolean accepted){
        for(Tip t : tips){
            if (t.getTipId() == tipId){
                if(accepted)
                    confirmedTips.add(t);
                tips.remove(t);
                break;
            }
        }
    }
}
