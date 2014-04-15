package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import org.eclipse.swt.custom.StyledText;

public class CreateDataTypePage extends UndefFormalPage{

	public Composite parent;
	private DataTypeComposite createDataTypeComposite;
	
	/**
	 * Create the wizard.
	 */
	public CreateDataTypePage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription(	"Formal: "+uf.getFormalName()+
				"\nSource: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		
		Composite container = new Composite(parent, SWT.NONE);		
		setControl(container);
		
		createDataTypeComposite = new DataTypeComposite(container, SWT.NONE, (UndefFormalOccurrence) occurrence);
		createDataTypeComposite.setBounds(10, 109, 553, 334);		
		
		StyledText questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setText(	"If <"+occurrence.getFormalName()+"> is a Domain Comparative Formal Relation it means that both related ends should own or inherit qualities, " +
							"which is not currently the case for <"+occurrence.getSource().getName()+"> and <"+occurrence.getTarget().getName()+">. " +
							"\r\n\r\n" +
							"Please create the missing qualities and specify the OCL rule that derives the relation:");
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setJustify(true);
		questionText.setAlwaysShowScrollBars(false);
		questionText.setBounds(10, 10, 554, 93);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		//Action =============================
		UndefFormalAction newAction = new UndefFormalAction(occurrence);
		newAction.setCreateDatatypesAttributesAndRules(occurrence.getFormal(),occurrence.getSource(),occurrence.getTarget(),
			createDataTypeComposite.getSourceMapType(), createDataTypeComposite.getTargetMapType(), createDataTypeComposite.getSourceMapStereo(), 
			createDataTypeComposite.getTargetMapStereo(), createDataTypeComposite.getConstraints());
		getAntipatternWizard().replaceAction(0,newAction);	
		//======================================
		
		return ((UndefFormalWizard)getWizard()).getFinishing();	
	}
}

