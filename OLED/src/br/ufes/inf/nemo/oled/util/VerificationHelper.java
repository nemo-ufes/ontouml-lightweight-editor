package br.ufes.inf.nemo.oled.util;

import java.io.File;
import java.util.List;

import refontouml2alloy.OntoUML2Alloy;

import RefOntoUML.Model;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;

public class VerificationHelper {
	
   public static OperationResult verifyModel(Model model, List<SimulationElement> simulationElements, String tempDir){
	   
		String alloyFileName = ConfigurationHelper.getCanonPath(tempDir, OLEDSettings.SIMULATION_DEFAULT_FILE.getValue()); 
		String themeFileName = ConfigurationHelper.getCanonPath(tempDir, OLEDSettings.SIMULATION_THEME_FILE.getValue());
		
		OntoUML2Alloy.dirPath = ConfigurationHelper.getCanonPath(tempDir, "");
		
    	try {
			if(!OntoUML2Alloy.transformToAlloyFile(model, null, alloyFileName, themeFileName))
				return new OperationResult(ResultType.ERROR, "Error while generating the alloy file", null);
			else
				return new OperationResult(ResultType.SUCESS, "Model has been successfuly transformed.", null );
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	/*
    	File alloyFile = new File(alloyFileName);  	
    	alloyFile.deleteOnExit();

    	if(alloyFile.exists())
		{		
	        A4Options opt = new A4Options();
	        opt.solver = A4Options.SatSolver.SAT4J;
	        
	        try {		
	        	
	        	Map<String, String> alloyMap = new LinkedHashMap<String,String>();        	
	        	Module module = CompUtil.parseEverything_fromFile(null, alloyMap, alloyFileName, 1);
   	
	        	if(!module.getAllCommands().isEmpty())
	        	{
		        	Command command = module.getAllCommands().get(0); //Obter e executar somente um comando, assim como o AlloyAnalyzer faz
		        	A4Solution solution = TranslateAlloyToKodkod.execute_command(null, module.getAllReachableSigs(), command, opt);
		        	
		        	if(solution.satisfiable())
		        	{
		        					        		
		        		ConstMap<String,String> alloySources = ConstMap.make(alloyMap);
		        		
		        		//We wrap all of them up in the result object array because they are needed in order to iterate over 
		        		//the solutions
		        		
		        		return new OperationResult(ResultType.SUCESS, "Model instance found", new Object[] { solution, module, alloySources });
		        	}
		        	else
		        	{
		        		return new OperationResult(ResultType.ERROR, "No model instance found (solution satisfiable)", null);
		        	}
	        	}
	        	else
	        	{
	        		return new OperationResult(ResultType.ERROR, "No alloy command to execute", null);
	        	}
	        	
	        } catch (Exception ex) {
	        	return new OperationResult(ResultType.ERROR, "A problem has ocurred when verifying the model \nDetails: " + ex.getMessage(), new Object[] { ex });
			} 
		}
    	*/
    	return new OperationResult(ResultType.ERROR, "A problem has ocurred when verifying the model", null);
	}

	   
	public static OperationResult verifyModelFromAlloyFile(String tempDir)
	{    	    	
		String alloyFileName = ConfigurationHelper.getCanonPath(tempDir, OLEDSettings.SIMULATION_DEFAULT_FILE.getValue());
		File alloyFile = new File(alloyFileName);  	
    	alloyFile.deleteOnExit();
    
    	/*	
    	if(alloyFile.exists())
		{		
	        A4Options opt = new A4Options();
	        opt.solver = A4Options.SatSolver.SAT4J;
	        
	        try {		
	        	
	        	Map<String, String> alloyMap = new LinkedHashMap<String,String>();        	
	        	Module module = CompUtil.parseEverything_fromFile(null, alloyMap, alloyFileName, 1);
   	
	        	if(!module.getAllCommands().isEmpty())
	        	{
		        	Command command = module.getAllCommands().get(0); //Obter e executar somente um comando, assim como o AlloyAnalyzer faz
		        	A4Solution solution = TranslateAlloyToKodkod.execute_command(null, module.getAllReachableSigs(), command, opt);
		        	
		        	if(solution.satisfiable())
		        	{
		        		ConstMap<String,String> alloySources = ConstMap.make(alloyMap);
		        		
		        		//We wrap all of them up in the result object array because they are needed in order to iterate over 
		        		//the solutions
		        		
		        		return new OperationResult(ResultType.SUCESS, "Model instance found", new Object[] { solution, module, alloySources });
		        	}
		        	else
		        	{
		        		return new OperationResult(ResultType.ERROR, "No model instance found (solution satisfiable)", null);
		        	}
	        	}
	        	else
	        	{
	        		return new OperationResult(ResultType.ERROR, "No alloy command to execute", null);
	        	}
	        	
	        } catch (Exception ex) {
	        	return new OperationResult(ResultType.ERROR, "A problem has ocurred when verifying the model \nDetails: " + ex.getMessage(), new Object[] { ex });
			} 
		}
    	*/
    	return new OperationResult(ResultType.ERROR, "Not Implemented yet.", null);		
	}
 
}