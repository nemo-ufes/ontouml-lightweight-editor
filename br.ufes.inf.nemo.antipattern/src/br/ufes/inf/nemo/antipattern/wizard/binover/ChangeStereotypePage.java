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

import br.ufes.inf.nemo.antipattern.binover.BinOverAntipattern;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryProperty;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryPropertyValue;
import br.ufes.inf.nemo.antipattern.wizard.binover.BinOverAction.Action;

public class ChangeStereotypePage extends BinOverPage {

	protected BinaryProperty propertyType;
	protected BinaryPropertyValue chosenPropertyValue, defaultPropertyValue;
	
	protected Composite container;
	protected StyledText styledText;
	protected Button btnKeep, btnChangeAndEnforce;
	protected CCombo combo;
	
	protected IWizardPage next;
	
	public ChangeStereotypePage(BinOverOccurrence occurrence, BinaryProperty propertyType, IWizardPage next) {
		super("ChangeStereotypePage", occurrence);
		
		this.propertyType = propertyType;
		this.next = next;
		
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
		
		styledText = new StyledText(container, SWT.WRAP);
		styledText.setBounds(10, 10, 554, 110);
		styledText.setBackground(container.getBackground());
		styledText.setJustify(true);
		
		combo = new CCombo(container, SWT.BORDER | SWT.READ_ONLY);
		combo.setBounds(10, 170, 138, 21);
		combo.setEnabled(false);
		
		btnKeep = new Button(container, SWT.RADIO);
		btnKeep.addSelectionListener(listener);
		btnKeep.setBounds(10, 126, 554, 16);
		
		btnChangeAndEnforce = new Button(container, SWT.RADIO);
		btnChangeAndEnforce.addSelectionListener(listener);
		btnChangeAndEnforce.setBounds(10, 148, 554, 16);
		
	}
	
	public void setUI(BinaryPropertyValue chosenPropertyValue){
		
		System.out.println("SetUI ChosenValue: "+chosenPropertyValue);
		
		defaultPropertyValue = getBinOverWizard().getDefaultValue(getBinOverWizard().getCurrentStereotype(this), propertyType);
		this.chosenPropertyValue = chosenPropertyValue;
		
	
		styledText.setText(	"We found an inconsistency between your previous answer and the embedded constraints on the current stereotype of <"+getRelationName()+">. " +
							"The problem is that all relations stereotyped as <"+getBinOverWizard().getCurrentStereotypeName(this)+"> are natively <"+defaultPropertyValue+">" +
							"\r\n\r\n" +
							"If <"+getRelationName()+"> must be <"+chosenPropertyValue+">, its ontological categorization (stereotype) must change.  Would like to:");
		
	
		
		ArrayList<String> options = getBinOverWizard().getStereotypeNames(getBinOverWizard().possibleStereotypes(propertyType, chosenPropertyValue));
		combo.removeAll();
				for (String op : options) {
			combo.add(op);
		}
		combo.select(0);
		
		btnKeep.setText("Keep as <"+getBinOverWizard().getCurrentStereotypeName(this)+"> and <"+defaultPropertyValue+">");
		
		btnChangeAndEnforce.setText("Change stereotype and enforce <"+chosenPropertyValue+">");		
	}
	
	@Override
	public IWizardPage getNextPage(){
		BinOverAction action = new BinOverAction(binOver);
			
		if(btnKeep.getSelection()){
			if(propertyType==BinaryProperty.Reflexivity){
				getBinOverWizard().removeAllActions(0, Action.SET_REFLEXIVITY);
				action.setReflexivity(defaultPropertyValue);
				getBinOverWizard().removeAllActions(1);
			}
			else if(propertyType==BinaryProperty.Symmetry){
				getBinOverWizard().removeAllActions(0, Action.SET_SYMMETRY);
				action.setSymmetry(defaultPropertyValue);
				getBinOverWizard().removeAllActions(2);
			}
			else if(propertyType==BinaryProperty.Transitivity){
				getBinOverWizard().removeAllActions(0, Action.SET_TRANSITIVITY);
				action.setTransitivity(defaultPropertyValue);
				getBinOverWizard().removeAllActions(3);
			}
			else if(propertyType==BinaryProperty.Cyclicity){
				getBinOverWizard().removeAllActions(0, Action.SET_CYCLICITY);
				action.setCyclicity(defaultPropertyValue);
				getBinOverWizard().removeAllActions(4);
			}
		}
		
		else{
			
			BinOverAction action2 = new BinOverAction(binOver);
			action2.setChangeStereortype(getBinOverWizard().possibleStereotypes(propertyType, chosenPropertyValue).get(combo.getSelectionIndex()));
			
			if(propertyType==BinaryProperty.Reflexivity){
				getBinOverWizard().removeAllActions(0, Action.SET_REFLEXIVITY);
				action.setReflexivity(chosenPropertyValue);
				getBinOverWizard().replaceAction(1, action2);
			}
			else if(propertyType==BinaryProperty.Symmetry){
				getBinOverWizard().removeAllActions(0, Action.SET_SYMMETRY);
				action.setSymmetry(chosenPropertyValue);
				getBinOverWizard().replaceAction(2, action2);
			}
			else if(propertyType==BinaryProperty.Transitivity){
				getBinOverWizard().removeAllActions(0, Action.SET_TRANSITIVITY);
				action.setTransitivity(chosenPropertyValue);
				getBinOverWizard().replaceAction(3, action2);
			}
			else if(propertyType==BinaryProperty.Cyclicity){
				getBinOverWizard().removeAllActions(0, Action.SET_CYCLICITY);
				action.setCyclicity(chosenPropertyValue);
				getBinOverWizard().replaceAction(4, action2);
			}
		}
		
		getBinOverWizard().addAction(0, action);	
		
		if(next instanceof BinOverPage)
			((BinOverPage)next).updateDescription();
		
		return next;
	}
}
