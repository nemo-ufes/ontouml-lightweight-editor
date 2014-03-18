package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigFirstPage extends GSRigPage {

	private Button btnNoMultiple;
	private Button btnNoCommon;
	private Button btnYes;
	private StyledText styledText;
	
	public GSRigFirstPage(GSRigOccurrence gsrig) 
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
		
		styledText = new StyledText(container, SWT.WRAP| SWT.V_SCROLL);
		styledText.setText("A Generalization Set (GS) is a group of Generalizations of a common supertype. " +
		"It captures a common criterion of specialization for all subtypes and restricts their instantiation. " +
		"It has two boolean meta-attributes: \r\n\r\nisCovering: if true, every instance of the supertype must " +
		"be an instance of at least one of the subtypes in the generalization set.\r\n\r\nisDisjoint: if true, " +
		"no instance of the supertype may instantiante more than one subtype in the generalization set simultaneously." +
		"\r\n\r\nThe GS <GS> of the rigid super-type "+gsrig.getGs().getGeneralization().get(0).getGeneral().getName()+", contains some subtypes that are rigid and some " +
		"that are anti-rigid. That indicates that there might be something wrong with the model.\r\n\r\nThe starting point " +
		"of the analysis is to verify if the generalization set was correctly specified. To do that, think about the specialization " +
		"criteria you chose for creating it. Is it the same for all subtypes?\r\n");
		
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBounds(10, 10, 643, 173);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 189, 643, 16);
		btnYes.setText("Yes");
		
		btnNoMultiple = new Button(container, SWT.RADIO);
		btnNoMultiple.setBounds(10, 211, 643, 16);
		btnNoMultiple.setText("No, there are multiple criteria");
		
		btnNoCommon = new Button(container, SWT.RADIO);
		btnNoCommon.setBounds(10, 233, 643, 16);
		btnNoCommon.setText("No, there is no common criteria");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnYes.getSelection()){
			
			return ((GSRigWizard)getWizard()).getThirdPage();
		}
		
		if(btnNoMultiple.getSelection()){
			
			return ((GSRigWizard)getWizard()).getSecondPage();
		}
		
		if(btnNoCommon.getSelection())
		{			
			//Action =============================
			GSRigAction newAction = new GSRigAction(gsrig);
			newAction.setDeleteGS(); 
			getGSRigWizard().replaceAction(0,newAction);	
			//======================================			
		}
		
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}