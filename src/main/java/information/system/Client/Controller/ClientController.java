package information.system.Client.Controller;

import information.system.Server.Model.Dish;
import information.system.Server.Model.DishСategory;

import java.util.Observer;

/**
 * Determines which methods are needed for user interaction with the server.
 * @author Yaroslav Lichnyi
 */
public interface ClientController {
    /**
     * Creates JFrame with information about all dishes.
     */
    void showMenuFrame();

    /**
     * Creates JFrame with information about all dish categories.
     */
    void showDishCategoriesFrame();

    /**
     * Updates content, getting actual information from server.
     */
    void updateInformation();

    /**
     * Checks server connection.
     */
    void checkConnection();

    /**
     * Adds new object in the table.
     * @return true if the addition was successful, else return false.
     */
    boolean add(Dish dish);

    /**
     * Adds new object in the table.
     * @return true if the addition was successful, else return false.
     */
    boolean add(DishСategory dishСategory );


    /**
     * Edits existing data in the table.
     * @return true if the edition was successful, else return false.
     */
    boolean edit();

    /**
     * Edits existing objects in the table.
     * @return true if the deleting was successful, else return false.
     */
    boolean delete();

    /**
     * Updates content, getting actual information.
     * @return true if the update was successful, else return false.
     */
    boolean updateContent();

    void connectToServer();

    void exit();

    void sendRequest(String message);
}
