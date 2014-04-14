package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class CreateDataTypePage extends UndefFormalPage{

	public Composite parent;
	private CreateDataTypeComposite createDataTypeComposite;
	
	/**
	 * Create the wizard.
	 */
	public CreateDataTypePage(UndefFormalOccurrence uf) 
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
		
		createDataTypeComposite = new CreateDataTypeComposite(container, SWT.NONE, (UndefFormalOccurrence) occurrence);
		createDataTypeComposite.setBounds(10, 10, 554, 285);		
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
