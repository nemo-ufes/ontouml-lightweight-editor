package br.ufes.inf.nemo.oled.ui.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.antipattern.ac.ACAntiPattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class ACAntiPatternController {

	private ACAntiPatternPane acView;
	private ACAntiPattern acModel;
	
	/**
	 * Constructor.
	 * 
	 * @param acView
	 * @param acModel
	 */
	public ACAntiPatternController(ACAntiPatternPane acView, ACAntiPattern acModel)
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
	    		OntoUMLParser refparser = ProjectBrowser.getParserFor(acView.getFrame().getDiagramManager().getCurrentProject());
	    		UmlProject project = acView.getFrame().getDiagramManager().getCurrentProject();
	    		OntoUML2AlloyOptions refOptions = ProjectBrowser.getOntoUMLOptionsFor(project);
	    			    		
	    		if(acView.isSelectedOpenCycle()) 
	    		{
	    			predicates += "\n\n"+acModel.generatePredicate(
	    				refparser, acView.getScope(), acModel.OPEN
	    			);
	    		}
			
	    		if(acView.isSelectedClosedCycle())				
	    		{
	    			predicates += "\n\n"+acModel.generatePredicate(
	    				refparser, acView.getScope(), acModel.CLOSED
	    			); 
	    		}
				
	    		//set parser...
	    		refparser= acModel.setSelected(refparser);
	    			    		
	    		// set options to false, because the simulated model is partial
	    		refOptions.identityPrinciple = false;
	    		refOptions.relatorAxiom = false;
	    		refOptions.weakSupplementationAxiom = false;
	    		refOptions.antiRigidity = false;
	    		
	    		// set alloy path
	    		AlloySpecification alloymodel = new AlloySpecification();
	    		String alsPath = project.getTempDir()+"ac.als";	    		
	    		alloymodel.setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		alloymodel.setAlloyModel(refparser,refOptions);	    		
	    		String content = alloymodel.getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		alloymodel.setContent(content);
	    		
	    		ProjectBrowser.setParserFor(project, refparser);
	    		ProjectBrowser.setOntoUMLOptionsFor(project, refOptions);	    		
	    		
	    		// open alloy model	    		
	    		acView.getFrame().getDiagramManager().openAlloyAnalyzer(alloymodel,true,-1);
	    		
	    	}catch(Exception exception){
	    		acView.getFrame().showErrorMessageDialog("Executing AC AntiPattern",exception.getMessage());
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
			
			OntoUMLParser refparser = ProjectBrowser.getParserFor(acView.getFrame().getDiagramManager().getCurrentProject());
			
			if(openCycle) 
				constraints = acModel.generateCycleOcl(acModel.OPEN, refparser)+"\n\n";		
			if(closedCycle)
				constraints += acModel.generateCycleOcl(acModel.CLOSED, refparser)+"\n\n";		
							
			acView.getFrame().getInfoManager().addConstraints(constraints);
	    }
	}
	
}
