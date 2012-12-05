package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.ACAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.ACAntiPatternView;

/**
 * @author John Guerson
 */

public class ACAntiPatternController {

	private ACAntiPatternView acView;
	private ACAntiPatternModel acModel;
	
	/**
	 * Constructor.
	 * 
	 * @param acView
	 * @param acModel
	 */
	public ACAntiPatternController(ACAntiPatternView acView, ACAntiPatternModel acModel)
	{
		this.acView = acView;
		this.acModel = acModel;		
		
		acView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
		acView.addOCLSolutionListener(new OCLSolutionListener());
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
				
	    		if(acView.isSelectedOpenCycle()) 
	    		{
	    			predicates += "\n\n"+acModel.getACAntiPattern().generatePredicate(
	    				acView.getTheFrame().getOntoUMLModel().getOntoUMLParser(),
	    				acView.getScope(), acModel.getACAntiPattern().OPEN
	    			);
	    		}
			
	    		if(acView.isSelectedClosedCycle())				
	    		{
	    			predicates += "\n\n"+acModel.getACAntiPattern().generatePredicate(
	    				acView.getTheFrame().getOntoUMLModel().getOntoUMLParser(), 
	    				acView.getScope(), acModel.getACAntiPattern().CLOSED
	    			); 
	    		}
				
	    		acModel.getACAntiPattern().setSelected(acView.getTheFrame().getOntoUMLModel().getOntoUMLParser());

	    		acView.getTheFrame().getAlloyModel().setAlloyModel(acView.getTheFrame().getOntoUMLModel(),acView.getTheFrame().getOntoUMLOptionModel());
	    		
	    		String content = FileUtil.readFile(acView.getTheFrame().getAlloyModel().getAlloyPath());	    		
	    		String alsPath = AlloyModel.alsOutDirectory+acView.getTheFrame().getAlloyModel().getAlloyModelName()+"$AC"+acModel.getId()+".als";
	    		FileUtil.copyStringToFile(content+"\n"+predicates, alsPath);
	    		
	    		acView.getTheFrame().OpenAlloyModelWithAnalyzer(alsPath,AlloyModel.alsOutDirectory);
	    		
	    	}catch(Exception exception){
	    		JOptionPane.showMessageDialog(acView.getTheFrame(),exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
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
			Boolean openCycle = acView.isSelectedOpenCycle();
			Boolean closedCycle = acView.isSelectedClosedCycle();

			String openCycleConstraint = new String();
			String closedCycleConstraint = new String();
					
			if(openCycle) openCycleConstraint = acModel.getACAntiPattern().generateCycleOcl(acModel.getACAntiPattern().OPEN, acView.getTheFrame().getOntoUMLModel().getOntoUMLParser());		
			if(closedCycle) closedCycleConstraint = acModel.getACAntiPattern().generateCycleOcl(acModel.getACAntiPattern().CLOSED, acView.getTheFrame().getOntoUMLModel().getOntoUMLParser());		
					
			acView.getTheFrame().getConsole().write(openCycleConstraint+"\n\n"+closedCycleConstraint);
			acView.getTheFrame().ShowConsole();
	    }
	}
	
}
