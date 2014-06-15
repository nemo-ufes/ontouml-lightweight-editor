package br.ufes.inf.nemo.ocl.pivot.parser.tests;

import java.io.IOException;

import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ParserException;

import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.PivotOCLUtil;
import br.ufes.inf.nemo.ocl.pivot.parser.PivotOCLParser;

public class PivotOCLParserTests {

	public static void main (String[] args)
	{	
		// we have to use absolute paths. I don't know why.
				
		String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl\\pivot\\models\\project.ocl";
		String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl\\pivot\\models\\project.refontouml";		 
		String tempPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl\\pivot\\models\\";
		System.out.print("#ProjectTest..."); run(oclPath, refPath, tempPath); System.out.println("");		

		String oclPath3 = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl\\pivot\\models\\RoadTrafficAccident.ocl";
		String refPath3 = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl\\pivot\\models\\RoadTrafficAccident.refontouml";
		String tempPath3 = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl\\pivot\\models\\";
		System.out.print("#RoadTrafficAccidentTest..."); run(oclPath3, refPath3, tempPath3); System.out.println("");	
	}
	
	public static void run (String oclPath, String refPath, String tempPath) 
	{
		try {
			
			PivotOCLParser parser = new PivotOCLParser(refPath, tempPath);
			String oclContent = PivotOCLUtil.readFile(oclPath);
			parser.parse(oclContent);
			 
			System.out.println("OCL parsed succesfully.");
			
			for(Constraint ct: parser.getConstraints())
			{
				System.out.println(ct);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}
