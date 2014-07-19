package br.ufes.inf.nemo.common.ontoumlparser;

import java.util.ArrayList;
import java.util.Set;

import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Collective;
import RefOntoUML.DataType;
import RefOntoUML.Derivation;
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

public class OntoUMLModelStatistic {
	
	OntoUMLParser parser;
	
	ArrayList<Generalization> generalizations;
	ArrayList<GeneralizationSet> genSets;
	ArrayList<Property> attributes;
	
	private ArrayList<Object> 	classifiers, classes, kinds, quantities, collectives, subkinds, roles, phases, categories,
								roleMixins, mixins, relators, modes, perceivable, nonperceivable, nominal, datatypes, associations, materials, mediations, derivations,
								enumerations, primitives, characterizations, componentOfs, subCollectionOfs, memberOfs, subQuantityOfs, formals, unknownClasses, unknownAssociations;
	
	private TypeDetail 	kindDetail, collectiveDetail, quantityDetail, subkindDetail, roleDetail, phaseDetail,
						mixinDetail, categoryDetail, perceivableDetail, nonperceivableDetail, nominalDetail, roleMixinDetail, datatypeDetail, materialDetail, mediationDetail,
						enumerationsDetail, primitivesDetail, formalDetail, characterizationDetail, componentOfDetail, subQuantityOfDetail,
						subCollectionOfDetail, memberOfDetail, relatorDetail, modeDetail, classesDetail,
						rigidDetail, antiRigidDetail, sortalDetail, nonSortalDetail, momentDetail, nonRigidDetail, associationsDetail;
	
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
		
		this.parser = parser;		
	}	
	
	public void run () {
		
		this.generalizations.addAll(parser.getAllInstances(Generalization.class));
		
		this.genSets.addAll(parser.getAllInstances(GeneralizationSet.class));
		
		for (Property p : parser.getAllInstances(Property.class)) {
			if(p.getAssociation()==null)
				attributes.add(p);
		}
		
		Set<Classifier> classi = parser.getAllInstances(Classifier.class);	  	   
		for (Object c : classi) {
			
			if (c instanceof Classifier){
				classifiers.add(c);
				if (c instanceof RefOntoUML.Class){
					classes.add((Class) c);
				   
					if (c instanceof RefOntoUML.Kind)
					   kinds.add((RefOntoUML.Kind) c);
				   
					else if (c instanceof Quantity)
					   quantities.add((Quantity) c);
				   
					else if (c instanceof Collective)
					   collectives.add((Collective) c);
					
					else if (c instanceof SubKind)
					   subkinds.add((SubKind) c);
				   
					else if (c instanceof Role)
					   roles.add((Role) c);
				   
					else if (c instanceof Phase)
						phases.add((Phase) c);
				   
					else if (c instanceof Category)
						categories.add((Category) c);
				   
					else if (c instanceof RoleMixin)
						roleMixins.add((RoleMixin) c);
				   
					else if (c instanceof Mixin)
						mixins.add((Mixin) c);
				   
					else if (c instanceof Mode)
						modes.add((Mode) c);
					else if (c instanceof Relator)
						relators.add((Relator) c);
					
					else if (c instanceof PerceivableQuality)
						perceivable.add((PerceivableQuality) c);
					else if (c instanceof NonPerceivableQuality)
						nonperceivable.add((NonPerceivableQuality) c);
					else if (c instanceof NominalQuality)
						nominal.add((NominalQuality) c);
					
					else
						unknownClasses.add(c);
				}
			   
				else if (c instanceof Association){
				   
					associations.add((Association) c);
				   
					if (c instanceof MaterialAssociation)
						materials.add((MaterialAssociation) c);
					
					else if (c instanceof Mediation)
						mediations.add((Mediation) c);
				   
					else if (c instanceof FormalAssociation)
						formals.add((FormalAssociation) c);
				   
					else if (c instanceof Characterization)
						characterizations.add((Characterization) c);
				   
					else if (c instanceof componentOf)
						componentOfs.add((componentOf) c);
				   
					else if (c instanceof subCollectionOf)
						subCollectionOfs.add((subCollectionOf) c);
				   
					else if (c instanceof memberOf)
						memberOfs.add((memberOf) c);
				   
					else if (c instanceof subQuantityOf)
						subQuantityOfs.add((subQuantityOf) c);
					
					else if (c instanceof Derivation)
						derivations.add(c);
					
					else
						unknownAssociations.add(c);
					
				}

				else if (c instanceof PrimitiveType)
					primitives.add((PrimitiveType) c);					
				else if (c instanceof Enumeration)
					enumerations.add((Enumeration) c);
				else if (c instanceof DataType)
					datatypes.add((DataType) c);
				
			}
	   }
		
		kindDetail = new TypeDetail("Kind", kinds.size(), classes.size(), classifiers.size());
		quantityDetail = new TypeDetail("Quantity",quantities.size(), classes.size(), classifiers.size());
		collectiveDetail = new TypeDetail("Collective",collectives.size(), classes.size(), classifiers.size());
		subkindDetail = new TypeDetail("Subkind",subkinds.size(), classes.size(), classifiers.size());
		roleDetail = new TypeDetail("Role",roles.size(), classes.size(), classifiers.size());
		phaseDetail = new TypeDetail("Phase",phases.size(), classes.size(), classifiers.size());
		categoryDetail = new TypeDetail("Category",categories.size(), classes.size(), classifiers.size());
		roleMixinDetail = new TypeDetail("RoleMixin",roleMixins.size(), classes.size(), classifiers.size());
		mixinDetail = new TypeDetail("Mixin",mixins.size(), classes.size(), classifiers.size());
		relatorDetail = new TypeDetail("Relator",relators.size(), classes.size(), classifiers.size());
		modeDetail = new TypeDetail("Mode",modes.size(), classes.size(), classifiers.size());
		perceivableDetail = new TypeDetail("Perceivable Quality",perceivable.size(), classes.size(), classifiers.size());
		nonperceivableDetail = new TypeDetail("NonPerceivable Quality",nonperceivable.size(), classes.size(), classifiers.size());
		nominalDetail = new TypeDetail("Nominal Quality",nominal.size(), classes.size(), classifiers.size());
		classesDetail = new TypeDetail("Class",classes.size(), classes.size(), classifiers.size());
		
		materialDetail = new TypeDetail("Material",materials.size(), associations.size(), classifiers.size());
		mediationDetail = new TypeDetail("Mediation",mediations.size(), associations.size(), classifiers.size());
		characterizationDetail = new TypeDetail("Characterization",characterizations.size(), associations.size(), classifiers.size());
		componentOfDetail = new TypeDetail("ComponentOf",componentOfs.size(), associations.size(), classifiers.size());
		memberOfDetail = new TypeDetail("MemberOf",memberOfs.size(), associations.size(), classifiers.size());
		subCollectionOfDetail = new TypeDetail("SubCollectionOf",subCollectionOfs.size(), associations.size(), classifiers.size());
		subQuantityOfDetail= new TypeDetail("SubQuantityOf",subQuantityOfs.size(), associations.size(), classifiers.size());
		formalDetail = new TypeDetail("Formal",formals.size(), associations.size(), classifiers.size());
		associationsDetail = new TypeDetail("Association",associations.size(), associations.size(), classifiers.size());
		
		enumerationsDetail = new TypeDetail("Enumeration", enumerations.size(), datatypes.size(), classifiers.size());
		primitivesDetail = new TypeDetail("PrimitiveType", primitives.size(), datatypes.size(), classifiers.size());
		datatypeDetail = new TypeDetail("DataType", datatypes.size(), datatypes.size(), classifiers.size());
		
		rigidDetail = createRigidDetail();
		antiRigidDetail = createAntiRigidDetail();
		nonRigidDetail = createNonRigidDetail();
		
		sortalDetail = createSortalDetail();
		nonSortalDetail = createNonSortalDetail();
		momentDetail = createMomentDetail();
	}
	
	private TypeDetail createRigidDetail(){
		int rigidSize = 0;
		
		rigidSize = kinds.size()+collectives.size()+quantities.size()+subkinds.size()+
					categories.size()+relators.size()+modes.size();
		
		TypeDetail rigidDetail = new TypeDetail("Rigid Type", rigidSize, classes.size(), classifiers.size());
		
		return rigidDetail;
	}
	
	private TypeDetail createAntiRigidDetail(){
		int antiRigidSize = 0;
		
		antiRigidSize = roles.size()+phases.size()+roleMixins.size();
		
		TypeDetail antiRigidDetail = new TypeDetail("AntiRigid Type", antiRigidSize, classes.size(), classifiers.size());
		
		return antiRigidDetail;
	}
	
	private TypeDetail createNonRigidDetail(){
		int nonRigidSize = 0;
		
		nonRigidSize = mixins.size();
		
		TypeDetail nonRigidDetail = new TypeDetail("NonRigid Type", nonRigidSize, classes.size(), classifiers.size());
		
		return nonRigidDetail;
	}
	
	private TypeDetail createSortalDetail(){
		int sortalSize = 0;
		
		sortalSize = kinds.size()+collectives.size()+quantities.size()+subkinds.size()+roles.size()+phases.size();
		
		TypeDetail sortalDetail = new TypeDetail("Sortal Type", sortalSize, classes.size(), classifiers.size());
		
		return sortalDetail;
	}
	
	private TypeDetail createNonSortalDetail(){
		int nonSortalSize = 0;
		
		nonSortalSize = categories.size()+roleMixins.size()+mixins.size();
		
		TypeDetail nonSortalDetail = new TypeDetail("Non Sortal Type", nonSortalSize, classes.size(), classifiers.size());
		
		return nonSortalDetail;
	}
	
	private TypeDetail createMomentDetail(){
		
		int momentSize = relators.size()+modes.size()+perceivable.size()+nonperceivable.size()+nominal.size();
		TypeDetail momentDetail = new TypeDetail("Moment Type", momentSize, classes.size(), classifiers.size());
		
		return momentDetail;
	}

	public int getMomentCount(){ return momentDetail.size; }
	public String getMomentPercentageClasses(){ return String.format("%.1f", momentDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMomentPercentageElements(){ return String.format("%.1f", momentDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getSortalCount(){ return sortalDetail.size; }
	public String getSortalPercentageClasses(){ return String.format("%.1f", sortalDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getSortalPercentageElements(){ return String.format("%.1f", sortalDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getNonSortalCount(){ return nonSortalDetail.size; }
	public String getNonSortalPercentageClasses(){ return String.format("%.1f", nonSortalDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getNonSortalPercentageElements(){ return String.format("%.1f", nonSortalDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getRigidCount(){ return rigidDetail.size; }
	public String getRigidPercentageClasses(){ return String.format("%.1f", rigidDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getRigidPercentageElements(){ return String.format("%.1f", rigidDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getNonRigidCount(){ return nonRigidDetail.size; }
	public String getNonRigidPercentageClasses(){ return String.format("%.1f", nonRigidDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getNonRigidPercentageElements(){ return String.format("%.1f", nonRigidDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getAntiRigidCount(){ return antiRigidDetail.size; }
	public String getAntiRigidPercentageClasses(){ return String.format("%.1f", antiRigidDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getAntiRigidPercentageElements(){ return String.format("%.1f", antiRigidDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getKindCount(){ return kindDetail.size; }
	public String getKindPercentageClasses(){ return String.format("%.1f", kindDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getKindPercentageElements(){ return String.format("%.1f", kindDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getSubKindCount(){ return subkindDetail.size; }
	public String getSubKindPercentageClasses(){ return String.format("%.1f", subkindDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getSubKindPercentageElements(){ return String.format("%.1f", subkindDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getQuantityCount(){ return quantityDetail.size; }
	public String getQuantityPercentageClasses(){ return String.format("%.1f", quantityDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getQuantityPercentageElements(){ return String.format("%.1f", quantityDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getCollectiveCount(){ return collectiveDetail.size; }
	public String getCollectivePercentageClasses(){ return String.format("%.1f", collectiveDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getCollectivePercentageElements(){ return String.format("%.1f", collectiveDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getRoleCount(){ return roleDetail.size; }
	public String getRolePercentageClasses(){ return String.format("%.1f", roleDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getRolePercentageElements(){ return String.format("%.1f", roleDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getPhaseCount(){ return phaseDetail.size; }
	public String getPhasePercentageClasses(){ return String.format("%.1f", phaseDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getPhasePercentageElements(){ return String.format("%.1f", phaseDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getCategoryCount(){ return categoryDetail.size; }
	public String getCategoryPercentageClasses(){ return String.format("%.1f", categoryDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getCategoryPercentageElements(){ return String.format("%.1f", categoryDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getMixinCount(){ return mixinDetail.size; }
	public String getMixinPercentageClasses(){ return String.format("%.1f", mixinDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMixinPercentageElements(){ return String.format("%.1f", mixinDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getRoleMixinCount(){ return roleDetail.size; }
	public String getRoleMixinPercentageClasses(){ return String.format("%.1f", roleDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getRoleMixinPercentageElements(){ return String.format("%.1f", roleDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getModeCount(){ return modeDetail.size; }
	public String getModePercentageClasses(){ return String.format("%.1f", modeDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getModePercentageElements(){ return String.format("%.1f", modeDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getRelatorCount(){ return relatorDetail.size; }
	public String getRelatorPercentageClasses(){ return String.format("%.1f", relatorDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getRelatorPercentageElements(){ return String.format("%.1f", relatorDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getPerceivableQualityCount(){ return perceivableDetail.size; }
	public String getPerceivableQualityPercentageClasses(){ return String.format("%.1f", perceivableDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getPerceivableQualityPercentageElements(){ return String.format("%.1f", perceivableDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getNonPerceivableQualityCount(){ return nonperceivableDetail.size; }
	public String getNonPerceivableQualityPercentageClasses(){ return String.format("%.1f", nonperceivableDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getNonPerceivableQualityPercentageElements(){ return String.format("%.1f", nonperceivableDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getNominalQualityCount(){ return nominalDetail.size; }
	public String getNominalQualityPercentageClasses(){ return String.format("%.1f", nominalDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getNominalQualityPercentageElements(){ return String.format("%.1f", nominalDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getEnumerationCount(){ return enumerationsDetail.size; }
	public String getEnumerationPercentageDataType(){ return String.format("%.1f", enumerationsDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getEnumerationPercentageElements(){ return String.format("%.1f", enumerationsDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getPrimitiveTypeCount(){ return primitivesDetail.size; }
	public String getPrimitiveTypePercentageDataType(){ return String.format("%.1f", primitivesDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getPrimitiveTypePercentageElements(){ return String.format("%.1f", primitivesDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getDataTypeCount(){ return datatypeDetail.size; }
	public String getDataTypePercentageDataType(){ return String.format("%.1f", datatypeDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getDataTypePercentageElements(){ return String.format("%.1f", datatypeDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getMaterialCount(){ return materialDetail.size; }
	public String getMaterialPercentageType(){ return String.format("%.1f", materialDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMaterialPercentageElements(){ return String.format("%.1f", materialDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getFormalCount(){ return formalDetail.size; }
	public String getFormalPercentageType(){ return String.format("%.1f", formalDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getFormalPercentageElements(){ return String.format("%.1f", formalDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getMediationCount(){ return mediationDetail.size; }
	public String getMediationPercentageType(){ return String.format("%.1f", mediationDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMediationPercentageElements(){ return String.format("%.1f", mediationDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getCharacterizationCount(){ return characterizationDetail.size; }
	public String getCharacterizationPercentageType(){ return String.format("%.1f", characterizationDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getCharacterizationPercentageElements(){ return String.format("%.1f", characterizationDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getComponentOfCount(){ return componentOfDetail.size; }
	public String getComponentOfPercentageType(){ return String.format("%.1f", componentOfDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getComponentOfPercentageElements(){ return String.format("%.1f", componentOfDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getMemberOfCount(){ return memberOfDetail.size; }
	public String getMemberOfPercentageType(){ return String.format("%.1f", memberOfDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getMemberOfPercentageElements(){ return String.format("%.1f", memberOfDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getSubCollectionOfCount(){ return subCollectionOfDetail.size; }
	public String getSubCollectionOfPercentageType(){ return String.format("%.1f", subCollectionOfDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getSubCollectionOfPercentageElements(){ return String.format("%.1f", subCollectionOfDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getSubQuantityOfCount(){ return subQuantityOfDetail.size; }
	public String getSubQuantityOfPercentageType(){ return String.format("%.1f", subQuantityOfDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getSubQuantityOfPercentageElements(){ return String.format("%.1f", subQuantityOfDetail.percentageAllElements).replace(",", ".")+"%"; }
	
	public int getAssociationCount(){ return associationsDetail.size; }
	public String getAssociationPercentageType(){ return String.format("%.1f", associationsDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getAssociationPercentageElements(){ return String.format("%.1f", associationsDetail.percentageAllElements).replace(",", ".")+"%"; }

	public int getClassCount(){ return classesDetail.size; }
	public String getClassPercentageType(){ return String.format("%.1f", classesDetail.percentageSupertype).replace(",", ".")+"%"; }
	public String getClassPercentageElements(){ return String.format("%.1f", classesDetail.percentageAllElements).replace(",", ".")+"%"; }
	
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
		return datatypeDetail + "\n" +
			   enumerationsDetail + "\n" +
			   primitivesDetail + "\n";
	}
	
	public String generateCSVLine(){
		return 
			parser.getModelName()+","+
			classes.size()+","+
			generalizations.size()+","+
			genSets.size()+","+
			associations.size()+","+
			attributes.size()+","+
    
			kinds.size()+","+
			collectives.size()+","+
			quantities.size()+","+
			subkinds.size()+","+
			roles.size()+","+
			phases.size()+","+
			categories.size()+","+
			roleMixins.size()+","+
			mixins.size()+","+
			relators.size()+","+
			modes.size()+","+
			datatypes.size()+","+
			perceivable.size()+","+
			nonperceivable.size()+","+
			nominal.size()+","+
			unknownClasses.size()+","+
			
    		materials.size()+","+
    		formals.size()+","+
    		mediations.size()+","+
    		characterizations.size()+","+
    		componentOfs.size()+","+
    		memberOfs.size()+","+
    		subCollectionOfs.size()+","+
    		subQuantityOfs.size()+","+
    		derivations.size()+","+
    		unknownAssociations.size()+"\n";
	}
	
	private class TypeDetail{
		String typeName;
		int size;
		float percentageSupertype;		
		float percentageAllElements;
		
		public TypeDetail(String typeName, int typesize, int supertype, int all) {
			this.typeName = typeName; 
			size = typesize;
			percentageSupertype = ((float) size/supertype)*100;
			percentageAllElements = ((float)size/all)*100;
		}
		
		@Override
		public String toString(){
			return 	typeName+": "+size+" ("+String.format("%.1f", percentageSupertype).replace(",", ".")+"%)";
		}
	}
	
}
