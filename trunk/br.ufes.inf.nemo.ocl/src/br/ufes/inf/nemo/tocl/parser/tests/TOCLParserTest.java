package br.ufes.inf.nemo.tocl.parser.tests;

import java.io.File;
import java.io.IOException;

import org.eclipse.ocl.ParserException;

import br.ufes.inf.nemo.tocl.parser.TOCLParser;

public class TOCLParserTest {
	
	public static void main (String[] args)
    {    	
    	String refpath = new File("src/resources/br/ufes/inf/nemo/ocl/models/RunningExample.refontouml").getAbsolutePath();
    	String oclPath = new File("src/resources/br/ufes/inf/nemo/ocl/models/RunningExample.ocl").getAbsolutePath();
    	
    	try {
    		
    		TOCLParser parser = new TOCLParser(refpath, refpath.substring(0,refpath.lastIndexOf(File.separator)), "RunningExample-b");
    		parser.parseTemporalOCL(new File(oclPath));
		
		} catch (IOException e) {			
			e.printStackTrace();
		} catch(ParserException e){
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
    	
    	System.out.println("Temporal OCL parsed: /RunningExample.refontouml and /RunningExample.ocl");
    	
//    	refpath = new File("src/resources/br/ufes/inf/nemo/ocl/models/project.refontouml").getAbsolutePath();
//    	oclPath = new File("src/resources/br/ufes/inf/nemo/ocl/models/project.ocl").getAbsolutePath();
//    	
//    	try {
//    		
//    		TOCLParser parser = new TOCLParser(refpath);
//    		parser.parseTemporalOCL(new File(oclPath));
//		
//		} catch (IOException e) {			
//			e.printStackTrace();
//		} catch(ParserException e){
//			e.printStackTrace();
//		} catch(Exception e){
//			e.printStackTrace();
//		}    	
//    	
//    	System.out.println("Temporal OCL parsed: /project.refontouml and /project.ocl");
    }  
}
