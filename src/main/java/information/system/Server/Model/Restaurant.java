package information.system.Server.Model;

import java.util.*;

public class Restaurant implements RestaurantInterface{

    /**
     * Adds new dish category at {@link RestaurantInterface#dishCategories}.
     * @param newDishCategory is a dish category which is added.
     * @return true is the dish category was added, else false, if there is already the same dish category.
     */
    @Override
    public boolean addDishCategory(DishCategory newDishCategory) {
        for (DishCategory dishCategoryFromList: dishCategories) {
            if (dishCategoryFromList.getName().equals(newDishCategory.getName())){
                return false;
            }
        }
        newDishCategory.setId(generateUniqueIdForDishCategory());
        dishCategories.add(newDishCategory);
        return true;
    }

    /**
     * Sets variable value {@link RestaurantInterface#dishCategories}.
     *
     * @param index is a place of a dish in the list, which You get.
     * @return DishCategory from {@link RestaurantInterface#dishCategories}.
     */
    @Override
    public DishCategory getDishCategory(int index) {
        return dishCategories.get(index);
    }

    @Override
    public ArrayList<DishCategory> getAllDishCategories() {
        return dishCategories;
    }

    /**
     * Removes dish from {@link RestaurantInterface#dishCategories}.
     *
     * @param dishCategory is removed from {@link RestaurantInterface#dishCategories}.
     * @return true if an object was removed, else return false.
     */
    @Override
    public boolean removeDishCategory(DishCategory dishCategory) {
        return dishCategories.remove(dishCategory);
    }

    /**
     * Adds new dish at {@link RestaurantInterface#menu}.
     *
     * @param dish is added as new Dish to {@link RestaurantInterface#menu}.
     */
    @Override
    public boolean addDish(Dish dish) {
        for (Dish dishFromMenu : menu) {
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
     * @return collection of dishes ordered by price by descent.
     */
    @Override
    public ArrayList<Dish> sortDishesByPrice() {
        ArrayList<Dish> result = (ArrayList<Dish>) menu.clone();;
        Collections.sort(result, new Comparator<Dish>() {
            @Override
            public int compare(Dish o1, Dish o2) {
                return (int) (o1.getPrice() * 100 - o2.getPrice() * 100);
            }
        });
        return result;
    }

    /**
     * Sorts dished by dish category.
     * @return collection of dishes ordered by dish category.
     */
    @Override
    public List<Dish> sortDishesByDishCategory() {
        ArrayList<Dish> result = new ArrayList<>();
        for (DishCategory dishCategory: dishCategories) {
            for (Dish dish: getDishesWithDishCategory(dishCategory)) {
                result.add(dish);
            }
        }
        return result;
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
        if (from > to){
            return null;
        }
        List<Dish> result = new ArrayList<>();
        for (Dish dish : menu) {
            if (dish.getPrice() >= from && dish.getPrice() <= to){  // Aleksandr has added =, =
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
    public List<Dish> getDishesWithDishCategory(DishCategory dishCategory) {
        for (DishCategory dishCat : dishCategories) {
            if (dishCategory.equals(dishCat)){
                return dishCat.getDishes();
            }
        }
        return null;
    }

    /**
     * Edits existing dish.
     *
     * @param oldDish is a dish, witch is added.
     * @param newDish is a dish, which parameter's existing dish adopts.
     * @return true if dish was successfully edited.
     */
    @Override
    public boolean edit(Dish oldDish, Dish newDish) {
        for (Dish dish : menu) {
            if (dish.equals(oldDish)){
                dish.setName(newDish.getName());
                dish.setPrice(newDish.getPrice());
                dish.setDescription(newDish.getDescription());
                return true;
            }
        }
        return false;
    }

    /**
     * Edits existing dish.
     *
     * @param oldDishCategory is a dish category, witch is added.
     * @param newDishCategory is a dish category, which parameter's existing dish adopts.
     */
    @Override
    public boolean edit(DishCategory oldDishCategory, DishCategory newDishCategory) {
        for (DishCategory dishCategory: dishCategories) {
            if (dishCategory.equals(oldDishCategory)){
                dishCategory.setName(newDishCategory.getName());
                dishCategory.setHealthyFood(newDishCategory.isHealthyFood());
                return true;
            }
        }
        return false;
    }


    /**
     * Check if <code>password</code> and <code>password</code> were inputted correct.
     * @param login is admin's <code>password</code>.
     * @param password is admin's <code>password</code>.
     * @return true, if <b>login</b> and <b>password</b> were inputted correct, else return false.
     */
    public static boolean isInputtedDataCorrect(String login, String password){
        if (login.length() > 4 && password.length() > 4) return true;
        return false;
    }


    /**
     * Generates unique id for dish category.
     * @return unique id.
     */
    public int generateUniqueIdForDishCategory(){
        int id = 0;
        boolean free = false;
        if(dishCategories.size() > 0){
            while (!free){
                for (DishCategory dishCategory: dishCategories) {
                    if (dishCategory.getId() == id){
                        id++;
                        continue;
                    }
                    free = true;
                }
            }
        } else return 0;
        return id;
    }

    /**
     * Generates unique id for dish.
     * @return unique id.
     */
    public int generateUniqueIdForDish(){
        int id = 0;
        boolean free = false;
        if(dishCategories.size() > 0){
            while (!free){
                for (Dish dish: menu) {
                    if (dish.getId() == id){
                        id++;
                        continue;
                    }
                    free = true;
                }
            }
        } else return 0;
        return id;
    }

}
