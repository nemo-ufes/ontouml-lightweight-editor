package br.ufes.inf.nemo.oled.util;

import javax.swing.JDialog;
import javax.swing.JFrame;

import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;

public class TextDescriptionHelper {
	/*
	private static JDialog writer;
	
	public static OperationResult generateText(final RefOntoUML.Package refmodel, JFrame frame)
	{
		MainWindow mainWindow = new MainWindow(frame, refmodel);
		writer = mainWindow.getAppDialog();
	   	writer.setLocationRelativeTo(frame);
	   	writer.setVisible(true);
	   	
	   	while(writer.isVisible()) {}
	   	
	   	try
		{
			String textOutput = mainWindow.getModelDescription();
		
			if(textOutput != null && textOutput.length() > 0)
			{
				return new OperationResult(ResultType.SUCESS, "Model description generated successfully.", new Object[] { textOutput });
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
	}*/
}
