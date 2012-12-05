package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.IAAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.IAAntiPatternView;

/**
 * @author John Guerson
 */

public class IAAntiPatternController {
	
	private IAAntiPatternView iaView;
	private IAAntiPatternModel iaModel;
	
	/**
	 * Constructor.
	 * 
	 * @param iaView
	 * @param iaModel
	 */
	public IAAntiPatternController(IAAntiPatternView iaView, IAAntiPatternModel iaModel)
	{
		this.iaView = iaView;
		this.iaModel = iaModel;
			
		iaView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		iaView.addOCLSolutionListener(new OCLSolutionListener());
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
				
	    		if(iaView.isSelectedSourceCustom()) 
	    		{
	    			predicates += "\n\n"+iaModel.getIAAntiPattern().generateSourcePredicate(
	    				iaView.getSourceCustomClassifiers(),
	    				iaView.getTheFrame().getOntoUMLModel().getOntoUMLParser()	    				
	    			);
	    		}
			
	    		if(iaView.isSelectedTargetCustom())				
	    		{
	    			predicates += "\n\n"+iaModel.getIAAntiPattern().generateTargetPredicate(
	    					iaView.getTargetCustomClassifiers(),
	    				iaView.getTheFrame().getOntoUMLModel().getOntoUMLParser()	    			
	    			); 
	    		}
								
	    		iaModel.getIAAntiPattern().setSelected(iaView.getTheFrame().getOntoUMLModel().getOntoUMLParser());
	    		
	    		iaView.getTheFrame().getAlloyModel().setAlloyModel(iaView.getTheFrame().getOntoUMLModel(),iaView.getTheFrame().getOntoUMLOptionModel());
	    		iaView.getTheFrame().getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		
	    		String content = FileUtil.readFile(iaView.getTheFrame().getAlloyModel().getAlloyPath());
	    		String alsPath = AlloyModel.alsOutDirectory+iaView.getTheFrame().getAlloyModel().getAlloyModelName()+"$IA"+iaModel.getId()+".als";	
	    		FileUtil.copyStringToFile(content+"\n"+predicates, alsPath);
			
	    		iaView.getTheFrame().OpenAlloyModelWithAnalyzer(alsPath,AlloyModel.alsOutDirectory);	    		
	    		
	    	}catch(Exception exception){
	    		JOptionPane.showMessageDialog(iaView.getTheFrame(),exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
			
    		if(iaView.isSelectedSourceCustom()) 
    		{
    			constraints += "\n\n"+iaModel.getIAAntiPattern().generateSourceOcl(iaView.getSourceCustomClassifiers(), iaView.getTheFrame().getOntoUMLModel().getOntoUMLParser());
    		}
		
    		if(iaView.isSelectedTargetCustom())				
    		{
    			constraints += "\n\n"+iaModel.getIAAntiPattern().generateTargetOcl(iaView.getTargetCustomClassifiers(), iaView.getTheFrame().getOntoUMLModel().getOntoUMLParser()); 
    		}    		
    		
    		iaView.getTheFrame().getConsole().write(constraints);
    		iaView.getTheFrame().ShowConsole();
	    }
	}
	
}
