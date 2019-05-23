package information.system.Server.Controller;

/**
 * Determines which methods are needed to work with the server.
 * @author Yaroslav Lichnyi
 */
public interface ServerControllerInterface {
    /**
     * Starts server.
     */
  //   void startServer() ;

    /**
     * Restarts server.
     */
    void restartServer();

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
