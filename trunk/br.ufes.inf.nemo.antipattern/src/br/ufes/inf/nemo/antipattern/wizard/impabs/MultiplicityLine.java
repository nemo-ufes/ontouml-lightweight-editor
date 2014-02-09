package br.ufes.inf.nemo.antipattern.wizard.impabs;

import RefOntoUML.Classifier;

public class MultiplicityLine extends ImpAbsLine {

	
	private int lowerSource, upperSource, lowerTarget, upperTarget;
	
	public MultiplicityLine (Classifier source, Classifier target, String multSource, String multTarget) throws Exception {
		super(source,target);
		lowerSource = getLower(multSource);
		upperSource = getUpper(multSource);
		lowerTarget = getLower(multTarget);
		upperTarget = getUpper(multTarget);
		
	}
	
	private int getLower(String multiplicity){
		
		String[] values = multiplicity.split("\\.\\.");
		
		if(values.length>0){
			System.out.println("GET LOWER LENGTH: >0 ");
			
			if(values[0].compareTo("*")==0){
				System.out.println("GET LOWER: * ");
				return 0;
			}
			
			try {
				System.out.println("GET LOWER: "+Integer.parseInt(values[0]));
				return Integer.parseInt(values[0]);
			}
			catch (Exception e){
				System.out.println("GET LOWER: PARSER FAILED - 1");
				return 1;
			}
			
		}
		else {
			System.out.println("GET LOWER LENGTH: 1 - FAILED! DEFAULT=1");
			return 1;
		}	
	}
	
	private int getUpper(String multiplicity){
		
		String[] values = multiplicity.split("\\.\\.");
		
		if(values.length==1){
			System.out.println("GET UPPER LENGTH: ==1 ");
			
			if(values[0].compareTo("*")==0){
				System.out.println("GET UPPER: * ");
				return -1;
			}
			
			try {
				System.out.println("GET UPPER: "+Integer.parseInt(values[0]));
				return Integer.parseInt(values[0]);
			}
			catch (Exception e){
				System.out.println("GET LOWER: PARSER FAILED - 1");
				return 1;
			}
		}
		
		else if(values.length>1){
			System.out.println("GET UPPER LENGTH: >1 ");
			
			if(values[1].compareTo("*")==0){
				System.out.println("GET UPPER: * ");
				return -1;
			}
			
			try {
				System.out.println("GET UPPER: "+Integer.parseInt(values[1]));
				return Integer.parseInt(values[1]);
			}
			catch (Exception e){
				System.out.println("GET UPPER: PARSER FAILED - 1");
				return 1;
			}
		}
		else {
			System.out.println("GET UPPER LENGTH: <1 - FAILED - 1");
			return 1;
		}	
	}
	
	public int getLowerSource() {
		return lowerSource;
	}
	
	public int getUpperSource() {
		return upperSource;
	}
	
	public int getLowerTarget() {
		return lowerTarget;
	}
	
	public int getUpperTarget() {
		return upperTarget;
	}
}
