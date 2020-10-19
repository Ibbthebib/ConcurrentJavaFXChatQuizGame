package Server;

import ChatRoomUI.Message;
import Database.Authentication;
import Database.DatabasePersonal;
import Database.DatabaseQuiz;
import org.postgresql.util.PSQLException;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Timestamp;
import java.util.HashMap;

import static Database.Authentication.AuthenticateUser;


/**
 * The purpose of this class is to have each connection run on a different thread,
 * allowing privacy for two clients conversation. The extends Thread allows the application
 * to have multiple threads executing. This achieves the concurrency of the program.
 */
public class ServerThreads implements Runnable {


    private Socket socket;                      // field variable for Socket
    ObjectOutputStream output;
    public HashMap<String, ObjectOutputStream> privateChat = new HashMap<>();
    Authentication auth = new Authentication();
    Message serverString;
    int leaderboardId = 1;
    int quizId = 1;


    /**
     * The following is a constructor for the Socket.
     *
     * @param socket one sided connection(s).
     */
    public ServerThreads(Socket socket) {       // Socket constructor
        this.socket = socket;
    }

    public void sendStringToClient(Object text) throws IOException {

        try {
            if (text instanceof String) {
                System.out.println(text);
                output.writeObject((String) (text));
                output.flush();
            } else {
                output.writeObject(text);
                output.flush();
            }
        } catch (SocketException e) {
            e.getMessage();
        }

    }

    public void sendPrivate(String username, String text) throws IOException {
        ObjectOutputStream sender = privateChat.get(username);
        sender.writeObject(text);
        sender.flush();
//        OnlineUsers.put(username, s);


    }

    public int verifyUser(String username, String password) throws IOException {
//        System.out.println(auth.AuthenticateUser(username, password));
        if (AuthenticateUser(username, password) == 1) {

            return 1;
        } else {
            return 0;
        }

    }

    public void registeration(String username, String Lastname, String Firstname, String email, String password) throws PSQLException {

        DatabasePersonal.insert(username, password, Lastname, Firstname, email);

    }


    public void sendStringtoAllClients(Object message) throws IOException {
        System.out.println("Connection pool " + MainServer.arr.size());

        try {
            for (int i = 0; i < MainServer.arr.size(); i++) {

                ServerThreads data = new MainServer().arr.get(i);
                if (message instanceof Message) {
                    data.sendStringToClient(message);
                } else {
//                    data.sendStringToClient(message);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateOnlineList() {

    }

    public int getUserId(String username) {
        return DatabasePersonal.searchID(username);

    }

    public String userDetails(int id) {

        return DatabasePersonal.searchUsername(id) + "\n" + DatabasePersonal.searchName(id) +
                "\n" + DatabasePersonal.searchEmail(id) + "\n" + DatabasePersonal.searchProgram(id);

    }

    public void updateUserDetails(String user, String userName, String email, String program) {
        DatabasePersonal.updatePersonalUsername(userName, DatabasePersonal.searchID(user));
        DatabasePersonal.updatePersonalEmail(email, DatabasePersonal.searchID(userName));
        DatabasePersonal.updatePersonalProgram(program, DatabasePersonal.searchID(userName));
    }

    public void deleteUserDetails(String userName) {
        DatabasePersonal.delete(DatabasePersonal.searchID(userName));

    }

    public void updateLeaderboard(String userName, String score) {

        if(DatabaseQuiz.selectByLeaderboardid(DatabasePersonal.searchID(userName)) == 0){

            DatabaseQuiz.creatQuizId(DatabasePersonal.searchID(userName),userName);
            DatabaseQuiz.updateQuizscore(Integer.parseInt(score), DatabasePersonal.searchID(userName));
            System.out.println("I dont exists");

        }else{
            System.out.println("I exist ");
        DatabaseQuiz.updateQuizscore(Integer.parseInt(score), DatabasePersonal.searchID(userName));}
    }

    public static Timestamp printTimeStamp() {
        java.util.Date date = new java.util.Date();
        return new Timestamp(date.getTime());
    }

    /**
     * The purpose of the following method is to retrieve the sockets input and output stream.
     * It further initiates communication. This is done from within the while loop.
     * As long as the client and server continue with their connection, the socket enables
     * messages to be sent back and forth between both parties.
     */
    @Override
    public void run() {
        try {
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
            output = new ObjectOutputStream(socket.getOutputStream());

            while (true) {
                /*
                ! represents normal user chat
                > represents database log in
                , represents database registering
                ¡ represents game scores
                º represents user details from database
                ª represents updating users details in database
                ¢ represents deleting users details in database
                § represents sending current leaderboard to client.
                 */
                serverString = (Message) input.readObject();

                System.out.println(serverString.getMessage());
                String[] splinter = serverString.getMessage().replaceFirst(">", "").split("\n");
                String[] registering = serverString.getMessage().replaceFirst(",", "").split("\n");
                String[] userDetails = serverString.getMessage().replaceFirst("ª", "").split(" ");
                String[] leaderboardUpdates = serverString.getMessage().replaceFirst("¡", "").split(" ");
//                for (String s : leaderboardUpdates) {
//                    System.out.println(s);
//                }
                for (String s : userDetails) {
                    System.out.println(s + " test");
                }

//                System.out.println(serverString);

                switch (serverString.getMessage().toCharArray()[0]) {
                    case ('!'):
                        serverString.setMessage(serverString.getMessage().replaceFirst("!", ""));
                        serverString.setMessage(serverString.getMessage() + "               at " + printTimeStamp());
                        if (serverString == null) {
                            System.out.println("ITs nullll");
                        }

                        //send username and users message to database for storage.
                        //
                        sendStringtoAllClients(serverString);
                        break;
                    case ('>'):
                        if (AuthenticateUser(splinter[0], splinter[1]) == 1) {
//                            privateChat.put(splinter[0], output);
                            serverString.setMessage(">");
                            sendStringToClient(serverString);
                            //Load users previous chat history from database
                            System.out.println("P");
                            sendStringtoAllClients(splinter[0] + " has logged in ");
                        } else {

                            sendStringToClient("Failed");
                            System.out.println("F");
                        }
                        break;
                    case (','):
                        System.out.println((registering[0] + " " + registering[1] + " " + registering[2] + " " + registering[3] + " " + registering[4]));

                        try {
                            registeration(registering[0], registering[1], registering[2], registering[3], registering[4]);
                        } catch (PSQLException e) {
                            System.out.println("User details already exist");
                        }
                        break;
                    case ('¡'):

                        updateLeaderboard(leaderboardUpdates[0], leaderboardUpdates[4]);
                        System.out.println(leaderboardUpdates[0]+ " " + leaderboardUpdates[4]);
                        sendStringtoAllClients(serverString);
                        break;
                    case ('º'):

                        serverString.setMessage("º" + userDetails(getUserId(serverString.getMessage().replaceFirst("º", ""))));

                        sendStringtoAllClients(serverString);
                    case ('ª'):

                        try {
                            updateUserDetails(userDetails[0], userDetails[1], userDetails[2], userDetails[3]);
                        } catch (ArrayIndexOutOfBoundsException e) {

                        }

                        System.out.println("successfully updated!");
                        break;
                    case ('¢'):

                        try {
                            deleteUserDetails(serverString.getMessage().replaceAll("¢", ""));
                        } catch (ArrayIndexOutOfBoundsException e) {

                        }
                        System.out.println("successfully deleted!");
                        break;
                    case ('§'):

                        serverString.setMessage("§" + DatabaseQuiz.rankQuizscore());
                        sendStringtoAllClients(serverString);
                        System.out.println(serverString.getMessage());
                        break;
                    default:
                        System.out.println(serverString.getMessage());

                }

            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("User has exited the chat ");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

