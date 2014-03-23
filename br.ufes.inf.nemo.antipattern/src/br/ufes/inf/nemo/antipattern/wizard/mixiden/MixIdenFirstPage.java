package br.ufes.inf.nemo.antipattern.wizard.mixiden;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

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
		styledText.setBounds(10, 10, 554, 102);
		styledText.setText(	"The "+getMixIdenWizard().mixinStereotype+" stereotype is used for "+getMixIdenWizard().mixinStereotype+" types whose extensions may " +
							"contain individuals that follow different identity principles. " +
							"\r\n\r\n" +
							"All subtypes of "+getMixIdenWizard().mixinName+" represented in the model follow the same identity principle. " +
							"Is it possible for another type, which follows a different identity principle, to be generalized into "+getMixIdenWizard().mixinName+"?");
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 118, 554, 16);
		btnYes.setText("Yes - Allow different identity principles");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 140, 554, 16);
		btnNo.setText("No - Forbid different identity principles");
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
