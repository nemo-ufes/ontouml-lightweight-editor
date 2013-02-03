package br.ufes.inf.nemo.oled.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;


import RefOntoUML.Model;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;
import br.ufes.inf.nemo.ontouml2sbvr.core.OntoUML2SBVR;

/**
 * Helper class for dealing with model transformation to SBVR
 * 
 *   @author Antognoni Albuquerque
 *   @version 1.0
 */
public class SBVRHelper {

	public static OperationResult generateSBVR(Model model, String tempDir)
	{
		// As the SBVR transformation requires a model file (.refontouml), so need to write the model to the temp file in order to run 
		// the transformation. If we decide to incorporate the OntoUML2SBVR plugin, we should modify the transformation to add the possibility to run 
		// the transformation directly from the model object
		
		String modelFileName = ConfigurationHelper.getCanonPath(tempDir, OLEDSettings.MODEL_DEFAULT_FILE.getValue());
		
		File modelFile = new File(modelFileName);  	
    	modelFile.deleteOnExit();
    	
		try {
			
			FileOutputStream out = new FileOutputStream(modelFile);
			model.eResource().save(out, Collections.EMPTY_MAP);
			
			OntoUML2SBVR.Transformation(modelFileName);
			
			String docPage = modelFile.getPath().replace(".refontouml", ".html");
			
			return new OperationResult(ResultType.SUCESS, "SBVR generated successfully", new Object[] { docPage });
			
		} catch (Exception ex) {
			return new OperationResult(ResultType.ERROR, "Error while generating the SBVR representaion. Details: " + ex.getMessage(), null);
		}
		
	}
	
}
