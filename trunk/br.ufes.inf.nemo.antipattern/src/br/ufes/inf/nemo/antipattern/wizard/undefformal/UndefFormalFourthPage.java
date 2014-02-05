package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class UndefFormalFourthPage extends UndefFormalPage{

	public Composite parent;
	private CreateDataTypeComposite createDataTypeComposite;
	
	/**
	 * Create the wizard.
	 */
	public UndefFormalFourthPage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription("Source: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		
		Composite container = new Composite(parent, SWT.NONE);		
		setControl(container);
		
		createDataTypeComposite = new CreateDataTypeComposite(container, SWT.NONE, (UndefFormalOccurrence) uf);
		createDataTypeComposite.setBounds(10, 10, 554, 262);		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		//Action =============================
		UndefFormalAction newAction = new UndefFormalAction(uf);
		newAction.setCreateDatatypesAttributesAndRules(uf.getFormal(),uf.getSource(),uf.getTarget(),
			createDataTypeComposite.getSourceMapType(), createDataTypeComposite.getTargetMapType(), createDataTypeComposite.getSourceMapStereo(), 
			createDataTypeComposite.getTargetMapStereo(), createDataTypeComposite.getConstraints());
		getUndefFormalWizard().replaceAction(0,newAction);	
		//======================================
		
		return ((UndefFormalWizard)getWizard()).getFinishing();	
	}
}

