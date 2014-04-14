package br.ufes.inf.nemo.antipattern.wizard.impabs;

import java.util.ArrayList;
import java.util.Random;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.Bullet;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.GlyphMetrics;
import org.eclipse.swt.widgets.Composite;

import RefOntoUML.Classifier;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;

public class MultiplicityPage extends ImpAbsPage<MultiplicityTableBuilder> {

	/**
	 * Create the wizard.
	 */
	public MultiplicityPage(ImpAbsOccurrence occurrence) {
		super(occurrence,"MultiplicityPage");
		setDescription("Association: "+occurrence.getParser().getStringRepresentation(occurrence.getAssociation())+" (Multiplicity)");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		int[] sourceIndexes = {0,0};
		int[] targetIndexes = {0,0};
		
		ArrayList<Classifier> sourceChildren = occurrence.getSourceChildren();
		ArrayList<Classifier> targetChildren = occurrence.getTargetChildren();
		
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		
		String contextualizationContent = 	"When an association is defined with at least one of the end types being the supertype of two or more other types, " +
											"like <"+occurrence.getParser().getStringRepresentation(occurrence.getAssociation())+">, there is a chance the model is underconstrained."+
											" Examples of these possibly undesired consequences are:\n"; 
		
		if(sourceChildren.isEmpty() && !targetChildren.isEmpty()){
			targetIndexes = getTwoRandomIndexesFromList(targetChildren);
			contextualizationContent += exampleLine(occurrence.getSource(),targetChildren.get(targetIndexes[0]));
			contextualizationContent += exampleLine(occurrence.getSource(),targetChildren.get(targetIndexes[1]));
		}
		
		if(!sourceChildren.isEmpty() && targetChildren.isEmpty()){
			sourceIndexes = getTwoRandomIndexesFromList(sourceChildren);
			contextualizationContent += exampleLine(occurrence.getTarget(),sourceChildren.get(sourceIndexes[0]));
			contextualizationContent += exampleLine(occurrence.getTarget(),sourceChildren.get(sourceIndexes[1]));
		}
		
		if(!targetChildren.isEmpty() && !sourceChildren.isEmpty()){
			sourceIndexes = getTwoRandomIndexesFromList(sourceChildren);
			targetIndexes = getTwoRandomIndexesFromList(targetChildren);
			
			contextualizationContent += exampleLine(sourceChildren.get(sourceIndexes[0]),targetChildren.get(targetIndexes[0]));
			contextualizationContent += exampleLine(sourceChildren.get(sourceIndexes[1]),targetChildren.get(targetIndexes[1]));
		}
			
		contextualizationContent += "\n\nIf there are any restrictions regarding the number of subtypes of <"+occurrence.getTarget().getName()+"> (minimum or maximum)  " +
									"an instance of <"+occurrence.getSource().getName()+"> (or any of its subtypes) must be related, or vice versa, select them below.";
		
		contextualizationText = new StyledText(container, SWT.WRAP | SWT.V_SCROLL);
		contextualizationText.setBackground(container.getBackground());
		contextualizationText.setBounds(10, 10, STD_WIDTH, 140);
		contextualizationText.setText(contextualizationContent);
		contextualizationText.setJustify(true);
		
		StyleRange style0 = new StyleRange();
	    style0.metrics = new GlyphMetrics(0, 0, 40);
	    Bullet bullet0 = new Bullet(style0);
	    contextualizationText.setLineBullet(2, 2, bullet0);
		contextualizationText.setEditable(false);
		
		try {
			builder = new MultiplicityTableBuilder(container,SWT.BORDER | SWT.FULL_SELECTION, occurrence, 120);
						
			table = builder.getTable();
			table.setBounds(10, TBL_POS_Y, STD_WIDTH, 161);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			btnAddLine = builder.getBtnAddLine();
			btnAddLine.setBounds(STD_WIDTH-75, BTN_POS, 75, 25);
			btnAddLine.setText("Add Line");
			
		} catch (Exception e) {	e.printStackTrace(); }
	}
	
	private String exampleLine(Classifier c1, Classifier c2){
		return "\nan instance of <"+c1.getName()+"> connected exclusively to instances of <"+c2.getName()+">";
	}
	
	private int[] getTwoRandomIndexesFromList(ArrayList<?> list){
		int[] result = {0,0};
		
		if(list==null || list.size()==0)
			return result;
		if(list.size()==1)
			return result;
		if(list.size()==2){
			result[1] = 1;
			return result;
		}
		
		Random rand = new Random();
		
		result[0] = rand.nextInt(list.size());
		result[1] = rand.nextInt(list.size());
		while(result[0]==result[1]){
			result[1] = rand.nextInt(list.size());
		}
		
		return result;
	}
	
	@Override
	public IWizardPage getNextPage(){
		
		getImpAbsWizard().removeAllActions(0, ImpAbsAction.Action.SET_MULTIPLICITY);
		System.out.println("LINES SIZE: "+builder.getLines().size());
		for (MultiplicityLine line : builder.getLines()) {
			ImpAbsAction action = new ImpAbsAction(occurrence);
			action.setMultiplicity(line.getSource(), line.getTarget(), line.getLowerSource(), line.getUpperSource(), line.getLowerTarget(), line.getUpperTarget());
			getImpAbsWizard().addAction(0, action);
		}
		
		return getImpAbsWizard().getDerivedPage();
	}
	
	public MultiplicityTableBuilder getBuilder() {
		return (MultiplicityTableBuilder) builder;
	}

}
