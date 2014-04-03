package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;

public class IntentionalDerivedPage  extends DecIntPage {

	private Button btnYes;
	private Button btnNo;
	
	public IntentionalDerivedPage(DecIntOccurrence decint) 
	{
		super(decint);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		String subtypeName = decint.getSubtype().getName();
		
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);			
		
		StyledText questionText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setText(	"Is it possible for an individual to be an instance of all direct supertypes of <"+subtypeName+">" +
								"but not be an instance of <"+subtypeName+">?");

		questionText.setBounds(10, 10, 554, 34);
		questionText.setJustify(true);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 50, 554, 16);
		btnYes.setText("Yes, <"+subtypeName+"> is an intentional subtype");
		btnYes.addSelectionListener(canGoToNextPageListener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No, <"+subtypeName+"> is derived by the intersection of its supertypes");
		btnNo.setBounds(10, 72, 554, 16);
		btnNo.addSelectionListener(canGoToNextPageListener);
	
		setPageComplete(false);

	}
	
	private SelectionListener canGoToNextPageListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			
			if(btnNo.getSelection() || btnYes.getSelection()){
				if(!isPageComplete())
					setPageComplete(true);
			}
			else{
				if(isPageComplete())
					setPageComplete(false);
			}
			
		}
	};
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnNo.getSelection()){
			DecIntAction action = new DecIntAction(decint);
			action.setDeriveByIntersection();
			getDecIntWizard().replaceAction(2, action);
			return getDecIntWizard().getFinishing();
		}
		else if (btnYes.getSelection()){
			getDecIntWizard().removeAllActions(2);
			return getDecIntWizard().getFinishing();
		}
		
		return super.getNextPage();
	}
}

