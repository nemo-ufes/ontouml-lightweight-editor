/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.util;


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
