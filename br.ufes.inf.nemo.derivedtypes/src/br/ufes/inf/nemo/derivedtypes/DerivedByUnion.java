package br.ufes.inf.nemo.derivedtypes;
import java.util.ArrayList;
import java.util.HashMap;



public class DerivedByUnion extends DerivedType{

	static HashMap<String, ArrayList<String>> tableValues = new HashMap<>();
	private static DerivedByUnion instance = null;
	
	public DerivedByUnion(){

		ArrayList<String> value;
		value= new ArrayList<>();
		value.add("CATEGORY");
		tableValues.put("Kind"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Kind"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("SubKind"+"Kind", value);
		
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Kind"+"Category", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Category"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("SubKind"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("SUBKIND");
		value.add("CATEGORY");
		tableValues.put("SubKind"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Category"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Category"+"Category", value);

		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Kind"+"Role", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Kind"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Kind"+"Role Mixin", value);
		
		
	}


	public static ArrayList<String> inferStereotype(String stereotype_1, String stereotype_2) {
		// TODO Auto-generated method stub
		System.out.println(tableValues);
		
		return tableValues.get(stereotype_1+stereotype_2);
	}

	public static DerivedByUnion getInstance() {
	      if(instance == null) {
	         instance = new DerivedByUnion();
	      }
	      return instance;
	 }
	
}
