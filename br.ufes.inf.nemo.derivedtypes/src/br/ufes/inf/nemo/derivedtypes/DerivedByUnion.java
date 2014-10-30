package br.ufes.inf.nemo.derivedtypes;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



@SuppressWarnings("unused")
public class DerivedByUnion extends DerivedType{

	public static DerivedByUnion instance = new DerivedByUnion();
	
	public DerivedByUnion(){
		
		//TABELA 1
		//linha 1
		ArrayList<String> value;
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Kind"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Collective"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Quantity"+"Quantity", value);

		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Kind"+"Quantity", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Quantity"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Kind"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Collective"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Quantity"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Collective"+"Quantity", value);
		
		
		//linha 2
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Kind"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("SubKind"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Quantity"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("SubKind"+"Quantity", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Collective"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("SubKind"+"Collective", value);
		
		
		//linha 3
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Kind"+"Category", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Category"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Collective"+"Category", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Category"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Quantity"+"Category", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Category"+"Quantity", value);
		
		//linha 4 e 5		
		value= new ArrayList<>();
		value.add("SubKind");
		value.add("Category");
		tableValues.put("SubKind"+"SubKind", value);
		
		//linha 6 
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Category"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("SubKind"+"Category", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Collective"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("SubKind"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Quantity"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("SubKind"+"Quantity", value);
		
		//linha 7 
		value= new ArrayList<>();
		value.add("Category");
		tableValues.put("Category"+"Category", value);

		//TABELA 2
		//linha 1
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Kind"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Role"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Collective"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Role"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Quantity"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Role"+"Quantity", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Kind"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Phase"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Collective"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Phase"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Quantity"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Phase"+"Quantity", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Kind"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("RoleMixin"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Collective"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("RoleMixin"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Quantity"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("RoleMixin"+"Quantity", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("SubKind"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Role"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("SubKind"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Phase"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("SubKind"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("RoleMixin"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Category"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Role"+"Category", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Category"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Phase"+"Category", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Category"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("RoleMixin"+"Category", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValues.put("Mixin"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValues.put("Role"+"Mixin", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValues.put("Mixin"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValues.put("Phase"+"Mixin", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValues.put("Mixin"+"Mixin", value);
		
		value= new ArrayList<>();
		value.add("Category");
		value.add("Mixin");
		tableValues.put("Mixin"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("RoleMixin"+"Mixin", value);
		
		value= new ArrayList<>();
		value.add("Role");
		value.add("SubKind");
		value.add("RoleMixin");
		tableValues.put("Role"+"Role", value);
		
		
		value= new ArrayList<>();
		value.add("RoleMixin");
		tableValues.put("Phase"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Phase");
		value.add("SubKind");
		value.add("RoleMixin");
		tableValues.put("Phase"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("RoleMixin");
		tableValues.put("RoleMixin"+"Role", value);
		
		value= new ArrayList<>();
		value.add("RoleMixin");
		tableValues.put("Role"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("RoleMixin");
		tableValues.put("RoleMixin"+"RoleMixin", value);
		

	}

	@Override
	public ArrayList<String> inferStereotype(String stereotype_1,
			String stereotype_2) {
		// TODO Auto-generated method stub
		return tableValues.get(stereotype_1+stereotype_2);
	}
	
	public static DerivedByUnion  getInstance(){
		return instance;
	}

	
}
