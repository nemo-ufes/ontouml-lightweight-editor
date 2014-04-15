package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class CreateMediatedPage extends UndefFormalPage{

	public Composite parent;
	public Button btnDependence;
	public Button btnNotCaptured;
	private SetUpperMultComposite setLowerMultComposite;
	private  CreateMediatedComposite createMediatedComposite ;
	
	/**
	 * Create the wizard.
	 */
	public CreateMediatedPage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription("Source: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		final Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		StyledText questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setBounds(10, 10, 554, 47);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setAlwaysShowScrollBars(false);
		questionText.setText("Relators are existentially dependent on two or more individuals. Does an instance of <"+occurrence.getSource().getName()+"> " +
			"depends on two or more instances of "+occurrence.getTarget().getName()+"? Or is there another object type that is still not captured in the model?");
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  if(btnDependence.getSelection()){
	    		  createMediatedComposite.enable(false);
	    		  setLowerMultComposite.enable(true);
	    	  }
	    	  if(btnNotCaptured.getSelection()){
	    		  setLowerMultComposite.enable(false);	    		 
	    		  createMediatedComposite.enable(true);
	    	  }
	      }
	    };
	    	    	    
		btnDependence = new Button(container, SWT.RADIO);
		btnDependence.setBounds(10, 70, 554, 16);
		btnDependence.setText("Yes, "+occurrence.getSource().getName()+" is connected to (at least) N instances of "+occurrence.getTarget().getName()+"...");
		btnDependence.addSelectionListener(listener);

		btnNotCaptured = new Button(container, SWT.RADIO);
		btnNotCaptured.setBounds(10, 150, 554, 16);
		btnNotCaptured.setText("There are other mediated types not captured by the model...");
		btnNotCaptured.addSelectionListener(listener);
	    
	    setLowerMultComposite = new SetUpperMultComposite(container, SWT.NONE);
	    setLowerMultComposite.setBounds(10, 92, 554, 39);
	    setLowerMultComposite.enable(false);
	    
	    createMediatedComposite = new CreateMediatedComposite(container, SWT.NONE);
	    createMediatedComposite.setBounds(10, 179, 554, 64);
	    createMediatedComposite.enable(false);
	    
	    setPageComplete(false);
	    setAsEnablingNextPageButton(btnDependence);
	    setAsEnablingNextPageButton(btnNotCaptured);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{					
		if (btnDependence.getSelection()){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(occurrence);
			newAction.setUpperMult(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget(),setLowerMultComposite.getValue());
			getAntipatternWizard().replaceAction(1,newAction);	
			//======================================
		}
		if(btnNotCaptured.getSelection()){	
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(occurrence);
			newAction.setCreateMediatedTypes(createMediatedComposite.getMap());
			getAntipatternWizard().replaceAction(1,newAction);	
			//======================================
		}
		
		return ((UndefFormalWizard)getWizard()).getFinishing();	
	}
}

