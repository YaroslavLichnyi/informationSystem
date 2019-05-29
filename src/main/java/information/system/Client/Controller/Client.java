package information.system.Client.Controller;
import information.system.Server.Controller.Protocol;
import information.system.Server.Controller.Server;
import information.system.Server.Model.Admin;
import information.system.Server.Model.Command;
import information.system.Server.Model.Dish;
import information.system.Server.Model.DishСategory;
import information.system.Server.Model.DishСategory;
import org.apache.log4j.Logger;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Yaroslav Lichnyi
 * {@link Client} represents a controller at client's side of the application.
 */
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

    public static void main(String[] args) {
        new Client();
    }


    public Client()  {
        try{
            clientSocket = new Socket("127.0.0.1", 8000);
            connectToServer();
            writer = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            /*writer.write(Command.GET_ALL_INFORMATION);
            writer.newLine();
            writer.flush();
            */
            //sendRequest(Command.FILE);
            //sendFile(new File(Command.CLIENT_FILE_RESTAURANT));
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
        }
    }

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

    @Override
    public boolean signIn(String login, String password) {
        sendRequest(Protocol.SIGN_IN);
        sendRequest(login);
        sendRequest(password);
        //РІРјРµСЃС‚РёС‚СЊ РІ РѕРґРЅРѕ РїРѕСЃР»Р°РЅРёРµ
        return false;
    }

    public InputStream getInputStream() throws IOException{
        return clientSocket.getInputStream();
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        Client.clientSocket = clientSocket;
    }

    public Socket getServerSocket() {
        return serverSocket;
    }
    /*
        public void sendFile(File file){

               // File file = new File(Command.SERVER_FILE_RESTAURANT);
                // Get the size of the file
                long length = file.length();
                byte[] bytes = new byte[16 * 1024];
                InputStream in = new FileInputStream(file);
                OutputStream out = clientSocket.getOutputStream();

                int count;
                while ((count = in.read(bytes)) > 0) {
                    out.write(bytes, 0, count);
                }

                if (out != null){
                    try {
                        out.close();
                    } catch (IOException e) {
                        LOGGER.error(e);
                    }
                }
                if ( in != null){
                    try {
                        in.close();
                    } catch (IOException e) {
                        LOGGER.error(e);
                    }
                }
        }
    */
    //РїРµСЂРµРґР°С‘Рј РїР°СЂР°РјРµС‚СЂ РїРѕ РєРѕС‚РѕСЂРѕРјСѓ Р±СѓРґРµРј СЃРѕСЂС‚РёСЂРѕРІР°С‚СЊ
    public List <Dish> getDishesSortedBy(String sortBy){
        return null;
    }

    @Override
    public boolean signUp(Admin admin) {
        return false;
    }


    /**
     * Finds dishes whisn contain <code>subStr</code> in the name.
     *
     * @param subStr is the substring that the name must contain.
     * @return dishes which contain <code>subStr</code> in the name.
     */
    public List<Dish> getDishesWhichContains(String subStr) {
        return null;
    }

    public void hearServer(){
        new Thread(){
            @Override
            public void run() {
                String responce;
                while (true){
                    try {
                        responce = reader.readLine();
                        switch(responce)
                        {
                            case "someResponce":
                                System.out.println("STOP");
                                break;

                            default:
                                System.out.println("no match");
                        }
                    } catch (IOException e){
                        LOGGER.error(e);
                    }
                }
            }
        }.start();
    }
}
