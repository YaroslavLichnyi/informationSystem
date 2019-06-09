package information.system.Server.Controller;

import information.system.Server.Model.*;
import information.system.Server.View.ServerViewGUI;
import org.apache.log4j.Logger;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Observable;
import java.util.Scanner;

/**
 * Server of client-server application for the restaurant.
 */
public class Server extends Observable implements ServerControllerInterface {
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
    private Thread serverThread;

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
        serverThread = new Thread( () -> {
            try {
                serverSocket = new ServerSocket(port);
                this.setRunning(true);
                while (true) {                                       // is being blocked until the new client connection
                    clientSocket = serverSocket.accept();
                    System.out.println("Client connected");
                    view.logging("New client accepted.");
                    clients.add(new ClientListener(clientSocket, this));
                }
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    try {
                        serverSocket.close();
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
        serverThread.start();
        logger.info("server is running.");
        view.logging("Server is running.");
        view.display("server is running");
    }

    /**
     * Restart server.
     */
    @Override
    public void restart() {
//        stop();
        logger.info("server is going to be restarted.");
        view.logging("Restarting server...");
        view.display("restarting...");
        if (this.isRunning()) {
            if (!clients.isEmpty()) {
                for (ClientListener clientListener: clients) {
                    try {
                        clientListener.getSocket().close();
                    } catch (IOException e) {
                        logger.error("client socket closing error.", e);
                        view.logging("Client socket closing error.");
                    }
                    logger.info("client " + clientListener.getName() + " was disconnected.");
                    view.logging("Client " + clientListener.getName() + " was disconnected.");
                }

            }
            try {
                serverSocket.close();
            } catch (IOException e) {
                logger.error("server socket closing error.", e);
                view.logging("Server socket closing error.");
            }
            logger.info("server was stopped.");
            view.logging("Server was stopped.");
        }
//        start();
        serverThread = new Thread( () -> {
            try {
                serverSocket = new ServerSocket(port);
                this.setRunning(true);
                // reconnect old clients
                for (ClientListener clientListener: clients) {
                    clientListener.setSocket(serverSocket.accept());
                }
                // connect of new clients
                while (true) {                                       // is being blocked until the new client connection
                    clientSocket = serverSocket.accept();
                    System.out.println("New client connected");
                    view.logging("New client accepted.");
                    clients.add(new ClientListener(clientSocket, this));
                }
            } catch (IOException e) {
                if (!serverSocket.isClosed()) {
                    try {
                        serverSocket.close();
                    } catch (IOException e1) {
                        logger.error("server socket closing error.", e1);
                        view.logging("Server socket closing error.");
                    }
                    logger.error("server socket error.", e);
                    view.logging("Server socket error.");
                }
            }
        });
        serverThread.start();
        logger.info("server is running.");
        view.logging("Server is running.");
        view.display("server is running");
    }

    /**
     * Stop server.
     */
    @Override
    public void stop() {
        if (this.isRunning()) {
            try {
                view.display("server is stopping");
                view.logging("Server is stopping...");
                logger.info("server is stoping.");
                // disconnect clients
                for (ClientListener clientListener: clients) {
                    clientListener.getSocket().close();
                    logger.info("client " + clientListener.getName() + " was disconnected.");
                }
                clients.clear();
                // stop server - close serverSocket
                if (serverSocket.isBound()) {
                    serverSocket.close();
                    this.setRunning(false);
                    view.display("server stopped and ready to start");
                    view.logging("Server stopped.");
                    logger.info("server stopped");
                }
            } catch (IOException e) {
                e.printStackTrace();
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
        FileWriter fw = null;
        try {
            fw = new FileWriter(file);
            fw.write(String.valueOf(port));
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // reconnect all clients onto new port
        if (!clients.isEmpty()) {
            try {
                serverSocket = new ServerSocket(port);
            } catch (IOException e) {
                logger.error("error creation server socket with new port. ", e);
                view.logging("Error creation server socket with new port.");
            }
            for (ClientListener clientListener: clients) {
                try {
                    clientListener.setSocket(serverSocket.accept());
                } catch (IOException e) {
                    logger.error("error reconnect clients onto new port. ", e);
                    view.logging("error reconnect clients onto new port.");
                }
            }
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
    public Restaurant getRestaurant() {
        return restaurant;
    }

    /**
     * Getter for ServerSocket.
     * @return ServerSocket
     */
    public ServerSocket getServerSocket() {
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
     * @return
     * @throws IOException
     */
    public int readPort() {

        int port = 8_000;

        if (file.exists()) {
            FileReader fr = null;
            try {
                fr = new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            Scanner scan = new Scanner(fr);
            if (scan.hasNextInt()) {
                port = scan.nextInt();
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            FileWriter fw = null;
            try {
                fw = new FileWriter(file);
                fw.write("8000");
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
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
     * @return boolean portWasChanged
     */
    public void setPortChanged(boolean changed) {
        this.portWasChanged = changed;
    }

    /**
     * (Getter) Server is running or not.
     * @return boolean running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * (Setter) Server is running or not.
     * @param running boolean
     */
    public void setRunning(boolean running) {
        this.running = running;
    }


}
