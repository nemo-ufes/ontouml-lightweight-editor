package br.ufes.inf.nemo.sml2alloy.util;

import java.io.IOException;

import br.ufes.inf.nemo.common.file.FileUtil;

/**
 *	Generates Situation library Files into destination directory path.
 *  
 * 	@authors Vinicius Sobral
 */

public class SituationLibraryFiles {

	/**
	 * Generate "situations.als" into the directory path "dirPath".
	 *  
	 * @param dirPath
	 * @throws IOException
	 */
	public static void generateLibraryFiles (String dirPath) throws IOException
	{
		//File libFile = new File(dirPath + "situations.als");
		//libFile.deleteOnExit();				
		FileUtil.writeToFile(situations, dirPath + "situations.als");
	}
	
	/** Situations common properties. */
	public static String situations = 
			
			"module situations[World]\n\n" +
			
			"//states that a situation continues throught time, i.e. it is the same\n" +
			"//if its participants remain the same in consecutive worlds\n" +
			"pred situationCont[sit: univ->univ, participants: univ->univ] {\n" +
			"\tall w1,w2:World | all s1: w1.sit, s2: w2.sit | w2 in (w1.next) and s1.(w1.participants) = s2.(w2.participants) implies s1 = s2\n" +
			"\tall w1,w2:World | all s1: w1.sit, s2: w2.sit | w2 in (w1.next) and s1 = s2 implies s1.(w1.participants) = s2.(w2.participants)\n" +
			"}\n\n" +
			
			"//states that a situation is unique for a particular conjunction of entities in a world\n" +
			"pred situationUniq[sit: univ->univ, participants: univ->univ] {\n" +
			"\tall w:World | all s1,s2:w.sit | s1.(w.participants) = s2.(w.participants) implies s1 = s2\n" +
			"}";
}