package br.ufes.inf.nemo.ontouml2uml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.uml2.uml.NamedElement;
import org.eclipse.uml2.uml.Profile;
import org.eclipse.uml2.uml.Stereotype;
import org.eclipse.uml2.uml.UMLPackage;
import org.eclipse.uml2.uml.resource.UMLResource;
import org.eclipse.uml2.uml.resources.util.UMLResourcesUtil;

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
import br.ufes.inf.nemo.common.file.FileUtil;

public class ProfileAplicator {

	//uml2 profile
	private URI uml2baseURI;
	
	//ontouml profile
	private String profilePath = new String();
	private ResourceSet profileRSet;
	private Resource profileResource;
	private Profile uprofile;	
	private ArrayList<Stereotype> stereotypes = new ArrayList<Stereotype>();
	
	//model	
	private ResourceSet modelRSet;
	private Resource modelResource;
	private org.eclipse.uml2.uml.Package umlRoot;
	private String umlPath = new String();
	
	//mapping		
	private HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap;
	public String log = new String();	
			
	public ProfileAplicator(org.eclipse.uml2.uml.Package root, String umlPath, HashMap<RefOntoUML.Element,org.eclipse.uml2.uml.Element> umap)  
    {
		this.umap = umap;
		this.umlRoot = root;
		this.umlPath = umlPath;		
		this.uml2baseURI = loadUML2();
		extractProfile();						
		loadProfile();		
		createModel();
    }
	
	public URI loadUML2()
	{		
		URL umlProfileUrl = ClassLoader.getSystemResource("profiles/UML2.profile.uml");
		if(umlProfileUrl == null){
			try {				
				umlProfileUrl = new URL("rsrc:"+"org.eclipse.uml2.uml.resources_4.1.0.v20130902-0826.jar");
			} catch (MalformedURLException e) {			
				e.printStackTrace();
			}			
		}				
		if(umlProfileUrl == null) throw new RuntimeException("Could not load 'profiles/UML2.profile.uml' from class path");		
		final String path = umlProfileUrl.toExternalForm().split("!")[0] + "!/";
		
		if(path!=null) log += "UML2 profile loaded at: "+path+"\n";
		
		return URI.createURI(path);						
	}
	
	public void createModel()
	{
		URI uri = URI.createFileURI(umlPath);				
		modelRSet = new ResourceSetImpl();				
		//local registry
		UMLResourcesUtil.init(modelRSet);
		modelRSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);
		modelRSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		//create resource
		modelResource = modelRSet.createResource(uri);		
		//add and load root
		modelResource.getContents().add(umlRoot);
	
		//uml2 registry
		final Map<URI,URI> uriModelMap = modelRSet.getURIConverter().getURIMap();
		uriModelMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uml2baseURI.appendSegment("libraries").appendSegment(""));
		uriModelMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uml2baseURI.appendSegment("metamodels").appendSegment(""));
		uriModelMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uml2baseURI.appendSegment("profiles").appendSegment(""));
		
		if(modelResource!=null)  log += "UML Model created in: "+modelResource+"\n";
	}

	public void resolveURIs()
	{		
		try{
			URI profileURI = EcoreUtil.getURI(uprofile);
			URI epackageURI = EcoreUtil.getURI((((EAnnotation)uprofile.eContents().get(0)).getContents().get(0)));
			String umlTextContent = FileUtil.readFile(umlPath);
			String newUmlTextContent = new String();
			newUmlTextContent = umlTextContent;
			Pattern p = Pattern.compile("appliedProfile href=\"(.)*\"");
			Matcher m = p.matcher(umlTextContent);
	    	while (m.find()) 
	    	{ 
	    		int indexBegin = m.start();
	    		int indexEnd = m.end();
	    		String piece = umlTextContent.substring(indexBegin,indexEnd);
	    		newUmlTextContent = newUmlTextContent.replace(piece,"appliedProfile hef=\""+profileURI.toString()+"\"");
	    	}
	    	p = Pattern.compile("<references xmi:type=\"ecore:EPackage\" href=\"(.)*\"");
	    	m = p.matcher(newUmlTextContent);
	    	while (m.find()) 
	    	{ 
	    		int indexBegin = m.start()+44;
	    		int indexEnd = m.end()-1;
	    		String piece = umlTextContent.substring(indexBegin,indexEnd);
	    		newUmlTextContent = newUmlTextContent.replaceAll(piece,epackageURI.toString());
	    	}
	    	
	    	FileUtil.writeToFile(newUmlTextContent, umlPath);
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void loadProfile()
	{
		profileRSet = new ResourceSetImpl();

		//local registry
		UMLResourcesUtil.init(profileRSet);
		profileRSet.getPackageRegistry().put(UMLPackage.eNS_URI, UMLPackage.eINSTANCE);		
		profileRSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(UMLResource.FILE_EXTENSION, UMLResource.Factory.INSTANCE);
		
		//create resource
		uprofile = null;		
		File file = new File(profilePath);
		URI ontoProfileUri = URI.createFileURI(file.getAbsolutePath());        
        if(ontoProfileUri==null) throw new RuntimeException("Could not load 'profile/OntoUML.uml' from class path");
        profileResource = profileRSet.createResource(ontoProfileUri);    				
                
		// load
		try {
			profileResource.load(null);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Could not load profile - resource.load()");
		}
		
		//uml2 registry
		final Map<URI,URI> uriMap = profileRSet.getURIConverter().getURIMap();
		uriMap.put(URI.createURI(UMLResource.LIBRARIES_PATHMAP), uml2baseURI.appendSegment("libraries").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.METAMODELS_PATHMAP), uml2baseURI.appendSegment("metamodels").appendSegment(""));
		uriMap.put(URI.createURI(UMLResource.PROFILES_PATHMAP), uml2baseURI.appendSegment("profiles").appendSegment(""));
				
		uprofile = (Profile)EcoreUtil.getObjectByType(profileResource.getContents(), UMLPackage.Literals.PROFILE);
		if(uprofile == null) throw new RuntimeException("Could not load the profile container from resource");					
		stereotypes.addAll(uprofile.getOwnedStereotypes());
		
		if(ontoProfileUri!=null) log += "OntoUML profile loaded at: "+ontoProfileUri+"\n";
	}
	
	public void apply()
	{	
		umlRoot.applyProfile(uprofile);	
		
		log += "Transforming the pure UML model into a UML Profile..."+"\n";
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
		modelResource.save(Collections.emptyMap());
		return modelResource;
	}

	/** Extract the OntoUML profile to the specified path */
	public String extractProfile() 
	{
		try {
			profilePath = umlPath.substring(0,umlPath.lastIndexOf(File.separator))+File.separator+"OntoUML.uml";
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
			
			if(profilePath!=null) log += "OntoUML profile extracted to: "+profileFile.getAbsolutePath()+"\n";
			return profileFile.getAbsolutePath();
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}		
		return null;			
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
