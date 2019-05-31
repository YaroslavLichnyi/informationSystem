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

public class Server extends Observable implements ServerControllerInterface {
    private static final Logger logger = Logger.getLogger(Server.class);
    private ServerSocket serverSocket;
    private ServerViewGUI view;
    private LinkedList <ClientListener> clients = new LinkedList<>();
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
     * Starts server.
     */
    @Override
    public void start() {
        logger.info("server is starting.");
        view.logging("Server is starting.");
        view.display("server is running");
        try {
            serverSocket = new ServerSocket(port);
            this.setRunning(true);
            logger.info("server is running.");
            view.logging("Server is running.");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                view.display("new client accepted.");
                view.logging("New client accepted.");
                clients.add(new ClientListener(clientSocket,this));
            }
        } catch (IOException e) {
            logger.error("error server starting. " + e.getStackTrace());
            view.logging("Error server starting. " + e.getMessage());
        }
    }

    /**
     * Restarts server.
     */
    @Override
    public void restart() {
        stop();
        start();
    }

    /**
     * Stops server.
     */
    @Override
    public void stop() {
        try {
            view.display("server is stopping");
            view.logging("Server is stopping");
            serverSocket.close();
            this.setRunning(false);
            view.display("server stopped");
            view.logging("Server stopped.");
//            view.closeView();
        } catch (IOException e) {
            e.printStackTrace();
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
        logger.info("port was successfully changed onto " + port + ".");
        view.logging("Port was successfully changed onto " + port + ".");
        return true;
    }

    public ServerViewGUI getView() {
        return view;
    }

    public void setView(ServerViewGUI view) {
        this.view = view;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

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
     * Port was changed lately or not.
     * @return boolean portWasChanged
     */
    public boolean portChanged() {
        return portWasChanged;
    }

    public void setPortChanged(boolean changed) {
        this.portWasChanged = changed;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
