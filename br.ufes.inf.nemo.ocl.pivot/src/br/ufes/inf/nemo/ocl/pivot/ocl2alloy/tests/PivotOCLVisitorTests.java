package br.ufes.inf.nemo.ocl.pivot.ocl2alloy.tests;

import java.io.IOException;

import org.eclipse.ocl.examples.pivot.ParserException;

import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.PivotOCLToAlloyVisitor;
import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.PivotOCLUtil;
import br.ufes.inf.nemo.ocl.pivot.ocl2alloy.PivotPrettyPrintAlloyOption;
import br.ufes.inf.nemo.ocl.pivot.parser.PivotOCLParser;

public class PivotOCLVisitorTests {
	
	public static void main (String[]args)
	{		 				
		// we have to use absolute paths. I don't know why.
		
		String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\project.ocl";
		String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\project.refontouml";		 
		String tempPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\temp\\";
		System.out.println("#ProjectTest..."); 
		run(oclPath, refPath, tempPath);
		
		String oclPath3 = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\RoadTrafficAccident.ocl";
		String refPath3 = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\RoadTrafficAccident.refontouml";
		String tempPath3 = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\temp\\";		
		System.out.println("#RoadTrafficAccidentTest..."); 
		run(oclPath3, refPath3, tempPath3);
	 }
	
	public static void run(String oclPath, String refPath, String tempPath)
	{
		try {
			
			PivotOCLParser parser = new PivotOCLParser(refPath,tempPath);
			String oclContent = PivotOCLUtil.readFile(oclPath);
			parser.parse(oclContent);
			
			PivotOCLToAlloyVisitor visitor = new PivotOCLToAlloyVisitor(parser);
			
			//PivotOCLToStringVisitor keplerVisitor = new PivotOCLToStringVisitor();			
			//for(Constraint ct: parser.getConstraints()) { keplerVisitor.visitConstraint(ct); }
									
			System.out.println(
				visitor.prettyPrintAlloy(
					parser.getConstraints(),
					PivotPrettyPrintAlloyOption.createListOfOptions(parser.getConstraints().size())
				)
			);
						
			System.out.println("OCL visited succesfully.");
			
		 } catch (IOException e) {
			e.printStackTrace();
			
		 } catch (ParserException e) {
			e.printStackTrace();
		 
		 } catch (Exception e) {
			e.printStackTrace();
		 }		
	}
}
