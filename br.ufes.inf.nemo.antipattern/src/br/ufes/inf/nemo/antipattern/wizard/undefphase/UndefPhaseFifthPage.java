package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.ParsingElement;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

public class UndefPhaseFifthPage extends UndefPhasePage{
	
	Composite parent;
	private Button noButton;
	private Button yesButton;
	private Label lblIfTheTypes;
	private ChangeStereoTable changeStereoTable;
	
	/**
	 * Create the wizard.
	 */
	public UndefPhaseFifthPage(UndefPhaseOccurrence up) 
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
		
		lblIfTheTypes = new Label(container, SWT.WRAP);
		lblIfTheTypes.setText("If the types in the partition are not defined by changes in quality values and also not defined by mode appearances, they might not be phases. Would you like to change their stereotypes?");
		
		noButton = new Button(container, SWT.RADIO);
		noButton.setText("No, keep the phases undefined [Not Recommended]");
		
		yesButton = new Button(container, SWT.RADIO);
		yesButton.setText("Yes");
		
		changeStereoTable = new ChangeStereoTable(container,SWT.BORDER | SWT.V_SCROLL,occurrence);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblIfTheTypes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(noButton, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(yesButton, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblIfTheTypes, GroupLayout.PREFERRED_SIZE, 50, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(noButton)
					.add(10)
					.add(yesButton))
		);
		container.setLayout(gl_container);
		changeStereoTable.getTable().setBounds(10, 122, 297, 150);
		
		SelectionAdapter listener = new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		    	  if(yesButton.getSelection()) changeStereoTable.getTable().setVisible(true);
		    	  if(noButton.getSelection()) changeStereoTable.getTable().setVisible(false);
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
			//Action =============================
			UndefPhaseAction newAction = new UndefPhaseAction(occurrence);			
			newAction.setChangeStereotypes(changeStereoTable.getStereotypes());
			getAntipatternWizard().replaceAction(0,newAction);	
			//======================================
			
			getAntipatternWizard().getFinishing();
		}
		if(noButton.getSelection())
		{
			getAntipatternWizard().getFinishing();
		}
		
		return getAntipatternWizard().getFinishing();
	}
}
