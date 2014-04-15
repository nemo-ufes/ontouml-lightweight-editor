package br.ufes.inf.nemo.antipattern.wizard.impabs;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;

public class MultiplicityTableBuilder extends ImpAbsTableBuilder<MultiplicityLine>{

	public MultiplicityTableBuilder (Composite parent, int args, ImpAbsOccurrence occurrence, int columnStdWidth) throws Exception{		 
		super(parent, args, occurrence,columnStdWidth,columnNamesList());
		int i=0;
		for (TableColumn column : table.getColumns()) {
			if(i%2==0) column.setWidth(250);
			else  column.setWidth(150);
			i++;	
		}
	}
	
	@Override
	public void addLine(){
	
		TableItem tableItem = new TableItem(table,SWT.NONE);
		
		insertTypeCombo(tableItem, occurrence.getSource(), 0);
		insertMultiplicityCombo(tableItem, 1);
		insertTypeCombo(tableItem, occurrence.getTarget(), 2);
		insertMultiplicityCombo(tableItem, 3);
		
		System.out.println("\n\nLines...");
		for (MultiplicityLine line : getLines()) {
			System.out.println("Source: "+line.getSource().getName()+", lower: "+line.getLowerSource()+", upper: "+line.getUpperSource());
			System.out.println("Target: "+line.getTarget().getName()+", lower: "+line.getLowerTarget()+", upper: "+line.getUpperTarget());	
		}

	}

	@Override	
	protected MultiplicityLine getLine(TableItem ti) throws Exception {
		
		CCombo 	comboSource = (CCombo) ti.getData(columnNames.get(0)),
				comboMultSource = (CCombo) ti.getData(columnNames.get(1)),
				comboTarget = (CCombo) ti.getData(columnNames.get(2)),
				comboMultTarget = (CCombo) ti.getData(columnNames.get(3));
		
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
		
		return new MultiplicityLine(source, target, comboMultSource.getText(), comboMultTarget.getText());
	}

	public static ArrayList<String>  columnNamesList() {
		ArrayList<String> coulmns = new ArrayList<String>();
		coulmns.add("Source Type");
		coulmns.add("Source Multiplicity");
		coulmns.add("Target Type");
		coulmns.add("Target Multiplicity");
		return coulmns;
	}	 
}

