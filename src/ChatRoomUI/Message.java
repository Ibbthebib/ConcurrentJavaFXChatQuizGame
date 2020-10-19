package ChatRoomUI;

import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = 7098525355326623206L;

    private String message;

    public void setMessage(String message){
this.message = message;
    }

    public String getMessage(){
        return message;
    }

    public String toString(){
        return message;
    }



}
