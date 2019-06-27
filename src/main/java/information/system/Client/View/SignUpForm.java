package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.Restaurant;
import information.system.Server.Model.User;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignUpForm extends InformSystemGUI {
    private Client client;
    public SignUpForm(Client client) {
        super();
        setBounds(dimension.width / 2 - 135, dimension.height / 2 - 100, 270, 200);
        setClient(client);
        init();
        add(panel);
        setVisible(true);
    }

    @Override
    protected void init() {
        final JTextField tfName;
        final JTextField tfLogin;
        final JTextField tfPassword;
        JLabel lbName;
        JLabel lbLogin;
        JLabel lbLabel2;
        JButton btSignUp;
        tfName = new JTextField( );
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(3,10,0,10);
        gridBagLayout.setConstraints( tfName, gridBag );
        panel.add( tfName );
        ((AbstractDocument) tfName.getDocument()).setDocumentFilter(new InformSystDocumentFilter());

        tfLogin = new JTextField( );
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(3,10,0,10);
        gridBagLayout.setConstraints( tfLogin, gridBag );
        panel.add( tfLogin );
        ((AbstractDocument) tfLogin.getDocument()).setDocumentFilter(new InformSystDocumentFilter());

        tfPassword = new JTextField( );
        gridBag.gridx = 0;
        gridBag.gridy = 5;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,10,3,10);
        gridBagLayout.setConstraints( tfPassword, gridBag );
        panel.add( tfPassword );
        ((AbstractDocument) tfPassword.getDocument()).setDocumentFilter(new InformSystDocumentFilter());

        lbName = new JLabel( "Enter your name:"  );
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(3,10,0,10);
        gridBagLayout.setConstraints( lbName, gridBag );
        panel.add( lbName );

        lbLogin = new JLabel( "Enter login:"  );
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(3,10,0,10);
        gridBagLayout.setConstraints( lbLogin, gridBag );
        panel.add( lbLogin );

        lbLabel2 = new JLabel( "Enter password:"  );
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(3,10,0,10);
        gridBagLayout.setConstraints( lbLabel2, gridBag );
        panel.add( lbLabel2 );

        btSignUp = new JButton( "Sign up"  );
        gridBag.gridx = 0;
        gridBag.gridy = 6;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(3,10,5,10);
        gridBagLayout.setConstraints( btSignUp, gridBag );
        btSignUp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tfName.getText().length() < 2){
                    showMessage("Name cannot contain less than 2 symbols");
                } else if (!Restaurant.isInputtedDataCorrect(tfLogin.getText(),tfPassword.getText() )){
                    showMessage("Login and password cannot contain less than 5 symbols");
                } else if(!User.isLoginFree(tfLogin.getText())) {
                    showMessage("This login is not free. There is a user with the same login.");
                } else {
                    User newUser = new User();
                    newUser.setName(tfName.getText());
                    newUser.setLogin(tfLogin.getText());
                    newUser.setPassword(tfPassword.getText());
                    client.signUp(newUser);
                    dispose();

                }
            }
        });
        panel.add( btSignUp );
    }

}


