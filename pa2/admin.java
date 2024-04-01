import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * admin
 */
public class admin {
  private List<user> users=new ArrayList();


  user get_user(String username){
    for (user user : users){
      if(username.equals(user.get_username())){
        return user;
      }
    }
    return null;
  }

  void add_user(String username){
    users.add(new user(username));
  }
  
  void delete_user(String username){
     for (user user : users){
      if(username.equals(user.get_username())){
        users.remove(user);
      }
    }  
  }
  
  void list_users(){
    for (user user : users){
      System.out.print(user.get_username()+"  ");
    }
  }
	
}
