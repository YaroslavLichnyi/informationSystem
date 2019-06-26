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
import java.util.Iterator;
import java.util.List;

public class MenuGUI extends InformSystemGUI {
    private JButton btAdd;
    private JButton btAddDishCategory;
    private JComboBox cmbSortBy;
    private JTextField tfName;
    private JButton btShowDishCategories;
    private JTable dishesTable;
    private JLabel lbSortBy;
    private DefaultTableModel model;
    private Client client;

    public MenuGUI(Client client, boolean singnedIn) {
        super();
        setBounds(dimension.width / 2 - 350, dimension.height / 2 - 300, 700, 600);
        setClient(client);
        this.client = client;
        init();
        if (singnedIn){
            initFroAdmin();
        }
        add(panel);
        setVisible(true);
    }

    private void initFroAdmin() {
/*
        btAdd = new JButton( "Add new dish"  );
        gridBag.gridx = 1;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0;
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

        btAddDishCategory = new JButton( "Add new dish category"  );
        gridBag.gridx = 1;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0;
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
        }); */
    }

    @Override
    protected void init(){
        JMenuBar jMenuBar = new JMenuBar();

        JMenu addMenu = new JMenu("Add");
        JMenuItem itAddNewDish = new JMenuItem("Add new dish");
        JMenuItem itAddNewDishCategory = new JMenuItem("Add new dish category");
        itAddNewDish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DishFillingForm(client);
            }
        });
        itAddNewDishCategory.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new DishCategoryFillingForm(client);
            }
        });
        addMenu.add(itAddNewDish);
        addMenu.add(itAddNewDishCategory);

        JMenu closeMenu = new JMenu("Exit");

        JMenu userMenu = new JMenu("User");

        JMenuItem itChangeData = new JMenuItem("Change information");
        itChangeData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangeUserDataForm(client, client.getUser());
            }
        });
        JMenu changePort = new JMenu("Change port");
        changePort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ChangePortForm(client);
            }
        });
        jMenuBar.add(changePort);
        userMenu.add(itChangeData);
        userMenu.add(new JMenuItem("Sign out"));
        jMenuBar.add(closeMenu);
        jMenuBar.add(addMenu);
        jMenuBar.add(userMenu);

        this.setJMenuBar(jMenuBar);
        this.revalidate();

         String []variantsOfSorting = {Command.PRICE, Command.DISH_CATEGORY };
        cmbSortBy = new JComboBox( variantsOfSorting );
        gridBag.gridx = 4;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0;
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

        tfName = new JTextField();
        gridBag.gridx = 3;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.EAST;
        gridBag.insets = new Insets(15,15,0,0);
        gridBagLayout.setConstraints(tfName, gridBag );
        panel.add(tfName);

        btShowDishCategories = new JButton( "Find"  );
        gridBag.gridx = 4;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 0;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,15);
        gridBagLayout.setConstraints(btShowDishCategories, gridBag );
        panel.add(btShowDishCategories);
        btShowDishCategories.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setValuesAtTable(
                        client.getDishesWhichContains(
                                tfName.getText()));
            }
        });


        Object[] headers = {"Name", "Price", "Description"};
        model = new DefaultTableModel(null, headers);
        dishesTable = new JTable(model){
            @Override
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        };
        dishesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(dishesTable.getSelectedRow());
            }
        });
        JScrollPane jscrlp = new JScrollPane(dishesTable);
        gridBag.gridx = 1;
        gridBag.gridy = 4;
        gridBag.gridwidth = 4;
        gridBag.gridheight = 4;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,15,15);
        gridBagLayout.setConstraints( jscrlp, gridBag );
        panel.add( jscrlp );
        dishesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(client.getMenu().get(dishesTable.getSelectedRow()) != null){
                    new DetailInformationFrame(getClient(), client.getMenu().get(dishesTable.getSelectedRow()));
                }
            }
        });


        btShowDishCategories = new JButton( "Show dish categories"  );
        gridBag.gridx = 2;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 0;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,15);
        gridBagLayout.setConstraints(btShowDishCategories, gridBag );
        panel.add(btShowDishCategories);

        lbSortBy = new JLabel( "Sort by:"  );
        gridBag.gridx = 3;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.EAST;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.EAST;
        gridBag.insets = new Insets(3,5,0,0);
        gridBagLayout.setConstraints( lbSortBy, gridBag );
        panel.add( lbSortBy );

        setValuesAtTable(client.getMenu());

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



