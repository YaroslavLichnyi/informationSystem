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
        for (DishСategory dishCategory: dishCategories) {
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
        for (DishСategory dishСat : dishCategories) {
            if (dishCategory.equals(dishСat)){
                return dishСat.getDishes();
            }
        }
        return null;
    }

    /**
     * Signin in application.
     * @param login is admin's {@link Admin#login}.
     * @param password is admin's {@link Admin#password}.
     * @return Admin object, if there is the admin with the same name
     *        or return null, if there is no admin with the same name
     */
    public static Admin singIn(String login, String password){
        for (Admin admin:InformSystXML.readAdmins(Command.SERVER_FILE_ADMINS)) {
            if (admin.getLogin().equals(login) && admin.getPassword().equals(password)){
                return admin;
            }
        }
        return null;
    }

    /**
     * Check if {@link Admin#login} and {@link Admin#password} were inputted correct.
     * @param login is admin's {@link Admin#login}.
     * @param password is admin's {@link Admin#password}.
     * @return true, if <b>login</b> and <b>password</b> were inputted correct, else return false.
     */
    public static boolean isInputtedDataCorrect(String login, String password){
        if (login != null && password != null && login.length() > 5 && password.length() > 5) return true;
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
}
