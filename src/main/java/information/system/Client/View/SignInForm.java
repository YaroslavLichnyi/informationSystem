package information.system.Client.View;
import information.system.Client.Controller.Client;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInForm extends InformSystemGUI {
    public SignInForm(Client client) {
        setBounds(dimension.width / 2 - 135, dimension.height / 2 - 100, 270, 200);
        setClient(client);
        this.client = client;
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

        tfLogin = new JTextField( );
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,10,0,10);
        gridBagLayout.setConstraints( tfLogin, gridBag );
        panel.add( tfLogin );
        ((AbstractDocument) tfLogin.getDocument()).setDocumentFilter(new InformSystDocumentFilter());

        lbLogin = new JLabel( "Login"  );
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,10,0,0);
        gridBagLayout.setConstraints( lbLogin, gridBag );
        panel.add( lbLogin );

        lbPassword = new JLabel( "Password"  );
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,10,0,0);
        gridBagLayout.setConstraints( lbPassword, gridBag );
        panel.add( lbPassword );

        tfPassword = new JTextField( );
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,10,0,10);
        gridBagLayout.setConstraints( tfPassword, gridBag );
        panel.add( tfPassword );
        ((AbstractDocument) tfPassword.getDocument()).setDocumentFilter(new InformSystDocumentFilter());

        btBut0 = new JButton( "Sign in"  );
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
    //    gridBag.insets = new Insets(0,10,7,0);
        gridBagLayout.setConstraints( btBut0, gridBag );
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

        btBut1 = new JButton( "Sign up"  );
        gridBag.gridx = 1;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBagLayout.setConstraints( btBut1, gridBag );
  //      gridBag.insets = new Insets(0,0,7,10);
        panel.add( btBut1 );
        btBut1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpForm(client);
            }
        });

    }
}
