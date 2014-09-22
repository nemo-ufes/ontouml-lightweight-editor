package br.ufes.inf.nemo.common.ontoumlparser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.Comment;
import RefOntoUML.Constraintx;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
import RefOntoUML.Element;
import RefOntoUML.Enumeration;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.NominalQuality;
import RefOntoUML.NonPerceivableQuality;
import RefOntoUML.Package;
import RefOntoUML.PerceivableQuality;
import RefOntoUML.Phase;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;
import RefOntoUML.parser.OntoUMLParser;

public class OntoUMLModelStatistic {
	
	OntoUMLParser parser;
	
	ArrayList<Generalization> generalizations;
	ArrayList<GeneralizationSet> genSets;
	ArrayList<Property> attributes;
	
	private ArrayList<Object> 	classifiers, classes, kinds, quantities, collectives, subkinds, roles, phases, categories, regularDatatypes, packages, comments, constraintx,
								roleMixins, mixins, relators, modes, perceivable, nonperceivable, nominal, datatypes, associations, materials, mediations, derivations,
								enumerations, primitives, characterizations, componentOfs, subCollectionOfs, memberOfs, subQuantityOfs, formals, unknownClasses, unknownAssociations;
	
	private TypeDetail 	kindDetail, collectiveDetail, quantityDetail, subkindDetail, roleDetail, phaseDetail,
						mixinDetail, categoryDetail, perceivableDetail, nonperceivableDetail, nominalDetail, roleMixinDetail, allDatatypesDetail, materialDetail, mediationDetail,
						enumerationsDetail, primitivesDetail, formalDetail, characterizationDetail, componentOfDetail, subQuantityOfDetail, regularDatatypeDetail, generalizationDetail, genSetDetail,
						subCollectionOfDetail, memberOfDetail, relatorDetail, modeDetail, classesDetail, unknownAssociationDetail, unknownClassDetail, attributeDetail, packagesDetail, commentsDetail, constraintxDetail,
						rigidDetail, antiRigidDetail, sortalDetail, nonSortalDetail, momentDetail, nonRigidDetail, associationsDetail, derivationDetail;
	
	private HashSet<TypeDetail> details = new HashSet<TypeDetail>();
	private HashMap<LineType, ArrayList<TypeDetail>> hashMap;
	
	public OntoUMLModelStatistic(OntoUMLParser parser) {
		
		classes = new ArrayList<>();
		kinds = new ArrayList<>();
		quantities = new ArrayList<>();
		collectives = new ArrayList<>();
		subkinds = new ArrayList<>();
		roles = new ArrayList<>();
		phases = new ArrayList<>();
		categories = new ArrayList<>();
		roleMixins = new ArrayList<>();
		mixins = new ArrayList<>();
		relators = new ArrayList<>();
		modes = new ArrayList<>();
		perceivable = new ArrayList<>();
		nonperceivable = new ArrayList<>();
		nominal = new ArrayList<>();
		datatypes = new ArrayList<>();
		regularDatatypes = new ArrayList<>();
		associations = new ArrayList<>();
		materials = new ArrayList<>();
		mediations = new ArrayList<>();
		characterizations = new ArrayList<>();
		enumerations  = new ArrayList<>();
		primitives  = new ArrayList<>();
		componentOfs = new ArrayList<>();
		subCollectionOfs = new ArrayList<>();
		memberOfs = new ArrayList<>();
		subQuantityOfs = new ArrayList<>();
		formals = new ArrayList<>();
		classifiers = new ArrayList<>();
		unknownClasses = new ArrayList<>();
		unknownAssociations = new ArrayList<>();
		derivations = new ArrayList<>();
		
		generalizations = new ArrayList<>();
		attributes = new ArrayList<>();
		genSets = new ArrayList<>();
		packages = new ArrayList<>();
		comments = new ArrayList<>();
		constraintx = new ArrayList<>();
		
		this.parser = parser;		
	}	
	
	public void run () {
			
		for (Element modelElement : parser.getAllInstances(Element.class)) {
			if (modelElement instanceof Classifier){
				classifiers.add(modelElement);
				if (modelElement instanceof RefOntoUML.Class){
					classes.add((Class) modelElement);
				   
					if (modelElement instanceof RefOntoUML.Kind)
					   kinds.add((RefOntoUML.Kind) modelElement);
				   
					else if (modelElement instanceof Quantity)
					   quantities.add((Quantity) modelElement);
				   
					else if (modelElement instanceof Collective)
					   collectives.add((Collective) modelElement);
					
					else if (modelElement instanceof SubKind)
					   subkinds.add((SubKind) modelElement);
				   
					else if (modelElement instanceof Role)
					   roles.add((Role) modelElement);
				   
					else if (modelElement instanceof Phase)
						phases.add((Phase) modelElement);
				   
					else if (modelElement instanceof Category)
						categories.add((Category) modelElement);
				   
					else if (modelElement instanceof RoleMixin)
						roleMixins.add((RoleMixin) modelElement);
				   
					else if (modelElement instanceof Mixin)
						mixins.add((Mixin) modelElement);
				   
					else if (modelElement instanceof Mode)
						modes.add((Mode) modelElement);
					
					else if (modelElement instanceof Relator)
						relators.add((Relator) modelElement);
					
					else if (modelElement instanceof PerceivableQuality)
						perceivable.add((PerceivableQuality) modelElement);
					
					else if (modelElement instanceof NonPerceivableQuality)
						nonperceivable.add((NonPerceivableQuality) modelElement);
					
					else if (modelElement instanceof NominalQuality)
						nominal.add((NominalQuality) modelElement);
					
					else
						unknownClasses.add(modelElement);
				}
			   
				else if (modelElement instanceof Association){
				   
					associations.add((Association) modelElement);
				   
					if (modelElement instanceof MaterialAssociation)
						materials.add((MaterialAssociation) modelElement);
					
					else if (modelElement instanceof Mediation)
						mediations.add((Mediation) modelElement);
				   
					else if (modelElement instanceof FormalAssociation)
						formals.add((FormalAssociation) modelElement);
				   
					else if (modelElement instanceof Characterization)
						characterizations.add((Characterization) modelElement);
				   
					else if (modelElement instanceof componentOf)
						componentOfs.add((componentOf) modelElement);
				   
					else if (modelElement instanceof subCollectionOf)
						subCollectionOfs.add((subCollectionOf) modelElement);
				   
					else if (modelElement instanceof memberOf)
						memberOfs.add((memberOf) modelElement);
				   
					else if (modelElement instanceof subQuantityOf)
						subQuantityOfs.add((subQuantityOf) modelElement);
					
					else if (modelElement instanceof Derivation)
						derivations.add(modelElement);
					
					else
						unknownAssociations.add(modelElement);
					
				}
				
				if (modelElement instanceof DataType){
					datatypes.add((DataType) modelElement);
					
					if (modelElement instanceof PrimitiveType)
						primitives.add((PrimitiveType) modelElement);					
					else if (modelElement instanceof Enumeration)
						enumerations.add((Enumeration) modelElement);
					else
						regularDatatypes.add((DataType) modelElement);
				}
			}
			
			if(modelElement instanceof Package){
				packages.add((Package)modelElement);
			}
			
			if(modelElement instanceof Comment){
				comments.add((Comment)modelElement);
			}
			
			if(modelElement instanceof Constraintx){
				constraintx.add((Package)modelElement);
			}
			
			if(modelElement instanceof Property){
				if(((Property) modelElement).getAssociation()==null)
					attributes.add((Property) modelElement);
			}
			
			if(modelElement instanceof Generalization){
				generalizations.add((Generalization)modelElement);
			}
			
			if(modelElement instanceof GeneralizationSet){
				genSets.add((GeneralizationSet)modelElement);
			}
	   }

		kindDetail = new TypeDetail("Kind", kinds.size(), classes.size());
		quantityDetail = new TypeDetail("Quantity",quantities.size(), classes.size());
		collectiveDetail = new TypeDetail("Collective",collectives.size(), classes.size());
		subkindDetail = new TypeDetail("Subkind",subkinds.size(), classes.size());
		roleDetail = new TypeDetail("Role",roles.size(), classes.size());
		phaseDetail = new TypeDetail("Phase",phases.size(), classes.size());
		categoryDetail = new TypeDetail("Category",categories.size(), classes.size());
		roleMixinDetail = new TypeDetail("RoleMixin",roleMixins.size(), classes.size());
		mixinDetail = new TypeDetail("Mixin",mixins.size(), classes.size());
		relatorDetail = new TypeDetail("Relator",relators.size(), classes.size());
		modeDetail = new TypeDetail("Mode",modes.size(), classes.size());
		perceivableDetail = new TypeDetail("Perceivable Quality",perceivable.size(), classes.size());
		nonperceivableDetail = new TypeDetail("NonPerceivable Quality",nonperceivable.size(), classes.size());
		nominalDetail = new TypeDetail("Nominal Quality",nominal.size(), classes.size());
		unknownClassDetail = new TypeDetail("Unknown Class",unknownClasses.size(), classes.size());
		classesDetail = new TypeDetail("Class",classes.size(), classes.size());
		
		materialDetail = new TypeDetail("Material",materials.size(), associations.size());
		mediationDetail = new TypeDetail("Mediation",mediations.size(), associations.size());
		derivationDetail = new TypeDetail("Derivation",derivations.size(), associations.size());
		characterizationDetail = new TypeDetail("Characterization",characterizations.size(), associations.size());
		componentOfDetail = new TypeDetail("ComponentOf",componentOfs.size(), associations.size());
		memberOfDetail = new TypeDetail("MemberOf",memberOfs.size(), associations.size());
		subCollectionOfDetail = new TypeDetail("SubCollectionOf",subCollectionOfs.size(), associations.size());
		subQuantityOfDetail= new TypeDetail("SubQuantityOf",subQuantityOfs.size(), associations.size());
		formalDetail = new TypeDetail("Formal",formals.size(), associations.size());
		unknownAssociationDetail = new TypeDetail("Unknown Association",unknownAssociations.size(), associations.size());
		associationsDetail = new TypeDetail("Association",associations.size(), associations.size());
		
		enumerationsDetail = new TypeDetail("Enumeration", enumerations.size(), datatypes.size());
		primitivesDetail = new TypeDetail("PrimitiveType", primitives.size(), datatypes.size());
		regularDatatypeDetail = new TypeDetail("DataType", regularDatatypes.size(), datatypes.size());
		allDatatypesDetail = new TypeDetail("All DataType", datatypes.size(), datatypes.size());
		
		rigidDetail = createRigidDetail();
		antiRigidDetail = createAntiRigidDetail();
		nonRigidDetail = createNonRigidDetail();
		
		sortalDetail = createSortalDetail();
		nonSortalDetail = createNonSortalDetail();
		momentDetail = createMomentDetail();
		
		genSetDetail = new TypeDetail("Generalization Set", genSets.size(), genSets.size());
		generalizationDetail = new TypeDetail("Generalization", generalizations.size(), generalizations.size());
		attributeDetail = new TypeDetail("Attribute", attributes.size(), attributes.size());
		packagesDetail = new TypeDetail("Package", packages.size(), packages.size());
		commentsDetail = new TypeDetail("Comment", comments.size(), comments.size());
		constraintxDetail = new TypeDetail("ConstraintX", constraintx.size(), constraintx.size());
		
		buildSequences();
		
	}
	
	public HashSet<TypeDetail> getDetails(){
		return details;
	}
	
	private TypeDetail createRigidDetail(){
		int rigidSize = 0;
		
		rigidSize = kindDetail.getCount()+collectiveDetail.getCount()+quantityDetail.getCount()+subkindDetail.getCount()+
					categoryDetail.getCount()+relatorDetail.getCount()+modeDetail.getCount()+perceivableDetail.getCount()+nonperceivableDetail.getCount()+nominalDetail.getCount();
		
		TypeDetail rigidDetail = new TypeDetail("Rigid Type", rigidSize, classes.size());
		
		return rigidDetail;
	}
	
	private TypeDetail createAntiRigidDetail(){
		int antiRigidSize = 0;
		
		antiRigidSize = roles.size()+phases.size()+roleMixins.size();
		
		TypeDetail antiRigidDetail = new TypeDetail("AntiRigid Type", antiRigidSize, classes.size());
		
		return antiRigidDetail;
	}
	
	private TypeDetail createNonRigidDetail(){
		int nonRigidSize = 0;
		
		nonRigidSize = mixins.size();
		
		TypeDetail nonRigidDetail = new TypeDetail("NonRigid Type", nonRigidSize, classes.size());
		
		return nonRigidDetail;
	}
	
	private TypeDetail createSortalDetail(){
		int sortalSize = 0;
		
		sortalSize = kinds.size()+collectives.size()+quantities.size()+subkinds.size()+roles.size()+phases.size();
		
		TypeDetail sortalDetail = new TypeDetail("Sortal Type", sortalSize, classes.size());
		
		return sortalDetail;
	}
	
	private TypeDetail createNonSortalDetail(){
		int nonSortalSize = 0;
		
		nonSortalSize = categories.size()+roleMixins.size()+mixins.size();
		
		TypeDetail nonSortalDetail = new TypeDetail("Non Sortal Type", nonSortalSize, classes.size());
		
		return nonSortalDetail;
	}
	
	private TypeDetail createMomentDetail(){
		
		int momentSize = relators.size()+modes.size()+perceivable.size()+nonperceivable.size()+nominal.size();
		TypeDetail momentDetail = new TypeDetail("Moment Type", momentSize, classes.size());
		
		return momentDetail;
	}
	
	public String allResults (){
		return 	classResults() + "\n\n" + 
				associationResults() + "\n\n" + 
				datatypeResults() + "\n\n" +
				aggregatedResults();
	}
	
	public String classResults() {
		return	"#Class: "+classes.size()+
				"\n\t"+kindDetail+
				"\n\t"+collectiveDetail+
				"\n\t"+quantityDetail+
				"\n\t"+subkindDetail+
				"\n\t"+roleDetail+
				"\n\t"+phaseDetail+
				"\n\t"+categoryDetail+
				"\n\t"+roleMixinDetail+
				"\n\t"+mixinDetail+
				"\n\t"+relatorDetail+
				"\n\t"+modeDetail+
				"\n\t"+perceivableDetail+
				"\n\t"+nonperceivableDetail+
				"\n\t"+nominalDetail;		
	}
	
	public String associationResults() {
		return 	"#Association: "+associations.size()+
				"\n\t"+materialDetail+
				"\n\t"+mediationDetail+
				"\n\t"+formalDetail+
				"\n\t"+characterizationDetail+
				"\n\t"+componentOfDetail+
				"\n\t"+subCollectionOfDetail+
				"\n\t"+memberOfDetail+
				"\n\t"+subQuantityOfDetail;
	}
	
	public String aggregatedResults(){
		return 	sortalDetail + "\n" +
				nonSortalDetail + "\n" +
				momentDetail + "\n" +
				rigidDetail + "\n" +
				antiRigidDetail + "\n"+
				nonRigidDetail + "\n";
	}
	
	public String datatypeResults(){
		return allDatatypesDetail + "\n" +
			   enumerationsDetail + "\n" +
			   primitivesDetail + "\n";
	}
	
	
	public enum LineType {BASIC, CLASS, ASSOCIATION, DATATYPE, RIGIDITY, NATURE};
	
	private void buildSequences(){
		hashMap = new HashMap<LineType, ArrayList<TypeDetail>>();
		
		ArrayList<TypeDetail> list = new ArrayList<TypeDetail>();
		list.add(classesDetail); 
		list.add(allDatatypesDetail);
		list.add(associationsDetail);
		list.add(generalizationDetail);
		list.add(genSetDetail);
		list.add(attributeDetail);
		list.add(packagesDetail);
		list.add(commentsDetail);
		list.add(constraintxDetail);
		hashMap.put(LineType.BASIC, list);
		
		list = new ArrayList<TypeDetail>();
		list.add(rigidDetail);
		list.add(nonRigidDetail);
		list.add(antiRigidDetail);
		list.add(unknownClassDetail);
		hashMap.put(LineType.RIGIDITY, list);
		
		list = new ArrayList<TypeDetail>();
		list.add(sortalDetail);
		list.add(nonSortalDetail);
		list.add(momentDetail);
		list.add(unknownClassDetail);
		hashMap.put(LineType.NATURE, list);
		
		list = new ArrayList<TypeDetail>();
		list.add(enumerationsDetail);
		list.add(primitivesDetail);
		list.add(regularDatatypeDetail);
		hashMap.put(LineType.DATATYPE, list);
		
		list = new ArrayList<TypeDetail>();
		list.add(kindDetail);
		list.add(quantityDetail);
		list.add(collectiveDetail);
		list.add(subkindDetail);
		list.add(roleDetail);
		list.add(phaseDetail);
		list.add(categoryDetail);
		list.add(roleMixinDetail);
		list.add(mixinDetail);
		list.add(relatorDetail);
		list.add(modeDetail);
		list.add(perceivableDetail);
		list.add(nonperceivableDetail);
		list.add(nominalDetail);
		list.add(unknownClassDetail);
		hashMap.put(LineType.CLASS, list);
		
		list = new ArrayList<TypeDetail>();
		list.add(materialDetail);
		list.add(mediationDetail);
		list.add(derivationDetail);
		list.add(characterizationDetail);
		list.add(componentOfDetail);
		list.add(memberOfDetail);
		list.add(subCollectionOfDetail);
		list.add(subQuantityOfDetail);
		list.add(formalDetail);
		list.add(unknownAssociationDetail);
		hashMap.put(LineType.ASSOCIATION, list);
		
		for (ArrayList<TypeDetail> detailList : hashMap.values()) {
			details.addAll(detailList);
		}
	}
	
	
	public enum InfoType {MEASURE, COUNT, TYPE_PERCENTAGE, ALL_PERCENTAGE};
	
	public String getCSVLine(LineType lt, InfoType it, String separator, boolean addModelName){
		ArrayList<TypeDetail> detailList = hashMap.get(lt);
		String s = "";
		int i = 0;
		
		if(detailList==null)
			return "";
		
		if(addModelName){
			if(it==InfoType.MEASURE)	
				s += "Model"+separator+" ";
			else
				s += parser.getModelName()+separator+" ";
		}
		
		for (TypeDetail detail : detailList) {
			
			if(it==InfoType.MEASURE)
				s+=detail.getMeasure();
			else if(it==InfoType.COUNT)
				s+=detail.getCount();
			else if(it==InfoType.TYPE_PERCENTAGE)
				s+=detail.getTypePercentage();
			else if(it==InfoType.ALL_PERCENTAGE)
				s+=detail.getAllPercentage();
			
			if(i<detailList.size()-1)
				s+=separator+" ";
			i++;
		}
		
		return s;
	}
	
	public String getCSVHeader(LineType lt, String separator, boolean addModelName){
		return getCSVLine(lt, InfoType.MEASURE, separator, addModelName);
	}
	
	public String getCSVCountLine(LineType lt, String separator, boolean addModelName){
		return getCSVLine(lt, InfoType.COUNT, separator, addModelName);
	}
	
	public String getCSVTypePercentageLine(LineType lt, String separator, boolean addModelName){
		return getCSVLine(lt, InfoType.TYPE_PERCENTAGE, separator, addModelName);
	}
	
	public String getCSVAllPercentageLine(LineType lt, String separator, boolean addModelName){
		return getCSVLine(lt, InfoType.ALL_PERCENTAGE, separator, addModelName);
	}
	
	public class TypeDetail{
		String typeName;
		int count;
		float percentageSupertype;		
		float percentageAllElements;
		
		private TypeDetail(String typeName, int typesize, int supertype) {
			this.typeName = typeName; 
			count = typesize;
			int all = classes.size()+associations.size()+generalizations.size()+genSets.size()+datatypes.size()+
						attributes.size()+comments.size()+constraintx.size()+packages.size();
			
			if(typesize==0) {
				percentageSupertype = 0;
				percentageAllElements = 0;					
			}else{
				if(supertype==0) {
					percentageSupertype=0;
					percentageAllElements = ((float)typesize/all)*100;
				}
				else if(all==0) {
					percentageSupertype = ((float) typesize/supertype)*100;
					percentageAllElements = 0;
				} else{
					percentageSupertype = ((float) typesize/supertype)*100;
					percentageAllElements = ((float)typesize/all)*100;
				}					
			}			
		}
		
		public String getMeasure() {
			return typeName;
		}
		
		public int getCount(){ 
			return count; 
		}
		
		public String getTypePercentage(){ 
			return String.format("%.2f", percentageSupertype).replace(",", ".")+"%"; 
		}
		
		public float getTypePercentageAsFloat(){ 
			return percentageSupertype/100;
		}
		
		public String getAllPercentage(){ 
			return String.format("%.2f", percentageAllElements).replace(",", ".")+"%"; 
		}
		
		public float getAllPercentageAsFloat(){ 
			return percentageAllElements/100;
		}
		
		@Override
		public String toString(){
			return 	typeName+": "+count+" ("+String.format("%.2f", percentageSupertype).replace(",", ".")+"%)";
		}

		
	}

	public int getMomentCount(){ return momentDetail.count; }
	public String getMomentPercentageClasses(){ return String.format("%.1f", momentDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMomentPercentageElements(){ return String.format("%.1f", momentDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getSortalCount(){ return sortalDetail.count; }
	public String getSortalPercentageClasses(){ return String.format("%.1f", sortalDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getSortalPercentageElements(){ return String.format("%.1f", sortalDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getNonSortalCount(){ return nonSortalDetail.count; }
	public String getNonSortalPercentageClasses(){ return String.format("%.1f", nonSortalDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getNonSortalPercentageElements(){ return String.format("%.1f", nonSortalDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getRigidCount(){ return rigidDetail.count; }
	public String getRigidPercentageClasses(){ return String.format("%.1f", rigidDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getRigidPercentageElements(){ return String.format("%.1f", rigidDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getNonRigidCount(){ return nonRigidDetail.count; }
	public String getNonRigidPercentageClasses(){ return String.format("%.1f", nonRigidDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getNonRigidPercentageElements(){ return String.format("%.1f", nonRigidDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getAntiRigidCount(){ return antiRigidDetail.count; }
	public String getAntiRigidPercentageClasses(){ return String.format("%.1f", antiRigidDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getAntiRigidPercentageElements(){ return String.format("%.1f", antiRigidDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getKindCount(){ return kindDetail.count; }
	public String getKindPercentageClasses(){ return String.format("%.1f", kindDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getKindPercentageElements(){ return String.format("%.1f", kindDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getSubKindCount(){ return subkindDetail.count; }
	public String getSubKindPercentageClasses(){ return String.format("%.1f", subkindDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getSubKindPercentageElements(){ return String.format("%.1f", subkindDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getQuantityCount(){ return quantityDetail.count; }
	public String getQuantityPercentageClasses(){ return String.format("%.1f", quantityDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getQuantityPercentageElements(){ return String.format("%.1f", quantityDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getCollectiveCount(){ return collectiveDetail.count; }
	public String getCollectivePercentageClasses(){ return String.format("%.1f", collectiveDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getCollectivePercentageElements(){ return String.format("%.1f", collectiveDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getRoleCount(){ return roleDetail.count; }
	public String getRolePercentageClasses(){ return String.format("%.1f", roleDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getRolePercentageElements(){ return String.format("%.1f", roleDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getPhaseCount(){ return phaseDetail.count; }
	public String getPhasePercentageClasses(){ return String.format("%.1f", phaseDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getPhasePercentageElements(){ return String.format("%.1f", phaseDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getCategoryCount(){ return categoryDetail.count; }
	public String getCategoryPercentageClasses(){ return String.format("%.1f", categoryDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getCategoryPercentageElements(){ return String.format("%.1f", categoryDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getMixinCount(){ return mixinDetail.count; }
	public String getMixinPercentageClasses(){ return String.format("%.1f", mixinDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMixinPercentageElements(){ return String.format("%.1f", mixinDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getRoleMixinCount(){ return roleMixinDetail.count; }
	public String getRoleMixinPercentageClasses(){ return String.format("%.1f", roleMixinDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getRoleMixinPercentageElements(){ return String.format("%.1f", roleMixinDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getModeCount(){ return modeDetail.count; }
	public String getModePercentageClasses(){ return String.format("%.1f", modeDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getModePercentageElements(){ return String.format("%.1f", modeDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getRelatorCount(){ return relatorDetail.count; }
	public String getRelatorPercentageClasses(){ return String.format("%.1f", relatorDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getRelatorPercentageElements(){ return String.format("%.1f", relatorDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getPerceivableQualityCount(){ return perceivableDetail.count; }
	public String getPerceivableQualityPercentageClasses(){ return String.format("%.1f", perceivableDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getPerceivableQualityPercentageElements(){ return String.format("%.1f", perceivableDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getNonPerceivableQualityCount(){ return nonperceivableDetail.count; }
	public String getNonPerceivableQualityPercentageClasses(){ return String.format("%.1f", nonperceivableDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getNonPerceivableQualityPercentageElements(){ return String.format("%.1f", nonperceivableDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getNominalQualityCount(){ return nominalDetail.count; }
	public String getNominalQualityPercentageClasses(){ return String.format("%.1f", nominalDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getNominalQualityPercentageElements(){ return String.format("%.1f", nominalDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getEnumerationCount(){ return enumerationsDetail.count; }
	public String getEnumerationPercentageDataType(){ return String.format("%.1f", enumerationsDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getEnumerationPercentageElements(){ return String.format("%.1f", enumerationsDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getPrimitiveTypeCount(){ return primitivesDetail.count; }
	public String getPrimitiveTypePercentageDataType(){ return String.format("%.1f", primitivesDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getPrimitiveTypePercentageElements(){ return String.format("%.1f", primitivesDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getDataTypeCount(){ return allDatatypesDetail.count; }
	public String getDataTypePercentageDataType(){ return String.format("%.1f", allDatatypesDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getDataTypePercentageElements(){ return String.format("%.1f", allDatatypesDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getMaterialCount(){ return materialDetail.count; }
	public String getMaterialPercentageType(){ return String.format("%.1f", materialDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMaterialPercentageElements(){ return String.format("%.1f", materialDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getFormalCount(){ return formalDetail.count; }
	public String getFormalPercentageType(){ return String.format("%.1f", formalDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getFormalPercentageElements(){ return String.format("%.1f", formalDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getMediationCount(){ return mediationDetail.count; }
	public String getMediationPercentageType(){ return String.format("%.1f", mediationDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMediationPercentageElements(){ return String.format("%.1f", mediationDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getCharacterizationCount(){ return characterizationDetail.count; }
	public String getCharacterizationPercentageType(){ return String.format("%.1f", characterizationDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getCharacterizationPercentageElements(){ return String.format("%.1f", characterizationDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getComponentOfCount(){ return componentOfDetail.count; }
	public String getComponentOfPercentageType(){ return String.format("%.1f", componentOfDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getComponentOfPercentageElements(){ return String.format("%.1f", componentOfDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getMemberOfCount(){ return memberOfDetail.count; }
	public String getMemberOfPercentageType(){ return String.format("%.1f", memberOfDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMemberOfPercentageElements(){ return String.format("%.1f", memberOfDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getSubCollectionOfCount(){ return subCollectionOfDetail.count; }
	public String getSubCollectionOfPercentageType(){ return String.format("%.1f", subCollectionOfDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getSubCollectionOfPercentageElements(){ return String.format("%.1f", subCollectionOfDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getSubQuantityOfCount(){ return subQuantityOfDetail.count; }
	public String getSubQuantityOfPercentageType(){ return String.format("%.1f", subQuantityOfDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getSubQuantityOfPercentageElements(){ return String.format("%.1f", subQuantityOfDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getAssociationCount(){ return associationsDetail.count; }
	public String getAssociationPercentageType(){ return String.format("%.1f", associationsDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getAssociationPercentageElements(){ return String.format("%.1f", associationsDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getDerivationCount(){ return derivationDetail.count; }
	public String getDerivationPercentageType(){ return String.format("%.1f", derivationDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getDerivationPercentageElements(){ return String.format("%.1f", derivationDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getUnknownClassCount(){ return unknownClassDetail.count; }
	public String getUnknownClassPercentageType(){ return String.format("%.1f", unknownClassDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getUnknownClassPercentageElements(){ return String.format("%.1f", unknownClassDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getUnknownAssociationCount(){ return unknownAssociationDetail.count; }
	public String getUnknownAssociationPercentageType(){ return String.format("%.1f", unknownAssociationDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getUnknownAssociationPercentageElements(){ return String.format("%.1f", unknownAssociationDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getGeneralizationCount(){ return generalizationDetail.count; }
	public String getGeneralizationPercentageType(){ return String.format("%.1f", generalizationDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getGeneralizationPercentageElements(){ return String.format("%.1f", generalizationDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getGeneralizationSetCount(){ return genSetDetail.count; }
	public String getGeneralizationSetPercentageType(){ return String.format("%.1f", genSetDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getGeneralizationSetPercentageElements(){ return String.format("%.1f", genSetDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getRegularDatatypeCount(){ return regularDatatypeDetail.count; }
	public String getRegularDatatypePercentageType(){ return String.format("%.1f", regularDatatypeDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getRegularDatatypePercentageElements(){ return String.format("%.1f", regularDatatypeDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getClassCount(){ return classesDetail.count; }
	public String getClassPercentageType(){ return String.format("%.1f", classesDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getClassPercentageElements(){ return String.format("%.1f", classesDetail.percentageAllElements).replace(",", ".")+"%"; }

	public ArrayList<Object> createList(LineType lt, InfoType it, boolean addModelName) {
		ArrayList<Object> list = new ArrayList<Object>();		
		ArrayList<TypeDetail> detailList = hashMap.get(lt);
		
		if(detailList==null)
			return list;
		
		if(addModelName){
			if(it==InfoType.MEASURE)	
				list.add("Model");
			else
				list.add(parser.getModelName());
		}
		
		for (TypeDetail detail : detailList) {
			
			if(it==InfoType.MEASURE)
				list.add(detail.getMeasure());
			else if(it==InfoType.COUNT)
				list.add(detail.getCount());
			else if(it==InfoType.TYPE_PERCENTAGE)
				list.add(detail.getTypePercentageAsFloat());
			else if(it==InfoType.ALL_PERCENTAGE)
				list.add(detail.getAllPercentageAsFloat());
		}
		
		return list;
		
	}

	
}
