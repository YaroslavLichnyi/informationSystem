package information.system.Client.Controller;

import information.system.Server.Model.Command;
import information.system.Server.Model.Dish;
import information.system.Server.Model.InformSystXML;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.LinkedList;


public class ServerListener extends Thread {
    private static final Logger LOGGER = Logger.getLogger(ServerListener.class);
    Client client;
    private BufferedReader reader;

    public ServerListener(Client client){
        this.client = client;
        try {
            reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        }catch (IOException e){
            LOGGER.error(e);
        }
        start();
    }


    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     * @see #stop()
     */
    @Override
    public void run() {
        super.run();
        JFrame frame = new JFrame("Error");
        JOptionPane.showMessageDialog(frame, "ClientListener starts work");
        String responce;
        while (true){
            try {
                responce = reader.readLine();
                switch(responce)
                {
                    case Command.STOP:
                        System.out.println("STOP");
                        break;
                    case Command.FILE:
                        System.out.println("FILE");
                        frame = new JFrame("Error");
                        JOptionPane.showMessageDialog(frame, getDished().get(0).toString());
                        break;
                    default:
                        System.out.println("no match");
                }
            } catch (IOException e){
                LOGGER.error(e);
            }
        }
    }
    /*
    public ArrayList<Dish> getDished(){
        try {
            int filesize = 1022386;
            int bytesRead;
            int currentTot;

            String FILE_NAME = "files/file1.txt";

//        ServerSocket s = new ServerSocket(0);
//        int port = s.getLocalPort();

            Socket socket = client.getClientSocket();
            System.out.println("Accepted connection : " + socket);

            byte[] bytearray = new byte[filesize];
            InputStream is = socket.getInputStream();
            File copyFileName = new File(FILE_NAME);
            FileOutputStream fos = new FileOutputStream(copyFileName);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            bytesRead = is.read(bytearray, 0, bytearray.length);
            currentTot = bytesRead;

            do {
                bytesRead = is.read(bytearray, currentTot, (bytearray.length - currentTot));
                if (bytesRead >= 0) {
                    currentTot += bytesRead;
                }
            } while (bytesRead > -1);

            bos.write(bytearray, 0, currentTot);

            bos.flush();
            bos.close();
            socket.close();
        }catch (IOException e){

        }
        return reader.read();
    }
    */
    public LinkedList<Dish> getDished(){
        try {
            Socket socket = client.getClientSocket();


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

            byte[] bytes = new byte[16*1024];

            int count;
            while ((count = in.read(bytes)) > 0) {
                out.write(bytes, 0, count);
            }

            out.close();
            in.close();
            return  InformSystXML.readXML(Command.CLIENT_FILE_RESTAURANT);

        } catch (IOException e){

        }
        return  null;
    }

}
