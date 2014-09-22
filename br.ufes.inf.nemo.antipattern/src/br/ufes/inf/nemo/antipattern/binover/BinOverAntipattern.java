package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;

import RefOntoUML.Association;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;

public class BinOverAntipattern extends Antipattern<BinOverOccurrence> {

	public BinOverAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);	
	}
	
	private static final AntipatternInfo info = new AntipatternInfo("Binary Relation With Overlapping Ends", 
			"BinOver", 
			"This anti-pattern occurs when a relation connects two type whose extension always or possibly overlap.",
			null); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	public AntipatternInfo info(){
		return info;
	}

	@Override
	public ArrayList<BinOverOccurrence> identify() {
		
		for (Association binaryAssociation : parser.getAllInstances(Association.class)) {
			try { 
				super.occurrence.add(new BinOverOccurrence(binaryAssociation, this)); 
			} 
			catch (Exception e){}
		}
		
		return this.getOccurrences();
	}
	
}
