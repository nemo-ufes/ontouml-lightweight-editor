package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class RigidTable {
	
	private GSRigOccurrence gsrig;
	private Table table;
	
	@SuppressWarnings("unused")
	public RigidTable (Composite parent, int args, GSRigOccurrence gsrig) 
	{		
		table = new Table(parent, args);
		
		this.gsrig = gsrig;
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		String columnName1 = "Rigid subtype";
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(100);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Antirigid Stereotype";
		TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
		tableColumn2.setWidth(140);
		tableColumn2.setText(columnName2);
			
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
		
		table.setSize(418, 117);
		
		fulfillLines();
	}
	
	public void enable(boolean value)
	{
		table.setEnabled(value);
	}
	
	public void limitChoiceToRoleAndPhase()
	{				
		for (TableItem ti : table.getItems()){	
			Combo combo = (Combo) ti.getData("1");		
			combo.setItems(new String[] {"Phase", "Role"});			
			combo.select(0);			
		}
	}
	
	public void limitChoiceToRoleMixin()
	{				
		for (TableItem ti : table.getItems()){	
			Combo combo = (Combo) ti.getData("1");		
			combo.setItems(new String[] {"RoleMixin"});			
			combo.select(0);			
		}
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public void fulfillLines()
	{
		for(Classifier c: gsrig.getRigidSpecifics())
		{
			TableItem tableItem = new TableItem(table,SWT.NONE);	
						
			// Rigid subtype
			TableEditor editor = new TableEditor(table);
		    editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			tableItem.setText(0, c.getName());
			
			// Antirigid Stereotype
			editor = new TableEditor(table);
			Combo combo = new Combo(table, SWT.READ_ONLY);
			combo.setItems(new String[] {"Phase", "Role", "RoleMixin"});			
			combo.setText(getStereotype(c));
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
	
	public ArrayList<String> getNewStereotypes()
	{
		ArrayList<String> result = new ArrayList<String>();		
		for (TableItem ti : table.getItems()){	
			Combo combo = (Combo) ti.getData("1");			
			result.add(combo.getText());
		}
		return result;	
	}
}
