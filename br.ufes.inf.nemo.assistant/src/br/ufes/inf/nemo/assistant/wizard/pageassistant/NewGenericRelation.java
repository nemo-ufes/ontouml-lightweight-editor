package br.ufes.inf.nemo.assistant.wizard.pageassistant;

import java.io.Serializable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

public class NewGenericRelation extends WizardPageAssistant implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		setTitle("Creating a Relation between two classes");
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
		srcClsNameMinCard.setBounds(10, 52, 291, 15);
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
		relationName.addModifyListener(new ModifyListener() {
			@Override
			public void modifyText(ModifyEvent arg0) {
				enableFinish(!relationName.getText().isEmpty());
			}
		});
		relationName.setBounds(124, 99, 176, 21);

		Label label_3 = new Label(container, SWT.CENTER);
		label_3.setBounds(124, 82, 180, 15);
		label_3.setText("------------------------------");

		relationStereotype = new Label(container, SWT.CENTER);
		relationStereotype.setBounds(124, 66, 176, 15);
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

	@Override
	public boolean next() {
		return true;
	}

	@Override
	public boolean canFlipToNextPage() {
		if(isEndPage)
			return false;
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
			relationStereotype.setText(relStereotype);
			if(!minSrcCard.isEmpty()){
				srcMinCard.setText(minSrcCard);
				srcMinCard.setEnabled(false);
			}
			if(!minTrgCard.isEmpty()){
				trgMinCard.setText(minTrgCard);
				trgMinCard.setEnabled(false);
			}
			if(!maxSrcCard.isEmpty()){
				srcMaxCard.setText(maxSrcCard);
				srcMaxCard.setEnabled(false);
			}
			if(!maxTrgCard.isEmpty()){
				trgMaxCard.setText(maxTrgCard);
				trgMaxCard.setEnabled(false);
			}
			if(targetClasses != null){
				cbClasses.setItems(targetClasses);
				cbClasses.select(0);
			}
			enableFinish(false);
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

	private String[] targetStereotypes;
	public void setTargetsStereotype(String[] classes){
		targetStereotypes = classes;
	}

	public String[] getTargetStereptypes(){
		return targetStereotypes;
	}

	private String[] targetClasses;
	public void setTargetClasses(String[] targetClasses){
		this.targetClasses = targetClasses;
		cbClasses.setItems(targetClasses);
		cbClasses.select(0);
	}

	private String relStereotype;
	public void setRelationStereotype(String relStereotype) {
		this.relStereotype = relStereotype;
	}

	private String minSrcCard = "";
	public void setMinSrcCard(String minSrcCard) {
		this.minSrcCard = minSrcCard;
	}

	private String minTrgCard = "";
	public void setMinTrgCard(String minTrgCard) {
		this.minTrgCard = minTrgCard;
	}

	private String maxSrcCard = "";
	public void setMaxSrcCard(String maxSrcCard) {
		this.maxSrcCard = maxSrcCard;
	}

	private String maxTrgCard = "";
	public void setMaxTrgCard(String maxTrgCard) {
		this.maxTrgCard = maxTrgCard;
	}

	/* get operations */

	public String getClassName(){
		return getCurrentClass();
	}

	public String getRelationName(){
		return relationName.getText(); 
	}

	public String getStereotype(){
		return relationStereotype.getText(); 
	}

	public int getSourceMinCardinality(){
		return cardMinToInt(srcMinCard.getText()); 
	}

	public int getSourceMaxCardinality(){
		return cardMaxToInt(srcMaxCard.getText()); 
	}

	public int getTargetMinCardinality(){
		return cardMinToInt(trgMinCard.getText()); 
	}

	public int getTargetMaxCardinality(){
		return cardMaxToInt(trgMaxCard.getText()); 
	}

	public String getTargetClass(){
		return cbClasses.getItem(cbClasses.getSelectionIndex()); 
	}

	private int cardMinToInt(String card){
		return Integer.parseInt(card);
	}

	private int cardMaxToInt(String card){
		if(card.equals("*"))
			return -1;
		return Integer.parseInt(card);
	}

	@Override
	public void init() {
		setTitle("Creating a Relation between two classes");
	}
}
