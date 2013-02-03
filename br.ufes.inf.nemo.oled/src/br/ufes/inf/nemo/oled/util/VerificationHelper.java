package br.ufes.inf.nemo.oled.util;

import java.awt.EventQueue;
import java.io.File;
import java.util.List;

import javax.swing.UIManager;

import RefOntoUML.Model;
import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.oled.util.OperationResult.ResultType;

public class VerificationHelper {
	
   public static OperationResult verifyModel(final Model refmodel, List<SimulationElement> simulationElements, String tempDir)
   {	   
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

    					TheFrame frame = new TheFrame(refmodel,"",alsPath);
    					frame.setVisible(true);
    					frame.setLocationRelativeTo(null);
    				
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
