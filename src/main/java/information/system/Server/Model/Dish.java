package information.system.Server.Model;

import java.io.Serializable;

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
}
