package br.ufes.inf.nemo.ocl.parser.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.ocl.parser.OCLParser;

/**
 * @author John Guerson
 */
public class OCLParserTest {

    public static void main (String[] args)
    {    	
    	String refpath = new File("src/resources/br/ufes/inf/nemo/ocl/models/project.refontouml").getAbsolutePath();
    	String oclPath = new File("src/resources/br/ufes/inf/nemo/ocl/models/project.ocl").getAbsolutePath();
    	
    	try {
    		
    		OCLParser parser = new OCLParser(refpath);
    		parser.parseStandardOCL(new File(oclPath));
		
		} catch (IOException e) {			
			e.printStackTrace();
		} catch(ParserException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
    	
    	System.out.println("OCL parsed");
    }    
}
