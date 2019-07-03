package information.system.Client.View;
import information.system.Client.Controller.Client;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.*;

public class DishFillingForm extends InformSystemGUI {
    protected JTextArea taDishDescription;
    protected JTextField tfDishName;
    protected JLabel lbDishName;
    protected JLabel lbLabel1;
    protected JSpinner spnHryvnas;
    protected JLabel lbDishDescription;
    protected JSpinner spnKopeikas;
    protected JLabel lbHrn;
    protected JLabel lbKop;
    protected JComboBox cmbDishCategory;
    public DishFillingForm(Client client) {
        super();
        setClient(client);
        this.client = client;
        setBounds(dimension.width / 2 - 250, dimension.height / 2 - 200, 500, 400);
        init();
        add(panel);
        setVisible(true);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    @Override
    protected void init() {


        taDishDescription = new JTextArea(3,20);
        taDishDescription.setMaximumSize(new Dimension(350, 200));
        JScrollPane scrollPane = new JScrollPane(taDishDescription);
        gridBag.gridx = 0;
        gridBag.gridy = 5;
        gridBag.gridwidth = 4;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,15,15);
        gridBagLayout.setConstraints( scrollPane, gridBag );
        //panel.add( taDishDescription );
        panel.add(scrollPane);

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

        SpinnerModel modelHr = new SpinnerNumberModel(0, 0, 10000, 1);
        spnHryvnas = new JSpinner(modelHr);
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

        SpinnerModel modelKop = new SpinnerNumberModel(0, 0, 99, 1);
        spnKopeikas = new JSpinner( modelKop);
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
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,15,0,0);
        gridBagLayout.setConstraints( lbLabel1, gridBag );
        panel.add( lbLabel1 );

        String[] dishCategories = new String[client.getRestaurant().getAllDishCategories().size()];
        for (int i = 0; i < client.getRestaurant().getAllDishCategories().size(); i++) {
            dishCategories[i] = client.getRestaurant().getAllDishCategories().get(i).getName();
        }
        cmbDishCategory = new JComboBox( dishCategories );
        gridBag.gridx = 1;
        gridBag.gridy = 6;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.HORIZONTAL;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,15);
        gridBagLayout.setConstraints(cmbDishCategory, gridBag );
        panel.add(cmbDishCategory);
        add(panel);
    }
}
