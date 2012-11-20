package br.ufes.inf.nemo.move.ocl.editor;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;

import org.fife.ui.autocomplete.AutoCompletion;
import org.fife.ui.autocomplete.BasicCompletion;
import org.fife.ui.autocomplete.CompletionProvider;
import org.fife.ui.autocomplete.DefaultCompletionProvider;
import org.fife.ui.autocomplete.FunctionCompletion;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxConstants;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Theme;
import org.fife.ui.rtextarea.RTextScrollPane;

import br.ufes.inf.nemo.move.util.ColorPalette;
import br.ufes.inf.nemo.move.util.ColorPalette.ThemeColor;

import java.awt.Color;
import java.io.IOException;

/**
 * @author John Guerson
 */

public class OCLEditorPanel extends JPanel {

	private static final long serialVersionUID = 1277358682337723759L;
	
	public RSyntaxTextArea textArea;
	public CompletionProvider provider;
	public AutoCompletion ac;
	public RTextScrollPane scrollPane;
	
	/**
	 * Set Text.
	 * 
	 * @param text
	 */
	public void setText(String text)
	{
		textArea.setText(text);
	}
	
	public void getText()
	{
		textArea.getText();
	}
	
	/**
	* Changes the styles used by the editor via an XML file specification. This
	* method is preferred because of its ease and modularity.
	*/
	public void changeStyleViaThemeXml() {
		try {
	      Theme theme = Theme.load(getClass().getResourceAsStream(
	            "eclipse_theme.xml"));
	      theme.apply(textArea);
		} catch (IOException ioe) { // Never happens
	      ioe.printStackTrace();
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
   public void setFont(RSyntaxTextArea textArea, Font font) {
      if (font != null) {
         SyntaxScheme ss = textArea.getSyntaxScheme();
         ss = (SyntaxScheme) ss.clone();
         for (int i = 0; i < ss.getStyleCount(); i++) {
            if (ss.getStyle(i) != null) {
               ss.getStyle(i).font = font;
            }
         }
         textArea.setSyntaxScheme(ss);
         textArea.setFont(font);
      }
   }
	   
	/**
	 * Constructor.
	 */
	public OCLEditorPanel ()
	{
		textArea = new RSyntaxTextArea(5, 30);
	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
	    textArea.setAntiAliasingEnabled(true);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(new Color(255, 255, 255));		
		setFont(textArea,new Font("Consolas", Font.PLAIN, 11));		
		textArea.setCurrentLineHighlightColor(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));
		
		provider = createCompletionProvider();
	     
	    ac = new AutoCompletion(provider);
      	ac.install(textArea);
      	setLayout(new BorderLayout(0, 0));

      	scrollPane = new RTextScrollPane(textArea);
      	scrollPane.getGutter().setLineNumberColor(Color.GRAY);
      	scrollPane.getTextArea().setRows(5);
      	scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
      	
      	setPreferredSize(new Dimension(400, 100));
      	add(scrollPane);      	      	
	}
	    
    /**
     * Create a simple provider that adds some related completions.
     * 
     * @return The completion provider.
     */
    private CompletionProvider createCompletionProvider() {

       DefaultCompletionProvider provider = new DefaultCompletionProvider();

       provider.addCompletion(new BasicCompletion(provider, "context"));
       provider.addCompletion(new BasicCompletion(provider, "inv"));
       provider.addCompletion(new BasicCompletion(provider, "derive"));
       provider.addCompletion(new BasicCompletion(provider, "."));
       provider.addCompletion(new BasicCompletion(provider, "->"));
       provider.addCompletion(new BasicCompletion(provider, "self"));
       provider.addCompletion(new BasicCompletion(provider, "and"));
       provider.addCompletion(new BasicCompletion(provider, "or"));
       provider.addCompletion(new BasicCompletion(provider, "xor"));
       provider.addCompletion(new BasicCompletion(provider, "implies"));
       provider.addCompletion(new FunctionCompletion(provider, "asSet()", "Set(T)"));
       
       return provider;
    }

}
