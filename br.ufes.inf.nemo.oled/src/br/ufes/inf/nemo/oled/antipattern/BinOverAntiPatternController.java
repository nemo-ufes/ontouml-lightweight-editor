package br.ufes.inf.nemo.oled.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.antipattern.binover.BinOverVariation1Occurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class BinOverAntiPatternController {

	private BinOverAntiPatternPane strView;
	private BinOverVariation1Occurrence strModel;
	
	/**
	 * Constructor.
	 * 
	 * @param strView
	 * @param strModel
	 */
	public BinOverAntiPatternController(BinOverAntiPatternPane strView, BinOverVariation1Occurrence strModel)
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
				OntoUMLParser refparser = ProjectBrowser.getParserFor(strView.getFrame().getDiagramManager().getCurrentProject());
	    		UmlProject project = strView.getFrame().getDiagramManager().getCurrentProject();
	    		OntoUML2AlloyOptions refOptions = ProjectBrowser.getOntoUMLOptionsFor(project);
	    		
	    		if(strView.isSelectedAntiSymmetric()) 
	    		{
	    			predicates += "\n\n"+strModel.generateAntisymmetricPredicate(
	    				cardinality,refparser
	    			);
	    		}
			
	    		if(strView.isSelectedIntransitive()) 
	    		{
	    			predicates += "\n\n"+strModel.generateIntransitivePredicate(
	    				cardinality,refparser
	    			);
	    		}	    		
	    		
	    		if(strView.isSelectedIrreflexive()) 
	    		{
	    			predicates += "\n\n"+strModel.generateIrreflexivePredicate(
	    				cardinality,refparser
	    			);
	    		}
			
	    		if(strView.isSelectedReflexive()) 
	    		{
	    			predicates += "\n\n"+strModel.generateReflexivePredicate(
	    				cardinality,refparser
	    			);
	    		}
								
	    		if(strView.isSelectedSymmetric()) 
	    		{
	    			predicates += "\n\n"+strModel.generateSymmetricPredicate(
	    				cardinality,refparser
	    			);
	    		}
	    		
	    		if(strView.isSelectedTransitive()) 
	    		{
	    			predicates += "\n\n"+strModel.generateTransitivePredicate(
	    				cardinality,refparser
	    			);
	    		}
	    		
	    		
	    		AlloySpecification alloymodel = new AlloySpecification();
	    		
	    		//set parser...
	    		strModel.setSelected();
	    		
	    		// set options to false, because the simulated model is partial
	    		refOptions.identityPrinciple = false;
	    		refOptions.relatorAxiom = false;
	    		refOptions.weakSupplementationAxiom = false;
	    		refOptions.antiRigidity = false;
	    		
	    		// set alloy path
	    		String alsPath = project.getTempDir()+"str.als";	    		
	    		alloymodel.setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		alloymodel.setAlloyModel(refparser,refOptions);	    		
	    		String content = alloymodel.getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		alloymodel.setContent(content);
	    		
	    		// open alloy model
	    		strView.getFrame().getDiagramManager().openAlloyAnalyzer(alloymodel,true,-1);
	    		
	    	}catch(Exception exception){
	    		strView.getFrame().showErrorMessageDialog("Executing STR AntiPattern",exception.getMessage());
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
			OntoUMLParser refparser = ProjectBrowser.getParserFor(strView.getFrame().getDiagramManager().getCurrentProject());
			
    		if(strView.isSelectedAntiSymmetric()) 
    		{
    			constraints += "\n\n"+strModel.generateAntiSymmetricOcl(
    					refparser
    			);
    		}		
    		if(strView.isSelectedIntransitive()) 
    		{
    			constraints += "\n\n"+strModel.generateIntransitiveOcl(
    					refparser
    	    			);
    		}	    		
    		if(strView.isSelectedIrreflexive()) 
    		{
    			constraints += "\n\n"+strModel.generateIrreflexiveOcl(
    					refparser
    	    			);
    		}		
    		if(strView.isSelectedReflexive()) 
    		{
    			constraints += "\n\n"+strModel.generateReflexiveOcl(
    					refparser
    	    			);
    		}							
    		if(strView.isSelectedSymmetric()) 
    		{
    			constraints += "\n\n"+strModel.generateSymmetricOcl(
    					refparser
    	    			);
    		}    		
    		if(strView.isSelectedTransitive()) 
    		{
    			constraints += "\n\n"+strModel.generateTransitiveOcl(
    					refparser
    	    			);
    		}    		
			strView.getFrame().getInfoManager().addConstraints(constraints);
	    }
	}	
}
