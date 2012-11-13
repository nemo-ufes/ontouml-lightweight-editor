package br.ufes.inf.nemo.move.option;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.ontouml2alloy.util.Options;

/**
 * @author John Guerson
 */

public class OptionController {
	
	private OptionView optView;
	private OptionModel optModel;
	
	public OptionController(OptionView optView, OptionModel optModel)
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
	    	Options opt = new Options();
	    	opt.antiRigidity = optView.isSelectedAntirigidity(); 
	    	opt.identityPrinciple = optView.isSelectedIdentityPrinciple();
	    	opt.weakSupplementationConstraint = optView.isSelectedWeakSupplementation();
	    	opt.relatorConstraint = optView.isSelectedRelatorConstraint();
	    	opt.openAnalyzer = optView.isSelectedOpenAnalyzer();
	    	
	    	optModel.setOptions(opt);
	    	
	    	optView.dispose();
	    	optView.setOptionView(optModel);
	    	
	    	optView.getTheFrame().Validate();	    	
	    }
	 }
}
