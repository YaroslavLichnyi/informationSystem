package information.system.Server.Controller;

import information.system.Server.Model.InformSystException;

import java.util.Observable;
import java.util.Observer;

/**
 * Determines which methods are needed to work with the server.
 * @author Yaroslav Lichnyi
 */
public interface ServerControllerInterface {
    /**
     * Starts server.
     */
    void startServer() throws InformSystException;

    /**
     * Restarts server.
     */
    void restartServer() throws InformSystException;

    /**
     * Created new thread, which represents new client.
     */
    void startNewClient();

    /**
     * Stops server.
     */
    void stopServer();

    /**
     * Changes server's configurations
     * @param port is a number of new port, which is set
     * @return true if the changing was successful, else return false.
     */
    boolean changeConfiguration(int port);

    /**TODO
     * public void createAccount();
     * public void signIn();
     * public void removeAccount();
     */


}
