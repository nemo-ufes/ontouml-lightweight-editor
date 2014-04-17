package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Association;
import RefOntoUML.NamedElement;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class AssCycRefactoringPage extends RefactoringPage {
	
	public AssCycOccurrence asscyc;
	private Label lblCreateOclDerivation;
	private Combo assocCombo;
	private Button btnEnforce;
	private Button btnForbid;
	private Button btnDerive;
		
	/**
	 * Create the wizard.
	 */
	public AssCycRefactoringPage(AssCycOccurrence asscyc) 
	{
		super();	
		this.asscyc = asscyc;
		
		setTitle(AssCycAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public AssCycWizard getAssCycWizard(){
		return (AssCycWizard)getWizard();
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	SelectionAdapter enableNextPageListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			if(btnDerive.getSelection())
				assocCombo.setEnabled(true);
			else
				assocCombo.setEnabled(false);
			
			if((btnDerive.getSelection() && assocCombo.getSelectionIndex()!=-1)|| btnForbid.getSelection() || btnEnforce.getSelection()){
				if(!isPageComplete())
					setPageComplete(true);
			}
			else{
				if(isPageComplete())
					setPageComplete(false);
			}
		}
	};
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);

		lblCreateOclDerivation = new Label(container, SWT.NONE);
		lblCreateOclDerivation.setBounds(10, 119, 554, 16);
		lblCreateOclDerivation.setText("Please, choose the association to be derived:");
		
		assocCombo = new Combo(container, SWT.READ_ONLY);
		assocCombo.setBounds(10, 141, 554, 23);
		assocCombo.addSelectionListener(enableNextPageListener);
		
		for(Relationship rel: asscyc.getCycleRelationship()){
			assocCombo.add(getStereotype(rel)+" "+((NamedElement)rel).getName()+": "+((Association)rel).getMemberEnd().get(0).getType().getName()+"->"+((Association)rel).getMemberEnd().get(1).getType().getName());
		}
		
		btnDerive = new Button(container, SWT.RADIO);
		btnDerive.setBounds(10, 87, 554, 16);
		btnDerive.setText("Create OCL derivation rule for one of the associations");
		btnDerive.addSelectionListener(enableNextPageListener);
		
		btnForbid = new Button(container, SWT.RADIO);
		btnForbid.setBounds(10, 65, 554, 16);
		btnForbid.setText("Create OCL invariant FORBIDDING the instance level cycle");
		btnForbid.addSelectionListener(enableNextPageListener);
		
		btnEnforce = new Button(container, SWT.RADIO);
		btnEnforce.setBounds(10, 43, 554, 16);
		btnEnforce.setText("Create OCL invariant ENFORCING the instance level cycle");
		btnEnforce.addSelectionListener(enableNextPageListener);
		
		Label lblPleaseChooseWhich = new Label(container, SWT.NONE);
		lblPleaseChooseWhich.setBounds(10, 10, 554, 15);
		lblPleaseChooseWhich.setText("Please choose which action to perform on the model:");
		
		setPageComplete(false);
		assocCombo.setEnabled(false);
	}

	@Override
	public IWizardPage getNextPage() 
	{
		((AssCycWizard)getWizard()).removeAllActions();
		
		if (btnDerive.getSelection() && assocCombo.getSelectionIndex()>=0)
		{
			Association assoc = (Association)asscyc.getCycleRelationship().get(assocCombo.getSelectionIndex());
			//Action =============================
			AssCycAction newAction = new AssCycAction(asscyc);
			newAction.setDeriveAssociation(assoc); 
			getAssCycWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnEnforce.getSelection())
		{
			//Action =============================
			AssCycAction newAction = new AssCycAction(asscyc);
			newAction.setCycleMandatory();
			getAssCycWizard().replaceAction(1,newAction);	
			//======================================	
		}
		if(btnForbid.getSelection())
		{
			//Action =============================
			AssCycAction newAction = new AssCycAction(asscyc);
			newAction.setCycleForbidden(); 
			getAssCycWizard().replaceAction(1,newAction);	
			//======================================
		}
		
		return ((AssCycWizard)getWizard()).getFinishing();
	}
}
