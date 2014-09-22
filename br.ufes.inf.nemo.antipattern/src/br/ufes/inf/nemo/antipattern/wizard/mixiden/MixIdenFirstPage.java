package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenAntipattern;
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;

public class MixIdenFirstPage extends MixIdenPage{

	//GUI
	private Button btnYes;
	private Button btnNo;
	
	public MixIdenFirstPage(MixIdenOccurrence mixIden) {
		super(mixIden);	
	}
	
	private SelectionAdapter listener = new SelectionAdapter() {
		
		public void widgetSelected(SelectionEvent event) {
			if(btnNo.getSelection() || btnYes.getSelection()){
				setPageComplete(true);
			}
		}
	};
	
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
		styledText.setText(	"The "+getMixIdenWizard().mixinStereotype+" stereotype is used for "+getMixIdenWizard().mixinRigidity+" types whose extensions may " +
							"contain individuals that follow different identity principles. " +
							"\r\n\r\n" +
							"All subtypes of "+OntoUMLNameHelper.getTypeAndName(getMixIdenWizard().getAp().getMixin(), true, true)+" represented in the model follow the same identity principle. " +
							"Is it possible for another type, which follows a different identity principle, to be generalized into "+getMixIdenWizard().mixinName+"?");
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes - Allow different identity principles");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No - Forbid different identity principles");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(btnYes, GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, styledText, GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, btnNo, GroupLayout.DEFAULT_SIZE, 651, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 102, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnYes)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(btnNo)
					.add(232))
		);
		container.setLayout(gl_container);
		btnNo.addSelectionListener(listener);
	}
	
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getMixIdenWizard().removeAllActions();
		
		if(btnYes.getSelection())
			return getMixIdenWizard().getSecondPage();
		if(btnNo.getSelection()) {
			MixIdenAction action = new MixIdenAction(mixIden);
			action.setChangeMixinStereotype();
			getMixIdenWizard().addAction(0, action);
			return getMixIdenWizard().getFinishing();
		}
		
		return super.getNextPage();
	}
}
