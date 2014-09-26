package br.ufes.inf.nemo.ontouml.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import RefOntoUML.Classifier;
import RefOntoUML.util.RefOntoUMLFactoryUtil;
import RefOntoUML.util.RefOntoUMLResourceUtil;

public class Example {

	public static void main(String[] args)
	{
		RefOntoUML.Package model = RefOntoUMLFactoryUtil.createPackage("Accident");
		
		//create kinds
		RefOntoUML.Kind person = RefOntoUMLFactoryUtil.createKind("Person", model);
		RefOntoUML.Kind vehicle = RefOntoUMLFactoryUtil.createKind("Vehicle", model);
		RefOntoUML.Kind roadway = RefOntoUMLFactoryUtil.createKind("Roadway", model);
		
		//create subkinds
		RefOntoUML.SubKind man = RefOntoUMLFactoryUtil.createSubKind("Man", model);
		RefOntoUML.SubKind woman = RefOntoUMLFactoryUtil.createSubKind("Woman", model);
		
		//create phases
		RefOntoUML.Phase living = RefOntoUMLFactoryUtil.createPhase("Living", model);
		RefOntoUML.Phase deceased = RefOntoUMLFactoryUtil.createPhase("Deceased", model);
		
		//create roles
		RefOntoUML.Role victim = RefOntoUMLFactoryUtil.createRole("Victim", model);
		RefOntoUML.Role traveler = RefOntoUMLFactoryUtil.createRole("Traveler", model);
		RefOntoUML.Role crashed = RefOntoUMLFactoryUtil.createRole("CrashedVehicle", model);
				
		//create relators
		RefOntoUML.Relator accident = RefOntoUMLFactoryUtil.createRelator("Traffic Accident", model);
		RefOntoUML.Relator rearEndCollision = RefOntoUMLFactoryUtil.createRelator("Rear End Collision", model);
		RefOntoUML.Relator travel = RefOntoUMLFactoryUtil.createRelator("Travel", model);
		
		//create a partition between man and woman
		List<Classifier> specifics = new ArrayList<Classifier>();
		specifics.add(man);
		specifics.add(woman);
		RefOntoUMLFactoryUtil.createPartition("gender",specifics, person, model);
		
		//create a partition between living and deceased
		specifics.clear();
		specifics.add(living);
		specifics.add(deceased);
		RefOntoUMLFactoryUtil.createPartition("life",specifics, person, model);
				
		// create generalizations/specializations
		RefOntoUMLFactoryUtil.createGeneralization(traveler, person);
		RefOntoUMLFactoryUtil.createGeneralization(victim, traveler);		
		RefOntoUMLFactoryUtil.createGeneralization(crashed, vehicle);
		RefOntoUMLFactoryUtil.createGeneralization(rearEndCollision, accident);
		
		//create primitive types and attributes
		RefOntoUML.PrimitiveType intPrimitive = RefOntoUMLFactoryUtil.createPrimitiveType("int", model);		
		RefOntoUMLFactoryUtil.createAttribute(accident, intPrimitive, 1, 1, "fatalvictims", true);
		
		//create mediations
		RefOntoUMLFactoryUtil.createMediation(accident, 1, -1, "occurs", roadway, 1, 1, model);
		RefOntoUMLFactoryUtil.createMediation(accident, 1, 1, "has", victim, 1, -1, model);
		RefOntoUMLFactoryUtil.createMediation(accident, 1, 1, "involves", crashed, 1, -1, model);		
		RefOntoUMLFactoryUtil.createMediation(travel, 1, 1, "has", traveler, 1, -1, model);
		RefOntoUMLFactoryUtil.createMediation(travel, 1, 1, "made by", vehicle, 1, 1, model);
		
		//create material relationship
		RefOntoUML.MaterialAssociation material = RefOntoUMLFactoryUtil.createMaterialAssociation(victim, 1, -1, "has been victim in", roadway, 1, 1, model);
		
		//create the derivation
		RefOntoUMLFactoryUtil.createDerivation(material, accident, model);
			
		//save model into a file
		File file = new File("src/br/ufes/inf/nemo/ontouml/example/accident.refontouml");
		RefOntoUMLResourceUtil.saveModel(file.getAbsolutePath(), model);
	}
}
