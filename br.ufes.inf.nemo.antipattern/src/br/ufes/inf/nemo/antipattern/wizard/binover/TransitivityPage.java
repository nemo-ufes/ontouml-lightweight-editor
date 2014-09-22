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
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.parser.OntoUMLNameHelper;
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
		
		setDescription("Binary Relation: "+OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+
				   "\nCurrent Stereotype: "+getBinOverWizard().getCurrentStereotypeName(this));	
				
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		styledText.setAlwaysShowScrollBars(false);
		styledText.setText(	"Now, lets analyze "+OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+" focusing on its transitivity." +
							"\r\n\r\n" +
							"Consider three distinct individuals: a, b and c. If "+OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+" " +
							"connects a to b, and also b to c, we can imply that the relation: ");
		
		styledText.setJustify(true);
		styledText.setBackground(container.getBackground());
		
		btnTransitive = new Button(container, SWT.RADIO);
		btnTransitive.setText("Connects a to c (Transitive)");
		
		btnIntransitive = new Button(container, SWT.RADIO);
		btnIntransitive.setText("DOES NOT connect a to c (Intransitive)");
		
		btnNonTransitive = new Button(container, SWT.RADIO);
		btnNonTransitive.setText("Nothing (Non-Transitive)");
		
		lblIncompatibility = new Label(container, SWT.WRAP);
		lblIncompatibility.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblIncompatibility.setText(	"(One or more options disable due to incompatibility with your previous answers.)");
		
		lblCurrentValues = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblCurrentValues.setText("Reflexivity = , Symmetry = ");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(styledText, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
						.add(btnTransitive, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(btnIntransitive, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(btnNonTransitive, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(lblIncompatibility, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE)
						.add(GroupLayout.TRAILING, lblCurrentValues, GroupLayout.PREFERRED_SIZE, 754, GroupLayout.PREFERRED_SIZE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnTransitive)
					.add(6)
					.add(btnIntransitive)
					.add(5)
					.add(btnNonTransitive)
					.add(6)
					.add(lblIncompatibility, GroupLayout.PREFERRED_SIZE, 16, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED, 86, Short.MAX_VALUE)
					.add(lblCurrentValues)
					.add(10))
		);
		container.setLayout(gl_container);
		
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
		
		if(!btnTransitive.getSelection() && !btnIntransitive.getSelection() && !btnNonTransitive.getSelection())
			return null;
		
		if(btnTransitive.getSelection())
			getBinOverWizard().transitivity = BinaryPropertyValue.TRANSITIVE;
		else if(btnIntransitive.getSelection())
			getBinOverWizard().transitivity = BinaryPropertyValue.ANTI_TRANSITIVE;
		else if(btnNonTransitive.getSelection())
			getBinOverWizard().transitivity = BinaryPropertyValue.NON_TRANSITIVE;
		
		if (getBinOverWizard().possibleStereotypes(getBinOverWizard().transitivity).contains(getBinOverWizard().getCurrentStereotype(this))){
			BinOverAction action = new BinOverAction(binOver);
			action.setBinaryProperty(getBinOverWizard().transitivity);
			getBinOverWizard().replaceAction(3, action);
			return getBinOverWizard().getCyclicityPage();
		}
		else
			return getBinOverWizard().getTransitivityChangePage(getBinOverWizard().transitivity);
	}

}
