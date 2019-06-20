package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.Command;
import information.system.Server.Model.Dish;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MenuGUI extends InformSystemGUI {
    private JButton btAdd;
    private JButton btAddDishCategory;
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
        this.client = client;
        basicInit();
        if (singnedIn){
            initFroAdmin();
        }
        add(panel);
        setVisible(true);
    }

    private void initFroAdmin() {

        btAdd = new JButton( "Add a dish"  );
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

        btAddDishCategory = new JButton( "Add a dish category"  );
        gridBag.gridx = 3;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,0);
        gridBagLayout.setConstraints(btAddDishCategory, gridBag );
        panel.add(btAddDishCategory);
        btAddDishCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DishCategoryFillingForm(client);
            }
        });


    }

    @Override
    protected void basicInit(){
         String []variantsOfSorting = {Command.PRICE, Command.DISH_CAREGORY };
        cmbSortBy = new JComboBox( variantsOfSorting );
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


        Object[] headers = {"Name", "Price", "Description"};


        DefaultTableModel model = new DefaultTableModel(null, headers);
        JTable jTable = new JTable(model){
            @Override
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        };
        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(jTable.getSelectedRow());
            }
        });
        //Создаем панель прокрутки и включаем в ее состав нашу таблицу
        JScrollPane jscrlp = new JScrollPane(jTable);
        gridBag.gridx = 3;
        gridBag.gridy = 4;
        gridBag.gridwidth = 3;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBagLayout.setConstraints( jscrlp, gridBag );
        panel.add( jscrlp );
      //  panel.add(jTable);;

        jTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(client.getMenu().get(dishesTable.getSelectedRow()) != null){
                    new DetailInformationFrame(getClient(), client.getMenu().get(dishesTable.getSelectedRow()));
                }
            }
        });

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

    /*    btUpdate = new JButton( "Update");
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
        */
      //  setValuesAtTable(new ArrayList<Dish>());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setValuesAtTable(List<Dish> menu){
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



