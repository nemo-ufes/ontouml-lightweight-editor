package br.ufes.inf.nemo.ocl2owl_swrl.parser;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLNamedObject;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.SWRLArgument;
import org.semanticweb.owlapi.model.SWRLAtom;
import org.semanticweb.owlapi.model.SWRLDArgument;
import org.semanticweb.owlapi.model.SWRLIArgument;
import org.semanticweb.owlapi.model.SWRLLiteralArgument;
import org.semanticweb.owlapi.model.SWRLPredicate;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.model.SWRLVariable;
import org.semanticweb.owlapi.vocab.XSDVocabulary;

import uk.ac.manchester.cs.owl.owlapi.OWLObjectComplementOfImpl;
import uk.ac.manchester.cs.owl.owlapi.SWRLVariableImpl;

//TODO This is basically a copy of the Protege-OWL parser that works with the OWLAPI. Need to rewrite this using JavaCC to make it more extensible.

public class SWRLParser
{
	public final static char AND_CHAR = '\u2227'; // ^
	public final static char IMP_CHAR = '\u2192'; // >
	public final static char RING_CHAR = '\u02da'; // .
	public final static String delimiters = " ?\n\t()[],\"'" + AND_CHAR + IMP_CHAR + RING_CHAR; // Note space.

	private final OWLOntology owlOntology;
	private final OWLDataFactory dataFactory;
	private final Set<String> variables;
	private boolean parseOnly;
	private Tokenizer tokenizer;
	private boolean inHead = false;
	private String nameSpace = "";

	public static final Set<String> ALL_DATATYPES;

	static {
		ALL_DATATYPES = new HashSet<String>();
		for (XSDVocabulary v : XSDVocabulary.values()) {
			ALL_DATATYPES.add(v.getShortName());
		}
	}

	private final OWLDatatype anyURIDatatype, stringDatatype, integerDatatype, floatDatatype, booleanDatatype;

	public SWRLParser(OWLOntology owlOntology, OWLDataFactory dataFactory, String nameSpace)
	{
		this.owlOntology = owlOntology;
		this.dataFactory = dataFactory;
		this.parseOnly = true;
		this.variables = new HashSet<String>();

		this.anyURIDatatype = getOWLDataFactory().getOWLDatatype(XSDVocabulary.ANY_URI.getIRI());
		this.stringDatatype = getOWLDataFactory().getOWLDatatype(XSDVocabulary.STRING.getIRI());
		this.booleanDatatype = getOWLDataFactory().getBooleanOWLDatatype();
		this.integerDatatype = getOWLDataFactory().getIntegerOWLDatatype();
		this.floatDatatype = getOWLDataFactory().getFloatOWLDatatype();
		
		this.nameSpace = nameSpace;
	}

	public void setParseOnly(boolean parseOnly)
	{
		this.parseOnly = parseOnly;
	}

	/**
	 * This parser will throw a {@link SWRLParseException} if it finds errors in the supplied rule. If the rule is correct but incomplete, a
	 * {@link SWRLIncompleteRuleExceptionP (which is a subclass of {@link SWRLParseException}) will be thrown.
	 * 
	 * If {@link #parseOnly} is true, only checking is performed - no SWRL rules are created; if it is false, individuals are created.
	 */
	public SWRLRule parse(String rule) throws SWRLParseException
	{
		return parse(rule, null);
	}
	
	public SWRLRule parse(SWRLRule rule) throws Exception
	{
		String ruleTxt = getRuleString(rule);
		return parse(ruleTxt, null);
	}
	
	public SWRLRule parse(String ruleText, SWRLRule rule) throws SWRLParseException
	{
		List<SWRLAtom> head = null;
		List<SWRLAtom> body = null;
		SWRLAtom atom = null;
		boolean atLeastOneAtom = false, justProcessedAtom = true;
		String token, message;

		this.inHead = false;

		this.variables.clear();
		this.tokenizer = new Tokenizer(ruleText.trim());

		if (!this.parseOnly) {
			head = new ArrayList<SWRLAtom>();
			body = new ArrayList<SWRLAtom>();
		}

		if (!this.parseOnly && !getTokenizer().hasMoreTokens())
			throw new SWRLParseException("Empty rule.");

		do {
			if (justProcessedAtom) {
				if (this.inHead)
					message = "Expecting " + AND_CHAR;
				else
					message = "Expecting " + IMP_CHAR + " or " + AND_CHAR + " or " + RING_CHAR + ".";
			} else {
				if (this.inHead)
					message = "Expecting atom.";
				else
					message = "Expecting atom or " + IMP_CHAR + ".";
			}

			token = getNextNonSpaceToken(message);

			if (token.equals("" + IMP_CHAR) || token.equals("->")) { // A rule can have an empty body.
				if (this.inHead)
					throw new SWRLParseException("Second occurence of " + IMP_CHAR + ".");
				this.inHead = true;
				justProcessedAtom = false;
			} else if (token.equals("-")) {
				continue; // Ignore "->" while we build up IMP_CHAR.
			} else if (token.equals("" + AND_CHAR) || token.equals("^")) {
				if (!justProcessedAtom)
					throw new SWRLParseException(AND_CHAR + " may occur only after an atom.");
				justProcessedAtom = false;
			} else if (token.equals("" + RING_CHAR) || token.equals(".")) {
				if (this.inHead || !justProcessedAtom)
					throw new SWRLParseException(RING_CHAR + " may occur only in query body.");
				justProcessedAtom = false;
			} else {
				atom = parseAtom(token);
				atLeastOneAtom = true;
				if (!this.parseOnly) {
					if (this.inHead)
						head.add(atom);
					else
						body.add(atom);
				}
				justProcessedAtom = true;
			}
		} while (getTokenizer().hasMoreTokens());

		if (!this.parseOnly) {
			if (!atLeastOneAtom)
				throw new SWRLParseException("Incomplete rule - no antecedent or consequent.");
			rule = getOWLDataFactory().getSWRLRule(new HashSet<SWRLAtom>(head), new HashSet<SWRLAtom>(body));
		}

		return rule;
	}

	/**
	 * If the rule is correct and incomplete return 'true'; if the rule has errors or is correct and complete, return 'false'.
	 */
	public boolean isCorrectAndIncomplete(String rule)
	{
		boolean oldParseOnly = this.parseOnly;
		boolean result = false;

		setParseOnly(true);

		try {
			parse(rule);
		} catch (SWRLParseException e) {
			if (e instanceof SWRLIncompleteRuleException)
				result = true;
		}

		setParseOnly(oldParseOnly);

		return result;
	}

	private SWRLAtom parseAtom(String identifier) throws SWRLParseException
	{
		checkAndSkipToken("(", "Expecting parameters enclosed in parentheses for atom " + identifier + ".");

		if (isSameAs(identifier))
			return parseSameAsAtomParameters();
		else if (isDifferentFrom(identifier))
			return parseDifferentFromAtomParameters();
		else if (isOWLClassName(identifier))
			return parseClassAtomParameters(identifier);
		else if (isOWLObjectPropertyName(identifier))
			return parseSWRLObjectPropertyAtomParameters(identifier);
		else if (isOWLDataPropertyName(identifier))
			return parseSWRLDataPropertyAtomParameters(identifier);
		else if (isSWRLBuiltinName(identifier))
			return parseBuiltinParameters(identifier);
		else
			throw new SWRLParseException("Invalid atom name " + identifier + ".");
	}

	private void checkAndSkipToken(String skipToken, String unexpectedTokenMessage) throws SWRLParseException
	{
		String token = getNextNonSpaceToken(unexpectedTokenMessage);

		if (!token.equalsIgnoreCase(skipToken))
			throw new SWRLParseException("Expecting " + skipToken + ", got " + token + "; " + unexpectedTokenMessage);
	}

	// TODO: Does not deal with escaped quotation characters.
	private String getNextStringToken(String noTokenMessage) throws SWRLParseException
	{
		String token = "";
		String errorMessage = "Incomplete rule. " + noTokenMessage;

		if (!getTokenizer().hasMoreTokens()) {
			if (this.parseOnly)
				throw new SWRLIncompleteRuleException(errorMessage);
			else
				throw new SWRLParseException(errorMessage);
		}

		while (getTokenizer().hasMoreTokens()) {
			token = getTokenizer().nextToken("\"");
			if (token.equals("\""))
				token = ""; // Empty string
			else
				checkAndSkipToken("\"", "Expected \" to close string.");
			return token;
		}

		if (this.parseOnly)
			throw new SWRLIncompleteRuleException(errorMessage);
		else
			throw new SWRLParseException(errorMessage); // Should not get here
	}

	private String getNextNonSpaceToken(String noTokenMessage) throws SWRLParseException
	{
		String errorMessage = "Incomplete rule. " + noTokenMessage;
		String token = "";

		if (!getTokenizer().hasMoreTokens()) {
			if (this.parseOnly)
				throw new SWRLIncompleteRuleException(errorMessage);
			else
				throw new SWRLParseException(errorMessage);
		}

		while (getTokenizer().hasMoreTokens()) {
			token = getTokenizer().nextToken();
			if (!(token.equals(" ") || token.equals("\n") || token.equals("\t")))
				return token;
		}

		if (this.parseOnly)
			throw new SWRLIncompleteRuleException(errorMessage);
		else
			throw new SWRLParseException(errorMessage); // Should not get here
	}

	private SWRLAtom parseSameAsAtomParameters() throws SWRLParseException
	{
		SWRLIArgument iObject1 = parseIObject();
		checkAndSkipToken(",", "Expecting comma-separated second parameter for SWRL same as atom.");
		SWRLIArgument iObject2 = parseIObject();
		SWRLAtom atom = null;

		if (!this.parseOnly)
			atom = getOWLDataFactory().getSWRLSameIndividualAtom(iObject1, iObject2);

		checkAndSkipToken(")", "Expecting closing parenthesis after second parameters in SWRL same as atom");

		return atom;
	}

	private SWRLAtom parseDifferentFromAtomParameters() throws SWRLParseException
	{
		SWRLIArgument iObject1 = parseIObject();
		checkAndSkipToken(",", "Expecting comma-separated second parameters for SWRL different from atom");
		SWRLIArgument iObject2 = parseIObject();
		SWRLAtom atom = null;

		if (!this.parseOnly)
			atom = getOWLDataFactory().getSWRLDifferentIndividualsAtom(iObject1, iObject2);

		checkAndSkipToken(")", "Only two parameters allowed for SWRL different from atom");

		return atom;
	}

	private SWRLAtom parseClassAtomParameters(String identifier) throws SWRLParseException
	{
		SWRLIArgument iObject = parseIObject();
		SWRLAtom atom = null;

		if (!this.parseOnly) {
			OWLClass aClass = getOWLClassFromName(identifier);
			atom = getOWLDataFactory().getSWRLClassAtom(aClass, iObject);
		}

		checkAndSkipToken(")", "Expecting closing parenthesis for parameter for SWRL class atom '" + identifier + "'.");

		return atom;
	}

	private SWRLAtom parseSWRLObjectPropertyAtomParameters(String identifier) throws SWRLParseException
	{
		SWRLIArgument iObject1 = parseIObject();
		checkAndSkipToken(",", "Expecting comma-separated second parameter for SWRL object property atom '" + identifier + "'");
		SWRLIArgument iObject2 = parseIObject();
		SWRLAtom atom = null;

		if (!this.parseOnly) {
			OWLObjectProperty objectProperty = getOWLObjectPropertyFromName(identifier);
			if (objectProperty == null)
				throw new SWRLParseException("no datatype property found for IndividualPropertyAtom: " + identifier);
			atom = getOWLDataFactory().getSWRLObjectPropertyAtom(objectProperty, iObject1, iObject2);
		}

		checkAndSkipToken(")", "Expecting closing parenthesis after second parameter of SWRL object property atom '" + identifier + "'.");

		return atom;
	}

	private SWRLAtom parseSWRLDataPropertyAtomParameters(String identifier) throws SWRLParseException
	{
		String token, errorMessage = "Expecting literal qualification symbol '#' or closing parenthesis after second parameter of data property atom' ";
		OWLDataProperty dataProperty;
		SWRLAtom atom = null;

		SWRLIArgument iObject = parseIObject();
		checkAndSkipToken(",", "Expecting comma-separated second parameter for data property atom '" + identifier + "'.");
		SWRLDArgument dObject = parseDObject();

		token = getNextNonSpaceToken(errorMessage + identifier + "'.");

		if (token.equals("#")) { // Literal qualification.
			if (dObject instanceof SWRLLiteralArgument) {
				SWRLLiteralArgument literalArgument = (SWRLLiteralArgument) dObject;
				String datatypePrefixedName = getNextNonSpaceToken("Expecting XML Schema datatype.");
				if (getTokenizer().hasMoreTokens() && !isXSDDatatype(datatypePrefixedName))
					throw new SWRLParseException("Invalid XML Schema datatype name '" + datatypePrefixedName + "'.");
				if (!this.parseOnly) {
					OWLDatatype datatype = getOWLDataFactory().getOWLDatatype(prefixedName2IRI(datatypePrefixedName));
					OWLLiteral literal = getOWLDataFactory().getOWLLiteral(literalArgument.getLiteral().getLiteral(), datatype);
					dObject = getOWLDataFactory().getSWRLLiteralArgument(literal);
				}
				checkAndSkipToken(")", "Expecting closing parenthesis after second parameter of data property atom");
			} else
				throw new SWRLParseException("Cannot use literal qualification symbol '#' after variable.");
		} else if (!token.equals(")"))
			throw new SWRLParseException(errorMessage + identifier + "'.");

		if (!this.parseOnly) {
			dataProperty = getOWLDataPropertyFromName(identifier);
			atom = getOWLDataFactory().getSWRLDataPropertyAtom(dataProperty, iObject, dObject);
		}

		return atom;
	}

	private SWRLAtom parseBuiltinParameters(String identifier) throws SWRLParseException
	{
		List<SWRLDArgument> objects = parseDObjectList(); // Swallows ')'
		SWRLAtom atom = null;

		if (!this.parseOnly)
			atom = getOWLDataFactory().getSWRLBuiltInAtom(prefixedName2IRI(identifier), objects);

		return atom;
	}

	// Parse a list of variables and literals.
	private List<SWRLDArgument> parseDObjectList() throws SWRLParseException
	{
		SWRLDArgument dObject;
		List<SWRLDArgument> dObjects = new ArrayList<SWRLDArgument>();

		if (!this.parseOnly)
			dObjects = new ArrayList<SWRLDArgument>();

		dObject = parseDObject();
		if (!this.parseOnly)
			dObjects.add(dObject);

		String token = getNextNonSpaceToken("Expecting additional comma-separated variables or literals or closing parenthesis.");
		while (token.equals(",")) {
			dObject = parseDObject();
			if (!this.parseOnly)
				dObjects.add(dObject);
			token = getNextNonSpaceToken("Expecting ',' or ')'.");
			if (!(token.equals(",") || token.equals(")")))
				throw new SWRLParseException("Expecting ',' or ')', got '" + token + "'.");
		}

		return dObjects;
	}

	/**
	 * Parse a variable, literal, or an OWL entity name (which will be encoded as an OWL literal with an xsd:anyURI datatype)
	 */
	@SuppressWarnings("unused")
	private SWRLArgument parseObject() throws SWRLParseException
	{
		String parsedString = getNextNonSpaceToken("Expecting variable or individual name or literal.");
		SWRLArgument parsedEntity = null;

		if (parsedString.equals("?"))
			parsedEntity = parseVariable();
		else { // The entity is an individual name or literal
			if (isValidIndividualName(parsedString)) {
				if (!this.parseOnly) {
					OWLIndividual individual = getOWLIndividualFromName(parsedString);
					parsedEntity = getOWLDataFactory().getSWRLIndividualArgument(individual);
				}
			} else if (isValidOWLClassName(parsedString) || isValidOWLIndividualName(parsedString) || isValidOWLObjectPropertyName(parsedString)
					|| isValidOWLDataPropertyName(parsedString) || isValidOWLAnnotationPropertyName(parsedString)) {
				if (!this.parseOnly) {
					OWLLiteral literal = getOWLDataFactory().getOWLLiteral(parsedString, this.anyURIDatatype);
					parsedEntity = getOWLDataFactory().getSWRLLiteralArgument(literal);
				}
			} else
				parsedEntity = parseLiteral(parsedString);
		}
		return parsedEntity;
	}

	/**
	 * Parse a variable or an individual name.
	 */
	private SWRLIArgument parseIObject() throws SWRLParseException
	{
		String parsedString = getNextNonSpaceToken("Expecting variable or individual name.");
		SWRLIArgument parsedEntity = null;

		if (parsedString.equals("?"))
			parsedEntity = parseVariable();
		else { // The entity is an
			if (isValidIndividualName(parsedString)) {
				if (!this.parseOnly) {
					OWLIndividual individual = getOWLIndividualFromName(parsedString);
					parsedEntity = getOWLDataFactory().getSWRLIndividualArgument(individual);
				}
			} else if (getTokenizer().hasMoreTokens())
				throw new SWRLParseException("Invalid individual name: '" + parsedString + "'.");
		}
		return parsedEntity;
	}

	/**
	 * Parse a variable or a literal.
	 */
	private SWRLDArgument parseDObject() throws SWRLParseException
	{
		String parsedString = getNextNonSpaceToken("Expecting variable or literal.");

		if (parsedString.equals("?"))
			return parseVariable();
		else
			return parseLiteral(parsedString);
	}

	private SWRLVariable parseVariable() throws SWRLParseException
	{
		String variableName = getNextNonSpaceToken("Expected variable name");
		checkThatVariableNameIsValid(variableName);
		SWRLVariable parsedEntity = null;

		if (getTokenizer().hasMoreTokens()) {
			if (!this.inHead)
				this.variables.add(variableName);
			else if (!this.variables.contains(variableName))
				throw new SWRLParseException("Variable ?" + variableName + " referred to in consequent is not present in antecedent.");
		}

		if (!this.parseOnly)
			parsedEntity = getSWRLVariable(variableName);

		return parsedEntity;
	}

	private SWRLLiteralArgument parseLiteral(String parsedString) throws SWRLParseException
	{
		SWRLLiteralArgument parsedEntity = null;

		if (parsedString.equals("\"")) { // The parsed entity is a string
			String stringValue = getNextStringToken("Expected a string.");
			if (!this.parseOnly) {
				OWLLiteral literal = getOWLDataFactory().getOWLLiteral(stringValue, this.stringDatatype);
				parsedEntity = getOWLDataFactory().getSWRLLiteralArgument(literal);
			}
		}
		// According to the XSD specification, xsd:boolean's have the lexical space: {true, false, 1, 0}. We don't allow {1, 0} since these are parsed
		// as xsd:ints.
		else if (parsedString.startsWith("t") || parsedString.startsWith("T") || parsedString.startsWith("f") || parsedString.startsWith("F")) {
			if (getTokenizer().hasMoreTokens()) {
				if (parsedString.equalsIgnoreCase("true") || parsedString.equalsIgnoreCase("false")) {
					if (!this.parseOnly) {
						OWLLiteral literal = getOWLDataFactory().getOWLLiteral(parsedString, this.booleanDatatype);
						parsedEntity = getOWLDataFactory().getSWRLLiteralArgument(literal);
					}
				} else
					throw new SWRLParseException("Invalid literal " + parsedString + ".");
			}
		} else { // Is it an integer, float, long or double then?
			try {
				if (parsedString.contains(".")) {
					Float.parseFloat(parsedString); // Check it
					if (!this.parseOnly) {
						OWLLiteral literal = getOWLDataFactory().getOWLLiteral(parsedString, this.floatDatatype);
						parsedEntity = getOWLDataFactory().getSWRLLiteralArgument(literal);
					}
				} else {
					Integer.parseInt(parsedString); // Check it
					if (!this.parseOnly) {
						OWLLiteral literal = getOWLDataFactory().getOWLLiteral(parsedString, this.integerDatatype);
						parsedEntity = getOWLDataFactory().getSWRLLiteralArgument(literal);
					}
				}
			} catch (NumberFormatException e) {
				String errorMessage = "Invalid literal " + parsedString + ".";
				if (this.parseOnly)
					throw new SWRLIncompleteRuleException(errorMessage);
				else
					throw new SWRLParseException(errorMessage);
			}
		}
		return parsedEntity;
	}

	private boolean isSameAs(String identifier) throws SWRLParseException
	{
		return identifier.equalsIgnoreCase("sameAs");
	}

	private boolean isDifferentFrom(String identifier) throws SWRLParseException
	{
		return identifier.equalsIgnoreCase("differentFrom");
	}

	private boolean isXSDDatatype(String identifier) throws SWRLParseException
	{
		return (identifier.startsWith("xsd:") && ALL_DATATYPES.contains(identifier.substring(4)));
	}

	private void checkThatIdentifierIsValid(String identifier) throws SWRLParseException
	{
		if (!isValidIdentifier(identifier))
			throw new SWRLParseException("Invalid identifier " + identifier + ".");
	}

	// Possible valid identifiers include 'http://swrl.stanford.edu/ontolgies/built-ins/3.3/swrlx.owl#createIndividual'.
	private boolean isValidIdentifier(String s)
	{
		if (s.length() == 0)
			return false;

		if (!Character.isJavaIdentifierStart(s.charAt(0)) && s.charAt(0) != ':')
			return false; // HACK to deal with ":TO" and ":FROM".

		for (int i = 1; i < s.length(); i++) {
			char c = s.charAt(i);
			if (!(Character.isJavaIdentifierPart(c) || c == ':' || c == '-' || c == '#' || c == '/' || c == '.')) {
				return false;
			}
		}
		return true;
	}

	private void checkThatVariableNameIsValid(String variableName) throws SWRLParseException
	{
		checkThatIdentifierIsValid(variableName);
	}

	private boolean isOWLClassName(String identifier) throws SWRLParseException
	{
		return getOWLClassFromName(identifier) != null;
	}

	private boolean isOWLObjectPropertyName(String identifier) throws SWRLParseException
	{
		return getOWLObjectPropertyFromName(identifier) != null;
	}

	private boolean isOWLDataPropertyName(String identifier) throws SWRLParseException
	{
		return getOWLDataPropertyFromName(identifier) != null;
	}

	private boolean isSWRLBuiltinName(String identifier) throws SWRLParseException
	{
		@SuppressWarnings("unused")
		IRI iri = prefixedName2IRI(identifier);

		throw new RuntimeException("isSWRLBuiltinName not implemented"); // TODO OWLAPI only understands core SWRL built-ins at the moment.
	}

	private IRI prefixedName2IRI(String prefixedName)
	{
		IRI iri = IRI.create(nameSpace+prefixedName);
		return iri;
		//throw new RuntimeException("prefixedName2IRI not implemented");
	}

	private boolean isValidIndividualName(String name) throws SWRLParseException
	{
		try {
			return getOWLIndividualFromName(name) != null;
		} catch (Throwable t) {
			return false;
		}
	}

	private boolean isValidOWLClassName(String name) throws SWRLParseException
	{
		return getOWLOntology().containsClassInSignature(prefixedName2IRI(name), true);
	}

	private boolean isValidOWLObjectPropertyName(String name) throws SWRLParseException
	{
		return getOWLOntology().containsObjectPropertyInSignature(prefixedName2IRI(name), true);
	}

	private boolean isValidOWLDataPropertyName(String name) throws SWRLParseException
	{
		return getOWLOntology().containsDataPropertyInSignature(prefixedName2IRI(name), true);
	}

	private boolean isValidOWLAnnotationPropertyName(String name) throws SWRLParseException
	{
		return getOWLOntology().containsAnnotationPropertyInSignature(prefixedName2IRI(name), true);
	}

	private boolean isValidOWLIndividualName(String name) throws SWRLParseException
	{
		return getOWLOntology().containsIndividualInSignature(prefixedName2IRI(name), true);
	}

	private SWRLVariable getSWRLVariable(String name) throws SWRLParseException
	{
		IRI iri = IRI.create(name);

		return getOWLDataFactory().getSWRLVariable(iri);
	}

	private OWLDataFactory getOWLDataFactory()
	{
		return this.dataFactory;
	}

	private OWLOntology getOWLOntology()
	{
		return this.owlOntology;
	}

	private OWLClass getOWLClassFromName(String identifier)
	{
		return getOWLDataFactory().getOWLClass(prefixedName2IRI(identifier));
	}

	private OWLNamedIndividual getOWLIndividualFromName(String identifier)
	{
		return getOWLDataFactory().getOWLNamedIndividual(prefixedName2IRI(identifier));
	}

	private OWLObjectProperty getOWLObjectPropertyFromName(String identifier)
	{
		return getOWLDataFactory().getOWLObjectProperty(prefixedName2IRI(identifier));
	}

	private OWLDataProperty getOWLDataPropertyFromName(String identifier)
	{
		return getOWLDataFactory().getOWLDataProperty(prefixedName2IRI(identifier));
	}

	private Tokenizer getTokenizer()
	{
		return this.tokenizer;
	}

	private static class Tokenizer
	{
		private final StringTokenizer internalTokenizer;

		public Tokenizer(String input)
		{
			this.internalTokenizer = new StringTokenizer(input, delimiters, true);
		}

		public boolean hasMoreTokens()
		{
			return this.internalTokenizer.hasMoreTokens();
		}

		public String nextToken(String myDelimiters)
		{
			return this.internalTokenizer.nextToken(myDelimiters);
		}

		public String nextToken() throws NoSuchElementException
		{
			String token = this.internalTokenizer.nextToken(delimiters);
			if (!token.equals("'"))
				return token;

			StringBuffer buffer = new StringBuffer();
			while (this.internalTokenizer.hasMoreTokens() && !(token = this.internalTokenizer.nextToken()).equals("'")) {
				buffer.append(token);
			}
			return buffer.toString();
		}
	}
	
	public static String getRuleString(SWRLRule rule) throws Exception{
		Set<SWRLAtom> antecedent = rule.getBody();
		Set<SWRLAtom> consequent = rule.getHead();
		
		String ruleTxt = "";
		
		ruleTxt += getAtomsString(antecedent);
		ruleTxt += "->";
		ruleTxt += getAtomsString(consequent);
		return ruleTxt;
	}
	
	public static String getAtomsString(Set<SWRLAtom> atoms) throws Exception{
		String ruleTxt = "";
		for(SWRLAtom atom : atoms) {
			
			SWRLPredicate predicate = atom.getPredicate();
			String property = "";
			if(predicate instanceof OWLNamedObject){
				IRI predicateIri = ((OWLNamedObject) predicate).getIRI();
				property = predicateIri.getFragment();
			}else if(predicate instanceof IRI){
				property = ((IRI) predicate).toString();
			}else if(predicate instanceof OWLObjectComplementOfImpl){
				OWLClassExpression operand = ((OWLObjectComplementOfImpl) predicate).getOperand();
				IRI predicateIri = ((OWLNamedObject)operand).getIRI();
				property = predicateIri.getFragment();
				
				ruleTxt += "(not ";
			}else{
				System.out.println();
			}
			
			if(property.equalsIgnoreCase("SameAs")){
				property = "SameAs";
			}else if(property.equalsIgnoreCase("DifferentFrom")){
				property = "DifferentFrom";
			}
			ruleTxt += property;
			
			if(predicate instanceof OWLObjectComplementOfImpl){
				ruleTxt += ")";
			}
			
			ruleTxt += "(";
			
			Collection<SWRLArgument> args = atom.getAllArguments();
			for (SWRLArgument swrlArgument : args) {
				String varName = ((SWRLVariableImpl)swrlArgument).getIRI().getFragment();
				ruleTxt += "?" + varName + ",";
			}
			int lastCommon = ruleTxt.lastIndexOf(",");
			ruleTxt = ruleTxt.substring(0, lastCommon);
			ruleTxt += "),";
		}
		int lastCommon = ruleTxt.lastIndexOf(",");
		ruleTxt = ruleTxt.substring(0, lastCommon);
		
		return ruleTxt;
	}
}
