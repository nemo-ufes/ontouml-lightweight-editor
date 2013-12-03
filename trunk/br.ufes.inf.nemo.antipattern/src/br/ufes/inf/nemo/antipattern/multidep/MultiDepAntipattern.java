package br.ufes.inf.nemo.antipattern.multidep;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.ObjectClass;
import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.AntiPatternUtil;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ocl.OCLQueryExecuter;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

//Multiple Relational Dependency
public class MultiDepAntipattern extends AntipatternOccurrence{

	Classifier dependent;
	ArrayList<Mediation> mediations;

	public MultiDepAntipattern (Classifier original, OntoUMLParser parser) throws Exception{
		
		this.dependent = original;
		this.mediations = new ArrayList<Mediation>();
		
		for (Mediation m : parser.getAllInstances(Mediation.class)) {
			if(m.getEndType().contains(dependent))
				mediations.add(m);
		}
		
		//System.out.println("Number of mediations: "+mediations.size());
		
		if (mediations.size()<2){
			throw new Exception("Classifier doesn't depend on multiple types.");
			
		}
	}
	
	@Override
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(dependent);
		selection.addAll(mediations);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
		
	}
	
	private static String oclQuery = "ObjectClass.allInstances()->select(c:ObjectClass | Mediation.allInstances()->select(m:Mediation | m.endType->includes(c))->size()>=2)";

	public static ArrayList<MultiDepAntipattern> identify(OntoUMLParser parser) {
		
		ArrayList<MultiDepAntipattern> result = new ArrayList<MultiDepAntipattern>();
		
		try {
			Copier copier = new Copier();
			Package model = parser.createPackageFromSelections(copier);
			Collection<ObjectClass> query_result = new ArrayList<>();		
			Object o = OCLQueryExecuter.executeQuery(oclQuery, (EClassifier)model.eClass(), model);
			
			query_result.addAll((Collection<ObjectClass>)o);
			
			for (ObjectClass a : query_result) 
			{
				Classifier original = (Classifier) AntiPatternUtil.getOriginal(a, copier);
				
				try {
					MultiDepAntipattern mrd = new MultiDepAntipattern(original, parser);
					result.add(mrd);
					
				} catch (Exception e) { 
					System.out.println("Failed to create Multi-Relational Dependency Antipattern\n\n"+e.getMessage());
				}
			}		
			
		} catch (Exception e) { }
		
		return result;
		
	}
	
	@Override
	public String toString(){
		String result = "Type: "+dependent.getName()+"\n"+
						"Relators: ";
		
		for (Mediation m : mediations){
			result += "\n\t"+m.relator().getName();
		}
		
		return result;
		
	}
}
