package information.system.Server.Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Restaurant implements RestaurantInterface{

    /**
     * Adds new dish category at {@link RestaurantInterface#dishCategories}.
     * @param name is set as name of new dish category.
     * @return true is the dish category was added, else false, if there is already the same dish category.
     */
    @Override
    public boolean addDishCategory(String name) {
        for (DishСategory dishCategory: dishCategories) {
            if (dishCategory.getName().equals(name)){
                return false;
            }
        }
        dishCategories.add(new DishСategory(name));
        return true;
    }

    /**
     * Sets variable value {@link RestaurantInterface#dishCategories}.
     *
     * @param index is a place of a dish in the list, which You get.
     * @return DishCategory from {@link RestaurantInterface#dishCategories}.
     */
    @Override
    public DishСategory getDishCategory(int index) {
        return dishCategories.get(index);
    }

    /**
     * Removes dish from {@link RestaurantInterface#dishCategories}.
     *
     * @param dishCategory is removed from {@link RestaurantInterface#dishCategories}.
     * @return true if an object was removed, else return false.
     */
    @Override
    public boolean removeDishCategory(DishСategory dishCategory) {
        return dishCategories.remove(dishCategory);
    }

    /**
     * Adds new dish at {@link RestaurantInterface#menu}.
     *
     * @param dish is added as new Dish to {@link RestaurantInterface#menu}.
     */
    @Override
    public boolean addDish(Dish dish) {
        for (Dish dishFromMenu: menu) {
            if (dishFromMenu.equals(dish)){
                return false;
            }
        }
        menu.add(dish);
        return true;
    }

    /**
     * Gets a dish from @link RestaurantInterface#menu}.
     *
     * @param index is a place of a dish in the list, which You get.
     * @return Dish from {@link RestaurantInterface#menu}.
     */
    @Override
    public Dish getDish(int index) {
        return menu.get(index);
    }

    /**
     * Removes a dish from @link RestaurantInterface#menu}.
     *
     * @param dish is removed from {@link RestaurantInterface#menu}.
     * @return true if an object was removed, else return false.
     */
    @Override
    public boolean removeDish(Dish dish) {
        return menu.remove(dish);
    }

    /**
     * Sorts dished by dish price.
     * @param menu is a collection that is sorted
     * @return collection of dishes ordered by price by descent.
     */
    @Override
    public ArrayList<Dish> sortDishesByPrice(ArrayList<Dish> menu) {
        ArrayList<Dish> result = (ArrayList<Dish>) menu.clone();
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }

    /**
     * Sorts dished by dish category.
     * @return collection of dishes ordered by dish category.
     */
    @Override
    public List<Dish> sortDishesByDishCategory() {
        //Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        return null;
    }

    /**
     * Finds a variety of dishes that are in the period from and to.
     *
     * @param from is the lowest search boundary.
     * @param to   is the highest search boundary.
     * @return a set of dishes whose prices are in the range of numbers, passed as a parameter.
     */
    @Override
    public List<Dish> getDishesWithPricesBetween(double from, double to) throws Exception {
        if (from > to) throw new InformSystException(
                "The lowest search boundary cannot be greater than the highest search boundary");
        List<Dish> result = new LinkedList<>();
        for (Dish dish : menu) {
            if (dish.getPrice() > from && dish.getPrice() < to){
                result.add(dish);
            }
        }
        return result;
    }

    /**
     * Finds a variety of dishes that are satisfy a condition.
     *
     * @param dishCategory is a parameter by which the search is performed.
     * @return a collection of dished which satisfy a condition.
     */
    @Override
    public List<Dish> getDishesWithDishCategory(DishСategory dishCategory) {
        List<Dish> result = new LinkedList<>();
        for (Dish dish : menu) {
            if (dish.getDishСategory().equals(dishCategory)){
                result.add(dish);
            }
        }
        return result;
    }
}
