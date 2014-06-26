/**
 * Copyright(C) 2011-2014 by John Guerson, Tiago Prince, Antognoni Albuquerque
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * license terms.
 *
 * OLED is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * OLED is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with OLED; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.ufes.inf.nemo.oled.ui.diagram;

import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import br.ufes.inf.nemo.oled.draw.Label;
import br.ufes.inf.nemo.oled.palette.ColorPalette;
import br.ufes.inf.nemo.oled.palette.ColorPalette.ThemeColor;

/**
 * This class acts as an in-editor text input element.
 *
 * @author Wei-ju Wu
 */
public class CaptionEditor extends JTextField
implements DocumentListener, BaseTextEditor {

	
	private static final long serialVersionUID = 4249755381822809715L;
	
	private Label currentLabel;
	@SuppressWarnings("unused")
	private DiagramEditor editor;
	
	/**
	 * Constructor. Initially hidden.
	 */
	public CaptionEditor(DiagramEditor editor) {
		super();
		this.editor=editor;
		setBackground(ColorPalette.getInstance().getColor(ThemeColor.GREEN_LIGHTEST));
		Border border = new CompoundBorder(new LineBorder(ColorPalette.getInstance().getColor(ThemeColor.GREEN_DARK), 1), new EmptyBorder(1, 3, 1, 1));	
		setBorder(border);
		hideEditor();
		getDocument().addDocumentListener(this);		
	}

	/**
	 * {@inheritDoc}
	 */
	public Label getLabel() { return currentLabel; }

	/**
	 * {@inheritDoc}
	 */
	public void hideEditor() {
		setEditable(false);
		setEnabled(false);
		setVisible(false);
	}

	/**
	 * {@inheritDoc}
	 */
	public void showEditor(Label aLabel, Graphics g) {
		currentLabel = aLabel;
		String text = currentLabel.getNameLabelText();
		int width = g.getFontMetrics().stringWidth(text);
		width += (int) (width * 0.3) + 10;
		int height = g.getFontMetrics().getHeight() + 4;	
		setText(text);
		setSize(width, height);
		setLocation((int) aLabel.getAbsoluteX1(), (int) aLabel.getAbsoluteY1());
		setEditable(true);
		setEnabled(true);
		setVisible(true);
		requestFocusInWindow();
		selectAll();
	}

	/**
	 * {@inheritDoc}
	 */
	public void insertUpdate(DocumentEvent e) {
		String text = getText();
		FontMetrics fm = getGraphics().getFontMetrics();
		int width = fm.stringWidth(text);		
		if(width>getWidth()) setSize(width + 5, getHeight());
	}

	/**
	 * {@inheritDoc}
	 */
	public void removeUpdate(DocumentEvent e) { }

	/**
	 * {@inheritDoc}
	 */
	public void changedUpdate(DocumentEvent e) { }

//	@Override
//	public void actionPerformed(ActionEvent arg0) {
//		String text = getText();
//		FontMetrics fm = getGraphics().getFontMetrics();
//		int width = fm.stringWidth(text);
//		if(currentLabel.getSource() instanceof ClassElement){				
//			currentLabel.setSize(width + 5, getHeight());
//			ClassElement ce = (ClassElement)currentLabel.getSource();
//			ce.recalculateSize(editor.getDrawingContext());
//			ce.setSize(width + 5, ce.getSize().getHeight());		
//			ce.setMinimumSize(width + 5, ce.getSize().getHeight());
//			ResizeElementCommand re= new ResizeElementCommand(editor, ce, ce.getOrigin(), new DoubleDimension(width+5, ce.getSize().getHeight()));
//			re.run();		
//			System.out.println("action performed");
//		}
//	}
}
