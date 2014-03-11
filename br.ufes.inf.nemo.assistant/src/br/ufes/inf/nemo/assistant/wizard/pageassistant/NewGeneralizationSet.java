package br.ufes.inf.nemo.assistant.wizard.pageassistant;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import br.ufes.inf.nemo.assistant.util.GeneralizationClass;

public class NewGeneralizationSet extends WizardPageAssistant implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Create the wizard.
	 */
	public NewGeneralizationSet() {
		super("Create new GeneralizationSet");
		setTitle("New GeneralizationSet");
		setDescription("Creating a Generalization set and setting its meta-properties");
	}

	private boolean canFinish = false;
	private Combo cbGenerals;
	private Combo cbGeneralizationSet;
	private Button isDisjoint;
	private Button isComplete;
	private Composite metaProperties;
	private Label className;
	private Button btnNewGeneralizationset;

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(null);

		Label lblSelectTheGeneral = new Label(container, SWT.NONE);
		lblSelectTheGeneral.setBounds(10, 10, 174, 15);
		lblSelectTheGeneral.setText("Setting the General for the class:");

		className = new Label(container, SWT.NONE);
		className.setBounds(190, 10, 215, 15);
		className.setText("<ClassName>");

		Label lblSelectTheGeneral_1 = new Label(container, SWT.NONE);
		lblSelectTheGeneral_1.setBounds(38, 50, 135, 15);
		lblSelectTheGeneral_1.setText("Select the General class:");

		cbGenerals = new Combo(container, SWT.READ_ONLY);
		cbGenerals.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				String generalSelected = cbGenerals.getItem(cbGenerals.getSelectionIndex());
				ArrayList<GeneralizationClass> genSetList = hashGeneralizationClasses.get(generalSelected);

				if(!genSetList.isEmpty()){
					String[] genSetArray = new String[genSetList.size()];
					for (int i = 0; i < genSetList.size(); i++) {
						genSetArray[i] = genSetList.get(i).getGeneralizationName();
					}

					//add elements
					cbGeneralizationSet.setItems(genSetArray);
					cbGeneralizationSet.select(0);
					cbGeneralizationSet.setEnabled(true);

					//change the current buttons
					isDisjoint.setSelection(genSetList.get(0).isDisjoint());
					isComplete.setSelection(genSetList.get(0).isComplete());
				}else{
					canFinish = false;
					cbGeneralizationSet.setItems(new String[0]);
					isDisjoint.setSelection(false);
					isComplete.setSelection(false);
				}
				metaProperties.setVisible(true);
				btnNewGeneralizationset.setEnabled(true);
			}
		});
		cbGenerals.setBounds(203, 44, 204, 23);

		Label lblSelectTheGeneralizationset = new Label(container, SWT.NONE);
		lblSelectTheGeneralizationset.setBounds(38, 84, 156, 15);
		lblSelectTheGeneralizationset.setText("Select the GeneralizationSet:");

		cbGeneralizationSet = new Combo(container, SWT.READ_ONLY);
		cbGeneralizationSet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(!isEndPage){
					canFinish = true;
					setPageComplete(true);
				}
				//to prevent to be a new generalizationSet 
				isDisjoint.setSelection(false);
				isComplete.setSelection(false);

				String generalSelected = cbGenerals.getItem(cbGenerals.getSelectionIndex());
				String genSetSelected = cbGeneralizationSet.getItem(cbGeneralizationSet.getSelectionIndex());

				ArrayList<GeneralizationClass> genSetList = hashGeneralizationClasses.get(generalSelected);
				if(!genSetList.isEmpty()){
					for (GeneralizationClass genSet : genSetList) {
						if(genSet.getGeneralizationName().equals(genSetSelected)){
							isDisjoint.setSelection(genSet.isDisjoint());
							isComplete.setSelection(genSet.isComplete());
						}
					}
				}
			}
		});
		cbGeneralizationSet.setEnabled(false);
		cbGeneralizationSet.setBounds(203, 77, 204, 23);

		metaProperties = new Composite(container, SWT.NONE);
		metaProperties.setBounds(38, 125, 476, 123);
		metaProperties.setVisible(false);

		Label lblSet = new Label(metaProperties, SWT.NONE);
		lblSet.setBounds(4, 10, 272, 15);
		lblSet.setText("Set meta-properties from the GeneralizationSet:");

		isDisjoint = new Button(metaProperties, SWT.CHECK);
		isDisjoint.setBounds(305, 9, 93, 16);
		isDisjoint.setText("Disjoint");

		isComplete = new Button(metaProperties, SWT.CHECK);
		isComplete.setBounds(305, 31, 93, 16);
		isComplete.setText("Complete");

		btnNewGeneralizationset = new Button(container, SWT.NONE);
		btnNewGeneralizationset.setEnabled(false);
		btnNewGeneralizationset.setBounds(429, 77, 135, 25);
		btnNewGeneralizationset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				InputDialog dialog = new InputDialog(getShell(),"Creating a new GeneralizationSet","GeneralizationSet name:",null,null);
				if( dialog.open() == IStatus.OK){ 
					canFinish = true;
					setPageComplete(true);
					String [] genSets = new String[cbGeneralizationSet.getItemCount()+1];
					int i = 0;
					for (; i < cbGeneralizationSet.getItemCount(); i++) {
						genSets[i] = cbGeneralizationSet.getItem(i);
					} 
					genSets[i] = dialog.getValue();
					if(isEditable){
						isComplete.setSelection(false);
						isDisjoint.setSelection(false);
					}else{
						isComplete.setSelection(true);
						isDisjoint.setSelection(true);	
					}
					cbGeneralizationSet.setItems(genSets);
					cbGeneralizationSet.select(i);
				}
			}
		});

		btnNewGeneralizationset.setText("New GeneralizationSet");
	}

	@Override
	public void performHelp() {
		Shell shell = new Shell(getShell());
		shell.setText("My Custom Help !!");

		Label label1 = new Label(shell, SWT.NONE);
		label1.setText("NEWCLASS.help");
		shell.open();
	}


	private String[] possibleStereotypes;
	public void setStereotypes(String[] sts){
		possibleStereotypes = sts;
	}

	public String[] getPossibleStereotypes(){
		return possibleStereotypes;
	}

	@Override
	public boolean next() {
		return true;
	}

	@Override
	public boolean canFlipToNextPage() {
		if(isEndPage)
			return false;
		if(canFinish)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return "NewGeneralizationSet";
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if(visible){
			setPageComplete(false);
			canFinish = false;
			if(isListSpecifics){
				className.setText("List of specifics");
			}else{
				className.setText(getCurrentClass());	
			}

			if(hashGeneralizationClasses != null){
				Set<String> generalList = hashGeneralizationClasses.keySet();
				String[] generals = new String[generalList.size()];

				int i = 0;
				for (String general : generalList) {
					generals[i] = general;
					i++;
				}

				cbGenerals.setItems(generals);
				cbGenerals.select(0);
				if(!isEditable){
					isDisjoint.setEnabled(false);
					isDisjoint.setSelection(true);

					isComplete.setEnabled(false);
					isComplete.setSelection(true);
				}
			}
		}
	}

	public String getGeneral(){
		return cbGenerals.getItem(cbGenerals.getSelectionIndex());
	}

	public String getGeneralizationSet(){
		return cbGeneralizationSet.getItem(cbGeneralizationSet.getSelectionIndex());		
	}

	public boolean getIsComplete(){
		return isComplete.getSelection();
	}

	public boolean getIsDisjoint(){
		return isDisjoint.getSelection();
	}

	private HashMap<String,ArrayList<GeneralizationClass>> hashGeneralizationClasses = null;
	public void setHashOfClasses(HashMap<String,ArrayList<GeneralizationClass>> hashGenClassList) {
		this.hashGeneralizationClasses = hashGenClassList;
	}

	private boolean isEditable = true;
	public void setEditableMetaProperties(boolean isEditable) {
		this.isEditable = isEditable;
	}
	
	private boolean isListSpecifics = false;
	public void setIsListSpecifics(boolean isListSpecifics){
		this.isListSpecifics = isListSpecifics;
	}
	
	public boolean isListSpecifics(){
		return isListSpecifics;
	}
	
	public ArrayList<String[]> getListSpecifics(){
		return getClassList();
	}

	@Override
	public void init() {
		setTitle("New GeneralizationSet");
		setDescription("Creating a Generalization set and setting its meta-properties");
	}
}