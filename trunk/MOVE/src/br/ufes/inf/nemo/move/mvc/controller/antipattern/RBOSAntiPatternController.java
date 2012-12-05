package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RBOSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.RBOSAntiPatternView;

/**
 * @author John Guerson
 */

public class RBOSAntiPatternController {

	private RBOSAntiPatternView rbosView;
	private RBOSAntiPatternModel rbosModel;
	
	/**
	 * Constructor.
	 * 
	 * @param rbosView
	 * @param rbosModel
	 */
	public RBOSAntiPatternController(RBOSAntiPatternView rbosView, RBOSAntiPatternModel rbosModel)
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
				
	    		if(rbosView.isSelectedReflexive()) 
	    		{
	    			predicates += "\n\n"+rbosModel.getRBOSAntiPattern().generateReflexivePredicate(
	    				rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()
	    			);
	    		}
			
	    		if(rbosView.isSelectedIrreflexive())				
	    		{
	    			predicates += "\n\n"+rbosModel.getRBOSAntiPattern().generateIrreflexivePredicate(
	    					rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser() 
	    			); 
	    		}
					
	    		rbosModel.getRBOSAntiPattern().setSelected(rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser());
	    		
	    		rbosView.getTheFrame().getManager().getAlloyModel().setAlloyModel(rbosView.getTheFrame().getManager().getOntoUMLModel(),rbosView.getTheFrame().getManager().getOntoUMLOptionModel());
	    		rbosView.getTheFrame().getManager().getOntoUMLOptionModel().getOptions().identityPrinciple = false;
	    		
	    		String content = FileUtil.readFile(rbosView.getTheFrame().getManager().getAlloyModel().getAlloyPath());
	    		String alsPath = AlloyModel.alsOutDirectory+rbosView.getTheFrame().getManager().getAlloyModel().getAlloyModelName()+"$RBOS"+rbosModel.getId()+".als";	
	    		FileUtil.copyStringToFile(content+"\n"+predicates, alsPath);
			
	    		rbosView.getTheFrame().getManager().OpenAlloyModelWithAnalyzer(alsPath,AlloyModel.alsOutDirectory);	 
	    		 		
	    		
	    	}catch(Exception exception){
	    		JOptionPane.showMessageDialog(rbosView.getTheFrame(),exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
			
    		if(rbosView.isSelectedReflexive()) 
    		{
    			constraints += "\n\n"+rbosModel.getRBOSAntiPattern().generateReflexiveOcl(rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser());
    		}
		
    		if(rbosView.isSelectedIrreflexive())				
    		{
    			constraints += "\n\n"+rbosModel.getRBOSAntiPattern().generateIrreflexiveOcl(rbosView.getTheFrame().getManager().getOntoUMLModel().getOntoUMLParser()); 
    		}
    		
    		rbosView.getTheFrame().getConsole().write(constraints);
    		rbosView.getTheFrame().ShowConsole();
	    }
	}	
}
