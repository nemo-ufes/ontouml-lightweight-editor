package br.ufes.inf.nemo.antipattern.wizard.binover;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryProperty;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryPropertyValue;

public class ChangeStereotypePage extends BinOverPage {

	protected BinaryProperty propertyType;
	protected BinaryPropertyValue chosenPropertyValue, defaultPropertyValue;
	
	protected Composite container;
	protected StyledText styledText;
	protected Button btnKeep, btnChangeAndEnforce;
	protected CCombo combo;
	
	public ChangeStereotypePage(BinOverOccurrence occurrence, BinaryProperty propertyType) {
		super("ChangeStereotypePage", occurrence);
		
		this.propertyType = propertyType;
		
		setTitle(BinOverAntipattern.getAntipatternInfo().getName()+" - "+propertyType);
		
		
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
		public void createControl(Composite parent) {
			
			setDescription("Binary Relation: "+getRelationName()+
					   "\nCurrent Stereotype: "+getBinOverWizard().getCurrentStereotypeName(this));	
			
			container = new Composite(parent, SWT.NULL);
			setControl(container);
		
			SelectionAdapter listener = new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent arg0) {
					
					if(btnChangeAndEnforce.getSelection()){ 
						combo.setEnabled(true);
					}
					else if(btnKeep.getSelection()){ 
						combo.setEnabled(false);
					}
					
					if(!isPageComplete())
						setPageComplete(true);
				}
			};	
		
			setPageComplete(false);
			
			styledText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
			styledText.setAlwaysShowScrollBars(false);
			styledText.setBackground(container.getBackground());
			styledText.setJustify(true);
			
			combo = new CCombo(container, SWT.BORDER | SWT.READ_ONLY);
			combo.setEnabled(false);
			
			btnKeep = new Button(container, SWT.RADIO);
			btnKeep.addSelectionListener(listener);
			
			btnChangeAndEnforce = new Button(container, SWT.RADIO);
			GroupLayout gl_container = new GroupLayout(container);
			gl_container.setHorizontalGroup(
				gl_container.createParallelGroup(GroupLayout.LEADING)
					.add(gl_container.createSequentialGroup()
						.add(10)
						.add(gl_container.createParallelGroup(GroupLayout.LEADING)
							.add(styledText, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
							.add(combo, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
							.add(btnChangeAndEnforce, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE)
							.add(btnKeep, GroupLayout.DEFAULT_SIZE, 754, Short.MAX_VALUE))
						.add(10))
			);
			gl_container.setVerticalGroup(
				gl_container.createParallelGroup(GroupLayout.LEADING)
					.add(gl_container.createSequentialGroup()
						.add(10)
						.add(styledText, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
						.add(6)
						.add(btnKeep)
						.add(6)
						.add(btnChangeAndEnforce)
						.add(6)
						.add(combo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
			);
			container.setLayout(gl_container);
			btnChangeAndEnforce.addSelectionListener(listener);
		
	}
	
	public void setUI(BinaryPropertyValue chosenPropertyValue){
		
		System.out.println("SetUI ChosenValue: "+chosenPropertyValue);
		
		defaultPropertyValue = getBinOverWizard().getDefaultValue(getBinOverWizard().getCurrentStereotype(this), propertyType);
		this.chosenPropertyValue = chosenPropertyValue;
		
	
		styledText.setText(	"We found an inconsistency between your previous answer and the embedded constraints on the current stereotype of "+OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+". " +
							"The problem is that all relations stereotyped as <"+getBinOverWizard().getCurrentStereotypeName(this)+"> are natively <"+defaultPropertyValue+">" +
							"\r\n\r\n" +
							"If "+OntoUMLNameHelper.getTypeAndName(binOver.getAssociation(), true, true)+" must be <"+chosenPropertyValue+">, its ontological categorization (stereotype) must change.  Would like to:");
		
	
		
		ArrayList<String> options = getBinOverWizard().getStereotypeNames(getBinOverWizard().possibleStereotypes(chosenPropertyValue));
		combo.removeAll();
		
		for (String op : options)
			combo.add(op);
		
		combo.select(0);
		
		btnKeep.setText("Keep as <"+getBinOverWizard().getCurrentStereotypeName(this)+"> and <"+defaultPropertyValue+">");
		
		btnChangeAndEnforce.setText("Change stereotype and enforce <"+chosenPropertyValue+">");		
	}
	
	@Override
	public IWizardPage getNextPage(){
		BinOverAction action = new BinOverAction(binOver);
			
		if(btnKeep.getSelection()){
			
			action.setBinaryProperty(defaultPropertyValue);
			
			if(propertyType==BinaryProperty.Reflexivity){
				getBinOverWizard().reflexivity = defaultPropertyValue;
				getBinOverWizard().replaceAction(1, action);	
			}
			else if(propertyType==BinaryProperty.Symmetry){
				getBinOverWizard().symmetry = defaultPropertyValue;
				getBinOverWizard().replaceAction(2, action);	
			}
			else if(propertyType==BinaryProperty.Transitivity){
				getBinOverWizard().transitivity = defaultPropertyValue;
				getBinOverWizard().replaceAction(3, action);	
			}
			else if(propertyType==BinaryProperty.Cyclicity){
				getBinOverWizard().cyclicity = defaultPropertyValue;
				getBinOverWizard().replaceAction(4, action);	
			}
		}
		
		else{
			
			action.setBinaryProperty(chosenPropertyValue);
			
			BinOverAction action2 = new BinOverAction(binOver);
			action2.setChangeStereortype(getBinOverWizard().possibleStereotypes(chosenPropertyValue).get(combo.getSelectionIndex()));
			
			if(propertyType==BinaryProperty.Reflexivity){
				getBinOverWizard().reflexivity = chosenPropertyValue;
				getBinOverWizard().replaceAction(1, action);
				getBinOverWizard().addAction(1, action2);
			}
			else if(propertyType==BinaryProperty.Symmetry){
				getBinOverWizard().symmetry = chosenPropertyValue;
				getBinOverWizard().replaceAction(2, action);
				getBinOverWizard().addAction(2, action2);
			}
			else if(propertyType==BinaryProperty.Transitivity){
				getBinOverWizard().transitivity = chosenPropertyValue;
				getBinOverWizard().replaceAction(3, action);
				getBinOverWizard().addAction(3, action2);
			}
			else if(propertyType==BinaryProperty.Cyclicity){
				getBinOverWizard().cyclicity = chosenPropertyValue;
				getBinOverWizard().replaceAction(4, action);
				getBinOverWizard().addAction(4, action2);
			}
		}
		
		if(propertyType==BinaryProperty.Reflexivity)
			return getBinOverWizard().getSymmetryPage();
		else if(propertyType==BinaryProperty.Symmetry)
			return getBinOverWizard().getTransitivityPage();
		else if(propertyType==BinaryProperty.Transitivity)
			return getBinOverWizard().getCyclicityPage();
		else if(propertyType==BinaryProperty.Cyclicity)
			return getBinOverWizard().getFinishing();
		
		return null;
	}
}
