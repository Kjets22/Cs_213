package app;

import java.io.Serializable;

/**
 * album
 */
public class Album implements Serializable {
  String name;
  
  public Album(String name){
    this.name=name;
  
  }
  String get_name(){
    return name;
  }
 	
}
