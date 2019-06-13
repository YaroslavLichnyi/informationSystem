package information.system;

import information.system.Client.Controller.Client;
import information.system.Client.View.*;
import information.system.Server.Controller.Server;
import information.system.Server.Model.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for lunching Client
 */
public class ClientApp
{
    public static void main( String[] args ) {
        //new DishCategoryFillingForm(null);
       // new SignInForm(null);
        ///////////////////////////////////////////////////////TestGUI testGUI = new TestGUI();
        //new Client();

 /*       Dish dish = new Dish();
        dish.setName("Borsh");
        dish.setPrice(25.70);
        dish.setDescription("Ukrainian national dish");
        new DetailInformationFrame(null, dish);

        System.out.println("List:");
        Restaurant restaurant = new Restaurant();
        restaurant.addDishCategory("Alcohol");
        restaurant.addDishCategory("first");
        restaurant.addDishCategory("second");
        restaurant.addDishCategory("tea");
        restaurant.addDishCategory("coffee");
        restaurant.removeDishCategory(restaurant.getDishCategory(0));
        restaurant.addDishCategory("smth");
        System.out.println("Cats ware created");
        for (DishСategory dishСategory: restaurant.getAllDishCategories()) {
            System.out.println(dishСategory.getId());
        }
*/
    /*    Restaurant restaurant = new Restaurant();
        List<DishCategory> dishCategories = new LinkedList<>();
        Dish dish1 = new Dish("name1", 3.56, "Some defhdjkdgkdgjkscr");
        Dish dish3 = new Dish("name3", 34.45, "Some defhkfkhscr");
        Dish dish2 = new Dish("name2", 3.57, "Some defhkdfhkdgfscr");
        Dish dish4 = new Dish("name4", 17.56, "Some defhdjkdgkdgjkscr");
        Dish dish5 = new Dish("name5", 45, "Some defhkfkhscr");
        Dish dish6 = new Dish("name6", 3.57, "Some defhkdfhkdgfscr");

        DishCategory dishСategory1 = new DishCategory();
        DishCategory dishСategory2 = new DishCategory();
        DishCategory dishСategory3 = new DishCategory();
        dishСategory1.setName("alco");
        dishСategory2.setName("salads");
        dishСategory3.setName("ice cream");

        dishСategory1.addDish(dish1);
        dishСategory1.addDish(dish2);
        dishСategory2.addDish(dish3);
        dishСategory2.addDish(dish4);
        dishСategory3.addDish(dish5);
        dishСategory3.addDish(dish6);
        dishСategory2.setId(1);
        dishCategories.add(dishСategory1);
        dishCategories.add(dishСategory2);
        dishCategories.add(dishСategory3);
        String file = "file.xml";

        InformSystXML.writeXML(dishCategories, file);
        Dish dish = new Dish();
        dish.setDishCategoryId(1);
        dish.setId(546);
        dish.setDescription("AAAAAAAAAAAAAAAA");
        dish.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        dish.setPrice(43543.3454);
        List<Dish> list = new ArrayList();
        list.add(dish);
        XmlSet.insertDishesIntoXmlFile(list, file); */
     //   LinkedList<DishСategory> dishСategories23 = InformSystXML.readXML(file);
     //   dishСategories23.toString();

        XmlSet.deleteDishesFromXmlFile(4, "file.xml");

    }
}
