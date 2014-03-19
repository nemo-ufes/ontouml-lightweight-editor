package br.ufes.inf.nemo.antipattern.wizard.depphase;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.depphase.DepPhaseAntipattern;
import br.ufes.inf.nemo.antipattern.depphase.DepPhaseOccurrence;

public class DepPhaseFirstPage  extends DepPhasePage {

	//GUI

	private Button btnRelational;
	private Button btnInstrinsic;

	public DepPhaseFirstPage(DepPhaseOccurrence depPhase) {
		super(depPhase);
		setTitle(DepPhaseAntipattern.getAntipatternInfo().getName());
		setDescription("Phase: "+depPhase.getPhase().getName()+", Relator: "+getRelatorList());
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
		
		SelectionAdapter btnAdpater = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(btnInstrinsic.getSelection()==true || btnRelational.getSelection()==true){
					setPageComplete(true);
				}
				else {
					setPageComplete(false);
				}
			}
		};
		
		btnInstrinsic = new Button(container, SWT.RADIO);
		btnInstrinsic.setBounds(10, 189, 554, 16);
		btnInstrinsic.setText("By intrinsic properties");
		btnInstrinsic.addSelectionListener(btnAdpater);
		
		btnRelational = new Button(container, SWT.RADIO);
		btnRelational.setBounds(10, 211, 554, 16);
		btnRelational.setText("By relational properties");
		btnRelational.addSelectionListener(btnAdpater);
		
		setPageComplete(false);
	}
	
	
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getDepPhaseWizard().removeAllActions();
		
		if(btnRelational.getSelection()==true){
			DepPhaseAction action = new DepPhaseAction(depPhase);
			action.setChangeToRole();
			getDepPhaseWizard().addAction(0, action);
			return getDepPhaseWizard().getFinishing();
		}
		
		return getDepPhaseWizard().getSecondPage();
	
	}
}
