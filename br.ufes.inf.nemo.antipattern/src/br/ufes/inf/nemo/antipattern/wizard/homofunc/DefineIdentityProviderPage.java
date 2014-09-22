package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class DefineIdentityProviderPage extends HomoFuncPage {

	private Button btnMakeWholeIdentityProvider;
	private Button btnCreateNewProvider;
	private StyledText question;
	
	public DefineIdentityProviderPage(HomoFuncOccurrence homoFunc) {
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
		
		question.setText("By your previous answer, we conclude that "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+" is a collection. " +
						"The change could not be made automatically because the type doesn't define or inherits an identity principle. " +
						"\r\n\r\n" +
						"Would you like to:");
		
		btnMakeWholeIdentityProvider = new Button(container, SWT.RADIO);
		btnMakeWholeIdentityProvider.setText("Make "+occurrence.getWhole().getName()+" the identity provider - change stereotype to Collective");
		
		btnCreateNewProvider = new Button(container, SWT.RADIO);
		btnCreateNewProvider.setText("Create a new identity provider - a collective");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(question, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnMakeWholeIdentityProvider, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(btnCreateNewProvider, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnMakeWholeIdentityProvider)
					.add(6)
					.add(btnCreateNewProvider))
		);
		container.setLayout(gl_container);
		
		setPageComplete(false);
		setAsEnablingNextPageButton(btnCreateNewProvider);
		setAsEnablingNextPageButton(btnMakeWholeIdentityProvider);
	}
	 
	@Override
	public IWizardPage getNextPage() 
	{	
		getAntipatternWizard().removeAllActions(1);
		
		if(btnMakeWholeIdentityProvider.getSelection())
		{
			//TODO won't change
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setChangeToCollective(); 
			getAntipatternWizard().addAction(1,newAction);	
			//======================================
		}
		
		else if(btnCreateNewProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setCreateNewIdentityProvider(); 
			getAntipatternWizard().addAction(1,newAction);	
			//======================================
		}
		
		if(occurrence.isCollectionPart()){
			return getAntipatternWizard().getMemberOrSubCollectionPage();
		}
		else{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setChangeToMemberOf(); 
			getAntipatternWizard().addAction(1,newAction);	
			//======================================
			
			return getAntipatternWizard().getFinishing();
		}
	}
}
