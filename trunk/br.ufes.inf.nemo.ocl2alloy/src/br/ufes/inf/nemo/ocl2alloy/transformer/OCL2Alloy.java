package br.ufes.inf.nemo.ocl2alloy.transformer;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

import br.ufes.inf.nemo.ocl2alloy.exception.IteratorException;
import br.ufes.inf.nemo.ocl2alloy.exception.LiteralException;
import br.ufes.inf.nemo.ocl2alloy.exception.OperationException;
import br.ufes.inf.nemo.ocl2alloy.exception.StereotypeException;
import br.ufes.inf.nemo.ocl2alloy.exception.TypeException;

import br.ufes.inf.nemo.ocl2alloy.options.OCLOptions;
import br.ufes.inf.nemo.ocl2alloy.parser.OCLParser;

import br.ufes.inf.nemo.ocl2alloy.visitor.ToAlloyVisitor;

/**
 * @author John Guerson
 */

public class OCL2Alloy {	
	
	public static String log;		
	public static Boolean succeeds;
	
	/**
	 * 
	 * @param oclparser
	 * @param opt
	 * @param refparser
	 * @return
	 */
	public static String Transformation (OCLParser oclparser, OCLOptions opt, OntoUMLParser refparser)
	{
		String result = new String();			
		log = new String();		
		succeeds = false;
		
		ToAlloyVisitor myVisitor = new ToAlloyVisitor(oclparser,refparser);
		
		/*
		 * Tests:
		 * org.eclipse.ocl.util.ToStringVisitor visitor = org.eclipse.ocl.util.ToStringVisitor.getInstance(oclparser.getUMLEnvironment());
		 * System.out.prinln("");
		 * System.out.println(visitor.visitConstraint(ct));
		 */	
		
		for(Constraint ct: opt.getConstraintList())
		{	
			try{	
		
				myVisitor.stereo_invariant = opt.getTransformationType(ct);
				result += myVisitor.visitConstraint(ct);
				succeeds = true;
				
			}catch(IteratorException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";
				succeeds=false;
				
			}catch(LiteralException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";;
				succeeds=false; 
				
			}catch(OperationException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";;
				succeeds=false; 
				
			}catch(StereotypeException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";;
				succeeds=false; 
				
			}catch(TypeException e){
				log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";;
				succeeds=false; 
			}			
		}
				
		return result;
	}
	
	/**
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
				
		ToAlloyVisitor myVisitor = new ToAlloyVisitor(oclparser,refparser);
						
		if(stereo.equals("RESTRIC")) myVisitor.stereo_invariant="RESTRICT";		
		if(stereo.equals("SIMULATE")) myVisitor.stereo_invariant="SIMULATE";		
		if(stereo.equals("CHECK")) myVisitor.stereo_invariant="CHECK";
		
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
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";
			succeeds=false; 
			
		}catch(LiteralException e){
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";;
			succeeds=false; 
			
		}catch(OperationException e){
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";;
			succeeds=false; 
			
		}catch(StereotypeException e){
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";;
			succeeds=false; 
			
		}catch(TypeException e){
			log += "Constraint: "+ct.getName()+"\n"+e.getMessage()+"\n"+"This Constraint was not transformed into Alloy.\n";;
			succeeds=false; 
		}
		
		if (succeeds) log += "\nTransforming OCL constraints... \nExecuted succesfully.";
		return result;
	}
}
