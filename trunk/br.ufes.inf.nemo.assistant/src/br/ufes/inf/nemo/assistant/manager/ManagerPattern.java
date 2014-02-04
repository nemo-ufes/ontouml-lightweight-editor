package br.ufes.inf.nemo.assistant.manager;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import org.eclipse.jface.wizard.IWizardPage;

import RefOntoUML.GeneralizationSet;
import br.ufes.inf.nemo.common.ontoumlfixer.Fix;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer;
import br.ufes.inf.nemo.common.ontoumlfixer.OutcomeFixer.ClassStereotype;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class ManagerPattern {

	private ArrayList<String> listClass = new ArrayList<>();
	private ArrayList<String> listStereotype = new ArrayList<>();
	private ArrayList<String> listGeneralizationSet = new ArrayList<>();

	private OntoUMLParser ontoParser;
	private OutcomeFixer of;
	private Fix fix = new Fix();

	private ManagerOLED managerOLED = new ManagerOLED();

	public Fix getFix(){
		return fix;
	}

	public void setOntoPaser(OntoUMLParser ontoParser) {
		this.ontoParser = ontoParser;
		of = new OutcomeFixer(ontoParser.getModel());
	}

	public void callback_newClass(String className, String stereotype) {
		RefOntoUML.Classifier classifier =  (RefOntoUML.Classifier) of.createClass(ClassStereotype.valueOf(stereotype.toUpperCase()));
		classifier.setName(className);
		fix.includeAdded(classifier);
		System.out.println("Callback_newClass.fix: "+fix.getAddedString());
	}

	/**
	 * Return a String array with all generalizationSets with the stereotype has
	 * a general of the generalization
	 * */
	public String[] getStringGeneralizationSet(String stereotype) {
		Set<GeneralizationSet> genSets = ontoParser.getAllInstances(GeneralizationSet.class);
		Iterator<GeneralizationSet> iterator = genSets.iterator();

		ArrayList<String> genSetList = new ArrayList<String>();

		while(iterator.hasNext()){
			GeneralizationSet genSet = iterator.next();
			String genSetName = genSet.getName();
			if(genSetName == null || genSetName.equals("")){
				genSetName = "unnamed";
			}
		}
		return (String[]) genSetList.toArray();
	}

	/**
	 * Return the metaproperties for the generalizationset with the name genSetName
	 * */

	public boolean[] getStringMetaPropertiesGeneralizationSet(String genSetName) {
		Set<GeneralizationSet> genSets = ontoParser.getAllInstances(GeneralizationSet.class);
		Iterator<GeneralizationSet> iterator = genSets.iterator();
		boolean metaProperties[] = new boolean[2];
		while(iterator.hasNext()){
			GeneralizationSet genSet = iterator.next();
			if(genSet.getName().equals(genSetName)){
				metaProperties[0] = genSet.isIsDisjoint();
				metaProperties[1] = genSet.isIsCovering();	
			}
		}
		return metaProperties;
	}

	public void callback_newGeneralizationSet(String stereotype_classe,String generalizationSet, boolean isDisjoint, boolean isComplete) {
		managerOLED.createGeneralizationSet(stereotype_classe,generalizationSet,isDisjoint,isComplete);

	}

	public void createNewGeneralizationSet(String newGeneralizationSet) {


	}

	public void callback_selectClass(String class_Stereotype) {
		//Quebra a String de retorno
		listStereotype.add(class_Stereotype.split(" | ")[0]);
		listClass.add(class_Stereotype.split(" | ")[1]);
	}

	public void callback_selectGeneralizationSet(String gs) {
		listGeneralizationSet.add(gs);
	}

	public boolean existSomeMixinUniversal(){
		//!this.parser.getExistSomeMixinUniversal().isEmpty();
		System.out.println("existSomeMixinUniversal");
		return true;
	}

	public void connectLastClasses() {
		System.out.println("General: <"+listStereotype.get(listStereotype.size()-1)+"> "+listClass.get(listClass.size()-1)+" Specific: <"+listStereotype.get(listStereotype.size()-2)+"> "+listClass.get(listClass.size()-2));
	}

	public String[] getGeneralClasses(String[] stereotypes) {
		/*Para cada um dos stereotypes informados:
		 * - Procurar nos que existem
		 * - retornar todos 
		 * */

		String [] ret = new String[]{"<Kind> | Pessoa", "<Collective> | Sala de aula","<Quanity> | Cachaca", "<Subkind> | Homem"};
		return ret;
	}

	public String getCurrentClass(){
		return listClass.get(listClass.size()-1);
	}

	public void callback_newPhases(ArrayList<String> phases, ArrayList<String> rules){
		for (String s : phases) {
			listClass.add(s);
			listStereotype.add("Phase");
		}

	}

	public String[] getGeneralClasses(String[] split, String filter) {
		/*
		 * Verifica o filtro e só traz os que estiverem de acordo com ele e com os tipos informados
		 * */
		String [] ret = new String[]{"<Kind> | Pessoa", "<Kind> | Time", "<Role> | Estudante"};
		return ret;
	}

	public boolean existSomeUltimateSortal() {
		System.out.println("existSomeUltimateSortal");
		return true;
	}

	public void connectLastClasses(String filter) {
		if(filter.equalsIgnoreCase("_allLastClasses")){
			System.out.println("General: <"+listStereotype.get(listStereotype.size()-1)+"> "+listClass.get(listClass.size()-1)+" Specific: ");
			for (int i = 0; i < listClass.size()-1; i++) {
				System.out.println("<"+listStereotype.get(i)+"> "+listClass.get(i));
			}
		}
	}
	
	public void acabou(){
		
		
	}

	public void run(IWizardPage currentPage) {
		System.out.println("running for: "+currentPage.getName());
		
	}
	
}
