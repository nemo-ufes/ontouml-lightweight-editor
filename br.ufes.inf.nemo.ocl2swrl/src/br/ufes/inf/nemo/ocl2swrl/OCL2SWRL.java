package br.ufes.inf.nemo.ocl2swrl;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.ocl.ParserException;
import org.eclipse.ocl.uml.impl.ExpressionInOCLImpl;
import org.eclipse.uml2.uml.Constraint;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.SWRLAtom;

import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;
import br.ufes.inf.nemo.ocl.parser.OCLParser;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonInitialized;
import br.ufes.inf.nemo.ocl2swrl.exceptions.NonSupported;
import br.ufes.inf.nemo.ocl2swrl.factory.ocl.uml.impl.ExpressionInOCLImplFactory;
import br.ufes.inf.nemo.ocl2swrl.tags.Tag;

public class OCL2SWRL {
	//public static String log = new String();;		
	//public static Boolean succeeds = false;
	private String nameSpace = null;
	//private OCLParser oclParser = null;
	private String oclRules = null;
	private OntoUMLParser refParser = null;
	private OWLOntologyManager manager = null;
	private OWLDataFactory factory = null;
	private OWLOntology ontology = null;
	public String errors = "";
	
	//public OCL2SWRL(OCLParser oclParser, OntoUMLParser refParser, OWLOntologyManager manager, String nameSpace) {
	public OCL2SWRL(String oclRules, OntoUMLParser refParser, OWLOntologyManager manager, String nameSpace) {	
		this.nameSpace = nameSpace;
		//this.oclParser = oclParser;
		this.oclRules = oclRules;
		this.refParser = refParser;
		this.manager = manager;
		this.factory = manager.getOWLDataFactory();
		this.ontology = manager.getOntology(IRI.create(nameSpace.substring(0, nameSpace.length()-1)));
		
		this.verifyVariablesInitialization();
	}
	
	@SuppressWarnings("unused")
	private OCL2SWRL() {
		//this constructor is private to force the use of the specific constructor
	}
	
	//this function verifies if all necessary attributes were initialized
	private void verifyVariablesInitialization(){
		if(this.nameSpace == null){
			throw new NonInitialized("nameSpace");
		}else if(this.nameSpace.equals("")){
			throw new NonInitialized("nameSpace");
		}
		
		/*if(this.oclParser == null){
			throw new NonInitialized("oclParser");
		}*/
		
		if(this.oclRules == null){
			throw new NonInitialized("oclRules");
		}else if(this.oclRules.equals("")){
			throw new NonInitialized("oclRules");
		}
		
		if(this.refParser == null){
			throw new NonInitialized("refParser");
		}
		
		if(this.manager == null){
			throw new NonInitialized("manager");
		}
		
		if(this.factory == null){
			throw new NonInitialized("factory");
		}
		
		if(this.ontology == null){
			throw new NonInitialized("ontology");
		}
	}
	
	//this main function transform OCL constraints in SWRL rules
	@SuppressWarnings("unchecked")
	public void Transformation () throws ParserException, Exception{
		/*
		OCLParser oclParser = null;
		try {
			oclParser = new OCLParser(oclRules, this.refParser, "company.uml");
		} catch (ParserException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		int iPackage = oclRules.indexOf("package");
		int endLinePackage = oclRules.indexOf("\n", iPackage);
		String strPackage = oclRules.substring(iPackage, endLinePackage);
		oclRules = oclRules.replace(strPackage, "");
		/*		
		int iEndPackage = oclRules.indexOf("endpackage");
		int endLineEndPackage = iEndPackage+10;//oclRules.indexOf("\n", iEndPackage);
		
		String strEndPackage = oclRules.substring(iEndPackage, endLineEndPackage);
		*/
		oclRules = oclRules.replace("endpackage", "");
		/*
		int firstTag = oclRules.indexOf("--@");
		int i = oclRules.indexOf("--@");
		int j = oclRules.indexOf("\n", i);
		String tag = oclRules.substring(i+3, j);
		System.out.println();
		*/
		int blockBegin = 0;
		int blockEnd = oclRules.indexOf("--@");
		
		while(true){
			if(blockEnd < 0){
				blockEnd = oclRules.length();
				if(blockBegin == blockEnd){
					break;
				}
			}
			int endOfTag = oclRules.indexOf("\n", blockBegin);
			String tag = oclRules.substring(blockBegin, endOfTag);
			tag = tag.replace("--@", "");
			tag = tag.replace("\n", "");
			tag = tag.replace("\r", "");
			String strBlockOclConstraints = oclRules.substring(endOfTag, blockEnd);
			
			blockBegin = blockEnd;
			blockEnd = oclRules.indexOf("--@", blockBegin+3);
			
			//OCLParser oclParser = null;
			//try {
			OCLParser oclParser = new OCLParser(strBlockOclConstraints, this.refParser, "temp.uml");
			/* catch (ParserException e) {
				//e.printStackTrace();
				return e.getMessage();
			} catch (Exception e) {
				//e.printStackTrace();
				return e.getMessage();
			}*/
			
			for(Constraint ct: oclParser.getConstraints())
			{
				String stereotype = "";
				if(!tag.equals("")){
					stereotype = tag;
				}else{
					stereotype = oclParser.getUMLReflection().getStereotype(ct);
				}
				
				if(stereotypeIsUnsupported(stereotype)){
					throw new NonSupported(stereotype);
				}else{
					ExpressionInOCLImpl expr = (ExpressionInOCLImpl) ct.getSpecification();
					
					ExpressionInOCLImplFactory exprFactory = new ExpressionInOCLImplFactory(expr);
					if(ct.getConstrainedElements().size() > 0){
						exprFactory.setElement(ct.getConstrainedElements().get(0));
					}
					
					Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
					Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
					
					exprFactory.solve(stereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, false, 1, false);
				}
				//org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(stereotype);
								
			}
		}
	}
	
	public Boolean stereotypeIsUnsupported(String stereotype){
		Boolean isTag = true;
		try {
			@SuppressWarnings("unused")
			Tag tag = Tag.valueOf(stereotype);
		} catch (Exception e) {
			isTag = false;
		}
		
		if(isTag){
			return false;
		}
		
		if(!org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(stereotype) && !org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(stereotype)){
			return true;
		}
		return false;
	}
}
