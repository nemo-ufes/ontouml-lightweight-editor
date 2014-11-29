package br.ufes.inf.nemo.antipattern.undefphase;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Characterization;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.PackageableElement;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Type;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

public class UndefPhaseOccurrence extends AntipatternOccurrence {

	Classifier general;
	GeneralizationSet partition;
	public Classifier getGeneral() {
		return general;
	}

	public GeneralizationSet getPartition() {
		return partition;
	}

	public ArrayList<Phase> getPhases() {
		return phases;
	}

	ArrayList<Phase> phases;
	
	
	public UndefPhaseOccurrence(GeneralizationSet gs, UndefPhaseAntipattern ap) throws Exception {
		super(ap);
		
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
		
		parser.select(selection,true);
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

	@Override
	public String getShortName() {
		return parser.getStringRepresentation(general);
	}

	//=======================================================
	// OUTCOMING FIXES 
	//======================================================
	
	public void createAttributes(ArrayList<String> names, ArrayList<String> type, ArrayList<String> stereotype, ArrayList<String> cardinalities) 
	{
		int i=0;
		for(String attrName: names)
		{
			Fix partial = new Fix();
			if (stereotype.get(i).equals("PrimitiveType")) partial.addAll(fixer.createAttribute(getGeneral(), attrName, ClassStereotype.PRIMITIVETYPE, type.get(i)));
			if (stereotype.get(i).equals("DataType")) partial.addAll(fixer.createAttribute(getGeneral(), attrName, ClassStereotype.DATATYPE, type.get(i)));
			
			Property attr = null;
			for(Object obj: partial.getAdded()){ if (obj instanceof Property) attr = (Property)obj; } 
			fix.addAll(partial);
			
			//change cardinality
			if(attr!=null){
				fix.addAll(fixer.changePropertyMultiplicity(attr, cardinalities.get(i)));
			}
						
			i++;
		}	
	}

	public void createOclDerivationRules(String text) 
	{
		fix.includeRule(text);
	}

	public void createModes(ArrayList<String> names, ArrayList<String> cardinalities, ArrayList<Classifier> phases) 
	{
		int i=0;
		for(String modeName: names)
		{
			//create mode
			PackageableElement newmode = fixer.createClass(ClassStereotype.MODE);
			newmode.setName(modeName);
			fixer.copyContainer(phases.get(i),newmode);
			fix.includeAdded(newmode);
			
			//create characterization from mode to phase
			Fix partial = fixer.createAssociationBetween(RelationStereotype.CHARACTERIZATION, "", (Type)newmode, (Type)phases.get(i));
			Characterization chr=null;
			for(Object obj: partial.getAdded()) { if(obj instanceof Characterization) chr = (Characterization)obj; }
			fix.addAll(partial);
			
			//change cardinality
			if (chr!=null) {
				fix.addAll(fixer.changePropertyMultiplicity(chr.getMemberEnd().get(0), cardinalities.get(i)));
			}
						
			i++;
		}		
		
		// include derivation by exclusion...
		if(!phases.containsAll(getPhases()))
		{
			for(Classifier c: getPhases())
			{
				if(!phases.contains(c))
				{
					String rule = "context _'"+getGeneral().getName()+"'"+"\ninv: ";					
					int j=0;
					for(Classifier c2: getPhases()){
						if(!c2.equals(c)){
							if(j==getPhases().size()-1) rule+="not self.oclIsTypeOf(_'"+c2.getName()+"') ";
							else rule+="not self.oclIsTypeOf(_'"+c2.getName()+"') and ";
						}
						j++;
					}
					rule+="implies self.oclIsTypeOf(_'"+c.getName()+"')\n";
					fix.includeRule(rule);
				}
			}
		}
	}

	public void changeStereotypes(ArrayList<String> stereotype) 
	{	
		int i=0;
		for(String stereo: stereotype){
			if(OutcomeFixer.getStereotype(getPhases().get(i)).compareToIgnoreCase(stereo)!=0){		
				fix.addAll(fixer.changeClassStereotypeTo(getPhases().get(i), fixer.getClassStereotype(stereo)));
			}
			i++;
		}
	}

}