package communication.messages.filtermessages;

public class MessageUpdateSearchTerm {
    String searchTerm;
    boolean addTerm;//false: remove from list; true:add to list

    public MessageUpdateSearchTerm(String searchTerm, boolean addTerm){
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
