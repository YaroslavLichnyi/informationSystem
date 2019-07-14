package information.system.server.controller;

import information.system.server.model.Restaurant;
import information.system.server.view.ServerViewGUI;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Server of client-server application for the restaurant.
 */
public class Server implements ServerControllerInterface {
    private static final Logger logger = Logger.getLogger(Server.class);
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ServerViewGUI view;
    private static LinkedList <ClientListener> clients = new LinkedList<>();
    private int port;
    private boolean portWasChanged;
    private Restaurant restaurant;
    private File file;
    private boolean running;

    public Server(ServerViewGUI view) {
        this.setRunning(false);
        file = new File("settings/server.ini");
        this.port = readPort();
        this.portWasChanged = false;
        restaurant = new Restaurant();
        this.view = view;

    }

    /**
     * Start server.
     */
    @Override
    public void start() {
        if (isRunning()) {
            view.logging("Fault: attempt to start running server.");
            logger.error("fault: attempt to start running server.");
            return;
        }
        // is being blocked until the new client connection
        Thread serverThread = new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                this.setRunning(true);
                while (true) {                                       // is being blocked until the new client connection
                    clientSocket = serverSocket.accept();
                    logger.info("New socket accepted.  User is attempting to login to the system.");
                    view.logging("New socket accepted. User is attempting to login to the system.");
                    clients.add(new ClientListener(clientSocket, this));
                }
            } catch (IOException e) {
                if (serverSocket != null && !serverSocket.isClosed()) {
                    try {
                        serverSocket.close();
                    } catch (IOException e1) {
                        logger.error("error closing server socket, ", e1);
                    }
                }
            }
        });
        serverThread.start();
        logger.info("server is running.");
        view.logging("server is running.");
        view.display("server is running");
    }

    /**
     * Restart server.
     */
    @Override
    public void restart() {
        stop();
        start();
    }

    /**
     * Stop server.
     */
    @Override
    public void stop() {
        if (this.isRunning()) {
            try {
                view.display("server is stopping");
                view.logging("server is stopping");
                logger.info("server is stoping.");
                // disconnect clients
                for (ClientListener clientListener: clients) {
                    clientListener.getSocket().close();
                    logger.info("client " + clientListener.getName() + " was disconnected.");
                }
                // stop server - close serverSocket
                if (serverSocket.isBound()) {
                    serverSocket.close();
                    this.setRunning(false);
                    view.display("server stopped and ready to start");
                    view.logging("server stopped.");
                    logger.info("server stopped.");
                }
            } catch (IOException e) {
                logger.error("error during server stopping, ", e);
            }
        } else {
            view.display("server stopped and ready to start");
            view.logging("Fault: attempt to stop a down server.");
            logger.info("fault: attempt to stop a down server.");
        }
    }

    /**
     * Change server port
     *
     * @param port is a number of new port, which is set
     * @return true if the changing was successful, else return false.
     */
    @Override
    public boolean changePort(int port) {
        if (port == this.port) {
            logger.info("port is the same and has not been changed.");
            view.logging("Port is the same and has not been changed.");
            return false;
        }
        if (port < 1_024 || port > 65_535) {
            logger.error("port cannot be less than 1025 or more than 65535.");
            view.logging("Port cannot be less than 1025 or more than 65535.");
            return false;
        }
        this.port = port;
        this.portWasChanged = true;
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            fw.write(String.valueOf(port));
            fw.close();
        } catch (IOException e) {
            logger.error("writing settings file error, ", e);
        }
        logger.info("port was successfully changed onto " + port + ".");
        view.logging("Port was successfully changed onto " + port + ".");
        return true;
    }

    /**
     * Getter for graphic view.
     * @return ServerViewGUI
     */
    public ServerViewGUI getView() {
        return view;
    }

    /**
     * Setter for graphic view.
     * @param view - ServerViewGUI
     */
    public void setView(ServerViewGUI view) {
        this.view = view;
    }

    /**
     * Getter for Restaurant.
     * @return Restaurant
     */
    Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Getter for ServerSocket.
     * @return ServerSocket
     */
    ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Get port from server.
     * @return int port
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Get port from file. Create file in case file does not exist.
     * @return port
     */
    int readPort() {

        int port = 8_000;

        if (file.exists()) {
            FileReader fr = null;
            try {
                fr = new FileReader(file);
            } catch (FileNotFoundException e) {
                logger.error("reading settings file error, ", e);
            }
            Scanner scan = new Scanner(fr);
            if (scan.hasNextInt()) {
                port = scan.nextInt();
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    logger.error("closing settings file error, ", e);
                }
            }
        } else {
            FileWriter fw;
            try {
                fw = new FileWriter(file);
                fw.write("8000");
                fw.close();
            } catch (IOException e) {
                logger.error("writing settings file error, ", e);
            }
        }

        return port;
    }

    /**
     * (Getter) Port was changed lately or not.
     * @return boolean portWasChanged
     */
    public boolean portChanged() {
        return portWasChanged;
    }

    /**
     * (Setter) Port was changed lately or not.
     */
    public void setPortChanged(boolean changed) {
        this.portWasChanged = changed;
    }

    /**
     * (Getter) server is running or not.
     * @return boolean running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * (Setter) server is running or not.
     * @param running boolean
     */
    void setRunning(boolean running) {
        this.running = running;
    }


/*    @Override
    public void update(Observable client, Object arg) {
        logger.info(client + " has lost connection. Cause is " + arg + ".");
        view.logging(client + " has lost connection. Cause is " + arg + ".");
    }
*/
}
