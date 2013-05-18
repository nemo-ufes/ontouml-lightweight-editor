package br.ufes.inf.nemo.move.ui;

import javax.swing.table.AbstractTableModel;

public class WarningTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	
	public WarningTableModel(String[] columnNames, Object[][] data)
	{
		this.columnNames=columnNames;
		this.data=data;
	}
	
	private String[] columnNames = {"Description","Element","Path"};
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
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        if (col < 2) {
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

	public String getData() 
    {
        int numRows = getRowCount();
        int numCols = getColumnCount();
        String result = new String();            
        
        for (int i=0; i < numRows; i++) 
        {            	
            for (int j=0; j < numCols; j++) {                	
            	if(j>0){                		
            		String elem = (String)data[i][j];
            		result += "  " + elem.trim()+"\n";                		
            	}else if (j==0){
            		String title = (String)data[i][j];                		
            		if(!title.trim().isEmpty()){
            			result += "  "+"\n"+title+"\n\n";
            		}else{
            			result += "\n";
            		}
            	}
            }                
        }
        return result;
    }
}
