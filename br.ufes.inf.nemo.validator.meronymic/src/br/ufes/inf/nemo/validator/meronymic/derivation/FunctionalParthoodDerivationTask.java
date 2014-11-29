package br.ufes.inf.nemo.validator.meronymic.derivation;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.componentOf;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.ontouml2directedgraph.EdgePath;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.validator.meronymic.derivation.DerivedMeronymic.PatternType;
import br.ufes.inf.nemo.validator.meronymic.derivation.ui.DerivedTableModel;

public class FunctionalParthoodDerivationTask extends DerivationTask<componentOf>{

	private ArrayList<DerivedMeronymic> derivedDirect;
	private ArrayList<DerivedMeronymic> derivedIndirectType1;
	private ArrayList<DerivedMeronymic> derivedIndirectType2;
	
	public FunctionalParthoodDerivationTask(OntoUMLParser parser, DerivedTableModel tableModel) {
		super(parser, tableModel);

		existing.addAll(parser.getAllInstances(componentOf.class));
		
		derivedDirect = new ArrayList<DerivedMeronymic>();
		derivedIndirectType1 = new ArrayList<DerivedMeronymic>();
		derivedIndirectType2 = new ArrayList<DerivedMeronymic>();
	}
	
	public ArrayList<DerivedMeronymic> getDerivedDirect() {
		return derivedDirect;
	}

	public ArrayList<DerivedMeronymic> getDerivedIndirectType1() {
		return derivedIndirectType1;
	}

	public ArrayList<DerivedMeronymic> getDerivedIndirectType2() {
		return derivedIndirectType2;
	}
	
	public void deriveDirectFunctionalParthoodType1(){

		if(paths==null)
			setPaths();
		
		for (EdgePath path : paths) {
			if(path.isCycle() || path.getEdges().size()==1)
				continue;
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			derivedDirect.add(createDerivedComponentOf(path, whole, part, PatternType.DIRECT_FUNCTIONAL));
		}
		
		derived.addAll(derivedDirect);
	}
	
	public void deriveDirectFunctionalParthoodType2(){

		if(paths==null)
			setPaths();
		
		for (EdgePath path : paths) {
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			for (Classifier partChild : parser.getAllChildren(part)) {
				derivedIndirectType1.add(createDerivedComponentOf(path, whole, partChild, PatternType.INDIRECT_FUNCTIONAL_TYPE1));
			}
		}
		
		derived.addAll(derivedIndirectType1);
	}
	
	public void deriveDirectFunctionalParthoodType3(){

		if(paths==null)
			setPaths();
		
		for (EdgePath path : paths) {
			
			Classifier whole = (Classifier) path.getIdOfNode(0);
			Classifier part = (Classifier) path.getIdOfNode(path.getEdges().size());
			
			for (Classifier wholeChild : parser.getAllChildren(whole)) {
				derivedIndirectType2.add(createDerivedComponentOf(path, wholeChild, part, PatternType.INDIRECT_FUNCTIONAL_TYPE2));
			}
		}
		
		derived.addAll(derivedIndirectType2);
	}

	private DerivedMeronymic createDerivedComponentOf(EdgePath path, Classifier whole,	Classifier part, PatternType pattern) {
		return createDerivedMeronymic(path, whole, part, pattern, getDirect(whole, part, componentOf.class), RelationStereotype.COMPONENTOF);
	}

	@Override
	protected Boolean doInBackground() throws Exception {
		System.out.println("Functional Parts Derivation: creating functional graph...");
		setPaths();
		System.out.println("Functional Parts Derivation: deriving direct functional parts");
		deriveDirectFunctionalParthoodType1();
		System.out.println("Functional Parts Derivation: "+derivedDirect.size()+" direct functional parts derived");
		System.out.println("Functional Parts Derivation: deriving indirect functional parts (type 2)");
		deriveDirectFunctionalParthoodType2();
		System.out.println("Functional Parts Derivation: "+derivedIndirectType1.size()+" indirect functional parts (type 2) derived");
		System.out.println("Functional Parts Derivation: deriving indirect functional parts (type 3)");
		deriveDirectFunctionalParthoodType3();
		System.out.println("Functional Parts Derivation: "+derivedIndirectType2.size()+" indirect functional parts (type 3) derived");
		System.out.println("Functional Parts Derivation: derivation completed!");
		System.out.println("Functional Parts Derivation: a total of "+derived.size()+" relations were derived!");
		setProgress(33);
		return true;
	}
}
