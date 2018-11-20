package communication.messages.sharedmessages;

public class MessageTipConfirm {
    int tipId;
    boolean confirm;

    public MessageTipConfirm(int tipId, boolean confirm){
        this.tipId = tipId;
        this.confirm = confirm;
    }

    public int getTipId() {
        return tipId;
    }

    public boolean isConfirm() {
        return confirm;
    }
}
