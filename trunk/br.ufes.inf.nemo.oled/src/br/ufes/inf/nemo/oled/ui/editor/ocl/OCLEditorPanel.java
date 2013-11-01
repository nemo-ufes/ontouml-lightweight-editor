package br.ufes.inf.nemo.oled.ui.editor.ocl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.LanguageAwareCompletionProvider;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.Gutter;
import org.fife.ui.rtextarea.RTextScrollPane;

import br.ufes.inf.nemo.oled.ui.AppFrame;
import br.ufes.inf.nemo.oled.ui.DiagramEditorWrapper;

/**
 * @author John Guerson
 */

public class OCLEditorPanel extends JPanel {

	private static final long serialVersionUID = 1277358682337723759L;
	
	public AppFrame frame;
	public RSyntaxTextArea textArea;
	public OCLTokenMaker tokenMaker;	
	public CompletionProvider parentProvider;
	public AutoCompletion ac;
	public RTextScrollPane scrollPane;
	public DefaultCompletionProvider provider;
	public JMenuItem saveMenuItem;
	public JMenuItem openMenuItem;
	public JMenuItem parserMenuItem;
	
   /**
    * Constructor
    * 
    * @param frame
    */
   public OCLEditorPanel(AppFrame frame)
   {
	   this();
	   
	   this.frame = frame;
   }
 
   public RSyntaxTextArea getTextArea(){
	   return textArea;
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
		setTheme(textArea,"/br/ufes/inf/nemo/oled/ui/editor/ocl/EclipseTheme.xml");
		//textArea.setCurrentLineHighlightColor(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));		
		
		parserMenuItem = new JMenuItem("Parse",new ImageIcon(DiagramEditorWrapper.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/check.png")));
		
		openMenuItem = new JMenuItem("Open");
		saveMenuItem = new JMenuItem("Save As...");
		textArea.getPopupMenu().add(parserMenuItem);
		textArea.getPopupMenu().add(openMenuItem);
		textArea.getPopupMenu().add(saveMenuItem);
				
		saveMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getDiagramManager().exportOCL();
			}
		});
		openMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getDiagramManager().importOCL();
			}
		});
		parserMenuItem.addActionListener(new ActionListener() {			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.getDiagramManager().parseOCL(true);
			}
		});
		
		tokenMaker = new OCLTokenMaker();	    
	    ((RSyntaxDocument)textArea.getDocument()).setSyntaxStyle(tokenMaker);
	    
	    CompletionProvider provider = createDefaultCompletionProvider();

	    parentProvider = new LanguageAwareCompletionProvider(provider);
	    
	    ac = new AutoCompletion(parentProvider);   
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
	public void setParent (AppFrame frame)
	{
		this.frame = frame;
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
	
	
	/**
	 * Create Default Completion Provider
	 */
	public CompletionProvider createDefaultCompletionProvider ()
	{				
		DefaultCompletionProvider provider = new DefaultCompletionProvider();
		
		provider.setAutoActivationRules(false, ".");
		provider.setAutoActivationRules(false, "->");
		
		//package declaration
		OCLShorthandCompletion c = new OCLShorthandCompletion(provider, "package","package PackageName\n\n\nendpackage",null);
		c.setSummary("package PackageName<br>...<br>endpackage");
		provider.addCompletion(c);    	
		
		//context declaration
		c = new OCLShorthandCompletion(provider, "context","context type\ninv : true",null);
		c.setSummary("context type<br> inv invariantname: true<br>");
		provider.addCompletion(c);  
		
		//property context declaration
		c = new OCLShorthandCompletion(provider, "property","context type::property : propertyType\nderive: null",null);
		c.setSummary("context type::property : propertyType<br>derive: null");
		provider.addCompletion(c);    	
		
		//oclIsKindOf
		c = new OCLShorthandCompletion(provider, "oclIsKindOf","oclIsKindOf",null);
		c.setSummary("oclIsKindOf(t: Classifier)<br><br>Evaluates to true if the type of self conforms to t. That is, self is of type t or a subtype of t.");
		provider.addCompletion(c);    	
		
		//oclIsTypeOf
		c = new OCLShorthandCompletion(provider, "oclIsTypeOf","oclIsTypeOf",null);
		c.setSummary("oclIsTypeOf(t: Classifier)<br><br>Evaluates to true if self is of the type t but not a subtype of t.");
		provider.addCompletion(c);    	
		
		//oclIsUndefined()
		c = new OCLShorthandCompletion(provider, "oclIsUndefined","oclIsUndefined()",null);
		c.setSummary("oclIsUndefined()<br><br>Evaluates to true if the self is equal to invalid or equal to null.");
		provider.addCompletion(c);   
		
		//size()
		c = new OCLShorthandCompletion(provider, "size","size()",null);
		c.setSummary("size()<br><br>The number of elements in the collection self.");
		provider.addCompletion(c);   
		
		//includesAll
		c = new OCLShorthandCompletion(provider, "includesAll","includesAll",null);
		c.setSummary("includesAll(c: Collection)<br><br>Does self contain all the elements of c ?");
		provider.addCompletion(c);
		
		//excludesAll
		c = new OCLShorthandCompletion(provider, "excludesAll","excludesAll",null);
		c.setSummary("excludesAll(c: Collection)<br><br>Does self contain none of  the elements of c ?");
		provider.addCompletion(c);
		
		//excludes
		c = new OCLShorthandCompletion(provider, "excludes","excludes",null);
		c.setSummary("excludes(o: Object)<br><br>True if object o is not an element of self, false otherwise.");
		provider.addCompletion(c);
		
		//includes
		c = new OCLShorthandCompletion(provider, "includes","includes",null);
		c.setSummary("includes(o: Object)<br><br>True if object is an element of self, false otherwise.");
		provider.addCompletion(c);
		
		//isEmpty()
		c = new OCLShorthandCompletion(provider, "isEmpty","isEmpty()",null);
		c.setSummary("isEmpty()<br><br>Is self the empty collection ?");
		provider.addCompletion(c);
		
		//notEmpty()
		c = new OCLShorthandCompletion(provider, "notEmpty","notEmpty()",null);
		c.setSummary("notEmpty()<br><br>Is self not the empty collection ?");
		provider.addCompletion(c);
		
		//asSet()
		c = new OCLShorthandCompletion(provider, "asSet","asSet()",null);
		c.setSummary("asSet()<br><br>The Set containing all the elements from self, with duplicates removed.");
		provider.addCompletion(c);
		
		//union
		c = new OCLShorthandCompletion(provider, "union","union",null);
		c.setSummary("union(s: Set(T))<br><br>The union of self and s.");
		provider.addCompletion(c);
		
		//intersection
		c = new OCLShorthandCompletion(provider, "intersection","intersection",null);
		c.setSummary("intersection(s: Set(T))<br><br>The intersection of self and s (i.e., the set of all elements that are in both self and s).");
		provider.addCompletion(c);
		
		//-
		//c = new OCLShorthandCompletion(provider, "-","-",null);
		//c.setSummary("-(s: Set(T))<br><br>The elements of self, which are not in s.");
		//provider.addCompletion(c);
		
		//including
		c = new OCLShorthandCompletion(provider, "including","including",null);
		c.setSummary("including(o: Object)<br><br>The set containing all elements of self plus object.");
		provider.addCompletion(c);
		
		//excluding
		c = new OCLShorthandCompletion(provider, "excluding","excluding",null);
		c.setSummary("excluding(o: Object)<br><br>The set containing all elements of self without object.");
		provider.addCompletion(c);
		
		//symmetricDifference
		c = new OCLShorthandCompletion(provider, "symmetricDifference","symmetricDifference",null);
		c.setSummary("symmetricDifference(s: Set(T))<br><br>The sets containing all the elements that are in self or s, but not in both.");
		provider.addCompletion(c);
		
		//allInstances()
		c = new OCLShorthandCompletion(provider, "allInstances","allInstances()",null);
		c.setSummary("allInstances()<br><br> All instances of self.");
		provider.addCompletion(c);
		
		//max()
		c = new OCLShorthandCompletion(provider, "max","max()",null);
		c.setSummary("max()<br><br>The maximum of self an i.");
		provider.addCompletion(c);
		
		//min()
		c = new OCLShorthandCompletion(provider, "min","min()",null);
		c.setSummary("min()<br><br>The minimum of self an i.");
		provider.addCompletion(c);
		        
		//abs()
		c = new OCLShorthandCompletion(provider, "abs","abs()",null);
		c.setSummary("abs()<br><br>The absolute value of self.");
		provider.addCompletion(c);
		
		//<>
		c = new OCLShorthandCompletion(provider, "<>","<>",null);
		c.setSummary("self <> object<br><br>True if self is a different object from object. Infix operator.");
		provider.addCompletion(c);
		
		//=
		c = new OCLShorthandCompletion(provider, "=","=",null);
		c.setSummary("self = object<br><br>True if self is the same object as object. Infix operator.");
		provider.addCompletion(c);    	
		
		//or
		c = new OCLShorthandCompletion(provider, "or","or",null);
		c.setSummary("self or b<br><br>True if either self or b is true.");
		provider.addCompletion(c); 
		
		//and
		c = new OCLShorthandCompletion(provider, "and","and",null);
		c.setSummary("self and b<br><br>True if both self and b are true.");
		provider.addCompletion(c); 
		
		//not
		c = new OCLShorthandCompletion(provider, "not","not",null);
		c.setSummary("not self<br><br>True if self is false.");
		provider.addCompletion(c);
		
		//implies
		c = new OCLShorthandCompletion(provider, "implies","implies",null);
		c.setSummary("self implies b<br><br>True if self is false, or if self is true and b is true.");
		provider.addCompletion(c);
		
		//xor
		c = new OCLShorthandCompletion(provider, "xor","xor",null);
		c.setSummary("self xor b<br><br>True if either self or b is true, but not both.");
		provider.addCompletion(c);
		
		//exists
		c = new OCLShorthandCompletion(provider, "exists","exists",null);
		c.setSummary("exists(x: Type | expression)");
		provider.addCompletion(c);
		
		//forAll
		c = new OCLShorthandCompletion(provider, "forAll","forAll",null);
		c.setSummary("forAll(x: Type | expression)");
		provider.addCompletion(c);
		
		//one
		c = new OCLShorthandCompletion(provider, "one","one",null);
		c.setSummary("one(x: Type | expression)");
		provider.addCompletion(c);
		
		//select
		c = new OCLShorthandCompletion(provider, "select","select",null);
		c.setSummary("select(x: Type | expression)");
		provider.addCompletion(c);
		
		//reject
		c = new OCLShorthandCompletion(provider, "reject","reject",null);
		c.setSummary("reject(x: Type | expression)");
		provider.addCompletion(c);
		
		//isUnique
		c = new OCLShorthandCompletion(provider, "isUnique","isUnique",null);
		c.setSummary("isUnique(x: Type | expression)");
		provider.addCompletion(c);
		
		//collect
		c = new OCLShorthandCompletion(provider, "collect","collect",null);
		c.setSummary("collect(x: Type | expression)");
		provider.addCompletion(c);
		
		//let x = in
		c = new OCLShorthandCompletion(provider, "let","let x = \nin ",null);
		c.setSummary("let x = expr <br>in expr");
		provider.addCompletion(c);
		
		//if then else
		c = new OCLShorthandCompletion(provider, "if","if then <br>else",null);
		c.setSummary("if expr then expr <br> else expr");
		provider.addCompletion(c);
		
		//>
		//c = new OCLShorthandCompletion(this, ">",">",null);
		//c.setSummary("self > i<br><br>True if self is greater than i.");
		//addCompletion(c);
		
		//<
		//c = new OCLShorthandCompletion(this, "<","<",null);
		//c.setSummary("self < i<br><br>True if self is less than i.");
		//addCompletion(c);
		
		//<=
		//c = new OCLShorthandCompletion(this, "<=","<=",null);
		//c.setSummary("self <= i<br><br>True if self is less than or equal to i.");
		//addCompletion(c);
		
		//>=
		//c = new OCLShorthandCompletion(this, ">=",">=",null);
		//c.setSummary("self >= i<br><br>True if self is greater than or equal to i.");
		//addCompletion(c);
		
		//-
		//c = new OCLShorthandCompletion(this, "-","-",null);
		//c.setSummary("- self <br><br>The negative value of self.");
		//addCompletion(c);
		
		//-
		//c = new OCLShorthandCompletion(this, "-","-",null);
		//c.setSummary("self - i<br><br>The value of the subtraction of i from self.");
		//addCompletion(c);
		
		//+
		//c = new OCLShorthandCompletion(this, "+","+",null);
		//c.setSummary("self + i<br><br>The value of the addition of self and i.");
		//addCompletion(c);*/

		return provider;
	}

}
