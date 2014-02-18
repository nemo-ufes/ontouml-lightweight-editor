package br.ufes.inf.nemo.assistant.util;

import java.util.ArrayList;

public class GeneralizationClass {
	private String general;
	private String generalizationName;
	private boolean isDisjoint;
	private boolean isComplete;
	
	public GeneralizationClass(String general, String genSetName, boolean isDisjoint, boolean isComplete) {
		this.general = general;
		this.generalizationName = genSetName;
		this.isDisjoint = isDisjoint;
		this.isComplete = isComplete;
	}

	public String getGeneral() {
		return general;
	}

	public String getGeneralizationName() {
		return generalizationName;
	}

	public boolean isDisjoint() {
		return isDisjoint;
	}

	public boolean isComplete() {
		return isComplete;
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		GeneralizationClass gs = (GeneralizationClass)obj;
		
		if(gs.getGeneral().equals(this.general) && gs.getGeneralizationName().equals(this.generalizationName)
				&& (gs.isComplete() == this.isComplete) && (gs.isDisjoint() == this.isDisjoint)){
			equals = true;
		}
		
		return equals;
	}
	
	@Override
	public String toString() {
		String s = "general: "+general
				+"\ngeneralizationSet: "+generalizationName
				+"\nisDisjoint: "+isDisjoint
				+"\nisComplete: "+isComplete;
		return s;
	}
	
	public static String[] getAllGeneralsFor(ArrayList<GeneralizationClass> list){
		ArrayList<String> generalsList = new ArrayList<>();
		
		for (GeneralizationClass genClass : list) {
			if(!generalsList.contains(genClass.getGeneral())){
				generalsList.add(genClass.getGeneral());
			}
		}
		
		String[] generalArray = new String[generalsList.size()];
		for (int i = 0; i < generalsList.size(); i++) {
			generalArray[i] = generalsList.get(i);
		}
		
		return generalArray;
	}
	
	public static String[] getAllGeneralizationSetNameFor(ArrayList<GeneralizationClass> list){
		ArrayList<String> genSetList = new ArrayList<>();
		
		for (GeneralizationClass genClass : list) {
			if(!genSetList.contains(genClass.getGeneral())){
				genSetList.add(genClass.getGeneral());
			}
		}
		
		String[] genSetArray = new String[genSetList.size()];
		for (int i = 0; i < genSetList.size(); i++) {
			genSetArray[i] = genSetList.get(i);
		}
		
		return genSetArray;
	}
}
