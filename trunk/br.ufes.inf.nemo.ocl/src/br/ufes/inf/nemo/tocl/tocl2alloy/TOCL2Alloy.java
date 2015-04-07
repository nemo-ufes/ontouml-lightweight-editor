package br.ufes.inf.nemo.tocl.tocl2alloy;

import org.eclipse.uml2.uml.Association;
import org.eclipse.uml2.uml.Constraint;
import org.eclipse.uml2.uml.Property;
import org.eclipse.uml2.uml.Type;

import br.ufes.inf.nemo.alloy.AlloyFactory;
import br.ufes.inf.nemo.alloy.Declaration;
import br.ufes.inf.nemo.alloy.SignatureDeclaration;
import br.ufes.inf.nemo.alloy.api.AlloyAPI;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.IteratorException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.LiteralException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.OperationException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.StereotypeException;
import br.ufes.inf.nemo.ocl.ocl2alloy.exception.TypeException;
import br.ufes.inf.nemo.tocl.parser.TOCLParser;

public class TOCL2Alloy {
	
	public static String log;		
	public static Boolean succeeds;
	
	public static String convertHistoricalRelationships(AlloyFactory alsFactory, SignatureDeclaration sigObject, TOCLParser oclparser)
	{	 
		String result = new String();
		for(Association rel: ((TOCLParser)oclparser).historicalRelationshipsList)
		{
			Type sourceType = rel.getMemberEnds().get(0).getType();
			Type targetType = rel.getMemberEnds().get(1).getType();	
			int sourceLower = rel.getMemberEnds().get(0).getLower();
			int sourceUpper = rel.getMemberEnds().get(0).getUpper();
			int targetLower = rel.getMemberEnds().get(1).getLower();
			int targetUpper = rel.getMemberEnds().get(1).getUpper();			
			Declaration decl = AlloyAPI.createSimpleDeclaration(alsFactory,rel.getName(),"Object");
			if (decl!=null) sigObject.getRelation().add(decl);
			
			result += "fact historicalRelationship { \n";
			result += "\t"+rel.getName()+".univ in World."+sourceType.getName()+"\n";
			result += "\tuniv."+rel.getName()+" in World."+targetType.getName()+"\n";					   
			result += "\t# univ."+rel.getName()+" >="+sourceLower+"\n";
			if(sourceUpper!=-1) result += "# univ."+rel.getName()+" <="+sourceUpper+"\n";
			result += "\t# "+rel.getName()+".univ >="+targetLower+"\n";
			if(targetUpper!=-1) result += "# "+rel.getName()+".univ <="+targetUpper+"\n";
			result += "}\n\n";
			
			Property pSource = rel.getMemberEnds().get(0);
			Property pTarget = rel.getMemberEnds().get(1);
			
			result += "fun "+pTarget.getName()+" [src: World."+sourceType.getName()+"] : set World."+targetType.getName()+"{ \n";
			result += "\tsrc."+rel.getName()+" \n}\n\n";
			
			result += "fun "+pSource.getName()+" [tgt: World."+targetType.getName()+"] : set World."+sourceType.getName()+"{ \n";
			result += "\t"+rel.getName()+".tgt \n}\n\n";
		}

		return result;
	}
	
	public static String convertTemporalConstraints(TOCLParser oclparser)
	{
		String result = new String();			
		log = new String();		
		succeeds = false;		
		TOCL2AlloyOption opt = new TOCL2AlloyOption(oclparser);
		TOCL2AlloyVisitor myVisitor = new TOCL2AlloyVisitor(oclparser,oclparser.getOntoUMLParser(), opt);				
		for(Constraint ct: oclparser.getConstraints())
		{	
			try{				
				result += ((TOCL2AlloyVisitor)myVisitor).visitConstraint(ct);		
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
	
	public static String convertTemporalConstraints (TOCLParser oclparser, TOCL2AlloyOption opt)
	{
		String result = new String();			
		log = new String();		
		succeeds = false;		
		TOCL2AlloyVisitor myVisitor = new TOCL2AlloyVisitor(oclparser,oclparser.getOntoUMLParser(),opt);				
		for(Constraint ct: oclparser.getConstraints())
		{	
			try{
				result += ((TOCL2AlloyVisitor)myVisitor).visitConstraint(ct);				
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

	public static String convertConstraint (Constraint ct, String stereo, TOCLParser oclparser)
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
			result += ((TOCL2AlloyVisitor)myVisitor).visitConstraint(ct); succeeds = true;						
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
