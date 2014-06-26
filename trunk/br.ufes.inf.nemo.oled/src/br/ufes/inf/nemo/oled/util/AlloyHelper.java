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

import java.io.File;
import java.util.List;

import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;

public class AlloyHelper {
	
   public static OperationResult validateModel(final RefOntoUML.Package refmodel, List<SimulationElement> simulationElements, String tempDir)
   {/*	   
	   	String directoryPath	= ConfigurationHelper.getCanonPath(tempDir,"");	

	   	final String alsPath;
	   	
	   	if (refmodel.getName() == "" || refmodel.getName()== null)	   	
	   		alsPath = directoryPath + File.separator + "Model"+".als";
	   	else
	   		alsPath = directoryPath + File.separator + refmodel.getName()+".als";
	   	
	   	Boolean succeeds = false;

	   	String message = new String();
	   	
    	try	{    		
    	
    		EventQueue.invokeLater(new Runnable() 
    		{
    			public void run() {
    				try {
    					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

    					//TheFrame frame = new TheFrame(refmodel,"",alsPath);
    					//frame.setVisible(true);
    					//frame.setLocationRelativeTo(null);
    				
    				} catch (Exception ex) {
    					ex.printStackTrace();
    				}	
    			}
    		});
    	
    		succeeds = true;
    	
		} catch (Exception e) {
			e.printStackTrace();
		}    	
    	
    	if (succeeds) 
    		return new OperationResult(ResultType.SUCESS, "Model Validation Environment called succesfully.", null );    	
    	
    	if (!succeeds) 
    		return new OperationResult(ResultType.ERROR, "An Error ocurred while calling the Model Validation Environment \n"+message, null);
    	*/
    	return new OperationResult(ResultType.ERROR, "A problem has ocurred when verifying the model", null);
	}
	   
	public static OperationResult verifyModelFromAlloyFile(String tempDir)
	{    	    	
		String alloyFileName = ConfigurationHelper.getCanonPath(tempDir, OLEDSettings.SIMULATION_DEFAULT_FILE.getValue());
		File alloyFile = new File(alloyFileName);  	
    	alloyFile.deleteOnExit();        	
    	return new OperationResult(ResultType.ERROR, "Not Implemented yet.", null);		
	}
	
}
