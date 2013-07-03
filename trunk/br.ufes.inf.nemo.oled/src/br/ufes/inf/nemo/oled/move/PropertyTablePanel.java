package br.ufes.inf.nemo.oled.move;

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

import org.eclipse.emf.ecore.EObject;

import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ModelTree;
import br.ufes.inf.nemo.oled.util.ColorPalette;
import br.ufes.inf.nemo.oled.util.ColorPalette.ThemeColor;

/**
 * @author John Guerson
 */

public class PropertyTablePanel extends JPanel implements TableModelListener {

	private static final long serialVersionUID = 1L;

	private JScrollPane scrollpane;
	
	private UmlProject project;
	private JTable table;	
	private PropertyTableModel tablemodel;
	private DefaultMutableTreeNode node;
	
	public PropertyTablePanel(UmlProject project)
	{
		this();
		this.project = project;		
	}
	
	private PropertyTableModel createCollectiveTableModel (RefOntoUML.Collective c)
	{
		Object[][] data = {
		{"    Name", c.getName()},			
		{"    Abstract", c.isIsAbstract()},
		{"    Extensional", c.isIsExtensional()},
		};
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createClassAndDataTypeTableModel (RefOntoUML.Classifier c)
	{
		Object[][] data = {
		{"    Name", c.getName()},			
		{"    Abstract", c.isIsAbstract()},		
		};
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}	
	
	private PropertyTableModel createAssociationTableModel (RefOntoUML.Association c)
	{
		Object[][] data = {
		{"    Name", c.getName()},			
		{"    Abstract", c.isIsAbstract()},
		{"    Derived", c.isIsDerived()},
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createMeronymicTableModel (RefOntoUML.Meronymic m)
	{
		Object[][] data = {
		{"    Name", m.getName()},			
		{"    Abstract", m.isIsAbstract()},
		{"    Derived", m.isIsDerived()},			
		{"    Essential", m.isIsEssential()},
		{"    Inseparable", m.isIsInseparable()},
		{"    Shareable", m.isIsShareable()},
		{"    ImmutablePart", m.isIsImmutablePart()},
		{"    ImmutableWhole", m.isIsImmutableWhole()},
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createPropertyTableModel (RefOntoUML.Property p)
	{
		Object[][] data = {
		{"    Name", p.getName()},			
		{"    Type", new OntoUMLElement(p.getType(),"")},
		{"    Upper", (new Integer(p.getUpper()))},
		{"    Lower", (new Integer(p.getLower()))},
		{"    Read Only", p.isIsReadOnly()},
		{"    Aggregation Kind", p.getAggregation()},		
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createGeneralizationSetTableModel (RefOntoUML.GeneralizationSet p)
	{
		Object[][] data = {
		{"    Name", p.getName()},				
		{"    Covering", p.isIsCovering()},
		{"    Disjoint", p.isIsDisjoint()},		
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);		
		/*ArrayList<String> generalizations = new ArrayList<String>(); 
		for(RefOntoUML.Generalization g: c.getGeneralization()){
			if (g.getGeneral()!=null && g.getSpecific()!=null)
			{
				generalizations.add(" "+g.getGeneral().getName()+" -> "+g.getSpecific().getName()+" ");
			}
		}
		genSetGeneralizationsCombo.setModel(new DefaultComboBoxModel(generalizations.toArray()));*/
		return tablemodel;
	}
	
	private PropertyTableModel createGeneralizationTableModel (RefOntoUML.Generalization p)
	{
		Object[][] data = {
		{"    General", new OntoUMLElement(p.getGeneral(),"")},
		//{"    Specific", new OntoUMLElem(p.getSpecific(),"")},				
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createPackageTableModel (RefOntoUML.Package p)
	{
		Object[][] data = {
		{"    Name", p.getName()},			
		};		
		String[] columnNames = {"Property","Value"};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private void configureTable ()
	{
		table.getModel().addTableModelListener(this);
		
		// edit table on the single click
	    DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
	    singleclick.setClickCountToStart(1);	
        table.setDefaultEditor(table.getColumnClass(1), singleclick);
        
        table.getColumnModel().getColumn(1).setCellEditor(new PropertyTableCellEditor(project));
        
		table.repaint();
		table.validate();	
	}
	
	public void setClassAndDataTypeData(OntoUMLElement elem)
	{
		RefOntoUML.Classifier c = (RefOntoUML.Classifier)elem.getElement();
		
		if (c instanceof RefOntoUML.Collective) tablemodel = createCollectiveTableModel((RefOntoUML.Collective)c);	
		else tablemodel = createClassAndDataTypeTableModel(c);				
		
		table.setModel(tablemodel);
		configureTable();			
	}
	
	public void setAssociationData(OntoUMLElement elem)
	{
		RefOntoUML.Association c = (RefOntoUML.Association)elem.getElement();
		
		if (c instanceof RefOntoUML.Meronymic) tablemodel = createMeronymicTableModel((RefOntoUML.Meronymic)c);
		else tablemodel = createAssociationTableModel(c);
			
		table.setModel(tablemodel);		
		configureTable();
	}
	
	public void setPropertyData(OntoUMLElement elem)
	{		
		RefOntoUML.Property c = (RefOntoUML.Property)elem.getElement();		
		
		tablemodel = createPropertyTableModel(c);
		
		table.setModel(tablemodel);		
		configureTable();
	}	
	
	public void setGeneralizationSetData(OntoUMLElement elem)
	{
		RefOntoUML.GeneralizationSet c = (RefOntoUML.GeneralizationSet)elem.getElement();

		tablemodel = createGeneralizationSetTableModel(c);
		
		table.setModel(tablemodel);		
		configureTable();
	}
	
	public void setGeneralizationData(OntoUMLElement elem)
	{
		RefOntoUML.Generalization c = (RefOntoUML.Generalization)elem.getElement();

		tablemodel = createGeneralizationTableModel(c);
		
		table.setModel(tablemodel);		
		configureTable();
	}
	
	public void setPackageData(OntoUMLElement elem)
	{
		RefOntoUML.Package p = (RefOntoUML.Package)elem.getElement();
		
		tablemodel = createPackageTableModel(p);
		
		table.setModel(tablemodel);		
		configureTable();
	}
	
	/**
	 * Set data
	 * 
	 * @param elem
	 */
	public void setData(DefaultMutableTreeNode node)
	{		
		this.node = node;
		
		OntoUMLElement elem = (OntoUMLElement) node.getUserObject();
		
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
	public PropertyTablePanel() 
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
	     String property = ((String)model.getValueAt(row, 0)).trim();
	     Object value = model.getValueAt(row, column);
	     EObject elem = ((OntoUMLElement)node.getUserObject()).getElement();	     
	     
	     // Do something with the value... modifying the correspondent OntoUML elem

	     if(elem instanceof RefOntoUML.Class || elem instanceof RefOntoUML.DataType)
	     {
	    	 if(property.equals("Name")) ((RefOntoUML.Classifier)elem).setName((String)value);
	    	 if(property.equals("Abstract")) ((RefOntoUML.Classifier)elem).setIsAbstract((Boolean)value);
	    	 
	    	 if(elem instanceof RefOntoUML.Collective){
	    		 if(property.equals("Extensional"))((RefOntoUML.Collective)elem).setIsExtensional((Boolean)value);
	    	 }
	     }
	     
	     if(elem instanceof RefOntoUML.Association)
	     {
	    	 if(property.equals("Name")) ((RefOntoUML.Association)elem).setName((String)value);
	    	 if(property.equals("Abstract")) ((RefOntoUML.Association)elem).setIsAbstract((Boolean)value);
	    	 if(property.equals("Derived")) ((RefOntoUML.Association)elem).setIsDerived((Boolean)value);
	    	 
	    	 if(elem instanceof RefOntoUML.Meronymic){
	    		 if(property.equals("Essential"))((RefOntoUML.Meronymic)elem).setIsEssential((Boolean)value);
	    		 if(property.equals("Inseparable"))((RefOntoUML.Meronymic)elem).setIsInseparable((Boolean)value);
	    		 if(property.equals("Shareable"))((RefOntoUML.Meronymic)elem).setIsShareable((Boolean)value);
	    		 if(property.equals("ImmutablePart"))((RefOntoUML.Meronymic)elem).setIsImmutablePart((Boolean)value);
	    		 if(property.equals("ImmutableWhole"))((RefOntoUML.Meronymic)elem).setIsImmutableWhole((Boolean)value);
	    	 }
	     }
	     
	     if(elem instanceof RefOntoUML.Property)
	     {
	    	 if(property.equals("Name")) ((RefOntoUML.Property)elem).setName((String)value);
	    	 if(property.equals("Type")) ((RefOntoUML.Property)elem).setType((RefOntoUML.Type)((OntoUMLElement)value).getElement());	    	 
	    	 
	    	 RefOntoUML.RefOntoUMLFactory factory = RefOntoUML.RefOntoUMLFactory.eINSTANCE;         
	    	 if(property.equals("Upper")) {
		         RefOntoUML.LiteralUnlimitedNatural upperValue = factory.createLiteralUnlimitedNatural();		         
		         Integer IntValue = Integer.parseInt((String)value);
		         if (IntValue<-1) IntValue = -1;
		         upperValue.setValue(IntValue);
		         ((RefOntoUML.Property)elem).setUpperValue(upperValue);	    		 
	    	 }
	    	 if(property.equals("Lower")) {
		         RefOntoUML.LiteralInteger lowerValue = factory.createLiteralInteger();		         
		         Integer IntValue = Integer.parseInt((String)value);
		         if (IntValue<-1) IntValue = -1;
		         lowerValue.setValue(IntValue);
		         ((RefOntoUML.Property)elem).setLowerValue(lowerValue);    		 
	    	 }
	    	 
	    	 if(property.equals("Read Only")) ((RefOntoUML.Property)elem).setIsReadOnly((Boolean)value);
	    	 if(property.equals("Aggregation Kind")) ((RefOntoUML.Property)elem).setAggregation((RefOntoUML.AggregationKind)value);			
	     }
	     
	     if(elem instanceof RefOntoUML.GeneralizationSet)
	     {
	    	 if(property.equals("Name")) ((RefOntoUML.GeneralizationSet)elem).setName((String)value);
	    	 if(property.equals("Covering")) ((RefOntoUML.GeneralizationSet)elem).setIsCovering((Boolean)value);
	    	 if(property.equals("Disjoint")) ((RefOntoUML.GeneralizationSet)elem).setIsDisjoint((Boolean)value);
	     }

	     if(elem instanceof RefOntoUML.Generalization)
	     {
	    	 if(property.equals("General")) ((RefOntoUML.Generalization)elem).setGeneral((RefOntoUML.Classifier)((OntoUMLElement)value).getElement());
	    	 //if(property.equals("Specific")) ((RefOntoUML.Generalization)elem).setSpecific((RefOntoUML.Classifier)((OntoUMLElem)value).getElement());
	     }
	     
	     if(elem instanceof RefOntoUML.Package)
	     {
	    	 if(property.equals("Name")) ((RefOntoUML.Package)elem).setName((String)value);
	     }
	     
	     //update the Tree 
	     ModelTree.refreshModelTree(project);
	}
	
}
