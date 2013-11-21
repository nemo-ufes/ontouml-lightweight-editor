package br.ufes.inf.nemo.oled.ui.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.antipattern.relover.RelOverAntipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.oled.model.AlloySpecification;
import br.ufes.inf.nemo.oled.model.UmlProject;
import br.ufes.inf.nemo.oled.ui.ProjectBrowser;
import br.ufes.inf.nemo.ontouml2alloy.OntoUML2AlloyOptions;

/**
 * @author John Guerson
 */

public class RWORAntiPatternController {

	private RWORAntiPatternPane rworView;
	private RelOverAntipattern rworModel;
	
	/**
	 * Constructor.
	 * 
	 * @param rworView
	 * @param rworModel
	 */
	public RWORAntiPatternController(RWORAntiPatternPane rworView, RelOverAntipattern rworModel)
	{
		this.rworView = rworView;
		this.rworModel =rworModel;		
		
		rworView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		rworView.addEmptyEntryRoleTableListener(new EmptyEntryRoleTableListener());
		rworView.addOCLSolutionListener(new OCLSolutionListener());
	}
	
	/**
	 * Add Empty Entry Role Table Action Listener
	 * 
	 * @author John
	 */
	class EmptyEntryRoleTableListener implements ActionListener 
	{
	    public void actionPerformed(ActionEvent e) 
	    {			
	    	rworView.getTableView().addEmptyRow();
	    }
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
	    		rworView.getTableView();
	    		
	    		String predicates = new String();
	    		OntoUMLParser refparser = ProjectBrowser.getParserFor(rworView.getFrame().getDiagramManager().getCurrentProject());
	    		UmlProject project = rworView.getFrame().getDiagramManager().getCurrentProject();
	    		OntoUML2AlloyOptions refOptions = ProjectBrowser.getOntoUMLOptionsFor(project);	    		
				
	    		if(rworView.isSelectedExclusive()) 
	    		{
	    			predicates += "\n\n"+rworModel.generateExclusivePredicate(
	    				refparser,
	    				rworView.getScope()
	    			);
	    		}
			
	    		if(rworView.isSelectedOverlapping())				
	    		{
	    			predicates += "\n\n"+rworModel.generateOverlappingPredicate(
	    				refparser, 
	    				rworView.getScope()
	    			); 
	    		}
	    			    	
	    		if(rworView.isSelectedDisjoint())				
	    		{
	    			predicates += "\n\n"+rworModel.generateDisjointPredicate(
	    				refparser, 
	    				rworView.getScope()
	    			); 
	    		}
	    		
	    		if(rworView.isSelectedDisjointFromTable())				
	    		{
	    			predicates += "\n\n"+rworModel.generateDisjointFromTablePredicate(
	    				rworView.getTableView().getMediationsMatrix(),
	    				refparser, 
	    				rworView.getScope()
	    			); 
	    		}
	    		
	    		if(rworView.isSelectedExclusiveFromTable())				
	    		{
	    			predicates += "\n\n"+rworModel.generateMultipleExclusivePredicate(
	    				rworView.getTableView().getMediationsMatrix(),
	    				refparser, 
	    				rworView.getScope()
	    			); 
	    		}	    		
	    		
	    		//set parser...
	    		rworModel.setSelected(refparser);
	    		
	    		// set options to false, because the simulated model is partial
	    		refOptions.identityPrinciple = false;
	    		refOptions.relatorAxiom = false;
	    		refOptions.weakSupplementationAxiom = false;
	    		refOptions.antiRigidity = false;
	    		
	    		AlloySpecification alloymodel = ProjectBrowser.getAlloySpecFor(project);
	    		
	    		// set alloy path
	    		String alsPath = project.getTempDir()+"rwor.als";	    		
	    		alloymodel.setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		alloymodel.setAlloyModel(refparser,refOptions);	    		
	    		String content = alloymodel.getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		alloymodel.setContent(content);
	    		
	    		// open alloy model
	    		rworView.getFrame().getDiagramManager().openAlloyAnalyzer(alloymodel,true,-1);
	    		
	    	}catch(Exception exception){
	    		rworView.getFrame().showErrorMessageDialog("Executing RWOR AntiPattern",exception.getMessage());
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
						
			OntoUMLParser refparser = ProjectBrowser.getParserFor(rworView.getFrame().getDiagramManager().getCurrentProject());
			
    		if(rworView.isSelectedExclusive()) 
    		{
    			constraints += rworModel.generateExclusiveOcl(
    				rworView.getTableView().getRolesArrayList(),
    				refparser
    			)+"\n\n";
    		}		
    		if(rworView.isSelectedOverlapping())				
    		{
    			// nothing to do...
    		}    			    		
    		if(rworView.isSelectedExclusiveFromTable())				
    		{
    			Integer size = rworView.getTableView().getRolesMatrix().size();
    			for(int i = 0; i< size; i++)
    			{
    				constraints += "\n\n"+rworModel.generateExclusiveOcl(
    						rworView.getTableView().getRolesMatrix().get(i),
    	    				refparser
    				)+"\n\n";
    			}
    			
    		}    		
    		rworView.getFrame().getInfoManager().addConstraints(constraints);
	    }
	}
}
