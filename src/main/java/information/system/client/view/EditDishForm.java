package information.system.client.view;

import information.system.client.сontroller.Client;
import information.system.server.model.Dish;
import information.system.server.model.Restaurant;

import javax.swing.*;
import java.awt.*;

public class EditDishForm extends DishFillingForm {
    private Dish oldDish;
    public EditDishForm(Client client, Dish oldDish) {
        super(client);
        setTitle("Edit form");
        this.oldDish = oldDish;
        extraInit();
    }
    private void extraInit(){
        taDishDescription.setText(oldDish.getDescription());
        tfDishName.setText(oldDish.getName());
        spnHryvnas.setValue((int)Math.floor(oldDish.getPrice()));
        spnKopeikas.setValue((oldDish.getPrice() - (int)Math.floor(oldDish.getPrice()))*100);
        cmbDishCategory.setSelectedItem(Restaurant.getDishCategoryById(client.getRestaurant().getAllDishCategories(), oldDish.getDishCategoryId()).getName());

        JButton btEdit = new JButton("Edit");
        gridBag.gridx = 1;
        gridBag.gridy = 8;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,10,15);
        gridBagLayout.setConstraints(btEdit, gridBag );
        panel.add(btEdit);
        btEdit.addActionListener(e -> {
            Dish newDish = new Dish();
            newDish.setName(tfDishName.getText());
            newDish.setPrice(getInputedPrice());
            newDish.setDescription(taDishDescription.getText());
            client.getRestaurant().getAllDishCategories().get(cmbDishCategory.getSelectedIndex()).addDish(newDish);
            if(client.edit(oldDish,newDish)){
                dispose();
            } else {
                InformSystemGUI.showMessage("Dish was not edited");
            }
        });
    }

    private double getInputedPrice(){
        return Double.valueOf((int)spnHryvnas.getValue())
                + (double) spnKopeikas.getValue()
                / 100.0;
    }
}
