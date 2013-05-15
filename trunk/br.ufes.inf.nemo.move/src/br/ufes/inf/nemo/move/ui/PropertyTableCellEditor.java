package br.ufes.inf.nemo.move.ui;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.move.ui.ontouml.OntoUMLElem;

public class PropertyTableCellEditor extends AbstractCellEditor implements TableCellEditor 
{
	private static final long serialVersionUID = 1L;
	
	private TableCellEditor editor;
	private TheFrame frame;
	
	public PropertyTableCellEditor(TheFrame frame)
	{
		super();
		this.frame = frame;
	}
	
    @Override
    public Object getCellEditorValue() 
    {
        if (editor != null) {
            return editor.getCellEditorValue();
        }
        return null;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) 
    {
        if (value instanceof String || value instanceof Integer) {
            editor = new DefaultCellEditor(new JTextField());
            
        } else if (value instanceof Boolean || value == null) {
        	
        	JComboBox comboBox = new JComboBox();
	    	comboBox.setFocusable(false);
	    	comboBox.setBackground(Color.WHITE);
        	comboBox.setModel(new DefaultComboBoxModel(new Boolean[] {null, Boolean.FALSE, Boolean.TRUE}));
            editor = new DefaultCellEditor(comboBox);            
        
	    } else if (value instanceof OntoUMLElem) {
	    	
	    	JComboBox comboBox = new JComboBox();
	    	comboBox.setFocusable(false);
	    	comboBox.setBackground(Color.WHITE);
	    	ArrayList<OntoUMLElem> list = new ArrayList<OntoUMLElem>();
	    	OntoUMLParser refparser = frame.getManager().getOntoUMLModel().getOntoUMLParser();
	    	
	    	for(RefOntoUML.Type t: refparser.getAllInstances(RefOntoUML.Type.class))
	    	{
	    		if(t instanceof RefOntoUML.Class || t instanceof RefOntoUML.DataType)
	    			list.add(new OntoUMLElem(t,""));
	    	}
	    	comboBox.setModel(new DefaultComboBoxModel(list.toArray()));	    	
	        editor = new DefaultCellEditor(comboBox);
	    	
	    }else if (value instanceof RefOntoUML.AggregationKind) {
	    	
	    	JComboBox comboBox = new JComboBox();
	    	comboBox.setFocusable(false);
	    	comboBox.setBackground(Color.WHITE);		    			    	
	    	comboBox.setModel(new DefaultComboBoxModel(new RefOntoUML.AggregationKind[] {RefOntoUML.AggregationKind.NONE, 
	    		RefOntoUML.AggregationKind.SHARED, RefOntoUML.AggregationKind.COMPOSITE}));
	        editor = new DefaultCellEditor(comboBox);
	    }

        return editor.getTableCellEditorComponent(table, value, isSelected, row, column);
    }
}	