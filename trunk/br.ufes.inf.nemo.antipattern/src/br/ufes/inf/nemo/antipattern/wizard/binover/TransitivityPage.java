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

public class TransitivityPage extends BinOverPage {
	
	Button btnTransitive, btnIntransitive, btnNonTransitive;
	
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
	
	
	
	@Override
	public IWizardPage getNextPage(){
		BinaryPropertyValue transitivityValue;
		
		if(btnTransitive.getSelection())
			transitivityValue = BinaryPropertyValue.TRANSITIVE;
		else if(btnIntransitive.getSelection())
			transitivityValue = BinaryPropertyValue.INTRANSITIVE;
		else if(btnNonTransitive.getSelection())
			transitivityValue = BinaryPropertyValue.NON_TRANSITIVE;
		else
			transitivityValue = BinaryPropertyValue.NONE;
		
		getBinOverWizard().removeAllActions(0, BinOverAction.Action.SET_TRANSITIVITY);
		BinOverAction action = new BinOverAction(binOver);
		action.setTransitivity(transitivityValue);
		getBinOverWizard().addAction(0, action);
		
		if (getBinOverWizard().possibleStereotypes(BinaryProperty.Transitivity, transitivityValue).contains(getBinOverWizard().getCurrentStereotype(this))){
			getBinOverWizard().removeAllActions(3);
			return getBinOverWizard().getCyclicityPage();
		}
		else
			return getBinOverWizard().getTransitivityChangePage(transitivityValue);
	}

}
