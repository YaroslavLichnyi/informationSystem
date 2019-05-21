package information.system.Client.View;

import information.system.Client.Controller.Client;

import javax.swing.*;
import java.awt.*;

public abstract class InformSystemGUI extends JFrame {
    protected JPanel panel;
    protected GridBagLayout gridBagLayout ;
    protected GridBagConstraints gridBag ;
    protected Toolkit toolkit;
    protected Dimension dimension;
    protected Client client;

    public InformSystemGUI() {
        super("Menu");
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        panel = new JPanel();
        /*
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        */
        gridBag = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    protected abstract void basicInit();
}
