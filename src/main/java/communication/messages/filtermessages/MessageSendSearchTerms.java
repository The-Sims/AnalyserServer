package communication.messages.filtermessages;

import java.util.ArrayList;

public class MessageSendSearchTerms {
    ArrayList<String> searchTerms = new ArrayList<>();

    public MessageSendSearchTerms(ArrayList<String> searchTerms){
        this.searchTerms = searchTerms;
    }

    public ArrayList<String> getSearchTerms() {
        return searchTerms;
    }
}
