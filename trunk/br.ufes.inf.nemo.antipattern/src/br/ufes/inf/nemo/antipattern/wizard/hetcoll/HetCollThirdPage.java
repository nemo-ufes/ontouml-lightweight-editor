package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;

public class HetCollThirdPage extends HetCollPage {

	public Button btnYes;
	public Button btnNo;
	public Button btnArrowLeft;
	public Button btnArrowRight;
	
	public List subCollectionList;
	public List memberOfList;
	
	public ArrayList<Property> memberArray;
	public ArrayList<Property> subCollectionArray;
	
	public HetCollThirdPage(HetCollOccurrence hetColl) 
	{
		super(hetColl);		
		setDescription("Whole: "+OntoUMLNameHelper.getTypeAndName(hetColl.getWhole(), true, true));
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText questionText = new StyledText(container, SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		questionText.setJustify(true);
		questionText.setAlwaysShowScrollBars(false);
		questionText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		questionText.setText("Are the parts of the "+OntoUMLNameHelper.getTypeAndName(occurrence.getWhole(), true, true)+"’s parts also a part of it? ");
		
		StyledText memberText = new StyledText(container, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		memberText.setTopMargin(5);
		memberText.setLeftMargin(5);
		memberText.setRightMargin(5);
		memberText.setBottomMargin(5);
		memberText.setJustify(true);
		memberText.setAlwaysShowScrollBars(false);
		memberText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		memberText.setText(	"No. (Intransitivity)" +
							"\r\n\r\n" +
							"The following parts are members of "+occurrence.getWhole().getName()+":");
		
		subCollectionArray = new ArrayList<Property>(occurrence.getCollectionProperties());
		subCollectionList = new List(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		for(Property p: subCollectionArray)
			subCollectionList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, true));
		
		btnArrowRight = new Button(container, SWT.NONE);
		btnArrowRight.setText("->");
		btnArrowRight.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int index = memberOfList.getSelectionIndex();
				
				if(index==-1)
					return;
				
				memberOfList.remove(index);
				Property p = memberArray.remove(index);
				
				subCollectionList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, true));
				subCollectionArray.add(p);
			}
		});
				
		btnArrowLeft = new Button(container, SWT.NONE);
		btnArrowLeft.setText("<-");
		btnArrowLeft.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int index = subCollectionList.getSelectionIndex();
				
				if(index==-1)
					return;
				
				subCollectionList.remove(index);
				Property p = subCollectionArray.remove(index);
				
				memberOfList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, true));
				memberArray.add(p);
			}
		});
				
		memberArray = new ArrayList<Property>(occurrence.getMemberProperties());
		memberOfList = new List(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		for(Property p: memberArray)
			memberOfList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, true));
		
		StyledText subCollectionText = new StyledText(container, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.READ_ONLY);
		subCollectionText.setLeftMargin(5);
		subCollectionText.setRightMargin(5);
		subCollectionText.setTopMargin(5);
		subCollectionText.setJustify(true);
		subCollectionText.setAlwaysShowScrollBars(false);
		subCollectionText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		subCollectionText.setText(	"Yes. (Transitivity)" +
									"\r\n\r\n" +
									"The following parts are subcollections of "+occurrence.getWhole().getName()+":");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(questionText, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
						.add(gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(memberText, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
								.add(memberOfList, GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE))
							.add(6)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(btnArrowRight, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.add(btnArrowLeft, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
							.add(6)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(subCollectionText, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
								.add(subCollectionList, GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE))))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(memberText, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(memberOfList, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))
						.add(gl_container.createSequentialGroup()
							.add(70)
							.add(btnArrowRight)
							.add(6)
							.add(btnArrowLeft))
						.add(gl_container.createSequentialGroup()
							.add(subCollectionText, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(subCollectionList, GroupLayout.DEFAULT_SIZE, 134, Short.MAX_VALUE))))
		);
		container.setLayout(gl_container);
		
		
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		//Action =============================
		HetCollAction newAction = new HetCollAction(occurrence);
		newAction.setChangeToSubCollectionOfAndMemberOf(subCollectionArray, memberArray);
		getAntipatternWizard().replaceAction(0,newAction);	
		//======================================
		
		return ((AntipatternWizard)getWizard()).getFinishing();		
	}	
}
