package communication.messages.operatormessages;

public class MessageTipConfirm {
    int incidentId;
    int tipId;
    boolean confirm;

    public MessageTipConfirm(int incidentId, int tipId, boolean confirm){
        this.incidentId = incidentId;
        this.tipId = tipId;
        this.confirm = confirm;
    }

    public int getIncidentId() {
        return incidentId;
    }

    public int getTipId() {
        return tipId;
    }

    public boolean isConfirm() {
        return confirm;
    }
}
