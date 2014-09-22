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

public class HetCollSecondPage extends HetCollPage {

	public Button btnYes;
	public Button btnNo;
	private Label label;
	private Label lblAllParts;
	private List partList;
	
	public HetCollSecondPage(HetCollOccurrence hetColl) 
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
		question.setJustify(true);
		question.setText("Are the cardinality constraints imposed by the multiple memberOf relations connected to "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+", really necessary?" +
						"\r\n\r\n" +
						"In other words, must an instance of "+occurrence.getWhole().getName()+" be composed of at least one instance of "+
						occurrence.getMemberProperties().get(0).getType().getName()+", one of "+occurrence.getMemberProperties().get(1).getType().getName()+" and etc?");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setText("Yes");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setText("No, create a single general memberOf");
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		lblAllParts = new Label(container, SWT.NONE);
		lblAllParts.setText("Cardinality constraints:");
		
		partList = new List(container, SWT.BORDER | SWT.V_SCROLL);
		
		for (Property p : occurrence.getMemberProperties()) {
			partList.add(OntoUMLNameHelper.getAllDetails(p.getAssociation()));
		}
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, btnNo, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, btnYes, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE))
					.add(9))
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(label, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(partList, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblAllParts, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(question, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnYes)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(btnNo)
					.add(18)
					.add(label)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(lblAllParts)
					.add(3)
					.add(partList, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
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
			return ((HetCollWizard)getWizard()).getThirdPage();
		}
		
		if(btnNo.getSelection()){
			//Action =============================
			HetCollAction newAction = new HetCollAction(occurrence);
			newAction.setMergeMemberOf();
			getAntipatternWizard().replaceAction(0,newAction);	
			//======================================
		
		}
		return ((AntipatternWizard)getWizard()).getFinishing();
	}
}
