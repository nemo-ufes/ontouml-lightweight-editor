package br.ufes.inf.nemo.antipattern.wizard.relspec;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecAntipattern;
import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecRefactoringPage extends RefactoringPage {
	
	protected RelSpecOccurrence relSpec;
	
	private Button btnSubsets;
	private Button btnRedefine;
	private Button btnDisjoint;
	private Button btnNothing;
	private Button btnSpecializeRedefine;
	private Button btnDelete;
	private CCombo comboDelete;
	private CCombo comboSpecializeRedefine;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecRefactoringPage(RelSpecOccurrence relSpec) 
	{
		super();	
		this.relSpec = relSpec;
				
		setTitle(RelSpecAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public RelSpecWizard getRelSpecWizard(){
		return (RelSpecWizard)getWizard();
	}
	
	class RadioButtonListener {
		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		Label lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring option:");
		lblChooseTheAppropriate.setBounds(10, 10, 554, 15);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
		    
	    setPageComplete(false);
	    
		btnSubsets = new Button(container, SWT.RADIO);
		btnSubsets.setText(relSpec.getParser().getStereotype(relSpec.getSpecific())+" subsets "+relSpec.getParser().getStereotype(relSpec.getGeneral()));
		btnSubsets.setBounds(10, 40, 449, 16);
		btnSubsets.addSelectionListener(listener);
		
		btnRedefine = new Button(container, SWT.RADIO);
		btnRedefine.setText(relSpec.getParser().getStereotype(relSpec.getSpecific())+" redefines "+relSpec.getParser().getStereotype(relSpec.getGeneral()));
		btnRedefine.setBounds(10, 65, 449, 16);
		btnRedefine.addSelectionListener(listener);
		
		btnDisjoint = new Button(container, SWT.RADIO);
		btnDisjoint.setText(relSpec.getParser().getStereotype(relSpec.getSpecific())+" is disjoint from "+relSpec.getParser().getStereotype(relSpec.getGeneral()));
		btnDisjoint.setBounds(10, 90, 449, 16);
		btnDisjoint.addSelectionListener(listener);
		
		btnNothing = new Button(container, SWT.RADIO);
		btnNothing.setText("Do nothing");
		btnNothing.setBounds(10, 115, 169, 16);
		btnNothing.addSelectionListener(listener);
		
		if (relSpec.isVariation4() || relSpec.isVariation5()) 
		{
			btnSpecializeRedefine = new Button(container, SWT.RADIO);
			btnSpecializeRedefine.setText("Specialize ends and redefine");
			btnSpecializeRedefine.setBounds(10, 140, 169, 16);
			btnSpecializeRedefine.addSelectionListener(listener);
			
			btnDelete = new Button(container, SWT.RADIO);
			btnDelete.setText("Delete one of the associations");
			btnDelete.setBounds(10, 165, 178, 16);
			btnDelete.addSelectionListener(listener);
			
			comboSpecializeRedefine = new CCombo(container, SWT.BORDER);
			comboSpecializeRedefine.setItems(new String[] {	relSpec.getParser().getStringRepresentation(relSpec.getSpecificSource())+" ("+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+" source end)", 
											relSpec.getParser().getStringRepresentation(relSpec.getSpecificTarget())+" ("+relSpec.getParser().getStringRepresentation(relSpec.getSpecific())+" target end)",
											"Both ends of "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific()), 
											relSpec.getParser().getStringRepresentation(relSpec.getGeneralSource())+" ("+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+" source end)", 
											relSpec.getParser().getStringRepresentation(relSpec.getGeneralTarget())+" ("+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+" target end)",
											"Both ends of "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())});
			
			comboSpecializeRedefine.setBounds(194, 135, 216, 21);
						
			comboDelete = new CCombo(container, SWT.BORDER);
			comboDelete.setItems(new String[] {	relSpec.getParser().getStringRepresentation(relSpec.getGeneral()), 
												relSpec.getParser().getStringRepresentation(relSpec.getSpecific())});
			comboDelete.setBounds(194, 160, 216, 21);
		}
		else {
			btnSpecializeRedefine = null;
			btnDelete = null;
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

	@Override
	public IWizardPage getNextPage() {
		getRelSpecWizard().removeAllActions(0);
		RelSpecAction newAction = new RelSpecAction(relSpec);
		if (btnSubsets.getSelection()){
			// Action =====================
			newAction.setSubset();
			getRelSpecWizard().replaceAction(0,newAction);
		}
		if (btnRedefine.getSelection()){
			// Action =====================
			newAction.setRedefine();			
			getRelSpecWizard().replaceAction(0,newAction);
		}
		if (btnDisjoint.getSelection()){
			// Action =====================
			newAction.setDisjoint();
			getRelSpecWizard().replaceAction(0,newAction);
		}
		
		if (btnSpecializeRedefine!=null && btnSpecializeRedefine.getSelection()){
			ClassStereotype source = ClassStereotype.ROLE;
			ClassStereotype target = ClassStereotype.ROLE;
			
			if(comboSpecializeRedefine.getSelectionIndex()==0) {
				// Action =====================
				newAction.setSpec_Specific_Source_Redefine(source);		
			}
			if(comboSpecializeRedefine.getSelectionIndex()==1) {
				// Action =====================
				newAction.setSpec_Specific_Target_Redefine(target);
			}
			if(comboSpecializeRedefine.getSelectionIndex()==2) {
				// Action =====================
				newAction.setSpec_Specific_Both_Redefine(source,target);
			}
			if(comboSpecializeRedefine.getSelectionIndex()==3) {
				// Action =====================
				newAction.setSpec_General_Source_Redefine(source);	
			}
			if(comboSpecializeRedefine.getSelectionIndex()==4) {
				// Action =====================
				newAction.setSpec_General_Target_Redefine(target);
			}
			if(comboSpecializeRedefine.getSelectionIndex()==5) {
				// Action =====================
				newAction.setSpec_General_Both_Redefine(source,target);			
			}			
			getRelSpecWizard().replaceAction(0,newAction);
		}
		
		if (btnDelete!=null && btnDelete.getSelection()){
			if(comboDelete.getSelectionIndex()==0){
				//ACTION
				newAction.setDeleteGeneral();
			}
			if(comboDelete.getSelectionIndex()==1){
				//ACTION
				newAction.setDeleteSpecific();
			}
			getRelSpecWizard().replaceAction(0,newAction);
		}				
		if(btnNothing.getSelection()){
			getRelSpecWizard().removeAllActions();
		}
	
		return 	getRelSpecWizard().getFinishing();				
	}
}
