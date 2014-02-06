package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.swt.widgets.Button;

public class HetCollRefactoringPage extends RefactoringPage {
	
	public HetCollOccurrence hetColl;
	
	public Button btnFirstOption;
	public Button btnSecondOption;
	private Button btnThirdOption;
	
	/**
	 * Create the wizard.
	 */
	public HetCollRefactoringPage(HetCollOccurrence hetColl) 
	{
		super();	
		this.hetColl = hetColl;
		
		setTitle( HetCollAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public HetCollWizard getHetCollWizard(){
		return (HetCollWizard)getWizard();
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		btnFirstOption = new Button(container, SWT.RADIO);
		btnFirstOption.setBounds(10, 21, 554, 16);		
		btnFirstOption.setText(hetColl.getWhole().getName()+" is a functional complex and all partOf relations are stereotyped as «componentOf»");
		
		btnSecondOption = new Button(container, SWT.RADIO);
		btnSecondOption.setBounds(10, 57, 554, 16);
		btnSecondOption.setText("The parts are also collectives and their respective relations are stereotyped as «subCollectionOf»");
		
		btnThirdOption = new Button(container, SWT.RADIO | SWT.WRAP);
		btnThirdOption.setBounds(10, 85, 554, 43);
		btnThirdOption.setText("There is a new type, named MemberPart, which is the super-type of all parts and is connected to "+hetColl.getWhole().getName()+" through a single «memberOf» relation. In addition, all other partOf relations are deleted.");		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		if(btnFirstOption.getSelection()){
		
		}
		if(btnSecondOption.getSelection()){
			
		}
		if(btnThirdOption.getSelection()){
			
		}
		return super.getNextPage();
	
	}
}
