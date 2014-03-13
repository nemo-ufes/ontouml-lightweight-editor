package br.ufes.inf.nemo.antipattern.wizard.depphase;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;

public class DepPhaseSecondPage  extends DepPhasePage {

	//GUI

	public DepPhaseSecondPage(DepPhaseOccurrence depPhase) {
		super(depPhase);
	}
		
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);	
		
		StyledText textQuestion = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		textQuestion.setBounds(10, 10, 554, 75);
		textQuestion.setText(	"Now, we should separate the relational dependencies." +
								"\r\n\r\n" +
								"Are all relational dependencies of <"+depPhase.getPhase().getName()+"> really mandatory, " +
								"i.e. must every single instance of <"+depPhase.getPhase().getName()+"> be connected to all its relators? " +
								"Use the lists below to select the mandatory and the optional dependencies.");
		textQuestion.setBackground(textQuestion.getParent().getBackground());
		textQuestion.setJustify(true);
		
		List listMandatory = new List(container, SWT.BORDER);
		listMandatory.setBounds(10, 114, 238, 157);
		
		List listOptional = new List(container, SWT.BORDER);
		listOptional.setBounds(326, 114, 238, 157);
		
		Button btnNewButton = new Button(container, SWT.NONE);
		btnNewButton.setBounds(269, 153, 37, 25);
		btnNewButton.setText("->");
		
		Button button = new Button(container, SWT.NONE);
		button.setText("<-");
		button.setBounds(269, 184, 37, 25);
		
		Label lblMandatory = new Label(container, SWT.NONE);
		lblMandatory.setBounds(10, 93, 238, 15);
		lblMandatory.setText("Mandatory");
		
		Label lblOptional = new Label(container, SWT.NONE);
		lblOptional.setText("Optional");
		lblOptional.setBounds(326, 93, 238, 15);
		
		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		return null;
	
	}
}
