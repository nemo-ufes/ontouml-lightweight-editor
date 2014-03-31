package br.ufes.inf.nemo.antipattern.wizard.decint;

import java.util.ArrayList;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import br.ufes.inf.nemo.antipattern.decint.DecIntOccurrence;
import br.ufes.inf.nemo.antipattern.decint.GeneralizationSetReplica;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;

public class GeneralizationSetComposite extends Composite {
	
	private Table table;
	private List listGenSet;
	
	private Text textName;
	private Text textSupertype;
	
	private Button checkIsDisjoint;
	private Button checkIsCovering;
	
	private Button btnSave;
	private Button btnReset;
	private Button btnDelete;
	
	private WizardPage page;
	
	int selectedLine = -1;
	private ArrayList<GeneralizationSet> genSets;
	private ArrayList<GeneralizationSetReplica> replicas;
	private Classifier subtype;
	DecIntOccurrence decInt;
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @param canGoToNextPageListener 
	 */
	public GeneralizationSetComposite(Composite parent, int style, DecIntOccurrence decInt, WizardPage page) {
		super(parent, style);
		this.decInt = decInt;
		this.page = page;
		
		subtype = decInt.getSubtype();
		genSets = decInt.getDisjointGSList();
		
		replicas = new ArrayList<GeneralizationSetReplica>();
		
		createReplicas();
		
		Label lblName = new Label(this, SWT.NONE);
		lblName.setBounds(10, 10, 63, 21);
		lblName.setText("Name:");
		
		checkIsDisjoint = new Button(this, SWT.CHECK);
		checkIsDisjoint.setBounds(91, 86, 156, 16);
		checkIsDisjoint.setText("Is Disjoint");
		
		checkIsCovering = new Button(this, SWT.CHECK);
		checkIsCovering.setBounds(91, 64, 156, 16);
		checkIsCovering.setText("Is Covering");
		
		textName = new Text(this, SWT.BORDER);
		textName.setBounds(91, 10, 156, 21);
		
		table = new Table(this, SWT.BORDER | SWT.V_SCROLL | SWT.CHECK);
		table.setHeaderVisible(true);
		table.setBounds(10, 129, 237, 106);
		
		TableColumn tblColCheck = new TableColumn(table,SWT.NONE);
		tblColCheck.setWidth(75);
		tblColCheck.setText("Is Member?");
		tblColCheck.setAlignment(SWT.CENTER);
		
		TableColumn tblColSubtype = new TableColumn(table,SWT.NONE);
		tblColSubtype.setWidth(158);
		tblColSubtype.setText("Subtype");
		
		Label lblSupertype = new Label(this, SWT.NONE);
		lblSupertype.setText("Supertype:");
		lblSupertype.setBounds(10, 37, 63, 21);
		
		textSupertype = new Text(this, SWT.BORDER);
		textSupertype.setEditable(false);
		textSupertype.setBounds(91, 37, 156, 21);

		Label lblGeneralizations = new Label(this, SWT.NONE);
		lblGeneralizations.setBounds(10, 108, 237, 15);
		lblGeneralizations.setText("Generalizations");
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		label.setBounds(265, 10, 2, 225);
		
		listGenSet = new List(this, SWT.BORDER | SWT.H_SCROLL);
		listGenSet.setBounds(284, 43, 290, 192);
		listGenSet.addSelectionListener(gsListListener);
		setGSList();
		
		Label lblGeneralizationSets = new Label(this, SWT.NONE);
		lblGeneralizationSets.setBounds(284, 16, 130, 15);
		lblGeneralizationSets.setText("Generalization Sets:");
		
		btnSave = new Button(this, SWT.NONE);
		btnSave.setText("Save");
		btnSave.setBounds(93, 241, 74, 25);
		btnSave.addSelectionListener(saveListener);
		btnSave.setToolTipText("Saves the modifications made in the current Generalization Set");
		
		btnDelete = new Button(this, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setBounds(173, 241, 74, 25);
		btnDelete.addSelectionListener(deleteListener);
		btnDelete.setToolTipText("Delete the current Generalization Set");
		
		btnReset = new Button(this, SWT.NONE);
		btnReset.setText("Reset");
		btnReset.setBounds(500, 10, 74, 25);
		btnReset.addSelectionListener(resetListener);
		btnReset.setToolTipText("Discard all changes and returns the Generalization Set list to its original state.");
		
		btnAllOverlapping = new Button(this, SWT.NONE);
		btnAllOverlapping.setText("All Overlapping");
		btnAllOverlapping.setBounds(480, 241, 94, 25);
		btnAllOverlapping.addSelectionListener(allOverlappingListener);
		btnAllOverlapping.setToolTipText("Set the isDisjoint meta-property of all Generalization Sets in the list to false.");
		
		btnRemoveAll = new Button(this, SWT.NONE);
		btnRemoveAll.setText("Remove All");
		btnRemoveAll.setBounds(380, 241, 94, 25);
		btnRemoveAll.addSelectionListener(removeAllListener);
		btnRemoveAll.setToolTipText("Removes all generalizations sets on the list.");
		
		setNoSelectionState();
	}

	private void createReplicas() {
		for (GeneralizationSet gs : genSets) {
			replicas.add(new GeneralizationSetReplica(gs));
		}
	}
	
	private void setPageComplete(){
		if(generalizationSetsFixed()){
			if(!page.isPageComplete())
				page.setPageComplete(true);
		}
		else
			page.setPageComplete(false);
	}
	
	private SelectionListener removeAllListener = new SelectionAdapter(){
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			if(listGenSet.getItems().length>0)
				listGenSet.removeAll();
			
			replicas.clear();
			setNoSelectionState();
			setPageComplete();
		}
	};
	
	private SelectionListener allOverlappingListener = new SelectionAdapter(){
		@Override
		public void widgetSelected(SelectionEvent arg0) {

			for (int i = 0; i < replicas.size(); i++)
				replicas.get(i).setDisjoint(false);
			
			setNoSelectionState();
			setPageComplete();
		}
	};
	
	private SelectionListener saveListener = new SelectionAdapter(){
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			if(selectedLine>=0 && selectedLine<listGenSet.getItems().length){
				GeneralizationSetReplica replica = replicas.get(selectedLine);
				replica.setName(textName.getText());
				replica.setCovering(checkIsCovering.getSelection());
				replica.setDisjoint(checkIsDisjoint.getSelection());
				
				replica.getSelectedGeneralizations().clear();
				
				int i = 0;
				for (TableItem ti : table.getItems()) {
					if(ti.getChecked())
						replica.getSelectedGeneralizations().add(replica.getAllowedGeneralizations().get(i));
					i++;
				}
				
				listGenSet.setItem(selectedLine, replica.toString());
				selectedLine = -1;
				setNoSelectionState();
			}
			setPageComplete();
		}
	};
	
	private SelectionListener gsListListener = new SelectionAdapter(){
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			selectedLine = listGenSet.getSelectionIndex();
			setSelectedState(selectedLine);
		}
	};
	
	private SelectionListener deleteListener = new SelectionAdapter(){
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			if(selectedLine>=0 && selectedLine<listGenSet.getItems().length){
				listGenSet.remove(selectedLine);
				replicas.remove(selectedLine);
				setNoSelectionState();
				setPageComplete();
			}
		}
	};
	
	private SelectionListener resetListener = new SelectionAdapter(){
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			listGenSet.removeAll();
			replicas.clear();
			
			createReplicas();
	
			setGSList();
			setNoSelectionState();
			setPageComplete();
		}
	};
	private Button btnAllOverlapping;
	private Button btnRemoveAll;
	
	private void setGSList(){
		for (GeneralizationSetReplica replica : replicas) {
			listGenSet.add(replica.toString());
		}
	}

	private void setNoSelectionState(){
		selectedLine = -1;
		
		textName.setText("");
		textName.setEnabled(false);
		textSupertype.setText("");
		textSupertype.setEnabled(false);
		
		checkIsCovering.setSelection(false);
		checkIsCovering.setEnabled(false);
		checkIsDisjoint.setSelection(false);
		checkIsDisjoint.setEnabled(false);
		
		if(table.getItems().length>0)
			table.removeAll();
		
		table.setEnabled(false);
		
		btnSave.setEnabled(false);
		btnDelete.setEnabled(false);
	}
	
	private void setSelectedState(int index){
		
		if(index>=genSets.size() || index<0)
			return;
		
		GeneralizationSetReplica replica = replicas.get(index);
		
		textName.setText(replica.getName());
		textSupertype.setText(replica.getSupertype().getName());
		checkIsCovering.setSelection(replica.isCovering());
		checkIsDisjoint.setSelection(replica.isDisjoint());
		
		if(table.getItems().length>0)
			table.removeAll();
		
		for (Generalization g : replica.getAllowedGeneralizations()) {
			TableItem ti = new TableItem(table, SWT.NONE);
			ti.setText(1, g.getSpecific().getName());
			ti.setChecked(replica.getSelectedGeneralizations().contains(g));
		}
		
		textName.setEnabled(true);
		checkIsCovering.setEnabled(true);
		checkIsDisjoint.setEnabled(true);
		table.setEnabled(true);
		btnSave.setEnabled(true);
		btnDelete.setEnabled(true);
	}

	public boolean generalizationSetsFixed(){
		if(replicas.size()==0)
			return true;
		
		for (GeneralizationSetReplica replica : replicas) {
			int generalizationLeadingToSubtype = 0;
			for (Generalization g : replica.getSelectedGeneralizations()) {
				if(g.getSpecific().equals(subtype) || decInt.getParser().getAllChildren(g.getSpecific()).contains(subtype))
					generalizationLeadingToSubtype++;
			}
			if(generalizationLeadingToSubtype>1 && replica.isDisjoint())
				return false;
		}
		
		return true;
	}
	
	
	
	public ArrayList<GeneralizationSetReplica> getReplicas() {
		return replicas;
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
