package GUI;

/**
 * @author Yiyi Feng
 * @version 2020-3-3
 * JavaFX Register class for registration screen 
 */

import Database.Authentication;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
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
import org.postgresql.util.PSQLException;

import java.io.IOException;

public class Login extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

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

        VBox labelBox = new VBox();
        labelBox.getChildren().add(userNameLabel);
        labelBox.getChildren().add(passwordLabel);
        labelBox.setAlignment(Pos.CENTER);
        labelBox.setSpacing(20);

        VBox textBox = new VBox();
        textBox.getChildren().add(userNameText);
        textBox.getChildren().add(passwordText);
        textBox.setAlignment(Pos.CENTER);
        textBox.setSpacing(20);

        HBox signUpTextBox = new HBox();
        signUpTextBox.getChildren().add(labelBox);
        signUpTextBox.getChildren().add(textBox);
        signUpTextBox.setAlignment(Pos.CENTER);
        signUpTextBox.setSpacing(-10);

        // button registerButton
        Button loginButton = new Button("Login");
        loginButton.setPrefHeight(30);
        loginButton.setPrefWidth(150);
        // register Button
        Button registerButton = new Button("Register");
        registerButton.setPrefHeight(30);
        registerButton.setPrefWidth(150);

        HBox buttonBox = new HBox();
        buttonBox.getChildren().add(loginButton);
        buttonBox.getChildren().add(registerButton);
        buttonBox.setPrefHeight(50);
        buttonBox.setPrefWidth(100);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(10);

        BorderPane pane = new BorderPane();
        pane.setCenter(signUpTextBox);
        pane.setBottom(buttonBox);

        Scene scene = new Scene(pane, 600, 250);


        primaryStage.setTitle("Login");
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }

}