package information.system.Client.Controller;

import information.system.Client.View.menuGUI;
import information.system.Server.Controller.Server;
import information.system.Server.Model.Command;
import information.system.Server.Model.Dish;
import information.system.Server.Model.DishСategory;
import information.system.Server.Model.InformSystException;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Client implements ClientController {
    private static final Logger LOGGER = Logger.getLogger(Client.class);
    private ArrayList<Dish> menu = new ArrayList<>();
    private ArrayList<DishСategory> dishCategories = new ArrayList<>();
    static private Server server;
    private Socket serverSocket;
    private String name;
    static private Socket clientSocket;
    static BufferedWriter writer;
    static BufferedReader reader;
    private ServerListener serverListener;

    public static void main(String[] args) {
         new Client();
    }


    public Client()  {
        try{
            clientSocket = new Socket("127.0.0.1", 8000);
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            serverListener = new ServerListener(this);
            writer.write(Command.GET_ALL_INFORMATION);
            writer.newLine();
            writer.flush();
            while(true){
                System.out.println("Enter message");
                Scanner scanner = new Scanner(System.in);
                writer.write(scanner.next());
                writer.newLine();
                writer.flush();
            }
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
     * Updates content, getting actual information from server.
     */
    @Override
    public void updateInformation() {

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
        return false;
    }

    /**
     * Adds new object in the table.
     *
     * @return true if the addition was successful, else return false.
     */
    @Override
    public boolean add(DishСategory dishСategory) {
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
    public boolean delete() {
        return false;
    }

    /**
     * Updates content, getting actual information.
     *
     * @return true if the update was successful, else return false.
     */
    @Override
    public boolean updateContent() {
        return false;
    }

    @Override
    public void connectToServer() {
        try {
            clientSocket = new Socket("127.0.0.1", 8000);
            BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));


        } catch (IOException e) {
            LOGGER.error("Problems with connection to server", e);
            new InformSystException("Problems with connection to server", e.toString());
        }
    }

    @Override
    public void exit() {
        try {
            clientSocket.close();
        } catch (IOException e) {
            LOGGER.error(e);

        }
    }

    @Override
    public void sendRequest(String message) {
        try {
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            writer.write(message);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            LOGGER.error(e);

        }

    }

    public InputStream getInputStream() throws IOException{
        return clientSocket.getInputStream();
    }
}
