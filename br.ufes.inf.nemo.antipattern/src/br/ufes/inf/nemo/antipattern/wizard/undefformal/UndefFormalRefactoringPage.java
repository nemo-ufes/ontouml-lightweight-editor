package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalAntipattern;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class UndefFormalRefactoringPage extends RefactoringPage {
	
	protected UndefFormalOccurrence uf;
	public List list;
	public SetUpperMultComposite setCardinalityComposite;	
	public CreateMediatedComposite createMediatedComposite;
	public DataTypeComposite createDataTypeComposite;
	
	/**
	 * Create the wizard.
	 */
	public UndefFormalRefactoringPage(UndefFormalOccurrence uf) 
	{
		super();	
		this.uf = uf;
				
		setTitle(UndefFormalAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the Formal relationship: "+uf.getSource().getName()+" -> "+uf.getTarget().getName());
	}

	public UndefFormalWizard getUndefFormalWizard(){
		return ( UndefFormalWizard)getWizard();
	}	
	
	public void createControl(Composite parent) {
//		
//		Composite container = new Composite(parent, SWT.NONE);
//				
//		setControl(container);
		
		ScrolledComposite sc = new ScrolledComposite(parent, SWT.V_SCROLL | SWT.BORDER);
		sc.setAlwaysShowScrollBars(true);
		sc.setBounds(10, 60, 554, 212);
		
		setControl(sc);
		
		Composite composite = new Composite(sc, SWT.NONE);
		
		list = new List(composite, SWT.V_SCROLL | SWT.BORDER);
		list.setItems(new String[] {
			"do nothing", 
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
			"create attributes/datatypes and OCL invariants",
			"change to Mediation and set upper cardinality on mediated side",
			"change to Mediation and create new mediated types "});
		list.select(0);
		
		list.addSelectionListener(new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		    	  if(list.getSelectionIndex()==11){
		    		  createDataTypeComposite.enable(true);
		    		  setCardinalityComposite.enable(false);
		    		  createMediatedComposite.enable(false);
		      	  }else if(list.getSelectionIndex()==12){
		    		  setCardinalityComposite.enable(true);
		    		  createMediatedComposite.enable(false);
		    		  createDataTypeComposite.enable(false);
		    	  }else if(list.getSelectionIndex()==13){
		    		  createMediatedComposite.enable(true);
		    		  setCardinalityComposite.enable(false);
		    		  createDataTypeComposite.enable(false);
		    	  }else {
		    		  setCardinalityComposite.enable(false);
		    		  createMediatedComposite.enable(false);
		    		  createDataTypeComposite.enable(false);
		    	  }
		      }
		});
		
		setCardinalityComposite = new SetUpperMultComposite(composite, SWT.BORDER);
		
		createMediatedComposite = new CreateMediatedComposite(composite, SWT.BORDER);
		
		createDataTypeComposite = new DataTypeComposite(composite, SWT.BORDER, (UndefFormalOccurrence) uf);
		GroupLayout gl_composite = new GroupLayout(composite);
		gl_composite.setHorizontalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(10)
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(list, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(setCardinalityComposite, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(createMediatedComposite, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(createDataTypeComposite, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
					.add(10))
		);
		gl_composite.setVerticalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(10)
					.add(list, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(setCardinalityComposite, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(createMediatedComposite, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(createDataTypeComposite, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
					.add(10))
		);
		composite.setLayout(gl_composite);
		
		setCardinalityComposite.enable(false);
		createMediatedComposite.enable(false);
		createDataTypeComposite.enable(false);
		
		sc.setContent(composite);
		sc.setExpandHorizontal(true);
		sc.setExpandVertical(true);
		sc.setMinSize(composite.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		(getUndefFormalWizard()).removeAllActions();	
		
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
//			newAction.setChangeToMaterial(uf.getFormal(),uf.getSource(),uf.getTarget());
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
		if(list.getSelectionIndex()==11){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setCreateDatatypesAttributesAndRules(uf.getFormal(),uf.getSource(),uf.getTarget(), createDataTypeComposite.getSourceMapType(), 
				createDataTypeComposite.getTargetMapType(), createDataTypeComposite.getSourceMapStereo(), createDataTypeComposite.getTargetMapStereo(), createDataTypeComposite.getConstraints());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(list.getSelectionIndex()==12){
			
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToMediation(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
			
			//Action =============================
			UndefFormalAction newAction2 = new UndefFormalAction(uf);
			newAction2.setUpperMult(uf.getFormal(),uf.getSource(),uf.getTarget(),setCardinalityComposite.getValue());
			getUndefFormalWizard().replaceAction(1,newAction2);	
			//======================================
		} 
		if(list.getSelectionIndex()==13){
			
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setChangeToMediation(uf.getFormal(),uf.getSource(),uf.getTarget());
			getUndefFormalWizard().replaceAction(0,newAction);	
			//======================================
			
			//Action =============================
			UndefFormalAction newAction2 = new UndefFormalAction(uf);
			newAction2.setCreateMediatedTypes(createMediatedComposite.getMap());
			getUndefFormalWizard().replaceAction(1,newAction2);	
			//======================================
		}
		return getUndefFormalWizard().getFinishing();
	}
}
