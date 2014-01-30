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
	private Button btnArrwoLeft;
	private Button btnArrowRight;
	private Label lblAllRelators_1;
	
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
		styledText.setBounds(10, 10, 537, 55);
		
		remainderList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		remainderList.setBounds(301, 92, 246, 160);
		
		connectedList = new List(container, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL );
		connectedList.setBounds(10, 92, 246, 160);
		
		for(Property p: multiDep.getRelatorEnds())
		{		
			Type relator = p.getType();
			connectedList.add("Relator "+relator.getName());			
		}
		connectedList.setSelection(0);
		
		Label lblAllRelators = new Label(container, SWT.NONE);
		lblAllRelators.setBounds(10, 71, 246, 15);
		lblAllRelators.setText(multiDep.getType().getName()+" is mandatorily connected to:");
		
		btnArrowRight = new Button(container, SWT.NONE);
		btnArrowRight.setBounds(262, 141, 33, 25);
		btnArrowRight.setText("->");
		btnArrowRight.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: connectedList.getSelection()){
					if(!contains(remainderList,str)) { remainderList.add(str); remainderList.select(remainderList.indexOf(str)); } 
				}
				if(connectedList.getSelectionIndex()>=0) {connectedList.select(connectedList.getSelectionIndex()-1); connectedList.remove(connectedList.getSelectionIndex());}				
			}
		});
		
		btnArrwoLeft = new Button(container, SWT.NONE);
		btnArrwoLeft.setBounds(262, 172, 33, 25);
		btnArrwoLeft.setText("<-");
		btnArrwoLeft.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				for(String str: remainderList.getSelection()){
					if(!contains(connectedList,str)) { connectedList.add(str); connectedList.select(connectedList.indexOf(str));  } 
				}
				if(remainderList.getSelectionIndex()>=0) {remainderList.select(remainderList.getSelectionIndex()-1); remainderList.remove(remainderList.getSelectionIndex()); }
			}
		});
		
		lblAllRelators_1 = new Label(container, SWT.NONE);
		lblAllRelators_1.setBounds(301, 71, 246, 15);
		lblAllRelators_1.setText(multiDep.getType().getName()+" is optionally connected to:");
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
	
	@SuppressWarnings("unused")
	@Override
	public IWizardPage getNextPage() 
	{	
		boolean allConnected = isAllConnectedSelected();
		boolean allRemainder = isAllRemainderSelected();		
		
		if (allConnected) {
			MultiDepThirdPage thirdPage = ((MultiDepWizard)getWizard()).getThirdPage();
			return thirdPage;
		}else{
			MultiDepSecondPage secondPage = ((MultiDepWizard)getWizard()).addSecondPage(getRemainderSelected());
			return secondPage;
		}
	}
}
