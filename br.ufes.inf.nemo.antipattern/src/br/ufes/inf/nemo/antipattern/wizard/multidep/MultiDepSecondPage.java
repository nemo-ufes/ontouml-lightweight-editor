package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;

public class MultiDepSecondPage extends MultiDepPage {
	
	public ArrayList<Property> properties = new ArrayList<Property>();
	public Button btnYesTheOrder;
	public Button btnNoTheOrder;
	public MultiDepSpinnerTable table;
	public Composite container;
	
	public MultiDepSecondPage(MultiDepOccurrence multiDep) {
		super(multiDep);
	}
	
	public void setProperties(ArrayList<Property> properties)
	{
		this.properties = properties;
		table = new MultiDepSpinnerTable(container,SWT.BORDER,properties);
		table.getTable().setBounds(23, 42, 527, 156);
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
			    
		setPageComplete(false);
		
//		Composite composite = new Composite(container, SWT.NONE);
//		composite.setBounds(23, 42, 527, 156);
				
		btnYesTheOrder = new Button(container, SWT.RADIO);
		btnYesTheOrder.setBounds(23, 204, 526, 16);
		btnYesTheOrder.setText("Yes, in this particular order");
		btnYesTheOrder.addSelectionListener(listener);
		
		btnNoTheOrder = new Button(container, SWT.RADIO);
		btnNoTheOrder.setBounds(23, 226, 526, 16);
		btnNoTheOrder.setText("No, the order does not matter");
		btnNoTheOrder.addSelectionListener(listener);
		
		Label lblAreTheseOptional = new Label(container, SWT.NONE);
		lblAreTheseOptional.setBounds(23, 10, 526, 15);
		lblAreTheseOptional.setText("Are these optional relators created in a particular order?");
	}
	
	public boolean isRemainderAllSelected()
	{
		return properties.size() == multiDep.getRelatorEnds().size();
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		boolean allRemainder = isRemainderAllSelected();
		
		if (btnNoTheOrder.getSelection())
		{
			if(allRemainder){
				//Action =============================
				MultiDepAction newAction = new MultiDepAction(multiDep);
				newAction.setAddSubTypeInvolvingMediation(properties); 
				getMultiDepWizard().replaceAction(0,newAction);	
				//======================================
			}else{
				if(properties.size()==1){					
					//Action =============================
					MultiDepAction newAction = new MultiDepAction(multiDep);
					newAction.setAddSubTypeInvolvingMediation(properties);
					getMultiDepWizard().replaceAction(0,newAction);	
					//====================================					
				}else if (properties.size()>1){					
					//Action =============================
					MultiDepAction newAction = new MultiDepAction(multiDep);
					newAction.setAddSubTypeWithIntermediate(properties); 
					getMultiDepWizard().replaceAction(0,newAction);
					//====================================
				}
			}
		}
		if (btnYesTheOrder.getSelection())
		{
			//Action =============================

			//====================================	
		}
	
		return ((MultiDepWizard)getWizard()).getThirdPage();		
	}
}
