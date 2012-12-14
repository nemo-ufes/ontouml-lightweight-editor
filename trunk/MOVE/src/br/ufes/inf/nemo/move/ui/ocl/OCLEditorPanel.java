package br.ufes.inf.nemo.move.ui.ocl;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxDocument;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import br.ufes.inf.nemo.move.ui.TheFrame;
import br.ufes.inf.nemo.move.utilities.ColorPalette;
import br.ufes.inf.nemo.move.utilities.ColorPalette.ThemeColor;

/**
 * @author John Guerson
 */

public class OCLEditorPanel extends JPanel {

	private static final long serialVersionUID = 1277358682337723759L;
	
	public TheFrame frame;
	public RSyntaxTextArea textArea;
	public OCLTokenMaker tokenMaker;
	public OCLCompletionProvider provider;
	public AutoCompletion ac;
	public RTextScrollPane scrollPane;
	
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
    * Constructor
    * 
    * @param frame
    */
   public OCLEditorPanel(TheFrame frame)
   {
	   this();
	   
	   this.frame = frame;
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
    * Initialize Text Area
    */   
   public void initializeTextArea()
   {
	    textArea = new RSyntaxTextArea(5, 30);		
	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
	    textArea.setAntiAliasingEnabled(true);
	    textArea.setCodeFoldingEnabled(false);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(new Color(255, 255, 255));				
		textArea.setCurrentLineHighlightColor(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));		
		setFont(textArea,new Font("Consolas", Font.PLAIN, 11));		
		setTheme(textArea,"/br/ufes/inf/nemo/move/ui/ocl/EclipseTheme.xml");
   }
   
	/**
	 * Constructor.
	 */
	public OCLEditorPanel ()
	{
		initializeTextArea();
			         
		tokenMaker = new OCLTokenMaker();	    
	    ((RSyntaxDocument)textArea.getDocument()).setSyntaxStyle(tokenMaker);
	       
	    provider = new OCLCompletionProvider();
	    addOCLCompletionsToProvider();
	    
	    ac = new AutoCompletion(provider);      	
	    ac.install(textArea);
	    ac.setAutoActivationEnabled(true);
      	ac.setShowDescWindow(true);      	
      	setLayout(new BorderLayout(0, 0));
      	
      	scrollPane = new RTextScrollPane(textArea);
      	scrollPane.getGutter().setLineNumberColor(Color.GRAY);
      	scrollPane.getTextArea().setRows(5);
      	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
      	
      	setPreferredSize(new Dimension(400, 100));
      	add(scrollPane); 
	}
	    
	/**
	 * Set Parent.
	 *
	 * @param frame
	 */
	public void setParent (TheFrame frame)
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
	public void getText()
	{
		textArea.getText();
	}	
	
    /**
     * Create a simple provider that adds some related completions.
     * 
     * @return The completion provider.
     */
    private void addOCLCompletionsToProvider() 
    {
    	provider.setAutoActivationRules(false, ".");
    	provider.setAutoActivationRules(false, "->");
    	
    	//package declaration
    	OCLShorthandCompletion c = new OCLShorthandCompletion(provider, "Package","package PackageName\n\n\nendpackage","package declaration");
    	c.setSummary("package PackageName<br>...<br>endpackage");
    	provider.addCompletion(c);    	
    	
    	//context declaration
    	c = new OCLShorthandCompletion(provider, "Context","context type\ninv : true","context declaration");
    	c.setSummary("context type<br> inv invariantname: true<br>");
    	provider.addCompletion(c);    	

    	//property context declaration
    	c = new OCLShorthandCompletion(provider, "Property Context","context type::property : propertyType\nderive: null","derive property context declaration");
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
    	c = new OCLShorthandCompletion(provider, "-","-",null);
    	c.setSummary("-(s: Set(T))<br><br>The elements of self, which are not in s.");
    	provider.addCompletion(c);

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
    	c.setSummary("<><br><br>True if self is a different object from object2. Infix operator.");
    	provider.addCompletion(c);
    	
        //=
    	c = new OCLShorthandCompletion(provider, "=","=",null);
    	c.setSummary("=<br><br>True if self is the same object as object2. Infix operator.");
    	provider.addCompletion(c);    	

       
       //provider.addCompletion(new ShorthandCompletion(provider, "."));
       /*provider.addCompletion(new ShorthandCompletion(provider, "->"));
       provider.addCompletion(new ShorthandCompletion(provider, "::"));       
              
       provider.addCompletion(new BasicCompletion(provider, "or","True if either self or b is true."));
       provider.addCompletion(new BasicCompletion(provider, "and","True if both b1 and b are true."));
       provider.addCompletion(new BasicCompletion(provider, "not","True if self is false."));
       provider.addCompletion(new BasicCompletion(provider, "implies","True if self is false, or if self is true and b is true."));
       provider.addCompletion(new BasicCompletion(provider, "xor","True if either self or b is true, but not both."));
       provider.addCompletion(new BasicCompletion(provider, "<","True if self is less than i."));
       provider.addCompletion(new BasicCompletion(provider, ">","True if self is greater than i."));
       provider.addCompletion(new BasicCompletion(provider, "<=","True if self is less than or equal to i."));
       provider.addCompletion(new BasicCompletion(provider, ">=","True if self is greater than or equal to i."));       
       provider.addCompletion(new BasicCompletion(provider, "-","The negative value of self."));
       provider.addCompletion(new BasicCompletion(provider, "-","The value of the subtraction of i from self."));
       provider.addCompletion(new BasicCompletion(provider, "+","The value of the addition of self and i."));
      
       provider.addCompletion(new BasicCompletion(provider, "exists"));
       provider.addCompletion(new BasicCompletion(provider, "forAll"));
       provider.addCompletion(new BasicCompletion(provider, "one"));
       provider.addCompletion(new BasicCompletion(provider, "select"));
       provider.addCompletion(new BasicCompletion(provider, "reject"));
       provider.addCompletion(new BasicCompletion(provider, "isUnique"));
       provider.addCompletion(new BasicCompletion(provider, "collect"));
       
       provider.addCompletion(new BasicCompletion(provider, "let x = in "));
       
       provider.addCompletion(new BasicCompletion(provider, "if then else "));
       */       
    }

}
