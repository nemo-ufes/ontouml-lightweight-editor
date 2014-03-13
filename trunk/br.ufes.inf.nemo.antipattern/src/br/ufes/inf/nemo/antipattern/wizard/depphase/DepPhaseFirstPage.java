package br.ufes.inf.nemo.antipattern.wizard.depphase;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;

public class DepPhaseFirstPage  extends DepPhasePage {

	//GUI

	public DepPhaseFirstPage(DepPhaseOccurrence depPhase) {
		super(depPhase);
	}
		
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setBounds(10, 10, 554, 173);
		styledText.setText(	"Phases and roles capture anti-rigid types, whose instances share the same identity principle. " +
							"The main difference between them is that phases are instantiated when there is a change in an intrinsic property, such as a quality or a mode, " +
							"whilst roles are instantiated when there is a change in a relational property, capture by mediations. " +
							"\r\n\r\n" +
							"The Phase type <"+depPhase.getPhase().getName()+"> is connected to the Relators: "+getRelatorList()+", as if it is a \"RolePhase\". " +
							"So, the first step to improve the model is to establish the nature of <"+depPhase.getPhase().getName()+">. " +
							"\r\n\r\n" +
							"Is <"+depPhase.getPhase().getName()+"> really defined by a change in an intrinsic property or by the relational dependencies captured in the model?");
		styledText.setBackground(styledText.getParent().getBackground());
		styledText.setJustify(true);
		
		Button btnInstrinsic = new Button(container, SWT.RADIO);
		btnInstrinsic.setBounds(10, 189, 554, 16);
		btnInstrinsic.setText("By intrinsic properties");
		
		Button btnRelational = new Button(container, SWT.RADIO);
		btnRelational.setBounds(10, 211, 554, 16);
		btnRelational.setText("By relational properties");
		
		
	}
	
	private String getRelatorList(){
		String relatorList = "";
		for (int i = 0; i < depPhase.getRelatorEnds().size(); i++) {
			if(i!=0)
				relatorList += ", ";
			relatorList += "<"+depPhase.getRelatorEnds().get(i).getType().getName()+">";
		}
		return relatorList;
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		return null;
	
	}
}
