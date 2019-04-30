package information.system.Server.Model;

import org.apache.log4j.Logger;

import javax.swing.*;

public class InformSystException extends Exception{

    public InformSystException(String message) {
        JFrame frame = new JFrame("Error");
        JOptionPane.showMessageDialog(frame, message);
    }
    public InformSystException(String message, String exception) {
        super(message);
        JFrame frame = new JFrame("Error");
        JOptionPane.showMessageDialog(frame, message);
    }

}
