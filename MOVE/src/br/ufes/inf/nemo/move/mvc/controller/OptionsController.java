package br.ufes.inf.nemo.move.mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.move.mvc.model.OptionsModel;
import br.ufes.inf.nemo.move.mvc.view.OptionsView;
import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;

/**
 * @author John Guerson
 */

public class OptionsController {
	
	private OptionsView optView;
	private OptionsModel optModel;
	
	public OptionsController(OptionsView optView, OptionsModel optModel)
	{
		this.optView = optView;
		this.optModel = optModel;
		
		optView.addOKActionListener(new OkActionListener());		
	}
	
	/**
	 * OK Action Listener.
	 * 
	 * @author John
	 */
	 class OkActionListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	OntoUMLOptions opt = new OntoUMLOptions();
	    	opt.antiRigidity = optView.isSelectedAntirigidity(); 
	    	opt.identityPrinciple = optView.isSelectedIdentityPrinciple();
	    	opt.weakSupplementationConstraint = optView.isSelectedWeakSupplementation();
	    	opt.relatorConstraint = optView.isSelectedRelatorConstraint();
	    	opt.openAnalyzer = optView.isSelectedOpenAnalyzer();	    	
	    	optModel.setOptions(opt);
	    	
	    	optView.dispose();	    		    	
	    	
	    	optView.getTheFrame().TransformsOntoUMLIntoAlloy();
	    	optView.getTheFrame().TransformsOCLIntoAlloy();
	    	optView.getTheFrame().OpenAlloyModelWithAnalyzer();	    		    	
	    }
	 }
}
