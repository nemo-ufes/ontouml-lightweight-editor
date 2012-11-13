package br.ufes.inf.nemo.move.antipattern.ia;

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

public class IAAntiPatternController {
	
	private IAAntiPatternView iaView;
	private IAAntiPatternModel iaModel;
	
	/**
	 * Constructor.
	 * 
	 * @param iaView
	 * @param iaModel
	 */
	public IAAntiPatternController(IAAntiPatternView iaView, IAAntiPatternModel iaModel)
	{
		this.iaView = iaView;
		this.iaModel = iaModel;
			
		iaView.addExecuteWithAnalzyerListener(new ExecuteWithAnalzyerListener());
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
				
	    		if(iaView.isSelectedSourceCustom()) 
	    		{
	    			predicates += "\n\n"+iaModel.getIAAntiPattern().generateSourcePredicate(
	    				iaView.getSourceCustomClassifiers(),
	    				iaView.getTheFrame().getOntoUMLModel().getOntoUMLParser()	    				
	    			);
	    		}
			
	    		if(iaView.isSelectedTargetCustom())				
	    		{
	    			predicates += "\n\n"+iaModel.getIAAntiPattern().generateTargetPredicate(
	    					iaView.getTargetCustomClassifiers(),
	    				iaView.getTheFrame().getOntoUMLModel().getOntoUMLParser()	    			
	    			); 
	    		}
								
	    		String alsPath = OutputModel.alsOutDirectory+
	    				iaView.getTheFrame().getOutputModel().alsmodelName+"$IA"+iaModel.getId()+".als";		
						
	    		Options opt = iaView.getTheFrame().getOptionModel().getOptions();
			
	    		RefOntoUML.Package refmodel = iaView.getTheFrame().getOntoUMLModel().getOntoUMLModelInstance();		
			
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
	    		JOptionPane.showMessageDialog(iaView.getTheFrame(),exception.getMessage(),"Error",JOptionPane.ERROR_MESSAGE);
	    	}
	    	
	    }
	}	
}
