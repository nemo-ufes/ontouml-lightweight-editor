package br.ufes.inf.nemo.antipattern.wizard.impabs;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;

public abstract class ImpAbsTableBuilder<T extends ImpAbsLine>{
	protected final ImpAbsOccurrence occurrence;
	protected final Table table;
	protected final Button btnAddLine;
	protected final ArrayList<String> columnNames;
	
	public ImpAbsTableBuilder (Composite parent, int args, ImpAbsOccurrence occurrence, int columnStdWidth, ArrayList<String> columnNames) throws Exception{
		this.columnNames = columnNames;
		table = new Table(parent, args);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		table.setSize(410, 157);
		
		if(occurrence==null)
			throw new Exception();
		this.occurrence = occurrence;
		
		if(columnNames==null || columnNames.isEmpty())
			throw new Exception();
		
		for (String columnName : columnNames) {
			TableColumn column = new TableColumn(table, SWT.CENTER);
			column.setWidth(columnStdWidth);
			column.setText(columnName);			
		}

		addLine();
				
		btnAddLine = new Button(parent, SWT.NONE);
		btnAddLine.setText("Add Line");
		
		btnAddLine.setBounds(table.getBounds().x+table.getBounds().width-75, table.getBounds().y-35, 75, 25);
		btnAddLine.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 addLine();
			 }
		});		
	}

	public Button getBtnAddLine() {
		return btnAddLine;
	}
	
	public Table getTable() {
		return table;
	}

	public void setColumnStdWidth(int columnStdWidth) {
		for (TableColumn column : table.getColumns()) {
			column.setWidth(columnStdWidth);
		}
	}

	public ImpAbsOccurrence getOccurrence() {
		return occurrence;
	}

	public ArrayList<T> getLines(){
		ArrayList<T> result = new ArrayList<T>();
		
		for (TableItem ti : table.getItems()){	
			try{
				T newLine = getLine(ti);
				if(!result.contains(newLine))
					result.add(newLine);
			}catch(Exception e){
				System.out.println("Couldn't get Line!!!");
			};
		}
		
		return result;
	}
	
	protected void insertCCombo(TableItem tableItem, ArrayList<String> options, int columnPosition, boolean isEditable) {
		TableEditor editor = new TableEditor(table);
		CCombo combo = new CCombo(table, SWT.BORDER);

		for (int i = 0; i < options.size(); i++){
			combo.add(options.get(i));
		}
		
		combo.setVisibleItemCount(options.size());
		
		combo.setEditable(isEditable);
		combo.pack();
		editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(combo, tableItem, columnPosition);
		tableItem.setData(columnNames.get(columnPosition), combo);
	}
	
	protected void insertTypeCombo(TableItem tableItem, Classifier c, int columnPosition){
		insertCCombo(tableItem, getNames(c), columnPosition, false);
	}
	
	protected void insertMultiplicityCombo(TableItem tableItem, int columnPosition){
		insertCCombo(tableItem, getMultiplicities(), columnPosition, true);
	}
	
	protected void insertTrueFalseCombo(TableItem tableItem, int columnPosition){
		insertCCombo(tableItem, getTrueFalse(), columnPosition, false);
	}
	
	protected void insertCheckBox(TableItem tableItem, Integer columnPosition){
		TableEditor editor = new TableEditor(table);
		Button checkBox = new Button(table, SWT.CHECK);
		checkBox.pack();
		editor.minimumWidth = checkBox.getSize().x;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(checkBox, tableItem, columnPosition);
		tableItem.setData(columnNames.get(columnPosition), checkBox);
	}
	
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}
	
	private ArrayList<String> getNames(Classifier c){
		ArrayList<String> names = new ArrayList<String>();
		names.add(c.getName());
		
		for (Classifier child : c.allChildren())
			names.add(child.getName());
	
		return names;
	}
	
	private ArrayList<String> getMultiplicities(){
		ArrayList<String> multiplicities = new ArrayList<String>();
		multiplicities.add("0..1");
		multiplicities.add("1..1");
		multiplicities.add("0..*");
		multiplicities.add("1..*");
		return multiplicities;
	}
	
	private ArrayList<String> getTrueFalse(){
		ArrayList<String> trueFalse = new ArrayList<String>();
		trueFalse.add("True");
		trueFalse.add("False");
		return trueFalse;
	}
	

	public abstract void addLine();
	protected abstract T getLine(TableItem ti) throws Exception;
}