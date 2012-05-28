package refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.sigrelationships;
import java.io.FileWriter;
import java.io.IOException;

import refontouml2alloy.bts.classifiers.relationalclassifiers.readonlyrelationships.AlloyReadOnlyRelationship;


public interface AlloySigRelationship extends AlloyReadOnlyRelationship
{
	//In sig relationships, the cardinality constraints are written in the sigrules for one side of the constraint, the other is printed as a fact
	public void writeSigRules(FileWriter out) throws IOException;	
	
	
	public String getSourceSigCardinalityConstraints();
	public String getTargetSigCardinalityConstraints();
	
	
}
