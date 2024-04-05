package app;
import java.util.Calendar;
import java.util.List;
import java.io.Serializable;

public class photo implements Serializable{

    String location;
    Calendar date;
    List<tag> tags;
    public photo(String location, Calendar date, List<tag> tags){
        this.location = location;
        this.date = date;
        this.tags = tags;
    }

    public String getLocation(){
        return location;
    }

    public Calendar getDate(){
        return date;
    }

    public List<tag> getTags(){
        return tags;
    }

   


}