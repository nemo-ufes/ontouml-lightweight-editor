package br.ufes.inf.nemo.antipattern.mixrig;

import java.util.ArrayList;

import RefOntoUML.Package;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.antipattern.hetcoll.HetCollAntipattern;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class MixRigAntipattern extends Antipattern {

	
	public MixRigAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public MixRigAntipattern(Package pack) throws NullPointerException {
		super(pack);
	}
	
	private static final String oclQuery =	
		"Mixin.allInstances()->select( m : Mixin | " +
			"m.children()->size()>0 " +
			"and (" +
				"m.children()->forAll(child : Classifier | child.oclIsTypeOf(Kind) or child.oclIsTypeOf(Collective) or child.oclIsTypeOf(Quantity) or child.oclIsTypeOf(SubKind) or child.oclIsTypeOf(Category)) " +
				"or " +
				"m.children()->forAll(child : Classifier | child.oclIsTypeOf(Role) or child.oclIsTypeOf(Phase) or child.oclIsTypeOf(RoleMixin))))" +
		"->collect ( x | Tuple { mixin : Classifier = x, children : Set (Classifier) = x.children()})";
	 
	
	public static final AntipatternInfo info = new AntipatternInfo("Mixin with same Rigidity", 
			"MixRig", 
			"This anti-pattern occurs when a «mixin» is specialized only by type whose rigidity meta-property are the same, i.e., only rigid types («kind», «quantity», «collective», " +
			"«subkind» or «category») or only by anti-rigid types («role», «phase», «roleMixin») ",
			MixRigAntipattern.oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	@Override
	public <T extends Antipattern> ArrayList<T> identify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T extends Antipattern> ArrayList<T> getOccurrences() {
		// TODO Auto-generated method stub
		return null;
	}

}
