package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;

public class OverlappingGroupComposite extends Composite{

	private final OverlappingGroup variation;
	private final ArrayList<Property> overlappingProperties;
	private final int variationIndex;
	protected Table table;
	protected Button btnAddLine;
	
	public OverlappingGroupComposite (Composite parent, int style, OverlappingGroup variation, String title, int variationIndex) {
		super(parent,style);
		
		table = new Table(this, SWT.BORDER);
		
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
			tableColumn.setWidth(150);
			tableColumn.setText(columnName);
		}
		
		addLine();
		
		btnAddLine = new Button(this, SWT.NONE);
		btnAddLine.setText("Add Line");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.TRAILING)
				.add(table, GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE)
				.add(groupLayout.createSequentialGroup()
					.addContainerGap()
					.add(btnAddLine, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, groupLayout.createSequentialGroup()
					.add(table, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
					.add(10)
					.add(btnAddLine))
		);
		setLayout(groupLayout);
		btnAddLine.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 addLine();
			 }
		});		
	}
	
	public ArrayList<Property> getOverlappingProperties() {
		return overlappingProperties;
	}

	public int getVariationIndex() {
		return variationIndex;
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
	
	public OverlappingGroup getVariation() {
		return variation;
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

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}