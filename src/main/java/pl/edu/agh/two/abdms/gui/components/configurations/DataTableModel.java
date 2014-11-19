package pl.edu.agh.two.abdms.gui.components.configurations;

import javax.swing.table.AbstractTableModel;

import pl.edu.agh.two.abdms.data.loader.DataModel;

public class DataTableModel extends AbstractTableModel {

    private final DataModel data;
    
    public DataTableModel(DataModel data) {
        this.data = data;
    }
    
    @Override
    public int getRowCount() {
        return data.getValues().size();
    }

    @Override
    public int getColumnCount() {
        return data.getColumnValues().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String header = data.getColumnValues()[columnIndex];
        Object value = data.getRow(rowIndex).get(header);
        return value == null ? "" : value;
    }

}
