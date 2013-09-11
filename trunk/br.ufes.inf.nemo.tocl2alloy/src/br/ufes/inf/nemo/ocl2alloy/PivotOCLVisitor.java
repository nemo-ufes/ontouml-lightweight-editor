package br.ufes.inf.nemo.ocl2alloy;

import java.io.IOException;

import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.prettyprint.EssentialOCLPrettyPrintVisitor;
import org.eclipse.ocl.examples.pivot.prettyprint.PrettyPrinter;
import org.eclipse.ocl.examples.pivot.util.AbstractVisitor;

public class PivotOCLVisitor extends EssentialOCLPrettyPrintVisitor {

	 public static PrettyPrinter.Factory FACTORY = new Factory();
	
	 public PivotOCLVisitor(PrettyPrinter context) 
	 {
         super(context);
	 }

	 private static final class Factory implements PrettyPrinter.Factory
     {
         private Factory() {
             EssentialOCLPrettyPrintVisitor.FACTORY.getClass();            
         }

         public AbstractVisitor<Object, PrettyPrinter> createPrettyPrintVisitor(PrettyPrinter printer) 
         {
             return new PivotOCLVisitor(printer);
         }
     }    
     
	 @Override
	 public Object visitConstraint(Constraint ct)
	 {
		 return new String("blabla");
	 }

	 public static void main (String[]args)
	 {
		 String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.ocl";				
		 String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.refontouml";
				
		 try {
			
			PivotOCLParser parser = new PivotOCLParser(oclPath,refPath);
			parser.parse();
			System.out.println("OCL parsed.");
			
			for(Constraint ct: parser.getConstraints())
			{
				
			}
			
		 } catch (IOException e) {
			e.printStackTrace();
			
		 } catch (ParserException e) {
			e.printStackTrace();
		 }
		
		
	}
}
