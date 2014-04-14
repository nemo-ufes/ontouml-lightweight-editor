package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Mediation;

public class RepRelTable {

	private final ArrayList<Mediation> mediations;
	private Table table;
	
	@SuppressWarnings("unused")
	public RepRelTable (Composite parent, int args, ArrayList<Mediation> mediations, String title) 
	{		
		table = new Table(parent, args);
		
		this.mediations = mediations;
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		for (Mediation m : mediations) 
		{
			String columnName = m.mediated().getName();
			TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
			tableColumn.setWidth(100);
			tableColumn.setText(columnName);
		}
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(150);
		tableColumn.setText("N-instances of "+mediations.get(0).relator().getName());
		
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
		
		table.setSize(418, 117);
		addLine();
	}
	
	public ArrayList<Mediation> getProperties() {
		return mediations;
	}

	public void addLine(){
	
		TableItem tableItem = new TableItem(table,SWT.NONE);
		
		for (Integer i = 0; i < mediations.size(); i++) {
			TableEditor editor = new TableEditor(table);
			Button checkButton = new Button(table, SWT.CHECK);
			checkButton.pack();
			editor.minimumWidth = checkButton.getSize().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(checkButton, tableItem, i);
			tableItem.setData(i.toString(), checkButton);
		}
		TableEditor editor = new TableEditor(table);
		Spinner spinner = new Spinner(table, SWT.CHECK);
		spinner.setSelection(1);
		spinner.pack();
		editor.minimumWidth = spinner.getSize().x;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(spinner, tableItem, mediations.size());
		tableItem.setData(Integer.toString(mediations.size()), spinner);
	}
	
	public Table getTable() {
		return table;
	}

	public ArrayList<Integer> getNs (){
		ArrayList<Integer> result = new ArrayList<Integer>();		
		for (TableItem ti : table.getItems()){			
			int n = getN(ti);			
			result.add(n);
		}		
		return result;		
	}
	
	public ArrayList<ArrayList<Mediation>> getSelections (){
		ArrayList<ArrayList<Mediation>> result = new ArrayList<ArrayList<Mediation>>();		
		for (TableItem ti : table.getItems()){			
			ArrayList<Mediation> selectedProperties = getSelected(ti);
			if(selectedProperties.size()==2)
				result.add(selectedProperties);
		}		
		return result;		
	}
	
	private int getN(TableItem ti){		
		Spinner spinner = (Spinner) ti.getData(Integer.toString(mediations.size()));
		return spinner.getSelection();		
	}
	
	private ArrayList<Mediation> getSelected(TableItem ti){
		ArrayList<Mediation> line = new ArrayList<Mediation>();		
		for (Integer i = 0; i < mediations.size(); i++) {			
			Button checkBox = (Button) ti.getData(i.toString());			
			if (checkBox.getSelection())
				line.add(mediations.get(i));
		}
		
		return line;
	}
	
}		 

