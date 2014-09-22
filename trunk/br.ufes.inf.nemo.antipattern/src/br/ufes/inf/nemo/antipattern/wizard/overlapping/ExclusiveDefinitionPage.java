package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizardPage;

public class ExclusiveDefinitionPage extends AntipatternWizardPage<OverlappingOccurrence, OverlappingWizard>{
	
	int variationIndex;
	
	public ExclusiveDefinitionPage(OverlappingOccurrence occurrence, AntipatternInfo info) {
		super(occurrence);
		this.variationIndex = 0;
		
		setTitle(info.getName());
		setDescription("Exclusive Types Definition");
	}
		
	
	
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		styledText.setText(	"Now, the concept of association exclusiveness is required. If you are not familiar with it, we advise you to read the " +
							"definition before continuing. If you already are, please go to the next page."+
							"\r\n\r\n"+
							"A set of association is said to be exclusive regarding a class if, and only if:"+
							"\r\n\r\n"+
							"The set is overlapping;"+
							"Exactly one end of every association in the set is connected to the same class (the base class);"+
							"No individual can be connected to the same instance of the base class through more than one of the associations in the set;"+
							"\r\n\r\n"+
							"In this anti-pattern the base class is the "+occurrence.getBaseClassType()+" "+OntoUMLNameHelper.getName(occurrence.getMainType(), false, true)+". " +
							"To make this notion more concrete, consider the following example:"+
							"\r\n\r\n"+
							occurrence.getExclusiveExample());
		styledText.setJustify(true);
		styledText.setBackground(styledText.getParent().getBackground());
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(styledText, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(styledText, GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
					.addContainerGap())
		);
		container.setLayout(gl_container);
		setPageComplete(true);
	}
	
	@Override
	public IWizardPage getNextPage(){
		System.out.println("hasShowExclusiveDefinition = true");
		getAntipatternWizard().hasShownExclusiveDefinition=true;
		return getAntipatternWizard().getExclusivePage(variationIndex);
	}
	
	

	@Override
	public IWizardPage getPreviousPage() {
		System.out.println("hasShowExclusiveDefinition = false");
		getAntipatternWizard().hasShownExclusiveDefinition=false;
		return super.getPreviousPage();
	}



	public void setIndex(int variationIndex) {
		this.variationIndex = variationIndex;
		
	}
	
	
	
}
