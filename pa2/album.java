import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.function.Consumer;
/**
 * album
 */
public class album {
  private List<photo> photos = new ArrayList();
  String name;
  int photo_number=0;

  album(String name){
    this.name=name;
  }

  String get_name(){
    return name;
  }

  void add_photo(String location, Calendar date, List<tag> tags){
    photos.add(new photo(location,date,tags,photo_number));
    photo_number++;
  }

  void remove_photo(int photo_Id){
    findphoto_action(photo_Id,photo->photos.remove(photo));
    }

  public void findphoto_action(int photo_Id,Consumer<photo> action){    
    for (photo photo : photos){
      if(photo_Id == photo.getPhoto_Id()){
        action.accept(photo);      
      }
    }
  }
  void caption_photo(int photo_Id,String caption){
    findphoto_action(photo_Id,photo->photo.change_capation(caption)); 
  }  
}
