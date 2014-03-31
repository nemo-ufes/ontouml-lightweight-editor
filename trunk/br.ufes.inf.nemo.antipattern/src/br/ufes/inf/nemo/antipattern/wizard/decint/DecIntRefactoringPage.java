package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.decint.DecIntAntipattern;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class DecIntRefactoringPage extends RefactoringPage {
	
	public DecIntOccurrence decint;
	private GeneralizationSetComposite gsComposite;
	private List listIdentityProvider;
	private Button btnIsDerived;
	private Label lblChoose;
	private Label lblFixTheFollowing;
	private Classifier selectedIP;
		
	/**
	 * Create the wizard.
	 */
	public DecIntRefactoringPage(DecIntOccurrence decint) 
	{
		super();	
		this.decint = decint;
		
		setTitle(DecIntAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public DecIntWizard getDecIntWizard(){
		return (DecIntWizard)getWizard();
	}
	
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);				
		
		int Y_POS = 10;
		int STD_WIDTH = 585;
	
		if(decint.getIdentityProviders().size()>1){
		
			lblChoose = new Label(container, SWT.NONE);
			lblChoose.setBounds(10, Y_POS, STD_WIDTH, 15);
			lblChoose.setText("Please, choose the identity provider");
			Y_POS += 21;
			
			listIdentityProvider = new List(container, SWT.BORDER);
			listIdentityProvider.setBounds(10, Y_POS, STD_WIDTH, 51);
			Y_POS += 57;
			
			for (Classifier ip : decint.getIdentityProviders()) {
				listIdentityProvider.add(decint.getParser().getStringRepresentation(ip));
			}
			
			listIdentityProvider.select(0);
			selectedIP = decint.getIdentityProviders().get(0);
			listIdentityProvider.addSelectionListener(identityListener);
		}
		else{
			lblChoose = null;
			listIdentityProvider = null;
			selectedIP = null;
		}
		
		if(decint.getDisjointGSList().size()>0){
				lblFixTheFollowing = new Label(container, SWT.NONE);
				lblFixTheFollowing.setText("Please, fix the following Generalization Sets: ");
				lblFixTheFollowing.setBounds(10, Y_POS, STD_WIDTH, 15);
				Y_POS += 21;
				gsComposite = new GeneralizationSetComposite(container, SWT.BORDER, decint, this);
				gsComposite.setBounds(10, Y_POS, STD_WIDTH, 275);
				Y_POS += 281;
				
				setPageComplete(false);
		}
		else {
			setPageComplete(true);
		}
		
		btnIsDerived = new Button(container, SWT.CHECK);
		btnIsDerived.setBounds(10, Y_POS, STD_WIDTH, 16);
		btnIsDerived.setText("<Subtype> is derived by the intersection of its supertypes");
		
		
	}
	
	private SelectionAdapter identityListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent arg0) {
					
			if(decint.getIdentityProviders().size()>1 && listIdentityProvider.getSelectionIndex()!=-1)
				selectedIP=decint.getIdentityProviders().get(listIdentityProvider.getSelectionIndex());
			
		}
	};

	@Override
	public IWizardPage getNextPage() 
	{
		DecIntAction action;
		
		if(decint.getIdentityProviders().size()>1){
			action = new DecIntAction(decint);
			action.setFixIdentityProvider(selectedIP);
			getDecIntWizard().replaceAction(0, action);
		}
		
		if(decint.getDisjointGSList().size()>0){
			action = new DecIntAction(decint);
			action.setFixGeneralizationSets(gsComposite.getReplicas());
			getDecIntWizard().replaceAction(1, action);
		}
		
		if(btnIsDerived.getSelection()){
			action = new DecIntAction(decint);
			action.setDeriveByIntersection();
			getDecIntWizard().replaceAction(2, action);
		}
			
		return ((DecIntWizard)getWizard()).getFinishing();
	}
}
