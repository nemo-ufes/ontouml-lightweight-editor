package br.ufes.inf.nemo.ocl.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
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

import RefOntoUML.Property;
import br.ufes.inf.nemo.common.ontoumlparser.OntoUMLParser;

/**
 * @author John Guerson
 */

public class OCLEditorPanel extends JPanel {

	private static final long serialVersionUID = 1277358682337723759L;
	
	public Component parent;
	public RSyntaxTextArea textArea;
	public OCLTokenMaker tokenMaker;	
	public CompletionProvider parentProvider;	
	public AutoCompletion ac;
	public RTextScrollPane scrollPane;
	public DefaultCompletionProvider provider;
	public JMenuItem saveMenuItem;
	public JMenuItem openMenuItem;
	public JMenuItem parserMenuItem;
	public ArrayList<OCLTemplateCompletion> modelCompletionList = new ArrayList<OCLTemplateCompletion>();
	
   /**
    * Constructor
    * 
    * @param frame
    */
   public OCLEditorPanel(Component parent)
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
		   addCompletion(oc,refparser);
	   }
	   for(RefOntoUML.Property p: refparser.getAllInstances(RefOntoUML.Property.class))
	   {
		   if (p.getAssociation()==null) addCompletion(p, refparser);		   
	   }
	   for(RefOntoUML.Association p: refparser.getAllInstances(RefOntoUML.Association.class))
	   {
		   addCompletion(p,refparser);
	   }
   }
   
   public void addCompletion(RefOntoUML.Association p, OntoUMLParser refparser)
   {
	   Property source = p.getMemberEnd().get(0);
	   Property target = p.getMemberEnd().get(1);
	   
	   if ((source.getName()!=null)&&!(source.getName().isEmpty()))
	   {
		   OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
				source.getName(),source.toString(),
				"_'"+source.getName()+"'",
				target.getType().getName()+"->"+source.getType().getName(),
				"Should be more of a description here...");		
		    provider.addCompletion(c);
		    modelCompletionList.add(c);
	   }
	   if ((target.getName()!=null)&&!(target.getName().isEmpty()))
	   {
		   OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
				target.getName(),target.toString(),
				"_'"+target.getName()+"'",
				source.getType().getName()+"->"+target.getType().getName(),
				"Should be more of a description here...");		
		    provider.addCompletion(c);
		    modelCompletionList.add(c);
	   }
   }   
   
   public void addCompletion(RefOntoUML.Property p, OntoUMLParser refparser)
   {
	   if ((p.getName()!=null)&&!(p.getName().isEmpty()))
	   {
		   OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
				p.getName(),p.toString(),
				"_'"+p.getName()+"'",
				((RefOntoUML.Classifier)p.eContainer()).getName(),
				"Should be more of a description here...");		
		    provider.addCompletion(c);
		    modelCompletionList.add(c);
	   }
   }   
   
   public void addCompletion(RefOntoUML.Class oc, OntoUMLParser refparser)
   {
	   OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
			oc.getName(),oc.toString(),
			"_'"+oc.getName()+"'",
			refparser.getStereotype(oc),
			"Should be more of a description here...");		
	    provider.addCompletion(c);
	    modelCompletionList.add(c);
   }
   
   @SuppressWarnings("rawtypes")
   public void removeCompletion(EObject elem)
   {	   
	   Iterator it = modelCompletionList.iterator();
	   while(it.hasNext())
	   {
		   OCLTemplateCompletion tc = (OCLTemplateCompletion)it.next();
		   if (tc.getDefinitionString().equals(elem.toString()))
		   {
			   it.remove();
			   provider.removeCompletion(tc);
		   }
	   }
   }
      
	/**
	 * Constructor.
	 */
	public OCLEditorPanel ()
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		textArea = new RSyntaxTextArea(5, 30);		
	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
	    textArea.setAntiAliasingEnabled(true);
	    textArea.setCodeFoldingEnabled(false);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(new Color(255, 255, 255));				
		setTheme(textArea,"/br/ufes/inf/nemo/ocl/editor/EclipseTheme.xml");
		//textArea.setCurrentLineHighlightColor(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));		
				
		tokenMaker = new OCLTokenMaker();	    
	    ((RSyntaxDocument)textArea.getDocument()).setSyntaxStyle(tokenMaker);
	    
	    provider = createDefaultCompletionProvider();

	    //parentProvider = new LanguageAwareCompletionProvider(provider);
	    
	    ac = new AutoCompletion(provider);   
	    ac.setListCellRenderer(new OCLCellRenderer());	    
		ac.setParameterAssistanceEnabled(true);
	    ac.setAutoActivationEnabled(true);
      	ac.setShowDescWindow(true);      	
      	ac.install(textArea);

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
		DefaultCompletionProvider provider = new DefaultCompletionProvider(); 
		provider.setAutoActivationRules(true, ".");
		//provider.setAutoActivationRules(true, "->");
		
		OCLTemplateCompletion c = new OCLTemplateCompletion(provider, 
			"package","package",
			"package ${<Package>}\n\n${cursor}\nendpackage",
			"Package",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"context","invariant",
			"context ${<Type>}\ninv : ${cursor}\n",
			"Invariant",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"context","derivation",
			"context ${<Type>}::${<Property>}:${<propertyType>}\nderive : ${cursor}\n",
			"Derivation",
			"Should be more of a description here...");		
		provider.addCompletion(c); 

		c = new OCLTemplateCompletion(provider, 
			"oclIsKindOf()","oclIsKindOf",
			"oclIsKindOf(${<Type>})${cursor}",
			"OclAny",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"allInstances()","allInstances",
			"allInstances()${cursor}",
			"OclAny",
			"Should be more of a description here...");		
		provider.addCompletion(c);
			
		c = new OCLTemplateCompletion(provider, 
			"oclIsTypeOf()","oclIsTypeOf",
			"oclIsTypeOf(${<Type>})${cursor}",
			"OclAny",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"oclIsUndefined()","oclIsUndefined",
			"oclIsUndefined()${cursor}",
			"OclAny",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"oclAsType()","oclAsType",
			"oclAsType(${<Type>})${cursor}",
			"OclAny",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"size()","size",
			"size()${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"includesAll()","includesAll",
			"includesAll(${<Set(Type)>})${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"excludesAll()","excludesAll",
			"excludesAll(${<Set(Type)>})${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"includes()","includes",
			"includes(${<object>})${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"excludes()","excludes",
			"excludes(${<object>})${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"isEmpty()","isEmpty",
			"isEmpty()${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"notEmpty()","notEmpty",
			"notEmpty()${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"asSet()","asSet",
			"asSet()${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"union()","union",
			"union(${<Set(Type)>})${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"intersection()","intersection",
			"intersection(${<Set(Type)>})${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"including()","including",
			"including(${<object>})${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"excluding()","excluding",
			"excluding(${<object>})${cursor}",
			null,
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"symmetricDifference()","symmetricDifference",
			"symmetricDifference(${<Set(Type)>})${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"sum()","sum",
			"sum()${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"product()","product",
			"product(${<Set(Type)>})${cursor}",
			"Set",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"max()","max",
			"max(${<Integer>})${cursor}",
			"Integer",
			"Should be more of a description here...");		
		provider.addCompletion(c);
			
		c = new OCLTemplateCompletion(provider, 
			"min()","min",
			"min(${<Integer>})${cursor}",
			"Integer",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"abs()","abs",
			"abs()${cursor}",
			"Integer",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"-","difference",
			"- ${<Set(Type)>} ${cursor}",
			"Set Difference",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"or","or",
			"or ${<OCLExpression>} ${cursor}",
			"Boolean",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"and","and",
			"and ${<OCLExpression>} ${cursor}",
			"Boolean",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"not","not",
			"not ${<OCLExpression>} ${cursor}",
			"Boolean",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"implies","implies",
			"implies ${<OCLExpression>} ${cursor}",
			"Boolean",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"xor","xor",
			"xor ${<OCLExpression>} ${cursor}",
			"Boolean",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"exists()","exists",
			"exists(${<x>} | ${<OCLExpression>})${cursor}",
			"Iterator",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"forAll()","forAll",
			"forAll(${<x>} | ${<OCLExpression>})${cursor}",
			"Iterator",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"one()","one",
			"one(${<x>} | ${<OCLExpression>})${cursor}",
			"Iterator",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"select()","select",
			"select(${<x>} | ${<OCLExpression>})${cursor}",
			"Iterator",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"reject()","reject",
			"reject(${<x>} | ${<OCLExpression>})${cursor}",
			"Iterator",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"isUnique()","isUnique",
			"isUnique(${<x>} | ${<OCLExpression>})${cursor}",
			"Iterator",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"collect()","collect",
			"collect(${<x>} | ${<OCLExpression>})${cursor}",
			"Iterator",
			"Should be more of a description here...");		
		provider.addCompletion(c);

		c = new OCLTemplateCompletion(provider, 
			"let-in","let-in",
			"let ${<x>} = ${<OCLExpression>}\nin ${<OCLExpression>}${cursor}",
			"Expression",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
		c = new OCLTemplateCompletion(provider, 
			"if-then-else","if-then-else",
			"is ${<OCLExpression>} then ${<OCLExpression>}\nelse ${<OCLExpression>}${cursor}",
			"Expression",
			"Should be more of a description here...");		
		provider.addCompletion(c);			
		
		c = new OCLTemplateCompletion(provider, 
			"closure()","closure",
			"closure(${<x>} | ${<OCLExpression>})${cursor}",
			"Iterator",
			"Should be more of a description here...");		
		provider.addCompletion(c);
		
//		c = new OCLTemplateCompletion(provider, 
//			">",">",
//			"> ${<Integer>} ${cursor}",
//			"Integer",
//			"Should be more of a description here...");		
//		provider.addCompletion(c);
//
//		c = new OCLTemplateCompletion(provider, 
//			">=",">=",
//			">= ${<Integer>} ${cursor}",
//			"Integer",
//			"Should be more of a description here...");		
//		provider.addCompletion(c);
		
//		c = new OCLTemplateCompletion(provider, 
//			"<=","<=",
//			"<= ${<Integer>} ${cursor}",
//			"Integer",
//			"Should be more of a description here...");		
//		provider.addCompletion(c);
//		
//		c = new OCLTemplateCompletion(provider, 
//			"<","<",
//			"< ${<Integer>} ${cursor}",
//			"Integer",
//			"Should be more of a description here...");		
//		provider.addCompletion(c);

//		c = new OCLTemplateCompletion(provider, 
//			"-","negative",
//			"- ${<Integer>} ${cursor}",
//			"Negative Integer",
//			"Should be more of a description here...");		
//		provider.addCompletion(c);
		
//		c = new OCLTemplateCompletion(provider, 
//			"-","subtraction",
//			"- ${<Integer>} ${cursor}",
//			"Integer Subtraction",
//			"Should be more of a description here...");		
//		provider.addCompletion(c);
//
//		c = new OCLTemplateCompletion(provider, 
//			"+","+",
//			"+ ${<Integer>} ${cursor}",
//			"Integer",
//			"Should be more of a description here...");		
//		provider.addCompletion(c);
//			
//		c = new OCLTemplateCompletion(provider, 
//			"*","*",
//			"* ${<Integer>} ${cursor}",
//			"Integer",
//			"Should be more of a description here...");		
//		provider.addCompletion(c);

//		c = new OCLTemplateCompletion(provider, 
//			"=","=",
//			"= ${cursor}",
//			"OclAny",
//			"Should be more of a description here...");		
//		provider.addCompletion(c); 

//		c = new OCLTemplateCompletion(provider, 
//			"<>","<>",
//			"<> ${cursor}",
//			"OclAny",
//			"Should be more of a description here...");		
//		provider.addCompletion(c);

		return provider;
	}

}
