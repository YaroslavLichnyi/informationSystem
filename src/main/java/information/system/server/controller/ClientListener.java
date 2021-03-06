package information.system.server.controller;

import information.system.server.model.*;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;


/**
 * Class for listening for clients' commands and transferring them to the server.
 */
public class ClientListener extends Thread {
    private Socket socket;  // сокет, через который сервер общается с клиентом,
                            // кроме него - клиент и сервер никак не связаны
    private static final Logger logger = Logger.getLogger(ClientListener.class);
    private Server server;
    private BufferedReader reader; // поток чтения из сокета
    private BufferedWriter writer; // поток записи в сокет

    public boolean isMistake() {
        return mistake;
    }
    public void setMistake(boolean mistake) {
        this.mistake = mistake;
    }
    private boolean mistake;

    /**
     * Constructor.
     * @param socket
     * @param server
     */
    ClientListener(Socket socket, Server server)  {
        this.socket = socket;
        this.server = server;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            logger.error("ClientListener creation error, ", e);
        }
        setMistake(true);
        start();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        try {
            updateInformation();
            while (true) {
                String documentInStr = reader.readLine();
                String tmp = reader.readLine();
                if (tmp != null) {
                    documentInStr += tmp;
                }
                logger.info("client listener got a string from client : " + documentInStr);
                Document doc = XmlSet.convertStringToDocument(documentInStr);
                XmlSet xmlToSend = new XmlSet();
                switch(XmlSet.getCommandFromDocument(doc)) {

                    case Protocol.ADD_DISH:
                        xmlToSend.setCommandToDocument(
                                    String.valueOf(server.getRestaurant().addDish(XmlSet.getDishesFrom(doc).get(0))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        updateInformation();
                        logger.info("using protocol ADD_DISH was detected. Dish was added into the storage.");
                    break;

                    case Protocol.ADD_DISH_CATEGORY:
                        DishCategory dishCategory = XmlSet.getDishCategoriesFrom(doc).get(0);
                        xmlToSend.setCommandToDocument(
                                    String.valueOf(server.getRestaurant().addDishCategory(dishCategory)));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        InformSystXML.writeXML(server.getRestaurant().getAllDishCategories(),
                                               Command.SERVER_FILE_RESTAURANT);
                        updateInformation();
                        logger.info("using protocol ADD_DISH_CATEGORY was detected." +
                                    " DishCategory was added into the storage.");
                        break;

                    case Protocol.SIGN_UP:
                        xmlToSend.setCommandToDocument(String.valueOf(User.signUp(
                                XmlSet.getUsersFromDocument(doc).get(0))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        logger.info("using protocol SIGN_UP was detected. User logged into the system.");
                        server.getView().logging("New user logged into the system.");
                        break;

                    case Protocol.DELETE_DISH:
                        xmlToSend.setCommandToDocument(
                                String.valueOf(server.getRestaurant().removeDish(XmlSet.getDishesFrom(doc).get(0))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        updateInformation();
                        logger.info("using protocol DELETE_DISH was detected. Dish was deleted from the storage.");
                        break;

                    case Protocol.DELETE_DISH_CATEGORY:
                        xmlToSend.setCommandToDocument(String.valueOf(server.getRestaurant().removeDishCategory(
                                                                        XmlSet.getDishCategoriesFrom(doc).get(0))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        updateInformation();
                        logger.info("using protocol DELETE_DISH_CATEGORY was detected." +
                                    " DishCategory was deleted from the storage.");
                        break;

                    case Protocol.DELETE_USER:
                        User.delete(XmlSet.getUsersFromDocument(doc).get(0));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        updateInformation();
                        logger.info("using protocol DELETE_USER was detected. User was deleted from the storage.");
                        break;

                    case Protocol.EDIT_DISH:
                        xmlToSend.setCommandToDocument(String.valueOf(server.getRestaurant().edit(
                                                XmlSet.getDishesFrom(doc).get(0),XmlSet.getDishesFrom(doc).get(1))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        updateInformation();
                        logger.info("using protocol EDIT_DISH was detected. Dish was edited successfully.");
                        break;

                    case Protocol.EDIT_DISH_CATEGORY:
                        xmlToSend.setCommandToDocument(String.valueOf(server.getRestaurant().edit(
                                   XmlSet.getDishCategoriesFrom(doc).get(0),XmlSet.getDishCategoriesFrom(doc).get(1))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        updateInformation();
                        logger.info("using protocol EDIT_DISH_CATEGORY was detected. " +
                                    "Dish category was edited successfully.");
                        break;

                    case Protocol.EDIT_USER:
                        User.edit(XmlSet.getUsersFromDocument(doc).get(0), XmlSet.getUsersFromDocument(doc).get(1));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        logger.info("using protocol EDIT_USER was detected. User was edited successfully.");
                        break;

                    case Protocol.UPDATE_INFORMATION:
                        updateInformation();
                        logger.info("using protocol UPDATE_INFORMATION was detected. " +
                                    "Information was successfully updated from the storage.");
                        break;

                    case Protocol.SIGN_OUT:
                        setMistake(false);
                        logger.info("using protocol SIGN_OUT was detected. User was logged out. Cause is " +
                                    (isMistake()? "error":"normal log out") + ".");
                        server.getView().logging("User was logged out. Cause is " +
                                                (isMistake()? "error":"normal log out") + ".");
                        break;

                    case Protocol.SIGN_IN:
                        User user = XmlSet.getUsersFromDocument(doc).get(0);
                        User signedInUser = User.signIn(user.getLogin(), user.getPassword());
                        if(signedInUser != null){
                            LinkedList<User> list = new LinkedList<>();
                            list.add(signedInUser);
                            xmlToSend.setCommandToDocument(Protocol.SIGN_IN);
                            xmlToSend.setUsersToDocument(list);
                            sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                            logger.info("using protocol SIGN_IN was detected. User logged in.");
                            server.getView().logging("User logged in.");
                        } else {
                            xmlToSend.setCommandToDocument(Protocol.FALSE);
                            sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                            logger.info("using protocol END_OF_SESSION was detected. " +
                                        "It was wrong trying to log the user in.");
                            server.getView().logging("Wrong attemption to log the user in.");
                        }
                        break;

                    case Protocol.FIND_DISH:
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        logger.info("using protocol FIND_DISH was detected. " +
                                    "The finding was successfully applied.");
                        break;

                    case Protocol.SORT_BY_PRICE:
                        xmlToSend.setDishesToDocument(server.getRestaurant().sortDishesByPrice());
                        xmlToSend.setCommandToDocument(Protocol.SORT_BY_PRICE);
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        logger.info("using protocol SORT_BY_PRICE was detected. Dishes was sorted by price.");
                        break;

                    case Protocol.SORT_BY_DISH_CATEGORY:
                        xmlToSend.setDishesToDocument(server.getRestaurant().sortDishesByDishCategory());
                        xmlToSend.setCommandToDocument(Protocol.SORT_BY_PRICE);
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        logger.info("using protocol SORT_BY_DISH_CATEGORY was detected." +
                                    " Dishes was sorted by category.");
                        break;

                    case Protocol.MAKE_ADMIN:
                        xmlToSend.setCommandToDocument(String.valueOf(User.makeAdmin(XmlSet.getUsersFromDocument(doc).get(0))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        break;

                    case Protocol.GET_USERS:
                        xmlToSend.setUsersToDocument(InformSystXML.readUsers(Command.SERVER_FILE_ADMINS));
                        xmlToSend.setCommandToDocument(Protocol.GET_USERS);
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        break;
                }
            }
        } catch (IOException e) {
            logger.error("protocol error, ", e);
            server.getView().logging("Connection reset.");
        }

    }

    /**
     * Sends message(response) from server to client
     * @param msg is a message that is sent
     */
    private synchronized void sendMessage(String msg) {
        try {
            writer.write(msg);
            writer.newLine();
            writer.flush();
        } catch (IOException ex) {
            logger.error("Sending message error, ", ex);
        }
    }

    /**
     * Method for updating information of all clients.
     */
    public synchronized void updateInformation() {
        XmlSet xmlSet = new XmlSet();
        xmlSet.setCommandToDocument(Protocol.UPDATE_INFORMATION);
        xmlSet.setMenuToDocument(server.getRestaurant().getAllDishCategories());
        sendMessage(XmlSet.convertDocumentToString(xmlSet.getDocument()));
//        System.out.println("!!!: " + XmlSet.convertDocumentToString(xmlSet.getDocument()));
    }

    /**
     * Gets value of {@link ClientListener#socket}.
     * @return {@link ClientListener#socket} value
     */
    Socket getSocket() {
        return socket;
    }

    /**
     * Sets value to {@link ClientListener#socket}.
     * @param socket is a value that is set.
     */
    public void setSocket(Socket socket) {
        this.socket = socket;
    }

}
