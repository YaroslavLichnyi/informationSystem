package information.system.Client.View;

import javax.swing.*;
import java.awt.*;

public class HelpForm extends JFrame {
    public HelpForm() throws HeadlessException {
        super("Information system - Help");
        JPanel panel = new JPanel();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension  dimension = toolkit.getScreenSize();
        setBounds(dimension.width / 2 - 350, dimension.height / 2 - 300, 700, 600);
        JLabel label = new JLabel();
        String text = "<html><h1>Application system</h1>" +
                " Application \"Information system\" was cteated to manage the restaurant menu.<br>" +
                "Dishes can be sorted by price and dish category, be found by substring, which dish name contains.<br>" +
                "There are two types of users:<br>" +
                "<ul type=\"disc\"><li>User (can browse dishes and dish categories)</li>" +
                "<li>Admin (can browse, add, edit, remove dishes and dish categories) </li></ul>" +
                "Both, users and admins, can change name and password.<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<h2>Developers:</h2>" +
                "<ul type=\"disc\">\n" +
                "<li>Yaroslav Lichnyi</li>\n" +
                "<li>Alexandr Plachkovskyi</li>\n" +
                "</ul></html>";
        label.setText(text);
        panel.add(label);
        this.add(panel);
        setVisible(true);
        validate();
        repaint();
    }
}
