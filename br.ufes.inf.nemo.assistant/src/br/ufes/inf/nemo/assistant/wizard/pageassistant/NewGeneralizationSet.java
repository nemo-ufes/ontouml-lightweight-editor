package br.ufes.inf.nemo.assistant.wizard.pageassistant;
import java.util.HashMap;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class NewGeneralizationSet extends WizardPageAssistant {

	/**
	 * Create the wizard.
	 */
	public NewGeneralizationSet() {
		super("Create new GeneralizationSet");
		setTitle("Create a new OntoUML class");
		setDescription("NewGeneralizationSet.description");
	}

	private NewGeneralizationSetDialog dialog = null;

	private Combo cbGenerals;
	private Combo cbGeneralizationSet;
	private Button isDisjoint;
	private Button isComplete;
	private Composite metaProperties;
	private Label className;
	private Button btnNewGeneralizationset;

	private HashMap<String,boolean[]> hashGeneralizationSet;
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
				hashGeneralizationSet = hashClasses.get(cbGenerals.getItem(cbGenerals.getSelectionIndex()));
				String[] genSets = new String[hashGeneralizationSet.size()];
				int i = 0;
				for (String genSetName : hashGeneralizationSet.keySet()) {
					genSets[i] = genSetName;
					i++;
				}
				cbGeneralizationSet.setItems(genSets);
				cbGeneralizationSet.select(0);
				cbGeneralizationSet.setEnabled(true);

				//change the current buttons
				boolean[] metaProperty = hashGeneralizationSet.get(cbGeneralizationSet.getItem(cbGeneralizationSet.getSelectionIndex()));
				isDisjoint.setSelection(metaProperty[0]);
				isComplete.setSelection(metaProperty[1]);
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
				boolean[] metaProperty = hashGeneralizationSet.get(cbGeneralizationSet.getItem(cbGeneralizationSet.getSelectionIndex()));
				isDisjoint.setSelection(metaProperty[0]);
				isComplete.setSelection(metaProperty[1]);
				metaProperties.setVisible(true);
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
			public void widgetSelected(SelectionEvent arg0) {
				if(dialog == null){
					dialog = new NewGeneralizationSetDialog(getShell());
				}
				if (dialog.open() == Window.OK) {
					String [] genSets = new String[cbGeneralizationSet.getItemCount()+1];
					int i = 0;
					for (; i < cbGeneralizationSet.getItemCount(); i++) {
						genSets[i] = cbGeneralizationSet.getItem(i);
					} 
					genSets[i] = dialog.getGeneralizationSet();
					isComplete.setSelection(false);
					isDisjoint.setSelection(false);
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
			className.setText(getCurrentClass());
			String[] generals = new String[hashClasses.size()];
			//For each class in hashClasses
			int i = 0;
			for (String cls : hashClasses.keySet()) {
				generals[i] = cls;
				i++;
			}
			cbGenerals.setItems(generals);
			cbGenerals.select(0);
		}
	}

	public String getGeneral(){
		return cbGenerals.getItem(cbGenerals.getSelectionIndex());
	}

	public String getGeneralizationSet(){
		return cbGeneralizationSet.getItem(cbGeneralizationSet.getSelectionIndex());		
	}

	public boolean[] getMetaProperty(){
		return new boolean[]{isDisjoint.getSelection(),isComplete.getSelection()};
	}

	private HashMap<String,HashMap<String,boolean[]>> hashClasses;
	public void setHashOfClasses(HashMap<String,HashMap<String,boolean[]>> classMetaProperty) {
		this.hashClasses = classMetaProperty;
	}
}
