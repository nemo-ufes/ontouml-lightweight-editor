package br.ufes.inf.nemo.oled.util;

import java.io.File;

import refontouml2alloy.bts.OntoUML2Alloy;

import RefOntoUML.Model;
import br.ufes.inf.nemo.oled.util.OLEDSettings.Setting;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4compiler.ast.Command;
import edu.mit.csail.sdg.alloy4compiler.ast.Module;
import edu.mit.csail.sdg.alloy4compiler.parser.CompUtil;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;
import edu.mit.csail.sdg.alloy4compiler.translator.TranslateAlloyToKodkod;

public class VerificationHelper {
	
	public static A4Solution verifyModelFromAlloyFile(String fileName)
	{    	    	
    	File alloyFile = new File(fileName);  	
		
    	if(alloyFile.exists())
		{		
	        A4Options opt = new A4Options();
	        opt.solver = A4Options.SatSolver.SAT4J;
	        
	        try {		
	        		        	
	        	Module module = CompUtil.parseEverything_fromFile(null, null, fileName);

	        	if(!module.getAllCommands().isEmpty())
	        	{
		        	Command command = module.getAllCommands().get(0); //Obter e executar somente um comando, assim como o AlloyAnalyzer faz
		        	A4Solution solution = TranslateAlloyToKodkod.execute_command(null, module.getAllReachableSigs(), command, opt);
		        	
		        	return solution;
	        	}
	        	else
	        	{
	        		return null;
	        	}
	        	
	        } catch (Err e) {
	        	return null; 
			} 
		}
    	
    	return null;
		
	}
		
    public static A4Solution verifyModel(Model model)
	{
		String fileName = OLEDSettings.getInstance().getSetting(Setting.SIMULATION_DEFAULT_FILE); 
		
    	if(!OntoUML2Alloy.transformToAlloyFile(model, model.getPackagedElement(), fileName))
    		return null;
    	    	
    	File alloyFile = new File(fileName);  	
		
    	if(alloyFile.exists())
		{		
	        A4Options opt = new A4Options();
	        opt.solver = A4Options.SatSolver.SAT4J;
	        
	        try {		
	        		        	
	        	Module module = CompUtil.parseEverything_fromFile(null, null, fileName);

	        	if(!module.getAllCommands().isEmpty())
	        	{
		        	Command command = module.getAllCommands().get(0); //Obter e executar somente um comando, assim como o AlloyAnalyzer faz
		        	A4Solution solution = TranslateAlloyToKodkod.execute_command(null, module.getAllReachableSigs(), command, opt);
		        	
		        	return solution;
	        	}
	        	else
	        	{
	        		return null;
	        	}
	        	
	        } catch (Err e) {
	        	return null; 
			} 
		}
    	
    	return null;
	}

}
