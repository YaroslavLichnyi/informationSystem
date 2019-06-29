package information.system.Server.Controller;

import information.system.Client.Controller.Client;
import information.system.Server.Model.*;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import java.io.*;
import java.net.Socket;
import java.util.LinkedList;


public class ClientListener extends Thread {
    private Socket socket;  // сокет, через который сервер общается с клиентом,
                            // кроме него - клиент и сервер никак не связаны
    private static final Logger logger = Logger.getLogger(Client.class);
    private Server server;
    private BufferedReader reader; // поток чтения из сокета
    private BufferedWriter writer; // поток записи в сокет

    ClientListener(Socket socket, Server server)  {
        this.socket = socket;
        this.server = server;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            logger.error("ClientListener creation error, ", e);
        }
        //recieveFile();
        //sendFile();
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
            while (true) {
                /*System.out.println("waiting for request");
                strDoc = reader.readLine();
                */
               // String line;
               // String documentInStr = "";
                /*while ((line = reader.readLine()) != null) {
                    if (line.isEmpty()) {
                        break;
                    }
                    System.out.println("2"+line);
                    documentInStr = documentInStr + line;
                    System.out.println("3"+documentInStr);
                } */
                String documentInStr = reader.readLine();
                documentInStr += reader.readLine();
                Document doc = XmlSet.convertStringToDocument(documentInStr);
                XmlSet xmlToSend = new XmlSet();
                switch(XmlSet.getCommandFromDocument(doc)) {
                    case Protocol.ADD_DISH:
                        xmlToSend.setCommandToDocument(
                                    String.valueOf(server.getRestaurant().addDish(XmlSet.getDishesFrom(doc).get(0))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        logger.info("using protocol ADD_DISH was detected. Dish was added into the storage.");
                    break;

                    case Protocol.ADD_DISH_CATEGORY:
                        DishCategory dishCategory = XmlSet.getDishCategoriesFrom(doc).get(0);
                        xmlToSend.setCommandToDocument(
                                    String.valueOf(server.getRestaurant().addDishCategory(dishCategory)));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        logger.info("using protocol ADD_DISH_CATEGORY was detected." +
                                    " DishCategory was added into the storage.");
                        break;

                    case Protocol.SIGN_UP:
                        User newUser = XmlSet.getUsersFromDocument(doc).get(0);
                       // xmlToSend.setCommandToDocument(server.getRestaurant().);
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        break;

                    case Protocol.DELETE_DISH:
                        xmlToSend.setCommandToDocument(
                                String.valueOf(server.getRestaurant().removeDish(XmlSet.getDishesFrom(doc).get(0))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        logger.info("using protocol DELETE_DISH was detected. Dish was deleted from the storage.");
                        break;

                    case Protocol.DELETE_DISH_CATEGORY:
                        xmlToSend.setCommandToDocument(
                                String.valueOf(server.getRestaurant().removeDishCategory(XmlSet.getDishCategoriesFrom(doc).get(0)))
                        );
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        logger.info("using protocol DELETE_DISH_CATEGORY was detected." +
                                " DishCategory was deleted from the storage.");
                        break;

                    case Protocol.DELETE_USER:
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));


                        break;

                    case Protocol.EDIT_DISH:
                        xmlToSend.setCommandToDocument(
                                String.valueOf
                                        (server.getRestaurant().
                                                edit(XmlSet.getDishesFrom(doc).get(0),XmlSet.getDishesFrom(doc).get(1))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        break;

                    case Protocol.EDIT_DISH_CATEGORY:
                        xmlToSend.setCommandToDocument(
                                String.valueOf
                                        (server.getRestaurant().
                                                edit(XmlSet.getDishCategoriesFrom(doc).get(0),XmlSet.getDishCategoriesFrom(doc).get(1))));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));

                        break;

                    case Protocol.EDIT_USER:
/*                        User oldUser = XmlSet.getUsersFromDocument(doc).get(0);
                        User newUser = XmlSet.getUsersFromDocument(doc).get(1);
                        if (User.isLoginFree()) {
                            xmlToSend.setCommandToDocument(User.edit(oldUser, newUser));
                            sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        } else {
                            sendMessage();
                        }
*/
                        break;

                    case Protocol.UPDATE_INFORMATION:
                        xmlToSend.setCommandToDocument(Protocol.UPDATE_INFORMATION);
                        xmlToSend.setMenuToDocument(InformSystXML.getMenu(Command.SERVER_FILE_RESTAURANT));
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        break;

                    case Protocol.END_OF_SESSION:

                        break;

                    case Protocol.SIGN_IN:
                        User user = XmlSet.getUsersFromDocument(doc).get(0);
                        User signedInUser = User.signIn(user.getLogin(), user.getPassword());
                        if(signedInUser != null){
                            LinkedList<User> list = new LinkedList<>();
                            list.add(signedInUser);
                            xmlToSend.setCommandToDocument(Protocol.SIGN_IN);
                            xmlToSend.setUsersToDocument(list);
                        } else {
                            xmlToSend.setCommandToDocument(Protocol.FALSE);
                        }
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));
                        break;

                    case Protocol.FIND_DISH:
                        sendMessage(XmlSet.convertDocumentToString(xmlToSend.getDocument()));

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

                }
            }
        } catch (IOException e) {
            logger.error("protocol error, ", e);
        }

    }

    /**
     * Sends message(response) from server to client
     * @param msg is a message that is sent
     */
    private void sendMessage(String msg) {
        try {
            writer.write(msg);
            writer.newLine();
            writer.flush();
        } catch (IOException ex) {
            logger.error("Sending message error, ", ex);
        }
    }


    /**
     * Sends a file to client
     * TODO the ability to transfer a file as a parameter
     */
    public void sendFile(){
        try {
            File file = new File(Command.SERVER_FILE_RESTAURANT);
            // Get the size of the file
            long length = file.length();
            byte[] bytes = new byte[16 * 1024];
            InputStream in = new FileInputStream(file);
            OutputStream out = socket.getOutputStream();

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            out.close();
            in.close();

        } catch (IOException e){
            logger.error("file sending error, ", e);
        }
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
