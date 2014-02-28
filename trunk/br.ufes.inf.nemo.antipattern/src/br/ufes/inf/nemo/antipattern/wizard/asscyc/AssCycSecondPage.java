package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;

public class AssCycSecondPage  extends AssCycPage {
		
	private Button btnAlways;
	private Button btnNever;
	private Button btnPossibly;
	private Label lblConsiderInstancesOf;

	public AssCycSecondPage(AssCycOccurrence asscyc) 
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
		
		lblConsiderInstancesOf = new Label(container, SWT.WRAP);
		lblConsiderInstancesOf.setBounds(10, 10, 554, 57);
		lblConsiderInstancesOf.setText("Consider instances of t1 of <Type-1>, t2 of <Typ2-2>, t3 of <Type-3> and so on. If t1 is connected to t2 through <assoc-1> and t2 is connected to t3 through <assoc-2>, must t3 be connected to t1 through <assoc-3>?");
		
		btnAlways = new Button(container, SWT.RADIO);
		btnAlways.setBounds(10, 73, 554, 16);
		btnAlways.setText("Always. Instance level cycle is mandatory");
		
		btnNever = new Button(container, SWT.RADIO);
		btnNever.setBounds(10, 95, 554, 16);
		btnNever.setText("Never. Instance level cycle is forbidden");
		
		btnPossibly = new Button(container, SWT.RADIO);
		btnPossibly.setBounds(10, 117, 554, 16);
		btnPossibly.setText("Possibly. Never mind, the model is correct");
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
		if(btnAlways.getSelection())
		{
			//Action =============================
			AssCycAction newAction = new AssCycAction(asscyc);
			newAction.setCycleMandatory();
			getAssCycWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnNever.getSelection())
		{
			//Action =============================
			AssCycAction newAction = new AssCycAction(asscyc);
			newAction.setCycleForbidden(); 
			getAssCycWizard().replaceAction(0,newAction);	
			//======================================
		}
		if(btnPossibly.getSelection())
		{
			
		}
		
		return getAssCycWizard().getFinishing();
	}
}