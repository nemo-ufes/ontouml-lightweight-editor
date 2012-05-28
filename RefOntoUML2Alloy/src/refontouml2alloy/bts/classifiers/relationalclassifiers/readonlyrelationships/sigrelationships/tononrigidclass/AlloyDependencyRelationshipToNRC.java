package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.tononrigidclass;

import RefOntoUML.DependencyRelationship;
import refontouml2alloy.bts.structuralfeature.AlloyAtomProperty;
import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships.AlloyDependencyRelationship;

public class AlloyDependencyRelationshipToNRC extends
        AlloyDependencyRelationship implements AlloySigRelationshipToNRC
{

	public AlloyDependencyRelationshipToNRC(DependencyRelationship rel,
            AlloyAtomProperty t, AlloyAtomProperty s)
    {
	    super(rel, t, s);
    }



}
