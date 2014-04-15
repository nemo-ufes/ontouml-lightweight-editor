package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.ArrayList;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Text;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.common.ontoumlverificator.MultiplicityValidator;

public class changeToMaterialPage extends UndefFormalPage{
	
	public Composite parent;
	private Text sourceText;
	private Text targetText;
	private Combo relatorCombo;
	private Combo sourceMediationCombo;
	private Combo sourceRelatorMultCombo;
	private Combo sourceMediatedMultCombo;
	private List mediatedList;
	private Combo targetMediationCombo;
	private Combo targetMediatedMultCombo;
	private Combo targetRelatorMultCombo;
	
	private ArrayList<Relator> relatorList;
	private ArrayList<Mediation> allMediationsList;
	private ArrayList<Mediation> sourceMediationList;
	private ArrayList<Mediation> targetMediationList;
	/**
	 * Create the wizard.
	 */
	public changeToMaterialPage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription(	"Formal: "+uf.getFormalName()+
				"\nSource: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
		
		relatorList = new ArrayList<Relator>();
		allMediationsList = new ArrayList<Mediation>();
		sourceMediationList = new ArrayList<Mediation>();
		targetMediationList = new ArrayList<Mediation>();
		
	}
	
	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		container.setLayout(null);
		
		questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.NO_BACKGROUND);
		questionText.setBounds(10, 10, 530, 49);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setJustify(true);
	
		questionText.setText("Now that we established that <"+occurrence.getFormalName()+"> depends on an external entity two hold, " +
							"please provide the following additional information to precisely characterize it as a material relation:");
		
		Label lblRelator = new Label(container, SWT.NONE);
		lblRelator.setBounds(10, 74, 127, 21);
		lblRelator.setText("Relator (Truth-Maker): ");
		
		Label lblNoticeThatYou = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblNoticeThatYou.setBounds(48, 332, 492, 35);
		lblNoticeThatYou.setText("Notice that you can choose to create a new relator type or use an existing one. The same thing goes for the mediations.");
		
		relatorCombo = new Combo(container, SWT.NONE);
		relatorCombo.setBounds(143, 74, 397, 23);
		
		Group grpTargetEnd = new Group(container, SWT.NONE);
		grpTargetEnd.setBounds(278, 177, 262, 149);
		
		

		grpTargetEnd.setText("Target End");
		grpTargetEnd.setLayout(null);
		
		Label lblTarget = new Label(grpTargetEnd, SWT.NONE);
		lblTarget.setBounds(16, 31, 37, 15);
		lblTarget.setText("Target:");
		
		targetText = new Text(grpTargetEnd, SWT.BORDER | SWT.READ_ONLY);
		targetText.setBounds(116, 28, 130, 21);
		
		Label label_5 = new Label(grpTargetEnd, SWT.NONE);
		label_5.setBounds(16, 58, 57, 15);
		label_5.setText("Mediation:");
		
		targetMediationCombo = new Combo(grpTargetEnd, SWT.NONE);
		targetMediationCombo.setBounds(116, 54, 130, 23);
		
		Label label_6 = new Label(grpTargetEnd, SWT.NONE);
		label_6.setBounds(16, 86, 95, 15);
		label_6.setText("Multi. (Mediated):");
		
		targetMediatedMultCombo = new Combo(grpTargetEnd, SWT.NONE);
		targetMediatedMultCombo.setBounds(116, 82, 130, 23);
		
		Label label_7 = new Label(grpTargetEnd, SWT.NONE);
		label_7.setBounds(16, 114, 82, 15);
		label_7.setText("Multi. (Relator):");
		
		targetRelatorMultCombo = new Combo(grpTargetEnd, SWT.NONE);
		targetRelatorMultCombo.setBounds(116, 110, 130, 23);
		
		Group grpSourceEnd = new Group(container, SWT.NONE);
		grpSourceEnd.setBounds(10, 177, 262, 149);
		grpSourceEnd.setText("Source End");
		grpSourceEnd.setLayout(null);
		
		Label label = new Label(grpSourceEnd, SWT.NONE);
		label.setBounds(16, 31, 39, 15);
		label.setText("Source:");
		
		sourceText = new Text(grpSourceEnd, SWT.BORDER | SWT.READ_ONLY);
		sourceText.setBounds(116, 28, 130, 21);
		
		Label label_1 = new Label(grpSourceEnd, SWT.NONE);
		label_1.setBounds(16, 58, 57, 15);
		label_1.setText("Mediation:");
		
		sourceMediationCombo = new Combo(grpSourceEnd, SWT.NONE);
		sourceMediationCombo.setBounds(116, 54, 130, 23);
		
		Label label_2 = new Label(grpSourceEnd, SWT.NONE);
		label_2.setBounds(16, 86, 95, 15);
		label_2.setText("Multi. (Mediated):");
		
		sourceMediatedMultCombo = new Combo(grpSourceEnd, SWT.NONE);
		sourceMediatedMultCombo.setBounds(116, 82, 130, 23);
		
		Label label_3 = new Label(grpSourceEnd, SWT.NONE);
		label_3.setBounds(16, 114, 82, 15);
		label_3.setText("Multi. (Relator):");
		
		sourceRelatorMultCombo = new Combo(grpSourceEnd, SWT.NONE);
		sourceRelatorMultCombo.setBounds(116, 110, 130, 23);
		
		Label lblTypesAlreadyMediated = new Label(container, SWT.WRAP);
		lblTypesAlreadyMediated.setBounds(10, 113, 127, 58);
		lblTypesAlreadyMediated.setText("Mediated by Relator:");
		
		mediatedList = new List(container, SWT.BORDER | SWT.V_SCROLL);
		mediatedList.setBounds(143, 103, 397, 68);
		
		setData();
		relatorCombo.addSelectionListener(relatorSelectionListener);
		relatorCombo.addKeyListener(relatorKeyListener);
		sourceMediationCombo.addModifyListener(mediationComboSelectionListener);
		targetMediationCombo.addModifyListener(mediationComboSelectionListener);
		sourceMediatedMultCombo.addModifyListener(multiplicityModifyListener);
		sourceRelatorMultCombo.addModifyListener(multiplicityModifyListener);
		targetMediatedMultCombo.addModifyListener(multiplicityModifyListener);
		targetRelatorMultCombo.addModifyListener(multiplicityModifyListener);
		
		setPageComplete(false);
	}	
	
	private ModifyListener mediationComboSelectionListener = new ModifyListener(){
		@Override
		public void modifyText(ModifyEvent event) {
			if(event.widget.equals(sourceMediationCombo))
				selectMediation(sourceMediationCombo, sourceMediatedMultCombo, sourceRelatorMultCombo, sourceMediationList);
			if(event.widget.equals(targetMediationCombo))
				selectMediation(targetMediationCombo, targetMediatedMultCombo, targetRelatorMultCombo, targetMediationList);
			
			checkPageComplete();
		}
	};
	
	private ModifyListener multiplicityModifyListener = new ModifyListener() {
		@Override
		public void modifyText(ModifyEvent e) {
			checkPageComplete();
		}
	};
	
	private SelectionListener relatorSelectionListener = new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {			
			selectRelator();
			checkPageComplete();
		}
	};
	
	private KeyListener relatorKeyListener = new KeyAdapter() {
		@Override
		public void keyReleased(KeyEvent arg0) {
			
			if(relatorCombo.getText()!=null && !relatorCombo.getText().trim().isEmpty())
				autoSelectCombo(relatorCombo);
			
			selectRelator();
			checkPageComplete();
			
		}
	};
	private StyledText questionText;
	
	private void checkPageComplete(){
		MultiplicityValidator validator;
		
		if(relatorCombo.getSelectionIndex()==-1 && (relatorCombo.getText()==null || relatorCombo.getText().trim().isEmpty())){
			setEnableNextPage(false);
			return;
		}
		
		if(sourceMediationCombo.getText()==null || sourceMediationCombo.getText().trim().isEmpty()){
			setEnableNextPage(false);
			return;
		}
		
		validator = new MultiplicityValidator(sourceMediatedMultCombo.getText());
		if(!validator.isValid()){
			setEnableNextPage(false);
			return;
		}
		
		validator = new MultiplicityValidator(sourceRelatorMultCombo.getText());
		if(!validator.isValid()){
			setEnableNextPage(false);
			return;
		}
			
		if(targetMediationCombo.getText()==null || targetMediationCombo.getText().trim().isEmpty()){
			setEnableNextPage(false);
			return;
		}
		
		validator = new MultiplicityValidator(targetMediatedMultCombo.getText());
		if(!validator.isValid()){
			setEnableNextPage(false);
			return;
		}
		
		validator = new MultiplicityValidator(targetRelatorMultCombo.getText());
		if(!validator.isValid()){
			setEnableNextPage(false);
			return;
		}
		
		setEnableNextPage(true);
	}
	
	private void setEnableNextPage(boolean value){
		if(isPageComplete()!=value)
			setPageComplete(value);
	}
	
	private void setData(){
		setRelatorComboData();
		sourceText.setText(occurrence.getSource().getName());
		targetText.setText(occurrence.getTarget().getName());
		setMultiplicityCombo(sourceMediatedMultCombo);
		setMultiplicityCombo(sourceRelatorMultCombo);
		setMultiplicityCombo(targetMediatedMultCombo);
		setMultiplicityCombo(targetRelatorMultCombo);
	}
	
	private void setRelatorComboData(){
		relatorList = new ArrayList<Relator>(occurrence.getParser().getAllInstances(Relator.class));
		
		for (Relator relator : relatorList) {
			relatorCombo.add(relator.getName());
		}
	}
	
	private void selectMediation(Combo mediationCombo, Combo mediatedMultCombo, Combo relatorMultCombo, ArrayList<Mediation> mediationList){
		int mediationIndex = mediationCombo.getSelectionIndex();
		
		if(mediationIndex==-1)
			return;
		
		Mediation m = mediationList.get(mediationIndex);

		mediatedMultCombo.setText(getMultiplicityString(occurrence.getParser().getMediatedEnd(m)));
		relatorMultCombo.setText(getMultiplicityString(occurrence.getParser().getRelatorEnd(m)));
	}
	
	private String getMultiplicityString(Property p){
		int lower = p.getLower();
		int upper = p.getUpper();
		
		if(lower!=upper)
			return getString(lower)+".."+getString(upper);
		else
			return getString(lower);
	}
	
	private String getString(Integer value){
		if (value==-1)
			return "*";
		return value.toString();
	}
	
	private void selectRelator(){
				
		allMediationsList.clear();
		
		if(relatorCombo.getSelectionIndex()==-1 || relatorList==null || relatorList.isEmpty()){
			mediatedList.removeAll();
			mediatedList.setEnabled(false);
			sourceMediationCombo.removeAll();
			targetMediationCombo.removeAll();
		}
		else {
			allMediationsList.addAll(occurrence.getParser().getRelatorsMediations(relatorList.get(relatorCombo.getSelectionIndex())));
			setMediationCombo(sourceMediationList, sourceMediationCombo, occurrence.getSource());
			setMediationCombo(targetMediationList, targetMediationCombo, occurrence.getTarget());
			setMediatedList();
			mediatedList.setEnabled(true);
		}
	}
	
	private void setMediatedList(){
		mediatedList.removeAll();
		
		if(relatorCombo.getSelectionIndex()==-1 || allMediationsList.isEmpty())
			return;
		
		for (Mediation mediation : allMediationsList) {
			try { mediatedList.add(occurrence.getParser().getMediated(mediation).getName()); } 
			catch (Exception e) {}
		}
	}
	
	private void setMediationCombo(ArrayList<Mediation> mediationList, Combo mediationCombo, Classifier mediated){
		
		mediationCombo.removeAll();
		
		mediationList.clear();
		
		if(relatorCombo.getSelectionIndex()==-1 || mediated==null || allMediationsList.isEmpty())
			return;
		
		for (Mediation mediation : allMediationsList) {
			try {
				if(occurrence.getParser().getMediated(mediation).equals(mediated)){
					mediationList.add(mediation);
					if(mediation.getName()!=null)
						mediationCombo.add(mediation.getName());
					else
						mediationCombo.add("");
				}
			} 
			catch (Exception e) {}
		}
	}
	
	private void autoSelectCombo(Combo combo){
		if(combo==null || combo.getItemCount()==0 || combo.getText()==null || combo.getText().trim().isEmpty())
			return;
		
		for (int i = 0; i < combo.getItemCount(); i++) {
			if(combo.getItem(i).compareTo(relatorCombo.getText())==0)
				combo.select(i);
		}		
	}
	
	private void setMultiplicityCombo(Combo multCombo){
		if(multCombo==null)
			return;
		
		multCombo.add("1");
		multCombo.add("1..*");
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(!isPageComplete())
			return null;
		
		UndefFormalAction newAction = new UndefFormalAction(occurrence);
		
		if(relatorCombo.getSelectionIndex()==-1){
			newAction.setChangeToMaterial(occurrence.getFormal(), relatorCombo.getText(), sourceMediationCombo.getText(), sourceRelatorMultCombo.getText(), sourceMediatedMultCombo.getText(), 
					targetMediationCombo.getText(), targetRelatorMultCombo.getText(), targetMediatedMultCombo.getText());
		}
		else {
			Relator relator = relatorList.get(relatorCombo.getSelectionIndex());
			if(sourceMediationCombo.getSelectionIndex()==-1){
				if(targetMediationCombo.getSelectionIndex()==-1){
					newAction.setChangeToMaterial(occurrence.getFormal(), relator, sourceMediationCombo.getText(), sourceRelatorMultCombo.getText(), sourceMediatedMultCombo.getText(), 
							targetMediationCombo.getText(), targetRelatorMultCombo.getText(), targetMediatedMultCombo.getText());
				}
				else{
					newAction.setChangeToMaterial(occurrence.getFormal(), relator, sourceMediationCombo.getText(), sourceRelatorMultCombo.getText(), sourceMediatedMultCombo.getText(), 
							targetMediationList.get(targetMediationCombo.getSelectionIndex()));
				}
			}
			else{
				if(targetMediationCombo.getSelectionIndex()==-1){
					newAction.setChangeToMaterial(occurrence.getFormal(), relator, sourceMediationList.get(sourceMediationCombo.getSelectionIndex()), 
							targetMediationCombo.getText(), targetRelatorMultCombo.getText(), targetMediatedMultCombo.getText());
				}
				else{
					newAction.setChangeToMaterial(occurrence.getFormal(), relator, sourceMediationList.get(sourceMediationCombo.getSelectionIndex()), targetMediationList.get(targetMediationCombo.getSelectionIndex()));
				}
			}
		}
		
		getAntipatternWizard().replaceAction(0,newAction);	
		return getAntipatternWizard().getFinishing();

	}
}
