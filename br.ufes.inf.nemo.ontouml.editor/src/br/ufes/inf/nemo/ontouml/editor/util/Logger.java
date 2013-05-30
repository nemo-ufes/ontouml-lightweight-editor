package br.ufes.inf.nemo.ontouml.editor.util;

public class Logger {

	public static void logException(Exception ex)
	{
		//For now, only displays the stack trace
		ex.printStackTrace();
	}
	
}
