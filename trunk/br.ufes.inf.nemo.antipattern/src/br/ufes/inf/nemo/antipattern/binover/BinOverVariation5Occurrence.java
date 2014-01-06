package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class BinOverVariation5Occurrence extends BinOverOccurrence {

	private Classifier supertype;
	
	public BinOverVariation5Occurrence(Association association, BinOverVariation5Antipattern ap) {
		super(association, ap);
		
		EList<Classifier> parentsSource=source.allParents();
		EList<Classifier> parentsTarget=target.allParents();
		
		this.supertype=null;
		for (Classifier c : parentsSource) {
			if(parentsTarget.contains(c) || target.equals(c)){
				this.supertype=c;
				break;
			}
		}
		
		for (Classifier c : parentsTarget) {
			if(parentsSource.contains(c) || source.equals(c)){
				this.supertype=c;
				break;
			}
		}
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(source);
		selection.add(target);
		selection.add(association);
		
		parser.selectThisElements(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);
		return parser;
	}

	@Override
	public String toString() {
		String result;

		result= "[Var 5]\nSupertype: "+parser.getStringRepresentation(supertype)+
				super.toString();
		return result;
	}
}
