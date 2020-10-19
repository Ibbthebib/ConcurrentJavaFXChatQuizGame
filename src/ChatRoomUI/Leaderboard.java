package ChatRoomUI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

public class Leaderboard extends Application {
    private ObservableList leader = FXCollections.observableArrayList();

    public static void main(String[] args) {
        launch(args);
    }


    public void infoReciever(ObservableList<String> leaderList) {
        this.leader = leaderList;
    }

    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        // title
        Label editProfile = new Label("Who's the strongest???");
        editProfile.setFont(Font.font(20));
        editProfile.setLayoutX(50);
        editProfile.setLayoutY(50);

        // information can be changed

        ListView<String> leaderboard = new ListView<>();
        leaderboard.setItems(leader);
        leaderboard.refresh();


        Button back = new Button("Back");
        back.prefWidth(55);
        back.setLayoutX(174);
        back.setLayoutY(490);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });


        // setting for grid pane and adding label
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(50);
        gridPane.setLayoutY(50);

        gridPane.add(editProfile,0,0);
        gridPane.add(leaderboard, 0, 1);
        AnchorPane anchorPane = new AnchorPane(gridPane, back);
        primaryStage.setTitle("Leaderboard");
        primaryStage.setScene(new Scene(anchorPane, 400, 550));
        primaryStage.show();
    }
}
