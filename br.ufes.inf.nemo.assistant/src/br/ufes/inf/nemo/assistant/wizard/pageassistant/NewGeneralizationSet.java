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
		setTitle("Creating a Generalization set and setting its meta-properties");
	}

	private boolean canFinish = false;
	private Combo cbGenerals;
	private Combo cbGeneralizationSet;
	private Button isDisjoint;
	private Button isComplete;
	private Composite generalizationSet;
	private Label className;
	private Button btnNewGeneralizationset;
	private Label genSetMsg;
	private Button btnNo;
	private Button btnYes;

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
		lblSelectTheGeneral_1.setBounds(130, 47, 54, 15);
		lblSelectTheGeneral_1.setText("General:");

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
				//generalizationSet.setVisible(true);
				btnNewGeneralizationset.setEnabled(true);
				if(btnNo.getSelection()){
					canFinish = true;
				}
			}
		});
		cbGenerals.setBounds(203, 44, 204, 23);

		generalizationSet = new Composite(container, SWT.BORDER);
		generalizationSet.setBounds(74, 125, 425, 147);
		generalizationSet.setVisible(false);

		Label lblSet = new Label(generalizationSet, SWT.NONE);
		lblSet.setBounds(10, 76, 204, 15);
		lblSet.setText("GeneralizationSet's meta-properties:");

		isDisjoint = new Button(generalizationSet, SWT.CHECK);
		isDisjoint.setBounds(31, 97, 93, 16);
		isDisjoint.setText("Disjoint");

		isComplete = new Button(generalizationSet, SWT.CHECK);
		isComplete.setBounds(31, 119, 93, 16);
		isComplete.setText("Complete");

		Label lblSelectTheGeneralizationset = new Label(generalizationSet, SWT.NONE);
		lblSelectTheGeneralizationset.setBounds(10, 16, 156, 15);
		lblSelectTheGeneralizationset.setText("Select the GeneralizationSet:");

		cbGeneralizationSet = new Combo(generalizationSet, SWT.READ_ONLY);
		cbGeneralizationSet.setBounds(31, 38, 204, 23);
		cbGeneralizationSet.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
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
				canFinish = true;
				enableFinish(true);
			}
		});
		cbGeneralizationSet.setEnabled(false);

		btnNewGeneralizationset = new Button(generalizationSet, SWT.NONE);
		btnNewGeneralizationset.setBounds(253, 36, 135, 25);
		btnNewGeneralizationset.setEnabled(false);
		btnNewGeneralizationset.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent event) {
				InputDialog dialog = new InputDialog(getShell(),"Creating a new GeneralizationSet","GeneralizationSet name:",null,null);
				if( dialog.open() == IStatus.OK){ 
					canFinish = true;
					enableFinish(true);
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

		genSetMsg = new Label(container, SWT.NONE);
		genSetMsg.setBounds(150, 82, 274, 15);
		genSetMsg.setText("Would you like to create a new GeneralizationSet?");

		btnYes = new Button(container, SWT.RADIO);
		btnYes.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				generalizationSet.setVisible(true);
			}
		});
		btnYes.setBounds(220, 103, 54, 16);
		btnYes.setText("Yes");

		btnNo = new Button(container, SWT.RADIO);
		btnNo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				generalizationSet.setVisible(false);
			}
		});
		btnNo.setBounds(280, 103, 54, 16);
		btnNo.setText("No");
		btnNo.setSelection(true);
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

			if(isListSpecifics){
				btnNo.setVisible(false);
				btnYes.setVisible(false);
				genSetMsg.setVisible(false);
				generalizationSet.setVisible(true);
			}
			canFinish = true;
			enableFinish(!isListSpecifics);
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

	public boolean isGeneralization(){
		if(isListSpecifics)
			return false;
		return btnNo.getSelection();
	}

	public boolean isGeneralizationSet(){
		if(isListSpecifics)
			return true;
		return btnYes.getSelection();
	}

	@Override
	public void init() {
		setTitle("Creating a Generalization set and setting its meta-properties");
	}
}