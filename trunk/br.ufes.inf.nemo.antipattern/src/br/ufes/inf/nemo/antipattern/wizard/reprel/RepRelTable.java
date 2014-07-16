package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Mediation;

public class RepRelTable extends Table{

	private final ArrayList<Mediation> mediations;
	private SelectionListener verifyAction;
	
	public RepRelTable (Composite parent, int args, ArrayList<Mediation> mediations, SelectionListener verifyAction) 
	{		
		super(parent, args);
		
		this.mediations = mediations;
		this.verifyAction = verifyAction;
		
		setHeaderVisible(true);
		setLinesVisible(true);
		
		if(mediations==null || mediations.size()==0)
			return;
		
		for (Mediation m : mediations) 
		{
			String columnName = m.mediated().getName();
			TableColumn tableColumn = new TableColumn(this, SWT.CENTER);
			tableColumn.setWidth(100);
			tableColumn.setText(columnName);
		}
		
		TableColumn tableColumn = new TableColumn(this, SWT.CENTER);
		tableColumn.setWidth(150);
		tableColumn.setText("N-instances of "+mediations.get(0).relator().getName());
		
		addLine();
	}
	
	public void addLine(){
	
		TableItem tableItem = new TableItem(this,SWT.NONE);
		TableEditor editor;
		for (Integer i = 0; i < mediations.size(); i++) {
			editor = new TableEditor(this);	
			Button checkButton = new Button(this, SWT.CHECK);
			
			if(verifyAction!=null)
				checkButton.addSelectionListener(verifyAction);
			
			checkButton.pack();
			editor.minimumWidth = checkButton.getSize().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(checkButton, tableItem, i);
			tableItem.setData(i.toString(), editor);
		}
		
		editor = new TableEditor(this);
		Spinner spinner = new Spinner(this, SWT.CHECK);
		spinner.setMinimum(1);
		spinner.setSelection(1);
		spinner.pack();
		editor.minimumWidth = spinner.getSize().x;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(spinner, tableItem, mediations.size());
		tableItem.setData(Integer.toString(mediations.size()), editor);
	}
	
	public void removeLine(int index, boolean updateTable){
		
		if(index<0 || index>=mediations.size())
			return;
		
		TableItem item = getItem(index);
		TableEditor editor;
		for (Integer i = 0; i <= mediations.size(); i++) {
			editor = ((TableEditor) item.getData(i.toString()));
			editor.getEditor().dispose();
			editor.dispose();
		}
		
		remove(index);
		
		if(updateTable)
			updateEditors();
		
	}
		
	public void removeLines(){
		while(getItemCount()>0){
			removeLine(0,false);
		}		
		
		updateEditors();
	}

	public void updateEditors(){
		for (TableItem item : getItems()) {
			for (Integer i = 0; i <= mediations.size(); i++) {
				((TableEditor) item.getData(i.toString())).layout();
			}
		}
	}

	public ArrayList<Integer> getNs (){
		ArrayList<Integer> result = new ArrayList<Integer>();		
		for (TableItem ti : getItems()){			
			int n = getN(ti);			
			result.add(n);
		}		
		return result;		
	}
	
	public ArrayList<ArrayList<Mediation>> getSelections (){
		ArrayList<ArrayList<Mediation>> result = new ArrayList<ArrayList<Mediation>>();		
		for (TableItem ti : getItems()){			
			ArrayList<Mediation> selectedProperties = getSelected(ti);
			if(selectedProperties.size()==2)
				result.add(selectedProperties);
		}		
		return result;		
	}
	
	private int getN(TableItem ti){		
		Spinner spinner = (Spinner)((TableEditor) ti.getData(Integer.toString(mediations.size()))).getEditor();
		return spinner.getSelection();		
	}
	
	private ArrayList<Mediation> getSelected(TableItem ti){
		ArrayList<Mediation> line = new ArrayList<Mediation>();		
		for (Integer i = 0; i < mediations.size(); i++) {			
			Button checkBox = (Button) ((TableEditor) ti.getData(i.toString())).getEditor();			
			if (checkBox.getSelection())
				line.add(mediations.get(i));
		}
		
		return line;
	}
	
	@Override
	protected void checkSubclass() {}
	
}		 

