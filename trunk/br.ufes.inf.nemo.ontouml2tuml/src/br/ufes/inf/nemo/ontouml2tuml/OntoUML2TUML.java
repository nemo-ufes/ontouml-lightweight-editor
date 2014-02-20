package br.ufes.inf.nemo.ontouml2tuml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.Classifier;
import org.eclipse.uml2.uml.PackageableElement;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UML;
import br.ufes.inf.nemo.ontouml2uml.OntoUML2UMLOption;

public class OntoUML2TUML {

	public static org.eclipse.uml2.uml.UMLFactory ufactory;
	
	public static HashMap<org.eclipse.uml2.uml.Association, String> assocMap = new HashMap<org.eclipse.uml2.uml.Association, String>();
	public static int assoc_counter=0;
	
	public static void main(String args[])
	{
		String path1 = "src/br/ufes/inf/nemo/ontouml2tuml/tests/models/Project.refontouml";
		
		try {
			
			Resource umlResource = OntoUML2UML.convertToUML(new OntoUMLParser(path1), path1.replace(".refontouml", ".uml" ), new OntoUML2UMLOption(true,true));
			HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> map = OntoUML2UML.getMap();
			String log = OntoUML2UML.getLog();
			org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)umlResource.getContents().get(0);		
			ufactory = org.eclipse.uml2.uml.UMLFactory.eINSTANCE;
					
			org.eclipse.uml2.uml.Class umlWorld = createWorldHierarchy(umlRoot);
			createWorldAccessibilityRelation(umlWorld);
			ArrayList<Classifier> topLevels = new ArrayList<Classifier>();
			getTopLevelTypes(umlRoot,topLevels);
			createExistenceRelations(umlWorld,topLevels);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void reifyAssociations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> topLevels)
	{
		for(Classifier top: topLevels)
		{
			if(top instanceof org.eclipse.uml2.uml.Association)
			{
				org.eclipse.uml2.uml.Association assoc = (org.eclipse.uml2.uml.Association)top;
				
				//reify it into a class
				org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)top.getOwner();
				org.eclipse.uml2.uml.Class reifiedAssoc = null;
				if (assoc.getName()==null || assoc.getName().isEmpty()) { assoc.setName("Association"+assoc_counter); }
				reifiedAssoc = umlRoot.createOwnedClass(assoc.getName(), false);				
				assocMap.put(assoc,"assoc"+assoc_counter);
				assoc_counter++; 
				
				//imitate first ternary link i.e., existence.
				boolean end1IsNavigable = true;
				String end1Name = "worlds";
				org.eclipse.uml2.uml.AggregationKind agg1 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
				int end1Lower = 0;
				int end1Upper = -1;				
				boolean end2IsNavigable = true;
				org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
				String end2Name = "exists";
				int end2Lower = 0;
				int end2Upper = -1;				
				org.eclipse.uml2.uml.Association existenceRelation = umlWorld.createAssociation(
					end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, (org.eclipse.uml2.uml.Type)reifiedAssoc,
					end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
				);
				umlRoot.getPackagedElements().add(existenceRelation);
				
				//imitate second ternary link i.e., association's source 
				end1IsNavigable = true;
				end1Name = "assoc"+assoc_counter;
				agg1 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
				end1Lower = 0;
				end1Upper = -1;				
				end2IsNavigable = true;
				agg2 = assoc.getMemberEnds().get(0).getAggregation();
				end2Name = assoc.getMemberEnds().get(0).getName();
				end2Lower = assoc.getMemberEnds().get(0).getLower();
				end2Upper = assoc.getMemberEnds().get(0).getUpper();				
				org.eclipse.uml2.uml.Association sourceRelation = reifiedAssoc.createAssociation(
					end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, assoc.getMemberEnds().get(0).getType(),
					end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
				);
				umlRoot.getPackagedElements().add(sourceRelation);
				
				//imitate third ternary linki.e., association's target
				end1IsNavigable = true;
				end1Name = "assoc"+assoc_counter;
				agg1 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
				end1Lower = 0;
				end1Upper = -1;				
				end2IsNavigable = true;
				agg2 = assoc.getMemberEnds().get(1).getAggregation();
				end2Name = assoc.getMemberEnds().get(1).getName();
				end2Lower = assoc.getMemberEnds().get(1).getLower();
				end2Upper = assoc.getMemberEnds().get(1).getUpper();				
				org.eclipse.uml2.uml.Association targetRelation = reifiedAssoc.createAssociation(
					end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, assoc.getMemberEnds().get(1).getType(),
					end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
				);				
				umlRoot.getPackagedElements().add(targetRelation);
			}
			
			EcoreUtil.delete(top);
		}
	}
	
	public static void createExistenceRelations(org.eclipse.uml2.uml.Class umlWorld, ArrayList<Classifier> topLevels)
	{
		for(Classifier top: topLevels)
		{
			if(top instanceof org.eclipse.uml2.uml.Class || (top instanceof org.eclipse.uml2.uml.DataType && 
			 !(top instanceof org.eclipse.uml2.uml.PrimitiveType) && !(top instanceof org.eclipse.uml2.uml.Enumeration)))
			{
				boolean end1IsNavigable = true;
				String end1Name = "worlds";
				org.eclipse.uml2.uml.AggregationKind agg1 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
				int end1Lower = 0;
				int end1Upper = -1;
				
				boolean end2IsNavigable = true;
				org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
				String end2Name = "exists";
				int end2Lower = 0;
				int end2Upper = -1;
				
				org.eclipse.uml2.uml.Association existenceRelation = umlWorld.createAssociation(
					end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, (org.eclipse.uml2.uml.Type)top,
					end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
				);
				
				org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)umlWorld.getOwner();
				umlRoot.getPackagedElements().add(existenceRelation);
			}
		}
	}
	
	public static void getTopLevelTypes(org.eclipse.uml2.uml.Package umlRoot, ArrayList<Classifier> result)
	{		
		for(PackageableElement pe: umlRoot.getPackagedElements())
		{
			if (pe instanceof org.eclipse.uml2.uml.Type){
				org.eclipse.uml2.uml.Classifier type = (org.eclipse.uml2.uml.Classifier)pe;
				if (type.getGeneralizations().size()==0) result.add(type);
			}
			if (pe instanceof org.eclipse.uml2.uml.Package){
				getTopLevelTypes((org.eclipse.uml2.uml.Package)pe,result);
			}
		}	
	}
			
	public static org.eclipse.uml2.uml.Class createWorldHierarchy(org.eclipse.uml2.uml.Package umlRoot)
	{
		org.eclipse.uml2.uml.Class umlWorld = umlRoot.createOwnedClass("World", false);
		org.eclipse.uml2.uml.Class umlCurrentWorld = umlRoot.createOwnedClass("Current World", false);
		org.eclipse.uml2.uml.Class umlPastWorld = umlRoot.createOwnedClass("Past World", false);
		org.eclipse.uml2.uml.Class umlCounterfactualWorld = umlRoot.createOwnedClass("Counterfactual World", false);
		org.eclipse.uml2.uml.Class umlFutureWorld = umlRoot.createOwnedClass("FutureWorld", false);
		
		org.eclipse.uml2.uml.Generalization genCurrent = ufactory.createGeneralization();
		genCurrent.setGeneral(umlWorld);
		genCurrent.setSpecific(umlCurrentWorld);
		
		org.eclipse.uml2.uml.Generalization genPast = ufactory.createGeneralization();
		genPast.setGeneral(umlWorld);
		genPast.setSpecific(umlPastWorld);
		
		org.eclipse.uml2.uml.Generalization genFuture = ufactory.createGeneralization();
		genFuture.setGeneral(umlWorld);
		genFuture.setSpecific(umlFutureWorld);	
		
		org.eclipse.uml2.uml.Generalization genCounterfactual = ufactory.createGeneralization();
		genCounterfactual.setGeneral(umlWorld);
		genCounterfactual.setSpecific(umlCounterfactualWorld);
		
		org.eclipse.uml2.uml.GeneralizationSet genSet = ufactory.createGeneralizationSet();
		genSet.getGeneralizations().add(genCurrent);
		genSet.getGeneralizations().add(genPast);
		genSet.getGeneralizations().add(genFuture);
		genSet.getGeneralizations().add(genCounterfactual);
		genSet.setIsDisjoint(true);
		genSet.setIsCovering(true);
		umlRoot.getPackagedElements().add(genSet);
		
		return umlWorld;
	}
	
	public static void createWorldAccessibilityRelation (org.eclipse.uml2.uml.Class umlWorld)
	{
		boolean end1IsNavigable = true;
		String end1Name = "previous";
		org.eclipse.uml2.uml.AggregationKind agg1 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		int end1Lower = 0;
		int end1Upper = 1;
		
		boolean end2IsNavigable = true;
		org.eclipse.uml2.uml.AggregationKind agg2 = org.eclipse.uml2.uml.AggregationKind.NONE_LITERAL;
		String end2Name = "next";
		int end2Lower = 0;
		int end2Upper = -1;
		
		org.eclipse.uml2.uml.Association worldAccessibility = umlWorld.createAssociation(
			end1IsNavigable, agg1, end1Name, end1Lower, end1Upper, (org.eclipse.uml2.uml.Type)umlWorld,
			end2IsNavigable, agg2, end2Name, end2Lower, end2Upper
		);
		
		org.eclipse.uml2.uml.Package umlRoot = (org.eclipse.uml2.uml.Package)umlWorld.getOwner();
		umlRoot.getPackagedElements().add(worldAccessibility);
	}
	
	
}
