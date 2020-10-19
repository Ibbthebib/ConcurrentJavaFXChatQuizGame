package ChatRoomUI;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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

public class MyProfile extends Application {
    String userName;
    String Name;
    String Email;
    String Program;
    String updatedUserName;
    String updatedEmail;
    String updatedProgram;

    public MyProfile() throws IOException {
    }


    public void recievingUserInfo(String username, String Name, String Email, String Program) {
        this.userName = username;
        this.Name = Name;
        this.Email = Email;
        this.Program = Program;
    }

    public void updateUserInfo(String userName, String Email, String Program){
        this.userName = userName;
        this.Email = Email;
        this.Program = Program;

    }

    public String getUserInfo(){
        return userName + "\n" + Email + "\n" + Program;
    }
    public String getUpdatedUserInfo(){
        System.out.println("GUI :" + updatedUserName);
        return updatedUserName + "\n" + updatedEmail + "\n" + updatedProgram;
    }

//    public static void main(String[] args){
//        launch(args);
//    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // title
        Label myProfile = new Label("My Profile");
        myProfile.setFont(Font.font(20));
        myProfile.setLayoutX(50);
        myProfile.setLayoutY(50);

        // grid pane for user label and information
        Label username = new Label("Username:");
        Label name = new Label("Name:");
        Label email = new Label("E-mail:");
        Label program = new Label("Program:");

        // information as label import from databases
        Label usernameDB = new Label(userName);
        Label nameDB = new Label(Name);
        Label emailDB = new Label(Email);
        Label programDB = new Label(Program);

        // setting for grid pane and adding label
        GridPane gridPane = new GridPane();
        gridPane.setLayoutX(50);
        gridPane.setLayoutY(102);
        gridPane.setPrefWidth(200);
        gridPane.setPrefHeight(120);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(name, 0, 0);
        gridPane.add(username, 0, 1);
        gridPane.add(email, 0, 2);
        gridPane.add(program, 0, 3);
        gridPane.add(usernameDB, 1, 0);
        gridPane.add(nameDB, 1, 1);
        gridPane.add(emailDB, 1, 2);
        gridPane.add(programDB, 1, 3);


        /*
         * edit account button and set action to ProfileEdit page
         */
        Button edit = new Button("Edit Account");
        edit.setLayoutX(162);
        edit.setLayoutY(245);
        edit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                ProfileEdit pe = null;
//                                    ClientApplicationDraft.profileEdit(new Stage());
            }
        });

        Button delete = new Button("Delete Account");
        delete.setLayoutX(50);
        delete.setLayoutY(245);
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
//                databases.delete();
            }
        });


        // connect grid pane to anchor pane as a children
        AnchorPane anchorPane = new AnchorPane(gridPane);
        anchorPane.getChildren().addAll(myProfile, edit, delete);

        Scene scene = new Scene(anchorPane);
        primaryStage.setScene(scene);
        primaryStage.setTitle("My Profile");
        primaryStage.setHeight(350);
        primaryStage.setWidth(330);
        primaryStage.show();

    }

    public void profileEdit(Stage primaryStage) {
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
               updateUserInfo(usernameDB.getText(),emailDB.getText(),programDB.getText());

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
