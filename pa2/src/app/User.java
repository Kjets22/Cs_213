package app;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * user
 */
public class User implements Serializable{
  public String username;

  public List<Album> albums=new ArrayList<>();
  private static final long serialVersionUID = 1L;

  public User (String username){
    this.username = username;
  }



  public String getUsername(){
    return username;
  }

	
  void add_album(String name){
    albums.add(new Album(name));
  }
  
  void delete_album(String name){
    for (Album album: albums){
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
