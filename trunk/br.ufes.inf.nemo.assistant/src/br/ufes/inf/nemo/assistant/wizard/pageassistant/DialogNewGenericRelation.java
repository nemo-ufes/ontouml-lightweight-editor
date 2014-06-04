package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;


public class DialogNewGenericRelation extends TitleAreaDialog{

	public DialogNewGenericRelation(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Create a association between classes");
	}

	@Override
	protected boolean isResizable() {
		return false;
	}

	private String[] targetClasses = null;
	public void setTargetClasses(String[] targetClasses) {
		this.targetClasses = targetClasses;
	}

	private Text TrelationName;
	private Text TsrcMinCard;
	private Text TsrcMaxCard;
	private Text TtrgMinCard;
	private Text TtrgMaxCard;
	private Combo cbClasses;

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		GridData gd_container = new GridData(SWT.LEFT, SWT.CENTER, false, false, 1, 1);
		gd_container.heightHint = 152;
		gd_container.widthHint = 450;
		container.setLayoutData(gd_container);

		Label srcClsNameMinCard;
		Label srcClsName;
		Label relationStereotype;

		srcClsName = new Label(container, SWT.NONE);
		srcClsName.setBounds(185, 10, 359, 15);

		srcClsNameMinCard = new Label(container, SWT.NONE);
		srcClsNameMinCard.setBounds(10, 51, 189, 15);
		srcClsNameMinCard.setText("<className>");

		Label lblMin = new Label(container, SWT.NONE);
		lblMin.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		lblMin.setBounds(20, 73, 21, 15);
		lblMin.setText("min");

		Label lblMax = new Label(container, SWT.NONE);
		lblMax.setText("max");
		lblMax.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		lblMax.setBounds(58, 73, 25, 15);

		TsrcMinCard = new Text(container, SWT.BORDER | SWT.CENTER);
		TsrcMinCard.setText("1");
		TsrcMinCard.setBounds(20, 90, 21, 21);

		TsrcMaxCard = new Text(container, SWT.BORDER | SWT.CENTER);
		TsrcMaxCard.setText("1");
		TsrcMaxCard.setBounds(60, 90, 21, 21);

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		label_2.setBounds(46, 92, 8, 15);
		label_2.setText("...");

		Label lblTargetClass = new Label(container, SWT.NONE);
		lblTargetClass.setBounds(326, 27, 100, 15);
		lblTargetClass.setText("Target Class");

		cbClasses = new Combo(container, SWT.READ_ONLY);
		cbClasses.setBounds(274, 48, 169, 23);
		if(targetClasses != null){
			cbClasses.setItems(targetClasses);
		}

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("min");
		label_1.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		label_1.setBounds(326, 73, 21, 15);

		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("max");
		label_4.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		label_4.setBounds(364, 73, 25, 15);

		TtrgMinCard = new Text(container, SWT.BORDER | SWT.CENTER);
		TtrgMinCard.setText("1");
		TtrgMinCard.setBounds(326, 90, 21, 21);

		TtrgMaxCard = new Text(container, SWT.BORDER | SWT.CENTER);
		TtrgMaxCard.setText("1");
		TtrgMaxCard.setBounds(366, 90, 21, 21);

		Label label_5 = new Label(container, SWT.NONE);
		label_5.setText("...");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		label_5.setBounds(352, 92, 8, 15);

		Label label_3 = new Label(container, SWT.CENTER);
		label_3.setBounds(102, 86, 180, 15);
		label_3.setText("------------------------------");

		relationStereotype = new Label(container, SWT.CENTER);
		relationStereotype.setBounds(105, 73, 176, 15);
		relationStereotype.setText("<relationStereotype>");

		TrelationName = new Text(container, SWT.BORDER);
		TrelationName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				addRelation.setEnabled(!TrelationName.getText().isEmpty());
			}
		});
		TrelationName.setBounds(105, 103, 176, 21);

		return area;
	}

	private Button addRelation;
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		addRelation = createButton(parent, IDialogConstants.OK_ID, "Add Relation", true);
		addRelation.setEnabled(false);
		createButton(parent, IDialogConstants.CANCEL_ID,IDialogConstants.CANCEL_LABEL, false);
	}

	@Override
	protected void okPressed() {
		//getting info from edits
		srcMinCard = Integer.parseInt(TsrcMinCard.getText());
		if(TsrcMaxCard.equals("*"))
			srcMaxCard = -1;
		else
			srcMaxCard = Integer.parseInt(TsrcMaxCard.getText());

		trgMinCard = Integer.parseInt(TtrgMinCard.getText());
		if(TtrgMaxCard.equals("*"))
			trgMaxCard = -1;
		else
			trgMaxCard = Integer.parseInt(TtrgMaxCard.getText());

		relationName = TrelationName.getText();
		targetClass  = cbClasses.getItem(cbClasses.getSelectionIndex());
		super.okPressed();
	}

	public String relationName = new String();
	public String targetClass = new String();
	public int srcMinCard;
	public int srcMaxCard;
	public int trgMinCard;
	public int trgMaxCard;

	public String getRelationName() {
		return relationName;
	}

	public String getTargetClass() {
		return targetClass;
	}

	public int getSrcMinCard() {
		return srcMinCard;
	}

	public int getSrcMaxCard() {
		return srcMaxCard;
	}

	public int getTrgMinCard() {
		return trgMinCard;
	}

	public int getTrgMaxCard() {
		return trgMaxCard;
	}
}
