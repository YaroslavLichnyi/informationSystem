package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.DishCategory;

import javax.swing.*;
import java.awt.*;

public class EditDishCategoryForm extends DishCategoryFillingForm {
    private DishCategory dishCategory;
    public EditDishCategoryForm(Client client, DishCategory dishCategory) {
        super(client);
        setTitle("Edit form");
        this.dishCategory = dishCategory;
        extraInit();

    }
    private void extraInit(){
        tfName.setText(dishCategory.getName());
        cbHealthyFood.setSelected(dishCategory.isHealthyFood());
        JButton btEdit = new JButton( "Edit"  );
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(10,15,10,15);
        gridBagLayout.setConstraints( btEdit, gridBag );
        panel.add( btEdit );
        btEdit.addActionListener(e -> {
            if(tfName.getText().length() < 3 ){
                showMessage("Name is too short");
            } else {
                DishCategory newDishCategory = new DishCategory();
                newDishCategory.setName(tfName.getText());
                newDishCategory.setHealthyFood(cbHealthyFood.isSelected());
                dispose();
                if(client.edit(dishCategory, newDishCategory)){
                    dispose();
                } else {
                    InformSystemGUI.showMessage("Cannot edit this dish category");
                }
            }
        });

        JButton btRemove = new JButton( "Remove"  );
        gridBag.gridx = 1;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(10,15,10,15);
        gridBagLayout.setConstraints( btRemove, gridBag );
        panel.add( btRemove );
        btRemove.addActionListener( e -> {
            if(client.delete(dishCategory)){
                dispose();
            } else {
                InformSystemGUI.showMessage("Cannot remove this dish category");
            }
        });
    }
}
