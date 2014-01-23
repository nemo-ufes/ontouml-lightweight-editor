package br.ufes.inf.nemo.assistant.manager;


public class ManagerOLED {
	public void createClass(String clsName, String stereotype){
		System.out.println("CREATED CLASS: <"+stereotype+"> "+clsName);
	}

	public void connectGeneralizationSet(String clsGeneral, String strGeneral,String clsSpecific, String strSpecific) {
		System.out.println("CONNECTES GENERALIZATION: General <"+strGeneral+"> "+clsGeneral+" Specific <"+strSpecific+"> "+clsSpecific);
	}

	public void createGeneralizationSet(String stereotype_classe,String generalizationSet, boolean isDisjoint, boolean isComplete) {
		System.out.println("CREATE GENERALIZATION SET: "+stereotype_classe+" | GS: "+generalizationSet+" | isDisjoint: "+isDisjoint+", isComplete: "+isComplete);
	}
}
