package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingTypesVariation;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class OverlappingRefactoringPage extends RefactoringPage {

	private OverlappingOccurrence occurrence;
	private ArrayList<OverlappingCheckBoxTableBuilder> exclusiveBuilderList;
	private ArrayList<OverlappingCheckBoxTableBuilder> disjointBuilderList;

	/**
	 * Create the wizard.
	 */
	public OverlappingRefactoringPage(OverlappingOccurrence occurrence) {
		super();
		
		this.occurrence = occurrence;
		exclusiveBuilderList = new ArrayList<OverlappingCheckBoxTableBuilder>();
		disjointBuilderList = new ArrayList<OverlappingCheckBoxTableBuilder>();
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		setControl(sc);
		sc.setBounds(10, 60, 554, 212);
		
		Composite composite = new Composite(sc, SWT.NONE);

		lblChooseTheAppropriate = new Label(composite, SWT.NONE);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring options:");
		lblChooseTheAppropriate.setBounds(10, 10, 300, 25);
		
		int tableVerticalPosition = 70;
		int tableVerticalSpacing = 110;
		
		for (int i = 0; i < occurrence.getVariations().size(); i++) {
			try {
				createComponents(composite, tableVerticalPosition, i, 0);
				tableVerticalPosition += tableVerticalSpacing;
				createComponents(composite, tableVerticalPosition, i, 1);
				tableVerticalPosition += tableVerticalSpacing;
			} catch (Exception e) {	e.printStackTrace(); }
		}
		
		lblnoteThatSome = new Label(composite, SWT.NONE);
		lblnoteThatSome.setAlignment(SWT.LEFT);
		lblnoteThatSome.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblnoteThatSome.setText("(Note that some options may be incompatible)");
		lblnoteThatSome.setBounds(10,tableVerticalPosition-31, 300, 25);
		
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));  
	}

	//actiontype = 0 -> disjoint, actiontype = 1->exclusive
	private void createComponents(Composite container, int tableYPosition, int variationIndex, int actionType) throws Exception {
		
		OverlappingTypesVariation currentVariation = occurrence.getVariations().get(variationIndex);
		int addLineButtonYPosition = tableYPosition - 31;
		int tableTitleLabelYPosition = tableYPosition - 21;
		OverlappingCheckBoxTableBuilder builder;
		
		if (actionType==0){
			String tableTitle = "Group "+(variationIndex+1)+": select the DISJOINT types";
			builder = new OverlappingCheckBoxTableBuilder(container,SWT.BORDER, currentVariation, tableTitle, variationIndex);
			disjointBuilderList.add(builder);
		}
		else{
			String tableTitle = "Group "+(variationIndex+1)+": select the EXCLUSIVE types";
			builder = new OverlappingCheckBoxTableBuilder(container,SWT.BORDER, currentVariation, tableTitle, variationIndex);
			exclusiveBuilderList.add(builder);
		}
		
		int tableHeight = 70;
		
		Label label = builder.getLabel();
		label.setBounds(10, tableTitleLabelYPosition, 300, 15);
		
		Table table = builder.getTable();
		table.setBounds(10, tableYPosition, 554, tableHeight);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		Button btnAddLine = builder.getButton();
		btnAddLine.setBounds(489, addLineButtonYPosition, 75, 25);
		btnAddLine.setText("Add Line");
	}
	
	public OverlappingOccurrence getOccurrence() {
		return occurrence;
	}

	public ArrayList<OverlappingCheckBoxTableBuilder> getBuilderList() {
		return exclusiveBuilderList;
	}
	
	public ArrayList<ArrayList<Property>> getAllDisjointSelections(){
		
		ArrayList<ArrayList<Property>> selections = new ArrayList<ArrayList<Property>>();
		
		for (OverlappingCheckBoxTableBuilder builder : disjointBuilderList) {
			selections.addAll(builder.getSelections());
		} 
		
		return selections;
	}
	
	public ArrayList<ArrayList<Property>> getAllExclusiveSelections(){
		
		ArrayList<ArrayList<Property>> selections = new ArrayList<ArrayList<Property>>();
		
		for (OverlappingCheckBoxTableBuilder builder : exclusiveBuilderList) {
			selections.addAll(builder.getSelections());
		} 
		
		return selections;
	}
	
	@Override
	public IWizardPage getNextPage(){
		if(getWizard() instanceof AntipatternWizard){
			AntipatternWizard wizard = (AntipatternWizard)getWizard();
			
			wizard.removeAllActions();
			
			for (OverlappingCheckBoxTableBuilder builder : exclusiveBuilderList) {
				for (ArrayList<Property> properties : builder.getSelections()) {
					OverlappingAction action = new OverlappingAction(occurrence,builder.getVariation());
					action.setExclusive(properties);
					wizard.addAction(builder.getVariationIndex(), action);
				}
			}
			
			for (OverlappingCheckBoxTableBuilder builder : disjointBuilderList) {
				for (ArrayList<Property> properties : builder.getSelections()) {
					OverlappingAction action = new OverlappingAction(occurrence,builder.getVariation());
					action.setDisjoint(properties);
					wizard.addAction(builder.getVariationIndex(), action);
				}
			}
		
			return wizard.getFinishing();
		}
		return null;
	}

}
