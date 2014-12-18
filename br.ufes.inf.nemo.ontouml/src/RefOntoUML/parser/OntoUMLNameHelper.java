package RefOntoUML.parser;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Comment;
import RefOntoUML.Constraintx;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Enumeration;
import RefOntoUML.EnumerationLiteral;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Kind;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Model;
import RefOntoUML.NamedElement;
import RefOntoUML.NominalQuality;
import RefOntoUML.NonPerceivableQuality;
import RefOntoUML.Package;
import RefOntoUML.PerceivableQuality;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.Structuration;
import RefOntoUML.SubKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

/** 
 * This class is used to print and give a string representation for a given OntoUML element like Types, Names, Multiplicities and so on.
 * 
 * @author Tiago Sales
 *
 */
public class OntoUMLNameHelper {

	/** Return the name of the type of this element. For instance, if a Kind, then "Kind", if a componentOf, then "ComponentOf", and so on and so forth. */
	public static String getTypeName(EObject elem)
	{
		if(elem==null) return "Null";
		if(elem instanceof Kind) return "Kind";
		if(elem instanceof Quantity) return "Quantity";
		if(elem instanceof Collective) return "Collective";
		if(elem instanceof SubKind) return "SubKind";
		if(elem instanceof Role) return "Role";
		if(elem instanceof Phase) return "Phase";
		if(elem instanceof RoleMixin) return "RoleMixin";
		if(elem instanceof Category) return "Category";
		if(elem instanceof Mixin) return "Mixin";
		if(elem instanceof Relator) return "Relator";
		if(elem instanceof Mode) return "Mode";
		if(elem instanceof PrimitiveType) return "PrimitiveType";
		if(elem instanceof Enumeration) return "Enumeration";
		if(elem instanceof DataType) return "Datatype";
		if(elem instanceof PerceivableQuality) return "PerceivableQuality";
		if(elem instanceof NonPerceivableQuality) return "NonPerceivableQuality";
		if(elem instanceof NominalQuality) return "NominalQuality";
		if(elem instanceof Class) return "Class";
		if(elem instanceof FormalAssociation) return "Formal";
		if(elem instanceof MaterialAssociation) return "Material";
		if(elem instanceof componentOf) return "ComponentOf";
		if(elem instanceof subQuantityOf) return "SubQuantityOf";
		if(elem instanceof memberOf) return "MemberOf";
		if(elem instanceof subCollectionOf) return "SubCollectionOf";
		if(elem instanceof Structuration) return "Structuration";
		if(elem instanceof Mediation) return "Mediation";
		if(elem instanceof Characterization) return "Characterization";
		if(elem instanceof Derivation) return "Derivation";
		if(elem instanceof Association) return "Association";
		if(elem instanceof Generalization) return "Generalization";
		if(elem instanceof GeneralizationSet) return "GeneralizationSet";
		if(elem instanceof Model) return "Model";
		if(elem instanceof Package) return "Package";		
		if(elem instanceof Property)
		{
			if(((Property) elem).getAssociation() instanceof Association) return "AssociationEnd";
			else return "Attribute";
		}		
		if(elem instanceof Comment) return "Comment";	
		if(elem instanceof Constraintx) return "Constraint";		
		if(elem instanceof EnumerationLiteral) return "EnumLiteral";		
		return "UnknownType";
	}
	
	/** 
	 * Return the name of the type of this element with guillemets
	 * For instance, if a kind, then ?Kind?, and so on and so forth.
	 */
	public static String getTypeName(EObject elem, boolean addGuillemets)
	{
		//Changed to unicode because on mac this character appear like ?
		if(addGuillemets) return "\u00AB"+getTypeName(elem)+"\u00BB";		
		return getTypeName(elem);
	}
	
	/** Return the name of this element */
	public static String getName(EObject elem)
	{
		if(elem==null) return "null";		
		if(elem instanceof NamedElement)
		{
			String name = ((NamedElement) elem).getName();			
			if(name == null) return "";			
			return name;
		}		
		return "nameless";
	}
	
	/** 
	 * Return the name of this element with single quote and lowerupper character.
	 * For instance, if a kind named Person then 'Person' or <Person>.
	 */
	public static String getName(EObject elem, boolean addSingleQuote, boolean addLowerUpper)
	{
		if(addSingleQuote) return "'"+getName(elem)+"'";		
		if(addLowerUpper) return "<"+getName(elem)+">";		
		return getName(elem);
	}
	
	/** 
	 * Return the name of the type and the name of the element with single quote and guillemets.
	 * For instance, if a kind named Person, then  ?Kind? 'Person' 
	 */
	public static String getTypeAndName(EObject elem, boolean addGuillemets, boolean addSingleQuotes)
	{		
		String name = "";		
		if(elem instanceof NamedElement) name = " "+getName(elem,addSingleQuotes,false);		
		return getTypeName(elem,addGuillemets)+name;
	}
	
	/** 
	 * Return the name of the element and the name of its type with guillemets and single quotes.
	 * For instance, if a kind named Person, then  'Person' (?Kind?)
	 */
	public static String getNameAndType(EObject elem, boolean addGuillemets, boolean addSingleQuotes)
	{		
		String name = "";		
		if(elem instanceof NamedElement) name = getName(elem,addSingleQuotes,false);		
		return name+" ("+getTypeName(elem,addGuillemets)+")";
	}
	
	/** 
	 * Return a common name used for displaying an OntoUML element which depends on its type.
	 * For instance, generalization are displayed like "Generalization Person" where person is the general type.
	 * A package and a classifier like "Package Accidents" and "?Kind? Person".
	 */
	public static String getCommonName(EObject elem) 
	{		
		if (elem instanceof Package) return getTypeAndName(elem, false, false);		
		if (elem instanceof Generalization)	return getTypeName(elem, false) +" " + getTypeAndName(((Generalization)elem).getGeneral(), false, false);
		if (elem instanceof Classifier) return getTypeAndName(elem, true, false);		
		if (elem instanceof GeneralizationSet)
		{	
			String result = new String();
		    Classifier general = null;
		    GeneralizationSet genset = (RefOntoUML.GeneralizationSet)elem;		    
		    if(genset.getGeneralization()!=null && !genset.getGeneralization().isEmpty())
		    {
		    	general = genset.getGeneralization().get(0).getGeneral();
		    }
		    result += getTypeAndName(elem, false, false) + " / "+getName(general)+" { ";		   	    
		    int i=1;
		    for(Generalization gen: genset.getGeneralization())
		    {
		    	if(i < genset.getGeneralization().size()) 
		    		result += getName(gen.getSpecific())+", ";
		    	else 
		    		result += getName(gen.getSpecific()) + " } ";
		    	i++;
		    }
		    return result;		    
		}		
		if (elem instanceof Property)
		{
			Property p = (Property)elem;
			return getTypeName(p)+" "+getName(p.getType())+" ("+getName(p)+")"+" ["+getMultiplicity(p,true,"..")+"]";			
		}		
		return getTypeAndName(elem, true, false);
	}
	
	/** 
	 * Return a more complete name used for displaying an OntoUML element which depends on its type.
	 * For instance, generalization are displayed like "?Generalization? {Person->Child}" where person is the general type and child the specific type.
	 * A package and a classifier like "?Package? Accidents" and "?Kind? Person".
	 */
	public static String getCompleteName(EObject elem)
	{
		if (elem instanceof Package) return getTypeAndName(elem, true, false);		
		if (elem instanceof Generalization)	
		{
			Generalization g = (Generalization) elem;
			return getTypeName(elem, true) +" {" + getTypeAndName(g.getSpecific(), true, false) + " -> "+getTypeAndName(g.getGeneral(), true, false)+ "}";
		}		
		if (elem instanceof Class || elem instanceof DataType) return getTypeAndName(elem, true, false);
		if (elem instanceof Association){
			Association a = (Association) elem;
			return getTypeAndName(elem,true, false)+" {"+getCommonName(a.getMemberEnd().get(0).getType())+" -> "+getCommonName(a.getMemberEnd().get(1).getType()) + "}";
		}		
		if(elem instanceof GeneralizationSet)
		{
			GeneralizationSet gs = (GeneralizationSet) elem;
			return getCommonName(elem)+" isCovering: "+gs.isIsCovering()+", isDisjoint: "+gs.isIsDisjoint();
		}		
		if(elem instanceof Property)
		{
			Property p = (Property)elem;
			return getTypeName(p,true)+" "+getName(p.eContainer())+"::"+getName(p)+" ("+getName(p.getType())+")"+" ["+getMultiplicity(p,true,"..")+"]";			
		}		
		return getTypeAndName(elem, true, false);
	}
	
	/**
	 * Return the string representation of a property multiplicity. It might always show the upper and lower bounds as
	 * well as choose a proper separator such as "..". 
	 */
	public static String getMultiplicity(Property p, boolean alwaysShowLowerAndUpper, String separator)
	{
		if(p==null) return "null";		
		Integer lower = p.getLower();
		Integer upper = p.getUpper();
		return getMultiplicity(lower, upper, alwaysShowLowerAndUpper, separator);
	}

	/**
	 * Return the string representation of a multiplicity. It might always show the upper and lower bounds as
	 * well as choose a proper separator such as "..". 
	 */
	public static String getMultiplicity(Integer lower, Integer upper, boolean alwaysShowLowerAndUpper, String separator) 
	{
		String lowerString = lower.toString();
		String upperString = upper.toString();		
		if (lower == -1) lowerString = "*";
		if (upper == -1) upperString = "*";		
		if(!alwaysShowLowerAndUpper && lower==upper) return lowerString;		
		return lowerString+separator+upperString;
	}
	
	/** Return the name and the type of a property i.e. association end-point or attribute 
	 *  For instance, 'age' (int) */
	public static String getNameAndType(Property p)
	{
		return getName(p, true, false)+" ("+getName(p.getType())+")";
	}
	
	/** Return the name and the type of a property i.e. association end-point or attribute where you might choose to display stereotype and name of the property type.
	 *  For instance, 'age' (?PrmitiveType? 'int' ) */
	public static String getNameAndType(Property p, boolean addTypeStereotype)
	{		
		if(addTypeStereotype) return getName(p, true, false)+" ("+getTypeAndName(p.getType(), true, false)+")";
		else return getName(p, true, false)+" ("+getName(p.getType())+")";
	}
	
	/** TODO: Should have a description here */
	public static String getNameTypeAndMultiplicity(Property p, boolean quotePropertyName, boolean quoteTypeName, boolean alwaysShowLowerAndUpper, boolean addTypeStereotype, boolean guillemetTypeStereotype)
	{		
		String typeDesc = "";
		if(addTypeStereotype) typeDesc = getTypeAndName(p.getType(), guillemetTypeStereotype, quoteTypeName);
		else typeDesc = getName(p.getType(),quoteTypeName,false);		
		return getName(p, quotePropertyName, false)+" ["+getMultiplicity(p, alwaysShowLowerAndUpper, "..")+"] ("+typeDesc+")";
	}

	/** Return a path of a element i.e. its package hierarchy */
	public static String getPath(EObject c)
	{
		if(c == null) return "";		
		if (c.eContainer()==null) return "";
		else{
			String containerName = "";
			if(c.eContainer() instanceof NamedElement) containerName = ((NamedElement) c.eContainer()).getName();
			else containerName = "unnamed";
			if(c.eContainer().eContainer()==null) return containerName;			
			return getPath(c.eContainer())+"::"+containerName;
		}
	}
	
	/** Return all the details of an association i.e. all its related data */
	public static String getAllDetails(Association a)
	{
		Property source = a.getMemberEnd().get(0);
		Property target = a.getMemberEnd().get(1);		
		return getTypeAndName(a, true, true)+" {"+getName(source.getType(), true, false)+" ("+getMultiplicity(source, true, "..")+") -> ("+getMultiplicity(target, true, "..")+") "+getName(target.getType(), true, false)+" }"; 
	}
}
