package information.system.Server.Controller;

import information.system.Client.Controller.Client;
import information.system.Server.Model.Command;
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
        //System.out.println("Connection succesful");
        System.out.println("ClientListener works");
        sendFile();
        try {
            while (true) {
                System.out.println("waiting for request");
                word = reader.readLine();
                switch(word)
                {
                    case Command.STOP:
                        System.out.println("STOP");
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
