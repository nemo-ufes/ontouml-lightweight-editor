package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class UndefFormalRefactoringPage extends RefactoringPage {
	
	protected UndefFormalOccurrence uf;
	public List list;
	
	/**
	 * Create the wizard.
	 */
	public UndefFormalRefactoringPage(UndefFormalOccurrence uf) 
	{
		super();	
		this.uf = uf;
				
		setTitle(RepRelAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the Formal relationship: "+uf.getSource().getName()+" -> "+uf.getTarget().getName());
	}

	public UndefFormalWizard getUndefFormalWizard(){
		return ( UndefFormalWizard)getWizard();
	}	
	
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		list = new List(container, SWT.V_SCROLL | SWT.BORDER);
		list.setItems(new String[] {
			"Do nothing", 
			"change to Mediation", 
			"change to Characterization with "+uf.getSource().getName()+" as source", 
			"change to Characterization with "+uf.getSource().getName()+" as target", 
			"change to Material", 
			"change to subCollectionOf with "+uf.getTarget().getName()+" as subcollection",  
			"change to subCollectionOf with "+uf.getSource().getName()+" as subcollection",
			"change to memberOf with "+uf.getTarget().getName()+" as member",   
			"change to memberOf with "+uf.getSource().getName()+" as member",   
			"change to subQuantityOf with "+uf.getSource().getName()+" as whole",  
			"change to subQuantityOf with "+uf.getTarget().getName()+" as whole", 
			"create attributes/datatypes and OCL invariants"});
		list.setBounds(10, 10, 554, 93);
		list.select(0);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		if (list.getSelectionIndex()==0){
			// do nothing
		}
		if(list.getSelectionIndex()==1){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToMediation(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==2){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToCharacterization(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==3){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToCharacterizationInvertingSides(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}		
		if(list.getSelectionIndex()==4){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToMaterial(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==5){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToSubCollectionOfSrcWhole(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==6){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToSubCollectionOfTgtWhole(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==7){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToMemberOfSrcWhole(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==8){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToMemberOfTgtWhole(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==9){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToSubQuantityOfSrcWhole(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==10){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToSubQuantityOfTgtWhole(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		return super.getNextPage();
	}
}
