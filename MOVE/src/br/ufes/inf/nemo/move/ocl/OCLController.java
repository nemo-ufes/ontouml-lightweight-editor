package br.ufes.inf.nemo.move.ocl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;


/**
 * @author John Guerson
 */

public class OCLController {

	private OCLView oclview;
	private OCLModel oclmodel;
	
	/**
	 * Constructor.
	 * 
	 * @param oclview
	 * @param oclmodel
	 */
	public OCLController(OCLView oclview, OCLModel oclmodel)
	{
		this.oclview = oclview;
		this.oclmodel = oclmodel;
		
		oclview.addLoadOCLListener(new LoadOCLListener());        
	}
	
	/**
	 * Load OCL Action Listener.
	 * 
	 * @author John
	 */
	 class LoadOCLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	try{
	     	
	      	String path = oclview.getOCLPathLocation();
	    				
	    	oclmodel.setOCL(path,"PATH");
	    					
	    	oclview.setPath(oclmodel.getOCLPath(),oclmodel.getOCLModelInstance());
	    		    		
	    	oclview.validate();
	    	oclview.repaint();
	    					
	    	} catch (IOException exception) {				
	    		String msg = "An error ocurred while loading the model.\n"+exception.getMessage();
	    		JOptionPane.showMessageDialog(oclview,msg,"Error",JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	 }   
	 
}
