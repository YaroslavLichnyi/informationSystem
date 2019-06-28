package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ChangeUserDataForm extends InformSystemGUI{

    private User user;

    public ChangeUserDataForm(Client client, User user) {
        super();
        this.user = user;
        setBounds(dimension.width / 2 - 125, dimension.height / 2 - 125, 250, 250);
        setClient(client);
        init();
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    protected void init() {
        JLabel lbUserData;
        JLabel lbName;
        JTextField tfName;
        JTextField tfLogin;
        JTextField tfPassword;
        JButton btSave;
        JButton btDeleteAccount;
        JLabel lbPassword;
        JLabel lbLogin;

        lbUserData = new JLabel( "User data"  );
        lbUserData.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.CENTER;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.CENTER;
        gridBag.insets = new Insets( 0,5,0,0 );
        gridBagLayout.setConstraints( lbUserData, gridBag );
        panel.add( lbUserData );

        lbName = new JLabel( "Name"  );
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.CENTER;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.EAST;
        gridBag.insets = new Insets( 0,0,0,15 );
        gridBagLayout.setConstraints( lbName, gridBag );
        panel.add( lbName );

        tfName = new JTextField( );
        tfName.setText(user.getName());
        gridBag.gridx = 1;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets = new Insets( 0,0,0,5 );
        gridBagLayout.setConstraints( tfName, gridBag );
        panel.add( tfName );

        btSave = new JButton( "Save"  );
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.SOUTH;
        gridBag.insets = new Insets( 3,5,3,5 );
        gridBagLayout.setConstraints( btSave, gridBag );
        panel.add( btSave );

        btDeleteAccount = new JButton( "Delete account"  );
        gridBag.gridx = 1;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.SOUTH;
        gridBag.insets = new Insets( 3,5,3,5 );
        gridBagLayout.setConstraints( btDeleteAccount, gridBag );
        panel.add( btDeleteAccount );

        lbPassword = new JLabel( "Password"  );
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.CENTER;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.EAST;
        gridBag.insets = new Insets( 0,0,0,15 );
        gridBagLayout.setConstraints( lbPassword, gridBag );
        panel.add( lbPassword );

        tfPassword = new JTextField( );
        tfPassword.setText(user.getPassword());
        gridBag.gridx = 1;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets = new Insets( 0,0,0,5 );
        gridBagLayout.setConstraints( tfPassword, gridBag );
        panel.add( tfPassword );

        lbLogin = new JLabel( "Login"  );
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.CENTER;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.EAST;
        gridBag.insets = new Insets( 0,0,0,15 );
        gridBagLayout.setConstraints( lbLogin, gridBag );
        panel.add( lbLogin );

        tfLogin = new JTextField( );
        tfLogin.setText(user.getLogin());
        gridBag.gridx = 1;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.WEST;
        gridBag.insets = new Insets( 0,0,0,5 );
        gridBagLayout.setConstraints( tfLogin, gridBag );
        panel.add( tfLogin );

        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                User newUser = new User();
                newUser.setName(tfName.getText());
                newUser.setLogin(tfLogin.getText());
                newUser.setPassword(tfPassword.getText());
                client.edit(user, newUser);
            }
        });

        btDeleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (client.delete(user)){
                    client.signOut();
                }
            }
        });
        add(panel);
    }
}



