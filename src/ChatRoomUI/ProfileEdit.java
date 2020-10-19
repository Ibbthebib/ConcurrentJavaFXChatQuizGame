package ChatRoomUI;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileEdit extends Application {
MyProfile profile = new MyProfile();

    public ProfileEdit() throws IOException {
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // title
        Label editProfile = new Label("Edit Profile");
        editProfile.setFont(Font.font(20));
        editProfile.setLayoutX(50);
        editProfile.setLayoutY(50);

        // information can be changed
        Label username = new Label("Username:");
        Label email = new Label("E-mail:");
        Label program = new Label("Program:");

        TextField usernameDB = new TextField();
        usernameDB.setPrefWidth(100);
        TextField emailDB = new TextField();
        emailDB.setPrefWidth(100);
        TextField programDB = new TextField();
        programDB.setPrefWidth(100);


        Button save = new Button("Save");
        save.prefWidth(55);
        save.setLayoutX(174);
        save.setLayoutY(222);
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                profile.updateUserInfo(usernameDB.getText(),emailDB.getText(),programDB.getText());

                primaryStage.close();
            }
        });

        Button cancel = new Button("Cancel");
        cancel.prefWidth(55);
        cancel.setLayoutX(70);
        cancel.setLayoutY(222);
        cancel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });

        // setting for grid pane and adding label
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(50);
        gridPane.setLayoutY(102);
        gridPane.setPrefWidth(200);
        gridPane.setPrefHeight(120);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(username,0,0);
        gridPane.add(email,0,1);
        gridPane.add(program,0,2);
        gridPane.add(usernameDB,1,0);
        gridPane.add(emailDB,1,1);
        gridPane.add(programDB,1,2);

        AnchorPane anchorPane = new AnchorPane(gridPane);
        anchorPane.getChildren().addAll(save,cancel,editProfile);
        primaryStage.setTitle("Edit Profile");
        primaryStage.setScene(new Scene(anchorPane, 300, 300));
        primaryStage.show();
    }
}
