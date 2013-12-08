package br.ufes.inf.nemo.ocl.ocl2alloy;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.ocl.ocl2alloy.exception.IteratorException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.LiteralException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.OperationException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.StereotypeException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.TypeException;
import br.ufes.inf.nemo.ocl.parser.OCLParser;

/**
 * @author John Guerson
 */

public class OCL2Alloy {	
	
	public static String log;		
	public static Boolean succeeds;
	
	public static String convertToAlloy(OCLParser oclparser)
	{
		String result = new String();			
		log = new String();		
		succeeds = false;
				
		OCLVisitor myVisitor = new OCLVisitor(oclparser,oclparser.getOntoUMLParser());
		
		OCL2AlloyOptions opt = new OCL2AlloyOptions(oclparser);
		for(Constraint ct: opt.getConstraintList())
		{	
			try{
		
				myVisitor.stereotype = opt.getTransformationType(ct);
				result += myVisitor.visitConstraint(ct);
				succeeds = true;
				
			}catch(IteratorException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false;				
			}catch(LiteralException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false;				
			}catch(OperationException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false;				
			}catch(StereotypeException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false;				
			}catch(TypeException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false; 
			}			
		}
				
		return result;
	}	
	
	public static String convertToAlloy (OCLParser oclparser, OCL2AlloyOptions opt)
	{
		String result = new String();			
		log = new String();		
		succeeds = false;
		
		OCLVisitor myVisitor = new OCLVisitor(oclparser,oclparser.getOntoUMLParser());
				
		for(Constraint ct: opt.getConstraintList())
		{	
			try{
		
				myVisitor.stereotype = opt.getTransformationType(ct);
				result += myVisitor.visitConstraint(ct);
				succeeds = true;
				
			}catch(IteratorException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false;				
			}catch(LiteralException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false;				
			}catch(OperationException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false;				
			}catch(StereotypeException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false;				
			}catch(TypeException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
				succeeds=false; 
			}			
		}
				
		return result;
	}
	
	/**
	 * Makes the transformation from OCL to Alloy compliant with OntoUML.
	 * 
	 * @param ct
	 * @param stereo
	 * @param oclparser
	 * @param refparser
	 * @return
	 */
	public static String convertToAlloy (Constraint ct, String stereo, OCLParser oclparser)
	{
		String result = new String();	
		log = new String();		
		succeeds = false;
				
		OCLVisitor myVisitor = new OCLVisitor(oclparser,oclparser.getOntoUMLParser());
						
		if(stereo.equals("FACT")) myVisitor.stereotype="FACT";		
		if(stereo.equals("SIMULATE")) myVisitor.stereotype="SIMULATE";		
		if(stereo.equals("CHECK")) myVisitor.stereotype="CHECK";
		
		try{			
			
			result += myVisitor.visitConstraint(ct);
						
			succeeds = true;			
						
		}catch(IteratorException e){
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
			succeeds=false; 
			
		}catch(LiteralException e){
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
			succeeds=false; 
			
		}catch(OperationException e){
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
			succeeds=false; 
			
		}catch(StereotypeException e){
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
			succeeds=false; 
			
		}catch(TypeException e){
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n";
			succeeds=false; 
		}
		
		if (succeeds) log += "\nOCL2Alloy executed successfully.";
		return result;
	}
}
