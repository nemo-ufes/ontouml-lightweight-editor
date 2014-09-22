package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class MultiDepRefactoringPage extends RefactoringPage {
	
	public MultiDepOccurrence multiDep;
	public Button btnYes;
	public Button btnNo;
	public Button addButton;
	private Button removeButton;
	public Button downButton;
	public Button upButton;
	public List requiredList;
	public MultiDepComboTable dependencyTable;
	public MultiDepSpinnerTable orderTable;
	private Label lblTypeIsOptionally;;
	
	ArrayList<Property> required = new ArrayList<Property>();
	ArrayList<Property> optional = new ArrayList<Property>();
	
	private SelectionAdapter moveUpAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			int index = orderTable.getSelectionIndex();
			if(index!=-1) {
				Property p = optional.remove(index);
				orderTable.removeLine(index);
				required.add(p);
				requiredList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, false));
			}	
		}
	};
	
	private SelectionAdapter moveDownAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			int index = requiredList.getSelectionIndex();
			if(index!=-1) { 
				Property p = required.remove(index);
				requiredList.remove(index);
				optional.add(p);
				orderTable.addLine(p, 0);
			}				
		}
	};
	
	private SelectionAdapter addAction = new SelectionAdapter() {
		 @Override
         public void widgetSelected(SelectionEvent e) {
			 dependencyTable.addLine();	
			 removeButton.setEnabled(true);
			
		 }
	};
	
	private SelectionAdapter removeAction = new SelectionAdapter() {
		 @Override
         public void widgetSelected(SelectionEvent e) {
			 int index = dependencyTable.getSelectionIndex();
			 
			 if(index<0 || index>=dependencyTable.getItemCount())
				 return;
			 
			 dependencyTable.removeLine(index);
			 
			 if(dependencyTable.getItemCount()>0)
				 removeButton.setEnabled(true);
			 else
				 removeButton.setEnabled(false);
		 }
	};
	
	
	/**
	 * Create the wizard.
	 */
	public MultiDepRefactoringPage(MultiDepOccurrence multiDep) 
	{
		super();	
		this.multiDep = multiDep;
		
		setTitle(MultiDepAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public MultiDepWizard getMultiDepWizard(){
		return (MultiDepWizard)getWizard();
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
	
		required.addAll(multiDep.getRelatorEnds());
		
		requiredList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );		
		orderTable = new MultiDepSpinnerTable(container,SWT.BORDER);
		orderTable.getColumns()[0].setWidth(100);
		
		//initialize connected list
		for(Property p: multiDep.getRelatorEnds()) {		
			requiredList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, false));			
		}
		requiredList.setSelection(0);
						
		Label lblChooseTheCombination = new Label(container, SWT.NONE);
		lblChooseTheCombination.setText("Is there a dependency between the relators?");
				
		dependencyTable = new MultiDepComboTable(container, SWT.BORDER);
		dependencyTable.setProperties(multiDep.getRelatorEnds());
		
		addButton = new Button(container, SWT.NONE);
		addButton.setText("Add");
		addButton.addSelectionListener(addAction);	
		
		Label lblChooseWhichRelators = new Label(container, SWT.NONE);
		lblChooseWhichRelators.setText("Necessarily connected to:");
							
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes, in this order");
				
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No order");
		btnNo.setSelection(true);
		
		downButton = new Button(container, SWT.NONE);
		downButton.setText("Down");
		downButton.addSelectionListener(moveDownAction);
		
		upButton = new Button(container, SWT.NONE);
		upButton.setText("Up");
		upButton.addSelectionListener(moveUpAction);
		
		lblTypeIsOptionally = new Label(container, SWT.NONE);
		lblTypeIsOptionally.setText("Optionally connected to:");
		
		removeButton = new Button(container, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.addSelectionListener(removeAction);
		removeButton.setEnabled(false);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblChooseTheCombination, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED, 184, Short.MAX_VALUE)
					.add(removeButton)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(addButton)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(btnYes, GroupLayout.PREFERRED_SIZE, 119, GroupLayout.PREFERRED_SIZE)
					.add(5)
					.add(btnNo, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(345, Short.MAX_VALUE))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(orderTable, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(dependencyTable, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(lblChooseWhichRelators, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(requiredList, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(gl_container.createSequentialGroup()
							.add(lblTypeIsOptionally, GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
							.add(69)
							.add(upButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(downButton, GroupLayout.PREFERRED_SIZE, 59, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblChooseWhichRelators)
					.add(6)
					.add(requiredList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.add(6)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(10)
							.add(lblTypeIsOptionally))
						.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
							.add(downButton)
							.add(upButton)))
					.add(6)
					.add(orderTable, GroupLayout.DEFAULT_SIZE, 83, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
								.add(btnNo)
								.add(btnYes))
							.add(18)
							.add(lblChooseTheCombination))
						.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
							.add(addButton)
							.add(removeButton)))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(dependencyTable, GroupLayout.DEFAULT_SIZE, 86, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		
		
	}
	
	public Property getProperty (String typeName){
		for(Property p: multiDep.getRelatorEnds()){
			if(p.getType().getName().compareToIgnoreCase(typeName)==0) return p;			
		}
		return null;
	}
	
	public ArrayList<Property> getRemainders(){
		ArrayList<Property> result = new ArrayList<Property>();
		for(TableItem it: orderTable.getTable().getItems()){
			String str = it.getText(0);
			Property p = getProperty(str.replace("Relator ", ""));
			if (p!=null) result.add(p);
		}
		return result;
	}
	
	public ArrayList<Property> getConnected(){
		ArrayList<Property> result = new ArrayList<Property>();
		for(String str: requiredList.getItems()){
			Property p = getProperty(str.replace("Relator ", ""));
			if (p!=null) result.add(p);
		}
		return result;
	}
		
	public boolean isAllConnected()
	{		
		return requiredList.getItemCount() == multiDep.getRelatorEnds().size();
	}
	public boolean isAllRemainder()
	{		
		return orderTable.getTable().getItemCount() == multiDep.getRelatorEnds().size();
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		getMultiDepWizard().removeAllActions();
		
		if (!isAllConnected()) {
			if (btnNo.getSelection()) {
				//Action =============================
				MultiDepAction newAction = new MultiDepAction(multiDep);
				newAction.setAddSubtypesInNoOrder(getRemainders()); 
				getMultiDepWizard().replaceAction(0,newAction);	
				//======================================
			}
			else if (btnYes.getSelection())
			{
				//Action =============================
				MultiDepAction newAction = new MultiDepAction(multiDep);
				newAction.setAddSubtypesInCustomOrder(orderTable.getValues()); 
				getMultiDepWizard().replaceAction(0,newAction);	
				//====================================	
			}
		}
		
		ArrayList<ArrayList<Property>> matrix = dependencyTable.getSelections();			
		//Action =============================
		MultiDepAction newAction = new MultiDepAction(multiDep);
		newAction.setCreateDependencies(matrix); 
		getMultiDepWizard().replaceAction(1,newAction);
		//====================================				
		
		return getMultiDepWizard().getFinishing();
	}
}
