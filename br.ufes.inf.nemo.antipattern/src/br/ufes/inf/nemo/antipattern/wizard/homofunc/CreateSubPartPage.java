package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class CreateSubPartPage extends HomoFuncPage {
	private StyledText question;
	
	private Composite container;
	
	private CreateComponentOfComposite createComponentOfComposite;
	private CreatePartComposite createSubPartComposite;
	private Label lblExistingSubtypes;
	private Label label;
	private Label lblCreateSubtypes;
	
//	private Composite createComponentOfComposite;
//	private Composite createSubPartComposite;
	
	public CreateSubPartPage(HomoFuncOccurrence homoFunc) 
	{
		super(homoFunc);
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		question = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		question.setAlwaysShowScrollBars(false);
		question.setJustify(true);
		question.setBackground(question.getParent().getBackground());
		
		question.setText("You can also define new particular types of "+OntoUMLNameHelper.getTypeAndName(occurrence.getPartEnd().getType(), true, true)+" " +
						"which play different functions in the context of "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+". " +
						"\r\n\r\n" +
						"You can specify new relations to existing or new subtypes. If not, please go to the next page.");
		
		createComponentOfComposite = new CreateComponentOfComposite(container, SWT.BORDER, occurrence,true);
		createSubPartComposite = new CreatePartComposite(container, SWT.BORDER, occurrence, true);
		
		lblExistingSubtypes = new Label(container, SWT.NONE);
		lblExistingSubtypes.setText("Create new subtypes:");
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		lblCreateSubtypes = new Label(container, SWT.NONE);
		lblCreateSubtypes.setText("Use existing subtypes:");
		
		if(!createComponentOfComposite.canEnable()){
			lblCreateSubtypes.setText(lblCreateSubtypes.getText()+" (disabled - no subtype available)");
		}
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(question, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
						.add(lblExistingSubtypes, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
						.add(createSubPartComposite, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
						.add(label, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
						.add(lblCreateSubtypes, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE)
						.add(createComponentOfComposite, GroupLayout.DEFAULT_SIZE, 653, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(lblExistingSubtypes)
					.add(6)
					.add(createSubPartComposite, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
					.add(6)
					.add(label)
					.add(4)
					.add(lblCreateSubtypes)
					.add(6)
					.add(createComponentOfComposite, GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{		
		int newSubparts = createSubPartComposite.getParts().size();
		int newSubCompositions = createComponentOfComposite.getRelations().size();

		getAntipatternWizard().removeAllActions(4);
		
		if(newSubparts>0)
		{							
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setCreateNewPart(createSubPartComposite.getParts(),false); 
			getAntipatternWizard().addAction(4,newAction);	
			//======================================		
		}
		
		if(newSubCompositions>0)
		{		
			//Action =============================
			HomoFuncAction newAction = new HomoFuncAction(occurrence);
			newAction.setCreateSubComponentOfToExistingSubPart(createComponentOfComposite.getRelations()); 
			getAntipatternWizard().addAction(4,newAction);	
			//======================================		
		}
		
		if ((newSubparts+newSubCompositions) >= 2){
			String message ="The creation of more than two sub-parts might imply the emergence of a WholeOver/PartOver anti-patterns occurrences. " +
							"\nPlease run a new search for the WholeOver/PartOver antipattern when finished.";
			MessageDialog.openInformation(getShell(), "WARNING", message);				
		}
		
		return getAntipatternWizard().getFinishing();
	}
}
