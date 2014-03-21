package br.ufes.inf.nemo.antipattern.wizard.mixrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;

public class MixRigSecondPage  extends MixRigPage {

	//GUI
	AddSelectTypeComposite addSelect;
	private Button btnYes;
	private Button btnNo;	
	
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
	private Label lblSelectExistingTypes;
		
	public MixRigSecondPage(MixRigOccurrence mixRig) {
		super(mixRig);	
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		
		setTitle(MixRigAntipattern.getAntipatternInfo().getName());
		setDescription("Mixin: "+mixRig.getMixin().getName()+"\r\nSubtypes: "+getSubtypeList(3));
		
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);	
		
		setPageComplete(false);
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setBounds(10, 10, 554, 30);
		styledText.setText("Are all "+getMixRigWizard().oppositeRigidity+" subtypes of "+getMixRigWizard().mixinName+" OUT of the scope of the ontology?");
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 46, 39, 16);
		btnYes.setText("Yes");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 68, 39, 16);
		btnNo.setText("No");
		btnNo.addSelectionListener(listener);

		try {
			addSelect = new AddSelectTypeComposite(container, SWT.BORDER, mixRig.getParser(), getMixRigWizard().allowedStereotypes());
			addSelect.setBounds(10, 121, 554, 255);
			addSelect.setEnabled(false);
			
			lblSelectExistingTypes = new Label(container, SWT.NONE);
			lblSelectExistingTypes.setBounds(10, 100, 509, 15);
			lblSelectExistingTypes.setText("If No, please select existing types or create new ones using the table below:");
		} catch (Exception e) {
			System.out.println("ERROR!");
		}
		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getMixRigWizard().removeAllActions();

		if(btnNo.getSelection()) {
			MixRigAction action = new MixRigAction(mixRig);
			action.setAddSubtypes(addSelect.getSelectedClassifier(), addSelect.getNewClassifiers());
			getMixRigWizard().addAction(0, action);
		}
		
		return getMixRigWizard().getFinishing();
	
	}
}
