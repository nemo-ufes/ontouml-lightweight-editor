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
		typeNameText.setBounds(80, 7, 96, 21);
		
		lblName = new Label(this, SWT.NONE);
		lblName.setText("(Name)");
		lblName.setEnabled(false);
		lblName.setAlignment(SWT.RIGHT);
		lblName.setBounds(10, 10, 64, 15);
		
		lblStereo = new Label(this, SWT.NONE);
		lblStereo.setText("(Stereotype)");
		lblStereo.setEnabled(false);
		lblStereo.setBounds(10, 31, 64, 15);
		
		stereoCombo = new Combo(this, SWT.READ_ONLY);
		stereoCombo.setItems(new String[] {"Kind", "SubKind", "Quantity", "Collective", "Role", "Phase", "Category", "RoleMixin", "Mixin", "Relator", "Mode", "DataType"});
		stereoCombo.setEnabled(false);
		stereoCombo.setBounds(80, 34, 96, 23);
		stereoCombo.select(0);
		
		typeList = new List(this, SWT.BORDER | SWT.V_SCROLL);
		typeList.setEnabled(false);
		typeList.setBounds(182, 6, 213, 51);
		
		btnCreate = new Button(this, SWT.NONE);
		btnCreate.setText("Create");
		btnCreate.setEnabled(false);
		btnCreate.setBounds(401, 5, 51, 25);
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
		btnDelete.setBounds(401, 31, 51, 25);
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
		lblMediated.setBounds(458, 10, 96, 15);

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
