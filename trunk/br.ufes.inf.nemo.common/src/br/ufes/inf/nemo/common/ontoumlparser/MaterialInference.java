package br.ufes.inf.nemo.common.ontoumlparser;

import java.util.ArrayList;

import br.ufes.inf.nemo.common.list.Combination;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Derivation;
import RefOntoUML.LiteralInteger;
import RefOntoUML.LiteralUnlimitedNatural;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Package;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Relator;

public class MaterialInference {

	private OntoUMLParser parser;
	private RefOntoUMLFactory factory;
	private ArrayList<MaterialAssociation> materials;
	private ArrayList<Relator> relators;
	private ArrayList<MaterialAssociation> inferredMaterials;
	private ArrayList<Derivation> inferredDerivations;
	
	public MaterialInference(OntoUMLParser parser) {
		this.parser = parser;
		this.factory = RefOntoUMLFactory.eINSTANCE;
		
		this.materials = new ArrayList<>();
		this.materials.addAll(parser.getAllInstances(MaterialAssociation.class));
		
		this.relators = new ArrayList<>();
		this.relators.addAll(parser.getAllInstances(Relator.class));
		
		this.inferredMaterials = new ArrayList<>();
		this.inferredDerivations = new ArrayList<>();
		
	}

	public ArrayList<MaterialAssociation> getInferredMaterials() {
		return inferredMaterials;
	}
	
	public ArrayList<Derivation> getInferredDerivations() {
		return inferredDerivations;
	}
	
	public OntoUMLParser infer() throws Exception{
		ArrayList<Mediation> mediations;
		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, true);
		
		for (Relator relator : relators) {
			
			mediations = new ArrayList<>();;
			parser.getAllMediations(relator, mediations);
			
			Combination comb = new Combination(mediations, 2);
			System.out.println(parser.getStringRepresentation(relator));
			
			while (comb.hasNext()){
				ArrayList<Mediation> result = comb.next();
				Mediation m1 = result.get(0);
				Mediation m2 = result.get(1);
				Classifier c1 = (Classifier) parser.getMediated(m1);
				Classifier c2 = (Classifier) parser.getMediated(m2);
				
				/*prevents the creation of a material association when:
				 * 		- mediations m1 and m2 mediate the same type
				 * 		- mediated by m1 is supertype of mediated by m2
				 * 		- mediated by m1 is subtype of mediated by m2
				 * 		- relator of m1 and m2 are the same, but the relator to derive is an specialization of the relator
				 * 		- the relator is not directly connected to one of the mediations
				 */
				if (!associatedThroughMaterial(c1, c2) && !c1.equals(c2) && !c1.allChildren().contains(c2) && !c1.allParents().contains(c2)
						&& !(parser.getRelator(m1).equals(parser.getRelator(m2)) && !parser.getRelator(m1).equals(relator) ) 
						&& (parser.getRelator(m1).equals(relator) || parser.getRelator(m2).equals(relator))){
					createMaterial(m1, m2, relator);
					System.out.println("\tCreated: "+ parser.getStringRepresentation(parser.getMediated(m1)) + " - " + parser.getStringRepresentation(parser.getMediated(m2)));
				}
			}
			
			System.out.println();
		}
		
		if (inferredMaterials.size()>0)
			return new OntoUMLParser(parser.getModel());
		
		return parser;
	}
	
	private boolean associatedThroughMaterial (Classifier c1, Classifier c2){
		
		for (MaterialAssociation m : this.materials) {
			if (m.getEndType().contains(c1) && m.getEndType().contains(c2))
				return true;
		}
		
		return false;
	}
	
	
	private MaterialAssociation createMaterial (Mediation m1, Mediation m2, Relator relator) throws Exception{
		
		Classifier source = (Classifier) parser.getMediated(m1);
		Classifier target = (Classifier) parser.getMediated(m2);
		
		MaterialAssociation material = OntoUMLUtil.createMaterialAssociation(source, target);
		Derivation derivation = OntoUMLUtil.createDerivation(source, target);
		
		
		material.setName(source.getName().trim()+"_"+target.getName().trim());
		material.setIsDerived(true);
		
		derivation.setName("derivation_"+relator.getName().trim()+"_"+source.getName().trim()+"_"+target.getName().trim());
		derivation.setIsDerived(false);
		
		inferMetaProperties(material, m1, m2, derivation);
		
		//add created associations to the inferred lists
		inferredMaterials.add(material);
		materials.add(material);
		inferredDerivations.add(derivation);
		
		//add created material and derivation to the model (package that contains the relator)
		((Package) relator.eContainer()).getPackagedElement().add(material);
		((Package) relator.eContainer()).getPackagedElement().add(derivation);
		
		return material;
		
	}

	
	
	private void inferMetaProperties (MaterialAssociation m, Mediation m1, Mediation m2, Derivation d) throws Exception {
		
		Property p1MediatedEnd, p1RelatorEnd, p2MediatedEnd, p2RelatorEnd, p1MaterialEnd, p2MaterialEnd;
		int p1Lower, p1Upper, p2Lower, p2Upper;
		
		p1MediatedEnd = parser.getMediatedEnd(m1);
		p1RelatorEnd = parser.getRelatorEnd(m1);
		
		p2MediatedEnd = parser.getMediatedEnd(m2);
		p2RelatorEnd = parser.getRelatorEnd(m2);
		
		if (parser.getMediated(m1).equals(m.getMemberEnd().get(0).getType())) {
			p1MaterialEnd = m.getMemberEnd().get(0);
			p2MaterialEnd = m.getMemberEnd().get(1);
		}
		else if (parser.getMediated(m1).equals(m.getMemberEnd().get(1).getType())){
			p1MaterialEnd = m.getMemberEnd().get(1);
			p2MaterialEnd = m.getMemberEnd().get(0);
		}
		else
			throw new Exception("Invalid Material Association");
		
		Relator m1Relator = parser.getRelator(m1);
		Relator m2Relator = parser.getRelator(m2);
	
		p1Lower = p1RelatorEnd.getLower()*p2MediatedEnd.getLower();
		p2Lower = p2RelatorEnd.getLower()*p1MediatedEnd.getLower();
		
		if (m1Relator.allParents().contains(m2Relator))
			p2Lower = 0;
		else if (m2Relator.allParents().contains(m1Relator))
			p1Lower = 0;
		
		if (p1RelatorEnd.getUpper()==-1 || p2MediatedEnd.getUpper()==-1)
			p1Upper = -1;
		else
			p1Upper = p1RelatorEnd.getUpper()*p2MediatedEnd.getUpper();
		
		if (p2RelatorEnd.getUpper()==-1 || p1MediatedEnd.getUpper()==-1)
			p2Upper = -1;
		else
			p2Upper = p2RelatorEnd.getUpper()*p1MediatedEnd.getUpper();
		
		((LiteralInteger) p1MaterialEnd.getLowerValue()).setValue(p2Lower);
		((LiteralUnlimitedNatural) p1MaterialEnd.getUpperValue()).setValue(p2Upper);
		((LiteralInteger) p2MaterialEnd.getLowerValue()).setValue(p1Lower);
		((LiteralUnlimitedNatural) p2MaterialEnd.getUpperValue()).setValue(p1Upper);
		
		((LiteralInteger) d.getMemberEnd().get(0).getLowerValue()).setValue(1);
		((LiteralUnlimitedNatural) d.getMemberEnd().get(0).getUpperValue()).setValue(1);
		((LiteralInteger) d.getMemberEnd().get(1).getLowerValue()).setValue(1);
		((LiteralUnlimitedNatural) d.getMemberEnd().get(1).getUpperValue()).setValue(1);
		
	}
	
}
