package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Property;

public class UndefFormalAttrTable  {

	private EList<Property> properties;
	private Table table;
	
	@SuppressWarnings("unused")
	public UndefFormalAttrTable (Composite parent, int args, EList<Property> properties) 
	{		
		table = new Table(parent, args);
		
		this.properties = properties;
		table.setHeaderVisible(true);		
		table.setLinesVisible(true);
		
		String columnName1 = "Name";
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(100);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Type";
		TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
		tableColumn2.setWidth(100);
		tableColumn2.setText(columnName2);
	
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
			
		table.setSize(418, 117);
		
		addLines();	
	}
		
	public EList<Property> getProperties() {
		return properties;
	}

	public void addLine(String str, String type)
	{
		TableItem item = new TableItem(table, SWT.NONE);
		
		TableEditor editor = new TableEditor(table);
	    editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		item.setText(0,str);
		
		editor = new TableEditor(table);			
		editor.grabHorizontal = true;		
		editor.horizontalAlignment = SWT.CENTER;		

		item.setText(1,type);
	}
	
	public void addLines()
	{
		for (int i = 0; i < properties.size(); i++) {
			 new TableItem(table, SWT.NONE);
		}
		TableItem[] items = table.getItems();
		for (Integer i = 0; i < items.length; i++) 
		{
			TableEditor editor = new TableEditor(table);
		    editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			items[i].setText(0,properties.get(i).getName());
			
			editor = new TableEditor(table);			
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			items[i].setText(1,properties.get(i).getType().getName());
		}		
	}
	
	public Table getTable() {
		return table;
	}

	public Property getProperty (String name, String typeName){
		for(Property p: properties){
			if(p.getType().getName().compareToIgnoreCase(typeName)==0 && p.getName().compareToIgnoreCase(name)==0) return p;			
		}
		return null;
	}
	
	public ArrayList<Property> getValues()
	{
		ArrayList<Property> list = new ArrayList<Property>();
		for (TableItem ti : table.getItems()){	
			String name = ti.getText(0);
			String type = ti.getText(1);
			Property p = getProperty(name, type);			
			list.add(p);			
		}
		return list;
	}	
}