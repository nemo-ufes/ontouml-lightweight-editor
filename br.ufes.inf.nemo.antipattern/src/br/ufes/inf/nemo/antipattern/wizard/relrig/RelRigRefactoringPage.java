package br.ufes.inf.nemo.antipattern.wizard.relrig;

import java.util.HashMap;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.layout.grouplayout.GroupLayout;
import org.eclipse.wb.swt.layout.grouplayout.LayoutStyle;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.Property;
import RefOntoUML.parser.OntoUMLNameHelper;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelRigRefactoringPage extends RefactoringPage {
	
	public RelRigOccurrence relRig;
	
	//GUI
	public ScrolledComposite scroll;
	public Composite content;
	public HashMap<Property,Combo> mapping = new HashMap<Property,Combo>();	
	
	/**
	 * Create the wizard.
	 */
	public RelRigRefactoringPage(RelRigOccurrence relRig) 
	{
		super();	
		this.relRig = relRig;
		
		setTitle(RelRigAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public RelRigWizard getRelRigWizard(){
		return (RelRigWizard)getWizard();
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		Label lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring options:");
		
		scroll = new ScrolledComposite(container, SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL);
		scroll.setExpandHorizontal(true);
		scroll.setExpandVertical(true);
		scroll.setLayout(new FillLayout());
		
		content = new Composite(scroll, SWT.NONE);
		content.setLayout(new GridLayout(3, false));
				
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.add(10)
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, scroll, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(GroupLayout.LEADING, lblChooseTheAppropriate, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
					.add(10))
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblChooseTheAppropriate)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(scroll, GroupLayout.PREFERRED_SIZE, 244, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(1, Short.MAX_VALUE))
		);
		container.setLayout(gl_container);
				
		createOptions();
		
		content.setSize(content.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
		//scroll.setMinSize(content.computeSize(SWT.DEFAULT, SWT.DEFAULT));		
		scroll.setContent(content);				
		container.redraw();
	}

	private void createOptions()	
	{	
		Label stereoLabel = new Label(content, SWT.NONE);
		stereoLabel.setText("Stereotype");
		stereoLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label nameLabel = new Label(content, SWT.NONE);
		nameLabel.setText("Name");
		nameLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		Label actionLabel = new Label(content, SWT.NONE);
		actionLabel.setText("Action");
		actionLabel.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		for(Property p: relRig.getRigidMediatedProperties())
		{	
			RefOntoUML.Type type = p.getType();
			
			Label stereotype = new Label(content, SWT.NONE);
			stereotype.setText(OntoUMLNameHelper.getTypeName(type, false));
			stereotype.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1));	
			
			Label name = new Label(content, SWT.NONE);
			name.setLayoutData(new GridData(SWT.FILL, SWT.LEFT, false, false, 1, 1));						
			
			if(type!=null) 
				name.setText(p.getType().getName());
			else
				name.setText("null");
			
			Combo combo = new Combo(content, SWT.READ_ONLY);
			combo.setItems(new String[] {"Transform to Role or RoleMixin", "Add Role or RoleMixin subtype", "Transform to Mode", "Enforce existential dependency on relator's end", "Do nothing"});
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			combo.select(4);			
			
			mapping.put(p,combo);			
		}
	}
		
	public int getActionIndex(String typeName){
		Combo combo = mapping.get(typeName);
		if(combo!=null) return combo.getSelectionIndex();
		else return -1;
	}
	
	public String getActionString(String typeName){
		Combo combo = mapping.get(typeName);
		if (combo!=null) return combo.getText();
		else return "<unknown>";
	}
	
	@Override
	public IWizardPage getNextPage() {
		
		int rigid=0;
		// Action =====================			
		for(Property p: mapping.keySet())
		{
			RelRigAction newAction = new RelRigAction(relRig);
			Combo c = mapping.get(p);
			
			if(c.getSelectionIndex()==0) {
				newAction.setChangeStereotypeToRole((Classifier) p.getType());
				getRelRigWizard().replaceAction(rigid,newAction);
			}
			else if(c.getSelectionIndex()==1) {
				newAction.setAddRoleSubtype((Classifier) p.getType(), (Mediation) p.getAssociation());
				getRelRigWizard().replaceAction(rigid,newAction);
			}
			else if(c.getSelectionIndex()==2) {
				newAction.setChangeStereotypeToMode((Classifier) p.getType(), (Mediation) p.getAssociation());
				getRelRigWizard().replaceAction(rigid,newAction);
			}
			else if(c.getSelectionIndex()==3) {				
				newAction.setBothReadOnly((Mediation) p.getAssociation());
				getRelRigWizard().replaceAction(rigid,newAction);
			}
			else if(c.getSelectionIndex()==4) {				
				getRelRigWizard().removeAllActions(rigid);
			}
			rigid++;
		}
	
		return ((RelRigWizard)getWizard()).getFinishing();
	}
	
}
