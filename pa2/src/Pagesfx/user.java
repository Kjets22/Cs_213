package Pagesfx;
import java.util.ArrayList;
import java.util.List;
/**
 * user
 */
public class user {
  private String username;
  private List<album> albums=new ArrayList<>();
  user (String username){
    this.username=username;
  }



  String get_username(){
    return username;
  }

	
  void add_album(String name){
    albums.add(new album(name));
  }
  
  void delete_album(String name){
    for (album album: albums){
      if(name.equals(album.get_name())){
        albums.remove(album);
      }
    }  
  }
  
  /*void list_users(){
    for (album album: albums){
      System.out.print(user.get_name()+"  ");
    }
  }*/
}
