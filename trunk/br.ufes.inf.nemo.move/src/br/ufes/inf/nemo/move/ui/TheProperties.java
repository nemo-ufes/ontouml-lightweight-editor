package br.ufes.inf.nemo.move.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.DefaultCellEditor;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLCheckBoxTree;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLElem;
import br.ufes.inf.nemo.move.ui.util.ColorPalette;
import br.ufes.inf.nemo.move.ui.util.ColorPalette.ThemeColor;

/**
 * @author John Guerson
 */

public class TheProperties extends JPanel implements TableModelListener {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollpane;
	
	private TheFrame frame;
	private OntoUMLParser refparser;
	private OntoUMLCheckBoxTree modeltree;	
	private JTable table;	
	private PropertyTableModel tablemodel;
	private DefaultMutableTreeNode node;
	private OntoUMLElem element;
	
	public TheProperties(TheFrame frame)
	{
		this();
		this.frame = frame;		
	}
	
	public void setClassAndDataTypeData(OntoUMLElem elem)
	{
		element = elem;
		RefOntoUML.Classifier c = (RefOntoUML.Classifier)elem.getElement();
		if (c instanceof RefOntoUML.Collective)
		{
			RefOntoUML.Collective col = (RefOntoUML.Collective)c;
			
			Object[][] data = {
			{"    Name", c.getName()},			
			{"    Abstract", c.isIsAbstract()},
			{"    Extensional", col.isIsExtensional()},
			};
			String[] columnNames = {"Property","Value"};
			tablemodel = new PropertyTableModel(columnNames,data);
		}else{
			Object[][] data = {
			{"    Name", c.getName()},			
			{"    Abstract", c.isIsAbstract()},
			};
			String[] columnNames = {"Property","Value"};
			tablemodel = new PropertyTableModel(columnNames,data);
		}			
		table.setModel(tablemodel);
		
		table.getModel().addTableModelListener(this);
		
		// edit table on the single click
	    DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
	    singleclick.setClickCountToStart(1);	
        table.setDefaultEditor(table.getColumnClass(1), singleclick);
        
        table.getColumnModel().getColumn(1).setCellEditor(new PropertyTableCellEditor(frame));
        
		table.repaint();
		table.validate();		
	}
	
	public void setAssociationData(OntoUMLElem elem)
	{
		element = elem;
		RefOntoUML.Association c = (RefOntoUML.Association)elem.getElement();
		
		if (c instanceof RefOntoUML.Meronymic)
		{
			RefOntoUML.Meronymic m = (RefOntoUML.Meronymic)c;			

			Object[][] data = {
			{"    Name", c.getName()},			
			{"    Abstract", c.isIsAbstract()},
			{"    Derived", c.isIsDerived()},			
			{"    Essential", m.isIsEssential()},
			{"    Inseparable", m.isIsInseparable()},
			{"    Shareable", m.isIsShareable()},
			{"    ImmutablePart", m.isIsImmutablePart()},
			{"    ImmutableWhole", m.isIsImmutableWhole()},
			};		
			String[] columnNames = {"Property","Value"};
			tablemodel = new PropertyTableModel(columnNames,data);
		}else{
			Object[][] data = {
			{"    Name", c.getName()},			
			{"    Abstract", c.isIsAbstract()},
			{"    Derived", c.isIsDerived()},
			};		
			String[] columnNames = {"Property","Value"};
			tablemodel = new PropertyTableModel(columnNames,data);
			
		}
		table.setModel(tablemodel);
		
		table.getModel().addTableModelListener(this);
		
		// edit table on the single click
	    DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
	    singleclick.setClickCountToStart(1);	
        table.setDefaultEditor(table.getColumnClass(1), singleclick);
        
        table.getColumnModel().getColumn(1).setCellEditor(new PropertyTableCellEditor(frame));
        
		table.repaint();
		table.validate();
	}
	
	public void setPropertyData(OntoUMLElem elem)
	{		
		element = elem;
		RefOntoUML.Property c = (RefOntoUML.Property)elem.getElement();
		
		Object[][] data = {
		{"    Name", c.getName()},			
		{"    Type", new OntoUMLElem(((RefOntoUML.Property)elem.getElement()).getType(),"")},
		{"    Upper", (new Integer(c.getUpper()))},
		{"    Lower", (new Integer(c.getLower()))},
		{"    Read Only", c.isIsReadOnly()},
		{"    Aggregation Kind", c.getAggregation()},		
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		
		table.getModel().addTableModelListener(this);
		
		// edit table on the single click
	    DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
	    singleclick.setClickCountToStart(1);
        table.setDefaultEditor(table.getColumnClass(1), singleclick);
        
        table.getColumnModel().getColumn(1).setCellEditor(new PropertyTableCellEditor(frame));
        
		table.repaint();
		table.validate();
	}	
	
	public void setGeneralizationSetData(OntoUMLElem elem)
	{
		element = elem;
		RefOntoUML.GeneralizationSet c = (RefOntoUML.GeneralizationSet)elem.getElement();
		
		/*ArrayList<String> generalizations = new ArrayList<String>(); 
		for(RefOntoUML.Generalization g: c.getGeneralization()){
			if (g.getGeneral()!=null && g.getSpecific()!=null)
			{
				generalizations.add(" "+g.getGeneral().getName()+" -> "+g.getSpecific().getName()+" ");
			}
		}
		genSetGeneralizationsCombo.setModel(new DefaultComboBoxModel(generalizations.toArray()));*/
		
		Object[][] data = {
		{"    Name", elem.getName()},				
		{"    Covering", c.isIsCovering()},
		{"    Disjoint", c.isIsDisjoint()},				
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		
		table.getModel().addTableModelListener(this);
		
		// edit table on the single click
	    DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
	    singleclick.setClickCountToStart(1);	
        table.setDefaultEditor(table.getColumnClass(1), singleclick);
        
        table.getColumnModel().getColumn(1).setCellEditor(new PropertyTableCellEditor(frame));
        
		table.repaint();
		table.validate();
	}
	
	public void setGeneralizationData(OntoUMLElem elem)
	{
		element = elem;
		RefOntoUML.Generalization c = (RefOntoUML.Generalization)elem.getElement();

		Object[][] data = {
		{"    Name", elem.getName()},			
		{"    General", new OntoUMLElem(c.getGeneral(),"")},
		{"    Specific", new OntoUMLElem(c.getSpecific(),"")},				
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		
		table.getModel().addTableModelListener(this);
		
		// edit table on the single click
	    DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
	    singleclick.setClickCountToStart(1);	
        table.setDefaultEditor(table.getColumnClass(1), singleclick);
        
        table.getColumnModel().getColumn(1).setCellEditor(new PropertyTableCellEditor(frame));
        
		table.repaint();
		table.validate();
	}
	
	public void setPackageData(OntoUMLElem elem)
	{
		element = elem;
		Object[][] data = {
		{"    Name", elem.getName()},			
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		
		table.setModel(tablemodel);
		
		table.getModel().addTableModelListener(this);
		
		// edit table on the single click
	    DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
	    singleclick.setClickCountToStart(1);	
        table.setDefaultEditor(table.getColumnClass(1), singleclick);
        
        table.getColumnModel().getColumn(1).setCellEditor(new PropertyTableCellEditor(frame));
        
		table.repaint();
		table.validate();
	}
	
	/**
	 * Set data
	 * 
	 * @param elem
	 */
	public void setData(DefaultMutableTreeNode node)
	{		
		this.node = node;
		this.refparser = frame.getManager().getOntoUMLModel().getOntoUMLParser();
		this.modeltree = frame.getManager().getOntoUMLView().getModelTree();
		
		OntoUMLElem elem = (OntoUMLElem) node.getUserObject();
		
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
	
	@Override
	public void tableChanged(TableModelEvent e) 
	{
 		 int row = e.getFirstRow();
	     int column = e.getColumn();
	     TableModel model = (TableModel)e.getSource();
	     //String columnName = model.getColumnName(column);
	     Object data = model.getValueAt(row, column);
	     // Do something with the data...
	     
	     if(element.getElement() instanceof RefOntoUML.Class)
	     {	    	 
	    	 if( ((String)model.getValueAt(row, 0)).trim().equals("Name") ) 
	    	 {	    		 
	    		 ((RefOntoUML.Class) element.getElement()).setName((String)data);	    		 
	    		 modeltree.updateUI();
	    	 }	    	 
	     }
	     
	     //update the Tree from the OntoUMLParser... or update only the node...
	}
	
}
