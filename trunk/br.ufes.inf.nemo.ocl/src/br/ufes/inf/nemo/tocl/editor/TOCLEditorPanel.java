package br.ufes.inf.nemo.tocl.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentListener;

import org.eclipse.emf.ecore.EObject;
import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import RefOntoUML.Association;
import RefOntoUML.Class;
import RefOntoUML.DataType;
import RefOntoUML.Enumeration;
import RefOntoUML.PrimitiveType;
import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * @author John Guerson
 */

public class TOCLEditorPanel extends JPanel {

	private static final long serialVersionUID = 1277358682337723759L;
	
	public Component parent;
	public RSyntaxTextArea textArea;
	public TOCLSyntaxHighlight oclSyntaxHighlight;	
	public CompletionProvider parentProvider;	
	public AutoCompletion ac;
	public RTextScrollPane scrollPane;
	public DefaultCompletionProvider provider;
	public JMenuItem saveMenuItem;
	public JMenuItem openMenuItem;
	public JMenuItem parserMenuItem;
	public ArrayList<TOCLTemplateCompletion> modelCompletionList = new ArrayList<TOCLTemplateCompletion>();
	public ArrayList<TOCLTemplateCompletion> oclCompletionList = new ArrayList<TOCLTemplateCompletion>();
	
   /**
    * Constructor
    * 
    * @param frame
    */
   public TOCLEditorPanel(Component parent)
   {
	   this();
	   
	   this.parent = parent;
   }
 
   public RSyntaxTextArea getTextArea(){
	   return textArea;
   }
   
   public JPopupMenu getPopupMenu()
   {
	   return textArea.getPopupMenu();
   }
   
   /**
    * Constructor.
    * @param refparser
    */
   public void addCompletions(OntoUMLParser refparser)
   {   
	   for(RefOntoUML.Class oc: refparser.getAllInstances(RefOntoUML.Class.class))
	   {
		   addCompletion(oc);
	   }
	   for(RefOntoUML.DataType oc: refparser.getAllInstances(RefOntoUML.DataType.class))
	   {
		   if ((!(oc instanceof PrimitiveType)) && (!(oc instanceof Enumeration))){
			   addCompletion(oc);
		   }
	   }
	   for(RefOntoUML.Association p: refparser.getAllInstances(RefOntoUML.Association.class))
	   {
		   addCompletion(p);
	   }
   }
   
   public void addCompletion(RefOntoUML.Association p)
   {
	   for(Property pp: p.getMemberEnd()){
		   addCompletion(pp);
	   }
   }   
   
   public String getStereotype(EObject element)
   {
		String type = element.getClass().toString().replaceAll("class RefOntoUML.impl.","");
	    type = type.replaceAll("Impl","");
	    type = Normalizer.normalize(type, Normalizer.Form.NFD);
	    if (!type.equalsIgnoreCase("association")) type = type.replace("Association","");
	    return type;
   }
   
   public void addCompletion(RefOntoUML.Property p)
   {
	   if ((p.getName()!=null)&&!(p.getName().isEmpty())&&(p.getType()!=null))
	   {		   		   
		   String description = new String();
		   String owner = new String();
		   String multiplicity = new String();
		   
		   if (p.getAssociation()!=null) 
			   owner=getStereotype(p.getAssociation())+" "+p.getAssociation().getName();
		   else if (p.getAssociation()==null && p.getOwner()!=null)
			   owner = getStereotype(p.getOwner())+" "+((RefOntoUML.NamedElement)p.getOwner()).getName();
		   else
			   owner = getStereotype(p.eContainer())+" "+((RefOntoUML.NamedElement)p.eContainer()).getName();
		   
		   if (p.getLower()==p.getUpper() && p.getUpper()!=-1) multiplicity = Integer.toString(p.getLower());
			else if (p.getLower()==p.getUpper() && p.getUpper()==-1) multiplicity = "*";
			else if (p.getUpper()==-1) multiplicity = p.getLower()+".."+"*";
			else multiplicity = p.getLower()+".."+p.getUpper();
		   
		   description = "<b>Property "+p.getName()+": "+p.getType().getName()+" ["+multiplicity+"] </b><br><br>" +
		   	"Owner: "+owner;
		   
		   TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
				p.getName(),p.toString().substring(0,p.toString().indexOf(" ")),
				"_'"+p.getName()+"'",
				p.getType().getName()+" ["+multiplicity+"]"
				,description);		
		    provider.addCompletion(c);
		    modelCompletionList.add(c);
	   }	   
   }   
   
   public void addCompletion(RefOntoUML.Type oc)
   {	   
	   String description = new String();
	   
	   description = "<b>"+getStereotype(oc)+" "+oc.getName()+"</b>";
			   
	   TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			oc.getName(),oc.toString().substring(0,oc.toString().indexOf(" ")),
			"_'"+oc.getName()+"'",
			null,
			description);		
	    provider.addCompletion(c);
	    modelCompletionList.add(c);
	    
	    if (oc instanceof RefOntoUML.Class){
	    	for(Property p: ((RefOntoUML.Class)oc).getOwnedAttribute()) addCompletion(p);	
	    }
	    if (oc instanceof RefOntoUML.DataType){
	    	for(Property p: ((RefOntoUML.DataType)oc).getOwnedAttribute()) addCompletion(p);	
	    }	    
   }
   
   public void removeCompletion(EObject elem)
   {
	   // Class and DataTypes
	   if (elem instanceof RefOntoUML.Class || elem instanceof RefOntoUML.DataType) 
	   {
		   	removeEntry(elem);
		   	//Attributes
			if (elem instanceof Class){ for(Property p: ((Class)elem).getOwnedAttribute()) removeEntry(p); }
			if (elem instanceof DataType){ for(Property p: ((DataType)elem).getOwnedAttribute()) removeEntry(p); }
	   }
	   // Associations
	   else if (elem instanceof RefOntoUML.Association)
	   {
			//Properties			
			for(Property p: ((Association)elem).getMemberEnd()) { removeEntry(p); }
	   }
	   // Properties
	   else if (elem instanceof RefOntoUML.Property) 
	   {
		   removeEntry(elem);
	   }
   }
   
   @SuppressWarnings("rawtypes")
   private void removeEntry(EObject elem)
   {	   
	   Iterator it = modelCompletionList.iterator();
	   while(it.hasNext())
	   {
		   TOCLTemplateCompletion tc = (TOCLTemplateCompletion)it.next();
		   if (tc.getDefinitionString().equals(elem.toString().substring(0,elem.toString().indexOf(" "))))
		   {
			   it.remove();
			   provider.removeCompletion(tc);
		   }
	   }	   
   }
   
   public void updateCompletion(EObject elem)
   {
	   removeCompletion(elem);
	   if (elem instanceof RefOntoUML.Class || elem instanceof RefOntoUML.DataType) addCompletion((RefOntoUML.Type)elem);
	   else if (elem instanceof RefOntoUML.Association) addCompletion((RefOntoUML.Association)elem);
	   else if (elem instanceof RefOntoUML.Property) addCompletion((RefOntoUML.Property)elem);
   }
   
   public void createAutoComplete (CompletionProvider provider)
   {
	    ac = new AutoCompletion(provider);	    
	    ac.setListCellRenderer(new TOCLCellRenderer());	    
		ac.setParameterAssistanceEnabled(true);
	    
	    // Allow the completion popup to appear automatically, no need for ctrl+space.
	    ac.setAutoActivationEnabled(false);
	    // Milliseconds before popup.  Most editors have a small delay.
	    //ac.setAutoActivationDelay(100);
	    
	    ac.setAutoCompleteSingleChoices(false);
     	ac.setShowDescWindow(true);     	
     	ac.install(textArea);
   }
   
	/**
	 * Constructor.
	 */
	public TOCLEditorPanel ()
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		textArea = new RSyntaxTextArea(5, 30);		
	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
	    textArea.setAntiAliasingEnabled(true);
	    textArea.setCodeFoldingEnabled(false);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(new Color(255, 255, 255));				
		setTheme(textArea,"/br/ufes/inf/nemo/ocl/editor/themes/eclipse.xml");
						
		oclSyntaxHighlight = new TOCLSyntaxHighlight();	    
	    ((RSyntaxDocument)textArea.getDocument()).setSyntaxStyle(oclSyntaxHighlight);
	    
	    provider = createDefaultCompletionProvider();

	    //parentProvider = new LanguageAwareCompletionProvider(provider);
	
	    createAutoComplete(provider);
	    
	    setLayout(new BorderLayout(0, 0));
      			
      	scrollPane = new RTextScrollPane(textArea);
      	scrollPane.getGutter().setBorder(new EmptyBorder(0, 0, 0, 0));
      	scrollPane.getGutter().setBorderColor(Color.LIGHT_GRAY);
      	scrollPane.setIconRowHeaderEnabled(true);
      	scrollPane.getGutter().setLineNumberColor(Color.GRAY);
      	scrollPane.getTextArea().setRows(5);
      	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    	scrollPane.setBorder(null);
    	
    	textArea.setMinimumSize(new Dimension(0, 0));
    	scrollPane.setMinimumSize(new Dimension(0, 0));
 	    setMinimumSize(new Dimension(0, 0));
      	add(scrollPane); 
      	
      	JMenu themeMenu = new JMenu("Theme");
      	ButtonGroup group = new ButtonGroup();
      	JRadioButtonMenuItem vsItem = new JRadioButtonMenuItem("VS Style");      	
      	themeMenu.add(vsItem);
      	group.add(vsItem);
      	vsItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setTheme(textArea,"/br/ufes/inf/nemo/ocl/editor/themes/vs.xml");
			}
		});
      	JRadioButtonMenuItem eclipseItem = new JRadioButtonMenuItem("Eclipse Style");
      	themeMenu.add(eclipseItem);
      	eclipseItem.setSelected(true);
      	group.add(eclipseItem);
      	eclipseItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setTheme(textArea,"/br/ufes/inf/nemo/ocl/editor/themes/eclipse.xml");
			}
		});
      	JRadioButtonMenuItem darkItem = new JRadioButtonMenuItem("Dark Style");
      	themeMenu.add(darkItem);
      	group.add(darkItem);
      	darkItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setTheme(textArea,"/br/ufes/inf/nemo/ocl/editor/themes/dark.xml");
			}
		});
      	JRadioButtonMenuItem ideaItem = new JRadioButtonMenuItem("Idea Style");
      	themeMenu.add(ideaItem);
     	group.add(ideaItem);
     	ideaItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setTheme(textArea,"/br/ufes/inf/nemo/ocl/editor/themes/idea.xml");
			}
		});
      	JRadioButtonMenuItem rstaItem = new JRadioButtonMenuItem("RSTA Style");
      	themeMenu.add(rstaItem);
      	group.add(rstaItem);
      	rstaItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				setTheme(textArea,"/br/ufes/inf/nemo/ocl/editor/themes/rsyntaxarea.xml");
			}
		});
      	getPopupMenu().add(themeMenu);      	
	}   
	
    /**
	  * Set Eclipse Theme on text area.
	  * 
	  * @param textArea
	  * @param xmlPath
	  */
	 public void setTheme(RSyntaxTextArea textArea, String xmlPath)
	 {
		 Theme theme;
		 try {
			theme = Theme.load(getClass().getResourceAsStream(xmlPath));
				theme.apply(textArea);
			} catch (IOException e) {
				e.printStackTrace();
			}   
	 }
	   
	 /**
	  * Set the font for all token types.
	  * 
	  * @param textArea
	  *           The text area to modify.
	  * @param font
	  *           The font to use.
	  */
	 public void setFont(RSyntaxTextArea textArea, Font font) 
	 {
	      if (font != null) 
	      {
	         SyntaxScheme ss = textArea.getSyntaxScheme();
	         ss = (SyntaxScheme) ss.clone();
	         for (int i = 0; i < ss.getStyleCount(); i++) 
	         {
	            if (ss.getStyle(i) != null) 
	            {
	               ss.getStyle(i).font = font;
	            }
	         }
	         textArea.setSyntaxScheme(ss);
	         textArea.setFont(font);
	      }
	}
	  	   
	/**
	 * Add Error Tracking Icon on the Editor
	 * 
	 * @param line
	 */
	public void addErrorTrackingIcon(int line)
	{
			
	}
	
	/**
	 * Clear All Tracking Icons
	 */
	public void clearTrackingIcons()
	{		
      	Gutter gutter = scrollPane.getGutter();      
		gutter.removeAllTrackingIcons();
	}
	
	/**
	 * Set Parent.
	 *
	 * @param frame
	 */
	public void setParent (Component parent)
	{
		this.parent = parent;
	}
	
	/**
	 * Set Text.
	 * 
	 * @param text
	 */
	public void setText(String text)
	{
		textArea.setText(text);
	}
	
	/**
	 * Get Text.
	 */
	public String getText()
	{
		return textArea.getText();
	}	
	
	/**
	 * Add text to the OCL Editor
	 */
	public void addText(String text){
		setText(getText()+text);
	}	
	
	public void addDocumentListener(DocumentListener docListener)
	{
		getTextArea().getDocument().addDocumentListener(docListener);
	}
	
	/**
	 * Create Default Completion Provider
	 */
	public DefaultCompletionProvider createDefaultCompletionProvider ()
	{				
		provider = new DefaultCompletionProvider(){ 
		   @Override
		   protected boolean isValidChar(char ch) {
		      return ch=='.' || ch=='>' || super.isValidChar(ch);
		   }
		};
		provider.setAutoActivationRules(true, ".");
		provider.setAutoActivationRules(true, ">");
		
		createConstraintCompletion();
		createObjectCompletion();
		createSetCompletion();
		createIteratorCompletion();
		createExpressionCompletion();		
		createIntegerCompletion();
		
//		createBooleanCompletion();
		
		return provider;
	}
	
	/** Constraint Completion*/
	public void createConstraintCompletion()
	{
		String description = new String();
		
		description = "<b>context Type inv: boolean-expression </b><br><br>"+
		"Invariant.";
		
		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"inv","invariant",
			"context ${Type}\ninv : ${true} ${cursor}\n",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "<b>context srcType::Property::tgtType derive: ocl-expression </b><br><br>"+
		"Derivation constraint.";
		
		c = new TOCLTemplateCompletion(provider, 
			"derive","derivation",
			"context ${Type}::${Property}:${propertyType}\nderive : ${cursor}\n",
			null,description);		
		provider.addCompletion(c); 
		oclCompletionList.add(c);
		
		description = "<b>context Type inv/derive: ocl-expression </b><br><br>"+
		"Context declaration.";
		
		c = new TOCLTemplateCompletion(provider, 
			"context","context",
			"context ${Type}\n${cursor}\n",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
	}
	
	/** Expression Completion*/
	public void createExpressionCompletion()
	{
		String description = "<b>let var = ocl-expression in ocl-expression2</b>";
		
		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"let","let-in",
			"let ${varName} = ${oclexpression}\nin ${expression}${cursor}",
			null,
			description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "<b>if condition then ocl-expression else null endif</b>";
		
		c = new TOCLTemplateCompletion(provider, 
			"if","if-then-else",
			"if ${oclexpression} then ${oclexpression}\nelse ${oclexpression} endif${cursor}",
			null,
			description);
		
		provider.addCompletion(c);
		oclCompletionList.add(c);
	}
	
	/** Object Completion */
	public void createObjectCompletion()
	{ 
		String description = "Operation <b>OclAny::=(object2 : OclSelf) : Boolean</b><br><br>"+
		"True if self is the same object as object2. Infix operator.";

		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"=","=",
			"= ${cursor}",
			null, description);		
		provider.addCompletion(c); 
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::&lt&gt(object2 : OclSelf) : Boolean</b><br><br>"+
		"True if self is a different object from object2. Infix operator.";

		c = new TOCLTemplateCompletion(provider, 
			"&lt&gt","<>",
			"<> ${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclAsType(T)(type : AnyClassifier(T)) : T</b><br><br>"+
		
		"Evaluates to self, where self is of the type identified by T. The type T may be any classifier " +
		"defined in the OntoUML model; if the actual type of self at evaluation time does not conform to T, then " +
		"the oclAsType operation evaluates to invalid."+"<br><br>"+

		"In the case of feature redefinition, casting an object to a supertype of its actual type does not access " +
		"the supertype definition of the feature; according to the semantics of redefinition, the redefined feature " +
		"simply does not exist for the object. However, when casting to a supertype, any features additionally defined " +
		"by the subtype are suppressed.";

		c = new TOCLTemplateCompletion(provider, 
			"oclAsType","oclAsType",
			"oclAsType(${T})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclIsKindOf(T)(type : AnyClassifier(T)) : Boolean</b><br><br>"+
		"Evaluates to true if the type of self conforms to t. That is, self is of type t or a subtype of t.";
		
		c = new TOCLTemplateCompletion(provider, 
			"oclIsKindOf","oclIsKindOf",
			"oclIsKindOf(${T})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "";
		
		c = new TOCLTemplateCompletion(provider, 
			"allInstances","allInstances",
			"allInstances()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclIsTypeOf(T)(type : AnyClassifier(T)) : Boolean</b><br><br>"+
		"Evaluates to true if self is of the type t but not a subtype of t.";
		
		c = new TOCLTemplateCompletion(provider, 
			"oclIsTypeOf","oclIsTypeOf",
			"oclIsTypeOf(${T})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>OclAny::oclIsUndefined() : Boolean</b><br><br>"+
		"Evaluates to true if the self is equal to invalid or equal to null.";

		c = new TOCLTemplateCompletion(provider, 
			"oclIsUndefined","oclIsUndefined",
			"oclIsUndefined()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
	}

	/** Collection Completion */
	public void createSetCompletion()
	{
		String description = "Operation <b>Collection(T)::size() : Integer</b><br><br>"+
		"The number of elements in the collection self.";
				
		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"size","size",
			"size()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::includesAll(T2)(c2 : Collection(T2)) : Boolean</b><br><br>"+
		"Does self contain all the elements of c2 ?";

		c = new TOCLTemplateCompletion(provider, 
			"includesAll","includesAll",
			"includesAll(${Collection(T)})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::excludesAll(T2)(c2 : Collection(T2)) : Boolean</b><br><br>"+
		"Does self contain none of the elements of c2 ?";

		c = new TOCLTemplateCompletion(provider, 
			"excludesAll","excludesAll",
			"excludesAll(${Collection(T)})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::includes(object : OclAny) : Boolean</b><br><br>"+
		"True if object is an element of self, false otherwise.";

		c = new TOCLTemplateCompletion(provider, 
			"includes","includes",
			"includes(${Collection(T))${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::excludes(object : OclAny) : Boolean</b><br><br>"+
		"True if object is not an element of self, false otherwise.";
		
		c = new TOCLTemplateCompletion(provider, 
			"excludes","excludes",
			"excludes(${object})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::isEmpty() : Boolean</b><br><br>"+
		"Is self the empty collection?"+"<br><br>"+ 
		"Note: null->isEmpty() returns true in virtue of the implicit casting from null to Bag{}.";

		c = new TOCLTemplateCompletion(provider, 
			"isEmpty","isEmpty",
			"isEmpty()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::notEmpty() : Boolean</b><br><br>"+
		"Is self not the empty collection?"+"<br><br>"+ 
		"null->notEmpty() returns false in virtue of the implicit casting from null to Bag{}.";

		c = new TOCLTemplateCompletion(provider, 
			"notEmpty","notEmpty",
			"notEmpty()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::asSet() : Set(T)</b><br><br>"+
		"The Set containing all the elements from self, with duplicates removed.";

		c = new TOCLTemplateCompletion(provider, 
			"asSet","asSet",
			"asSet()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::union(s : Collection(T)) : Set(T)</b><br><br>"+
		"The set consisting of all elements in self and all elements in s.";

		c = new TOCLTemplateCompletion(provider, 
			"union","union",
			"union(${Collection(T)})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::intersection(s : Collection(T)) : Set(T)</b><br><br>"+
		"The intersection of self and s (i.e., the set of all elements that are in both self and s).";
				
		c = new TOCLTemplateCompletion(provider, 
			"intersection","intersection",
			"intersection${Collection(T)})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::including(object : T) : Set(T)</b><br><br>"+
		"The set containing all elements of self plus object.";

		c = new TOCLTemplateCompletion(provider, 
			"including","including",
			"including(${object})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::excluding(object : OclAny) : Set(T)</b><br><br>"+
		"The set containing all elements of self without object.";

		c = new TOCLTemplateCompletion(provider, 
			"excluding","excluding",
			"excluding(${object})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>UniqueCollection(T)::symmetricDifference(s : UniqueCollection(OclAny)) : Set(T)</b><br><br>"+
		"The set containing all the elements that are in self or s, but not in both.";

		c = new TOCLTemplateCompletion(provider, 
			"symmetricDifference","symmetricDifference",
			"symmetricDifference(${UniqueCollection(OclAny)})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::-(s : UniqueCollection(OclAny)) : Set(T)</b><br><br>"+
		"The elements of self, which are not in s.";
				
		c = new TOCLTemplateCompletion(provider, 
			"-","-",
			"- ${UniqueCollection(OclAny)} ${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::sum() : T</b><br><br>"+
		
		"The addition of all elements in self. Elements must be of an OclSummable type to provide " +
		"the zero() and sum() operations. The sum operation must be both associative: a.sum(b).sum(c) = " +
		"a.sum(b.sum(c)), and commutative: a.sum(b) = b.sum(a). UnlimitedNatural, Integer and Real fulfill this condition."+"<br><br>"+

		"If the sum operation is not both associative and commutative, the sum expression is not well-formed, which may " +
		"result in unpredictable results during evaluation. If an implementation is able to detect a lack of associativity " +
		"or commutativity, the implementation may bypass the evaluation and return an invalid result.";

		c = new TOCLTemplateCompletion(provider, 
			"sum","sum",
			"sum()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::product(T2)(c2 : Collection(T2)) : Set(Tuple(first : T, second : T2))</b><br><br>"+
		"The cartesian product operation of self and c2.";

		c = new TOCLTemplateCompletion(provider, 
			"product","product",
			"product(${Collection(T)})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Set(T)::flatten(T2)() : Set(T2)</b><br><br>"+
		"Redefines the Collection operation. If the element type is not a collection type, " +
		"this results in the same set as self. If the element type is a collection type, the result " +
		"is the set containing all the elements of all the recursively flattened elements of self.";

		c = new TOCLTemplateCompletion(provider, 
			"flatten","flatten",
			"flatten()${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Collection(T)::count(object : OclAny) : Integer</b><br><br>"+
		"The number of times that object occurs in the collection self.";
		
		c = new TOCLTemplateCompletion(provider, 
			"count","count",
			"count(${object})${cursor}",
			null, description);		
		provider.addCompletion(c);		
		oclCompletionList.add(c);
	}
	
	/** Iterator Completion */
	public void createIteratorCompletion()
	{
		String description = "Iteration <b>Collection(T)::exists(j : T, i : T | body : Lambda T() : Boolean) : Boolean</b>";
		
		TOCLTemplateCompletion  c = new TOCLTemplateCompletion(provider, 
			"exists","exists",
			"exists(${i,j:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Collection(T)::forAll(i : T, j : T | body : Lambda T() : Boolean) : Boolean</b>";
		
		c = new TOCLTemplateCompletion(provider, 
			"forAll","forAll",
			"forAll(${i,j:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Collection(T)::one(i : T | body : Lambda T() : Boolean) : Boolean</b><br><br>"+
		"Results in true if there is exactly one element in the source collection for which body is true.";

		c = new TOCLTemplateCompletion(provider, 
			"one","one",
			"one(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Set(T)::select(i : T | body : Lambda T() : Boolean) : Set(T)</b><br><br>"+
		"The subset of set for which expr is true.";

		c = new TOCLTemplateCompletion(provider, 
			"select","select",
			"select(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Set(T)::reject(i : T | body : Lambda T() : Boolean) : Set(T)</b><br><br>"+
		"The subset of the source set for which body is false.";

		c = new TOCLTemplateCompletion(provider, 
			"reject","reject",
			"reject(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Bag(T)::closure(i : T | body : Lambda T() : Set(T)) : Set(T)</b><br><br>"+
		"The closure of applying body transitively to every distinct element of the source collection";

		c = new TOCLTemplateCompletion(provider, 
			"closure","closure",
			"closure(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Collection(T)::isUnique(i : T | body : Lambda T() : OclAny) : Boolean</b><br><br>"+
		"Results in true if body evaluates to a different value for each element in the source collection; " +
		"otherwise, result is false.";

		c = new TOCLTemplateCompletion(provider, 
			"isUnique","isUnique",
			"isUnique(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Bag(T)::collect(V)(i : T | body : Lambda T() : V) : Bag(V)</b>";
		
		c = new TOCLTemplateCompletion(provider, 
			"collect","collect",
			"collect(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Iteration <b>Collection(T)::any(i : T | body : Lambda T() : Boolean) : T</b><br><br>"+
		"Returns any element in the source collection for which body evaluates to true. If there is more than one " +
		"element for which body is true, one of them is returned. There must be at least one element fulfilling body, " +
		"otherwise the result of this IteratorExp is null.";
		
		c = new TOCLTemplateCompletion(provider, 
			"any","any",
			"any(${i:T} | ${body})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
	}

	/** Integer Completion */
	public void createIntegerCompletion()
	{
		String description = new String();
		
		description = "Operation <b>Real::floor() : Integer</b><br><br>"+
		"The largest integer that is less than or equal to self.";

		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"max","max",
			"max(${Integer})${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Integer::min(i : OclSelf) : Integer</b><br><br>"+
		"The minimum of self an i.";

		c = new TOCLTemplateCompletion(provider, 
			"min","min",
			"min(${Integer})${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Integer::abs() : Integer</b><br><br>"+
		"The absolute value of self.";

		c = new TOCLTemplateCompletion(provider, 
			"abs","abs",
			"abs()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Real::floor() : Integer</b><br><br>"+
		"The largest integer that is less than or equal to self";

		c = new TOCLTemplateCompletion(provider, 
			"floor","floor",
			"floor()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		description = "Operation <b>Real::round() : Integer</b><br><br>"+
		"The integer that is closest to self. When there are two such integers, the largest one.";

		c = new TOCLTemplateCompletion(provider, 
			"round","round",
			"round()${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
//		description = "Operation <b>Integer::+(i : OclSelf) : Integer</b><br><br>"+
//		"The value of the addition of self and i.";
//
//		c = new OCLTemplateCompletion(provider, 
//			"+","+",
//			"+ ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		description = "Operation <b>Integer::*(i : OclSelf) : Integer</b><br><br>"+
//		"The value of the multiplication of self and i.";
//
//		c = new OCLTemplateCompletion(provider, 
//			"*","*",
//			"* ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		description = "Operation <b>Integer::-() : Integer</b><br><br>"+
//		"The negative value of self.";
//
//		c = new OCLTemplateCompletion(provider, 
//			"-","negative",
//			"- ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			">",">",
//			"> ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			">=",">=",
//			">= ${<Integer>} ${cursor}",
//			null,
//			description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			"<=","<=",
//			"<= ${<Integer>} ${cursor}",
//			null,description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			"<","<",
//			"< ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			"-","subtraction",
//			"- ${<Integer>} ${cursor}",
//			null, description);		
//		provider.addCompletion(c);
//		oclCompletionList.add(c);

	}
	
	/** Boolean Completion */
	public void createBooleanCompletion()
	{
		String description = new String();
		
		TOCLTemplateCompletion c = new TOCLTemplateCompletion(provider, 
			"or","or",
			"or ${OCLExpression} ${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		c = new TOCLTemplateCompletion(provider, 
			"and","and",
			"and ${OCLExpression} ${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		c = new TOCLTemplateCompletion(provider, 
			"not","not",
			"not ${OCLExpression} ${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		c = new TOCLTemplateCompletion(provider, 
			"implies","implies",
			"implies ${OCLExpression} ${cursor}",
			null, description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
		
		c = new TOCLTemplateCompletion(provider, 
			"xor","xor",
			"xor ${OCLExpression} ${cursor}",
			null,description);		
		provider.addCompletion(c);
		oclCompletionList.add(c);
	}	
}
