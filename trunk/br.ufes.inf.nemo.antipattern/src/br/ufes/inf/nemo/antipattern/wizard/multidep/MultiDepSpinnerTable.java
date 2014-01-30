package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

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

	public void addLines()
	{
		for (int i = 0; i < properties.size(); i++) {
			 new TableItem(table, SWT.NONE);
		}
		TableItem[] items = table.getItems();
		for (Integer i = 0; i < items.length; i++) 
		{
			TableEditor editor = new TableEditor(table);
			Text text = new Text(table, SWT.NONE);
			text.setEditable(false);
			text.setBackground(Display.getCurrent().getSystemColor(SWT.COLOR_WHITE));
		    text.setText(properties.get(i).getType().getName());
		    editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(text, items[i], 0);	
			
			editor = new TableEditor(table);			
			Spinner spinner = new Spinner(table, SWT.NONE);
			spinner.pack();
			spinner.setSelection(i);
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(spinner, items[i], 1);			
		}		
	}
	
	public Table getTable() {
		return table;
	}

//	public ArrayList<ArrayList<Property>> getSelections (){
//		ArrayList<ArrayList<Property>> result = new ArrayList<ArrayList<Property>>();		
//		for (TableItem ti : table.getItems()){			
//			ArrayList<Property> selectedProperties = getSelected(ti);
//			if(selectedProperties.size()==2)
//				result.add(selectedProperties);
//		}		
//		return result;		
//	}
//		
//	private ArrayList<Property> getSelected(TableItem ti){
//		ArrayList<Property> line = new ArrayList<Property>();		
//		for (Integer i = 0; i < properties.size(); i++) {
//			Spinner spinnner = (Spinner) ti.getData(i.toString());
//			if (spinnner.getSelection())
//				line.add(properties.get(i));
//		}		
//		return line;
//	}
	
}		 

