package br.ufes.inf.nemo.derivedtypes;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;





@SuppressWarnings("unused")
public class DerivedByExclusion extends DerivedType{

	public static DerivedByExclusion instance = new DerivedByExclusion();

	public DerivedByExclusion(){

		//TABELA 1
		//linha 1
		ArrayList<String> value;
		value= new ArrayList<>();
		value.add("SubKind");
		tableValues.put("Kind"+"SubKind", value);

		value= new ArrayList<>();
		value.add("SubKind");
		tableValues.put("Collective"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("SubKind");
		tableValues.put("Quantity"+"SubKind", value);
		
		
		value= new ArrayList<>();
		value.add("SubKind");
		tableValues.put("SubKind"+"SubKind", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Quantity");
		value.add("Collective");
		value.add("SubKind");
		value.add("Category");
		tableValues.put("Category"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Kind");
		value.add("Quantity");
		value.add("Collective");
		value.add("SubKind");
		value.add("Category");
		tableValues.put("Category"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Kind");
		value.add("Quantity");
		value.add("Collective");
		value.add("SubKind");
		value.add("Category");
		tableValues.put("Category"+"Quantity", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Quantity");
		value.add("Collective");
		value.add("SubKind");
		value.add("Category");
		tableValues.put("Category"+"SubKind", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Quantity");
		value.add("Collective");
		value.add("SubKind");
		value.add("Category");
		tableValues.put("Category"+"Category", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		value.add("RoleMixin");
		tableValues.put("Mixin"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		value.add("RoleMixin");
		tableValues.put("Mixin"+"Collective", value);
		
		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		value.add("RoleMixin");
		tableValues.put("Mixin"+"Quantity", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		value.add("RoleMixin");
		tableValues.put("Mixin"+"SubKind", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		value.add("RoleMixin");
		tableValues.put("Mixin"+"Category", value);

		//linha 4
		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		tableValues.put("Kind"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		tableValues.put("Collective"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		tableValues.put("Quantity"+"Role", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		tableValues.put("SubKind"+"Role", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Kind"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Collective"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Quantity"+"Phase", value);

		value= new ArrayList<>();
		value.add("Role");
		tableValues.put("SubKind"+"Phase", value);

		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Category"+"Role", value);

		value= new ArrayList<>();
		value.add("Mixin");
		tableValues.put("Category"+"Phase", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		tableValues.put("Role"+"Role", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		tableValues.put("Phase"+"Role", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Role"+"Phase", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Phase"+"Phase", value);

		value= new ArrayList<>();
		value.add("Role");
		tableValues.put("RoleMixin"+"Role", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("RoleMixin"+"Phase", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("RoleMixin"+"Phase", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("RoleMixin");
		tableValues.put("RoleMixin"+"RoleMixin", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Quantity");
		value.add("Collective");
		value.add("SubKind");
		value.add("Category");
		value.add("Mixin");
		tableValues.put("Mixin"+"Role", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Quantity");
		value.add("Collective");
		value.add("SubKind");
		value.add("Mixin");
		tableValues.put("Mixin"+"Phase", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Quantity");
		value.add("Collective");
		value.add("SubKind");
		value.add("Category");
		value.add("Mixin");
		tableValues.put("Mixin"+"RoleMixin", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		value.add("RoleMixin");
		tableValues.put("Category"+"Mixin", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Quantity");
		value.add("Collective");
		value.add("SubKind");
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		value.add("RoleMixin");
		tableValues.put("Mixin"+"Mixin", value);


	}

	@Override
	public ArrayList<String> inferStereotype(String stereotype_1,
			String stereotype_2) {
		// TODO Auto-generated method stub
		return tableValues.get(stereotype_1+stereotype_2);
	}

	public static DerivedByExclusion  getInstance(){
		return instance;
	}

	public String createExclusionRule(String pai, String filho, String name){
		String rule = null;
		
		if(!((filho.equals("Role") && (pai.equals("Role") || pai.equals("Phase") || pai.equals("Kind") || pai.equals("SubKind")  || pai.equals("Quantity")  || pai.equals("Collective") )))){
			rule="\ncontext "+pai+"\n"+"inv: not oclIsTypeOf(_'"+filho+"') implies oclIsTypeOf(_'"+name+"')";			
		}
		else{
			return "false";
		}
		return rule;
	}

}
