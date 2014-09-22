package br.ufes.inf.nemo.antipattern.GSRig;

import java.util.ArrayList;

import RefOntoUML.GeneralizationSet;
import RefOntoUML.Package;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntiPatternIdentifier;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class GSRigAntipattern extends Antipattern<GSRigOccurrence> {

	public GSRigAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
	}
	
	public GSRigAntipattern(Package pack) throws NullPointerException {
		this(new OntoUMLParser(pack));
	}

	private static final String oclQuery = 	
			"GeneralizationSet.allInstances()->select(gs:GeneralizationSet |"+
			"	not gs.parent().oclIsTypeOf(Mixin)"+
			"	and"+
			"	("+
			"		("+
			"		gs.children()->exists(x | x.oclIsKindOf(RigidSortalClass) or x.oclIsKindOf(Category))"+ 
			"		and "+
			"		gs.children()->exists(x | x.oclIsKindOf(AntiRigidSortalClass) or x.oclIsKindOf(RoleMixin))"+
			"		)"+
			"		or"+
			"		("+
			"		gs.children()->exists(x | x.oclIsKindOf(RigidSortalClass) or x.oclIsKindOf(Category))"+ 
			"		and "+
			"		gs.children()->exists(x | x.oclIsKindOf(Mixin))"+
			"		)"+
			"		or"+
			"		("+
			"		gs.children()->exists(x | x.oclIsKindOf(Mixin))"+ 
			"		and "+
			"		gs.children()->exists(x | x.oclIsKindOf(AntiRigidSortalClass) or x.oclIsKindOf(RoleMixin))"+
			"		)"+
			"	)"+
			")";				
			
	private static final AntipatternInfo info = new AntipatternInfo("Generalization Set with Mixed Rigidity", 
			"GSRig", 
			"A generalization set in which the common super-type is a rigid (stereotyped as  «kind», «quantity», «collective», «subkind» or «category») " +
			"and there is at least one rigid subtype and at least one anti-rigid subtype. ",
			oclQuery); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}

	public AntipatternInfo info(){
		return info;
	}

	@Override
	public ArrayList<GSRigOccurrence> identify() {
		ArrayList<GeneralizationSet> query_result;
		
		query_result = AntiPatternIdentifier.runOCLQuery(parser, oclQuery, GeneralizationSet.class);
		
		for (GeneralizationSet gs : query_result) 
		{
			try {
				GSRigOccurrence occurrence = new GSRigOccurrence(gs, this);
				super.occurrence.add(occurrence);
			} catch (Exception e) {
				System.out.println(info.getAcronym()+": Could not create occurrence!");
			}
		}
		
		return this.getOccurrences();
	}	

}
