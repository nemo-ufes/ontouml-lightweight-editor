package br.ufes.inf.nemo.ocl2alloy.pivot.tests;

import java.io.IOException;

import org.eclipse.ocl.examples.pivot.ParserException;

import br.ufes.inf.nemo.ocl2alloy.pivot.KeplerToStringVisitor;
import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCL2AlloyUtil;
import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCLParser;
import br.ufes.inf.nemo.ocl2alloy.pivot.PivotOCLVisitor;
import br.ufes.inf.nemo.ocl2alloy.pivot.PrettyPrintAlloyOption;

public class PivotOCLVisitorTests {
	
	public static void main (String[]args)
	 {		 				
//		 String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.ocl";
//		 String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.refontouml";
		 
		 String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";
		 String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
		 
		 String tempPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.jguerson\\temp\\";
		 
//		 String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.ocl";
//		 String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\RoadTrafficAccident.refontouml";
		 
//		 String oclPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.ocl";
//		 String refPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\model\\project.refontouml";
		 
//		 String tempPath = "C:\\Users\\John\\SVNs\\SVN-OLED\\br.ufes.inf.nemo.jguerson\\temp\\";
			
		 try {
			
			PivotOCLParser parser = new PivotOCLParser(refPath,tempPath);
			String oclContent = PivotOCL2AlloyUtil.readFile(oclPath);
			parser.parse(oclContent);
			
			System.out.println("OCL parsed succesfully.");

			PivotOCLVisitor visitor = new PivotOCLVisitor(parser);
			
			KeplerToStringVisitor keplerVisitor = new KeplerToStringVisitor();			
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
