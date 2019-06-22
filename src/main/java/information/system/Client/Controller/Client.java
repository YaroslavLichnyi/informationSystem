package information.system.Client.Controller;

import information.system.Client.View.InformSystemGUI;
import information.system.Client.View.SignInForm;
import information.system.Server.Controller.Protocol;
import information.system.Server.Controller.Server;
import information.system.Server.Model.*;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Exchanger;

/**
 * @author Yaroslav Lichnyi
 * {@link Client} represents a controller at client's side of the application.
 */
public class Client implements ClientController {
    private static final Logger LOGGER = Logger.getLogger(Client.class);
    private ArrayList<Dish> menu;
    private ArrayList<DishCategory> dishCategories;
    private Server server;
    private Socket serverSocket;
    private Socket clientSocket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private User user;
    private Exchanger<String> exgr;

    public static void main(String[] args) {
        new Client();
    }

    public Client()  {
        try{
            menu = new ArrayList<>();
            dishCategories = new ArrayList<>();
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            Dish dish1 = new Dish("name1", 3.56, "Some defhdjkdgkdgjkscr");
            Dish dish3 = new Dish("name3", 34.45, "Some defhkfkhscr");
            Dish dish2 = new Dish("name2", 3.57, "Some defhkdfhkdgfscr");
            Dish dish4 = new Dish("name4", 17.56, "Some defhdjkdgkdgjkscr");
            Dish dish5 = new Dish("name5", 45, "Some defhkfkhscr");
            Dish dish6 = new Dish("name6", 3.57, "Some defhkdfhkdgfscr");
            menu.add(dish3);
            menu.add(dish2);
            menu.add(dish1);
            menu.add(dish5);
            menu.add(dish4);
            menu.add(dish6);
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
            dishCategories.add(dishСategory1);
            dishCategories.add(dishСategory2);
            dishCategories.add(dishСategory3);
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            clientSocket = new Socket("127.0.0.1", 8000);
            connectToServer();
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            new SignInForm(this);
            exgr = new Exchanger<>();
            Listener listener = new Listener(exgr);

        } catch (IOException e){
            LOGGER.error(e);
        }
                  /*  writer.close();
            reader.close();
            clientSocket.close();*/
    }

    /**
     * Creates JFrame with information about all dishes.
     */
    @Override
    public void showMenuFrame() {

    }

    /**
     * Creates JFrame with information about all dish categories.
     */
    @Override
    public void showDishCategoriesFrame() {

    }

    /**
     * Checks server connection.
     */
    @Override
    public void checkConnection() {

    }

    /**
     * Adds new object on the table.
     *
     * @return true if the addition was successful, else return false.
     */
    @Override
    public boolean add(Dish dish) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.ADD);
        LinkedList <Dish> list = new LinkedList();
        list.add(dish);
        xmlSet.setDishesToDocument(list);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return false;
    }

    /**
     * Adds new object in the table.
     *
     * @return true if the addition was successful, else return false.
     */
    @Override
    public boolean add(DishCategory dishСategory) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.ADD);
        LinkedList <DishCategory> list = new LinkedList();
        list.add(dishСategory);
        xmlSet.setDishCategoriesToDocument(list);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        /*try {
            Document document = XmlSet.convertStringToDocument(exgr.exchange(null));
            LinkedList <User> list = (LinkedList<User>) XmlSet.getUserFromDocument(document);
            if (list.size() < 1){
                return null;
            } else {
                return list.get(0);
            }
        } catch (InterruptedException e) {
            LOGGER.error("Exception while exchanging", e);
        }*/
        return false;

    }

    /**
     * Edits existing data in the table.
     *
     * @return true if the edition was successful, else return false.
     */
    @Override
    public boolean edit() {
        return false;
    }

    /**
     * Edits existing objects in the table.
     *
     * @return true if the deleting was successful, else return false.
     */
    @Override
    public boolean delete(Dish dish) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.DELETE);
        LinkedList <Dish> dishes = new LinkedList<>();
        dishes.add(dish);
        xmlSet.setDishesToDocument(dishes);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return false;
    }

    /**
     * Deletes existing dish category.
     *
     * @param dishCategory
     * @return true if the deleting was successful, else return false.
     */
    @Override
    public boolean delete(DishCategory dishCategory) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.DELETE);
        LinkedList <DishCategory> dishCategories = new LinkedList<>();
        dishCategories.add(dishCategory);
        xmlSet.setDishCategoriesToDocument(dishCategories);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return false;
    }

    /**
     * Updates content, getting actual information.
     *
     * @return true if the update was successful, else return false.
     */
    @Override
    public boolean updateContent() {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.GET_INFORMATION);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return false;
    }

    /**
     * Connects to server
     */
    @Override
    public void connectToServer() {
        try {
            clientSocket = new Socket("127.0.0.1", 8000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            LOGGER.error("Problems with connection to server", e);
        }
    }

    /**
     * Disconnects from the server. Finishes the client session.
     */
    @Override
    public void exit() {
        sendRequest(Protocol.END_OF_SESSION);
        if (clientSocket != null){
            try {
                clientSocket.close();
            } catch (IOException e) {
                LOGGER.error(e);
            }
        }
    }

    /**
     * Sends request to the server.
     * @param message is sent as a request
     */
    @Override
    public void sendRequest(String message) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            writer.write(message);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            LOGGER.error(e);
        }
    }

    /**
     * Checks if a user with the same password and login exists.
     * @param login is the parameter by which the user is checked.
     * @param password is the parameter by which the user is checked.
     * @return user, if there is the user exists in user base, return null, if there is not the user exists in user base
     */
    @Override
    public User signIn(String login, String password) {
        XmlSet xmlSet = new XmlSet();
        LinkedList<User> users = new LinkedList<>();
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        users.add(user);
        xmlSet.setUsersToDocument(users);
        xmlSet.setCommandToDocument(Protocol.SIGN_IN);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        try {
            Document document = XmlSet.convertStringToDocument(exgr.exchange(null));
            LinkedList <User> list = (LinkedList<User>) XmlSet.getUserFromDocument(document);
            if (list.size() < 1){
                return null;
            } else {
                return list.get(0);
            }
        } catch (InterruptedException e) {
            LOGGER.error("Exception while exchanging", e);
        }
        return null;
    }

    public InputStream getInputStream() throws IOException{
        return clientSocket.getInputStream();
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public Socket getServerSocket() {
        return serverSocket;
    }

    /**
     * Sorts dishes by a criteria, which is pointed in parameter.
     * @param sortBy is a criteria, by which dishes are sorted.
     * @return list of dishes sorted by pointed criteria.
     */
    @Override
    public List <Dish> getDishesSortedBy(String sortBy){
        XmlSet xmlSet = new XmlSet();
        if (Command.PRICE.equals(sortBy)){
            xmlSet.setCommandToDocument(Protocol.SORT_BY_PRICE);
        } else if (Command.DISH_CAREGORY.equals(sortBy)){
            xmlSet.setCommandToDocument(Protocol.SORT_BY_DISH_CATEGORY);
        }
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return null;
    }

    /**
     * Adds a new user to the database of existing ones.
     * @param user is added as a new Admin
     * @return true if an <code>user</code> was added
     */
    @Override
    public boolean signUp(User user) {
        XmlSet xmlSet = new XmlSet();
        LinkedList<User> users = new LinkedList<>();
        users.add(user);
        xmlSet.setUsersToDocument(users);
        xmlSet.setCommandToDocument(Protocol.SIGN_UP);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return false;
    }


    /**
     * Finds dishes which contain <code>subStr</code> in the name.
     *
     * @param subStr is the substring that the name must contain.
     * @return dishes which contain <code>subStr</code> in the name.
     */
    public List<Dish> getDishesWhichContains(String subStr) {
        return null;
    }

    public ArrayList<Dish> getMenu() {
        return menu;
    }

    public ArrayList<DishCategory> getDishCategories() {
        return dishCategories;
    }

    class Listener extends Thread {
        Exchanger <String> exchanger;
        public Listener(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }
        @Override
        public void run() {
            while (true){
                try {
                    String responseStr = reader.readLine();
                    Document responseDoc  = XmlSet.convertStringToDocument(responseStr);
                    String command = XmlSet.getCommandFromDocument(responseDoc);
                    switch(command) {
                        case Protocol.SIGN_IN:
                            user = XmlSet.getUserFromDocument(responseDoc).get(0);
                            break;
                        case Protocol.END_OF_SESSION:
                            break;
                        case Protocol.SORT_BY_DISH_CATEGORY:
                            break;
                        case Protocol.SORT_BY_PRICE:
                            break;
                        case Protocol.SIGN_UP:
                            try {
                                exchanger.exchange(responseStr);
                            } catch (InterruptedException e) {
                                LOGGER.error("Exception while exchanging",e);
                            }
                            break;
                        default:
                            LOGGER.error("Unknown protocol");
                            InformSystemGUI.showMessage("Unknown protocol");
                    }
                } catch (IOException e){
                    LOGGER.error(e);
                }
            }
        }
    }
}