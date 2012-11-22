package br.ufes.inf.nemo.move.mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.move.mvc.model.OntoUMLModel;
import br.ufes.inf.nemo.move.mvc.view.OntoUMLView;

/**
 * @author John Guerson
 */

public class OntoUMLController {

	private OntoUMLView ontoumlview;
	private OntoUMLModel ontoumlmodel;
	
	/**
	 * Constructor.
	 * 
	 * @param ontoumlview
	 * @param ontoumlmodel
	 */
	public OntoUMLController(OntoUMLView ontoumlview, OntoUMLModel ontoumlmodel)
	{
		this.ontoumlview = ontoumlview;
		this.ontoumlmodel = ontoumlmodel;
		
		ontoumlview.addLoadOntoUMLListener(new LoadOntoUMLListener());
		ontoumlview.addVerifyModelListener(new VerifyModelListener());
	}	
	
	/**
	 * Load OntoUML Action Listener.
	 * 
	 * @author John
	 */
	 class LoadOntoUMLListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	try{
	     	
	      	String path = ontoumlview.getOntoUMLPathLocation();
	    				
	      	if (path==null) return;
	      	
	    	ontoumlmodel.setOntoUML(path);
	    					
	    	ontoumlview.setPath(ontoumlmodel.getOntoUMLPath(),ontoumlmodel.getOntoUMLModelInstance());
	    	ontoumlview.setModelTree(ontoumlmodel.getOntoUMLModelInstance());	    		
	    	ontoumlview.validate();
	    	ontoumlview.repaint();
	    	
	    	ontoumlview.getTheFrame().getAntiPatternListView().Clear();
	    	
	    	ontoumlview.getTheFrame().TransformsOntoUMLIntoUML();
	    	
	    	ontoumlview.getTheFrame().getAlloyModel().setAlloyModel(path.replace(".refontouml",".als"));
	    		    		    					
	    	} catch (IOException exception) {				
	    		String msg = "An error ocurred while loading the model.\n"+exception.getMessage();
	    		JOptionPane.showMessageDialog(ontoumlview,msg,"Error",JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	 }   
	 
	 /**
	 * Verify OntoUML Action Listener.
	 * 
	 * @author John
	 */
	 class VerifyModelListener implements ActionListener 
	 {
	    public void actionPerformed(ActionEvent e) 
	    {
	    	ontoumlview.getTheFrame().getConsole().write(ontoumlmodel.verifyModel());	    	
	    	ontoumlview.getTheFrame().ShowConsole();
	    }
	 }
	 
}
