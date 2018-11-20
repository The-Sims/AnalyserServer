package models;

public class Filter {
    String filterId;
    String filterName;

    public Filter(String filterId, String filterName){
        this.filterId = filterId;
        this.filterName =filterName;
    }

    public String getFilterId(){
        return filterId;
    }

    public String getFilterName(){
        return filterName;
    }
}
