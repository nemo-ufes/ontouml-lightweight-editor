package br.ufes.inf.nemo.ontouml2alloy.scenarios;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.common.file.TimeHelper;
import br.ufes.inf.nemo.ontouml2alloy.scenarios.StoryScenario.Limit;

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
		
		StoryScenario s1 = new StoryScenario();
		s1.setType(StoryType.LINEAR);
		s1.setAsFact();
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s1.getParagraph());
		
		StoryScenario s2 = new StoryScenario();
		s2.setType(StoryType.COUNTER);
		s2.setAsPredicate();
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s2.getParagraph());
		
		StoryScenario s3 = new StoryScenario();
		s3.setType(StoryType.FUTURES);
		s3.setAsAssertion();
		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s3.getParagraph());
		
//		SimpleQuantification sq4 = new SimpleQuantification();
//		sq4.setAsStory();
//		Segment seg = new Segment(parser);
//		seg.setAsPopulation();
//		SegmentSizeScenario s4 = new SegmentSizeScenario(parser, seg, sq4, 7, 9);
//		s4.setMode(MODE.PRED);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s4.getParagraph());
//		
//		SimpleQuantification sq5 = new SimpleQuantification();
//		sq5.setAsExactly(2);
//		seg.setAsObject();
//		SegmentSizeScenario s5 = new SegmentSizeScenario(parser, seg, sq5, 0, 6);
//		s5.setMode(MODE.PRED);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s5.getParagraph());
//		
//		ArrayList<Kind> kinds = new ArrayList<Kind>(parser.getAllInstances(Kind.class));
//		
//		seg.setAsClassifier(kinds.get(0));
//		SegmentVariabilityScenario s6 = new SegmentVariabilityScenario(parser, seg);
//		s6.setMode(MODE.PRED);
//		
//		s6.setAsIncremental();
//		
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s6.getParagraph());
//		
//		seg.setAsStereotype(Mediation.class);
//		SegmentVariabilityScenario s7 = new SegmentVariabilityScenario(parser, seg);
//		s7.setMode(MODE.PRED);
//		
//		s7.setAsConstant();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s7.getParagraph());
//		
//		s7.setAsRandom();
//		seg.setAsProperty();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+s7.getParagraph());
//		
//		Segment seg2 = new Segment(parser);
//		seg2.setAsClassifier(kinds.get(1));
//		seg.setAsClassifier(kinds.get(2));
//		sq5.setAsEvery();
//	
//		ComparisonScenario cs = new ComparisonScenario(parser,seg,seg2,sq5);
//		cs.setCompareExtension(ExtensionOperator.DISJ);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
//		cs.setCompareExtension(ExtensionOperator.INTER);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
//		cs.setCompareExtension(ExtensionOperator.EQUAL);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
//		cs.setCompareExtension(ExtensionOperator.INC);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
//		cs.setCompareSize(new Comparator(Operator.LESSER));
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
//		cs.setCompareSize(new Comparator(Operator.DIF));
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+cs.getParagraph());
		
//		InstantiationScenario is = new InstantiationScenario(parser);
//		ArrayList<Classifier> roles = new ArrayList<Classifier>(parser.getAllInstances(Role.class));
//		ArrayList<Classifier> sublist = new ArrayList<Classifier>(roles.subList(0, 2));
//		
//		is.setAsMultiple(sublist);
//		
//		is.getWorldQuantification().setAsAll();
//		is.getClassifierQuantification().setAsNo();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+is.getParagraph());
//		
//		is.getWorldQuantification().setAsComparison(new Comparator(Operator.EQUAL), 3);
//		is.getClassifierQuantification().setAsSome();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+is.getParagraph());
//		
//		is.getWorldQuantification().setAsSome();
//		is.getClassifierQuantification().setAsComparison(new Comparator(Operator.GREATER), 3);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+is.getParagraph());
//		
//		is.getWorldQuantification().setAsNo();
//		is.getClassifierQuantification().setAsAll();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+is.getParagraph());
//		
//		is.setAsExclusive(sublist);
//		
//		is.getWorldQuantification().setAsAll();
//		is.getClassifierQuantification().setAsNo();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+is.getParagraph());
//		
//		is.getWorldQuantification().setAsComparison(new Comparator(Operator.EQUAL), 3);
//		is.getClassifierQuantification().setAsSome();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+is.getParagraph());
//		
//		is.getWorldQuantification().setAsSome();
//		is.getClassifierQuantification().setAsComparison(new Comparator(Operator.GREATER), 3);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+is.getParagraph());
//		
//		is.getWorldQuantification().setAsNo();
//		is.getClassifierQuantification().setAsAll();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+is.getParagraph());
		
//		RigidityScenario rs = new RigidityScenario(parser);
//		rs.setAsPredicate();
//		rs.setMandatoryAntiRigidity((Class) roles.get(0));
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+rs.getParagraph());
//		rs.setPseudoRigidity((Class) roles.get(1));
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+rs.getParagraph());
		
//		ArrayList<Association> associations = new ArrayList<Association>(parser.getAllInstances(Association.class));
//		
//		AssociationMultiplicityScenario am = new AssociationMultiplicityScenario(parser, associations.get(0), false);
//		am.getWorldQuantification().setAsAll();
//		am.getClassQuantification().setAsAll();
//		am.setData(new Comparator(BinaryOperator.GREATER_EQ),5);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+am.getParagraph());
//		
//		am.getWorldQuantification().setAsSome();
//		am.reverse(true);
//		am.getClassQuantification().setAsComparison(new Comparator(BinaryOperator.GREATER), 3);
//		am.setData(new Comparator(BinaryOperator.EQUAL),2);
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+am.getParagraph());
//	
//		AssociationVariabilityScenario av = new AssociationVariabilityScenario(parser, associations.get(1), true);
//		av.setIsChangeable();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+av.getParagraph());
//		
//		av.setIsConstant();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+av.getParagraph());
//		
//		av.reverse(false);
//		av.setIsDisjoint();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+av.getParagraph());
//		
//		AssociationDepth ad = new AssociationDepth(parser, associations.get(2));
//		ad.setAsLowerBound(3);
//		ad.getWorldQuantification().setAsAll();
//		ad.getClassQuantification().setAsSome();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+ad.getParagraph());
//		
//		ad.setAsUpperBound(3);
//		ad.getWorldQuantification().setAsOne();
//		System.out.println(TimeHelper.getTime()+" - "+fileName+": \n"+ad.getParagraph());
		
	}

}
