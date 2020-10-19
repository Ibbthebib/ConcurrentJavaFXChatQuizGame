package ChatRoomUI;

import Server.ServerThreads;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import sun.dc.pr.PRError;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class ClientConnection extends Thread {
    Socket socket;
    ObjectInputStream input;
    ObjectOutputStream output;
    public User user;
    ServerThreads s = new ServerThreads(socket);
    ScheduledThreadPoolExecutor threadpool = new ScheduledThreadPoolExecutor(10);
    public ObservableList<String> chatLog = FXCollections.observableArrayList();
    public ObservableList<String> gameScore = FXCollections.observableArrayList();
    public ObservableList<String> leaderboardScores = FXCollections.observableArrayList();

    public boolean isVerified = false;
    Media sound = new Media(new File("/Users/ibbys/Desktop/SWWProject/iphone-ding-sound.mp3").toURI().toString());
    Media ping = new Media(new File("/Users/ibbys/Desktop/SWWProject/src/Bouncy_Bounce-Bugs_Bunny-1735935456.mp3").toURI().toString());
    MediaPlayer mediaPlayer;
    Message inputFromServer;
    String signals;
    String username;
    String Name;
    String Email;
    String Program;


    public ClientConnection(Socket socket, User user) throws IOException {
        this.socket = socket;
        this.user = user;


    }


    public void sendMessagetoServer(String data) {
        try {
            Message message = new Message();
            message.setMessage(data);
            System.out.println("IM the data " + message.getMessage());
            output.writeObject(message);
            output.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String activeMembers(ArrayList<String> users) {
        String sample = "";
        for (String s : users) {
            sample += s + "\n";
        }
        return sample;
    }

    public void setVerifiedStatus(boolean value) {
        this.isVerified = value;
    }

    public String getCurrentUserDetails() {
        return username + " " + Name + " " + Email + " " + Program;
    }

    public void setCurrentUserDetails(String username, String Name, String Email, String Program) {
        this.username = username;
        this.Name = Name;
        this.Email = Email;
        this.Program = Program;
    }

    public void run() {

        try {


            output = new ObjectOutputStream(socket.getOutputStream());
            input = new ObjectInputStream(socket.getInputStream());

//            sendMessagetoServer("Username: " + user.getName());


            while (true) {
                try {


//                    String inputFromServer = (String) input.readObject();


                    try {
                        inputFromServer = (Message) input.readObject();
                    } catch (ClassCastException e) {
                        System.out.println("Serialisable object issue");
                    }


                    if (inputFromServer == null) {
                        System.out.println("Getting null pointer");
                    }

                    System.out.println(signals);
                    if (inputFromServer.getMessage().equals(">")) {
                        setVerifiedStatus(true);
                    } else {

                        if (inputFromServer.getMessage().substring(0, 1).equals("º")) {
                            String[] splinter = inputFromServer.getMessage().replaceFirst("º", "").split("\n");

try{                            setCurrentUserDetails(splinter[0], splinter[1], splinter[2], splinter[3]);} catch(ArrayIndexOutOfBoundsException e){
    System.out.println(e.getMessage());
}
                        } else if (inputFromServer.getMessage().substring(0, 1).equals("§")) {


                            if(leaderboardScores.contains("\n NEWEST SCORES \n" + inputFromServer.getMessage().replaceAll("§",""))){
                                leaderboardScores.add(" \n SCORES HAVE NOT CHANGED SINCE \n");
                            }else {
                                Platform.runLater(new Runnable() {
                                    @Override
                                    public void run() {
                                        leaderboardScores.add("\n NEWEST SCORES \n" + inputFromServer.getMessage().replaceAll("§", ""));

                                    }
                                });
                            }
                        } else if (inputFromServer.getMessage().substring(0, 1).equals("¡")) {

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {

                                    gameScore.add(inputFromServer.getMessage().replaceAll("¡", ""));

                                }
                            });

                        } else {
/*
                    Essentially the event dispatch thread for JavaFX
 */

                            Platform.runLater(new Runnable() {

                                public void run() {
//                            System.out.println(inputFromServer);

                                    chatLog.add(inputFromServer.getMessage());
                                    if (!inputFromServer.getMessage().contains("PING")) {
                                        System.out.println("Hey im not working");
                                        mediaPlayer = new MediaPlayer(sound);
                                        mediaPlayer.play();
                                    } else {
                                        mediaPlayer = new MediaPlayer(ping);
                                        mediaPlayer.play();

                                    }

                                }

                            });
                        }
                    }
                } catch (NullPointerException e) {

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            close();

        }

    }


    public void close() {
        try {
            input.close();
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

