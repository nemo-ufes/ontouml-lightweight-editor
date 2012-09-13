package br.ufes.inf.nemo.refontouml2alloy;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import br.ufes.inf.nemo.refontouml2alloy.util.AlloyLibraryFiles;
import br.ufes.inf.nemo.refontouml2alloy.util.AlloyThemeFile;

import RefOntoUML.Model;

import edu.mit.csail.sdg.alloy4whole.SimpleGUI_custom;

/**
 *	This class is used to call the transformation of OntoUML into Alloy. 
 */

public class OntoUML2Alloy {
		
	public static String dirPath;	
	public static String alsPath;	
	public static String[] argsAnalyzer = {"",""};	
	public static boolean relatorRuleFlag = true;	
	public static boolean weakSupplementationRuleFlag = true;
	
	public static boolean Transformation (Model refmodel, String destinationPath, boolean RelatorRuleFlag, boolean WeakSupplementationRuleFlag) throws Exception 
	{
		dirPath = destinationPath;

		if (refmodel.getName()=="" || refmodel.getName()==null) 
			alsPath = dirPath + File.separator+ "Model" + ".als";
		else
			alsPath = dirPath + File.separator + refmodel.getName() + ".als";		
		File f = new File(alsPath);
		f.deleteOnExit();	
		
		relatorRuleFlag = RelatorRuleFlag;
		weakSupplementationRuleFlag = WeakSupplementationRuleFlag;
		
		// "alloy4.2-rc.jar" 
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

		AlloyThemeFile.generateAlloyThemeFile(dirPath);	

		AlloyLibraryFiles.generateLibraryFiles(dirPath);	
		
		Reader.init(refmodel);
		
		argsAnalyzer[0] = alsPath;
		argsAnalyzer[1] = dirPath + File.separator + "standart_theme.thm"	;	
		SimpleGUI_custom.main(OntoUML2Alloy.argsAnalyzer);

		return true;
	}	
}
