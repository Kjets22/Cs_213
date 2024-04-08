package app;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * user
 */
public class user implements Serializable {
  private static final long serialVersionUID = 1L;
  private String username;
  private List<album> albums = new ArrayList<>();
  public user (String username){
    this.username=username;
  }
  user (){}
  album find_album(String name){
    for(album album : albums){
      if (album.get_name() == name){
        return album;
      }
    }
      return albums.get(1);
  }

  public String get_username(){
    return username;
  }

	
  public void add_album(String name){
    albums.add(new album(name));
  }

  public List<album> get_albums(){
    return albums;
  }
  
  void delete_album(String name){
    for (album album: albums){
      if(name.equals(album.get_name())){
        albums.remove(album);
      }
    }  
  }
  
  void list_users(){
    for (album album: albums){
      //System.out.print(()+"  ");
    }
  }

  void copy_photo_to_ablbum(photo photo,String album_name){
    album album = find_album(album_name);
    album.add_photo(photo);
  }
  
}