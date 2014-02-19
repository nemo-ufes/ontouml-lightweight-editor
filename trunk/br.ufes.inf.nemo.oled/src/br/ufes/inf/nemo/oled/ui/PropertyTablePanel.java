package br.ufes.inf.nemo.oled.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.text.Normalizer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Element;
import RefOntoUML.Property;
import br.ufes.inf.nemo.oled.InfoManager;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.dialog.FeatureListDialog;
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
	private RefOntoUML.Element element;
	
	public PropertyTablePanel(UmlProject project)
	{
		this();
		this.project = project;	
	}
	
	public void reset()
	{
		Object[][] data = {};String[] columnNames = {};
		tablemodel = new PropertyTableModel(columnNames,data);
		table.setModel(tablemodel);	
		table.repaint();
		table.validate();		
	}
	
	private PropertyTableModel createCollectiveTableModel (RefOntoUML.Collective c)
	{
		this.element=c;
		Object[][] data = {
		{"    Name", c.getName(),""},			
		{"    Abstract", c.isIsAbstract(),""},
		{"    Extensional", c.isIsExtensional(),""},
		};
		String[] columnNames = {"Property","Value",""};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createClassAndDataTypeTableModel (RefOntoUML.Classifier c)
	{
		this.element=c;
		Object[][] data = {
		{"    Name", c.getName(),""},			
		{"    Abstract", c.isIsAbstract(),""},		
		};
		String[] columnNames = {"Property","Value",""};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}	
	
	private PropertyTableModel createAssociationTableModel (RefOntoUML.Association c)
	{
		this.element=c;
		Object[][] data = {
		{"    Name", c.getName(),""},				
		{"    Abstract", c.isIsAbstract(),""},	
		{"    Derived", c.isIsDerived(),""},	
		};		
		String[] columnNames = {"Property","Value",""};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createMeronymicTableModel (RefOntoUML.Meronymic m)
	{
		this.element=m;
		Object[][] data = {
		{"    Name", m.getName(),""},				
		{"    Abstract", m.isIsAbstract(),""},	
		{"    Derived", m.isIsDerived(),""},				
		{"    Essential", m.isIsEssential(),""},	
		{"    Inseparable", m.isIsInseparable(),""},	
		{"    Shareable", m.isIsShareable(),""},	
		{"    ImmutablePart", m.isIsImmutablePart(),""},	
		{"    ImmutableWhole", m.isIsImmutableWhole(),""},	
		};		
		String[] columnNames = {"Property","Value",""};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	 
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	 
	private PropertyTableModel createPropertyTableModel (RefOntoUML.Property p)
	{
		this.element=p;
		
		String subsetted = new String();
    	int i=0;
    	for(Property p2: p.getSubsettedProperty()){    		
			if (i==p.getSubsettedProperty().size()-1) subsetted += "<"+getStereotype(p2)+"> "+p2.getName()+": "+p2.getType().getName()+"";
			else subsetted += "<"+getStereotype(p2)+"> "+p2.getName()+": "+p2.getType().getName()+", ";
			i++;
		}		
    	
    	String redefined = new String();
    	i=0;
    	for(Property p2: p.getRedefinedProperty()){    		
			if (i==p.getRedefinedProperty().size()-1) subsetted += "<"+getStereotype(p2)+"> "+p2.getName()+": "+p2.getType().getName()+"";
			else subsetted += "<"+getStereotype(p2)+"> "+p2.getName()+": "+p2.getType().getName()+", ";
			i++;
		}
    	
		Object[][] data = {		
		{"    Name", p.getName(),""},				
		{"    Type", new OntoUMLElement(p.getType(),""),""},	
		{"    Upper", (new Integer(p.getUpper())),""},	
		{"    Lower", (new Integer(p.getLower())),""},	
		{"    Derived", p.isIsDerived(),""},	
		{"    Read Only", p.isIsReadOnly(),""},	
		{"    Aggregation Kind", p.getAggregation(),""},	
		{"    Redefined", redefined,""},
		{"    Subsetted", subsetted,""},	
		};		
		String[] columnNames = {"Property","Value",""};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createGeneralizationSetTableModel (RefOntoUML.GeneralizationSet p)
	{
		this.element=p;
		Object[][] data = {
		{"    Name", p.getName(),""},					
		{"    Covering", p.isIsCovering(),""},	
		{"    Disjoint", p.isIsDisjoint(),""},			
		};		
		String[] columnNames = {"Property","Value",""};
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
		this.element=p;
		Object[][] data = {
		{"    General", new OntoUMLElement(p.getGeneral(),""),""},
		//{"    Specific", new OntoUMLElem(p.getSpecific(),"")},				
		};		
		String[] columnNames = {"Property","Value",""};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createCommentTableModel(RefOntoUML.Comment c)
	{
		this.element=c;
		Object[][] data = {
		{"    Body", c.getBody(),""},
		};		
		String[] columnNames = {"Property","Value",""};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	
	private PropertyTableModel createPackageTableModel (RefOntoUML.Package p)
	{
		this.element=p;
		Object[][] data = {
		{"    Name", p.getName(),""},			
		};		
		String[] columnNames = {"Property","Value",""};
		tablemodel = new PropertyTableModel(columnNames,data);
		return tablemodel;
	}
	

	private void configureTable ()
	{
		table.getModel().addTableModelListener(this);
		
		// edit table on the single click
//	    DefaultCellEditor singleclick = new DefaultCellEditor(new JTextField());
//	    singleclick.setClickCountToStart(1);	
//	    table.setDefaultEditor(table.getColumnClass(1), singleclick);
        
	    table.getColumnModel().getColumn(1).setCellEditor(new PropertyTableCellEditor(project, table));
	    
	    Action action = new AbstractAction()
	    {
			private static final long serialVersionUID = 1L;
			public void actionPerformed(ActionEvent e)
	        {	        		            
	        	final int modelRow = Integer.valueOf( e.getActionCommand() );	        		
	        	final String value = (String) tablemodel.getValueAt(modelRow,0);
	        	
	        	if(value.trim().compareToIgnoreCase("redefined")==0 || (value.trim().compareToIgnoreCase("subsetted")==0))
	        	{
		        	SwingUtilities.invokeLater(new Runnable() {					
						@Override
						public void run() {
							FeatureListDialog.open(ProjectBrowser.frame,null,(String)value, InfoManager.getProperties().getElement(), ProjectBrowser.getParserFor(project));		
							tablemodel.setValueAt(FeatureListDialog.getResult(),modelRow,1);
						}
					});		
 				}
	        }
	    };
	    new ButtonColumn(table, action, 2);	    
	    for(int i=0;i<table.getRowCount();i++){
	    	table.setRowHeight(i, 18);	
	    }	    
	    
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

	public void setCommentData(OntoUMLElement elem)
	{
		RefOntoUML.Comment c = (RefOntoUML.Comment)elem.getElement();

		tablemodel = createCommentTableModel(c);
		
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
		if (elem.getElement() instanceof RefOntoUML.Comment)
		{
			setCommentData(elem);
		}
		if (elem.getElement() instanceof RefOntoUML.Package)
		{
			setPackageData(elem);
		}
		
		table.setRowSelectionInterval(0, 0);
	}	
		
	public void setSelected(int row1, int row2)
	{
		table.setRowSelectionInterval(row1, row2);
	}
	
	/**
	 * Constructor.
	 */
	public PropertyTablePanel() 
	{
		setBackground(Color.WHITE);
		setBorder(new EmptyBorder(0, 0, 0, 0));
				
		setLayout(new BorderLayout(0, 0));		

		String[] columnNames = {"Property","Value",""};
        Object[][] data = {};
        
        scrollpane = new JScrollPane();		
        scrollpane.setMinimumSize(new Dimension(0, 0));
	    setMinimumSize(new Dimension(0, 0));
		scrollpane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollpane.setBorder(new EmptyBorder(0,0,0,0));
		
		table = new JTable(data,columnNames);		
		scrollpane.setViewportView(table);

		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setFillsViewportHeight(true);
		table.setGridColor(Color.LIGHT_GRAY);		
	    table.setSelectionBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_MEDIUM));
	    table.setSelectionForeground(Color.BLACK);
	    table.setFocusable(false);	    
	    
		add(scrollpane,BorderLayout.CENTER);
	}		
	
	@Override
	public void tableChanged(TableModelEvent e) 
	{		
 		 int row = e.getFirstRow();
	     int column = e.getColumn();
	     if (column==2) return; // these changes are only made through the components at the column 1. See line 201 for changes through column 2.
	     
	     TableModel model = (TableModel)e.getSource();
	     String property = ((String)model.getValueAt(row, 0)).trim();
	     Object value = model.getValueAt(row, column);
	     EObject elem = ((OntoUMLElement)node.getUserObject()).getElement();	     
	     
	     this.element = (RefOntoUML.Element)elem;
	     boolean redesign = false;
	     
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
	    	 if(property.equals("Derived")) {
	    		 ((RefOntoUML.Association)elem).setIsDerived((Boolean)value);
	    		 if((Boolean)value==true){
	    			 ((RefOntoUML.Association)elem).setName("/"+((RefOntoUML.Association)elem).getName());
	    			 model.setValueAt(((RefOntoUML.Association)elem).getName(),0, 1);
	    		 }else{
	    			 ((RefOntoUML.Association)elem).setName(((RefOntoUML.Association)elem).getName().replace("/", ""));
	    			 model.setValueAt(((RefOntoUML.Association)elem).getName(),0, 1);
	    		 }
	    	 }
	    	 
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
	    	 if(property.equals("Type")) {
	    		 RefOntoUML.Type type = (RefOntoUML.Type)((OntoUMLElement)value).getElement();
	    		 ((RefOntoUML.Property)elem).setType(type);	  
	    		 redesign=true;
	    	 }
	    	 
	    	 RefOntoUML.RefOntoUMLFactory factory = RefOntoUML.RefOntoUMLFactory.eINSTANCE;         
	    	 if(property.equals("Upper")) {
		         RefOntoUML.LiteralUnlimitedNatural upperValue = factory.createLiteralUnlimitedNatural();		         
		         if(((String)value).isEmpty()) value = "1";
		         Integer IntValue = Integer.parseInt((String)value);
		         if (IntValue<-1) IntValue = -1;
		         upperValue.setValue(IntValue);
		         ((RefOntoUML.Property)elem).setUpperValue(upperValue);	    		 
	    	 }
	    	 if(property.equals("Lower")) {
		         RefOntoUML.LiteralInteger lowerValue = factory.createLiteralInteger();		         
		         if(((String)value).isEmpty()) value = "1";
		         Integer IntValue = Integer.parseInt((String)value);
		         if (IntValue<-1) IntValue = -1;
		         lowerValue.setValue(IntValue);
		         ((RefOntoUML.Property)elem).setLowerValue(lowerValue);    		 
	    	 }
	    	 
	    	 if(property.equals("Read Only")) ((RefOntoUML.Property)elem).setIsReadOnly((Boolean)value);
	    	 if(property.equals("Derived")) {
	    		 ((RefOntoUML.Property)elem).setIsDerived((Boolean)value);
	    		 if((Boolean)value==true){
	    			 ((RefOntoUML.Property)elem).setName("/"+((RefOntoUML.Property)elem).getName());
	    			 model.setValueAt(((RefOntoUML.Property)elem).getName(),0, 1);
	    		 }else{
	    			 ((RefOntoUML.Property)elem).setName(((RefOntoUML.Property)elem).getName().replace("/", ""));
	    			 model.setValueAt(((RefOntoUML.Property)elem).getName(),0, 1);
	    		 }
	    	 }
	    	 if(property.equals("Aggregation Kind")) {
	    		 ((RefOntoUML.Property)elem).setAggregation((RefOntoUML.AggregationKind)value);
	    	 }
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
	     }
	     
	     if(elem instanceof RefOntoUML.Package)
	     {
	    	 if(property.equals("Name")) ((RefOntoUML.Package)elem).setName((String)value);
	     }
	     
	     if(!redesign) ProjectBrowser.frame.getDiagramManager().updateOLEDFromModification((RefOntoUML.Element)elem,false);
	     else ProjectBrowser.frame.getDiagramManager().updateOLEDFromModification((RefOntoUML.Element)elem,true);
	}
	
	public void setProject(UmlProject project)
	{
		this.project = project;
	}

	public Element getElement() {		
		return element;
	}

	public JTable getTable() {
		return table;
	}
}
