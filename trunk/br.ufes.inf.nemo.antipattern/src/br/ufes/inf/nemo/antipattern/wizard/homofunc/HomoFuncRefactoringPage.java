package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class HomoFuncRefactoringPage extends RefactoringPage {
	
	public HomoFuncOccurrence homoFunc;
	private Button btnNewTypeAsIdentityProvider;
	private Button btnWholeAsIdentityProvider;
	private Button btnChangeStereotypeIdentityProvider;
	private CreatePartComposite createPartComposite;
	private CreatePartComposite createSubPartComposite;
	private CreateComponentOfComposite createSubComponentOf;
	private Button btnMemberOf;
	private Button btnSubCollectionOf;
		
	/**
	 * Create the wizard.
	 */
	public HomoFuncRefactoringPage(HomoFuncOccurrence homoFunc) 
	{
		super();	
		this.homoFunc = homoFunc;
		
		setTitle( HomoFuncAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public HomoFuncWizard getHomoFuncWizard(){
		return (HomoFuncWizard)getWizard();
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		Composite composite = new Composite(container, SWT.BORDER);
		composite.setBounds(10, 96, 588, 55);
		
		btnMemberOf = new Button(composite, SWT.RADIO);
		btnMemberOf.setBounds(10, 10, 534, 16);
		btnMemberOf.setText("Change partOf relation to memberOf");
		
		btnSubCollectionOf = new Button(composite, SWT.RADIO);
		btnSubCollectionOf.setBounds(10, 32, 534, 16);
		btnSubCollectionOf.setText("Change partOf relation to subCollectionOf");
		
		Composite composite_1 = new Composite(container, SWT.BORDER);
		composite_1.setBounds(10, 10, 588, 80);
		
		btnNewTypeAsIdentityProvider = new Button(composite_1, SWT.RADIO);
		btnNewTypeAsIdentityProvider.setBounds(10, 10, 534, 16);
		btnNewTypeAsIdentityProvider.setText("Create new Collective to act as the identity provider of Whole");
		
		btnWholeAsIdentityProvider = new Button(composite_1, SWT.RADIO);
		btnWholeAsIdentityProvider.setBounds(10, 32, 534, 16);
		btnWholeAsIdentityProvider.setText("Make Whole the identity provider");
		
		btnChangeStereotypeIdentityProvider = new Button(composite_1, SWT.RADIO);
		btnChangeStereotypeIdentityProvider.setBounds(10, 54, 534, 16);
		btnChangeStereotypeIdentityProvider.setText("Change the stereotype of the identity provider to Collective");
		
		ExpandBar expandBar = new ExpandBar(container, SWT.V_SCROLL);
		expandBar.setBounds(10, 157, 588, 185);
		
		//===========
		
		Composite composite2 = new Composite (expandBar, SWT.NONE);
		GridLayout layout2 = new GridLayout ();
		layout2.marginLeft = layout2.marginTop=3;
		layout2.marginRight=layout2.marginBottom=3;
		layout2.verticalSpacing = 3;
		composite2.setLayout(layout2);
			
		createPartComposite = new CreatePartComposite(composite2, SWT.NONE,homoFunc,false);
		createPartComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		createPartComposite.setVisible(true);
		
		ExpandItem item2 = new ExpandItem (expandBar, SWT.NONE, 0);
		item2.setText("Create new parts via componentOf");
		item2.setHeight
		(composite2.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item2.setControl(composite2);
		
		//===========
		
		Composite composite3 = new Composite (expandBar, SWT.NONE);
		GridLayout layout3 = new GridLayout ();
		layout3.marginLeft = layout3.marginTop=3;
		layout3.marginRight=layout3.marginBottom=3;
		layout3.verticalSpacing = 3;
		composite3.setLayout(layout3);
		
		createSubPartComposite = new CreatePartComposite(composite3, SWT.NONE,homoFunc,true);
		createSubPartComposite.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		createSubPartComposite.setVisible(true);
				
		ExpandItem item3 = new ExpandItem (expandBar, SWT.NONE, 1);
		item3.setText("Create new subparts via subsetted componentOfs");
		item3.setHeight
		(composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item3.setControl(composite3);		
		
		//===========

		Composite composite4 = new Composite (expandBar, SWT.NONE);
		GridLayout layout4 = new GridLayout ();
		layout4.marginLeft = layout4.marginTop=3;
		layout4.marginRight=layout4.marginBottom=3;
		layout4.verticalSpacing = 3;
		composite4.setLayout(layout4);
		
		createSubComponentOf = new CreateComponentOfComposite(composite4, SWT.NONE,homoFunc);
		createSubComponentOf.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1));
		createSubComponentOf.setVisible(true);
				
		ExpandItem item4 = new ExpandItem (expandBar, SWT.NONE, 2);
		item4.setText("Create new subsetted componentOfs from existing subparts");
		item4.setHeight
		(composite4.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item4.setControl(composite4);	
		
	}

	@Override
	public IWizardPage getNextPage() 
	{
		((HomoFuncWizard)getWizard()).removeAllActions();
		
		if(btnNewTypeAsIdentityProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setCreateNewIdentityProvider(homoFunc.getWhole()); 
			getHomoFuncWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnWholeAsIdentityProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToCollective(homoFunc.getWhole()); 
			getHomoFuncWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnChangeStereotypeIdentityProvider.getSelection())
		{
			ArrayList<Classifier> providers = homoFunc.getParser().getIdentityProvider(homoFunc.getWhole());
			if (providers.size()>0){
				//Action =============================
				HomoFuncAction newAction = new HomoFuncAction(homoFunc);
				newAction.setChangeAllToCollective(providers); 
				getHomoFuncWizard().replaceAction(0,newAction);	
				//======================================				
			}
		}
		if(btnSubCollectionOf.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToSubCollectionOf(homoFunc.getPartEnd().getAssociation()); 
			getHomoFuncWizard().replaceAction(1,newAction);	
			//======================================
		}
		if(btnMemberOf.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToMemberOf(homoFunc.getPartEnd().getAssociation()); 
			getHomoFuncWizard().replaceAction(1,newAction);	
			//======================================
		}		
		if(createPartComposite.getParts().size()>0)
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setCreateNewPart(createPartComposite.getParts(),true); 
			getHomoFuncWizard().replaceAction(3,newAction);	
			//======================================	
		}		
		if(createSubPartComposite.getParts().size()>0)
		{		
			if (createSubPartComposite!=null){				
				//Action =============================
				HomoFuncAction newAction = new HomoFuncAction(homoFunc);
				newAction.setCreateNewPart(createSubPartComposite.getParts(),false); 
				getHomoFuncWizard().replaceAction(4,newAction);	
				//======================================		
			}						
			if (createSubPartComposite.parts.size()>=2){				
				String message = "These created sub-parts might characterize a WholeOver/PartOver anti-pattern." +
						"\n\n";
				for(PartElement r: createSubPartComposite.parts){
					message+=r.toString()+"\n";
				}
				message += "\nSearch for the WholeOver/PartOver antipattern when finished.";
				MessageDialog.openInformation(getShell(), "WARNING", message);	
			}			
		}				
		if(createSubComponentOf.getRelations().size()>0)
		{			
			if (createSubComponentOf!=null){
				//Action =============================
				HomoFuncAction newAction = new HomoFuncAction(homoFunc);
				newAction.setCreateSubComponentOfToExistingSubPart(createSubComponentOf.getRelations()); 
				getHomoFuncWizard().replaceAction(5,newAction);	
				//======================================		
			}						
			if (createSubComponentOf.relations.size()>=2){
				String message = "These created sub-parts might characterize a WholeOver/PartOver anti-pattern." +
						"\n\n";
				for(RelationElement r: createSubComponentOf.relations){
					message+=r.toString()+"\n";
				}
				message +=  "\nSearch for the WholeOver/PartOver antipattern when finished.";
				MessageDialog.openInformation(getShell(), "WARNING", message);
			}
		}
		
		return ((HomoFuncWizard)getWizard()).getFinishing();
	}
}