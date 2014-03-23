package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;

public class MixIdenSecondPage  extends MixIdenPage {

	//GUI
	AddSelectSortalComposite addSelect;
	private Button btnYes;
	private Button btnNo;	
	private Label lblSelectExistingTypes;
		
	public MixIdenSecondPage(MixIdenOccurrence mixIdenOccurrence) {
		super(mixIdenOccurrence);	
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		
		setTitle(MixIdenAntipattern.getAntipatternInfo().getName());
		setDescription(	"Mixin: "+mixIden.getMixin().getName()+
						"\r\nSubtypes: "+getSubtypeList(3)+
						"\r\nCommon Identity Provider: "+mixIden.getIdentityProvider().getName());
		
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);	
		
		setPageComplete(false);
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setBounds(10, 10, 554, 30);
		styledText.setText(	"Are any of the types that can specialize <"+getMixIdenWizard().mixinName+"> and " +
							"follow a different identity principle from the one supplied by <"+mixIden.getIdentityProvider().getName()+">, " +
							"in the scope of the ontology?");
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 68, 39, 16);
		btnYes.setText("Yes");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 46, 39, 16);
		btnNo.setText("No");
		btnNo.addSelectionListener(listener);

		try {
			addSelect = new AddSelectSortalComposite(container, SWT.BORDER, mixIden.getParser(), 
					getMixIdenWizard().allowedStereotypes(), getMixIdenWizard().identityProviderStereotypes());
			addSelect.setBounds(10, 121, 554, 255);
			addSelect.setEnabled(false);
			
			lblSelectExistingTypes = new Label(container, SWT.NONE);
			lblSelectExistingTypes.setBounds(10, 100, 509, 15);
			lblSelectExistingTypes.setText("If Yes, please select existing types or create new ones using the table below:");
		} catch (Exception e) {
			System.out.println("ERROR!");
		}
		
	}
	
	private SelectionAdapter listener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnNo.getSelection()){
				setPageComplete(true);
				addSelect.setEnabled(true);
			}
			if(btnYes.getSelection()){
				setPageComplete(true);
				addSelect.setEnabled(false);
			}
		}
	};
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getMixIdenWizard().removeAllActions();

		if(btnNo.getSelection()) {
			MixIdenAction action = new MixIdenAction(mixIden);
			action.setAddSubtypes(addSelect.getNewSortalSubtypes());
			getMixIdenWizard().addAction(0, action);
		}
		
		return getMixIdenWizard().getFinishing();
	
	}
}
