package information.system.Server.Controller;
import information.system.Server.Model.InformSystException;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.Observable;

public class Server extends Observable implements ServerControllerInterface {
    private static final Logger LOGGER = Logger.getLogger(Server.class);
    private int portNumber;
    private ServerSocket serverSocket;

    public Server(int portNumber) throws InformSystException {
        this.portNumber = portNumber;
        startServer();
    }


    /**
     * Starts server.
     */
    @Override
    public void startServer() throws InformSystException {
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            LOGGER.error(e.toString());
            throw new InformSystException(e.toString() , "Problems with server starting");
        }
    }

    /**
     * Restarts server.
     */
    @Override
    public void restartServer() throws InformSystException {
        stopServer();
        startServer();
    }

    /**
     * Created new thread, which represents new client.
     */
    @Override
    public void startNewClient() {

    }

    /**
     * Stops server.
     */
    @Override
    public void stopServer() {

    }

    /**
     * Changes server's configurations
     *
     * @param port is a number of new port, which is set
     * @return true if the changing was successful, else return false.
     */
    @Override
    public boolean changeConfiguration(int port) {
        return false;
    }


}
