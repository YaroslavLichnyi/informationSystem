package information.system.Server.Controller;

import information.system.Client.Controller.Client;
import information.system.Server.Model.Command;
import information.system.Server.Model.XmlSet;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 * ClientListener for each of the clients.
 */
public class ClientListener extends Thread {

    private Socket socket;
    private static final Logger logger = Logger.getLogger(Client.class);
    private Server server;
    private BufferedReader reader;
    private BufferedWriter writer;

    ClientListener(Socket socket, Server server)  {
        this.socket = socket;
        this.server = server;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            logger.error("ClientListener creation error, ", e);
        }
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
        String strDoc;
        System.out.println("ClientListener works");

        try {
            while (true) {
                System.out.println("waiting for request");
                String line;
                String documentInStr = "";
                while ((line = reader.readLine()) != null) {
                    if (line.isEmpty()) {
                        break;
                    }
                    documentInStr = documentInStr + line;
                }
                Document doc = XmlSet.convertStringToDocument(documentInStr);
                switch(XmlSet.getCommandFromDocument(doc)) {

                    case Protocol.ADD_DISH:
                        server.getRestaurant().addDish(XmlSet.getDishesFrom(doc).get(0));
                        logger.info("using protocol ADD_DISH was detected. Dish was added into the storage.");
                        break;

                    case Protocol.ADD_DISH_CATEGORY:
                        server.getRestaurant().addDishCategory(XmlSet.getDishCategoriesFrom(doc).get(0).toString());
                        logger.info("using protocol ADD_DISH_CATEGORY was detected." +
                                    " DishCategory was added into the storage.");
                        break;

                    case Protocol.SIGN_UP:

                        break;

                    case Protocol.DELETE_DISH:
                        server.getRestaurant().removeDish(XmlSet.getDishesFrom(doc).get(0));
                        logger.info("using protocol DELETE_DISH was detected. Dish was deleted from the storage.");
                        break;

                    case Protocol.DELETE_DISH_CATEGORY:
                        server.getRestaurant().removeDishCategory(XmlSet.getDishCategoriesFrom(doc).get(0));
                        logger.info("using protocol DELETE_DISH_CATEGORY was detected." +
                                    " DishCategory was deleted from the storage.");
                        break;

                    case Protocol.DELETE_USER:

                        break;

                    case Protocol.EDIT_DISH:

                        break;

                    case Protocol.EDIT_DISH_CATEGORY:

                        break;

                    case Protocol.EDIT_USER:

                        break;

                    case Protocol.GET_INFORMATION:

                        break;

                    case Protocol.END_OF_SESSION:

                        break;

                    case Protocol.SIGN_IN:
//                        String login = reader.readLine();
//                        String password = reader.readLine();
//                        //до этого на стороне клиента должны были провериться данные
//                        // при помощи метода Restaurant.isInputtedDataCorrect()
//                        User responce = Restaurant.singIn(login, password);
//                        if (responce != null) {
//                            //тправить админа в xml
//                            sendMessage(Command.CORRECT);
//                        } else {
//                            sendMessage(Command.INCORRECT);
//                        }
                        break;

                    case Protocol.FIND_DISH:

                        break;

                    case Protocol.SORT_BY_PRICE:
                        server.getRestaurant().sortDishesByPrice(new ArrayList(XmlSet.getDishesFrom(doc)));
                        logger.info("using protocol SORT_BY_PRICE was detected. Dishes was sorted by price.");
                        break;

                    case Protocol.SORT_BY_DISH_CATEGORY:
                        server.getRestaurant().sortDishesByDishCategory();
                        logger.info("using protocol SORT_BY_DISH_CATEGORY was detected." +
                                    " Dishes was sorted by category.");
                        break;

                    default:
                        logger.error("unknown command was entered.");
                        sendMessage("no match");
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
            writer.write(msg + "\n");
            writer.flush();
        } catch (IOException ex) {
            logger.error("sending mesage error, ", ex);
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
     * Recieve a file from client
     */
    public void recieveFile() {
        try {
            Socket socket = server.getServerSocket().accept();

            InputStream in = null;
            OutputStream out = null;
            try {

                in = socket.getInputStream();
            } catch (IOException ex) {
                logger.error("cannot get socket input stream, ", ex);
            }
            try {
                out = new FileOutputStream(Command.CLIENT_FILE_RESTAURANT);
            } catch (FileNotFoundException ex) {
                logger.error("file not found, ", ex);
            }
            byte[] bytes = new byte[16 * 1024];
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            out.close();
            in.close();


        } catch (IOException e) {
            logger.error("file recieving error, ", e);
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
