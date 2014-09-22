package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLNameHelper;
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
		String subtypeName = OntoUMLNameHelper.getTypeAndName(decint.getSubtype(), true, true);
		
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);			
		
		StyledText questionText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setText(subtypeName+" specialize types that follow different identity principles. " +
							"That generates a logical contradiction, which implies that it cannot have any instances. " +
							"The list below shows the current identity providers, please choose only one:");
		questionText.setJustify(true);
		
		identityProviderList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, identityProviderList, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, questionText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(identityProviderList, GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		identityProviderList.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(identityProviderList.getSelectionIndex()!=-1){
					
					selectedIP=decint.getIdentityProviders().get(identityProviderList.getSelectionIndex());
					
					if(!isPageComplete())
						setPageComplete(true);
				}
				else{
					selectedIP=null;
					
					if(isPageComplete())
						setPageComplete(false);
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
		if(identityProviderList.getSelectionCount()!=-1){
			DecIntAction action = new DecIntAction(decint);
			action.setFixIdentityProvider(selectedIP);
			getDecIntWizard().replaceAction(0, action);
			
			if(decint.getIdentityProviders().containsAll(decint.getRelevantParents())){
				getDecIntWizard().getFinishing();
			}
			
			if(getDecIntWizard().getGeneralizationSetPage()!=null)
				return getDecIntWizard().getGeneralizationSetPage();
			
			return getDecIntWizard().getIntentionalDerivedPage();
		}
		
		return super.getNextPage();
	}
}

