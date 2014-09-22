package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class FixNaturePage extends HomoFuncPage {

	private StyledText question;
	private Button btnCreateNewIdentityProvider;
	private Button btnMakeWholeProvider;
	private Button btnChangeStereotypeProvider;
	private List identityProviderList;

	public FixNaturePage(HomoFuncOccurrence homoFunc) 
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
		
		question.setText("By your previous answer, we conclude that "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+" is a collection. " +
						"We still can't automatically fix your model because the whole type inherits its identity principle (and functional complex nature) from another type. " +
						"\r\n\r\n" +
						"Would like to:");
		
		btnCreateNewIdentityProvider = new Button(container, SWT.RADIO);
		btnCreateNewIdentityProvider.setText("Create a new type to act as the identity provider");
		
		btnMakeWholeProvider = new Button(container, SWT.RADIO);
		btnMakeWholeProvider.setText("Make "+occurrence.getWhole().getName()+" the identity provider");
		
		btnChangeStereotypeProvider = new Button(container, SWT.RADIO);
		btnChangeStereotypeProvider.setText("Change the nature of all current identity providers");
		
		Label lblCurrentIdentityProviders = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		Label lblCurrentIdentityProviders_1 = new Label(container, SWT.NONE);
		lblCurrentIdentityProviders_1.setText("Identity Providers:");
		
		identityProviderList = new List(container, SWT.BORDER);
		for (Classifier ip : occurrence.getWholeIdentityProviders()) {
			identityProviderList.add(OntoUMLNameHelper.getTypeAndName(ip, true, true));
		}
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(question, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnCreateNewIdentityProvider, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(btnMakeWholeProvider, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE)
						.add(btnChangeStereotypeProvider, GroupLayout.PREFERRED_SIZE, 554, GroupLayout.PREFERRED_SIZE))
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblCurrentIdentityProviders, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(identityProviderList, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblCurrentIdentityProviders_1, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnCreateNewIdentityProvider)
					.add(6)
					.add(btnMakeWholeProvider)
					.add(6)
					.add(btnChangeStereotypeProvider)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(lblCurrentIdentityProviders, GroupLayout.PREFERRED_SIZE, 7, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(lblCurrentIdentityProviders_1)
					.add(3)
					.add(identityProviderList, GroupLayout.DEFAULT_SIZE, 137, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		
		setPageComplete(false);
		setAsEnablingNextPageButton(btnChangeStereotypeProvider);
		setAsEnablingNextPageButton(btnCreateNewIdentityProvider);
		setAsEnablingNextPageButton(btnMakeWholeProvider);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getAntipatternWizard().removeAllActions(1);
		
		if(btnCreateNewIdentityProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setCreateNewIdentityProvider(); 
			getAntipatternWizard().addAction(1,newAction);	
			//======================================
		}
		
		else if(btnMakeWholeProvider.getSelection())
		{
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setChangeToCollective(); 
			getAntipatternWizard().addAction(1,newAction);	
			//======================================
		}
		
		else if(btnChangeStereotypeProvider.getSelection())
		{	
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setChangeNatureToCollection(); 
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
