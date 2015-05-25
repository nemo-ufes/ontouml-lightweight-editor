package br.ufes.inf.nemo.oled.derivation;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import RefOntoUML.Classifier;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.oled.AppFrame;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;

public class IntersectionDerivationOperations {

	public boolean verifyExistentIntersection(List<DiagramElement> list2, DiagramManager dm){
		List<DiagramElement> list =  list2;
		ArrayList<Classifier> classes = new ArrayList<Classifier>();
		for (DiagramElement diagramElement : list) {
			classes.add(((ClassElement)diagramElement).getClassifier());
		}
		boolean intersection=false;
		Set<Classifier> c = new HashSet<Classifier>();
		for (DiagramElement element : list) {
			OntoUMLParser refparser = dm.getFrame()
					.getBrowserManager().getProjectBrowser().getParser();
			c= refparser.getAllChildren(((ClassElement)element).getClassifier());
			for (Classifier classifier : c) {
				intersection=true;
				Set<Classifier> c2= refparser.getParents(classifier);
				for (Classifier classifier2 : c2) {
					if(!classes.contains(classifier2)){
						intersection=false;
						break;
					}
				}
			}
			
		}
		
		return intersection;
	}
}
