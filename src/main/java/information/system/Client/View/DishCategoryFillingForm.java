package information.system.Client.View;
import information.system.Client.Controller.Client;
import information.system.Server.Model.DishСategory;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.text.AbstractDocument;

public class DishCategoryFillingForm extends InformSystemGUI {
    public DishCategoryFillingForm(Client client) {
        super();
        setClient(client);
        this.client = client;
        setBounds(dimension.width / 2 - 100, dimension.height / 2 - 100, 200, 200);
        basicInit();
        add(panel);
        setVisible(true);
    }

    @Override
    protected void basicInit() {
        JLabel lbName;
        JLabel lbHf;
        JCheckBox cbBox0;
        JTextField tfText0;
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

        cbBox0 = new JCheckBox( "Healthy food"  );
        gridBag.gridx = 1;
        gridBag.gridy = 2;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(10,15,0,15);
        gridBagLayout.setConstraints( cbBox0, gridBag );
        panel.add( cbBox0 );

        tfText0 = new JTextField( );
        gridBag.gridx = 0;
        gridBag.gridy = 1;
        gridBag.gridwidth = 2;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 0;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(0,15,0,15);
        gridBagLayout.setConstraints( tfText0, gridBag );
        ((AbstractDocument) tfText0.getDocument()).setDocumentFilter(new InformSystDocumentFilter());
        panel.add( tfText0 );

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
                if(tfText0.getText().length() < 3 ){
                    showMessage("Name is too short");
                } else {
                    DishСategory newDishCategory = new DishСategory();
                    newDishCategory.setName(tfText0.getText());
                    newDishCategory.setHealthyFood(cbBox0.isSelected());
                    client.add(newDishCategory);
                    dispose();
                }
            }
        });
        add(panel);
    }
}




