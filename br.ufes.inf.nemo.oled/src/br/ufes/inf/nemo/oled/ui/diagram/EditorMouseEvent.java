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

import java.awt.event.MouseEvent;

import br.ufes.inf.nemo.oled.draw.Scaling;

/**
 * This class represents an editor specific mouse event. The properties of the
 * original MouseEvent are preserved by wrapping and delegating most
 * functionality to it.
 *
 * @author Wei-ju Wu
 */
public class EditorMouseEvent {
  private MouseEvent event;
  private Scaling scaling;

  /**
   * Sets the mouse event and current editor scaling.
   * @param anEvent the MouseEvent
   * @param aScaling the Scaling
   */
  public void setMouseEvent(MouseEvent anEvent, Scaling aScaling) {
    this.event = anEvent;
    this.scaling = aScaling;
  }

  /**
   * Returns the original mouse event.
   * @return the original mouse event
   */
  public MouseEvent getMouseEvent() { return event; }

  /**
   * Returns the x coordinate in the editor coordinate system.
   * @return the x coordinate
   */
  public double getX() {
    return scaling ==  Scaling.SCALING_100 ? event.getX() :
      event.getX()/scaling.getScaleFactor();
  }

  public boolean isScaling100()
  {
	  return scaling == Scaling.SCALING_100;	  
  }
  
  /**
   * Returns the y coordinate in the editor coordinate system.
   * @return the y coordinate
   */
  public double getY() {
    return scaling ==  Scaling.SCALING_100 ? event.getY() :
    	event.getY()/scaling.getScaleFactor();
  }

  /**
   * Returns the click count.
   * @return the click count
   */
  public int getClickCount() { return event.getClickCount(); }

  /**
   * Determines if the mouse button pressed was the popup trigger.
   * Note that this function only returns the correct value on a mouseReleased
   * event (at least on Windows)
   * @return true if popup trigger, false otherwise
   */
  public boolean isPopupTrigger() { return event.isPopupTrigger(); }

  /**
   * Returns true if the event was triggered by the main button. This is to
   * compensate that we can not rely on isPopupTrigger().
   * @return true if main button, false otherwise
   */
  public boolean isMainButton() {
    return event.getButton() == MouseEvent.BUTTON1;
  }
}
