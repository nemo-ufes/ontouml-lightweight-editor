package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.antipattern.STRAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.STRAntiPatternView;
import br.ufes.inf.nemo.move.ui.TheManager;

/**
 * @author John Guerson
 */

public class STRAntiPatternController {

	private STRAntiPatternView strView;
	private STRAntiPatternModel strModel;
	
	/**
	 * Constructor.
	 * 
	 * @param strView
	 * @param strModel
	 */
	public STRAntiPatternController(STRAntiPatternView strView, STRAntiPatternModel strModel)
	{
		this.strView = strView;
		this.strModel = strModel;		
		
		strView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		strView.addOCLSolutionListener(new OCLSolutionListener());
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
				Integer cardinality = strView.getScope();
				
	    		if(strView.isSelectedAntiSymmetric()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateAntisymmetricPredicate(
	    				cardinality,strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
			
	    		if(strView.isSelectedIntransitive()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateIntransitivePredicate(
	    				cardinality,strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}	    		
	    		
	    		if(strView.isSelectedIrreflexive()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateIrreflexivePredicate(
	    				cardinality,strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
			
	    		if(strView.isSelectedReflexive()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateReflexivePredicate(
	    				cardinality,strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
								
	    		if(strView.isSelectedSymmetric()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateSymmetricPredicate(
	    				cardinality,strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
	    		
	    		if(strView.isSelectedTransitive()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateTransitivePredicate(
	    				cardinality,strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
	    		
	    		/* Execute... TODO : JOHN, PLEASE, FIXE MEEE SOON !!!!!!!! */
	    		
	    		/*=======================================================*/
	    		
	    		TheManager manager = strView.getTheFrame().getManager();
	    		
	    		//set parser...
	    		strModel.getSTRAntiPattern().setSelected(manager.getOntoUMLModel().getOntoUMLParser());
	    		
	    		// set options to false, because the simulated model is partial
	    		manager.getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		manager.getOntoUMLOptionModel().getOptions().relatorConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().weakSupplementationConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().antiRigidity = false;
	    		
	    		// set alloy path
	    		String alsPath = manager.getAlloyModel().getDirectory()+manager.getAlloyModel().getAlloyModelName()+"$AC"+strModel.getId()+".als";	    		
	    		manager.getAlloyModel().setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		manager.getAlloyModel().setAlloyModel(manager.getOntoUMLModel(),manager.getOntoUMLOptionModel());	    		
	    		String content = manager.getAlloyModel().getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		manager.getAlloyModel().setContent(content);
	    		
	    		// open alloy model
	    		strView.getTheFrame().getManager().doOpeningAlloy(true,-1);
	    			    		
	    		/*=======================================================*/	
    		
	    	}catch(Exception exception){
	    		strView.getTheFrame().showErrorMessageDialog("STR : Execute With Analyzer",exception.getMessage());
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
			
    		if(strView.isSelectedAntiSymmetric()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateAntiSymmetricOcl(
    					strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
    			);
    		}		
    		if(strView.isSelectedIntransitive()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateIntransitiveOcl(
    					strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}	    		
    		if(strView.isSelectedIrreflexive()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateIrreflexiveOcl(
    					strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}		
    		if(strView.isSelectedReflexive()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateReflexiveOcl(
    					strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}							
    		if(strView.isSelectedSymmetric()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateSymmetricOcl(
    					strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}    		
    		if(strView.isSelectedTransitive()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateTransitiveOcl(
    					strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}    		
    		strView.getTheFrame().getManager().getOCLView().getOcleditor().addText(constraints);
	    }
	}	
}
