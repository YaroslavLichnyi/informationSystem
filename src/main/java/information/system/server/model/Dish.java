package information.system.server.model;

import java.io.Serializable;
import java.util.Objects;

/**
 * Class for dishes.
 * @author Yaroslav Lichnyi
 */
public class Dish implements Serializable {
    private String name;
    private double price;
    private String description;
    private int id;
    private int dishCategoryId;


    /**
     * Constructor 2.
     * @param name
     * @param price
     * @param description
     */
    public Dish(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    /**
     * Constructor 1.
     */
    public Dish() {
    }

    /**
     * Constructor 3.
     * @param id
     */
    public Dish(int id) {
        super();
        this.id = id;
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
     * Getter for dish category.
      * @return
     */
    public int getDishCategoryId() {
        return dishCategoryId;
    }

    /**
     * Setter for dish category.
     * @param dishCategoryId
     */
    public void setDishCategoryId(int dishCategoryId) {
        this.dishCategoryId = dishCategoryId;
    }

    /**
     * @return name of the dish.
     */
    public String getName() {
        return name;
    }

    /**
     * @param name is set as name of the dish.
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return price of the dish.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price is set as dish price.
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return dish description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description is set as dish description.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Mandatory method.
     * @return
     */
    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", id=" + id +
                ", dishCategoryId=" + dishCategoryId +
                '}';
    }

    /**
     * Mandatory method.
      * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Double.compare(dish.price, price) == 0 &&
                Objects.equals(name, dish.name) &&
                Objects.equals(description, dish.description);
    }

    /**
     * Mandatory method.
     * @return
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }

}
