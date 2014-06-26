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
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

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
		lblName.setText("Name:");
		
		checkIsDisjoint = new Button(this, SWT.CHECK);
		checkIsDisjoint.setText("Is Disjoint");
		
		checkIsCovering = new Button(this, SWT.CHECK);
		checkIsCovering.setText("Is Covering");
		
		textName = new Text(this, SWT.BORDER);
		
		table = new Table(this, SWT.BORDER | SWT.V_SCROLL | SWT.CHECK);
		table.setHeaderVisible(true);
		
		TableColumn tblColCheck = new TableColumn(table,SWT.NONE);
		tblColCheck.setWidth(75);
		tblColCheck.setText("Is Member?");
		tblColCheck.setAlignment(SWT.CENTER);
		
		TableColumn tblColSubtype = new TableColumn(table,SWT.NONE);
		tblColSubtype.setWidth(158);
		tblColSubtype.setText("Subtype");
		
		Label lblSupertype = new Label(this, SWT.NONE);
		lblSupertype.setText("Supertype:");
		
		textSupertype = new Text(this, SWT.BORDER);
		textSupertype.setEditable(false);

		Label lblGeneralizations = new Label(this, SWT.NONE);
		lblGeneralizations.setText("Generalizations");
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.VERTICAL);
		
		listGenSet = new List(this, SWT.BORDER | SWT.H_SCROLL);
		listGenSet.addSelectionListener(gsListListener);
		setGSList();
		
		Label lblGeneralizationSets = new Label(this, SWT.NONE);
		lblGeneralizationSets.setText("Generalization Sets:");
		
		btnSave = new Button(this, SWT.NONE);
		btnSave.setText("Save");
		btnSave.addSelectionListener(saveListener);
		btnSave.setToolTipText("Saves the modifications made in the current Generalization Set");
		
		btnDelete = new Button(this, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.addSelectionListener(deleteListener);
		btnDelete.setToolTipText("Delete the current Generalization Set");
		
		btnReset = new Button(this, SWT.NONE);
		btnReset.setText("Reset");
		btnReset.addSelectionListener(resetListener);
		btnReset.setToolTipText("Discard all changes and returns the Generalization Set list to its original state.");
		
		btnAllOverlapping = new Button(this, SWT.NONE);
		btnAllOverlapping.setText("All Overlapping");
		btnAllOverlapping.addSelectionListener(allOverlappingListener);
		btnAllOverlapping.setToolTipText("Set the isDisjoint meta-property of all Generalization Sets in the list to false.");
		
		btnRemoveAll = new Button(this, SWT.NONE);
		btnRemoveAll.setText("Remove All");
		btnRemoveAll.addSelectionListener(removeAllListener);
		btnRemoveAll.setToolTipText("Removes all generalizations sets on the list.");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
						.add(groupLayout.createSequentialGroup()
							.add(10)
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(groupLayout.createSequentialGroup()
									.add(lblName, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.add(18)
									.add(textName, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
								.add(groupLayout.createSequentialGroup()
									.add(lblSupertype, GroupLayout.PREFERRED_SIZE, 63, GroupLayout.PREFERRED_SIZE)
									.add(18)
									.add(textSupertype, GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE))
								.add(groupLayout.createSequentialGroup()
									.add(81)
									.add(checkIsCovering, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
								.add(groupLayout.createSequentialGroup()
									.add(81)
									.add(checkIsDisjoint, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE))
								.add(lblGeneralizations, GroupLayout.PREFERRED_SIZE, 237, GroupLayout.PREFERRED_SIZE)
								.add(table, GroupLayout.DEFAULT_SIZE, 237, Short.MAX_VALUE)))
						.add(groupLayout.createSequentialGroup()
							.addContainerGap()
							.add(btnSave, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(btnDelete, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE)))
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(18)
							.add(label, GroupLayout.PREFERRED_SIZE, 2, GroupLayout.PREFERRED_SIZE)
							.add(17)
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(GroupLayout.TRAILING, groupLayout.createSequentialGroup()
									.add(lblGeneralizationSets, GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(btnReset, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
								.add(listGenSet, GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)))
						.add(GroupLayout.TRAILING, groupLayout.createSequentialGroup()
							.add(133)
							.add(btnRemoveAll, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(btnAllOverlapping)))
					.add(11))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(GroupLayout.TRAILING, groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, label, GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, groupLayout.createSequentialGroup()
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(lblName, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.add(textName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.add(6)
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(lblSupertype, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.add(textSupertype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.add(6)
							.add(checkIsCovering)
							.add(6)
							.add(checkIsDisjoint)
							.add(6)
							.add(lblGeneralizations)
							.add(6)
							.add(table, GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE))
						.add(GroupLayout.LEADING, groupLayout.createSequentialGroup()
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(btnReset)
								.add(groupLayout.createSequentialGroup()
									.add(6)
									.add(lblGeneralizationSets)))
							.add(8)
							.add(listGenSet, GroupLayout.DEFAULT_SIZE, 232, Short.MAX_VALUE)))
					.add(6)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createParallelGroup(GroupLayout.BASELINE)
							.add(btnDelete)
							.add(btnSave))
						.add(btnRemoveAll)
						.add(btnAllOverlapping))
					.add(9))
		);
		setLayout(groupLayout);
		
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
		else{
			if(page.isPageComplete())
				page.setPageComplete(false);
		}
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
