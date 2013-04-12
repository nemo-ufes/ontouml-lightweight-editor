package br.ufes.inf.nemo.common.ontoumlverificator;

import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.AggregationKind;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Kind;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Meronymic;
import RefOntoUML.Mixin;
import RefOntoUML.MixinClass;
import RefOntoUML.Mode;
import RefOntoUML.NamedElement;
import RefOntoUML.Phase;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.Type;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ProblemVerificator {

	String details;
	
	public static void main(String args[])
	{	
		ProblemVerificator pv = new ProblemVerificator();
		
		try {
			System.out.println(pv.getDiagnostic(new OntoUMLParser("src/br/ufes/inf/nemo/common/ontoumlverificator/NameTest.refontouml")));
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
			
	public ProblemVerificator () {}	
		
	public String getName(NamedElement c)
	{
		if (c.getName()==null) return "null";
		else if (c.getName().trim().isEmpty()) return"<empty>"; 
		else return c.getName();
	}
	
	public String getTypeDescription (RefOntoUML.Type c)
	{
		return "Element: <"+c.getClass().getSimpleName().replace("Impl", "")+"> "+getName(c) + "\n"+
		"Path: "+getPath(c)+"\n";	
	}
	
	public String getDiagnostic (OntoUMLParser ontoparser)
	{		
		String result = new String();
		int i = 0;
		
		result += "The following item(s) were found:\n\n";
			
		String Warning1 = new String();
		Warning1 += "Warning #1: Unnamed elements.\n\n";		
		int Warning1Counter = 0;
		
		String Warning2 = new String();
		Warning2 += "Warning #2: Elements names are OCL keywords.\n\n";
		int Warning2Counter = 0;
		
		String Error1 = new String();
		Error1 += "Error #1: Elements stereotype not supported.\n\n";
		int Error1Counter = 0;
		
		for(RefOntoUML.Type c: ontoparser.getAllInstances(RefOntoUML.Type.class))
		{			
			String strItems = new String();			
			int items = 1;					
			
			// Warning #1
			if (c.getName()==null || c.getName().trim().isEmpty()) 
			{ 				
				Warning1 += getTypeDescription(c);
				Warning1 += "-----------------------------------\n";
				Warning1Counter++;
			}			
			// Warning #2
			if (isOCLkeyword(c.getName())) 
			{ 				
				Warning2 += getTypeDescription(c);
				Warning2 += "-----------------------------------\n";
				Warning2Counter++;
			}			
			// Error #1			
			if (!isValidStereotype(c)) 
			{ 				
				Error1 += getTypeDescription(c);
				Error1 += "-----------------------------------\n";
				Error1Counter++;
			}
			/*
			// Item 3. Duplicated Class Name
			ArrayList<RefOntoUML.Type> duplicateClasses = findDuplicateClasses(c,ontoparser);
			if(duplicateClasses.size()>0) 
			{				
				strItems += "Warning: Elements names duplicated.\n"; 
				strItems += "-"+getPath(c)+"\n";
				for (RefOntoUML.Type c1: duplicateClasses) { strItems += "-"+getPath(c1)+"\n"; }
				items++;
			}
						
			// Item 5. Not abstract Mixin Classes
			if((c instanceof MixinClass) && (((MixinClass)c).isIsAbstract()== false)) { strItems += "Error: Categories, mixins and roleMixins must be abstract."+"\n"; items++; }
			
			// Printing the diagnostic...			
			if (items>1) {
				
				i++;
				
				String diagnostic = new String();
				diagnostic += "\n["+i+"] Diagnostic : <"+c.getClass().getSimpleName().replace("Impl", "")+"> "+getName(c);
				diagnostic += "\n-----------------------------------\n";
				
				diagnostic += strItems;

				diagnostic += "-----------------------------------\n";				
				diagnostic += "Path: "+getPath(c)+"\n\n";
				
				result += diagnostic;
			}*/					
		}
		/*		
		for(RefOntoUML.Association c: ontoparser.getAllInstances(RefOntoUML.Association.class))
		{
			String strItems = new String();			
			int items = 1;					
			
			// Searching for items...
									
			// Item 1. Empty Association Name
			if (c.getName()==null || c.getName().trim().isEmpty()) { strItems += "Warning: Unnamed association."+"\n"; items++; }
			
			// Item 2. Association name Contains Reserved Keyword of OCL
			if (isOCLkeyword(c.getName())) { strItems += "Warning: Association name is an OCL keyword."+"\n"; items++; }			
					
			// Item 3. Duplicated Association name
			ArrayList<RefOntoUML.Association> duplicateAssociations = findDuplicateAssociation(c,ontoparser);
			if(duplicateAssociations.size()>0) 
			{				
				strItems +="Warning: Associations names duplicated: \n"; 
				strItems +="-"+getPath(c)+"\n";
				for (RefOntoUML.Association c1: duplicateAssociations) { strItems += "-"+getPath(c1)+"\n"; }
				items++;
			}
			
			// Item 4. Unsupported stereotype			
			if (! isValidStereotype(c)) { strItems += "Error: Association stereotype not supported."+"\n"; items++; }
			
			// Item 5. Two association ends
			if(c.getMemberEnd().size()!=2)			
			{ strItems += "Error: Instead of two, this association has "+c.getMemberEnd().size()+ " association ends."+"\n"; items++; }
			
			// Item 6. Mediation items...
			if(c instanceof Mediation)
			{
				Mediation m = (Mediation) c;
				
				if(!(m.relator() instanceof Relator))
				{ strItems += "Error: Source end type must be a Relator."+"\n"; items++; }
				
				if(m.mediatedEnd().isIsReadOnly()==false)
				{ strItems += "Error: Mediated end must have isReadOnly = true."+"\n"; items++; }
			}
			
			// Items 7. Meronymic items...			
			if(c instanceof Meronymic)
			{
				Meronymic m = (Meronymic)c;
				
				if(m.getMemberEnd().size()==2)
				{
					if(m.wholeEnd().getAggregation().equals(AggregationKind.NONE)) 
					{ strItems += "Error: Whole must have aggregation kind != NONE."+"\n"; items++; }
				
					if(!m.partEnd().getAggregation().equals(AggregationKind.NONE)) 
					{ strItems += "Error: Part must have aggregation kind = NONE."+"\n"; items++; }
				}				
			}
			
			// Item 8. Characterization items...
			if(c instanceof Characterization)
			{
				Characterization m = (Characterization) c;
				
				if(m.getMemberEnd().size()==2)
				{
					if(!(m.mode() instanceof Mode))
					{ strItems += "Error: Source end type must be a Mode."+"\n"; items++; }
				
					if(((Characterization) c).characterizedEnd().isIsReadOnly()==false)
					{ strItems += "Error: Characterized End must have isReadOnly = true."+"\n"; items++; }
				}
			}			
						
			// Printing the diagnostic...
			if (items>1) {
				
				i++;
				
				String diagnostic = new String();
				diagnostic += "\n["+i+"] Diagnostic : <"+c.getClass().getSimpleName().replace("Impl", "")+"> "+getName(c);
				diagnostic += "\n-----------------------------------\n";
				
				diagnostic += strItems;

				diagnostic += "-----------------------------------\n";
				diagnostic += "Path: "+getPath(c)+"\n\n";
				
				result += diagnostic;
			}			
		}	
		
		for(RefOntoUML.Property p: ontoparser.getAllInstances(RefOntoUML.Property.class))		
		{			
			String strItems = new String();			
			int items = 1;					
			
			// Searching for items...
			
			// Item 1. Unnamed property name
			if (p.getName()==null || p.getName().trim().isEmpty()) { strItems += "Warning: Unnamed property."+"\n"; items++; }
			
			// Item 2. property name contains reserved keyword of OCL
			if (isOCLkeyword(p.getName())) { strItems += "Warning: Property name is an OCL keyword."+"\n"; items++; }	
							
			// Item 3. Type null
			if (p.getType()==null)
			{ strItems += "Error: Type is null."+"\n"; items++; }
			
			// Printing the diagnostic...
			if (items>1) {
				
				i++;
				
				String diagnostic = new String();
				diagnostic += "\n["+i+"] Diagnostic : <"+p.getClass().getSimpleName().replace("Impl", "")+"> "+getName(p);
				diagnostic += "\n-----------------------------------\n";
				
				diagnostic += strItems;

				diagnostic += "-----------------------------------\n";
				diagnostic += "Path: "+getPath(p)+"\n\n";
				
				result += diagnostic;
			}

		}
	*/
		if (Warning1Counter>0) result += Warning1+"\n";
		if (Warning2Counter>0) result += Warning2+"\n";
		if (Error1Counter>0) result += Error1+"\n";	
		return result;
				
	}
	
	public ArrayList<RefOntoUML.Association> findDuplicateAssociation (RefOntoUML.Association e, OntoUMLParser ontoparser)
	{
		ArrayList<RefOntoUML.Association> list = new ArrayList<RefOntoUML.Association>();
		
		if ( e.getName() == null || e.getName().trim().isEmpty())
			return list;
		
		for(RefOntoUML.Association c: ontoparser.getAllInstances(RefOntoUML.Association.class))
		{
			if (e.getName().equals(c.getName()) && !e.equals(c)) list.add(c);
		}		
		return list;
	}
	
	public ArrayList<RefOntoUML.Type> findDuplicateClasses (RefOntoUML.Type e, OntoUMLParser ontoparser)
	{
		ArrayList<RefOntoUML.Type> list = new ArrayList<RefOntoUML.Type>();
		
		if ( e.getName() == null || e.getName().trim().isEmpty())
			return list;
		
		for(RefOntoUML.Type c: ontoparser.getAllInstances(RefOntoUML.Type.class))
		{
			if (e.getName().equals(c.getName()) && !e.equals(c)) list.add(c);
		}		
		return list;
	}
	
	public boolean isValidStereotype(EObject e)
	{
		if (e instanceof Kind || e instanceof Collective || e instanceof Quantity || e instanceof Category || e instanceof SubKind ||
			e instanceof Mixin || e instanceof RoleMixin || e instanceof Role || e instanceof Phase || e instanceof Relator ||
			e instanceof Mode || e instanceof DataType || e instanceof MaterialAssociation || e instanceof FormalAssociation || 
			e instanceof Mediation || e instanceof Characterization || e instanceof Derivation ||
			e instanceof componentOf || e instanceof memberOf || e instanceof subCollectionOf || e instanceof subQuantityOf
		) return true;
			
		if (e instanceof Association)
		{
			Association assoc = (Association)e;
			Type srcType = null;
			Type tgtType = null;
			
			if (assoc.getMemberEnd().size()==1)
				srcType = assoc.getMemberEnd().get(0).getType();
			if (assoc.getMemberEnd().size()==2)
				tgtType = assoc.getMemberEnd().get(1).getType();
			
			if (srcType instanceof DataType || tgtType instanceof DataType) return true;
		}
		return false;	
	}
	
	public boolean isOCLkeyword (String name)
	{
		if (name == null )
			return false;
		
		if ( name =="and" || name =="body" ||name =="context" ||name =="def" ||name =="derive" ||name =="else" ||
		     name =="init" ||name.equals("inv") ||name =="invalid" ||name =="let" ||name =="not" ||name =="null" ||
		     name =="endif" ||name =="endpackage" ||name =="false" ||name =="if" ||name =="implies" ||name =="in" ||
		     name =="or" ||name =="package" ||name =="post" ||name =="static" ||name =="true" ||name =="then" ||		 
		     name =="xor" ||name =="Bag" ||name =="Boolean" ||name =="Collection" ||name =="Integer" ||name =="OclAny" ||
		     name =="OclInvalid" ||name =="OclMessage" ||name =="OclVoid" ||name =="OrderedSet" ||name =="Real" ||name =="Sequence" ||
		     name =="Set" ||name =="String" ||name =="Tuple" ||name =="UnlimitedNatural" 
		) return true;
		
		return false;
	}
		
	public void renameNamedElementFromAlias (NamedElement e, OntoUMLParser ontoparser)
	{
		e.setName(ontoparser.getAlias(e));
	}
	
	public String getPath (EObject e)
	{
		String path = "";
				
		if (e.eContainer()!=null) 
			path += getPath((e.eContainer()))+" ::";

		path += " <"+e.getClass().getSimpleName().replace("Impl", "")+"> ";
		
		path += getName(((NamedElement)e))+"";
		
		return path;
	}
}
