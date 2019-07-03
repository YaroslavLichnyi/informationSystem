package information.system.Client.Controller;
import information.system.Client.View.ChangePortForm;
import information.system.Client.View.InformSystemGUI;
import information.system.Client.View.MenuGUI;
import information.system.Client.View.SignInForm;
import information.system.Server.Controller.Protocol;
import information.system.Server.Model.*;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Exchanger;

/**
 * @author Yaroslav Lichnyi
 * {@link Client} represents a controller at client's side of the application.
 */
public class Client implements ClientController {
    private static final Logger LOGGER = Logger.getLogger(Client.class);
    private Socket clientSocket;
    private BufferedWriter writer;
    private BufferedReader reader;
    private User user;
    private Exchanger<String> exgr;
    private int port;
    private File file;
    private MenuGUI menuGUI;
    private Restaurant restaurant;

    public static void main(String[] args) {
        new ChangePortForm(new Client());
    }

    public Client()  {
        restaurant = new Restaurant();
        file = new File("settings/client.ini");
        this.port = readPort();
        exgr = new Exchanger<>();
    }

    /**
     * Checks server connection.
     */
    @Override
    public void checkConnection() {

    }

    /**
     * Adds new object in the table.
     *
     * @return true if the addition was successful, else return false.
     */
    @Override
    public boolean add(Dish dish) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.ADD_DISH);
        LinkedList <Dish> list = new LinkedList();
        list.add(dish);
        xmlSet.setDishesToDocument(list);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        LOGGER.info("New dish was added: " + dish.toString());
        return getExchange();
    }

    /**
     * Adds new object in the table.
     *
     * @return true if the addition was successful, else return false.
     */
    @Override
    public boolean add(DishCategory dishСategory) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.ADD_DISH_CATEGORY);
        LinkedList <DishCategory> list = new LinkedList();
        list.add(dishСategory);
        xmlSet.setDishCategoriesToDocument(list);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return getExchange();
    }

    /**
     * Edits existing dish.
     *
     * @param oldDish
     * @param newDish
     * @return true if the edition was successful, else return false.
     */
    @Override
    public boolean edit(Dish oldDish, Dish newDish) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.EDIT_DISH);
        LinkedList <Dish> dishes = new LinkedList<>();
        dishes.add(oldDish);
        dishes.add(newDish);
        xmlSet.setDishesToDocument(dishes);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return getExchange();
    }

    /**
     * Edits existing dish category.
     *
     * @param oldDishCategory
     * @param newDishCategory
     * @return true if the edition was successful, else return false.
     */
    @Override
    public boolean edit(DishCategory oldDishCategory, DishCategory newDishCategory) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.EDIT_DISH_CATEGORY);
        LinkedList <DishCategory> dishCategories = new LinkedList<>();
        dishCategories.add(oldDishCategory);
        dishCategories.add(newDishCategory);
        xmlSet.setDishCategoriesToDocument(dishCategories);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return getExchange();
    }

    /**
     * Edits existing user.
     *
     * @param oldUser
     * @param newUser
     * @return true if the edition was successful, else return false.
     */
    @Override
    public boolean edit(User oldUser, User newUser) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.EDIT_USER);
        LinkedList <User> users = new LinkedList<>();
        users.add(oldUser);
        users.add(newUser);
        xmlSet.setUsersToDocument(users);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return getExchange();
    }


    /**
     * Edits existing objects in the table.
     *
     * @return true if the deleting was successful, else return false.
     */
    @Override
    public boolean delete(Dish dish) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.DELETE_DISH);
        LinkedList <Dish> dishes = new LinkedList<>();
        dishes.add(dish);
        xmlSet.setDishesToDocument(dishes);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return getExchange();
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
        xmlSet.setCommandToDocument(Protocol.DELETE_DISH_CATEGORY);
        LinkedList <DishCategory> dishCategories = new LinkedList<>();
        dishCategories.add(dishCategory);
        xmlSet.setDishCategoriesToDocument(dishCategories);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return getExchange();
    }

    /**
     * Deletes existing user account.
     *
     * @param user
     * @return
     */
    @Override
    public boolean delete(User user) {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.DELETE_USER);
        LinkedList <User> users = new LinkedList<>();
        users.add(user);
        xmlSet.setUsersToDocument(users);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return getExchange();
    }

    /**
     * Updates content, getting actual information.
     *
     * @return true if the update was successful, else return false.
     */
    @Override
    public boolean updateContent() {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.UPDATE_INFORMATION);
        sendRequest(XmlSet.convertDocumentToString(xmlSet.getDocument()));
        return false;
    }

    /**
     * Connects to server
     */
    @Override
    public synchronized void connectToServer() throws IOException {
        clientSocket = new Socket("127.0.0.1", getPort());
        try {
            clientSocket.setKeepAlive(true);
        } catch (SocketException e) {
            LOGGER.error("Cannot keep socket live", e);
        }
        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
        new Listener(exgr);
        LOGGER.info("Client was connected to server.");
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
        if(writer != null){
            try {
                writer.close();
            } catch (IOException e) {
                LOGGER.error("Exception while closing writer.",e);
            }
        }
        if(reader != null){
            try {
                reader.close();
            } catch (IOException e) {
                LOGGER.error("Exception while closing reader.",e);
            }
        }
        if(clientSocket != null){
            try {
                clientSocket.close();
            } catch (IOException e) {
                LOGGER.error("Exception while closing socket.",e);
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
            LOGGER.info("Client send next request: " + message);
            writer.write(message);
            writer.newLine();
            writer.flush();
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
            System.out.println(clientSocket.toString());
            LOGGER.info("Client waits for response (sign in)");
            Document document = XmlSet.convertStringToDocument(exgr.exchange(null));
            LOGGER.info("Client got response (sign in)");
            LinkedList <User> list = (LinkedList<User>) XmlSet.getUsersFromDocument(document);
            if(Protocol.FALSE.equals(XmlSet.getCommandFromDocument(document))){
                return null;
            } else {
                return XmlSet.getUsersFromDocument(document).get(0);
            }
        } catch (InterruptedException e) {
            LOGGER.error("Exception while exchanging", e);
        }
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
        return getExchange();
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


    /**
     * Sorts dishes by a criteria, which is pointed in parameter.
     * @param sortBy is a criteria, by which dishes are sorted.
     * @return list of dishes sorted by pointed criteria.
     */
    @Override
    public List <Dish> getDishesSortedBy(String sortBy){
        if (Command.PRICE.equals(sortBy)){
            return restaurant.sortDishesByPrice();
        } else if (Command.DISH_CATEGORY.equals(sortBy)){
            return restaurant.sortDishesByDishCategory();
        }
        return null;
    }

    /**
     * Shows general frame.
     */
    @Override
    public void showGeneralFrame() {
        menuGUI = new MenuGUI(this);
    }

    /**
     * Signs out
     */
    @Override
    public void signOut() {
        setUser(null);
        new SignInForm(this);
        menuGUI.dispose();
    }

    /**
     * Finds dishes which contain <code>subStr</code> in the name.
     *
     * @param subStr is the substring that the name must contain.
     * @return dishes which contain <code>subStr</code> in the name.
     */
    public List<Dish> getDishesWhichContains(String subStr) {
        List <Dish> result = new LinkedList<>();
        for (Dish dish : restaurant.getAllDishes()){
            if (dish.getName().toLowerCase().contains(subStr.toLowerCase())){
                result.add(dish);
            }
        }
        return result;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private boolean getExchange(){
        try {
            LOGGER.info("Exchanger in Client waits for exchange");
            Document document = XmlSet.convertStringToDocument(exgr.exchange(null));
            if (Protocol.TRUE.equals(XmlSet.getCommandFromDocument(document))){
                return true;
            }
        } catch (InterruptedException e) {
            LOGGER.error("Exception while exchanging", e);
        }
        return false;
    }

    /**
     * Get port from file. Create file in case file does not exist.
     * @return port
     */
    int readPort() {
        int port = 8000;
        if (file.exists()) {
            FileReader fr = null;
            try {
                fr = new FileReader(file);
            } catch (FileNotFoundException e) {
                LOGGER.error("reading settings file error, ", e);
            }
            Scanner scan = new Scanner(fr);
            if (scan.hasNextInt()) {
                port = scan.nextInt();
            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    LOGGER.error("closing settings file error, ", e);
                }
            }
        } else {
            FileWriter fw;
            try {
                fw = new FileWriter(file);
                fw.write("8000");
                fw.close();
            } catch (IOException e) {
                LOGGER.error("writing settings file error, ", e);
            }
        }

        return port;
    }

    /**
     * Get port for client.
     * @return int port
     */
    public int getPort() {
        return this.port;
    }

    /**
     * Change client port
     *
     * @param port is a number of new port, which is set
     * @return true if the changing was successful, else return false.
     */
    public boolean changePort(int port) {
        if (port == this.port) {
            LOGGER.info("port is the same and has not been changed.");
            return false;
        }
        if (port < 1_024 || port > 65_535) {
            LOGGER.error("port cannot be less than 1025 or more than 65535.");
            return false;
        }
        this.port = port;
        FileWriter fw;
        try {
            fw = new FileWriter(file);
            fw.write(String.valueOf(port));
            fw.close();
        } catch (IOException e) {
            LOGGER.error("writing settings file error, ", e);
        }
        LOGGER.info("port was successfully changed onto " + port + ".");
        return true;
    }


    class Listener extends Thread{
        Exchanger <String> exchanger;
        public Listener(Exchanger<String> exchanger) {
            this.exchanger = exchanger;
            start();
        }

        @Override
        public synchronized void run() {
            LOGGER.info("Listener starts working");
            while (true){
                if(!clientSocket.isClosed() && clientSocket.isConnected()){
                    try {
                        LOGGER.info("Listener waits for response");
                        reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        String responseStr = reader.readLine();
                        responseStr += reader.readLine();
                        LOGGER.info("Listener got from server message.");//string: " + responseStr);
                        Document responseDoc = XmlSet.convertStringToDocument(responseStr);
                        String command = XmlSet.getCommandFromDocument(responseDoc);
                        switch(command)
                        {
                            case Protocol.SIGN_IN:
                                try {
                                    LOGGER.info("Exchanger in listener waits");
                                    exchanger.exchange(responseStr);
                                    LOGGER.info("Exchange was successful. Command \"sign in\" was performed");
                                } catch (InterruptedException e) {
                                    LOGGER.error("Exception while exchanging",e);
                                }
                                break;
                            case Protocol.SIGN_UP:
                                try {
                                    LOGGER.info("Exchanger in listener waits");
                                    exchanger.exchange(responseStr);
                                    LOGGER.info("Exchange was successful. Command \"sign up\" was performed");
                                } catch (InterruptedException e) {
                                    LOGGER.error("Exception while exchanging",e);
                                }
                                break;
                            case Protocol.UPDATE_INFORMATION:
                                restaurant.setMenu(XmlSet.getMenuFromDocument(responseDoc));
                                //menu = (LinkedList<Dish>) XmlSet.getDishesFrom(responseDoc);
                                if (user != null ){
                                    LOGGER.info("Data at dishTable was updated");
                                    menuGUI.setValuesAtTable(restaurant.getAllDishes());
                                }
                                //dishCategories = (LinkedList<DishCategory>) XmlSet.getDishCategoriesFrom(responseDoc);
                                LOGGER.info("Command \"update information\" was performed");
                                break;

                            case Protocol.END_OF_SESSION:
                                break;
/*
                            case Protocol.FIND_DISH:
                                break;

                            case Protocol.SORT_BY_PRICE:
                                menuGUI.setValuesAtTable(XmlSet.getDishesFrom(responseDoc));
                                LOGGER.info("Command \"sort by dish price\" was performed");
                                break;

                            case Protocol.SORT_BY_DISH_CATEGORY:
                                menuGUI.setValuesAtTable(XmlSet.getDishesFrom(responseDoc));
                                LOGGER.info("Command \"sort by dish category\" was performed");
                                break;
*/
                            case Protocol.TRUE:
                                try {
                                    exchanger.exchange(responseStr);
                                    LOGGER.info("Response \"true\" was delivered to client");

                                } catch (InterruptedException e) {
                                    LOGGER.error("Exception while exchanging",e);
                                }
                                break;
                            case Protocol.FALSE:
                                try {
                                    exchanger.exchange(responseStr);
                                    LOGGER.info("Response \"false\" was delivered to client");
                                } catch (InterruptedException e) {
                                    LOGGER.error("Exception while exchanging",e);
                                }
                                break;
                            default:
                                LOGGER.error("Unknown protocol");
                        }
                    } catch (IOException e){
                        LOGGER.error(e);
                    }
                } else {
                    LOGGER.info("Socket was closed");
                    InformSystemGUI.showMessage("Problems with connection");
                }
            }
        }
    }
}