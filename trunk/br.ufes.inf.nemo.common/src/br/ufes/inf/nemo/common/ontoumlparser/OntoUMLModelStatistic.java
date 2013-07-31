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
import RefOntoUML.FormalAssociation;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Phase;
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
	
	private ArrayList<Object> 	classifiers, classes, kinds, quantities, collectives, subkinds, roles, phases, categories,
								roleMixins, mixins, relators, modes, datatypes, associations, materials, mediations,
								characterizations, componentOfs, subCollectionOfs, memberOfs, subQuantityOfs, formals;
	
	private TypeDetail 	kindDetail, collectiveDetail, quantityDetail, subkindDetail, roleDetail, phaseDetail,
						mixinDetail, categoryDetail, roleMixinDetail, datatypeDetail, materialDetail, mediationDetail,
						formalDetail, characterizationDetail, componentOfDetail, subQuantityOfDetail,
						subCollectionOfDetail, memberOfDetail, relatorDetail, modeDetail, 
						rigidDetail, antiRigidDetail, sortalDetail, nonSortalDetail, momentDetail, nonRigidDetail;
	
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
		datatypes = new ArrayList<>();
		associations = new ArrayList<>();
		materials = new ArrayList<>();
		mediations = new ArrayList<>();
		characterizations = new ArrayList<>();
		componentOfs = new ArrayList<>();
		subCollectionOfs = new ArrayList<>();
		memberOfs = new ArrayList<>();
		subQuantityOfs = new ArrayList<>();
		formals = new ArrayList<>();
		classifiers = new ArrayList<>();
		
		this.parser = parser;
		
	}
	
	
	public void run () {
		
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
					
					else
						System.out.println(c);
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
					
					else
						System.out.println(c);
				}
			   
				else if (c instanceof DataType)
					datatypes.add((DataType) c);  
				
				else
					System.out.println(c);
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
		
		materialDetail = new TypeDetail("Material",materials.size(), associations.size(), classifiers.size());
		mediationDetail = new TypeDetail("Mediation",mediations.size(), associations.size(), classifiers.size());
		characterizationDetail = new TypeDetail("Characterization",characterizations.size(), associations.size(), classifiers.size());
		componentOfDetail = new TypeDetail("ComponentOf",componentOfs.size(), associations.size(), classifiers.size());
		memberOfDetail = new TypeDetail("MemberOf",memberOfs.size(), associations.size(), classifiers.size());
		subCollectionOfDetail = new TypeDetail("SubCollectionOf",subCollectionOfs.size(), associations.size(), classifiers.size());
		subQuantityOfDetail= new TypeDetail("SubQuantityOf",subQuantityOfs.size(), associations.size(), classifiers.size());
		formalDetail = new TypeDetail("Formal",formals.size(), associations.size(), classifiers.size());
		
		datatypeDetail = new TypeDetail("Datatype", datatypes.size(), datatypes.size(), classifiers.size());
		
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
		
		int momentSize = relators.size()+modes.size();
		TypeDetail momentDetail = new TypeDetail("Moment Type", momentSize, classes.size(), classifiers.size());
		
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
				"\n\t"+modeDetail;
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
		return datatypeDetail.toString();
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
			return 	typeName+": "+size+" ("+String.format("%.1f", percentageSupertype)+"%)";
		}
	}
	
}
