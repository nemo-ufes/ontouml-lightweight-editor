package br.ufes.inf.nemo.ontouml2alloy;

import java.util.ArrayList;

import RefOntoUML.Classifier;
import RefOntoUML.Relator;
import RefOntoUML.RigidSortalClass;
import RefOntoUML.parser.OntoUMLParser;

public class OntoUML2AlloyOptions {
	
	public boolean weakSupplementation = true;	
	public boolean weakSupplementationInvalid = false;
	public String weakSupplementationInvalidMsg = new String();
	
	public boolean relatorConstraint = true;
	public boolean relatorConstraintInvalid = false;
	public String relatorConstraintInvalidMsg = new String();
	
	public boolean identityPrinciple = true;
	public boolean identityPrincipleInvalid = false;
	public String identityPrincipleInvalidMsg = new String();
	
	public boolean antiRigidity = false;		
	public boolean openAnalyzer = true;	
	
	public void check(OntoUMLParser refparser)
	{
		openAnalyzer=true;    	
		ArrayList<Classifier> identityMissing = refparser.getClassesWithIdentityMissing();
		if (!identityMissing.isEmpty()) {
			identityPrinciple = false;
			identityPrincipleInvalid = true;   
			identityPrincipleInvalidMsg = "The following elements does not have an identity principle:\n";
			for(Classifier c: identityMissing) { identityPrincipleInvalidMsg += "- "+refparser.getStringRepresentation(c)+"\n"; }
		}
		ArrayList<Relator> relatorAxiomMissing = refparser.getRelatorsWithInvalidAxiom();
		if (!relatorAxiomMissing.isEmpty()) {
			relatorConstraint = false;
			relatorConstraintInvalid = true;    	
			relatorConstraintInvalidMsg = "The relator constraint axiom does not hold for the following relators:\n";
			for(Relator c: relatorAxiomMissing) { relatorConstraintInvalidMsg += "- "+refparser.getStringRepresentation(c)+"\n"; }
		}
		ArrayList<RigidSortalClass> weakSupplementationMissing = refparser.getWholesWithInvalidWeakSupplementation(); 
		if (!weakSupplementationMissing.isEmpty()) {
			weakSupplementation = false;
			weakSupplementationInvalid = true;
			weakSupplementationInvalidMsg = "The weak supplementation axiom does not hold for the following classes:\n";
			for(RigidSortalClass c: weakSupplementationMissing) { weakSupplementationInvalidMsg += "- "+refparser.getStringRepresentation(c)+"\n"; }
		}
	}
}
