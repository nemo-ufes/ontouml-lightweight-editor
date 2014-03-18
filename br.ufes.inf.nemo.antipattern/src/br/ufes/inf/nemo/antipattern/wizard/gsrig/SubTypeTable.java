package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.AntiRigidMixinClass;
import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import RefOntoUML.RigidMixinClass;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.SubKind;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class SubTypeTable {
	
	private GSRigOccurrence gsrig;
	private Table table;
	ArrayList<Combo> newStereoCombo = new ArrayList<Combo>();
	ArrayList<Combo> newRigidityCombo = new ArrayList<Combo>();
	
	public void populatesRigid()
	{
		Classifier supertype = gsrig.getGs().getGeneralization().get(0).getGeneral();
		int i=0;
		for(Classifier c: gsrig.getSpecifics())
		{
			if(c instanceof AntiRigidMixinClass || c instanceof AntiRigidSortalClass)
			{	
				if(supertype instanceof Kind ||supertype instanceof SubKind || supertype instanceof Quantity || supertype instanceof Collective)
				{
					Combo combo2 = (Combo)table.getItems()[i].getData("3");
					combo2.select(3);
				}
				if(supertype instanceof Category){
					Combo combo2 = (Combo)table.getItems()[i].getData("3");
					combo2.select(7);	
				}			
			}
			i++;
		}
	}
	
	public void populatesAntiRigid()
	{
		Classifier supertype = gsrig.getGs().getGeneralization().get(0).getGeneral();
		int i=0;
		for(Classifier c: gsrig.getSpecifics())
		{
			if(c instanceof RigidMixinClass || c instanceof RigidSortalClass)
			{	
				if(supertype instanceof Kind ||supertype instanceof SubKind || supertype instanceof Quantity || supertype instanceof Collective)
				{
					Combo combo2 = (Combo)table.getItems()[i].getData("3");
					combo2.select(5);
				}
				if(supertype instanceof Category){
					Combo combo2 = (Combo)table.getItems()[i].getData("3");
					combo2.select(8);	
				}			
			}	
			i++;
		}
	}
	
	@SuppressWarnings("unused")
	public SubTypeTable (Composite parent, int args, GSRigOccurrence gsrig) 
	{		
		table = new Table(parent, args);
		
		this.gsrig = gsrig;
		
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		String columnName1 = "Subtype";
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(70);
		tableColumn1.setText(columnName1);
		
		String columnName2 = "Current Stereotype";
		TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
		tableColumn2.setWidth(120);
		tableColumn2.setText(columnName2);
		
		String columnName3 = "Current Rigidity";
		TableColumn tableColumn3 = new TableColumn(table, SWT.CENTER);
		tableColumn3.setWidth(100);
		tableColumn3.setText(columnName3);
		
		String columnName4 = "New Stereotype";
		TableColumn tableColumn4 = new TableColumn(table, SWT.CENTER);
		tableColumn4.setWidth(100);
		tableColumn4.setText(columnName4);
		
		String columnName5 = "New Rigidity";
		TableColumn tableColumn5 = new TableColumn(table, SWT.CENTER);
		tableColumn5.setWidth(90);
		tableColumn5.setText(columnName5);
			
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
		
		table.setSize(418, 117);
		
		fulfillLines();
		
		populatesRigid();
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public void fulfillLines(){

		for(Classifier c: gsrig.getSpecifics())
		{
			TableItem tableItem = new TableItem(table,SWT.NONE);	
						
			// Subtype
			TableEditor editor = new TableEditor(table);
		    editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			tableItem.setText(0, c.getName());
			
			// Current Stereotype
			editor = new TableEditor(table);
			final Combo combo = new Combo(table, SWT.READ_ONLY);
			combo.setItems(new String[] {"Kind", "Collective", "Quantity", "SubKind","Phase", "Role", "Mixin", "Category", "RoleMixin"});			
			combo.setText(getStereotype(c));	
			combo.setEnabled(false);
			combo.pack();
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(combo, tableItem, 1);
			tableItem.setData("1", combo);
			
			// Current Rigidity
			editor = new TableEditor(table);
			final Combo combo2 = new Combo(table, SWT.READ_ONLY);
			combo2.setItems(new String[] {"Rigid", "Anti-Rigid"});
			if (c instanceof AntiRigidMixinClass || c instanceof AntiRigidSortalClass) combo2.select(1);
			else combo2.select(0);	
			combo2.setEnabled(false);
			combo2.pack();
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(combo2, tableItem, 2);
			tableItem.setData("2", combo2);
			
			// New Stereotype
			editor = new TableEditor(table);
			final Combo combo3 = new Combo(table, SWT.NONE);
			combo3.setItems(new String[] {"Kind", "Collective", "Quantity", "SubKind","Phase", "Role", "Mixin", "Category", "RoleMixin"});
			combo3.select(0);
			combo3.pack();
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(combo3, tableItem, 3);
			tableItem.setData("3", combo3);
			newStereoCombo.add(combo3);
			
			// New Rigidity
			editor = new TableEditor(table);
			final Combo combo4 = new Combo(table, SWT.NONE);
			combo4.setItems(new String[] {"Rigid", "Anti-Rigid"});
			combo4.select(0);
			combo4.setEnabled(false);
			combo4.pack();
			editor.grabHorizontal = true;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(combo4, tableItem, 4);
			tableItem.setData("4", combo4);
			newRigidityCombo.add(combo4);
		
			SelectionAdapter listener = new SelectionAdapter() {
			      public void widgetSelected(SelectionEvent e) {
			    	  if (combo3.getText().compareToIgnoreCase("Role")==0||combo3.getText().compareToIgnoreCase("RoleMixin")==0||
			   			   combo3.getText().compareToIgnoreCase("Phase")==0||combo3.getText().compareToIgnoreCase("Mixin")==0)
			    	  {
			    		  combo4.select(1);
			    	  }else{
			    		  combo4.select(0);
			    	  }
			      }
			};
			combo3.addSelectionListener(listener);
		
				    
		}
	}

	public Table getTable() {
		return table;
	}

	public Boolean isNewStereotypesAllAntiRigid()
	{
		boolean result=true;
		for(String str: getNewStereotypes())
		{
			if(str.compareToIgnoreCase("Kind")==0 || str.compareToIgnoreCase("SubKind")==0 ||
			   str.compareToIgnoreCase("Quantity")==0 || str.compareToIgnoreCase("Collective")==0 ||
			   str.compareToIgnoreCase("Category")==0)
			{
				result = false;
			}				
		}
		return result;
	}
	
	public Boolean isNewStereotypesAllRigid()
	{
		boolean result=true;
		for(String str: getNewStereotypes())
		{
			if(str.compareToIgnoreCase("Role")==0 || str.compareToIgnoreCase("Phase")==0 ||
			   str.compareToIgnoreCase("RoleMixin")==0 || str.compareToIgnoreCase("Mixin")==0)			   
			{
				result = false;
			}				
		}
		return result;
	}
	
	public ArrayList<String> getAntiRigids()
	{
		ArrayList<String> result = new ArrayList<String>();		
		for (TableItem ti : table.getItems()){	
			Combo combo = (Combo) ti.getData("3");			
			if(combo.getText().compareToIgnoreCase("Role")==0||combo.getText().compareToIgnoreCase("RoleMixin")==0||
			   combo.getText().compareToIgnoreCase("Phase")==0||combo.getText().compareToIgnoreCase("Mixin")==0)
			{
				result.add(combo.getText()+" "+(ti.getText(0)));
			}
		}
		return result;
	}
	
	public ArrayList<String> getNewStereotypes()
	{
		ArrayList<String> result = new ArrayList<String>();		
		for (TableItem ti : table.getItems()){	
			Combo combo = (Combo) ti.getData("3");			
			result.add(combo.getText());
		}
		return result;	
	}
}