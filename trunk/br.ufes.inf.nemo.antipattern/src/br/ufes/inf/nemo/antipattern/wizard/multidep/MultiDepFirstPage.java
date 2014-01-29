package br.ufes.inf.nemo.antipattern.wizard.multidep;

import java.util.ArrayList;

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

import RefOntoUML.Property;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.multidep.MultiDepOccurrence;

public class MultiDepFirstPage  extends MultiDepPage {

	//GUI
	public List remainderList;
	public List connectedList;
	public Button btnDown;
	public Button btnUp;
	private Button btnArrwoLeft;
	private Button btnArrowRight;
	private Label lblAllRelators_1;
	private Button btnNoOrder;
	private Button btnYes;
	private Label lblNewLabel;
	
	public MultiDepFirstPage(MultiDepOccurrence multiDep) {
		super(multiDep);
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
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		StyledText styledText = new StyledText(container, SWT.WRAP);
		styledText.setText("When an instance of "+multiDep.getType().getName()+" is created, to which relators must it be connected to?");
		styledText.setMarginColor(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		styledText.setBounds(10, 10, 537, 15);
		
		remainderList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		remainderList.setBounds(301, 92, 246, 124);
		
		connectedList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		connectedList.setBounds(10, 92, 246, 124);
		
		for(Property p: multiDep.getRelatorEnds())
		{		
			Type relator = p.getType();
			remainderList.add("Relator "+relator.getName());			
		}
		
		Label lblAllRelators = new Label(container, SWT.NONE);
		lblAllRelators.setBounds(10, 71, 246, 15);
		lblAllRelators.setText(multiDep.getType().getName()+" when created is connected to:");
		
		btnArrowRight = new Button(container, SWT.NONE);
		btnArrowRight.setBounds(262, 115, 33, 25);
		btnArrowRight.setText("->");
		btnArrowRight.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: connectedList.getSelection()){
					if(!contains(remainderList,str)) { remainderList.add(str); } 
				}
				if(connectedList.getSelectionIndex()>=0) connectedList.remove(connectedList.getSelectionIndex());
			}
		});
		
		btnArrwoLeft = new Button(container, SWT.NONE);
		btnArrwoLeft.setBounds(262, 146, 33, 25);
		btnArrwoLeft.setText("<-");
		btnArrwoLeft.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: remainderList.getSelection()){
					if(!contains(connectedList,str)) { connectedList.add(str); } 
				}
				if(remainderList.getSelectionIndex()>=0) remainderList.remove(remainderList.getSelectionIndex());
			}
		});
		
		btnUp = new Button(container, SWT.NONE);
		btnUp.setBounds(504, 61, 43, 25);
		btnUp.setText("Up");
		btnUp.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (remainderList.getSelectionCount()==1){
					int index = remainderList.getSelectionIndex();
					int prev = index-1;
					if(index>0){
						String str = remainderList.getItem(index);
						String strPrev = remainderList.getItem(prev);
						remainderList.setItem(prev,str);
						remainderList.setItem(index,strPrev);
						remainderList.setSelection(prev);
					}
				}
			}
		});	
		
		btnDown = new Button(container, SWT.NONE);
		btnDown.setBounds(449, 61, 49, 25);
		btnDown.setText("Down");
		
		lblAllRelators_1 = new Label(container, SWT.NONE);
		lblAllRelators_1.setBounds(301, 71, 142, 15);
		lblAllRelators_1.setText("Remainder relators:");
		
		btnNoOrder = new Button(container, SWT.RADIO);
		btnNoOrder.setBounds(301, 244, 246, 16);
		btnNoOrder.setText("No, the order does not matter");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(301, 222, 246, 16);
		btnYes.setText("Yes, in this particular order");
		
		lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 34, 537, 15);
		lblNewLabel.setText(" Is there a particular order in which an instance of "+multiDep.getType().getName()+" is connected to the remainder relators?");
		btnDown.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if (remainderList.getSelectionCount()==1){
					int index = remainderList.getSelectionIndex();
					int next = index+1;
					if(next<remainderList.getItemCount()){
						String str = remainderList.getItem(index);
						String strNext = remainderList.getItem(next);
						remainderList.setItem(next,str);
						remainderList.setItem(index,strNext);
						remainderList.setSelection(next);
					}
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
	
	public ArrayList<Property> getConnectedSelected(){
		ArrayList<Property> result = new ArrayList<Property>();
		for(String str: connectedList.getItems()){
			Property p = getProperty(str.replace("Relator ", ""));
			if (p!=null) result.add(p);
		}
		return result;
	}
	
	public ArrayList<Property> getRemainderSelected(){
		ArrayList<Property> result = new ArrayList<Property>();
		for(String str: remainderList.getItems()){
			Property p = getProperty(str.replace("Relator ", ""));
			if (p!=null) result.add(p);
		}
		return result;
	}
	
	public boolean isAllConnectedSelected()
	{		
		return connectedList.getItemCount() == multiDep.getRelatorEnds().size();
	}
	
	
	public boolean isAllRemainderSelected()
	{		
		return remainderList.getItemCount() == multiDep.getRelatorEnds().size();
	}
	
	public boolean isOrderRelevant()
	{
		return btnYes.getSelection();
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		boolean allConnected = isAllConnectedSelected();
		boolean allRemainder = isAllRemainderSelected();
		
		if (allConnected) {
			MultiDepSecondPage secondPage = ((MultiDepWizard)getWizard()).addSecondPage(getConnectedSelected());		
			return secondPage; 
		}			
		
		if(!allConnected && btnYes.getSelection()) {
			
		}		
		if(!allConnected && btnNoOrder.getSelection()) 
		{
			if(allRemainder){
				for(String item: remainderList.getItems()){
					Property p = getProperty(item);
					int index = multiDep.getRelatorEnds().indexOf(p);
					//Action =============================
					MultiDepAction newAction = new MultiDepAction(multiDep);
					newAction.setAddSubTypeInvolvingMediation(p.getType(), p.getAssociation()); // WITH GENSET
					getMultiDepWizard().replaceAction(index,newAction);	
					//======================================
				}
			}
			if(!allRemainder){
				if(remainderList.getItemCount()==1){					
					for(String item: remainderList.getItems()){
						Property p = getProperty(item);
						int index = multiDep.getRelatorEnds().indexOf(p);
						//Action =============================
						MultiDepAction newAction = new MultiDepAction(multiDep);
						newAction.setAddSubTypeInvolvingMediation(p.getType(), p.getAssociation());
						getMultiDepWizard().replaceAction(index,newAction);	
						//====================================
					}
				}else if (remainderList.getItemCount()>1){
					for(String item: remainderList.getItems()){
						Property p = getProperty(item);
						int index = multiDep.getRelatorEnds().indexOf(p);
						//Action =============================
						MultiDepAction newAction = new MultiDepAction(multiDep);
						newAction.setAddSubTypeWithIntermediate(p.getType(), p.getAssociation());
						getMultiDepWizard().replaceAction(index,newAction);
						//====================================
					}
				}
			}
		}
		
		MultiDepSecondPage secondPage = ((MultiDepWizard)getWizard()).addSecondPage(multiDep.getRelatorEnds());		
		return secondPage;		
	}
}
