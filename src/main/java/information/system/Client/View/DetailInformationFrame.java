package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.Dish;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JButton;
public class DetailInformationFrame extends InformSystemGUI{
    private Dish dish;
    public DetailInformationFrame(Client client, Dish dish) {
        super();
        this.dish = dish;
        setBounds(dimension.width / 2 - 200, dimension.height / 2 - 150, 400, 300);
 //       setClient(client);
        basicInit();
        add(panel);
        setVisible(true);
    }

    @Override
    protected void basicInit() {
        JLabel lbName;
        JLabel lbDishName;
        JLabel lbPrice;
        JLabel lbDescription;
        JLabel lbLabel6;
        JLabel lbLabel9;
        JLabel lbDishcategoryName;
        JButton btBut0;
        JButton btBut1;
        panel = new JPanel();

        panel.setLayout( gridBagLayout );

        lbName = new JLabel( "Name"  );
        gridBag.gridx = 2;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,15,0,0);
        gridBagLayout.setConstraints( lbName, gridBag );
        panel.add( lbName );

        lbDishName = new JLabel( dish.getName()  );
        gridBag.gridx = 3;
        gridBag.gridy = 1;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(15,0,0,15);
        gridBagLayout.setConstraints( lbDishName, gridBag );
        panel.add( lbDishName );

        lbPrice = new JLabel( "Price"  );
        gridBag.gridx = 2;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,0);
        gridBagLayout.setConstraints( lbPrice, gridBag );
        panel.add( lbPrice );

        lbDishName = new JLabel(String.valueOf(dish.getPrice()));
        gridBag.gridx = 3;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,0,0,15);
        gridBagLayout.setConstraints( lbDishName, gridBag );
        panel.add( lbDishName );

        lbDescription = new JLabel( "Description"  );
        gridBag.gridx = 2;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,0);
        gridBagLayout.setConstraints( lbDescription, gridBag );
        panel.add( lbDescription );

        lbLabel6 = new JLabel( dish.getDescription()  );
        gridBag.gridx = 3;
        gridBag.gridy = 3;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,0,0,15);
        gridBagLayout.setConstraints( lbLabel6, gridBag );
        panel.add( lbLabel6 );

        lbLabel9 = new JLabel( "Dish category:"  );
        gridBag.gridx = 2;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,0);
        gridBagLayout.setConstraints( lbLabel9, gridBag );
        panel.add( lbLabel9 );

        lbDishcategoryName = new JLabel( ""  );
        gridBag.gridx = 3;
        gridBag.gridy = 4;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,0,0,15);
        gridBagLayout.setConstraints( lbDishcategoryName, gridBag );
        panel.add( lbDishcategoryName );

        btBut0 = new JButton( "Edit"  );
        gridBag.gridx = 2;
        gridBag.gridy = 5;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.insets = new Insets(15,15,15,0);
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBagLayout.setConstraints( btBut0, gridBag );
        panel.add( btBut0 );

        btBut1 = new JButton( "Remove"  );
        gridBag.gridx = 3;
        gridBag.gridy = 5;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.insets = new Insets(15,0,15,15);
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBagLayout.setConstraints( btBut1, gridBag );
        panel.add( btBut1 );
        add(panel);
    }

}




