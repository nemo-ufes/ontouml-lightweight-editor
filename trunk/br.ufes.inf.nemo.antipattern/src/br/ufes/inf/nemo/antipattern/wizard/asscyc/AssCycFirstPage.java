package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.Association;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;

public class AssCycFirstPage  extends AssCycPage {
		
	private StyledText questionText;
	private Button btnYes;
	private Button btnNo;
	private Label lblDerivedAssociation;
	private Combo assocCombo;
	private Composite composite;

	public AssCycFirstPage(AssCycOccurrence asscyc) 
	{
		super(asscyc);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);

		questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setAlwaysShowScrollBars(false);
		questionText.setJustify(true);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setText(
				"To start the analysis of this anti-pattern, we first remember the concept of derived associations, inherit by OntoUML from UML." +
				"\r\n\r\n" +
				"Briefly, derived associations are the ones that can be calculated from other associations and attributes. " +
				"\r\n\r\n" +
				"An example of a derived association is “older than”, which can hold between two people. " +
				"Every instance of the association can be computed by the comparing people’s ages. " +
				"Another example can be found by considering the part-whole relations that hold in a Car. " +
				"A Car is composed by an Engine, which in turn is composed by an Exhaust System. " +
				"The part-whole relation that holds between a Car and an Exhaust System is derived from the relations between the Car and the Engine and between the Engine and the Exhaust System." +
				"\n\n" +
				"In your model, we identified a cycle of relations (generalizations and/or associations) containing the classes: "+occurrence.getClassCycleString()+"."+
				"\n\n" +
				"That suggests that one of the associations may be derived from the others. Is that the case?");
  	  
		SelectionAdapter yesListener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  assocCombo.setEnabled(true);
	    	  
	    	  if(assocCombo.getSelectionIndex()!=-1){
	    		  if(!isPageComplete())
		    		  setPageComplete(true);
	    	  }
	    	  else{
	    		  if(isPageComplete())
		    		  setPageComplete(false);
	    	  }
	      }
	    };

		SelectionAdapter noListener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  assocCombo.setEnabled(false);
	    	  
	    	  if(!isPageComplete())
	    		  setPageComplete(true);
	      }
	    };
	    
	    SelectionAdapter comboListener = new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		    	  if(assocCombo.getSelectionIndex()!=-1){
			    	  if(!isPageComplete())
			    		  setPageComplete(true);
		    	  }else{
		    		  if(isPageComplete())
			    		  setPageComplete(false);
		    	  }
		      }
		    };
		    
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes, one association is derived");
		btnYes.addSelectionListener(yesListener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No, none of the associations are derived");
		btnNo.addSelectionListener(noListener);
		
		composite = new Composite(container, SWT.BORDER);
		
		lblDerivedAssociation = new Label(composite, SWT.NONE);
		lblDerivedAssociation.setText("Please, choose which association is derived:");
		
		
		assocCombo = new Combo(composite, SWT.READ_ONLY);
		assocCombo.setEnabled(false);
		for(Association a: occurrence.getOnlyAssociations()){
			Type source = a.getMemberEnd().get(0).getType();
			Type target = a.getMemberEnd().get(1).getType();
			
			assocCombo.add(OntoUMLNameHelper.getTypeAndName(source, true, false)+" - "+OntoUMLNameHelper.getTypeAndName(target, true, false)+" ("+OntoUMLNameHelper.getTypeAndName(a, false, false)+")");
		}
		assocCombo.addSelectionListener(comboListener);
		
		lblDerivedAssociation.setVisible(true);
		assocCombo.setVisible(true);
		composite.setVisible(true);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, btnYes, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, btnNo, GroupLayout.DEFAULT_SIZE, 715, Short.MAX_VALUE)
						.add(questionText, GroupLayout.DEFAULT_SIZE, 700, Short.MAX_VALUE))
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(9)
					.add(composite, GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnNo)
					.add(6)
					.add(btnYes)
					.add(18)
					.add(composite, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.add(36))
		);
		GroupLayout gl_composite = new GroupLayout(composite);
		gl_composite.setHorizontalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.add(10)
					.add(gl_composite.createParallelGroup(GroupLayout.LEADING)
						.add(lblDerivedAssociation, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE)
						.add(assocCombo, GroupLayout.DEFAULT_SIZE, 692, Short.MAX_VALUE))
					.add(10))
		);
		gl_composite.setVerticalGroup(
			gl_composite.createParallelGroup(GroupLayout.LEADING)
				.add(gl_composite.createSequentialGroup()
					.addContainerGap()
					.add(lblDerivedAssociation, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(assocCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
		);
		composite.setLayout(gl_composite);
		container.setLayout(gl_container);
		
		setPageComplete(false);
	}
	
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if (btnNo.getSelection())
		{
			return getAntipatternWizard().getSecondPage();
		}
		if(btnYes.getSelection())
		{
			if(assocCombo.getSelectionIndex()>=0)
			{
				Association assoc = (Association)occurrence.getOnlyAssociations().get(assocCombo.getSelectionIndex());
				//Action =============================
				AssCycAction newAction = new AssCycAction(occurrence);
				newAction.setDeriveAssociation(assoc); 
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
			}				
		}
		
		return getAntipatternWizard().getFinishing();
	}
}

