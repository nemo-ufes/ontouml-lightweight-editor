package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.ParsingElement;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

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
		lblWhichDatatypesMust.setText("Which datatypes must be created to define the phases?");
		
		createDataTypeComposite = new CreateDataTypeComposite(container, SWT.NONE, (UndefPhaseOccurrence) occurrence, this);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, lblWhichDatatypesMust, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, createDataTypeComposite, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblWhichDatatypesMust)
					.add(17)
					.add(createDataTypeComposite, GroupLayout.DEFAULT_SIZE, 254, Short.MAX_VALUE)
					.add(8))
		);
		container.setLayout(gl_container);
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
			UndefPhaseAction newAction = new UndefPhaseAction(occurrence);			
			newAction.setCreateAttributes(names, types, stereotypes, cardinalities);
			getAntipatternWizard().replaceAction(0,newAction);	
			//======================================
			
			return getAntipatternWizard().getThirdPage();
		}
				
		return getAntipatternWizard().getFinishing();
	}
}