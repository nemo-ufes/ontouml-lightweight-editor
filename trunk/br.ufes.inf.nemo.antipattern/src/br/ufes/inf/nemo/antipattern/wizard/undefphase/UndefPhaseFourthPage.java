package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.Classifier;
import RefOntoUML.parser.ParsingElement;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

public class UndefPhaseFourthPage  extends UndefPhasePage{
	
	Composite parent;
	private Label lblPhasesCanAlso;
	private Button noButton;
	private Button yesButton;
	private CreateModeComposite createModeComposite;
	
	/**
	 * Create the wizard.
	 */
	public UndefPhaseFourthPage(UndefPhaseOccurrence up) 
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
		
		lblPhasesCanAlso = new Label(container, SWT.WRAP);
		lblPhasesCanAlso.setText("Phases can also be defined by the appearance of modes. For example, a kind Person may own a partition containing the Sick and Healthy phases. A Sick Person is one that has a mode Disease. \r\n\r\nIs that is the case?");
		
		noButton = new Button(container, SWT.RADIO);
		noButton.setText("No");
		
		yesButton = new Button(container, SWT.RADIO);
		yesButton.setText("Yes");
		
		createModeComposite = new CreateModeComposite(container, SWT.NONE, (UndefPhaseOccurrence) occurrence);
		createModeComposite.setVisible(false);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, createModeComposite, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, yesButton, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, noButton, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, lblPhasesCanAlso, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblPhasesCanAlso, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.add(9)
					.add(noButton)
					.add(3)
					.add(yesButton)
					.add(18)
					.add(createModeComposite, GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		SelectionAdapter listener = new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		    	  if(yesButton.getSelection()) createModeComposite.setVisible(true);
		    	  if(noButton.getSelection()) createModeComposite.setVisible(false);
		      }
		};				
		noButton.addSelectionListener(listener);
		yesButton.addSelectionListener(listener);
		setAsEnablingNextPageButton(noButton);
		setAsEnablingNextPageButton(yesButton);
		setPageComplete(false);
	}
	
	@Override
	public IWizardPage getNextPage() {
		if(yesButton.getSelection())
		{
			ArrayList<String> names = new ArrayList<String>();
			ArrayList<String> cardinalities = new ArrayList<String>();
			names.addAll(createModeComposite.getValues().keySet());
			cardinalities.addAll(createModeComposite.getValues().values());
			ArrayList<Classifier> phases = createModeComposite.getPhases();
			
			//Action =============================
			UndefPhaseAction newAction = new UndefPhaseAction(occurrence);			
			newAction.setCreateModes(names, cardinalities,phases);
			getAntipatternWizard().replaceAction(0,newAction);	
			//======================================
			
			return getAntipatternWizard().getFinishing();
		}
		if(noButton.getSelection())
		{
			return getAntipatternWizard().getFifthPage();
		}
		
		return getAntipatternWizard().getFinishing();
	}
}
