package information.system.Client.View;
import information.system.Client.Controller.Client;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
public class ChangePortForm extends InformSystemGUI{
    private static final Logger LOGGER = Logger.getLogger(ChangePortForm.class);

    public ChangePortForm(Client client) {
        super();
        setBounds(dimension.width / 2 - 175, dimension.height / 2 - 100, 350, 200);
        setClient(client);
        setVisible(true);
        init();
        revalidate();
    }

    @Override
    protected void init() {
        JSpinner spnPort;
        JLabel lbPort;
        JButton btApply;

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

        btApply = new JButton( "Apply"  );
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets( 3,5,5,5 );
        gridBagLayout.setConstraints( btApply, gridBag );
        panel.add( btApply );
        btApply.addActionListener(new ActionListener() {
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
                    LOGGER.error("Unable to connect to server", e1);
                    InformSystemGUI.showMessage("Unable to connect to server");
                }
            }
        });

        JButton btClose = new JButton( "Close"  );
        gridBag.gridx = 1;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets( 3,5,5,5 );
        gridBagLayout.setConstraints( btClose, gridBag );
        panel.add( btClose );
        btClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
