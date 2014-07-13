package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Property;

public class MultiDepComboTable extends Table{

	private static final String DEPENDEE_COMBO_EDITOR = Integer.toString(1);
	private static final String DEPENDER_COMBO_EDITOR = Integer.toString(0);
	
	private ArrayList<Property> properties = new ArrayList<Property>();
	
	public MultiDepComboTable (Composite parent, int args) 
	{		
		super(parent, args);
		
		this.setHeaderVisible(true);
		this.setLinesVisible(true);
		
		String columnName0 = "#";
		TableColumn tableColumn0 = new TableColumn(this, SWT.CENTER);
		tableColumn0.setWidth(25);
		tableColumn0.setText(columnName0);
		
		String columnName1 = "Depender";
		TableColumn tableColumn1 = new TableColumn(this, SWT.CENTER);
		tableColumn1.setWidth(275);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Dependee";
		TableColumn tableColumn2 = new TableColumn(this, SWT.CENTER);
		tableColumn2.setWidth(275);
		tableColumn2.setText(columnName2);

	}
	
	public void setProperties(ArrayList<Property> list){
		properties.clear(); 
		
		if(!list.containsAll(properties) || !properties.containsAll(list)){
			removeLines();
			properties.addAll(list);
		}
	}
	
	public ArrayList<Property> getProperties() {
		return properties;
	}
	
	public void removeLines(){
		while(getItemCount()>0){
			removeLine(0);
		}		
	}

	public void updateEditors(){
		Integer i = 1;
		for (TableItem item : getItems()) {
			item.setText(0, i.toString());
			((TableEditor) item.getData(DEPENDER_COMBO_EDITOR)).layout();
			((TableEditor) item.getData(DEPENDEE_COMBO_EDITOR)).layout();
			i++;
		}
	}
	
	public void removeLine(int index) {
		if(index<0 || index>=getItemCount())
			return;
		 
		TableEditor editor = (TableEditor) this.getItem(index).getData(DEPENDER_COMBO_EDITOR);
		editor.getEditor().dispose();
		editor.dispose();
		
		editor = (TableEditor) this.getItem(index).getData(DEPENDEE_COMBO_EDITOR);
		editor.getEditor().dispose();
		editor.dispose();
		
		remove(index);
		
		updateEditors();
	}

	public void addLine(){
	
		TableItem item = new TableItem(this,SWT.NONE);
		
		item.setText(0, Integer.toString(getItemCount()));
		
		TableEditor editor = new TableEditor(this);
		CCombo combo = new CCombo(this, SWT.NONE);
		for(Property p: properties){
			combo.add(p.getType().getName());
		}
		combo.setEditable(false);
		combo.pack();
		combo.select(0);
		//editor.minimumWidth = combo.getSize().x;
		editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(combo, item, 1);
		item.setData(DEPENDER_COMBO_EDITOR,editor);
		
		editor = new TableEditor(this);
		combo = new CCombo(this, SWT.NONE);
		for(Property p: properties){
			combo.add(p.getType().getName());
		}
		combo.setEditable(false);
		combo.select(1);
		combo.pack();
		editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(combo, item, 2);		
		item.setData(DEPENDEE_COMBO_EDITOR,editor);
	}
	
	public Table getTable() {
		return this;
	}
	
	public ArrayList<ArrayList<Property>> getSelections (){
		ArrayList<ArrayList<Property>> result = new ArrayList<ArrayList<Property>>();		
		for (TableItem ti : this.getItems()){			
			ArrayList<Property> selectedProperties = getSelected(ti);
			result.add(selectedProperties);
		}		
		return result;		
	}
		
	private ArrayList<Property> getSelected(TableItem ti){
		ArrayList<Property> line = new ArrayList<Property>();
		
		CCombo combo = (CCombo) ((TableEditor) ti.getData(DEPENDER_COMBO_EDITOR)).getEditor();
		int index = combo.getSelectionIndex();
		if(index!=-1)
			line.add(properties.get(index));
		
		combo = (CCombo) ((TableEditor) ti.getData(DEPENDEE_COMBO_EDITOR)).getEditor();
		index = combo.getSelectionIndex();
		if(index!=-1)
			line.add(properties.get(index));
		
		return line;
	}
	
	@Override
	protected void checkSubclass() {}
	
}		 

