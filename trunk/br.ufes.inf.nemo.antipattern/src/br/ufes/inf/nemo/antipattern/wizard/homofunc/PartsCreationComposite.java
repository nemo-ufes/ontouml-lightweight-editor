package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.List;

public class PartsCreationComposite extends Composite {
	
	private Text partNameText;
	private Text relationNameText;
	private Label labelPart; 
	private Combo partStereoCombo;
	private Label labelName;
	private Label labelComponentOf;
	private Button btnIsImmutableWhole;
	private Button btnIsImmutablePart;
	private Button btnIsInseparable;
	private Button btnIsEssential;
	private Button btnIsShareable;
	private Label separator;
	private List typeList;		
	private Button btnDelete; 
	private Button btnCreate;
	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public PartsCreationComposite(Composite parent, int style) {
		super(parent, style);
		
		labelPart = new Label(this, SWT.NONE);
		labelPart.setText("Part stereotype:");
		labelPart.setBounds(10, 10, 188, 21);
		
		partStereoCombo = new Combo(this, SWT.NONE);
		partStereoCombo.setBounds(10, 34, 190, 23);
		
		partNameText = new Text(this, SWT.BORDER);
		partNameText.setBounds(204, 34, 215, 21);
		
		labelName = new Label(this, SWT.NONE);
		labelName.setText("Part name:");
		labelName.setBounds(204, 10, 215, 21);
		
		labelComponentOf = new Label(this, SWT.NONE);
		labelComponentOf.setText("ComponentOf Name:");
		labelComponentOf.setBounds(425, 10, 139, 21);
		
		relationNameText = new Text(this, SWT.BORDER);
		relationNameText.setBounds(425, 34, 139, 21);
		
		btnIsImmutableWhole = new Button(this, SWT.CHECK);
		btnIsImmutableWhole.setText("isImmutableWhole");
		btnIsImmutableWhole.setBounds(425, 63, 139, 16);
		
		btnIsImmutablePart = new Button(this, SWT.CHECK);
		btnIsImmutablePart.setText("isImmutablePart");
		btnIsImmutablePart.setBounds(303, 63, 116, 16);
		
		btnIsInseparable = new Button(this, SWT.CHECK);
		btnIsInseparable.setText("isInseparable");
		btnIsInseparable.setBounds(204, 63, 93, 16);
		
		btnIsEssential = new Button(this, SWT.CHECK);
		btnIsEssential.setText("isEssential");
		btnIsEssential.setBounds(107, 63, 93, 16);
		
		btnIsShareable = new Button(this, SWT.CHECK);
		btnIsShareable.setText("isShareable");
		btnIsShareable.setBounds(10, 63, 93, 16);
		
		separator = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		separator.setBounds(10, 85, 554, 7);
		
		typeList = new List(this, SWT.BORDER | SWT.V_SCROLL);
		typeList.setBounds(10, 98, 473, 68);
		
		btnCreate = new Button(this, SWT.NONE);
		btnCreate.setText("Create");
		btnCreate.setBounds(489, 97, 75, 25);
		btnCreate.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {

			}
		});
		
		btnDelete = new Button(this, SWT.NONE);
		btnDelete.setText("Delete");
		btnDelete.setBounds(489, 128, 75, 25);
		btnDelete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent arg0) {
			
			}
		});
	}

	public void enableAll(boolean value){
		partNameText.setEnabled(value);
		relationNameText.setEnabled(value);
		labelPart.setEnabled(value); 
		partStereoCombo.setEnabled(value);
		labelName.setEnabled(value);
		labelComponentOf.setEnabled(value);
		btnIsImmutableWhole.setEnabled(value);
		btnIsImmutablePart.setEnabled(value);
		btnIsInseparable.setEnabled(value);
		btnIsEssential.setEnabled(value);
		btnIsShareable.setEnabled(value);
		separator.setEnabled(value);
		typeList.setEnabled(value);		
		btnDelete.setEnabled(value); 
		btnCreate.setEnabled(value);
	}
	
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}

}
