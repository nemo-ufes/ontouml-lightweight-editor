package br.ufes.inf.nemo.antipattern.mixiden;

import RefOntoUML.Classifier;

public class SortalToAdd {

	private Classifier sortal, identityProvider;
	private Class<?> sortalStereotype, identityProviderStereotype; 
	String sortalName, identityProviderName;

	public SortalToAdd(Classifier sortal, Classifier identityProvider){
		setSortalFromClassifier(sortal);
		setIdentityProviderFromClassifier(identityProvider);
	}
	
	public SortalToAdd(Classifier sortal, Class<?> sortalStereotype, Classifier identityProvider){
		this.sortal = sortal;
		this.sortalName = sortal.getName();
		this.sortalStereotype = sortalStereotype;
		setIdentityProviderFromClassifier(identityProvider);
	}
	
	public SortalToAdd(Classifier sortal, Class<?> sortalStereotype, Classifier identityProvider, Class<?> identityProviderStereotype){
		this.sortal = sortal;
		this.sortalName = sortal.getName();
		this.sortalStereotype = sortalStereotype;
		this.identityProvider = identityProvider;
		this.identityProviderName = identityProvider.getName();
		this.identityProviderStereotype = identityProviderStereotype;
	}
	
	public SortalToAdd(Classifier sortal, Classifier identityProvider, Class<?> identityProviderStereotype){
		setSortalFromClassifier(sortal);
		this.identityProvider = identityProvider;
		this.identityProviderName = identityProvider.getName();
		this.identityProviderStereotype = identityProviderStereotype;
	}

	public SortalToAdd(String sortalName, Class<?> sortalStereotype, String identityProviderName,  Class<?> identityProviderStereotype){
		setSortalFromName(sortalName, sortalStereotype);
		setIdentityProviderFromName(identityProviderName, identityProviderStereotype);
	}

	public SortalToAdd(String sortalName, Class<?> sortalStereotype, Classifier identityProvider){
		setSortalFromName(sortalName, sortalStereotype);
		setIdentityProviderFromClassifier(identityProvider);
	} 
	
	public SortalToAdd(Classifier sortal, String identityProviderName, Class<?> identityProviderStereotype){
		setSortalFromClassifier(sortal);
		setIdentityProviderFromName(identityProviderName, identityProviderStereotype);
	}
	
	public SortalToAdd(Classifier sortal, Class<?> sortalStereotype, String identityProviderName, Class<?> identityProviderStereotype) {
		this.sortal = sortal;
		this.sortalName = sortal.getName();
		this.sortalStereotype = sortalStereotype;
		setIdentityProviderFromName(identityProviderName, identityProviderStereotype);
	}

	private void setIdentityProviderFromClassifier(Classifier identityProvider) {
		this.identityProvider = identityProvider;
		this.identityProviderName = identityProvider.getName();
		this.identityProviderStereotype = identityProvider.getClass();
	}

	private void setSortalFromClassifier(Classifier sortal) {
		this.sortal = sortal;
		this.sortalName = sortal.getName();
		this.sortalStereotype = sortal.getClass();
	}
	
	private void setIdentityProviderFromName(String identityProviderName, Class<?> identityProviderStereotype) {
		this.identityProvider = null;
		this.identityProviderName = identityProviderName;
		this.identityProviderStereotype = identityProviderStereotype;
	}

	private void setSortalFromName(String sortalName, Class<?> sortalStereotype) {
		this.sortal = null;
		this.sortalName = sortalName;
		this.sortalStereotype = sortalStereotype;
	}

	public Classifier getSortal() {
		return sortal;
	}

	public Classifier getIdentityProvider() {
		return identityProvider;
	}

	public Class<?> getSortalStereotype() {
		return sortalStereotype;
	}

	public Class<?> getIdentityProviderStereotype() {
		return identityProviderStereotype;
	}

	public String getSortalName() {
		return sortalName;
	}

	public String getIdentityProviderName() {
		return identityProviderName;
	}
	
	public boolean newSortal(){
		return sortal==null;
	}
	
	public boolean existingSortal(){
		return !newSortal();
	}
	
	public boolean isSortalStereotypeChange(){
		return !sortalStereotype.equals(sortal.getClass());
	}
	
	public boolean isIdentityProvideStereotypeChange(){
		return !identityProviderStereotype.equals(identityProvider.getClass());
	}
	
	public boolean newIdentityProvider(){
		return identityProvider==null;
	}
	
	public boolean existingIdentityProvider(){
		return !newIdentityProvider();
	}
}
