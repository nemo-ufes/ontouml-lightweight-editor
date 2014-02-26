package br.ufes.inf.nemo.antipattern.wizard.homofunc;

public class PartElement
{
	public String partStereotype;
	public String partName;
	public String componentOfName;
	public boolean isShareable;
	public boolean isEssential;
	public boolean isImmutablePart;
	public boolean isImmutableWhole;
	public boolean isInseparable;
	
	public PartElement(String partStereotype, String partName, String componentOfName, boolean isShareable, boolean isEssential, boolean isImmutablePart, boolean isImmutableWhole, boolean isInseparable)
	{
		this.partStereotype=partStereotype;
		this.partName=partName;
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
		String result = partStereotype+" "+partName+" - <componentOf> "+componentOfName+" { ";
		if (isEssential) result += "isEssential ";
		if (isInseparable) result += "isInseparable ";
		if (isImmutableWhole) result += "isImmutableWhole ";
		if (isImmutablePart) result += "isImmutablePart ";
		if (isShareable) result += "isShareable ";
		result+= "}";
		return result;
	}
}