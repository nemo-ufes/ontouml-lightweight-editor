package br.ufes.inf.nemo.ontouml2alloy.transformer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import br.ufes.inf.nemo.ontouml2alloy.util.AlloyLibraryFiles;
import br.ufes.inf.nemo.ontouml2alloy.util.AlloyThemeFile;

import RefOntoUML.Model;

import edu.mit.csail.sdg.alloy4whole.SimpleGUI_custom;

/**
 *	This class is used to call the transformation of OntoUML to Alloy. 
 *  
 *  @author John Guerson    
 */

public class OntoUML2Alloy {
		
	/** Destination directory path. */
	public static String dirPath;	
	
	/** If the relators constraints should be transformed. */
	public static boolean relatorRuleFlag = true;
	
	/** If the weak supplementation rule should be transformed. */
	public static boolean weakSupplementationRuleFlag = true;
		
	/** Alloy specification absolute path. */
	public static String alsPath;
	
	/** Parameters to open Alloy Analyzer. */
	public static String[] argsAnalyzer = {"",""};
	
	/**
	 * 
	 * This method performs the transformation of the RefOntoUML Model 
	 * into an Alloy specification (.als). The Alloy libraries used 
	 * by this transformation are generated into the same folder of the Alloy file, as well as
	 * the standard Alloy theme file. In order to open the Analyzer standalone, we need to copy the Analyzer 
	 * into the folder as well.
	 * 
	 * @param refmodel : The root of .refontouml model (RefOntoUML.Model).
	 * @param alloyPath: The absolute path of alloy model.
	 * @param RelatorRuleFlag: True if the relator constraint should be transformed. 
	 * @param WeakSupplementationRuleFlag:  True if the weak supplementation rule should be transformed.
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean Transformation (Model refmodel, String alloyPath, boolean RelatorRuleFlag, boolean WeakSupplementationRuleFlag, boolean openAnalyzer) throws Exception 
	{
		alsPath = alloyPath;
		
		File f = new File(alsPath);
		if (!f.exists()) f.createNewFile();
		if (f.exists()) f.delete(); f.createNewFile();
		f.deleteOnExit();
		
		// initialize dirPath
		dirPath = alsPath.substring(0,alsPath.lastIndexOf(File.separator)+1);

		// generate alloy standard theme file
		AlloyThemeFile.generateAlloyThemeFile(dirPath);	

		// generate alloy library files
		AlloyLibraryFiles.generateLibraryFiles(dirPath);	
				
		// initialize the options
		relatorRuleFlag = RelatorRuleFlag;
		weakSupplementationRuleFlag = WeakSupplementationRuleFlag;
		
		// Copy "alloy4.2-rc.jar" to the destination directory 
		InputStream is = OntoUML2Alloy.class.getClassLoader().getResourceAsStream("alloy4.2-rc.jar");
		if(is == null) is = new FileInputStream("lib/alloy4.2-rc.jar");
		File alloyJarFile = new File(dirPath + File.separator + "alloy4.2-rc.jar");
		alloyJarFile.deleteOnExit();
		OutputStream out = new FileOutputStream(alloyJarFile);

		// copy data flow -> MB x MB
		byte[] src = new byte[1024];
		int read = 0;
		while ((read = is.read(src)) != -1) {
			out.write(src, 0, read);
		}		
		is.close();
		out.flush();
		out.close();
		
		// Here the transformation begins...
		Transformation.start(refmodel);
		
		// open he Alloy Analyzer
		if (openAnalyzer)
		{
			argsAnalyzer[0] = alsPath;
			argsAnalyzer[1] = dirPath + File.separator + "standart_theme.thm"	;	
			SimpleGUI_custom.main(argsAnalyzer);
		}

		return true;
	}	
}
