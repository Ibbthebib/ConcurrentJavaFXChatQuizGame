package GUI;

/**
 * @author Yiyi Feng
 * @version 2020-3-3
 * JavaFX Register class for registration screen
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;

public class Register extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        // FirstName Label
        Label firstNameLabel = new Label("First Name :");
        firstNameLabel.setPrefHeight(30);
        firstNameLabel.setPrefWidth(150);
        // FirstName TextField
        TextField firstNameText = new TextField();
        firstNameText.setPrefHeight(30);
        firstNameText.setPrefWidth(200);
        // FirstNameLabel for the FirstNameText
        firstNameLabel.setLabelFor(firstNameText);  // label is for this input box

        // LastName Label
        Label lastNameLabel = new Label("Last Name :");
        lastNameLabel.setPrefHeight(30);
        lastNameLabel.setPrefWidth(150);
        // LastName TextField
        TextField lastNameText = new TextField();
        lastNameText.setPrefHeight(30);
        lastNameText.setPrefWidth(200);
        // LastNameLabel for the LastNameText
        lastNameLabel.setLabelFor(lastNameText);

        // Username Label
        Label userNameLabel = new Label("Username :");
        userNameLabel.setPrefHeight(30);
        userNameLabel.setPrefWidth(150);
        // Username TextField
        TextField userNameText = new TextField();
        userNameText.setPrefHeight(30);
        userNameText.setPrefWidth(200);
        // UserNameLabel for the UserNameText
        userNameLabel.setLabelFor(userNameText);

        // PasswordLabel
        Label passwordLabel = new Label("Password :");
        passwordLabel.setPrefHeight(30);
        passwordLabel.setPrefWidth(150);
        // Password  TextField
        TextField passwordText = new TextField();
        passwordText.setPrefHeight(30);
        passwordText.setPrefWidth(200);
        // PasswordLabel for the PasswordText
        passwordLabel.setLabelFor(passwordText);

        // E-mail Label
        Label emailLabel = new Label("E-mail :");
        emailLabel.setPrefHeight(30);
        emailLabel.setPrefWidth(150);
        // e-mail TextField
        TextField emailText = new TextField();
        emailText.setPrefHeight(30);
        emailText.setPrefWidth(200);
        // E-mail for the LastNameText
        lastNameLabel.setLabelFor(emailText);


        VBox labelBox = new VBox();
        labelBox.getChildren().add(firstNameLabel);
        labelBox.getChildren().add(lastNameLabel);
        labelBox.getChildren().add(userNameLabel);
        labelBox.getChildren().add(emailLabel);
        labelBox.getChildren().add(passwordLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(20);

        VBox textBox = new VBox();
        textBox.getChildren().add(firstNameText);
        textBox.getChildren().add(lastNameText);
        textBox.getChildren().add(userNameText);
        textBox.getChildren().add(emailText);
        textBox.getChildren().add(passwordText);
        textBox.setAlignment(Pos.CENTER);
        textBox.setSpacing(20);

        HBox signUpTextBox = new HBox();
        signUpTextBox.getChildren().add(labelBox);
        signUpTextBox.getChildren().add(textBox);
        signUpTextBox.setAlignment(Pos.CENTER);
        signUpTextBox.setSpacing(-10);

        // button registerButton
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(30);
        submitButton.setPrefWidth(150);

        // Cancel Button
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefHeight(30);
        cancelButton.setPrefWidth(150);

        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(submitButton);
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Send details to database.


            }
        });


        buttonBox.getChildren().add(cancelButton);
        buttonBox.setPrefHeight(50);
        buttonBox.setPrefWidth(100);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        BorderPane pane = new BorderPane();
        pane.setCenter(signUpTextBox);
        pane.setBottom(buttonBox);
        Scene scene = new Scene(pane, 600, 350);


        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}