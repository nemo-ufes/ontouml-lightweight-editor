package newversion;

import java.io.ByteArrayOutputStream;
import java.text.Normalizer;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.util.EList;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import RefOntoUML.Class;
import RefOntoUML.Generalization;
import RefOntoUML.GeneralizationSet;
import RefOntoUML.Model;
import RefOntoUML.Phase;
import RefOntoUML.Property;
import RefOntoUML.RefOntoUMLFactory;
import RefOntoUML.Type;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

public class NEW_Transformer {	

	//Global Variables
	private Set<Class> lstOntClass;
	private Set<GeneralizationSet> lstGenSets;
	private OntoUMLParser ontoParser;

	//OWL
	private String nameSpace;

	//OWL API
	private OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
	private OWLOntology ontology;
	private OWLDataFactory factory;

	/**
	 * Initialize the Transformer
	 * */
	public NEW_Transformer(String nameSpace) {
		this.nameSpace = nameSpace+"#";

		try {
			this.ontology = manager.createOntology(IRI.create(nameSpace));
			this.factory = manager.getOWLDataFactory();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}	
	}

	/**
	 * Used to clean up the string with the owl.
	 * Sometimes wildcards can do a mistake in the code.
	 * */
	private String processSpecialCharacter(String owl) {
		owl = Normalizer.normalize(owl, Normalizer.Form.NFD);
		owl = owl.replaceAll("[^\\p{ASCII}]", "");
		return owl;
	}

	/**
	 * This method make a unique for to create a string name for an OWLClass
	 * */
	private String getName(RefOntoUML.Classifier ontCls){
		return nameSpace+ontCls.getName().replaceAll(" ", "_");
	}

	/**
	 * This method make a unique for to create a string name for an Type
	 * */
	private String getName(RefOntoUML.Type ontType){
		return ontType.getName().replaceAll(" ", "_");
	}

	/**
	 * This method make a unique for to create a IRI name for an OWLClass
	 * */
	private IRI getIRIName(RefOntoUML.Classifier ontCls){
		return IRI.create(nameSpace+ontCls.getName().replaceAll(" ", "_"));
	}

	/**
	 * Create a unique name for DataProperty
	 * */
	private String getDataPropertyName(RefOntoUML.Classifier ontCls, RefOntoUML.Type ontType){
		return nameSpace + getName(ontCls)+"."+getName(ontType);
	}

	/**
	 * Initialize some variables used in many methods 
	 * */
	private void init(RefOntoUML.Model model) {
		ontoParser = new OntoUMLParser(model);
		lstOntClass = ontoParser.getAllInstances(RefOntoUML.Class.class);
		lstGenSets = ontoParser.getAllInstances(GeneralizationSet.class);
	}


	private OWLClass getOwlClass(RefOntoUML.Class ontCls){
		return factory.getOWLClass(IRI.create(ontCls.getName().replaceAll(" ", "_")));
	}
	
	
	/**
	 * Transform a RefOntoUML.Model to OWL
	 * 
	 * @param ecoreModel
	 * @return a String with the OWL code
	 */
	public String transform(RefOntoUML.Model ecoreModel) {
		init(ecoreModel);

		processClass();
		processDataType();//FALTA FAZER OS ESTRUTURADOS
		processGeneralizationSet();

		try {	
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			manager.saveOntology(ontology, os);
			//String s = new String(os.toByteArray(),"ISO-8859-1");
			String owl = new String(os.toByteArray(),"UTF-8");
			//Process special characters
			owl = processSpecialCharacter(owl);
			return owl;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * Create all GeneralizationSets
	 * */
	private void processGeneralizationSet() {
		for(GeneralizationSet gen : lstGenSets){
			if(!gen.getGeneralization().isEmpty()){
				if((gen.isIsDisjoint() && gen.isIsCovering()) || gen.getGeneralization().get(0).getSpecific() instanceof Phase){
					//{disjoint, complete} or is a Phase Partition
					processGeneralizationDisjointCovering(gen.getGeneralization(), true, true);
				}else if(gen.isIsDisjoint()){
					//{disjoint}
					processGeneralizationDisjoint(gen.getGeneralization(), true, false);
				}else if(gen.isIsCovering()){
					//{complete}
					processGeneralizationCovering(gen.getGeneralization(), false, true);
				}else{
					//{}
					processGeneralization(gen.getGeneralization(), false, false);
				}		
			}
		}
	}
	
	/**
	 * This method is called by the processGeneralizationSet setted with his boolean parameter disjoint and complete
	 * @param 
	 */
	private void processGeneralization(EList<Generalization> genSet) {
		Set<OWLClass> lst = new HashSet<OWLClass>();
		OWLClass general = factory.getOWLClass(getIRIName(genSet.get(0).getGeneral()));
	}


	/*
	 * Process the simple DataTypes (as class attributes), DataTypes in class (class with
	 * stereotype DataType) and DataType structured (DataType that has other DataTypes).
	 * */
	private void processDataType() {
		for(RefOntoUML.Class ontCls: lstOntClass){
			//has some attribute
			if(!ontCls.getAttribute().isEmpty()){
				createAttributes(ontCls);
			}
		}
	}

	/**
	 * Used to create the Class attributes
	 * */
	private void createAttributes(Class ontCls) {
		OWLDatatype tipoAtributo = null;
		OWLDataProperty atributo = null;
		OWLClass owlCls = null;
		for(Property prop:ontCls.getAttribute()){
			//If the type of this property isn't in the model.
			if(prop.getType() == null){
				//Than create a generic type for this property (RDFS_LITERAL)
				tipoAtributo = factory.getOWLDatatype(OWL2Datatype.RDFS_LITERAL.getIRI());
			}else{
				tipoAtributo = getDataTypeRange(prop.getType());
				if(tipoAtributo == null){
					//Isn't a simple DataType
					//TODO
				}
				//Is a simple DataType
				atributo = factory.getOWLDataProperty(IRI.create(getDataPropertyName(ontCls, prop.getType())));

				//Set the Range of the DataProperty
				OWLDataPropertyRangeAxiom axRange = factory.getOWLDataPropertyRangeAxiom(atributo, tipoAtributo);
				manager.applyChange(new AddAxiom(ontology, axRange));

				//set the owner of this datatype (Domain)
				owlCls = getOwlClass(ontCls);
				OWLDataPropertyDomainAxiom axDomain = factory.getOWLDataPropertyDomainAxiom(atributo, owlCls);
				manager.applyChange(new AddAxiom(ontology, axDomain));
			}

			//Solving the cardinality of the attribute			
			int upperCard = prop.getUpper();
			int lowerCard = prop.getLower();			

			OWLEquivalentClassesAxiom ax = null;
			if(upperCard == 1 && lowerCard == 1){
				//EXACTLY
				OWLClassExpression oecr = factory.getOWLDataExactCardinality(1, atributo, tipoAtributo);
				ax = factory.getOWLEquivalentClassesAxiom(owlCls, oecr);
			}else if(upperCard == -1 && lowerCard == 1){
				//SOME
				OWLDataMinCardinality mincard = factory.getOWLDataMinCardinality(1, atributo,tipoAtributo);
				ax = factory.getOWLEquivalentClassesAxiom(owlCls, mincard);
			}else if (upperCard > 0 && lowerCard == 0){
				//MAX
				//TODO FIXME
			}else if(upperCard > 0 && lowerCard > 0){
				//MIN MAX
				OWLDataMinCardinality mincard = factory.getOWLDataMinCardinality(lowerCard, atributo,tipoAtributo);
				OWLDataMaxCardinality maxcard = factory.getOWLDataMaxCardinality(upperCard, atributo,tipoAtributo);
				OWLObjectIntersectionOf oio =  factory.getOWLObjectIntersectionOf(maxcard,mincard);
				ax = factory.getOWLEquivalentClassesAxiom(owlCls, oio);
			}
			manager.applyChange(new AddAxiom(ontology, ax));
		}
	}

	/**
	 * Get the range of this type.
	 * The rages supported are: unsigned_int, int, unsigned_byte, 
	 * double, string, normalized_string, boolean, hex_binary, 
	 * short, byte, unsigned_long or null if doesn't have some match
	 * */
	private OWLDatatype getDataTypeRange(Type type) {
		String range = getName(type);
		if (range.equalsIgnoreCase("unsigned_int")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_UNSIGNED_INT.getIRI());
		}else if(range.equalsIgnoreCase("int") || range.equalsIgnoreCase("integer")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_INTEGER.getIRI());
		}else if(range.equalsIgnoreCase("unsigned_byte")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_UNSIGNED_BYTE.getIRI());
		}else if(range.equalsIgnoreCase("double")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_DOUBLE.getIRI());
		}else if(range.equalsIgnoreCase("string")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
		}else if(range.equalsIgnoreCase("normalized_string")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_NORMALIZED_STRING.getIRI());
		}else if(range.equalsIgnoreCase("boolean")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_BOOLEAN.getIRI());
		}else if(range.equalsIgnoreCase("hex_binary")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_HEX_BINARY.getIRI());
		}else if(range.equalsIgnoreCase("short")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_SHORT.getIRI());
		}else if(range.equalsIgnoreCase("byte")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_BYTE.getIRI());
		}else if(range.equalsIgnoreCase("unsigned_long")){
			return factory.getOWLDatatype(OWL2Datatype.XSD_UNSIGNED_LONG.getIRI());
		}
		return null;
	}

	/**
	 * Process all OntoUML class.
	 * This method is the funnel between OntoUML and OWL.
	 * It create a OWLClass for each RefOntoUML.Class
	 * */
	private void processClass() {
		for(RefOntoUML.Class ontCls: lstOntClass){
			OWLClass owlCls = getOwlClass(ontCls);
			OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(owlCls);
			manager.addAxiom(ontology, declarationAxiom);
		}
	}


}