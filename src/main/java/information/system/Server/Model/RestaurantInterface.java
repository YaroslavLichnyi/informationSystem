package information.system.Server.Model;

import information.system.Server.Model.Dish;
import information.system.Server.Model.DishСategory;

import java.util.List;

/**
 * Interface created to work with classes <b>Dish</b> and <b>DishСategory</b>.
 * @author Yaroslav Lichnyi
 */
public interface RestaurantInterface {
    /**
     * Collection of dishes.
     */
    public List<Dish> menu = null;
    /**
     * Collection of dish categories.
     */
    public List<DishСategory> dishCategories = null;

    /**
     * Adds new dish category at {@link RestaurantInterface#dishCategories}.
     * @param name is set as name of new dish category.
     */
    public void addDishCategory(String name);

    /**
     * Sets variable value {@link RestaurantInterface#dishCategories}.
     * @param index is a place of a dish in the list, which You get.
     * @return DishCategory from {@link RestaurantInterface#dishCategories}.
     */
    public DishСategory getDishCategory(int index);

    /**
     * Removes dish from {@link RestaurantInterface#dishCategories}.
     * @param dishСategory is removed from {@link RestaurantInterface#dishCategories}.
     * @return true if an object was removed, else return false.
     */
    public boolean removeDishCategory(DishСategory dishСategory);

    /**
     * Adds new dish at {@link RestaurantInterface#menu}.
     * @param dish is added as new Dish to {@link RestaurantInterface#menu}.
     */
    public void addDish(Dish dish);

    /**
     * Gets a dish from @link RestaurantInterface#menu}.
     * @param index is a place of a dish in the list, which You get.
     * @return Dish from {@link RestaurantInterface#menu}.
     */
    public Dish getDish(int index);

    /**
     * Removes a dish from @link RestaurantInterface#menu}.
     * @param dish is removed from {@link RestaurantInterface#menu}.
     * @return true if an object was removed, else return false.
     */
    public boolean removeDish(Dish dish);

    /**
     * Sorts dished by dish price.
     * @return collection of dishes ordered by price by descent.
     */
    public List<Dish> sortDishesByPrice();

    /**
     * Sorts dished by dish category.
     * @return collection of dishes ordered by dish category.
     */
    public List<Dish> sortDishesByDishCategory();

    /**
     * Finds a variety of dishes that are in the period from and to.
     * @param from is the lowest search boundary.
     * @param to is the highest search boundary.
     * @return  a set of dishes whose prices are in the range of numbers, passed as a parameter.
     */
    public List<Dish> getDichesWithPricesBetween(double from, double to);

    /**
     * Finds a variety of dishes that are satisfy a condition.
     * @param dishСategory is a parameter by which the search is performed.
     * @return a collection of dished which satisfy a condition.
     */
    public List<Dish> getDichesWithDishCategory(DishСategory dishСategory);



}
