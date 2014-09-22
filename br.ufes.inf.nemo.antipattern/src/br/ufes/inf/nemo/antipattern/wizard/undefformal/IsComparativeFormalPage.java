package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
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
		
		StyledText questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
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
							"Is the relation "+OntoUMLNameHelper.getTypeAndName(occurrence.getFormal(), true, true)+", which holds between "+
							OntoUMLNameHelper.getTypeAndName(occurrence.getSource(), true, true)+" and "+OntoUMLNameHelper.getTypeAndName(occurrence.getTarget(), true, true)+" " +
							"really a Domain Comparative Formal Relation, as suggested by the stereotype choice?"
							);
		
		btnComparative = new Button(container, SWT.RADIO);
		btnComparative.setText("Yes, it is Domain Comparative");
		setAsEnablingNextPageButton(btnComparative);
		
		btnNotComparative = new Button(container, SWT.RADIO);
		btnNotComparative.setText("No, it is Formal (Internal) but not Comparative");
		setAsEnablingNextPageButton(btnNotComparative);
		
		btnMaterial = new Button(container, SWT.RADIO);
		btnMaterial.setText("No, it is Material");
		setAsEnablingNextPageButton(btnMaterial	);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, btnMaterial, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, btnNotComparative, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, btnComparative, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE)
						.add(questionText, GroupLayout.DEFAULT_SIZE, 577, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE)
					.add(1)
					.add(btnComparative)
					.add(6)
					.add(btnNotComparative)
					.add(6)
					.add(btnMaterial)
					.add(18))
		);
		container.setLayout(gl_container);
		
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

