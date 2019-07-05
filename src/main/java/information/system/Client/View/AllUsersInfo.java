package information.system.Client.View;

import information.system.Client.Controller.Client;
import information.system.Server.Model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.LinkedList;

public class AllUsersInfo extends InformSystemGUI {
    private DefaultTableModel model = new DefaultTableModel();
    private JTable userTable = new JTable();
    private LinkedList <User> users;

    public AllUsersInfo(Client client) {
        super();
        setTitle("Dish categories");
        setBounds(dimension.width / 2 - 125, dimension.height / 2 - 125, 250, 250);
        setClient(client);
        init();
        updateTable();
    }

    @Override
    protected void init() {
        Object[] headers = {"Name", "login", "admin"};
        model = new DefaultTableModel(null, headers);
        userTable = new JTable(model){
            @Override
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        };
        userTable.setGridColor(Color.WHITE);
        JScrollPane jscrlp = new JScrollPane(userTable);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,10,10,10);
        gridBagLayout.setConstraints( jscrlp, gridBag );
        panel.add( jscrlp );
        userTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(users.get(userTable.getSelectedRow()) != null){
                    String message = "Are you sure you want to make this user an administrator?";
                    int reply = JOptionPane.showConfirmDialog(null,message , "Confirm removing", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        client.makeAdmin(users.get(userTable.getSelectedRow())) ;
                        System.out.println(users.get(userTable.getSelectedRow()));
                        updateTable();
                    } else {
                        System.out.println(users.get(userTable.getSelectedRow()));
                    }
                }
            }
        });
    }

    private void updateTable(){
        users = (LinkedList<User>) client.getAllUsers();
        if ( users != null ){
            final int tableRowSize = users.size();
            model.setRowCount(tableRowSize);
            Iterator<User> userIterator = users.iterator();
            User user;
            int i = 0;
            while (userIterator.hasNext()){
                user = userIterator.next();
                model.setValueAt(user.getName(), i , 0);
                model.setValueAt(user.getLogin(), i , 1);
                model.setValueAt(user.isAdmin(), i , 1);
                i++;
            }
            while(i<tableRowSize){
                model.setValueAt(" ", i , 0);
                model.setValueAt(" ", i , 1);
                model.setValueAt(" ", i , 2);
                i++;
            }
            userTable.repaint();
        }
    }
}
