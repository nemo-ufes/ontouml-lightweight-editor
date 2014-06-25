package br.ufes.inf.nemo.antipattern.wizard.mixrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.mixrig.MixRigAntipattern;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class MixRigFirstPage extends MixRigPage{

	//GUI
	
	private SelectionAdapter listener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnNo.getSelection() || btnYes.getSelection()){
				setPageComplete(true);
			}
		}
	};
	private Button btnYes;
	private Button btnNo;
	
	public MixRigFirstPage(MixRigOccurrence mixRig) {
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
		styledText.setText(	"Mixins are non-rigid types, which are used to generalize others that have different values for their rigidity meta-property. " +
							"\r\n\r\n" +
							"Even though all represented subtypes of the mixin type <"+getMixRigWizard().mixinName+"> are "+getMixRigWizard().subtypesRigidity+", " +
							"is it still possible for an "+getMixRigWizard().oppositeRigidity+" type to be generalized into "+getMixRigWizard().mixinName+"?");
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes ("+getMixRigWizard().oppositeRigidity+" subtypes allowed)");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No (only "+getMixRigWizard().subtypesRigidity+" subtypes)");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnYes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnYes)
					.add(6)
					.add(btnNo))
		);
		container.setLayout(gl_container);
		btnNo.addSelectionListener(listener);
	}
	
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getMixRigWizard().removeAllActions();
		
		if(btnYes.getSelection())
			return getMixRigWizard().getSecondPage();
		if(btnNo.getSelection()) {
			MixRigAction action = new MixRigAction(mixRig);
			action.setChangeMixinStereotype();
			getMixRigWizard().addAction(0, action);
			return getMixRigWizard().getFinishing();
		}
		
		return super.getNextPage();
	}
}
