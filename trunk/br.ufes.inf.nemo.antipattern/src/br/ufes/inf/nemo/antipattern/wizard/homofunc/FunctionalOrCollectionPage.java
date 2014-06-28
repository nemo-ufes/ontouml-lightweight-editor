package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.SubstanceSortal;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLNameHelper;


//First Page
public class FunctionalOrCollectionPage extends HomoFuncPage {
	
	public Button btnYes;
	public Button btnNo;
	
	public FunctionalOrCollectionPage(HomoFuncOccurrence homoFunc) {
		super(homoFunc);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText question = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		question.setJustify(true);
		question.setAlwaysShowScrollBars(false);
		question.setBackground(question.getParent().getBackground());
		
		question.setText("The whole type "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+" represents a functional complex. " +
						"The embedded semantics is that the whole has an heterogeneous internal structure, " +
						"in which its parts contribute differently for the function of the whole. " +
						"\r\n\r\n" +
						"The model only captures one type of part: "+OntoUMLNameHelper.getNameAndType(occurrence.getPartEnd())+". " +
						"Do you mean that all parts have the same function (or play the same role) regarding the whole?");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes - whole is a collection");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No - whole is a functional complex");		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(question, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnYes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.add(6)
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
		HomoFuncAction action;
		getAntipatternWizard().removeAllActions(0);
		
		if(btnYes.getSelection()){
			getAntipatternWizard().removeAllActions(3);
			getAntipatternWizard().removeAllActions(4);
			
			if (occurrence.getWhole() instanceof SubstanceSortal)
			{	
				//Action =============================
				action = new HomoFuncAction(occurrence);
				action.setChangeNatureToCollection(); 
				getAntipatternWizard().addAction(0,action);	
				//======================================
				
				if(occurrence.isCollectionPart()){
					return getAntipatternWizard().getMemberOrSubCollectionPage();
				}
				else{
					//Action =============================
					action = new HomoFuncAction(occurrence);
					action.setChangeToMemberOf(); 
					getAntipatternWizard().addAction(0, action);	
					//======================================
					
					return getAntipatternWizard().getFinishing();	
				}							
			}
			
			if(occurrence.getWholeIdentityProviders().size()==0){
				return getAntipatternWizard().getDefineIdentityProviderPage();
			}
			else{
				return getAntipatternWizard().getFixNaturePage();
			}			
		}
		
		if(btnNo.getSelection()){
			getAntipatternWizard().removeAllActions(1);
			getAntipatternWizard().removeAllActions(2);
			return getAntipatternWizard().getCreatePartPage();
		}
		
		return getAntipatternWizard().getFinishing();
	}
	
	
}

