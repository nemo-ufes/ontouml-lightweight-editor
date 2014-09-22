package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.ParsingElement;
import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;

public class UndefPhaseThirdPage extends UndefPhasePage{
	
	Composite parent;
	private Label lblForEachPhase;
	private StyledText oclText;
	private StyledText templateText;

	/**
	 * Create the wizard.
	 */
	public UndefPhaseThirdPage(UndefPhaseOccurrence up) 
	{
		super(up);
		setDescription((new ParsingElement(up.getPartition(),true,"")).toString());
	}
	
	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		lblForEachPhase = new Label(container, SWT.NONE);
		lblForEachPhase.setText("For each phase, please specify the OCL derivation rule to define its extension:");
		
		oclText = new StyledText(container, SWT.BORDER);
		
		templateText = new StyledText(container, SWT.BORDER | SWT.READ_ONLY);
		templateText.setText("context <Phase> :: allInstances() : Set(<Phase>)\r\nbody : <Supertype>.allInstances()->select ( x | <CONDITION>)");
		
		Label lblOclderivationRuleTemplate = new Label(container, SWT.NONE);
		lblOclderivationRuleTemplate.setText("Template:");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, oclText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, lblOclderivationRuleTemplate, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, templateText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, lblForEachPhase, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblOclderivationRuleTemplate)
					.add(6)
					.add(templateText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.add(19)
					.add(lblForEachPhase)
					.add(6)
					.add(oclText, GroupLayout.DEFAULT_SIZE, 163, Short.MAX_VALUE)
					.addContainerGap())
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() {
			
		if(oclText.getText()!=null && !oclText.getText().isEmpty() && !oclText.getText().trim().isEmpty())
		{
			//Action =============================
			UndefPhaseAction newAction = new UndefPhaseAction(occurrence);
			newAction.setCreateOclDerivationRules(oclText.getText());
			getAntipatternWizard().replaceAction(1,newAction);	
			//======================================			
		}
		
		return getAntipatternWizard().getFinishing();
	}
}