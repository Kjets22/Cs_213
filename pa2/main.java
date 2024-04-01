import java.util.Scanner;

/**
 * main
 */
public class main {
  public static void main(String[] args){
    admin admin=new admin();
    System.out.println("inside photo directory in the main the java file");
    String input=get_user_input("what is the username");
    if (input.equals("admin")){
      // You are now in the admin and can do admin commands
    }else{
      if(admin.get_user(input).equals(null)){
        System.out.println("the username does not exist");
        return;
      }
      user user=admin.get_user(input);
        
    }
  }
  static String get_user_input(String output){
    Scanner myObj = new Scanner(System.in);
    System.out.println(output);   
    String input = myObj.nextLine();
    return(input);
  }
  static void login_user(){
    
  }

  static void login_admin(){
    
  }



  
}

