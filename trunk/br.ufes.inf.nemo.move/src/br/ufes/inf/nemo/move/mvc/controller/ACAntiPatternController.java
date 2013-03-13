package br.ufes.inf.nemo.move.mvc.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.move.mvc.model.ACAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.ACAntiPatternView;
import br.ufes.inf.nemo.move.ui.TheManager;

/**
 * @author John Guerson
 */

public class ACAntiPatternController {

	private ACAntiPatternView acView;
	private ACAntiPatternModel acModel;
	
	/**
	 * Constructor.
	 * 
	 * @param acView
	 * @param acModel
	 */
	public ACAntiPatternController(ACAntiPatternView acView, ACAntiPatternModel acModel)
	{
		this.acView = acView;
		this.acModel = acModel;		
		
		acView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		acView.addOCLSolutionListener(new OCLSolutionListener());
	}
		
	/**
	 * Execute With Analyzer Action Listener
	 * 
	 * @author John
	 */
	class ExecuteWithAnalzyerListener implements ActionListener 
	{
	    @SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) 
	    {			
	    	try{
	    		String predicates = new String();
				
	    		if(acView.isSelectedOpenCycle()) 
	    		{
	    			predicates += "\n\n"+acModel.getACAntiPattern().generatePredicate(
	    				acView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(),
	    				acView.getScope(), acModel.getACAntiPattern().OPEN
	    			);
	    		}
			
	    		if(acView.isSelectedClosedCycle())				
	    		{
	    			predicates += "\n\n"+acModel.getACAntiPattern().generatePredicate(
	    				acView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(), 
	    				acView.getScope(), acModel.getACAntiPattern().CLOSED
	    			); 
	    		}
				
	    		/* Execute... TODO : JOHN, PLEASE, FIXE MEEE SOON !!!!!!!! */
	    		
	    		/*=======================================================*/
	    		
	    		TheManager manager = acView.getTheFrame().getManager();
	    		
	    		//set parser...
	    		acModel.getACAntiPattern().setSelected(manager.getOntoUMLModel().getOntoUMLParser());
	    		
	    		// set options to false, because the simulated model is partial
	    		manager.getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		manager.getOntoUMLOptionModel().getOptions().relatorConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().weakSupplementationConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().antiRigidity = false;
	    		
	    		// set alloy path
	    		String alsPath = manager.getAlloyModel().getDirectory()+manager.getAlloyModel().getAlloyModelName()+"$AC"+acModel.getId()+".als";	    		
	    		manager.getAlloyModel().setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		manager.getAlloyModel().setAlloyModel(manager.getOntoUMLModel(),manager.getOntoUMLOptionModel());	    		
	    		String content = manager.getAlloyModel().getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		manager.getAlloyModel().setContent(content);
	    		
	    		// open alloy model
	    		acView.getTheFrame().getManager().doOpeningAlloy(true,-1);
	    			    		
	    		/*=======================================================*/
	    		
	    	}catch(Exception exception){
	    		acView.getTheFrame().showErrorMessageDialog("AC : Execute With Analyzer",exception.getMessage());
	    	}
	    	
	    }
	}
	
	/**
	 * Generate OCL Solution
	 * 
	 * @author John
	 */
	class OCLSolutionListener implements ActionListener 
	{
		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) 
	    {
			Boolean openCycle = acView.isSelectedOpenCycle();
			Boolean closedCycle = acView.isSelectedClosedCycle();

			String constraints = new String();
			
					
			if(openCycle) 
				constraints = acModel.getACAntiPattern().generateCycleOcl(acModel.getACAntiPattern().OPEN, acView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser())+"\n\n";		
			if(closedCycle)
				constraints += acModel.getACAntiPattern().generateCycleOcl(acModel.getACAntiPattern().CLOSED, acView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser())+"\n\n";		
							
			acView.getTheFrame().getManager().getOCLView().getOcleditor().addText(constraints);
	    }
	}
	
}
