package information.system.Server.Model;

import java.io.Serializable;
import java.util.LinkedList;

public class DishСategory implements Serializable {
    private String name;
    private LinkedList <Dish> dishes;
    private boolean healthyFood;

    /**
     *
     * @param name
     */
    public DishСategory(String name) {
        this.name = name;
    }

    /**
     *
     * @return name of the dish category.
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name is set as name of the dish category.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return dishes of this dish category
     */
    public LinkedList<Dish> getDishes() {
        return dishes;
    }

    /**
     *
     * @param dishes are set as dishes of this dish category.
     */
    public void setDishes(LinkedList<Dish> dishes) {
        this.dishes = dishes;
    }

    /**
     *
     * @param dish is added in collection of dishes.
     */
    public void addDish(Dish dish){
        dishes.add(dish);
    }

    /**
     *
     * @param index is a number of the dish which is got from {@link DishСategory #dishes}.
     * @return
     */
    public Dish getDish(int index){
        return dishes.get(index);
    }

    /**
     *
     * @param dish is removed from {@link DishСategory #dishes}.
     */
    public void removeDish(Dish dish){
        dishes.remove(dish);
    }

    /**
     *
     * @return value of variable {@link DishСategory #healthyFood}.
     */
    public boolean isHealthyFood() {
        return healthyFood;
    }

    /**
     *
     * @param healthyFood is set as value for {@link DishСategory #healthyFood}.
     */
    public void setHealthyFood(boolean healthyFood) {
        this.healthyFood = healthyFood;
    }
}
