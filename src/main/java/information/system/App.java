package information.system;

import information.system.Client.Controller.Client;
import information.system.Client.View.DishFillingForm;
import information.system.Client.View.MenuGUI;
import information.system.Client.View.SignInForm;
import information.system.Server.Controller.Server;
import information.system.Server.Model.*;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InformSystException{
       /* Dish dish1 = new Dish("name1", 13, "Some descr");
        ArrayList<Dish> menu = new ArrayList<>();
        menu.add(dish1);
        InformSystXML.writeXML(menu, "restaurant.xml");*/
        /*LinkedList<Dish> menu1 =  InformSystXML.readXML(Command.CLIENT_FILE_NAME);
        System.out.println(menu1.get(0));*/


       /* Admin admin1 = new Admin("Yaroslav", "superCooker", "qwerty12345");
        Admin admin2 = new Admin("Kate", "kate12345", "74edgj63");
        LinkedList<Admin> list = new LinkedList<>();
        list.add(admin1);
        list.add(admin2);
        InformSystXML.writeAdmins(list, Command.SERVER_FILE_ADMINS);
        System.out.println(InformSystXML.readAdmins(Command.SERVER_FILE_ADMINS).get(0).toString());
        */
       //MenuGUI menuGUI = new MenuGUI(new Client());
       // DishGUI dishGUI = new DishGUI();
        //DishFillingForm dishFillingForm = new DishFillingForm(null);
        SignInForm signInForm = new SignInForm(null);


    }
}
