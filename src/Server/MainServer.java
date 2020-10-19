package Server;

import Server.ServerThreads;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

/**
 * The purpose of this class is to provide a listening port and establish connections.
 *
 * The initiation of the programs begins with the creation of a new ServerSocket object.
 * This primarily allows the ability to listen to specific, specified ports. The ports
 * which are chosen will not already be dedicated elsewhere.
 *
 * The socket is then placed in to a continuous loop.
 * The serverSocket is to continuously accept client connections. The accept method helps us
 * achieve this. The accept method waits for clients to put a request through.
 *
 * The serverSocket constructor throws and exception. The exception is thrown when the socket
 * can't listen into a port, or the port is already is in use. The server from here, exits the
 * connection.
 */
public class MainServer {
    static ArrayList<ServerThreads> arr = new ArrayList<>();
//    static User user;

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(3333)) {         // Creation of a new ServerSocket object. This primarily listens to specific ports. It chooses a port which is not already dedicated.
                                                               // Continuous loop...
                while(true) {
                    ServerThreads sc = new ServerThreads(serverSocket.accept());
                    arr.add(sc);
                    Thread connectionThread = new Thread(sc);
                    connectionThread.start();

                }
        }   catch (IOException e) {                                             // Constructor for ServerSocket to throw an exception if it can't listen to a specified port.
            e.printStackTrace();
        }
    }
}

