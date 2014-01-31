package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Property;

public class MultiDepSpinnerTable {

	private ArrayList<Property> properties;
	private Table table;
	
	@SuppressWarnings("unused")
	public MultiDepSpinnerTable (Composite parent, int args, ArrayList<Property> properties) 
	{		
		table = new Table(parent, args);
		
		this.properties = properties;
		table.setHeaderVisible(true);		
		table.setLinesVisible(true);
		
		String columnName1 = "Relator";
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(250);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Order";
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
		
	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void addLine(String str, int order)
	{
		TableItem item = new TableItem(table, SWT.NONE);
		
		TableEditor editor = new TableEditor(table);
//		Text text = new Text(table, SWT.NONE);
//		text.setEditable(false);
//		text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
//	    text.setText(str);
	    editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
	    //editor.minimumWidth = text.getSize().x;
		//editor.setEditor(text, item, 0);	
		item.setText(0,str);
		
		editor = new TableEditor(table);			
		Spinner spinner = new Spinner(table, SWT.NONE);
		spinner.pack();
		spinner.setSelection(order);
		editor.grabHorizontal = true;		
		editor.horizontalAlignment = SWT.CENTER;		
		editor.setEditor(spinner, item, 1);	
		item.setData(Integer.toString(1),spinner);
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
//			Text text = new Text(table, SWT.NONE);
//			text.setEditable(false);
//			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
//		    text.setText(properties.get(i).getType().getName());
		    editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			//editor.setEditor(text, items[i], 0);	
			items[i].setText(0,properties.get(i).getType().getName());
			
			editor = new TableEditor(table);			
			Spinner spinner = new Spinner(table, SWT.NONE);
			spinner.pack();
			spinner.setSelection(i);
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(spinner, items[i], 1);	
			items[i].setData(Integer.toString(1),spinner);
		}		
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
	
	public HashMap<Property,Integer> getValues()
	{
		HashMap<Property, Integer> valueHashMap = new HashMap<Property,Integer>();
		for (TableItem ti : table.getItems()){	
			String str = ti.getText(0);
			Property p = getProperty(str.replace("Relator ", ""));
			Spinner spinner = (Spinner)ti.getData("1");
			int order = spinner.getSelection();
			valueHashMap.put(p,order);			
		}
		return valueHashMap;
	}	
	
}		 

