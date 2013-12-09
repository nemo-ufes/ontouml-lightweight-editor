package br.ufes.inf.nemo.antipattern.mm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil.Copier;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Mediation;
import RefOntoUML.ObjectClass;
import RefOntoUML.Package;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import br.ufes.inf.nemo.antipattern.AntiPatternUtil;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.mrd.MRDAntiPattern;
import br.ufes.inf.nemo.common.ocl.OCLQueryExecuter;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

//Multiple Mediation
public class MMAntiPattern extends Antipattern {


	private Relator supertype;
	private HashMap<Classifier,ArrayList<Mediation>> hashRelators;
	private HashMap<Classifier,ArrayList<Mediation>> hashMediated;
	private ArrayList<Classifier> types;

	public MMAntiPattern(Relator original, OntoUMLParser parser) throws Exception {
		supertype = original;
		hashRelators = new HashMap<Classifier,ArrayList<Mediation>>();
		hashMediated = new HashMap<Classifier,ArrayList<Mediation>>();
		types = new ArrayList<Classifier>();
		
		for (Mediation m : parser.getAllInstances(Mediation.class)){
			
			for (Classifier child : parser.getAllChildren(supertype)) {
				
				if (m.getEndType().contains(child)){
					
					Classifier opposite = getOpposite(m, child);
					
					ArrayList<Mediation> mediationsRelator = hashRelators.get(child);
					ArrayList<Mediation> mediationsMediated = hashMediated.get(opposite);
					
					if (mediationsRelator==null){
						mediationsRelator = new ArrayList<Mediation>();
						hashRelators.put(child, mediationsRelator);
					}
					
					mediationsRelator.add(m);
					
					if (mediationsMediated==null){
						mediationsMediated = new ArrayList<Mediation>();
						hashMediated.put(opposite, mediationsMediated);
					}
					
					mediationsMediated.add(m);
					System.out.println(opposite.getName());
				}
			}
		}
		
		
		System.out.println(parser.getStringRepresentation(supertype));
		for (Classifier r : hashMediated.keySet()) {
			System.out.println(r.getName());
			for (Mediation m : hashMediated.get(r)) {
				System.out.println(m.getName()+" - "+getOpposite(m, r).getName());
			}
		}
		
		for (Classifier mediated : hashMediated.keySet()) {
			if (hashMediated.get(mediated).size()>=2)
				types.add(mediated);
		}
		
		if (types.isEmpty()){
			throw new Exception("Input relator does not characterize the MMAntipattern!");
		}
		
	}

	public Classifier getOpposite (Association a, Classifier c) {
		Classifier source = (Classifier) a.getMemberEnd().get(0).getType();
		Classifier target = (Classifier) a.getMemberEnd().get(1).getType();
		
		if (source.equals(c))
			return target;
		if (target.equals(c))
			return source;
		return null;
	}
	
	@Override
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(supertype);
		selection.addAll(hashMediated.keySet());
		selection.addAll(hashRelators.keySet());
		
		for (ArrayList<Mediation> subRelatorMediations : hashRelators.values()) {
			selection.addAll(subRelatorMediations);
		}
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	private static String oclQuery = 	"Relator.allInstances()->select(r:Relator | "+ 
										"	r.children()->exists(child1,child2 : Classifier | "+ 
										"		child1<>child2" +
										"		and" +
										"		Association.allInstances()->select(a : Association | a.endType->includes(child1)).endType->excluding(child1) "+ 
										"			->intersection(Association.allInstances()->select(a : Association | a.endType->includes(child2)).endType->excluding(child2)) "+
										"			->size()>0 "+
										"	) "+
										")";
	
	public static ArrayList<MMAntiPattern> identify(OntoUMLParser parser) {
	
		ArrayList<MMAntiPattern> result = new ArrayList<MMAntiPattern>();
		
		try {
			Copier copier = new Copier();
			Package model = parser.createPackageFromSelections(copier);
			Collection<Relator> query_result = new ArrayList<>();		
			Object o = OCLQueryExecuter.executeQuery(oclQuery, (EClassifier)model.eClass(), model);
			
			query_result.addAll((Collection<Relator>)o);
			
			for (Relator a : query_result) 
			{
				Relator original = (Relator) AntiPatternUtil.getOriginal(a, copier);
				
				try {
					MMAntiPattern mm = new MMAntiPattern(original, parser);
					result.add(mm);
					
				} catch (Exception e) { 
					System.out.println("Failed to create Multi-Relational Dependency Antipattern\n"+e.getMessage());
				}
			}
		
				
		} catch (Exception e) { 
			System.out.println("Unable to search model for MM Antipatterns.");
		}
		
		return result;
	}

}
