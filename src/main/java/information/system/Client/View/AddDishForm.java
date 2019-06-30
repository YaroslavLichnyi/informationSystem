package information.system.Client.View;

import information.system.Client.Controller.Client;
import information.system.Server.Model.Dish;

import javax.swing.*;
import java.awt.*;

public class AddDishForm extends DishFillingForm {
    private JButton btAdd;
    public AddDishForm(Client client) {
        super(client);
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
            if((double) (int) spnHryvnas.getValue()
                    + ((double) (int) spnKopeikas.getValue())/100 < 0 ){
                InformSystemGUI.showMessage("The price can not be less than 0");
            } else {
                Dish newDish = new Dish();
                newDish.setName(tfDishName.getText());
                newDish.setPrice((double) (int) spnHryvnas.getValue()
                        + ((double) (int) spnKopeikas.getValue())/100);
                newDish.setDescription(taDishDescription.getText());
                client.getDishCategories().get(cmbDishCategory.getSelectedIndex()).addDish(newDish);
                //срабатывает если поставть client.add(newDish) два раза
                client.add(newDish);
                if(client.add(newDish)){
                    dispose();
                } else {
                    InformSystemGUI.showMessage("Dish was not added");
                }
            }
        });
    }
}
