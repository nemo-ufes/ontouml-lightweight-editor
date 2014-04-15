package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;

public class IsComparativeFormalPage extends UndefFormalPage{

	public Composite parent;
	public Button btnComparative;
	public Button btnNotComparative;
	private Button btnMaterial;
	
	/**
	 * Create the wizard.
	 */
	public IsComparativeFormalPage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription(	"Formal: "+uf.getFormalName()+
						"\nSource: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;	
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		container.setLayout(null);
		
		StyledText questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setBounds(10, 10, 554, 257);
		questionText.setAlwaysShowScrollBars(false);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setJustify(true);
		questionText.setText(
							"The Unified Foundational Ontology, in which OntoUML is based upon, classifies relations in two main groups: material (or external) and formal (or internal). " +
							"Briefly, the difference between them is that in order for a material relation to hold between two individuals, it requires an external entity: its truth-maker. " +
							"Formal relations, on the other hand, do not require such entity." +
							"\r\n\r\n" +
							"The semantics of the generic concept of formal relation is not the same of the one embedded in the <<formal>> stereotype. " +
							"In fact, in this broader sense, all part-whole relations, mediations and characterizations are also classified as formal. " +
							"The formal stereotype is intended for a particular subset of formal relations, named Domain Comparative Formal Relation (DCFR). " +
							"\r\n\r\n" +
							"The DCFR captures relations which can be reduced to the comparison of values from qualities (Data Types) that characterize the related types. " +
							"An example of such relation is “heavier than”, which holds between two people and that can be derived from the comparison of their weights." +
							"\r\n\r\n" +
							"Is the relation <"+occurrence.getFormalName()+">, which holds between <"+occurrence.getSource().getName()+"> and <"+occurrence.getTarget().getName()+">, " +
							"really a Domain Comparative Formal Relation, as suggested by the stereotype choice?"
							);
		
		btnComparative = new Button(container, SWT.RADIO);
		btnComparative.setBounds(10, 268, 554, 16);
		btnComparative.setText("Yes, it is Domain Comparative");
		setAsEnablingNextPageButton(btnComparative);
		
		btnNotComparative = new Button(container, SWT.RADIO);
		btnNotComparative.setBounds(10, 290, 554, 16);
		btnNotComparative.setText("No, it is Formal (Internal) but not Comparative");
		setAsEnablingNextPageButton(btnNotComparative);
		
		btnMaterial = new Button(container, SWT.RADIO);
		btnMaterial.setBounds(10, 312, 554, 16);
		btnMaterial.setText("No, it is Material");
		setAsEnablingNextPageButton(btnMaterial	);
		
		setPageComplete(false);
		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnComparative.getSelection())
			return getAntipatternWizard().createDatatypePage;

		else if(btnMaterial.getSelection())
			return getAntipatternWizard().changeToMaterialPage;
		
		else if(btnNotComparative.getSelection()){
			
			if(getAntipatternWizard().hasNatureIssue)
				return getAntipatternWizard().cantDefineNaturePage;
			
			if(!getAntipatternWizard().hasRelationTypeBetweenSourceAndTarget()){
				getAntipatternWizard().noRelationTypePossiblePage.setQuestion();
				return getAntipatternWizard().noRelationTypePossiblePage;
			}
			
			getAntipatternWizard().changeStereotypePage.setQuestionUI();
			return getAntipatternWizard().changeStereotypePage;
		}
		
		return null;
	}
}

//MessageDialog.openInformation(getShell(), "Information", "You are representing a particular type of formal relation, which is not covered in OntoUML.");

