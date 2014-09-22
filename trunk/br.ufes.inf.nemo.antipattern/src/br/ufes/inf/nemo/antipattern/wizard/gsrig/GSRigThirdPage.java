package br.ufes.inf.nemo.antipattern.wizard.gsrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Kind;
import RefOntoUML.Quantity;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.GSRig.GSRigOccurrence;

public class GSRigThirdPage extends GSRigPage {
	private StyledText lblNowThatWe;
	private Button btnAntiRigid;
	private Button btnSemiRigid;
	private Button btnRigid;

	public GSRigThirdPage(GSRigOccurrence gsrig) 
	{
		super(gsrig);		
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		lblNowThatWe = new StyledText(container, SWT.WRAP | SWT.READ_ONLY);
		lblNowThatWe.setText("Now that we established that all subtypes belong to the same generalization set, let's verify the rigidity of the GS's supertype. " +
							"The options below are the possible rigidity options for "+OntoUMLNameHelper.getTypeAndName(gsrig.getParent(), true, true)+":");
		lblNowThatWe.setBackground(lblNowThatWe.getParent().getBackground());
		lblNowThatWe.setJustify(true);

		btnRigid = new Button(container, SWT.RADIO);
		btnRigid.setText("Rigid: if x instantiates it in a given moment, it must always do so in every possible ");
		
		btnAntiRigid = new Button(container, SWT.RADIO);
		btnAntiRigid.setText("Anti-Rigid: if x instantiates it in a given moment, there is at least one possible situation in which x does not do so");
		
		btnSemiRigid = new Button(container, SWT.RADIO);
		btnSemiRigid.setText("Semi-Rigid: the type may act as rigid for some individuals and anti-rigid for others");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(btnSemiRigid, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
						.add(btnAntiRigid, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
						.add(btnRigid, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE)
						.add(lblNowThatWe, GroupLayout.DEFAULT_SIZE, 657, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblNowThatWe, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.add(10)
					.add(btnRigid)
					.add(6)
					.add(btnAntiRigid)
					.add(6)
					.add(btnSemiRigid))
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnRigid.getSelection()){
			
			return ((GSRigWizard)getWizard()).getFourthPage();
		}
		
		if(btnAntiRigid.getSelection()){
			
			return ((GSRigWizard)getWizard()).getSixthPage();
		}
		
		if(btnSemiRigid.getSelection())
		{		
			Classifier supertype = gsrig.getGs().getGeneralization().get(0).getGeneral();
			if(supertype instanceof Kind || supertype instanceof Quantity || supertype instanceof Collective || supertype instanceof SubKind)
			{
				//Action =============================
				GSRigAction newAction = new GSRigAction(gsrig);
				newAction.setCreateMixinSupertype();
				getGSRigWizard().replaceAction(0,newAction);
				//======================================
			}else{
				//Action =============================
				GSRigAction newAction = new GSRigAction(gsrig);
				newAction.setChangeSuperTypeToMixin();
				getGSRigWizard().replaceAction(0,newAction);
				//======================================
			}
		}
		
		return ((GSRigWizard)getWizard()).getFinishing();
	}
}