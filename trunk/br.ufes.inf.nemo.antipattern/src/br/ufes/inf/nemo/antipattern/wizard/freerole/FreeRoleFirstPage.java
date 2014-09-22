package br.ufes.inf.nemo.antipattern.wizard.freerole;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;

public class FreeRoleFirstPage extends FreeRolePage {
	
	private StyledText styledTextQuestion;
	private Button btnYes;
	private Button btnNo;
	
	public FreeRoleFirstPage(FreeRoleOccurrence freeRole, int freeRoleIndex) 
	{
		super(freeRole);		
		this.index = freeRoleIndex;
		setDescription(	"Defined Role: " +freeRole.getDependentType().getName()+
						"\nCurrent Free Role: "+freeRole.getFreeRoles().get(index).getName());
	}
	
	public boolean contains(List list, String elem){
		for(String str: list.getItems()){
			if (str.equals(elem)) return true;
		}
		return false;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
	
		setControl(container);			
		
		styledTextQuestion = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		styledTextQuestion.setAlwaysShowScrollBars(false);
		styledTextQuestion.setBackground(styledTextQuestion.getParent().getBackground());
		styledTextQuestion.setText(	"Does an instance of "+OntoUMLNameHelper.getTypeAndName(occurrence.getDependentType(), true, true)+" becomes a "+
									OntoUMLNameHelper.getTypeAndName(occurrence.getFreeRoles().get(index), true, true)+" when a pre-determined set of conditions is valid? " +
									"\r\n\r\n"+
									"For example, on one hand a Student is a Freshman when he/she is in the first semester of his Enrollment (the relator which characterizes the Student type). " +
									"On the other hand, a Senior, is a student that is on his last semester before graduation.");		
		styledTextQuestion.setJustify(true);
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes - "+OntoUMLNameHelper.getTypeAndName(occurrence.getFreeRoles().get(index), true, true)+" is a derived type");

		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No - "+OntoUMLNameHelper.getTypeAndName(occurrence.getFreeRoles().get(index), true, true)+" is a intentional type");
		
		setAsEnablingNextPageButton(btnYes);
		setAsEnablingNextPageButton(btnNo);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(styledTextQuestion, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnYes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(styledTextQuestion, GroupLayout.PREFERRED_SIZE, 105, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnYes)
					.add(6)
					.add(btnNo))
		);
		container.setLayout(gl_container);
		
		setPageComplete(false);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if (btnYes.getSelection()) {			
			FreeRoleSecondPage secondPage = ((FreeRoleWizard)getWizard()).getSecondPage(index);			
			return secondPage;
		}
		if(btnNo.getSelection()){
			FreeRoleThirdPage thirdPage = ((FreeRoleWizard)getWizard()).getThirdPage(index);			
			return thirdPage;
		}
		
		return super.getNextPage();
	}
}