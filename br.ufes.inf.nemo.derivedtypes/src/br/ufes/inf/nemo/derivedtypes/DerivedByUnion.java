package br.ufes.inf.nemo.derivedtypes;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;



@SuppressWarnings("unused")
public class DerivedByUnion extends DerivedType{

	
	
	public DerivedByUnion(){
		
		//TABELA 1
		//linha 1
		ArrayList<String> value;
		value= new ArrayList<>();
		value.add("CATEGORY");
		tableValues.put("Kind"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		tableValues.put("Collective"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		tableValues.put("Quantity"+"Quantity", value);

		value= new ArrayList<>();
		value.add("CATEGORY");
		tableValues.put("Kind"+"Quantity", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		tableValues.put("Quantity"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		tableValues.put("Kind"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		tableValues.put("Collective"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		tableValues.put("Quantity"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
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
		value.add("SUBKIND");
		value.add("CATEGORY");
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
		value.add("MIXIN");
		tableValues.put("Kind"+"Role", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Role"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Collective"+"Role", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Role"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Quantity"+"Role", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Role"+"Quantity", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Kind"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Phase"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Collective"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Phase"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Quantity"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Phase"+"Quantity", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Kind"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Role Mixin"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Collective"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Role Mixin"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Quantity"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Role Mixin"+"Quantity", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Subkind"+"Role", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Role"+"Subkind", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Subkind"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Phase"+"Subkind", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Subkind"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("RoleMixin"+"Subkind", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Category"+"Role", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Role"+"Category", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Category"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Phase"+"Category", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Category"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("RoleMixin"+"Category", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		value.add("MIXIN");
		tableValues.put("Mixin"+"Role", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		value.add("MIXIN");
		tableValues.put("Role"+"Mixin", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		value.add("MIXIN");
		tableValues.put("Mixin"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		value.add("MIXIN");
		tableValues.put("Phase"+"Mixin", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		value.add("MIXIN");
		tableValues.put("Mixin"+"Mixin", value);
		
		value= new ArrayList<>();
		value.add("CATEGORY");
		value.add("MIXIN");
		tableValues.put("Mixin"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("RoleMixin"+"Mixin", value);
		
		value= new ArrayList<>();
		value.add("SUBKIND");
		value.add("ROLE MIXIN");
		tableValues.put("Role"+"Role", value);
		
		value= new ArrayList<>();
		value.add("ROLEMIXIN");
		tableValues.put("Role"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("ROLEMIXIN");
		tableValues.put("Phase"+"Role", value);
		
		value= new ArrayList<>();
		value.add("SUBKIND");
		value.add("ROLEMIXIN");
		tableValues.put("Phase"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		value.add("ROLEMIXIN");
		tableValues.put("RoleMixin"+"Role", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		value.add("ROLEMIXIN");
		tableValues.put("Role"+"RoleMixin", value);
		
		value= new ArrayList<>();
		value.add("ROLEMIXIN");
		tableValues.put("RoleMixin"+"RoleMixin", value);
		

	}

	
}
