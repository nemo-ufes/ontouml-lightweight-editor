package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

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

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;

public class HetCollFirstPage extends HetCollPage {

	public Button btnYes;
	public Button btnNo;
	private Label label;
	private Label lblAllParts;
	private List partList;
	
	public HetCollFirstPage(HetCollOccurrence hetColl) 
	{
		super(hetColl);		
		setDescription("Whole: "+OntoUMLNameHelper.getTypeAndName(hetColl.getWhole(), true, true));
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText question = new StyledText(container, SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		question.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		question.setAlwaysShowScrollBars(false);
		question.setText("Do all parts of a "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+" have the same function (or play the same role) regarding their whole?");
						
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes, "+OntoUMLNameHelper.getName(occurrence.getWhole(), true, false)+" is a collective.");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No, "+OntoUMLNameHelper.getName(occurrence.getWhole(), true, false)+" is a functional complex.");
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		lblAllParts = new Label(container, SWT.NONE);
		lblAllParts.setText("All Parts (Members and SubCollections):");
		
		partList = new List(container, SWT.BORDER | SWT.V_SCROLL);
		
		for (Property p : occurrence.getMemberProperties()) {
			partList.add(OntoUMLNameHelper.getNameAndType(p));
		}
		
		for (Property p : occurrence.getCollectionProperties()) {
			partList.add(OntoUMLNameHelper.getNameAndType(p));
		}
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblAllParts, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(partList, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(btnYes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(question, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnYes)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnNo)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(label)
					.addPreferredGap(LayoutStyle.UNRELATED)
					.add(lblAllParts)
					.add(3)
					.add(partList, GroupLayout.DEFAULT_SIZE, 144, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		
		setAsEnablingNextPageButton(btnNo);
		setAsEnablingNextPageButton(btnYes);
		setPageComplete(false);
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnYes.getSelection()){
			return ((HetCollWizard)getWizard()).getSecondPage();
		}
		
		if(btnNo.getSelection()){
			//Action =============================
			HetCollAction newAction = new HetCollAction(occurrence);
			newAction.setChangeAllToComponentOf(); 
			getAntipatternWizard().replaceAction(0,newAction);	
			//======================================
		
		}
		return ((AntipatternWizard)getWizard()).getFinishing();
	}
}
