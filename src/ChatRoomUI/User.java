package ChatRoomUI;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class User extends Thread {
    Socket s;
    ObjectInputStream input;
    ObjectOutputStream output;
    ClientConnection client;
    private String host;
    private int port;
    static String userName;
    static String password;
    boolean isLoggedIn;
    private boolean isVerified = false;


    public static void main(String[] args) throws IOException {

//        new ChatRoomUI.User("localHost", 3333, "Ibrahim");
    }

    public User(String userName, String password) throws IOException {

        this.userName = userName;
        this.password = password;
        System.out.println(userName);
        this.isLoggedIn = true;
        s = new Socket("localhost", 3333);

        client = new ClientConnection(s, this);


    }


    public String getName1() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void logout() {
        isLoggedIn = false;
    }

    public void setVerifiedStatus(boolean value) {
        this.isVerified = value;
    }

    public boolean getVerified() {
        return isVerified;
    }

    @Override
    public void run() {

        client.start();


    }


}


