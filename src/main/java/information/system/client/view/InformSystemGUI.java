package information.system.client.view;

import information.system.client.сontroller.Client;

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

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    protected abstract void init();

    public static void showMessage(String message){
        JFrame frame = new JFrame("Attention!");
        JOptionPane.showMessageDialog(frame, message);
    }
}
