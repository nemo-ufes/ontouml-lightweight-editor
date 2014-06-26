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
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

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
		btnInstrinsic.setText("By intrinsic properties");
		btnInstrinsic.addSelectionListener(btnAdpater);
		
		btnRelational = new Button(container, SWT.RADIO);
		btnRelational.setText("By relational properties");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnInstrinsic, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnRelational, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnInstrinsic)
					.add(6)
					.add(btnRelational))
		);
		container.setLayout(gl_container);
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
