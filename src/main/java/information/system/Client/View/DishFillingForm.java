package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.Dish;

import javax.swing.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DishFillingForm extends InformSystemGUI {
    private Client client;
    public DishFillingForm(Client client) {
        super();
        this.client = client;
        setBounds(dimension.width / 2 - 150, dimension.height / 2 - 150, 300, 300);
        basicInit();
        add(panel);
        setVisible(true);
    }

    @Override
    protected void basicInit() {
        final JTextArea taDishDescription;
        final JTextField tfDishName;
        JLabel lbDishName;
        JLabel lbLabel1;
        final JSpinner spnHryvnas;
        JLabel lbDishDescription;
        final JSpinner spnKopeikas;
        JLabel lbHrn;
        JLabel lbKop;
        JButton btAdd;

        panel = new JPanel();
        GridBagLayout gbPanel0 = new GridBagLayout();
        GridBagConstraints gbcPanel0 = new GridBagConstraints();
        panel.setLayout( gbPanel0 );

        taDishDescription = new JTextArea(2,10);
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 5;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,15,15,15);
        gbPanel0.setConstraints( taDishDescription, gbcPanel0 );
        panel.add( taDishDescription );

        tfDishName = new JTextField( );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 1;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(15,15,0,15);
        gbPanel0.setConstraints( tfDishName, gbcPanel0 );
        panel.add( tfDishName );

        lbDishName = new JLabel( "Enter dish name:"  );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 0;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,15,0,0);
        gbPanel0.setConstraints( lbDishName, gbcPanel0 );
        panel.add( lbDishName );

        lbLabel1 = new JLabel( "Enter dish price:"  );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 2;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,15,0,0);
        gbPanel0.setConstraints( lbLabel1, gbcPanel0 );
        panel.add( lbLabel1 );

        spnHryvnas = new JSpinner( );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 3;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,15,0,0);
        gbcPanel0.ipadx = 30;
        gbPanel0.setConstraints( spnHryvnas, gbcPanel0 );
        panel.add( spnHryvnas );

        lbDishDescription = new JLabel( "Enter dish description:"  );
        gbcPanel0.gridx = 0;
        gbcPanel0.gridy = 4;
        gbcPanel0.gridwidth = 4;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,15,0,0);
        gbPanel0.setConstraints( lbDishDescription, gbcPanel0 );
        panel.add( lbDishDescription );

        spnKopeikas = new JSpinner( );
        gbcPanel0.gridx = 2;
        gbcPanel0.gridy = 3;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(5,0,0,0);
        gbcPanel0.ipadx = 30;
        gbPanel0.setConstraints( spnKopeikas, gbcPanel0 );
        panel.add( spnKopeikas );

        lbHrn = new JLabel( "hrn"  );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 3;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTHWEST;
        gbcPanel0.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints( lbHrn, gbcPanel0 );
        panel.add( lbHrn );

        lbKop = new JLabel( "kop"  );
        gbcPanel0.gridx = 3;
        gbcPanel0.gridy = 3;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.NONE;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 1;
        gbcPanel0.anchor = GridBagConstraints.NORTHWEST;
        gbcPanel0.insets = new Insets(0,5,0,0);
        gbPanel0.setConstraints( lbKop, gbcPanel0 );
        panel.add( lbKop );

        btAdd = new JButton( "Add"  );
        gbcPanel0.gridx = 1;
        gbcPanel0.gridy = 6;
        gbcPanel0.gridwidth = 1;
        gbcPanel0.gridheight = 1;
        gbcPanel0.fill = GridBagConstraints.BOTH;
        gbcPanel0.weightx = 1;
        gbcPanel0.weighty = 0;
        gbcPanel0.anchor = GridBagConstraints.NORTH;
        gbcPanel0.insets = new Insets(0,0,10,0);
        gbPanel0.setConstraints( btAdd, gbcPanel0 );
        panel.add( btAdd );
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dish newDish = new Dish();
                newDish.setName(tfDishName.getText());
                newDish.setPrice((int)spnHryvnas.getValue() + ((double)spnKopeikas.getValue())/100);
                newDish.setDescription(taDishDescription.getText());
                client.add(newDish);
            }
        });

        add(panel);

    }
}
