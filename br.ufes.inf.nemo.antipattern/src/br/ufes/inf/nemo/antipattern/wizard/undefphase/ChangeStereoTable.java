package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Phase;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

public class ChangeStereoTable {

	private UndefPhaseOccurrence up;
	private Table table;
	
	@SuppressWarnings("unused")
	public ChangeStereoTable (Composite parent, int args, UndefPhaseOccurrence up) 
	{		
		table = new Table(parent, args);
		
		this.up = up;		
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		String columnName1 = "Phase";
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(142);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Stereotype";
		TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
		tableColumn2.setWidth(112);
		tableColumn2.setText(columnName2);
				
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
		
		table.setSize(332, 117);
		
		fulfillLines();
	}
		
	public void fulfillLines()
	{		
		for(Phase p: up.getPhases())
		{
			TableItem tableItem = new TableItem(table,SWT.NONE);	
			
			// Phase Name
			TableEditor editor = new TableEditor(table);			
			tableItem.setText(0, p.getName());
			
			// Stereotype
			editor = new TableEditor(table);
			Combo combo = new Combo(table, SWT.READ_ONLY);
			combo.setItems(new String[] {"Phase","SubKind","Role"});			
			combo.select(0);
			combo.pack();
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(combo, tableItem, 1);
			tableItem.setData("1", combo);			
		}		
	}
	
	public Table getTable() {
		return table;
	}
		
	public boolean isAllPhase()
	{				
		for (TableItem ti : table.getItems()){	
			Combo combo = (Combo) ti.getData("1");
			if(combo.getText().compareToIgnoreCase("Phase")!=0) return false;
		}
		return true;
	}
	
	public ArrayList<String> getStereotypes()
	{
		ArrayList<String> result = new ArrayList<String>();		
		for (TableItem ti : table.getItems()){	
			Combo combo = (Combo) ti.getData("1");
			result.add(combo.getText());
		}
		return result;	
	}
}		