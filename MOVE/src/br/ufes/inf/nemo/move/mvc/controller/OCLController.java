package br.ufes.inf.nemo.move.mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.OCLModel;
import br.ufes.inf.nemo.move.mvc.view.OCLView;
import br.ufes.inf.nemo.move.ui.ocl.OCLEditorBar;

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
		oclview.addHelpOCLListener(new HelpOCLListener());	
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
	    		JOptionPane.showMessageDialog(oclview,msg,"Error",JOptionPane.ERROR_MESSAGE);
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
	    	if (oclview.getOCLPath()==null || oclview.getOCLPath().isEmpty())
	    	{
	    		try{
	    			String path = oclview.saveOCLPathLocation();	    		
	    			if (path==null) return;		
		      		    					      	
	    			oclmodel.setConstraints(oclview.getConstraints(),"CONTENT");
	    			oclmodel.setOCLPath(path);
	    			
	    			FileUtil.copyStringToFile(oclview.getConstraints(), path);
	    			
	    			oclview.setPath(path,oclview.getConstraints());
	    			
	    		}catch(IOException exception)
	    		{
	    			String msg = "An error ocurred while saving the model.\n"+exception.getMessage();
	    			JOptionPane.showMessageDialog(
		       			oclview.getTheFrame(),msg,"IO",JOptionPane.ERROR_MESSAGE,
		       			new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/delete-36x36.png"))
		       		);
	    			exception.printStackTrace();
	    		}
		      			      	
	    	}else{
	    		try{
	    			oclmodel.setConstraints(oclview.getConstraints(),"CONTENT");
	    			oclmodel.setOCLPath(oclview.getOCLPath());
	    			
	    			FileUtil.copyStringToFile(oclview.getConstraints(), oclview.getOCLPath());
	    			
	    		}catch(IOException exception)
	    		{
	    			String msg = "An error ocurred while saving the model.\n"+exception.getMessage();
	    			JOptionPane.showMessageDialog(
	       				oclview.getTheFrame(),msg,"IO",JOptionPane.ERROR_MESSAGE,
	       				new ImageIcon(OCLEditorBar.class.getResource("/resources/br/ufes/inf/nemo/move/delete-36x36.png"))
	       			);
	    			exception.printStackTrace();
	    		}		      		
	    	}	    	
	    }
	 }
	 
	 /**
	 * Help OCL Action Listener.
	 * 
	 * @author John
	 */
	 class HelpOCLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
		    	
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
	    	if (oclview.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()==null)
	    	{ 
	    		oclview.getTheFrame().getConsole().write("First you need to load the Model"); 
	    		return; 
	    	}
	    
    		oclview.getTheFrame().getManager().ParseOCL(true);
	    }
	 }	 
}
