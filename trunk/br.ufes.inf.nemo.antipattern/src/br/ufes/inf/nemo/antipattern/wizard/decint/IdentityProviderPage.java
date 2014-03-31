package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;

public class IdentityProviderPage  extends DecIntPage {
	
	private List identityProviderList;
	private Classifier selectedIP;
	
	public IdentityProviderPage(DecIntOccurrence decint) 
	{
		super(decint);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		String subtypeName = decint.getSubtype().getName();
		
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);			
		
		StyledText questionText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setText(	"<"+subtypeName+"> specialize types which follow different identity principles. " +
							"That generates a logical contradiction, so <"+subtypeName+"> cannot have any instances. " +
							"The list below shows the current identity providers, please choose only one:");
		questionText.setBounds(10, 10, 554, 49);
		questionText.setJustify(true);
		
		identityProviderList = new List(container, SWT.BORDER);
		identityProviderList.setBounds(10, 65, 554, 206);
		identityProviderList.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(identityProviderList.getSelectionIndex()!=-1){
					setPageComplete(true);
					selectedIP=decint.getIdentityProviders().get(identityProviderList.getSelectionIndex());
				}
				else{
					setPageComplete(false);
					selectedIP=null;
				}
			}
			
		});
		
		for (Classifier ip : decint.getIdentityProviders()) {
			identityProviderList.add(decint.getParser().getStringRepresentation(ip));
		}
		
		selectedIP = null;
		setPageComplete(false);

	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		DecIntAction action = new DecIntAction(decint);
		action.setFixIdentityProvider(selectedIP);
		getDecIntWizard().replaceAction(0, action);
		
		if(decint.getIdentityProviders().containsAll(decint.getRelevantParents()))
			getDecIntWizard().getFinishing();
		
		if(getDecIntWizard().getGeneralizationSetPage()!=null)
			return getDecIntWizard().getGeneralizationSetPage();
		
		return getDecIntWizard().getIntentionalDerivedPage();
	}
}

