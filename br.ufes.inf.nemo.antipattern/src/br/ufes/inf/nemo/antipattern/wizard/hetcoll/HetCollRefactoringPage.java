package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class HetCollRefactoringPage extends RefactoringPage {
	
	public HetCollOccurrence hetColl;
	
	public Button changeToComponentOfRadio;
	public Button changeToSubCollectionAndMemberOfRadio;
	private Button mergeMemberOfRadio;
	private ArrayList<Button> enablingNextPageButtons;
	
	private Button btnArrowRight;
	private Button btnArrowLeft;
	
	private List subCollectionList;
	private List memberOfList;
	private List partList;
	
	private StyledText subCollectionText;
	private StyledText memberOfText;
	private StyledText componentOfText;
	private StyledText mergeText;
	
	private Label separator;
	private Label allPartsLabel;
	
	public ArrayList<Property> memberArray;
	public ArrayList<Property> subCollectionArray;
	
	/**
	 * Create the wizard.
	 */
	public HetCollRefactoringPage(HetCollOccurrence hetColl) 
	{
		super();	
		this.hetColl = hetColl;
		this.enablingNextPageButtons = new ArrayList<Button>();
		
		setTitle( HetCollAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public AntipatternWizard getHetCollWizard(){
		return (AntipatternWizard)getWizard();
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public boolean contains(List list, String elem){
		for(String str: list.getItems()){
			if (str.equals(elem)) return true;
		}
		return false;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) 
	{
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
	    
		changeToComponentOfRadio = new Button(container, SWT.RADIO);
		changeToSubCollectionAndMemberOfRadio = new Button(container, SWT.RADIO);
		
		subCollectionArray = new ArrayList<Property>(hetColl.getCollectionProperties());
		subCollectionList = new List(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		for(Property p: subCollectionArray)
			subCollectionList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, true));
		
		btnArrowRight = new Button(container, SWT.NONE);
		btnArrowRight.setText("->");
		btnArrowRight.setEnabled(false);
		btnArrowRight.addSelectionListener(new SelectionAdapter() {
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

		btnArrowLeft = new Button(container, SWT.NONE);
		btnArrowLeft.setText("<-");
		btnArrowLeft.setEnabled(false);
		btnArrowLeft.addSelectionListener(new SelectionAdapter() {
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
		
		memberArray = new ArrayList<Property>(hetColl.getMemberProperties());
		memberOfList = new List(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		for(Property p: memberArray)
			memberOfList.add(OntoUMLNameHelper.getTypeAndName(p.getType(), true, true));
		
		componentOfText = new StyledText(container, SWT.READ_ONLY | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		componentOfText.setTopMargin(5);
		componentOfText.setRightMargin(5);
		componentOfText.setLeftMargin(5);
		componentOfText.setBottomMargin(5);
		componentOfText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		componentOfText.setText(hetColl.getWhole().getName()+" is a functional complex and all partOf relations are stereotyped as «componentOf»");
		componentOfText.setJustify(true);
		componentOfText.setAlwaysShowScrollBars(false);
		
		subCollectionText = new StyledText(container, SWT.READ_ONLY | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		subCollectionText.setTopMargin(5);
		subCollectionText.setRightMargin(5);
		subCollectionText.setLeftMargin(5);
		subCollectionText.setBottomMargin(5);
		subCollectionText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		subCollectionText.setText("Change (if necessary) stereotype of the following relations to  «subCollectionOf» and change (if necessary) the nature of the respective part types to Collection:");
		subCollectionText.setJustify(true);
		subCollectionText.setAlwaysShowScrollBars(false);
		
		memberOfText = new StyledText(container, SWT.READ_ONLY | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		memberOfText.setBottomMargin(5);
		memberOfText.setLeftMargin(5);
		memberOfText.setRightMargin(5);
		memberOfText.setTopMargin(5);
		memberOfText.setText("Change stereotype (if necessary) of the following relations to  «memberOf»:");
		memberOfText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		memberOfText.setJustify(true);
		memberOfText.setAlwaysShowScrollBars(false);
		
		separator = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		allPartsLabel = new Label(container, SWT.NONE);
		allPartsLabel.setText("All parts:");
		
		partList = new List(container, SWT.BORDER);
		for (Property p : hetColl.getMemberProperties()) {
			partList.add(OntoUMLNameHelper.getNameAndType(p));
		}
		
		mergeMemberOfRadio = new Button(container, SWT.RADIO);
		
		mergeText = new StyledText(container, SWT.BORDER | SWT.READ_ONLY | SWT.WRAP);
		mergeText.setTopMargin(5);
		mergeText.setRightMargin(5);
		mergeText.setLeftMargin(5);
		mergeText.setText("There is a new type, named MemberPart, which is the super-type of all parts and is connected to "+hetColl.getWhole().getName()+" through a single «memberOf» relation. In addition, all other partOf relations are deleted.");
		mergeText.setJustify(true);
		mergeText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		mergeText.setAlwaysShowScrollBars(false);
		
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(allPartsLabel, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
							.addContainerGap())
						.add(gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(separator, GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
								.add(gl_container.createSequentialGroup()
									.add(mergeMemberOfRadio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(mergeText, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE))
								.add(gl_container.createSequentialGroup()
									.add(changeToComponentOfRadio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(componentOfText, GroupLayout.DEFAULT_SIZE, 612, Short.MAX_VALUE)
									.addPreferredGap(LayoutStyle.RELATED))
								.add(gl_container.createSequentialGroup()
									.add(changeToSubCollectionAndMemberOfRadio, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(gl_container.createParallelGroup(GroupLayout.LEADING)
										.add(subCollectionList, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
										.add(subCollectionText, GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE))
									.addPreferredGap(LayoutStyle.RELATED)
									.add(gl_container.createParallelGroup(GroupLayout.LEADING)
										.add(btnArrowRight, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE)
										.add(btnArrowLeft, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
									.add(6)
									.add(gl_container.createParallelGroup(GroupLayout.LEADING)
										.add(memberOfList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.add(memberOfText, GroupLayout.DEFAULT_SIZE, 296, Short.MAX_VALUE))
									.addPreferredGap(LayoutStyle.RELATED)))
							.add(10))
						.add(gl_container.createSequentialGroup()
							.add(partList, GroupLayout.DEFAULT_SIZE, 637, Short.MAX_VALUE)
							.addContainerGap())))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(changeToComponentOfRadio, GroupLayout.DEFAULT_SIZE, 45, Short.MAX_VALUE)
						.add(componentOfText, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(mergeMemberOfRadio, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE)
						.add(mergeText, GroupLayout.PREFERRED_SIZE, 47, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING, false)
						.add(subCollectionText, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
						.add(memberOfText, GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, changeToSubCollectionAndMemberOfRadio, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(memberOfList, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
						.add(gl_container.createSequentialGroup()
							.add(43)
							.add(btnArrowRight)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(btnArrowLeft)
							.addPreferredGap(LayoutStyle.RELATED, 29, Short.MAX_VALUE))
						.add(subCollectionList, GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
					.add(25)
					.add(separator)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(allPartsLabel)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(partList, GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		
		setPageComplete(false);
		setAsEnablingNextPageButton(changeToSubCollectionAndMemberOfRadio);
		setAsEnablingNextPageButton(mergeMemberOfRadio);
		setAsEnablingNextPageButton(changeToComponentOfRadio);
		
		btnArrowLeft.setEnabled(false);
		btnArrowRight.setEnabled(false);
	}
	
	public void setAsEnablingNextPageButton(Button b){
		if(b!=null){
			b.addSelectionListener(canGoToNextPageRadioAdapter);
			enablingNextPageButtons.add(b);
			
		}
	}
	
	//enables next page if a radio button is selected
	protected SelectionAdapter canGoToNextPageRadioAdapter = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent event) {
			boolean hasSelection = false;
			
			for (Button	button : enablingNextPageButtons) {
				if(button.getSelection()){
					hasSelection = true;
					break;
				}
			}	
			
			if(isPageComplete()!=hasSelection)
				setPageComplete(hasSelection);
			
			if(changeToSubCollectionAndMemberOfRadio.getSelection()){
				btnArrowLeft.setEnabled(true);
				btnArrowRight.setEnabled(true);
			}
			else{
				btnArrowLeft.setEnabled(false);
				btnArrowRight.setEnabled(false);
			}
				
		}
	}; 
	
	@Override
	public IWizardPage getNextPage() 
	{
		((AntipatternWizard)getWizard()).removeAllActions();
		
		if(changeToComponentOfRadio.getSelection()){
			//Action =============================
			HetCollAction newAction = new HetCollAction(hetColl);
			newAction.setChangeAllToComponentOf(); 
			getHetCollWizard().replaceAction(0,newAction);	
			//======================================
		}
		else if(changeToSubCollectionAndMemberOfRadio.getSelection()){
			//Action =============================
			HetCollAction newAction = new HetCollAction(hetColl);
			newAction.setChangeToSubCollectionOfAndMemberOf(subCollectionArray, memberArray); 
			getHetCollWizard().replaceAction(0,newAction);	
			//======================================
		}
		else if(mergeMemberOfRadio.getSelection()){
			//Action =============================
			HetCollAction newAction = new HetCollAction(hetColl);
			newAction.setMergeMemberOf(); 
			getHetCollWizard().replaceAction(0,newAction); 
			//======================================	
		}
		return ((AntipatternWizard)getWizard()).getFinishing();	
	}
}
