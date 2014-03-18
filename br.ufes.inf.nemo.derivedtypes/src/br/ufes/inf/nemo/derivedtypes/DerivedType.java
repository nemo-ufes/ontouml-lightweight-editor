package br.ufes.inf.nemo.derivedtypes;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;


abstract class DerivedType {

	static HashMap<String, ArrayList<String>> tableValues = new HashMap<>();
	private static DerivedByUnion instance = null;
	
	ArrayList<String> possibleStereotypes= new ArrayList<>()  ;
	String Stereotype= "";
	
	
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

	public static String DefineNameDerivedType(){
		
		// a jframe here isn't strictly necessary, but it makes the example a little more real
	    JFrame frame = new JFrame("InputDialog Example #1");

	    // prompt the user to enter their name
	    String name="";
	    while(name==""){
	    name = JOptionPane.showInputDialog(null, "What's the New Type Name", "Name Type", 1);    
	    }
	    return name;
	}
	
	public  static  String selectStereotype(Object[] stereo) {
		// TODO Auto-generated method stub
		JFrame frame = new JFrame("Input Dialog Example 3");
	    String stereotype = (String) JOptionPane.showInputDialog(frame, 
	        "Choose between the possible options ?",
	        "New Type Derived By Union",
	        JOptionPane.QUESTION_MESSAGE, 
	        null, 
	        stereo, 
	        stereo[0]);
	        return stereotype;
	    
	}
	 
}
