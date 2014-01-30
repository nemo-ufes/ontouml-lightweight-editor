package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Property;

public class MultiDepComboTable {

	private ArrayList<Property> properties;
	private Table table;
	
	@SuppressWarnings("unused")
	public MultiDepComboTable (Composite parent, int args, ArrayList<Property> properties) 
	{		
		table = new Table(parent, args);
		
		this.properties = properties;
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		String columnName1 = "Depender";
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(150);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Dependee";
		TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
		tableColumn2.setWidth(150);
		tableColumn2.setText(columnName2);
		
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
		
		table.setSize(418, 117);		
	}
		
	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void addLine(){
	
		TableItem tableItem = new TableItem(table,SWT.NONE);
				
		TableEditor editor = new TableEditor(table);
		Combo combo = new Combo(table, SWT.NONE);
		for(Property p: properties){
			combo.add(p.getType().getName());
		}
		combo.pack();
		combo.select(0);
		//editor.minimumWidth = combo.getSize().x;
		editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(combo, tableItem, 0);
		tableItem.setData(Integer.toString(0),combo);
		
		editor = new TableEditor(table);
		combo = new Combo(table, SWT.NONE);
		for(Property p: properties){
			combo.add(p.getType().getName());
		}
		combo.select(1);
		combo.pack();
		//editor.minimumWidth = combo.getSize().x;
		editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(combo, tableItem, 1);		
		tableItem.setData(Integer.toString(1),combo);
	}
	
	public Table getTable() {
		return table;
	}
	
	public Property getProperty (String typeName){
		for(Property p: properties){
			if(p.getType().getName().compareToIgnoreCase(typeName)==0) return p;			
		}
		return null;
	}
	
	public ArrayList<ArrayList<Property>> getSelections (){
		ArrayList<ArrayList<Property>> result = new ArrayList<ArrayList<Property>>();		
		for (TableItem ti : table.getItems()){			
			ArrayList<Property> selectedProperties = getSelected(ti);
			result.add(selectedProperties);
		}		
		return result;		
	}
		
	private ArrayList<Property> getSelected(TableItem ti){
		ArrayList<Property> line = new ArrayList<Property>();		
		for (Integer i = 0; i < 2; i++) {
			Combo combo = (Combo) ti.getData(i.toString());
			String selected = combo.getText();
			if (selected!=null && !selected.isEmpty()){
				Property p = getProperty(selected);
				if (p!=null) line.add(p);
			}			
		}		
		return line;
	}
	
}		 

