package information.system.Server.Controller;

import information.system.Server.Model.*;
import information.system.Server.View.ServerView;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;

public class Server extends Observable implements ServerControllerInterface {
    private static final Logger LOGGER = Logger.getLogger(Server.class);
    private int portNumber;
    private ServerSocket serverSocket;
    private ServerView view;
    private LinkedList <ClientListener> clients = new LinkedList<>();
    static private int port = 8000;
    private Restaurant restaurant;

    public static void main(String[] args){
        new Server(port);
    }

    public Server(int portNumber) {
        this.portNumber = portNumber;
        restaurant = new Restaurant();
        view = new ServerView();
        startServer();
    }


    /**
     * Starts server.
     */
   // @Override
      public void startServer() {
        try {
            view.display("Server starts to work");
            serverSocket = new ServerSocket(port);
            while (true){
                Socket clientSocket = serverSocket.accept();
                view.display("New client detected");
                clients.add(new ClientListener(clientSocket,this));
            }
        } catch (IOException e) {
            LOGGER.error(e.toString());
        }
    }

    /**
     * Restarts server.
     */
    @Override
    public void restartServer() {
        stopServer();
        startServer();
    }

    /**
     * Stops server.
     */
    @Override
    public void stopServer() {
        try {
            view.display("Server is stopped");
            serverSocket.close();
            view.closeView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Changes server's configurations
     *
     * @param port is a number of new port, which is set
     * @return true if the changing was successful, else return false.
     */
    @Override
    public boolean changeConfiguration(int port) {
        if (port < 1024) return false;
        this.port = port;
        return true;
    }

    public ServerView getView() {
        return view;
    }

    public void setView(ServerView view) {
        this.view = view;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public ServerSocket getServerSocket() {
        return serverSocket;
    }
/*
    public void sendFile(){
        try {
            // Get the size of the file
            File file = new File(Command.CLIENT_FILE_RESTAURANT);
            long length = file.length();
            byte[] bytes = new byte[16 * 1024];
            InputStream in = new FileInputStream(file);
            OutputStream out =  clientSocket.getOutputStream();
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            out.close();
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    */
}
