package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.MessageDialog;
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

import RefOntoUML.Category;
import RefOntoUML.Kind;
import RefOntoUML.Mixin;
import RefOntoUML.Phase;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import br.ufes.inf.nemo.antipattern.homofunc.HomoFuncOccurrence;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class CreatePartComposite extends Composite {

	public HomoFuncOccurrence homoFunc;
	public boolean isSubpart;
	
	private Text partNameField;
	private Text componentOfNameField;
	private Combo stereoCombo;
	private Button btnIsShareable;
	private Button btnIsEssential;
	private Button btnIsInseparable;
	private Button btnIsImmutablePart;
	private Button btnIsImmutableWhole;
	private Label label;
	private Label label_1;
	private Label lblComponentofName;
	private Button btnCreateNewPart;
	private Button deleteButton;	
	private List partsList;
	public ArrayList<PartElement> parts = new ArrayList<PartElement>();
	
	public CreatePartComposite(Composite parent, int style, HomoFuncOccurrence homoFunc, boolean isSubpart) 
	{
		super(parent, style);
		this.isSubpart=isSubpart;
		setSize(new Point(538, 165));
		this.homoFunc=homoFunc;
		createPartControl();
	}
			
	public void createPartControl() 
	{
		setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		label = new Label(this, SWT.NONE);
		if(!isSubpart)
			label.setText("Part stereotype:");
		else
			label.setText("Subtype stereotype:");
		label.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		label_1 = new Label(this, SWT.NONE);
		if(!isSubpart)
			label_1.setText("Part name:");
		else
			label_1.setText("Subtype name:");
		label_1.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		lblComponentofName = new Label(this, SWT.NONE);
		lblComponentofName.setText("ComponentOf name:");
		lblComponentofName.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		stereoCombo = new Combo(this, SWT.READ_ONLY);
		if(!isSubpart){
			stereoCombo.setItems(new String[] {"Kind", "SubKind", "Role", "Phase", "Mixin", "RoleMixin", "Category"});
		}
		else {
			if(homoFunc.getPartEnd().getType() instanceof Kind || homoFunc.getPartEnd().getType() instanceof SubKind){
				stereoCombo.setItems(new String[] {"SubKind", "Role", "Phase"});
			}else if (homoFunc.getPartEnd().getType() instanceof Role){
				stereoCombo.setItems(new String[] {"Role"});
			}else if (homoFunc.getPartEnd().getType() instanceof Phase){
				stereoCombo.setItems(new String[] {"Role","Phase"});				
			}else if (homoFunc.getPartEnd().getType() instanceof Category){
				stereoCombo.setItems(new String[] {"Category","RoleMixin","Kind"});
			}else if (homoFunc.getPartEnd().getType() instanceof Mixin){
				stereoCombo.setItems(new String[] {"Category","RoleMixin","Mixin","Kind"});
			}else if (homoFunc.getPartEnd().getType() instanceof RoleMixin){
				stereoCombo.setItems(new String[] {"RoleMixin"});
			}else{
				stereoCombo.setItems(new String[] {"Kind", "Collective", "Quantity", "SubKind", "Role", "Phase", "Mixin", "RoleMixin", "Category", "Relator", "Mode", "DataType"});
			}
		}
		
		partNameField = new Text(this, SWT.BORDER);
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
		
		btnCreateNewPart = new Button(this, SWT.NONE);
		btnCreateNewPart.addSelectionListener(newAction);
		btnCreateNewPart.setText("New");
		
		deleteButton = new Button(this, SWT.NONE);
		deleteButton.setText("Delete");
		deleteButton.addSelectionListener(deleteAction);
		
		partsList = new List(this, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
		
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(label, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(stereoCombo, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.add(6)
					.add(btnIsEssential, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.add(16))
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(label_1, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(partNameField, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.add(6)
					.add(btnIsImmutablePart, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.add(16))
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(lblComponentofName, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(componentOfNameField, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.add(6)
					.add(btnIsInseparable, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.add(16))
				.add(groupLayout.createSequentialGroup()
					.add(43)
					.add(btnCreateNewPart, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.add(3)
					.add(deleteButton, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(partsList, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
					.add(6)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(btnIsImmutableWhole, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
						.add(btnIsShareable, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE))
					.add(16))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(3)
							.add(label, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.add(stereoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(groupLayout.createSequentialGroup()
							.add(2)
							.add(btnIsEssential)))
					.add(3)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(3)
							.add(label_1, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.add(partNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(groupLayout.createSequentialGroup()
							.add(2)
							.add(btnIsImmutablePart)))
					.add(3)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(3)
							.add(lblComponentofName, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE))
						.add(componentOfNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(groupLayout.createSequentialGroup()
							.add(2)
							.add(btnIsInseparable)))
					.add(6)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(btnCreateNewPart)
						.add(deleteButton)
						.add(partsList, GroupLayout.DEFAULT_SIZE, 60, Short.MAX_VALUE)
						.add(groupLayout.createSequentialGroup()
							.add(2)
							.add(btnIsImmutableWhole)
							.add(6)
							.add(btnIsShareable, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)))
					.add(10))
		);
		setLayout(groupLayout);
		
		
		
	}
	
	private SelectionAdapter newAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {	
			int index = stereoCombo.getSelectionIndex();
			
			if (index!=-1)
			{
				String stereo = stereoCombo.getItem(index);

				//check admissible stereotypes
				if(!isSubpart && !(stereo.compareToIgnoreCase("Kind")==0)) {
					String message = "Plase notice that, by creating a part stereotyped as " + stereo +", your model will be incomplete. Please fix it after you are done analyzing this anti-patern."+"\n\n";						
					MessageDialog.openInformation(getShell(), "WARNING", message);	
				}
				
				PartElement newpart = new PartElement(stereo, getPartName(), getComponentOfName(), 
						isShareable(), isEssential(), isImmutablePart(), isImmutableWhole(), isInseparable());
				parts.add(newpart);
				partsList.add(newpart.toString());				
				
				clearAll();
			}
		}
	};
	
	private SelectionAdapter deleteAction = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent arg0) {
			int index = partsList.getSelectionIndex();
			if(index!=-1){
				parts.remove(index);
				partsList.remove(index);
			}
		}
	};
	
	public String getPartName() {
		return partNameField.getText();
	}
	
	public String getComponentOfName() {
		return componentOfNameField.getText();
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
	
	public ArrayList<PartElement> getParts()
	{
		return parts;
	}
	
	public void enableAll(boolean value)
	{
		label.setEnabled(value);		
		label_1.setEnabled(value);		
		lblComponentofName.setEnabled(value);		
		stereoCombo.setEnabled(value);		
		partNameField.setEnabled(value);		
		componentOfNameField.setEnabled(value);		
		btnIsShareable.setEnabled(value);		
		btnIsEssential.setEnabled(value);		
		btnIsInseparable.setEnabled(value);		
		btnIsImmutablePart.setEnabled(value);		
		btnIsImmutableWhole.setEnabled(value);
		btnCreateNewPart.setEnabled(value);
		deleteButton.setEnabled(value);
		partsList.setEnabled(value);
		
	}
	
	protected void clearAll() {
		stereoCombo.select(-1);		
		partNameField.setText("");		
		componentOfNameField.setText("");		
		btnIsShareable.setSelection(false);		
		btnIsEssential.setSelection(false);
		btnIsInseparable.setSelection(false);		
		btnIsImmutablePart.setSelection(false);		
		btnIsImmutableWhole.setSelection(false);
	}
}
