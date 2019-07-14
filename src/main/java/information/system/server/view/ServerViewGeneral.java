package information.system.server.view;

/**
 * Interface for console and graphic components view of MVC-pattern of Information System.
 */
public interface ServerViewGeneral {

    /**
     * Display server state when it is necessary.
     * @param message
     */
    void display(String message);

    /**
     * Event handler for view closing.
     */
    void closeView();

    /**
     * Logging server events.
     * @param message
     */
    void logging(String message);

}
