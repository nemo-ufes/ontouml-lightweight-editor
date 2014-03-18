package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Button;

public class GSRigSixthPage extends GSRigPage {
	
	private Label lblNewLabel;
	private Label lblNewStereotype;
	private Combo stereoCombo;
	private Label lblAlsoIfis;
	private Button btnYesAllSubtypes;
	private RigidTableComposite rigidTableComposite;

	public GSRigSixthPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 10, 644, 15);
		lblNewLabel.setText("Please choose the new anti-rigid stereotype of <RigidSuperType>.");
		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setBounds(10, 31, 644, 38);
		
		lblNewStereotype = new Label(composite, SWT.NONE);
		lblNewStereotype.setBounds(10, 10, 159, 15);
		lblNewStereotype.setText("New stereotype:");
							    
		lblAlsoIfis = new Label(container, SWT.WRAP);
		lblAlsoIfis.setBounds(10, 75, 644, 51);
		lblAlsoIfis.setText("Also, if <RigidSuperType>is anti-rigid, then ALL of its subtypes must also be anti-rigid, including the ones that are not in the generalization set currently under the microscope. Is that true? If it is, choose the new stereotypes for the rigid supertypes.");
		
//		Composite composite_1 = new Composite(container, SWT.NONE);
//		composite_1.setBounds(10, 167, 644, 105);
				
		btnYesAllSubtypes = new Button(container, SWT.CHECK);
		btnYesAllSubtypes.setBounds(10, 145, 644, 16);
		btnYesAllSubtypes.setText("Yes, all subtypes are anti-rigid.");
		
		rigidTableComposite = new RigidTableComposite(container,SWT.NONE,gsrig);
		rigidTableComposite.setBounds(0, 167, 352, 105);
		rigidTableComposite.setVisible(true);
		
		stereoCombo = new Combo(composite, SWT.READ_ONLY);
		stereoCombo.setBounds(175, 7, 148, 23);
		stereoCombo.setItems(new String[] {"Role", "Phase", "RoleMixin"});
		stereoCombo.select(0);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  if(stereoCombo.getText().compareToIgnoreCase("Role")==0 || stereoCombo.getText().compareToIgnoreCase("Phase")==0){
	    		  	rigidTableComposite.limitChoiceToRoleAndPhase();
	    	  	  	rigidTableComposite.setVisible(true);
	    	  }else{
	    		  	rigidTableComposite.limitChoiceToRoleMixin();
	    		  	rigidTableComposite.setVisible(true);
	    	  }
	      }
	    };
	    stereoCombo.addSelectionListener(listener);    
	    
	    SelectionAdapter listener2 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  if(btnYesAllSubtypes.getSelection()) rigidTableComposite.setVisible(true);
	    	  else rigidTableComposite.setVisible(false);
	      }
	    };
	    btnYesAllSubtypes.addSelectionListener(listener2);  		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnYesAllSubtypes.getSelection()){
			
			return ((GSRigWizard)getWizard()).getThirdPage();
		}
				
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}