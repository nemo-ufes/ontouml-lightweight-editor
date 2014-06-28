package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncAntipattern;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class HomoFuncRefactoringPage extends RefactoringPage {
	
	public HomoFuncOccurrence homoFunc;
	private Button btnNewTypeAsIdentityProvider;
	private Button btnWholeAsIdentityProvider;
	private Button btnChangeStereotypeIdentityProvider;
	private Button btnMemberOf;
	private Button btnSubCollectionOf;
	
	private CreatePartComposite createPartComposite;
	private CreatePartComposite createSubtypePartComposite;
	private CreateComponentOfComposite createComponentOfToSubtype;
	private CreateComponentOfComposite createComponentOfToExistingFunctional;
		
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
		
		Group composite = new Group(container, SWT.NONE);
		composite.setText("Change stereotype of componentOf relation:");
		
		btnMemberOf = new Button(composite, SWT.RADIO);
		btnMemberOf.setText("Change stereotype to memberOf");
		
		btnSubCollectionOf = new Button(composite, SWT.RADIO);
		btnSubCollectionOf.setText("Change partOf relation to subCollectionOf");
		
		Group composite_1 = new Group(container, SWT.NONE);
		composite_1.setText("Change whole nature to Collection:");
		
		btnNewTypeAsIdentityProvider = new Button(composite_1, SWT.RADIO);
		btnNewTypeAsIdentityProvider.setText("Set new collective as the identity provider");
		
		btnWholeAsIdentityProvider = new Button(composite_1, SWT.RADIO);
		btnWholeAsIdentityProvider.setText("Change whole stereotype to «Collective»");
		
		btnChangeStereotypeIdentityProvider = new Button(composite_1, SWT.RADIO);
		btnChangeStereotypeIdentityProvider.setText("Change whole's identity providers' stereotype(s) to «Collective»");
		
		ExpandBar expandBar = new ExpandBar(container, SWT.V_SCROLL);
		
		Composite item1Composite = new Composite (expandBar, SWT.NONE);
			
		createPartComposite = new CreatePartComposite(item1Composite, SWT.NONE, homoFunc, false);
		
		ExpandItem item1 = new ExpandItem (expandBar, SWT.NONE);
		item1.setText("New functional complex types");
		item1.setHeight(item1Composite.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item1.setControl(item1Composite);
		GroupLayout gl_item1Composite = new GroupLayout(item1Composite);
		gl_item1Composite.setHorizontalGroup(
			gl_item1Composite.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_item1Composite.createSequentialGroup()
					.add(1)
					.add(createPartComposite, GroupLayout.DEFAULT_SIZE, 541, Short.MAX_VALUE)
					.add(1))
		);
		gl_item1Composite.setVerticalGroup(
			gl_item1Composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_item1Composite.createSequentialGroup()
					.add(1)
					.add(createPartComposite, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
					.add(1))
		);
		item1Composite.setLayout(gl_item1Composite);
		
		//===========
		
		Composite item2Composite = new Composite (expandBar, SWT.NONE);
		createComponentOfToExistingFunctional = new CreateComponentOfComposite(item2Composite, SWT.NONE, homoFunc, false);
				
		ExpandItem item2 = new ExpandItem (expandBar, SWT.NONE);
		item2.setText("Existing functional complex types");
		item2.setHeight
		(165);
		item2.setControl(item2Composite);
		GroupLayout gl_item2Composite = new GroupLayout(item2Composite);
		gl_item2Composite.setHorizontalGroup(
			gl_item2Composite.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_item2Composite.createSequentialGroup()
					.add(1)
					.add(createComponentOfToExistingFunctional, GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
					.add(1))
		);
		gl_item2Composite.setVerticalGroup(
			gl_item2Composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_item2Composite.createSequentialGroup()
					.add(1)
					.add(createComponentOfToExistingFunctional, GroupLayout.DEFAULT_SIZE, 139, Short.MAX_VALUE)
					.add(1))
		);
		item2Composite.setLayout(gl_item2Composite);
		
		//===========

		Composite item4Composite = new Composite (expandBar, SWT.NONE);
		
		createComponentOfToSubtype = new CreateComponentOfComposite(item4Composite, SWT.NONE,homoFunc,true);
		
		ExpandItem item3 = new ExpandItem(expandBar, SWT.NONE);
		item3.setText("New part subtypes");
		
		Composite item3Composite = new Composite(expandBar, SWT.NONE);
		item3.setControl(item3Composite);
		item3.setHeight(165);
		
		createSubtypePartComposite = new CreatePartComposite(item3Composite, SWT.NONE,homoFunc,true);

		GroupLayout gl_item3Composite = new GroupLayout(item3Composite);
		gl_item3Composite.setHorizontalGroup(
			gl_item3Composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_item3Composite.createSequentialGroup()
					.add(1)
					.add(createSubtypePartComposite, GroupLayout.DEFAULT_SIZE, 559, Short.MAX_VALUE)
					.add(1))
		);
		gl_item3Composite.setVerticalGroup(
			gl_item3Composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_item3Composite.createSequentialGroup()
					.add(1)
					.add(createSubtypePartComposite, GroupLayout.DEFAULT_SIZE, 41, Short.MAX_VALUE)
					.add(1))
		);
		item3Composite.setLayout(gl_item3Composite);
				
		ExpandItem item4 = new ExpandItem (expandBar, SWT.NONE);
		item4.setText("Existing part subtypes");
		item4.setHeight
		(165);
		item4.setControl(item4Composite);	
		GroupLayout gl_item4Composite = new GroupLayout(item4Composite);
		gl_item4Composite.setHorizontalGroup(
			gl_item4Composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_item4Composite.createSequentialGroup()
					.add(1)
					.add(createComponentOfToSubtype, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
					.add(1))
		);
		gl_item4Composite.setVerticalGroup(
			gl_item4Composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_item4Composite.createSequentialGroup()
					.add(1)
					.add(createComponentOfToSubtype, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE)
					.add(1))
		);
		item4Composite.setLayout(gl_item4Composite);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("Define new componentOf relations using:");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(composite, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
					.add(9))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(composite_1, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, 586, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.add(9)
					.add(expandBar, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(GroupLayout.LEADING, gl_container.createSequentialGroup()
					.add(10)
					.add(composite_1, GroupLayout.PREFERRED_SIZE, 99, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(composite, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.add(18)
					.add(lblNewLabel)
					.add(5)
					.add(expandBar, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
		);
		GroupLayout gl_composite = new GroupLayout(composite);
		gl_composite.setHorizontalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_composite.createSequentialGroup()
					.add(10)
					.add(gl_composite.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, btnSubCollectionOf, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, btnMemberOf, GroupLayout.DEFAULT_SIZE, 573, Short.MAX_VALUE))
					.add(10))
		);
		gl_composite.setVerticalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(10)
					.add(btnMemberOf)
					.add(6)
					.add(btnSubCollectionOf)
					.addContainerGap())
		);
		composite.setLayout(gl_composite);
		GroupLayout gl_composite_1 = new GroupLayout(composite_1);
		gl_composite_1.setHorizontalGroup(
			gl_composite_1.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite_1.createSequentialGroup()
					.add(10)
					.add(gl_composite_1.createParallelGroup(GroupLayout.LEADING)
						.add(btnWholeAsIdentityProvider, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, btnNewTypeAsIdentityProvider, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)
						.add(btnChangeStereotypeIdentityProvider, GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE))
					.add(10))
		);
		gl_composite_1.setVerticalGroup(
			gl_composite_1.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite_1.createSequentialGroup()
					.add(10)
					.add(btnNewTypeAsIdentityProvider)
					.add(6)
					.add(btnWholeAsIdentityProvider)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnChangeStereotypeIdentityProvider)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		composite_1.setLayout(gl_composite_1);
		container.setLayout(gl_container);
		
	}

	@Override
	public IWizardPage getNextPage() 
	{
		((HomoFuncWizard)getWizard()).removeAllActions();
		
		if(btnNewTypeAsIdentityProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setCreateNewIdentityProvider(); 
			getHomoFuncWizard().addAction(0,newAction);	
			//======================================
		}
		else if(btnWholeAsIdentityProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToCollective(); 
			getHomoFuncWizard().addAction(0,newAction);	
			//======================================
		}
		else if(btnChangeStereotypeIdentityProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeNatureToCollection(); 
			getHomoFuncWizard().addAction(0,newAction);	
			//======================================
		}
		
		if(btnSubCollectionOf.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToSubCollectionOf(); 
			getHomoFuncWizard().addAction(0,newAction);	
			//======================================
		}
		else if(btnMemberOf.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setChangeToMemberOf(); 
			getHomoFuncWizard().addAction(0,newAction);	
			//======================================
		}	
		
		int newParts = createPartComposite.getParts().size();
		int newPartSubtype = createSubtypePartComposite.getParts().size();
		
		if(newParts>0)
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setCreateNewPart(createPartComposite.getParts(),true); 
			getHomoFuncWizard().addAction(0,newAction);	
			//======================================	
		}		
		
		if(newPartSubtype>0)
		{		
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setCreateNewPart(createSubtypePartComposite.getParts(),false); 
			getHomoFuncWizard().addAction(0,newAction);	
			//======================================							
					
		}		
		
		int newComponentOfToSubtype = createComponentOfToSubtype.getRelations().size();
		int newComponentOfToExistingType = createComponentOfToExistingFunctional.getRelations().size();
				
		if(newComponentOfToSubtype>0)
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setCreateSubComponentOfToExistingSubPart(createComponentOfToSubtype.getRelations()); 
			getHomoFuncWizard().addAction(0,newAction);	
			//======================================		
		}
		
		if(newComponentOfToExistingType>0)
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
			newAction.setCreateComponentOfToExistingType(createComponentOfToExistingFunctional.getRelations());
			getHomoFuncWizard().addAction(0,newAction);	
			//======================================		
		}
		
		if((newComponentOfToSubtype+newPartSubtype)>=2){				
			String message ="The creation of new parthood relations might characterize a WholeOver/PartOver anti-pattern." +
							"\r\n\r\n"+
							"Please remember to search the model for the WholeOver and PartOver antipatterns.";
			MessageDialog.openInformation(getShell(), "WARNING", message);	
		}	
		
		return ((HomoFuncWizard)getWizard()).getFinishing();
	}
}