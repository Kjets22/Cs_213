package app;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class photo implements Serializable{
    String caption; 
    String location;
    Calendar date;
    private List<tag> tags = new ArrayList<>();
    int photo_Id;
    private List<album> albums = new ArrayList<>();
    private static final long serialVersionUID = 1L;
    public photo(String location, Calendar date){
        this.location = location;
        this.date = date;
    }

    public String getLocation(){
        return location;
    }

    public void setTags(List<tag> newTags){
        tags = newTags;
    }

    public Calendar getDate(){
        return date;
    }

    public List<tag> getTags(){
        return tags;
    }

    public List<album> getAlbums(){
        return albums;
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

    public void add_tag(tag e){
        getTags().add(e);
    } 

    public void add_album(album e){
        albums.add(e);
    } 

    public void delete_tag(String value, String name){
        for (tag tag : tags){
            if(value == tag.getName() && name == tag.getValue()){
                tags.remove(tag);
            }
        }
    }

}