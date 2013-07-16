package br.ufes.inf.nemo.ocl2alloy;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl2alloy.exception.IteratorException;
import br.ufes.inf.nemo.ocl2alloy.exception.LiteralException;
import br.ufes.inf.nemo.ocl2alloy.exception.OperationException;
import br.ufes.inf.nemo.ocl2alloy.exception.StereotypeException;
import br.ufes.inf.nemo.ocl2alloy.exception.TypeException;

/**
 * @author John Guerson
 */

public class OCL2Alloy {	
	
	public static String log;		
	public static Boolean succeeds;
	
	/**
	 * Makes the transformation from OCL to Alloy compliant with OntoUML.
	 * 
	 * @param oclparser
	 * @param opt
	 * @param refparser
	 * @return
	 */
	public static String Transformation (OCLParser oclparser, OCL2AlloyOptions opt, OntoUMLParser refparser)
	{
		String result = new String();			
		log = new String();		
		succeeds = false;
		
		OCLVisitor myVisitor = new OCLVisitor(oclparser,refparser);
		
		/*
		 * Tests:
		 * org.eclipse.ocl.util.ToStringVisitor visitor = org.eclipse.ocl.util.ToStringVisitor.getInstance(oclparser.getUMLEnvironment());
		 * System.out.prinln("");
		 * System.out.println(visitor.visitConstraint(ct));
		 */	
		
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
	public static String Transformation (Constraint ct, String stereo, OCLParser oclparser, OntoUMLParser refparser)
	{
		String result = new String();	
		log = new String();		
		succeeds = false;
				
		OCLVisitor myVisitor = new OCLVisitor(oclparser,refparser);
						
		if(stereo.equals("FACT")) myVisitor.stereotype="FACT";		
		if(stereo.equals("SIMULATE")) myVisitor.stereotype="SIMULATE";		
		if(stereo.equals("CHECK")) myVisitor.stereotype="CHECK";
		
		/*
		 * Tests:
		 * org.eclipse.ocl.util.ToStringVisitor visitor = org.eclipse.ocl.util.ToStringVisitor.getInstance(oclparser.getUMLEnvironment());
		 * System.out.prinln("");
		 * System.out.println(visitor.visitConstraint(ct));
		 */	
		
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
