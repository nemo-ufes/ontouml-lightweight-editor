package br.ufes.inf.nemo.move.output;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * @author John Guerson
 */

public class OutputController {

	private OutputView outView;
	private OutputModel outModel;
	
	/**
	 * Constructor.
	 * 
	 * @param outView
	 * @param outModel
	 */
	public OutputController (OutputView outView, OutputModel outModel)
	{
		this.outModel = outModel;
		this.outView = outView;
		
		outView.addSaveOutputLocationsListener(new SaveOutputLocationsListener());
	}
	
	/**
	 * Save Locations Action Listener.
	 * 
	 * @author John
	 */
	 class SaveOutputLocationsListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	outModel.setAlloy(outView.getAlloyTextPath());
	    	outModel.setUMLPath(outView.getUMLTextPath());	 
	    	
	    	outView.dispose();
	    }
	 }
}
