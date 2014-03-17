package br.ufes.inf.nemo.antipattern.wizard.depphase;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;

public class DepPhaseSecondPage  extends DepPhasePage {

	//GUI

	private List listMandatory;
	private List listOptional;
	private Button btnFromMandatoryToOptional;
	private Button btnFromOptionalToMandatory;

	public DepPhaseSecondPage(DepPhaseOccurrence depPhase) {
		super(depPhase);
		setDescription("Phase: "+depPhase.getPhase().getName()+", Relator: "+getRelatorList());
	}
		
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);	
		
		StyledText textQuestion = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		textQuestion.setBounds(10, 10, 554, 75);
		textQuestion.setText(	"Now, we should separate the relational dependencies." +
								"\r\n\r\n" +
								"Are all relational dependencies of <"+depPhase.getPhase().getName()+"> really mandatory, " +
								"i.e. must every single instance of <"+depPhase.getPhase().getName()+"> be connected to all its relators? " +
								"Use the lists below to select the mandatory and the optional dependencies.");
		textQuestion.setBackground(textQuestion.getParent().getBackground());
		textQuestion.setJustify(true);
		
		listMandatory = new List(container, SWT.BORDER);
		listMandatory.setBounds(10, 114, 238, 157);		
		listOptional = new List(container, SWT.BORDER);
		listOptional.setBounds(326, 114, 238, 157);
		
		getDepPhaseWizard().addAllDependencies(listMandatory, listOptional);
		
		btnFromMandatoryToOptional = new Button(container, SWT.NONE);
		btnFromMandatoryToOptional.setBounds(269, 153, 37, 25);
		btnFromMandatoryToOptional.setText("->");
		btnFromMandatoryToOptional.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: listMandatory.getSelection()){
					if(!getDepPhaseWizard().contains(listOptional,str)) { 
						listOptional.add(str); 
						listOptional.select(listOptional.indexOf(str));  
					} 
				}
				if(listMandatory.getSelectionIndex()>=0) {
					int prev = listMandatory.getSelectionIndex()-1;
					listMandatory.remove(listMandatory.getSelectionIndex());
					listMandatory.select(prev);					 
				}
			}
		});
		
		
		btnFromOptionalToMandatory = new Button(container, SWT.NONE);
		btnFromOptionalToMandatory.setText("<-");
		btnFromOptionalToMandatory.setBounds(269, 184, 37, 25);
		btnFromOptionalToMandatory.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: listOptional.getSelection()){
					if(!getDepPhaseWizard().contains(listMandatory,str)) { 
						listMandatory.add(str); 
						listMandatory.select(listMandatory.indexOf(str));  
					} 
				}
				if(listOptional.getSelectionIndex()>=0) {
					int prev = listOptional.getSelectionIndex()-1;
					listOptional.remove(listOptional.getSelectionIndex());
					listOptional.select(prev);					 
				}
			}
		});
		
		Label lblMandatory = new Label(container, SWT.NONE);
		lblMandatory.setBounds(10, 93, 238, 15);
		lblMandatory.setText("Mandatory");
		
		Label lblOptional = new Label(container, SWT.NONE);
		lblOptional.setText("Optional");
		lblOptional.setBounds(326, 93, 238, 15);
	}
	
	public ArrayList<Property> getMandatoryDependencies(){
		return getDepPhaseWizard().getPropertyFromList(listMandatory);
	}
	
	public ArrayList<Property> getOptionalDependencies(){
		return getDepPhaseWizard().getPropertyFromList(listOptional);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		DepPhaseAction action;
		getDepPhaseWizard().removeAllActions(1);
		
		for (Property mandatory : getMandatoryDependencies()) {
			action = new DepPhaseAction(depPhase);
			action.setAddSupertypeAndMoveMediation(mandatory);
			getDepPhaseWizard().addAction(1, action);
		}
		
		for (Property optional : getOptionalDependencies()) {
			action = new DepPhaseAction(depPhase);
			action.setAddSubtypeAndMoveMediation(optional);
			getDepPhaseWizard().addAction(1, action);
		}
		
		return getDepPhaseWizard().getFinishing();
	
	}
}
