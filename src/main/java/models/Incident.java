package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Incident {

    int id;
    String place;
    ArrayList<Tip> tips = new ArrayList<>();
    ArrayList<String> subscribedIds = new ArrayList<>();
    Date createDate = new Date();
    Date modifyDate = new Date();
    Category category = new Category(1, "Explosie");
    boolean live = true;
    boolean confirmed;
    List<IncidentDescription> descriptions = new ArrayList<>();
    List<ReinforceInfo> reinforceInfo = new ArrayList<>();


    public Incident(int incidentId, String location){
        this.id = incidentId;
        this.place = location;
    }

    public Incident(String location){
        this.place = location;
    }

    public void addTip(Tip tip){
        if (tips == null)
            tips = new ArrayList<>();
        tips.add(tip);
    }

    public void subscribe(String id, boolean subscribe){
        if (subscribedIds == null)
            subscribedIds = new ArrayList<>();
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
        return id;
    }

    public Tip confirmTip(int tipId, boolean accepted){
        for(Tip t : tips){
            if (t.getTipId() == tipId){
                t.setAccepted();
                if(!accepted)
                    tips.remove(t);
                return t;
            }
        }
        return null;
    }

    public String getLocation() {
        return place;
    }

    public ArrayList<String> getSubscribedIds() {
        return subscribedIds;
    }

    public ArrayList<Tip> getTips() {
        ArrayList<Tip> temp = new ArrayList<>();
        for(Tip t:tips){
            if (!t.getAccepted())
                temp.add(t);
        }
        return temp;
    }

    public ArrayList<Tip> getConfirmedTips(){
        ArrayList<Tip> temp = new ArrayList<>();
        for(Tip t:tips){
            if (t.getAccepted())
                temp.add(t);
        }
        return temp;
    }
}
