package information.system.Client.View;

import information.system.Client.Controller.Client;
import information.system.Server.Model.DishCategory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class DishCategoryInfo extends InformSystemGUI{
    private DefaultTableModel model = new DefaultTableModel();
    private JTable dishesTable = new JTable();

    public DishCategoryInfo(Client client) {
        super();
        setBounds(dimension.width / 2 - 125, dimension.height / 2 - 125, 250, 250);
        setClient(client);
        init();
        setValuesAtTable();
    }

    @Override
    protected void init() {
        Object[] headers = {"Name", "Healthy food"};
        model = new DefaultTableModel(null, headers);
        dishesTable = new JTable(model){
            @Override
            public boolean isCellEditable(int arg0, int arg1) {
                return false;
            }
        };
        JScrollPane jscrlp = new JScrollPane(dishesTable);
        gridBag.gridx = 0;
        gridBag.gridy = 0;
        gridBag.gridwidth = 1;
        gridBag.gridheight = 1;
        gridBag.fill = GridBagConstraints.BOTH;
        gridBag.weightx = 1;
        gridBag.weighty = 1;
        gridBag.anchor = GridBagConstraints.NORTH;
        gridBag.insets = new Insets(5,10,10,10);
        gridBagLayout.setConstraints( jscrlp, gridBag );
        panel.add( jscrlp );
    }

    /**
     *  Gets
     */
    private void setValuesAtTable(){
        ArrayList<DishCategory> dishCategories = client.getDishCategories();
        final int tableRowSize = dishCategories.size();
        model.setRowCount(tableRowSize);
        Iterator<DishCategory> dishCategoryIterator = dishCategories.iterator();
        DishCategory dishCategory;
        int i = 0;
        while (dishCategoryIterator.hasNext()){
            dishCategory = dishCategoryIterator.next();
            model.setValueAt(dishCategory.getName(), i , 0);
            model.setValueAt(dishCategory.isHealthyFood(), i , 1);
            i++;
        }
        while(i<tableRowSize){
            model.setValueAt(" ", i , 0);
            model.setValueAt(" ", i , 1);
            i++;
        }
        dishesTable.repaint();
    }

}