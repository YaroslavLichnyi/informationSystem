package information.system.Server.Model;

import java.util.*;

public class Restaurant implements RestaurantInterface{

    /**
     * Adds new dish category at {@link RestaurantInterface#dishCategories}.
     * @param name is set as name of new dish category.
     * @return true is the dish category was added, else false, if there is already the same dish category.
     */
    @Override
    public boolean addDishCategory(String name) {
        for (DishCategory dishCategory: dishCategories) {
            if (dishCategory.getName().equals(name)){
                return false;
            }
        }
        dishCategories.add(new DishCategory(name, generateUniqueIdForDishCategory()));
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
     * @param menu is a collection that is sorted
     * @return collection of dishes ordered by price by descent.
     */
    @Override
    public ArrayList<Dish> sortDishesByPrice(ArrayList<Dish> menu) {
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
        for (DishCategory dishСat : dishCategories) {
            if (dishCategory.equals(dishСat)){
                return dishСat.getDishes();
            }
        }
        return null;
    }

    /**
     * Signin in application.
     * @param login is admin's {@link User#login}.
     * @param password is admin's {@link User#password}.
     * @return Admin object, if there is the admin with the same name
     *        or return null, if there is no admin with the same name
     */
    public static User singIn(String login, String password){
        for (User admin:InformSystXML.readAdmins(Command.SERVER_FILE_ADMINS)) {
            if (admin.getLogin().equals(login) && admin.getPassword().equals(password)){
                return admin;
            }
        }
        return null;
    }

    /**
     * Check if {@link User#login} and {@link User#password} were inputted correct.
     * @param login is admin's {@link User#login}.
     * @param password is admin's {@link User#password}.
     * @return true, if <b>login</b> and <b>password</b> were inputted correct, else return false.
     */
    public static boolean isInputtedDataCorrect(String login, String password){
        if (login.length() > 4 && password.length() > 4) return true;
        return false;
    }

    /**
     * Checks if a user with the same login exists.
     * @param login is a login which is checked.
     * @return true if there is no user with the same login.
     */
    public static boolean isLoginFree(String login) {
        return false;
    }

    public Restaurant() {
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
                for (DishCategory dishСategory: dishCategories) {
                    if (dishСategory.getId() == id){
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
