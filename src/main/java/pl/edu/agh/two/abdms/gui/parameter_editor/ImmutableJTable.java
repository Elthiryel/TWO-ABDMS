package pl.edu.agh.two.abdms.gui.parameter_editor;

import javax.swing.JTable;

class ImmutableJTable extends JTable{

	private static final long serialVersionUID = -3057693475076845383L;

	public ImmutableJTable(Object[][] rowData, Object[] columnNames) {
		super(rowData, columnNames);
	}
	
	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}
	
}
