package Samplejavafx;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FirstDraftQuizApp extends Application {

    private static final Font font = Font.font(18);
    private static sidePane spane = new sidePane();
private static QuestionPane pane = new QuestionPane();
    public static Scene createContent(){
        GridPane root = new GridPane();
        root.setHgap(50);
        root.setVgap(50);
        root.setPadding(new Insets(50,50,50,50));
        root.setPrefSize(800,600);
        pane.setQuestion(new Question("What is 2 + 2?", "4", "5", "6", "-1"));
        root.add(pane,0,0);
        root.add(spane,1,0);
        return new Scene(root,600,500);
    }

    public static void nextQuestion(){
        /*
        Reads next question in.
         */
        pane.setQuestion(new Question("What is 3 + 3?", "6","0","4","3"));
        spane.selectNext();
    }

    private static class QuestionPane extends VBox{
        private Text text = new Text();
        private List<Button> buttons =new ArrayList<>();
        private Question currentq;
        public QuestionPane(){
            super(20);
            text.setFont(font);
            HBox box = new HBox();
            for(int i = 0; i < 4; i++){
                /*
                Setting the buttons for the game.
                 */
                Button button = new Button();
                button.setPrefWidth(120);
                button.setFont(font);
                button.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        /*
                        Handles whether a correct question  has been  inputted. if so it shows the next question.
                         */
                        if(button.getText().equals(currentq.getCorrectAnswer())){
                            System.out.println("Correct");

                            nextQuestion();
                        } else{
                            System.out.println("incorrect!");
                        }
                    }
                });
                buttons.add(button);
                box.getChildren().add(button);


            }
            setAlignment(Pos.CENTER);
            getChildren().addAll(text,box);


        }
    public void setQuestion(Question question){
            /*
            Setting the current question.
             */
            currentq = question;
            text.setText(question.name);
        Collections.shuffle(buttons);
        for(int i = 0; i< 4; i++){
            buttons.get(i).setText(question.answers.get(i));
        }
    }
    }

    private static class sidePane extends VBox{
        private int current = 1;
        public sidePane(){
            for(int i = 0; i < 15; i++){
                Text text = new Text("Question : " + i);
                text.setFill(i== current ? Color.BLACK : Color.GREY);
                getChildren().add(text);
            }
        }
        public void selectNext(){
            if(current == 0){
                return;
            }
            Text text = (Text) getChildren().get(0 + current);
            text.setFill(Color.GREY);
            current++;
             text = (Text) getChildren().get(0 + current);
            text.setFill(Color.BLACK);
        }
    }

    private static class Question{
        /*
        The shell of the Question and its answers.
         */
        private String name;
        private List<String> answers;

        public Question(String name, String... answers){
            this.name = name;
            this.answers = Arrays.asList(answers);

        }
        public String getCorrectAnswer(){
            return answers.get(0);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
    primaryStage.setScene((createContent()));
    primaryStage.setTitle("Quiz");
    primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
