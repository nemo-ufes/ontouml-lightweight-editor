package br.ufes.inf.nemo.derivedtypes;

import java.util.ArrayList;
import java.util.HashMap;

import javax.crypto.spec.PSource;

public class DerivedByIntersection extends DerivedType {

	public static HashMap<String, ArrayList<String>> values= new HashMap<String, ArrayList<String>>();
	
	
	public DerivedByIntersection(){
		
		ArrayList<String> possibilites= new ArrayList<String>();
		possibilites.add("Role");
		values.put("Role"+"Role", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");	
		values.put("Role"+"Phase", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Role"+"Subkind", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Role"+"Role Mixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Role"+"Category", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Phase"+"Role", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Phase");
		values.put("Phase"+"Phase", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Phase");
		values.put("Phase"+"Subkind", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Phase");
		values.put("Phase"+"RoleMixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Phase");
		values.put("Phase"+"Category", possibilites); 
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Kind"+"RoleMixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Subkind");
		values.put("Kind"+"Category", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		possibilites.add("Phase");
		possibilites.add("Subkind");
		values.put("Kind"+"Mixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Quantity"+"RoleMixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Subkind");
		values.put("Quantity"+"Category", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		possibilites.add("Phase");
		possibilites.add("Subkind");
		values.put("Quantity"+"Mixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Collective"+"RoleMixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Subkind");
		values.put("Collective"+"Category", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		possibilites.add("Phase");
		possibilites.add("Subkind");
		values.put("Collective"+"Mixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Subkind"+"Role", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Phase");
		values.put("Subkind"+"Phase", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Subkind");
		values.put("Subkind"+"Subkind", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Subkind"+"RoleMixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Phase");
		values.put("Subkind"+"Subkind", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		possibilites.add("Phase");
		possibilites.add("Subkind");
		values.put("Subkind"+"Mixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Subkind");
		values.put("Subkind"+"Category", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("RoleMixin"+"Role", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Phase");
		values.put("RoleMixin"+"Phase", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("RoleMixin"+"Subkind", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("RoleMixin");
		values.put("RoleMixin"+"RoleMixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("RoleMixin");
		values.put("RoleMixin"+"Category", possibilites); 
		
		possibilites = new ArrayList<String>();
		possibilites.add("RoleMixin");
		values.put("RoleMixin"+"Mixin", possibilites); 
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Mixin"+"Role", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Phase");
		values.put("Mixin"+"Phase", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Subkind");
		values.put("Mixin"+"Subkind", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role Mixin");
		values.put("Mixin"+"Role Mixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role Mixin");
		possibilites.add("Mixin");
		values.put("Mixin"+"Category", possibilites); 
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role Mixin");
		possibilites.add("Mixin");
		values.put("Mixin"+"Mixin", possibilites); 
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role");
		values.put("Category"+"Role", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Phase");
		values.put("Category"+"Phase", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Subkind");
		values.put("Category"+"Subkind", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role Mixin");
		values.put("Category"+"Role Mixin", possibilites);
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role Mixin");
		possibilites.add("Mixin");
		values.put("Category"+"Category", possibilites); 
		
		possibilites = new ArrayList<String>();
		possibilites.add("Role Mixin");
		possibilites.add("Mixin");
		values.put("Category"+"Mixin", possibilites); 
		
	}
	
	public static DerivedByIntersection instance = new DerivedByIntersection();
	
	@Override
	public ArrayList<String> inferStereotype(String stereotype_1,
			String stereotype_2) {
		return values.get(stereotype_1+stereotype_2);
	}
	
	public static DerivedByIntersection getInstance(){
		return instance;
	}

}
