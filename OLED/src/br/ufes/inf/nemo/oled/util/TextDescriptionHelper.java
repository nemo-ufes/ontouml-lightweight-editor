package br.ufes.inf.nemo.oled.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collections;

import ontouml2text.core.Writer;

import RefOntoUML.Model;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;

public class TextDescriptionHelper {
	
   public static OperationResult generateText(final Model refmodel, String tempDir) {
	   
	   String modelFileName = ConfigurationHelper.getCanonPath(tempDir, OLEDSettings.MODEL_DEFAULT_FILE.getValue());
	   File modelFile = new File(modelFileName);  	
	   modelFile.deleteOnExit();
   	
	   try {
			
			FileOutputStream out = new FileOutputStream(modelFile);
			refmodel.eResource().save(out, Collections.EMPTY_MAP);
			
			Writer.Write(modelFileName);
			
			String docPage = modelFile.getPath().replace(".refontouml", ".txt");
			
			return new OperationResult(ResultType.SUCESS, "Model description generated successfully", new Object[] { docPage });
			
		} catch (Exception ex) {
			return new OperationResult(ResultType.ERROR, "Error while generating the model description. Details: " + ex.getMessage(), null);
		}
	}
	
}
