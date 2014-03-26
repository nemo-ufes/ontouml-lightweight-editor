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
		value.add("SUBKIND");
		tableValues.put("Kind"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("SUBKIND");
		tableValues.put("SubKind"+"SubKind", value);

		value= new ArrayList<>();
		value.add("KIND");
		value.add("SUBKIND");
		value.add("CATEGORY");
		tableValues.put("Category"+"Kind", value);

		value= new ArrayList<>();
		value.add("KIND");
		value.add("SUBKIND");
		value.add("CATEGORY");
		tableValues.put("Category"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("KIND");
		value.add("SUBKIND");
		value.add("CATEGORY");
		tableValues.put("Category"+"Category", value);

		value= new ArrayList<>();
		value.add("ROLE");
		value.add("PHASE");
		value.add("MIXIN");
		value.add("ROLE MIXIN");
		tableValues.put("Mixin"+"Kind", value);
		
		value= new ArrayList<>();
		value.add("ROLE");
		value.add("PHASE");
		value.add("MIXIN");
		value.add("ROLE MIXIN");
		tableValues.put("Mixin"+"SubKind", value);
		
		value= new ArrayList<>();
		value.add("ROLE");
		value.add("PHASE");
		value.add("MIXIN");
		value.add("ROLE MIXIN");
		tableValues.put("Mixin"+"Category", value);
		
		//linha 4
		value= new ArrayList<>();
		value.add("ROLE");
		tableValues.put("Kind"+"Role", value);
		
		value= new ArrayList<>();
		value.add("ROLE");
		tableValues.put("SubKind"+"Role", value);
		
		value= new ArrayList<>();
		value.add("ROLE");
		tableValues.put("Kind"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("ROLE");
		tableValues.put("SubKind"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
		tableValues.put("Category"+"Role", value);
		
		value= new ArrayList<>();
		value.add("MIXIN");
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
		value.add("Role Mixin");
		tableValues.put("Role Mixin"+"Role Mixin", value);
		
		value= new ArrayList<>();
		value.add("Kind");
		value.add("SubKind");
		value.add("Mixin");
		tableValues.put("Mixin"+"Role", value);
		
		value= new ArrayList<>();
		value.add("Kind");
		value.add("SubKind");
		value.add("Mixin");
		tableValues.put("Mixin"+"Phase", value);
		
		value= new ArrayList<>();
		value.add("Kind");
		value.add("SubKind");
		value.add("Mixin");
		tableValues.put("Mixin"+"Role Mixin", value);
		
		value= new ArrayList<>();
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		tableValues.put("Category"+"Mixin", value);
		
		value= new ArrayList<>();
		value.add("Kind");
		value.add("SubKind");
		value.add("Role");
		value.add("Phase");
		value.add("Mixin");
		value.add("Role Mixin");
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
	
}
