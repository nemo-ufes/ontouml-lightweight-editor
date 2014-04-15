package br.ufes.inf.nemo.antipattern.wizard.undefformal;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.undefformal.UndefFormalOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.undefformal.UndefFormalWizard.Nature;

public class NoRelationTypePossiblePage extends UndefFormalPage{

	public Composite parent;
	private Button btnRemove;
	private Button btnKeep;
	StyledText questionText;
	
	/**
	 * Create the wizard.
	 */
	public NoRelationTypePossiblePage(UndefFormalOccurrence uf) 
	{
		super(uf);
		setDescription(	"Formal: "+uf.getFormalName()+
						"\nSource: "+uf.getSource().getName()+", Target: "+uf.getTarget().getName());
	}

	@Override
	public void createControl(Composite parent) 
	{
		this.parent = parent;	
		Composite container = new Composite(parent, SWT.NULL);
		
		setControl(container);
		container.setLayout(new FormLayout());
		
		questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL | SWT.NO_BACKGROUND);
		FormData fd_questionText = new FormData();
		fd_questionText.bottom = new FormAttachment(0, 135);
		fd_questionText.top = new FormAttachment(0, 10);
		
		questionText.setLayoutData(fd_questionText);
		questionText.setJustify(true);
		questionText.setAlwaysShowScrollBars(false);
		questionText.setBackground(questionText.getParent().getBackground());
		setQuestion();
		
		btnKeep = new Button(container, SWT.RADIO);
		fd_questionText.left = new FormAttachment(btnKeep, -554);
		fd_questionText.right = new FormAttachment(btnKeep, 0, SWT.RIGHT);
		FormData fd_btnKeepItIn = new FormData();
		fd_btnKeepItIn.right = new FormAttachment(0, 564);
		fd_btnKeepItIn.top = new FormAttachment(0, 141);
		fd_btnKeepItIn.left = new FormAttachment(0, 10);
		btnKeep.setLayoutData(fd_btnKeepItIn);
		btnKeep.setText("Keep it in the model");
		
		btnRemove = new Button(container, SWT.RADIO);
		FormData fd_btnRemoveItFrom = new FormData();
		fd_btnRemoveItFrom.right = new FormAttachment(0, 564);
		fd_btnRemoveItFrom.top = new FormAttachment(0, 163);
		fd_btnRemoveItFrom.left = new FormAttachment(0, 10);
		btnRemove.setLayoutData(fd_btnRemoveItFrom);
		btnRemove.setText("Remove it from the model");
		
		Label lblTip = new Label(container, SWT.WRAP | SWT.RIGHT);
		FormData fd_lblTip = new FormData();
		fd_lblTip.left = new FormAttachment(100, -564);
		fd_lblTip.right = new FormAttachment(100, -10);
		fd_lblTip.top = new FormAttachment(100, -45);
		fd_lblTip.bottom = new FormAttachment(100, -10);
		lblTip.setLayoutData(fd_lblTip);
		lblTip.setForeground(SWTResourceManager.getColor(255, 0, 0));
		lblTip.setAlignment(SWT.RIGHT);
		lblTip.setText("Tip: Only keep the relation if it captures a type of relation not covered in OntoUML, like instantiation, constitution, etc...");
		
		setAsEnablingNextPageButton(btnKeep);
		setAsEnablingNextPageButton(btnRemove);
		
		setPageComplete(false);
	}

	protected String presentedStereotypesList(){
		String result = "";
		ArrayList<String> stereoList = new ArrayList<String>();
		stereoList.add("Formal");
		stereoList.add("Material");
		
		if(getAntipatternWizard().isBetweenCollectiveAndFunctional()){
			stereoList.add("MemberOf");
		}
		else if (getAntipatternWizard().isBetweenCollectives()){
			stereoList.add("MemberOf");
			stereoList.add("SubCollectionOf");
		}
		else if (getAntipatternWizard().isBetweenModes() || getAntipatternWizard().isBetweenModeAndClass()){
			stereoList.add("Characterization");
		}
		else if (getAntipatternWizard().isBetweenFunctionals()){
			stereoList.add("ComponentOf");
		}
		else if (getAntipatternWizard().isBetweenRelatorAndObject()){
			stereoList.add("Mediation");
		}
		else if (getAntipatternWizard().isBetweenQuantities()){
			stereoList.add("SubQuantityOf");
		}
		
		for (int i = 0; i < stereoList.size(); i++) {
			if(i==0){
				result += "«"+stereoList.get(i)+"»";
			}
			else if (i<stereoList.size()-1){
				result += ", «"+stereoList.get(i)+"»";
			}
			else{
				result += " and «"+stereoList.get(i)+"»"; 
			}
		}
		
		
		return result;
	}
	
	protected void setQuestion() {
		questionText.setText("There is no other relation stereotype in OntoUML, besides "+presentedStereotypesList()+", that is applicable between classes whose natures are:\r\n" +
							"\r\n" +
							"Source (<"+occurrence.getSource().getName()+">): "+sourceNatureString()+"\r\n" +
							"Target (<"+occurrence.getTarget().getName()+">): "+targetNatureString()+"\r\n" +
							"\r\n" +
							"What would you like to do about the formal relation <"+occurrence.getFormalName()+">:");
	}
	
	private String sourceNatureString(){
		return getNatureString(getAntipatternWizard().sourceNatureHash);
	}
	
	private String targetNatureString(){
		return getNatureString(getAntipatternWizard().targetNatureHash);
	}
	
	private String getNatureString(HashMap<Nature, Boolean> hash){
		String result = "";
		
		ArrayList<String> natureList = new ArrayList<String>();
		if(hash.get(Nature.FUNCTIONAL))
			natureList.add("Functional Complex");
		if(hash.get(Nature.COLLECTIVE))
			natureList.add("Collection");
		if(hash.get(Nature.QUANTITY))
			natureList.add("Quantity");
		if(hash.get(Nature.MODE))
			natureList.add("Mode");
		if(hash.get(Nature.RELATOR))
			natureList.add("Relator");
		if(hash.get(Nature.DATATYPE))
			natureList.add("DataType");
		
		for (int i = 0; i < natureList.size(); i++) {
			if(i==0){
				result += natureList.get(i);
			}
			else if (i<natureList.size()-1){
				result += ", "+natureList.get(i);
			}
			else{
				result += " and "+natureList.get(i); 
			}
		}
		
		return result;
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnKeep.getSelection())
			return getAntipatternWizard().getFinishing();
		
		if(btnRemove.getSelection()){
			UndefFormalAction action = new UndefFormalAction(occurrence);
			action.setRemoveFormal();
			getAntipatternWizard().replaceAction(0, action);
			return getAntipatternWizard().getFinishing();
		}
		
		return null;
	}
}
