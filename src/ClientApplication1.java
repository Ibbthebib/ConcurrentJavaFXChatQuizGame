//import Database.Authentication;
//import Database.databases;
//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.*;
//import javafx.scene.input.MouseEvent;
//import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.GridPane;
//import javafx.scene.layout.HBox;
//import javafx.scene.layout.VBox;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class ClientApplication1 extends Application {
//	static User user;
//	private static ArrayList<User> onlineUsers;
//	private ArrayList<Thread> threads;
//private static Stage primaryStage;
//	public static void main(String[] args) {
//		launch();
//	}
//
//	@Override
//	public void stop() throws Exception {
//		// TODO Auto-generated method stub
//		super.stop();
//		for (Thread thread : threads) {
//			thread.interrupt();
//		}
//	}
//
//	@Override
//	public void start(Stage stage) throws Exception {
//		// TODO Auto-generated method stub
//		threads = new ArrayList<Thread>();
//		stage.setTitle("Group chat");
//		stage.setScene(init(stage));
//		stage.show();
//	}
//
//
//	public Scene chatroomUI(User client) {
//		/* Make the root pane and set properties */
//
//		GridPane rootPane = new GridPane();
//		rootPane.setPadding(new Insets(40));
//		rootPane.setAlignment(Pos.BASELINE_LEFT);
//
//		rootPane.setHgap(20);
//		rootPane.setVgap(30);
//		Button viewFriends = new Button("View friends");
//		Button onlineUsers = new Button("Online users");
//		Button playGame = new Button("Game");
//		Button revision = new Button("Revision");
//
//
//		/*
//		 * Make the Chat's listView and set it's source to the Client's chatLog
//		 * ArrayList
//		 */
//		ListView<String> chatListView = new ListView<String>();
//		chatListView.setItems(user.client.chatLog);
//		chatListView.getOnScrollTo();
//		chatListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
//			@Override
//			public void handle(MouseEvent event) {
//				/**
//				 * PrivateChat
//				 *
//				 */
//			}
//		});
//
//		viewFriends.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//
//			}
//		});
//		onlineUsers.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//
//			}
//		});
//
//		playGame.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//
//			}
//		});
//		revision.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				primaryStage.close();
//				QuizApp app = new QuizApp();
//				try {
//					primaryStage.setScene(app.createContent());
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
////						onlineUsers.add(user);
//
//				primaryStage.show();}
//
//		});
//
//		/*
//		 * Make the chat text box and set it's action to send a message to the
//		 * server
//		 */
//		TextField chatTextField = new TextField();
//		chatTextField.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				// TODO Auto-generated method stub
//				user.client.sendMessagetoServer(user.getName() + " : " + chatTextField.getText());
//				chatTextField.clear();
//			}
//		});
//
//		/* Add the components to the root pane */
//		rootPane.add(chatListView, 0, 0,5,4);
//		rootPane.add(chatTextField, 0, 4,5,3);
//		rootPane.add(viewFriends,6,1);
//		rootPane.add(playGame,6,2);
//		rootPane.add(onlineUsers,6,3);
//		rootPane.add(revision,6,4);
//
//		/* Make and return the scene */
//		return new Scene(rootPane, 600, 400);
//
//	}
//
//	public Scene reviseQuiz(Stage primaryStage){
//		this.primaryStage = primaryStage;
//		GridPane rootPane = new GridPane();
//
//
//
//		return new Scene(rootPane, 800,800);
//
//	}
//
//	public Scene init(Stage primaryStage) {
//this.primaryStage = primaryStage;
////		System.out.println(onlineUsers.size());
//		/* Make the root pane and set properties */
//		GridPane rootPane = new GridPane();
//		rootPane.setPadding(new Insets(20));
//		rootPane.setVgap(10);
//		rootPane.setHgap(10);
//		rootPane.setAlignment(Pos.CENTER);
//		// Username Label
//		Label userNameLabel = new Label("Username :");
//		userNameLabel.setPrefHeight(30);
//		userNameLabel.setPrefWidth(150);
//		// Username TextField
//		TextField userNameText = new TextField();
//		userNameText.setPrefHeight(30);
//		userNameText.setPrefWidth(200);
//		// UserNameLabel for the UserNameText
//		userNameLabel.setLabelFor(userNameText);
//
//		// PasswordLabel
//		Label passwordLabel = new Label("Password :");
//		passwordLabel.setPrefHeight(30);
//		passwordLabel.setPrefWidth(150);
//		// Password  TextField
//		TextField passwordText = new TextField();
//		passwordText.setPrefHeight(30);
//		passwordText.setPrefWidth(200);
//		// PasswordLabel for the PasswordText
//		passwordLabel.setLabelFor(passwordText);
//
//		VBox labelBox = new VBox();
//		labelBox.getChildren().add(userNameLabel);
//		labelBox.getChildren().add(passwordLabel);
//		labelBox.setAlignment(Pos.CENTER);
//		labelBox.setSpacing(20);
//
//		VBox textBox = new VBox();
//		textBox.getChildren().add(userNameText);
//		textBox.getChildren().add(passwordText);
//		textBox.setAlignment(Pos.CENTER);
//		textBox.setSpacing(20);
//
//		HBox signUpTextBox = new HBox();
//		signUpTextBox.getChildren().add(labelBox);
//		signUpTextBox.getChildren().add(textBox);
//		signUpTextBox.setAlignment(Pos.CENTER);
//		signUpTextBox.setSpacing(-10);
//
//		// button registerButton
//		Button loginButton = new Button("Login");
//		loginButton.setPrefHeight(30);
//		loginButton.setPrefWidth(150);
//		// register Button
//		Button registerButton = new Button("Register");
//		registerButton.setPrefHeight(30);
//		registerButton.setPrefWidth(150);
//
//		HBox buttonBox = new HBox();
//		buttonBox.getChildren().add(loginButton);
//		buttonBox.getChildren().add(registerButton);
//		buttonBox.setPrefHeight(50);
//		buttonBox.setPrefWidth(100);
//		buttonBox.setAlignment(Pos.CENTER);
//		buttonBox.setSpacing(10);
//
//		BorderPane pane = new BorderPane();
//		pane.setCenter(signUpTextBox);
//		pane.setBottom(buttonBox);
//
//		Scene scene = new Scene(pane, 600, 250);
//
//
//		primaryStage.setTitle("Login");
//		primaryStage.setScene(scene);
////		primaryStage.initStyle(StageStyle.DECORATED);
//		primaryStage.show();
//
//		Label errorLabel = new Label();
//		loginButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent Event) {
//				// TODO Auto-generated method stub
//				/* Instantiate the client class and start it's thread */
//
//				try {
//					user = new User(userNameText.getText(), passwordText.getText());
//					Thread userThread = new Thread(user);
//					userThread.setDaemon(true);
//					userThread.start();
//					threads.add(userThread);
//
//					if (Authentication.AuthenticateUser(userNameText.getText(), passwordText.getText()) == 1) {
//						/* Change the scene of the primaryStage */
//						primaryStage.close();
//						primaryStage.setScene(chatroomUI(user));
////						onlineUsers.add(user);
//
//						primaryStage.show();}
//
//					else{
//						errorLabel.setTextFill(Color.RED);
//						errorLabel.setText("You need to register!");
//					}
//
//				} catch (NumberFormatException | IOException e) {
//					// TODO Auto-generated catch block
//					errorLabel.setTextFill(Color.RED);
//					errorLabel.setText("Unexecpted error please shut down!");
//				}
//
//			}
//		});
//
//		registerButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//
//						/* Change the scene of the primaryStage */
//						primaryStage.close();
//						primaryStage.setScene(register(primaryStage));
//						primaryStage.show();}
//
//
//		});
//
//		/*
//		 * Add the components to the root pane arguments are (Node, Column
//		 * Number, Row Number)
//		 */
//		rootPane.add(userNameLabel, 0, 0);
//		rootPane.add(userNameText, 1, 0);
//		rootPane.add(passwordLabel, 0, 1);
//		rootPane.add(passwordText, 1, 1);
//		rootPane.add(loginButton, 0, 2);
//		rootPane.add(registerButton, 1, 2);
////		rootPane.add(submitClientInfoButton, 0, 3, 2, 1);
//		rootPane.add(errorLabel, 0, 4);
//		/* Make the Scene and return it */
//		return new Scene(rootPane, 600, 250);
//	}
//
//
//	public Scene register(Stage primaryStage) {
//this.primaryStage = primaryStage;
//		GridPane rootPane = new GridPane();
//		rootPane.setPadding(new Insets(20));
//		rootPane.setAlignment(Pos.CENTER);
//		rootPane.setHgap(10);
//		rootPane.setVgap(10);
//
//		// FirstName Label
//		Label firstNameLabel = new Label("First Name :");
//		firstNameLabel.setPrefHeight(30);
//		firstNameLabel.setPrefWidth(150);
//		// FirstName TextField
//		TextField firstNameText = new TextField();
//		firstNameText.setPrefHeight(30);
//		firstNameText.setPrefWidth(200);
//		// FirstNameLabel for the FirstNameText
//		firstNameLabel.setLabelFor(firstNameText);  // label is for this input box
//
//		// LastName Label
//		Label lastNameLabel = new Label("Last Name :");
//		lastNameLabel.setPrefHeight(30);
//		lastNameLabel.setPrefWidth(150);
//		// LastName TextField
//		TextField lastNameText = new TextField();
//		lastNameText.setPrefHeight(30);
//		lastNameText.setPrefWidth(200);
//		// LastNameLabel for the LastNameText
//		lastNameLabel.setLabelFor(lastNameText);
//
//		// Username Label
//		Label userNameLabel = new Label("Username :");
//		userNameLabel.setPrefHeight(30);
//		userNameLabel.setPrefWidth(150);
//		// Username TextField
//		TextField userNameText = new TextField();
//		userNameText.setPrefHeight(30);
//		userNameText.setPrefWidth(200);
//		// UserNameLabel for the UserNameText
//		userNameLabel.setLabelFor(userNameText);
//
//		// PasswordLabel
//		Label passwordLabel = new Label("Password :");
//		passwordLabel.setPrefHeight(30);
//		passwordLabel.setPrefWidth(150);
//		// Password  TextField
//		TextField passwordText = new TextField();
//		passwordText.setPrefHeight(30);
//		passwordText.setPrefWidth(200);
//		// PasswordLabel for the PasswordText
//		passwordLabel.setLabelFor(passwordText);
//
//		// E-mail Label
//		Label emailLabel = new Label("E-mail :");
//		emailLabel.setPrefHeight(30);
//		emailLabel.setPrefWidth(150);
//		// e-mail TextField
//		TextField emailText = new TextField();
//		emailText.setPrefHeight(30);
//		emailText.setPrefWidth(200);
//		// E-mail for the LastNameText
//		lastNameLabel.setLabelFor(emailText);
//
//
//		VBox labelBox = new VBox();
//		labelBox.getChildren().add(firstNameLabel);
//		labelBox.getChildren().add(lastNameLabel);
//		labelBox.getChildren().add(userNameLabel);
//		labelBox.getChildren().add(emailLabel);
//		labelBox.getChildren().add(passwordLabel);
//		labelBox.setAlignment(Pos.CENTER);
//		labelBox.setSpacing(20);
//
//		VBox textBox = new VBox();
//		textBox.getChildren().add(firstNameText);
//		textBox.getChildren().add(lastNameText);
//		textBox.getChildren().add(userNameText);
//		textBox.getChildren().add(emailText);
//		textBox.getChildren().add(passwordText);
//		textBox.setAlignment(Pos.CENTER);
//		textBox.setSpacing(20);
//
//		HBox signUpTextBox = new HBox();
//		signUpTextBox.getChildren().add(labelBox);
//		signUpTextBox.getChildren().add(textBox);
//		signUpTextBox.setAlignment(Pos.CENTER);
//		signUpTextBox.setSpacing(-10);
//
//		// button registerButton
//		Button submitButton = new Button("Submit");
//		submitButton.setPrefHeight(30);
//		submitButton.setPrefWidth(150);
//
//		// Cancel Button
//		Button cancelButton = new Button("Cancel");
//		cancelButton.setPrefHeight(30);
//		cancelButton.setPrefWidth(150);
//
//		HBox buttonBox = new HBox();
//		buttonBox.getChildren().add(submitButton);
//		submitButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent actionEvent) {
//				//Send details to database.
//				databases.insert(userNameText.getText(),lastNameText.getText(),firstNameText.getText(),emailText.getText(),passwordText.getText());
//				primaryStage.close();
//				primaryStage.setScene(init(primaryStage));
//				primaryStage.show();}
//
//
//		});
//
//		cancelButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent event) {
//				primaryStage.close();
//				primaryStage.setScene(init(primaryStage));
//				primaryStage.show();}
//
//		});
//
//
//		buttonBox.getChildren().add(cancelButton);
//		buttonBox.setPrefHeight(50);
//		buttonBox.setPrefWidth(100);
//		buttonBox.setAlignment(Pos.CENTER);
//		buttonBox.setSpacing(10);
//
//		BorderPane pane = new BorderPane();
//		pane.setCenter(signUpTextBox);
//		pane.setBottom(buttonBox);
//		Scene scene = new Scene(pane, 600, 250);
//
//
//		primaryStage.setTitle("Register");
//		primaryStage.setScene(scene);
////		primaryStage.initStyle(StageStyle.DECORATED);
//		primaryStage.show();
//
//		rootPane.add(userNameLabel, 0, 0);
//		rootPane.add(userNameText, 1, 0);
//		rootPane.add(passwordLabel, 0, 1);
//		rootPane.add(passwordText, 1, 1);
//		rootPane.add(firstNameLabel, 0, 2);
//		rootPane.add(firstNameText, 1, 2);
//		rootPane.add(lastNameLabel, 0, 3);
//		rootPane.add(lastNameText, 1, 3);
//		rootPane.add(emailLabel, 0, 4);
//		rootPane.add(emailText, 1, 4);
//		rootPane.add(cancelButton, 0, 5);
//		rootPane.add(submitButton, 1, 5);
//		/* Make the Scene and return it */
//		return new Scene(rootPane, 600, 250);
//
//
//}
//
//}
