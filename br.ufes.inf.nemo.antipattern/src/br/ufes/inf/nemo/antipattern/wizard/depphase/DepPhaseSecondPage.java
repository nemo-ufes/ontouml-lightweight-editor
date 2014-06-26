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
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class DepPhaseSecondPage  extends DepPhasePage {

	//GUI

	private List listMandatory;
	private List listOptional;
	private Button btnFromMandatoryToOptional;
	private Button btnFromOptionalToMandatory;

	public DepPhaseSecondPage(DepPhaseOccurrence depPhase) {
		super(depPhase);
		setTitle(DepPhaseAntipattern.getAntipatternInfo().getName());
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
		textQuestion.setText(	"Now, we should separate the relational dependencies." +
								"\r\n\r\n" +
								"Are all relational dependencies of <"+depPhase.getPhase().getName()+"> really mandatory, " +
								"i.e. must every single instance of <"+depPhase.getPhase().getName()+"> be connected to all its relators? " +
								"Use the lists below to select the mandatory and the optional dependencies.");
		textQuestion.setBackground(textQuestion.getParent().getBackground());
		textQuestion.setJustify(true);
		
		listMandatory = new List(container, SWT.BORDER);
		listOptional = new List(container, SWT.BORDER);
		
		getDepPhaseWizard().addAllDependencies(listMandatory, listOptional);
		
		btnFromMandatoryToOptional = new Button(container, SWT.NONE);
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
		lblMandatory.setText("Mandatory");
		
		Label lblOptional = new Label(container, SWT.NONE);
		lblOptional.setText("Optional");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(textQuestion, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(gl_container.createSequentialGroup()
									.add(listMandatory, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
									.add(21)
									.add(gl_container.createParallelGroup(GroupLayout.LEADING)
										.add(btnFromMandatoryToOptional, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
										.add(btnFromOptionalToMandatory, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
									.add(20))
								.add(gl_container.createSequentialGroup()
									.add(lblMandatory, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
									.add(78)))
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(lblOptional, GroupLayout.PREFERRED_SIZE, 238, GroupLayout.PREFERRED_SIZE)
								.add(GroupLayout.TRAILING, listOptional, GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE))))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(textQuestion, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.add(8)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblMandatory)
						.add(lblOptional))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(listMandatory, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
						.add(gl_container.createSequentialGroup()
							.add(39)
							.add(btnFromMandatoryToOptional)
							.add(6)
							.add(btnFromOptionalToMandatory))
						.add(listOptional, GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE))
					.add(10))
		);
		container.setLayout(gl_container);
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
