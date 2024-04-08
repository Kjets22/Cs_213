package app;

import java.io.Serializable;

public class tag implements Serializable{
    private static final long serialVersionUID = 1L;
    
    String name, value;
    public tag(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName(){
        return name;
    }

    public String getValue(){
        return value;
    }

}
