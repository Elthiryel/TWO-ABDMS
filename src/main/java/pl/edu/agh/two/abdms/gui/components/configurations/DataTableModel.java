package pl.edu.agh.two.abdms.gui.components.configurations;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

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
    
    public boolean isCellEditable(int row, int col) { 
        return true;
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
    
    @Override
    public String getColumnName(int column) {
        return data.getColumnValues()[column];
    }
    
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex){
    	String header = data.getColumnValues()[columnIndex];
    	data.getRow(rowIndex).put(header, (String)value);
    }

}
