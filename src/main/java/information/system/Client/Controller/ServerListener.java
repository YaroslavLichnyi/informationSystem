package information.system.Client.Controller;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


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
        while (true){
            try {
                String responce = reader.readLine();
                frame = new JFrame("Error");
                JOptionPane.showMessageDialog(frame, responce);
            }catch (IOException e){
                LOGGER.error(e);
            }
        }
    }
}
