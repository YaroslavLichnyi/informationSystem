package information.system.Client.View;

import information.system.Client.Controller.Client;
import information.system.Server.Model.Dish;
import information.system.Server.Model.Restaurant;

import javax.swing.*;
import java.awt.*;

public class EditDishForm extends DishFillingForm {
    private JButton btEdit;
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

        btEdit = new JButton( "Edit"  );
        gridBag.gridx = 1;
        gridBag.gridy = 8;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,10,15);
        gridBagLayout.setConstraints( btEdit, gridBag );
        panel.add( btEdit );
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
}
