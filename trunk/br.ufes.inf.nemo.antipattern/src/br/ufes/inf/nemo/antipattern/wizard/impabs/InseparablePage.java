package br.ufes.inf.nemo.antipattern.wizard.impabs;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Classifier;
import RefOntoUML.Meronymic;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;

public class InseparablePage extends ImpAbsPage<MetaPropertiesTableBuilder> {

	Meronymic meronymic;
	Classifier whole, part;
	
	private final String IS_INSEPARABLE = "isInseparable";
	private final String IS_IMMUTABLE_PART = "isImmutablePart";
	
	public InseparablePage(ImpAbsOccurrence occurrence) throws Exception {
		super(occurrence,"InseparablePage");
		setDescription("Meronymic: "+occurrence.getParser().getStringRepresentation(occurrence.getAssociation())+" (isInseparable)");

		if(occurrence.getAssociation() instanceof Meronymic)
			meronymic = (Meronymic) occurrence.getAssociation();
		else
			throw new Exception("Can't create isEssential Page! Association provided is not a Meronymic relation.");
	
		whole = (Classifier) OntoUMLParser.getWholeEnd(meronymic).getType();
		part = (Classifier) OntoUMLParser.getPartEnd(meronymic).getType();
		
		TBL_POS_Y += 80;
		BTN_POS = TBL_POS_Y - 31;
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		setPageComplete(true);
		String contextualizationContent = 	"Opposite to the previous meta-properties, isInseparable and isImmutableWhole capture specific dependencies " +
											"from the part to the whole on meronymic relations. " +
											
											"\n\nOn one hand, isInseparable is used for rigid part types and capture that every part must always composed " +
											"the same instances of the whole. On the other hand, isImmutableWhole, is used for anti-rigid parts and capture " +
											"that, while an object is instantiating the part type, it must be compose the same wholes. " +
											"Notice that isInseparable implies isImmutableWhole, but the opposite doesn't."+
											
											"\n\nThe values for these meta-properties in the part-whole relation <"+parser.getStringRepresentation(meronymic)+
											">, between whole <"+parser.getStringRepresentation(whole)+"> and part <"+parser.getStringRepresentation(part)+"> are: "+
											"\nisInseparable: "+meronymic.isIsInseparable()+
											"\nisImmutableWhole: "+meronymic.isIsImmutableWhole()+
											
											"\n\nIs that true for all subtypes?";

		contextualizationText = new StyledText(container, SWT.WRAP | SWT.V_SCROLL);
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
			columnNames.add(IS_INSEPARABLE);
			columnNames.add(IS_IMMUTABLE_PART);
			
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
		
		getImpAbsWizard().removeAllActions(0, ImpAbsAction.Action.SET_INSEPARABLE);
		
		for (MetaPropertyLine line : builder.getLines()) {
			ImpAbsAction action = new ImpAbsAction(occurrence);
			action.setIsInseparable(line.getSource(), line.getTarget(), line.getValue(IS_INSEPARABLE), line.getValue(IS_IMMUTABLE_PART));
			getImpAbsWizard().addAction(0, action);
		}
		
		return getImpAbsWizard().getShareablePage();
	}
}
