package br.ufes.inf.nemo.antipattern.undefphase;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Phase;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class UndefPhaseOccurrence extends AntipatternOccurrence {

	Classifier general;
	GeneralizationSet partition;
	ArrayList<Phase> phases;
	
	
	public UndefPhaseOccurrence(GeneralizationSet gs, OntoUMLParser parser) throws Exception {
		super(parser);
		
		if(gs==null)
			throw new NullPointerException("UndefPhase: Can't create occurrence with null generalization set.");
		
		partition = gs;
		general = null;
		phases = new ArrayList<Phase>();
		
		if(gs.getGeneralization().size()==0)
			throw new Exception("UndefPhase: There is no generalization in the generalization set.");
		
		for (Generalization g : gs.getGeneralization()) {
			
			if(g.getSpecific() instanceof Phase)
				phases.add((Phase) g.getSpecific());
			else
				throw new Exception("UndefPhase: Every subtype in the partition must be an instance of Phase");
			
			if(general==null)
				general=g.getGeneral();
			else if (!general.equals(g.getGeneral()))
				throw new Exception("UndefPhase: Every generalization in the generalization set must refer to the same supertype.");
		}
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(this.partition);
		
		for (Generalization g : this.partition.getGeneralization()){
			selection.add(g);
			selection.add(g.getSpecific());
			selection.add(g.getGeneral());
		}
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
	}
	
	@Override
	public String toString(){
		String result = parser.getStringRepresentation(partition)+
						"\nGeneral: "+parser.getStringRepresentation(this.general)+
						"\nPhases: ";
		for (Generalization g : partition.getGeneralization()) {
			result += "\n\t"+parser.getStringRepresentation(g.getSpecific());
		}
		
	
		return result;
	}

}