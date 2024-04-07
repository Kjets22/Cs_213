package app;
import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;
import java.util.function.Consumer;
/**
 * album
 */
public class album extends user{
  private List<photo> photos = new ArrayList<photo>();
  String name;
  int photo_number=0;

  album(String name){
    this.name=name;
  }

  public String get_name(){
    return name;
  }

  public List<photo> get_photos(){
    return photos;
  }

  public void add_photo(String location, Calendar date, List<tag> tags){
    photos.add(new photo(location,date,tags,photo_number));
    photo_number++;
  }
  public void add_photo(photo photo){
    photos.add(photo);//jave might not have pointers but i am pretty sure this should work
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

  void add_tag(int photo_Id,String name,String value){
    findphoto_action(photo_Id,photo->photo.add_tag(name,value));
  }

  void delete_tag(int photo_Id,String name, String value){
    findphoto_action(photo_Id,photo->photo.delete_tag(name,value));
  }

  void copy_photo_to_album(int photo_Id, String album_name){
    findphoto_action(photo_Id,photo-> super.copy_photo_to_ablbum(photo,album_name) );
  }

  void replace_photo_to_album(int photo_Id, String album_name){
    findphoto_action(photo_Id,photo-> {
      super.copy_photo_to_ablbum(photo,album_name );
      photos.remove(photo);
    });
  }

  photo backward(photo photo){
    for(int i=0;i<photos.size();i++){
      if (photo == photos.get(i)){
        return photos.get(i++);
      }
    }
    return null;
  }
  
  photo forward(photo photo){
    for(int i=0;i<photos.size();i++){
      if (photo == photos.get(i)){
        return photos.get(i++);
      }
    }
    return null;
  }

  
}