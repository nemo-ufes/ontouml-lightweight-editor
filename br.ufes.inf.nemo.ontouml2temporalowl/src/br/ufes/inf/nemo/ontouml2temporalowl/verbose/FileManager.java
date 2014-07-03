package br.ufes.inf.nemo.ontouml2temporalowl.verbose;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class FileManager
{
	Writer output;
		
	public FileManager (String outName)
	{		
		try
		{																
			// Create the owl file
			File f = new File(outName);
			output = new BufferedWriter(new FileWriter(f));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void write (String s)
	{
		try
		{
			output.write(s);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}	
	}
				
	public void done ()
	{
		try
		{
			output.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
