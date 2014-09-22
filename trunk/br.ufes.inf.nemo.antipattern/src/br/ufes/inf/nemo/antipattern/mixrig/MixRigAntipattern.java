package br.ufes.inf.nemo.antipattern.mixrig;

import java.util.ArrayList;

import RefOntoUML.Mixin;
import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class MixRigAntipattern extends Antipattern<MixRigOccurrence> {

	
	public MixRigAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public MixRigAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}
	
	private static final String oclQuery =	
		"Mixin.allInstances()->select( m : Mixin | " +
			"m.children()->size()>0 " +
			"and (" +
				"m.children()->forAll(child : Classifier | child.oclIsTypeOf(Kind) or child.oclIsTypeOf(Collective) or child.oclIsTypeOf(Quantity) or child.oclIsTypeOf(SubKind) or child.oclIsTypeOf(Category)) " +
				"or " +
				"m.children()->forAll(child : Classifier | child.oclIsTypeOf(Role) or child.oclIsTypeOf(Phase) or child.oclIsTypeOf(RoleMixin))))" ;
		//"->collect ( x | Tuple { mixin : Classifier = x, children : Set (Classifier) = x.children()})";
	 
	
	public static final AntipatternInfo info = new AntipatternInfo("Mixin with same Rigidity", 
			"MixRig", 
			"This anti-pattern occurs when a «mixin» is specialized only by type whose rigidity meta-property are the same, i.e., only rigid types («kind», «quantity», «collective», " +
			"«subkind» or «category») or only by anti-rigid types («role», «phase», «roleMixin») ",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	@Override
	public ArrayList<MixRigOccurrence> identify() {
		ArrayList<Mixin> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, Mixin.class);
		
		for (Mixin mixin : query_result) 
		{
			try {
				MixRigOccurrence occurrence = new MixRigOccurrence(mixin, this);
				super.occurrence.add(occurrence);
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
			}
		}
		
		return this.getOccurrences();
	}

}
