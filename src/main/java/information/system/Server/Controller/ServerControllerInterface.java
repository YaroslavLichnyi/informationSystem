package information.system.Server.Controller;

/**
 * Determines which methods are needed to work with the server.
 * @author Yaroslav Lichnyi
 */
public interface ServerControllerInterface {
    /**
     * Start server.
     */
    void start() ;

    /**
     * Restart server.
     */
    void restart();

    /**
     * Stop server.
     */
    void stop();

    /**
     * Change server port.
     * @param port is a number of new port, which is set
     * @return true if the changing was successful, else return false.
     */
    boolean changePort(int port);

    /**TODO
     * public void createAccount();
     * public void signIn();
     * public void removeAccount();
     */
}
