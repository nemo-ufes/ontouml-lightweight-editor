package br.ufes.inf.nemo.move.ui;

import javax.swing.table.AbstractTableModel;

public class PropertyTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	
	public PropertyTableModel(String[] columnNames, Object[][] data)
	{
		this.columnNames=columnNames;
		this.data=data;
	}
	
	private String[] columnNames = {"Property","Value"};
    private Object[][] data = {};

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return data.length;
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int c) {
        if (getValueAt(0, c)!=null)
        	return getValueAt(0, c).getClass();
        else
        	return String.class;
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears on screen.
        if (col <= 0) {
            return false;
        } else {
            return true;
        }
    }

    /*
     * Don't need to implement this method unless your table's
     * data can change.
     */
    public void setValueAt(Object value, int row, int col) 
    { 
        data[row][col] = value;
        fireTableCellUpdated(row, col); 
    }

    @SuppressWarnings("unused")
	private void printDebugData() 
    {
        int numRows = getRowCount();
        int numCols = getColumnCount();

        for (int i=0; i < numRows; i++) {
            System.out.print("    row " + i + ":");
            for (int j=0; j < numCols; j++) {
                System.out.print("  " + data[i][j]);
            }
            System.out.println();
        }
        System.out.println("--------------------------");
    }
}
