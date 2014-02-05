package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class UndefFormalFifthPage extends UndefFormalPage{

	public Composite parent;
	public Button btnDependence;
	public Button btnNotCaptured;
	private SetUpperMultComposite setUpperMultComposite;
	private  CreateMediatedComposite createMediatedComposite ;
	
	/**
	 * Create the wizard.
	 */
	public UndefFormalFifthPage(UndefFormalOccurrence uf) 
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
		
		Label lblQRelatorsAre = new Label(container, SWT.WRAP);
		lblQRelatorsAre.setBounds(10, 10, 554, 47);
		lblQRelatorsAre.setText("Relators are existentially dependent on two or more individuals. Does an instance of "+uf.getSource().getName()+" " +
			"depends on two or more instances of "+uf.getTarget().getName()+"? Or is there another object type that is still not captured in the model?");
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  if(btnDependence.getSelection()){
	    		  createMediatedComposite.enable(false);
	    		  setUpperMultComposite.enable(true);
	    	  }
	    	  if(btnNotCaptured.getSelection()){
	    		  setUpperMultComposite.enable(false);	    		 
	    		  createMediatedComposite.enable(true);
	    	  }
	      }
	    };
	    	    	    
		btnDependence = new Button(container, SWT.RADIO);
		btnDependence.setBounds(10, 70, 554, 16);
		btnDependence.setText("Yes, "+uf.getSource().getName()+" is connected to (at most) N instances of "+uf.getTarget().getName()+"...");
		btnDependence.addSelectionListener(listener);

		btnNotCaptured = new Button(container, SWT.RADIO);
		btnNotCaptured.setBounds(10, 150, 554, 16);
		btnNotCaptured.setText("There are other mediated types not captured by the model...");
		btnNotCaptured.addSelectionListener(listener);
	    
	    setUpperMultComposite = new SetUpperMultComposite(container, SWT.NONE);
	    setUpperMultComposite.setBounds(10, 92, 554, 39);
	    
	    createMediatedComposite = new CreateMediatedComposite(container, SWT.NONE);
	    createMediatedComposite.setBounds(10, 179, 554, 64);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{					
		if (btnDependence.getSelection()){
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setUpperMult(uf.getFormal(),uf.getSource(),uf.getTarget(),setUpperMultComposite.getValue());
			getUndefFormalWizard().replaceAction(1,newAction);	
			//======================================
		}
		if(btnNotCaptured.getSelection()){	
			//Action =============================
			UndefFormalAction newAction = new UndefFormalAction(uf);
			newAction.setCreateMediatedTypes(createMediatedComposite.getMap());
			getUndefFormalWizard().replaceAction(1,newAction);	
			//======================================
		}
		
		return ((UndefFormalWizard)getWizard()).getFinishing();	
	}
}

