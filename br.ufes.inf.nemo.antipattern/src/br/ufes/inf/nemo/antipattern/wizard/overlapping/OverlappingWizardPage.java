package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Button;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingTypesVariation;
import org.eclipse.swt.custom.StyledText;

public abstract class OverlappingWizardPage extends WizardPage {

	private OverlappingOccurrence occurrence;
	private OverlappingTypesVariation variation;
	private OverlappingCheckBoxTableBuilder builder;
	private StyledText contextualizationText;
	int variationIndex;

	/**
	 * Create the wizard.
	 */
	public OverlappingWizardPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(pageName);
		setTitle("Overlapping Wizard Page");
		setDescription("Overlapping Wizard Page description");
		
		this.occurrence = occurrence;
		this.variationIndex = variationIndex;
		variation = occurrence.getVariations().get(variationIndex);
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		setControl(container);
		
		String contextualizationContent = getQuestion();
		
		contextualizationText = new StyledText(container, SWT.WRAP );
		contextualizationText.setBackground(container.getBackground());
		contextualizationText.setText(contextualizationContent);
		contextualizationText.setEditable(false);
		contextualizationText.setBounds(10, 10, 554, 95);
		
		try {
			
			int initialTablePosition = 140;
			int initialButtonPosition = initialTablePosition - 31;
			int initialLabelPosition = initialTablePosition - 21;
			
			String tableTitle = "Overlapping Types - Group "+(variationIndex+1);
			builder = new OverlappingCheckBoxTableBuilder(container,SWT.BORDER, variation, tableTitle, variationIndex);
			
			Label label = builder.getLabel();
			label.setBounds(10, initialLabelPosition, 300, 15);
			
			Table table = builder.getTable();
			table.setBounds(10, initialTablePosition, 554, 102);
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			Button btnAddLine = builder.getButton();
			btnAddLine.setBounds(489, initialButtonPosition, 75, 25);
			btnAddLine.setText("Add Line");
			
		} catch (Exception e) {	e.printStackTrace(); }
		
	}
	
	public OverlappingOccurrence getOccurrence() {
		return occurrence;
	}

	public OverlappingCheckBoxTableBuilder getBuilder() {
		return builder;
	}
	
	public ArrayList<ArrayList<Property>> getTableSelections(){
		return builder.getSelections();
	}
	public StyledText getContextualizationText() {
		return contextualizationText;
	}
	
	public int getVariationIndex(){
		return variationIndex;
	}
	
	public OverlappingTypesVariation getVariation(){
		return variation;
	}
	
	public OverlappingWizard getOverlappingWizard(){
		
		if(getWizard() instanceof OverlappingWizard)
			return (OverlappingWizard) getWizard();
		
		return null;
	}
	
	public abstract String getQuestion();
	
	public abstract void registerActions(); 
}
