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
		
		OCL2AlloyOption opt = new OCL2AlloyOption(oclparser);
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
		
		if (myVisitor.getCustomLibraryOperations()!=null && !myVisitor.getCustomLibraryOperations().isEmpty())
			result += myVisitor.getCustomLibraryOperations();
		
		return result;
	}	
	
	public static String convertToAlloy (OCLParser oclparser, OCL2AlloyOption opt)
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
				
		if (myVisitor.getCustomLibraryOperations()!=null && !myVisitor.getCustomLibraryOperations().isEmpty())
			result += myVisitor.getCustomLibraryOperations();
		
		return result;
	}
	
	/**
	 * Makes the transformation from an OCL constraint to Alloy compliant with an OntoUML model.
	 * 
	 * @param ct: Constraint
	 * @param stereo: "FACT","SIMULATE" or "CHECK"
	 * @param oclparser: OCL Parser related to a particular OntoUML model
	 * @return
	 */
	public static String convertConstraintToAlloy (Constraint ct, String stereo, OCLParser oclparser)
	{
		if (!stereo.equalsIgnoreCase("FACT") && !stereo.equalsIgnoreCase("SIMULATE") && !stereo.equalsIgnoreCase("CHECK"))
		{
			log += "Invalid Alloy stereotype. Possible values are: FACT, SIMULATE or CHECK. ";
			succeeds = false;
			return ""; 
		}
		
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
				
		if (myVisitor.getCustomLibraryOperations()!=null && !myVisitor.getCustomLibraryOperations().isEmpty())
			result += myVisitor.getCustomLibraryOperations();
		
		return result;
	}
}
