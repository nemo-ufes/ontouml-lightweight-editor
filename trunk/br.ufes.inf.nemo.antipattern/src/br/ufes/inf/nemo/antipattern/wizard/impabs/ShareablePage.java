package br.ufes.inf.nemo.antipattern.wizard.impabs;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;

public class ShareablePage extends ImpAbsPage<MetaPropertiesTableBuilder> {

	Meronymic meronymic;
	Classifier whole, part;
	private final String IS_SHAREABLE = "isShareable";
	
	public ShareablePage(ImpAbsOccurrence occurrence) throws Exception {
		super(occurrence,"ShareablePage");
		setDescription("Meronymic: "+occurrence.getParser().getStringRepresentation(occurrence.getAssociation())+" (isShareable)");

		if(occurrence.getAssociation() instanceof Meronymic)
			meronymic = (Meronymic) occurrence.getAssociation();
		else
			throw new Exception("Can't create isEssential Page! Association provided is not a Meronymic relation.");
	
		whole = (Classifier) OntoUMLParser.getWholeEnd(meronymic).getType();
		part = (Classifier) OntoUMLParser.getPartEnd(meronymic).getType();
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		setPageComplete(true);
		
		String contextualizationContent = 	"The isShareable meta-property of part-whole relations states that every instance of the part type can only be connected " +
											"to at most one whole. That implies that every meronymic, directly or indirectly connected to the part type, must have the upper " +
											"multiplicity on the whole end equal to one, and the sum of the lower multiplicity on the whole ends lower or equal to one." +
											
											"\n\nThe current value for isShareable in <"+parser.getStringRepresentation(meronymic)+"> is: "+meronymic.isIsShareable()+"."+
											
											"\n\nIs that true for all subtypes?";

		contextualizationText = new StyledText(container, SWT.WRAP | SWT.V_SCROLL);
		contextualizationText.setBackground(container.getBackground());
		contextualizationText.setBounds(10, 10, STD_WIDTH, 140);
		contextualizationText.setText(contextualizationContent);
		contextualizationText.setJustify(true);
		
		try {
			ArrayList<String> columnNames = new ArrayList<String>();
			columnNames.add(IS_SHAREABLE);
			
			builder = new MetaPropertiesTableBuilder(container,SWT.BORDER | SWT.FULL_SELECTION, occurrence, 120, columnNames);
						
			table = builder.getTable();
			table.setBounds(10, TBL_POS_Y, STD_WIDTH, 161);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			btnAddLine = builder.getBtnAddLine();
			btnAddLine.setBounds(STD_WIDTH-75, BTN_POS, 75, 25);
			btnAddLine.setText("Add Line");
			
		} catch (Exception e) {	e.printStackTrace(); }
	}
	
	@Override
	public IWizardPage getNextPage(){
		
		getImpAbsWizard().removeAllActions(0, ImpAbsAction.Action.SET_SHAREABLE);
		
		for (MetaPropertyLine line : builder.getLines()) {
			ImpAbsAction action = new ImpAbsAction(occurrence);
			action.setIsShareable(line.getSource(), line.getTarget(), line.getValue(IS_SHAREABLE));
			getImpAbsWizard().addAction(0, action);
		}
		
		return getImpAbsWizard().getFinishing();
	}
	
}
