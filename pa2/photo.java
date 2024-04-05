import java.util.Calendar;
import java.util.List;

public class photo{
    String caption; 
    String location;
    Calendar date;
    List<tag> tags;
    int photo_Id;
    public photo(String location, Calendar date, List<tag> tags,int photo_Id){
        this.location = location;
        this.date = date;
        this.tags = tags;
        this.photo_Id=photo_Id;
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

    public int getPhoto_Id(){
        return photo_Id;
    }

   public void change_capation(String caption){
       this.caption=caption; 
   }


}
