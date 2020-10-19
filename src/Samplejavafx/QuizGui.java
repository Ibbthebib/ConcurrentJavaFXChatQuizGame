//package Samplejavafx;
//
//import javafx.application.Application;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class QuizGui extends Application implements Runnable {
//    private Stage primaryStage;
//    private ArrayList<Thread> threads;
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        this.primaryStage = primaryStage;
//        primaryStage.setScene(quiz(primaryStage));
//        primaryStage.setTitle("Quiz");
//        primaryStage.show();
//    }
//
//    public Scene quiz(Stage primaryStage) throws IOException {
//        this.primaryStage = primaryStage;
//
//        GridPane rootPane = new GridPane();
//        rootPane.setAlignment(Pos.CENTER);
//        QuizDataSet quiz = new QuizDataSet("/Users/ibbys/Desktop/QuizDataSet.txt");
//        Thread line = new Thread(quiz);
//        line.setDaemon(true);
//        line.start();
////        threads.add(line);
//
//
//        ListView<String> questions = new ListView<>();
//        ObservableList<String> ok = FXCollections.observableArrayList();
//
////        ok.add(QuizDataSet.questions.toString());
//
//        questions.setItems(ok);
//
//
////        questions.setItems(quiz.questions);
//        questions.setMinWidth(400);
//
//
//        TextField answer = new TextField();
//
//
//        rootPane.add(questions, 0, 0);
//        rootPane.add(answer, 0, 1);
//
//
//        return new Scene(rootPane, 600, 600);
//    }
//
//
//    @Override
//    public void run() {
//        QuizGui in = new QuizGui();
//        Thread quiz = new Thread(in);
//        quiz.setDaemon(true);
//        quiz.start();
//        threads.add(quiz);
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
