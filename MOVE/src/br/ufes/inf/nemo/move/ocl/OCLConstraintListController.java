package br.ufes.inf.nemo.move.ocl;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;


/**
 * @author John Guerson
 */

public class OCLConstraintListController {

	private OCLConstraintListView viewList;
	private OCLConstraintListModel modelList;
	
	/**
	 * Constructor.
	 * 
	 * @param oclview
	 * @param oclmodel
	 */
	public OCLConstraintListController(OCLConstraintListView viewList, OCLConstraintListModel oclmodellist)
	{
		this.viewList = viewList;
		this.modelList = oclmodellist;
		
		viewList.addLoadOCLListener(new LoadOCLListener()); 
		viewList.addAditionalConstraintListener(new AditionalConstraintListener());
		viewList.addSaveAsListener(new SaveAsListener());
		viewList.addEnableAllListener(new EnableAllListener());
		viewList.addDisableAllListener(new DisableAllListener());
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
	     	
	      	String path = viewList.getOCLPathLocation();
	    		
	      	if (path==null) return;
	      	
	      	modelList.setConstraintList(path,"PATH");
	    					
	      	viewList.setPath(modelList.getOCLPath(),modelList.getOCLString());	      			
	      	viewList.setConstraints(modelList.getConstraintModelList());
	      	
	      	viewList.validate();
	      	viewList.repaint();
	    	
	    	} catch (IOException exception) {				
	    		String msg = "An error ocurred while loading the model.\n"+exception.getMessage();
	    		JOptionPane.showMessageDialog(viewList,msg,"Error",JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	 }   
	 
	/**
	 * Aditional Constraint Action Listener.
	 * 
	 * @author John
	 */
	 class AditionalConstraintListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {		
						
	    }
	 }
	 
	/**
	 * Enable All Action Listener.
	 * 
	 * @author John
	 */
	 class EnableAllListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	
	    }
	 }
	 
	 /**
	 * Disable All Action Listener.
	 * 
	 * @author John
	 */
	 class DisableAllListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	
	    }
	 }
	 
	 /**
	 * Save As Action Listener.
	 * 
	 * @author John
	 */
	 class SaveAsListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	
	    }
	 }
	 
}
