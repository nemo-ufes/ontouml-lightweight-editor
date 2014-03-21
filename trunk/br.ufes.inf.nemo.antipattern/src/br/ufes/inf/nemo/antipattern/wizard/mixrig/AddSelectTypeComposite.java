package br.ufes.inf.nemo.antipattern.wizard.mixrig;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Classifier;
import RefOntoUML.NamedElement;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import org.eclipse.wb.swt.SWTResourceManager;

public class AddSelectTypeComposite extends Composite {
	private Table table;
	private OntoUMLParser parser;
	private ArrayList<Class<?>> allowedStereotypes;
	private ArrayList<Classifier> existingClassifiers;	
	private TableColumn tblclmnType;
	private TableColumn tblclmnStereotype;
	private TableColumn tblclmnExisting;
	private Button btnDeleteFromTable;
	private Button btnAddToTable;
	private Button btnNew;
	private CCombo comboClassifier;
	private CCombo comboStereotype;
	private final String NEW = "New";
	private int selectedRow;
	
	private SelectionListener btnAddToTableListener = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent event) {
			if(addLine()){
				resetComboClassifier();
				resetComboStereotype();
				btnAddToTable.setEnabled(false);
				selectedRow = -1;
				lblSavingFailed.setVisible(true);
				lblSavingFailed.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				lblSavingFailed.setText("Subtype added!");
			}
			else{
				lblSavingFailed.setVisible(true);
				lblSavingFailed.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
				lblSavingFailed.setText("Saving failed! Type and stereotype combination already in the table.");
			}
			
		}
	};
	
	private SelectionListener btnNewListener = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent event)  {
			resetComboClassifier();
			resetComboStereotype(); 
			btnAddToTable.setEnabled(false);
			selectedRow = -1;
		}
	};
	
	private SelectionListener btnDeleteToTableListener = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent event)  {
			
			int index = table.getSelectionIndex();
			
			if(index>=0 && index<table.getItemCount()){
				table.remove(index);
				resetComboClassifier();
				resetComboStereotype();
				btnAddToTable.setEnabled(false);
				selectedRow = -1;
				lblSavingFailed.setVisible(true);
				lblSavingFailed.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
				lblSavingFailed.setText("Subtype removed!");
			}
		}
	};
	
	private SelectionListener comboClassifierListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent event)  {
			btnAddToTable.setEnabled(true);
			setComboStereotypeFromClassifier(comboClassifier.getSelectionIndex());
		}
	};
	
	private SelectionListener comboStereotypeListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent event)  {
			if(comboStereotype.getSelectionIndex()!=-1 && comboClassifier.getText()!=null)
				btnAddToTable.setEnabled(true);
		}
	};
	
	private SelectionListener tableListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event)  {
			
			selectedRow = table.getSelectionIndex();
			TableItem ti = table.getItem(selectedRow);
			
			comboClassifier.setText(ti.getText(0));
			autoSelectItem(comboClassifier);
			
			if(comboClassifier.getSelectionIndex()!=-1)
				setComboStereotypeFromClassifier(comboClassifier.getSelectionIndex());
			else{
				comboStereotype.setText(ti.getText(1));
				autoSelectItem(comboStereotype);
				comboStereotype.setEnabled(true);
			}
			
		}
	};
	private Label lblSavingFailed;
	
	/**
	 * Create the composite.
	 * @param <T>
	 * @param parent
	 * @param style
	 * @throws Exception 
	 */
	public AddSelectTypeComposite(Composite parent, int style, OntoUMLParser parser, ArrayList<Class<?>> allowedStereotypes) throws Exception {
		super(parent, SWT.BORDER);
		
		if (parser==null)
			throw new Exception();
		if(allowedStereotypes==null || allowedStereotypes.size()==0)
			throw new Exception();
		
		this.selectedRow = -1;
		this.parser = parser;
		this.allowedStereotypes = allowedStereotypes;
		this.existingClassifiers = new ArrayList<Classifier>();
				
		table = new Table(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setBounds(10, 103, 530, 113);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addSelectionListener(tableListener);
		
		tblclmnType = new TableColumn(table, SWT.NONE);
		tblclmnType.setWidth(189);
		tblclmnType.setText("Type");
		
		tblclmnStereotype = new TableColumn(table, SWT.NONE);
		tblclmnStereotype.setWidth(118);
		tblclmnStereotype.setText("Stereotype");
		
		tblclmnExisting = new TableColumn(table, SWT.NONE);
		tblclmnExisting.setWidth(118);
		tblclmnExisting.setText("Location");
		
		btnDeleteFromTable = new Button(this, SWT.NONE);
		btnDeleteFromTable.setBounds(432, 72, 108, 25);
		btnDeleteFromTable.setText("Delete From Table");
		btnDeleteFromTable.addSelectionListener(btnDeleteToTableListener);
		btnDeleteFromTable.setEnabled(true);
		
		btnAddToTable = new Button(this, SWT.NONE);
		btnAddToTable.setBounds(318, 72, 108, 25);
		btnAddToTable.setText("Add To Table");
		btnAddToTable.addSelectionListener(btnAddToTableListener);
		btnAddToTable.setEnabled(false);
		
		btnNew = new Button(this, SWT.NONE);
		btnNew.setBounds(204, 72, 108, 25);
		btnNew.setText("New");
		btnNew.addSelectionListener(btnNewListener);
		
		Label lblType = new Label(this, SWT.NONE);
		lblType.setBounds(10, 10, 62, 21);
		lblType.setText("Type:");
		
		Label lblStereotype = new Label(this, SWT.NONE);
		lblStereotype.setBounds(10, 37, 62, 21);
		lblStereotype.setText("Stereotype: \r\n");
		
		comboClassifier = new CCombo(this, SWT.BORDER);
		comboClassifier.setBounds(78, 10, 462, 21);
		setComboTypeItems();
		comboClassifier.addSelectionListener(comboClassifierListener);
		comboClassifier.addListener(SWT.KeyUp, new Listener() {
	        @Override
	        public void handleEvent(Event arg0) {
	        	autoSelectItem(comboClassifier);
	        	if(comboClassifier.getSelectionIndex()!=-1){
	        		setComboStereotypeFromClassifier(comboClassifier.getSelectionIndex());
	        		btnAddToTable.setEnabled(true);
	        	}
	        	else{
	        		comboStereotype.setEnabled(true);
	        		if(comboStereotype.getSelectionIndex()>=0 && comboStereotype.getSelectionIndex()<AddSelectTypeComposite.this.allowedStereotypes.size())
	        			btnAddToTable.setEnabled(true);
	        	}
	        }
	    });
		
		comboStereotype = new CCombo(this, SWT.BORDER);
		comboStereotype.setBounds(78, 37, 462, 21);
		comboStereotype.setEditable(false);
		comboStereotype.addSelectionListener(comboStereotypeListener);
		setComboStereotype();
		
		lblSavingFailed = new Label(this, SWT.NONE);
		lblSavingFailed.setAlignment(SWT.RIGHT);
		lblSavingFailed.setBounds(10, 222, 530, 15);
		lblSavingFailed.setVisible(false);

	}

	private void setComboTypeItems(){
		
		if(existingClassifiers==null)
			existingClassifiers = new ArrayList<Classifier>();
		
		if(comboClassifier==null){
			comboClassifier = new CCombo(this, SWT.BORDER);
			comboClassifier.setBounds(78, 10, 362, 25);
		}
		if(allowedStereotypes!=null){
			for (Class<?> stereotype : allowedStereotypes) {
				Set<?> classifiers = parser.getAllInstances(stereotype);
				for (Object classifier : classifiers) {
					if (classifier instanceof Classifier){
						existingClassifiers.add((Classifier) classifier);
						comboClassifier.add(((Classifier) classifier).getName());
					}
				}
			}
		}
	}
	
	private void setComboStereotype(){
		if(comboStereotype==null){
			comboStereotype = new CCombo(this, SWT.BORDER);
			comboStereotype.setBounds(78, 10, 362, 25);
		}
		if(allowedStereotypes!=null){
			for (Class<?> stereotype : allowedStereotypes) {
				comboStereotype.add(getStereotypeName(stereotype));
			}
		}
	}
	
	private void setComboStereotypeFromClassifier(int classifierIndex){
		String stereotypeName = OutcomeFixer.getStereotype(existingClassifiers.get(classifierIndex));
		comboStereotype.setEditable(true);
		comboStereotype.setText(stereotypeName);
		comboStereotype.setEditable(false);
		comboStereotype.setEnabled(false);
		autoSelectItem(comboStereotype);
	}
	
	private void resetComboStereotype(){
		comboStereotype.setEnabled(true);
		comboStereotype.select(-1);
	}
	
	private void resetComboClassifier(){
		comboClassifier.setText("");
	}
	
	private boolean autoSelectItem(CCombo combo){
		String name = combo.getText();
		if(name==null || combo==null)
			return false;
		
		for (int i = 0; i < combo.getItems().length; i++) {
			if(combo.getItem(i)!=null && combo.getItem(i).compareTo(name)==0){
				combo.select(i);
				return true;
			}
		}
			
		return false;
	}
	
	private boolean addLine(){
		TableItem tableItem;
		
		System.out.println("SELECTED ROW: "+selectedRow);
		
		if(existsInTable(comboClassifier.getText(), comboStereotype.getText()))
			return false;
		
		if(comboClassifier.getSelectionIndex()==-1 && comboStereotype.getSelectionIndex()!=-1){
			if(selectedRow==-1 || selectedRow>=table.getItems().length)
				tableItem = new TableItem(table,SWT.NONE);
			else
				tableItem = table.getItem(selectedRow);
			
			tableItem.setText(0, comboClassifier.getText());
			tableItem.setText(1, comboStereotype.getText());
			tableItem.setText(2, NEW);
			return true;
		}
		if (comboClassifier.getSelectionIndex()>=0 && comboClassifier.getSelectionIndex()<existingClassifiers.size())
		{
			if(selectedRow==-1 || selectedRow>=table.getItems().length)
				tableItem = new TableItem(table,SWT.NONE);
			else
				tableItem = table.getItem(selectedRow);
			
			tableItem.setText(0, comboClassifier.getText());
			tableItem.setText(1, comboStereotype.getText());
			tableItem.setText(2, getPath(existingClassifiers.get(comboClassifier.getSelectionIndex())));
			return true;
		}
		
		return false;
	}
		
	private String getPath(EObject c){
		if (c.eContainer()==null) 
			return "";
		else
		{
			String containerName = "";
			if(c.eContainer() instanceof NamedElement)
				containerName = ((NamedElement) c.eContainer()).getName();
			else
				containerName = "unnamed";
			
			return getPath(c.eContainer())+"::"+containerName;
		}
	}
	
	public ArrayList<Classifier> getSelectedClassifier(){
		ArrayList<Classifier> selected = new ArrayList<Classifier>();
		
		for (TableItem ti : table.getItems()) {
			if(ti.getText(2).compareTo(NEW)!=0){
				for (Classifier c : existingClassifiers) {
					if(ti.getText(0).compareTo(c.getName())==0){
						selected.add(c);
						break;
					}
				}
			}
		}
		
		return selected;
	}
	
	public HashMap<String, Class<?>> getNewClassifiers(){
		
		HashMap<String, Class<?>> newClassifiers = new HashMap<String, Class<?>>();
		
		for (TableItem ti : table.getItems()) {
			if(ti.getText(2).compareTo(NEW)==0){
				for (Class<?> stereo : allowedStereotypes) {
					if(getStereotypeName(stereo).compareTo(ti.getText(1))==0){
						newClassifiers.put(ti.getText(0), stereo);
						break;
					}
				}
				
			}
		}
		
		return newClassifiers;	
	}
	
	private String getStereotypeName(Class<?> stereotype){
		String type = stereotype.getName().replaceAll("RefOntoUML.impl.","");
		type = type.replaceAll("class","");
		type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public boolean existsInTable(String type, String stereotype){
		
		for (TableItem ti : table.getItems()) {
			if(ti.getText(0).compareTo(type)==0 && ti.getText(1).compareTo(stereotype)==0)
				return true;
		}
		
		return false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
