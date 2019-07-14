package information.system.server.model;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Class for dish categories.
 * @author Yaroslav Lichnyi
 */
public class DishCategory implements Serializable {
    private String name;
    private LinkedList <Dish> dishes;
    private boolean healthyFood;
    private int id;

    /**
     * Constructor 2.
     * @param name is a name that is set to dish category.
     * @param id is unique id.
     */
    public DishCategory(String name, int id) {
        dishes = new LinkedList<>();
        this.name = name;
        this.id = id;
    }

    /**
     * Constructor 1.
     */
    public DishCategory() {
        dishes = new LinkedList<>();
    }

    /**
     * Getter for name.
     * @return name of the dish category.
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name.
     * @param name is set as name of the dish category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for id.
     * @return
     */
    public int getId() {
        return id;
    }

    /**
     * Setter for id.
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter for list of dishes.
     * @return dishes of this dish category
     */
    public LinkedList<Dish> getDishes() {
        return dishes;
    }

    /**
     * Setter for list of dishes.
     * @param dishes are set as dishes of this dish category.
     */
    public void setDishes(LinkedList<Dish> dishes) {
        this.dishes = dishes;
    }

    /**
     * Add dish on the list.
     * @param dish is added in collection of dishes.
     */
    public void addDish(Dish dish){
        dish.setDishCategoryId(getId());
        dishes.add(dish);
    }

    /**
     * Get dish from the list.
     * @param index is a number of the dish which is got from {@link DishCategory #dishes}.
     * @return
     */
    public Dish getDish(int index){
        return dishes.get(index);
    }

    /**
     * Remove dish from the list.
     * @param dish is removed from {@link DishCategory #dishes}.
     */
    public boolean removeDish(Dish dish){
        return dishes.remove(dish);
    }

    /**
     * Getter for the healthy food.
     * @return value of variable {@link DishCategory #healthyFood}.
     */
    public boolean isHealthyFood() {
        return healthyFood;
    }

    /**
     * Setter for the healthy food.
     * @param healthyFood is set as value for {@link DishCategory #healthyFood}.
     */
    public void setHealthyFood(boolean healthyFood) {
        this.healthyFood = healthyFood;
    }

    /**
     * Mandatory method.
     * @return
     */
    @Override
    public String toString() {
        return "DishCategory{" +
                "name='" + name + '\'' +
                ", dishes=" + dishes +
                ", healthyFood=" + healthyFood +
                ", id=" + id +
                '}';
    }
}
