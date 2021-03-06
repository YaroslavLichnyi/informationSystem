package information.system.client.сontroller;

import information.system.server.model.User;
import information.system.server.model.Dish;
import information.system.server.model.DishCategory;

import java.io.IOException;
import java.util.List;


/**
 * Determines which methods are needed for user interaction with the server.
 * @author Yaroslav Lichnyi
 */
public interface ClientController {
    /**
     * Adds new object in the table.
     *
     * @return true if the addition was successful, else return false.
     */
    boolean add(Dish dish);

    /**
     * Adds new object in the table.
     *
     * @return true if the addition was successful, else return false.
     */
    boolean add(DishCategory dishCategory );


    /**
     * Edits existing dish.
     *
     * @param oldDish is an existing dish, that is changed.
     * @param newDish is a dish which parameters is set to existing dish.
     * @return true if the edition was successful, else return false.
     */
    boolean edit(Dish oldDish, Dish newDish);

    /**
     * Edits existing dish category.
     *
     * @param oldDishCategory is an existing dish category, that is changed.
     * @param newDishCategory is a dish category which parameters is set to existing dish category.
     * @return true if the edition was successful, else return false.
     */

    boolean edit(DishCategory oldDishCategory, DishCategory newDishCategory);

    /**
     * Edits existing user.
     *
     * @param oldUser is an existing user, that is changed.
     * @param newUser is an user which parameters is set to existing user.
     * @return true if the edition was successful, else return false.
     */
    boolean edit(User oldUser, User newUser);

    /**
     * Deletes existing dish.
     *
     * @return true if the deleting was successful, else return false.
     */
    boolean delete(Dish dish);

    /**
     * Deletes existing dish category.
     *
     * @return true if the deleting was successful, else return false.
     */
    boolean delete(DishCategory dishCategory);

    /**
     * Deletes existing user account.
     *
     * @param user is an user that is deleted.
     * @return true if user was deleted, else return false.
     */

    boolean delete(User user);

    /**
     * Updates content, getting actual information.
     * @return true if the update was successful, else return false.
     */
    boolean updateContent();

    /**
     * Connects to server
     */
    void connectToServer() throws IOException;

    /**
     * Disconnects from the server. Finishes the client session.
     */
    void exit();

    /**
     * Sends request to the server.
     *
     * @param message is sent as a request
     */
    void sendRequest(String message);

    /**
     * Checks if a user with the same password and login exists.
     *
     * @param login is the parameter by which the user is checked.
     * @param password is the parameter by which the user is checked.
     * @return user, if there is the user exists in user base, return null, if there is not the user exists in user base
     */
    User signIn(String login, String password);

    /**
     * Adds a new user to the database of existing ones.
     *
     * @param user is added as a new Admin
     * @return true if an <code>user</code> was added
     */
    boolean signUp(User user);


    /**
     * Signs out
     */
    void signOut();

    /**
     * Finds dishes which contain <code>subStr</code> in the name.
     *
     * @param subStr is the substring that the name must contain.
     * @return dishes which contain <code>subStr</code> in the name.
     */
    List<Dish> getDishesWhichContains(String subStr);

    /**
     * Sorts dishes by a criteria, which is pointed in parameter.
     * @param sortBy is a criteria, by which dishes are sorted.
     * @return list of dishes sorted by pointed criteria.
     */
    List <Dish> getDishesSortedBy(String sortBy);

    /**
     *  Shows general frame.
     */
    void showGeneralFrame();

    /**
     * Gets all users from storage.
     *
     * @return all users that are sighed up.
     */
    List<User> getAllUsers();

    /**
     * Gives the user admin rights.
     *
     * @param user is a user, that gets admin rights.
     * @return true if operation was successful, else return false.
     */
    boolean makeAdmin(User user);

}
