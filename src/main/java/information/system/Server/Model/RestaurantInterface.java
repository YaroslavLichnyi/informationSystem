package information.system.Server.Model;

import information.system.Server.Model.Dish;
import information.system.Server.Model.DishСategory;

import java.util.ArrayList;
import java.util.List;

/**
 * Interface created to work with classes <b>Dish</b> and <b>DishСategory</b>.
 * @author Yaroslav Lichnyi
 */
public interface RestaurantInterface {
    /**
     * Collection of dishes.
     */
    ArrayList<Dish> menu = new ArrayList<>();
    /**
     * Collection of dish categories.
     */
    ArrayList<DishСategory> dishCategories = new ArrayList<>();

    /**
     * Adds new dish category at {@link RestaurantInterface#dishCategories}.
     * @param name is set as name of new dish category.
     * @return true is the dish category was added, else false, if there is already the same dish category.
     */
    boolean addDishCategory(String name);

    /**
     * Sets variable value {@link RestaurantInterface#dishCategories}.
     * @param index is a place of a dish in the list, which You get.
     * @return DishCategory from {@link RestaurantInterface#dishCategories}.
     */
    DishСategory getDishCategory(int index);

    /**
     * Removes dish from {@link RestaurantInterface#dishCategories}.
     * @param dishCategory is removed from {@link RestaurantInterface#dishCategories}.
     * @return true if an object was removed, else return false.
     */
    boolean removeDishCategory(DishСategory dishCategory);

    /**
     * Adds new dish at {@link RestaurantInterface#menu}.
     * @param dish is added as new Dish to {@link RestaurantInterface#menu}.
     * @return true is the dish was added, else false, if there is already the same dish.
     */
    boolean addDish(Dish dish);

    /**
     * Gets a dish from @link RestaurantInterface#menu}.
     * @param index is a place of a dish in the list, which You get.
     * @return Dish from {@link RestaurantInterface#menu}.
     */
    Dish getDish(int index);

    /**
     * Removes a dish from @link RestaurantInterface#menu}.
     * @param dish is removed from {@link RestaurantInterface#menu}.
     * @return true if an object was removed, else return false.
     */
    boolean removeDish(Dish dish);

    /**
     * Sorts dished by dish price.
     * @param menu is a collection that is sorted
     * @return collection of dishes ordered by price by descent.
     */
    ArrayList<Dish> sortDishesByPrice(ArrayList<Dish> menu);

    /**
     * Sorts dished by dish category.
     * @return collection of dishes ordered by dish category.
     */
    List<Dish> sortDishesByDishCategory();

    /**
     * Finds a variety of dishes that are in the period from and to.
     * @param from is the lowest search boundary.
     * @param to is the highest search boundary.
     * @return  a set of dishes whose prices are in the range of numbers, passed as a parameter.
     */
    List<Dish> getDishesWithPricesBetween(double from, double to) throws Exception;

    /**
     * Finds a variety of dishes that are satisfy a condition.
     * @param dishCategory is a parameter by which the search is performed.
     * @return a collection of dished which satisfy a condition.
     */
    List<Dish> getDishesWithDishCategory(DishСategory dishCategory);

//    static boolean isLoginFree(String login);

}
