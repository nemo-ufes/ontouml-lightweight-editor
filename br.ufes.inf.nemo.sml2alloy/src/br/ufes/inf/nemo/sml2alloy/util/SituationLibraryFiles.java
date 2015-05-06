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
			
			"module situations[World]\n\n"
			
			+"//Situation Continuity"
			+ "//\tStates that a situation continues throught time, i.e. it is the same"
			+ "//if its participants remain the same in consecutive worlds"
			+ "pred situationCont[sit: univ->univ, parts: univ->univ->univ, partsTemp: univ->univ] {"
			+ "\tall w1,w2:World | all s1: w1.sit, s2: w2.sit | w2 in (w1.next) and s1.(w1.parts) = s2.(w2.parts) and s1.partsTemp = s2.partsTemp implies s1 = s2"
			+ "}\n\n"

			+ "//Situation Uniqueness"
			+ "//\tStates that a situation is unique for a particular conjunction of entities in a world"
			+ "pred situationUniq[sit: univ->univ, parts: univ->univ->univ, partsTemp: univ->univ] {"
			+ "\tall w:World | all s1,s2:w.sit | s1.(w.parts) = s2.(w.parts) and s1.partsTemp = s2.partsTemp implies s1 = s2"
			+ "}\n\n"

			+ "//Situation Immutability"
			+ "//\tA situation will aways have the same participants while it exists"
			+ "pred situationImut[sit: univ->univ, parts: univ->univ->univ, partsTemp: univ->univ] {"
			+ "\tall w1,w2:World | all s1: w1.sit, s2: w2.sit | w1 != w2 and s1 = s2 implies s1.(w1.parts) = s2.(w2.parts) and s1.partsTemp = s2.partsTemp"
			+ "}\n\n"
			
			+ "//situation s1 happens before situation s2"
			+ "// s1 |-----------------|"
			+ "//                                         s2 |-----------------|"
			+ "pred before[w1: World, w2: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w2 in w1.^next and (s1 in w1.exists) and not(s1 in w2.exists) and (s2 in w2.exists) and not(s2 in w1.exists)"
			+ "}\n\n"
			
			+ "//situation s1 happens after situation s2"
			+ "// s2 |-----------------|"
			+ "//                                         s1 |-----------------|"
			+ "pred after[w1: World, w2: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w1 in w2.^next and (s1 in w1.exists) and not(s1 in w2.exists) and (s2 in w2.exists) and not(s2 in w1.exists)"
			+ "}\n\n"
			
			+ "//situation s1 ends right before situation s2 starts"
			+ "// s1 |-----------------|"
			+ "//                            s2 |-----------------|"
			+ "pred mets[w1: World, w2: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w2 in w1.next and (s1 in w1.exists) and not(s1 in w2.exists) and (s2 in w2.exists) and not(s2 in w1.exists)"
			+ "}\n\n"
			
			+ "//situation s1 begins right after situation s2 ends"
			+ "// s2 |-----------------|"
			+ "//                            s1 |-----------------|"
			+ "pred metby[w1: World, w2: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w1 in w2.next and (s1 in w1.exists) and not(s1 in w2.exists) and (s2 in w2.exists) and not(s2 in w1.exists)"
			+ "}\n\n"
			
			+ "//situation s1 overlaps(occurs at the same time as) situation s2"
			+ "// s1 |-----------------|"
			+ "//                 s2 |-----------------|"
			+ "pred overlaps[w: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\t(s1 in w.exists) and (s2 in w.exists)"
			+ "}\n\n"
			
			+ "//situation s1 overlaps(occurs at the same time as) situation s2 (identical to overlaps)"
			+ "// s2 |-----------------|"
			+ "//                 s1 |-----------------|"
			+ "pred overlappedby[w: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\t(s1 in w.exists) and (s2 in w.exists)"
			+ "}\n\n"
			
			+ "//situation s1 ends at the same time as situation s2"
			+ "//          s1 |-----------|"
			+ "// s2 |-----------------|"
			+ "pred finishes[w1: World, w2: World ,s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w2 in w1.next and (s1 in w1.exists) and not(s1 in w2.exists) and (s2 in w1.exists) and not(s2 in w2.exists)"
			+ "}\n\n"
			
			+ "//situation s2 ends at the same time as situation s1 (identical to finishes)"
			+ "//          s2 |-----------|"
			+ "// s1 |-----------------|"
			+ "pred finishedby[w1: World, w2: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w2 in w1.next and (s1 in w1.exists) and not(s1 in w2.exists) and (s2 in w1.exists) and not(s2 in w2.exists)"
			+ "}\n\n"
			
			+ "//situation s1 exists through the entirety of situation s2"
			+ "// s1 |--------------------------------------|"
			+ "//                 s2 |-----------------|"
			+ "//w1->w2/w3->w4, w2 and w3 are not necessarily different"
			+ "pred includes[w1: World, w2: World, w3: World, w4: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w1 != w3 and w1 != w4 and w2 != w4 and w3 != w4 and"
			+ " w2 in w1.next and w3 in w2.*next and w4 in w3.next and"
			+ " (s1 in w1.exists) and (s1 in w2.exists) and (s1 in w3.exists) and (s1 in w4.exists) and"
			+ " not(s2 in w1.exists) and (s2 in w2.exists) and (s2 in w3.exists) and not(s2 in w4.exists)"
			+ "}\n\n"
			
			+ "//situation s2 exists through the entirety of situation s1"
			+ "// s2 |--------------------------------------|"
			+ "//                 s1 |-----------------|"
			+ "//w1->w2/w3->w4, w2 and w3 are not necessarily different"
			+ "pred during[w2: World, w1: World, w3: World, w4:World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w1 != w3 and w1 != w4 and w2 != w4 and w3 != w4 and"
			+ "	w2 in w1.next and w3 in w2.*next and w4 in w3.next and"
			+ " (s2 in w1.exists) and (s2 in w2.exists) and (s2 in w3.exists) and (s2 in w4.exists) and"
			+ "	not(s1 in w1.exists) and (s1 in w2.exists) and (s1 in w3.exists) and not(s1 in w4.exists)"
			+ "}\n\n"
			
			+ "//situation s1 starts at the same time as situation s2"
			+ "// s1 |-----------|"
			+ "// s2 |-----------------|"
			+ "pred starts[w1: World, w2: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w2 in w1.next and not(s1 in w1.exists) and (s1 in w2.exists) and not(s2 in w1.exists) and (s2 in w2.exists)"
			+ "}\n\n"
			
			+ "//situation s2 starts at the same time as situation s1"
			+ "// s2 |-----------|"
			+ "// s1 |-----------------|"
			+ "pred startedby[w1: World, w2: World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w2 in w1.next and not(s1 in w1.exists) and (s1 in w2.exists) and not(s2 in w1.exists) and (s2 in w2.exists)"
			+ "}\n\n"
			
			+ "//situation s1 coincides with situation s2"
			+ "// s1 |-----------------|"
			+ "// s2 |-----------------|"
			+ "pred coincides[w1: World, w2: World, w3: World, w4:World, s1: univ, s2: univ, exists: univ->univ] {"
			+ "\tw1 != w2 and w1 != w3 and w1 != w4 and w2 != w4 and w3 != w4 and w2 in w1.next and w3 in w2.*next and w4 in w3.next and"
			+ "	not(s1 in w1.exists) and (s1 in w2.exists) and (s1 in w3.exists) and not(s1 in w4.exists) and"
			+ "	not(s2 in w1.exists) and (s2 in w2.exists) and (s2 in w3.exists) and not(s2 in w4.exists)"
			+ "}\n\n";
}