package information.system.Client.View;

import information.system.Client.Controller.Client;
import information.system.Server.Model.Dish;

import javax.swing.*;
import java.awt.*;

public class AddDishForm extends DishFillingForm {
    private JButton btAdd;
    public AddDishForm(Client client) {
        super(client);
        setTitle("Filling form");
        extraInit();
    }
    private void extraInit(){
        btAdd = new JButton( "Add"  );
        gridBag.gridx = 1;
        gridBag.gridy = 8;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,10,15);
        gridBagLayout.setConstraints( btAdd, gridBag );
        panel.add( btAdd );
        btAdd.addActionListener(e -> {
            Dish newDish = new Dish();
            newDish.setName(tfDishName.getText());
            newDish.setPrice(getInputedPrice());
            newDish.setDescription(taDishDescription.getText());
            client.getRestaurant().getAllDishCategories().get(cmbDishCategory.getSelectedIndex()).addDish(newDish);
            if(client.add(newDish)){
                dispose();
            } else {
                InformSystemGUI.showMessage("Dish was not added");
            }
        });
    }
}
