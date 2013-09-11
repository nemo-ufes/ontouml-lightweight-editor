package br.ufes.inf.nemo.antipattern.rdp;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Generalization;
import RefOntoUML.Mediation;
import RefOntoUML.Phase;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

//Relationally Dependent Phase
public class RDPAntiPattern extends Antipattern{

	Phase phase;
	ArrayList<Mediation> dependencies;
	
	public RDPAntiPattern(Phase phase, ArrayList<Mediation> dependencies) throws Exception {
		this.phase = phase;
		
		for (Mediation m : dependencies) {
			if(!m.getEndType().contains(phase))
				throw new Exception();
		}
		
		this.dependencies = dependencies;
		
	}

	@Override
	public OntoUMLParser setSelected(OntoUMLParser parser) {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(phase);
		selection.addAll(dependencies);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	
	public static ArrayList<RDPAntiPattern> identify(OntoUMLParser parser) {
		
		ArrayList<RDPAntiPattern> result = new ArrayList<RDPAntiPattern>();
		HashMap<Phase,ArrayList<Mediation>> hash = new HashMap<Phase, ArrayList<Mediation>>();
		
		try 
		{
			//collect the phases and their dependencies, if any
			for (Mediation m : parser.getAllInstances(Mediation.class)) 
			{
				
				Type mediated = parser.getMediated(m);
							
				if (mediated instanceof Phase){
					ArrayList<Mediation> mediations = hash.get(mediated);
					
					if(mediations==null){
						mediations = new ArrayList<Mediation>();
						hash.put((Phase) mediated, mediations);
					}
					
					mediations.add(m);
				}
			}
			
			//foreach relationally dependent phase, creates an antipattern with all its dependencies.
			for (Phase phase : hash.keySet()) {
				try { 
					RDPAntiPattern rdp = new RDPAntiPattern(phase, hash.get(phase));
					result.add(rdp);
				} catch (Exception e) {}
				
			}
			
		} catch (Exception e) { }
		
		return result;
		
	}

	@Override
	public String toString(){
		String result = "Phase: "+phase.getName()+"\n"+
						"Dependencies:";
		
		for (Mediation m : dependencies){
			result += "\n\t"+m.getName()+" - "+m.relator().getName();
		}
		
		return result;
		
	}
	
}
