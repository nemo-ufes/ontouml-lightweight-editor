package br.ufes.inf.nemo.antipattern.wizard.impabs;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;

public class DerivedPage extends ImpAbsPage<MetaPropertiesTableBuilder> {

	Property sourceEnd, targetEnd;
	private final String SOURCE_IS_DERIVED;
	private final String TARGET_IS_DERIVED;
			
	public DerivedPage(ImpAbsOccurrence occurrence) {
		super(occurrence,"DerivedPage");
		setDescription("Association: "+occurrence.getParser().getStringRepresentation(occurrence.getAssociation())+" (isDerived)");
		
		if(occurrence.getAssociation() instanceof Meronymic){
			sourceEnd = OntoUMLParser.getWholeEnd((Meronymic) occurrence.getAssociation());
			targetEnd = OntoUMLParser.getPartEnd((Meronymic) occurrence.getAssociation());
			SOURCE_IS_DERIVED = "Whole isDerived";
			TARGET_IS_DERIVED = "Part isDerived";
		}
		else {
			sourceEnd = occurrence.getAssociation().getMemberEnd().get(0);
			targetEnd = sourceEnd.getOpposite();
			SOURCE_IS_DERIVED = "Source isDerived";
			TARGET_IS_DERIVED = "Target isDerived";
		}
		
		TBL_POS_Y += 60;
		BTN_POS = TBL_POS_Y - 31;
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		setPageComplete(true);
		
		String contextualizationContent = 	"The isDerived meta-property on the ends of a relation are used to differentiate intentional relations, established " +
											"by event occurrences, from derived relations, instantiate by the consequence of some pre-determined condition. "+

											"\n\nSince all relations in OntoUML are binary and bidirectional, when setting one end as derived, the semantics of " +
											"the language force the opposite end to also be derived (even if it is not set as such)."+
											
											"\n\nThe current values of isDerived for <"+parser.getStringRepresentation(occurrence.getAssociation())+"> are:"+
											"\n"+parser.getStringRepresentation(sourceEnd)+" : "+sourceEnd.isIsDerived()+
											"\n"+parser.getStringRepresentation(targetEnd)+" : "+targetEnd.isIsDerived()+
											
											"\n\nIs that true for all subtypes?";

		contextualizationText = new StyledText(container, SWT.WRAP | SWT.V_SCROLL );
		contextualizationText.setBackground(container.getBackground());
		contextualizationText.setBounds(10, 10, STD_WIDTH, TBL_POS_Y-45);
		contextualizationText.setText(contextualizationContent);
		contextualizationText.setJustify(true);
		
		StyleRange style0 = new StyleRange();
	    style0.metrics = new GlyphMetrics(0, 0, 40);
	    Bullet bullet0 = new Bullet(style0);
	    contextualizationText.setLineBullet(5, 2, bullet0);
		contextualizationText.setEditable(false);
		
		try {
			ArrayList<String> columnNames = new ArrayList<String>();
			columnNames.add(SOURCE_IS_DERIVED);
			columnNames.add(TARGET_IS_DERIVED);
			
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
	public IWizardPage getNextPage() {
		
		getImpAbsWizard().removeAllActions(0, ImpAbsAction.Action.SET_DERIVED);
		
		for (MetaPropertyLine line : builder.getLines()) {
			ImpAbsAction action = new ImpAbsAction(occurrence);
			action.setIsDerived(line.getSource(), line.getTarget(), line.getValue(SOURCE_IS_DERIVED), line.getValue(TARGET_IS_DERIVED));
			getImpAbsWizard().addAction(0, action);
		}
		
		if(getImpAbsWizard().isMeronymic())
			return getImpAbsWizard().getEssentialPage();
		
		return getImpAbsWizard().getReadOnlyPage();
	}
}
