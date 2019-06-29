package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.Dish;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DishFillingForm extends InformSystemGUI {
    private Client client;
    public DishFillingForm(Client client) {
        super();
        setClient(client);
        this.client = client;
        setBounds(dimension.width / 2 - 150, dimension.height / 2 - 200, 300, 400);
        init();
        add(panel);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    protected void init() {
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
        JComboBox cmbDishCategory;

        taDishDescription = new JTextArea(2,10);
        gridBag.gridx = 0;
        gridBag.gridy = 5;
        gridBag.gridwidth = 4;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,15,15);
        gridBagLayout.setConstraints( taDishDescription, gridBag );
        panel.add( taDishDescription );

        tfDishName = new JTextField( );
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 4;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,15);
        gridBagLayout.setConstraints( tfDishName, gridBag );
        panel.add( tfDishName );
        ((AbstractDocument) tfDishName.getDocument()).setDocumentFilter(new InformSystDocumentFilter());


        lbDishName = new JLabel( "Enter dish name:"  );
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 4;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,0,0);
        gridBagLayout.setConstraints( lbDishName, gridBag );
        panel.add( lbDishName );

        lbLabel1 = new JLabel( "Enter dish price:"  );
        gridBag.gridx = 0;
        gridBag.gridy = 2;
        gridBag.gridwidth = 4;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,0,0);
        gridBagLayout.setConstraints( lbLabel1, gridBag );
        panel.add( lbLabel1 );

        spnHryvnas = new JSpinner( );
        gridBag.gridx = 0;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.NONE;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,0,0);
        gridBag.ipadx = 30;
        gridBagLayout.setConstraints( spnHryvnas, gridBag );
        panel.add( spnHryvnas );

        lbDishDescription = new JLabel( "Enter dish description:"  );
        gridBag.gridx = 0;
        gridBag.gridy = 4;
        gridBag.gridwidth = 4;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,0,0);
        gridBagLayout.setConstraints( lbDishDescription, gridBag );
        panel.add( lbDishDescription );

        spnKopeikas = new JSpinner( );
        gridBag.gridx = 2;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.NONE;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,0,0,0);
        gridBag.ipadx = 30;
        gridBagLayout.setConstraints( spnKopeikas, gridBag );
        panel.add( spnKopeikas );

        lbHrn = new JLabel( "hrn"  );
        gridBag.gridx = 1;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.NONE;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTHWEST;
        gridBag.insets = new Insets(0,5,0,0);
        gridBagLayout.setConstraints( lbHrn, gridBag );
        panel.add( lbHrn );

        lbKop = new JLabel( "kop"  );
        gridBag.gridx = 3;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.NONE;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTHWEST;
        gridBag.insets = new Insets(0,5,0,0);
        gridBagLayout.setConstraints( lbKop, gridBag );
        panel.add( lbKop );

        lbLabel1 = new JLabel( "Chose a dish category:"  );
        gridBag.gridx = 0;
        gridBag.gridy = 6;
        gridBag.gridwidth = 4;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,0,0);
        gridBagLayout.setConstraints( lbLabel1, gridBag );
        panel.add( lbLabel1 );

        String[] dishCategories
                = new String[client.getDishCategories().size()];
        for (int i = 0; i < client.getDishCategories().size(); i++) {
            dishCategories[i] = client.getDishCategories().get(i).getName();
        }
        cmbDishCategory = new JComboBox( dishCategories );
        gridBag.gridx = 1;
        gridBag.gridy = 7;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,15);
        gridBagLayout.setConstraints(cmbDishCategory, gridBag );
        panel.add(cmbDishCategory);

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
        btAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Dish newDish = new Dish();
                newDish.setName(tfDishName.getText());
                newDish.setPrice(Double.valueOf((int)spnHryvnas.getValue())
                                 + (Double.valueOf((int)spnKopeikas.getValue()))/100);
                newDish.setDescription(taDishDescription.getText());
                client.getDishCategories().get(cmbDishCategory.getSelectedIndex()).addDish(newDish);
                client.add(newDish);
            }
        });
        add(panel);
    }
}
