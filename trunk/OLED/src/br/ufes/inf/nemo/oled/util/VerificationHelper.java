package br.ufes.inf.nemo.oled.util;

import java.io.File;
import java.text.MessageFormat;
import java.util.List;

import RefOntoUML.Model;
import br.ufes.inf.nemo.ontouml.transformation.OntoUML2Alloy;
import br.ufes.inf.nemo.ontouml.transformation.OntoUML2Alloy.TransformationType;
import edu.mit.csail.sdg.alloy4.Err;
import edu.mit.csail.sdg.alloy4compiler.ast.Command;
import edu.mit.csail.sdg.alloy4compiler.ast.Module;
import edu.mit.csail.sdg.alloy4compiler.parser.CompUtil;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Options;
import edu.mit.csail.sdg.alloy4compiler.translator.A4Solution;
import edu.mit.csail.sdg.alloy4compiler.translator.TranslateAlloyToKodkod;

public class VerificationHelper {
	
	public static String verifyModel(Model model, List<String> outputFiles)
	{
		
		if(model.getPackagedElement().size() == 0)
			return("An empty model cannot be verified.");
		
		//TODO Parametrize this
		String fileName = "Simulation.als"; 
		
		StringBuilder sb = new StringBuilder();	
		sb.append("--- Verification Results ---\n\n");
		
		long validationStartMilis = System.currentTimeMillis();
		
		try {
			
			if(OntoUML2Alloy.transformToAlloyFile(model, TransformationType.V3, fileName))
			{
				sb.append(verifyModel(fileName, outputFiles));
			}
			else
			{
				return("An error has occurred generating the Alloy file.\n\n");
			}
			
		} catch (Exception ex) {
			return("An error has occurred when verifying the model.\n" + ex.getMessage());
		}
		
		long validationEndMilis = System.currentTimeMillis();
		sb.append(MessageFormat.format("--- Model verified in {0} ms ---", (validationEndMilis - validationStartMilis)));

		return sb.toString();
		
	}
	
	public static String verifyModelFromAlloyFile(String fileName, List<String> outputFiles)
	{

		StringBuilder sb = new StringBuilder();	
		sb.append("--- Verification Results ---\n\n");
		
		long validationStartMilis = System.currentTimeMillis();
		
		try {
			
			sb.append(verifyModel(fileName, outputFiles));
			
		} catch (Exception ex) {
			
			return("An error has occurred when verifying the model.\n\n" + ex.getLocalizedMessage());
			
		}
	
		long validationEndMilis = System.currentTimeMillis();
		sb.append(MessageFormat.format("--- Model verified in {0} ms ---", (validationEndMilis - validationStartMilis)));

		return sb.toString();
		
	}
	
	public static String verifyModel(String fileName, List<String> outputFiles)
	{
		
		File file = new File(fileName);
				  	
		if(file.exists())
		{		
			// Chooses the Alloy4 options
	        A4Options opt = new A4Options();
	        opt.solver = A4Options.SatSolver.SAT4J;

	        boolean satisfiable = true;
	        int i = 0;
	        
	        try {		
	        	Module world = CompUtil.parseEverything_fromFile(null, null, fileName);
	        	for (Command command: world.getAllCommands()) {
	        		// Execute the command
	                A4Solution ans = TranslateAlloyToKodkod.execute_command(null, world.getAllReachableSigs(), command, opt);
	                satisfiable &= ans.satisfiable();
	                
	                String outputFileName = "verification_output_" + i + ".xml";
	                ans.writeXML(outputFileName);
	                outputFiles.add(outputFileName);

	                i++;
	            }
	        	
	        } catch (Err e) {
	        	
	        	return("An error has occurred when verifying the model.\n\n" + e.getLocalizedMessage());
			} 
	        
	        if(satisfiable)
	        {
	        	return("Model appears to be consistent.\n\n");
	        }
	        else
	        {
	        	return("\nModel is NOT consistent. \n\n");
	        	
	        }
		}
		else
		{
			return("Alloy file not found.\n\n");
		}
	}
}
