package br.ufes.inf.nemo.antipattern.wizard.asscyc;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;

public class AssCycSecondPage  extends AssCycPage {
		
	private Button btnAlways;
	private Button btnNever;
	private Button btnPossibly;
	private Text lblConsiderInstancesOf;

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
		
		lblConsiderInstancesOf = new Text(container, SWT.WRAP | SWT.V_SCROLL);
		lblConsiderInstancesOf.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblConsiderInstancesOf.setBounds(10, 10, 554, 148);
		String text = "Consider instances of\n\n";
		int i = 0;
		for(RefOntoUML.Class c: asscyc.getCycle()){
			if(i!=asscyc.getCycle().size()-1) text += "- t"+(i+1)+": \""+c.getName()+"\"\n";
			else text+= "- t"+(i+1)+": \""+c.getName()+"\"\n";
			i++;
		}
		text+= "\nIf t1 is connected to t2, t2 is connected to t3, and so on, must tn be connected to t1 ?";		
		lblConsiderInstancesOf.setText(text);
		
		btnAlways = new Button(container, SWT.RADIO);
		btnAlways.setBounds(10, 164, 554, 16);
		btnAlways.setText("Always. Instance level cycle is mandatory");
		
		btnNever = new Button(container, SWT.RADIO);
		btnNever.setBounds(10, 186, 554, 16);
		btnNever.setText("Never. Instance level cycle is forbidden");
		
		btnPossibly = new Button(container, SWT.RADIO);
		btnPossibly.setBounds(10, 208, 554, 16);
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