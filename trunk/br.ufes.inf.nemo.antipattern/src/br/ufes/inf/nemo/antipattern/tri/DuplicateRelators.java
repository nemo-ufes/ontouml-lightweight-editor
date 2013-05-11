package br.ufes.inf.nemo.antipattern.tri;

import java.util.ArrayList;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.InstantiationPattern;
import br.ufes.inf.nemo.antipattern.InstantiationPatternParameter;

public class DuplicateRelators extends InstantiationPattern {
	TRIAntiPattern tri;
	
	
	public DuplicateRelators(TRIAntiPattern tri){
		
		this.antipattern = tri;
		this.tri = tri;
		this.worldNumber = 1;
			
	}
	
	public String description() {
				
		return 	"Inside a given snapshot, different instances of the relator '"+tri.getRelator().getName()+"' "+
				"mediate the very same combination of instances of the mediated types.";
	}

	/*
	 * 	context Supervision
		inv duplicateExample : Supervision.allInstances()->forAll(x:Supervision | Supervision.allInstances()->exists(y:Supervision| x<>y and (x.student=y.student) and (x.university=y.university) ))

	*/
	
	@Override
	public String predicate(ArrayList<InstantiationPatternParameter> parameter) throws Exception{

		/*create an OCL rule which generates examples of the instantiation pattern*/ 
		String relatorName = "_'"+tri.getRelator().getName()+"'";
				
		String oclConstraint = "";
		String alloyPredicate = "";
		
		oclConstraint = "context "+relatorName+"\n"+
						"inv duplicate"+tri.getRelator().getName()+" : "+relatorName+".allInstances()->forAll(x:"+relatorName+" | "+relatorName+".allInstances()->exists(y:"+relatorName+" | x<>y";
		
		int i = 0;
		for (InstantiationPatternParameter ipp : parameter) {
			
			if(ipp.getElement() instanceof Property && tri.getProblematicMediatedProperties().contains(ipp.getElement()) ){
				Property property = (Property) ipp.getElement();
				oclConstraint += " and x."+property.getName()+"=y."+property.getName();
				i++;
			}
		}
		
		if (i==0) throw new Exception("TRI: There should be at leat one type!");
		
		oclConstraint += "))";
		
		return oclConstraint;
		
		/*TODO: 
		 * 		(1) Transform the generate OCL constraint to Alloy
		 * 		(2) Include in the Alloy predicate the cardinality constraints of the parameters
		 */
		
	}

	/*create an OCL rule which forbids the instantiation pattern from happening
	 * 
	 * 	context Supervision
	 *	inv duplicateForbiddance : Supervision.allInstances()->forAll(x,y:Supervision | x<>y implies (x.student<>y.student) and (x.university<>y.university))
	 * 
	 * */
	public String forbiddanceConstraint(ArrayList<InstantiationPatternParameter> parameter) throws Exception {
		 
		String relatorName = "_'"+tri.getRelator().getName()+"'";
		String oclConstraint = "";
			
		oclConstraint = "context "+relatorName+"\n"+
						"inv noDuplicate"+tri.getRelator().getName()+" : "+relatorName+".allInstances()->forAll(x,y:"+relatorName+" | x<>y implies";
		int i = 0;
		for (int j = 0; j < parameter.size(); j++) {
			InstantiationPatternParameter ipp = parameter.get(j);
			
			if(ipp.getElement() instanceof Property && tri.getProblematicMediatedProperties().contains(ipp.getElement()) ){
				Property property = (Property) ipp.getElement();
				
				if(i!=0)
					oclConstraint += " or";
				
				oclConstraint += " x."+property.getName()+"<>y."+property.getName();
				i++;
			}
		}
		
		if (i==0) throw new Exception("TRI: There should be at leat one type!");
		
		oclConstraint += ")";
		
		
		return oclConstraint;
	}

}
