package br.inf.ufes.nemo.transformation.ontouml2alloy.v2.base;


public interface AlloyStaticRelationshipParent
{
	AlloyProperty getSource();
	AlloyProperty getTarget();
	String getName();
	
}
