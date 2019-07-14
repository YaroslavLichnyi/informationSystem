package information.system;
import information.system.client.сontroller.Client;
import information.system.client.view.ChangePortForm;

public class ClientApp {
    public static void main(String[] args) {
        //return Double.valueOf((int)spnHryvnas.getValue()) + (Double.valueOf((int)spnKopeikas.getValue()))/100;
        //int hrn = 34;
        //int kop = 76;
        //double price = Double.valueOf(hrn) + Double.valueOf(kop) / 100;
        //System.out.println(price);
        new ChangePortForm(new Client());
       // Restaurant restaurant = new Restaurant();
        //restaurant.setMenu(Command.SERVER_FILE_RESTAURANT);
        /*
        DishCategory dishCategory = new DishCategory();
        dishCategory.setHealthyFood(true);
        dishCategory.setName("UKR food");
        restaurant.addDishCategory(dishCategory);
        Dish dish = new Dish();
        dish.setPrice(34.5);
        dish.setDescription("some");
        dish.setName("borsch");
        dish.setDishCategoryId(restaurant.getAllDishCategories().get(restaurant.getAllDishCategories().size() -1).getId());
        restaurant.addDish(dish);
        */
        //restaurant.removeDish(restaurant.getAllDishCategories().get(restaurant.getAllDishCategories().size() - 1 ).getDish(0));
    }
}
