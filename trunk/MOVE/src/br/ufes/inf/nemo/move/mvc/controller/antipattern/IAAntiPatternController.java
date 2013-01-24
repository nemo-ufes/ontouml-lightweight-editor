package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.antipattern.IAAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.IAAntiPatternView;
import br.ufes.inf.nemo.move.ui.TheManager;

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
	    				iaView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()	    				
	    			);
	    		}
			
	    		if(iaView.isSelectedTargetCustom())				
	    		{
	    			predicates += "\n\n"+iaModel.getIAAntiPattern().generateTargetPredicate(
	    					iaView.getTargetCustomClassifiers(),
	    				iaView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()	    			
	    			); 
	    		}
								
	    		/*iaModel.getIAAntiPattern().setSelected(iaView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser());
	    		
	    		iaView.getTheFrame().getManager().getAlloyModel().setAlloyModel(iaView.getTheFrame().getManager().getOntoUMLModel(),iaView.getTheFrame().getManager().getOntoUMLOptionModel());
	    		iaView.getTheFrame().getManager().getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		
	    		String content = FileUtil.readFile(iaView.getTheFrame().getManager().getAlloyModel().getAlloyPath());
	    		String alsPath = iaView.getTheFrame().getManager().getAlloyModel().getDirectory()+iaView.getTheFrame().getManager().getAlloyModel().getAlloyModelName()+"$IA"+iaModel.getId()+".als";	
	    		FileUtil.copyStringToFile(content+"\n"+predicates, alsPath);
			
	    		iaView.getTheFrame().getManager().doOpeningAlloy(true,-1);	  */
	    		
	    		/* Execute... TODO : JOHN, PLEASE, FIXE MEEE SOON !!!!!!!! */
	    		
	    		/*=======================================================*/
	    		
	    		TheManager manager = iaView.getTheFrame().getManager();
	    		
	    		//set parser...
	    		iaModel.getIAAntiPattern().setSelected(manager.getOntoUMLModel().getOntoUMLParser());
	    		
	    		// set options to false, because the simulated model is partial
	    		manager.getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		manager.getOntoUMLOptionModel().getOptions().relatorConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().weakSupplementationConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().antiRigidity = false;
	    		
	    		// set alloy path
	    		String alsPath = manager.getAlloyModel().getDirectory()+manager.getAlloyModel().getAlloyModelName()+"$AC"+iaModel.getId()+".als";	    		
	    		manager.getAlloyModel().setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		manager.getAlloyModel().setAlloyModel(manager.getOntoUMLModel(),manager.getOntoUMLOptionModel());	    		
	    		String content = manager.getAlloyModel().getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		manager.getAlloyModel().setContent(content);
	    		
	    		// open alloy model
	    		iaView.getTheFrame().getManager().doOpeningAlloy(true,-1);
	    			    		
	    		/*=======================================================*/
	    		
	    	}catch(Exception exception){
	    		iaView.getTheFrame().showErrorMessageDialog("IA : Execute With Analyzer",exception.getMessage());
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
    			constraints += iaModel.getIAAntiPattern().generateSourceOcl(iaView.getSourceCustomClassifiers(), iaView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser());
    		}
		
    		if(iaView.isSelectedTargetCustom())				
    		{
    			if(iaView.isSelectedSourceCustom())
    				constraints += "\n\n";
    			constraints += iaModel.getIAAntiPattern().generateTargetOcl(iaView.getTargetCustomClassifiers(), iaView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()); 
    		}    		
    		
    		iaView.getTheFrame().getManager().getOCLView().getOcleditor().addText(constraints);
	    }
	}
	
}
