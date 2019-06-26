package information.system.Client.View;
import information.system.Client.Controller.Client;
import org.apache.log4j.Logger;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JButton;
public class ChangePortForm extends InformSystemGUI{
    private static final Logger LOGGER = Logger.getLogger(ChangePortForm.class);

    public ChangePortForm(Client client) {
        super();
        setBounds(dimension.width / 2 - 75, dimension.height / 2 - 75, 150, 150);
        setClient(client);
        setVisible(true);
        init();
    }

    @Override
    protected void init() {
        JSpinner spnPort;
        JLabel lbPort;
        JButton btSave;

        spnPort = new JSpinner( );
        spnPort.setValue(client.getPort());
        gridBag.gridx = 1;
        gridBag.gridy = 0;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.CENTER;
        gridBag.insets = new Insets( 5,1,5,5 );
        gridBagLayout.setConstraints( spnPort, gridBag );
        panel.add( spnPort );

        lbPort = new JLabel( "Port: "  );
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets( 5,5,5,0 );
        gridBagLayout.setConstraints( lbPort, gridBag );
        panel.add( lbPort );

        btSave = new JButton( "Save"  );
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets( 3,5,5,5 );
        gridBagLayout.setConstraints( btSave, gridBag );
        panel.add( btSave );
        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int port = (int)spnPort.getValue();
                if (port < 1024 || port > 65535) {
                    InformSystemGUI.showMessage("Port can has between 1024 and 65535");
                    return;
                }
                client.changePort(port);
                try {
                    client.connectToServer();
                    new SignInForm(getClient());
                    dispose();
                } catch (IOException e1) {
                    LOGGER.error("Cannot to connect to server.", e1);
                    InformSystemGUI.showMessage("Chose another port");
                }
            }
        });
    }
}
