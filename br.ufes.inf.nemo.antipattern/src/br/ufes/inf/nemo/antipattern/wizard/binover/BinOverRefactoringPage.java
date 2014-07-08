package br.ufes.inf.nemo.antipattern.wizard.binover;

import java.util.ArrayList;
import java.util.Arrays;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.ExpandAdapter;
import org.eclipse.swt.events.ExpandEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ExpandBar;
import org.eclipse.swt.widgets.ExpandItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Association;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence;
import br.ufes.inf.nemo.antipattern.binover.BinOverOccurrence.BinaryPropertyValue;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class BinOverRefactoringPage extends RefactoringPage {

	protected BinOverOccurrence binOver;
	private ArrayList<Class<? extends Association>> possibleStereotypes;
	
	//UI
	private CCombo comboDisjoint, comboStereotype;
	private Label lblimpossibleCombination;
	private static Composite compositeReflexivity, compositeSymmetry, compositeTransitivity, compositeOthers, compositeCyclicity;
	private static List list;
	private static ExpandItem itemReflexivity, itemSymmetry ,itemTransitivity, itemCyclicity, itemOthers;
	private static Button checkReflexive, checkCoReflexive, checkShiftReflexive, checkQuasiReflexive, checkAntiReflexive, checkNonReflexive, 
						checkSymmetric, checkAntiSymmetric, checkAsymmetric, checkNonSymmetric, checkTransitive, checkCoTransitive, 
						checkOpTransitive, checkAntiTransitive, checkNonTransitive, checkNegativelyTransitive, checkSemiTransitive, checkQuasiTransitive, 
						checkCyclic, checkAcyclic, checkEuclidean, checkWeakConnected, checkLeftEuclidean, checkStrongConnected, checkWeakComplete, 
						checkTrichotomous, checkStrongComplete, checkFerrers;
	private ExpandBar bar;
	
	public ArrayList<BinaryPropertyValue> getSelectedProperties (){
		ArrayList<BinaryPropertyValue> selected = new ArrayList<BinaryPropertyValue>();
		
		if(checkReflexive.getSelection()) selected.add(BinaryPropertyValue.REFLEXIVE);
		if(checkCoReflexive.getSelection()) selected.add(BinaryPropertyValue.CO_REFLEXIVE);
		if(checkShiftReflexive.getSelection()) selected.add(BinaryPropertyValue.SHIFT_REFLEXIVE);
		if(checkQuasiReflexive.getSelection()) selected.add(BinaryPropertyValue.QUASI_REFLEXIVE);
		if(checkAntiReflexive.getSelection()) selected.add(BinaryPropertyValue.ANTI_REFLEXIVE);
		if(checkNonReflexive.getSelection()) selected.add(BinaryPropertyValue.NON_REFLEXIVE);
		
		if(checkSymmetric.getSelection()) selected.add(BinaryPropertyValue.SYMMETRIC);
		if(checkAntiSymmetric.getSelection()) selected.add(BinaryPropertyValue.ANTI_SYMMETRIC);
		if(checkAsymmetric.getSelection()) selected.add(BinaryPropertyValue.ASYMMETRIC);
		if(checkNonSymmetric.getSelection()) selected.add(BinaryPropertyValue.NON_SYMMETRIC);
		
		if(checkTransitive.getSelection()) selected.add(BinaryPropertyValue.TRANSITIVE);
		if(checkCoTransitive.getSelection()) selected.add(BinaryPropertyValue.CO_TRANSITIVE);
		if(checkOpTransitive.getSelection()) selected.add(BinaryPropertyValue.OP_TRANSITIVE);
		if(checkAntiTransitive.getSelection()) selected.add(BinaryPropertyValue.ANTI_TRANSITIVE);
		if(checkNonTransitive.getSelection()) selected.add(BinaryPropertyValue.NON_TRANSITIVE);
		if(checkNegativelyTransitive.getSelection()) selected.add(BinaryPropertyValue.NEGATIVELY_TRANSITIVE);
		if(checkSemiTransitive.getSelection()) selected.add(BinaryPropertyValue.SEMI_TRANSITIVE);
		if(checkQuasiTransitive.getSelection()) selected.add(BinaryPropertyValue.QUASI_TRANSITIVE);
		
		if(checkCyclic.getSelection()) selected.add(BinaryPropertyValue.CYCLIC);
		if(checkAcyclic.getSelection()) selected.add(BinaryPropertyValue.ACYCLIC);
		
		if(checkEuclidean.getSelection()) selected.add(BinaryPropertyValue.EUCLIDEAN);
		if(checkLeftEuclidean.getSelection()) selected.add(BinaryPropertyValue.LEFT_EUCLIDEAN);
		if(checkWeakConnected.getSelection()) selected.add(BinaryPropertyValue.WEAK_CONNECTED);
		if(checkStrongConnected.getSelection()) selected.add(BinaryPropertyValue.STRONG_CONNECTED);
		if(checkWeakComplete.getSelection()) selected.add(BinaryPropertyValue.WEAK_COMPLETE);
		if(checkStrongComplete.getSelection()) selected.add(BinaryPropertyValue.STRONG_COMPLETE);
		if(checkTrichotomous.getSelection()) selected.add(BinaryPropertyValue.TRICHOTOMOUS);
		if(checkFerrers.getSelection()) selected.add(BinaryPropertyValue.FERRERS);
			
		return selected;
	}
	
	public BinOverRefactoringPage(BinOverOccurrence binOver) {
		super();
		this.binOver = binOver;
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);
		setControl(container);

		Label label_1 = new Label(container, SWT.NONE);
		
		ExpandAdapter expandAdapter = new ExpandAdapter() {
			@Override
			public void itemExpanded(ExpandEvent e){
				if(!e.getSource().equals(itemReflexivity))
					itemReflexivity.setExpanded(false);
				if(!e.getSource().equals(itemSymmetry))
					itemSymmetry.setExpanded(false);
				if(!e.getSource().equals(itemTransitivity))
					itemTransitivity.setExpanded(false);
				if(!e.getSource().equals(itemCyclicity))
					itemCyclicity.setExpanded(false);
				if(!e.getSource().equals(itemOthers))
					itemOthers.setExpanded(false);
			}
		};		
		
		SelectionAdapter listenerDisjoint = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				if(comboDisjoint.getSelectionIndex()==-1 || comboDisjoint.getSelectionIndex()==0){
					itemReflexivity.setExpanded(false);
					itemSymmetry.setExpanded(false);
					itemTransitivity.setExpanded(false);
					itemCyclicity.setExpanded(false);
					itemOthers.setExpanded(false);
					bar.setEnabled(false);
					comboStereotype.setEnabled(false);
					lblimpossibleCombination.setVisible(false);
				}
				
				if(comboDisjoint.getSelectionIndex()==0)
					setPageComplete(true);
				
				if(comboDisjoint.getSelectionIndex()==1){
					bar.setEnabled(true);
					itemReflexivity.setExpanded(true);
					comboStereotype.setEnabled(true);
				}
			}
		};
		
		SelectionAdapter listenerCheckboxes = new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				
				String selected = null;
				int index = comboStereotype.getSelectionIndex();
				
				if(index!=-1)
					selected = comboStereotype.getItem(index);
				
				possibleStereotypes = getBinOverWizard().allStereotypes();
				for (BinaryPropertyValue property : getSelectedProperties())
					possibleStereotypes.retainAll(getBinOverWizard().possibleStereotypes(property));
				
				ArrayList<String> possibleStereotypeNames = getBinOverWizard().getStereotypeNames(possibleStereotypes);
				ArrayList<String> currentStereotypeNames = new ArrayList<String>(Arrays.asList(comboStereotype.getItems()));
				
				if(!possibleStereotypeNames.containsAll(currentStereotypeNames) || !currentStereotypeNames.containsAll(possibleStereotypeNames)){
					comboStereotype.removeAll();	
					
					for (String op : possibleStereotypeNames)
						comboStereotype.add(op);
					
					if(possibleStereotypes.size()>0 ){//&& BinOverOccurrence.validCombination(getReflexivityValue(), getSymmetryValue(), getTransitivityValue(), getCyclicityValue())){
						if(selected!=null && possibleStereotypeNames.contains(selected))
							comboStereotype.select(possibleStereotypeNames.indexOf(selected));
						else
							comboStereotype.select(0);
						
						comboStereotype.setEnabled(true);
						lblimpossibleCombination.setVisible(false);
						setPageComplete(true);
					}
					else{
						setPageComplete(false);
						comboStereotype.setEnabled(false);
						lblimpossibleCombination.setVisible(true);
					}
				}
			}
		};
			
		
		Label lblChoose = new Label(container, SWT.NONE);
		lblChoose.setText("Choose the appropriate refactoring options:");
		
		Label lblIsDisjoint = new Label(container, SWT.NONE);
		lblIsDisjoint.setText("Is Disjoint: ");
		Label label = new Label(container, SWT.NONE);
		
		comboDisjoint = new CCombo(container, SWT.BORDER | SWT.READ_ONLY);
		comboDisjoint.setItems(new String[] {"True", "False"});
		comboDisjoint.addSelectionListener(listenerDisjoint);
		
		Label lblNewStereotype = new Label(container, SWT.NONE);
		lblNewStereotype.setText("New Stereotype:");
		Label label_3 = new Label(container, SWT.NONE);

		comboStereotype = new CCombo(container, SWT.BORDER | SWT.READ_ONLY);		
		comboStereotype.setEnabled(false);
		for (String op : getBinOverWizard().getStereotypeNames(getBinOverWizard().allStereotypes()))
			comboStereotype.add(op);
		
		Label label_2 = new Label(container, SWT.NONE);
		
		lblimpossibleCombination = new Label(container, SWT.WRAP);
		lblimpossibleCombination.setVisible(false);
		lblimpossibleCombination.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblimpossibleCombination.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		lblimpossibleCombination.setText("(No stereotype is suitable for the selected binary property values. Try modifying them!)");
		
		bar = new ExpandBar (container, SWT.V_SCROLL);
		bar.setSpacing(0);
		bar.setBackground(container.getBackground());
		bar.addExpandListener(expandAdapter);
		bar.setEnabled(false);
		
		  compositeReflexivity = new Composite (bar, SWT.BORDER);
		  GridLayout layout = new GridLayout ();
		  layout.numColumns = 2;
		  layout.marginLeft = layout.marginTop=
		  layout.marginRight=layout.marginBottom=8;
		  layout.verticalSpacing = 10;
		  layout.horizontalSpacing = 10;
		  compositeReflexivity.setLayout(layout);
		  compositeReflexivity.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		  
		  compositeSymmetry = new Composite (bar, SWT.BORDER);
		  layout = new GridLayout (2, false);
		  layout.marginLeft = layout.marginTop = layout.marginRight = layout.marginBottom = 8;
		  layout.verticalSpacing = 10;
		  layout.horizontalSpacing = 10;
		  compositeSymmetry.setLayout(layout);  
		  compositeSymmetry.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		  
		  checkReflexive = new Button(compositeReflexivity, SWT.CHECK);
		  checkReflexive.setText("Reflexive");
		  checkReflexive.setBackground(checkReflexive.getParent().getBackground());
		  checkReflexive.addSelectionListener(listenerCheckboxes);
		  
		  checkQuasiReflexive = new Button(compositeReflexivity, SWT.CHECK);
		  checkQuasiReflexive.setText("Quasi-Reflexive");
		  checkQuasiReflexive.setBackground(checkQuasiReflexive.getParent().getBackground());
		  checkQuasiReflexive.addSelectionListener(listenerCheckboxes);
		  
		  checkCoReflexive = new Button(compositeReflexivity, SWT.CHECK);
		  checkCoReflexive.setText("Co-Reflexive");
		  checkCoReflexive.setBackground(checkCoReflexive.getParent().getBackground());
		  checkCoReflexive.addSelectionListener(listenerCheckboxes);
		  
		  checkAntiReflexive = new Button(compositeReflexivity, SWT.CHECK);
		  checkAntiReflexive.setText("Anti-Reflexive");
		  checkAntiReflexive.setBackground(checkAntiReflexive.getParent().getBackground());
		  checkAntiReflexive.addSelectionListener(listenerCheckboxes);
		  
		  checkShiftReflexive = new Button(compositeReflexivity, SWT.CHECK);
		  checkShiftReflexive.setText("Shift-Reflexive");
		  checkShiftReflexive.setBackground(checkShiftReflexive.getParent().getBackground());
		  checkShiftReflexive.addSelectionListener(listenerCheckboxes);
		  
		  checkNonReflexive = new Button(compositeReflexivity, SWT.CHECK);
		  checkNonReflexive.setText("Non-Reflexive");
		  checkNonReflexive.setBackground(checkNonReflexive.getParent().getBackground());
		  checkNonReflexive.addSelectionListener(listenerCheckboxes);
		  
		  itemReflexivity = new ExpandItem (bar, SWT.NONE, 0);
		  itemReflexivity.setExpanded(false);
		  itemReflexivity.setText("Reflexivity");
		  itemReflexivity.setHeight(compositeReflexivity.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		  itemReflexivity.setControl(compositeReflexivity);		  
		  
		  checkSymmetric = new Button(compositeSymmetry, SWT.CHECK);
		  checkSymmetric.setText("Symmetric");
		  checkSymmetric.setBackground(checkSymmetric.getParent().getBackground());
		  checkSymmetric.addSelectionListener(listenerCheckboxes);
		  
		  checkAntiSymmetric = new Button(compositeSymmetry, SWT.CHECK);
		  checkAntiSymmetric.setText("Anti-Symmetric");
		  checkAntiSymmetric.setBackground(checkAntiSymmetric.getParent().getBackground());
		  checkAntiSymmetric.addSelectionListener(listenerCheckboxes);
		  
		  checkAsymmetric = new Button(compositeSymmetry, SWT.CHECK);
		  checkAsymmetric.setText("Asymmetric");
		  checkAsymmetric.setBackground(checkAsymmetric.getParent().getBackground());
		  checkAsymmetric.addSelectionListener(listenerCheckboxes);
		  
		  checkNonSymmetric = new Button(compositeSymmetry, SWT.CHECK);
		  checkNonSymmetric.setText("Non-Symmetric");
		  checkNonSymmetric.setBackground(checkNonSymmetric.getParent().getBackground());
		  checkNonSymmetric.addSelectionListener(listenerCheckboxes);
		  
		  itemSymmetry = new ExpandItem (bar, SWT.NONE, 1);
		  itemSymmetry.setText("Symmetry");
		  itemSymmetry.setHeight(compositeSymmetry.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		  itemSymmetry.setControl(compositeSymmetry);
		  
		  compositeTransitivity = new Composite (bar, SWT.BORDER);
		  layout = new GridLayout (2, true);
		  layout.marginLeft = layout.marginTop= layout.marginRight=layout.marginBottom=8;
		  layout.verticalSpacing = 10;
		  layout.horizontalSpacing = 10;
		  compositeTransitivity.setLayout(layout);
		  compositeTransitivity.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		  
		  checkTransitive = new Button(compositeTransitivity, SWT.CHECK);
		  checkTransitive.setText("Transitive");
		  checkTransitive.setBackground(checkTransitive.getParent().getBackground());
		  checkTransitive.addSelectionListener(listenerCheckboxes);
		  
		  checkCoTransitive = new Button(compositeTransitivity, SWT.CHECK);
		  checkCoTransitive.setText("Co-Transitive");
		  checkCoTransitive.setBackground(checkCoTransitive.getParent().getBackground());
		  checkCoTransitive.addSelectionListener(listenerCheckboxes);
		  
		  checkOpTransitive = new Button(compositeTransitivity, SWT.CHECK);
		  checkOpTransitive.setText("Op-Transitive");
		  checkOpTransitive.setBackground(checkOpTransitive.getParent().getBackground());
		  checkOpTransitive.addSelectionListener(listenerCheckboxes);
		  
		  checkAntiTransitive = new Button(compositeTransitivity, SWT.CHECK);
		  checkAntiTransitive.setText("Anti-Transitive");
		  checkAntiTransitive.setBackground(checkAntiTransitive.getParent().getBackground());
		  checkAntiTransitive.addSelectionListener(listenerCheckboxes);
		  
		  checkNonTransitive = new Button(compositeTransitivity, SWT.CHECK);
		  checkNonTransitive.setText("Non-Transitive");
		  checkNonTransitive.setBackground(checkNonTransitive.getParent().getBackground());
		  checkNonTransitive.addSelectionListener(listenerCheckboxes);
		  
		  checkNegativelyTransitive = new Button(compositeTransitivity, SWT.CHECK);
		  checkNegativelyTransitive.setText("Negatively Transitive");
		  checkNegativelyTransitive.setBackground(checkNegativelyTransitive.getParent().getBackground());
		  checkNegativelyTransitive.addSelectionListener(listenerCheckboxes);
		  
		  checkSemiTransitive = new Button(compositeTransitivity, SWT.CHECK);
		  checkSemiTransitive.setText("Semi-Transitive");
		  checkSemiTransitive.setBackground(checkSemiTransitive.getParent().getBackground());
		  checkSemiTransitive.addSelectionListener(listenerCheckboxes);
		  
		  checkQuasiTransitive = new Button(compositeTransitivity, SWT.CHECK);
		  checkQuasiTransitive.setText("Quasi-Transitive");
		  checkQuasiTransitive.setBackground(checkQuasiTransitive.getParent().getBackground());
		  checkQuasiTransitive.addSelectionListener(listenerCheckboxes);
		  
		  itemTransitivity = new ExpandItem (bar, SWT.NONE, 2);
		  itemTransitivity.setText("Transitivity");
		  itemTransitivity.setHeight(compositeTransitivity.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		  itemTransitivity.setControl(compositeTransitivity);  
		  
		  compositeCyclicity = new Composite (bar, SWT.BORDER);
		  layout = new GridLayout (2, true);
		  layout.marginLeft = layout.marginTop= layout.marginRight=layout.marginBottom=8;
		  layout.verticalSpacing = 10;
		  layout.horizontalSpacing = 10;
		  compositeCyclicity.setLayout(layout);
		  compositeCyclicity.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		  
		  checkCyclic = new Button(compositeCyclicity, SWT.CHECK);
		  checkCyclic.setText("Cyclic");
		  checkCyclic.setBackground(checkCyclic.getParent().getBackground());
		  checkCyclic.addSelectionListener(listenerCheckboxes);
		  
		  checkAcyclic = new Button(compositeCyclicity, SWT.CHECK);
		  checkAcyclic.setText("Acyclic");
		  checkAcyclic.setBackground(checkAcyclic.getParent().getBackground());
		  checkAcyclic.addSelectionListener(listenerCheckboxes);
		  
		  itemCyclicity = new ExpandItem (bar, SWT.NONE, 3);
		  itemCyclicity.setText("Cyclicity");
		  itemCyclicity.setHeight(compositeCyclicity.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		  itemCyclicity.setControl(compositeCyclicity);  
		  
		  compositeOthers = new Composite (bar, SWT.BORDER);
		  layout = new GridLayout (2, true);
		  layout.marginLeft = layout.marginTop= layout.marginRight=layout.marginBottom=8;
		  layout.verticalSpacing = 10;
		  layout.horizontalSpacing = 10;
		  compositeOthers.setLayout(layout);
		  compositeOthers.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		  
		  checkEuclidean = new Button(compositeOthers, SWT.CHECK);
		  checkEuclidean.setText("(Right) Euclidean");
		  checkEuclidean.setBackground(checkEuclidean.getParent().getBackground());
		  checkEuclidean.addSelectionListener(listenerCheckboxes);
		  
		  checkWeakConnected = new Button(compositeOthers, SWT.CHECK);
		  checkWeakConnected.setText("Weak Connected (Tight)");
		  checkWeakConnected.setBackground(checkWeakConnected.getParent().getBackground());
		  checkWeakConnected.addSelectionListener(listenerCheckboxes);
		  
		  checkLeftEuclidean = new Button(compositeOthers, SWT.CHECK);
		  checkLeftEuclidean.setText("Left Euclidean");
		  checkLeftEuclidean.setBackground(checkLeftEuclidean.getParent().getBackground());
		  checkLeftEuclidean.addSelectionListener(listenerCheckboxes);
		  
		  checkStrongConnected = new Button(compositeOthers, SWT.CHECK);
		  checkStrongConnected.setText("Strong Connected");
		  checkStrongConnected.setBackground(checkStrongConnected.getParent().getBackground());
		  checkStrongConnected.addSelectionListener(listenerCheckboxes);
		  
		  checkWeakComplete = new Button(compositeOthers, SWT.CHECK);
		  checkWeakComplete.setText("Weak Complete");
		  checkWeakComplete.setBackground(checkWeakComplete.getParent().getBackground());
		  checkWeakComplete.addSelectionListener(listenerCheckboxes);
		  
		  checkTrichotomous = new Button(compositeOthers, SWT.CHECK);
		  checkTrichotomous.setText("Thrichotomous");
		  checkTrichotomous.setBackground(checkTrichotomous.getParent().getBackground());
		  checkTrichotomous.addSelectionListener(listenerCheckboxes);
		  
		  checkStrongComplete = new Button(compositeOthers, SWT.CHECK);
		  checkStrongComplete.setText("Strong Complete (Total)");
		  checkStrongComplete.setBackground(checkStrongComplete.getParent().getBackground());
		  checkStrongComplete.addSelectionListener(listenerCheckboxes);
		  
		  checkFerrers = new Button(compositeOthers, SWT.CHECK);
		  checkFerrers.setText("Ferrers");
		  checkFerrers.setBackground(checkFerrers.getParent().getBackground());
		  checkFerrers.addSelectionListener(listenerCheckboxes);
		  
		  itemOthers = new ExpandItem (bar, SWT.NONE, 4);
		  itemOthers.setText("Others");
		  itemOthers.setHeight(compositeOthers.computeSize(SWT.DEFAULT, SWT.DEFAULT).y);
		  itemOthers.setControl(compositeOthers);
	
		list = new List(container, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		list.setItems(new String[] {"Reflexive: ∀ x ∈ X. R(x,x)", 
				  "Co-Reflexive: ∀ x, y ∈ X. R(x,y) ⟶ x = y", 
				  "Shift-Reflexive: ∀ x, y ∈ X. R(x,y) ⟶ R(y,y)", 
				  "Quasi-Reflexive: ∀ x, y ∈ X. R(x,y) ∨ R(y,x) ⟶ R(y,y)", 
				  "Anti-Reflexive: ∀ x ∈ X. ¬R(x,x)", 
				  "Non-Reflexive: ¬(Reflexive)", 
				  "", 
				  "Symmetric: ∀ x,y∈X,R(x,y)⟶R(y,x)", 
				  "Non-Symmetric: ¬(symmetric)", 
				  "Anti-Symmetric: ∀ x,y∈X,R(x,y)  ∧ R(y,x)  ⟶x=y", 
				  "Asymmetric: ∀ x,y∈X,R(x,y)⟶¬R(y,x)", 
				  "", 
				  "Transitive: ∀ x, y, z ∈ X. R(x,y) ∧ R(y,z) ⟶ R(x,z)", 
				  "Co-Transitive: ∀ x, y, z ∈ X. R(x,z) ⟶ R(x,y) ∨ R(y,z)", 
				  "Op-Transitive: ∀ x, z ∈ X. R(x,z) ⟶ ∃ y ∈ X. R(x,y)∧R(y,z)", 
				  "Anti-Transitive: ∀ x, y, z ∈ X. R(x,y) ∧ R(y,z) ⟶ ¬R(x,z)", 
				  "Non-Transitive: ¬(transitive)", 
				  "Negatively Transitive: ∀ x, y, z ∈ X. ¬R(x,y) ∧ ¬R(y,z) ⟶ ¬R(x,z)", 
				  "Semi-Transitive: ∀ w, x, y, z ∈ X. ( R(w,x) ∧ R(x,y) ) ⟶ ( R(w,z) ∨ R(z,y) )", 
				  "Quasi-Transitive: ∀ x, y, z ∈X. R(x,y) ∧ R(y,z) ∧ ¬R(y,x) ∧ ¬R(z,y) ⟶ R(x,z) ∧ ¬R(z,x)", 
				  "", 
				  "Acyclic: ∀ x_1,x_2,…,x_n∈X.R(x_1,x_2 )∧ R(x_1,x_3 )… R(x_(n-1),x_n )⟶¬R(x_n,x_1 )", 
				  "Cyclic:  ¬(Acyclic)", 
				  "", 
				  "(Right) Euclidean: ∀ x, y, z ∈ X. R(x,y) ∧ R(x,z) ⟶ R(y,z) ∧ R(z,y)", 
				  "Left Euclidean: ∀ x, y, z ∈ X. R(y,x) ∧ R(z,x) ⟶ R(y,z) ∧ R(z,y)", 
				  "Weak Complete: ∀ x, y ∈ X. x ≠ y → R(x,y) ∨ R(y,x)", 
				  "Strong Complete (Total): ∀ x, y ∈ X. R(x,y) ∨ R(y,x)", 
				  "Weak Connected (Tight): ∀ x, y ∈ X. ¬( R(x,y) ∨ R(y,x) ) ⟶ x = y ", 
				  "Strong Connected: ∀ x, y ∈ X. x ≠ y ⟶ ( R(x,y) ∨ R(y,x) )", 
				  "Trichotomous: ∀ x, y ∈ X. R(x,y) ∨ R(y,x) ∨ x = y", 
				  "Ferrers: ∀ w, x, y, z ∈ X. ( R(w,x) ∧ R(y,z) ) ⟶ ( R(w,z) ∨ R(x,y) ) "});
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(label)
				.add(label_1)
				.add(label_2)
				.add(label_3)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(lblIsDisjoint, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
							.add(10)
							.add(comboDisjoint, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
						.add(gl_container.createSequentialGroup()
							.add(lblNewStereotype)
							.add(10)
							.add(comboStereotype, GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE))
						.add(lblimpossibleCombination, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
						.add(bar, GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
					.add(17)
					.add(list, GroupLayout.DEFAULT_SIZE, 377, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblChoose, GroupLayout.DEFAULT_SIZE, 753, Short.MAX_VALUE)
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(label, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.add(label_1, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.add(label_2, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE)
						.add(label_3, GroupLayout.PREFERRED_SIZE, 0, GroupLayout.PREFERRED_SIZE))
					.add(10)
					.add(lblChoose)
					.add(16)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(lblIsDisjoint, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.add(comboDisjoint, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.add(6)
							.add(gl_container.createParallelGroup(GroupLayout.LEADING)
								.add(lblNewStereotype, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
								.add(comboStereotype, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.add(7)
							.add(lblimpossibleCombination, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(bar, GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE))
						.add(list, GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE))
					.add(4))
		);
		container.setLayout(gl_container);
		
		setPageComplete(false);
		
	}
		
	public BinOverWizard getBinOverWizard(){
		return (BinOverWizard)getWizard();
	}
	
	

	@Override
	public IWizardPage getNextPage(){
		
		getBinOverWizard().removeAllActions();
		BinOverAction action;
		
		if(comboDisjoint.getSelectionIndex()==0){
			action = new BinOverAction(binOver);
			action.setDisjointness();
			getBinOverWizard().addAction(0, action);
		}
		else {
			for (BinaryPropertyValue property : getSelectedProperties()) {
				action = new BinOverAction(binOver);
				action.setBinaryProperty(property);
				getBinOverWizard().addAction(0, action);
			}
		}
		return getBinOverWizard().getFinishing();
	}
}
