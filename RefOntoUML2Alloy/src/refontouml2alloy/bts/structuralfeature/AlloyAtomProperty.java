package refontouml2alloy.bts.structuralfeature;

import refontouml2alloy.bts.classifiers.atoms.AlloyIsAnAtom;
import RefOntoUML.Property;

public class AlloyAtomProperty extends AlloyProperty
{

	public AlloyAtomProperty(Property p, AlloyIsAnAtom c)
    {
	    super(p, c);
    }
	
	@Override
	public AlloyIsAnAtom getEndType() 
	{
		return (AlloyIsAnAtom) super.getEndType();
	}
}
