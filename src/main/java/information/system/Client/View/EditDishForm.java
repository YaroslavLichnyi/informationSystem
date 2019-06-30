package information.system.Client.View;

import information.system.Client.Controller.Client;
import information.system.Server.Model.Dish;
import information.system.Server.Model.Restaurant;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditDishForm extends DishFillingForm {
    private JButton btEdit;
    private Dish oldDish;
    public EditDishForm(Client client, Dish oldDish) {
        super(client);
        this.oldDish = oldDish;
        extraInit();
    }
    private void extraInit(){
        taDishDescription.setText(oldDish.getDescription());
        tfDishName.setText(oldDish.getName());
        spnHryvnas.setValue((int)Math.floor(oldDish.getPrice()));
        spnKopeikas.setValue((oldDish.getPrice() - (int)Math.floor(oldDish.getPrice()))*100);
        cmbDishCategory.setSelectedItem(Restaurant.getDishCategoryById(client.getDishCategories(), oldDish.getDishCategoryId()).getName());

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
        btEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dish newDish = new Dish();
                newDish.setName(tfDishName.getText());
                newDish.setPrice(Double.valueOf((int)spnHryvnas.getValue())
                        + (Double.valueOf((int)spnKopeikas.getValue()))/100);
                newDish.setDescription(taDishDescription.getText());
                client.getDishCategories().get(cmbDishCategory.getSelectedIndex()).addDish(newDish);
                client.edit(oldDish,newDish);
            }
        });
    }
}
