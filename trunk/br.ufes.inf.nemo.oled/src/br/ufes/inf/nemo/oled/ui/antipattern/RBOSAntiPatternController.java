package br.ufes.inf.nemo.oled.ui.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.antipattern.rbos.RBOSAntiPattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class RBOSAntiPatternController {

	private RBOSAntiPatternPane rbosView;
	private RBOSAntiPattern rbosModel;
	
	/**
	 * Constructor.
	 * 
	 * @param rbosView
	 * @param rbosModel
	 */
	public RBOSAntiPatternController(RBOSAntiPatternPane rbosView, RBOSAntiPattern rbosModel)
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
	    		OntoUMLParser refparser = ProjectBrowser.getParserFor(rbosView.getFrame().getDiagramManager().getCurrentProject());
	    		UmlProject project = rbosView.getFrame().getDiagramManager().getCurrentProject();
	    		OntoUML2AlloyOptions refOptions = ProjectBrowser.getOntoUMLOptionsFor(project);
	    		
	    		if(rbosView.isSelectedAntiSymmetric()) 
	    		{
	    			predicates += "\n\n"+rbosModel.generateAntisymmetricPredicate(
	    				refparser
	    			);
	    		}
			
	    		if(rbosView.isSelectedIntransitive()) 
	    		{
	    			predicates += "\n\n"+rbosModel.generateIntransitivePredicate(
	    				refparser
	    			);
	    		}	    		
	    		
	    		if(rbosView.isSelectedIrreflexive()) 
	    		{
	    			predicates += "\n\n"+rbosModel.generateIrreflexivePredicate(
	    				refparser
	    			);
	    		}
			
	    		if(rbosView.isSelectedReflexive()) 
	    		{
	    			predicates += "\n\n"+rbosModel.generateReflexivePredicate(
	    				refparser
	    			);
	    		}
								
	    		if(rbosView.isSelectedSymmetric()) 
	    		{
	    			predicates += "\n\n"+rbosModel.generateSymmetricPredicate(
	    				refparser
	    			);
	    		}
	    		
	    		if(rbosView.isSelectedTransitive()) 
	    		{
	    			predicates += "\n\n"+rbosModel.generateTransitivePredicate(
	    				refparser
	    			);
	    		}
	    							
	    		if(rbosView.isSelectedDisjoint()) 
	    		{
	    			predicates += "\n\n"+rbosModel.generateDisjointPredicate(
	    				refparser
	    			);
	    		}

	    		AlloySpecification alloymodel = new AlloySpecification();
	    		
	    		//set parser...
	    		rbosModel.setSelected(refparser);
	    		
	    		// set options to false, because the simulated model is partial
	    		refOptions.identityPrinciple = false;
	    		refOptions.relatorAxiom = false;
	    		refOptions.weakSupplementationAxiom = false;
	    		refOptions.antiRigidity = false;
	    		
	    		// set alloy path
	    		String alsPath = project.getTempDir()+"rbos.als";	    		
	    		alloymodel.setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		alloymodel.setAlloyModel(refparser,refOptions);	    		
	    		String content = alloymodel.getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		alloymodel.setContent(content);
	    		
	    		// open alloy model
	    		rbosView.getFrame().getDiagramManager().openAlloyAnalyzer(alloymodel,true,-1);
	    
	    	}catch(Exception exception){
	    		rbosView.getFrame().showErrorMessageDialog("Executing RBOS AntiPattern",exception.getMessage());
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
			
			OntoUMLParser refparser = ProjectBrowser.getParserFor(rbosView.getFrame().getDiagramManager().getCurrentProject());
			
    		if(rbosView.isSelectedReflexive()) 
    		{
    			constraints += rbosModel.generateReflexiveOcl(refparser);
    		}
		
    		if(rbosView.isSelectedIrreflexive())				
    		{
    			if(rbosView.isSelectedReflexive())
    				constraints += "\n\n"; 
    			constraints += rbosModel.generateIrreflexiveOcl(refparser); 
    		}
    		
    		rbosView.getFrame().getInfoManager().addConstraints(constraints);
	    }
	}	
}
