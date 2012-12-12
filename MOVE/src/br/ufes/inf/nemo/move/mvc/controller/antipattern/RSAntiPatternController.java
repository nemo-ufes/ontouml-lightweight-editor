package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.RSAntiPatternView;

/**
 * @author John Guerson
 */

public class RSAntiPatternController {

	private RSAntiPatternView rsView;
	private RSAntiPatternModel rsModel;
	
	/**
	 * Constructor.
	 * 
	 * @param rsView
	 * @param rsModel
	 */
	public RSAntiPatternController(RSAntiPatternView rsView, RSAntiPatternModel rsModel)
	{
		this.rsView = rsView;
		this.rsModel = rsModel;		
		
		rsView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		rsView.addOCLSolutionListener(new OCLSolutionListener());
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
				
	    		if(rsView.isSelectedDisjoint()) 
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(),rsModel.getRSAntiPattern().DISJOINT
	    			);
	    		}
			
	    		if(rsView.isSelectedNonSubsetting())				
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    					rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(), rsModel.getRSAntiPattern().NONSUBSET
	    			); 
	    		}
	    		
	    		if(rsView.isSelectedRedefinition()) 
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(),rsModel.getRSAntiPattern().REDEFINE
	    			);
	    		}
			
	    		if(rsView.isSelectedSubType())				
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    					rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser(), rsModel.getRSAntiPattern().SUBSET
	    			); 
	    		}
				
	    		rsModel.getRSAntiPattern().setSelected(rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser());
	    		
	    		rsView.getTheFrame().getManager().getAlloyModel().setAlloyModel(rsView.getTheFrame().getManager().getOntoUMLModel(),rsView.getTheFrame().getManager().getOntoUMLOptionModel());
	    		rsView.getTheFrame().getManager().getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		
	    		String content = FileUtil.readFile(rsView.getTheFrame().getManager().getAlloyModel().getAlloyPath());
	    		String alsPath = rsView.getTheFrame().getManager().getAlloyModel().getDirectory()+rsView.getTheFrame().getManager().getAlloyModel().getAlloyModelName()+"$RS"+rsModel.getId()+".als";	
	    		FileUtil.copyStringToFile(content+"\n"+predicates, alsPath);
			
	    		rsView.getTheFrame().getManager().doOpeningAlloy(true,-1);	
	    		
	    	}catch(Exception exception){
	    		JOptionPane.showMessageDialog(rsView.getTheFrame(),exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
			try {
				String constraints = new String();
				
	    		if(rsView.isSelectedDisjoint()) 
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().DISJOINT,
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			)+"\n\n";
	    		}			
	    		if(rsView.isSelectedNonSubsetting())				
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().NONSUBSET,
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			)+"\n\n"; 
	    		}	    		
	    		if(rsView.isSelectedRedefinition()) 
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().REDEFINE,
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			)+"\n\n";
	    		}			
	    		if(rsView.isSelectedSubType())				
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().SUBSET,
	    				rsView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			)+"\n\n"; 
	    		}    		
	    		rsView.getTheFrame().getConsole().write(constraints);
	    		rsView.getTheFrame().ShowConsole();
    		
		    }catch(Exception exception){
	    		JOptionPane.showMessageDialog(rsView.getTheFrame(),exception.getMessage(),"Error - RS AntiPattern ",JOptionPane.ERROR_MESSAGE);
	    	}
	    }
	}	
}
