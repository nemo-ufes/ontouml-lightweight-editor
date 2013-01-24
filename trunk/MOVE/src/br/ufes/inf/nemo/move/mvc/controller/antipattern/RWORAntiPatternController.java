package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RWORAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.RWORAntiPatternView;
import br.ufes.inf.nemo.move.ui.TheManager;

/**
 * @author John Guerson
 */

public class RWORAntiPatternController {

	private RWORAntiPatternView rworView;
	private RWORAntiPatternModel rworModel;
	
	/**
	 * Constructor.
	 * 
	 * @param rworView
	 * @param rworModel
	 */
	public RWORAntiPatternController(RWORAntiPatternView rworView, RWORAntiPatternModel rworModel)
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
				
	    		if(rworView.isSelectedExclusive()) 
	    		{
	    			predicates += "\n\n"+rworModel.getRWORAntiPattern().generateExclusivePredicate(
	    				rworView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(),
	    				rworView.getScope()
	    			);
	    		}
			
	    		if(rworView.isSelectedOverlapping())				
	    		{
	    			predicates += "\n\n"+rworModel.getRWORAntiPattern().generateOverlappingPredicate(
	    				rworView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(), 
	    				rworView.getScope()
	    			); 
	    		}
	    			    	
	    		if(rworView.isSelectedDisjoint())				
	    		{
	    			predicates += "\n\n"+rworModel.getRWORAntiPattern().generateDisjointPredicate(
	    				rworView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(), 
	    				rworView.getScope()
	    			); 
	    		}
	    		
	    		if(rworView.isSelectedDisjointFromTable())				
	    		{
	    			predicates += "\n\n"+rworModel.getRWORAntiPattern().generateDisjointFromTablePredicate(
	    				rworView.getTableView().getMediationsMatrix(),
	    				rworView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(), 
	    				rworView.getScope()
	    			); 
	    		}
	    		
	    		if(rworView.isSelectedExclusiveFromTable())				
	    		{
	    			predicates += "\n\n"+rworModel.getRWORAntiPattern().generateMultipleExclusivePredicate(
	    				rworView.getTableView().getMediationsMatrix(),
	    				rworView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(), 
	    				rworView.getScope()
	    			); 
	    		}
							
	    		/* Execute... TODO : JOHN, PLEASE, FIXE MEEE SOON !!!!!!!! */
	    		
	    		/*=======================================================*/
	    		
	    		TheManager manager = rworView.getTheFrame().getManager();
	    		
	    		//set parser...
	    		rworModel.getRWORAntiPattern().setSelected(manager.getOntoUMLModel().getOntoUMLParser());
	    		
	    		// set options to false, because the simulated model is partial
	    		manager.getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		manager.getOntoUMLOptionModel().getOptions().relatorConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().weakSupplementationConstraint = false;
	    		manager.getOntoUMLOptionModel().getOptions().antiRigidity = false;
	    		
	    		// set alloy path
	    		String alsPath = manager.getAlloyModel().getDirectory()+manager.getAlloyModel().getAlloyModelName()+"$AC"+rworModel.getId()+".als";	    		
	    		manager.getAlloyModel().setAlloyModel(alsPath);
	    		
	    		// set alloy model from ontoUML transformation
	    		manager.getAlloyModel().setAlloyModel(manager.getOntoUMLModel(),manager.getOntoUMLOptionModel());	    		
	    		String content = manager.getAlloyModel().getContent();
	    		
	    		// add predicates to alloy content
	    		content = content+"\n"+predicates;	    		
	    		manager.getAlloyModel().setContent(content);
	    		
	    		// open alloy model
	    		rworView.getTheFrame().getManager().doOpeningAlloy(true,-1);
	    			    		
	    		/*=======================================================*/	
    		
	    	}catch(Exception exception){
	    		rworView.getTheFrame().showErrorMessageDialog("RWOR : Execute With Analyzer",exception.getMessage());
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
						
    		if(rworView.isSelectedExclusive()) 
    		{
    			constraints += rworModel.getRWORAntiPattern().generateExclusiveOcl(
    				rworView.getTableView().getRolesArrayList(),
    				rworView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
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
    				constraints += "\n\n"+rworModel.getRWORAntiPattern().generateExclusiveOcl(
    						rworView.getTableView().getRolesMatrix().get(i),
    	    				rworView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
    				)+"\n\n";
    			}
    			
    		}    		
    		rworView.getTheFrame().getManager().getOCLView().getOcleditor().addText(constraints);
	    }
	}
}
