import java.util.ArrayList;
import java.util.List;

/**
 * admin
 */
public class admin {
  private List<user> users=new ArrayList();



  void add_user(String username){
    users.add(new user(username));
  }
  
  void delete_user(String username){
    for (user user : users){
      if(username.equals(user.getusername())){
        users.remove(user);
      }
    }  
  }
  
  void list_users(){
    for (user user : users){
      System.out.print(user.getusername()+"  ");
    }
  }
	
}
