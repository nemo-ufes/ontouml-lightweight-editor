package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.common.util.EList;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;

public class AttrTable  {

	private ArrayList<Property> properties;
	private HashMap<String,String> attrMap = new HashMap<String,String>();
	private Table table;
	
	@SuppressWarnings("unused")
	public AttrTable (Composite parent, int args, EList<Property> properties) 
	{		
		table = new Table(parent, args);
		this.properties = new ArrayList<Property>();
		
		for (Property p : properties) {
			if(p.getType()!=null)
				this.properties.add(p);
		}
		
		table.setHeaderVisible(true);		
		table.setLinesVisible(true);
				
		String columnName1 = "Name";
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(75);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Type";
		TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
		tableColumn2.setWidth(75);
		tableColumn2.setText(columnName2);
	
		String columnName3 = "";
		TableColumn tableColumn3 = new TableColumn(table, SWT.CENTER);
		tableColumn3.setWidth(100);
		tableColumn3.setText(columnName3);
		
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
			
		table.setSize(240, 82);
		
		addExistentAttributes();
	}
		
	public ArrayList<Property> getProperties() {
		return properties;
	}

	public void addNewPrimitiveType(String str, String type)
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
		
		item.setText(2,"(PrimitiveType)");
		
		getTable().select(getTable().getItemCount()-1);
	}
	
	public void addNewDataType(String str, String type)
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
		
		item.setText(2,"(DataType)");
		getTable().select(getTable().getItemCount()-1);
	}	
	
	public void addNewEnumeration(String str, String type)
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
		
		item.setText(2,"(Enumeration)");
		getTable().select(getTable().getItemCount()-1);
	}
	
	public void removeLine()
	{
		if(getTable().getSelectionIndex()>=0) {	
			int prev = getTable().getSelectionIndex()-1;				
			getTable().remove(getTable().getSelectionIndices());					
			getTable().select(prev);		
			getTable().setRedraw(true);
		}
	}
	
	private void addExistentAttributes()
	{
		for (int i = 0; i < properties.size(); i++) {
			
			TableItem item = new TableItem(table, SWT.NONE);
		
			TableEditor editor = new TableEditor(table);
		    editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			item.setText(0,properties.get(i).getName());
			
			editor = new TableEditor(table);			
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			
			item.setText(1,OntoUMLNameHelper.getName(properties.get(i).getType()));
			item.setText(2,"("+OntoUMLNameHelper.getTypeName(properties.get(i).getType(), false)+")");
			
			attrMap.put(properties.get(i).getName(), properties.get(i).getType().getName());
		}		
	}
	
	public Table getTable() {
		return table;
	}

	public HashMap<String,String> getValues()
	{
		HashMap<String,String> map = new HashMap<String,String>();
		for (TableItem ti : table.getItems()){	
			String name = ti.getText(0);
			String type = ti.getText(1);			
			if (name!= null && !name.isEmpty() && type!=null && !type.isEmpty()){
				if (attrMap.get(name)==null){
					map.put(name,type);	
				}
			}
		}
		return map;
	}
	
	public HashMap<String,String>  getStereotypes()
	{
		HashMap<String,String> map = new HashMap<String,String>();
		for (TableItem ti : table.getItems()){	
			String name = ti.getText(0);
			String type = ti.getText(2);			
			if (name!= null && !name.isEmpty() && type!=null && !type.isEmpty()){
				if (attrMap.get(name)==null){
					map.put(name,type.replace("(","").replace(")",""));	
				}
			}
		}
		return map;
	}
}