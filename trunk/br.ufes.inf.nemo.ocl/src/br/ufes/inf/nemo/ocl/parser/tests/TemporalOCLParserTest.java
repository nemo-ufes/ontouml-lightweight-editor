package br.ufes.inf.nemo.ocl.parser.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.ocl.parser.OCLParser;

public class TemporalOCLParserTest {
	
	public static void main (String[] args)
    {    	
    	String refpath = new File("src/br/ufes/inf/nemo/ocl/parser/tests/models/project.refontouml").getAbsolutePath();
    	String oclPath = new File("src/br/ufes/inf/nemo/ocl/parser/tests/models/temporalProject.ocl").getAbsolutePath();
    	
    	try {
    		
    		OCLParser parser = new OCLParser(refpath,true);
    		parser.parseTemporalOCL(new File(oclPath));
		
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
