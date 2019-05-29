package information.system.Server.Model;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Yaroslav Lichnyi
 */
public class Dish implements Serializable {

    private String name;
    private double price;
    private String description;

    public Dish(String name, double price, String description) {
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public Dish() {
        super();
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

    @Override
    public String toString() {
        return "Dish{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish dish = (Dish) o;
        return Double.compare(dish.price, price) == 0 &&
                Objects.equals(name, dish.name) &&
                Objects.equals(description, dish.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price, description);
    }

}
