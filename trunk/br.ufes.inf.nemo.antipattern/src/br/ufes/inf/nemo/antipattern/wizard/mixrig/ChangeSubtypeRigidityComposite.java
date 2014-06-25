package br.ufes.inf.nemo.antipattern.wizard.mixrig;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import RefOntoUML.Classifier;
import RefOntoUML.impl.CategoryImpl;
import RefOntoUML.impl.CollectiveImpl;
import RefOntoUML.impl.KindImpl;
import RefOntoUML.impl.MixinImpl;
import RefOntoUML.impl.PhaseImpl;
import RefOntoUML.impl.QuantityImpl;
import RefOntoUML.impl.RoleImpl;
import RefOntoUML.impl.RoleMixinImpl;
import RefOntoUML.impl.SubKindImpl;
import br.ufes.inf.nemo.antipattern.mixrig.MixRigOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class ChangeSubtypeRigidityComposite extends Composite {

	private MixRigOccurrence mixRig;
	private ArrayList<Class<?>> stereotypes;
	private HashMap<Classifier,CCombo> comboList;
	
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ChangeSubtypeRigidityComposite(Composite parent, int style, MixRigOccurrence mixRig, ArrayList<Class<?>> stereotypes, SelectionListener comboListener) {
		super(parent, style);
		this.mixRig = mixRig;
		this.stereotypes = stereotypes;
		
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tblclmnSubtype = new TableColumn(table, SWT.NONE);
		tblclmnSubtype.setWidth(293);
		tblclmnSubtype.setText("Subtype");
		
		TableColumn tblclmnStereotype = new TableColumn(table, SWT.NONE);
		tblclmnStereotype.setWidth(115);
		tblclmnStereotype.setText("Stereotype");
		
		TableColumn tblclmnRigidity = new TableColumn(table, SWT.NONE);
		tblclmnRigidity.setWidth(109);
		tblclmnRigidity.setText("Rigidity");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(table, GroupLayout.DEFAULT_SIZE, 566, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(table, GroupLayout.DEFAULT_SIZE, 177, Short.MAX_VALUE)
		);
		setLayout(groupLayout);
		
		addRows(comboListener);

	}
	
	public void setEnabledToAllContents(boolean b){
		for (CCombo combo : comboList.values()) {
			combo.setEnabled(b);
		}
		table.setEnabled(b);
	}
	
	
	private SelectionListener comboAdapter = new SelectionAdapter() {
		public void widgetSelected(SelectionEvent event) {
			CCombo combo = (CCombo) event.widget;
			TableItem ti = getItem(combo);
			ti.setText(2, ChangeSubtypeRigidityComposite.this.getRigidityFromStereotype(combo.getText()));
		}
	};
	
	private TableItem getItem(CCombo combo){
		for (TableItem ti : table.getItems()) {
			if(ti.getData("combo").equals(combo))
				return ti;
		}
		return null;
	}
	
	//adds a row for each subtype
	private void addRows(SelectionListener comboListener){
		this.comboList = new HashMap<Classifier,CCombo>();
		for (Classifier subtype : mixRig.getSubtypes()) {
			TableItem ti = new TableItem(table,SWT.NONE);
			ti.setText(0, subtype.getName());
			
			TableEditor editor = new TableEditor(table);
			CCombo combo = createStereotypeCCombo(table, SWT.BORDER, subtype);
			combo.setText(getStereotypeName(subtype.getClass()));
			combo.setEditable(false);
			combo.addSelectionListener(comboAdapter);
			combo.addSelectionListener(comboListener);
			combo.pack();
			
			editor.minimumWidth = combo.getSize().x;
			editor.horizontalAlignment = SWT.CENTER;
			editor.setEditor(combo, ti, 1);
			
			ti.setData("combo", combo);
			ti.setText(2, getRigidityFromStereotype(getStereotypeName(subtype.getClass())));
			
			comboList.put(subtype, combo);
		}
	}
	
	//creates a combo with the stereotypes allowed for change
	private CCombo createStereotypeCCombo(Table table, int SWT_Options, Classifier c){
		CCombo combo = new CCombo(table, SWT.BORDER);
		for (Class<?> stereotype : stereotypes) {
			combo.add(getStereotypeName(stereotype));
		}
		combo.add(getStereotypeName(c.getClass()));
		return combo;
	}

	//return a string for the stereotype
	private String getStereotypeName(Class<?> stereotype){
		String type = stereotype.getName().replaceAll("RefOntoUML.impl.","");
		type = type.replaceAll("class","");
		type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	public HashMap<Classifier, Class<?>> getModifiedSubtypes(){
		HashMap<Classifier, Class<?>> result = new HashMap<Classifier, Class<?>>();
		
		for (Classifier subtype : comboList.keySet()) {
			CCombo combo = comboList.get(subtype);
			if((combo.getSelectionIndex()>=0 && combo.getSelectionIndex()<stereotypes.size()) 
					&& !subtype.getClass().equals(stereotypes.get(combo.getSelectionIndex())))
				result.put(subtype, stereotypes.get(combo.getSelectionIndex()));
		}
		
		return result;
	}
	
	private String getRigidityFromStereotype(String stereotypeName){
		if(stereotypeName.compareTo(getStereotypeName(KindImpl.class))==0 || stereotypeName.compareTo(getStereotypeName(QuantityImpl.class))==0 || stereotypeName.compareTo(getStereotypeName(CollectiveImpl.class))==0
				|| stereotypeName.compareTo(getStereotypeName(SubKindImpl.class))==0 || stereotypeName.compareTo(getStereotypeName(CategoryImpl.class))==0)
			return "Rigid";
		if(stereotypeName.compareTo(getStereotypeName(RoleImpl.class))==0 || stereotypeName.compareTo(getStereotypeName(PhaseImpl.class))==0 || stereotypeName.compareTo(getStereotypeName(RoleMixinImpl.class))==0)
			return "Anti-Rigid";
		if(stereotypeName.compareTo(getStereotypeName(MixinImpl.class))==0)
			return "Semi-Rigid";
		else
			return "";
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
