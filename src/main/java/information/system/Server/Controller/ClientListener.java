package information.system.Server.Controller;

import information.system.Client.Controller.Client;
import information.system.Server.Model.*;
import org.apache.log4j.Logger;

import java.io.*;
import java.net.Socket;


public class ClientListener extends Thread {
    private Socket socket; // сокет, через который сервер общается с клиентом,
    // кроме него - клиент и сервер никак не связаны
    private static final Logger LOGGER = Logger.getLogger(Client.class);
    private Server server;
    private BufferedReader reader; // поток чтения из сокета
    private BufferedWriter writer; // поток записи в сокет

    public ClientListener(Socket socket, Server server)  {
        this.socket = socket;
        this.server = server;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        } catch (IOException e) {
            LOGGER.error(e);
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
        String word;
        System.out.println("ClientListener works");

        //recieveFile();
        try {
            while (true) {
                System.out.println("waiting for request");
                word = reader.readLine();
                System.out.println("REQUEST: " + word);
                switch(word)
                {
                    case Command.SIGN_IN:
                        System.out.println("Signin in");
                        String login = reader.readLine();
                        String password = reader.readLine();
                        //до этого на стороне клиента должны были провериться данные при помощи метода Restaurant.isInputtedDataCorrect()
                        Admin responce = Restaurant.singIn(login, password);
                        if (responce != null){
                            //тправить админа в xml
                            sendMessage(Command.CORRECT);
                        } else {
                            sendMessage(Command.INCORRECT);
                        }
                        break;
                    case Command.STOP:
                        System.out.println("STOP");
                        break;

                    case Command.FILE:
                        //recieveFile();
                        //System.out.println("File was recieved");
                        break;
                    case Command.ADD:

                        System.out.println("Smth was added");
                        sendMessage("Smth was added");
                        //server.getRestaurant().addDish(null);
                        break;
                    case Command.EDIT:
                        System.out.println("Smth was edited");
                        sendMessage("Smth was edited");
                        //server.getRestaurant().removeDish(null);
                        //server.getRestaurant().addDish(null);
                        break;
                    case Command.REMOVE:
                        System.out.println("Smth was removed");
                        sendMessage("Smth was removed");
                        //server.getRestaurant().removeDish(null);
                        break;
                    case Command.GET_ALL_INFORMATION:
                        sendFile();
                        System.out.println("Smth was removed");
                        sendMessage("Smth was removed");
                        //server.getRestaurant().removeDish(null);
                        break;
                    default:
                        System.out.println("no match");
                        sendMessage("no match");
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
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
            LOGGER.error(ex);
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
                System.out.println("Can't get socket input stream. ");
            }
            try {
                out = new FileOutputStream(Command.CLIENT_FILE_RESTAURANT);
            } catch (FileNotFoundException ex) {
                System.out.println("File not found. ");
            }
            byte[] bytes = new byte[16 * 1024];
            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }
            out.close();
            in.close();


        } catch (IOException e) {

        }
    }

    /**
     * Gets value of {@link ClientListener#socket}.
     * @return {@link ClientListener#socket} value
     */
    public Socket getSocket() {
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
