package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class BinOverVariation6Occurrence extends BinOverOccurrence {

	private ArrayList<Classifier> commonSubtypes;
	
	public BinOverVariation6Occurrence(Association association, OntoUMLParser parser) throws Exception {
		
		super(association,parser);
		
		this.commonSubtypes = new ArrayList<>();
		
		commonSubtypes.addAll(source.allParents());
		commonSubtypes.retainAll(target.allParents());
		
		if(commonSubtypes.size()==0)
			throw new Exception("BinOver-Var4: There should be at least one common subtype between the related mixin classes.");
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(source);
		selection.add(target);
		selection.add(association);
		selection.addAll(commonSubtypes);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.COMPLETE_HIERARCHY, false);
		return parser;
	}

	@Override
	public String toString() {
		String result;

		result= "[Var 6]\n"+super.toString()+
				"\nCommon Subtypes: ";
		
		for (Classifier subtype : commonSubtypes) {
			System.out.println("\n\t"+parser.getStringRepresentation(subtype));
		}
		
		return result;
	}

}
