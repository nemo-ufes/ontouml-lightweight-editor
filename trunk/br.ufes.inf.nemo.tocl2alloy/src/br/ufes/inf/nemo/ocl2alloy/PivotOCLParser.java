package br.ufes.inf.nemo.ocl2alloy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.ocl.examples.pivot.Constraint;
import org.eclipse.ocl.examples.pivot.OCL;
import org.eclipse.ocl.examples.pivot.ParserException;
import org.eclipse.ocl.examples.pivot.model.OCLstdlib;
import org.eclipse.ocl.examples.pivot.utilities.PivotEnvironmentFactory;

import br.ufes.inf.nemo.ontouml2ecore.OntoUML2Ecore;

public class PivotOCLParser {
	
	private PivotEnvironmentFactory environmentFactory;
	private OCL ocl;
	private String oclPath;
	private HashMap <RefOntoUML.Element, EModelElement> emap;
	private ArrayList<Constraint> constraintlist = new ArrayList<Constraint>();
	private RefOntoUML.Package refmodel;
	private EPackage ecoremodel;
	
	private void setupOCL(ResourceSet resourceSet)
	{	
		OCLstdlib.install();		
		org.eclipse.ocl.examples.xtext.completeocl.CompleteOCLStandaloneSetup.doSetup();
    	org.eclipse.ocl.examples.xtext.essentialocl.EssentialOCLStandaloneSetup.doSetup();    	
    	org.eclipse.ocl.examples.xtext.oclstdlib.OCLstdlibStandaloneSetup.doSetup();
    	    			
		OCL.initialize(resourceSet);
		org.eclipse.ocl.examples.domain.utilities.StandaloneProjectMap.getAdapter(resourceSet);
		org.eclipse.ocl.examples.pivot.delegate.OCLDelegateDomain.initialize(resourceSet);
	}
	
	/** Constructor */
	public PivotOCLParser (String oclAbsolutePath, String refAbsolutePath) throws IOException
	{	
		String ecorePath = refAbsolutePath.replace(".refontouml",".ecore");
		oclPath = oclAbsolutePath;
		
		Resource refResource = OntoUMLUtil.readOntoUML(refAbsolutePath);
		refmodel = (RefOntoUML.Package)refResource.getContents().get(0);
		
		// convert OntoUML to Ecore
		Resource ecoreResource = OntoUML2Ecore.convertToEcore(refmodel, ecorePath);
	
		// configure OCL from resource set resultant of the conversion to ecore
		setupOCL(ecoreResource.getResourceSet());

		// this map contains the elements mapping from the conversion to ecore
		emap = OntoUML2Ecore.getMap();
		
		// put Ecore in package registry lookup
		ecoremodel = (EPackage) ecoreResource.getContents().get(0);
		ecoreResource.getResourceSet().getPackageRegistry().put(null,ecoremodel);
		
		environmentFactory = new PivotEnvironmentFactory(ecoreResource.getResourceSet().getPackageRegistry(), null);
	    ocl = OCL.newInstance(environmentFactory);	    
   	}	    
		 
	/** Parse constraints */
	public void parse() throws ParserException
	{
		 URI oclURI = URI.createFileURI(oclPath);
		 Resource pivotResource = ocl.parse(oclURI);
		 
		 for(TreeIterator<EObject> tit = pivotResource.getAllContents(); tit.hasNext();)
		 {
			 EObject next = tit.next();	    	
		     if(next instanceof Constraint)
		     {	
		    	 Constraint constraint = (Constraint)next;
		    	 constraintlist.add(constraint);		    	 
		     }
		 }		 
	}	

	/** Get key on Map */
	public RefOntoUML.Element getKey(EModelElement elem) 
    {    	
        for (Entry<RefOntoUML.Element,EModelElement> entry : emap.entrySet()) 
        {
            if (elem.equals(entry.getValue())) 
            {
                return entry.getKey();
            }
        }
        return null;
    }
	
	public HashMap <RefOntoUML.Element, EModelElement> getMap ()
	{
		return emap;
	}
	
	public ArrayList<Constraint> getConstraints()
	{
		return constraintlist;
	}
	
	public RefOntoUML.Package getRefModel()
	{
		return refmodel;
	}
	
	public EPackage getEcoreModel ()
	{
		return ecoremodel;
	}
	
	public OCL getOCLFacade()
	{
		return ocl;
	}
	/** Run a Test */
	public static void main (String[] args)
	{				
		String oclPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.ocl";				
		String refPath = "C:\\Users\\Guerson\\SVN\\OLED-SVN\\br.ufes.inf.nemo.tocl2alloy\\model\\project.refontouml";
				
		try {
			
			PivotOCLParser parser = new PivotOCLParser(oclPath,refPath);
			parser.parse();
			System.out.println("OCL parsed.");
			
		} catch (IOException e) {
			e.printStackTrace();
			
		} catch (ParserException e) {
			e.printStackTrace();
		}
		
		
	}
}
