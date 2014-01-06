package br.ufes.inf.nemo.antipattern.binover;

import java.util.ArrayList;

import br.ufes.inf.nemo.antipattern.Antipattern;
import br.ufes.inf.nemo.antipattern.AntipatternInfo;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class BinOverAntipattern extends Antipattern<BinOverOccurrence> {

	BinOverVariation1Antipattern var1;
	BinOverVariation2Antipattern var2;
	BinOverVariation3Antipattern var3;
	BinOverVariation4Antipattern var4;
	BinOverVariation5Antipattern var5;
	BinOverVariation6Antipattern var6;
	
	public BinOverAntipattern(OntoUMLParser parser) throws NullPointerException {
		super(parser);
		
		var1 = new BinOverVariation1Antipattern(parser);
		var2 = new BinOverVariation2Antipattern(parser);
		var3 = new BinOverVariation3Antipattern(parser);
		var4 = new BinOverVariation4Antipattern(parser);
		var5 = new BinOverVariation5Antipattern(parser);
		var6 = new BinOverVariation6Antipattern(parser);
		
	}
	
	private static final AntipatternInfo info = new AntipatternInfo("Binary Relation With Overlapping Ends", 
			"BinOver", 
			"This anti-pattern occurs when...",
			null); 
		
	public static AntipatternInfo getAntipatternInfo(){
		return info;
	}
	
	@Override
	public ArrayList<BinOverOccurrence> getOccurrences(){
		ArrayList<BinOverOccurrence> occurrences = new ArrayList<BinOverOccurrence>();
		
		occurrences.addAll(var1.getOccurrences());
		occurrences.addAll(var2.getOccurrences());
		occurrences.addAll(var3.getOccurrences());
		occurrences.addAll(var4.getOccurrences());
		occurrences.addAll(var5.getOccurrences());
		occurrences.addAll(var6.getOccurrences());
		
		return occurrences;
	}

	public ArrayList<BinOverOccurrence> identifyOCL() {
		
		this.occurrence.addAll(var1.identify());
		this.occurrence.addAll(var4.identify());
		this.occurrence.addAll(var5.identify());
		this.occurrence.addAll(var6.identify());
		
		return super.getOccurrences();
	}
	
	/*@Override
	public ArrayList<BinOverOccurrence> identify() {
		
		Set<Association> allAssociations = parser.getAllInstances(Association.class);
		
		int i = 1, total=allAssociations.size();
		
		for (Association a : allAssociations) {
			i++;
			System.out.println("("+i+" of "+total+") " +parser.getStringRepresentation(a)+": Analyzing...");
			
			Classifier source = (Classifier) a.getMemberEnd().get(0).getType();
			Classifier target = (Classifier) a.getMemberEnd().get(1).getType();
			
//			System.out.println("Analyzing association: "+parser.getStringRepresentation(a));
			if(OverlappingTypesIdentificator.isVariation1(source, target)) {
//				System.out.println("V1 occurrence found!");
				try { var1.getOccurrences().add(new BinOverVariation1Occurrence(a, parser));} 
				catch (Exception e) { e.printStackTrace();}
			}
			else if(OverlappingTypesIdentificator.isVariation2(source, target)) {
//				System.out.println("V2 occurrence found!");
				try { var2.getOccurrences().add(new BinOverVariation2Occurrence(a, parser));} 
				catch (Exception e) { e.printStackTrace();}
			}
			else if(OverlappingTypesIdentificator.isVariation3(source, target)) {
//				System.out.println("V3 occurrence found!");
				try { var3.getOccurrences().add(new BinOverVariation3Occurrence(a, parser));} 
				catch (Exception e) { e.printStackTrace();}
			}
			else if(OverlappingTypesIdentificator.isVariation4(source, target)) {
//				System.out.println("V4 occurrence found!");
				try { var4.getOccurrences().add(new BinOverVariation4Occurrence(a, parser));} 
				catch (Exception e) { e.printStackTrace();}
			}
			else if(OverlappingTypesIdentificator.isVariation5(source, target)) {
//				System.out.println("V5 occurrence found!");
				try { var5.getOccurrences().add(new BinOverVariation5Occurrence(a, parser));} 
				catch (Exception e) { e.printStackTrace();}
			}
			else if(OverlappingTypesIdentificator.isVariation6(source, target)) {
//				System.out.println("V6 occurrence found!");
				try { var6.getOccurrences().add(new BinOverVariation6Occurrence(a, parser));} 
				catch (Exception e) { e.printStackTrace();}
			}
			
		}
		
		this.occurrence.addAll(var1.getOccurrences());
		this.occurrence.addAll(var2.getOccurrences());
		this.occurrence.addAll(var3.getOccurrences());
		this.occurrence.addAll(var4.getOccurrences());
		this.occurrence.addAll(var5.getOccurrences());
		this.occurrence.addAll(var6.getOccurrences());
		
		return this.getOccurrences();
	}*/
	
	@Override
	public ArrayList<BinOverOccurrence> identify() {
		var1.identify();
		var2.identify();
		var3.identify();
		var4.identify();
		var5.identify();
		var6.identify();
		
		return this.getOccurrences();
	}
	
}
