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
        String text = "<html><h2>Application system</h2>" +
                " Application \"Information system\" was cteated to manage the restaurant menu.<br>" +
                "Dishes can be sorted by price and dish category, be found by substring, which dish name contains.<br>" +
                "There are two types of users:<br>" +
                "\t\t1)User (can browse dishes and dish categories)<br>" +
                "\t\t2)Admin (can browse, add, edit, remove dishes and dish categories) <br>" +
                "Both, users and admins, can change name and password.<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "<br>" +
                "\t\t\t\t\t\t\t\tDevelopers:<br>" +
                "\t\t\t\t\t\t\t\t\t- Yaroslav Lichnyi<br>" +
                "\t\t\t\t\t\t\t\t\t- Alexandr Plachkovskyi</html>";
        label.setText(text);
        panel.add(label);
        add(panel);
        setVisible(true);
    }
}
