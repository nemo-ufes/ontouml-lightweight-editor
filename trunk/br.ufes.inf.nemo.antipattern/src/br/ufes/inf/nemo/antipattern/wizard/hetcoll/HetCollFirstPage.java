package br.ufes.inf.nemo.antipattern.wizard.hetcoll;

import java.text.Normalizer;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import RefOntoUML.Association;
import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollOccurrence;
import br.ufes.inf.nemo.antipattern.wizard.AntipatternWizard;

public class HetCollFirstPage extends HetCollPage {

	public Button btnYes;
	public Button btnNo;
	
	public HetCollFirstPage(HetCollOccurrence hetColl) 
	{
		super(hetColl);		
		setDescription("Whole: <"+getStereotype(hetColl.getWhole())+"> "+hetColl.getWhole().getName());
	}

	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
	/**
	 * Create contents of the wizard.
	 * @param parent
	 */
	public void createControl(Composite parent) {
		Composite container = new Composite(parent, SWT.NULL);

		setControl(container);
		
		Text lblDoAllParts = new Text(container, SWT.WRAP | SWT.V_SCROLL);
		lblDoAllParts.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblDoAllParts.setBounds(10, 10, 554, 48);
		lblDoAllParts.setText("Do all parts of a "+hetColl.getWhole().getName()+" have the same function (or play the same role) regarding their whole?  ");
		
		btnYes = new Button(container, SWT.RADIO);
		btnYes.setBounds(10, 163, 554, 16);
		btnYes.setText("Yes, "+hetColl.getWhole().getName()+" is a collective.");
		
		btnNo = new Button(container, SWT.RADIO);
		btnNo.setBounds(10, 185, 554, 16);
		btnNo.setText("No, "+hetColl.getWhole().getName()+" is a functional complex.");
		
		Text lblMustAnInstance = new Text(container, SWT.WRAP | SWT.V_SCROLL);
		lblMustAnInstance.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_BACKGROUND));
		lblMustAnInstance.setBounds(10, 64, 554, 63);
		
		if(hetColl.getMemberEnds().size()>=2){
			lblMustAnInstance.setText("In other words, must an instance of "+hetColl.getWhole().getName()+" be composed of at least one instance of "+
				hetColl.getMemberEnds().get(0).getType().getName()+", one of "+hetColl.getMemberEnds().get(1).getType().getName()+" and etc?");
		}else{
			lblMustAnInstance.setText("In other words, must an instance of "+hetColl.getWhole().getName()+" be composed of at least one instance of the parts ?");
		}
	}
	
	@Override
	public IWizardPage getNextPage() 
	{	
		if(btnYes.getSelection()){
			return ((HetCollWizard)getWizard()).getSecondPage();
		}
		
		if(btnNo.getSelection()){
			ArrayList<Association> assocList = new ArrayList<Association>();
			for(Property p: hetColl.getMemberEnds()) { if (p!=null) assocList.add(p.getAssociation()); }
			if(assocList.size()>0){
				//Action =============================
				HetCollAction newAction = new HetCollAction(hetColl);
				newAction.setChangeAllToComponentOf(assocList); 
				getHetCollWizard().replaceAction(0,newAction);	
				//======================================
			}
		}
		return ((AntipatternWizard)getWizard()).getFinishing();
	}
}
