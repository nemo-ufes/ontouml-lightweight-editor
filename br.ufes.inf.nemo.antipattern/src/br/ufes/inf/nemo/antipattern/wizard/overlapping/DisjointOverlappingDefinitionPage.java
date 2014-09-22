package br.ufes.inf.nemo.antipattern.wizard.overlapping;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.overlapping.OverlappingOccurrence;

public class DisjointOverlappingDefinitionPage extends WizardPage{
	OverlappingOccurrence occurrence;
	
	public DisjointOverlappingDefinitionPage(OverlappingOccurrence occurrence, AntipatternInfo info) {
		super("ExclusiveDefinitionPage");
		this.occurrence = occurrence;
		
		setTitle(info.getName());
		setDescription("Overlapping and Disjoint Types Definition");
	}
		
	
	
	public void createControl(Composite parent) {
		
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		styledText.setText(	"To be able to successfully analyze this anti-pattern you should understand the concepts of overlapping and disjoint types. " +
							"If you are not familiar with them, we advise you to read the definitions bellow before proceeding. " +
							"If you already are, please go to the next page." +
							"\r\n\r\n" +
							"A set of types is said to be overlapping if, and only if, there is a possible instantiation of the model in which at least one individual simultaneously instantiate all types in the set. " +
							"The set containing Employer, Male and Adult types, for example, is expected to be overlapping, since there are many adult male workers in the world." +
							"\r\n\r\n" +
							"On the other hand, a set of types is classified as disjoint if, and only if, it is not overlapping. " +
							"In other words, if there is no possible instantiation in which an individual instantiate all types in the set. " +
							"Consider the types Adult, Child and Elder as an example. " +
							"Is there a single individual which instantiate all these types at the same time?" +
							"\r\n\r\n" +
							"Always remember that both definitions consider simultaneous instantiations and not throughout time. " +
							"A child can become an adult, but nobody can be an adult and a child at the same time." +
							"\r\n\r\n" +
							"This anti-pattern occurrence of the contains "+occurrence.getGroups().size()+" sets of overlapping types,  " +
							"which were obtained considering only the "+occurrence.getPropertyTypeString()+" "+OntoUMLNameHelper.getTypeAndName(occurrence.getMainType(), true, true)+". " +
							"The wizard will present each of them individually.");
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
		return ((OverlappingWizard)getWizard()).getDisjointOverlappingPage(0);
	}
	
}
