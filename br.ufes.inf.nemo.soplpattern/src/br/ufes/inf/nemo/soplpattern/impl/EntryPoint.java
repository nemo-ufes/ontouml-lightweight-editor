package br.ufes.inf.nemo.soplpattern.impl;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import RefOntoUML.Association;
import RefOntoUML.Category;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.Package;
import RefOntoUML.Relator;
import RefOntoUML.memberOf;
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.soplpattern.dynamic.ui.JanBase;
import br.ufes.inf.nemo.soplpattern.impl.SOPLPattern;

public class EntryPoint extends SOPLPattern{

	private Classifier c = null;
	private int entryPoint = 0;  // 1 = SOffering and 2 = SAgreement 
			
	public EntryPoint(OntoUMLParser parser, double x, double y) {		
		super(parser, x, y, "/resource/SOFFERING.png", "SOFFERING");
	}
	
	public EntryPoint(OntoUMLParser parser, double x, double y, int entrypoint) {		
		super(parser, x, y, "/resource/SOFFERING.png", "SOFFERING");
		this.entryPoint = entrypoint;
	}

	public EntryPoint(OntoUMLParser parser, Classifier c, double x, double y) {		
		super(parser, x, y, "/resource/SOFFERING.png", "SOFFERING");
		this.c = c;
	}	
	
	
	public void runPattern(DiagramManager diagramManager) {
		this.diagramManager = diagramManager;	
		janBase = new JanBase(this, entryPoint);
	}
	
	public Fix getSpecificFixSOffering(int patternProviderSelected, int patternCustomerSelected, boolean yes_no, int pattern_yes_no_selecionado) {			
		
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		
		Association providerOffering = null;
		Association descriptionOffering = null;
		Association commitmentOffering = null;
		Association targetCustomerOffering = null;
		
		Association serviceAgreementOffering = null;
		Association HiredProviderServiceAgreement = null;
		Association ServiceCustomerServiceAgreement = null;
		
		Association serviceNegotiationOffering = null;
		
		Association providerNegotiation = null;
		Association targetCustomerNegotiation = null;
		
		Classifier collectiveA = null;
		Classifier roleServiceProvider = null;
		Classifier roleOrgTC = null;
		Classifier roleOrgUnitProvider = null;
		Classifier roleOrgUnitTC = null;
		Classifier rolePersonProvider = null;
		Classifier rolePersonTC = null;
		Classifier roleOrganizationProvider = null;
		Classifier collectiveB = null;
		Classifier roleTargetCustomer = null;
		Classifier relatorOffering = null; //Nome da Offering
		Classifier relatorAgreement = null; //Nome do Agreement
		Classifier relatorNegotiation = null; //Nome da Negotiation
		
		Classifier roleServiceCustomer = null;
		Classifier roleHiredServiceProvider = null;
		
		Classifier collectiveTCC = null; // Collective Target Customer Community
		Classifier categorySODescription = null; // Service Offering Description
		Classifier categorySOCommitment = null; // Service Offering Commitment
		
		//Chamar um metodo que pega todos os elementos da interface
		
		// 1 STEP - VERIFICAR E CRIAR DENTRO DO FIX OS ELEMENTOS DO PATTERN PROVIDER E CUSTOMER SUBGROUP SELECIONADOS
		
		if(patternProviderSelected == 1){ //Pattern P-Provider
			//Create Person
			String person = janBase.getTxtPerson_P_Provider().getText();			
			collectiveA  = this.createClassifier(person, "kind", 0, 150);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_P_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", 0, 300);	
			
			fix.addAll(outcomeFixer.createGeneralization(roleServiceProvider, collectiveA));
		}else if(patternProviderSelected == 2){// Pattern O-Provider
			//Create Organization
			String organization = janBase.getTxtOrganization_O_Provider().getText();			
			collectiveA  = this.createClassifier(organization, "kind", 200, 150);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_O_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", 200, 300);	
			
			fix.addAll(outcomeFixer.createGeneralization(roleServiceProvider, collectiveA));
		}else if(patternProviderSelected == 3){// Pattern OU-Provider
			//Create Organization Unit
			String orgUnit = janBase.getTxtOrgUnit_OU_Provider().getText();			
			collectiveA  = this.createClassifier(orgUnit, "kind", 200, 150);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_OU_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", 200, 300);
			
			fix.addAll(outcomeFixer.createGeneralization(roleServiceProvider, collectiveA));
		}else if(patternProviderSelected == 4){// Pattern P-O-OU-Provider
			
		}else if(patternProviderSelected == 5){// Pattern O-OU-Provider
			//Create Organization Unit Provider
			String orgUnitProvider = janBase.getTxtOrgUnitProvider_O_OU_Provider().getText();			
			roleOrgUnitProvider  = this.createClassifier(orgUnitProvider, "role", 150, 450);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_O_OU_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", 200, 300);
			
			//Create Organization Provider
			String organizationProvider = janBase.getTxtOrganizationProvider_O_OU_Provider().getText();
			roleOrganizationProvider = this.createClassifier(organizationProvider, "role", 250, 450);
		
			fix.addAll(outcomeFixer.createGeneralization(roleOrgUnitProvider, roleServiceProvider));	
			fix.addAll(outcomeFixer.createGeneralization(roleOrganizationProvider, roleServiceProvider));	
			
		}else if(patternProviderSelected == 6){// Pattern P-O-Provider
			//Create Organization Unit Provider
			String personProvider = janBase.getTxtPersonProvider_P_O_Provider().getText();			
			rolePersonProvider  = this.createClassifier(personProvider, "role", 150, 450);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_P_O_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", 200, 300);
			
			//Create Organization Provider
			String organizationProvider = janBase.getTxtOrganizationProvider_P_O_Provider().getText();
			roleOrganizationProvider = this.createClassifier(organizationProvider, "role", 250, 450); 
		
			fix.addAll(outcomeFixer.createGeneralization(rolePersonProvider, roleServiceProvider));	
			fix.addAll(outcomeFixer.createGeneralization(roleOrganizationProvider, roleServiceProvider));	
		}else if(patternProviderSelected == 7){// Pattern P-OU-Provider
			
		}
		
		if(patternCustomerSelected == 1){ //Pattern P-TCustomer
			//Create Person
			String person = janBase.getTxtPerson_P_TCustomer().getText();			
			collectiveB  = this.createClassifier(person, "kind",600, 500);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_P_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", 400, 500);
			
			fix.addAll(outcomeFixer.createGeneralization(roleTargetCustomer, collectiveB));
		}else if(patternCustomerSelected == 2){// Pattern O-TCustomer
			//Create Organization
			String organization = janBase.getTxtOrganization_O_TCustomer().getText();			
			collectiveB  = this.createClassifier(organization, "kind",600, 150);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_O_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", 600, 300);
			
			fix.addAll(outcomeFixer.createGeneralization(roleTargetCustomer, collectiveB));	
		}else if(patternCustomerSelected == 3){// Pattern OU-TCustomer
			//Create Organization Unit
			String orgUnit = janBase.getTxtOrgUnit_OU_TCustomer().getText();			
			collectiveB  = this.createClassifier(orgUnit, "kind",600, 150);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_OU_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", 600, 300);
			
			fix.addAll(outcomeFixer.createGeneralization(roleTargetCustomer, collectiveB));	
			
		}else if(patternCustomerSelected == 4){// Pattern P-O-OU-TCustomer
		
		}else if(patternCustomerSelected == 5){// Pattern O-OU-TCustomer
			//Create Organization Unit Target Customer
			String orgUnitTC = janBase.getTxtOrgUnitTC_O_OU_TCustomer().getText();			
			roleOrgUnitTC  = this.createClassifier(orgUnitTC, "role", 550, 450);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_O_OU_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", 600, 300);
			
			//Create Organization Target Customer
			String organizationTC = janBase.getTxtOrgTC_O_OU_TCustomer().getText();
			roleOrgTC = this.createClassifier(organizationTC, "role", 650, 450);
			
			fix.addAll(outcomeFixer.createGeneralization(roleOrgUnitTC, roleTargetCustomer));				
			fix.addAll(outcomeFixer.createGeneralization(roleOrgTC, roleTargetCustomer));	
		}else if(patternCustomerSelected == 6){// Pattern P-O-TCustomer
			//Create Person Target Customer
			String personTC = janBase.getTxtPersonTC_P_O_TCustomer().getText();			
			rolePersonTC  = this.createClassifier(personTC, "role", 550, 450);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_P_O_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", 600, 300);
			
			//Create Organization Target Customer
			String organizationTC = janBase.getTxtOrgTC_P_O_TCustomer().getText();
			roleOrgTC = this.createClassifier(organizationTC, "role", 650, 450);
			
			fix.addAll(outcomeFixer.createGeneralization(rolePersonTC, roleTargetCustomer));				
			fix.addAll(outcomeFixer.createGeneralization(roleOrgTC, roleTargetCustomer));	
		}else if(patternCustomerSelected == 7){// Pattern P-OU-TCustomer
		}
		
		// STEP 2 - CRIAR OFFERING, DESCRIPTION E COMMITMENT
		String offering = janBase.getTxtServiceOffering().getText(); //Offering		
		relatorOffering = this.createClassifier(offering , "Relator",  200, 300);		
		
		String soDescription = janBase.getTxtServiceOfferingDescription().getText(); //SODescription
		if(!soDescription.equals("")) {			
			categorySODescription = this.createClassifier(soDescription, "Category", 200, 500);
			
			descriptionOffering = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", categorySODescription, relatorOffering).getAdded().get(0);
			fix.includeAdded(descriptionOffering);	
		}	
		
		String soCommitment = janBase.getTxtServiceOfferingCommitment().getText();
		if(!soCommitment.equals("")) {			
			categorySOCommitment = this.createClassifier(soCommitment, "Category", 100, 500);
			
			commitmentOffering = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", categorySOCommitment, relatorOffering).getAdded().get(0);
			fix.includeAdded(commitmentOffering);	
		}	
		
	
		
		if(yes_no) { // O Usuario deseja modelar 1 dos 3 padroes : SNegAgree ou SOfferAgree ou SNegotiation
			if (pattern_yes_no_selecionado == 1) { //O Usuario escolheu o pattern SNegAgree
				
			}else if (pattern_yes_no_selecionado == 2) { //O Usuario escolheu o pattern SOfferAgree
				
				String agreement = janBase.getTextServiceAgreement_1().getText();
				relatorAgreement= this.createClassifier(agreement , "Relator",  200, 300);	
				
				roleServiceCustomer = this.createClassifier("Service Customer", "RoleMixin", 600, 300);		
				roleHiredServiceProvider = this.createClassifier("Hired Service Provider", "RoleMixin", 600, 300);	
				
				fix.addAll(outcomeFixer.createGeneralization(roleServiceCustomer, roleTargetCustomer));
				fix.addAll(outcomeFixer.createGeneralization(roleHiredServiceProvider, roleServiceProvider));
				
				
				serviceAgreementOffering = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", relatorAgreement, relatorOffering).getAdded().get(0);
				fix.includeAdded(serviceAgreementOffering);	
				
				HiredProviderServiceAgreement = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", roleHiredServiceProvider, relatorAgreement).getAdded().get(0);
				fix.includeAdded(HiredProviderServiceAgreement);	
				
				ServiceCustomerServiceAgreement = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", roleServiceCustomer, relatorAgreement).getAdded().get(0);
				fix.includeAdded(ServiceCustomerServiceAgreement);	
				
				
			}else if (pattern_yes_no_selecionado == 3) { //O Usuario escolheu o pattern SNegotiation
				
				String negotiation = janBase.getTextServiceNegotiation_1().getText();
				relatorNegotiation= this.createClassifier(negotiation , "Relator",  200, 300);	
				
				serviceNegotiationOffering = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", relatorNegotiation, relatorOffering).getAdded().get(0);
				fix.includeAdded(serviceNegotiationOffering);	
				
			}
		}
				
		// Association between Service Provider, Target Customer and Service Offering/Service Negotiation
		if( (yes_no) && (pattern_yes_no_selecionado != 2) ) { //Quando há uma NEGOTIATION, o service customer e target Customer sao ligados a ela e nao a offering
			providerNegotiation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", roleServiceProvider, relatorNegotiation).getAdded().get(0);
			fix.includeAdded(providerNegotiation);	
			
			targetCustomerNegotiation = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", roleTargetCustomer, relatorNegotiation).getAdded().get(0);
			fix.includeAdded(targetCustomerNegotiation);
		} else {
			providerOffering = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", roleServiceProvider, relatorOffering).getAdded().get(0);
			fix.includeAdded(providerOffering);	
			
			targetCustomerOffering = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", roleTargetCustomer, relatorOffering).getAdded().get(0);
			fix.includeAdded(targetCustomerOffering);
		}

			
		diagramManager.updateOLED(fix);
		return fix; 
		
	}

	public Fix getSpecificFixSAgreement(int patternProviderSelected, int patternCustomerSelected, boolean yes_no, int pattern_yes_no_selecionado) {			
		
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		
		diagramManager.updateOLED(fix);
		return fix;
	}
}
