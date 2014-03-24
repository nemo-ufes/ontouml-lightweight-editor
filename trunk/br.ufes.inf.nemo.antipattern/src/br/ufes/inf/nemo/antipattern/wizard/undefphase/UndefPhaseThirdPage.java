package br.ufes.inf.nemo.antipattern.wizard.undefphase;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.undefphase.UndefPhaseOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.ParsingElement;
import org.eclipse.swt.custom.StyledText;

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
		lblForEachPhase.setBounds(10, 84, 554, 15);
		lblForEachPhase.setText("For each phase, please specify the OCL derivation rule to define its extension:");
		
		oclText = new StyledText(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		oclText.setBounds(10, 105, 554, 139);
		
		templateText = new StyledText(container, SWT.BORDER | SWT.READ_ONLY);
		templateText.setText("context <Phase> :: allInstances() : Set(<Phase>)\r\nbody : <Supertype>.allInstances()->select ( x | <CONDITION>)");
		templateText.setBounds(10, 31, 554, 34);
		
		Label lblOclderivationRuleTemplate = new Label(container, SWT.NONE);
		lblOclderivationRuleTemplate.setBounds(10, 10, 554, 15);
		lblOclderivationRuleTemplate.setText("Template:");
	}
	
	@Override
	public IWizardPage getNextPage() {
			
		if(oclText.getText()!=null && !oclText.getText().isEmpty() && !oclText.getText().trim().isEmpty())
		{
			//Action =============================
			UndefPhaseAction newAction = new UndefPhaseAction(up);
			newAction.setCreateOclDerivationRules(oclText.getText());
			getUndefPhaseWizard().replaceAction(1,newAction);	
			//======================================			
		}
		
		return getUndefPhaseWizard().getFinishing();
	}
}