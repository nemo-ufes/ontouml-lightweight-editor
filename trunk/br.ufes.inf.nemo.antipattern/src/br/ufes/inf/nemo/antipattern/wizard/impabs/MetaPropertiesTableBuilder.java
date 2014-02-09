package br.ufes.inf.nemo.antipattern.wizard.impabs;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;

public class MetaPropertiesTableBuilder extends ImpAbsTableBuilder<MetaPropertyLine>{
		
	public MetaPropertiesTableBuilder (Composite parent, int args, ImpAbsOccurrence occurrence, int columnStdWidth) throws Exception{
		super(parent,args,occurrence,columnStdWidth,columnNamesList(occurrence.getAssociation()));
	}
	
	public MetaPropertiesTableBuilder (Composite parent, int args, ImpAbsOccurrence occurrence, int columnStdWidth, ArrayList<String> columnNames) throws Exception{
		super(parent,args,occurrence,columnStdWidth,columnNamesList(occurrence.getAssociation(), columnNames));
	}
	
	@Override
	public void addLine(){
	
		TableItem tableItem = new TableItem(table,SWT.NONE);

		insertTypeCombo(tableItem, occurrence.getSource(), 0);
		insertTypeCombo(tableItem, occurrence.getTarget(), 1);
		
		for (int i = 2; i < columnNames.size(); i++)
			insertTrueFalseCombo(tableItem, i);
		
		System.out.println("\n\nLines...");
		for (MetaPropertyLine line : getLines()) {
			System.out.println("Source: "+line.getSource().getName()+", Target: "+line.getTarget().getName());
		}

	}

	@Override
	protected MetaPropertyLine getLine(TableItem ti) throws Exception{
		
		CCombo 	comboSource = (CCombo) ti.getData(columnNames.get(0)),
				comboTarget = (CCombo) ti.getData(columnNames.get(1));
		
		HashMap<String,Boolean> hashMeta = new HashMap<String,Boolean>();
		Classifier source, target;
		
		if(comboSource.getSelectionIndex()==0)
			source = occurrence.getSource();
		else if (comboSource.getSelectionIndex()>=1)
			source = occurrence.getSourceChildren().get(comboSource.getSelectionIndex()-1);
		else
			source = null;
		
		if(comboTarget.getSelectionIndex()==0)
			target = occurrence.getTarget();
		else if(comboTarget.getSelectionIndex()>=1)
			target = occurrence.getTargetChildren().get(comboTarget.getSelectionIndex()-1);
		else
			target = null;
		
		for (int i = 2; i < columnNames.size(); i++) {
			CCombo trueFalseCombo = (CCombo) ti.getData(columnNames.get(i));
			hashMeta.put(columnNames.get(i),trueFalseCombo.getSelectionIndex()==0);
		}
		
		return new MetaPropertyLine(source, target, hashMeta);
	}


	private static ArrayList<String> columnNamesList(Association a) {
		ArrayList<String> columns = new ArrayList<String>();
		
		if(a instanceof Meronymic){
			columns.add("Whole");
			columns.add("Part");
			columns.add("isEssential?");
			columns.add("isInseparable?");
			columns.add("isImmutablePart?");
			columns.add("isImmutableWhole?");
			columns.add("isShareable");
		}
		
		else{
			columns.add("Source");
			columns.add("Target");
			columns.add("Source isReadOnly");
			columns.add("Target isReadOnly");
		}
		
		columns.add("Source isDerived?");
		columns.add("Target isDerived?");
		
		return columns;
	}
	
	
	private static ArrayList<String> columnNamesList(Association a, ArrayList<String> columnNames) {
		ArrayList<String> columns = new ArrayList<String>();
		
		if(a instanceof Meronymic){
			columns.add("Whole");
			columns.add("Part");
		}
		
		else{
			columns.add("Source");
			columns.add("Target");
		}
		
		columns.addAll(columnNames);
		
		return columns;
	}
}
