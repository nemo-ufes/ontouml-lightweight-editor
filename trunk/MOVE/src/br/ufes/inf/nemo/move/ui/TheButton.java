package br.ufes.inf.nemo.move.ui;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import br.ufes.inf.nemo.move.util.ColorPalette;
import br.ufes.inf.nemo.move.util.ColorPalette.ThemeColor;

/**
 * @author John Guerson
 */

public class TheButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;

	public TheButton (String name, String path)
	{
		this();
		setText(name);		
		setIcon(new ImageIcon(TheButton.class.getResource(path)));
	}
	
	public TheButton ()
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
		//setBorder(new LineBorder(ColorPalette.getInstance().getColor(ThemeColor.GREEN_DARK),1,true));
		repaint();
	}	

	@Override
	public void mouseExited(MouseEvent e) {
		setBackground(new Color(240,240,240));
		//setBorder(null);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}
