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
package br.ufes.inf.nemo.oled.draw;

import java.awt.FontMetrics;

import br.ufes.inf.nemo.oled.draw.DrawingContext.FontType;

/**
 * Default label implementation.
 * 
 * @author Wei-ju Wu
 */
public class SimpleLabel extends AbstractNode implements Label {

	private static final long serialVersionUID = 45848345687785401L;
	private LabelSource source;
	private FontType fontType = FontType.DEFAULT;
	private boolean valid;
	private boolean editable;
	
	/**
	 * {@inheritDoc}
	 */
	public void setSource(LabelSource aSource) {
		source = aSource;
	}

	/**
	 * {@inheritDoc}
	 */
	public LabelSource getSource() {
		return source;
	}

	/**
	 * {@inheritDoc}
	 */
	public String getNameLabelText() {
		return source.getLabelText();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setNameLabelText(String text) {
		source.setLabelText(text);
		invalidate();
	}

	/**
	 * {@inheritDoc}
	 */
	public void setFontType(FontType aFontType) {
		fontType = aFontType;
	}

	public FontType getFontType() { return fontType; } 
	
	/**
	 * {@inheritDoc}
	 */
	public void draw(DrawingContext drawingContext) {
		if (!isValid()) {
			recalculateSize(drawingContext);
		}
		drawingContext.drawLabel(getNameLabelText(), getAbsoluteX1(), getAbsoluteY1() + drawingContext.getFontMetrics(fontType).getMaxAscent(), fontType);
	}

	/**
	 * {@inheritDoc}
	 */
	public void recalculateSize(DrawingContext drawingContext) {
		FontMetrics fm = drawingContext.getFontMetrics(fontType);
		
		String text = getNameLabelText();
		
		if (text!=null) setSize(fm.stringWidth(text), fm.getHeight());
		else setSize(0,fm.getHeight());
		
		valid = true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid() {
		return valid;
	}

	/**
	 * {@inheritDoc}
	 */
	protected void setValid(boolean flag) {
		valid = flag;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void invalidate() {
		valid = false;
	}

	/**
	 * {@inheritDoc} This is only needed if we choose to not move the labels in the diagram.
	 */
	@Override
	public Selection getSelection(DiagramOperations operations) {
		return NullSelection.getInstance();
	}

	/**
	 * {@inheritDoc}
	 */
	public void centerHorizontally() {
		double lwidth = getSize().getWidth();
		double centerx = (getParent().getSize().getWidth()) / 2;
		setOrigin(centerx - (lwidth / 2), getOrigin().getY());
	}

	/**
	 * {@inheritDoc}
	 */
	public Label getLabelAt(double mx, double my) {
		if (contains(mx, my))
			return this;
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * {@inheritDoc}
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}
}
