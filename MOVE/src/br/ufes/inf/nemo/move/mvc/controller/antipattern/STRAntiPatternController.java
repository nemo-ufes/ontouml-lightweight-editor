package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.antipattern.STRAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.STRAntiPatternView;

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
	    		
	    		strModel.getSTRAntiPattern().setSelected(strView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser());
	    		
	    		strView.getTheFrame().getManager().getAlloyModel().setAlloyModel(strView.getTheFrame().getManager().getOntoUMLModel(),strView.getTheFrame().getManager().getOntoUMLOptionModel());
	    		strView.getTheFrame().getManager().getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		
	    		String content = FileUtil.readFile(strView.getTheFrame().getManager().getAlloyModel().getAlloyPath());
	    		String alsPath = strView.getTheFrame().getManager().getAlloyModel().getDirectory()+strView.getTheFrame().getManager().getAlloyModel().getAlloyModelName()+"$STR"+strModel.getId()+".als";	
	    		FileUtil.copyStringToFile(content+"\n"+predicates, alsPath);
			
	    		strView.getTheFrame().getManager().doOpeningAlloy(true,-1);	
    		
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
    		strView.getTheFrame().getConsole().write(constraints);
    		strView.getTheFrame().ShowConsole();
	    }
	}	
}
