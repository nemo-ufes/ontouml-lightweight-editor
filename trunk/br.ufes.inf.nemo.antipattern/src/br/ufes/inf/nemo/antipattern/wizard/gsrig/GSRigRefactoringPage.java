package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class GSRigRefactoringPage extends RefactoringPage {
	
	public GSRigOccurrence gsrig;
	private List list;

	/**
	 * Create the wizard.
	 */
	public GSRigRefactoringPage(GSRigOccurrence gsrig) 
	{
		super();	
		this.gsrig = gsrig;
		
		setTitle(GSRigAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public GSRigWizard getGSRigWizard(){
		return (GSRigWizard)getWizard();
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parentss
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		list = new List(container, SWT.BORDER | SWT.V_SCROLL);
		list.setItems(new String[] {
				"do nothing", 
				"Delete the generalization set", 
				"Create new generalization set for rigid types", 
				"Create new generalization set for anti-rigid types", 
				"Create both generalization set for rigid and anti-rgid types", 
				"Change supertype to Mixin",  
				"Create a new Mixin supertype for all subtypes",
				"Change supertype and rigid subtypes stereotypes to anti-rigid",   
				"Create a rigid subtype for each anti-rigid"});
		list.select(0);
		list.setBounds(10, 10, 644, 96);
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	@SuppressWarnings("unused")
	@Override
	public IWizardPage getNextPage() 
	{
		((GSRigWizard)getWizard()).removeAllActions();
		
		if (list.getSelectionIndex()==0){
			// do nothing
		}
		if(list.getSelectionIndex()==1){		
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setDeleteGS(); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================		
		}
		if(list.getSelectionIndex()==2){		
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
		}
		if(list.getSelectionIndex()==3){		
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForAntiRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
		}
		if(list.getSelectionIndex()==4){		
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateGSForBoth(); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==5){		
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setChangeSuperTypeToMixin(); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==6){		
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateMixinSupertype(); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==7){		
			Classifier supertype = gsrig.getGs().getGeneralization().get(0).getGeneral();
			boolean createNewSupertype = false;
			if (supertype instanceof Kind || supertype instanceof Quantity || supertype instanceof Collective){
				createNewSupertype = true;
			}
			//Action =============================
//			GSRigAction newAction = new GSRigAction(gsrig);
//			newAction.setChangeSupertypeAndRigidSpecificsStereotypesTo(stereoCombo.getText(), rigidTableComposite.getRigidTable().getNewStereotypes(), createNewSupertype); 
//			getGSRigWizard().replaceAction(0,newAction);
			//======================================			
		}
		return ((GSRigWizard)getWizard()).getFinishing();	
	}
}

	