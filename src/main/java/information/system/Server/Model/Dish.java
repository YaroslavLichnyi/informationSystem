package information.system.Server.Model;

/**
 * @author Yaroslav Lichnyi
 */
public class Dish {

    private String name;
    private DishСategory dishСategory;
    private double price;
    private String description;

    public Dish(String name, DishСategory dishСategory, double price, String description) {
        this.name = name;
        this.dishСategory = dishСategory;
        this.price = price;
        this.description = description;
    }

    public Dish() {
        super();
    }

    /**
     * @return name of the dish
     */
    public String getName() {
        return name;
    }

    /**
     * @param name is set as name of the dish
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return category to which the dish belongs
     */
    public DishСategory getDishСategory() {
        return dishСategory;
    }

    /**
     * @param dishСategory  is set as category of the dish
     */
    public void setDishСategory(DishСategory dishСategory) {
        this.dishСategory = dishСategory;
    }

    /**
     * @return price of the dish
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price is set as dish price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return dish description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description is set as dish description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
