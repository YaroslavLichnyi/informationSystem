package information.system.Server.Model;

import org.apache.log4j.Logger;

import java.util.*;

public class Restaurant implements RestaurantInterface{
    private static final Logger LOGGER = Logger.getLogger(Restaurant.class);


    /**
     * Collection of dish categories, which contain dishes.
     */
    private List<DishCategory> menu;

    public Restaurant() {
        menu = new LinkedList<>();
        setMenu(Command.SERVER_FILE_RESTAURANT);
    }

    /**
     * Sets dishes from tile to local variable.
     *
     * @param path is a path to resource where dish categories and dishes are stored.
     */
    @Override
    public void setMenu(String path) {
        menu = InformSystXML.getMenu(Command.SERVER_FILE_RESTAURANT);
    }

    public void setMenu(List<DishCategory> menu){
        this.menu = menu;
    }

    /**
     * Adds new dish category at <code>menu</code>.
     * @param newDishCategory is a dish category which is added.
     * @return true is the dish category was added, else false, if there is already the same dish category.
     */
    @Override
    public boolean addDishCategory(DishCategory newDishCategory) {
        for (DishCategory dishCategoryFromList: menu) {
            if (dishCategoryFromList.getName().equals(newDishCategory.getName())){
                return false;
            }
        }
        newDishCategory.setId(generateUniqueIdForDishCategory());
        menu.add(newDishCategory);
        updateDataBase();
        return true;
    }

    /**
     * Sets variable value <code>menu</code>.
     *
     * @param index is a place of a dish in the list, which You get.
     * @return DishCategory from <code>menu</code>.
     */
    @Override
    public DishCategory getDishCategory(int index) {
        return menu.get(index);
    }

    @Override
    public List<DishCategory> getAllDishCategories() {
        return menu;
    }

    /**
     * Removes dish from <code>menu</code>.
     *
     * @param dishCategory is removed from <code>menu</code>.
     * @return true if an object was removed, else return false.
     */
    @Override
    public boolean removeDishCategory(DishCategory dishCategory) {
        if(menu.remove(Restaurant.getDishCategoryById(menu, dishCategory.getId()))){
            updateDataBase();
            return  true;
        }
        return false;
    }

    /**
     * Adds new dish at <code>menu</code>.
     *
     * @param dish is added as new Dish to <code>menu</code>.
     */
    @Override
    public boolean addDish(Dish dish) {
        dish.setId(generateUniqueIdForDish());
        for (Dish dishFromMenu : getAllDishes()) {
            if (dishFromMenu.equals(dish)){
                return false;
            }
        }
        for (DishCategory dishCategory : getAllDishCategories()) {
            if (dish.getDishCategoryId() ==  dishCategory.getId()){
                dishCategory.addDish(dish);
                updateDataBase();
                return true;
            }
        }
        return false;
    }

    /**
     * Gets a dish from @link RestaurantInterface#menu}.
     *
     * @param index is a place of a dish in the list, which You get.
     * @return Dish from <code>menu</code>.
     */
    @Override
    public Dish getDish(int index) {
        return getAllDishes().get(index);
    }

    /**
     * Removes a dish from @link RestaurantInterface#menu}.
     *
     * @param dish is removed from <code>menu</code>.
     * @return true if an object was removed, else return false.
     */
    @Override
    public boolean removeDish(Dish dish) {
        if( getDishCategoryById(dish.getDishCategoryId()).removeDish(dish)){
            LOGGER.info("Dish was deleted: " + dish.toString());
            System.out.println(menu.toString());
            updateDataBase();
            return true;
        }
        LOGGER.info("Dish was not deleted: " + dish.toString());
        return false;
    }

    /**
     * Sorts dished by dish price.
     * @return collection of dishes ordered by price by descent.
     */
    @Override
    public List<Dish> sortDishesByPrice() {
        LinkedList<Dish> result = (LinkedList<Dish>) getAllDishes();
        result.sort((o1, o2) -> (int) (o1.getPrice() * 100 - o2.getPrice() * 100));
        return result;
    }

    /**
     * Sorts dished by dish category.
     * @return collection of dishes ordered by dish category.
     */
    @Override
    public List<Dish> sortDishesByDishCategory() {
        ArrayList<Dish> result = new ArrayList<>();
        for (DishCategory dishCategory: menu) {
            result.addAll(getDishesWithDishCategory(dishCategory));
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
    public List<Dish> getDishesWithPricesBetween(double from, double to) {
        if (from > to){
            return null;
        }
        List<Dish> result = new ArrayList<>();
        for (Dish dish : getAllDishes()) {
            if (dish.getPrice() >= from && dish.getPrice() <= to){
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
        for (DishCategory dishCat : menu) {
            if (dishCategory.equals(dishCat)){
                return dishCat.getDishes();
            }
        }
        return null;
    }

    /**
     * Edits existing dish.
     *
     * @param oldDish is a dish, which is added.
     * @param newDish is a dish, which parameter's existing dish adopts.
     * @return true if dish was successfully edited.
     */
    @Override
    public boolean edit(Dish oldDish, Dish newDish) {
        for (Dish dish : getAllDishes()) {
            if (dish.equals(oldDish)){
                dish.setName(newDish.getName());
                dish.setPrice(newDish.getPrice());
                dish.setDescription(newDish.getDescription());
                updateDataBase();
                return true;
            }
        }
        return false;
    }

    /**
     * Edits existing dish.
     *
     * @param oldDishCategory is a dish category, which is added.
     * @param newDishCategory is a dish category, which parameter's existing dish adopts.
     */
    @Override
    public boolean edit(DishCategory oldDishCategory, DishCategory newDishCategory) {
        for (DishCategory dishCategory: menu) {
            if (dishCategory.getId() == oldDishCategory.getId()){
                dishCategory.setName(newDishCategory.getName());
                dishCategory.setHealthyFood(newDishCategory.isHealthyFood());
                updateDataBase();
                return true;
            }
        }
        return false;
    }

    /**
     * Gets dishes which contains substring in the name.
     *
     * @param substr is a substring.
     * @return dishes which contains substring in the name.
     */
    @Override
    public List<Dish> getDishesWithSubstrInName(String substr) {
        List <Dish> result = new LinkedList<>();
        for (Dish dish : getAllDishes()) {
            if (dish.getName().contains(substr)){
                result.add(dish);
            }
        }
        return result;
    }

    /**
     * @return all dishes, which are stored in dish categories.
     */
    @Override
    public List<Dish> getAllDishes() {
        List <Dish> dishes = new LinkedList<>();
        for (DishCategory dishCategory : menu) {
            dishes.addAll(dishCategory.getDishes());
        }
        return dishes;
    }

    /**
     * Gets dish with given id.
     *
     * @param id s an id by which dish is searched.
     * @return dish with given id.
     */
    @Override
    public Dish getDishById(int id) {
        for (DishCategory dishCategory : menu){
            for (Dish dish: dishCategory.getDishes()) {
                if (dish.getId() == id ){
                    return dish;
                }
            }
        }
        return null;
    }

    /**
     * Gets dish category with given id.
     *
     * @param id is an id by which dish category is searched.
     * @return dish category with given id.
     */
    @Override
    public DishCategory getDishCategoryById(int id) {
        for (DishCategory dishCategory : menu){
            if (dishCategory.getId() == id ){
                return dishCategory;
            }
        }
        return null;
    }


    /**
     * Check if <code>password</code> and <code>password</code> were inputted correct.
     * @param login is admin's <code>password</code>.
     * @param password is admin's <code>password</code>.
     * @return true, if <b>login</b> and <b>password</b> were inputted correct, else return false.
     */
    public static boolean isInputtedDataCorrect(String login, String password){
        return login.length() > 4 && password.length() > 4;
    }


    /**
     * Generates unique id for dish category.
     * @return unique id.
     */
    private int generateUniqueIdForDishCategory(){
        int id = 1;
        boolean free = false;
        if(menu.size() > 0){
            while (!free){
                for (DishCategory dishCategory: menu) {
                    if (dishCategory.getId() == id){
                        id++;
                        continue;
                    }
                    free = true;
                }
            }
        } else return 1;
        return id;
    }

    /**
     * Generates unique id for dish.
     * @return unique id.
     */
    private int generateUniqueIdForDish(){
        int id = 1;
        boolean free = false;
        if(menu.size() > 0){
            while (!free){
                for (Dish dish: getAllDishes()) {
                    if (dish.getId() == id){
                        id++;
                        continue;
                    }
                    free = true;
                }
            }
        } else return 1;
        return id;
    }

    private void updateDataBase(){
        InformSystXML.writeXML(menu, Command.SERVER_FILE_RESTAURANT);
    }

    public static DishCategory getDishCategoryById(List<DishCategory> dishCategories, int id){
        for (DishCategory dishCategory : dishCategories) {
            if(id == dishCategory.getId()){
                return dishCategory;
            }
        }
        return  null;
    }

}
