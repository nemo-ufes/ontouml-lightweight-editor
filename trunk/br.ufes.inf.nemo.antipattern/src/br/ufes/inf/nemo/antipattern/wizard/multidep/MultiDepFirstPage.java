package br.ufes.inf.nemo.antipattern.wizard.multidep;

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
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;

public class MultiDepFirstPage extends MultiDepPage {

	//GUI
	public List optionalList;
	public ArrayList<Property> optional = new ArrayList<Property>();
	
	public List requiredList;
	public ArrayList<Property> required = new ArrayList<Property>();
	
	private Button upButton;
	private Button downButton;
	private Label lblAllRelators_1;
	
	public MultiDepFirstPage(MultiDepOccurrence multiDep) {
		super(multiDep);
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setText("When an instance of "+OntoUMLNameHelper.getTypeAndName(occurrence.getType(), true, true)+" is created, to which relators must it be connected to?");
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		optionalList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		requiredList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		required.addAll(occurrence.getRelatorEnds());
		
		for(Property p: occurrence.getRelatorEnds()) {		
			requiredList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, false));			
		}
		requiredList.setSelection(0);
		
		Label lblAllRelators = new Label(container, SWT.NONE);
		lblAllRelators.setText("Necessarily connected to:");
		
		downButton = new Button(container, SWT.NONE);
		downButton.setText("Down");
		downButton.addSelectionListener(downAction);
		
		upButton = new Button(container, SWT.NONE);
		upButton.setText("Up");
		upButton.addSelectionListener(upAction);
		
		lblAllRelators_1 = new Label(container, SWT.NONE);
		lblAllRelators_1.setText("Optionally connected to:");
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel.setAlignment(SWT.RIGHT);
		lblNewLabel.setText("For each optional relator, a subtype of "+OntoUMLNameHelper.getTypeAndName(occurrence.getType(), true, true)+" will be created with a default name. Please remember to rename them after the analysis. ");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(requiredList, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
						.add(gl_container.createSequentialGroup()
							.add(10)
							.add(styledText, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE))
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(lblAllRelators, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
						.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
							.addContainerGap()
							.add(lblAllRelators_1, GroupLayout.DEFAULT_SIZE, 416, Short.MAX_VALUE)
							.add(43)
							.add(upButton, GroupLayout.PREFERRED_SIZE, 44, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(downButton))
						.add(gl_container.createSequentialGroup()
							.addContainerGap()
							.add(optionalList, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
						.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
							.addContainerGap()
							.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblAllRelators)
					.add(3)
					.add(requiredList, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
						.add(downButton)
						.add(upButton)
						.add(lblAllRelators_1))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(optionalList, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblNewLabel, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
					.add(23))
		);
		container.setLayout(gl_container);
	}
		
	private SelectionAdapter upAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			int index = optionalList.getSelectionIndex();
			if(index!=-1) { 
				Property p = optional.remove(index);
				optionalList.remove(index);
				required.add(p);
				requiredList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, false));
			}	
		}
	};
	
	private SelectionAdapter downAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			int index = requiredList.getSelectionIndex();
			if(index!=-1) { 
				Property p = required.remove(index);
				requiredList.remove(index);
				optional.add(p);
				optionalList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, false));
			}				
		}
	};
	
	@Override
	public IWizardPage getNextPage() 
	{	
		
		if(optional.size()>=2){
			MultiDepSecondPage secondPage = ((MultiDepWizard)getWizard()).getSecondPage();
			secondPage.setProperties(optional);			
			return secondPage;
		}
		else if(optional.size()==1){				
			//Action =============================
			MultiDepAction newAction = new MultiDepAction(occurrence);
			newAction.setAddSubtypesInNoOrder(optional);
			getAntipatternWizard().replaceAction(0,newAction);	
			//====================================
		}
		else{
			getAntipatternWizard().removeAllActions(0);
		}
		
		MultiDepThirdPage thirdPage = getAntipatternWizard().getThirdPage();
		return thirdPage;
		
	}
}
