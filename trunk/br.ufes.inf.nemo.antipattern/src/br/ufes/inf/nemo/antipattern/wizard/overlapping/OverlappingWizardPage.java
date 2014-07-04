package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public abstract class OverlappingWizardPage extends AntipatternWizardPage<OverlappingOccurrence, OverlappingWizard> {

	protected OverlappingGroup variation;
	private OverlappingGroupComposite overlappingGroupComposite;
	private Label firstLineLabel;
	int variationIndex;
	protected Button noButton;
	protected Button yesButton;
	private Label questionLabel;

	/**
	 * Create the wizard.
	 */
	public OverlappingWizardPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(occurrence);
		setTitle("Overlapping Wizard Page");
		setDescription("Overlapping Wizard Page description");
		
		this.variationIndex = variationIndex;
		variation = occurrence.getGroups().get(variationIndex);
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		
		setControl(container);
		int currentIndex = variationIndex+1;
		
//		String contextualizationContent = 	"This is the overlapping group "+currentIndex+" of "+occurrence.getVariations().size()+" ." +
//											"\r\n\r\n"+
//											"Are there any ___________ types?"+
//											"\r\n\r\nIf you answer is \"Yes\" use the table below to specify which are ___________. " +
//											"If your answer is \"No\", just go directly to the next page.";
			
		firstLineLabel = new Label(container, SWT.WRAP );
		firstLineLabel.setText("Overlapping group: "+currentIndex+" of "+occurrence.getGroups().size()+" ("+occurrence.getGroups().get(variationIndex).getType()+"). ");
			
		String tableTitle = "Overlapping Set "+(variationIndex+1);
		PropertyListComposite propertyListComposite = new PropertyListComposite(container, SWT.NONE, occurrence.getGroups().get(variationIndex));
		overlappingGroupComposite = new OverlappingGroupComposite(container,SWT.NONE, variation, tableTitle, variationIndex);
		
		yesButton = new Button(container, SWT.RADIO);
		yesButton.setText("Yes");
		
		noButton = new Button(container, SWT.RADIO);
		noButton.setText("No");
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		setAsEnablingNextPageButton(noButton);
		setAsEnablingNextPageButton(yesButton);
		
		questionLabel = new Label(container, SWT.WRAP);
		questionLabel.setText(getQuestion());
		
//		Composite composite = new Composite(container, SWT.NONE);
		Composite composite = occurrence.getGroups().get(variationIndex).createComposite(container, SWT.BORDER);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(11)
					.add(firstLineLabel, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(9))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(overlappingGroupComposite, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(yesButton, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
					.add(5)
					.add(noButton, GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(propertyListComposite, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(composite, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(questionLabel, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(13)
					.add(firstLineLabel)
					.add(9)
					.add(composite, GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(propertyListComposite, GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(label)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(questionLabel, GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
						.add(yesButton)
						.add(noButton))
					.add(18)
					.add(overlappingGroupComposite, GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		setPageComplete(false);	
	}
	
	public OverlappingOccurrence getOccurrence() {
		return occurrence;
	}

	public OverlappingGroupComposite getBuilder() {
		return overlappingGroupComposite;
	}
	
	public ArrayList<ArrayList<Property>> getTableSelections(){
		return overlappingGroupComposite.getSelections();
	}
	public Label getContextualizationText() {
		return firstLineLabel;
	}
	
	public int getVariationIndex(){
		return variationIndex;
	}
	
	public OverlappingGroup getVariation(){
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
