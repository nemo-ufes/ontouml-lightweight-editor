package br.ufes.inf.nemo.antipattern.wizard.decint;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;

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
		String subtypeName = decint.getSubtype().getName();
		
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);			
		
		StyledText questionText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setText("<"+subtypeName+"> specializes two or more types that are made disjoint by a generalization set, " +
							"that generates a logical contradiction and implies that it has an empty extension (no instances). " +
							"\n\nTo fix that you must either remove some generalizations to the types or modify the isDisjoint " +
							"meta-attribute of the generalization set.");
		questionText.setBounds(10, 10, 585, 77);
		questionText.setJustify(true);
		
		gsComposite = new GeneralizationSetComposite(container, SWT.BORDER, decint, this);
		gsComposite.setBounds(10, 93, 585, 275);
		setPageComplete(false);

	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		return super.getNextPage();
	}
}

