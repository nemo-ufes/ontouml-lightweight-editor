package br.ufes.inf.nemo.ocl2alloy.pivot.tests;

import java.io.IOException;

import org.eclipse.ocl.examples.pivot.ParserException;

import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCL2AlloyUtil;
import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCLParser;

public class PivotOCLParserTests {

	public static void main (String[] args)
	{				
//		String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";				
//		String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
				
//		String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.ocl";		
//		String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.refontouml";
		
//		String tempPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\temp\\";
		
		//================================
		
//		String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";				
//		String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
				
		String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.ocl";		
		String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.refontouml";
		
		String tempPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\temp\\";
		
		try {
			
			PivotOCLParser parser = new PivotOCLParser(refPath, tempPath);
			String oclContent = PivotOCL2AlloyUtil.readFile(oclPath);
			parser.parse(oclContent);
			 
			System.out.println("OCL parsed succesfully.");
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		
	}
}
