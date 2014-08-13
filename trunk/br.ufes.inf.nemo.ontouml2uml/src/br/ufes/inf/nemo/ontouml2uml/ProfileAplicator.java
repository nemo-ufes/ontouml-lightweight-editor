package br.ufes.inf.nemo.ontouml2uml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ContentHandler;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;

import RefOntoUML.Category;
import RefOntoUML.Characterization;
import RefOntoUML.Collective;
import RefOntoUML.Derivation;
import RefOntoUML.FormalAssociation;
import RefOntoUML.Kind;
import RefOntoUML.MaterialAssociation;
import RefOntoUML.Mediation;
import RefOntoUML.Mixin;
import RefOntoUML.Mode;
import RefOntoUML.Phase;
import RefOntoUML.Quality;
import RefOntoUML.Quantity;
import RefOntoUML.Relator;
import RefOntoUML.Role;
import RefOntoUML.RoleMixin;
import RefOntoUML.SubKind;
import RefOntoUML.componentOf;
import RefOntoUML.memberOf;
import RefOntoUML.subCollectionOf;
import RefOntoUML.subQuantityOf;

public class ProfileAplicator {
		
	//pure UML	
	private ResourceSet modelRSet = new ResourceSetImpl();
	private Resource modelResource;
	private org.eclipse.uml2.uml.Package umlRoot;
	
	//profile UML
	private ResourceSet profileRSet = new ResourceSetImpl();;
	private Resource profileResource;
	private Profile uprofile;	
	private ArrayList<Stereotype> stereotypes = new ArrayList<Stereotype>();
	
	//mapping		
	private HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap;
	public String log = new String();
			
	public ProfileAplicator(org.eclipse.uml2.uml.Package root, String umlPath, HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap)  
    {
		this.umap = umap;
		this.umlRoot = root;

		//prepare the resource set of the UML model to be generated
		URI uri = URI.createURI(umlPath);		
		//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap( ).put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE); 
		modelRSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		modelRSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);		
		modelResource = modelRSet.createResource(uri);		
		if(modelResource==null) modelRSet.createResource(uri, ContentHandler.UNSPECIFIED_CONTENT_TYPE);
		modelResource.getContents().add(umlRoot);
		
		//prepare the resource set of the OntoUML profile to be loaded		
		//Resource.Factory.Registry.INSTANCE.getExtensionToFactoryMap( ).put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE); 
		profileRSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);		
		profileRSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		URL umlProfileUrl = ClassLoader.getSystemResource("profiles/UML2.profile.uml");
		if(umlProfileUrl == null){
			try {
				umlProfileUrl = new URL("rsrc:"+"org.eclipse.uml2.uml.resources_3.1.1.v201008191505.jar");
			} catch (MalformedURLException e) {			
				e.printStackTrace();
			}
			if(umlProfileUrl == null) throw new RuntimeException("Could not load 'profiles/UML2.profile.uml' from class path");
		}
		final String path = umlProfileUrl.toExternalForm().split("!")[0] + "!/";		  
		final URI baseURI = URI.createURI(path);
		final Map<URI,URI> uriMap = profileRSet.getURIConverter().getURIMap();
		uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), baseURI.appendSegment("libraries").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), baseURI.appendSegment("metamodels").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), baseURI.appendSegment("profiles").appendSegment(""));
		
		//load OntoUML profile 
		uprofile = null;		
		URL ontoProfileUrl = null;
		URI ontoProfileUri  = null;
		try{
        	ontoProfileUrl = new URL("rsrc:"+"OntoUML.uml");        	        	
        	URI ontobaseURI = URI.createURI(ontoProfileUrl.toExternalForm());        	        	   		  
        	profileResource = profileRSet.createResource(ontobaseURI);        	
		}catch(MalformedURLException e){			
			String dir = System.getProperty("user.dir");
			if (dir.contains("br.ufes.inf.nemo.oled")) dir = dir.replace("br.ufes.inf.nemo.oled","br.ufes.inf.nemo.ontouml2uml").concat(File.separator).concat("profile"+File.separator);
			else dir = ProfileAplicator.class.getProtectionDomain().getCodeSource().getLocation().getPath();			
        	File file = new File(dir.concat("OntoUML.uml"));
        	ontoProfileUri = URI.createFileURI(file.getAbsolutePath());	        	
        	profileResource = profileRSet.createResource(ontoProfileUri);
    	}	
		if(ontoProfileUrl == null && ontoProfileUri==null) throw new RuntimeException("Could not load 'profiles/OntoUML.uml' from class path");
		try {
			profileResource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not load profile - resource.load()");
		}
		uprofile = (Profile)EcoreUtil.getObjectByType(profileResource.getContents(), UMLPackage.Literals.PROFILE);
		if(uprofile == null) {
				throw new RuntimeException("Could not load the profile container from resource");
		}			
		stereotypes.addAll(uprofile.getOwnedStereotypes());
    }
	
	public void apply()
	{	
		umlRoot.applyProfile(uprofile);	
		
		log += "Transforming the pure UML model into a UML Profile...";
		for(RefOntoUML.Element pe: umap.keySet())
		{
			if(pe instanceof Kind) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Kind"));						
			if(pe instanceof SubKind) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("SubKind"));
			if(pe instanceof Collective) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Collective"));
			if(pe instanceof Quantity) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Quantity"));
			if(pe instanceof Phase) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Phase"));
			if(pe instanceof Role) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Role"));
			if(pe instanceof Category) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Category"));
			if(pe instanceof Mixin) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Mixin"));
			if(pe instanceof Relator) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Relator"));
			if(pe instanceof RoleMixin) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("RoleMixin"));
			if(pe instanceof Mode) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Mode"));
			if(pe instanceof Quality) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Quality"));
			if(pe instanceof MaterialAssociation) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Material"));
			if(pe instanceof FormalAssociation) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Formal"));
			if(pe instanceof Characterization) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Characterization"));
			if(pe instanceof Mediation) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Mediation"));
			if(pe instanceof componentOf) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("componentOf"));
			if(pe instanceof subCollectionOf) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("subcollectionOf"));
			if(pe instanceof subQuantityOf) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("subquantityOf"));
			if(pe instanceof memberOf) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("memberOf"));
			if(pe instanceof Derivation) applyStereotype(((NamedElement)umap.get(pe)),getStereotype("Derivation"));
		}
		log += "UML Profile sucessfully generated";		
	}
	
	public Resource save() throws IOException
	{	
		//save model to its resource
		modelResource.save(null);
		return modelResource;
	}

	/** Extract the OntoUML profile to the specified path */
	public String extractOntoUMLProfile(String profilePath) throws IOException
	{
		//Made these changes to correct an error while copying the alloy file to folders with space " " in the path.
		profilePath = URLDecoder.decode(profilePath, "UTF-8");		
		File profileFile = new File(profilePath);
		if (profileFile.exists()) return profileFile.getAbsolutePath();				
		// Copy "OntoUML.uml" 
		InputStream is = this.getClass().getClassLoader().getResourceAsStream("OntoUML.uml");		
		if(is == null) is = new FileInputStream("profile/"+"OntoUML.uml");		
		OutputStream out = new FileOutputStream(profileFile);				
		// copy data flow -> MB x MB
		byte[] src = new byte[1024];
		int read = 0;
		while ((read = is.read(src)) != -1){
			out.write(src, 0, read);
		}
		is.close();
		out.flush();
		out.close();				
		return profileFile.getAbsolutePath();			
	}
	
	public void applyStereotype(NamedElement namedElement, Stereotype stereo)
	{
		namedElement.applyStereotype(stereo);
		log += "UML Stereotype: <<"+stereo.getName()+">> applied to \""+namedElement.getName()+"\"\n";		
	}
	
	public Stereotype getStereotype(String name)
	{
		for(Stereotype pe: stereotypes){
			if(pe.getName().trim().toLowerCase().equals(name.trim().toLowerCase())){
				return pe;
			}
		}
		return null;
	}
	
	public String getLog() { return log; }
}
