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
import br.ufes.inf.nemo.antipattern.mixiden.MixIdenOccurrence;
import br.ufes.inf.nemo.antipattern.mixiden.SortalToAdd;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class ChangeIdentityProviderComposite extends Composite {
	
	private static final String IDENTITY_PROVIDER_STEREOTYPE = "IdentityProviderStereotype";
	private static final String STEREOTYPE = "Stereotype";
	private static final String SUBTYPE = "Subtype";
	private static final String IDENTITY_PROVIDER = "IdentityProvider";
	private static final String NEW = "New";
	
	private Table table;
	private TableColumn tblclmnSubtype;
	private TableColumn tblclmnStereotype;
	private TableColumn tblclmnLocation;
	private TableColumn tblclmnIP;
	private TableColumn tblclmnIPStereotype;
	private TableColumn tblclmnIPLocation;
	
	private Button btnDeleteFromTable;
	private Button btnSaveInTable;
	private Button btnClear;
	
	private CCombo comboSubtype;
	private CCombo comboStereotype;
	private CCombo comboIP;
	private CCombo comboIPStereotype;
	
	private Label lblOperationResult;
	private Label lblIdentityProvider;
	private Label lblIpStereotype;
	private Label lblStereotype;
	private Label lblSubtype;
	
	private HashMap<Object, Boolean> enabledHashmap;
	
	
	private int selectedRow;
	private boolean isEnabled;
	private MixIdenOccurrence mixIden;
	
	private ArrayList<Class<?>> allowedStereotypes;
	private ArrayList<Class<?>> allowedIpsStereotypes;
	private ArrayList<Classifier> existingIps;	
		
	public ChangeIdentityProviderComposite(Composite parent, int style, MixIdenOccurrence mixIden, SelectionListener enableNextListener) throws Exception {
		super(parent, SWT.BORDER);
		
		if (mixIden==null)
			throw new Exception();
		this.mixIden = mixIden;
		
		allowedStereotypes = mixIden.allowedSubtypeStereotypes();
		allowedIpsStereotypes = mixIden.identityProviderStereotypes();
		
		this.enabledHashmap =  new HashMap<Object,Boolean>();

		existingIps = getExistingClassifiersList(allowedIpsStereotypes);
		existingIps.remove(mixIden.getIdentityProvider());
		
		table = new Table(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.addSelectionListener(tableListener);
		
		tblclmnSubtype = new TableColumn(table, SWT.NONE);
		tblclmnSubtype.setWidth(58);
		tblclmnSubtype.setText(SUBTYPE);
		
		tblclmnStereotype = new TableColumn(table, SWT.NONE);
		tblclmnStereotype.setWidth(90);
		tblclmnStereotype.setText(STEREOTYPE);
		
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
		tblclmnIPLocation.setWidth(85);
		tblclmnIPLocation.setText("IP's Location");
		
		btnDeleteFromTable = new Button(this, SWT.NONE);
		btnDeleteFromTable.setText("Delete From Table");
		btnDeleteFromTable.addSelectionListener(btnDeleteToTableListener);
		btnDeleteFromTable.addSelectionListener(enableNextListener);
		
		btnSaveInTable = new Button(this, SWT.NONE);
		btnSaveInTable.setText("Save In Table");
		btnSaveInTable.addSelectionListener(btnSaveInTableListener);
		btnSaveInTable.addSelectionListener(enableNextListener);
		
		btnClear = new Button(this, SWT.NONE);
		btnClear.setText("Clear");
		btnClear.addSelectionListener(btnClearListener);
		
		lblSubtype = new Label(this, SWT.NONE);
		lblSubtype.setText("Subtype:");
		
		lblStereotype = new Label(this, SWT.NONE);
		lblStereotype.setText("Stereotype: \r\n");
		
		lblIdentityProvider = new Label(this, SWT.NONE);
		lblIdentityProvider.setText("New Identity Provider (IP):");
		
		lblIpStereotype = new Label(this, SWT.NONE);
		lblIpStereotype.setText("IP's Stereotype:");

		comboSubtype = new CCombo(this, SWT.BORDER);
		setClassifierComboItems(mixIden.getSubtypes(), comboSubtype);
		comboSubtype.setEditable(false);
		comboSubtype.addSelectionListener(combosListener);
		
		comboStereotype = new CCombo(this, SWT.BORDER);
		setComboStereotypeItems(comboStereotype,allowedStereotypes);
		comboStereotype.addSelectionListener(combosListener);
		comboStereotype.setEditable(false);
		
		comboIP = new CCombo(this, SWT.BORDER);
		setClassifierComboItems(existingIps, comboIP);
		comboIP.setEditable(true);
		comboIP.addSelectionListener(combosListener);
		comboIP.addListener(SWT.KeyUp, keyUpListener);
		
		comboIPStereotype = new CCombo(this, SWT.BORDER);
		setComboStereotypeItems(comboIPStereotype,allowedIpsStereotypes);
		comboIPStereotype.addSelectionListener(combosListener);
		comboIPStereotype.setEditable(false);
		
		lblOperationResult = new Label(this, SWT.NONE);
		lblOperationResult.setAlignment(SWT.RIGHT);
		lblOperationResult.setVisible(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(lblSubtype, GroupLayout.PREFERRED_SIZE, 62, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(comboSubtype, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
					.add(10)
					.add(lblIdentityProvider)
					.add(6)
					.add(comboIP, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
					.add(8))
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(lblStereotype)
					.add(6)
					.add(comboStereotype, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
					.add(10)
					.add(lblIpStereotype, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(comboIPStereotype, GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
					.add(8))
				.add(GroupLayout.TRAILING, groupLayout.createSequentialGroup()
					.add(192)
					.add(btnClear, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnSaveInTable, GroupLayout.PREFERRED_SIZE, 108, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(btnDeleteFromTable)
					.add(8))
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(table, GroupLayout.DEFAULT_SIZE, 518, Short.MAX_VALUE)
					.add(8))
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(lblOperationResult, GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
					.add(10))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(lblSubtype, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.add(comboSubtype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblIdentityProvider, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.add(comboIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(6)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(lblStereotype, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.add(comboStereotype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(lblIpStereotype, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.add(comboIPStereotype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(13)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(btnClear)
						.add(btnSaveInTable)
						.add(btnDeleteFromTable))
					.add(6)
					.add(table, GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
					.add(6)
					.add(lblOperationResult)
					.add(7))
		);
		setLayout(groupLayout);
		
		setInitialState();
	}

	private SelectionListener btnSaveInTableListener = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent event) {
			boolean saved = addLine();
			if(saved){
				setInitialState();
				setMessage("Operation Succeded! New identity provider added for subtype "+comboSubtype.getText()+"!", SWT.COLOR_BLACK);
			}
			else{
				setMessage("Operation Failed! Subtype "+comboSubtype.getText()+" already in table. Edit current entry or select another subtype.", SWT.COLOR_RED);
			}
			
		}
	};
	
	private SelectionListener btnClearListener = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent event)  {
			setInitialState();
		}
	};
	
	private SelectionListener btnDeleteToTableListener = new SelectionAdapter() {

		public void widgetSelected(SelectionEvent event)  {
			
			int index = table.getSelectionIndex();
			
			if(index>=0 && index<table.getItemCount()){
				String subtypeName = table.getItem(index).getText(0);
				table.remove(index);
				setInitialState();
				setMessage("Operation Succeded! Subtype "+subtypeName+" removed!", SWT.COLOR_BLACK);
			}
			else{
				setMessage("Operation Failed! To delete, select a line in the table.", SWT.COLOR_RED);
			}
		}
	};
	
	private SelectionListener combosListener = new SelectionAdapter() {
		
		@Override
		public void widgetSelected(SelectionEvent event)  {
			
			if(event.widget.equals(comboSubtype)){
				setComboStereotypeFromComboType(comboStereotype, comboSubtype, mixIden.getSubtypes());
			}

			if(event.widget.equals(comboIP)){
				setComboStereotypeFromComboType(comboIPStereotype, comboIP, existingIps);
			}
			
			commonActions();
		}
	};
	
	private void commonActions() {
		if(isIdentityProviderStereotype(comboStereotype.getText())){
			comboIP.setText(comboSubtype.getText());
			comboIPStereotype.setText(comboStereotype.getText());
			comboIP.setEnabled(false);
			comboIPStereotype.setEnabled(false);
		}
		else{
			comboIP.setEnabled(true);
			comboIPStereotype.setEnabled(true);
		}
		
		if(canSave())
			btnSaveInTable.setEnabled(true);
		else
			btnSaveInTable.setEnabled(false);
	}
	
	private Listener keyUpListener = new Listener() {
        @Override
        public void handleEvent(Event arg0) {
        	setComboStereotypeFromComboType(comboIPStereotype, comboIP, existingIps);
        	
        	if(canSave())
				btnSaveInTable.setEnabled(true);
			else
				btnSaveInTable.setEnabled(false);
        }
    };
	
    private SelectionListener tableListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent event)  {
			
			selectedRow = table.getSelectionIndex();
			TableItem ti = table.getItem(selectedRow);
			
			comboSubtype.setText(ti.getText(0));
			comboStereotype.setText(ti.getText(1));
			comboIP.setText(ti.getText(3));
			comboIPStereotype.setText(ti.getText(4));
			
			autoSelectItem(comboSubtype);
			autoSelectItem(comboStereotype);
			autoSelectItem(comboIP);
			autoSelectItem(comboIPStereotype);
			
			commonActions();
		}
	};
	
    
	private boolean canSave(){
		return 	comboSubtype.getSelectionIndex()!=-1 &&
				comboStereotype.getSelectionIndex()!=-1 &&
				((comboIP.getText()!=null && !comboIP.getText().trim().isEmpty()) || (comboIP.getSelectionIndex()!=-1)) &&
				comboIPStereotype.getSelectionIndex()!=-1 ;
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
			Set<?> classifiers = mixIden.getParser().getAllInstances(stereotype);
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
		return enabledHashmap.containsKey(btnSaveInTable) && enabledHashmap.containsKey(btnDeleteFromTable) && enabledHashmap.containsKey(btnClear) && enabledHashmap.containsKey(table) && 
				enabledHashmap.containsKey(comboSubtype) && enabledHashmap.containsKey(comboStereotype) && enabledHashmap.containsKey(comboIP) && enabledHashmap.containsKey(comboIPStereotype);
	}
	
	private void setAllDisabled(){
		btnSaveInTable.setEnabled(false);
		btnDeleteFromTable.setEnabled(false);
		btnClear.setEnabled(false);
		
		comboSubtype.setEnabled(false);
		comboStereotype.setEnabled(false);
		comboIP.setEnabled(false);
		comboIPStereotype.setEnabled(false);
		
		table.setEnabled(false);
	}
	
	private void setDefaultEnabled(){
		btnSaveInTable.setEnabled(false);
		btnDeleteFromTable.setEnabled(true);
		btnClear.setEnabled(true);
		
		comboSubtype.setEnabled(true);
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
		enabledHashmap.put(btnClear, btnClear.isEnabled());
		
		enabledHashmap.put(comboSubtype,comboSubtype.isEnabled());
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
		
		if(comboType.getSelectionIndex()!=-1){
			stereotypeName = OutcomeFixer.getStereotype(existingClass.get(comboType.getSelectionIndex()));
			comboStereotype.setEditable(true);
			comboStereotype.setText(stereotypeName);
			comboStereotype.setEditable(false);
			autoSelectItem(comboStereotype);
		}
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
		
		if(selectedRow==-1 && !existsInTable(comboSubtype.getText()))
			tableItem = new TableItem(table,SWT.NONE);
		else if(selectedRow>=0 && selectedRow<table.getItems().length)
			tableItem = table.getItem(selectedRow);
		else
			return false;
		
		//the subtype cant be create, only existing ones can be chose
		Classifier subtype = mixIden.getSubtypes().get(comboSubtype.getSelectionIndex());
		//the stereotype of the subtype may be changed
		Class<?> stereotype = allowedStereotypes.get(comboStereotype.getSelectionIndex());
		
		Classifier identityProvider;
		Class<?> identityProviderStereotype;
		
		//if the subtype is an ultimate sortal, the it is its own identity provider
		if(isIdentityProviderStereotype(comboStereotype.getText())){
			identityProvider = subtype;
			identityProviderStereotype = stereotype;
		}
		else{
			//existing identity provider
			if(comboIP.getSelectionIndex()!=-1)
				identityProvider = existingIps.get(comboIP.getSelectionIndex());
			//new identity provider
			else
				identityProvider = null;
			//identity provider stereotype can be changed
			identityProviderStereotype = allowedIpsStereotypes.get(comboIPStereotype.getSelectionIndex());
		}
		
		tableItem.setData(SUBTYPE, subtype);
		tableItem.setData(IDENTITY_PROVIDER, identityProvider);
		tableItem.setData(STEREOTYPE, stereotype);
		tableItem.setData(IDENTITY_PROVIDER_STEREOTYPE, identityProviderStereotype);
		
		//sets the types names and their stereotypes
		tableItem.setText(0, comboSubtype.getText());
		tableItem.setText(1, comboStereotype.getText());
		tableItem.setText(3, comboIP.getText());
		tableItem.setText(4, comboIPStereotype.getText());
		
		//sets subtype path
		tableItem.setText(2, getPath(subtype));

		//sets the identity provider path
		if(identityProvider!=null){
			tableItem.setText(5,getPath(identityProvider));
		}
		else
			tableItem.setText(5, NEW);
			
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
			
			if(c.eContainer()!=null && c.eContainer().eContainer()!=null )
				connector = "::";
			
			return getPath(c.eContainer())+connector+containerName;
		}
	}
	
	public ArrayList<SortalToAdd> getSubtypesToFix(){
		
		ArrayList<SortalToAdd> sortals = new ArrayList<SortalToAdd>();
		
		for (TableItem ti : table.getItems()){
			if(ti.getData(IDENTITY_PROVIDER)==null)
				sortals.add(createSortalToAdd((Classifier)ti.getData(SUBTYPE), (Class<?>)ti.getData(STEREOTYPE),  
						ti.getText(3), (Class<?>)ti.getData(IDENTITY_PROVIDER_STEREOTYPE)));
			else
				sortals.add(createSortalToAdd((Classifier)ti.getData(SUBTYPE), (Class<?>)ti.getData(STEREOTYPE), 
						(Classifier)ti.getData(IDENTITY_PROVIDER), (Class<?>)ti.getData(IDENTITY_PROVIDER_STEREOTYPE)));
		}
		
		return sortals;
	}
	
	private SortalToAdd createSortalToAdd(Classifier sortal, Class<?> sortalStereotype, Classifier identityProvider, Class<?> identityProviderStereotype){
		SortalToAdd sta;
		
		if(sortal==null || sortalStereotype==null || identityProvider == null || identityProviderStereotype==null)
			return null;
		//existing sortal with same stereotype
		if(sortal.getClass().equals(sortalStereotype)){
			//existing ip with same stereotype
			if(identityProvider.getClass().equals(identityProviderStereotype))
				sta = new SortalToAdd(sortal,identityProvider);
			//existing ip with different stereotype
			else
				sta = new SortalToAdd(sortal,identityProvider,identityProviderStereotype);
		}
		//existing sortal with different stereotype
		else{
			//existing ip with same stereotype
			if(identityProvider.getClass().equals(identityProviderStereotype))
				sta = new SortalToAdd(sortal,sortalStereotype,identityProvider);
			//existing ip with different stereotype
			else
				sta = new SortalToAdd(sortal,sortalStereotype,identityProvider,identityProviderStereotype);
		}

		return sta;
	}
	
	private SortalToAdd createSortalToAdd(Classifier sortal, Class<?> sortalStereotype, String identityProviderName, Class<?> identityProviderStereotype){
		SortalToAdd sta;
		
		if(sortal==null || sortalStereotype==null || identityProviderName == null || identityProviderStereotype==null)
			return null;
		
		//existing sortal with same stereotype
		if(sortal.getClass().equals(sortalStereotype))
			sta = new SortalToAdd(sortal,identityProviderName,identityProviderStereotype);
		
		//existing sortal with different stereotype
		else
			sta = new SortalToAdd(sortal,sortalStereotype,identityProviderName,identityProviderStereotype);
		

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
	
	//user can only add each subtype once
	public boolean existsInTable(String type){	
		for (TableItem ti : table.getItems()) {
			if(ti.getText(0).compareTo(type)==0)
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
		lblOperationResult.setVisible(true);
		lblOperationResult.setForeground(SWTResourceManager.getColor(SWT_COLOR));
		lblOperationResult.setText(message);
	}
	
	public void setInitialState(){
		resetCombo(comboSubtype);
		resetCombo(comboStereotype);
		resetCombo(comboIP);
		resetCombo(comboIPStereotype);
		resetSelectedRow();
		setDefaultEnabled();
		setMessage("", SWT.COLOR_BLACK);
	}

	private boolean isIdentityProviderStereotype(String stereotypeName){
		for (Class<?> stereo : allowedIpsStereotypes) {
			if(getStereotypeName(stereo).compareTo(stereotypeName)==0)
				return true;
		}
		
		return false;
	}
}
