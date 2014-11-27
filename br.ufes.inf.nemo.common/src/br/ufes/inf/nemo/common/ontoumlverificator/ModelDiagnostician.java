package br.ufes.inf.nemo.common.ontoumlverificator;

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
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Model;
import RefOntoUML.NamedElement;
import RefOntoUML.PackageableElement;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
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
import RefOntoUML.parser.OntoUMLParser;

public class ModelDiagnostician {

	private int warnings = 0;
	private int errors = 0;
	
	public int getErrors() {return errors;}
	public int getWarnings() {return warnings;}
	
//	public static void main(String args[])
//	{	
//		ModelDiagnostician pv = new ModelDiagnostician();		
//		try {
//			System.out.println(pv.getWarningsMatrixFormat(new OntoUMLParser("src/br/ufes/inf/nemo/common/ontoumlverificator/NameTest.refontouml")));
//		} catch (IOException e) {
//			e.printStackTrace();
//		}			
//	}
		
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
	
	public String getElement (RefOntoUML.Property c)
	{
		return ""+c.getClass().getSimpleName().replace("Impl", "")+" "+getName(c);		
	}	
	
	public String getPath (EObject e)
	{
		String path = "";				
		if (e.eContainer()!=null) path += getPath((e.eContainer()))+" ::";				
		path += getName(((NamedElement)e))+"";		
		return path;
	}	
	
	/**
	 * Get Errors in a Matrix format
	 * 
	 * @param ontoparser
	 * @return
	 */
	public ArrayList<ArrayList<String>> getErrorsMatrixFormat (OntoUMLParser ontoparser)
	{
		ArrayList<ArrayList<String>> items = new ArrayList<ArrayList<String>>();
		errors = 0;
		
		// # Error : Invalid stereotype
		for(RefOntoUML.PackageableElement c: ontoparser.getAllInstances(RefOntoUML.PackageableElement.class))
		{			
			if (!(c instanceof RefOntoUML.Generalization) && !(c instanceof RefOntoUML.GeneralizationSet))
			{								
				if (!isValidStereotype(c)) 
				{ 				
					ArrayList<String> line = new ArrayList<String>();
					errors++;
					line.add(String.format("%02d", errors)+". Invalid stereotype");					
					line.add(getElement(c));
					line.add(getPath(c));
					items.add(line);	
				}				
			}
		}
		
		// # Error : Name contains an OCL keyword
		for(RefOntoUML.PackageableElement c: ontoparser.getAllInstances(RefOntoUML.PackageableElement.class))
		{	
			if(!(c instanceof PrimitiveType))
			{
				if (isOCLkeyword(c.getName())) 
				{ 				
					ArrayList<String> line = new ArrayList<String>();
					errors++;
					line.add(String.format("%02d", errors)+". Name contains an OCL keyword");					
					line.add(getElement(c));
					line.add(getPath(c));
					items.add(line);
				}	
			}
		}
		for(RefOntoUML.Property c: ontoparser.getAllInstances(RefOntoUML.Property.class))
		{	
			if (isOCLkeyword(c.getName())) 
			{ 				
				ArrayList<String> line = new ArrayList<String>();
				errors++;
				line.add(String.format("%02d", errors)+". Name contains an OCL keyword");				
				line.add(getElement(c));
				line.add(getPath(c));
				items.add(line);
			}
		}
		
		// # Error : Association end type is null
		for(RefOntoUML.Property p: ontoparser.getAllInstances(RefOntoUML.Property.class))		
		{			
			if (p.getType()==null){
				ArrayList<String> line = new ArrayList<String>();
				errors++;
				line.add(String.format("%02d", errors)+". Association end type is null");				
				line.add(getElement(p));
				line.add(getPath(p));
				items.add(line);
			}
		}
		
		// # Error : Association hasn't two association ends.
		for(RefOntoUML.Association c: ontoparser.getAllInstances(RefOntoUML.Association.class))
		{			
			if(c.getMemberEnd().size()!=2)			
			{ 
				ArrayList<String> line = new ArrayList<String>();
				errors++;
				line.add(String.format("%02d", errors)+". Association has not two association ends");				
				line.add(getElement(c));
				line.add(getPath(c));
				items.add(line);							
			}
		}
		
		// # Error : Source end type is not a Relator
		// # Error : Mediated end is not read only
		for(RefOntoUML.Mediation m: ontoparser.getAllInstances(RefOntoUML.Mediation.class))
		{						
			if(!(m.relator() instanceof Relator))
			{ 					
				ArrayList<String> line = new ArrayList<String>();
				errors++;
				line.add(String.format("%02d", errors)+". Source end type must be a Relator");				
				line.add(getElement(m));
				line.add(getPath(m));
				items.add(line);
			}				
			if(m.mediatedEnd().isIsReadOnly()==false)
			{
				ArrayList<String> line = new ArrayList<String>();
				errors++;
				line.add(String.format("%02d", errors)+". Mediated end must be read only");				
				line.add(getElement(m));
				line.add(getPath(m));
				items.add(line);
			}
		}
		
		// # Error : Whole must have aggregation kind equal to Composite or Shared.
		// # Error : Part must have aggregation kind equal to None. 
		for(RefOntoUML.Meronymic m: ontoparser.getAllInstances(RefOntoUML.Meronymic.class))
		{					
			if(m.getMemberEnd().size()==2)
			{
				if(m.wholeEnd().getAggregation().equals(AggregationKind.NONE)) 
				{
					ArrayList<String> line = new ArrayList<String>();
					errors++;
					line.add(String.format("%02d", errors)+". Whole must have aggregation kind equal to composite or shared");					
					line.add(getElement(m));
					line.add(getPath(m));
					items.add(line);
				}				
				if(!m.partEnd().getAggregation().equals(AggregationKind.NONE)) 
				{
					ArrayList<String> line = new ArrayList<String>();
					errors++;
					line.add(String.format("%02d", errors)+". Part must have aggregation kind equal to none");					
					line.add(getElement(m));
					line.add(getPath(m));
					items.add(line);
				}
			}				
		}	
		
		// # Error : Source end type of characterization must be a Mode.
		// # Error : Characterized end of characterization must be read only.
		for(RefOntoUML.Characterization m: ontoparser.getAllInstances(RefOntoUML.Characterization.class))
		{						
			if(m.getMemberEnd().size()==2)
			{
				if(!(m.characterizing() instanceof Mode))
				{
					ArrayList<String> line = new ArrayList<String>();
					errors++;
					line.add(String.format("%02d", errors)+". Source end type of characterization must be a Mode");					
					line.add(getElement(m));
					line.add(getPath(m));
					items.add(line);
				}				
				if(m.characterizedEnd().isIsReadOnly()==false)
				{
					ArrayList<String> line = new ArrayList<String>();
					errors++;
					line.add(String.format("%02d", errors)+". Characterized end of characterization must be read only");					
					line.add(getElement(m));
					line.add(getPath(m));
					items.add(line);
				}
			}
		}	
		
		return items;
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
		
		// # Warning : Relator with Invalid Axiom
		
		for (RefOntoUML.Relator c: ontoparser.getRelatorsWithInvalidAxiom())
		{
			ArrayList<String> line = new ArrayList<String>();
			warnings++;
			line.add(String.format("%02d", warnings)+". Relator is not in accordance with Cardinality Axiom");			
			line.add(getElement(c));
			line.add(getPath(c));
			items.add(line);
		}
		
		// # Warning : Whole with Invalid Axiom
		
		for (RefOntoUML.RigidSortalClass c: ontoparser.getWholesWithInvalidWeakSupplementation())
		{
			ArrayList<String> line = new ArrayList<String>();
			warnings++;
			line.add(String.format("%02d", warnings)+". Whole is not in conformance with Weak Supplementation Axiom");			
			line.add(getElement(c));
			line.add(getPath(c));
			items.add(line);
		}
				
		// # Warning : Elements Without Identity
		
		for (RefOntoUML.Classifier c: ontoparser.getClassesWithIdentityMissing())
		{
			ArrayList<String> line = new ArrayList<String>();
			warnings++;
			line.add(String.format("%02d", warnings)+". Element does not inherit identity principle");			
			line.add(getElement(c));
			line.add(getPath(c));
			items.add(line);
		}
				
		// # Warning : Unnamed Element
		
		for(RefOntoUML.PackageableElement c: ontoparser.getAllInstances(RefOntoUML.PackageableElement.class))
		{			
			if (!(c instanceof RefOntoUML.Generalization) && !(c instanceof RefOntoUML.GeneralizationSet))
			{
				if (c.getName()==null || c.getName().trim().isEmpty()) 
				{ 				
					ArrayList<String> line = new ArrayList<String>();
					warnings++;
					line.add(String.format("%02d", warnings)+". Unnamed element");					
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
				line.add(String.format("%02d", warnings)+". Name conflict");				
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
			e instanceof componentOf || e instanceof memberOf || e instanceof subCollectionOf || e instanceof subQuantityOf || e instanceof Association
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
