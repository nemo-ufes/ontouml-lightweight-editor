package br.ufes.inf.nemo.tocl.tocl2alloy;

import org.eclipse.uml2.uml.Constraint;

import br.ufes.inf.nemo.ocl.ocl2alloy.exception.IteratorException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.LiteralException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.OperationException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.StereotypeException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.TypeException;
import br.ufes.inf.nemo.tocl.parser.TOCLParser;

public class TOCL2Alloy {
	
	public static String log;		
	public static Boolean succeeds;
	
	public static String convertToAlloy(TOCLParser oclparser)
	{
		String result = new String();			
		log = new String();		
		succeeds = false;		
		TOCL2AlloyOption opt = new TOCL2AlloyOption(oclparser);
		TOCL2AlloyVisitor myVisitor = new TOCL2AlloyVisitor(oclparser,oclparser.getOntoUMLParser(), opt);				
		for(Constraint ct: oclparser.getConstraints())
		{	
			try{				
				result += myVisitor.visitConstraint(ct);		
				succeeds = true;				
			}catch(IteratorException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false;				
			}catch(LiteralException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false;				
			}catch(OperationException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false;				
			}catch(StereotypeException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false;				
			}catch(TypeException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false; 
			}			
		}		
		if (myVisitor.getLibrary()!=null && !myVisitor.getLibrary().isEmpty()) result += myVisitor.getLibrary();		
		return result;
	}	
	
	public static String convertToAlloy (TOCLParser oclparser, TOCL2AlloyOption opt)
	{
		String result = new String();			
		log = new String();		
		succeeds = false;		
		TOCL2AlloyVisitor myVisitor = new TOCL2AlloyVisitor(oclparser,oclparser.getOntoUMLParser(),opt);				
		for(Constraint ct: oclparser.getConstraints())
		{	
			try{
				result += myVisitor.visitConstraint(ct);				
				succeeds = true;				
			}catch(IteratorException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false;				
			}catch(LiteralException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false;				
			}catch(OperationException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false;				
			}catch(StereotypeException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false;				
			}catch(TypeException e){
				log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false; 
			}			
		}				
		if (myVisitor.getLibrary()!=null && !myVisitor.getLibrary().isEmpty()) result += myVisitor.getLibrary();		
		return result;
	}

	public static String convertConstraintToAlloy (Constraint ct, String stereo, TOCLParser oclparser)
	{
		if (stereo.equalsIgnoreCase("SIMULATION")) stereo = "SIMULATE";
		if (stereo.equalsIgnoreCase("ASSERTION")) stereo = "CHECK";
		if (stereo.equalsIgnoreCase("RESTRICT")) stereo = "FACT";
		if (!stereo.equalsIgnoreCase("FACT") && !stereo.equalsIgnoreCase("SIMULATE") && !stereo.equalsIgnoreCase("CHECK")) {
			log += "Invalid Alloy stereotype. Possible values are: FACT, SIMULATE or CHECK. ";
			succeeds = false;
			return ""; 
		}		
		String result = new String();	
		log = new String();		
		succeeds = false;				
		TOCL2AlloyOption opt = new TOCL2AlloyOption();
		opt.getTransformationType().set(opt.getTransformationType().indexOf(ct),stereo);
		TOCL2AlloyVisitor myVisitor = new TOCL2AlloyVisitor(oclparser, oclparser.getOntoUMLParser(), opt);		
		try{						
			result += myVisitor.visitConstraint(ct); succeeds = true;						
		}catch(IteratorException e){
			log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false; 			
		}catch(LiteralException e){
			log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false; 			
		}catch(OperationException e){
			log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false; 			
		}catch(StereotypeException e){
			log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false; 			
		}catch(TypeException e){
			log += "Temporal Conversion: "+ct.getName()+"\n"+e.getMessage()+"\n"; succeeds=false; 
		}				
		if (myVisitor.getLibrary()!=null && !myVisitor.getLibrary().isEmpty()) result += myVisitor.getLibrary();		
		return result;
	}
}
