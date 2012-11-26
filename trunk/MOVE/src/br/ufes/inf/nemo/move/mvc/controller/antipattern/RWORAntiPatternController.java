package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RWORAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.RWORAntiPatternView;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ontouml2alloy.options.Options;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

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
	    				rworView.getTheFrame().getOntoUMLModel().getOntoUMLParser(),
	    				rworView.getScope()
	    			);
	    		}
			
	    		if(rworView.isSelectedOverlapping())				
	    		{
	    			predicates += "\n\n"+rworModel.getRWORAntiPattern().generateOverlappingPredicate(
	    				rworView.getTheFrame().getOntoUMLModel().getOntoUMLParser(), 
	    				rworView.getScope()
	    			); 
	    		}
	    			    		
	    		if(rworView.isSelectedCustomizedExclusive())				
	    		{
	    			predicates += "\n\n"+rworModel.getRWORAntiPattern().generateMultipleExclusivePredicate(
	    				rworView.getTableView().getMediationsMatrix(),
	    				rworView.getTheFrame().getOntoUMLModel().getOntoUMLParser(), 
	    				rworView.getScope()
	    			); 
	    		}
								
	    		String alsPath = AlloyModel.alsOutDirectory+
	    				rworView.getTheFrame().getAlloyModel().getAlloyModelName()+"$RWOR"+rworModel.getId()+".als";		
						
	    		Options opt = rworView.getTheFrame().getOptionModel().getOptions();
			
	    		RefOntoUML.Package refmodel = rworView.getTheFrame().getOntoUMLModel().getOntoUMLModelInstance();		
			
	    		OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
						
	    		FileUtil.writeToFile(predicates, alsPath);
			
	    		if (opt.openAnalyzer)
	    		{
	    			AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", AlloyModel.alsOutDirectory);
				
	    			String[] argsAnalyzer = { "","" };
	    			argsAnalyzer[0] = alsPath;
	    			argsAnalyzer[1] = AlloyModel.alsOutDirectory + "standart_theme.thm"	;	
	    			SimpleGUICustom.main(argsAnalyzer);
	    		}
	    		
	    	}catch(Exception exception){
	    		JOptionPane.showMessageDialog(rworView.getTheFrame(),exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
    			constraints += "\n\n"+rworModel.getRWORAntiPattern().generateExclusiveOcl(
    				rworView.getTableView().getRolesArrayList()
    			);
    		}		
    		if(rworView.isSelectedOverlapping())				
    		{
    			// nothing to do...
    		}    			    		
    		if(rworView.isSelectedCustomizedExclusive())				
    		{
    			Integer size = rworView.getTableView().getRolesMatrix().size();
    			for(int i = 0; i< size; i++)
    			{
    				constraints += "\n\n"+rworModel.getRWORAntiPattern().generateExclusiveOcl(rworView.getTableView().getRolesMatrix().get(i));
    			}
    			
    		}    		
    		rworView.getTheFrame().getConsole().write(constraints);
    		rworView.getTheFrame().ShowConsole();
	    }
	}
}
