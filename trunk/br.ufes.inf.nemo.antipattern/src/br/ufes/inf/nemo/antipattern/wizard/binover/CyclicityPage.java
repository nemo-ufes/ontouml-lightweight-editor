package br.ufes.inf.nemo.antipattern.wizard.binover;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryProperty;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryPropertyValue;

public class CyclicityPage extends BinOverPage {

	Button btnCyclic, btnAcyclic, btnNonCyclic;
	
	/**
	 * Create the wizard.
	 */
	public CyclicityPage(BinOverOccurrence binOver) {
		super("TransitivityPage", binOver);
		
		setTitle(BinOverAntipattern.getAntipatternInfo().getName()+" - Cyclicity");
		
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
		styledText.setText(	"The last binary property we analyze in this antipattern is the Ciclicity."+
							"\r\n\r\n" +
							"Consider three distinct individuals: a, b and c. " +
							"If <"+getRelationName()+"> connects a to b, and also b to c, " +
							"we can imply that the relation: ");
			
		styledText.setJustify(true);
		styledText.setBackground(container.getBackground());
		styledText.setBounds(10, 10, 554, 82);
				
		btnCyclic = new Button(container, SWT.RADIO);
		btnCyclic.setBounds(10, 98, 554, 16);
		btnCyclic.setText("Connects c to a (Cyclic)");
		
		btnAcyclic = new Button(container, SWT.RADIO);
		btnAcyclic.setText("DOES NOT connect c to a (Acyclic)");
		btnAcyclic.setBounds(10, 120, 554, 16);
		
		btnNonCyclic = new Button(container, SWT.RADIO);
		btnNonCyclic.setText("Nothing (Non-Cyclic)");
		btnNonCyclic.setBounds(10, 141, 554, 16);
		
		setPageComplete(false);
		
		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				if((btnCyclic.getSelection() || btnAcyclic.getSelection() || btnNonCyclic.getSelection()) && !isPageComplete())
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		};
		
		btnCyclic.addSelectionListener(listener);
		btnAcyclic.addSelectionListener(listener);
		btnNonCyclic.addSelectionListener(listener);
	}
	
	@Override
	public IWizardPage getNextPage(){
		BinaryPropertyValue cyclicityValue;
		
		if(btnCyclic.getSelection())
			cyclicityValue = BinaryPropertyValue.CYCLIC;
		else if(btnAcyclic.getSelection())
			cyclicityValue = BinaryPropertyValue.ACYCLIC;
		else if(btnNonCyclic.getSelection())
			cyclicityValue = BinaryPropertyValue.NON_CYCLIC;
		else
			cyclicityValue = BinaryPropertyValue.NONE;
		
		getBinOverWizard().removeAllActions(0, BinOverAction.Action.SET_CYCLICITY);
		BinOverAction action = new BinOverAction(binOver);
		action.setCyclicity(cyclicityValue);
		getBinOverWizard().addAction(0, action);
		
		if (getBinOverWizard().possibleStereotypes(BinaryProperty.Cyclicity, cyclicityValue).contains(getBinOverWizard().getCurrentStereotype(this))){
			getBinOverWizard().removeAllActions(4);
			return getBinOverWizard().getFinishing();
		}
		else
			return getBinOverWizard().getCyclicityChangePage(cyclicityValue);
	}

}
