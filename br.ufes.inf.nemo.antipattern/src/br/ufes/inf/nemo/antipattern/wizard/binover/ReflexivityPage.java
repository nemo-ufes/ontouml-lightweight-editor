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

public class ReflexivityPage extends BinOverPage {

	Button btnReflexive, btnIrreflexive, btnNonReflexive;
	
	public ReflexivityPage(BinOverOccurrence binOver) {
		super("ReflexivityPage", binOver);
		
		setTitle(BinOverAntipattern.getAntipatternInfo().getName()+" - Reflexivity");
		
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
		styledText.setText(	"Now, we begin the analysis focusing on the properties of binary relations. We start with reflexivity." +
							"\r\n\r\nWhat can be said about an individual being connected to itself through relation " +
							"<"+getRelationName()+">?");
		styledText.setJustify(true);
		styledText.setBackground(container.getBackground());
		styledText.setBounds(10, 10, 554, 65);
		
		btnReflexive = new Button(container, SWT.RADIO);
		btnReflexive.setBounds(10, 81, 554, 16);
		btnReflexive.setText("It is a NECESSARY condition (Reflexive)");
		
		btnIrreflexive = new Button(container, SWT.RADIO);
		btnIrreflexive.setText("It is FORBIDDEN (Irreflexive)");
		btnIrreflexive.setBounds(10, 103, 554, 16);
		
		btnNonReflexive = new Button(container, SWT.RADIO);
		btnNonReflexive.setText("It is POSSIBLE (NonReflexive)");
		btnNonReflexive.setBounds(10, 124, 554, 16);
		
		setPageComplete(false);
		
		SelectionAdapter listener = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				if((btnReflexive.getSelection() || btnIrreflexive.getSelection() || btnNonReflexive.getSelection()) && !isPageComplete())
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		};
		
		btnReflexive.addSelectionListener(listener);
		btnIrreflexive.addSelectionListener(listener);
		btnNonReflexive.addSelectionListener(listener);
	}
	
	@Override
	public IWizardPage getNextPage(){
		BinaryPropertyValue reflexivityValue;
		
		if(btnReflexive.getSelection()){
			reflexivityValue = BinaryPropertyValue.REFLEXIVE;
		}
		else if(btnIrreflexive.getSelection()) {
			reflexivityValue = BinaryPropertyValue.IRREFLEXIVE;
		}
		else if(btnNonReflexive.getSelection()) {
			reflexivityValue = BinaryPropertyValue.NON_REFLEXIVE;
		}
		else {
			reflexivityValue = BinaryPropertyValue.NONE;
		}
		
		getBinOverWizard().removeAllActions(0, BinOverAction.Action.SET_REFLEXIVITY);
		BinOverAction action = new BinOverAction(binOver);
		action.setReflexivity(reflexivityValue);
		getBinOverWizard().addAction(0, action);
		
		if (getBinOverWizard().possibleStereotypes(BinaryProperty.Reflexivity, reflexivityValue).contains(binOver.getAssociation().getClass())){
			getBinOverWizard().removeAllActions(1);
			return getBinOverWizard().getSymmetryPage();
		}
		else{
			return getBinOverWizard().getReflexivityChangePage(reflexivityValue);
		}
	}

}
