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
import RefOntoUML.Model;
import RefOntoUML.NamedElement;
import RefOntoUML.PackageableElement;
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

public class ModelDiagnostician {

	private int warnings = 0;
	private int errors = 0;
	
	public int getErrors() {return errors;}
	public int getWarnings() {return warnings;}
	
	public static void main(String args[])
	{	
		ModelDiagnostician pv = new ModelDiagnostician();		
		try {
			System.out.println(pv.getWarnings(new OntoUMLParser("src/br/ufes/inf/nemo/common/ontoumlverificator/NameTest.refontouml")));
		} catch (IOException e) {
			e.printStackTrace();
		}			
	}
		
	/**
	 * Constructor
	 */
	public ModelDiagnostician () 
	{
		
	}	
		
	/**
	 * Get Element Name
	 */
	public String getName(NamedElement c)
	{
		if (c.getName()==null) return "null";
		else if (c.getName().trim().isEmpty()) return"<empty>"; 
		else return c.getName();
	}
	
	public String getElement (RefOntoUML.PackageableElement c)
	{
		return ""+c.getClass().getSimpleName().replace("Impl", "")+" "+getName(c);		
	}
	
	public String getPath (EObject e)
	{
		String path = "";				
		if (e.eContainer()!=null) path += getPath((e.eContainer()))+" ::";
		path += " "+e.getClass().getSimpleName().replace("Impl", "")+" ";		
		path += getName(((NamedElement)e))+"";		
		return path;
	}
	
	/**
	 * Get Description (Element type, name and path).
	 */
	public String getDescription (RefOntoUML.PackageableElement c)
	{
		return 
		"   Element: <"+c.getClass().getSimpleName().replace("Impl", "")+"> "+getName(c) + "\n"+
		"   Path: "+getPath(c)+"\n"+"\n";		
	}
		
	/**
	 * Get Description (Element type, name and path).
	 */
	public String getDescription (RefOntoUML.Property p)
	{
		return 
		"   Association End: <"+p.getClass().getSimpleName().replace("Impl", "")+"> "+getName(p) + "\n"+
		"   Path: "+getPath(p)+"\n"+"\n";		
	}
	
	public String getErrors (OntoUMLParser ontoparser)
	{
		String errors = new String();
		int ecount=0;
		
		ArrayList<String> stereotypeList = new ArrayList<String>();
		ArrayList<String> oclkeywordList = new ArrayList<String>();
		
		for(RefOntoUML.PackageableElement c: ontoparser.getAllInstances(RefOntoUML.PackageableElement.class))
		{			
			if (!(c instanceof RefOntoUML.Generalization) && !(c instanceof RefOntoUML.GeneralizationSet))
			{								
				if (!isValidStereotype(c)) 
				{ 				
					stereotypeList.add(getDescription(c));				
				}
				if (isOCLkeyword(c.getName())) 
				{ 				
					oclkeywordList.add(getDescription(c));				
				}	
			}
		}
		
		if (stereotypeList.size()>0) 
		{
			ecount++;
			errors += "#"+ecount+" Error: Elements stereotype not supported.\n\n" ;
			for(String str: stereotypeList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}	
		if (oclkeywordList.size()>0) 
		{
			ecount++;
			errors += "#"+ecount+" Error: Elements names are OCL keywords.\n\n";
			for(String str: oclkeywordList) { errors += str; }
			errors+="--------------------------------------------------\n\n";
		}
		
		// ------------------------
		
		ArrayList<String> abstractList = new ArrayList<String>();
		for(RefOntoUML.Type c: ontoparser.getAllInstances(RefOntoUML.Type.class))
		{			
			if((c instanceof MixinClass) && (((MixinClass)c).isIsAbstract()== false)) 
			{ 				 
				abstractList.add(getDescription(c));
			}				
		}		
		if (abstractList.size()>0) 
		{
			ecount++;
			errors += "#"+ecount+" Error: Categories, mixins and roleMixins must be abstract.\n\n"; 
			for(String str: abstractList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}
		
		// ------------------------
		
		ArrayList<String> nullTypeList = new ArrayList<String>();
		
		for(RefOntoUML.Property p: ontoparser.getAllInstances(RefOntoUML.Property.class))		
		{			
			if (p.getType()==null) nullTypeList.add(getDescription(p));
		}
		if(nullTypeList.size()>0)
		{
			ecount++;
			errors += "#"+ecount+" Error: Association end type is null.\n\n" ;
			for(String str: nullTypeList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}	
		
		// ------------------------
		
		ArrayList<String> twoEndList = new ArrayList<String>();
		ArrayList<String> srcMedList = new ArrayList<String>();
		ArrayList<String> tgtMedList = new ArrayList<String>();
		ArrayList<String> srcMerList = new ArrayList<String>();
		ArrayList<String> tgtMerList = new ArrayList<String>();
		ArrayList<String> srcChrList = new ArrayList<String>();
		ArrayList<String> tgtChrList = new ArrayList<String>();
		
		for(RefOntoUML.Association c: ontoparser.getAllInstances(RefOntoUML.Association.class))
		{			
			if(c.getMemberEnd().size()!=2)			
			{ 
				twoEndList.add(getDescription(c));			
			}
			if(c instanceof Mediation)
			{
				Mediation m = (Mediation) c;				
				if(!(m.relator() instanceof Relator))
				{ 					
					srcMedList.add(getDescription(c));
				}				
				if(m.mediatedEnd().isIsReadOnly()==false)
				{
					tgtMedList.add(getDescription(c));
				}
			}
			if(c instanceof Meronymic)
			{
				Meronymic m = (Meronymic)c;				
				if(m.getMemberEnd().size()==2)
				{
					if(m.wholeEnd().getAggregation().equals(AggregationKind.NONE)) 
					{
						srcMerList.add(getDescription(c));
					}				
					if(!m.partEnd().getAggregation().equals(AggregationKind.NONE)) 
					{
						tgtMerList.add(getDescription(c));
					}
				}				
			}			
			if(c instanceof Characterization)
			{
				Characterization m = (Characterization) c;				
				if(m.getMemberEnd().size()==2)
				{
					if(!(m.mode() instanceof Mode))
					{
						srcChrList.add(getDescription(c));
					}				
					if(((Characterization) c).characterizedEnd().isIsReadOnly()==false)
					{
						tgtChrList.add(getDescription(c));
					}
				}
			}			
		}
		if(twoEndList.size()>0)
		{
			ecount++;
			errors += "#"+ecount+" Error: Association hasn't two association ends.\n\n" ;
			for(String str: twoEndList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}	
		if(srcMedList.size()>0)
		{
			ecount++;
			errors += "#"+ecount+" Error: Source end type of mediation must be a Relator.\n\n" ;
			for(String str: srcMedList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}
		if(tgtMedList.size()>0)
		{
			ecount++;
			errors += "#"+ecount+" Error: Mediated end of mediation must be Read Only. \n\n" ;
			for(String str: tgtMedList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}			
		if(srcMerList.size()>0)
		{
			ecount++;
			errors += "#"+ecount+" Error: Whole must have aggregation kind equal to Composite or Shared.\n\n" ;
			for(String str: srcMerList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}
		if(tgtMerList.size()>0)
		{
			ecount++;
			errors += "#"+ecount+" Error: Part must have aggregation kind equal to None. \n\n" ;
			for(String str: tgtMerList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}
		if(srcChrList.size()>0)
		{
			ecount++;
			errors += "#"+ecount+" Error: Source end type of characterization must be a Mode.\n\n" ;
			for(String str: srcChrList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}
		if(tgtChrList.size()>0)
		{
			ecount++;
			errors += "#"+ecount+" Error: Characterized end of characterization must be Read Only.\n\n" ;
			for(String str: tgtChrList) { errors += str; } 
			errors+="--------------------------------------------------\n\n";
		}		
		
		String result = new String();		
		
		if (ecount>0) {			
			result += "\n\nThe following error(s) were found:\n\n";
			result += "--------------------------------------------------\n\n";
			result += errors;
		}else{
			result += "No error was found. ";
		}
		return result;
	}	
	
	/**
	 * Get Warnings in a Matrix format
	 * 
	 * @param ontoparser
	 * @return
	 */
	public ArrayList<ArrayList<String>> getWarningsMatrixFormat (OntoUMLParser ontoparser)
	{
		ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
		warnings = 0;
		
		// # Warning : Unnamed Element
		
		for(RefOntoUML.PackageableElement c: ontoparser.getAllInstances(RefOntoUML.PackageableElement.class))
		{			
			if (!(c instanceof RefOntoUML.Generalization) && !(c instanceof RefOntoUML.GeneralizationSet))
			{
				if (c.getName()==null || c.getName().trim().isEmpty()) 
				{ 				
					ArrayList<String> line = new ArrayList<String>();
					warnings++;
					line.add(warnings+". Unnamed element");
					line.add(getElement(c));
					line.add(getPath(c));
					items.add(line);					
				}				
			}
		}

		// # Warning : Duplicated name
		
		ArrayList< ArrayList<PackageableElement>> duplicatedNames = new ArrayList< ArrayList<PackageableElement>>();
		
		boolean dontAdd;		
		for(RefOntoUML.PackageableElement c: ontoparser.getAllInstances(RefOntoUML.PackageableElement.class))
		{		
			ArrayList<RefOntoUML.PackageableElement> duplicatedNamesList = getDuplicatedNamesList(c,ontoparser);
			if (duplicatedNamesList.size()>1) 
			{
				dontAdd = false;
				for(ArrayList<RefOntoUML.PackageableElement> list: duplicatedNames)
				{
					if (list.containsAll(duplicatedNamesList) && (list.size() >= duplicatedNamesList.size()))
					{
						dontAdd = true; 
					}
				}
				if (!dontAdd) duplicatedNames.add(duplicatedNamesList);				
			}
		}		
		if(duplicatedNames.size()>0)
		{				 
			for(ArrayList<RefOntoUML.PackageableElement> list: duplicatedNames) 
			{ 
				ArrayList<String> line = new ArrayList<String>();
				warnings++;
				line.add(warnings+". Duplicated name");
				int count=1;					
				for (RefOntoUML.PackageableElement e: list)
				{
					if(count==1){
						line.add(getElement(e));
						line.add(getPath(e));
						items.add(line);
					}else if (count<=list.size()) {
						ArrayList<String> row = new ArrayList<String>();
						row.add("");
						row.add(getElement(e));
						row.add(getPath(e));
						items.add(row);
					}						
					count++;
				}					
			}				
		}			
		
		return items;				
	}
	
	/**
	 * Get Warnings as String text...
	 * 
	 * @param ontoparser
	 * @return
	 */
	public String getWarnings (OntoUMLParser ontoparser)
	{		
		String warnings = new String();
		int wcount=0;
		
		// # Warning : Unnamed Element		
		
		ArrayList<String> unnamedList = new ArrayList<String>();		
		
		for(RefOntoUML.PackageableElement c: ontoparser.getAllInstances(RefOntoUML.PackageableElement.class))
		{			
			if (!(c instanceof RefOntoUML.Generalization) && !(c instanceof RefOntoUML.GeneralizationSet))
			{
				if (c.getName()==null || c.getName().trim().isEmpty()) 
				{ 				
					unnamedList.add(getDescription(c));				
				}				
				
			}
		}
		
		if (unnamedList.size()>0) 
		{
			wcount++;
			warnings += "#"+wcount+" Warning: Unnamed elements.\n\n"; 
			for(String str: unnamedList) { warnings += str; } 
			warnings+="--------------------------------------------------\n\n";
		}		
		
		// # Warning : Duplicated name
		
		ArrayList< ArrayList<PackageableElement>> duplicatedNames = new ArrayList< ArrayList<PackageableElement>>();
		
		boolean dontAdd;		
		for(RefOntoUML.PackageableElement c: ontoparser.getAllInstances(RefOntoUML.PackageableElement.class))
		{		
			ArrayList<RefOntoUML.PackageableElement> duplicatedNamesList = getDuplicatedNamesList(c,ontoparser);
			if (duplicatedNamesList.size()>1) 
			{
				dontAdd = false;
				for(ArrayList<RefOntoUML.PackageableElement> list: duplicatedNames)
				{
					if (list.containsAll(duplicatedNamesList) && (list.size() >= duplicatedNamesList.size()))
					{
						dontAdd = true; 
					}
				}
				if (!dontAdd) duplicatedNames.add(duplicatedNamesList);				
			}
		}
		if(duplicatedNames.size()>0)
		{
			wcount++;
			warnings += "#"+wcount+" Warning:  Elements names duplicated.\n\n"; 
			for(ArrayList<RefOntoUML.PackageableElement> list: duplicatedNames) 
			{ 
				for (RefOntoUML.PackageableElement e: list)
				{
					warnings += "   Path: "+getPath(e)+"\n";
				}
				warnings+="\n";
			}			
			warnings+="--------------------------------------------------\n\n";
		}
				
		// -------------------- 
		
		String result = new String();		
		
		if (wcount>0) {
			result += "\n\nThe following warning(s) were found:\n\n";
			result += "--------------------------------------------------\n\n";
			result += warnings;
		}else{
			result += "No warning was found.";
		}
		
		return result;
				
	}
	
	/**
	 * Get Elements that have their names equals...
	 * 
	 * @param e
	 * @param ontoparser
	 * @return
	 */
	public ArrayList<RefOntoUML.PackageableElement> getDuplicatedNamesList (RefOntoUML.PackageableElement e, OntoUMLParser ontoparser)
	{
		ArrayList<RefOntoUML.PackageableElement> list = new ArrayList<RefOntoUML.PackageableElement>();
		
		if ( e.getName() == null || e.getName().trim().isEmpty())
		return list;
		
		list.add(e);
		
		for(RefOntoUML.PackageableElement c: ontoparser.getAllInstances(RefOntoUML.PackageableElement.class))
		{
			if (e.getName().equals(c.getName()) && !e.equals(c)) list.add(c);
		}		
		return list;
	}
	
	/**
	 * Verify if the stereotype is valid.
	 * 
	 * @param e
	 * @return
	 */
	public boolean isValidStereotype(EObject e)
	{
		if (e instanceof Kind || e instanceof Collective || e instanceof Quantity || e instanceof Category || e instanceof SubKind ||
			e instanceof Mixin || e instanceof RoleMixin || e instanceof Role || e instanceof Phase || e instanceof Relator ||
			e instanceof Mode || e instanceof DataType || e instanceof MaterialAssociation || e instanceof FormalAssociation || 
			e instanceof Mediation || e instanceof Characterization || e instanceof Derivation || e instanceof RefOntoUML.Package || e instanceof Model ||
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
	
	/**
	 * Verify if the name contains a OCL keyword...
	 * 
	 * @param name
	 * @return
	 */
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
		
	/**
	 * Rename a element from its unique alias.
	 * 
	 * @param e
	 * @param ontoparser
	 */
	public void renameNamedElementFromAlias (NamedElement e, OntoUMLParser ontoparser)
	{
		e.setName(ontoparser.getAlias(e));
	}
}
