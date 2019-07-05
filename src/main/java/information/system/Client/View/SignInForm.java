package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.User;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SignInForm extends InformSystemGUI {
    private JTextField tfLogin;
    private JPasswordField tfPassword;
    public SignInForm(Client client) {
        setBounds(dimension.width / 2 - 150, dimension.height / 2 - 125, 300, 250);
        setClient(client);
        this.client = client;
        init();
        add(panel);
        setTitle("Sign in");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    protected void init() {
        JLabel lbLogin;
        JLabel lbPassword;
        JButton btSignIn;
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
        gridBag.insets = new Insets(0,10,10,10);
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
        gridBag.insets = new Insets(0,10,10,10);
        gridBagLayout.setConstraints( lbPassword, gridBag );
        panel.add( lbPassword );

        tfPassword = new JPasswordField( );
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBagLayout.setConstraints( tfPassword, gridBag );
        gridBag.insets = new Insets(0,10,10,10);
        panel.add( tfPassword );
        ((AbstractDocument) tfPassword.getDocument()).setDocumentFilter(new InformSystDocumentFilter());

        btSignIn = new JButton( "Sign in"  );
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(10,10,10,10);
        gridBagLayout.setConstraints( btSignIn, gridBag );
        panel.add( btSignIn );
        btSignIn.addActionListener(e -> signIn());

        btBut1 = new JButton( "Sign up"  );
        gridBag.gridx = 1;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.insets = new Insets(10,10,10,10);
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBagLayout.setConstraints( btBut1, gridBag );
        panel.add( btBut1 );
        btBut1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new SignUpForm(client);
            }
        });

        tfPassword.addKeyListener(new KeyAdapter() {
            /**
             * Invoked when a key has been typed.
             * This event occurs when a key press is followed by a key release.
             *
             * @param e
             */
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    signIn();
                }
            }
        });
    }
    private void signIn(){
        User user = client.signIn(tfLogin.getText(), String.valueOf(tfPassword.getPassword()));
        if (user == null){
            JFrame frame = new JFrame("Error");
            JOptionPane.showMessageDialog(frame, "There is no user with the same login or/and password");
        } else {
            client.setUser(user);
            client.showGeneralFrame();
            dispose();
        }
    }
}
