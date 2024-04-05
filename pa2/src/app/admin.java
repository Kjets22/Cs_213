package app;
import java.util.ArrayList;
import java.util.List;



/**
 * admin
 */
public class admin {
  private List<User> users=new ArrayList();


  User get_user(String username){
    for (User user : users){
      if(username.equals(user.get_username())){
        return user;
      }
    }
    return null;
  }

  void add_user(String username){
    users.add(new User(username));
  }
  
  void delete_user(String username){
     for (User user : users){
      if(username.equals(user.get_username())){
        users.remove(user);
      }
    }  
  }
  
  void list_users(){
    for (User user : users){
      System.out.print(user.get_username()+"  ");
    }
  }
	
}
