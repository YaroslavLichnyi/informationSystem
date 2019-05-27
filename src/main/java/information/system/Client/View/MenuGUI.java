package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.Command;
import information.system.Server.Model.Dish;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuGUI extends InformSystemGUI {
    private JButton btAdd;
    private JButton btEdit;
    private JButton btDelete;
    private JComboBox cmbSortBy;
    private JTextField tfName;
    private JButton btFind;
    private JTable dishesTable;
    private JLabel lbSortBy;
    private JButton btUpdate;
    private DefaultTableModel model;
    private Client client;

    public MenuGUI(Client client, boolean singnedIn) {
        super();
        setBounds(dimension.width / 2 - 300, dimension.height / 2 - 150, 600, 300);
        setClient(client);
        basicInit();
        if (singnedIn){
            initFroAdmin();
        }
        add(panel);
        setVisible(true);
    }

    private void initFroAdmin() {
        btAdd = new JButton( "Add"  );
        gridBag.gridx = 3;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,0);
        gridBagLayout.setConstraints( btAdd, gridBag );
        panel.add( btAdd );
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DishFillingForm(client);
            }
        });

        btEdit = new JButton( "Edit"  );
        gridBag.gridx = 3;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,0);
        gridBagLayout.setConstraints( btEdit, gridBag );
        panel.add( btEdit );

        btDelete = new JButton( "Delete"  );
        gridBag.gridx = 3;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,0);
        gridBagLayout.setConstraints( btDelete, gridBag );
        panel.add( btDelete );
    }

    @Override
    protected void basicInit(){
        String []variantsOfSorts = {Command.PRICE, Command.DISH_CAREGORY };
        cmbSortBy = new JComboBox( variantsOfSorts );
        gridBag.gridx = 5;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,15);
        gridBagLayout.setConstraints(cmbSortBy, gridBag );
        panel.add(cmbSortBy);
        cmbSortBy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    setValuesAtTable(
                            client.getDishesSortedBy(
                                    (String) cmbSortBy.getSelectedItem()));
            }
        });

        tfName = new JTextField( );
        gridBag.gridx = 4;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.ipadx = 50;
        gridBag.insets = new Insets(15,15,0,0);
        gridBagLayout.setConstraints(tfName, gridBag );
        panel.add(tfName);

        btFind = new JButton( "Find"  );
        gridBag.gridx = 5;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,15);
        gridBagLayout.setConstraints( btFind, gridBag );
        panel.add( btFind );
        btFind.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setValuesAtTable(
                        client.getDishesWhichContains(
                                tfName.getText()));
            }
        });

/*
        Object [][]dataTable1 = new String[][] { new String[] {"11", "21", "SGFHF"},
                new String[] {"12", "22", "dgs"},
                new String[] {"13", "23", "fsdg"} }; */
       // Object []colsTable1 = new String[] { "Name", "Price" , "Description"};
        //dishesTable = new JTable( null, colsTable1 );


        Object [] headers = {"Name", "Price", "Description"};
        model = new DefaultTableModel(null, headers);
        model.setColumnIdentifiers(headers);
        dishesTable = new JTable(model){
            @Override
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        };

        JScrollPane scrollPane = new JScrollPane(dishesTable);
        gridBag.gridx = 3;
        gridBag.gridy = 4;
        gridBag.gridwidth = 4;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 4;
        gridBag.weighty = 4;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,15,15);
        gridBagLayout.setConstraints(dishesTable, gridBag );
        panel.add(dishesTable);

        lbSortBy = new JLabel( "Sort by:"  );
        gridBag.gridx = 4;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.NORTHEAST;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTHEAST;
        gridBag.insets = new Insets(15,15,0,0);
        gridBagLayout.setConstraints( lbSortBy, gridBag );
        panel.add( lbSortBy );

        btUpdate = new JButton( "Update");
        gridBag.gridx = 5;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,15);
        gridBagLayout.setConstraints(btUpdate, gridBag );
        panel.add(btUpdate);
        setValuesAtTable(new ArrayList<Dish>());
    }

    private void setValuesAtTable(List<Dish> menu){

        //test//
        Dish dish1 = new Dish("name1", 3.56, "Some defhdjkdgkdgjkscr");
        Dish dish3 = new Dish("name3", 34.45, "Some defhkfkhscr");
        Dish dish2 = new Dish("name2", 3.57, "Some defhkdfhkdgfscr");
        Dish dish4 = new Dish("name4", 17.56, "Some defhdjkdgkdgjkscr");
        Dish dish5 = new Dish("name5", 45, "Some defhkfkhscr");
        Dish dish6= new Dish("name6", 3.57, "Some defhkdfhkdgfscr");
        menu.add(dish3);
        menu.add(dish2);
        menu.add(dish1);
        menu.add(dish5);
        menu.add(dish4);
        menu.add(dish6);
        ////////
        final int tableRowSize = menu.size();
        model.setRowCount(tableRowSize);
        Iterator<Dish> taskIterator = menu.iterator();
        Dish dish;
        int i = 0;
        while (taskIterator.hasNext()){
            dish = taskIterator.next();
            model.setValueAt(dish.getName(), i , 0);
            model.setValueAt(dish.getPrice(), i , 1);
            model.setValueAt(dish.getDescription(), i , 2);
            i++;
        }
        while(i<tableRowSize){
            model.setValueAt(" ", i , 0);
            model.setValueAt(" ", i , 1);
            model.setValueAt(" ", i , 2);
            i++;
        }
        dishesTable.repaint();
    }
}


