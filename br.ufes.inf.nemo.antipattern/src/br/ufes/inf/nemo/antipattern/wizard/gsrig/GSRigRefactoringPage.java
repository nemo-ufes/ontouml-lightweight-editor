package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.List;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigAntipattern;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class GSRigRefactoringPage extends RefactoringPage {
	
	public GSRigOccurrence gsrig;
	private List list;
	private ExpandBar expandBar;
	private SubTypeComposite subtypeComposite;
	private RigidTableComposite rigidTableComposite;
	private ExpandItem item3_1;
	private ExpandItem item3;
	
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
				"Change all subtypes to rigid or anti-rigid",
				"Create a rigid subtype for each anti-rigid",
				"Create a common subtype for anti-rigids",
				"Create OCL derivation rule and a common subtype for anti-rigids"});
		
		list.select(0);

		list.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		    	  if(list.getSelectionIndex()==7){
		    		  rigidTableComposite.enable(true);
		    		  item3.setExpanded(true);
		    		  subtypeComposite.enable(false);
		    		  item3_1.setExpanded(false);
		      	  }else if(list.getSelectionIndex()==8){
		      		  subtypeComposite.enable(true);
		      		  item3_1.setExpanded(true);
		    		  rigidTableComposite.enable(false);
		    		  item3.setExpanded(false);
		    	  }else {
		    		  subtypeComposite.enable(false);
		    		  item3_1.setExpanded(false);
		    		  rigidTableComposite.enable(false);
		    		  item3.setExpanded(false);
		    	  }
		      }
		});
		expandBar = new ExpandBar(container, SWT.V_SCROLL | SWT.H_SCROLL);
		
		//===============================
		
		Composite composite3 = new Composite (expandBar, SWT.NONE);
		GridLayout layout3 = new GridLayout ();
		layout3.marginLeft = layout3.marginTop=3;
		layout3.marginRight=layout3.marginBottom=3;
		layout3.verticalSpacing = 3;
		composite3.setLayout(layout3);
		
		rigidTableComposite = new RigidTableComposite(composite3, SWT.V_SCROLL,gsrig);
		rigidTableComposite.enable(false);
		GridData gd_styledText = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_styledText.heightHint = 125;
		gd_styledText.widthHint = 628;
		rigidTableComposite.setLayoutData(gd_styledText);
		rigidTableComposite.setVisible(true);
		
		item3 = new ExpandItem (expandBar, SWT.NONE, 0);
		item3.setText("Change supertype and rigid subtypes stereotypes to anti-rigid");
		item3.setHeight(composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item3.setControl(composite3);
		item3.setExpanded(false);
		//===============================		
		
		composite3 = new Composite (expandBar, SWT.NONE);
		layout3 = new GridLayout ();
		layout3.marginLeft = layout3.marginTop=3;
		layout3.marginRight=layout3.marginBottom=3;
		layout3.verticalSpacing = 3;
		composite3.setLayout(layout3);
		
		subtypeComposite = new SubTypeComposite(composite3, SWT.V_SCROLL,gsrig);
		subtypeComposite.enable(false);
		gd_styledText = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		gd_styledText.heightHint = 137;
		gd_styledText.widthHint = 629;
		subtypeComposite.setLayoutData(gd_styledText);
		subtypeComposite.setVisible(true);
		
		item3_1 = new ExpandItem (expandBar, SWT.NONE, 1);
		item3_1.setExpanded(true);
		item3_1.setText("Change all subtypes to rigid or anti-rigid");
		item3_1.setHeight(composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item3_1.setControl(composite3);
		item3_1.setExpanded(false);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(list, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE)
						.add(expandBar, GroupLayout.DEFAULT_SIZE, 669, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(list, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE)
					.add(12)
					.add(expandBar, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE))
		);
		container.setLayout(gl_container);
		//===============================
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
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setChangeSupertypeAndRigidSpecificsStereotypesTo(rigidTableComposite.stereoCombo.getText(), rigidTableComposite.getRigidTable().getNewStereotypes(), createNewSupertype); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================			
		}
		if(list.getSelectionIndex()==8){						
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setChangeSpecificsStereotypesTo(subtypeComposite.getSubtypeTable().getNewStereotypes()); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================			
		}
		if(list.getSelectionIndex()==9){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateRigidSubtypeForAntiRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================	
		}
		if(list.getSelectionIndex()==10){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateCommonSubtypeForAntiRigids(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================	
		}
		if(list.getSelectionIndex()==11){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setCreateDerivationByNegation(); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
			//Action =============================
			newAction = new GSRigAction(gsrig);
			newAction.setCreateCommonSubtypeForAntiRigids(); 
			getGSRigWizard().replaceAction(1,newAction);
			//======================================
		}
		return ((GSRigWizard)getWizard()).getFinishing();	
	}
}

	