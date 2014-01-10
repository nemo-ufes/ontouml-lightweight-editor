package br.ufes.inf.nemo.oled.antipattern.wizard.relspec;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.relspec.RelSpecOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelSpecFirstPage extends RelSpecPage {

	//GUI
	public Button btnRequired;
	public Button btnForbidden;
	public Button btnPossible;
	
	/**
	 * Create the wizard.
	 */
	public RelSpecFirstPage(RelSpecOccurrence relSpec) 
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
		styledText.setText(	"Consider an instance ‘x’ of "+relSpec.getAlignedSpecificSource().getName()+" that is related to an instance ‘y’ of " +
							relSpec.getAlignedSpecificTarget().getName()+", through relation "+relSpec.getSpecific().getName()+". What can be said" +
							" about ‘x’ being connected to ‘y’ through "+relSpec.getGeneral().getName()+"?");
		styledText.setEditable(false);
		styledText.setBounds(10, 10, 554, 63);
		
		btnRequired = new Button(container, SWT.RADIO);
		btnRequired.setBounds(10, 79, 90, 16);
		btnRequired.setText("Required");
		btnRequired.setSelection(true);
		
		btnForbidden = new Button(container, SWT.RADIO);
		btnForbidden.setText("Forbbiden");
		btnForbidden.setBounds(10, 101, 90, 16);
		
		btnPossible = new Button(container, SWT.RADIO);
		btnPossible.setText("Possible, but not required");
		btnPossible.setBounds(10, 123, 156, 16);
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnPossible.getSelection()) 
			return getRelSpecWizard().getFinishing();
			
		else if(btnForbidden.getSelection()){			
			
			// Action =====================			
			//TODO: <Specific> is disjoint from <General>		
			//=============================
			
			return getRelSpecWizard().getFinishing();
		}
		else if(btnRequired.getSelection()){
			return getRelSpecWizard().getSecondPage();
		}
		return super.getNextPage();
	}
}
