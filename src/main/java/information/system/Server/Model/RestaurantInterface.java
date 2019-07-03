package information.system.Server.Model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Interface created to work with classes <b>Dish</b> and <b>DishСategory</b>.
 * @author Yaroslav Lichnyi
 */
public interface RestaurantInterface {

    /**
     * Collection of dish categories, which contain dishes.
     */
    List<DishCategory> menu = new LinkedList<>();

    /**
     * Sets dishes from tile to local variable.
     *
     * @param path is a path to resource where dish categories and dishes are stored.
     */
    void setMenu(String path);

    /**
     * Adds new dish category at {@link RestaurantInterface#menu}.
     * @param newDishCategory is a dish category which is added.
     * @return true is the dish category was added, else false, if there is already the same dish category.
     */
    boolean addDishCategory(DishCategory newDishCategory);

    /**
     * Sets variable value {@link RestaurantInterface#menu}.
     *
     * @param index is a place of a dish in the list, which You get.
     * @return DishCategory from {@link RestaurantInterface#menu}.
     */
    DishCategory getDishCategory(int index);

    /**
     *
     * @return dish categories
     */
    List<DishCategory> getAllDishCategories();

    /**
     * Removes dish from {@link RestaurantInterface#menu}.
     *
     * @param dishCategory is removed from {@link RestaurantInterface#menu}.
     * @return true if an object was removed, else return false.
     */
    boolean removeDishCategory(DishCategory dishCategory);

    /**
     * Adds new dish at {@link RestaurantInterface#menu}.
     *
     * @param dish is added as new Dish to {@link RestaurantInterface#menu}.
     * @return true is the dish was added, else false, if there is already the same dish.
     */
    boolean addDish(Dish dish);

    /**
     * Gets a dish from @link RestaurantInterface#menu}.
     *
     * @param index is a place of a dish in the list, which You get.
     * @return Dish from {@link RestaurantInterface#menu}.
     */
    Dish getDish(int index);

    /**
     * Removes a dish from @link RestaurantInterface#menu}.
     *
     * @param dish is removed from {@link RestaurantInterface#menu}.
     * @return true if an object was removed, else return false.
     */
    boolean removeDish(Dish dish);

    /**
     * Sorts dished by dish price.
     *
     * @return collection of dishes ordered by price by descent.
     */
    ArrayList<Dish> sortDishesByPrice();

    /**
     * Sorts dished by dish category.
     *
     * @return collection of dishes ordered by dish category.
     */
    List<Dish> sortDishesByDishCategory();

    /**
     * Finds a variety of dishes that are in the period from and to.
     *
     * @param from is the lowest search boundary.
     * @param to is the highest search boundary.
     * @return  a set of dishes whose prices are in the range of numbers, passed as a parameter.
     */
    List<Dish> getDishesWithPricesBetween(double from, double to) throws Exception;

    /**
     * Finds a variety of dishes that are satisfy a condition.
     *
     * @param dishCategory is a parameter by which the search is performed.
     * @return a collection of dished which satisfy a condition.
     */
    List<Dish> getDishesWithDishCategory(DishCategory dishCategory);

    /**
     * Edits existing dish.
     *
     * @param oldDish is a dish, witch is added.
     * @param newDish is a dish, which parameter's existing dish adopts.
     * @return true if dish was successfully edited.
     */
    boolean edit(Dish oldDish, Dish newDish);

    /**
     * Edits existing dish.
     *
     * @param OldDishCategory is a dish category, witch is added.
     * @param NewDishCategory is a dish category, which parameter's existing dish adopts.
     * @return true if dish category was successfully edited.
     */
    boolean edit(DishCategory OldDishCategory, DishCategory NewDishCategory);

    /**
     * Gets dishes which contains substring in the name.
     * @param substr is a substring.
     * @return dishes which contains substring in the name.
     */
    List<Dish> getDishesWithSubstrInName(String substr);

    /**
     *
     * @return all dishes, which are stored in dish categories.
     */
    List<Dish> getAllDishes();

}
