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
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;

public class ReadOnlyPage extends ImpAbsPage<MetaPropertiesTableBuilder> {

	Property sourceEnd, targetEnd;
	private final String SOURCE_READ_ONLY = "Source isReadOnly";
	private final String TARGET_READ_ONLY = "Target isReadOnly";
	
	public ReadOnlyPage(ImpAbsOccurrence occurrence) throws Exception {
		super(occurrence,"ReadOnlyPage");
		setDescription("Association: "+occurrence.getParser().getStringRepresentation(occurrence.getAssociation())+" (isReadOnly)");

		if(occurrence.getAssociation() instanceof Meronymic)
			throw new Exception("Can't create readOnly Page! Association provided is a Meronymic relation.");
		
		sourceEnd = occurrence.getAssociation().getMemberEnd().get(0);
		targetEnd = sourceEnd.getOpposite();
	}

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		setPageComplete(true);
		
		String contextualizationContent = 	"The readOnly meta-property on the association ends of a relation are used to capture " +
											"specific dependencies between the related types." +
											
											"\n\nWhen it is set to true on a given association end, the embedded semantics is that " +
											"every instance of the opposite end must always be connected to the same instance of the readOnly end."+
											
											"\n\nThe current values of readOnly for <"+parser.getStringRepresentation(occurrence.getAssociation())+"> are:"+
											"\n"+parser.getStringRepresentation(sourceEnd)+" : "+sourceEnd.isIsReadOnly()+
											"\n"+parser.getStringRepresentation(targetEnd)+" : "+targetEnd.isIsReadOnly()+
											
											"\n\nIs that true for all subtypes?"+
											
											"\n\n(Note that for some types of relations, the values are set by default. " +
											"Mediations, for example, always have readOnly=true on the mediated end)";

		contextualizationText = new StyledText(container, SWT.WRAP| SWT.V_SCROLL );
		contextualizationText.setBackground(container.getBackground());
		contextualizationText.setBounds(10, 10, STD_WIDTH, 140);
		contextualizationText.setText(contextualizationContent);
		contextualizationText.setJustify(true);
		
		StyleRange style0 = new StyleRange();
	    style0.metrics = new GlyphMetrics(0, 0, 40);
	    Bullet bullet0 = new Bullet(style0);
	    contextualizationText.setLineBullet(5, 2, bullet0);
		contextualizationText.setEditable(false);
		
		try {
			ArrayList<String> columnNames = new ArrayList<String>();
			columnNames.add(SOURCE_READ_ONLY);
			columnNames.add(TARGET_READ_ONLY);
			
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
		
		getImpAbsWizard().removeAllActions(0, ImpAbsAction.Action.SET_READ_ONLY);
		
		for (MetaPropertyLine line : builder.getLines()) {
			ImpAbsAction action = new ImpAbsAction(occurrence);
			action.setIsReadOnly(line.getSource(), line.getTarget(), line.getValue(SOURCE_READ_ONLY), line.getValue(TARGET_READ_ONLY));
			getImpAbsWizard().addAction(0, action);
		}
		
		return getImpAbsWizard().getFinishing();
	}
	
}
