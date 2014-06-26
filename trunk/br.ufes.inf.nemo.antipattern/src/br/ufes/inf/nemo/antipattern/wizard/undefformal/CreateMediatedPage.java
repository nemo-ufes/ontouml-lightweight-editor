package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

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
		btnDependence.setText("Yes, "+occurrence.getSource().getName()+" is connected to (at least) N instances of "+occurrence.getTarget().getName()+"...");
		btnDependence.addSelectionListener(listener);

		btnNotCaptured = new Button(container, SWT.RADIO);
		btnNotCaptured.setText("There are other mediated types not captured by the model...");
		btnNotCaptured.addSelectionListener(listener);
	    
	    setLowerMultComposite = new SetUpperMultComposite(container, SWT.NONE);
	    setLowerMultComposite.enable(false);
	    
	    createMediatedComposite = new CreateMediatedComposite(container, SWT.NONE);
	    createMediatedComposite.enable(false);
	    
	    setPageComplete(false);
	    setAsEnablingNextPageButton(btnDependence);
	    setAsEnablingNextPageButton(btnNotCaptured);
	    GroupLayout gl_container = new GroupLayout(container);
	    gl_container.setHorizontalGroup(
	    	gl_container.createParallelGroup(GroupLayout.LEADING)
	    		.add(gl_container.createSequentialGroup()
	    			.add(10)
	    			.add(gl_container.createParallelGroup(GroupLayout.LEADING)
	    				.add(questionText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
	    				.add(btnDependence, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
	    				.add(setLowerMultComposite, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
	    				.add(btnNotCaptured, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
	    				.add(createMediatedComposite))
	    			.add(10))
	    );
	    gl_container.setVerticalGroup(
	    	gl_container.createParallelGroup(GroupLayout.LEADING)
	    		.add(gl_container.createSequentialGroup()
	    			.add(10)
	    			.add(questionText, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
	    			.add(13)
	    			.add(btnDependence)
	    			.add(6)
	    			.add(setLowerMultComposite, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
	    			.add(19)
	    			.add(btnNotCaptured)
	    			.add(13)
	    			.add(createMediatedComposite, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
	    );
	    GroupLayout gl_setLowerMultComposite = new GroupLayout(setLowerMultComposite);
	    gl_setLowerMultComposite.setHorizontalGroup(
	    	gl_setLowerMultComposite.createParallelGroup(GroupLayout.LEADING)
	    		.add(gl_setLowerMultComposite.createSequentialGroup()
	    			.add(10)
	    			.add(setLowerMultComposite.spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
	    			.add(6)
	    			.add(setLowerMultComposite.lblCardinality, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
	    			.addContainerGap())
	    );
	    gl_setLowerMultComposite.setVerticalGroup(
	    	gl_setLowerMultComposite.createParallelGroup(GroupLayout.LEADING)
	    		.add(gl_setLowerMultComposite.createSequentialGroup()
	    			.add(10)
	    			.add(gl_setLowerMultComposite.createParallelGroup(GroupLayout.LEADING)
	    				.add(setLowerMultComposite.lblCardinality, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
	    				.add(setLowerMultComposite.spinner, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
	    			.addContainerGap())
	    );
	    setLowerMultComposite.setLayout(gl_setLowerMultComposite);
	    container.setLayout(gl_container);
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

