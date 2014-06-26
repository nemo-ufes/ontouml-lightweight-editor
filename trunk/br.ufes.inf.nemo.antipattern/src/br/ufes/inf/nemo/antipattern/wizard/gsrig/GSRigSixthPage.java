package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigSixthPage extends GSRigPage {
	private StyledText questionText;
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
								    
		questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setText("Please choose the new anti-rigid stereotype of "+gsrig.getGs().getGeneralization().get(0).getGeneral().getName()+"."+
							"\r\n\r\n"+
							"Please remember that, if "+gsrig.getGs().getGeneralization().get(0).getGeneral().getName()+"is anti-rigid, then ALL of its subtypes must also be anti-rigid, including the ones that are not in the generalization set currently under the microscope. Is that true? If it is, choose the new stereotypes for the rigid supertypes.");
		questionText.setJustify(true);
		questionText.setAlwaysShowScrollBars(false);
		
		btnYesAllSubtypes = new Button(container, SWT.CHECK);
		btnYesAllSubtypes.setText("Yes, all subtypes are anti-rigid.");
		
		rigidTableComposite = new RigidTableComposite(container,SWT.NONE,gsrig);
		rigidTableComposite.setVisible(true);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(btnYesAllSubtypes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(9))
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, questionText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, rigidTableComposite, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(questionText, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnYesAllSubtypes)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(rigidTableComposite, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
			    
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