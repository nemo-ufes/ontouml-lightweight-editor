package br.ufes.inf.nemo.ocl.editor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

import RefOntoUML.parser.OntoUMLParser;
import br.ufes.inf.nemo.ocl.editor.completion.ModelCompletionProvider;
import br.ufes.inf.nemo.ocl.editor.completion.OCLCompletionProvider;

/**
 * @author John Guerson
 */

public class OCLEditorPanel extends JPanel {

	private static final long serialVersionUID = 1277358682337723759L;
	
	public Component parent;
	public RSyntaxTextArea textArea;
	public OCLSyntaxHighlight oclSyntaxHighlight;	
	public CompletionProvider parentProvider;	
	public AutoCompletion ac;
	public RTextScrollPane scrollPane;	
	public JMenuItem saveMenuItem;
	public JMenuItem openMenuItem;
	public JMenuItem parserMenuItem;
	public DefaultCompletionProvider mainProvider;
	public OCLCompletionProvider oclProvider;
	public ModelCompletionProvider modelProvider;
	
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
   
   public void createAutoComplete (CompletionProvider provider)
   {
	    ac = new AutoCompletion(provider);	    
	    ac.setListCellRenderer(new OCLCellRenderer());	    
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
	public OCLEditorPanel ()
	{
		setBorder(new EmptyBorder(0, 0, 0, 0));
		
		textArea = new RSyntaxTextArea(5, 30);		
	    textArea.setSyntaxEditingStyle(SyntaxConstants.SYNTAX_STYLE_NONE);
	    textArea.setAntiAliasingEnabled(true);
	    textArea.setCodeFoldingEnabled(false);
		textArea.setForeground(Color.BLACK);
		textArea.setBackground(new Color(255, 255, 255));				
		setTheme(textArea,"/br/ufes/inf/nemo/ocl/editor/themes/eclipse.xml");
						
		oclSyntaxHighlight = new OCLSyntaxHighlight();	    
	    ((RSyntaxDocument)textArea.getDocument()).setSyntaxStyle(oclSyntaxHighlight);
	    
	    mainProvider = new DefaultCompletionProvider(){ 
			   @Override
			   protected boolean isValidChar(char ch) {
			      return ch=='.' || ch=='>' || super.isValidChar(ch);
			   }
			};
		mainProvider.setAutoActivationRules(true, ".");
		mainProvider.setAutoActivationRules(true, ">");
			
	    modelProvider = new ModelCompletionProvider(mainProvider);
	    oclProvider = new OCLCompletionProvider(mainProvider);
	    oclProvider.addCompletions();
	    
	    createAutoComplete(mainProvider);
	    
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
      	      	
      	getPopupMenu().add(createThemeMenu());      	
	}   
	
	/**
	 * Create a Theme edition menu.
	 * 
	 * @return
	 */
	public JMenu createThemeMenu()
	{      	
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
      	return themeMenu;
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
	
	// Completions ======================
	
	public void addCompletions(OntoUMLParser refparser)
	{   
		modelProvider.addCompletions(refparser);	   
	}
   
	public void removeCompletion(EObject elem)
	{
	    modelProvider.removeCompletion(elem);
	}
    
	public void removeAllModelCompletions()
	{
		modelProvider.removeAllCompletions();
	}
	
	public void updateCompletion(EObject elem)
	{
		modelProvider.updateCompletion(elem);
	}
}
