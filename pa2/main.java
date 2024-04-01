import java.util.Scanner;

/**
 * main
 */
public class main {
  public static void main(String[] args){
    System.out.println("inside photo directory in the main the java file");
    String input=get_user_input("what is the username");
    if (input.equals("admin")){
      
    }
  }
  static String get_user_input(String output){
    Scanner myObj = new Scanner(System.in);
    System.out.println(output);   
    String input = myObj.nextLine();
    return(input);
  }
}

