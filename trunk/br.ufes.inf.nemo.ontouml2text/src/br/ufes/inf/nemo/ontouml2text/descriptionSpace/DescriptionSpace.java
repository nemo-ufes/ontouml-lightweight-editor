package br.ufes.inf.nemo.ontouml2text.descriptionSpace;

import java.util.ArrayList;
import java.util.List;
import RefOntoUML.Property;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class DescriptionSpace {
	
	private List<DescriptionCategory> categories;
	private List<DescriptionFunction> functions;
	public OntoUMLParser ontoParser;
	
	public DescriptionSpace(){
		this.categories = new ArrayList <DescriptionCategory>();
		this.functions = new ArrayList<DescriptionFunction>();
	}
	
	public List<DescriptionCategory> getCategories() {
		return categories;
	}
	
	public List<DescriptionFunction> getFunctions() {
		return functions;
	}
	
	public void ParserTest(){
		System.out.println(ontoParser.getModelName());
	}
	
	public void SetParser(OntoUMLParser parser){
		this.ontoParser = parser;
	}
	
	
	public DescriptionCategory FindCategory(Type type){
		if(type instanceof RefOntoUML.Category){
			DescriptionCategory mat = new Category(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Collective){
			DescriptionCategory mat = new Collective(type.getName());
			return mat;
		}	
		if(type instanceof RefOntoUML.Kind){
			DescriptionCategory mat = new Kind(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Mixin){
			DescriptionCategory mat = new Mixin(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Mode){
			DescriptionCategory mat = new Mode(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Quantity){
			DescriptionCategory mat = new Quantity(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Relator){
			DescriptionCategory mat = new Relator(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.Role){
			DescriptionCategory mat = new Role(type.getName());
			return mat;
		}
		if(type instanceof RefOntoUML.RoleMixin){
			DescriptionCategory mat = new RoleMixin(type.getName());
			return mat;
		}
		return null;
	}
	
}