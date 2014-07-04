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

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

/**
 * This class provides a very thin abstraction on Java2D and groups it together
 * with the global drawing settings.
 * Serialization note: DrawingContext should not be serialized.
 *
 * @author Wei-ju Wu
 */
public class DrawingContextImpl implements DrawingContext {

	// This is the standard font for the component.
	private static final Font DEFAULT_FONT = new Font("Arial", Font.PLAIN, 12);
	private static final Font ELEMENT_NAME_FONT =
		new Font("Arial", Font.BOLD, 12);
	private static final Font ABSTRACT_ELEMENT_FONT =
		new Font("Arial", Font.BOLD | Font.ITALIC, 12);
	private static final Font SMALL_FONT = new Font("Arial", Font.BOLD, 10);

	private Graphics2D g2d;
	private DrawingShapeFactory shapeFactory = DrawingShapeFactory.getInstance();
	private Color gridColor = new Color(230, 230, 230);
	private Rectangle bounds = new Rectangle();
	private StrokeType strokeType = StrokeType.DEFAULT;

	/**
	 * {@inheritDoc}
	 */
	public void setGraphics2D(Graphics2D aG2d, Rectangle theBounds) {
		this.g2d = aG2d;
		this.bounds = theBounds;
	}

	/**
	 * {@inheritDoc}
	 */
	public void drawDashedLine(double x0, double y0, double x1, double y1) {
		if(strokeType == StrokeType.DASHED_BOLD)
			g2d.setStroke(shapeFactory.getDashedStrokeBold());
		else
			g2d.setStroke(shapeFactory.getDashedStroke());
		g2d.setColor(Color.BLACK);
		Line2D line = shapeFactory.createLine2d(x0, y0, x1, y1);
		g2d.draw(line);
	}

	/**
	 * {@inheritDoc}
	 */
	public void drawGridLine(double x0, double y0, double x1, double y1) {
		g2d.setColor(gridColor);
		g2d.setStroke(shapeFactory.createGridStroke());
		g2d.draw(shapeFactory.createLine2d(x0, y0, x1, y1));
	}

	/**
	 * {@inheritDoc}
	 */
	public void drawLine(double x0, double y0, double x1, double y1) {
		g2d.setStroke(shapeFactory.getStandardStroke());
		g2d.setColor(Color.BLACK);
		Line2D line = shapeFactory.createLine2d(x0, y0, x1, y1);
		g2d.draw(line);
	}

	// *************************************************************************
	// ****** Rectangles
	// **********************************

	/**
	 * {@inheritDoc}
	 */
	public void drawRectangle(double x, double y, double width, double height,
			Color fillColor) {
		drawRectangle(x, y, width, height, null, fillColor);
	}

	/**
	 * {@inheritDoc}
	 */
	public void drawRectangle(double x, double y, double width, double height,
			Color strokeColor, Color fillColor) {
		g2d.setStroke(shapeFactory.getStandardStroke());
		Rectangle2D rect = shapeFactory.createRect2d(x, y, width, height);
		if (fillColor != null) {
			g2d.setColor(fillColor);
			g2d.fill(rect);
		}
		g2d.setColor(strokeColor == null ? Color.BLACK : strokeColor);
		g2d.draw(rect);
	}

	/**
	 * {@inheritDoc}
	 */
	public void fillRectangle(double x, double y, double width, double height,
			Color fillColor) {
		g2d.setColor(fillColor);
		g2d.fill(shapeFactory.createRect2d(x, y, width, height));
	}

	/**
	 * {@inheritDoc}
	 */
	public void drawRubberband(double x, double y, double width, double height) {
		g2d.setColor(Color.BLACK);
		g2d.setStroke(shapeFactory.getDashedStroke());
		g2d.draw(shapeFactory.createRect2d(x, y, width, height));
	}

	/**
	 * {@inheritDoc}
	 */
	public Rectangle getClipBounds() {
		return bounds;
	}

	/**
	 * {@inheritDoc}
	 */
	public void draw(Shape shape, Color fillColor) {
		if(shape != null)
		{
			g2d.setColor(fillColor);
			g2d.fill(shape);
			g2d.setColor(Color.BLACK);
			g2d.setStroke(shapeFactory.getStandardStroke());
			g2d.draw(shape);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void draw(Shape shape, Color fillColor, char content, Color fontColor, FontType fontType) {
		if(shape != null)
		{
			g2d.setColor(fillColor);
			g2d.fill(shape);
			g2d.setColor(Color.BLACK);
			g2d.setStroke(shapeFactory.getStandardStroke());
			g2d.draw(shape);
		}
		if(content != '\u0000')
		{
			g2d.setColor(fontColor);
			g2d.setFont(getFont(fontType));

			int x = (int) Math.floor(shape.getBounds2D().getCenterX() - (g2d.getFontMetrics().stringWidth(String.valueOf(content))/2)) ; //Fine tunning

			int y = (int) Math.floor(shape.getBounds2D().getCenterY() + (g2d.getFontMetrics().getHeight() / 2) - 2); //Fine tunning

			g2d.drawString(String.valueOf(content), x , y);
			g2d.setColor(Color.BLACK);
		}
	}

	// ***********************************************************************
	// ***** Drawing text
	// ******************************************

	/**
	 * {@inheritDoc}
	 */
	public void drawLabel(String text, double x, double y, FontType fontType) {
		g2d.setFont(getFont(fontType));
		
		if(text==null)
			text = "";
		
		g2d.drawString(text, (float) x, (float) y);
	}

	/**
	 * {@inheritDoc}
	 */
	public void drawLabel(String text, double x, double y, FontType fontType, Color fontColor) {
		g2d.setColor(fontColor);
		g2d.setFont(getFont(fontType));
		g2d.drawString(text, (float) x, (float) y);
		g2d.setColor(Color.BLACK);//Reset to default
	}

	/**
	 * {@inheritDoc}
	 */
	public Font getFont(FontType fontType) {
		switch (fontType) {
		case ELEMENT_NAME: return ELEMENT_NAME_FONT;
		case ABSTRACT_ELEMENT: return ABSTRACT_ELEMENT_FONT;
		case SMALL: return SMALL_FONT;
		case DEFAULT: default: return DEFAULT_FONT;
		}
	}

	/**
	 * {@inheritDoc}
	 */
	public FontMetrics getFontMetrics(FontType fontType) {
		return g2d.getFontMetrics(getFont(fontType));
	}

	/**
	 * {@inheritDoc}
	 */
	public Graphics2D getGraphics2D() { return g2d; }

	public void setStrokeType(StrokeType strokeType) {
		this.strokeType = strokeType;
	}

	public StrokeType getStrokeType() {
		return strokeType;
	}
	}
