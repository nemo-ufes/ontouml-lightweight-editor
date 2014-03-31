package br.ufes.inf.nemo.oled.palette;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public 
class PaletteElement extends JPanel implements MouseListener
{

	private static final long serialVersionUID = 5550202293825101613L;
	private boolean selected = false;
	private Palette parent = null;
	private String command = ""; 

	public PaletteElement(Icon icon, String caption, String command, Palette palette)
	{
		super();

		this.command = command;
		this.parent = palette;

		this.setMaximumSize(new Dimension(32767, 24));
		this.setSize(new Dimension(200, 24));
		this.setPreferredSize(new Dimension(200, 24));
		this.setMinimumSize(new Dimension(0, 24));
		
		
		this.setBorder(PaletteAccordion.getResetBorder());
		this.setBackground(PaletteAccordion.getResetBackground());

		this.setLayout(new BorderLayout());

		JLabel label = new JLabel(caption, icon, JLabel.LEFT);
		label.setIconTextGap(10);
		label.setBorder(BorderFactory.createEmptyBorder(1, 10, 1, 1));
		
		this.add(label, BorderLayout.CENTER);
		this.addMouseListener(this);
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
		if(selected)
		{
			setSelectedStyle();
			parent.NotifySelection(this);
		}
		else
		{
			resetStyle();
		}
	}

	public boolean isSelected() {
		return selected;
	}

	private void setSelectedStyle()
	{
		setBackground(PaletteAccordion.getSelectedItemBackground());
		setBorder(PaletteAccordion.getSelectedItemBorder());
		repaint();
	}

	private void setHoverStyle()
	{
		setBackground(PaletteAccordion.getHoverItemBackground());
		setBorder(PaletteAccordion.getHoverItemBorder());
		repaint();
	}

	private void resetStyle()
	{
		setBackground(PaletteAccordion.getResetBackground());
		setBorder(PaletteAccordion.getResetBorder());
		repaint();
	}

	public String getCommand() {
		return command;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		setSelected(true);		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		if(!selected)
			setHoverStyle();
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if(!selected)
			resetStyle();
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}