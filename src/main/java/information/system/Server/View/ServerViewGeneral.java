package information.system.Server.View;

/**
 * Interface for console and graphic components View of MVC-pattern of Information System.
 */
public interface ServerViewGeneral {

    /**
     * Display server state when it is necessary.
     * @param message
     */
    void display(String message);

    /**
     * Event handler for View closing.
     */
    void closeView();

    /**
     * Logging server events.
     * @param message
     */
    void logging(String message);

}
