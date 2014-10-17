package br.ufes.inf.nemo.ocl.ocl2alloy.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.ocl.ParserException;
import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2Alloy;
import br.ufes.inf.nemo.ocl.ocl2alloy.OCL2AlloyOption;
import br.ufes.inf.nemo.ocl.parser.OCLParser;

/**
 * @author John Guerson
 */

public class OCL2AlloyTests {

	public static void main (String[] args)
    {    	
    	String refpath = new File("src/resources/br/ufes/inf/nemo/ocl/models/Project.refontouml").getAbsolutePath();
    	String oclPath = new File("src/resources/br/ufes/inf/nemo/ocl/models/Project.ocl").getAbsolutePath();
    	
    	try {
    		
    		OCLParser parser = new OCLParser(refpath);
    		parser.parseStandardOCL(new File(oclPath));
		
    		//testing OCL pretty printer 
    		//just to know the form which the constraints are printed for debug in Eclipse    		
    		//prettyPrintOCL(parser);
    		
    		System.out.println("");
    		
    		String result = OCL2Alloy.convertToAlloy(parser, new OCL2AlloyOption(parser));
    	
    		System.out.println(result);
    		
		} catch (IOException e) {			
			e.printStackTrace();
		} catch(ParserException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}    	
    }    
	
	/**
	 * Pretty print OCL constraints
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void prettyPrintOCL(OCLParser oclparser)
	{			
		org.eclipse.ocl.util.ToStringVisitor visitor = org.eclipse.ocl.util.ToStringVisitor.getInstance(oclparser.getUMLEnvironment());		
		for(Constraint ct: oclparser.getConstraints()){
			System.out.println(visitor.visitConstraint(ct));
		}		 
	}
}
