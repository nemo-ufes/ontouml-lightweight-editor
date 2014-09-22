package br.ufes.inf.nemo.antipattern.wizard.relrig;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Mediation;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelRigSecondPage extends RelRigPage {

	//GUI
	public Button btnYes;
	public Button btnNo;
	private Label label;
	private Composite composite;
	
	/**
	 * Create the wizard.
	 */
	public RelRigSecondPage(RelRigOccurrence relRig, int rigid) {
		super(relRig,rigid);
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);
		setPageComplete(false);
		
		StyledText styledText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP);
		styledText.setAlwaysShowScrollBars(false);
		styledText.setJustify(true);
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setText(	"Is it always necessary for an instance of "+OntoUMLNameHelper.getTypeAndName(rigidType, true, true)+
							" to be connected to an instance of "+OntoUMLNameHelper.getTypeAndName(occurrence.getRelator(), true, true)+"?");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes");
		setAsEnablingNextPageButton(btnYes);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No");
		setAsEnablingNextPageButton(btnNo);
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		composite = new RigidMediatedComposite(container, SWT.NONE, rigidEnd, rigid+1, occurrence.getRigidMediatedProperties().size());
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(styledText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnYes, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(btnNo, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
					.add(10))
				.add(GroupLayout.TRAILING, gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, composite, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledText, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnYes)
					.add(8)
					.add(btnNo)
					.add(18)
					.add(label, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(composite, GroupLayout.PREFERRED_SIZE, 132, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
	}	
	
	@Override
	public IWizardPage getNextPage() {
		
		if(btnYes.getSelection()) 
			return ((RelRigWizard)getWizard()).getThirdPage(rigid);
			
		else if(btnNo.getSelection()){			

			RelRigAction newAction = new RelRigAction(occurrence);
			newAction.setAddRoleSubtype(rigidType,(Mediation) rigidEnd.getAssociation());
			getAntipatternWizard().replaceAction(rigid,newAction);
			
			//=============================
									
			if(rigid < occurrence.getRigidMediatedProperties().size()-1)
				return ((RelRigWizard)getWizard()).getFirstPage(rigid+1);				
			else{								
				return ((RelRigWizard)getWizard()).getFinishing();
			}
		}
		return super.getNextPage();
	}	
}
