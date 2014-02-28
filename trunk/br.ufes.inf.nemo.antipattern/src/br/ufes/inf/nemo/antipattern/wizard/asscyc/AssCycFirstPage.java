package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
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
		
	private Label lblIsItAny;
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

		lblIsItAny = new Label(container, SWT.WRAP);
		lblIsItAny.setBounds(10, 10, 554, 87);
		lblIsItAny.setText("Is it any of the associations in the cycle derived from the others? For an example of derived association consider the domain of physiology.  The human body is composed of organ systems, such as the circulatory system and the digestive system. Each system is composed of organs, like the heart, lungs and stomach. The part-hood relation that holds between human body and organ is derived from the composition from human body to organ system and from organ system to organ. ");
  	  
		SelectionAdapter listener = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  assocCombo.setVisible(true);
	    	  lblDerivedAssociation.setVisible(true);
	    	  composite.setVisible(true);
	      }
	    };

		SelectionAdapter listener1 = new SelectionAdapter() {
	      public void widgetSelected(SelectionEvent e) {
	    	  assocCombo.setVisible(false);
	    	  lblDerivedAssociation.setVisible(false);
	    	  composite.setVisible(false);
	      }
	    };
		    
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 125, 554, 16);
		btnYes.setText("Yes. Association is derived");
		btnYes.addSelectionListener(listener);
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 103, 554, 16);
		btnNo.setText("No. All associations are intentional");
		btnNo.addSelectionListener(listener1);
		
		composite = new Composite(container, SWT.BORDER);
		composite.setBounds(10, 159, 554, 64);
		
		lblDerivedAssociation = new Label(composite, SWT.NONE);
		lblDerivedAssociation.setBounds(10, 10, 530, 16);
		lblDerivedAssociation.setText("Choose which association is derived:");
		
		assocCombo = new Combo(composite, SWT.READ_ONLY);
		assocCombo.setBounds(10, 32, 530, 23);		
		for(Relationship rel: asscyc.getCycleRelationship()){
			assocCombo.add(getStereotype(rel)+" "+((NamedElement)rel).getName()+": "+((Association)rel).getMemberEnd().get(0).getType().getName()+"->"+((Association)rel).getMemberEnd().get(1).getType().getName());
		}
		
		lblDerivedAssociation.setVisible(false);
		assocCombo.setVisible(false);
		composite.setVisible(false);
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
			return getAssCycWizard().getSecondPage();
		}
		if(btnYes.getSelection())
		{
			if(assocCombo.getSelectionIndex()>=0)
			{
				Association assoc = (Association)asscyc.getCycleRelationship().get(assocCombo.getSelectionIndex());
				//Action =============================
				AssCycAction newAction = new AssCycAction(asscyc);
				newAction.setDeriveAssociation(assoc); 
				getAssCycWizard().replaceAction(0,newAction);	
				//======================================
			}				
		}
		
		return getAssCycWizard().getFinishing();
	}
}

