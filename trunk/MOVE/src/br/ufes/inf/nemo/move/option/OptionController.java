package br.ufes.inf.nemo.move.option;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	    	optModel.setOptions(optView.getOptionModel().getOptions());
	    	
	    	optView.dispose();
	    }
	 }
}
