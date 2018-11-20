package communication.messages.filtermessages;

public class UpdateSearchTermMessage {
    String searchTerm;
    boolean addTerm;//false: remove from list; true:add to list

    public UpdateSearchTermMessage(String searchTerm, boolean addTerm){
        this.searchTerm=searchTerm;
        this.addTerm=addTerm;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public boolean DoAddTerm() {
        return addTerm;
    }
}
