package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import java.util.ArrayList;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

public class NewRelator extends WizardPageAssistant {

	/**
	 * Create the wizard.
	 */
	public NewRelator() {
		super("New Relator");
		init();
	}

	@Override
	public void init() {
		setTitle("Creating a Relator class and its mediations");
	}
	
	private Table table;
	private Text relatorName;
	private int currentItemSelection;
	private Button btDeleteMediation;

	private int contMediation = 0;

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);

		container.setLayout(null);

		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);

		table = new Table(container, SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(-1, 57);
		table.setSize(574, 177);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		table.setLayoutData(data);

		TableColumn column = new TableColumn (table, SWT.NONE);
		column.setText ("Relator");
		column.setWidth(100);
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Cardinality");
		column.setWidth(70);
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Mediation");
		column.setWidth(157);
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Cardinality");
		column.setWidth(70);
		column = new TableColumn (table, SWT.NONE);
		column.setText ("Class Destiny");
		column.setWidth(169);

		final TableEditor editor = new TableEditor (table);

		Button btNewMediation = new Button(container, SWT.NONE);
		btNewMediation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent elem) {
				if(relatorName.getText().isEmpty()){
					MessageDialog.openError(getShell(), "Error", "Give a name to Relator");
					return;
				}
				
				DialogNewGenericRelation dialog = new DialogNewGenericRelation(getShell());
				dialog.setTargetClasses(possibleClasses);
				
				if(dialog.open() == IStatus.OK){
					TableItem item = new TableItem (table, SWT.NONE);
					item.setText (0,relatorName.getText());
					item.setText (1,dialog.srcMinCard+".."+dialog.srcMaxCard);
					item.setText (2,"Mediation");
					item.setText (3,dialog.trgMinCard+".."+dialog.trgMaxCard);
					item.setText (4,dialog.targetClass);
					contMediation++;
					validate();	
				}
			}
		});
		btNewMediation.setBounds(429, 20, 123, 25);
		btNewMediation.setText("Add new Mediation");

		Label lblNameToRelator = new Label(container, SWT.NONE);
		lblNameToRelator.setBounds(10, 25, 93, 15);
		lblNameToRelator.setText("Name to Relator");

		relatorName = new Text(container, SWT.BORDER);
		relatorName.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0) {
				for (int i = 0; i < table.getItemCount(); i++) {
					table.getItem(i).setText(0,relatorName.getText());
				}
			}
		});
		relatorName.setBounds(103, 23, 139, 21);

		Button btnAddNewClass = new Button(container, SWT.NONE);
		btnAddNewClass.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				//TODO criar uma classe nova
			}
		});
		btnAddNewClass.setBounds(8, 247, 95, 25);
		btnAddNewClass.setText("Add new Class");
		btnAddNewClass.setEnabled(false);

		btDeleteMediation = new Button(container, SWT.NONE);
		btDeleteMediation.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				table.remove(currentItemSelection);
				btDeleteMediation.setEnabled(false);
				contMediation--;
				validate();
			}
		});
		btDeleteMediation.setBounds(314, 20, 109, 25);
		btDeleteMediation.setText("Delete Mediation");
		btDeleteMediation.setEnabled(false);
		
		Button btnEditSelection = new Button(container, SWT.NONE);
		btnEditSelection.setText("Edit Selection");
		btnEditSelection.setEnabled(false);
		btnEditSelection.setBounds(469, 247, 95, 25);

		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;

		table.addListener(SWT.Selection, new Listener() {
			@Override
			public void handleEvent(Event arg0) {
				currentItemSelection = table.getSelectionIndex();
				btDeleteMediation.setEnabled(true);
			}
		});

	}

	@Override
	public boolean canFlipToNextPage() {
		if(validate()){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean next() {
		return true;
	}

	private String[] possibleClasses;

	public void setPossbileClasses(String[] classes){
		possibleClasses = classes;
	}
	
	private boolean validate(){
		boolean isValid = false;
		
		//Validate quantity of mediations
		if(contMediation > 0){
			isValid = true;
		}else{
			isValid = false;
		}
		
		if(isValid){
			if(!isPageComplete())
				setPageComplete(true);
		}else{
			if(isPageComplete())
				setPageComplete(false);
		}
		
		return isValid;
	}
	
	@Override
	public String toString() {
		String s;
		s = "Page: "+getName()+"{";
		s += "\nrelator: "+getRelatorName();
		ArrayList<String[]> list = getMediations();
		for(String[] row : list){
			s += "\nSrcCard: "+row[1]+" - MedName: "+row[2]+" - TrgCard: "+row[3]+" - ClassTrg: "+row[4];
		}
		s += "\n}";
		return s;
	}
	
	/* get operations */
	public ArrayList<String[]> getMediations(){
		ArrayList<String[]> list = new ArrayList<>();
		String[] row;
		for(TableItem ti : table.getItems()){
			row = new String[table.getColumnCount()];
			for (int i = 0; i < table.getColumnCount(); i++) {
				row[i] = ti.getText(i);
			}
			list.add(row);
		}
		return list;
	}
	
	public String getRelatorName(){
		return relatorName.getText();
	}
	
	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if(visible){
			enableFinish(false);
		}
	}
}