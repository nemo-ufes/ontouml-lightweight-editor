package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import br.ufes.inf.nemo.antipattern.overlapping.OverlappingTypesVariation;

import RefOntoUML.Property;

public class OverlappingCheckBoxTableBuilder{

	private final OverlappingTypesVariation variation;
	private final ArrayList<Property> overlappingProperties;
	private final int variationIndex;
	private Table table;
	private Button btnAddLine;
	private Label lblTableTitle;
	
	public OverlappingCheckBoxTableBuilder (Composite parent, int args, OverlappingTypesVariation variation, String title, int variationIndex) throws Exception{
		
		table = new Table(parent, args);
		
		if(variation==null)
			throw new Exception();
		
		this.variation = variation;
		this.variationIndex = variationIndex;
		overlappingProperties = variation.getOverlappingProperties();
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		for (Property p : overlappingProperties) {
			String columnName = p.getType().getName();
			if(p.getName()!=null && !p.getName().trim().isEmpty())
				columnName += " ("+p.getName()+")";
			else
				columnName += " (unnamed)";
			TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
			tableColumn.setWidth(200);
			tableColumn.setText(columnName);
		}
		
		int tableWidth = 0;
		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
		
		table.setSize(tableWidth+10, 200);
		addLine();
		
		lblTableTitle = new Label(parent, SWT.NONE);
		lblTableTitle.setText(title);
		
		btnAddLine = new Button(parent, SWT.NONE);
		btnAddLine.setText("Add Line");
		btnAddLine.setBounds(300, 300, 300, 300);
		btnAddLine.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 addLine();
				 
				 for (ArrayList<Property> line : getSelections()) {
					 System.out.print("Line: ");
					 for (Property p : line) {
						System.out.print(p.getType().getName()+", ");
					 }
					 System.out.println();
				}
			 }
		});		
	}
	
	public ArrayList<Property> getOverlappingProperties() {
		return overlappingProperties;
	}

	public int getVariationIndex() {
		return variationIndex;
	}

	public Button getBtnAddLine() {
		return btnAddLine;
	}

	public Label getLblTableTitle() {
		return lblTableTitle;
	}

	public void addLine(){
	
		TableItem tableItem = new TableItem(table,SWT.NONE);
		
		for (Integer i = 0; i < overlappingProperties.size(); i++) {
			TableEditor editor = new TableEditor(table);
			Button checkButton = new Button(table, SWT.CHECK);
			checkButton.pack();
			editor.minimumWidth = checkButton.getSize().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(checkButton, tableItem, i);
			tableItem.setData(i.toString(), checkButton);
		}
	}
	
	public OverlappingTypesVariation getVariation() {
		return variation;
	}

	public Table getTable() {
		return table;
	}
	
	public Button getButton() {
		return btnAddLine;
	}
	
	public Label getLabel() {
		return lblTableTitle;
	}
	
	public ArrayList<ArrayList<Property>> getSelections (){
		ArrayList<ArrayList<Property>> result = new ArrayList<ArrayList<Property>>();
		
		for (TableItem ti : table.getItems()){			
			ArrayList<Property> selectedProperties = getSelectedProperties(ti);
			if(selectedProperties.size()>=2)
				result.add(selectedProperties);
		}
		
		return result;
		
	}
	
	private ArrayList<Property> getSelectedProperties(TableItem ti){
		ArrayList<Property> line = new ArrayList<Property>();
		
		for (Integer i = 0; i < overlappingProperties.size(); i++) {
			Button checkBox = (Button) ti.getData(i.toString());
			if (checkBox.getSelection())
				line.add(overlappingProperties.get(i));
		}
		
		return line;
	}
	
		 
}