package br.ufes.inf.nemo.antipattern.hetcoll;

import java.util.ArrayList;

import org.eclipse.emf.ecore.EObject;

import RefOntoUML.Association;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.Role;
import RefOntoUML.SubKind;
import RefOntoUML.memberOf;
import RefOntoUML.parser.OntoUMLNameHelper;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.antipattern.AntipatternOccurrence;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;

public class HetCollOccurrence extends AntipatternOccurrence {

	private Classifier whole;
	private ArrayList<Property> memberProperties;
	private ArrayList<Property> collectionProperties;
	
	public HetCollOccurrence(Classifier whole, ArrayList<Property> memberProperties, ArrayList<Property> collectionProperties, HetCollAntipattern ap) throws Exception {
		super(ap);
		
		if(whole==null || memberProperties==null || parser==null)
			throw new NullPointerException("HetColl: null inputs!");
		if(memberProperties.size()<2)
			throw new Exception("HetColl: more than two memberOfs are required!");
		if(!(whole instanceof Collective) && !(whole instanceof SubKind) && !(whole instanceof Role) && !(whole instanceof Phase))
			throw new Exception("HetColl: whole type not acceptable. Required to be Collective, Subkind, Role or Phase");
		
		for (Property p : memberProperties) {
			if (!(p.getAssociation() instanceof memberOf))
				throw new Exception("HetColl: All properties must refer to memberOf relations.");
		}
		
		this.whole = whole;
		this.memberProperties = memberProperties;
		this.collectionProperties = collectionProperties;
	}

	@Override
	public OntoUMLParser setSelected() {
		ArrayList<EObject> selection = new ArrayList<EObject>();
		
		selection.add(this.whole);
		for (Property p : this.memberProperties) {
			selection.add(p.getAssociation());
			selection.add(p.getType());
		}
		
		parser.select(selection,true);
		parser.autoSelectDependencies(OntoUMLParser.SORTAL_ANCESTORS, false);

		return parser;
	}
	
	@Override
	public String toString(){
		String result = "Collection: "+OntoUMLNameHelper.getTypeAndName(whole, true, false)+"\n";
		
		result+="Members: ";
		int direct = 0;
		for (Property p : this.memberProperties){
			if(p.getOpposite().getType().equals(whole)){
				result+="\n\t"+OntoUMLNameHelper.getNameAndType(p);
				direct++;
			}
		}
		
		if(memberProperties.size()>direct){
			result+="\nInherited Members: ";
			for (Property p : this.memberProperties) {
				if(!p.getOpposite().getType().equals(whole))
					result += "\n\t"+OntoUMLNameHelper.getNameAndType(p)+" (from "+OntoUMLNameHelper.getTypeAndName(p.getOpposite().getType(), true, false)+")";
			}
		}
		
		return result;
		
	}

	@Override
	public String getShortName() {
		return "Collection: "+OntoUMLNameHelper.getTypeAndName(whole, true, true);
	}
	
	public Classifier getWhole() {
		return whole;
	}

	public ArrayList<Property> getMemberProperties() {
		return memberProperties;
	}
	
	public ArrayList<Property> getCollectionProperties() {
		return collectionProperties;
	}

	// OUTCOMING FIXES ===============================================
	
	public void changeToComponentOf() {
		ArrayList<Association> assocList = new ArrayList<Association>();
		
		for (Property p : memberProperties)
			assocList.add(p.getAssociation());
		
		for (Property p : collectionProperties)
			assocList.add(p.getAssociation());
	
		fix.addAll(fixer.changeAllRelationsTo(assocList, RelationStereotype.COMPONENTOF, ClassStereotype.KIND, ClassStereotype.KIND));
		
	}

	public void changeToSubCollectionOf(ArrayList<Property> changeToSubCollectionOfList, ArrayList<Property> changeToMemberOfList) {
		ArrayList<Association> subCollectionList = new ArrayList<Association>();
		ArrayList<Association> memberOfList = new ArrayList<Association>();
		
		for (Property p : changeToSubCollectionOfList)
			subCollectionList.add(p.getAssociation());
		
		for (Property p : changeToMemberOfList)
			memberOfList.add(p.getAssociation());
		
		fix.addAll(fixer.changeAllRelationsTo(subCollectionList, RelationStereotype.SUBCOLLECTIONOF, ClassStereotype.COLLECTIVE, ClassStereotype.COLLECTIVE));
		fix.addAll(fixer.changeAllRelationsTo(memberOfList, RelationStereotype.MEMBEROF, ClassStereotype.COLLECTIVE, null));
	}

	public void mergeToMemberOf() {
		ArrayList<Association> assocList = new ArrayList<Association>();
		
		for (Property p : memberProperties)
			assocList.add(p.getAssociation());
		
		fix.addAll(fixer.changeAllToOneSuperMember(assocList));
		
	}


}
