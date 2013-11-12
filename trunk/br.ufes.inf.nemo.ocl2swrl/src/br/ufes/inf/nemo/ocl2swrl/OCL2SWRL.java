package br.ufes.inf.nemo.ocl2swrl;

import java.util.HashSet;
import java.util.Set;

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

public class OCL2SWRL {
	//public static String log = new String();;		
	//public static Boolean succeeds = false;
	private String nameSpace = null;
	private OCLParser oclParser = null;
	private OntoUMLParser refParser = null;
	private OWLOntologyManager manager = null;
	private OWLDataFactory factory = null;
	private OWLOntology ontology = null;
	
	public OCL2SWRL(OCLParser oclParser, OntoUMLParser refParser, OWLOntologyManager manager, String nameSpace) {
		this.nameSpace = nameSpace;
		this.oclParser = oclParser;
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
		
		if(this.oclParser == null){
			throw new NonInitialized("oclParser");
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
	public void Transformation ()	{
		for(Constraint ct: this.oclParser.getConstraints())
		{
			@SuppressWarnings("unchecked")
			String stereotype = this.oclParser.getUMLReflection().getStereotype(ct);
			if(!org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(stereotype) || !org.eclipse.ocl.utilities.UMLReflection.DERIVATION.equals(stereotype)){
				throw new NonSupported(stereotype);
			}
			//org.eclipse.ocl.utilities.UMLReflection.INVARIANT.equals(stereotype);
			
			ExpressionInOCLImpl expr = (ExpressionInOCLImpl) ct.getSpecification();
			
			ExpressionInOCLImplFactory exprFactory = new ExpressionInOCLImplFactory(expr);
			if(ct.getConstrainedElements().size() > 0){
				exprFactory.setElement(ct.getConstrainedElements().get(0));
			}
			
			Set<SWRLAtom> antecedent = new HashSet<SWRLAtom>();
			Set<SWRLAtom> consequent = new HashSet<SWRLAtom>();
			
			exprFactory.solve(stereotype, refParser, nameSpace, manager, factory, ontology, antecedent, consequent, null, false, false, 1);
		}
	}
	
	
}
