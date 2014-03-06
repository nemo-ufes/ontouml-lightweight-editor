package br.ufes.inf.nemo.antipattern.wizard.binover;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryPropertyValue;

public class TransitivityPage extends BinOverPage {
	
	Button btnTransitive, btnIntransitive, btnNonTransitive;
	private Label lblIncompatibility;
	private Label lblCurrentValues;
	
	/**
	 * Create the wizard.
	 */
	public TransitivityPage(BinOverOccurrence binOver) {
		super("TransitivityPage", binOver);
		
		setTitle(BinOverAntipattern.getAntipatternInfo().getName()+" - Transitivity");
		
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
		styledText.setText(	"Now, lets analyze <"+getRelationName()+"> focusing on its transitivity." +
							"\r\n\r\n" +
							"Consider three distinct individuals: a, b and c. If <"+getRelationName()+"> " +
							"connects a to b, and also b to c, we can imply that the relation: ");
		
		styledText.setJustify(true);
		styledText.setBackground(container.getBackground());
		styledText.setBounds(10, 10, 554, 82);
		
		btnTransitive = new Button(container, SWT.RADIO);
		btnTransitive.setBounds(10, 98, 554, 16);
		btnTransitive.setText("Connects a to c (Transitive)");
		
		btnIntransitive = new Button(container, SWT.RADIO);
		btnIntransitive.setText("DOES NOT connect a to c (Intransitive)");
		btnIntransitive.setBounds(10, 120, 554, 16);
		
		btnNonTransitive = new Button(container, SWT.RADIO);
		btnNonTransitive.setText("Nothing (Non-Transitive)");
		btnNonTransitive.setBounds(10, 141, 554, 16);
		
		lblIncompatibility = new Label(container, SWT.WRAP);
		lblIncompatibility.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblIncompatibility.setBounds(10, 163, 554, 16);
		lblIncompatibility.setText(	"(One or more options disable due to incompatibility with your previous answers.)");
		
		lblCurrentValues = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblCurrentValues.setBounds(10, 256, 554, 15);
		lblCurrentValues.setText("Reflexivity = , Symmetry = ");
		
		setPageComplete(false);
		
		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				if((btnTransitive.getSelection() || btnIntransitive.getSelection() || btnNonTransitive.getSelection()) && !isPageComplete())
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		};
		
		btnTransitive.addSelectionListener(listener);
		btnIntransitive.addSelectionListener(listener);
		btnNonTransitive.addSelectionListener(listener);
	}
	
	public void updateUI(){
		
		BinaryPropertyValue reflexivityValue = getBinOverWizard().reflexivity, 
							symmetryValue = getBinOverWizard().symmetry;
		
		boolean transitiveAvailable = BinOverOccurrence.validCombination(reflexivityValue, symmetryValue, BinaryPropertyValue.TRANSITIVE, BinaryPropertyValue.NONE),
				intransitiveAvailable = BinOverOccurrence.validCombination(reflexivityValue, symmetryValue, BinaryPropertyValue.ANTI_TRANSITIVE, BinaryPropertyValue.NONE),
				nonTransitiveAvailable = BinOverOccurrence.validCombination(reflexivityValue, symmetryValue, BinaryPropertyValue.NON_TRANSITIVE, BinaryPropertyValue.NONE);
		
		if (!transitiveAvailable){
			btnTransitive.setEnabled(false);
			btnTransitive.setSelection(false);
		}
		else
			btnTransitive.setEnabled(true);
		
		if (!intransitiveAvailable){
			btnIntransitive.setEnabled(false);
			btnIntransitive.setSelection(false);
		}
		else
			btnIntransitive.setEnabled(true);
		
		if (!nonTransitiveAvailable){
			btnNonTransitive.setEnabled(false);
			btnNonTransitive.setSelection(false);
		}
		else
			btnNonTransitive.setEnabled(true);
		
		lblCurrentValues.setText("Reflexivity = "+getBinOverWizard().reflexivity+", Symmetry = "+getBinOverWizard().symmetry);
		
		if(!transitiveAvailable || !intransitiveAvailable || !nonTransitiveAvailable){
			lblIncompatibility.setVisible(true);
			lblCurrentValues.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		}
		else{
			lblIncompatibility.setVisible(false);
			lblCurrentValues.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
		}
	}
	
	@Override
	public IWizardPage getNextPage(){
		
		
		if(btnTransitive.getSelection())
			getBinOverWizard().transitivity = BinaryPropertyValue.TRANSITIVE;
		else if(btnIntransitive.getSelection())
			getBinOverWizard().transitivity = BinaryPropertyValue.ANTI_TRANSITIVE;
		else if(btnNonTransitive.getSelection())
			getBinOverWizard().transitivity = BinaryPropertyValue.NON_TRANSITIVE;
		else
			getBinOverWizard().transitivity = BinaryPropertyValue.NONE;
		
		getBinOverWizard().removeAllActions(0, BinOverAction.Action.SET_BINARY_PROPERTY);
		BinOverAction action = new BinOverAction(binOver);
		action.setBinaryProperty(getBinOverWizard().transitivity);
		getBinOverWizard().addAction(0, action);
		
		if (getBinOverWizard().possibleStereotypes(getBinOverWizard().transitivity).contains(getBinOverWizard().getCurrentStereotype(this))){
			getBinOverWizard().removeAllActions(3);
			return getBinOverWizard().getCyclicityPage();
		}
		else
			return getBinOverWizard().getTransitivityChangePage(getBinOverWizard().transitivity);
	}

}
