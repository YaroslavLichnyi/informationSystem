package information.system.Client.Controller;

import java.util.Observer;

/**
 * Determines which methods are needed for user interaction with the server.
 * @author Yaroslav Lichnyi
 */
public interface ClientController {
    /**
     * Creates JFrame with information about all dishes.
     */
    public void showMenuFrame();

    /**
     * Creates JFrame with information about all dish categories.
     */
    public void showDishCategoriesFrame();

    /**
     * Updates content, getting actual information from server.
     */
    public void updateInformation();

    /**
     * Checks server connection.
     */
    public void checkConnection();
}
