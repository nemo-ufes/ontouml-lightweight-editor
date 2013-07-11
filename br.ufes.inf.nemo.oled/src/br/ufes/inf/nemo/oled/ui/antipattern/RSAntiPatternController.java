package br.ufes.inf.nemo.oled.ui.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.antipattern.RSAntiPattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ModelTree;
import br.ufes.inf.nemo.ontouml2alloy.Onto2AlloyOptions;

/**
 * @author John Guerson
 */

public class RSAntiPatternController {

	private RSAntiPatternPane rsView;
	private RSAntiPattern rsModel;
	
	/**
	 * Constructor.
	 * 
	 * @param rsView
	 * @param rsModel
	 */
	public RSAntiPatternController(RSAntiPatternPane rsView, RSAntiPattern rsModel)
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
	    		OntoUMLParser refparser = ModelTree.getParserFor(rsView.getFrame().getDiagramManager().getCurrentProject());
	    		UmlProject project = rsView.getFrame().getDiagramManager().getCurrentProject();
	    		Onto2AlloyOptions refOptions = ModelTree.getOntoUMLOptionsFor(project);
	    		
	    		if(rsView.isSelectedDisjoint()) 
	    		{
	    			predicates += "\n\n"+rsModel.generatePredicate(
	    				refparser,rsModel.DISJOINT
	    			);
	    		}
			
	    		if(rsView.isSelectedNonSubsetting())				
	    		{
	    			predicates += "\n\n"+rsModel.generatePredicate(
	    					refparser, rsModel.NONSUBSET
	    			); 
	    		}
	    		
	    		if(rsView.isSelectedRedefinition()) 
	    		{
	    			predicates += "\n\n"+rsModel.generatePredicate(
	    				refparser,rsModel.REDEFINE
	    			);
	    		}
			
	    		if(rsView.isSelectedSubType())				
	    		{
	    			predicates += "\n\n"+rsModel.generatePredicate(
	    					refparser, rsModel.SUBSET
	    			); 
	    		}
				
	    		//set parser...
	    		rsModel.setSelected(refparser);
	    		
	    		// set options to false, because the simulated model is partial
	    		refOptions.identityPrinciple = false;
	    		refOptions.relatorAxiom = false;
	    		refOptions.weakSupplementationAxiom = false;
	    		refOptions.antiRigidity = false;
	    		
	    		AlloySpecification alloymodel = new AlloySpecification();
	    		
	    		// set alloy path
	    		String alsPath = project.getTempDir()+"rs.als";	    		
	    		alloymodel.setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		alloymodel.setAlloyModel(refparser,refOptions);	    		
	    		String content = alloymodel.getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		alloymodel.setContent(content);
	    		
	    		// open alloy model
	    		rsView.getFrame().getDiagramManager().openAlloyAnalyzer(alloymodel,true,-1);
	    		
	    	}catch(Exception exception){
	    		rsView.getFrame().showErrorMessageDialog("Executing RS AntiPattern",exception.getMessage());
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
				OntoUMLParser refparser = ModelTree.getParserFor(rsView.getFrame().getDiagramManager().getCurrentProject());
				
	    		if(rsView.isSelectedDisjoint()) 
	    		{
	    			constraints += ""+rsModel.generateOcl(
	    				rsModel.DISJOINT,
	    				refparser
	    			)+"\n\n";
	    		}			
	    		if(rsView.isSelectedNonSubsetting())				
	    		{
	    			constraints += ""+rsModel.generateOcl(
	    				rsModel.NONSUBSET,
	    				refparser
	    			)+"\n\n"; 
	    		}	    		
	    		if(rsView.isSelectedRedefinition()) 
	    		{
	    			constraints += ""+rsModel.generateOcl(
	    				rsModel.REDEFINE,
	    				refparser
	    			)+"\n\n";
	    		}			
	    		if(rsView.isSelectedSubType())				
	    		{
	    			constraints += ""+rsModel.generateOcl(
	    				rsModel.SUBSET,
	    				refparser
	    			)+"\n\n"; 
	    		}    		

	    		rsView.getFrame().getDiagramManager().getCurrentWrapper().addConstraints(constraints);
    		
		    }catch(Exception exception){
		    	rsView.getFrame().showErrorMessageDialog("Solving RS AntiPattern",exception.getMessage());
		    	exception.printStackTrace();
	    	}
	    }
	}	
}
