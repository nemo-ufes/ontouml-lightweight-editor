package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class HomoFuncThirdPage extends HomoFuncPage {

	private Button btnYes;
	private Button btnNo;
	private Label lblYouCanAlso;
	private Button btnCreateNewParts;
	private Button btnNewComponentOfs;	
	private Composite container;
	private Control control;
	private Composite composite;

	public HomoFuncThirdPage(HomoFuncOccurrence homoFunc) 
	{
		super(homoFunc);		
		//setDescription("Whole: <"+getStereotype(homoFunc.getWhole())+"> "+homoFunc.getWhole().getName());
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		lblYouCanAlso = new Label(container, SWT.WRAP);
		lblYouCanAlso.setBounds(10, 10, 554, 31);
		lblYouCanAlso.setText("You can also define new particular types of <FunctionalPart> which play different functions in the context of <FunctionalWhole>. Would like to do that?");
		
		composite = new Composite(container, SWT.NONE);
		composite.setLayout(new GridLayout(1, true));
		composite.setBounds(10, 98, 569, 174);
		
		btnCreateNewParts = new Button(container, SWT.RADIO);
		btnCreateNewParts.setBounds(10, 76, 213, 16);
		btnCreateNewParts.setText("Create new parts of <FuntionalPart>");		
				
		btnNewComponentOfs = new Button(container, SWT.RADIO);
		btnNewComponentOfs.setBounds(229, 76, 335, 16);
		btnNewComponentOfs.setText("Create new componentOfs for existing subtypes");
		
		SelectionAdapter listener2 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  btnCreateNewParts.setVisible(true);
	    	  btnNewComponentOfs.setVisible(true);
	    	  if (control!=null)control.dispose();
	      }
	    };		
		
		SelectionAdapter listener3 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  btnCreateNewParts.setVisible(false);
	    	  btnNewComponentOfs.setVisible(false);
	    	  if (control!=null)control.dispose();
	      }
	    };
	    
	    btnCreateNewParts.setVisible(false);
  	    btnNewComponentOfs.setVisible(false);
  	  
	    btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(205, 47, 60, 16);
		btnYes.setText("Yes...");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(271, 47, 90, 16);
		btnNo.setText("No");
				
	    btnYes.addSelectionListener(listener2);
	    btnNo.addSelectionListener(listener3);
	    		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {	    	 
	    	 
	    	  if (control != null) control.dispose();
	    	  
	    	  control = new CreatePartComposite(composite, SWT.NONE,homoFunc);
	    	  control.setBounds(10, 98, 569, 176);
	    	  control.setVisible(true);
	    	  
	    	  GridData data = new GridData(SWT.FILL, SWT.NONE, false, false);
	  	      data.horizontalSpan = 1;
	  	      control.setLayoutData(data);
	  		  composite.layout(true);
	      }
	    };
	       
	    SelectionAdapter listener0 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {	 

	    	  if (control != null) control.dispose();
	    	  
	    	  control = new CreateComponentOfComposite(composite, SWT.NONE,homoFunc);
	    	  control.setBounds(10, 95, 554, 177);
	    	  control.setVisible(true);
	    	  
	    	  GridData data = new GridData(SWT.FILL, SWT.NONE, false, false);
	  	      data.horizontalSpan = 1;
	  	      control.setLayoutData(data);
	  		  composite.layout(true);
	      }
	    };
	    
	    btnCreateNewParts.addSelectionListener(listener);
	    btnNewComponentOfs.addSelectionListener(listener0);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{		
		if(btnCreateNewParts.getSelection()){
			
//			//Action =============================
//			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
//			newAction.setCreateNewPart(dialog.getPartStereotype(), dialog.getPartName(), dialog.getComponentOfName(), 
//				dialog.isShareable(), dialog.isEssential(), dialog.isImmutablePart(), dialog.isImmutableWhole(), dialog.isInseparable()); 
//			getHomoFuncWizard().replaceAction(0,newAction);	
//			//======================================
			
			CreatePartComposite createPartComposite = (CreatePartComposite)control;
			if (createPartComposite.parts.size()>=2){
				
			}else{
				return ((HomoFuncWizard)getWizard()).getFinishing();
			}			
		}
		if(btnNewComponentOfs.getSelection()){
			
//			//Action =============================
//			HomoFuncAction newAction = new HomoFuncAction(homoFunc);
//			newAction.setCreateNewPart(dialog.getPartStereotype(), dialog.getPartName(), dialog.getComponentOfName(), 
//				dialog.isShareable(), dialog.isEssential(), dialog.isImmutablePart(), dialog.isImmutableWhole(), dialog.isInseparable()); 
//			getHomoFuncWizard().replaceAction(0,newAction);	
//			//======================================
			
			CreateComponentOfComposite createComponentOfComposite = (CreateComponentOfComposite)control;
			if (createComponentOfComposite.relations.size()>=2){
				return ((HomoFuncWizard)getWizard()).getFourthPage();
			}else{
				return ((HomoFuncWizard)getWizard()).getFinishing();
			}			
		}
		return super.getNextPage();
	}
}