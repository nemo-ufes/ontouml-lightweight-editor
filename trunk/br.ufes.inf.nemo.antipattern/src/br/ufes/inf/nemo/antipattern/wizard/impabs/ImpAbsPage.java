package br.ufes.inf.nemo.antipattern.wizard.impabs;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsAntipattern;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;

public abstract class ImpAbsPage<T extends ImpAbsTableBuilder<?>> extends WizardPage {

	protected final ImpAbsOccurrence occurrence;
	protected final OntoUMLParser parser;
	protected T builder;
	protected StyledText contextualizationText;
	protected Button btnAddLine;
	protected Table table;
	protected int TBL_POS_Y;
	protected int BTN_POS;
	protected int STD_WIDTH;
	
	/**
	 * Create the wizard.
	 */
	public ImpAbsPage(ImpAbsOccurrence occurrence, String pageName) {
		super(pageName);
		setTitle(ImpAbsAntipattern.getAntipatternInfo().getName());
		
		this.occurrence = occurrence;
		parser = occurrence.getParser();
	
		TBL_POS_Y = 185;
		BTN_POS = TBL_POS_Y - 31;
		STD_WIDTH = 600;
	}

	public ImpAbsOccurrence getOccurrence() {
		return occurrence;
	}

	public T getBuilder() {
		return builder;
	}
	
	public StyledText getContextualizationText() {
		return contextualizationText;
	}
	
	public ImpAbsWizard getImpAbsWizard(){
		if (getWizard() instanceof ImpAbsWizard)
			return (ImpAbsWizard) getWizard();
		return null;
	}
	
}
