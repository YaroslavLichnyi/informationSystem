package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.Command;
import information.system.Server.Model.Dish;
import information.system.Server.Model.Restaurant;
import org.apache.log4j.Logger;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

public class MenuGUI extends InformSystemGUI {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(MenuGUI.class);
    private JComboBox cmbSortBy;
    private JTextField tfName;
    private JButton btShowDishCategories;
    private JTable dishesTable;
    private JLabel lbSortBy;
    private DefaultTableModel model;
    private Client client;

    public MenuGUI(Client client) {
        super();
        setBounds(dimension.width / 2 - 350, dimension.height / 2 - 300, 700, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setClient(client);
        this.client = client;
        init();
        add(panel);
        setVisible(true);
    }


    @Override
    protected void init(){
        JMenuBar jMenuBar = new JMenuBar();

        JMenu menuFile = new JMenu("File");
        JMenuItem itExit = new JMenuItem("Exit");
        menuFile.add(itExit);
        itExit.addActionListener(e -> {
            client.exit();
            System.exit(0);
        });

        jMenuBar.add(menuFile);


        if (client.getUser().isAdmin()){
            JMenu addRestMenu = new JMenu("Restaurant menu");
            JMenuItem itAddNewDish = new JMenuItem("Add new dish");
            JMenuItem itAddNewDishCategory = new JMenuItem("Add new dish category");
            itAddNewDish.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AddDishForm(client);
                }
            });
            itAddNewDishCategory.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new AddDishCategoryForm(client);
                }
            });
            addRestMenu.add(itAddNewDish);
            addRestMenu.add(itAddNewDishCategory);
            jMenuBar.add(addRestMenu);
        }



        JMenu userMenu = new JMenu("User : " + client.getUser().getName());

        JMenuItem itChangeData = new JMenuItem("Change information");
        itChangeData.addActionListener(e -> new ChangeUserDataForm(client, client.getUser()));

        JMenuItem signOut = new JMenuItem("Sign out");
        signOut.addActionListener(e -> {
            client.setUser(null);
            new SignInForm(getClient());
            dispose();
            LOGGER.info("User signed out");
        });

        JMenu updateMenu = new JMenu("Update content");
        updateMenu.addMenuListener(new MenuListener() {
            @Override
            public void menuSelected(MenuEvent e) {
                client.updateContent();
            }

            @Override
            public void menuDeselected(MenuEvent e) {

            }

            @Override
            public void menuCanceled(MenuEvent e) {

            }
        });

        userMenu.add(itChangeData);
        userMenu.add(signOut);

        jMenuBar.add(updateMenu);
        jMenuBar.add(userMenu);

        this.setJMenuBar(jMenuBar);
        this.revalidate();

         String []variantsOfSorting = {Command.DISH_CATEGORY, Command.PRICE };
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
        cmbSortBy.addActionListener(e -> setValuesAtTable(client.getDishesSortedBy((String) cmbSortBy.getSelectedItem())));

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
        ((AbstractDocument) tfName.getDocument()).setDocumentFilter(new InformSystDocumentFilter());
        panel.add(tfName);

        btShowDishCategories = new JButton( "Find"  );
        gridBag.gridx = 4;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,15);
        gridBagLayout.setConstraints(btShowDishCategories, gridBag );
        panel.add(btShowDishCategories);
        btShowDishCategories.addActionListener(e -> {
            if(tfName.getText().length() > 0){
                List <Dish> dishes = client.getDishesWhichContains(tfName.getText());
                if (dishes==null){
                    InformSystemGUI.showMessage("Problems with getting dishes from server");
                } else if (dishes.size() == 0){
                    InformSystemGUI.showMessage("There are no dishes, which contain \""
                            + tfName.getText() + "\" in name");
                } else {
                    setValuesAtTable(dishes);
                }
            } else {
             InformSystemGUI.showMessage("Enter at least 1 letter");
            }
        });


        Object[] headers = {"Dish category", "Name", "Description","Price"};
        model = new DefaultTableModel(null, headers);
        dishesTable = new JTable(model){
            @Override
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        };
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
                if(client.getRestaurant().getAllDishes().get(dishesTable.getSelectedRow()) != null){
                    new DetailInformationFrame(getClient(), client.getRestaurant().getAllDishes().get(dishesTable.getSelectedRow()));
                }
            }
        });


        btShowDishCategories = new JButton( "Show dish categories"  );
        gridBag.gridx = 2;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,15);
        gridBagLayout.setConstraints(btShowDishCategories, gridBag );
        panel.add(btShowDishCategories);
        btShowDishCategories.addActionListener(e -> new DishCategoryInfo(getClient()));

        btShowDishCategories = new JButton( "Show all dishes"  );
        gridBag.gridx = 2;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 0;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,15);
        gridBagLayout.setConstraints(btShowDishCategories, gridBag );
        panel.add(btShowDishCategories);
        btShowDishCategories.addActionListener(e -> setValuesAtTable(getClient().getRestaurant().getAllDishes()));

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

        setValuesAtTable(client.getRestaurant().getAllDishes());

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void setValuesAtTable(List<Dish> menu){
        final int tableRowSize = menu.size();
        model.setRowCount(tableRowSize);
        Iterator<Dish> taskIterator = menu.iterator();
        Dish dish;
        int i = 0;
        while (taskIterator.hasNext()){
            dish = taskIterator.next();
            model.setValueAt(Restaurant.getDishCategoryById(client.getRestaurant().getAllDishCategories(), dish.getDishCategoryId()).getName(), i, 0);
            model.setValueAt(dish.getName(), i , 1);
            model.setValueAt(dish.getDescription(), i , 2);
            model.setValueAt(dish.getPrice(), i , 3);
            i++;
        }
        while(i<tableRowSize){
            model.setValueAt(" ", i , 0);
            model.setValueAt(" ", i , 1);
            model.setValueAt(" ", i , 2);
            model.setValueAt(" ", i , 3);
            i++;
        }
        dishesTable.repaint();
    }
}



