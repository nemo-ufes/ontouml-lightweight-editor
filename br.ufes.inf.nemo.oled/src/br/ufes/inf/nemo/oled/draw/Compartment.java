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
import java.util.ArrayList;
import java.util.List;

/**
 * A compartment represents a rectangular area which contains other diagram
 * allElements. For now these are labels, which are placed vertically. Label
 * alignment can either be centered or left aligned.
 * Note that the clone() method must be overridden, to correctly set the
 * label parents contained in the compartment.
 *
 * @author Wei-ju Wu
 */
public class Compartment extends AbstractCompositeNode
implements CompositeNode {

  private static final long serialVersionUID = 160961297210956777L;

  /**
   * The possible alignment types for labels.
   */
  public enum Alignment { LEFT, CENTERED };
  private static final double MIN_WIDTH = 80;
  private static final double MIN_HEIGHT = 10;
  private List<Label> labels = new ArrayList<Label>();
  private double marginTop = Defaults.getInstance().getMarginTop();
  private boolean isValid;
  private Alignment alignment = Alignment.CENTERED;
  private Color background = Color.WHITE;
  private double aspectToParentWidth = 1.0;

  /**
   * Constructor.
   */
  public Compartment() {
    setMinimumSize(MIN_WIDTH, MIN_HEIGHT);
    setSize(MIN_WIDTH, MIN_HEIGHT);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public Object clone() {
    Compartment cloned = (Compartment) super.clone();
    cloned.labels = new ArrayList<Label>();
    for (Label label : labels) {
      Label clonedLabel = (Label) label.clone();
      // Note that the source might have changed for the clone
      clonedLabel.setSource(label.getSource());
      clonedLabel.setParent(cloned);
      cloned.labels.add(clonedLabel);
    }
    return cloned;
  }

  /**
   * Returns the background color. The default background color is white.
   * @return the background color
   */
  public Color getBackground() { return background; }

  /**
   * Sets a background color.
   * @param color the color to set as the new background
   */
  public void setBackground(Color color) { background = color; }

  /**
   * Sets this compartment's aspect width to the parent.
   * @param aspect the aspect
   */
  public void setAspectToParentWidth(double aspect) {
    aspectToParentWidth = aspect;
  }

  /**
   * Sets the top margin.
   * @param value the top margin value
   */
  public void setMarginTop(double value) { marginTop = value; }

  /**
   * Sets an Alignment for the labels.
   * @param anAlignment an Alignment
   */
  public void setAlignment(Alignment anAlignment) { alignment = anAlignment; }

  /**
   * Adds a label.
   * @param label the label to add
   */
  public void addLabel(Label label) {
    labels.add(label);
    label.setParent(this);
  }

  /**
   * Removes all labels from this Compartment.
   */
  public void removeAllLabels() { labels.clear(); }

  /**
   * Exposes the Label list for testing purposes.
   * @return the label list
   */
  public List<Label> getLabels() { return labels; }

  /**
   * {@inheritDoc}
   */
  public void recalculateSize(DrawingContext drawingContext) {
    if (getParent().getSize().getWidth() >= getMinimumSize().getWidth()) {
      setWidth(getParent().getSize().getWidth() * aspectToParentWidth);
    }
    double y = marginTop;
    for (Label label : labels) {
      label.recalculateSize(drawingContext);
      label.setOrigin(label.getOrigin().getX(), y);
      y += label.getSize().getHeight();
    }
    double maxLabelWidth = 0;
    double verticalLabelSpace = marginTop +
                                Defaults.getInstance().getMarginBottom();
    // Now determine the initial sizes
    for (Label label : labels) {
      if (label.getSize().getWidth() > maxLabelWidth) {
        maxLabelWidth = label.getSize().getWidth();
      }
      verticalLabelSpace += label.getSize().getHeight();
    }
    double horizontalLabelSpace = maxLabelWidth +
      Defaults.getInstance().getMarginSide();
    adjustMinimumSize(horizontalLabelSpace, verticalLabelSpace);
    adjustSize(horizontalLabelSpace, verticalLabelSpace);
    isValid = true;
  }

  /**
   * Adjusts the minimum size according to the space the labels use up.
   * @param horizontalLabelSpace the horizontal space that is used by labels
   * @param verticalLabelSpace the vertical space that is used by labels
   */
  private void adjustMinimumSize(double horizontalLabelSpace,
    double verticalLabelSpace) {
    double minwidth = Math.max(horizontalLabelSpace,
      getMinimumSize().getWidth());
    double minheight = Math.max(verticalLabelSpace,
      getMinimumSize().getHeight());
    double width = Math.max(minwidth, getSize().getWidth());
    double height = Math.max(minheight, getSize().getHeight());
    setMinimumSize(minwidth, minheight);
    setSizePlain(width, height);
  }

  /**
   * Adjusts the size of the compartment.
   * @param horizontalLabelSpace the horizontal space that is used by labels
   * @param verticalLabelSpace the vertical space that is used by labels
   */
  private void adjustSize(double horizontalLabelSpace,
    double verticalLabelSpace) {
    // only change the size if the label space is larger than the
    // original one
    double width = Math.max(horizontalLabelSpace, getSize().getWidth());
    width = Math.max(width, getMinimumSize().getWidth());
    double height = Math.max(getSize().getHeight(), verticalLabelSpace);
    height = Math.max(height, getMinimumSize().getHeight());
    setSizePlain(width, height);
  }

  /**
   * {@inheritDoc}
   */
  public void draw(DrawingContext drawingContext) {
    if (!isValid()) {
      recalculateSize(drawingContext);
    }
    drawingContext.drawRectangle(getAbsoluteX1(), getAbsoluteY1(),
      getSize().getWidth(), getSize().getHeight(), background);
    for (Label label : labels) {
      // for now, we always center the labels
      if (alignment == Alignment.CENTERED) label.centerHorizontally();
      else if (alignment == Alignment.LEFT) {
        label.setOrigin(Defaults.getInstance().getMarginLeft(),
                        label.getOrigin().getY());
      }
      label.draw(drawingContext);
    }
    // to draw the children
    super.draw(drawingContext);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void invalidate() {
    isValid = false;
    for (Label label : labels) {
      label.invalidate();
    }
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean isValid() {
    for (Label label : labels) {
      if (!label.isValid()) return false;
    }
    return isValid;
  }

  /**
   * {@inheritDoc}
   */
  public Label getLabelAt(double mx, double my) {
    for (Label label : labels) {
      if (label.contains(mx, my)) return label;
    }
    return null;
  }
}
