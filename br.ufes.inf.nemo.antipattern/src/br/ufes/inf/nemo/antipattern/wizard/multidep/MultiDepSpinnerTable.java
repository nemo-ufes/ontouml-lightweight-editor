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

public class MultiDepSpinnerTable extends Table{

	private static final String SPINNER_EDITOR = "spinner";
	private ArrayList<Property> properties = new ArrayList<Property>();

	public MultiDepSpinnerTable (Composite parent, int args) 
	{	super(parent,args);
	
		setHeaderVisible(true);		
		setLinesVisible(true);
		
		String columnName1 = "Relator";
		TableColumn tableColumn1 = new TableColumn(this, SWT.CENTER);
		tableColumn1.setWidth(400);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Order";
		TableColumn tableColumn2 = new TableColumn(this, SWT.CENTER);
		tableColumn2.setWidth(100);
		tableColumn2.setText(columnName2);
		
	}
	
	public void setProperties(ArrayList<Property> list)
	{
		if(!list.containsAll(properties) || !properties.containsAll(list)){
			removeLines();
			properties.clear();
			properties.addAll(list);
			addLines();
		}
		
	}
	
	public ArrayList<Property> getProperties() {
		return new ArrayList<Property>(properties);
	}

	public void addLine(Property p, int order)
	{
		TableItem item = new TableItem(this, SWT.NONE);
		item.setText(0,p.getType().getName());
		TableEditor editor = new TableEditor(this);			
		Spinner spinner = new Spinner(this, SWT.NONE);
		spinner.pack();
		spinner.setSelection(order);
		editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(spinner, item, 1);	
		item.setData(SPINNER_EDITOR,editor);
	}
		
	
	public void removeLines(){
		while(getItemCount()>0){
			removeLine(0);
		}
		
		properties.clear();
	}

	public void updateEditors(){
		for (TableItem item : getItems()) {
			((TableEditor) item.getData(SPINNER_EDITOR)).layout();
		}
	}
	
	public void removeLine(int index) {
		
		if(index<0 || index>=getItemCount())
			return;
		
		TableItem item = getItem(index);
		TableEditor editor = (TableEditor) item.getData(SPINNER_EDITOR);
		editor.getEditor().dispose();
		editor.dispose();
		remove(index);
	
		updateEditors();
	}
		
	public void addLines(){
		for (int i = 0; i < properties.size(); i++) 
			addLine(properties.get(i), i);
	}
	
	public Table getTable() {
		return this;
	}
	
	public HashMap<Property,Integer> getValues()
	{
		HashMap<Property, Integer> hash = new HashMap<Property,Integer>();
		for (int i = 0; i < getItems().length; i++) {
			hash.put(properties.get(i), getOrder(i));
		}
		return hash;
	}	
	
	@Override
	protected void checkSubclass() {}

	public Integer getOrder(int index) {
		if(index<0 || index>=getItemCount())
			return null;
	
		Spinner spinner = (Spinner)((TableEditor)getItem(index).getData(SPINNER_EDITOR)).getEditor();
		return spinner.getSelection();
	}
	
}		 

