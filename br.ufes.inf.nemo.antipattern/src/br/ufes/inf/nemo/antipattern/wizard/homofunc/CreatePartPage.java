package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class CreatePartPage extends HomoFuncPage {
	
	private CreatePartComposite createPartComposite;
	private CreateComponentOfComposite createComponentOfComposite; 
	private Label lblNewLabel;
    private Label label;
    private Label lblUseExistingPart;
    
	public CreatePartPage(HomoFuncOccurrence homoFunc) 
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
		
		StyledText question = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		question.setAlwaysShowScrollBars(false);
		question.setJustify(true);
		question.setBackground(question.getParent().getBackground());
		
		question.setText("Through your previous answer, we established that "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+" is indeed a functional complex. " +
						"That indicates the possibility that there are other parts which are not specified in the model. " +
						"\r\n\r\n" +
						"Would you like to define new parts, which are not instances of "+OntoUMLNameHelper.getTypeAndName(occurrence.getPartEnd().getType(), true, true)+"?");
		
		createPartComposite = new CreatePartComposite(container, SWT.BORDER, occurrence, false);
		createComponentOfComposite = new CreateComponentOfComposite(container, SWT.BORDER, occurrence, false);
		
		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setText("Create new types:");
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		lblUseExistingPart = new Label(container, SWT.NONE);
		lblUseExistingPart.setText("Use existing types:");
		
		if(!createComponentOfComposite.canEnable()){
			lblUseExistingPart.setText(lblUseExistingPart.getText()+" (disabled - no other functional complex type available)");
		}
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblNewLabel, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(createPartComposite, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(label, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblUseExistingPart, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(createComponentOfComposite, GroupLayout.DEFAULT_SIZE, 654, Short.MAX_VALUE)
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(lblNewLabel)
					.add(6)
					.add(createPartComposite, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
					.add(6)
					.add(label)
					.add(6)
					.add(lblUseExistingPart)
					.add(6)
					.add(createComponentOfComposite, GroupLayout.DEFAULT_SIZE, 175, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
	}	
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getAntipatternWizard().removeAllActions(3);
		
		int newParts = createPartComposite.getParts().size();
		int newCompositions = createComponentOfComposite.getRelations().size();
		
		if(newParts>0){
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setCreateNewPart(createPartComposite.getParts(),true); 
			getAntipatternWizard().addAction(3,newAction);	
			//======================================					
		}
		
		if(newCompositions>0){
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setCreateComponentOfToExistingType(createComponentOfComposite.getRelations()); 
			getAntipatternWizard().addAction(3,newAction);	
			//======================================		
		}
		
		return getAntipatternWizard().getCreateSubPartPage();
	}
	
    
}

