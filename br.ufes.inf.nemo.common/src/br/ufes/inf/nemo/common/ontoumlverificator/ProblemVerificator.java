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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
	}
			
	public ProblemVerificator () {}	
		
	public String getName(NamedElement c)
	{
		if (c.getName()==null) return "<null-name>";
		else if (c.getName().trim().isEmpty()) return"<empty-name>"; 
		else return c.getName();
	}
	
	public String getDiagnostic (OntoUMLParser ontoparser)
	{		
		String result = new String();
		int i = 1;
		
		result += "The following error(s) where found:\n\n";
			
		for(RefOntoUML.Type c: ontoparser.getAllInstances(RefOntoUML.Type.class))
		{
			String diagnostic = new String();
			int items = 0;
			
			diagnostic += "\n["+i+"] Diagnostic : <"+c.getClass().getSimpleName().replace("Impl", "")+"> "+getName(c)+"\n-----------------------------------\n";;			 
									
			// Empty Element Name
			if (c.getName()==null || c.getName().trim().isEmpty()) { diagnostic += "Unnamed Element."+"\n"; items++; }
			
			// Type name Contains Reserved Keyword of OCL
			if (isOCLkeyword(c.getName())) { diagnostic += "Element name is an OCL keyword."+"\n"; items++; }			
									
			// duplicated Class Name
			ArrayList<RefOntoUML.Type> duplicateClasses = findDuplicateClasses(c,ontoparser);
			if(duplicateClasses.size()>0) 
			{
				items++;
				diagnostic += "Duplicated Elements...\n"; 
				diagnostic += "-"+getPath(c)+"\n";
				for (RefOntoUML.Type c1: duplicateClasses) { diagnostic += "-"+getPath(c1)+"\n"; }
			}
			
			// unsupported stereotype			
			if (! isValidStereotype(c)) { diagnostic += "Element Stereotype not supported."+"\n"; items++; }
						
			// not abstract Mixin Classes
			if((c instanceof MixinClass) && (((MixinClass)c).isIsAbstract()== false)) { diagnostic += "Categories, Mixins and roleMixins must be abstract."+"\n"; items++; }
			
			// path
			diagnostic += "-----------------------------------\nElement Path: "+getPath(c)+"\n\n";
			
			i++;
			System.out.print(diagnostic);
			if (items>0) result += diagnostic;
			
		}
				
		for(RefOntoUML.Association c: ontoparser.getAllInstances(RefOntoUML.Association.class))
		{
			String diagnostic = new String();
			int items = 0;
			
			diagnostic += "\n["+i+"] Diagnostic : <"+c.getClass().getSimpleName().replace("Impl", "")+"> "+getName(c)+"\n-----------------------------------\n";;	
						
			// Empty Association Name
			if (c.getName()==null || c.getName().trim().isEmpty()) { diagnostic += "Unnamed Association."+"\n"; items++; }
			
			// Association name Contains Reserved Keyword of OCL
			if (isOCLkeyword(c.getName())) { diagnostic += "Association name is an OCL keyword."+"\n"; items++; }			
					
			// duplicated Association name
			ArrayList<RefOntoUML.Association> duplicateAssociations = findDuplicateAssociation(c,ontoparser);
			if(duplicateAssociations.size()>0) 
			{
				items++;
				diagnostic +="Duplicated Associations: \n"; 
				diagnostic +="-"+getPath(c)+"\n";
				for (RefOntoUML.Association c1: duplicateAssociations) { diagnostic += "-"+getPath(c1)+"\n"; }
			}
			
			// unsupported stereotype			
			if (! isValidStereotype(c)) { diagnostic += "Association Stereotype not supported."+"\n"; items++; }
			
			// two assocaition ends
			if(c.getMemberEnd().size()!=2)			
				{ diagnostic += "Instead of two, this association has "+c.getMemberEnd().size()+ " association ends."+"\n"; items++; }
			
			// mediation
			if(c instanceof Mediation)
			{
				Mediation m = (Mediation) c;
				
				if(!(m.relator() instanceof Relator))
					{ diagnostic += "Source end type must be a Relator."+"\n"; items++; }
				if(m.mediatedEnd().isIsReadOnly()==false)
					{ diagnostic += "Mediated end must have isReadOnly = true."+"\n"; items++; }
			}
			
			// meronymic			
			if(c instanceof Meronymic)
			{
				Meronymic m = (Meronymic)c;
				
				if(m.getMemberEnd().size()==2)
				{
					if(m.wholeEnd().getAggregation().equals(AggregationKind.NONE)) 
						{ diagnostic += "Whole must have aggregation kind != NONE."+"\n"; items++; }
				
					if(!m.partEnd().getAggregation().equals(AggregationKind.NONE)) 
						{ diagnostic += "Part must have aggregation kind = NONE."+"\n"; items++; }
				}				
			}
			
			// characterization
			if(c instanceof Characterization)
			{
				Characterization m = (Characterization) c;
				
				if(m.getMemberEnd().size()==2)
				{
					if(!(m.mode() instanceof Mode))
						{ diagnostic += "Source end type must be a Mode."+"\n"; items++; }
				
					if(((Characterization) c).characterizedEnd().isIsReadOnly()==false)
						{ diagnostic += "Characterized End must have isReadOnly = true."+"\n"; items++; }
				}
			}			
						
			// path
			diagnostic += "-----------------------------------\nElement Path: "+getPath(c)+"\n\n";
			
			i++;
			System.out.print(diagnostic);
			if (items>0) result += diagnostic;
		}	
		
		for(RefOntoUML.Property p: ontoparser.getAllInstances(RefOntoUML.Property.class))		
		{
			
			String diagnostic = new String();
			int items = 0;
			
			diagnostic += "\n["+i+"] Diagnostic : <"+p.getClass().getSimpleName().replace("Impl", "")+"> "+getName(p)+"\n-----------------------------------\n";;	

			// type null
			if (p.getType()==null)
				{ diagnostic += "Type is null."+"\n"; items++; }
		
			// Unnamed property name
			if (p.getName()==null || p.getName().trim().isEmpty()) { diagnostic += "Unnamed Property."+"\n"; items++; }
			
			// property name Contains Reserved Keyword of OCL
			if (isOCLkeyword(p.getName())) { diagnostic += "Property name is an OCL keyword."+"\n"; items++; }	
							
			// path
			diagnostic += "-----------------------------------\nPath: "+getPath(p)+"\n\n";
			
			i++;
			System.out.print(diagnostic);
			if (items>0) result += diagnostic;
		}
				
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
			e instanceof Mediation || e instanceof Characterization ||
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

		path += " '<"+e.getClass().getSimpleName().replace("Impl", "")+"> ";
		
		path += getName(((NamedElement)e))+"'";
		
		return path;
	}
}
