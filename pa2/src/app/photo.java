package app;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class photo implements Serializable{
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

   public String get_caption(){
    return caption;
   }

    public void add_tag(String name,String value){
        tags.add(new tag(name,value ));
    } 

    public void delete_tag(String value, String name){
        for (tag tag : tags){
            if(value == tag.getName() && name == tag.getValue()){
                tags.remove(tag);
            }
        }
    }

}