package br.ufes.inf.nemo.oled.util;

import javax.swing.JFrame;

import ontouml2text.ui.Options;

import RefOntoUML.Model;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;

public class TextDescriptionHelper {
	
	private static Options writer;
	
	public static OperationResult generateText(final Model refmodel, JFrame frame)
	{
		writer = new Options(frame, refmodel);
	   	writer.setLocationRelativeTo(frame);
	   	writer.setVisible(true);
	   	
	   	while(writer.isVisible()) {}
	   	
	   	try
		{
			String textOutput = writer.getModelDescription();
		
			if(textOutput != null && textOutput.length() > 0)
			{
				if (textOutput.equals("CSS"))
				{
					return new OperationResult(ResultType.SUCESS, "CSS generated successfully.", null);
				}
				else
	//			if(fileOutput && filePath != null)
	//			{
	//				String owlFileName = ConfigurationHelper.canon(filePath);
	//			    	
	//				// Writing transformed model into owl file
	//				FileManager fileManager = new FileManager(owlFileName);
	//				fileManager.write(owlOutput);
	//				fileManager.done();
	//				
	//				return new OperationResult(ResultType.SUCESS, "OWL generated successfully", new Object[] { owlFileName });
	//			}
	//			else
	//			{
					return new OperationResult(ResultType.SUCESS, "Model description generated successfully.", new Object[] { textOutput });
	//			}
			}
			else
			{
				return new OperationResult(ResultType.ERROR, "No model description generated.", null);
			}
	}
		catch (Exception ex)
		{
			ex.printStackTrace();
			return new OperationResult(ResultType.ERROR, "Error while generating the model description. Details: " + ex.getMessage(), null);
		}
	}
}
