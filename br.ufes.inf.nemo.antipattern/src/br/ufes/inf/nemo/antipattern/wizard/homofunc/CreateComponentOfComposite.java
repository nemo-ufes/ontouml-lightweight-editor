package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

import RefOntoUML.AntiRigidSortalClass;
import RefOntoUML.Classifier;
import RefOntoUML.Kind;
import RefOntoUML.MixinClass;
import RefOntoUML.SubKind;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;

public class CreateComponentOfComposite extends Composite {

	public HomoFuncOccurrence homoFunc;
	public ArrayList<RelationElement> relations = new ArrayList<RelationElement>();
	private ArrayList<Classifier> typeList;
	
	private Text componentOfNameField;
	private Combo typeCombo;
	private Button btnIsShareable;
	private Button btnIsEssential;
	private Button btnIsInseparable;
	private Button btnIsImmutablePart;
	private Button btnIsImmutableWhole;
	private Label lblType;
	private Label lblComponentofName;
	private Button newButton;
	private Button deleteButton;	
	private List relationList;
	private boolean isSubPart;
	private boolean canEnable;
	
	public CreateComponentOfComposite(Composite parent, int style, HomoFuncOccurrence homoFunc, boolean isSubPart) 
	{
		super(parent, style);
		setSize(new Point(540, 149));
		this.homoFunc=homoFunc;
		this.isSubPart = isSubPart;
		createPartControl();
	}

	public void createPartControl() 
	{
		
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		lblType = new Label(this, SWT.NONE);

		if(isSubPart)
			lblType.setText("Subtype:");
		else
			lblType.setText("Type:");
		
		lblType.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		lblComponentofName = new Label(this, SWT.NONE);
		lblComponentofName.setText("ComponentOf name:");
		lblComponentofName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		typeCombo = new Combo(this, SWT.READ_ONLY);
		
		if(isSubPart){
			typeList = new ArrayList<Classifier>(homoFunc.getParser().getAllChildren(homoFunc.getPart()));
		}
		else{
			typeList = new ArrayList<Classifier>(homoFunc.getParser().getAllInstances(Kind.class));
			typeList.addAll(homoFunc.getParser().getAllInstances(SubKind.class));
			typeList.addAll(homoFunc.getParser().getAllInstances(AntiRigidSortalClass.class));
			typeList.addAll(homoFunc.getParser().getAllInstances(MixinClass.class));
			
//			while(it.hasNext()) {
//				Classifier type = it.next();
//				if(!homoFunc.getParser().isFunctionalComplex(type) || 
//						type.equals(homoFunc.getWhole()) || homoFunc.getWhole().allChildren().contains(type) || homoFunc.getWhole().allParents().contains(type) ||
//						type.equals(homoFunc.getPart()) || homoFunc.getPart().allChildren().contains(type) || homoFunc.getPart().allParents().contains(type))
//					it.remove();
//			}
		}
		
		for(Classifier child : typeList)
			typeCombo.add(OntoUMLNameHelper.getNameAndType(child, true, false));
		
		componentOfNameField = new Text(this, SWT.BORDER);
		
		btnIsShareable = new Button(this, SWT.CHECK);
		btnIsShareable.setText("isShareable");
		btnIsShareable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		btnIsEssential = new Button(this, SWT.CHECK);
		btnIsEssential.setText("isEssential");
		btnIsEssential.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		btnIsInseparable = new Button(this, SWT.CHECK);
		btnIsInseparable.setText("isInseparable");
		btnIsInseparable.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		btnIsImmutablePart = new Button(this, SWT.CHECK);
		btnIsImmutablePart.setText("isImmutablePart");
		btnIsImmutablePart.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		btnIsImmutableWhole = new Button(this, SWT.CHECK);		
		btnIsImmutableWhole.setText("isImmutableWhole");
		btnIsImmutableWhole.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		newButton = new Button(this, SWT.NONE);
		newButton.setText("New");
		newButton.addSelectionListener(newAction);
		
		deleteButton = new Button(this, SWT.NONE);
		deleteButton.setText("Delete");
		deleteButton.addSelectionListener(deleteAction);
		
		relationList = new List(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(10)
							.add(lblType, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(typeCombo, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.add(6)
							.add(btnIsEssential, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.add(groupLayout.createSequentialGroup()
							.add(10)
							.add(lblComponentofName, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(componentOfNameField, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.add(6)
							.add(btnIsImmutablePart, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
						.add(groupLayout.createSequentialGroup()
							.add(43)
							.add(newButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.add(3)
							.add(deleteButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(relationList, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.add(6)
							.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
								.add(btnIsInseparable, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.add(btnIsImmutableWhole, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
								.add(btnIsShareable, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))))
					.add(18))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(3)
							.add(lblType, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.add(typeCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(groupLayout.createSequentialGroup()
							.add(2)
							.add(btnIsEssential)))
					.add(5)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(1)
							.add(lblComponentofName, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.add(componentOfNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(btnIsImmutablePart))
					.add(5)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(1)
							.add(newButton))
						.add(groupLayout.createSequentialGroup()
							.add(1)
							.add(deleteButton))
						.add(groupLayout.createSequentialGroup()
							.add(1)
							.add(relationList, GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE))
						.add(groupLayout.createSequentialGroup()
							.add(btnIsInseparable)
							.add(13)
							.add(btnIsImmutableWhole)
							.add(6)
							.add(btnIsShareable, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.add(11))
		);
		setLayout(groupLayout);
		
		canEnable = true;
		
		if(typeList.size()==0){
			enableAll(false);
			canEnable=false;
		}
	}
	
	private SelectionAdapter deleteAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			int index = relationList.getSelectionIndex();
			
			if(index!=-1){
				relationList.remove(index);
				relations.remove(index);
			}
		}
	};
	
	private SelectionAdapter newAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			
			if (getType() !=null){
				RelationElement newrelation = new RelationElement(getType(), getComponentOfName(), isShareable(), isEssential(), isImmutablePart(), isImmutableWhole(), isInseparable());
				relations.add(newrelation);
				relationList.add(newrelation.toString());
				
				clearAll();
			}
		}
	};
	
	public ArrayList<RelationElement> getRelations()
	{
		return relations;
	}
	
	public String getComponentOfName()
	{
		return componentOfNameField.getText();
	}
	
	public Classifier getType()
	{
		if(typeCombo.getSelectionIndex()==-1)
			return null;
		
		return typeList.get(typeCombo.getSelectionIndex());
	}
	 
	public boolean isShareable()
	{
		return btnIsShareable.getSelection();
	}
	
	public boolean isEssential()
	{
		return btnIsEssential.getSelection();
	}
	
	public boolean isInseparable()
	{
		return btnIsInseparable.getSelection();
	}
	
	public boolean isImmutablePart()
	{
		return btnIsImmutablePart.getSelection();
	}
	
	public boolean isImmutableWhole(){
		return btnIsImmutableWhole.getSelection();
	}
	
	public boolean canEnable(){
		return canEnable;
	}
	
	protected void clearAll() {
		typeCombo.select(-1);
		componentOfNameField.setText("");
		btnIsShareable.setSelection(false);
		btnIsEssential.setSelection(false);	
		btnIsInseparable.setSelection(false);
		btnIsImmutablePart.setSelection(false);
		btnIsImmutableWhole.setSelection(false);
	}
	
	public void enableAll(boolean value)
	{
		if(!canEnable)
			return;
		
		lblType.setEnabled(value);		
		lblComponentofName.setEnabled(value);		
		typeCombo.setEnabled(value);		
		componentOfNameField.setEnabled(value);		
		btnIsShareable.setEnabled(value);		
		btnIsEssential.setEnabled(value);		
		btnIsInseparable.setEnabled(value);		
		btnIsImmutablePart.setEnabled(value);		
		btnIsImmutableWhole.setEnabled(value);
		newButton.setEnabled(value);
		deleteButton.setEnabled(value);
		relationList.setEnabled(value);
	}
	
	public void enableCreation(boolean value)
	{
		enableAll(value);
		newButton.setEnabled(value);
		deleteButton.setEnabled(value);
		relationList.setEnabled(value);
		setVisible(value);
	}
}
