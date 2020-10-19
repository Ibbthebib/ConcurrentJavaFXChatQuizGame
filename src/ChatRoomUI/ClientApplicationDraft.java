package ChatRoomUI;

import GUI.Login;
import Game.UserInterface;
import QuestionQuiz.QuestionReader;
import javafx.animation.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ClientApplicationDraft extends Application {
    static User user;
    private String username;
    private String password;
    //    private ArrayList<Thread> threads;
    private static Stage primaryStage;
    private Game.UserInterface game;
    private static final Font font = Font.font(36);
    private static final Font gameOverFont = Font.font(60);
    private static final Font sPaneFont = Font.font(18);
    private static sidePane spane = new sidePane();
    private QuestionPane Qpane = new QuestionPane();
    private static MyProfile profile;
    private static boolean changed = false;
//private String  textStyle= getClass().getResource("TextField.css").toExternalForm();

    QuestionReader read;
    int questioncounter = 0;
    ScheduledThreadPoolExecutor threadPool = new ScheduledThreadPoolExecutor(10);
    boolean isVerified = false;
    int score = 0;
    String temp = "";
    Label scorer = new Label("Score : 0");
    Media sound = new Media(new File("/Users/ibbys/Desktop/SWWProject/src/Countdown - Clock Only.mp3").toURI().toString());
    MediaPlayer player;


    int time = 60;
    String tmp = "";
    Label timer = new Label("Time : 60");
    Timeline animated = new Timeline();
    Label gameOverr = new Label("");
    ListView<String> scores = new ListView<>();


    TranslateTransition timerMovement = new TranslateTransition();
//    JavaSoundRecorder ibby = new JavaSoundRecorder();


    String userName;
    String Name;
    String Email;
    String Program;
    String updatedUserName;
    String updatedEmail;
    String updatedProgram;

    public void recievingUserInfo(String username, String Name, String Email, String Program) {
        this.userName = username;
        this.Name = Name;
        this.Email = Email;
        this.Program = Program;
    }

    public void updateUserInfo(String userName, String Email, String Program) {
        this.userName = userName;
        this.Email = Email;
        this.Program = Program;

    }

    public String getUserInfo() {
        return userName + "\n" + Email + "\n" + Program;
    }

    public String getUpdatedUserInfo() {
        System.out.println("GUI :" + updatedUserName);
        return updatedUserName + "\n" + updatedEmail + "\n" + updatedProgram;
    }


    public ClientApplicationDraft() throws IOException {
        read = new QuestionReader("/Users/ibbys/Desktop/SampleQuiz.txt");
    }


    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws Exception {
        // TODO Auto-generated method stub
//        threads = new ArrayList<Thread>();
        stage.setTitle("Group chat");
        starter(stage);
        stage.show();
    }


    public void chatroomUI(Stage primaryStage) throws IOException, InterruptedException {
        /* Make the root pane and set properties */
        System.out.println(username);
        user = new User(username, password);
        Thread userThread = new Thread(user);
        userThread.setDaemon(true);
        userThread.join();
        threadPool.execute(userThread);
        /*

         */

        Button viewLeaderboard = new Button("Leaderboard");
        Button playGame = new Button("Game");
        Button revision = new Button("Revision");
        Button viewProfile = new Button("My Profile");
        Button logOut = new Button("Log out");

        /*
         * functions box lay on left of the border
         * and has four button : play game, revision, online users, view profile.
         */
        VBox functionBox = new VBox();
        playGame.setStyle("-fx-background-color: #F5F5F5");
        playGame.setPrefHeight(100);
        playGame.setPrefWidth(100);

        revision.setPrefHeight(100);
        revision.setPrefWidth(100);
        revision.setStyle("-fx-background-color: #FFFFFF");

        viewLeaderboard.setPrefHeight(100);
        viewLeaderboard.setPrefWidth(100);
        viewLeaderboard.setStyle("-fx-background-color: #F5F5F5");

        viewProfile.setPrefHeight(100);
        viewProfile.setPrefWidth(100);
        viewProfile.setStyle("-fx-background-color: #FFFFFF");

        logOut.setPrefHeight(60);
        logOut.setPrefWidth(60);
        logOut.setLayoutY(140);
        logOut.setStyle("-fx-background-color: #FFFFFF");


//        functionBox.getChildren().add(logOut);

        functionBox.getChildren().addAll(playGame, revision, viewLeaderboard, viewProfile);

        VBox logout = new VBox();

        logout.getChildren().add(logOut);
        ListView<String> chatListView = new ListView<String>();
        chatListView.setItems(user.client.chatLog);

        chatListView.setStyle("-fx-background-color: #FFFFFF");
        chatListView.setPadding(new Insets(20));

        logout.setPrefWidth(200);
        logout.setStyle("-fx-background-color: #F5F5F5");

        chatListView.refresh();
        chatListView.setEditable(false);
        chatListView.getOnScrollTo();

        chatListView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                privateChat(new Stage());

            }
        });

        logOut.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                user.client.sendMessagetoServer("!" + user.getName1() + " has logged out");
                Platform.exit();

                System.exit(0);


            }
        });
        viewProfile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    /*
                    Input users details from database.
                     */
                    user.client.sendMessagetoServer("º" + username);
                    while (user.client.username == null) {
                        Thread.sleep(100);

                    }

                    String[] splitting = user.client.getCurrentUserDetails().split(" ");
//
                    recievingUserInfo(splitting[0], splitting[1] + " " + splitting[2], splitting[3], splitting[4]);

                    myProfile(new Stage());

//

//                    primaryStage.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        viewLeaderboard.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Leaderboard board = new Leaderboard();

                user.client.sendMessagetoServer("§");

                while (user.client.leaderboardScores == null) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                board.infoReciever(user.client.leaderboardScores);

                try {
                    board.start(new Stage());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });


        playGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                game = new UserInterface();
                game.start(new Stage());


            }
        });
        revision.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    createContent(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                primaryStage.show();
            }


        });
        /*
         * set chat text field and css
         */
        TextField chatTextField = new TextField();
        chatTextField.setStyle("-fx-background-radius:30px");
        chatTextField.setPrefWidth(400);
        chatTextField.setPrefHeight(30);
        chatTextField.setLayoutX(240);
        chatTextField.setLayoutY(15);
        chatTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user.client.sendMessagetoServer("!" + user.getName1() + " : " + chatTextField.getText());
                chatTextField.clear();
            }
        });

        // border pane for the whole layout
        BorderPane borderPane = new BorderPane();

        /*
         * set left of border pane
         */
        borderPane.setRight(functionBox);
        borderPane.setLeft(logout);
        borderPane.setCenter(chatListView);

        /*
         * set bottom of border pane
         * it has a anchor pane including a text field to text message.
         */
        AnchorPane apb = new AnchorPane();
        apb.setPrefHeight(60);
        apb.setStyle("-fx-background-color:#696969");
        apb.getChildren().addAll(chatTextField);

        /* Add the components to the root pane */
        borderPane.setBottom(apb);

        /* Make and return the scene */
        primaryStage.setScene(new Scene(borderPane, 800, 460));
        primaryStage.setTitle("Chat Room");
        primaryStage.show();


    }

    public void myProfile(Stage primaryStage) throws Exception {
        // title
        user = new User(user.getName1(), user.getPassword());
        Thread userThread = new Thread(user);
        userThread.setDaemon(true);
        threadPool.execute(userThread);
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
        Label usernameDB = new Label(Name);
        Label nameDB = new Label(userName);
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
                try {
                    profileEdit(new Stage());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        Button delete = new Button("Delete Account");
        delete.setLayoutX(50);
        delete.setLayoutY(245);
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user.client.sendMessagetoServer("¢" + user.getName1());
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

    public void profileEdit(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        user = new User(user.getName1(), user.getPassword());
        Thread userThread = new Thread(user);
        userThread.setDaemon(true);
        userThread.start();
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
                try {
                    profile = new MyProfile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                profile.updateUserInfo(usernameDB.getText(), emailDB.getText(), programDB.getText());

                user.client.sendMessagetoServer("ª" + user.getName1() + " " + usernameDB.getText() + " " + emailDB.getText() + " " + programDB.getText());
                changed = true;
                System.out.println(changed);
                if (changed = true) {
                    /*
                    Exit the program for security purposes.
                     */
                    Platform.exit();
                }

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
        gridPane.add(username, 0, 0);
        gridPane.add(email, 0, 1);
        gridPane.add(program, 0, 2);
        gridPane.add(usernameDB, 1, 0);
        gridPane.add(emailDB, 1, 1);
        gridPane.add(programDB, 1, 2);

        AnchorPane anchorPane = new AnchorPane(gridPane);
        anchorPane.getChildren().addAll(save, cancel, editProfile);
        primaryStage.setTitle("Edit Profile");
        primaryStage.setScene(new Scene(anchorPane, 300, 300));
        primaryStage.show();
    }

    public void privateChat(Stage primaryStage) {
        GridPane chatbox = new GridPane();

        ListView<String> chatlog = new ListView<>();
        chatlog.setItems(user.client.chatLog);
        TextField userInput = new TextField();
        userInput.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                user.client.sendMessagetoServer(userInput.getText());
            }
        });

        chatbox.add(chatlog, 0, 0);
        chatbox.add(userInput, 0, 1);

        Scene root = new Scene(chatbox);

        primaryStage.setScene(root);

        primaryStage.show();
    }

    public void createContent(Stage primaryStage) throws IOException {
        GridPane root = new GridPane();
        root.setHgap(50);
        root.setAlignment(Pos.CENTER);
        root.setVgap(50);
        root.setPadding(new Insets(50, 50, 50, 50));
        root.setPrefSize(1000, 600);

/*
Circle object that moves in sync with the countdown timer.
 */
        Circle circle = new Circle();
        circle.setRadius(20);
        circle.setLayoutX(20);
        circle.setLayoutY(20);
        circle.setStroke(Color.PURPLE);
        circle.setFill(Color.PURPLE);
/*
Timer animation system that allows the circle to move from right to left.
 */

        timerMovement = new TranslateTransition();
        /*
        Where it should reach on x
         */
        timerMovement.setToX(650);
        /*
        duration
         */
        timerMovement.setDuration(Duration.seconds(10));
        timerMovement.setAutoReverse(true);
        timerMovement.setCycleCount(Animation.INDEFINITE);
        timerMovement.setNode(circle);
        timerMovement.play();

        animated = new Timeline(new KeyFrame(Duration.seconds(1), e -> settingLabel()));
        animated.setCycleCount(Timeline.INDEFINITE);
        animated.play();
        timer.setFont(font);
        scorer.setFont(font);

        scores.setLayoutX(5);
        scores.setLayoutY(100);
        scores.setItems(user.client.gameScore);


        nextQuestion();
        Button close = new Button("Close");
        Button restart = new Button("Restart");

        root.add(Qpane, 0, 1, 3, 1);
        root.add(spane, 4, 1);
        root.add(timer, 2, 0);
        root.add(circle, 0, 3);
        root.add(scorer, 5, 0);
        root.add(gameOverr, 2, 2);
        root.add(scores, 5, 2, 1, 2);
        root.add(close, 0, 2);

        Scene scene = new Scene(root, 1100, 700);
        primaryStage.setScene(scene);
        primaryStage.show();
        close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player.dispose();
                player.stop();
                primaryStage.close();


            }
        });

    }

    public void nextQuestion() throws FileNotFoundException {
        /*
        Reads next question in from DB ideally.
         */

        Qpane.setQuestion(new Question(read.Question(questioncounter), read.Answer(questioncounter)));
        questioncounter++;
        spane.selectNext();

    }


    private class QuestionPane extends VBox {
        private Text text = new Text();
        private Question currentq;

        public QuestionPane() {
            text.setFont(font);
            HBox box = new HBox();

            Label correctA = new Label();
                /*
                Setting the user text for the game.
                 */
            Button nextQ = new Button();
            nextQ.setText("Next Question?");
            nextQ.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    try {
                        nextQuestion();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });


            TextField userInput = new TextField();
            userInput.setAlignment(Pos.CENTER);
            userInput.setLayoutX(50);
            userInput.setPrefColumnCount(50);
            userInput.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                        /*
                        Handles whether a correct question  has been  inputted. if so it shows the next question.
                         */
                    String[] userInp = userInput.getText().split(" ");
                    String[] correctAnswer = currentq.getCorrectAnswer().split(" ");


                    if (userInp[0].equals(correctAnswer[0])) {
                        correctA.setTextFill(Color.GREEN);
                        correctA.setText("Correct!");
                        userInput.clear();
                        score += 10;
                        temp = "Score : " + score;
                        scorer.setText(temp);

                        user.client.sendMessagetoServer("¡" + username + " current score : " + score);


                        try {
                            nextQuestion();
                        } catch (FileNotFoundException e) {

                        }

                    } else {
                        correctA.setTextFill(Color.RED);
                        correctA.setFont(font);
                        correctA.setText("Incorrect answer!");
                        score -= 5;
                        user.client.sendMessagetoServer("¡" + username + " current score : " + score);

                        temp = "Score : " + score;
                        scorer.setText(temp);

                    }
                }
            });

            box.getChildren().add(userInput);


            setAlignment(Pos.CENTER);

            getChildren().addAll(text, box, correctA, nextQ);


        }


        public void setQuestion(Question question) {
            /*
            Setting the current question for the user.
             */
            currentq = question;
            text.setText(question.name);
        }
    }

    private static class sidePane extends VBox {
        private int current = 0;

        /*
        Sidepane to keep track of completed and remaining questions.
         */
        public sidePane() {
            for (int i = 0; i < 15; i++) {
                Text text = new Text("Question : " + i);
                text.setFont(sPaneFont);
                text.setFill(i == current ? Color.BLACK : Color.GREY);
                getChildren().add(text);
            }
        }

        public void selectNext() {
            Text text = (Text) getChildren().get(0 + current);
            text.setFill(Color.GREY);
            current++;
            text.setFill(Color.BLACK);
        }
    }


    private static class Question {
        /*
        The shell of the Question and its possible answers.
         */
        private String name;
        private List<String> answers;

        public Question(String name, String... answers) {
            this.name = name;
            this.answers = Arrays.asList(answers);

        }

        public String getCorrectAnswer() {
            return answers.get(0);
        }
    }

    /*
    Setting countdown visual for Quiz plus stopping the visualisation once time has ran out.
     */
    private void settingLabel() {
        if (time > 0) {
            time--;

        } else {
            timerMovement.stop();
            gameOverr.setText("Game Over!");
            gameOverr.setFont(gameOverFont);
            gameOverr.setTextFill(Color.RED);

        }

        tmp = "Time : " + time;
        timer.setText(tmp);
        if (time == 30) {
            player = new MediaPlayer(sound);
            player.play();
        }

    }

    /*
    The inital screen.
     */
    public void starter(Stage primaryStage) {
        this.primaryStage = primaryStage;

        AnchorPane anchorPane = new AnchorPane();

        // welcome label
        Label welcome = new Label("WELCOME");
        welcome.setFont(Font.font("System", FontWeight.BOLD, 23));
        welcome.setLayoutX(190);
        welcome.setLayoutY(116);
        Label norwich = new Label("NORWICH.APP");
        norwich.setLayoutX(325);
        norwich.setLayoutY(125);

        // Username TextField
        TextField userNameText = new TextField();
        userNameText.setPrefHeight(30);
        userNameText.setPrefWidth(220);
        userNameText.setPromptText("username");

        // Password  PasswordField
        PasswordField passwordText = new PasswordField();
        passwordText.setPrefHeight(30);
        passwordText.setPrefWidth(220);
        passwordText.setPromptText("password");

        // login button and register button
        Button loginButton = new Button("Login");
        loginButton.setPrefWidth(65);
        loginButton.setPrefHeight(25);
        loginButton.setLayoutX(345);
        loginButton.setLayoutY(270);
        Button registerButton = new Button("Register");
        registerButton.setPrefWidth(65);
        registerButton.setPrefHeight(25);
        registerButton.setLayoutX(190);
        registerButton.setLayoutY(270);

        // set button css style
        loginButton.setStyle("-fx-background-radius:30;-fx-background-color: #B0E0E6");
        registerButton.setStyle("-fx-background-radius:30;-fx-background-color: #FFFFFF");

        // VBox store text fields
        VBox tfBox = new VBox();
        tfBox.getChildren().addAll(userNameText, passwordText);
        tfBox.setSpacing(20);
        tfBox.setLayoutX(190);
        tfBox.setLayoutY(170);
        tfBox.setPrefWidth(220);
        tfBox.setPrefHeight(100);
        Label errorLabel = new Label();

        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                try {
                    user = new User(userNameText.getText(), passwordText.getText());
                    username = userNameText.getText();
                    password = passwordText.getText();

                    Thread userThread = new Thread(user);
                    userThread.setDaemon(true);
                    threadPool.execute(userThread);


                    Service<Void> service = new Service<Void>() {
                        @Override
                        protected Task<Void> createTask() {
                            return new Task<Void>() {
                                @Override
                                protected Void call() throws Exception {
                                    user.client.sendMessagetoServer(">" + userNameText.getText() + "\n" + passwordText.getText());

                                    //Background work
                                    final CountDownLatch latch = new CountDownLatch(1);
                                    Platform.runLater(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                //FX Stuff done here
                                                if (user.client.isVerified != true) {
                                                    Thread.sleep(1000);
                                                }
                                                System.out.println("APP " + user.client.isVerified);
                                                if (user.client.isVerified == true) {

                                                    /* Change the scene of the primaryStage */
                                                    primaryStage.close();
                                                    chatroomUI(primaryStage);
                                                    primaryStage.show();
                                                } else {
                                                    errorLabel.setText("Please register!");
                                                    errorLabel.setTextFill(Color.RED);
                                                }

                                            } catch (InterruptedException | IOException e) {
                                                e.printStackTrace();
                                            } finally {
                                                latch.countDown();
                                            }
                                        }
                                    });
                                    //Keep with the background work
                                    return null;
                                }
                            };
                        }
                    };
                    service.start();


                } catch (NumberFormatException | IOException e) {
                    // TODO Auto-generated catch block
                    errorLabel.setTextFill(Color.RED);
                    errorLabel.setText("Unexecpted error please shut down!");
                }

            }
        });
        // register button to register page
        registerButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                /* Change the scene of the primaryStage */
                primaryStage.close();
                register(primaryStage);
                primaryStage.show();
            }
        });

        // remove the default focus from username textField
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        userNameText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                tfBox.requestFocus();
                firstTime.setValue(false);
            }
        });

        errorLabel.setTextFill(Color.RED);
        errorLabel.setLayoutX(255);
        errorLabel.setLayoutY(315);
        errorLabel.setVisible(true);

        anchorPane.getChildren().addAll(welcome, norwich, tfBox, loginButton, registerButton, errorLabel);
        Scene scene = new Scene(anchorPane, 600, 400);
//        scene.getStylesheets().add("ChatRoomUI/TextField.css");

        primaryStage.setTitle("Login");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void setVerified(boolean value) {
        isVerified = value;
    }


    public void register(Stage primaryStage) {

        Label register = new Label("REGISTER");
        register.setFont(Font.font("System", FontWeight.BOLD, 23));
        register.setLayoutX(70);
        register.setLayoutY(60);

        // FirstName TextField
        TextField firstNameText = new TextField();
        firstNameText.setPromptText("first name");
        firstNameText.setPrefHeight(30);
        firstNameText.setPrefWidth(220);

        // LastName TextField
        TextField lastNameText = new TextField();
        lastNameText.setPromptText("last name");
        lastNameText.setPrefHeight(30);
        lastNameText.setPrefWidth(220);

        // Username TextField
        TextField userNameText = new TextField();
        userNameText.setPromptText("username");
        userNameText.setPrefHeight(30);
        userNameText.setPrefWidth(220);

        // e-mail TextField
        TextField emailText = new TextField();
        emailText.setPromptText("e-mail");
        emailText.setPrefHeight(30);
        emailText.setPrefWidth(200);

        // Password  TextField
        PasswordField passwordText = new PasswordField();
        passwordText.setPromptText("password");
        passwordText.setPrefHeight(30);
        passwordText.setPrefWidth(220);

        VBox textBox = new VBox();
        textBox.setLayoutX(310);
        textBox.setLayoutY(65);
        textBox.getChildren().addAll(firstNameText, lastNameText, userNameText, emailText, passwordText);
        textBox.setAlignment(Pos.CENTER);
        textBox.setSpacing(20);

        // remove the default focus from username textField
        final BooleanProperty firstTime = new SimpleBooleanProperty(true);
        firstNameText.focusedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue && firstTime.get()) {
                textBox.requestFocus();
                firstTime.setValue(false);
            }
        });

        // button registerButton
        Button submitButton = new Button("Submit");
        submitButton.setPrefHeight(25);
        submitButton.setLayoutX(473);
        submitButton.setLayoutY(325);
        submitButton.setStyle("-fx-background-radius:30;-fx-background-color: #B0E0E6");
        submitButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println(Platform.isFxApplicationThread());

                try {
                    user = new User(userNameText.getText(), passwordText.getText());
                    Thread userThread = new Thread(user);
                    userThread.setDaemon(true);
                    threadPool.execute(userThread);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Service<Void> service = new Service<Void>() {
                    @Override
                    protected Task<Void> createTask() {
                        return new Task<Void>() {
                            @Override
                            protected Void call() throws Exception {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        user.client.sendMessagetoServer("," + userNameText.getText() + "\n" + lastNameText.getText() + "\n"
                                                + firstNameText.getText() + "\n" + emailText.getText() + "\n" + passwordText.getText());
                                    }
                                });

                                //Background work
                                //Keep with the background work
                                return null;
                            }
                        };
                    }
                };
                service.start();

                primaryStage.close();
                starter(primaryStage);
                primaryStage.show();


            }


        });

        // Cancel Button
        Button cancelButton = new Button("Cancel");
        cancelButton.setPrefHeight(25);
        cancelButton.setPrefWidth(55);
        cancelButton.setLayoutX(310);
        cancelButton.setLayoutY(325);
        cancelButton.setStyle("-fx-background-radius:30;-fx-background-color: #FFFFFF");
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                Login login = new Login();
                try {
                    login.start(new Stage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // set text VBox css style
//        textBox.getStylesheets().add(textStyle);

        Separator separator = new Separator();
        separator.setOrientation(Orientation.VERTICAL);
        separator.setLayoutX(245);
        separator.setLayoutY(65);
        separator.setPrefHeight(233);
        separator.setPrefWidth(9);

        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().addAll(register, textBox, separator, submitButton, cancelButton);
        Scene scene = new Scene(anchorPane, 600, 400);

        primaryStage.setTitle("Register");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
