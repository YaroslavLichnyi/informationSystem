package information.system.client.view;
import information.system.client.сontroller.Client;
import information.system.server.model.DishCategory;

import javax.swing.*;
import java.awt.*;

public class AddDishCategoryForm extends DishCategoryFillingForm {
    public AddDishCategoryForm(Client client) {
        super(client);
        setTitle("Filling form");
        extraInit();
    }

    private void extraInit(){
        JButton btAdd = new JButton( "Add"  );
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(10,15,10,15);
        gridBagLayout.setConstraints( btAdd, gridBag );
        panel.add( btAdd );
        btAdd.addActionListener(e -> {
            if(tfName.getText().length() < 3 ){
                showMessage("Name is too short");
            } else {
                DishCategory newDishCategory = new DishCategory();
                newDishCategory.setName(tfName.getText());
                newDishCategory.setHealthyFood(cbHealthyFood.isSelected());
                client.add(newDishCategory);
                dispose();
            }
        });
    }
}