package br.ufes.inf.nemo.antipattern.asscyc;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import RefOntoUML.Relationship;
import RefOntoUML.Type;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontouml2graph.GraphAlgo;
import br.ufes.inf.nemo.common.ontouml2graph.OntoUML2Graph;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class AssCycAntipattern extends Antipattern<AssCycOccurrence> {

	public AssCycAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}

	public AssCycAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
	private static final AntipatternInfo info = new AntipatternInfo("Association Cycle", 
			"AssCyc", 
			"This anti-pattern occurs when...",
			null); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	@Override
	public ArrayList<AssCycOccurrence> identify() {
			
		int aux[][]; 
		int nodei[], nodej[];
		ArrayList<RefOntoUML.Class> classes = new ArrayList<RefOntoUML.Class>();
		ArrayList<RefOntoUML.Class> cycle = new ArrayList<RefOntoUML.Class>();
		ArrayList<Relationship> relationships = new ArrayList<Relationship>();
		ArrayList<Relationship> cycle_ass = new ArrayList<Relationship>();
		
		aux = OntoUML2Graph.buildGraph(parser, classes, relationships, false, false);
		nodei = aux[0];
		nodej = aux[1];
		
		if (relationships.size()<=2) return super.getOccurrences();
		
		int fundcycle[][] = new int [relationships.size()-2][classes.size()];
		GraphAlgo.fundamentalCycles(classes.size()-1, relationships.size()-1, nodei, nodej, fundcycle);
		
		for (int i=1; i<=fundcycle[0][0]; i++) { 
			
			cycle = new ArrayList<RefOntoUML.Class>();
			cycle_ass = new ArrayList<Relationship>();
			
			for (int j=1; j<=fundcycle[i][0]; j++)
				cycle.add(classes.get(fundcycle[i][j]));
			
			for (int j = 0; j < cycle.size(); j++) {
				
				int pos1, pos2;
				if(j<cycle.size()-1) {
					pos1 = j;
					pos2 = j+1;
				}
				else {
					pos1=j;
					pos2=0;
				}
				
				for (Relationship r : relationships) {
					if(r instanceof Association){
						Type source, target;
						if (((Association) r).getMemberEnd().size()==2) {
							source = ((Association)r).getMemberEnd().get(0).getType();
							target = ((Association)r).getMemberEnd().get(1).getType();
							if( (source.equals(cycle.get(pos1)) && target.equals(cycle.get(pos2))) || (source.equals(cycle.get(pos2)) && target.equals(cycle.get(pos1))))
								cycle_ass.add(r);
						}
					}
					if (r instanceof Generalization){
						Classifier general, specific;
						general = ((Generalization)r).getGeneral();
						specific = ((Generalization)r).getSpecific();
						if ( (general.equals(cycle.get(pos1)) && specific.equals(cycle.get(pos2))) || (general.equals(cycle.get(pos2)) && specific.equals(cycle.get(pos1))) )
							cycle_ass.add(r);
					}	
				}
			}
			super.occurrence.add(new AssCycOccurrence(cycle,cycle_ass,this));
		}
		return super.getOccurrences();
	}

}
