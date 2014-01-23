package br.ufes.inf.nemo.assistant.manager;

import java.util.ArrayList;
import java.util.Random;

public  class ManagerPattern {
	
	protected ArrayList<String> listClass = new ArrayList<>();
	protected ArrayList<String> listStereotype = new ArrayList<>();
	protected ArrayList<String> listGeneralizationSet = new ArrayList<>();
	protected String ontoParser;
	
	protected ManagerOLED managerOLED = new ManagerOLED();
	
	public String getParser(){
		return ontoParser;
	}
	
	public void callback_newClass(String cls, String stereotype) {
		this.listClass.add(cls); 
		this.listStereotype.add(stereotype);

		this.managerOLED.createClass(cls, stereotype);
	}
	
	public void connectGeneralizationSet() {
		managerOLED.connectGeneralizationSet(listClass.get(listClass.size()),listStereotype.get(listStereotype.size()),listClass.get(listClass.size()-1),listStereotype.get(listStereotype.size()-1));		
	}
	
	public void createGeneralizationSet() {
		System.out.println("CREATE A GENERALIZATION SET");		
		listGeneralizationSet.add("GS");
	}

	public void callback_selectClass(String class_Stereotype) {
		//Quebra a String de retorno
		listStereotype.add(class_Stereotype.split(" | ")[0]);
		listClass.add(class_Stereotype.split(" | ")[1]);
	}
	
	public void callback_selectGeneralizationSet(String gs) {
		listGeneralizationSet.add(gs);
	}
	
	public String[] getStringGeneralizationSets() {
		//Pega a Ultima classe e verifica os GneralizationSets dela
		return new String[]{"gs:Tipo de Sexo", "gs:Profissao", "gs:Linha de Produto"};
	}
	
	public String getCurrentStereotype_Class() {
		return listStereotype.get(listStereotype.size())+" | "+listClass.get(listClass.size());
	}

	String[] aux1 = new String[]{"Reino Animal", "Tipo de especie", "gs:Cor de cabelo"};
	String[] aux2 = new String[]{"gs:Tipo de Sexo", "Profissao", "Linha de Produto"};
	public String[] getStringGeneralizationSet(String stereotype_classe) {
		//Consulta o GS da classe informada
		if(stereotype_classe.contains("Category")){
			return aux1;	
		}
		return aux2;
	}

	public boolean[] getStringMetaPropertiesGeneralizationSet(String selectedItem) {
		Random r = new Random();
		boolean[] b = {r.nextBoolean(),r.nextBoolean()};
		return b;
	}

	public void callback_newGeneralizationSet(String stereotype_classe,String generalizationSet, boolean isDisjoint, boolean isComplete) {
		managerOLED.createGeneralizationSet(stereotype_classe,generalizationSet,isDisjoint,isComplete);
		
	}

	public void createNewGeneralizationSet(String newGeneralizationSet) {
		aux1 = new String[]{"Reino Animal", "Tipo de especie", "gs:Cor de cabelo",newGeneralizationSet};
		aux2 = new String[]{"gs:Tipo de Sexo", "Profissao", "Linha de Produto",newGeneralizationSet};
		
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
}
