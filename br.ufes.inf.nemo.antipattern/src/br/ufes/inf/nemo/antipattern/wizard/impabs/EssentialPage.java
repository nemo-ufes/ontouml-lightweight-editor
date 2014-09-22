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

public class EssentialPage extends ImpAbsPage<MetaPropertiesTableBuilder> {

	Meronymic meronymic;
	Classifier whole, part;
	
	private final String IS_ESSENTIAL = "isEssential";
	private final String IS_IMMUTABLE_WHOLE = "isImmutableWhole";
	
	public EssentialPage(ImpAbsOccurrence occurrence) throws Exception {
		super(occurrence,"EssentialPage");
		setDescription("Meronymic: "+occurrence.getParser().getStringRepresentation(occurrence.getAssociation())+" (isEssential)");

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
		
		String contextualizationContent = 	"The isEssential and the isImmutablePart meta-properties of part-whole relations are used to capture" +
											" specific dependencies from the whole to the part. On one hand, isEssential is used for rigid wholes " +
											"and capture that every instance of the whole type must always be composed of the same instances of the part type. " +
											
											"\n\nOn the other hand, isImmutablePart, is used for anti-rigid whole and capture that, while an " +
											"object is instantiating the whole type, it must be composed by the same objects of the given part type. " +
											"Notice that isEssential implies isImmutablePart but the opposite doesn't."+
											
											"\n\nThe values for these meta-properties in the part-whole relation <"+parser.getStringRepresentation(meronymic)+
											">, between whole <"+parser.getStringRepresentation(whole)+"> and part <"+parser.getStringRepresentation(part)+"> are: "+
											"\nisEssential: "+meronymic.isIsEssential()+
											"\nisImmutablePart: "+meronymic.isIsImmutablePart()+
											
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
			columnNames.add(IS_ESSENTIAL);
			columnNames.add(IS_IMMUTABLE_WHOLE);
						
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
		
		getImpAbsWizard().removeAllActions(0, ImpAbsAction.Action.SET_ESSENTIAL);
		
		for (MetaPropertyLine line : builder.getLines()) {
			ImpAbsAction action = new ImpAbsAction(occurrence);
			action.setIsEssential(line.getSource(), line.getTarget(), line.getValue(IS_ESSENTIAL), line.getValue(IS_IMMUTABLE_WHOLE));
			getImpAbsWizard().addAction(0, action);
		}
		
		return getImpAbsWizard().getInseparablePage();
	}
}
