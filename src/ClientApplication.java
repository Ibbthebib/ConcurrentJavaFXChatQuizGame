//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Scene;
//import javafx.scene.control.Button;
//import javafx.scene.control.Label;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.scene.paint.Color;
//import javafx.stage.Stage;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class ClientApplication extends Application {
//	private ArrayList<Thread> threads;
//	public static void main(String[] args){
//		launch();
//	}
//
//	@Override
//	public void stop() throws Exception {
//		// TODO Auto-generated method stub
//		super.stop();
//		for (Thread thread: threads){
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
//	public Scene chatroomUI(ChatRoomUI.User client) {
//		/* Make the root pane and set properties */
//		GridPane rootPane = new GridPane();
//		rootPane.setPadding(new Insets(20));
//		rootPane.setAlignment(Pos.CENTER);
//		rootPane.setHgap(10);
//		rootPane.setVgap(10);
//
//		/*
//		 * Make the Chat's listView and set it's source to the Client's chatLog
//		 * ArrayList
//		 */
//		ListView<String> chatListView = new ListView<String>();
//		chatListView.setItems(client.client.chatLog);
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
//				client.client.sendMessagetoServer( client.getName() + " : "  +chatTextField.getText());
//				chatTextField.clear();
//			}
//		});
//
//		/* Add the components to the root pane */
//		rootPane.add(chatListView, 0, 0);
//		rootPane.add(chatTextField, 0, 1);
//
//		/* Make and return the scene */
//		return new Scene(rootPane, 400, 400);
//
//	}
//
//	public Scene init(Stage primaryStage) {
//		/* Make the root pane and set properties */
//		GridPane rootPane = new GridPane();
//		rootPane.setPadding(new Insets(20));
//		rootPane.setVgap(10);
//		rootPane.setHgap(10);
//		rootPane.setAlignment(Pos.CENTER);
//
//		TextField nameField = new TextField();
//		TextField hostNameField = new TextField();
//		TextField portNumberField = new TextField();
//
//		Label nameLabel = new Label("Name ");
//		Label hostNameLabel = new Label("Host Name");
//		Label portNumberLabel = new Label("Port Number");
//		Label errorLabel = new Label();
//		Button submitClientInfoButton = new Button("Done");
//		submitClientInfoButton.setOnAction(new EventHandler<ActionEvent>() {
//			@Override
//			public void handle(ActionEvent Event) {
//				// TODO Auto-generated method stub
//				/* Instantiate the client class and start it's thread */
//				ChatRoomUI.User user;
//				try {
//					user = new ChatRoomUI.User(nameField.getText(),hostNameField.getText());
//					Thread userThread = new Thread(user);
//					userThread.setDaemon(true);
//					userThread.start();
//					threads.add(userThread);
//
//					/* Change the scene of the primaryStage */
//					primaryStage.close();
//					primaryStage.setScene(chatroomUI(user));
//					primaryStage.show();
//				} catch (NumberFormatException | IOException e) {
//					// TODO Auto-generated catch block
//					errorLabel.setTextFill(Color.RED);
//					errorLabel.setText("Invalid port number, try again");
//				}
//
//			}
//		});
//
//		/*
//		 * Add the components to the root pane arguments are (Node, Column
//		 * Number, Row Number)
//		 */
//		rootPane.add(nameField, 0, 0);
//		rootPane.add(nameLabel, 1, 0);
//		rootPane.add(hostNameField, 0, 1);
//		rootPane.add(hostNameLabel, 1, 1);
//		rootPane.add(portNumberField, 0, 2);
//		rootPane.add(portNumberLabel, 1, 2);
//		rootPane.add(submitClientInfoButton, 0, 3, 2, 1);
//		rootPane.add(errorLabel, 0, 4);
//		/* Make the Scene and return it */
//		return new Scene(rootPane, 400, 400);
//	}
//
//}
