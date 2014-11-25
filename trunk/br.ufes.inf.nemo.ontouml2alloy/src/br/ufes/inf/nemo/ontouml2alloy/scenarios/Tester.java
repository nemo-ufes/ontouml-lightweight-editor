package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.ArrayList;

import RefOntoUML.Kind;
import RefOntoUML.Mediation;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.file.TimeHelper;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.ComparisonScenario.ExtensionOperator;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.Scenario.MODE;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryScenario.Limit;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryScenario.Type;

public class Tester {

	public Tester() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OntoUMLParser parser;
		String fileName = "model/OntoBio.refontouml";
		System.out.println(TimeHelper.getTime()+" - "+fileName+": Loading parser...");
		
		try {
			parser = new OntoUMLParser(fileName);
			
		}catch(Exception e){
			System.out.println(TimeHelper.getTime()+" - "+fileName+": Parser not loaded!");
			return;
		}
		
		System.out.println(TimeHelper.getTime()+" - "+fileName+": Parser loaded!");
		
		StoryScenario s1 = new StoryScenario(Type.LINEAR, 4, Operator.EQUAL, 0, Limit.UNDEF);
		s1.setMode(MODE.PRED);
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s1.getParagraph());
//		
//		StoryScenario s2 = new StoryScenario(Type.COUNTER, 5, Operator.GREATER, 4, Limit.LOWER);
//		s2.setMode(MODE.FACT);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s2.getParagraph());
//		
//		StoryScenario s3 = new StoryScenario(Type.FUTURES, 3, Operator.LESSER_EQ, 2, Limit.UPPER);
//		s3.setMode(MODE.ASSERT);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s3.getParagraph());
		
		SimpleQuantification sq4 = new SimpleQuantification();
		sq4.setAsStory();
		Segment seg = new Segment(parser);
		seg.setAsPopulation();
		SegmentSizeScenario s4 = new SegmentSizeScenario(parser, seg, sq4, 7, 9);
		s4.setMode(MODE.PRED);
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s4.getParagraph());
		
		SimpleQuantification sq5 = new SimpleQuantification();
		sq5.setAsExactly(2);
		seg.setAsObject();
		SegmentSizeScenario s5 = new SegmentSizeScenario(parser, seg, sq5, 0, 6);
		s5.setMode(MODE.PRED);
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s5.getParagraph());
		
		ArrayList<Kind> kinds = new ArrayList<Kind>(parser.getAllInstances(Kind.class));
		
		seg.setAsClassifier(kinds.get(0));
		SegmentVariabilityScenario s6 = new SegmentVariabilityScenario(parser, seg);
		s6.setMode(MODE.PRED);
		
		s6.setAsIncremental();
		
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s6.getParagraph());
		
		seg.setAsStereotype(Mediation.class);
		SegmentVariabilityScenario s7 = new SegmentVariabilityScenario(parser, seg);
		s7.setMode(MODE.PRED);
		
		s7.setAsConstant();
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s7.getParagraph());
		
		s7.setAsRandom();
		seg.setAsProperty();
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s7.getParagraph());
		
		Segment seg2 = new Segment(parser);
		seg2.setAsClassifier(kinds.get(1));
		seg.setAsClassifier(kinds.get(2));
		sq5.setAsEvery();
	
		ComparisonScenario cs = new ComparisonScenario(parser,seg,seg2,sq5);
		cs.setCompareExtension(ExtensionOperator.DISJ);
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
		cs.setCompareExtension(ExtensionOperator.INTER);
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
		cs.setCompareExtension(ExtensionOperator.EQUAL);
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
		cs.setCompareExtension(ExtensionOperator.INC);
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
		cs.setCompareSize(new Comparator(Operator.LESSER));
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
		cs.setCompareSize(new Comparator(Operator.DIF));
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
		
	}

}
