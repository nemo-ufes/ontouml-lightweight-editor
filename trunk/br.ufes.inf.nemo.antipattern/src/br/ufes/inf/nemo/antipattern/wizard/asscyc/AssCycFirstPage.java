package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import RefOntoUML.Association;
import RefOntoUML.NamedElement;
import RefOntoUML.Relationship;
import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;

public class AssCycFirstPage  extends AssCycPage {
		
	private StyledText questionText;
	private Button btnYes;
	private Button btnNo;
	private Label lblDerivedAssociation;
	private Combo assocCombo;
	private Composite composite;

	public AssCycFirstPage(AssCycOccurrence asscyc) 
	{
		super(asscyc);		
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		container.setLayout(null);

		questionText = new StyledText(container, SWT.WRAP | SWT.READ_ONLY | SWT.V_SCROLL);
		questionText.setBounds(10, 10, 554, 233);
		questionText.setJustify(true);
		questionText.setBackground(questionText.getParent().getBackground());
		questionText.setText(
				"To start the analysis of this Association Cycle anti-pattern, we first remember the concept of derived associations, inherit by OntoUML from UML." +
				"\n\n" +
				"In a brief description, derived associations are the ones that can be calculated from other associations and attributes. " +
				"An example of a derived association is “older than”, which can hold between two people. " +
				"Every instance of the association can be computed by the comparing people’s ages. " +
				"Another example can be found by considering the part-whole relations that hold in a Car. " +
				"A Car is composed by an Engine, which in turn is composed by an Exhaust System. " +
				"The part-whole relation that holds between a Car and an Exhaust System is derived from the relations between the Car and the Engine and between the Engine and the Exhaust System." +
				"\n\n" +
				"In your model, we identified a cycle of associations containing the classes: "+occurrence.getClassCycleString()+"."+
				"\n\n" +
				"That suggests that one of the associations may be derived from the others. Is that the case?");
  	  
		SelectionAdapter yesListener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  assocCombo.setVisible(true);
	    	  lblDerivedAssociation.setVisible(true);
	    	  composite.setVisible(true);
	    	  
	    	  if(assocCombo.getSelectionIndex()!=-1){
	    		  if(!isPageComplete())
		    		  setPageComplete(true);
	    	  }
	    	  else{
	    		  if(isPageComplete())
		    		  setPageComplete(false);
	    	  }
	      }
	    };

		SelectionAdapter noListener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  assocCombo.setVisible(false);
	    	  lblDerivedAssociation.setVisible(false);
	    	  composite.setVisible(false);
	    	  
	    	  if(!isPageComplete())
	    		  setPageComplete(true);
	      }
	    };
	    
	    SelectionAdapter comboListener = new SelectionAdapter() {
		      public void widgetSelected(SelectionEvent e) {
		    	  if(assocCombo.getSelectionIndex()!=-1){
			    	  if(!isPageComplete())
			    		  setPageComplete(true);
		    	  }else{
		    		  if(isPageComplete())
			    		  setPageComplete(false);
		    	  }
		      }
		    };
		    
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 271, 554, 16);
		btnYes.setText("Yes, one association is derived");
		btnYes.addSelectionListener(yesListener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 249, 554, 16);
		btnNo.setText("No, none of the associations are derived");
		btnNo.addSelectionListener(noListener);
		
		composite = new Composite(container, SWT.BORDER);
		composite.setBounds(10, 293, 554, 64);
		
		lblDerivedAssociation = new Label(composite, SWT.NONE);
		lblDerivedAssociation.setBounds(10, 10, 530, 16);
		lblDerivedAssociation.setText("Please, choose which association is derived:");
		
		assocCombo = new Combo(composite, SWT.READ_ONLY);
		assocCombo.setBounds(10, 32, 530, 23);		
		for(Relationship rel: occurrence.getCycleRelationship()){
			assocCombo.add(getStereotype(rel)+" "+((NamedElement)rel).getName()+": "+((Association)rel).getMemberEnd().get(0).getType().getName()+"->"+((Association)rel).getMemberEnd().get(1).getType().getName());
		}
		assocCombo.addSelectionListener(comboListener);
		
		lblDerivedAssociation.setVisible(false);
		assocCombo.setVisible(false);
		composite.setVisible(false);
		
		setPageComplete(false);
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if (btnNo.getSelection())
		{
			return getAntipatternWizard().getSecondPage();
		}
		if(btnYes.getSelection())
		{
			if(assocCombo.getSelectionIndex()>=0)
			{
				Association assoc = (Association)occurrence.getCycleRelationship().get(assocCombo.getSelectionIndex());
				//Action =============================
				AssCycAction newAction = new AssCycAction(occurrence);
				newAction.setDeriveAssociation(assoc); 
				getAntipatternWizard().replaceAction(0,newAction);	
				//======================================
			}				
		}
		
		return getAntipatternWizard().getFinishing();
	}
}

