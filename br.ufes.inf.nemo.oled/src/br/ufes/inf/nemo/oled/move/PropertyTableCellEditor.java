package br.ufes.inf.nemo.oled.move;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.UmlProject;

public class PropertyTableCellEditor extends AbstractCellEditor implements TableCellEditor
{
	private static final long serialVersionUID = 1L;
	
	private TableCellEditor editor;
	private UmlProject project;
	
	public PropertyTableCellEditor(UmlProject project)
	{
		super();
		this.project = project;
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
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) 
    {
    	if (value==null){
    		
    		editor = new DefaultCellEditor(new JTextField());
    		
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
	    	OntoUMLParser refparser = project.getParser();
	    	
	    	for(RefOntoUML.Type t: refparser.getAllInstances(RefOntoUML.Type.class))
	    	{
	    		if(t instanceof RefOntoUML.Class || t instanceof RefOntoUML.DataType){
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
	    }

        return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }   
}	