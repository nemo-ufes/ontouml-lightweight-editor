package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tononrigidclass;

import RefOntoUML.Meronymic;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloyInseparableMeronymic;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;

public class AlloyInseparableMeronymicToNRC extends AlloyInseparableMeronymic
        implements AlloySigRelationshipToNRC
{

	public AlloyInseparableMeronymicToNRC(Meronymic rel, AlloyAtomProperty t,
            AlloyAtomProperty s)
    {
	    super(rel, t, s);
    }

}
