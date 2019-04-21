package information.system.Server.Controller;

import java.util.Observer;

/**
 * Determines which methods are needed to work with the server.
 * @author Yaroslav Lichnyi
 */
public interface ServerControllerInterface extends Observer {
    /**
     * Starts server.
     */
    public void startServer();

    /**
     * Restarts server.
     */
    public void restartServer();

    /**
     * Created new thread, which represents new client.
     */
    public void startNewClient();

    /**
     * Stops server.
     */
    public void stopServer();

    /**
     * Changes server's configurations
     * @param port is a number of new port, which is set
     * @return true if the changing was successful, else return false.
     */
    public boolean changeConfiguration(int port);

    /**TODO
     * public void createAccount();
     * public void signIn();
     * public void removeAccount();
     */


}
