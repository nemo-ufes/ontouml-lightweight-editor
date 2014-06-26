package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.HashMap;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;

public class CreateMediatedComposite extends Composite {
	private Text typeNameText;
	private Label lblName;
	private Label lblStereo;
	private Combo stereoCombo;
	private List typeList;
	private Button btnCreate;
	private Button btnDelete;
	private Label lblMediated;
	private HashMap<String,String> newMediatedTypeMap = new HashMap<String,String>();;
	
	public HashMap<String,String> getMap(){
		return newMediatedTypeMap;
	}
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public CreateMediatedComposite(Composite parent, int style) {
		super(parent, style);
		
		typeNameText = new Text(this, SWT.BORDER);
		typeNameText.setEnabled(false);
		
		lblName = new Label(this, SWT.NONE);
		lblName.setText("Name:");
		lblName.setEnabled(false);
		
		lblStereo = new Label(this, SWT.NONE);
		lblStereo.setText("Stereotype:");
		lblStereo.setEnabled(false);
		
		stereoCombo = new Combo(this, SWT.READ_ONLY);
		stereoCombo.setItems(new String[] {"Kind", "SubKind", "Quantity", "Collective", "Role", "Phase", "Category", "RoleMixin", "Mixin", "Relator", "Mode", "DataType"});
		stereoCombo.setEnabled(false);
		stereoCombo.select(0);
		
		typeList = new List(this, SWT.BORDER | SWT.V_SCROLL);
		typeList.setEnabled(false);
		
		btnCreate = new Button(this, SWT.NONE);
		btnCreate.setText("Create");
		btnCreate.setEnabled(false);
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {				
				newMediatedTypeMap.put(typeNameText.getText(),stereoCombo.getItem(stereoCombo.getSelectionIndex()));
				typeList.add(stereoCombo.getItem(stereoCombo.getSelectionIndex())+" "+typeNameText.getText());
			}
		});
		
		btnDelete = new Button(this, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setEnabled(false);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				String str = typeList.getItem(typeList.getSelectionIndex());
				String name = str.substring(str.indexOf(" ")+1);				
				newMediatedTypeMap.remove(name);
				typeList.remove(typeList.getSelectionIndex());			
			}
		});
		
		lblMediated = new Label(this, SWT.NONE);
		lblMediated.setText("(mediated type)");
		lblMediated.setEnabled(false);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(10)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING, false)
						.add(lblStereo, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.add(lblName, GroupLayout.DEFAULT_SIZE, 64, Short.MAX_VALUE))
					.add(6)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(typeNameText, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
						.add(stereoCombo, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE))
					.add(6)
					.add(typeList, GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
					.add(6)
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(btnCreate, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
						.add(btnDelete, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE))
					.add(6)
					.add(lblMediated, GroupLayout.PREFERRED_SIZE, 96, GroupLayout.PREFERRED_SIZE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(GroupLayout.LEADING)
				.add(groupLayout.createSequentialGroup()
					.add(groupLayout.createParallelGroup(GroupLayout.LEADING)
						.add(groupLayout.createSequentialGroup()
							.add(10)
							.add(lblName)
							.add(12)
							.add(lblStereo))
						.add(groupLayout.createSequentialGroup()
							.add(7)
							.add(typeNameText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.add(6)
							.add(stereoCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.add(groupLayout.createSequentialGroup()
							.add(6)
							.add(typeList, GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE))
						.add(groupLayout.createSequentialGroup()
							.add(5)
							.add(btnCreate)
							.add(1)
							.add(btnDelete))
						.add(groupLayout.createSequentialGroup()
							.add(10)
							.add(lblMediated)))
					.add(8))
		);
		setLayout(groupLayout);

	}

	public void enable(boolean value)
	{
	  typeNameText.setEnabled(value);
	  stereoCombo.setEnabled(value);
	  lblMediated.setEnabled(value);
	  lblName.setEnabled(value);
	  lblStereo.setEnabled(value);	  
	  btnCreate.setEnabled(value);
	  typeList.setEnabled(value);
	  btnDelete.setEnabled(value);		  
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
