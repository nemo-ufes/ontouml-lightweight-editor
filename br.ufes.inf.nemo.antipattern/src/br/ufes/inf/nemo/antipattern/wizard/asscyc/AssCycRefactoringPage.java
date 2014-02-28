package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Association;
import RefOntoUML.NamedElement;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycAntipattern;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class AssCycRefactoringPage extends RefactoringPage {
	
	public AssCycOccurrence asscyc;
	private Label lblCreateOclDerivation;
	private Combo assocCombo;
	private Button btnEnforce;
	private Button btnForbid;
	private Composite composite_1;
	private Composite composite;
		
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
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);				
		
		composite = new Composite(container, SWT.BORDER);
		composite.setBounds(10, 80, 554, 73);
		
		lblCreateOclDerivation = new Label(composite, SWT.NONE);
		lblCreateOclDerivation.setText("Create OCL derivation to the following derived association:");
		lblCreateOclDerivation.setBounds(10, 10, 530, 16);
				
		assocCombo = new Combo(composite, SWT.READ_ONLY);
		assocCombo.setBounds(10, 32, 530, 23);		
		for(Relationship rel: asscyc.getCycleRelationship()){
			assocCombo.add(getStereotype(rel)+" "+((NamedElement)rel).getName()+": "+((Association)rel).getMemberEnd().get(0).getType().getName()+"->"+((Association)rel).getMemberEnd().get(1).getType().getName());
		}
		composite_1 = new Composite(container, SWT.BORDER);
		composite_1.setBounds(10, 10, 554, 64);
		
		btnEnforce = new Button(composite_1, SWT.RADIO);
		btnEnforce.setBounds(10, 10, 534, 16);
		btnEnforce.setText("Create OCL invariant enforcing the instance level cycle");
		
		btnForbid = new Button(composite_1, SWT.RADIO);
		btnForbid.setBounds(10, 32, 534, 16);
		btnForbid.setText("Create OCL invariant fobidding the instance level cycle");
	}

	@Override
	public IWizardPage getNextPage() 
	{
		((AssCycWizard)getWizard()).removeAllActions();
		
		if (assocCombo.getSelectionIndex()>=0)
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
