package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;

public class GeneralizationSetPage  extends DecIntPage {
	
	GeneralizationSetComposite gsComposite;
	
	public GeneralizationSetPage(DecIntOccurrence decint) 
	{
		super(decint);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);			
		
		StyledText questionText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setText(OntoUMLNameHelper.getTypeAndName(decint.getSubtype(), true, true)+" specializes two or more types that are made disjoint by a generalization set, " +
							"that generates a logical contradiction and implies that it has an empty extension (no instances). " +
							"\n\nTo fix that you must either remove the disjoint generalizations sets or set their isDisjoint " +
							"meta-attribute to false.");
		questionText.setJustify(true);
		
		gsComposite = new GeneralizationSetComposite(container, SWT.BORDER, decint, this);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, gsComposite, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, questionText, GroupLayout.DEFAULT_SIZE, 585, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 77, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(gsComposite, GroupLayout.DEFAULT_SIZE, 284, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		setPageComplete(false);

	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(gsComposite.generalizationSetsFixed()){
			DecIntAction action = new DecIntAction(decint);
			action.setFixGeneralizationSets(gsComposite.getReplicas());
			getDecIntWizard().replaceAction(1, action);
			
			return getDecIntWizard().getIntentionalDerivedPage();
		}
		
		return super.getNextPage();
	}
}

