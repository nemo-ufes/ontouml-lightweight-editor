package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleSecondPage extends FreeRolePage {
	
	private StyledText questionText;
	private StyledText constraintText;
	private String currentInvariantText, currentDerivationText;
	private final String defaultInvariantText, defaultDerivationText;
	private Button btnInvariant;
	private Button btnDerivation;
	private Label lblNewLabel;
	private enum SELECTION {NONE, INVARIANT, DERIVATION}
	private SELECTION selection;
	
	public FreeRoleSecondPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription(	"Defined Role: " +freeRole.getDefinedRole().getName()+
						"\nCurrent Free Role: "+freeRole.getFreeRoles().get(index).getName());

		String quotedDefinedRole = occurrence.addQuotes(occurrence.getDefinedRole().getName());
		String quotedFreeRole = occurrence.addQuotes(occurrence.getFreeRoles().get(index).getName());
		
		defaultDerivationText ="context "+quotedFreeRole+" :: allInstances() : Set("+quotedFreeRole+")" +
								"\r\nbody : "+quotedDefinedRole+".allInstances()->select ( x | <INSERT CONDITION HERE>)";
		
		defaultInvariantText = 	"context "+quotedFreeRole+"" +
								"\r\ninv : <INSERT CONDITION HERE>";
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		
		currentDerivationText = defaultDerivationText;
		currentInvariantText = defaultInvariantText;
		
		questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY);
		
		questionText.setJustify(true);
		questionText.setBounds(10, 10, 554, 36);
		questionText.setText("Every derived type must be have an OCL constraint that define its instantiation, which can be achieved using an invariant or a derivation rule. " +
							"Which type of rule would you like to create? ");
		
//							"\r\nPlease complete the OCL template provided below to define how an instance of <"+definedRole+"> becomes an <"+freeRole+">: ");
		
		constraintText = new StyledText(container, SWT.BORDER | SWT.V_SCROLL);
		constraintText.setBounds(10, 149, 554, 92);
		constraintText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		btnInvariant = new Button(container, SWT.RADIO);
		btnInvariant.setBounds(10, 52, 554, 16);
		btnInvariant.setText("Invariant (supported by the simulation)");
		btnInvariant.addSelectionListener(constraintTextAdapter);
		
		btnDerivation = new Button(container, SWT.RADIO);
		btnDerivation.setText("Derivation");
		btnDerivation.setBounds(10, 74, 554, 16);
		btnDerivation.addSelectionListener(constraintTextAdapter);
		
		lblNewLabel = new Label(container, SWT.WRAP);
		lblNewLabel.setBounds(10, 113, 554, 30);
		lblNewLabel.setText("Please complete the OCL template below to define the instantiation of <"+occurrence.getFreeRoles().get(index).getName()+">:");
		
		setAsEnablingNextPageButton(btnDerivation);
		setAsEnablingNextPageButton(btnInvariant);
		setPageComplete(false);
		
		btnReset = new Button(container, SWT.NONE);
		btnReset.setBounds(495, 246, 69, 25);
		btnReset.setText("Reset");
		btnReset.setEnabled(false);
		btnReset.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(selection==SELECTION.INVARIANT)
					constraintText.setText(defaultInvariantText);
				else if(selection==SELECTION.DERIVATION)
					constraintText.setText(defaultDerivationText);
				
			}
		});
		
		selection = SELECTION.NONE;
	}
	   
	SelectionAdapter constraintTextAdapter = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			if(btnInvariant.getSelection()){
				
				if(selection == SELECTION.NONE){
					constraintText.setText(currentInvariantText);
				}
				
				else if(selection == SELECTION.DERIVATION){
					currentDerivationText = constraintText.getText();
					constraintText.setText(currentInvariantText);
				}
				
				selection = SELECTION.INVARIANT;
				btnReset.setEnabled(true);
			}
			else if (btnDerivation.getSelection()){
				
				if(selection == SELECTION.NONE){
					constraintText.setText(currentDerivationText);
				}
				
				else if(selection == SELECTION.INVARIANT){
					currentInvariantText = constraintText.getText();
					constraintText.setText(currentDerivationText);
				}
				
				selection = SELECTION.DERIVATION;
				btnReset.setEnabled(true);
			}
			
		}
	};
	private Button btnReset;
	
	
	@Override
	public IWizardPage getNextPage() 
	{			
		//Action =============================
		FreeRoleAction newAction = new FreeRoleAction(occurrence);
		newAction.setCreateOCLDerivation(occurrence.getFreeRoles().get(index),constraintText.getText()); 
		getAntipatternWizard().replaceAction(index,newAction);	
		//======================================
		
		if(index<occurrence.getFreeRoles().size()-1){
			return getAntipatternWizard().getFirstPage(index+1);
		}else{
			return getAntipatternWizard().getFinishing();
		}
	}
}
