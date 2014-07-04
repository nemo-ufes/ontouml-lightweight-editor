package br.ufes.inf.nemo.antipattern.wizard.freerole;

import java.util.ArrayList;
import java.util.HashSet;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import RefOntoUML.Classifier;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleDependenceTable {

	private FreeRoleOccurrence freeRole;
	private Table table;
	
	@SuppressWarnings("unused")
	public FreeRoleDependenceTable (Composite parent, int args, FreeRoleOccurrence freeRole) 
	{		
		table = new Table(parent, args);
		
		this.freeRole = freeRole;
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		String columnName1 = "Relator";
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(150);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Use?";
		TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
		tableColumn2.setWidth(50);
		tableColumn2.setText(columnName2);
		
		String columnName3 = "Specialize?";
		TableColumn tableColumn3 = new TableColumn(table, SWT.CENTER);
		tableColumn3.setWidth(80);
		tableColumn3.setText(columnName3);
		
		String columnName4 = "New Relator's Name";
		TableColumn tableColumn4 = new TableColumn(table, SWT.CENTER);
		tableColumn4.setWidth(120);
		tableColumn4.setText(columnName4);
		
		String columnName5 = "Mult. Role End";
		TableColumn tableColumn5 = new TableColumn(table, SWT.CENTER);
		tableColumn5.setWidth(110);
		tableColumn5.setText(columnName5);

		String columnName6 = "Mult. Relator End";
		TableColumn tableColumn6 = new TableColumn(table, SWT.CENTER);
		tableColumn6.setWidth(110);
		tableColumn6.setText(columnName6);
		
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
		
		table.setSize(418, 117);
		
		fillData();
	}
		
	public void fillData(){
	
		HashSet<Classifier> relators = new HashSet<Classifier>();
		
		for(Property p: freeRole.getDefiningRelatorEnds()){
			Classifier relator = (Classifier) p.getType();
			relators.add(relator);
			
			if(!((Antipattern<FreeRoleOccurrence>)freeRole.getAntipattern()).getParser().allChildrenHash.containsKey(relator))
				continue;
			
			for (Classifier child : ((Antipattern<FreeRoleOccurrence>)freeRole.getAntipattern()).getParser().allChildrenHash.get(relator)) {
				relators.add(child);
			}
		}
		
		for(Classifier relator: relators)
		{
			TableItem tableItem = new TableItem(table,SWT.NONE);	
			tableItem.setData("relator", relator);
			
			// Current Relator Name
			TableEditor editor = new TableEditor(table);
		    editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			tableItem.setText(0, relator.getName());
			
			// Use
			editor = new TableEditor(table);
			Button checkButton = new Button(table, SWT.CHECK);
			checkButton.pack();
			editor.minimumWidth = checkButton.getSize().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(checkButton, tableItem, 1);
			tableItem.setData("1", checkButton);
			
			// Specialize
			editor = new TableEditor(table);
			checkButton = new Button(table, SWT.CHECK);
			checkButton.pack();
			editor.minimumWidth = checkButton.getSize().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(checkButton, tableItem, 2);
			tableItem.setData("2", checkButton);
			
			// New Relator Name
			editor = new TableEditor(table);
			Text text = new Text(table, SWT.BORDER);
			text.pack();
			editor.minimumWidth = text.getSize().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(text, tableItem, 3);
			tableItem.setData("3", text);
			
			// Mult. Role End
			editor = new TableEditor(table);
			Combo combo = new Combo(table, SWT.NONE);
			combo.setItems(new String[] {"0..1", "1", "1..*", "0..*"});
			combo.select(2);	
			combo.pack();
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(combo, tableItem, 4);
			tableItem.setData("4", combo);
			
			// Mult. Relator End
			editor = new TableEditor(table);
			combo = new Combo(table, SWT.NONE);
			combo.setItems(new String[] {"0..1", "1", "1..*", "0..*"});
			combo.select(2);
			combo.pack();
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(combo, tableItem, 5);
			tableItem.setData("5", combo);
		}
	}
	
	public Table getTable() {
		return table;
	}
	
	public ArrayList<Boolean> getUse()
	{
		ArrayList<Boolean> result = new ArrayList<Boolean>();		
		for (TableItem ti : table.getItems()){	
			Button button = (Button) ti.getData("1");			
			result.add(button.getSelection());
		}
		return result;	
	}
	
	public ArrayList<String> getMultRoleEnd()
	{
		ArrayList<String> result = new ArrayList<String>();		
		for (TableItem ti : table.getItems()){	
			Combo combo = (Combo) ti.getData("4");
			result.add(combo.getText());
		}
		return result;	
	}
	
	public ArrayList<String> getMultRelatorEnd()
	{
		ArrayList<String> result = new ArrayList<String>();		
		for (TableItem ti : table.getItems()){	
			Combo combo = (Combo) ti.getData("5");
			result.add(combo.getText());
		}
		return result;	
	}
	
	public ArrayList<String> getRelatorNames()
	{
		ArrayList<String> result = new ArrayList<String>();		
		for (TableItem ti : table.getItems()){	
			Text text = (Text) ti.getData("3");			
			result.add(text.getText());
		}
		return result;	
	}
	
	public ArrayList<Boolean> getSpecialize()
	{
		ArrayList<Boolean> result = new ArrayList<Boolean>();		
		for (TableItem ti : table.getItems()){	
			Button button = (Button) ti.getData("2");			
			result.add(button.getSelection());
		}
		return result;	
	}	
}		 
