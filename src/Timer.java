import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Timer extends Application {
    private static final Font font = Font.font(18);
    int time = 10;
    String tmp = "";
    Label timer = new Label("10");
    Timeline animated = new Timeline();
    TranslateTransition timerMovement = new TranslateTransition();


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(createTimer(primaryStage));
        primaryStage.show();
    }

    public Scene createTimer(Stage primaryStage) {
        GridPane root = new GridPane();

        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setLayoutX(20);
        circle.setLayoutY(20);
        circle.setStroke(Color.PURPLE);


        root.getChildren().add(timer);
        root.getChildren().add(circle);
         timerMovement = new TranslateTransition();
        timerMovement.setToX(500);
        timerMovement.setDuration(Duration.seconds(11));
        timerMovement.setAutoReverse(false);
        timerMovement.setCycleCount(Animation.INDEFINITE);
        timerMovement.setNode(circle);
        timerMovement.play();

        animated = new Timeline(new KeyFrame(Duration.seconds(1), e -> settingLabel()));
        animated.setCycleCount(Timeline.INDEFINITE);
        animated.play();

        timer.setFont(font);


//        root.add(timer, 0, 0);


        return new Scene(root, 600, 600);
    }

    private void settingLabel() {
        if (time > 0) {
            time--;

        } else{
            timerMovement.stop();
        }
        tmp = time + "";
        timer.setText(tmp);
    }
}
