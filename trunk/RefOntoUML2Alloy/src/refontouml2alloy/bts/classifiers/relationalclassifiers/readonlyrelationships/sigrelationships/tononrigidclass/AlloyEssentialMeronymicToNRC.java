package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tononrigidclass;

import RefOntoUML.Meronymic;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloyEssentialMeronymic;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;

public class AlloyEssentialMeronymicToNRC extends AlloyEssentialMeronymic implements AlloySigRelationshipToNRCParent
{

	public AlloyEssentialMeronymicToNRC(Meronymic rel, AlloyAtomProperty t,
            AlloyAtomProperty s)
    {
	    super(rel, t, s);
    }

}
