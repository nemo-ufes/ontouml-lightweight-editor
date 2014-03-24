package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.ParsingElement;

public class UndefPhaseSecondPage extends UndefPhasePage{
	
	Composite parent;
	private Label lblWhichDatatypesMust;
	CreateDataTypeComposite createDataTypeComposite;
	/**
	 * Create the wizard.
	 */
	public UndefPhaseSecondPage(UndefPhaseOccurrence up) 
	{
		super(up);
		setDescription((new ParsingElement(up.getPartition(),true,"")).toString());
	}
	
	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		lblWhichDatatypesMust = new Label(container, SWT.NONE);
		lblWhichDatatypesMust.setBounds(10, 10, 541, 15);
		lblWhichDatatypesMust.setText("Which datatypes must be created to define the phases?");
		
		createDataTypeComposite = new CreateDataTypeComposite(container, SWT.NONE, (UndefPhaseOccurrence) up);
		createDataTypeComposite.setBounds(10, 42, 554, 254);		
	}
	
	@Override
	public IWizardPage getNextPage() {
		if(createDataTypeComposite.getValues().keySet().size()>=1)
		{
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> types = new ArrayList<String>();
			ArrayList<String> stereotypes = new ArrayList<String>();
			ArrayList<String> cardinalities = new ArrayList<String>();
			names.addAll(createDataTypeComposite.getValues().keySet());
			types.addAll(createDataTypeComposite.getValues().values());
			stereotypes.addAll(createDataTypeComposite.getStereotypes().values());
			cardinalities.addAll(createDataTypeComposite.getCardinalities().values());
			
			//Action =============================
			UndefPhaseAction newAction = new UndefPhaseAction(up);			
			newAction.setCreateAttributes(names, types, stereotypes, cardinalities);
			getUndefPhaseWizard().replaceAction(0,newAction);	
			//======================================
			
			return getUndefPhaseWizard().getThirdPage();
		}
				
		return getUndefPhaseWizard().getFinishing();
	}
}