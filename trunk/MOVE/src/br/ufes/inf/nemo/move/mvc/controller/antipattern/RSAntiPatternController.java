package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.RSAntiPatternView;
import br.ufes.inf.nemo.move.ui.TheManager;

/**
 * @author John Guerson
 */

public class RSAntiPatternController {

	private RSAntiPatternView rsView;
	private RSAntiPatternModel rsModel;
	
	/**
	 * Constructor.
	 * 
	 * @param rsView
	 * @param rsModel
	 */
	public RSAntiPatternController(RSAntiPatternView rsView, RSAntiPatternModel rsModel)
	{
		this.rsView = rsView;
		this.rsModel = rsModel;		
		
		rsView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		rsView.addOCLSolutionListener(new OCLSolutionListener());
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
				
	    		if(rsView.isSelectedDisjoint()) 
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(),rsModel.getRSAntiPattern().DISJOINT
	    			);
	    		}
			
	    		if(rsView.isSelectedNonSubsetting())				
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    					rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(), rsModel.getRSAntiPattern().NONSUBSET
	    			); 
	    		}
	    		
	    		if(rsView.isSelectedRedefinition()) 
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(),rsModel.getRSAntiPattern().REDEFINE
	    			);
	    		}
			
	    		if(rsView.isSelectedSubType())				
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    					rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(), rsModel.getRSAntiPattern().SUBSET
	    			); 
	    		}
				
	    		/* Execute... TODO : JOHN, PLEASE, FIXE MEEE SOON !!!!!!!! */
	    		
	    		/*=======================================================*/
	    		
	    		TheManager manager = rsView.getTheFrame().getManager();
	    		
	    		//set parser...
	    		rsModel.getRSAntiPattern().setSelected(manager.getOntoUMLModel().getOntoUMLParser());
	    		
	    		// set options to false, because the simulated model is partial
	    		manager.getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		manager.getOntoUMLOptionModel().getOptions().relatorConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().weakSupplementationConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().antiRigidity = false;
	    		
	    		// set alloy path
	    		String alsPath = manager.getAlloyModel().getDirectory()+manager.getAlloyModel().getAlloyModelName()+"$AC"+rsModel.getId()+".als";	    		
	    		manager.getAlloyModel().setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		manager.getAlloyModel().setAlloyModel(manager.getOntoUMLModel(),manager.getOntoUMLOptionModel());	    		
	    		String content = manager.getAlloyModel().getContent();
	    		System.out.println(content);
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		manager.getAlloyModel().setContent(content);
	    		
	    		// open alloy model
	    		rsView.getTheFrame().getManager().doOpeningAlloy(true,-1);
	    			    		
	    		/*=======================================================*/
	    		
	    	}catch(Exception exception){
	    		rsView.getTheFrame().showErrorMessageDialog("RS : Execute With Analyzer",exception.getMessage());
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
			try {
				String constraints = new String();
				
	    		if(rsView.isSelectedDisjoint()) 
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().DISJOINT,
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			)+"\n\n";
	    		}			
	    		if(rsView.isSelectedNonSubsetting())				
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().NONSUBSET,
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			)+"\n\n"; 
	    		}	    		
	    		if(rsView.isSelectedRedefinition()) 
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().REDEFINE,
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			)+"\n\n";
	    		}			
	    		if(rsView.isSelectedSubType())				
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().SUBSET,
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			)+"\n\n"; 
	    		}    		
	    		rsView.getTheFrame().getConsole().write(constraints);
	    		rsView.getTheFrame().ShowConsole();
    		
		    }catch(Exception exception){
		    	rsView.getTheFrame().showErrorMessageDialog("RS : Generate OCL",exception.getMessage());
	    	}
	    }
	}	
}
