package information.system.client.view;
import information.system.client.сontroller.Client;
import information.system.server.model.Dish;
import information.system.server.model.DishCategory;

import javax.swing.*;
import java.awt.*;
public class DishInfo extends InformSystemGUI{
    private Dish dish;
    public DishInfo(Client client, Dish dish) {
        super();
        setTitle("Dish details");
        this.dish = dish;
        setBounds(dimension.width / 2 - 200, dimension.height / 2 - 150, 400, 300);
        this.client = client;
        init();
        add(panel);
        setVisible(true);
    }

    @Override
    protected void init() {
        JLabel lbName;
        JLabel lbDishName;
        JLabel lbPrice;
        JLabel lbDescription;
        JLabel lbLabel6;
        JLabel lbLabel9;
        JLabel lbDishcategoryName;
        JButton btEdit;
        JButton btRomove;
        panel = new JPanel();

        panel.setLayout( gridBagLayout );

        lbName = new JLabel( "Name"  );
        gridBag.gridx = 2;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,0);
        gridBagLayout.setConstraints( lbName, gridBag );
        panel.add( lbName );

        lbDishName = new JLabel( dish.getName()  );
        gridBag.gridx = 3;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,0,0,15);
        gridBagLayout.setConstraints( lbDishName, gridBag );
        panel.add( lbDishName );

        lbPrice = new JLabel( "Price"  );
        gridBag.gridx = 2;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,0);
        gridBagLayout.setConstraints( lbPrice, gridBag );
        panel.add( lbPrice );

        lbDishName = new JLabel(String.valueOf(dish.getPrice()));
        gridBag.gridx = 3;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,0,0,15);
        gridBagLayout.setConstraints( lbDishName, gridBag );
        panel.add( lbDishName );

        lbDescription = new JLabel( "Description"  );
        gridBag.gridx = 2;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,0);
        gridBagLayout.setConstraints( lbDescription, gridBag );
        panel.add( lbDescription );

        lbLabel6 = new JLabel( dish.getDescription()  );
        gridBag.gridx = 3;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,0,0,15);
        gridBagLayout.setConstraints( lbLabel6, gridBag );
        panel.add( lbLabel6 );

        lbLabel9 = new JLabel( "Dish category:"  );
        gridBag.gridx = 2;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,0);
        gridBagLayout.setConstraints( lbLabel9, gridBag );
        panel.add( lbLabel9 );

        lbDishcategoryName = new JLabel();
        for (DishCategory dishCategory : client.getRestaurant().getAllDishCategories()){
            if (dish.getDishCategoryId()==dishCategory.getId()){
                if (dishCategory.isHealthyFood()){
                    lbDishcategoryName.setText(dishCategory.getName() + " (healthy)");
                    lbDishcategoryName.setForeground(Color.GREEN);
                } else {
                    lbDishcategoryName.setText(dishCategory.getName() + " (not healthy)");
                }
                break;
            }
        }
        gridBag.gridx = 3;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,0,0,15);
        gridBagLayout.setConstraints( lbDishcategoryName, gridBag );
        panel.add( lbDishcategoryName );

        btEdit = new JButton( "Edit"  );
        btEdit.setEnabled(client.getUser().isAdmin() && client.isConnectedToServer());
        gridBag.gridx = 2;
        gridBag.gridy = 5;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.insets = new Insets(15,15,15,15);
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBagLayout.setConstraints( btEdit, gridBag );
        panel.add( btEdit );
        btEdit.addActionListener( e -> {
            new EditDishForm(client, dish);
            dispose();
        });

        btRomove = new JButton( "Remove"  );
        btRomove.setEnabled(client.getUser().isAdmin() && client.isConnectedToServer());
        gridBag.gridx = 3;
        gridBag.gridy = 5;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.insets = new Insets(15,15,15,15);
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBagLayout.setConstraints( btRomove, gridBag );
        panel.add( btRomove );
        btRomove.addActionListener( e -> {
            if (client.delete(dish)){
                dispose();
            } else {
                InformSystemGUI.showMessage("Dish was not removed");
            }
        });
        add(panel);
    }

}
