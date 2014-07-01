package br.ufes.inf.nemo.antipattern.wizard.freerole;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;

import RefOntoUML.Relator;
import RefOntoUML.Role;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleAntipattern;
import br.ufes.inf.nemo.antipattern.freerole.FreeRoleOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

public class FreeRoleRefactoringPage extends RefactoringPage {
		
	public FreeRoleOccurrence freeRole;
	private List deriveList;
	private List dependentList;
	private List independentLilst;
	private Label lblDependentRoles;
	private Label lblRolesToBe;
	private Label lblIndependentRoles;
	private Button fromDerivedToDependent;
	private Button fromDependentToDerived;
	private Button fromDependentToIndependent;
	private Button fromIndependentToDependent;
	private Button processButton;
	private ExpandBar expandBar;
	private Label label;
	private HashMap<StyledText,Integer> oclRules = new HashMap<StyledText,Integer>();
	private HashMap<FreeRoleDependenceTable,Integer> depTableList = new HashMap<FreeRoleDependenceTable,Integer>();
	private HashMap<FreeRoleIndependenceComposite,Integer> indepCompositeList = new HashMap<FreeRoleIndependenceComposite,Integer>();
	private Composite container;
	
	/**
	 * Create the wizard.
	 */
	public FreeRoleRefactoringPage(FreeRoleOccurrence freeRole) 
	{
		super();	
		this.freeRole = freeRole;
		
		setTitle(FreeRoleAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public FreeRoleWizard getFreeRoleWizard(){
		return (FreeRoleWizard)getWizard();
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
		container = new Composite(parent, SWT.NONE);

		setControl(container);				
		
		deriveList = new List(container, SWT.BORDER);
		for(Role role: freeRole.getFreeRoles()) { deriveList.add(getStereotype(role)+" "+role.getName()); }
		
		dependentList = new List(container, SWT.BORDER);
		
		independentLilst = new List(container, SWT.BORDER);
		
		lblRolesToBe = new Label(container, SWT.NONE);
		lblRolesToBe.setText("Roles to be derived:");
		
		lblDependentRoles = new Label(container, SWT.NONE);
		lblDependentRoles.setText("Dependent roles:");
		
		lblIndependentRoles = new Label(container, SWT.NONE);
		lblIndependentRoles.setText("Independent roles:");
		
		fromDerivedToDependent = new Button(container, SWT.NONE);
		fromDerivedToDependent.setText("->");
		fromDerivedToDependent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: deriveList.getSelection()){
					if(!contains(dependentList,str)) { dependentList.add(str); dependentList.select(dependentList.indexOf(str)); } 
				}
				if(deriveList.getSelectionIndex()>=0) { 
					int prev = deriveList.getSelectionIndex()-1;
					deriveList.remove(deriveList.getSelectionIndex());
					deriveList.select(prev); 
				}				
			}
		});
		
		fromDependentToDerived = new Button(container, SWT.NONE);
		fromDependentToDerived.setText("<-");
		fromDependentToDerived.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: dependentList.getSelection()){
					if(!contains(deriveList,str)) { deriveList.add(str); deriveList.select(deriveList.indexOf(str)); } 
				}
				if(dependentList.getSelectionIndex()>=0) { 
					int prev = dependentList.getSelectionIndex()-1;
					dependentList.remove(dependentList.getSelectionIndex());
					dependentList.select(prev); 
				}				
			}
		});
		
		fromDependentToIndependent = new Button(container, SWT.NONE);
		fromDependentToIndependent.setText("->");
		fromDependentToIndependent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: dependentList.getSelection()){
					if(!contains(independentLilst,str)) { independentLilst.add(str); independentLilst.select(independentLilst.indexOf(str)); } 
				}
				if(dependentList.getSelectionIndex()>=0) { 
					int prev = dependentList.getSelectionIndex()-1;
					dependentList.remove(dependentList.getSelectionIndex());
					dependentList.select(prev); 
				}				
			}
		});
		
		fromIndependentToDependent = new Button(container, SWT.NONE);
		fromIndependentToDependent.setText("<-");
		fromIndependentToDependent.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: independentLilst.getSelection()){
					if(!contains(dependentList,str)) { dependentList.add(str); dependentList.select(dependentList.indexOf(str)); } 
				}
				if(independentLilst.getSelectionIndex()>=0) { 
					int prev = independentLilst.getSelectionIndex()-1;
					independentLilst.remove(independentLilst.getSelectionIndex());
					independentLilst.select(prev); 
				}				
			}
		});
		
		label = new Label(container, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		expandBar = new ExpandBar(container, SWT.V_SCROLL | SWT.H_SCROLL);
		
		processButton = new Button(container, SWT.NONE);
		processButton.setText("Process Information");
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(10)
							.add(expandBar, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE))
						.add(gl_container.createSequentialGroup()
							.add(10)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(gl_container.createSequentialGroup()
									.add(lblRolesToBe, GroupLayout.DEFAULT_SIZE, 173, Short.MAX_VALUE)
									.add(16))
								.add(gl_container.createSequentialGroup()
									.add(deriveList, GroupLayout.DEFAULT_SIZE, 183, Short.MAX_VALUE)
									.addPreferredGap(LayoutStyle.RELATED)))
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(fromDependentToDerived)
								.add(fromDerivedToDependent))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(dependentList, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
								.add(lblDependentRoles, GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(fromIndependentToDependent)
								.add(fromDependentToIndependent))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(independentLilst, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
								.add(lblIndependentRoles, GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))))
					.add(10))
				.add(gl_container.createSequentialGroup()
					.addContainerGap(487, Short.MAX_VALUE)
					.add(processButton, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE)
					.addContainerGap())
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(label, GroupLayout.DEFAULT_SIZE, 616, Short.MAX_VALUE)
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(8)
					.add(gl_container.createParallelGroup(GroupLayout.BASELINE)
						.add(lblRolesToBe)
						.add(lblDependentRoles)
						.add(lblIndependentRoles))
					.add(3)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(31)
							.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
								.add(fromDerivedToDependent)
								.add(fromDependentToIndependent))
							.addPreferredGap(LayoutStyle.RELATED)
							.add(fromIndependentToDependent))
						.add(gl_container.createSequentialGroup()
							.add(62)
							.add(fromDependentToDerived))
						.add(GroupLayout.TRAILING, deriveList, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, dependentList, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(GroupLayout.TRAILING, independentLilst, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(label, GroupLayout.PREFERRED_SIZE, 9, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(processButton)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(expandBar, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
		processButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				
				while(expandBar.getItemCount()>0){
					expandBar.getItem(0).getControl().dispose();
					expandBar.getItem(0).dispose();
				}
				
				System.out.println("Item Count: "+expandBar.getItemCount());
				
				int i=0;
				for(Role role: getDerivedSelected())
				{			
				    Composite composite3 = new Composite (expandBar, SWT.NONE);
					GridLayout layout3 = new GridLayout ();
					layout3.marginLeft = layout3.marginTop=3;
					layout3.marginRight=layout3.marginBottom=3;
					layout3.verticalSpacing = 3;
					composite3.setLayout(layout3);
					
					StyledText styledText = new StyledText(composite3, SWT.NONE);
					GridData gd_styledText = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
					gd_styledText.heightHint = 57;
					gd_styledText.widthHint = 510;
					styledText.setLayoutData(gd_styledText);
					styledText.setVisible(true);
					styledText.setText(" context <FreeRole> :: allInstances() : Set(<FreeRole>) \n body : <DefinedRole>.allInstances()->select ( x | <CONDITION>)");
					oclRules.put(styledText,new Integer(freeRole.getFreeRoles().indexOf(role)));
					
					ExpandItem item3 = new ExpandItem (expandBar, SWT.NONE, i);
					item3.setExpanded(true);
					item3.setText("Role "+role.getName());
					item3.setHeight(composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
					item3.setControl(composite3);
					i++;
				}		
				
				int j=i;
				for(Role role: getDependentSelected())
				{			
				    Composite composite3 = new Composite (expandBar, SWT.NONE);
					GridLayout layout3 = new GridLayout ();
					layout3.marginLeft = layout3.marginTop=3;
					layout3.marginRight=layout3.marginBottom=3;
					layout3.verticalSpacing = 3;
					composite3.setLayout(layout3);
					
					FreeRoleDependenceTable depTable = new FreeRoleDependenceTable(composite3, SWT.NONE,freeRole);
					GridData gd_styledText = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
					gd_styledText.heightHint = 57;
					gd_styledText.widthHint = 510;
					depTable.getTable().setLayoutData(gd_styledText);
					depTable.getTable().setVisible(true);					
					depTableList.put(depTable,new Integer(freeRole.getFreeRoles().indexOf(role)));
					
					ExpandItem item3 = new ExpandItem (expandBar, SWT.NONE, j);
					item3.setExpanded(true);
					item3.setText("Role "+role.getName());
					item3.setHeight(composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
					item3.setControl(composite3);
					j++;
				}	
				
				int k =j;
				for(Role role: getIndependentSelected())
				{			
				    Composite composite3 = new Composite (expandBar, SWT.NONE);
					GridLayout layout3 = new GridLayout ();
					layout3.marginLeft = layout3.marginTop=3;
					layout3.marginRight=layout3.marginBottom=3;
					layout3.verticalSpacing = 3;
					composite3.setLayout(layout3);
					
					FreeRoleIndependenceComposite indepComposite = new FreeRoleIndependenceComposite(composite3, SWT.NONE,freeRole,freeRole.getFreeRoles().indexOf(role));
					GridData gd_styledText = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
					gd_styledText.heightHint = 57;
					gd_styledText.widthHint = 510;
					indepComposite.getIndependenceTable().getTable().setLayoutData(gd_styledText);
					indepComposite.getIndependenceTable().getTable().setVisible(true);					
					indepCompositeList.put(indepComposite,new Integer(freeRole.getFreeRoles().indexOf(role)));
					
					ExpandItem item3 = new ExpandItem (expandBar, SWT.NONE, k);
					item3.setExpanded(true);
					item3.setText("Role "+role.getName());
					item3.setHeight(composite3.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
					item3.setControl(composite3);
					k++;
				}	
				
			}
		});		
	}
	
	public Role getRole (String typeName){
		for(Role p: freeRole.getFreeRoles()){
			if(p.getName().compareToIgnoreCase(typeName)==0) return p;			
		}
		return null;
	}
	
	public ArrayList<Role> getDerivedSelected(){
		ArrayList<Role> result = new ArrayList<Role>();
		for(String str: deriveList.getItems()){
			Role p = getRole(str.replace("Role ", ""));
			if (p!=null) result.add(p);
		}
		return result;
	}
	
	public ArrayList<Role> getIndependentSelected(){
		ArrayList<Role> result = new ArrayList<Role>();
		for(String str: independentLilst.getItems()){
			Role p = getRole(str.replace("Role ", ""));
			if (p!=null) result.add(p);
		}
		return result;
	}
	
	public ArrayList<Role> getDependentSelected(){
		ArrayList<Role> result = new ArrayList<Role>();
		for(String str: dependentList.getItems()){
			Role p = getRole(str.replace("Role ", ""));
			if (p!=null) result.add(p);
		}
		return result;
	}
	
	
	public boolean isAllIndependentSelected()
	{		
		return independentLilst.getItemCount() == freeRole.getFreeRoles().size();
	}	
	
	public boolean isAllDependentSelected()
	{		
		return dependentList.getItemCount() == freeRole.getFreeRoles().size();
	}
	
	public boolean isAllDerivedSelected()
	{		
		return deriveList.getItemCount() == freeRole.getFreeRoles().size();
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		getFreeRoleWizard().removeAllActions();
				
		// Derived Roles...
		for(StyledText styledText: oclRules.keySet()){
			//Action =============================
			FreeRoleAction newAction = new FreeRoleAction(freeRole);
			newAction.setCreateOCLDerivation(freeRole.getFreeRoles().get(oclRules.get(styledText)),styledText.getText()); 
			getFreeRoleWizard().replaceAction(oclRules.get(styledText),newAction);	
			//======================================
		}
		
		//Dependent Roles...
		for(FreeRoleDependenceTable dependenceTable: depTableList.keySet())
		{
			int i=0;
			for(Boolean use: dependenceTable.getUse())
			{
				Boolean specialize = dependenceTable.getSpecialize().get(i);
				String relatorEndMultip = dependenceTable.getMultRelatorEnd().get(i);
				String roleEndMultip = dependenceTable.getMultRoleEnd().get(i);
				Relator relator = (Relator) dependenceTable.getTable().getItem(i).getData("relator");
				
				if(use && !specialize) {
					//Action =============================
					FreeRoleAction newAction = new FreeRoleAction(freeRole);
					newAction.setCreateMediation(relator,freeRole.getFreeRoles().get(depTableList.get(dependenceTable)),relatorEndMultip,roleEndMultip); 
					getFreeRoleWizard().addAction(depTableList.get(dependenceTable),newAction);	
					//======================================				
				}
				if(use && specialize){
					
					String relatorName = dependenceTable.getRelatorNames().get(i);
					//Action =============================
					FreeRoleAction newAction = new FreeRoleAction(freeRole);
					newAction.setCreateSubRelatorWithMediation(relator,freeRole.getFreeRoles().get(depTableList.get(dependenceTable)),relatorName,relatorEndMultip,roleEndMultip); 
					getFreeRoleWizard().addAction(depTableList.get(dependenceTable),newAction);	
					//======================================
				}
				i++;
			}	
		}
		
		//Independent Roles...
		for(FreeRoleIndependenceComposite composite: indepCompositeList.keySet())
		{
			//Action =============================				
			FreeRoleAction newAction = new FreeRoleAction(freeRole);
			newAction.setCreateNewRelatorWithMediation(freeRole.getFreeRoles().get(indepCompositeList.get(composite)), composite.getRelatorNameText().getText(), composite.getRelatorMultipCombo().getText(), composite.getRoleMultipCombo().getText()); 
			getFreeRoleWizard().replaceAction(indepCompositeList.get(composite),newAction);	
			//======================================
			
			if(composite.getIndependenceTable().getTypeNames().size()>=1)
			{
				int i=1;
				for(String typeName: composite.getIndependenceTable().getTypeNames())
				{
					String stereo = composite.getIndependenceTable().getStereotypes().get(i-1);
					String relatorMultiplicity = composite.getIndependenceTable().getMultRelatorEnd().get(i-1);
					String mediatedMultiplicity = composite.getIndependenceTable().getMultMediatedEnd().get(i-1);
					Boolean createMaterial = composite.getIndependenceTable().getCreateMaterial().get(i-1);
					
					//Action =============================				
					newAction = new FreeRoleAction(freeRole);
					newAction.setCreateDependentObjects(freeRole.getFreeRoles().get(indepCompositeList.get(composite)), typeName, stereo, relatorMultiplicity, mediatedMultiplicity,createMaterial); 
					getFreeRoleWizard().addAction(indepCompositeList.get(composite),newAction);	
					//======================================
				}
			}	
		}
		
		return getFreeRoleWizard().getFinishing();		
	}
}
