package br.ufes.inf.nemo.oled.ui.popup;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import br.ufes.inf.nemo.oled.ui.AppFrame;

public class DiagramPopupMenu extends JPopupMenu {

	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private AppFrame frame;
	JMenuItem verifyItem = new JMenuItem("Verify Model (Syntactically)");
	JMenuItem inferenceItem = new JMenuItem("Infer Relationships");
	
	public DiagramPopupMenu(final AppFrame frame)
    {        
    	this.frame = frame;
    	add(verifyItem);
    	addSeparator();
    	add(inferenceItem);
    	addSeparator();
    	
    	verifyItem.setToolTipText("Check model against metamodel's syntactic constraints");
    	verifyItem.setIcon(new ImageIcon(DiagramPopupMenu.class.getResource("/resources/br/ufes/inf/nemo/oled/ui/validate.png")));
    	inferenceItem.setToolTipText("Automatically derive relationships such as material, derivation and meronymic relationships");
    	    	
    	inferenceItem.addMouseListener(new MouseAdapter()
	    {
    		@Override
    		public void mousePressed(MouseEvent e) 
    		{   
    			frame.getDiagramManager().deriveRelations();
    		}
	    });
    	
    	verifyItem.addMouseListener(new MouseAdapter()
	    {
    		@Override
    		public void mousePressed(MouseEvent e) 
    		{   
    			frame.getDiagramManager().validateCurrentModel();
    		}
	    });
    }
}
