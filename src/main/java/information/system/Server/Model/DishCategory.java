package information.system.Server.Model;

import java.io.Serializable;
import java.util.LinkedList;

public class DishCategory implements Serializable {
    private String name;
    private LinkedList <Dish> dishes;
    private boolean healthyFood;
    private int id;

    /**
     *
     * @param name
     */
    public DishCategory(String name, int id) {
        dishes = new LinkedList<>();
        this.name = name;
        this.id = id;
    }

    public DishCategory() {
        dishes = new LinkedList<>();
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
        dish.setDishCategoryId(getId());
        dishes.add(dish);
    }

    /**
     *
     * @param index is a number of the dish which is got from {@link DishCategory #dishes}.
     * @return
     */
    public Dish getDish(int index){
        return dishes.get(index);
    }

    /**
     *
     * @param dish is removed from {@link DishCategory #dishes}.
     */
    public boolean removeDish(Dish dish){
        return dishes.remove(dish);
    }

    /**
     *
     * @return value of variable {@link DishCategory #healthyFood}.
     */
    public boolean isHealthyFood() {
        return healthyFood;
    }

    /**
     *
     * @param healthyFood is set as value for {@link DishCategory #healthyFood}.
     */
    public void setHealthyFood(boolean healthyFood) {
        this.healthyFood = healthyFood;
    }

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
