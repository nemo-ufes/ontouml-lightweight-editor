package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class HomoFuncThirdPage extends HomoFuncPage {

	private Button btnYes;
	private Button btnNo;
	private Label lblYouCanAlso;
	private Composite container;
	private ExpandBar expandBar;
	private CreatePartComposite createPartComposite;
	private CreatePartComposite createSubPartComposite;
	private CreateComponentOfComposite createSubComponentOf;

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
		lblYouCanAlso.setText("You can also define new particular types of "+homoFunc.getPartEnd().getType().getName()+" which play different functions in the context " +
		"of "+homoFunc.getWhole().getName()+". Would like to do that?");
		
		SelectionAdapter listener2 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  expandBar.setVisible(true);
	      }
	    };		
		
		SelectionAdapter listener3 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  expandBar.setVisible(false);
	      }
	    };
  	  
	    btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 62, 54, 16);
		btnYes.setText("Yes");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(70, 62, 90, 16);
		btnNo.setText("No");
		
		expandBar = new ExpandBar(container, SWT.V_SCROLL);
		expandBar.setBounds(10, 84, 569, 188);
		expandBar.setVisible(false);
		
	    btnYes.addSelectionListener(listener2);
	    btnNo.addSelectionListener(listener3);
	    		
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
				
		ExpandItem item3 = new ExpandItem (expandBar, SWT.NONE, 0);
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
				
		ExpandItem item4 = new ExpandItem (expandBar, SWT.NONE, 1);
		item4.setText("Create new subsetted componentOfs from existing subparts");
		item4.setHeight
		(composite4.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		item4.setControl(composite4);	
		
		// ===============		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{		
		
		if(createPartComposite!=null && createPartComposite.getParts().size()>0)
		{						
			if (createPartComposite!=null){				
				//Action =============================
				HomoFuncAction newAction = new HomoFuncAction(homoFunc);
				newAction.setCreateNewPart(createPartComposite.getParts(),false); 
				getHomoFuncWizard().replaceAction(4,newAction);	
				//======================================		
			}			
			if (createPartComposite.parts.size()>=2){
				
				String message = "The creation of more than two sub-parts might have made a WholeOver/PartOver anti-patterns emerge. " +
						"\n\n";
				for(PartElement r: createPartComposite.parts){
					message+=r.toString()+"\n";
				}
				message += "\nPlease, run a new search for the WholeOver/PartOver antipattern when finished.";
				MessageDialog.openInformation(getShell(), "WARNING", message);		
				
				return ((HomoFuncWizard)getWizard()).getFinishing();
			}else{
				return ((HomoFuncWizard)getWizard()).getFinishing();
			}			
		}
		if(createSubComponentOf!=null && createSubComponentOf.getRelations().size()>0)
		{					
			if (createSubComponentOf!=null){
				//Action =============================
				HomoFuncAction newAction = new HomoFuncAction(homoFunc);
				newAction.setCreateSubComponentOfToExistingSubPart(createSubComponentOf.getRelations()); 
				getHomoFuncWizard().replaceAction(5,newAction);	
				//======================================		
			}						
			if (createSubComponentOf.relations.size()>=2){
				String message = "The creation of more than two sub-parts might have made a WholeOver/PartOver anti-patterns emerge. " +
						"\n\n";
				for(RelationElement r: createSubComponentOf.relations){
					message+=r.toString()+"\n";
				}
				message +=  "\nPlease, run a new search for the WholeOver/PartOver antipattern when finished.";
				MessageDialog.openInformation(getShell(), "WARNING", message);			
					
				return ((HomoFuncWizard)getWizard()).getFinishing();
			}else{
				return ((HomoFuncWizard)getWizard()).getFinishing();
			}			
		}
		
		if(btnNo.getSelection())
		{
			return getHomoFuncWizard().getFinishing();
		}
				
		return super.getNextPage();
	}
}
