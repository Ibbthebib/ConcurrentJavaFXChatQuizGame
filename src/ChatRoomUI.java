//import javafx.application.Application;
//import javafx.event.ActionEvent;
//import javafx.event.EventHandler;
//import javafx.geometry.Insets;
//import javafx.geometry.Pos;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.ListView;
//import javafx.scene.control.TextField;
//import javafx.scene.layout.GridPane;
//import javafx.stage.Stage;
//
//public class ChatRoomUI extends Application {
//
//    User user;
//    Stage primaryStage;
//    public Parent chatroomUI(Stage primaryStage) {
//        /* Make the root pane and set properties */
//        GridPane rootPane = new GridPane();
//        rootPane.setPadding(new Insets(20));
//        rootPane.setAlignment(Pos.CENTER);
//        rootPane.setHgap(10);
//        rootPane.setVgap(10);
//
//        /*
//         * Make the Chat's listView and set it's source to the Client's chatLog
//         * ArrayList
//         */
//        ListView<String> chatListView = new ListView<String>();
//        chatListView.setItems(user.client.chatLog);
//
//        /*
//         * Make the chat text box and set it's action to send a message to the
//         * server
//         */
//        TextField chatTextField = new TextField();
//        chatTextField.setOnAction(new EventHandler<ActionEvent>() {
//            @Override
//            public void handle(ActionEvent event) {
//                // TODO Auto-generated method stub
//                user.client.sendMessagetoServer( user.getName() + " : "  +chatTextField.getText());
//                chatTextField.clear();
//            }
//        });
//
//        /* Add the components to the root pane */
//        rootPane.add(chatListView, 0, 0);
//        rootPane.add(chatTextField, 0, 1);
//
//        /* Make and return the scene */
//        return rootPane;
//
//    }
//
//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        this.primaryStage = primaryStage;
//        primaryStage.setScene(new Scene(chatroomUI(primaryStage)));
//        primaryStage.show();
//    }
//
//    public static void main(String[] args) {
//        launch();
//    }
//}
