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
	private Button btnProcessInformation;
	private ExpandBar expandBar;
	private Label label;
	private HashMap<StyledText,Integer> oclRules = new HashMap<StyledText,Integer>();
	private HashMap<FreeRoleDependenceTable,Integer> depTableList = new HashMap<FreeRoleDependenceTable,Integer>();
	private HashMap<FreeRoleIndependenceComposite,Integer> indepCompositeList = new HashMap<FreeRoleIndependenceComposite,Integer>();
	
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
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);				
		
		deriveList = new List(container, SWT.BORDER);
		deriveList.setBounds(10, 31, 174, 68);
		for(Role role: freeRole.getFreeRoles()) { deriveList.add(getStereotype(role)+" "+role.getName()); }
		
		dependentList = new List(container, SWT.BORDER);
		dependentList.setBounds(231, 31, 174, 68);
		
		independentLilst = new List(container, SWT.BORDER);
		independentLilst.setBounds(452, 31, 174, 68);
		
		lblRolesToBe = new Label(container, SWT.NONE);
		lblRolesToBe.setBounds(27, 10, 140, 15);
		lblRolesToBe.setText("Roles to be derived:");
		
		lblDependentRoles = new Label(container, SWT.NONE);
		lblDependentRoles.setBounds(204, 10, 140, 15);
		lblDependentRoles.setText("Dependent roles:");
		
		lblIndependentRoles = new Label(container, SWT.NONE);
		lblIndependentRoles.setBounds(381, 10, 140, 15);
		lblIndependentRoles.setText("Independent roles:");
		
		fromDerivedToDependent = new Button(container, SWT.NONE);
		fromDerivedToDependent.setBounds(200, 30, 25, 25);
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
		fromDependentToDerived.setBounds(200, 61, 25, 25);
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
		fromDependentToIndependent.setBounds(421, 30, 25, 25);
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
		fromIndependentToDependent.setBounds(421, 61, 25, 25);
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
		label.setBounds(10, 105, 616, 9);
		
		expandBar = new ExpandBar(container, SWT.V_SCROLL | SWT.H_SCROLL);
		expandBar.setBounds(10, 151, 616, 121);
		
		btnProcessInformation = new Button(container, SWT.NONE);
		btnProcessInformation.setBounds(252, 120, 140, 25);
		btnProcessInformation.setText("Process Information");
		btnProcessInformation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
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
							
				if(use && !specialize) {
					//Action =============================
					FreeRoleAction newAction = new FreeRoleAction(freeRole);
					newAction.setCreateMediation((Relator)freeRole.getDefiningRelatorEnds().get(i).getType(),freeRole.getFreeRoles().get(depTableList.get(dependenceTable)),relatorEndMultip,roleEndMultip); 
					getFreeRoleWizard().addAction(depTableList.get(dependenceTable),newAction);	
					//======================================				
				}
				if(use && specialize){
					
					String relatorName = dependenceTable.getRelatorNames().get(i);
					//Action =============================
					FreeRoleAction newAction = new FreeRoleAction(freeRole);
					newAction.setCreateSubRelatorWithMediation((Relator)freeRole.getDefiningRelatorEnds().get(i).getType(),freeRole.getFreeRoles().get(depTableList.get(dependenceTable)),relatorName,relatorEndMultip,roleEndMultip); 
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
