package br.ufes.inf.nemo.ocl2alloy.pivot.tests;

import java.io.IOException;

import org.eclipse.ocl.examples.pivot.ParserException;

import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCL2AlloyUtil;
import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCLParser;

public class PivotOCLParserTests {

	public static void main (String[] args)
	{	
		// we have to use absolute paths. I don't know why.
				
		String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\project.ocl";
		String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\project.refontouml";		 
		String tempPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\temp\\";
		System.out.print("#ProjectTest..."); run(oclPath, refPath, tempPath); System.out.println("");		

		String oclPath3 = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\RoadTrafficAccident.ocl";
		String refPath3 = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\RoadTrafficAccident.refontouml";
		String tempPath3 = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\temp\\";
		System.out.print("#RoadTrafficAccidentTest..."); run(oclPath3, refPath3, tempPath3); System.out.println("");	
	}
	
	public static void run (String oclPath, String refPath, String tempPath) 
	{
		try {
			
			PivotOCLParser parser = new PivotOCLParser(refPath, tempPath);
			String oclContent = PivotOCL2AlloyUtil.readFile(oclPath);
			parser.parse(oclContent);
			 
			System.out.print("OCL parsed succesfully.");
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}
}
