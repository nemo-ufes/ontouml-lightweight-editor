package br.ufes.inf.nemo.antipattern.wizard.homofunc;

import java.text.Normalizer;

import org.eclipse.emf.ecore.EObject;

public class RelationElement
{
	public RefOntoUML.Type type;
	public RefOntoUML.Type getType() {
		return type;
	}

	public String typeStereotype;
	public String typeName;
	public String componentOfName;
	public boolean isShareable;
	public boolean isEssential;
	public boolean isImmutablePart;
	public boolean isImmutableWhole;
	public boolean isInseparable;
	
	public RelationElement(RefOntoUML.Type type, String componentOfName, boolean isShareable, boolean isEssential, boolean isImmutablePart, boolean isImmutableWhole, boolean isInseparable)
	{
		this.type = type;
		this.typeStereotype=getStereotype(type);
		this.typeName=type.getName();
		this.componentOfName=componentOfName;
		this.isEssential=isEssential;
		this.isInseparable=isInseparable;
		this.isImmutablePart=isImmutablePart;
		this.isImmutableWhole=isImmutableWhole;
		this.isShareable=isShareable;
	}
	
	@Override
	public String toString()
	{
		String result = typeStereotype+" "+typeName+" - <componentOf> "+componentOfName+" { ";
		if (isEssential) result += "isEssential ";
		if (isInseparable) result += "isInseparable ";
		if (isImmutableWhole) result += "isImmutableWhole ";
		if (isImmutablePart) result += "isImmutablePart ";
		if (isShareable) result += "isShareable ";
		result+= "}";
		return result;
	}
	
	public static String getStereotype(EObject element)
	{
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
	}
	
}