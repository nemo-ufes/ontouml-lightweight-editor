package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class OverlappingRefactoringPage extends RefactoringPage {

	private OverlappingOccurrence occurrence;
	private ArrayList<OverlappingGroupComposite> exclusiveCompositeList;
	private ArrayList<OverlappingGroupComposite> disjointCompositeList;

	/**
	 * Create the wizard.
	 */
	public OverlappingRefactoringPage(OverlappingOccurrence occurrence) {
		super();
		
		this.occurrence = occurrence;
		exclusiveCompositeList = new ArrayList<OverlappingGroupComposite>();
		disjointCompositeList = new ArrayList<OverlappingGroupComposite>();
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.H_SCROLL | SWT.V_SCROLL);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		
		setControl(sc);
		
		Composite composite = new Composite(sc, SWT.NONE);
		composite.setLayout(new GridLayout(1, false));
		
		Label lblInstruction = new Label(composite, SWT.NONE);
		GridData gd_lblInstruction = new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1);
		gd_lblInstruction.widthHint = 554;
		lblInstruction.setLayoutData(gd_lblInstruction);
		lblInstruction.setText("Please choose the appropriate refactoring options:");		
				
		for (int i = 0; i < occurrence.getGroups().size(); i++) {
			createComponents(composite, i, 0);
			createComponents(composite, i, 1);
		}
		
		for (int i = 0; i < occurrence.getGroups().size(); i++) {
			GridData gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
			disjointCompositeList.get(i).setLayoutData(gridData);
			
			gridData = new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1);
			exclusiveCompositeList.get(i).setLayoutData(gridData);
		}
		
		Label lblWarning = new Label(composite, SWT.RIGHT);
		GridData gd_lblWarning = new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1);
		gd_lblWarning.widthHint = 554;
		lblWarning.setLayoutData(gd_lblWarning);
		lblWarning.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblWarning.setText("Note that some options may be incompatible");
		
		sc.setContent(composite);
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		sc.setSize(100, 100);
		
	}

	//actiontype = 0 -> disjoint, actiontype = 1->exclusive
	private void createComponents(Composite container, int variationIndex, int actionType){
		
		OverlappingGroup currentVariation = occurrence.getGroups().get(variationIndex);
		OverlappingGroupComposite builder;
		
		if (actionType==0){
			String tableTitle = "Group "+(variationIndex+1)+": select the DISJOINT types";
			builder = new OverlappingGroupComposite(container,SWT.NONE, currentVariation, tableTitle, variationIndex);
			disjointCompositeList.add(builder);
		}
		else{
			String tableTitle = "Group "+(variationIndex+1)+": select the EXCLUSIVE types";
			builder = new OverlappingGroupComposite(container,SWT.NONE, currentVariation, tableTitle, variationIndex);
			exclusiveCompositeList.add(builder);
		}
	}
	
	public OverlappingOccurrence getOccurrence() {
		return occurrence;
	}

	public ArrayList<OverlappingGroupComposite> getBuilderList() {
		return exclusiveCompositeList;
	}
	
	public ArrayList<ArrayList<Property>> getAllDisjointSelections(){
		
		ArrayList<ArrayList<Property>> selections = new ArrayList<ArrayList<Property>>();
		
		for (OverlappingGroupComposite builder : disjointCompositeList) {
			selections.addAll(builder.getSelections());
		} 
		
		return selections;
	}
	
	public ArrayList<ArrayList<Property>> getAllExclusiveSelections(){
		
		ArrayList<ArrayList<Property>> selections = new ArrayList<ArrayList<Property>>();
		
		for (OverlappingGroupComposite builder : exclusiveCompositeList) {
			selections.addAll(builder.getSelections());
		} 
		
		return selections;
	}
	
	@Override
	public IWizardPage getNextPage(){
		if(getWizard() instanceof AntipatternWizard){
			AntipatternWizard wizard = (AntipatternWizard)getWizard();
			
			wizard.removeAllActions();
			
			for (OverlappingGroupComposite builder : exclusiveCompositeList) {
				for (ArrayList<Property> properties : builder.getSelections()) {
					OverlappingAction action = new OverlappingAction(occurrence,builder.getVariation());
					action.setExclusive(properties);
					wizard.addAction(builder.getVariationIndex(), action);
				}
			}
			
			for (OverlappingGroupComposite builder : disjointCompositeList) {
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
