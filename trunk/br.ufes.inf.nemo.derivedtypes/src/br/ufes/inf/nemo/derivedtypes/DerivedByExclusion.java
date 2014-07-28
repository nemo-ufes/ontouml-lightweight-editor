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
		value.add("Subkind");
		tableValues.put("Kind"+"SubKind", value);

		value= new ArrayList<>();
		value.add("Subkind");
		tableValues.put("SubKind"+"SubKind", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Subkind");
		value.add("Category");
		tableValues.put("Category"+"Kind", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Subkind");
		value.add("Category");
		tableValues.put("Category"+"SubKind", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Subkind");
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
		tableValues.put("Kind"+"Role", value);

		value= new ArrayList<>();
		value.add("Role");
		tableValues.put("SubKind"+"Role", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Kind"+"Phase", value);

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
		tableValues.put("Role"+"Role", value);

		value= new ArrayList<>();
		value.add("Role");
		tableValues.put("Phase"+"Role", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Role"+"Phase", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Phase"+"Phase", value);

		value= new ArrayList<>();
		value.add("Role");
		tableValues.put("Role Mixin"+"Role", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Role Mixin"+"Phase", value);

		value= new ArrayList<>();
		value.add("Phase");
		tableValues.put("Role Mixin"+"Phase", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("RoleMixin");
		tableValues.put("Role Mixin"+"Role Mixin", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Subkind");
		value.add("Mixin");
		tableValues.put("MIXIN"+"ROLE", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Subkind");
		value.add("MIXIN");
		tableValues.put("MIXIN"+"PHASE", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Subkind");
		value.add("Mixin");
		tableValues.put("MIXIN"+"ROLE MIXIN", value);

		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		tableValues.put("CATEGORY"+"MIXIN", value);

		value= new ArrayList<>();
		value.add("Kind");
		value.add("Subkind");
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		value.add("RoleMixin");
		tableValues.put("MIXIN"+"MIXIN", value);


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
