package br.ufes.inf.nemo.oled.antipattern.wizard.relrig;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
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

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.relrig.RelRigAntipattern;
import br.ufes.inf.nemo.antipattern.relrig.RelRigOccurrence;

/**
 * @author Tiago Sales
 * @author John Guerson
 *
 */

public class RelRigRefactoringPage extends WizardPage {
	
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
		super("RelRigRefactoringPage");	
		this.relRig = relRig;
		
		setTitle(RelRigAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
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
		scroll.setLayout(new FillLayout());		
		scroll.setAlwaysShowScrollBars(true);
		
		content = new Composite(scroll, SWT.NONE);
		content.setLayout(new GridLayout(3, false));
				
		GroupLayout gl_container = new GroupLayout(container);
		gl_container.setHorizontalGroup(
			gl_container.createParallelGroup(GroupLayout.TRAILING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(gl_container.createParallelGroup(GroupLayout.TRAILING)
						.add(GroupLayout.LEADING, scroll, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE)
						.add(lblChooseTheAppropriate, GroupLayout.DEFAULT_SIZE, 552, Short.MAX_VALUE))
					.addContainerGap())
		);
		gl_container.setVerticalGroup(
			gl_container.createParallelGroup(GroupLayout.LEADING)
				.add(gl_container.createSequentialGroup()
					.addContainerGap()
					.add(lblChooseTheAppropriate)
					.addPreferredGap(LayoutStyle.RELATED)
					.add(scroll, GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
					.add(36))
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
		ArrayList<Property> list = relRig.getRigidMediatedProperties();				
		for(Property p: list)
		{	
			Label stereoLabel = new Label(content, SWT.NONE);
			stereoLabel.setText("Stereotype");
			
			Label nameLabel = new Label(content, SWT.NONE);
			nameLabel.setText("Name");
			
			Label actionLabel = new Label(content, SWT.NONE);
			actionLabel.setText("Action");
			
			RefOntoUML.Type type = p.getType();
			
			Label stereotype = new Label(content, SWT.NONE);
			if(type!=null) stereotype.setText(""+getStereotype(type)+"");
			
			Label name = new Label(content, SWT.NONE);
			name.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));						
			if(type!=null) name.setText(p.getType().getName());
			else name.setText("null");
			
			Combo combo = new Combo(content, SWT.READ_ONLY);
			combo.setItems(new String[] {"Transform to Role or RoleMixin", "Add Role or RoleMixin subtype", "Transform to Mode", "Enforce existential dependency on relator's end", "Do nothing"});
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			combo.select(4);			
			
			mapping.put(p,combo);			
		}
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
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
		
		// Action =====================			
		for(Property p: mapping.keySet())
		{
			Combo c = mapping.get(p);
			
			if(c.getSelectionIndex()==0) {
				relRig.changeToRoleOrRoleMixin(p.getType());
				//relRig.changeToRoleMixin(p.getType());
			}
			if(c.getSelectionIndex()==1) {
				relRig.createRoleSubType(p.getType(), p.getAssociation());
				//relRig.createRoleMixinSubType(p.getType(), p.getAssociation());
			}
			if(c.getSelectionIndex()==2) {
				relRig.changeToMode(p.getType(), p.getAssociation());				
			}
			if(c.getSelectionIndex()==3) {
				relRig.setBothReadOnly(p.getAssociation());				
			}
		}
		
		//set fixes
		((RelRigWizard)getWizard()).finishing.addFix(relRig.getFix());
		((RelRigWizard)getWizard()).finishing.updateOLED();
		((RelRigWizard)getWizard()).canFinish=true;
		
		return ((RelRigWizard)getWizard()).finishing;
	}
	
}
