/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.Classifier;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.PackageableElement;
import RefOntoUML.impl.GeneralizationSetImpl;
import br.ufes.inf.nemo.oled.Main;
import br.ufes.inf.nemo.oled.draw.DiagramElement;
import br.ufes.inf.nemo.oled.umldraw.structure.ClassElement;
import br.ufes.inf.nemo.oled.util.ConfigurationHelper;
import br.ufes.inf.nemo.oled.util.ModelHelper;

/**
 * Class responsable for holding the model instance and its diagrams
 * @author Antognoni Albuquerque
 */
public class UmlProject implements Serializable {

	//The UmlProject is serialized to a binary file in order to store the diagrams and its graphics allElements.
	//The model (contained in a resource) is serialized separately, by its own means so here it is transient.
	//All the model operations like adding, removing or changing allElements are done through a editing domain
	//which provides some useful features, like redo/undo, clipboard etc.
	
	private static final long serialVersionUID = -2356413039446009810L;
	private transient AdapterFactoryEditingDomain editingDomain;
	private transient Resource resource;
	private transient boolean saveNeeded = false;
	public static transient String tempDir;
	private List<UmlDiagram> diagrams = new ArrayList<UmlDiagram>();
	private ArrayList<Integer> openedDiagrams = new ArrayList<Integer>();
	private Properties properties;
	private  Collection<DiagramElement> exclusionDerivationList= new HashSet<DiagramElement>();
	
	public Collection<DiagramElement> getExclusionDerivationList() {
		return exclusionDerivationList;
	}

	public void setExclusionDerivationList(
			Collection<DiagramElement> exclusionDerivationList) {
		this.exclusionDerivationList.addAll(exclusionDerivationList);
	}

	private String name = new String();	
	private String version = new String();
	
	public UmlProject() {
		super();
		properties = new Properties();
		resource = ModelHelper.createResource();
		version = Main.OLED_VERSION;
		RefOntoUML.Package model = ModelHelper.getFactory().createModel();
		if(model.getName()==null || model.getName()=="") model.setName("Model");
		resource.getContents().add(model);		
		getEditingDomain();
		name = "New Project";		
	}

	public String getVersion() {
		return version;
	}
	
	public void setVersion(String version)
	{
		this.version = version;
	}
	public int getVersionAsInt()
	{		
		return Integer.parseInt(version.replaceAll("[.]", ""));
	}
	
	public void clearOpenedDiagrams()
	{
		if(openedDiagrams!=null) openedDiagrams.clear();
		else openedDiagrams = new ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getOpenedDiagrams()
	{
		return openedDiagrams;
	}
	
	public void saveAsOpened(UmlDiagram diagram)
	{
		int index = diagrams.indexOf(diagram);
		if(index>=0) {
			if(openedDiagrams!=null){
				if(!openedDiagrams.contains(index)) openedDiagrams.add(index);
			}
		}
	}
	
	public boolean isAllClosed()
	{
		boolean allClosed = true;
		if (openedDiagrams!=null){
			for(UmlDiagram diagram: diagrams)
			if(openedDiagrams.contains(diagram)) allClosed=false;
		}
		return allClosed;
	}

	public boolean isOpened(UmlDiagram diagram)
	{
		if (openedDiagrams!=null){
			return openedDiagrams.contains(diagrams.indexOf(diagram));
		}
		return false;
	}
	
	public UmlProject(RefOntoUML.Package model) {
		super();
		properties = new Properties();
		resource = ModelHelper.createResource();
		resource.getContents().add(model);		
		getEditingDomain();
		name = "New Project";
	}
	
	public void addDiagram(UmlDiagram diagram) {
		diagrams.add(diagram);
	}

	@Override
	public String toString()
	{
		return "OLED Project";
	}
	
	public void setName (String name)
	{
		this.name = name;
	}
	
	public String getName ()
	{
		return name;
	}
	
	public List<? extends UmlDiagram> getDiagrams() {
		return diagrams;
	}

	public RefOntoUML.Package getModel() {
		return (RefOntoUML.Package) resource.getContents().get(0);
	}

	//CLEANUP
	/*public void addElement(Element element) {	
		AddCommand cmd = new AddCommand(getEditingDomain(), getModel().getPackagedElement(), element);
		getEditingDomain().getCommandStack().execute(cmd);
	}

	public void removeElement(Element element) {		
		//RemoveCommand cmd = new RemoveCommand(getEditingDomain(), getModel().getPackagedElement(), element);
		//getEditingDomain().getCommandStack().execute(cmd);
		
		//EditingDomain editingDomain = AdapterFactoryEditingDomain.getEditingDomainFor(resource);
		
		//EditingDomain editingDomain = getEditingDomain();
		
		//DeleteCommand cmd = (DeleteCommand) DeleteCommand.create(editingDomain, element);
		//editingDomain.getCommandStack().execute(cmd);
	}

	public void changeElement(ChangeDescription changeDescription) {
		//model.getPackagedElement().remove(element);
		ChangeCommand cmd = new ChangeCommand(recorder) {
			
			@Override
			protected void doExecute() {
				// End the change recording.
				ChangeDescription changeDescription = changeRecorder.endRecording();
				
				// Add the result to our resouce.
				resource.getContents().add(changeDescription);
				
				//this.run();
				
			}
		};
		getEditingDomain().getCommandStack().execute(cmd);
		
		// Add the result to our resouce.
		resource.getContents().add(changeDescription);
	}
	
	
	 //Executes the command.
	 
	public void run() {
		EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(eObject);
		if (domain != null) {
			domain.getCommandStack().execute(this);
		}
	}
	 */
	
	public void setResource(Resource resource) {
		this.resource = resource;
	}
	
	public boolean contains(PackageableElement element) {
		return getModel().getPackagedElement().contains(element);
	}

	public Resource getResource() {
		return resource;
	}

	public EList<PackageableElement> getElements() {
		return getModel().getPackagedElement();
	}

	
	public Set<RefOntoUML.Class> getClasses() {
		Set<RefOntoUML.Class> classes = new HashSet<RefOntoUML.Class>();
		for (PackageableElement item : getElements()) {
			if (item instanceof RefOntoUML.Class) {
				classes.add((Class) item);
			}
		}
		return classes;
	}

	public Set<Generalization> getGeneralizations() {
		Set<Generalization> generalizations = new HashSet<Generalization>();
		for (PackageableElement item : getElements()) {
			if (item instanceof RefOntoUML.Class) {
				generalizations.addAll(((RefOntoUML.Class) item)
						.getGeneralization());
			}
		}
		return generalizations;
	}

	public Set<GeneralizationSet> getGeneralizationSets() {
		Set<GeneralizationSet> generalizationSets = new HashSet<GeneralizationSet>();
		for (PackageableElement item : getElements()) {
			if (item instanceof GeneralizationSetImpl) {
				generalizationSets.add((GeneralizationSet) item);
			}
		}
		return generalizationSets;
	}
	
	public Set<Association> getAssociations() {
		Set<Association> associations = new HashSet<Association>();
		for (PackageableElement item : getElements()) {
			if (item instanceof Association) {
				associations.add((Association) item);
			}
		}
		return associations;
	}

	public AdapterFactoryEditingDomain getEditingDomain() {
		if(editingDomain == null)
			editingDomain = ModelHelper.getAdapterEditingDomain();
		return editingDomain;
	}

	public void setSaveModelNeeded(boolean saveNeeded) {
		this.saveNeeded = saveNeeded;		
	}

	public boolean isSaveModelNeeded() {
		return saveNeeded;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	
	public String getTempDir()
	{
		if(tempDir == null)
			tempDir = ConfigurationHelper.makeTempDir();
		
		return tempDir;
	}
	
	public static String createTempDir()
	{
		if(tempDir == null)
			tempDir = ConfigurationHelper.makeTempDir();		
		return tempDir;
	}
}
