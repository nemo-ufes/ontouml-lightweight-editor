package br.ufes.inf.nemo.antipattern.wizard.binover;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
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

		setDescription("Binary Relation: "+OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+
				   "\nCurrent Stereotype: "+getBinOverWizard().getCurrentStereotypeName(this));	
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		styledText.setAlwaysShowScrollBars(false);
		styledText.setText(	"Now lets analyze the symmetry of "+OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+". " +
							"\r\n\r\n" +
							"Consider two individuals, x and y, both instances of "+OntoUMLNameHelper.getTypeAndName(binOver.getSource(), true, true)+" " +
							"and <"+OntoUMLNameHelper.getTypeAndName(binOver.getTarget(), true, true)+">. " +
							"If x, acting as "+OntoUMLNameHelper.getNameAndType(binOver.getAssociation().getMemberEnd().get(0))+", is connected to y, " +
							"acting as <"+OntoUMLNameHelper.getNameAndType(binOver.getAssociation().getMemberEnd().get(1))+">, " +
							"through "+OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+" " +
							"does it mean that y must also act as "+OntoUMLNameHelper.getNameAndType(binOver.getAssociation().getMemberEnd().get(0))+" " +
							"and be connected to x acting as "+OntoUMLNameHelper.getNameAndType(binOver.getAssociation().getMemberEnd().get(1))+"?");
		
		styledText.setJustify(true);
		styledText.setBackground(container.getBackground());
		
		btnSymmetric = new Button(container, SWT.RADIO);
		btnSymmetric.setText("Yes (Symmetric)");
		
		btnAsymmetric = new Button(container, SWT.RADIO);
		btnAsymmetric.setText("No and it is forbidden (Anti-Symmetric)");
		
		btnNonSymmetric = new Button(container, SWT.RADIO);
		btnNonSymmetric.setText("It does not imply, but it is possible (Non-symmetric)");
		
		lblCurrentValues = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblCurrentValues.setText("Reflexivity = ");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(btnSymmetric, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(btnAsymmetric, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(btnNonSymmetric, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblCurrentValues, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnSymmetric)
					.add(6)
					.add(btnAsymmetric)
					.add(5)
					.add(btnNonSymmetric)
					.add(61)
					.add(lblCurrentValues)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		
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
			
		if(!btnSymmetric.getSelection() && !btnAsymmetric.getSelection() && !btnNonSymmetric.getSelection())
			return null;
		
		if(btnSymmetric.getSelection())
			getBinOverWizard().symmetry = BinaryPropertyValue.SYMMETRIC;
		else if(btnAsymmetric.getSelection())
			getBinOverWizard().symmetry = BinaryPropertyValue.ASYMMETRIC;
		else if(btnNonSymmetric.getSelection())
			getBinOverWizard().symmetry = BinaryPropertyValue.NON_SYMMETRIC;

		if ( getBinOverWizard().possibleStereotypes(getBinOverWizard().symmetry).contains(getBinOverWizard().getCurrentStereotype(this))){
			BinOverAction action = new BinOverAction(binOver);
			action.setBinaryProperty(getBinOverWizard().symmetry);
			getBinOverWizard().replaceAction(2, action);
			return getBinOverWizard().getTransitivityPage();
		}
		else
			return getBinOverWizard().getSymmetryChangePage(getBinOverWizard().symmetry);
		
	}
	
}
