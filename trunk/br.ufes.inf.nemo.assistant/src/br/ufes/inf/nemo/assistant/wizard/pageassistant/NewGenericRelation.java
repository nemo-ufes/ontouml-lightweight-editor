package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class NewGenericRelation extends WizardPageAssistant{
	private Text srcMinCard;
	private Text srcMaxCard;
	private Text relationName;
	private Text trgMinCard;
	private Text trgMaxCard;

	/**
	 * Create the wizard.
	 */
	public NewGenericRelation() {
		super("Create new generic relation");
		setTitle("Create a new Generic Relation");
		setDescription("NEWGENERICRELATION.description");
	}

	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	private Label srcClsNameMinCard;
	private Label srcClsName;
	private Label relationStereotype;
	private Combo cbClasses;

	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);

		Label lblCreateAAssociation = new Label(container, SWT.NONE);
		lblCreateAAssociation.setBounds(10, 10, 169, 15);
		lblCreateAAssociation.setText("Create a association from class");

		srcClsName = new Label(container, SWT.NONE);
		srcClsName.setBounds(185, 10, 359, 15);
		srcClsName.setText(getCurrentClass());

		srcClsNameMinCard = new Label(container, SWT.NONE);
		srcClsNameMinCard.setBounds(10, 52, 115, 15);
		srcClsNameMinCard.setText("<className>");

		Label lblMin = new Label(container, SWT.NONE);
		lblMin.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		lblMin.setBounds(20, 73, 21, 15);
		lblMin.setText("min");

		Label lblMax = new Label(container, SWT.NONE);
		lblMax.setText("max");
		lblMax.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		lblMax.setBounds(58, 73, 25, 15);

		srcMinCard = new Text(container, SWT.BORDER | SWT.CENTER);
		srcMinCard.setText("1");
		srcMinCard.setBounds(20, 90, 21, 21);

		srcMaxCard = new Text(container, SWT.BORDER | SWT.CENTER);
		srcMaxCard.setText("1");
		srcMaxCard.setBounds(60, 90, 21, 21);

		Label label_2 = new Label(container, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		label_2.setBounds(46, 92, 8, 15);
		label_2.setText("...");

		relationName = new Text(container, SWT.BORDER);
		relationName.addFocusListener(new org.eclipse.swt.events.FocusAdapter() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if(trgMinCard.getText().isEmpty()){
					//					warning.setVisible(true);
					setPageComplete(false);
				}else{
					//					warning.setVisible(false);
					setPageComplete(true);
				}
			}
		});
		relationName.setBounds(124, 90, 176, 21);

		Label label_3 = new Label(container, SWT.CENTER);
		label_3.setBounds(124, 73, 180, 15);
		label_3.setText("------------------------------");

		relationStereotype = new Label(container, SWT.CENTER);
		relationStereotype.setBounds(124, 57, 176, 15);
		relationStereotype.setText("<relationStereotype>");

		Label lblTargetClass = new Label(container, SWT.NONE);
		lblTargetClass.setBounds(370, 31, 89, 15);
		lblTargetClass.setText("Target Class");

		cbClasses = new Combo(container, SWT.READ_ONLY);
		cbClasses.setBounds(327, 48, 169, 23);

		Label label_1 = new Label(container, SWT.NONE);
		label_1.setText("min");
		label_1.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		label_1.setBounds(380, 73, 21, 15);

		Label label_4 = new Label(container, SWT.NONE);
		label_4.setText("max");
		label_4.setFont(SWTResourceManager.getFont("Arial", 9, SWT.BOLD));
		label_4.setBounds(418, 73, 25, 15);

		trgMinCard = new Text(container, SWT.BORDER | SWT.CENTER);
		trgMinCard.setText("1");
		trgMinCard.setBounds(380, 90, 21, 21);

		trgMaxCard = new Text(container, SWT.BORDER | SWT.CENTER);
		trgMaxCard.setText("1");
		trgMaxCard.setBounds(420, 90, 21, 21);

		Label label_5 = new Label(container, SWT.NONE);
		label_5.setText("...");
		label_5.setFont(SWTResourceManager.getFont("Segoe UI", 9, SWT.BOLD));
		label_5.setBounds(406, 92, 8, 15);
	}

	public void setTargetClasses(String[] classes){
		cbClasses.setItems(classes);
	}

	@Override
	public boolean next() {
		return true;
	}

	@Override
	public boolean canFlipToNextPage() {
		if(isEndPage)
			return false;
		System.out.println("canFlipToNextPage");
		if(relationName.getText().isEmpty()){
			return false;
		}
		return true;
	}

	@Override
	public void setVisible(boolean visible) {
		super.setVisible(visible);
		if(visible){
			srcClsName.setText(getCurrentClass());
		}
	}
	
	@Override
	public String toString() {
		String s;
		s = "Page: "+getName()+"{";
		s += "\nclass: "+getClassName();
		s += getSourceMinCardinality()+".."+getSourceMaxCardinality()+" <"+getStereotype()+"> "+getRelationName()+" "+getTargetMinCardinality()+".."+getTargetMaxCardinality()+" to class "+getTargetClass();
		s += "\n}";
		return s;
	}
	
	/* get operations */
	
	public String getClassName(){
		return srcClsName.getText();
	}
	
	public String getRelationName(){
		return relationName.getText(); 
	}
	
	public String getStereotype(){
		return relationStereotype.getText(); 
	}
	
	public String getSourceMinCardinality(){
		return srcMinCard.getText(); 
	}

	public String getSourceMaxCardinality(){
		return srcMaxCard.getText(); 
	}
	
	public String getTargetMinCardinality(){
		return trgMinCard.getText(); 
	}
	
	public String getTargetMaxCardinality(){
		return trgMaxCard.getText(); 
	}
	
	public String getTargetClass(){
		return cbClasses.getItem(cbClasses.getSelectionIndex()); 
	}
}
