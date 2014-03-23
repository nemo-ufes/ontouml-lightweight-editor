package br.ufes.inf.nemo.antipattern.wizard.mixiden;

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
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Classifier;
import RefOntoUML.NamedElement;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class AddSelectSortalComposite extends Composite {
	private Table table;
	private TableColumn tblclmnType;
	private TableColumn tblclmnStereotype;
	private TableColumn tblclmnLocation;
	private TableColumn tblclmnIP;
	private TableColumn tblclmnIPStereotype;
	private TableColumn tblclmnIPLocation;
	
	private Button btnDeleteFromTable;
	private Button btnSaveInTable;
	private Button btnNew;
	
	private CCombo comboClassifier;
	private CCombo comboStereotype;
	private CCombo comboIP;
	private CCombo comboIPStereotype;
	
	private Label lblSavingFailed;
	private Label lblIdentityProvider;
	private Label lblIpStereotype;
	private Label lblStereotype;
	private Label lblType;
	
	private HashMap<Object, Boolean> enabledHashmap;
	
	private final String NEW = "New";
	private int selectedRow;
	private OntoUMLParser parser;
	private boolean isEnabled;
	
	private ArrayList<Class<?>> allowedStereotypes;
	private ArrayList<Classifier> existingClassifiers;	
	
	private ArrayList<Class<?>> allowedIpsStereotypes;
	private ArrayList<Classifier> existingIps;	
		
	public AddSelectSortalComposite(Composite parent, int style, OntoUMLParser parser, ArrayList<Class<?>> allowedStereotypes, ArrayList<Class<?>> allowedIpsStereotypes, ArrayList<Classifier> forbiddenTypes) throws Exception {
		super(parent, SWT.BORDER);
		
		if (parser==null)
			throw new Exception();
		if(allowedStereotypes==null || allowedStereotypes.size()==0)
			throw new Exception();
		if(allowedIpsStereotypes==null || allowedIpsStereotypes.size()==0)
			throw new Exception();
		
		this.parser = parser;
		this.allowedStereotypes = allowedStereotypes;
		this.allowedIpsStereotypes = allowedIpsStereotypes;
		this.enabledHashmap =  new HashMap<Object,Boolean>();
		
		this.existingClassifiers = getExistingClassifiersList(allowedStereotypes);
		this.existingClassifiers.removeAll(forbiddenTypes);
		this.existingIps = getExistingClassifiersList(allowedIpsStereotypes);
		this.existingIps.removeAll(forbiddenTypes);
		
		table = new Table(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setBounds(10, 102, 530, 113);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addSelectionListener(tableListener);
		
		tblclmnType = new TableColumn(table, SWT.NONE);
		tblclmnType.setWidth(58);
		tblclmnType.setText("Type");
		
		tblclmnStereotype = new TableColumn(table, SWT.NONE);
		tblclmnStereotype.setWidth(90);
		tblclmnStereotype.setText("Stereotype");
		
		tblclmnLocation = new TableColumn(table, SWT.NONE);
		tblclmnLocation.setWidth(91);
		tblclmnLocation.setText("Location");
		
		tblclmnIP = new TableColumn(table, SWT.NONE);
		tblclmnIP.setWidth(114);
		tblclmnIP.setText("Identity Provider");
		
		tblclmnIPStereotype = new TableColumn(table, SWT.NONE);
		tblclmnIPStereotype.setWidth(113);
		tblclmnIPStereotype.setText("IP's Stereotype");
		
		tblclmnIPLocation = new TableColumn(table, SWT.NONE);
		tblclmnIPLocation.setWidth(100);
		tblclmnIPLocation.setText("IP's Location");
		
		btnDeleteFromTable = new Button(this, SWT.NONE);
		btnDeleteFromTable.setBounds(432, 71, 108, 25);
		btnDeleteFromTable.setText("Delete From Table");
		btnDeleteFromTable.addSelectionListener(btnDeleteToTableListener);
		
		btnSaveInTable = new Button(this, SWT.NONE);
		btnSaveInTable.setBounds(318, 71, 108, 25);
		btnSaveInTable.setText("Add To Table");
		btnSaveInTable.addSelectionListener(btnSaveInTableListener);
		
		btnNew = new Button(this, SWT.NONE);
		btnNew.setBounds(204, 71, 108, 25);
		btnNew.setText("New");
		btnNew.addSelectionListener(btnNewListener);
		
		lblType = new Label(this, SWT.NONE);
		lblType.setBounds(10, 10, 62, 21);
		lblType.setText("Type:");
		
		lblStereotype = new Label(this, SWT.NONE);
		lblStereotype.setBounds(10, 37, 62, 21);
		lblStereotype.setText("Stereotype: \r\n");
		
		lblIdentityProvider = new Label(this, SWT.NONE);
		lblIdentityProvider.setText("Identity Provider (IP):");
		lblIdentityProvider.setBounds(258, 10, 116, 21);
		
		lblIpStereotype = new Label(this, SWT.NONE);
		lblIpStereotype.setText("IP's Stereotype:");
		lblIpStereotype.setBounds(258, 37, 116, 21);

		comboClassifier = new CCombo(this, SWT.BORDER);
		setClassifierComboItems(existingClassifiers, comboClassifier);
		comboClassifier.setBounds(78, 10, 160, 21);
		comboClassifier.addSelectionListener(combosListener);
		comboClassifier.addListener(SWT.KeyUp, keyUpListener);
		
		comboStereotype = new CCombo(this, SWT.BORDER);
		setComboStereotypeItems(comboStereotype,allowedStereotypes);
		comboStereotype.setBounds(78, 37, 160, 21);
		comboStereotype.addSelectionListener(combosListener);
		comboStereotype.setEditable(false);
		
		comboIPStereotype = new CCombo(this, SWT.BORDER);
		setComboStereotypeItems(comboIPStereotype,allowedIpsStereotypes);
		comboIPStereotype.setBounds(380, 37, 160, 21);
		comboIPStereotype.addSelectionListener(combosListener);
		comboIPStereotype.setEditable(false);
		
		comboIP = new CCombo(this, SWT.BORDER);
		setClassifierComboItems(existingIps, comboIP);
		comboIP.setBounds(380, 10, 160, 21);
		comboIP.addSelectionListener(combosListener);
		comboIP.addListener(SWT.KeyUp, keyUpListener);
		
		lblSavingFailed = new Label(this, SWT.NONE);
		lblSavingFailed.setAlignment(SWT.RIGHT);
		lblSavingFailed.setBounds(10, 221, 530, 15);
		lblSavingFailed.setVisible(false);
		
		setInitialState();
	}

	private SelectionListener btnSaveInTableListener = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent event) {
			boolean saved = addLine();
			if(saved){
				setInitialState();
				setMessage("Subtype added!", SWT.COLOR_BLACK);
			}
			else{
				setMessage("Saving failed! Type and stereotype combination already in the table.", SWT.COLOR_RED);
			}
			
		}
	};
	
	private SelectionListener btnNewListener = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent event)  {
			setInitialState();
		}
	};
	
	private SelectionListener btnDeleteToTableListener = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent event)  {
			
			int index = table.getSelectionIndex();
			
			if(index>=0 && index<table.getItemCount()){
				table.remove(index);
				setInitialState();
				setMessage("Subtype removed!", SWT.COLOR_BLACK);
			}
			else{
				setMessage("Can't delete! Select a line in the table.", SWT.COLOR_RED);
			}
		}
	};
	
	private SelectionListener combosListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent event)  {
			setAllStereotypeCombos();
			btnSaveInTable.setEnabled(canSave());
		}
	};
	
	private Listener keyUpListener = new Listener() {
        @Override
        public void handleEvent(Event arg0) {
        	setAllStereotypeCombos();
			btnSaveInTable.setEnabled(canSave());
        }
    };
	
    private SelectionListener tableListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event)  {
			
			selectedRow = table.getSelectionIndex();
			TableItem ti = table.getItem(selectedRow);
			
			comboClassifier.setText(ti.getText(0));
			comboIP.setText(ti.getText(3));
			setAllStereotypeCombos();
			
			btnSaveInTable.setEnabled(canSave());
		}
	};
	
    
	private boolean canSave(){
		return comboClassifier.getText()!=null && !comboClassifier.getText().trim().isEmpty() && 
				comboIP.getText()!=null && !comboIP.getText().trim().isEmpty() &&
				comboStereotype.getSelectionIndex()>=0 && comboStereotype.getSelectionIndex()<allowedStereotypes.size() &&
				comboIPStereotype.getSelectionIndex()>=0 && comboIPStereotype.getSelectionIndex()<allowedIpsStereotypes.size();
	}
	
	private void setClassifierComboItems(ArrayList<Classifier> existingClass, CCombo combo){
		
		if(existingClass==null || existingClass.size()==0 || combo==null)
			return;
		
		for (Classifier classifier : existingClass){
				combo.add(classifier.getName());
		}
		
		return;
	}
	
	private ArrayList<Classifier> getExistingClassifiersList(ArrayList<Class<?>> allowedStereo){
		ArrayList<Classifier> existing = new ArrayList<Classifier>();
		
		if(allowedStereo==null || allowedStereo.size()==0)
			return existing;
		
		for (Class<?> stereotype : allowedStereo) {
			Set<?> classifiers = parser.getAllInstances(stereotype);
			for (Object classifier : classifiers) {
				if (classifier instanceof Classifier){
					existing.add((Classifier) classifier);
				}
			}
		}
		
		return existing;
	}
	
	private void setComboStereotypeItems(CCombo combo, ArrayList<Class<?>> stereotypes){
		if(combo==null)
			combo = new CCombo(this, SWT.BORDER);
		
		if(stereotypes!=null){
			for (Class<?> stereotype : stereotypes) {
				combo.add(getStereotypeName(stereotype));
			}
		}
	}
	

	public void setAllEnabled(boolean b){
		
		System.out.println("isEnabled: "+isEnabled);
		System.out.println("b: "+b);
		
		if(isEnabled && b==false){
			isEnabled = false;
			saveAllEnabled();
			setAllDisabled();
		}
		else if(!isEnabled && b==true){
			isEnabled = true;
			if(hashContainAllComponents())
				recoverAllEnabled();
			else
				setDefaultEnabled();
		}
		
	}
	
	public boolean isAllEnabled(){
		return isEnabled;
	}
	
	private boolean hashContainAllComponents(){
		return enabledHashmap.containsKey(btnSaveInTable) && enabledHashmap.containsKey(btnDeleteFromTable) && enabledHashmap.containsKey(btnNew) && enabledHashmap.containsKey(table) && 
				enabledHashmap.containsKey(comboClassifier) && enabledHashmap.containsKey(comboStereotype) && enabledHashmap.containsKey(comboIP) && enabledHashmap.containsKey(comboIPStereotype);
	}
	
	private void setAllDisabled(){
		btnSaveInTable.setEnabled(false);
		btnDeleteFromTable.setEnabled(false);
		btnNew.setEnabled(false);
		
		comboClassifier.setEnabled(false);
		comboStereotype.setEnabled(false);
		comboIP.setEnabled(false);
		comboIPStereotype.setEnabled(false);
		
		table.setEnabled(false);
	}
	
	private void setDefaultEnabled(){
		btnSaveInTable.setEnabled(false);
		btnDeleteFromTable.setEnabled(true);
		btnNew.setEnabled(true);
		
		comboClassifier.setEnabled(true);
		comboStereotype.setEnabled(true);
		comboIP.setEnabled(true);
		comboIPStereotype.setEnabled(true);
		
		table.setEnabled(true);
		
		this.isEnabled = true;
	}
	
	private void saveAllEnabled(){
		
		if(enabledHashmap==null)
			enabledHashmap = new HashMap<Object,Boolean>();
		
		enabledHashmap.put(btnSaveInTable, btnSaveInTable.isEnabled());
		enabledHashmap.put(btnDeleteFromTable, btnDeleteFromTable.isEnabled());
		enabledHashmap.put(btnNew, btnNew.isEnabled());
		
		enabledHashmap.put(comboClassifier,comboClassifier.isEnabled());
		enabledHashmap.put(comboStereotype,comboStereotype.isEnabled());
		enabledHashmap.put(comboIP,comboIP.isEnabled());
		enabledHashmap.put(comboIPStereotype,comboIPStereotype.isEnabled());
		
		enabledHashmap.put(table, table.isEnabled());
	}
	
	private void recoverAllEnabled(){
		for (Object object : enabledHashmap.keySet()) {
			((Control) object).setEnabled(enabledHashmap.get(object));
		}
	}
	
	private void setComboStereotypeFromComboType(CCombo comboStereotype, CCombo comboType, ArrayList<Classifier> existingClass){
		String stereotypeName;
		
		autoSelectItem(comboType);
		
		if(comboType.getSelectionIndex()>=0 && comboType.getSelectionIndex()<existingClass.size()){
			stereotypeName = OutcomeFixer.getStereotype(existingClass.get(comboType.getSelectionIndex()));
			comboStereotype.setEditable(true);
			comboStereotype.setText(stereotypeName);
			comboStereotype.setEditable(false);
			comboStereotype.setEnabled(false);
			autoSelectItem(comboStereotype);
		}
		else
			comboStereotype.setEnabled(true);
	}
	
	private void resetCombo(CCombo combo){
		combo.setText("");
		combo.select(-1);
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
		
		if(!canSave())
			return false;
		
		if(existsInTable(comboClassifier.getText(), comboStereotype.getText(),comboIP.getText(),comboIPStereotype.getText()))
			return false;
		
		//selects existing item or creates new one
		if(selectedRow>=0 && selectedRow<table.getItems().length)
			tableItem = table.getItem(selectedRow);
		else
			tableItem = new TableItem(table,SWT.NONE);
		
		tableItem.setText(0, comboClassifier.getText());
		tableItem.setText(1, comboStereotype.getText());
		tableItem.setText(3, comboIP.getText());
		tableItem.setText(4, comboIPStereotype.getText());
		
		if(comboClassifier.getSelectionIndex()==-1)
			tableItem.setText(2, NEW);
		else
			tableItem.setText(2, getPath(existingClassifiers.get(comboClassifier.getSelectionIndex())));
		
		if(comboIP.getSelectionIndex()==-1)
			tableItem.setText(5, NEW);
		else
			tableItem.setText(5, getPath(existingIps.get(comboIP.getSelectionIndex())));
		
		return true;
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
			
			String connector = "";
			
			if(c.eContainer()!=null)
				connector = "::";
			
			return getPath(c.eContainer())+connector+containerName;
		}
	}
	
	public ArrayList<SortalToAdd> getNewSortalSubtypes(){
		
		ArrayList<SortalToAdd> sortals = new ArrayList<SortalToAdd>();
		
		for (TableItem ti : table.getItems()) 
			sortals.add(createSortalToAdd(ti.getText(0), ti.getText(1), ti.getText(3), ti.getText(4)));
		
		return sortals;
	}
	
	private SortalToAdd createSortalToAdd(String sortalName, String sortalStereotypeName, String identityProviderName, String identityProviderStereotypeName){
		SortalToAdd sta;
		
		Classifier sortal = null;
		Classifier identityProvider = null;
		Class<?> sortalStereotype = null;
		Class<?> identityProviderStereotype = null;
		
		for (Classifier c : existingClassifiers) {
			if(sortalName.compareTo(c.getName())==0){
				sortal = c;
				break;
			}
		}
		
		for (Classifier c : existingIps) {
			if(identityProviderName.compareTo(c.getName())==0){
				identityProvider = c;
				break;
			}
		}
		
		for (Class<?> stereotype : allowedStereotypes) {
			if(getStereotypeName(stereotype).compareTo(sortalStereotypeName)==0){
				sortalStereotype = stereotype;
				break;
			}
		}
		
		for (Class<?> stereotype : allowedIpsStereotypes) {
			if(getStereotypeName(stereotype).compareTo(identityProviderName)==0){
				identityProviderStereotype = stereotype;
				break;
			}
		}
		
		if(sortal!=null){
			if(identityProvider!=null)
				sta = new SortalToAdd(sortal, identityProvider);
			else
				sta = new SortalToAdd(sortal, identityProviderName, identityProviderStereotype);
		}
		else{
			if(identityProvider!=null)
				sta = new SortalToAdd(sortalName, sortalStereotype, identityProvider);
			else
				sta = new SortalToAdd(sortalName, sortalStereotype, identityProviderName, identityProviderStereotype);
		}
		
		
		return sta;
	}
	
	private String getStereotypeName(Class<?> stereotype){
		String type = stereotype.getName().replaceAll("RefOntoUML.impl.","");
		type = type.replaceAll("class","");
		type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public boolean existsInTable(String type, String stereotype, String identityProvider, String identityProviderStereotype){
		
		for (TableItem ti : table.getItems()) {
			if(ti.getText(0).compareTo(type)==0 && ti.getText(1).compareTo(stereotype)==0 && ti.getText(3).compareTo(identityProvider)==0 && ti.getText(4).compareTo(identityProviderStereotype)==0)
				return true;
		}
		
		return false;
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

	private void resetSelectedRow() {
		selectedRow = -1;
	}

	private void setMessage(String message, int SWT_COLOR) {
		lblSavingFailed.setVisible(true);
		lblSavingFailed.setForeground(SWTResourceManager.getColor(SWT_COLOR));
		lblSavingFailed.setText(message);
	}
	
	public void setInitialState(){
		resetCombo(comboClassifier);
		resetCombo(comboStereotype);
		resetCombo(comboIP);
		resetCombo(comboIPStereotype);
		resetSelectedRow();
		setDefaultEnabled();
	}

	private void setAllStereotypeCombos() {
		setComboStereotypeFromComboType(comboStereotype, comboClassifier, existingClassifiers);
		setComboStereotypeFromComboType(comboIPStereotype, comboIP, existingIps);
	}
}
