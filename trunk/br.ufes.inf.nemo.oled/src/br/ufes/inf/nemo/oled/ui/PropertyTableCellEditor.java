package br.ufes.inf.nemo.oled.ui;

import java.awt.Color;
import java.awt.Component;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.UmlProject;

public class PropertyTableCellEditor extends AbstractCellEditor implements TableCellEditor
{
	private static final long serialVersionUID = 1L;
	
	private TableCellEditor editor;
	@SuppressWarnings("unused")
	private JTable table;
	private UmlProject project;
	
	public PropertyTableCellEditor(UmlProject project, JTable table)
	{
		super();
		this.project = project;
		this.table=table;
	}
	
    @Override
    public Object getCellEditorValue() 
    {
        if (editor != null) {
            return editor.getCellEditorValue();
        }
        return null;
    }

    public class CustomComparator implements Comparator<OntoUMLElement> 
    {
        @Override
        public int compare(OntoUMLElement o1, OntoUMLElement o2) {
            return o1.toString().compareToIgnoreCase(o2.toString());
        }
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Component getTableCellEditorComponent(final JTable table, Object value, boolean isSelected, int row, int column) 
    {
    	if (value==null){
    		
    		JTextField textField = new JTextField();
    		textField.setEditable(false);
	    	textField.setEnabled(false);
    		editor = new DefaultCellEditor(textField);
    		
    	} else if (value instanceof String || value instanceof Integer) {
        	
            editor = new DefaultCellEditor(new JTextField());
            
        } else if (value instanceof Boolean) {
        	
        	JComboBox comboBox = new JComboBox();
	    	comboBox.setFocusable(false);
	    	comboBox.setBackground(Color.WHITE);
        	comboBox.setModel(new DefaultComboBoxModel(new Boolean[] {Boolean.FALSE, Boolean.TRUE}));
            editor = new DefaultCellEditor(comboBox);            
        
	    } else if (value instanceof OntoUMLElement) {
	    	
	    	JComboBox comboBox = new JComboBox();
	    	comboBox.setFocusable(false);
	    	comboBox.setBackground(Color.WHITE);
	    	ArrayList<OntoUMLElement> list = new ArrayList<OntoUMLElement>();
	    	OntoUMLParser refparser = ProjectBrowser.getParserFor(project);	    	
	    	for(RefOntoUML.Type t: refparser.getAllInstances(RefOntoUML.Type.class))
	    	{
	    		if(t instanceof RefOntoUML.Class || t instanceof RefOntoUML.DataType || t instanceof RefOntoUML.Association){
	    			if (t.equals(((OntoUMLElement) value).getElement())) list.add((OntoUMLElement)value);
	    			else list.add(new OntoUMLElement(t,""));	    			
	    		}	    			
	    	}
	    	Collections.sort(list,new CustomComparator());	    	
	    	comboBox.setModel(new DefaultComboBoxModel(list.toArray()));	    	
	        editor = new DefaultCellEditor(comboBox);
	    	
	    }else if (value instanceof RefOntoUML.AggregationKind) {
	    	
	    	JComboBox comboBox = new JComboBox();
	    	comboBox.setFocusable(false);
	    	comboBox.setBackground(Color.WHITE);		    			    	
	    	comboBox.setModel(new DefaultComboBoxModel(new RefOntoUML.AggregationKind[] {
	    		RefOntoUML.AggregationKind.NONE, 
	    		RefOntoUML.AggregationKind.SHARED, 
	    		RefOntoUML.AggregationKind.COMPOSITE})
	    	);
	        editor = new DefaultCellEditor(comboBox);	    

	    }else if (value instanceof List) {
	    	List<Object> valueList = (List<Object>)value;
	    	
	    	String str = new String();
	    	int i=0;
	    	for(Object p: valueList){
	    		if(p instanceof Property){
	    			Property p2 = (Property)p;
	    			if (i==valueList.size()-1) str += "<"+getStereotype(p2)+"> "+p2.getName()+": "+p2.getType().getName()+"";
	    			else str += "<"+getStereotype(p2)+"> "+p2.getName()+": "+p2.getType().getName()+", ";
	    		}
	    		i++;
	    	}		
	    	
	    	JTextField textField = new JTextField(str);
	    	textField.setEditable(false);
	    	textField.setEnabled(false);
	    	editor = new DefaultCellEditor(textField);
	    }
	    
        return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
    
    public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
}	