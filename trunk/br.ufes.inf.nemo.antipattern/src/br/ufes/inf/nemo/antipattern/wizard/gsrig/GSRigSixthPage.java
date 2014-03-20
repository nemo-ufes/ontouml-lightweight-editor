package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigSixthPage extends GSRigPage {
	
	private Label lblNewLabel;
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
		lblNewLabel.setText("Please choose the new anti-rigid stereotype of "+gsrig.getGs().getGeneralization().get(0).getGeneral().getName()+".");
								    
		lblAlsoIfis = new Label(container, SWT.WRAP);
		lblAlsoIfis.setBounds(10, 43, 644, 51);
		lblAlsoIfis.setText("Also, if "+gsrig.getGs().getGeneralization().get(0).getGeneral().getName()+"is anti-rigid, then ALL of its subtypes must also be anti-rigid, including the ones that are not in the generalization set currently under the microscope. Is that true? If it is, choose the new stereotypes for the rigid supertypes.");
		
//		Composite composite_1 = new Composite(container, SWT.NONE);
//		composite_1.setBounds(10, 167, 644, 105);
				
		btnYesAllSubtypes = new Button(container, SWT.CHECK);
		btnYesAllSubtypes.setBounds(10, 100, 644, 16);
		btnYesAllSubtypes.setText("Yes, all subtypes are anti-rigid.");
		
		rigidTableComposite = new RigidTableComposite(container,SWT.NONE,gsrig);
		rigidTableComposite.setBounds(0, 134, 654, 105);
		rigidTableComposite.setVisible(true);
			    
	    SelectionAdapter listener2 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  if(btnYesAllSubtypes.getSelection()) rigidTableComposite.setVisible(true);
	    	  else rigidTableComposite.setVisible(false);
	      }
	    };
	    btnYesAllSubtypes.addSelectionListener(listener2);  		
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
		Classifier supertype = gsrig.getGs().getGeneralization().get(0).getGeneral();
		boolean createNewSupertype = false;
		if (supertype instanceof Kind || supertype instanceof Quantity || supertype instanceof Collective){
			createNewSupertype = true;
		}
		if(btnYesAllSubtypes.getSelection())
		{			
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setChangeSupertypeAndRigidSpecificsStereotypesTo(rigidTableComposite.stereoCombo.getText(), rigidTableComposite.getRigidTable().getNewStereotypes(), createNewSupertype); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
		}
				
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}