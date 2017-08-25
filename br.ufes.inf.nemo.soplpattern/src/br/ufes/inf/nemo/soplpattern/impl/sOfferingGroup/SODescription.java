package br.ufes.inf.nemo.soplpattern.impl.sOfferingGroup;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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
import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.assistant.util.UtilAssistant;
import br.ufes.inf.nemo.common.ontoumlfixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.RelationStereotype;
import br.ufes.inf.nemo.oled.DiagramManager;
import br.ufes.inf.nemo.soplpattern.dynamic.ui.JanBase;
import br.ufes.inf.nemo.soplpattern.impl.SOPLPattern;

public class SODescription extends SOPLPattern{
	
	
	private Classifier c = null;
	private String elements[][] = new String[4][2];
	private String rdProviderSubgroupSelected;
	private String rdCustomerSubgroupSelected;
			
	public SODescription(OntoUMLParser parser, double x, double y) {		
		super(parser, x, y, "/resource/SOFFERING.png", "SOFFERING");
	}

	public SODescription(OntoUMLParser parser, Classifier c, double x, double y) {		
		super(parser, x, y, "/resource/SOFFERING.png", "SOFFERING");
		this.c = c;
	}	
	
	
	public void runPattern(DiagramManager diagramManager) {
						
		//Instanciar a Janela Principal SOPL aqui !
		this.diagramManager = diagramManager;	
		janBase = new JanBase(this);
	}
	
	public void criarTabela(String[][] tabela){
		this.elements = tabela;				
	}
	
	public Fix getSpecificFix(int patternProviderSelected, int patternCustomerSelected) {			
				
		//VAMOS RESOLVER O PROBLEMA TODOO NESSE METODO - SE FOR CRIAR UM PRA CADA PARTE VAI FICAR MT GRANDE
		
		Package root = parser.getModel();
		outcomeFixer = new OutcomeFixer(root);
		fix = new Fix();
		
		Association providerOffering = null;
		Association customerOffering = null;
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
		
		//Chamar um metodo que pega todos os elementos da interface
		
		// 1 STEP - VERIFICAR E CRIAR DENTRO DO FIX OS ELEMENTOS DO PATTERN PROVIDER E CUSTOMER SUBGROUP SELECIONADOS
		
		if(patternProviderSelected == 1){ //Pattern P-Provider
			//Create Person
			String person = janBase.getTxtPerson_P_Provider().getText();			
			collectiveA  = this.createClassifier(person, "kind", x, y);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_P_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", x, y);	
			
			fix.addAll(outcomeFixer.createGeneralization(roleServiceProvider, collectiveA));
		}else if(patternProviderSelected == 2){// Pattern O-Provider
			//Create Organization
			String organization = janBase.getTxtOrganization_O_Provider().getText();			
			collectiveA  = this.createClassifier(organization, "kind", x, y);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_O_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", x, y);	
			
			fix.addAll(outcomeFixer.createGeneralization(roleServiceProvider, collectiveA));
		}else if(patternProviderSelected == 3){// Pattern OU-Provider
			//Create Organization Unit
			String orgUnit = janBase.getTxtOrgUnit_OU_Provider().getText();			
			collectiveA  = this.createClassifier(orgUnit, "kind", x, y);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_OU_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", x, y);
			
			fix.addAll(outcomeFixer.createGeneralization(roleServiceProvider, collectiveA));
		}else if(patternProviderSelected == 4){// Pattern P-O-OU-Provider
			
		}else if(patternProviderSelected == 5){// Pattern O-OU-Provider
			//Create Organization Unit Provider
			String orgUnitProvider = janBase.getTxtOrgUnitProvider_O_OU_Provider().getText();			
			roleOrgUnitProvider  = this.createClassifier(orgUnitProvider, "role", x, y);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_O_OU_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", x, y);
			
			//Create Organization Provider
			String organizationProvider = janBase.getTxtOrganizationProvider_O_OU_Provider().getText();
			roleOrganizationProvider = this.createClassifier(organizationProvider, "role", x, y);
		
			fix.addAll(outcomeFixer.createGeneralization(roleOrgUnitProvider, roleServiceProvider));	
			fix.addAll(outcomeFixer.createGeneralization(roleOrganizationProvider, roleServiceProvider));	
			
		}else if(patternProviderSelected == 6){// Pattern P-O-Provider
			//Create Organization Unit Provider
			String personProvider = janBase.getTxtPersonProvider_P_O_Provider().getText();			
			rolePersonProvider  = this.createClassifier(personProvider, "role", x, y);
			
			//Create Service Provider
			String serviceProvider = janBase.getTxtServiceProvider_P_O_Provider().getText();
			roleServiceProvider = this.createClassifier(serviceProvider, "RoleMixin", x, y);
			
			//Create Organization Provider
			String organizationProvider = janBase.getTxtOrganizationProvider_P_O_Provider().getText();
			roleOrganizationProvider = this.createClassifier(organizationProvider, "role", x, y);
		
			fix.addAll(outcomeFixer.createGeneralization(rolePersonProvider, roleServiceProvider));	
			fix.addAll(outcomeFixer.createGeneralization(roleOrganizationProvider, roleServiceProvider));	
		}else if(patternProviderSelected == 7){// Pattern P-OU-Provider
			
		}
		
		if(patternCustomerSelected == 1){ //Pattern P-TCustomer
			//Create Person
			String person = janBase.getTxtPerson_P_TCustomer().getText();			
			collectiveB  = this.createClassifier(person, "kind",x+(verticalDistance/3), y);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_P_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", x+(verticalDistance/3), y+70);
			
			fix.addAll(outcomeFixer.createGeneralization(roleTargetCustomer, collectiveB));
		}else if(patternCustomerSelected == 2){// Pattern O-TCustomer
			//Create Organization
			String organization = janBase.getTxtOrganization_O_TCustomer().getText();			
			collectiveB  = this.createClassifier(organization, "kind",x+(verticalDistance/3), y);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_O_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", x+(verticalDistance/3), y+70);
			
			fix.addAll(outcomeFixer.createGeneralization(roleTargetCustomer, collectiveB));	
		}else if(patternCustomerSelected == 3){// Pattern OU-TCustomer
			//Create Organization Unit
			String orgUnit = janBase.getTxtOrgUnit_OU_TCustomer().getText();			
			collectiveB  = this.createClassifier(orgUnit, "kind",x+(verticalDistance/3), y);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_OU_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", x+(verticalDistance/3), y+70);
			
			fix.addAll(outcomeFixer.createGeneralization(roleTargetCustomer, collectiveB));	
			
		}else if(patternCustomerSelected == 4){// Pattern P-O-OU-TCustomer
		
		}else if(patternCustomerSelected == 5){// Pattern O-OU-TCustomer
			//Create Organization Unit Target Customer
			String orgUnitTC = janBase.getTxtOrgUnitTC_O_OU_TCustomer().getText();			
			roleOrgUnitTC  = this.createClassifier(orgUnitTC, "role", x, y);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_O_OU_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", x, y);
			
			//Create Organization Target Customer
			String organizationTC = janBase.getTxtOrgTC_O_OU_TCustomer().getText();
			roleOrgTC = this.createClassifier(organizationTC, "role", x, y);
			
			fix.addAll(outcomeFixer.createGeneralization(roleOrgUnitTC, roleTargetCustomer));				
			fix.addAll(outcomeFixer.createGeneralization(roleOrgTC, roleTargetCustomer));	
		}else if(patternCustomerSelected == 6){// Pattern P-O-TCustomer
			//Create Person Target Customer
			String personTC = janBase.getTxtPersonTC_P_O_TCustomer().getText();			
			rolePersonTC  = this.createClassifier(personTC, "role", x, y);
			
			//Create Target Customer
			String targetCustomer = janBase.getTxtTargetCustomer_P_O_TCustomer().getText();
			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", x, y);
			
			//Create Organization Target Customer
			String organizationTC = janBase.getTxtOrgTC_P_O_TCustomer().getText();
			roleOrgTC = this.createClassifier(organizationTC, "role", x, y);
			
			fix.addAll(outcomeFixer.createGeneralization(rolePersonTC, roleTargetCustomer));				
			fix.addAll(outcomeFixer.createGeneralization(roleOrgTC, roleTargetCustomer));	
		}else if(patternCustomerSelected == 7){// Pattern P-OU-TCustomer
//			//Create Person Target Customer
//			String personTC = janBase.getTxtPersonTC_P_OU_TCustomer().getText();			
//			rolePersonTC  = this.createClassifier(personTC, "role", x, y);
//			
//			//Create Target Customer
//			String targetCustomer = janBase.getTxtTarg;
//			roleTargetCustomer = this.createClassifier(targetCustomer, "RoleMixin", x, y);
//			
//			//Create Organization Target Customer
//			String organizationUnitTC = janBase.getTxtOrgTC_P_O_TCustomer().getText();
//			roleOrgTC = this.createClassifier(organizationUnitTC, "role", x, y);
//			
//			fix.addAll(outcomeFixer.createGeneralization(rolePersonTC, roleTargetCustomer));				
//			fix.addAll(outcomeFixer.createGeneralization(roleOrgTC, roleTargetCustomer));	
		}
		
		// STEP 2 - CRIAR A OFFERING 
		String offering = janBase.getTxtServiceOffering().getText();
		
		relatorOffering = this.createClassifier(offering , "Relator",  x+(verticalDistance/2), y);
		
		providerOffering = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", roleServiceProvider, relatorOffering).getAdded().get(0);
		fix.includeAdded(providerOffering);	

		customerOffering = (Association)outcomeFixer.createAssociationBetween(RelationStereotype.ASSOCIATION, "", relatorOffering, roleTargetCustomer).getAdded().get(0);
		fix.includeAdded(customerOffering);
		
		// STEP 3  - CREATE GENERALIZATIONS AND ASSOCIATIONS

		
		// STEP 4 (OPCIONAL) - CRIAR A SODESCRIPTION
		
		// STEP 5 (OPCIONAL) - CRIAR A SOCOMMITMENT
		
		// STEP 6 (OPCIONAL) - CRIAR A SOCLAIM
		
		
		diagramManager.updateOLED(fix);
		return fix;
		
	}
	
}
