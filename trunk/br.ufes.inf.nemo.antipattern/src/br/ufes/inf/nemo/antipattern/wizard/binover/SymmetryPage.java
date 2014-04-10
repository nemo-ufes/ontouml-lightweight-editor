package br.ufes.inf.nemo.antipattern.wizard.binover;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryPropertyValue;

public class SymmetryPage extends BinOverPage {

	Button btnSymmetric, btnAsymmetric, btnNonSymmetric;
	private Label lblCurrentValues;
	
	/**
	 * Create the wizard.
	 */
	public SymmetryPage(BinOverOccurrence binOver) {
		super("SymmetryPage", binOver);		
		setTitle(BinOverAntipattern.getAntipatternInfo().getName()+" - Symmetry");
		
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);

		setDescription("Binary Relation: "+getRelationName()+
				   "\nCurrent Stereotype: "+getBinOverWizard().getCurrentStereotypeName(this));	
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setText(	"Now lets analyze the symmetry of <"+getRelationName()+">. " +
							"\r\n\r\n" +
							"Consider two individuals, x and y, both instances of <"+binOver.getSource().getName()+"> " +
							"and <"+binOver.getTarget().getName()+">. " +
							"If x, acting as <"+getSourceEndName()+">, is connected to y, " +
							"acting as <"+getTargetEndName()+">, " +
							"through <"+getRelationName()+"> " +
							"does it mean that y must also act as <"+getSourceEndName()+"> " +
							"and be connected to x acting as <"+getTargetEndName()+">?");
		
		styledText.setJustify(true);
		styledText.setBackground(container.getBackground());
		styledText.setBounds(10, 10, 554, 114);
		
		btnSymmetric = new Button(container, SWT.RADIO);
		btnSymmetric.setBounds(10, 130, 554, 16);
		btnSymmetric.setText("Yes (Symmetric)");
		
		btnAsymmetric = new Button(container, SWT.RADIO);
		btnAsymmetric.setText("No and it is forbidden (Anti-Symmetric)");
		btnAsymmetric.setBounds(10, 152, 554, 16);
		
		btnNonSymmetric = new Button(container, SWT.RADIO);
		btnNonSymmetric.setText("It does not imply, but it is possible (Non-symmetric)");
		btnNonSymmetric.setBounds(10, 173, 554, 16);
		
		lblCurrentValues = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblCurrentValues.setBounds(10, 256, 554, 15);
		lblCurrentValues.setText("Reflexivity = ");
		
		setPageComplete(false);
		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				if((btnSymmetric.getSelection() || btnAsymmetric.getSelection() || btnNonSymmetric.getSelection()) && !isPageComplete())
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		};
		
		btnSymmetric.addSelectionListener(listener);
		btnAsymmetric.addSelectionListener(listener);
		btnNonSymmetric.addSelectionListener(listener);
	}

	public void updateUI(){
		lblCurrentValues.setText("Reflexivity = "+getBinOverWizard().reflexivity);
	}
	
	@Override
	public IWizardPage getNextPage(){
			
		if(btnSymmetric.getSelection())
			getBinOverWizard().symmetry = BinaryPropertyValue.SYMMETRIC;
		else if(btnAsymmetric.getSelection())
			getBinOverWizard().symmetry = BinaryPropertyValue.ASYMMETRIC;
		else if(btnNonSymmetric.getSelection())
			getBinOverWizard().symmetry = BinaryPropertyValue.NON_SYMMETRIC;
		else
			getBinOverWizard().symmetry = BinaryPropertyValue.NONE;
		
		getBinOverWizard().removeAllActions(0, BinOverAction.Action.SET_BINARY_PROPERTY);
		BinOverAction action = new BinOverAction(binOver);
		action.setBinaryProperty(getBinOverWizard().symmetry);
		getBinOverWizard().addAction(0, action);
			
		if ( getBinOverWizard().possibleStereotypes(getBinOverWizard().symmetry).contains(getBinOverWizard().getCurrentStereotype(this))){
			getBinOverWizard().removeAllActions(2);
			return getBinOverWizard().getTransitivityPage();
		}
		else
			return getBinOverWizard().getSymmetryChangePage(getBinOverWizard().symmetry);
		
	}
	
}
