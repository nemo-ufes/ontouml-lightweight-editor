package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Classifier;
import RefOntoUML.Phase;

public class ModeTable {

	private ArrayList<Phase> phaseList = new ArrayList<Phase>();
	private Table table;
	
	@SuppressWarnings("unused")
	public ModeTable (Composite parent, int args, ArrayList<Phase> phaseList) 
	{		
		table = new Table(parent, args);
		
		this.phaseList=phaseList;
		table.setHeaderVisible(true);		
		table.setLinesVisible(true);
				
		String columnName1 = "Mode name";
		TableColumn tableColumn1 = new TableColumn(table, SWT.CENTER);
		tableColumn1.setWidth(91);
		tableColumn1.setText(columnName1);
		
		String columnName3 = "Mode cardinality";
		TableColumn tableColumn3 = new TableColumn(table, SWT.CENTER);
		tableColumn3.setWidth(119);
		tableColumn3.setText(columnName3);
		
		String columnName2 = "Which Phase";
		TableColumn tableColumn2 = new TableColumn(table, SWT.CENTER);
		tableColumn2.setWidth(102);
		tableColumn2.setText(columnName2);
		
		int tableWidth = 0;		
		for (TableColumn tc : table.getColumns()) {
			tableWidth+=tc.getWidth();
		}
			
		table.setSize(388, 129);		
	}		

	public void addNewMode(String name, String mult)
	{
		TableItem item = new TableItem(table, SWT.NONE);
		
		TableEditor editor = new TableEditor(table);
	    editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		item.setText(0,name);
		
		editor = new TableEditor(table);			
		editor.grabHorizontal = true;		
		editor.horizontalAlignment = SWT.CENTER;		

		item.setText(1,mult);
				
		//phase
		editor = new TableEditor(table);
		Combo combo = new Combo(table, SWT.READ_ONLY);
		for(Classifier c: phaseList)
		{
			combo.add("Phase "+c.getName());
		}		
		combo.select(0);
		combo.pack();
		editor.grabHorizontal = true;
		editor.horizontalAlignment = SWT.CENTER;
		editor.setEditor(combo, item, 2);
		item.setData("2", combo);
					
		getTable().select(getTable().getItemCount()-1);
	}
	
	public void removeLine()
	{
		if(getTable().getSelectionIndex()>=0) {	
			int prev = getTable().getSelectionIndex()-1;				
			getTable().remove(getTable().getSelectionIndices());					
			getTable().select(prev);		
			getTable().setRedraw(true);
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
	
	public Table getTable() {
		return table;
	}

	public HashMap<String,String> getValues()
	{
		HashMap<String,String> map = new HashMap<String,String>();
		for (TableItem ti : table.getItems()){	
			String name = ti.getText(0);
			String cardinality = ti.getText(1);			
			if (name!= null && !name.isEmpty() && cardinality!=null && !cardinality.isEmpty()){
					map.put(name,cardinality);	
			}
		}
		return map;
	}
	
	public Classifier getPhase(String name)
	{		
		for(Classifier p: phaseList){
			if(p.getName().compareToIgnoreCase(name)==0) return p;			
		}
		return null;		
	}
	
	public ArrayList<Classifier> getPhases()
	{
		ArrayList<Classifier> map = new ArrayList<Classifier>();
		for (TableItem ti : table.getItems()){			
			Combo phaseCombo = (Combo)ti.getData("2");			
			if (phaseCombo.getText()!=null && !phaseCombo.getText().isEmpty()){	
				String name = phaseCombo.getText().replace("Phase ","");
				Classifier c = getPhase(name);
				if(c!=null)map.add(c);				
			}
		}
		return map;
	}	
}