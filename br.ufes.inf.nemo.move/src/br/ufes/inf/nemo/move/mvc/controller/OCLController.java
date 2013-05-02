package br.ufes.inf.nemo.move.mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.OCLModel;
import br.ufes.inf.nemo.move.mvc.view.OCLView;

/**
 * This class represents a Controller between OCL Model and OCL View.
 * 
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
		
		oclview.addOpenOCLListener(new OpenOCLLListener());		
		oclview.addSaveOCLListener(new SaveOCLListener());		
		oclview.addNewOCLListener(new NewOCLListener());
		oclview.addParseOCLListener(new ParseOCLListener());	
	}
	
	/**
	 * Open OCL Action Listener.
	 * 
	 * @author John
	 */
	 class OpenOCLLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	try{
	     	
	      	String path = oclview.openOCLPathLocation();
	    		
	      	if (path==null) return;
	      	
	      	oclmodel.setConstraints(path,"PATH");
				
	      	oclview.setPath(oclmodel.getOCLPath(),oclmodel.getOCLString());	      			
	      	oclview.setConstraints(oclmodel.getOCLString());
	      	
	      	oclview.validate();
	      	oclview.repaint();
	    	
	    	} catch (IOException exception) {				
	    		String msg = "An error ocurred while loading the model.\n"+exception.getMessage();
	    		oclview.getTheFrame().showErrorMessageDialog("Open OCL",msg);
	    		exception.printStackTrace();
	    	}
	    }
	}   
	 
	/**
	 * New OCL Action Listener.
	 * 
	 * @author John
	 */
	 class NewOCLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {	    	
	    	boolean option = JOptionPane.showConfirmDialog(
	    			oclview.getTheFrame(),
	    			"Do you really want to create a new OCL Document?\n",
	    			"New OCL Document",
	    			JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
	    	
	    	if (option)
	    	{
	    		oclmodel.clearModel();
	    	
	    		oclview.setPath(oclmodel.getOCLPath(), oclmodel.getOCLString());
	    		oclview.setConstraints(oclmodel.getOCLString());
	    	}
	    }
	 }	 	 
	 
	 /**
	 * Save OCL Action Listener.
	 * 
	 * @author John
	 */
	 class SaveOCLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	if (oclview.getPath()==null || oclview.getPath().isEmpty())
	    	{
	    		try{
	    			
	    			String path = oclview.saveOCLPathLocation();	    		
	    			if (path==null) return;		
		      		    					      	
	    			oclmodel.setConstraints(oclview.getConstraints(),"CONTENT");
	    			oclmodel.setOCLPath(path);
	    			
	    			FileUtil.copyStringToFile(oclview.getConstraints(), path);
	    			
	    			oclview.setPath(path,oclview.getConstraints());	    			
	    	    	
	    		}catch(IOException exception){
	    			
	    			String msg = "An error ocurred while saving the model.\n"+exception.getMessage();
	    			oclview.getTheFrame().showErrorMessageDialog("IO",msg);		       			
	    			exception.printStackTrace();
	    		}
		      			      	
	    	}else{
	    		try{
	    			
	    			oclmodel.setConstraints(oclview.getConstraints(),"CONTENT");
	    			oclmodel.setOCLPath(oclview.getPath());
	    			
	    			FileUtil.copyStringToFile(oclview.getConstraints(), oclview.getPath());
	    			
	    		}catch(IOException exception){
	    			
	    			String msg = "An error ocurred while saving the model.\n"+exception.getMessage();
	    			oclview.getTheFrame().showErrorMessageDialog("IO",msg);	       			
	    			exception.printStackTrace();
	    		}		      		
	    	}	    	
	    }
	 }
	 
	 /**
	 * Parse OCL Action Listener.
	 * 
	 * @author John
	 */
	 class ParseOCLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    { 
    		oclview.getTheFrame().getManager().ParseOCL(true);
	    }
	 }	 
}
