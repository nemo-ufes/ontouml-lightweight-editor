package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import RefOntoUML.AggregationKind;
import br.ufes.inf.nemo.move.ui.util.ColorPalette;
import br.ufes.inf.nemo.move.ui.util.ColorPalette.ThemeColor;
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
	
	public void setClassAndDataTypeData(OntoUMLTreeNodeElem elem)
	{
		RefOntoUML.Classifier c = (RefOntoUML.Classifier)elem.getElement();
		String isExtensional = "";
		if (c instanceof RefOntoUML.Collective)
		{
			RefOntoUML.Collective col = (RefOntoUML.Collective)c;
			if(col.isIsExtensional()) isExtensional="true"; else isExtensional="false";
			Object[][] data = {
			{"    Name", " "+c.getName()},			
			{"    Type", " "+elem.getTypeName()},
			{"    Alias", " "+elem.getUniqueName()},
			{"    Abstract", " "+c.isIsAbstract()},
			{"    Extensional", " "+isExtensional},
			};
			String[] columnNames = {"Property","Value"};
			tablemodel = new PropertyTableModel(columnNames,data);
		}else{
			Object[][] data = {
			{"    Name", " "+c.getName()},			
			{"    Type", " "+elem.getTypeName()},
			{"    Alias", " "+elem.getUniqueName()},
			{"    Abstract", " "+c.isIsAbstract()},
			};
			String[] columnNames = {"Property","Value"};
			tablemodel = new PropertyTableModel(columnNames,data);
		}			
		table.setModel(tablemodel);
		table.repaint();
		table.validate();		
	}
	
	public void setAssociationData(OntoUMLTreeNodeElem elem)
	{
		RefOntoUML.Association c = (RefOntoUML.Association)elem.getElement();
		String essential = new String();
		String inseparable = new String();
		String shareable = new String();
		String immutablePart = new String();
		String immutableWhole = new String();
		
		if (c instanceof RefOntoUML.Meronymic)
		{
			RefOntoUML.Meronymic m = (RefOntoUML.Meronymic)c;
			if(m.isIsEssential()) essential="true"; else essential="false";
			if(m.isIsInseparable()) inseparable="true"; else inseparable="false";
			if(m.isIsShareable()) shareable="true"; else shareable="false";
			if(m.isIsImmutablePart()) immutablePart="true"; else immutablePart="false";
			if(m.isIsImmutableWhole()) immutableWhole="true"; else immutableWhole="false";

			Object[][] data = {
			{"    Name", " "+c.getName()},			
			{"    Type", " "+elem.getTypeName()},
			{"    Alias", " "+elem.getUniqueName()},
			{"    Abstract", " "+c.isIsAbstract()},
			{"    Derived", " "+c.isIsDerived()},			
			{"    Essential", " "+essential},
			{"    Inseparable", " "+inseparable},
			{"    Shareable", " "+shareable},
			{"    ImmutablePart", " "+immutablePart},
			{"    ImmutableWhole", " "+immutableWhole},
			};		
			String[] columnNames = {"Property","Value"};
			tablemodel = new PropertyTableModel(columnNames,data);
		}else{
			Object[][] data = {
			{"    Name", " "+c.getName()},			
			{"    Type", " "+elem.getTypeName()},
			{"    Alias", " "+elem.getUniqueName()},
			{"    Abstract", " "+c.isIsAbstract()},
			{"    Derived", " "+c.isIsDerived()},
			};		
			String[] columnNames = {"Property","Value"};
			tablemodel = new PropertyTableModel(columnNames,data);
			
		}
		table.setModel(tablemodel);
		table.repaint();
		table.validate();
	}
	
	public void setPropertyData(OntoUMLTreeNodeElem elem)
	{
		String readOnly = new String();
		String aggregationKind = new String();
		
		RefOntoUML.Property c = (RefOntoUML.Property)elem.getElement();

		if(c.isIsReadOnly()) readOnly = "true"; else readOnly = "false";
		
		if(c.getAggregation().equals(AggregationKind.COMPOSITE)) aggregationKind="composite";
		else if(c.getAggregation().equals(AggregationKind.SHARED)) aggregationKind="shared";
		else if(c.getAggregation().equals(AggregationKind.NONE)) aggregationKind="none";
		
		Object[][] data = {
		{"    Name", " "+c.getName()},			
		{"    Type", " "+((RefOntoUML.Property)elem.getElement()).getType().getName()},
		{"    Alias", " "+elem.getUniqueName()},
		{"    Upper", " "+(new Integer(c.getUpper())).toString()},
		{"    Lower", " "+(new Integer(c.getLower())).toString()},
		{"    Read Only", " "+readOnly},
		{"    Aggregation Kind", " "+aggregationKind},		
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		table.repaint();
		table.validate();
	}	
	
	public void setGeneralizationSetData(OntoUMLTreeNodeElem elem)
	{
		RefOntoUML.GeneralizationSet c = (RefOntoUML.GeneralizationSet)elem.getElement();
		String covering = new String();
		String disjoint = new String();
				
		if(c.isIsCovering()) covering="true"; else covering="false";
		if(c.isIsDisjoint()) disjoint="true"; else disjoint="false";
		
		/*ArrayList<String> generalizations = new ArrayList<String>(); 
		for(RefOntoUML.Generalization g: c.getGeneralization()){
			if (g.getGeneral()!=null && g.getSpecific()!=null)
			{
				generalizations.add(" "+g.getGeneral().getName()+" -> "+g.getSpecific().getName()+" ");
			}
		}
		genSetGeneralizationsCombo.setModel(new DefaultComboBoxModel(generalizations.toArray()));*/
		
		Object[][] data = {
		{"    Name", " "+elem.getName()},			
		{"    Type", " "+elem.getTypeName()},		
		{"    Covering", " "+covering},
		{"    Disjoint", " "+disjoint},				
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		table.repaint();
		table.validate();
	}
	
	public void setGeneralizationData(OntoUMLTreeNodeElem elem)
	{
		RefOntoUML.Generalization c = (RefOntoUML.Generalization)elem.getElement();
		String general = new String();
		String specific = new String();
		
		if (c.getGeneral()!=null) general = c.getGeneral().getName();
		if (c.getSpecific()!=null) specific = c.getSpecific().getName();
		
		Object[][] data = {
		{"    Name", " "+elem.getName()},			
		{"    Type", " "+elem.getTypeName()},		
		{"    General", " "+general},
		{"    Specific", " "+specific},				
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		table.repaint();
		table.validate();
	}
	
	public void setPackageData(OntoUMLTreeNodeElem elem)
	{
		Object[][] data = {
		{"    Name", " "+elem.getName()},			
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		table.repaint();
		table.validate();
	}
	
	/**
	 * Set data
	 * 
	 * @param elem
	 */
	public void setData(OntoUMLTreeNodeElem elem)
	{		
		if (elem.getElement() instanceof RefOntoUML.Class || elem.getElement() instanceof RefOntoUML.DataType)
		{
			setClassAndDataTypeData(elem);
		}		
		if (elem.getElement() instanceof RefOntoUML.Association)
		{
			setAssociationData(elem);
		}
		if (elem.getElement() instanceof RefOntoUML.Property)
		{
			setPropertyData(elem);
		}
		if (elem.getElement() instanceof RefOntoUML.GeneralizationSet)
		{
			setGeneralizationSetData(elem);
		}
		if (elem.getElement() instanceof RefOntoUML.Generalization)
		{
			setGeneralizationData(elem);
		}	
		if (elem.getElement() instanceof RefOntoUML.Package)
		{
			setPackageData(elem);
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
        
        scrollpane = new JScrollPane();		
	    scrollpane.setPreferredSize(new Dimension(100,70));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		table = new JTable(data,columnNames);		
		scrollpane.setViewportView(table);
		
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setPreferredScrollableViewportSize(new Dimension(500, 150));		
		table.setFillsViewportHeight(true);
		table.setGridColor(Color.LIGHT_GRAY);		
	    table.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHT));
	    table.setSelectionForeground(Color.BLACK);
	    table.setFocusable(false);
	    					
		add(scrollpane,BorderLayout.CENTER);				
	}
		
	/**
	 * 
	 * My own TableModel...
	 * 
	 * @author John
	 *
	 */
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
