package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.move.mvc.model.antipattern.RBOSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.RBOSAntiPatternView;
import br.ufes.inf.nemo.move.ui.TheManager;

/**
 * @author John Guerson
 */

public class RBOSAntiPatternController {

	private RBOSAntiPatternView rbosView;
	private RBOSAntiPatternModel rbosModel;
	
	/**
	 * Constructor.
	 * 
	 * @param rbosView
	 * @param rbosModel
	 */
	public RBOSAntiPatternController(RBOSAntiPatternView rbosView, RBOSAntiPatternModel rbosModel)
	{
		this.rbosView = rbosView;
		this.rbosModel = rbosModel;		
		
		rbosView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		rbosView.addOCLSolutionListener(new OCLSolutionListener());
	}
	
	/**
	 * Execute With Analyzer Action Listener
	 * 
	 * @author John
	 */
	class ExecuteWithAnalzyerListener implements ActionListener 
	{
	    public void actionPerformed(ActionEvent e) 
	    {			
	    	try{
	    		
	    		String predicates = new String();
				
	    		if(rbosView.isSelectedAntiSymmetric()) 
	    		{
	    			predicates += "\n\n"+rbosModel.getRBOSAntiPattern().generateAntisymmetricPredicate(
	    				rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
			
	    		if(rbosView.isSelectedIntransitive()) 
	    		{
	    			predicates += "\n\n"+rbosModel.getRBOSAntiPattern().generateIntransitivePredicate(
	    				rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}	    		
	    		
	    		if(rbosView.isSelectedIrreflexive()) 
	    		{
	    			predicates += "\n\n"+rbosModel.getRBOSAntiPattern().generateIrreflexivePredicate(
	    				rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
			
	    		if(rbosView.isSelectedReflexive()) 
	    		{
	    			predicates += "\n\n"+rbosModel.getRBOSAntiPattern().generateReflexivePredicate(
	    				rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
								
	    		if(rbosView.isSelectedSymmetric()) 
	    		{
	    			predicates += "\n\n"+rbosModel.getRBOSAntiPattern().generateSymmetricPredicate(
	    				rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
	    		
	    		if(rbosView.isSelectedTransitive()) 
	    		{
	    			predicates += "\n\n"+rbosModel.getRBOSAntiPattern().generateTransitivePredicate(
	    				rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
	    							
	    		if(rbosView.isSelectedDisjoint()) 
	    		{
	    			predicates += "\n\n"+rbosModel.getRBOSAntiPattern().generateDisjointPredicate(
	    				rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
	    		
	    		/* Execute... TODO : JOHN, PLEASE, FIXE MEEE SOON !!!!!!!! */
	    		
	    		/*=======================================================*/
	    		
	    		TheManager manager = rbosView.getTheFrame().getManager();
	    		
	    		//set parser...
	    		rbosModel.getRBOSAntiPattern().setSelected(manager.getOntoUMLModel().getOntoUMLParser());
	    		
	    		// set options to false, because the simulated model is partial
	    		manager.getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		manager.getOntoUMLOptionModel().getOptions().relatorConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().weakSupplementationConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().antiRigidity = false;
	    		
	    		// set alloy path
	    		String alsPath = manager.getAlloyModel().getDirectory()+manager.getAlloyModel().getAlloyModelName()+"$AC"+rbosModel.getId()+".als";	    		
	    		manager.getAlloyModel().setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		manager.getAlloyModel().setAlloyModel(manager.getOntoUMLModel(),manager.getOntoUMLOptionModel());	    		
	    		String content = manager.getAlloyModel().getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		manager.getAlloyModel().setContent(content);
	    		
	    		// open alloy model
	    		rbosView.getTheFrame().getManager().doOpeningAlloy(true,-1);
	    			    		
	    		/*=======================================================*/
	    		 		
	    		
	    	}catch(Exception exception){
	    		rbosView.getTheFrame().showErrorMessageDialog("RBOS : Execute With Analyzer",exception.getMessage());
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
		public void actionPerformed(ActionEvent e) 
	    {
			String constraints = new String();
			
    		if(rbosView.isSelectedReflexive()) 
    		{
    			constraints += rbosModel.getRBOSAntiPattern().generateReflexiveOcl(rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser());
    		}
		
    		if(rbosView.isSelectedIrreflexive())				
    		{
    			if(rbosView.isSelectedReflexive())
    				constraints += "\n\n"; 
    			constraints += rbosModel.getRBOSAntiPattern().generateIrreflexiveOcl(rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()); 
    		}
    		
    		rbosView.getTheFrame().getManager().getOCLView().getOcleditor().addText(constraints);
	    }
	}	
}
