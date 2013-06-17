package br.ufes.inf.nemo.ontouml.editor.ui.model;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ToolboxPopupMenuListener extends MouseAdapter {
	
    public void mousePressed(MouseEvent e)
    {
        if (e.isPopupTrigger()) doPop(e);
    }

    public void mouseReleased(MouseEvent e)
    {
        if (e.isPopupTrigger()) doPop(e);
    }

    private void doPop(MouseEvent e)
    {
        ToolboxPopupMenu menu = new ToolboxPopupMenu();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }	
}
