package br.ufes.inf.nemo.move.antipattern.ac;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.ufes.inf.nemo.common.file.FileUtil;
import br.ufes.inf.nemo.move.output.OutputModel;
import br.ufes.inf.nemo.move.util.AlloyJARExtractor;
import br.ufes.inf.nemo.ontouml2alloy.transformer.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml2alloy.util.Options;
import edu.mit.csail.sdg.alloy4whole.SimpleGUICustom;

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
				
	    		if(acView.isSelectedOpenCycle()) 
	    		{
	    			predicates += "\n\n"+acModel.getACAntiPattern().generateOpenCyclePredicate(
	    				acView.getTheFrame().getOntoUMLModel().getOntoUMLParser(),
	    				acView.getScope()
	    			);
	    		}
			
	    		if(acView.isSelectedClosedCycle())				
	    		{
	    			predicates += "\n\n"+acModel.getACAntiPattern().generateClosedCyclePredicate(
	    				acView.getTheFrame().getOntoUMLModel().getOntoUMLParser(), 
	    				acView.getScope()
	    			); 
	    		}
								
	    		String alsPath = OutputModel.alsOutDirectory+
	    				acView.getTheFrame().getOutputModel().alsmodelName+"$AC"+acModel.getId()+".als";		
						
	    		Options opt = acView.getTheFrame().getOptionModel().getOptions();
			
	    		RefOntoUML.Package refmodel = acView.getTheFrame().getOntoUMLModel().getOntoUMLModelInstance();		
			
	    		OntoUML2Alloy.Transformation(refmodel, alsPath, opt);
						
	    		FileUtil.writeToFile(predicates, alsPath);
			
	    		if (opt.openAnalyzer)
	    		{
	    			AlloyJARExtractor.extractAlloyJaRTo("alloy4.2.jar", OutputModel.alsOutDirectory);
				
	    			String[] argsAnalyzer = { "","" };
	    			argsAnalyzer[0] = alsPath;
	    			argsAnalyzer[1] = OutputModel.alsOutDirectory + "standart_theme.thm"	;	
	    			SimpleGUICustom.main(argsAnalyzer);
	    		}
	    		
	    	}catch(Exception exception){
	    		JOptionPane.showMessageDialog(acView.getTheFrame(),exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	    	}
	    	
	    }
	}

	
}
