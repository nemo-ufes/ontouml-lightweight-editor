package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Association;
import RefOntoUML.Property;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class HetCollRefactoringPage extends RefactoringPage {
	
	public HetCollOccurrence hetColl;
	
	public Button btnFirstOption;
	public Button btnSecondOption;
	private Button btnThirdOption;
	private Button btnArrowRight;
	private Button btnArrowLeft;
	private List yesList;
	private List noList;
	private Text lblSecondOption;
	private Text lblThirdOption;

	private Text lblFirstOption;
	
	/**
	 * Create the wizard.
	 */
	public HetCollRefactoringPage(HetCollOccurrence hetColl) 
	{
		super();	
		this.hetColl = hetColl;
		
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
		
		SelectionAdapter listener1 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  if (btnFirstOption.getSelection()){
	    		  btnSecondOption.setSelection(false);
	    		  btnThirdOption.setSelection(false);
	    	  }	    	 
	      }
	    };
	    SelectionAdapter listener2 = new SelectionAdapter() {
	    	public void widgetSelected(SelectionEvent e) {
	    		if (btnSecondOption.getSelection()){
		    		  btnFirstOption.setSelection(false);
		    	  }
	    		if (btnThirdOption.getSelection()){
		    		  btnFirstOption.setSelection(false);
		    	  }  
	    	}
	    };
	    
		btnFirstOption = new Button(container, SWT.CHECK);
		btnFirstOption.setBounds(10, 10, 20, 59);
		btnFirstOption.addSelectionListener(listener1);
		
		btnSecondOption = new Button(container, SWT.CHECK | SWT.WRAP);
		btnSecondOption.setBounds(10, 75, 20, 85);
		btnSecondOption.addSelectionListener(listener2);
		
		btnThirdOption = new Button(container, SWT.CHECK | SWT.WRAP);
		btnThirdOption.setBounds(292, 75, 20, 85);
		btnThirdOption.addSelectionListener(listener2);
		
		yesList = new List(container,SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		yesList.setBounds(36, 166, 224, 106);
		
		for(Property p: hetColl.getMemberEnds())
		{		
			Type type = p.getType();
			yesList.add(getStereotype(type)+" "+type.getName());			
		}
		yesList.setSelection(0);
		
		btnArrowRight = new Button(container, SWT.NONE);
		btnArrowRight.setBounds(266, 181, 40, 25);
		btnArrowRight.setText("->");
		btnArrowRight.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: yesList.getSelection()){
					if(!contains(noList,str)) { noList.add(str); noList.select(noList.indexOf(str)); } 
				}
				if(yesList.getSelectionIndex()>=0) { 
					int prev = yesList.getSelectionIndex()-1;
					yesList.remove(yesList.getSelectionIndex());
					yesList.select(prev); 
				}				
			}
		});
		
		btnArrowLeft = new Button(container, SWT.NONE);
		btnArrowLeft.setBounds(266, 212, 40, 25);
		btnArrowLeft.setText("<-");
		btnArrowLeft.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: noList.getSelection()){
					if(!contains(yesList,str)) { yesList.add(str); yesList.select(yesList.indexOf(str));  } 
				}
				if(noList.getSelectionIndex()>=0) {
					int prev = noList.getSelectionIndex()-1;
					noList.remove(noList.getSelectionIndex());
					noList.select(prev);					 
				}
			}
		});
		
		noList = new List(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		noList.setBounds(318, 166, 224, 106);
		
		lblFirstOption = new Text(container, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		lblFirstOption.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblFirstOption.setBounds(36, 10, 506, 59);		
		lblFirstOption.setText(hetColl.getWhole().getName()+" is a functional complex and all partOf relations are stereotyped as «componentOf»");
		
		lblSecondOption = new Text(container, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		lblSecondOption.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblSecondOption.setBounds(36, 75, 224, 85);
		lblSecondOption.setText("New Label");
		lblSecondOption.setText("The parts are also collectives and their respective relations are stereotyped as «subCollectionOf»");
		
		lblThirdOption = new Text(container, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		lblThirdOption.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblThirdOption.setBounds(318, 75, 224, 85);
		lblThirdOption.setText("There is a new type, named MemberPart, which is the super-type of all parts and is connected to "+hetColl.getWhole().getName()+" through a single «memberOf» relation. In addition, all other partOf relations are deleted.");
	}
	
	public Property getProperty (String typeName){
		for(Property p: hetColl.getMemberEnds()){
			if(p.getType().getName().compareToIgnoreCase(typeName)==0) return p;			
		}
		return null;
	}
		
	public ArrayList<Property> getYesList()
	{
		ArrayList<Property> result = new ArrayList<Property>();
		for(String str: yesList.getItems()){
			Property p = getProperty(str.substring(str.indexOf(" ")+1));
			if (p!=null) { result.add(p); }
		}
		return result;
	}
	
	public ArrayList<Property> getNoList()
	{
		ArrayList<Property> result = new ArrayList<Property>();
		for(String str: noList.getItems()){
			Property p = getProperty(str.substring(str.indexOf(" ")+1));
			if (p!=null) result.add(p);
		}
		return result;
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		((AntipatternWizard)getWizard()).removeAllActions();
		
		if(btnFirstOption.getSelection()){
			ArrayList<Association> assocList = new ArrayList<Association>();
			for(Property p: hetColl.getMemberEnds()) { if (p!=null) assocList.add(p.getAssociation()); }
			if(assocList.size()>0){
				//Action =============================
				HetCollAction newAction = new HetCollAction(hetColl);
				newAction.setChangeAllToComponentOf(assocList); 
				getHetCollWizard().replaceAction(0,newAction);	
				//======================================
			}
		}
		if(btnSecondOption.getSelection()){
			ArrayList<Association> assocList = new ArrayList<Association>();
			for(Property p: getYesList()) { if (p!=null) assocList.add(p.getAssociation()); }		
			if(assocList.size()>0){
				//Action =============================
				HetCollAction newAction = new HetCollAction(hetColl);
				newAction.setChangeAllToCollectionAndSubCollectionOf(assocList); 
				getHetCollWizard().replaceAction(0,newAction);	
				//======================================
			}
		}
		if(btnThirdOption.getSelection()){
			ArrayList<Association> assocList = new ArrayList<Association>();
			for(Property p: getNoList()) { if (p!=null) assocList.add(p.getAssociation()); }
			if(assocList.size()>=2){
				//Action =============================
				HetCollAction newAction = new HetCollAction(hetColl);
				newAction.setChangeAllToOneSuperMember(assocList); 
				if (btnSecondOption.getSelection()) getHetCollWizard().replaceAction(1,newAction);
				else getHetCollWizard().replaceAction(0,newAction);
				//======================================	
			}
		}
		return ((AntipatternWizard)getWizard()).getFinishing();	
	}
}
