package br.ufes.inf.nemo.oled.antipattern.old;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.antipattern.asscyc.AssCycOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.ProjectBrowser;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class AssCycAntiPatternController {

	private AssCycAntiPatternPane assCycView;
	private AssCycOccurrence assCycModel;
	
	/**
	 * Constructor.
	 * 
	 * @param assCycView
	 * @param acModel
	 */
	public AssCycAntiPatternController(AssCycAntiPatternPane assCycView, AssCycOccurrence acModel)
	{
		this.assCycView = assCycView;
		this.assCycModel = acModel;		
		
		assCycView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		assCycView.addOCLSolutionListener(new OCLSolutionListener());
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
	    		OntoUMLParser refparser = ProjectBrowser.getParserFor(assCycView.getFrame().getDiagramManager().getCurrentProject());
	    		UmlProject project = assCycView.getFrame().getDiagramManager().getCurrentProject();
	    		OntoUML2AlloyOptions refOptions = ProjectBrowser.getOntoUMLOptionsFor(project);
	    			    		
	    		if(assCycView.isSelectedOpenCycle()) 
	    		{
	    			predicates += "\n\n"+assCycModel.generatePredicate(
	    				refparser, assCycView.getScope(), assCycModel.OPEN
	    			);
	    		}
			
	    		if(assCycView.isSelectedClosedCycle())				
	    		{
	    			predicates += "\n\n"+assCycModel.generatePredicate(
	    				refparser, assCycView.getScope(), assCycModel.CLOSED
	    			); 
	    		}
				
	    		//set parser...
	    		refparser= assCycModel.setSelected();
	    			    		
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
	    		assCycView.getFrame().getDiagramManager().openAlloyAnalyzer(alloymodel,true,-1);
	    		
	    	}catch(Exception exception){
	    		assCycView.getFrame().showErrorMessageDialog("Executing AC AntiPattern",exception.getMessage());
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
			Boolean openCycle = assCycView.isSelectedOpenCycle();
			Boolean closedCycle = assCycView.isSelectedClosedCycle();

			String constraints = new String();
			
			OntoUMLParser refparser = ProjectBrowser.getParserFor(assCycView.getFrame().getDiagramManager().getCurrentProject());
			
			if(openCycle) 
				constraints = assCycModel.generateCycleOcl(assCycModel.OPEN, refparser)+"\n\n";		
			if(closedCycle)
				constraints += assCycModel.generateCycleOcl(assCycModel.CLOSED, refparser)+"\n\n";		
							
			assCycView.getFrame().getInfoManager().addConstraints(constraints);
	    }
	}
	
}
