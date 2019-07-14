package information.system.client.view;

import information.system.client.сontroller.Client;
import information.system.server.model.Dish;

import javax.swing.*;
import java.awt.*;

public class AddDishForm extends DishFillingForm {
    public AddDishForm(Client client) {
        super(client);
        setTitle("Filling form");
        extraInit();
    }
    private void extraInit(){
        JButton btAdd = new JButton("Add");
        gridBag.gridx = 1;
        gridBag.gridy = 8;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,10,15);
        gridBagLayout.setConstraints(btAdd, gridBag );
        panel.add(btAdd);
        btAdd.addActionListener(e -> {
            Dish newDish = new Dish();
            newDish.setName(tfDishName.getText());
            newDish.setPrice(getInputtedPrice());
            newDish.setDescription(taDishDescription.getText());
            client.getRestaurant().getAllDishCategories().get(cmbDishCategory.getSelectedIndex()).addDish(newDish);
            if(client.add(newDish)){
                dispose();
            } else {
                InformSystemGUI.showMessage("Dish was not added");
            }
        });
    }

    private double getInputtedPrice(){
        return Double.valueOf((int)spnHryvnas.getValue())
                + (double) (int) spnKopeikas.getValue()
                / 100.0;
    }
}
