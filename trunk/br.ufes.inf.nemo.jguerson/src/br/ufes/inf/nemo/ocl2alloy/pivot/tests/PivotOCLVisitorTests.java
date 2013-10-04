package br.ufes.inf.nemo.ocl2alloy.pivot.tests;

import java.io.IOException;

import org.eclipse.ocl.examples.pivot.ParserException;

import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCL2AlloyUtil;
import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCLParser;
import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCLToAlloyVisitor;
import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCLToStringVisitor;
import br.ufes.inf.nemo.ocl2alloy.pivot.PrettyPrintAlloyOption;

public class PivotOCLVisitorTests {
	
	public static void main (String[]args)
	{		 				
		// we have to use absolute paths. I don't know why.
		
		String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\project.ocl";
		String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\project.refontouml";		 
		String tempPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\temp\\";
		System.out.println("#ProjectTest..."); 
		run(oclPath, refPath, tempPath);
		
		String oclPath3 = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\RoadTrafficAccident.ocl";
		String refPath3 = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\models\\RoadTrafficAccident.refontouml";
		String tempPath3 = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\src\\br\\ufes\\inf\\nemo\\ocl2alloy\\pivot\\tests\\temp\\";		
		System.out.println("#RoadTrafficAccidentTest..."); 
		run(oclPath3, refPath3, tempPath3);
	 }
	
	public static void run(String oclPath, String refPath, String tempPath)
	{
		try {
			
			PivotOCLParser parser = new PivotOCLParser(refPath,tempPath);
			String oclContent = PivotOCL2AlloyUtil.readFile(oclPath);
			parser.parse(oclContent);
			
			PivotOCLToAlloyVisitor visitor = new PivotOCLToAlloyVisitor(parser);
			
			PivotOCLToStringVisitor keplerVisitor = new PivotOCLToStringVisitor();			
			keplerVisitor.visitConstraint(parser.getConstraints().get(0));
						
			System.out.println(
				visitor.prettyPrintAlloy(
					parser.getConstraints(),
					PrettyPrintAlloyOption.createListOfOptions(parser.getConstraints().size())
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
