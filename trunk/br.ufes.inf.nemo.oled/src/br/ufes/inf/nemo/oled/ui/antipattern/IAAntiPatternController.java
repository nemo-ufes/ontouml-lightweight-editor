package br.ufes.inf.nemo.oled.ui.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.antipattern.ia.IAAntiPattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ModelTree;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class IAAntiPatternController {
	
	private IAAntiPatternPane iaView;
	private IAAntiPattern iaModel;
	
	/**
	 * Constructor.
	 * 
	 * @param iaView
	 * @param iaModel
	 */
	public IAAntiPatternController(IAAntiPatternPane iaView, IAAntiPattern iaModel)
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
	    		OntoUMLParser refparser = ModelTree.getParserFor(iaView.getFrame().getDiagramManager().getCurrentProject());
	    		UmlProject project = iaView.getFrame().getDiagramManager().getCurrentProject();
	    		OntoUML2AlloyOptions refOptions = ModelTree.getOntoUMLOptionsFor(project);
	    		
	    		if(iaView.isSelectedSourceCustom()) 
	    		{
	    			predicates += "\n\n"+iaModel.generateSourcePredicate(
	    				iaView.getSourceCustomClassifiers(),
	    				refparser	    				
	    			);
	    		}
			
	    		if(iaView.isSelectedTargetCustom())				
	    		{
	    			predicates += "\n\n"+iaModel.generateTargetPredicate(
	    				iaView.getTargetCustomClassifiers(),
	    				refparser	    			
	    			); 
	    		}
								
	    		/*iaModel.getIAAntiPattern().setSelected(iaView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser());
	    		
	    		iaView.getTheFrame().getManager().getAlloyModel().setAlloyModel(iaView.getTheFrame().getManager().getOntoUMLModel(),iaView.getTheFrame().getManager().getOntoUMLOptionModel());
	    		iaView.getTheFrame().getManager().getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		
	    		String content = FileUtil.readFile(iaView.getTheFrame().getManager().getAlloyModel().getAlloyPath());
	    		String alsPath = iaView.getTheFrame().getManager().getAlloyModel().getDirectory()+iaView.getTheFrame().getManager().getAlloyModel().getAlloyModelName()+"$IA"+iaModel.getId()+".als";	
	    		FileUtil.copyStringToFile(content+"\n"+predicates, alsPath);
			
	    		iaView.getTheFrame().getManager().doOpeningAlloy(true,-1);	  */
	    		
	    		//set parser...
	    		iaModel.setSelected(refparser);
	    		
	    		// set options to false, because the simulated model is partial
	    		refOptions.identityPrinciple = false;
	    		refOptions.relatorAxiom = false;
	    		refOptions.weakSupplementationAxiom = false;
	    		refOptions.antiRigidity = false;
	    		
	    		AlloySpecification alloymodel = new AlloySpecification();
	    		
	    		// set alloy path
	    		String alsPath = project.getTempDir()+"ia.als";	    		
	    		alloymodel.setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		alloymodel.setAlloyModel(refparser,refOptions);	    		
	    		String content = alloymodel.getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		alloymodel.setContent(content);
	    		
	    		// open alloy model
	    		iaView.getFrame().getDiagramManager().openAlloyAnalyzer(alloymodel,true,-1);
	    		
	    	}catch(Exception exception){
	    		iaView.getFrame().showErrorMessageDialog("Executing IA AntiPattern",exception.getMessage());
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
			
			OntoUMLParser refparser = ModelTree.getParserFor(iaView.getFrame().getDiagramManager().getCurrentProject());
			
    		if(iaView.isSelectedSourceCustom()) 
    		{
    			constraints += iaModel.generateSourceOcl(iaView.getSourceCustomClassifiers(), refparser);
    		}
		
    		if(iaView.isSelectedTargetCustom())				
    		{
    			if(iaView.isSelectedSourceCustom())
    				constraints += "\n\n";
    			constraints += iaModel.generateTargetOcl(iaView.getTargetCustomClassifiers(), refparser); 
    		}    		
    		
    		iaView.getFrame().getDiagramManager().getCurrentWrapper().addConstraints(constraints);
	    }
	}
	
}
