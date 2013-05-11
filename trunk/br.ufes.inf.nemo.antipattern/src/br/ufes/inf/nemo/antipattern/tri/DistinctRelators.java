package br.ufes.inf.nemo.antipattern.tri;

import java.util.ArrayList;

import RefOntoUML.Property;
import br.ufes.inf.nemo.antipattern.InstantiationPattern;
import br.ufes.inf.nemo.antipattern.InstantiationPatternParameter;

public class DistinctRelators extends InstantiationPattern{
	TRIAntiPattern tri;
	
	public DistinctRelators(TRIAntiPattern tri){
		
		this.antipattern = tri;
		this.tri = tri;
		this.worldNumber = 1;
			
	}
	
	@Override
	public String predicate(ArrayList<InstantiationPatternParameter> parameter) throws Exception {
			 
		String relatorName = "_'"+tri.getRelator().getName()+"'";
		String oclConstraint = "";
		String alloyPredicate = "";
			
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
		
		if (i==0) throw new Exception("TRI: There should be at least one type!");
		
		oclConstraint += ")";
		
		/*TODO: 
		 * 		(1) Transform the generate OCL constraint to Alloy
		 * 		(2) Include in the Alloy predicate the cardinality constraints of the parameters
		 */
		
		return oclConstraint;
	}
	
}
