package br.ufes.inf.nemo.move.mvc.controller.antipattern;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.mvc.model.AlloyModel;
import br.ufes.inf.nemo.move.mvc.model.antipattern.RSAntiPatternModel;
import br.ufes.inf.nemo.move.mvc.view.antipattern.RSAntiPatternView;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ontouml2alloy.options.OntoUMLOptions;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

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
	    				rsView.getTheFrame().getOntoUMLModel().getOntoUMLParser(),rsModel.getRSAntiPattern().DISJOINT
	    			);
	    		}
			
	    		if(rsView.isSelectedNonSubsetting())				
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    					rsView.getTheFrame().getOntoUMLModel().getOntoUMLParser(), rsModel.getRSAntiPattern().NONSUBSET
	    			); 
	    		}
	    		
	    		if(rsView.isSelectedRedefinition()) 
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    				rsView.getTheFrame().getOntoUMLModel().getOntoUMLParser(),rsModel.getRSAntiPattern().REDEFINE
	    			)+"\n\n";
	    		}
			
	    		if(rsView.isSelectedSubType())				
	    		{
	    			predicates += "\n\n"+rsModel.getRSAntiPattern().generatePredicate(
	    					rsView.getTheFrame().getOntoUMLModel().getOntoUMLParser(), rsModel.getRSAntiPattern().SUBSET
	    			)+"\n\n"; 
	    		}
								
	    		String alsPath = AlloyModel.alsOutDirectory+
	    				rsView.getTheFrame().getAlloyModel().getAlloyModelName()+"$RS"+rsModel.getId()+".als";		
						
	    		OntoUMLOptions opt = rsView.getTheFrame().getOntoUMLOptionModel().getOptions();
	    		
	    		System.out.println(opt.relatorConstraint);
	    		
	    		RefOntoUML.Package refmodel = rsView.getTheFrame().getOntoUMLModel().getOntoUMLModelInstance();		
			
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
	    				rsModel.getRSAntiPattern().DISJOINT
	    			);
	    		}			
	    		if(rsView.isSelectedNonSubsetting())				
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().NONSUBSET
	    			); 
	    		}	    		
	    		if(rsView.isSelectedRedefinition()) 
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().REDEFINE
	    			)+"\n\n";
	    		}			
	    		if(rsView.isSelectedSubType())				
	    		{
	    			constraints += "\n\n"+rsModel.getRSAntiPattern().generateOcl(
	    				rsModel.getRSAntiPattern().SUBSET
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
