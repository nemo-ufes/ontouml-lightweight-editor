package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;

public class MultiDepThirdPage  extends MultiDepPage {

	public MultiDepComboTable table;
	public Composite container;
	private Button addButton;
	private Button removeButton;
	private Button noRadio;
	private Button yesRadio;
	private Label lblNewLabel_1;
	
	
	private SelectionAdapter radioAction = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent e) {
    	 
	    	if(noRadio.getSelection()){
	    		addButton.setEnabled(false);
	    		removeButton.setEnabled(false);
	    		table.setEnabled(false);
	    		setPageComplete(true);
	    	}
	    	if(yesRadio.getSelection()){
	    		addButton.setEnabled(true);
	    		table.setEnabled(true);
	    		
	    		if(table.getSelections().size()>0){
	    			setPageComplete(true);
	    			removeButton.setEnabled(true);
	    		}
	    		else{
	    			setPageComplete(false);
	    			removeButton.setEnabled(false);
	    		}
	    	}
    	
		}
	};
	
	private SelectionAdapter addAction = new SelectionAdapter() {
		 @Override
           public void widgetSelected(SelectionEvent e) {
			 setPageComplete(true);
			 table.addLine();
			 removeButton.setEnabled(true);
		 }
	};
	
	private SelectionAdapter removeAction = new SelectionAdapter() {
		 @Override
          public void widgetSelected(SelectionEvent e) {
			 int index = table.getSelectionIndex();
			 
			 if(index<0 || index>=table.getItemCount())
				 return;
			 
			 table.removeLine(index);
			 
			 if(table.getItemCount()>0)
				 removeButton.setEnabled(true);
			 else
				 removeButton.setEnabled(false);
		 }
	};
	
	
	public MultiDepThirdPage(MultiDepOccurrence multiDep) 
	{
		super(multiDep);
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);
		setControl(container);
		setPageComplete(false);
		
		table = new MultiDepComboTable(container, SWT.BORDER);
		table.setProperties(occurrence.getRelatorEnds());
		table.setEnabled(false);
		
		Label lblNewLabel = new Label(container, SWT.WRAP);
		lblNewLabel.setText("Is there a dependency between the relators connected to "+OntoUMLNameHelper.getTypeAndName(occurrence.getType(), true, true)+"?");
		
		addButton = new Button(container, SWT.NONE);
		addButton.setText("Add");
		addButton.setEnabled(false);
		addButton.addSelectionListener(addAction);	
		
		noRadio = new Button(container, SWT.RADIO);
		noRadio.setText("No");
		noRadio.addSelectionListener(radioAction);
		
		yesRadio = new Button(container, SWT.RADIO);
		yesRadio.setText("Yes");
		yesRadio.addSelectionListener(radioAction);
		
		Label lblPleaseUseThe = new Label(container, SWT.NONE);
		lblPleaseUseThe.setText("Please, use the table below to specify which relators depend on which:");
		
		lblNewLabel_1 = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblNewLabel_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblNewLabel_1.setText("Each line indicate the need to specify a new formal relation between the relators. Please remember to define the appropriate name and multiplicities after completing this wizard.");
		
		removeButton = new Button(container, SWT.NONE);
		removeButton.setText("Remove");
		removeButton.setEnabled(false);
		removeButton.addSelectionListener(removeAction);	
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(yesRadio, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(9))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(noRadio, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(9))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblNewLabel_1, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(table, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(gl_container.createSequentialGroup()
							.add(lblPleaseUseThe, GroupLayout.PREFERRED_SIZE, 386, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED, 55, Short.MAX_VALUE)
							.add(removeButton)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(addButton, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblNewLabel, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(noRadio)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(yesRadio)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(23)
							.add(lblPleaseUseThe))
						.add(gl_container.createSequentialGroup()
							.addPreferredGap(LayoutStyle.UNRELATED)
							.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
								.add(addButton)
								.add(removeButton))))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(table, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
		);
		container.setLayout(gl_container);
		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(yesRadio.getSelection()){
			ArrayList<ArrayList<Property>> matrix = table.getSelections();
			
			//Action =============================
			MultiDepAction newAction = new MultiDepAction(occurrence);
			newAction.setCreateDependencies(matrix); 
			getAntipatternWizard().replaceAction(1,newAction);
			//====================================
		}
		else{
			getAntipatternWizard().removeAllActions(1);
		}
		return getAntipatternWizard().getFinishing();
	}
}
