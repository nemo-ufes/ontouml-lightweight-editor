package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RWORAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.RWORAntiPatternView;

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
							
	    		rworModel.getRWORAntiPattern().setSelected(rworView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser());
	    		
	    		rworView.getTheFrame().getManager().getAlloyModel().setAlloyModel(rworView.getTheFrame().getManager().getOntoUMLModel(),rworView.getTheFrame().getManager().getOntoUMLOptionModel());
	    		rworView.getTheFrame().getManager().getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		
	    		String content = FileUtil.readFile(rworView.getTheFrame().getManager().getAlloyModel().getAlloyPath());
	    		String alsPath = rworView.getTheFrame().getManager().getAlloyModel().getDirectory()+rworView.getTheFrame().getManager().getAlloyModel().getAlloyModelName()+"$RWOR"+rworModel.getId()+".als";	
	    		FileUtil.copyStringToFile(content+"\n"+predicates, alsPath);
			
	    		rworView.getTheFrame().getManager().doOpeningAlloy(true,-1);	
    		
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
    		rworView.getTheFrame().getConsole().write(constraints);
    		rworView.getTheFrame().ShowConsole();
	    }
	}
}
