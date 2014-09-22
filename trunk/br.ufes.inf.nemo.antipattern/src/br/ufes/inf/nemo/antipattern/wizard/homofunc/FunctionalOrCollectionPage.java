package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Meronymic;
import RefOntoUML.Property;
import RefOntoUML.SubstanceSortal;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;


//First Page
public class FunctionalOrCollectionPage extends HomoFuncPage {
	
	public Button btnYes;
	public Button btnNo;
	private List wholesList;
	private StyledText questionText;
	private StyledText observationText;
	
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
		
		questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setJustify(true);
		questionText.setAlwaysShowScrollBars(false);
		questionText.setBackground(questionText.getParent().getBackground());
		
		questionText.setText("The whole type "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+" represents a functional complex. " +
						"The embedded semantics is that the whole has an heterogeneous internal structure, " +
						"in which its parts contribute differently for the function of the whole. " +
						"\r\n\r\n" +
						"The model only captures one type of part: "+OntoUMLNameHelper.getNameAndType(occurrence.getPartEnd())+". " +
						"Do you mean that all parts have the same function (or play the same role) regarding the whole?");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes - whole is a collection");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No - whole is a functional complex");		
		
		Label label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		observationText = new StyledText(container, SWT.READ_ONLY | SWT.WRAP | SWT.V_SCROLL);
		observationText.setAlwaysShowScrollBars(false);
		observationText.setText("Take into consideration that "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+" is also a functional part of the types below. " +
								"If you change the whole into a collection, you will also need to fix these parthoods relations.");
		observationText.setJustify(true);
		observationText.setBackground(SWTResourceManager.getColor(240, 240, 240));
		
		wholesList = new List(container, SWT.BORDER);
		for (Property p : occurrence.getFunctionalWholes()) {
			Type part = OntoUMLParser.getPartEnd((Meronymic) p.getAssociation()).getType();
			String line = OntoUMLNameHelper.getTypeAndName(p.getType(), true, false);
			if(part.equals(occurrence.getWhole()))
				line += " (direct)";
			else
				line += " (inherited from: "+OntoUMLNameHelper.getTypeAndName(part, true, false)+")";
			wholesList.add(line);
		}
		
		if(occurrence.getFunctionalWholes().size()==0){
			wholesList.setVisible(false);
			observationText.setText("Note that "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+" is not a functional part of any other type.");
		}
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(questionText, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
						.add(btnYes, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE)
						.add(btnNo, GroupLayout.DEFAULT_SIZE, 556, Short.MAX_VALUE))
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(observationText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(wholesList, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 110, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnYes)
					.add(6)
					.add(btnNo)
					.add(18)
					.add(label, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(observationText, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(wholesList, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE))
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

