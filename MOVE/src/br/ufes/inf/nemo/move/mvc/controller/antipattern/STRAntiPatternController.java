package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
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
	    				cardinality,strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
			
	    		if(strView.isSelectedIntransitive()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateIntransitivePredicate(
	    				cardinality,strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}	    		
	    		
	    		if(strView.isSelectedIrreflexive()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateIrreflexivePredicate(
	    				cardinality,strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
			
	    		if(strView.isSelectedReflexive()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateReflexivePredicate(
	    				cardinality,strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
								
	    		if(strView.isSelectedSymmetric()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateSymmetricPredicate(
	    				cardinality,strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
	    		
	    		if(strView.isSelectedTransitive()) 
	    		{
	    			predicates += "\n\n"+strModel.getSTRAntiPattern().generateTransitivePredicate(
	    				cardinality,strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
	    		
	    		strModel.getSTRAntiPattern().setSelected(strView.getTheFrame().getOntoUMLModel().getOntoUMLParser());
	    		
	    		strView.getTheFrame().TransformsOntoUMLIntoAlloy();
	    		//strView.getTheFrame().TransformsOCLIntoAlloy();
	    		
	    		String content = FileUtil.readFile(strView.getTheFrame().getAlloyModel().getAlloyPath());
	    		String alsPath = AlloyModel.alsOutDirectory+strView.getTheFrame().getAlloyModel().getAlloyModelName()+"$STR"+strModel.getId()+".als";	
	    		FileUtil.copyStringToFile(content+"\n"+predicates, alsPath);
			
	    		strView.getTheFrame().OpenAlloyModelWithAnalyzer(alsPath,AlloyModel.alsOutDirectory);	
    		
	    	}catch(Exception exception){
	    		JOptionPane.showMessageDialog(strView.getTheFrame(),exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
    					strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
    			);
    		}		
    		if(strView.isSelectedIntransitive()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateIntransitiveOcl(
    					strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}	    		
    		if(strView.isSelectedIrreflexive()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateIrreflexiveOcl(
    					strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}		
    		if(strView.isSelectedReflexive()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateReflexiveOcl(
    					strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}							
    		if(strView.isSelectedSymmetric()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateSymmetricOcl(
    					strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}    		
    		if(strView.isSelectedTransitive()) 
    		{
    			constraints += "\n\n"+strModel.getSTRAntiPattern().generateTransitiveOcl(
    					strView.getTheFrame().getOntoUMLModel().getOntoUMLParser()
    	    			);
    		}    		
    		strView.getTheFrame().getConsole().write(constraints);
    		strView.getTheFrame().ShowConsole();
	    }
	}	
}
