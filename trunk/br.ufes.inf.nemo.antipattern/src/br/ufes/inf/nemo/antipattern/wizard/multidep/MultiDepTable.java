package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Property;

public class MultiDepTable {

	private ArrayList<Property> properties;
	private Table table;
	
	@SuppressWarnings("unused")
	public MultiDepTable (Composite parent, int args, ArrayList<Property> properties) 
	{		
		table = new Table(parent, args);
		
		this.properties = properties;
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		for (Property p : properties) 
		{
			String columnName = p.getType().getName();
			TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
			tableColumn.setWidth(100);
			tableColumn.setText(columnName);
		}
		
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
		
		table.setSize(418, 117);
		
		addLine();
	}
		
	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void addLine(){
	
		TableItem tableItem = new TableItem(table,SWT.NONE);
		
		for (Integer i = 0; i < properties.size(); i++) {
			TableEditor editor = new TableEditor(table);
			Button checkButton = new Button(table, SWT.CHECK);
			checkButton.pack();
			editor.minimumWidth = checkButton.getSize().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(checkButton, tableItem, i);
			tableItem.setData(i.toString(), checkButton);
		}		
	}
	
	public Table getTable() {
		return table;
	}

	public ArrayList<ArrayList<Property>> getSelections (){
		ArrayList<ArrayList<Property>> result = new ArrayList<ArrayList<Property>>();		
		for (TableItem ti : table.getItems()){			
			ArrayList<Property> selectedProperties = getSelected(ti);
			if(selectedProperties.size()==2)
				result.add(selectedProperties);
		}		
		return result;		
	}
		
	private ArrayList<Property> getSelected(TableItem ti){
		ArrayList<Property> line = new ArrayList<Property>();		
		for (Integer i = 0; i < properties.size(); i++) {
			Button checkBox = (Button) ti.getData(i.toString());
			if (checkBox.getSelection())
				line.add(properties.get(i));
		}		
		return line;
	}
	
}		 

