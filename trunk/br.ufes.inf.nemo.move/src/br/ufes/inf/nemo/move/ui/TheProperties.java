package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import br.ufes.inf.nemo.move.util.ontoumlview.OntoUMLTreeNodeElem;

/**
 * @author John Guerson
 */

public class TheProperties extends JPanel {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollpane;
	@SuppressWarnings("unused")
	private TheFrame frame;
	private JTable table;
	private PropertyTableModel tablemodel;
	
	public TheProperties(TheFrame frame)
	{
		this();
		this.frame = frame;
	}
	
	/**
	 * Set data
	 * 
	 * @param elem
	 */
	public void setData(OntoUMLTreeNodeElem elem)
	{		
		// set Class data
		if (elem.getElement() instanceof RefOntoUML.Class || elem.getElement() instanceof RefOntoUML.DataType)
		{
			RefOntoUML.Classifier c = (RefOntoUML.Classifier)elem.getElement();
			String isExtensional = "";
			if (c instanceof RefOntoUML.Collective)
			{
				RefOntoUML.Collective col = (RefOntoUML.Collective)c;
				if(col.isIsExtensional()) isExtensional="true"; else isExtensional="false";
			}
			Object[][] data = {
			{"    Name", " "+c.getName()},			
			{"    Type", " "+elem.getTypeName()},
			{"    Alias", " "+elem.getUniqueName()},
			{"    Abstract", " "+c.isIsAbstract()},
			{"    Extensional", " "+isExtensional},
			};
	
			String[] columnNames = {"Property","Value"};
			tablemodel = new PropertyTableModel(columnNames,data);
			table.setModel(tablemodel);
			table.repaint();
			table.validate();
		}				 
	}
	
		
	/**
	 * Constructor.
	 */
	public TheProperties() 
	{
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(0, 0, 0, 0));
				
		setLayout(new BorderLayout(0, 0));		

		String[] columnNames = {"Property","Value"};
        Object[][] data = {};
        
		table = new JTable(data,columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));		
		table.setFillsViewportHeight(true);
		        
		scrollpane = new JScrollPane(table);
		add(scrollpane);
	}
		
	class PropertyTableModel extends AbstractTableModel 
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
}
