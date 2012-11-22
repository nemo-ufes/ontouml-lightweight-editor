package br.ufes.inf.nemo.move.util;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import br.ufes.inf.nemo.move.util.ColorPalette.ThemeColor;



/**
 * @author John Guerson
 */

public class ToolbarButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;

	public ToolbarButton (String name, String path)
	{
		this();
		setText(name);		
		setIcon(new ImageIcon(ToolbarButton.class.getResource(path)));
		setVerticalTextPosition(SwingConstants.BOTTOM);
		setHorizontalTextPosition(SwingConstants.CENTER);
	}
	
	public ToolbarButton ()
	{
		super();
		setFocusable(false);
		this.addMouseListener(this);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {	
		
	}

	@Override
	public void mouseEntered(MouseEvent e) 
	{		
		setBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHT));
		repaint();
	}	

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(new Color(240,240,240));
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}
	

}
