package information.system.Client.View;
import information.system.Client.Controller.Client;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class DishCategoryFillingForm extends InformSystemGUI {
    protected JCheckBox cbHealthyFood;
    protected JTextField tfName;
    public DishCategoryFillingForm(Client client) {
        super();
        setClient(client);
        this.client = client;
        setBounds(dimension.width / 2 - 200, dimension.height / 2 - 100, 400, 200);
        init();
        add(panel);
        setVisible(true);
    }

    @Override
    protected void init() {
        JLabel lbName = new JLabel( "Name:"  );
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
        gridBag.gridx = 0;
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
        add(panel);
    }
}




