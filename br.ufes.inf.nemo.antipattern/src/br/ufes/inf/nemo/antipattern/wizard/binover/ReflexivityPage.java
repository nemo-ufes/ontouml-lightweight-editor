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
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryPropertyValue;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;

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

		setDescription("Binary Relation: "+OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+
				   "\nCurrent Stereotype: "+getBinOverWizard().getCurrentStereotypeName(this));	
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		styledText.setText(	"Now, we begin the analysis focusing on the properties of binary relations. We start with reflexivity." +
							"\r\n\r\nWhat can be said about an individual being connected to itself through relation " +
							OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+"?");
		styledText.setJustify(true);
		styledText.setBackground(container.getBackground());
		styledText.setBounds(10, 10, 754, 65);
		
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
		if(!btnReflexive.getSelection() && !btnIrreflexive.getSelection() && !btnNonReflexive.getSelection())
			return null;
		
		if(btnReflexive.getSelection()){
			getBinOverWizard().reflexivity = BinaryPropertyValue.REFLEXIVE;
		}
		else if(btnIrreflexive.getSelection()) {
			getBinOverWizard().reflexivity = BinaryPropertyValue.ANTI_REFLEXIVE;
		}
		else if(btnNonReflexive.getSelection()) {
			getBinOverWizard().reflexivity = BinaryPropertyValue.NON_REFLEXIVE;
		}
		
		if (getBinOverWizard().possibleStereotypes(getBinOverWizard().reflexivity).contains(binOver.getAssociation().getClass())){
			BinOverAction action = new BinOverAction(binOver);
			action.setBinaryProperty(getBinOverWizard().reflexivity);
			getBinOverWizard().replaceAction(1, action);
			return getBinOverWizard().getSymmetryPage();
		}
		else{
			return getBinOverWizard().getReflexivityChangePage(getBinOverWizard().reflexivity);
		}
	}

}
