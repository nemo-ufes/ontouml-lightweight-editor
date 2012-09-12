package br.ufes.inf.nemo.refontouml2alloy.util;

import java.io.BufferedWriter;
import java.io.FileWriter;

public class FileUtil {

	/** Copy String to a File. */
	public static void copyStringToFile(String content, String FilePath) {
		try {
			FileWriter fstream = new FileWriter(FilePath);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(content);
			out.close();
			System.out.println("Done!");
		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}
	}
}
