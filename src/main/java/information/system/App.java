package information.system;

import information.system.Client.View.menuGUI;
import information.system.Server.Controller.Server;
import information.system.Server.Model.Dish;
import information.system.Server.Model.DishСategory;
import information.system.Server.Model.InformSystException;
import information.system.Server.Model.InformSystXML;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InformSystException {
       // Server server = new Server(8000);
        menuGUI menu1 = new menuGUI();

        /*
        Dish dish1 = new Dish("name1", new DishСategory("italian"), 13, "Some descr");
        ArrayList<Dish> menu = new ArrayList<>();
        menu.add(dish1);
        InformSystXML.writeXML(menu, "restaurant.xml");
        LinkedList<Dish> menu1 = (LinkedList<Dish>) InformSystXML.readXML("restaurant.xml");
        System.out.println(menu1.get(0));
        */
    }
}
