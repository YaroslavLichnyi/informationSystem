package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.DishCategory;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DishCategoryFillingForm extends InformSystemGUI {
    public DishCategoryFillingForm(Client client) {
        super();
        setClient(client);
        this.client = client;
        setBounds(dimension.width / 2 - 100, dimension.height / 2 - 100, 200, 200);
        init();
        add(panel);
        setVisible(true);
    }

    @Override
    protected void init() {
        JLabel lbName;
        JLabel lbHf;
        JCheckBox cbHealthyFood;
        JTextField tfName;
        JButton btAdd;

        panel.setLayout( gridBagLayout );

        lbName = new JLabel( "Name:"  );
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(10,15,0,15);
        gridBagLayout.setConstraints( lbName, gridBag );
        panel.add( lbName );

        cbHealthyFood = new JCheckBox( "Healthy food"  );
        gridBag.gridx = 1;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(10,15,0,15);
        gridBagLayout.setConstraints( cbHealthyFood, gridBag );
        panel.add( cbHealthyFood );

        tfName = new JTextField( );
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,15);
        gridBagLayout.setConstraints( tfName, gridBag );
        ((AbstractDocument) tfName.getDocument()).setDocumentFilter(new InformSystDocumentFilter());
        panel.add( tfName );

        btAdd = new JButton( "Add"  );
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
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(tfName.getText().length() < 3 ){
                    showMessage("Name is too short");
                } else {
                    DishCategory newDishCategory = new DishCategory();
                    newDishCategory.setName(tfName.getText());
                    newDishCategory.setHealthyFood(cbHealthyFood.isSelected());
                    client.add(newDishCategory);
                    dispose();
                }
            }
        });
        add(panel);
    }
}




