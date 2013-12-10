package br.ufes.inf.nemo.oled.ui.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.antipattern.impabs.ImpAbsOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class ImpAbsAntiPatternController {
	
	private ImpAbsAntiPatternPane impAbsView;
	private ImpAbsOccurrence impAbsModel;
	
	/**
	 * Constructor.
	 * 
	 * @param impAbsView
	 * @param impAbsModel
	 */
	public ImpAbsAntiPatternController(ImpAbsAntiPatternPane impAbsView, ImpAbsOccurrence impAbsModel)
	{
		this.impAbsView = impAbsView;
		this.impAbsModel = impAbsModel;
			
		impAbsView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		impAbsView.addOCLSolutionListener(new OCLSolutionListener());
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
	    		OntoUMLParser refparser = ProjectBrowser.getParserFor(impAbsView.getFrame().getDiagramManager().getCurrentProject());
	    		UmlProject project = impAbsView.getFrame().getDiagramManager().getCurrentProject();
	    		OntoUML2AlloyOptions refOptions = ProjectBrowser.getOntoUMLOptionsFor(project);
	    		
	    		if(impAbsView.isSelectedSourceCustom()) 
	    		{
	    			predicates += "\n\n"+impAbsModel.generateSourcePredicate(
	    				impAbsView.getSourceCustomClassifiers(),
	    				refparser	    				
	    			);
	    		}
			
	    		if(impAbsView.isSelectedTargetCustom())				
	    		{
	    			predicates += "\n\n"+impAbsModel.generateTargetPredicate(
	    				impAbsView.getTargetCustomClassifiers(),
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
	    		impAbsModel.setSelected();
	    		
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
	    		impAbsView.getFrame().getDiagramManager().openAlloyAnalyzer(alloymodel,true,-1);
	    		
	    	}catch(Exception exception){
	    		impAbsView.getFrame().showErrorMessageDialog("Executing IA AntiPattern",exception.getMessage());
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
			
			OntoUMLParser refparser = ProjectBrowser.getParserFor(impAbsView.getFrame().getDiagramManager().getCurrentProject());
			
    		if(impAbsView.isSelectedSourceCustom()) 
    		{
    			constraints += impAbsModel.generateSourceOcl(impAbsView.getSourceCustomClassifiers(), refparser);
    		}
		
    		if(impAbsView.isSelectedTargetCustom())				
    		{
    			if(impAbsView.isSelectedSourceCustom())
    				constraints += "\n\n";
    			constraints += impAbsModel.generateTargetOcl(impAbsView.getTargetCustomClassifiers(), refparser); 
    		}    		
    		
    		impAbsView.getFrame().getInfoManager().addConstraints(constraints);
	    }
	}
	
}
