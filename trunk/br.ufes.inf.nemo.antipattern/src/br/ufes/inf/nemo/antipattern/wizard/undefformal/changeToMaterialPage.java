package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.ArrayList;
import java.util.Collections;

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
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.Relator;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.common.list.OntoUMLComparator;
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
		
		questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.NO_BACKGROUND);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setJustify(true);
	
		questionText.setText("Now that we established that <"+occurrence.getFormalName()+"> depends on an external entity two hold, " +
							"please provide the following additional information to precisely characterize it as a material relation:");
		
		Label lblRelator = new Label(container, SWT.NONE);
		lblRelator.setText("Relator (Truth-Maker): ");
		
		Label lblNoticeThatYou = new Label(container, SWT.WRAP | SWT.RIGHT);
		lblNoticeThatYou.setText("Notice that you can choose to create a new relator type or use an existing one. The same thing goes for the mediations.");
		
		relatorCombo = new Combo(container, SWT.NONE);
		
		Group grpTargetEnd = new Group(container, SWT.NONE);
	
		grpTargetEnd.setText("Target End");
		
		Label lblTarget = new Label(grpTargetEnd, SWT.NONE);
		lblTarget.setText("Target:");
		
		targetText = new Text(grpTargetEnd, SWT.BORDER | SWT.READ_ONLY);
		
		Label label_5 = new Label(grpTargetEnd, SWT.NONE);
		label_5.setText("Mediation:");
		
		targetMediationCombo = new Combo(grpTargetEnd, SWT.NONE);
		
		Label label_6 = new Label(grpTargetEnd, SWT.NONE);
		label_6.setText("Multi. (Mediated):");
		
		targetMediatedMultCombo = new Combo(grpTargetEnd, SWT.NONE);
		
		Label label_7 = new Label(grpTargetEnd, SWT.NONE);
		label_7.setText("Multi. (Relator):");
		
		targetRelatorMultCombo = new Combo(grpTargetEnd, SWT.NONE);
		
		Group grpSourceEnd = new Group(container, SWT.NONE);
		grpSourceEnd.setText("Source End");
		
		Label label = new Label(grpSourceEnd, SWT.NONE);
		label.setText("Source:");
		
		sourceText = new Text(grpSourceEnd, SWT.BORDER | SWT.READ_ONLY);
		
		Label label_1 = new Label(grpSourceEnd, SWT.NONE);
		label_1.setText("Mediation:");
		
		sourceMediationCombo = new Combo(grpSourceEnd, SWT.NONE);
		
		Label label_2 = new Label(grpSourceEnd, SWT.NONE);
		label_2.setText("Multi. (Mediated):");
		
		sourceMediatedMultCombo = new Combo(grpSourceEnd, SWT.NONE);
		
		Label label_3 = new Label(grpSourceEnd, SWT.NONE);
		label_3.setText("Multi. (Relator):");
		
		sourceRelatorMultCombo = new Combo(grpSourceEnd, SWT.NONE);
		
		Label lblTypesAlreadyMediated = new Label(container, SWT.WRAP);
		lblTypesAlreadyMediated.setText("Mediated by Relator:");
		
		mediatedList = new List(container, SWT.BORDER | SWT.V_SCROLL);
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblRelator, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(relatorCombo, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(lblTypesAlreadyMediated, GroupLayout.PREFERRED_SIZE, 127, GroupLayout.PREFERRED_SIZE)
					.add(6)
					.add(mediatedList, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(grpSourceEnd, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
					.add(6)
					.add(grpTargetEnd, GroupLayout.DEFAULT_SIZE, 262, Short.MAX_VALUE)
					.add(10))
				.add(gl_container.createSequentialGroup()
					.add(48)
					.add(lblNoticeThatYou, GroupLayout.DEFAULT_SIZE, 492, Short.MAX_VALUE)
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(questionText, GroupLayout.PREFERRED_SIZE, 49, GroupLayout.PREFERRED_SIZE)
					.add(15)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(lblRelator, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.add(relatorCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(6)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(gl_container.createSequentialGroup()
							.add(10)
							.add(lblTypesAlreadyMediated, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE))
						.add(mediatedList))
					.add(6)
					.add(gl_container.createParallelGroup(GroupLayout.LEADING)
						.add(grpSourceEnd, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE)
						.add(grpTargetEnd, GroupLayout.PREFERRED_SIZE, 149, GroupLayout.PREFERRED_SIZE))
					.add(6)
					.add(lblNoticeThatYou, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
		);
		GroupLayout gl_grpTargetEnd = new GroupLayout(grpTargetEnd);
		gl_grpTargetEnd.setHorizontalGroup(
			gl_grpTargetEnd.createParallelGroup(GroupLayout.LEADING)
				.add(gl_grpTargetEnd.createSequentialGroup()
					.add(10)
					.add(gl_grpTargetEnd.createParallelGroup(GroupLayout.LEADING)
						.add(gl_grpTargetEnd.createSequentialGroup()
							.add(1)
							.add(gl_grpTargetEnd.createParallelGroup(GroupLayout.TRAILING)
								.add(GroupLayout.LEADING, gl_grpTargetEnd.createSequentialGroup()
									.add(label_7, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(targetRelatorMultCombo, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))
								.add(GroupLayout.LEADING, gl_grpTargetEnd.createSequentialGroup()
									.add(label_6, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(LayoutStyle.RELATED)
									.add(targetMediatedMultCombo, GroupLayout.DEFAULT_SIZE, 108, Short.MAX_VALUE))))
						.add(gl_grpTargetEnd.createSequentialGroup()
							.addPreferredGap(LayoutStyle.RELATED)
							.add(label_5, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(targetMediationCombo, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
						.add(gl_grpTargetEnd.createSequentialGroup()
							.add(lblTarget, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(LayoutStyle.RELATED)
							.add(targetText, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)))
					.add(10))
		);
		gl_grpTargetEnd.setVerticalGroup(
			gl_grpTargetEnd.createParallelGroup(GroupLayout.LEADING)
				.add(gl_grpTargetEnd.createSequentialGroup()
					.add(10)
					.add(gl_grpTargetEnd.createParallelGroup(GroupLayout.BASELINE)
						.add(lblTarget)
						.add(targetText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(5)
					.add(gl_grpTargetEnd.createParallelGroup(GroupLayout.TRAILING)
						.add(label_5)
						.add(targetMediationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(5)
					.add(gl_grpTargetEnd.createParallelGroup(GroupLayout.BASELINE)
						.add(targetMediatedMultCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.add(label_6))
					.add(5)
					.add(gl_grpTargetEnd.createParallelGroup(GroupLayout.BASELINE)
						.add(label_7)
						.add(targetRelatorMultCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.add(25))
		);
		grpTargetEnd.setLayout(gl_grpTargetEnd);
		GroupLayout gl_grpSourceEnd = new GroupLayout(grpSourceEnd);
		gl_grpSourceEnd.setHorizontalGroup(
			gl_grpSourceEnd.createParallelGroup(GroupLayout.LEADING)
				.add(gl_grpSourceEnd.createSequentialGroup()
					.addContainerGap()
					.add(gl_grpSourceEnd.createParallelGroup(GroupLayout.TRAILING)
						.add(label, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.add(label_1, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.add(label_2, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE)
						.add(label_3, GroupLayout.PREFERRED_SIZE, 94, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(LayoutStyle.RELATED)
					.add(gl_grpSourceEnd.createParallelGroup(GroupLayout.LEADING)
						.add(sourceText, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.add(sourceMediationCombo, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.add(sourceMediatedMultCombo, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
						.add(sourceRelatorMultCombo, GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
					.add(10))
		);
		gl_grpSourceEnd.setVerticalGroup(
			gl_grpSourceEnd.createParallelGroup(GroupLayout.LEADING)
				.add(gl_grpSourceEnd.createSequentialGroup()
					.add(10)
					.add(gl_grpSourceEnd.createParallelGroup(GroupLayout.LEADING)
						.add(gl_grpSourceEnd.createSequentialGroup()
							.add(sourceText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.add(5)
							.add(gl_grpSourceEnd.createParallelGroup(GroupLayout.BASELINE)
								.add(sourceMediationCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.add(label_1))
							.add(5)
							.add(gl_grpSourceEnd.createParallelGroup(GroupLayout.BASELINE)
								.add(sourceMediatedMultCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.add(label_2))
							.add(5)
							.add(gl_grpSourceEnd.createParallelGroup(GroupLayout.BASELINE)
								.add(sourceRelatorMultCombo, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.add(label_3)))
						.add(label))
					.addContainerGap(4, Short.MAX_VALUE))
		);
		grpSourceEnd.setLayout(gl_grpSourceEnd);
		container.setLayout(gl_container);
		
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
				
		Collections.sort(relatorList, new OntoUMLComparator<Relator>());
		
		for (Relator relator : relatorList) {
			relatorCombo.add(OntoUMLNameHelper.getNameAndType(relator, true, false));
		}
	}
	
	private void selectMediation(Combo mediationCombo, Combo mediatedMultCombo, Combo relatorMultCombo, ArrayList<Mediation> mediationList){
		int mediationIndex = mediationCombo.getSelectionIndex();
		
		if(mediationIndex==-1)
			return;
		
		Mediation m = mediationList.get(mediationIndex);

		mediatedMultCombo.setText(getMultiplicityString(OntoUMLParser.getMediatedEnd(m)));
		relatorMultCombo.setText(getMultiplicityString(OntoUMLParser.getRelatorEnd(m)));
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
			allMediationsList.addAll(occurrence.getParser().getMediations(relatorList.get(relatorCombo.getSelectionIndex())));
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
			try { 
				mediatedList.add(OntoUMLParser.getMediatedType(mediation).getName()); } 
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
				if(OntoUMLParser.getMediatedType(mediation).equals(mediated)){
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
