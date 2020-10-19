import java.io.Serializable;

public class QuizMessage implements Serializable {
    private static final long serialVersionUID = 7098525355326623206L;

    private String message;
    public QuizMessage(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
}
