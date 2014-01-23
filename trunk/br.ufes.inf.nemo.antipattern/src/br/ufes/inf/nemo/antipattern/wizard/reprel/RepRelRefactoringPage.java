package br.ufes.inf.nemo.antipattern.wizard.reprel;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;
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

import RefOntoUML.Mediation;
import br.ufes.inf.nemo.antipattern.reprel.RepRelAntipattern;
import br.ufes.inf.nemo.antipattern.reprel.RepRelOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.RefactoringPage;

public class RepRelRefactoringPage extends RefactoringPage {
	
	protected RepRelOccurrence repRel;
		
	//GUI
	protected Composite content;
	public HashMap<Mediation,Combo> mapping = new HashMap<Mediation,Combo>();
	
	/**
	 * Create the wizard.
	 */
	public RepRelRefactoringPage(RepRelOccurrence repRel) 
	{
		super();	
		this.repRel = repRel;
				
		setTitle(RepRelAntipattern.getAntipatternInfo().acronym+" Refactoring Options");
		setDescription("The follwing options can be used to refactor the model.");
	}

	public RepRelWizard getRepRelWizard(){
		return ( RepRelWizard)getWizard();
	}	

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    type = type.replace("Association","");
	    return type;
	}
	
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);

		setControl(container);
		
		Label lblChooseTheAppropriate = new Label(container, SWT.NONE);
		lblChooseTheAppropriate.setText("Choose the appropriate refactoring options:");
		
		ScrolledComposite scroll = new ScrolledComposite(container, SWT.NONE | SWT.V_SCROLL | SWT.H_SCROLL);		
		scroll.setLayout(new FillLayout());		
		scroll.setAlwaysShowScrollBars(true);
		
		content = new Composite(scroll, SWT.NONE);
		content.setLayout(new GridLayout(2, false));
				
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
		ArrayList<Mediation> list = repRel.getMediations();				
		for(Mediation m: list)
		{	
			Label nameLabel = new Label(content, SWT.NONE);
			nameLabel.setText("Mediation");
			
			Label actionLabel = new Label(content, SWT.NONE);
			actionLabel.setText("Action");
			
			RefOntoUML.Type source = m.getMemberEnd().get(0).getType();
			String upperSource = "*";
			if(m.getMemberEnd().get(0).getUpper() != -1) upperSource = Integer.toString(m.getMemberEnd().get(0).getUpper());
			String lowerSource = "*";
			if (m.getMemberEnd().get(0).getLower() != -1) lowerSource = Integer.toString(m.getMemberEnd().get(0).getLower());		
			RefOntoUML.Type target = m.getMemberEnd().get(1).getType();
						
			Label name = new Label(content, SWT.NONE);
			name.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));						
			name.setText(target.getName()+" -> ["+lowerSource+","+upperSource+"] "+source.getName());
			
			Combo combo = new Combo(content, SWT.READ_ONLY);
			combo.setItems(new String[] {"Do nothing", "Change the upper cardinality on relator's side", "Limit number of simoutaneous relators (Current Rleator)", "Limit number of concurrent relators (Historical Relator)"});
			combo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			combo.select(0);			
			
			mapping.put(m,combo);			
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
	
}
