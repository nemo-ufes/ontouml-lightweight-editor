package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class MemberOrSubCollectionPage extends HomoFuncPage {

	private StyledText question;
	private Button btnYes;
	private Button btnNo;

	public MemberOrSubCollectionPage(HomoFuncOccurrence homoFunc) 
	{
		super(homoFunc);	
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		question = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		question.setAlwaysShowScrollBars(false);
		question.setJustify(true);
		question.setBackground(question.getParent().getBackground());
		
		question.setText("Now, both the whole the part are collections. " +
						"To make sure that we correctly refactor the model, answer the following: " +
						"\r\n\r\n" +
						"Are the members of "+OntoUMLNameHelper.getTypeAndName(occurrence.getPart(), true, true)+" also members of "+
						OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+"?");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes - change relation to subCollectionOf");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No - change relation to memberOf");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(question, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnYes, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(btnNo, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.add(3)
					.add(btnYes)
					.add(6)
					.add(btnNo))
		);
		container.setLayout(gl_container);
		setPageComplete(false);
		setAsEnablingNextPageButton(btnNo);
		setAsEnablingNextPageButton(btnYes);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{			
		getAntipatternWizard().removeAllActions(2);
		
		if(btnNo.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setChangeToMemberOf(); 
			getAntipatternWizard().addAction(2,newAction);	
			//======================================
		}
		
		else if(btnYes.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setChangeToSubCollectionOf(); 
			getAntipatternWizard().addAction(2,newAction);	
			//======================================
		}
		
		return getAntipatternWizard().getFinishing();
	}
}
