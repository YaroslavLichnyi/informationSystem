package information.system.Client.View;
import information.system.Client.Controller.Client;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInForm extends InformSystemGUI {
    public SignInForm(Client client) {
        setBounds(dimension.width / 2 - 125, dimension.height / 2 - 100, 250, 200);
        setClient(client);
        basicInit();
        add(panel);
        setVisible(true);
    }

    @Override
    protected void basicInit() {
        final JTextField tfLogin;
        JLabel lbLogin;
        JLabel lbPassword;
        final JTextField tfPassword;
        JButton btBut0;
        JButton btBut1;

        panel = new JPanel();
        GridBagLayout gbLogin = new GridBagLayout();
        GridBagConstraints gbcLogin = new GridBagConstraints();
        panel.setLayout( gbLogin );

        tfLogin = new JTextField( );
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 1;
        gbcLogin.gridwidth = 2;
        gbcLogin.gridheight = 1;
        gbcLogin.fill = GridBagConstraints.BOTH;
        gbcLogin.weightx = 1;
        gbcLogin.weighty = 0;
        gbcLogin.anchor = GridBagConstraints.NORTH;
        gbcLogin.insets = new Insets(0,10,0,10);
        gbLogin.setConstraints( tfLogin, gbcLogin );
        panel.add( tfLogin );

        lbLogin = new JLabel( "Login"  );
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 0;
        gbcLogin.gridwidth = 1;
        gbcLogin.gridheight = 1;
        gbcLogin.fill = GridBagConstraints.BOTH;
        gbcLogin.weightx = 1;
        gbcLogin.weighty = 1;
        gbcLogin.anchor = GridBagConstraints.NORTH;
        gbcLogin.insets = new Insets(0,10,0,0);
        gbLogin.setConstraints( lbLogin, gbcLogin );
        panel.add( lbLogin );

        lbPassword = new JLabel( "Password"  );
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 2;
        gbcLogin.gridwidth = 1;
        gbcLogin.gridheight = 1;
        gbcLogin.fill = GridBagConstraints.BOTH;
        gbcLogin.weightx = 1;
        gbcLogin.weighty = 1;
        gbcLogin.anchor = GridBagConstraints.NORTH;
        gbcLogin.insets = new Insets(0,10,0,0);
        gbLogin.setConstraints( lbPassword, gbcLogin );
        panel.add( lbPassword );

        tfPassword = new JTextField( );
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 3;
        gbcLogin.gridwidth = 2;
        gbcLogin.gridheight = 1;
        gbcLogin.fill = GridBagConstraints.BOTH;
        gbcLogin.weightx = 1;
        gbcLogin.weighty = 0;
        gbcLogin.anchor = GridBagConstraints.NORTH;
        gbcLogin.insets = new Insets(0,10,0,10);
        gbLogin.setConstraints( tfPassword, gbcLogin );
        panel.add( tfPassword );

        btBut0 = new JButton( "Sign in"  );
        gbcLogin.gridx = 0;
        gbcLogin.gridy = 4;
        gbcLogin.gridwidth = 1;
        gbcLogin.gridheight = 1;
        gbcLogin.fill = GridBagConstraints.BOTH;
        gbcLogin.weightx = 1;
        gbcLogin.weighty = 0;
        gbcLogin.anchor = GridBagConstraints.NORTH;
        gbcLogin.insets = new Insets(2,10,7,0);
        gbLogin.setConstraints( btBut0, gbcLogin );
        panel.add( btBut0 );
        btBut0.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*
                if(client.signIn(tfLogin.getText(), tfPassword.getText())){
                    new MenuGUI(client, true);
                    dispose();
                } else {
                    JFrame frame = new JFrame("Error");
                    JOptionPane.showMessageDialog(frame, "There is no user with the same login or/and password");
                }
                */
                new MenuGUI(client, true);
                dispose();
            }
        });

        btBut1 = new JButton( "Enter as a guest"  );
        gbcLogin.gridx = 1;
        gbcLogin.gridy = 4;
        gbcLogin.gridwidth = 1;
        gbcLogin.gridheight = 1;
        gbcLogin.fill = GridBagConstraints.BOTH;
        gbcLogin.weightx = 1;
        gbcLogin.weighty = 0;
        gbcLogin.anchor = GridBagConstraints.NORTH;
        gbLogin.setConstraints( btBut1, gbcLogin );
        gbcLogin.insets = new Insets(2,0,7,10);
        panel.add( btBut1 );
        btBut1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new MenuGUI(client, false);
                dispose();
            }
        });

    }
}
