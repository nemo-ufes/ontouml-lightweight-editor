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
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class MixRigThirdPage  extends MixRigPage {

	//GUI
	AddSelectTypeComposite addSelect;
	private Button btnYes;
	private Button btnNo;	
	
	private SelectionAdapter listener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnNo.getSelection()){
				addSelect.setEnabled(true);
				if(addSelect.getSelectedClassifier().size()+addSelect.getNewClassifiers().size()>0)
					setPageComplete(true);
				else
					setPageComplete(false);
			}
			if(btnYes.getSelection()){
				setPageComplete(true);
				addSelect.setEnabled(false);
			}
		}
	};
	
	private SelectionAdapter addRemoveListener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnNo.getSelection()){
				addSelect.setEnabled(true);
				if(addSelect.getSelectedClassifier().size()+addSelect.getNewClassifiers().size()>0)
					setPageComplete(true);
				else
					setPageComplete(false);
			}
		}
	};
		
	private Label lblSelectExistingTypes;
		
	public MixRigThirdPage(MixRigOccurrence mixRig) {
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
		styledText.setText("Are all "+getMixRigWizard().oppositeRigidity+" subtypes of "+getMixRigWizard().mixinName+" OUT of the scope of the ontology?");
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		btnNo.addSelectionListener(listener);

		try {
			addSelect = new AddSelectTypeComposite(container, SWT.BORDER, mixRig.getParser(), getMixRigWizard().allowedStereotypes(),addRemoveListener);
			addSelect.setEnabled(false);
			
			lblSelectExistingTypes = new Label(container, SWT.NONE);
			lblSelectExistingTypes.setText("If No, please select existing types or create new ones using the table below:");
			GroupLayout gl_container = new GroupLayout(container);
			gl_container.setHorizontalGroup(
				gl_container.createParallelGroup(GroupLayout.LEADING)
					.add(gl_container.createSequentialGroup()
						.add(10)
						.add(gl_container.createParallelGroup(GroupLayout.LEADING)
							.add(gl_container.createSequentialGroup()
								.add(btnYes, GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
								.addContainerGap())
							.add(gl_container.createSequentialGroup()
								.add(gl_container.createParallelGroup(GroupLayout.LEADING)
									.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
									.add(addSelect, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
									.add(lblSelectExistingTypes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
									.add(btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
								.add(10))))
			);
			gl_container.setVerticalGroup(
				gl_container.createParallelGroup(GroupLayout.LEADING)
					.add(gl_container.createSequentialGroup()
						.add(10)
						.add(styledText, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
						.add(6)
						.add(btnYes)
						.add(6)
						.add(btnNo)
						.add(16)
						.add(lblSelectExistingTypes)
						.add(6)
						.add(addSelect, GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
						.add(9))
			);
			container.setLayout(gl_container);
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
