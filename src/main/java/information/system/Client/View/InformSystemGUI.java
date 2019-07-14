package information.system.Client.View;

import information.system.Client.Controller.Client;

import javax.swing.*;
import java.awt.*;

public abstract class InformSystemGUI extends JFrame {
    JPanel panel;
    GridBagLayout gridBagLayout ;
    GridBagConstraints gridBag ;
    private Toolkit toolkit;
    Dimension dimension;
    Client client;

    InformSystemGUI() {
        super("Information System");
        toolkit = Toolkit.getDefaultToolkit();
        dimension = toolkit.getScreenSize();
        panel = new JPanel();
        gridBag = new GridBagConstraints();
        gridBagLayout = new GridBagLayout();
        panel.setLayout(gridBagLayout);
        add(panel);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    Client getClient() {
        return client;
    }

    void setClient(Client client) {
        this.client = client;
    }

    protected abstract void init();

    public static void showMessage(String message){
        JFrame frame = new JFrame("Attention!");
        JOptionPane.showMessageDialog(frame, message);
    }
}
