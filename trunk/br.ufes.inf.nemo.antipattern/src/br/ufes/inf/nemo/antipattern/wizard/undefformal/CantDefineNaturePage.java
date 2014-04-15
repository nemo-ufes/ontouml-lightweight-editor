package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.Collection;
import java.util.HashMap;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.undefformal.UndefFormalWizard.Nature;

public class CantDefineNaturePage extends UndefFormalPage{

	public Composite parent;
	
	private Button targetCheckDataType;
	private Button targetCheckMode;
	private Button targetCheckRelator;
	private Button targetCheckQuantity;
	private Button targetCheckCollective;
	private Button targetCheckFunctional;
	
	private Button sourceCheckQuantity;
	private Button sourceCheckCollective;
	private Button sourceCheckFunctional;
	private Button sourceCheckDataType;
	private Button sourceCheckMode;
	private Button sourceCheckRelator;
	
	private boolean isSourceEnabled, isTargetEnabled;
	
	private HashMap<Nature,Button> sourceCheckList;
	private HashMap<Nature,Button> targetCheckList;
	/**
	 * Create the wizard.
	 */
	public CantDefineNaturePage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription(	"Formal: "+uf.getFormalName()+
						"\nSource: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;	
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		
		StyledText questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setJustify(true);
		questionText.setAlwaysShowScrollBars(false);
		questionText.setBounds(10, 10, 554, 65);
		questionText.setText(
							"The wizard was unable to automatically identify if the related types of <Formal> are functional complexes, quantities or collectives. \r\n\r\nPlease choose the possible natures of the related types to continue the analysis:"
							);
		
		Label lblNewLabel = new Label(container, SWT.NONE);
		lblNewLabel.setBounds(10, 81, 554, 15);
		lblNewLabel.setText("Source Type ("+occurrence.getSource().getName()+"):");
		
		sourceCheckFunctional = new Button(container, SWT.CHECK);
		sourceCheckFunctional.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		sourceCheckFunctional.setBounds(10, 102, 162, 16);
		sourceCheckFunctional.setText("Functional Complex (Kind)");
		
		sourceCheckCollective = new Button(container, SWT.CHECK);
		sourceCheckCollective.setText("Collective");
		sourceCheckCollective.setBounds(10, 124, 162, 16);
		
		sourceCheckQuantity = new Button(container, SWT.CHECK);
		sourceCheckQuantity.setText("Quantity");
		sourceCheckQuantity.setBounds(10, 146, 162, 16);
		
		Label label = new Label(container, SWT.NONE);
		label.setText("Target Type ("+occurrence.getTarget().getName()+"):");
		label.setBounds(10, 178, 554, 15);
		
		targetCheckFunctional = new Button(container, SWT.CHECK);
		targetCheckFunctional.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			}
		});
		targetCheckFunctional.setText("Functional Complex (Kind)");
		targetCheckFunctional.setBounds(10, 199, 162, 16);
		
		targetCheckCollective = new Button(container, SWT.CHECK);
		targetCheckCollective.setText("Collective");
		targetCheckCollective.setBounds(10, 221, 162, 16);
		
		targetCheckQuantity = new Button(container, SWT.CHECK);
		targetCheckQuantity.setText("Quantity");
		targetCheckQuantity.setBounds(10, 243, 162, 16);
		
		sourceCheckRelator = new Button(container, SWT.CHECK);
		sourceCheckRelator.setText("Relator");
		sourceCheckRelator.setBounds(198, 102, 162, 16);
		
		sourceCheckMode = new Button(container, SWT.CHECK);
		sourceCheckMode.setText("Mode");
		sourceCheckMode.setBounds(198, 124, 162, 16);
		
		sourceCheckDataType = new Button(container, SWT.CHECK);
		sourceCheckDataType.setText("DataType");
		sourceCheckDataType.setBounds(198, 146, 162, 16);
		
		targetCheckDataType = new Button(container, SWT.CHECK);
		targetCheckDataType.setText("DataType");
		targetCheckDataType.setBounds(198, 243, 162, 16);
		
		targetCheckMode = new Button(container, SWT.CHECK);
		targetCheckMode.setText("Mode");
		targetCheckMode.setBounds(198, 221, 162, 16);
		
		targetCheckRelator = new Button(container, SWT.CHECK);
		targetCheckRelator.setText("Relator");
		targetCheckRelator.setBounds(198, 199, 162, 16);
		
		sourceCheckList = new HashMap<Nature,Button>();
		sourceCheckList.put(Nature.COLLECTIVE,sourceCheckCollective);
		sourceCheckList.put(Nature.FUNCTIONAL,sourceCheckFunctional);
		sourceCheckList.put(Nature.QUANTITY,sourceCheckQuantity);
		sourceCheckList.put(Nature.RELATOR,sourceCheckRelator);
		sourceCheckList.put(Nature.MODE,sourceCheckMode);
		sourceCheckList.put(Nature.DATATYPE,sourceCheckDataType);
		
		targetCheckList = new HashMap<Nature, Button>();
		targetCheckList.put(Nature.COLLECTIVE,targetCheckCollective);
		targetCheckList.put(Nature.FUNCTIONAL,targetCheckFunctional);
		targetCheckList.put(Nature.QUANTITY,targetCheckQuantity);
		targetCheckList.put(Nature.RELATOR,targetCheckRelator);
		targetCheckList.put(Nature.MODE,targetCheckMode);
		targetCheckList.put(Nature.DATATYPE,targetCheckDataType);
		
		if(getAntipatternWizard().canDefineSourceNature()){
			setEnabledList(sourceCheckList.values(), false);
			setSelectionFromHash(sourceCheckList, getAntipatternWizard().sourceNatureHash);
			isSourceEnabled = false;
		}
		else{
			addSelectionListenerList(sourceCheckList.values(), canGoToNextPageListener);
			isSourceEnabled = true;
		}
		
		if(getAntipatternWizard().canDefineTargetNature()){
			setEnabledList(targetCheckList.values(), false);
			setSelectionFromHash(targetCheckList, getAntipatternWizard().targetNatureHash);
			isTargetEnabled = false;
		}
		else{
			addSelectionListenerList(targetCheckList.values(), canGoToNextPageListener);
			isTargetEnabled = true;
		}
		
	}
	
	public void setSelectionFromHash(HashMap<Nature,Button> btnHash, HashMap<Nature,Boolean> valuesHash){
		for (Nature nature : Nature.values())
			btnHash.get(nature).setSelection(valuesHash.get(nature));
	}
	
	public void setHashFromSelection(HashMap<Nature,Button> btnHash, HashMap<Nature,Boolean> valuesHash){
		for (Nature nature : Nature.values())
			valuesHash.put(nature,btnHash.get(nature).getSelection());
	}
	
	public void setEnabledList(Collection<Button> btnList, boolean value){
	
		for (Button checkButton : btnList)
			checkButton.setEnabled(value);
		
	}
	
	public boolean getSelectionListAny(Collection<Button> btnList){
		for (Button checkButton : btnList)
			if(checkButton.getSelection())
				return true;
		
		return false;
	}
	
	public void addSelectionListenerList(Collection<Button> btnList, SelectionListener listener){
		for (Button checkButton : btnList)
			checkButton.addSelectionListener(listener);
	}
	
	private SelectionListener canGoToNextPageListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event) {
			
			Button button = (Button) event.widget;
			
			if(button.getSelection()){
				if(button.equals(sourceCheckDataType) || button.equals(sourceCheckMode) || button.equals(sourceCheckRelator))
					setSelectionAllBut(sourceCheckList.values(), button, false);
				if(button.equals(targetCheckDataType) || button.equals(targetCheckMode) || button.equals(targetCheckRelator))
					setSelectionAllBut(targetCheckList.values(), button, false);
				if(button.equals(sourceCheckCollective) || button.equals(sourceCheckFunctional) || button.equals(sourceCheckQuantity)){
					sourceCheckDataType.setSelection(false);
					sourceCheckMode.setSelection(false);
					sourceCheckRelator.setSelection(false);
				}
				if(button.equals(targetCheckCollective) || button.equals(targetCheckFunctional) || button.equals(targetCheckQuantity)){
					targetCheckDataType.setSelection(false);
					targetCheckMode.setSelection(false);
					targetCheckRelator.setSelection(false);
				}
				
			}
			
			if(getSelectionListAny(sourceCheckList.values()) && getSelectionListAny(targetCheckList.values())){
				if(!isPageComplete())
					setPageComplete(true);
			}
			else{
				if(isPageComplete())
					setPageComplete(false);
			}				
		}
	};	
	
	public void setSelectionAllBut(Collection<Button> btnList, Button opposite, boolean value){
		if(!btnList.contains(opposite))
			return;
		
		for (Button button : btnList) {
			if(!button.equals(opposite))
				button.setSelection(value);
		}
		
		opposite.setSelection(!value);
	}
	
	
	
	@Override
	public IWizardPage getNextPage() 
	{	
		//sets new nature values
		if(isSourceEnabled)
			setHashFromSelection(sourceCheckList, getAntipatternWizard().sourceNatureHash);
		if(isTargetEnabled)
			setHashFromSelection(targetCheckList, getAntipatternWizard().targetNatureHash);
		
		if(getAntipatternWizard().hasRelationTypeBetweenSourceAndTarget()){
			getAntipatternWizard().changeStereotypePage.setQuestionUI();
			return getAntipatternWizard().changeStereotypePage;
		}
		else{
			getAntipatternWizard().noRelationTypePossiblePage.setQuestion();
			return getAntipatternWizard().noRelationTypePossiblePage;
		}
	}
}
