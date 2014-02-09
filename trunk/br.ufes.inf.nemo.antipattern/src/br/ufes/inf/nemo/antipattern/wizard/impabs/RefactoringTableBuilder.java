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

public class RefactoringTableBuilder extends ImpAbsTableBuilder<RefactoringLine>{
	
	public RefactoringTableBuilder (Composite parent, int args, ImpAbsOccurrence occurrence, int columnStdWidth) throws Exception{
		super(parent,args,occurrence,columnStdWidth,columnNamesList(occurrence.getAssociation()));
	}
	
	@Override
	public void addLine(){
	
		if(occurrence.getAssociation() instanceof Meronymic)
			addMeronymicLine();
		else
			addRegularLine();
		
		System.out.println("\n\nLines...");
		for (RefactoringLine line : getLines()) {
			System.out.println("Source: "+line.getSource().getName()+", Target: "+line.getTarget().getName());
		}
	}
	
	private void addMeronymicLine(){
		
		TableItem tableItem = new TableItem(table,SWT.NONE);

		insertTypeCombo(tableItem, occurrence.getSource(), 0);
		insertMultiplicityCombo(tableItem, 1);
		insertTrueFalseCombo(tableItem, 2);
		
		insertTypeCombo(tableItem, occurrence.getTarget(), 3);
		insertMultiplicityCombo(tableItem, 4);
		insertTrueFalseCombo(tableItem, 5);
		
		for (int i = 6; i < columnNames.size(); i++)
			insertTrueFalseCombo(tableItem, i);
	}
	
	private void addRegularLine(){
		
		TableItem tableItem = new TableItem(table,SWT.NONE);

		insertTypeCombo(tableItem, occurrence.getSource(), 0);
		insertMultiplicityCombo(tableItem, 1);
		insertTrueFalseCombo(tableItem, 2);
		insertTrueFalseCombo(tableItem, 3);
		
		insertTypeCombo(tableItem, occurrence.getTarget(), 4);
		insertMultiplicityCombo(tableItem, 5);
		insertTrueFalseCombo(tableItem, 6);
		insertTrueFalseCombo(tableItem, 7);
	}

	@Override
	protected RefactoringLine getLine(TableItem ti) throws Exception{
		
		if(occurrence.getAssociation() instanceof Meronymic)
			return getMeronymicLine(ti);
		else
			return getRegularLine(ti);
		
	}
	
	private RefactoringLine getMeronymicLine(TableItem ti) throws Exception{

		CCombo 	comboSource = (CCombo) ti.getData(columnNames.get(0)),
				comboTarget = (CCombo) ti.getData(columnNames.get(3)),
				comboMultSource = (CCombo) ti.getData(columnNames.get(1)),
				comboMultTarget = (CCombo) ti.getData(columnNames.get(4)),
				trueFalseCombo;
				
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
		
		trueFalseCombo = (CCombo) ti.getData(columnNames.get(2));
		hashMeta.put(columnNames.get(2),trueFalseCombo.getSelectionIndex()==0);
		trueFalseCombo = (CCombo) ti.getData(columnNames.get(5));
		hashMeta.put(columnNames.get(5),trueFalseCombo.getSelectionIndex()==0);
		
		for (int i = 6; i < columnNames.size(); i++) {
			trueFalseCombo = (CCombo) ti.getData(columnNames.get(i));
			hashMeta.put(columnNames.get(i),trueFalseCombo.getSelectionIndex()==0);
		}
		
		return new RefactoringLine(source, target, comboMultSource.getText(), comboMultTarget.getText(), hashMeta); 
	}
	
	private RefactoringLine getRegularLine(TableItem ti) throws Exception{
		CCombo 	comboSource = (CCombo) ti.getData(columnNames.get(0)),
				comboMultSource = (CCombo) ti.getData(columnNames.get(1)),
				comboIsReadOnlySource = (CCombo) ti.getData(columnNames.get(2)),
				comboIsDerivedSource = (CCombo) ti.getData(columnNames.get(3)),
				
				comboTarget = (CCombo) ti.getData(columnNames.get(4)),
				comboMultTarget = (CCombo) ti.getData(columnNames.get(5)),
				comboIsReadOnlyTarget = (CCombo) ti.getData(columnNames.get(6)),
				comboIsDerivedTarget = (CCombo) ti.getData(columnNames.get(7));
				
		HashMap<String,Boolean> hashMeta = new HashMap<String,Boolean>();
		Classifier source, target;
		
		if(comboSource.getSelectionIndex()==0)
			source = occurrence.getSource();
		else if (comboSource.getSelectionIndex()>=1)
			source = occurrence.getSourceChildren().get(comboSource.getSelectionIndex()-1);
		else
			source = null;
		
		hashMeta.put(columnNames.get(2),comboIsReadOnlySource.getSelectionIndex()==0);;
		hashMeta.put(columnNames.get(3),comboIsDerivedSource.getSelectionIndex()==0);
		
		if(comboTarget.getSelectionIndex()==0)
			target = occurrence.getTarget();
		else if(comboTarget.getSelectionIndex()>=1)
			target = occurrence.getTargetChildren().get(comboTarget.getSelectionIndex()-1);
		else
			target = null;
		
		hashMeta.put(columnNames.get(6),comboIsReadOnlyTarget.getSelectionIndex()==0);;
		hashMeta.put(columnNames.get(7),comboIsDerivedTarget.getSelectionIndex()==0);

		return new RefactoringLine(source, target, comboMultSource.getText(), comboMultTarget.getText(), hashMeta); 
	}

	private static ArrayList<String> columnNamesList(Association a) {
		ArrayList<String> columns = new ArrayList<String>();
		
		if(a instanceof Meronymic){
			columns.add("Whole"); //0
			columns.add("Whole Multiplicity"); //1
			columns.add("Whole isDerived?"); //2
			columns.add("Part"); //3
			columns.add("Part Multiplicity"); //4
			columns.add("Part isDerived?"); //5
			columns.add("isEssential?"); //6
			columns.add("isImmutablePart?"); //7
			columns.add("isInseparable?"); //8
			columns.add("isImmutableWhole?"); //9
			columns.add("isShareable"); //10
		}
		
		else{
			columns.add("Source"); //0
			columns.add("Source Multiplicity"); //1
			columns.add("Source isReadOnly"); //2
			columns.add("Source isDerived?"); //3
			columns.add("Target"); //4
			columns.add("Target Multiplicity"); //5
			columns.add("Target isReadOnly"); //6
			columns.add("Target isDerived?"); //7
		}
		
		return columns;
	}
}
