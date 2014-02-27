package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Property;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepAntipattern;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class MultiDepRefactoringPage extends RefactoringPage {
	
	public MultiDepOccurrence multiDep;
	public Button btnYes;
	public Button btnNo;
	public Button btnAddLine;
	public Button btnArrowRight;
	public Button btnArrowLeft;
	public List connectedList;
	public MultiDepComboTable table;
	public MultiDepSpinnerTable remainderTable;
	private Label lblTypeIsOptionally;;
	
	/**
	 * Create the wizard.
	 */
	public MultiDepRefactoringPage(MultiDepOccurrence multiDep) 
	{
		super();	
		this.multiDep = multiDep;
		
		setTitle(MultiDepAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public MultiDepWizard getMultiDepWizard(){
		return (MultiDepWizard)getWizard();
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
	
	public boolean contains(ArrayList<Property> list, String elem)
	{
		for(Property p: list){
			String str = p.getName();
			if (str.equals(elem)) return true;
		}
		return false;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
//		Composite composite = new Composite(container, SWT.NONE);
//		composite.setBounds(21, 34, 246, 118);
		
		connectedList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		connectedList.setBounds(21, 34, 246, 118);
		
//		Composite composite_2 = new Composite(container, SWT.NONE);
//		composite_2.setBounds(303, 34, 246, 118);
		
		remainderTable = new MultiDepSpinnerTable(container,SWT.BORDER, new ArrayList<Property>());
		remainderTable.getTable().setBounds(303, 34, 246, 118);
		remainderTable.getTable().getColumns()[0].setWidth(100);
		
		//initialize connected list
		for(Property p: multiDep.getRelatorEnds())
		{		
			Type relator = p.getType();
			connectedList.add("Relator "+relator.getName());			
		}
		connectedList.setSelection(0);
						
		Label lblChooseTheCombination = new Label(container, SWT.NONE);
		lblChooseTheCombination.setBounds(21, 166, 241, 15);
		lblChooseTheCombination.setText("Is there a dependency between the relators?");
		
//		Composite composite_1 = new Composite(container, SWT.NONE);
//		composite_1.setBounds(21, 187, 528, 64);
		
		table = new MultiDepComboTable(container, SWT.BORDER, multiDep.getRelatorEnds());
		table.getTable().setBounds(21, 187, 528, 64);
		
		btnAddLine = new Button(container, SWT.NONE);
		btnAddLine.setBounds(490, 257, 59, 25);
		btnAddLine.setText("Add Line");
		btnAddLine.addSelectionListener(new SelectionAdapter() {
			 @Override
	            public void widgetSelected(SelectionEvent e) {
				 table.addLine();				 
			 }
		});	
		
		Label lblChooseWhichRelators = new Label(container, SWT.NONE);
		lblChooseWhichRelators.setBounds(21, 10, 246, 15);
		lblChooseWhichRelators.setText(multiDep.getType().getName()+" is mandatorily connected to:");
							
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setAlignment(SWT.RIGHT);
		btnYes.setBounds(367, 165, 108, 16);
		btnYes.setText("Yes, in this order");
				
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setAlignment(SWT.RIGHT);
		btnNo.setBounds(481, 165, 68, 16);
		btnNo.setText("No order");
		btnNo.setSelection(true);
		
		btnArrowRight = new Button(container, SWT.NONE);
		btnArrowRight.setLocation(272, 62);
		btnArrowRight.setSize(25, 25);
		btnArrowRight.setText("->");
		btnArrowRight.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: connectedList.getSelection()){
					if(!contains(remainderTable.getProperties(),str)) { remainderTable.addLine(str,remainderTable.getTable().getItemCount()); } 
				}
				if(connectedList.getSelectionIndex()>=0) { 
					int prev = connectedList.getSelectionIndex()-1;
					connectedList.remove(connectedList.getSelectionIndex());
					connectedList.select(prev); 
				}				
			}
		});
		
		btnArrowLeft = new Button(container, SWT.NONE);
		btnArrowLeft.setBounds(273, 93, 24, 25);
		btnArrowLeft.setText("<-");
		
		lblTypeIsOptionally = new Label(container, SWT.NONE);
		lblTypeIsOptionally.setBounds(303, 10, 246, 15);
		lblTypeIsOptionally.setText(multiDep.getType().getName()+" is optionally connected to:");
		btnArrowLeft.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(TableItem ti: remainderTable.getTable().getSelection()){
					String str = ti.getText(0);
					if(!contains(connectedList,str)) { connectedList.add(str); connectedList.select(connectedList.indexOf(str));  } 
				}
				if(remainderTable.getTable().getSelectionIndex()>=0) {	
					int prev = remainderTable.getTable().getSelectionIndex()-1;				
					remainderTable.getTable().remove(remainderTable.getTable().getSelectionIndices());					
					remainderTable.getTable().select(prev);		
					remainderTable.getTable().setRedraw(true);
				}
			}
		});
	}
	
	public Property getProperty (String typeName){
		for(Property p: multiDep.getRelatorEnds()){
			if(p.getType().getName().compareToIgnoreCase(typeName)==0) return p;			
		}
		return null;
	}
	
	public ArrayList<Property> getRemainders(){
		ArrayList<Property> result = new ArrayList<Property>();
		for(TableItem it: remainderTable.getTable().getItems()){
			String str = it.getText(0);
			Property p = getProperty(str.replace("Relator ", ""));
			if (p!=null) result.add(p);
		}
		return result;
	}
	
	public ArrayList<Property> getConnected(){
		ArrayList<Property> result = new ArrayList<Property>();
		for(String str: connectedList.getItems()){
			Property p = getProperty(str.replace("Relator ", ""));
			if (p!=null) result.add(p);
		}
		return result;
	}
		
	public boolean isAllConnected()
	{		
		return connectedList.getItemCount() == multiDep.getRelatorEnds().size();
	}
	public boolean isAllRemainder()
	{		
		return remainderTable.getTable().getItemCount() == multiDep.getRelatorEnds().size();
	}
	
	@Override
	public IWizardPage getNextPage() 
	{
		boolean allConnected = isAllConnected();
		boolean allRemainder = isAllRemainder();		
		((MultiDepWizard)getWizard()).removeAllActions();
		
		if (!allConnected) {
			if (btnNo.getSelection())
			{
				if(allRemainder){
					//Action =============================
					MultiDepAction newAction = new MultiDepAction(multiDep);
					newAction.setAddSubTypeInvolvingMediation(getRemainders()); 
					getMultiDepWizard().replaceAction(0,newAction);	
					//======================================
				}else{
					if(getRemainders().size()==1){					
						//Action =============================
						MultiDepAction newAction = new MultiDepAction(multiDep);
						newAction.setAddSubTypeInvolvingMediation(getRemainders());
						getMultiDepWizard().replaceAction(0,newAction);	
						//====================================					
					}else if (getRemainders().size()>1){					
						//Action =============================
						MultiDepAction newAction = new MultiDepAction(multiDep);
						newAction.setAddSubTypeWithIntermediate(getRemainders()); 
						getMultiDepWizard().replaceAction(0,newAction);
						//====================================
					}
				}
			}
			if (btnYes.getSelection())
			{
				//Action =============================
				
				//====================================	
			}
		}
		
		ArrayList<ArrayList<Property>> matrix = table.getSelections();			
		//Action =============================
		MultiDepAction newAction = new MultiDepAction(multiDep);
		newAction.setCreateFormalAssocs(matrix); 
		getMultiDepWizard().replaceAction(1,newAction);
		//====================================				
		
		return ((MultiDepWizard)getWizard()).getFinishing();
	}
}
