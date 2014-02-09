package br.ufes.inf.nemo.antipattern.wizard.impabs;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Meronymic;
import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class ImpAbsRefactoringPage extends RefactoringPage {

	private ImpAbsOccurrence occurrence;
	private RefactoringTableBuilder builder;
	private Table table;
	private Button btnAddLine;
	
	int STD_X = 10, STD_Y = 10, STD_WIDTH = 650, LBL_HEIGHT = 15, TBL_HEIGHT = 160;
	
	public ImpAbsRefactoringPage(ImpAbsOccurrence occurrence) {
		super();	
		this.occurrence = occurrence;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		setControl(container);
		
		setPageComplete(true);
		
		lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setText("To refactor the model, add a new line and choose the appropriate options: ");
		lblChooseTheAppropriate.setBounds(STD_X, STD_Y, STD_WIDTH, LBL_HEIGHT);
		
		try {
			builder = new RefactoringTableBuilder(container,SWT.BORDER | SWT.FULL_SELECTION, occurrence, 100);
			
			table = builder.getTable();
			table.setBounds(STD_X, LBL_HEIGHT+45, STD_WIDTH, TBL_HEIGHT); //161
			table.setHeaderVisible(true);
			table.setLinesVisible(true);
			
			btnAddLine = builder.getBtnAddLine();
			btnAddLine.setBounds(STD_WIDTH-65, LBL_HEIGHT+10, 75, 25);
			btnAddLine.setText("Add Line");
			
		} catch (Exception e) {}
		
		lblnoteThatSome = new Label(container, SWT.NONE);
		lblnoteThatSome.setAlignment(SWT.RIGHT);
		lblnoteThatSome.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblnoteThatSome.setText("(Note that some options may be incompatible)");
		lblnoteThatSome.setBounds(STD_X, STD_Y+LBL_HEIGHT+TBL_HEIGHT+55, STD_WIDTH, LBL_HEIGHT);
	}
	
	@Override
	public IWizardPage getNextPage(){
		
		((ImpAbsWizard)getWizard()).removeAllActions();
		ImpAbsAction action;
		
		for (RefactoringLine line : builder.getLines()) {
			if(occurrence.getAssociation() instanceof Meronymic){
				action = new ImpAbsAction(occurrence);
				action.setMultiplicity(line.getSource(), line.getTarget(), line.getLowerSource(), line.getUpperSource(), line.getLowerTarget(), line.getUpperTarget());
				((ImpAbsWizard)getWizard()).addAction(0, action);
				
				action = new ImpAbsAction(occurrence);
				action.setIsDerived(line.getSource(), line.getTarget(), line.getValue(builder.getColumnNames().get(2)), line.getValue(builder.getColumnNames().get(5)));
				((ImpAbsWizard)getWizard()).addAction(0, action);
				
				action = new ImpAbsAction(occurrence);
				action.setIsEssential(line.getSource(), line.getTarget(), line.getValue(builder.getColumnNames().get(6)), line.getValue(builder.getColumnNames().get(7)));
				((ImpAbsWizard)getWizard()).addAction(0, action);
				
				action = new ImpAbsAction(occurrence);
				action.setIsInseparable(line.getSource(), line.getTarget(), line.getValue(builder.getColumnNames().get(8)), line.getValue(builder.getColumnNames().get(9)));
				((ImpAbsWizard)getWizard()).addAction(0, action);
				
				action = new ImpAbsAction(occurrence);
				action.setIsShareable(line.getSource(), line.getTarget(), line.getValue(builder.getColumnNames().get(10)));
				((ImpAbsWizard)getWizard()).addAction(0, action);
			}
			else{
				action = new ImpAbsAction(occurrence);
				action.setMultiplicity(line.getSource(), line.getTarget(), line.getLowerSource(), line.getUpperSource(), line.getLowerTarget(), line.getUpperTarget());
				((ImpAbsWizard)getWizard()).addAction(0, action);
				
				action = new ImpAbsAction(occurrence);
				action.setIsReadOnly(line.getSource(), line.getTarget(), line.getValue(builder.getColumnNames().get(2)), line.getValue(builder.getColumnNames().get(6)));
				((ImpAbsWizard)getWizard()).addAction(0, action);
				
				action = new ImpAbsAction(occurrence);
				action.setIsDerived(line.getSource(), line.getTarget(), line.getValue(builder.getColumnNames().get(3)), line.getValue(builder.getColumnNames().get(7)));
				((ImpAbsWizard)getWizard()).addAction(0, action);
			}
		}
		
		return ((ImpAbsWizard) getWizard()).getFinishing();
	}
}
