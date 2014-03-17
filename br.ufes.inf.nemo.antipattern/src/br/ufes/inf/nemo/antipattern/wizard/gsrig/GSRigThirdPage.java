package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigThirdPage extends GSRigPage {
	private StyledText lblNowThatWe;
	private Button btnAntiRigid;
	private Button btnSemiRigid;
	private Button btnRigid;

	public GSRigThirdPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		lblNowThatWe = new StyledText(container, SWT.WRAP | SWT.READ_ONLY);
		lblNowThatWe.setBounds(10, 10, 614, 48);
		lblNowThatWe.setText("Now that we established that all subtypes belong to the same generalization set, let's verify the rigidity of the GS's supertype. The options below are the possible rigidity options for <RigidSuperType>:");
		lblNowThatWe.setBackground(lblNowThatWe.getParent().getBackground());
		lblNowThatWe.setJustify(true);

		btnRigid = new Button(container, SWT.RADIO);
		btnRigid.setBounds(10, 68, 614, 16);
		btnRigid.setText("Rigid: if x instantiates it in a given moment, it must always do so in every possible ");
		
		btnAntiRigid = new Button(container, SWT.RADIO);
		btnAntiRigid.setBounds(10, 90, 614, 16);
		btnAntiRigid.setText("Anti-Rigid: if x instantiates it in a given moment, there is at least one possible situation in which x does not do so");
		
		btnSemiRigid = new Button(container, SWT.RADIO);
		btnSemiRigid.setBounds(10, 111, 614, 16);
		btnSemiRigid.setText("Semi-Rigid: the type may act as rigid for some individuals and anti-rigid for others");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnRigid.getSelection()){
			return ((GSRigWizard)getWizard()).getFourthPage();
		}
		
		if(btnAntiRigid.getSelection()){
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			//newAction.setChangeAllToComponentOf(assocList); 
			getGSRigWizard().replaceAction(0,newAction);
			//======================================
		}
		
		if(btnSemiRigid.getSelection())
		{			
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			//newAction.setChangeAllToComponentOf(assocList); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================
			
			return ((GSRigWizard)getWizard()).getFinishing();
		}
		
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}