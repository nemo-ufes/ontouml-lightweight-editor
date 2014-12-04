package br.ufes.inf.nemo.derivedtypes;
import java.util.ArrayList;
import java.util.HashMap;


abstract class DerivedType {

	public HashMap<String, ArrayList<String>> tableValues = new HashMap<>();
	static public HashMap<String, ArrayList<String>> tableValuesGen = new HashMap<>();
	
	ArrayList<String> possibleStereotypes= new ArrayList<>()  ;
	
	public abstract ArrayList<String> inferStereotype(String stereotype_1, String stereotype_2);

	public DerivedType(){
		
		ArrayList<String> value;
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValuesGen.put("Kind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Kind");
		value.add("SubKind");
		value.add("Mixin");
		tableValuesGen.put("SubKind", value);
		
		value= new ArrayList<>();
		value.add("Kind");
		value.add("SubKind");
		value.add("Role");
		value.add("Phase");
		value.add("Quantity");
		value.add("Collective");
		value.add("RoleMixin");
		value.add("Mixin");
		tableValuesGen.put("Role", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Kind");
		value.add("SubKind");
		value.add("Role");
		value.add("Phase");
		value.add("Quantity");
		value.add("Collective");
		value.add("RoleMixin");
		value.add("Mixin");
		tableValuesGen.put("Phase", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValuesGen.put("Mixin", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValuesGen.put("Category", value);
		
		value= new ArrayList<>();
		value.add("RoleMixin");
		value.add("Mixin");
		tableValuesGen.put("RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValuesGen.put("Quantity", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValuesGen.put("Collective", value);
	}
	
	static public ArrayList<String> getPossibleGeneralization(String subtype){
		return tableValuesGen.get(subtype);
	}
	
}
