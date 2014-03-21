package br.ufes.inf.nemo.antipattern.wizard.undefphase;

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
		createDataTypeComposite.setBounds(10, 42, 554, 164);		
		
//		Composite composite = new Composite(container, SWT.NONE);
//		composite.setBounds(10, 42, 541, 130);
	}
}