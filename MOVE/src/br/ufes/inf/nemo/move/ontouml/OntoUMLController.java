package br.ufes.inf.nemo.move.ontouml;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.move.output.OutputModel;

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
	    	
	    	// clear AntiPattern View...
	    	ontoumlview.getTheFrame().getAntiPatternListView().Clear();
	    	
	    	// create UML BackEnd Model from OntoUML...
	    	ontoumlview.getTheFrame().getUMLModel().setUMLModel(path.replace(".refontouml",".uml"),ontoumlmodel.getOntoUMLModelInstance());
	    	
	    	// temporary code...
	    	OutputModel outputmodel = new OutputModel(path.replace(".refontouml",".als"),path.replace(".refontouml",".uml"));	    	
	    	ontoumlview.getTheFrame().setOutputModel(outputmodel);	    	
	    		    					
	    	} catch (IOException exception) {				
	    		String msg = "An error ocurred while loading the model.\n"+exception.getMessage();
	    		JOptionPane.showMessageDialog(ontoumlview,msg,"Error",JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	 }   
	 
}
