package br.ufes.inf.nemo.oled.antipattern.wizard.relspec;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecThirdPage extends RelSpecPage {
	
	//GUI
	public Button btnSpecialize;
	public Button btnDelete;
	public Button btnKeep;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecThirdPage(RelSpecOccurrence relSpec) 
	{
		super(relSpec);	
		setDescription("Associations: "+relSpec.getParser().getStringRepresentation(relSpec.getGeneral())+" and "+relSpec.getParser().getStringRepresentation(relSpec.getSpecific()));
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		
		styledText.setText(	"When two associations have exactly the same domain and exactly the same range, if said that one redefines the other, " +
							"they turn out to be replicas, increasing the model’s complexity, without adding new information.\n\nIf you would like to keep both associations and it is true that " +
							"one redefines the other (as your previous answers suggested), at least one of end types must be specialized into a new class. " +
							"\n\nWith that in mind, would you like to:");

		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 128);
				
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	        if (isPageComplete()==false) setPageComplete(true);
	      }
	    };
		    
	    setPageComplete(false);
		    
		btnSpecialize = new Button(container, SWT.RADIO);
		btnSpecialize.setBounds(10, 144, 449, 16);
		btnSpecialize.setText("Specialize ends and include the redefinition constraint");
		btnSpecialize.addSelectionListener(listener);
		
		btnKeep = new Button(container, SWT.RADIO);
		btnKeep.setText("Keep the model as it is and include the redefinition constraint");
		btnKeep.setBounds(10, 166, 449, 16);
		btnKeep.addSelectionListener(listener);
		
		btnDelete = new Button(container, SWT.RADIO);
		btnDelete.setText("Delete one of the associations (redefinition constraint will not be included)");
		btnDelete.setBounds(10, 188, 449, 16);
		btnDelete.addSelectionListener(listener);		
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnKeep.getSelection()) {
			// Action =====================
			RelSpecAction newAction = new RelSpecAction(relSpec);
			newAction.setRedefine();
			getRelSpecWizard().addAction(0,newAction);
						
			return getRelSpecWizard().getFinishing();
		}	
		else if(btnSpecialize.getSelection()){			
			return getRelSpecWizard().getFourthPage();			
		}
		else if (btnDelete.getSelection()){
			return getRelSpecWizard().getFifthPage();
		}
		return super.getNextPage();
	}
}
