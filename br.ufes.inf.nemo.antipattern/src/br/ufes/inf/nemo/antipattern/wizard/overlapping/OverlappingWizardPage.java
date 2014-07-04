package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingGroup;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;
import org.eclipse.swt.widgets.Label;

public abstract class OverlappingWizardPage extends AntipatternWizardPage<OverlappingOccurrence, OverlappingWizard> {

	protected OverlappingGroup variation;
	private OverlappingGroupComposite overlappingGroupComposite;
	private StyledText contextualizationText;
	int variationIndex;
	protected Button noButton;
	protected Button yesButton;

	/**
	 * Create the wizard.
	 */
	public OverlappingWizardPage(String pageName, OverlappingOccurrence occurrence, int variationIndex) {
		super(occurrence);
		setTitle("Overlapping Wizard Page");
		setDescription("Overlapping Wizard Page description");
		
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
		int currentIndex = variationIndex+1;
		
		
		String contextualizationContent = 	"This is the overlapping group "+currentIndex+" of "+occurrence.getVariations().size()+" ." +
											"\r\n\r\n"+
											getQuestion();
		
//		String contextualizationContent = 	"This is the overlapping group "+currentIndex+" of "+occurrence.getVariations().size()+" ." +
//											"\r\n\r\n"+
//											"Are there any ___________ types?"+
//											"\r\n\r\nIf you answer is \"Yes\" use the table below to specify which are ___________. " +
//											"If your answer is \"No\", just go directly to the next page.";
			
		contextualizationText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		contextualizationText.setAlwaysShowScrollBars(false);
		contextualizationText.setBackground(container.getBackground());
		contextualizationText.setText(contextualizationContent);
		contextualizationText.setEditable(false);
			
		String tableTitle = "Overlapping Set "+(variationIndex+1);
		
		overlappingGroupComposite = new OverlappingGroupComposite(container,SWT.NONE, variation, tableTitle, variationIndex);
		
		yesButton = new Button(container, SWT.RADIO);
		yesButton.setText("Yes");
		
		noButton = new Button(container, SWT.RADIO);
		noButton.setText("No");
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(contextualizationText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(9))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(noButton, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(9))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(yesButton, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.add(9)
					.add(overlappingGroupComposite, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(contextualizationText, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(noButton)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(yesButton)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(label)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(overlappingGroupComposite, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		
		setAsEnablingNextPageButton(noButton);
		setAsEnablingNextPageButton(yesButton);
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
	public StyledText getContextualizationText() {
		return contextualizationText;
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
