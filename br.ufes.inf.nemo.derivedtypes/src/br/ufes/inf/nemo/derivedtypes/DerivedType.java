package br.ufes.inf.nemo.derivedtypes;
import java.util.ArrayList;
import java.util.HashMap;


abstract class DerivedType {

	public HashMap<String, ArrayList<String>> tableValues = new HashMap<>();
	
	
	ArrayList<String> possibleStereotypes= new ArrayList<>()  ;
	
	
	public abstract ArrayList<String> inferStereotype(String stereotype_1, String stereotype_2);

	


	
	 
}
