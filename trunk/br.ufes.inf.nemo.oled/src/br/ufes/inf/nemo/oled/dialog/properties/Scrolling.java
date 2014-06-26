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
package br.ufes.inf.nemo.oled.dialog.properties;

import java.awt.Insets;
import java.awt.Rectangle;

import javax.swing.JComponent;

public abstract class Scrolling
{
    public static final int
        VIEWPORT = 0,       // take the policy of the viewport
        UNCHANGED = 1,      // don't scroll if it fills the visible area, otherwise take the policy of the viewport
        FIRST = 2,          // scroll the first part of the region into view 
        CENTER = 3,         // center the region
        LAST = 4;           // scroll the last part of the region into view

    public static final int
        NONE = 0,
        TOP = 1,
        VCENTER = 2,
        BOTTOM = 4,
        LEFT = 8,
        HCENTER = 16,
        RIGHT = 32;


    private static final Insets
        EMPTY_INSETS = new Insets(0, 0, 0, 0);


    private Scrolling()
    {
    }
    
    public static void scroll(JComponent c, int part)
    {
        scroll(c, part & (LEFT|HCENTER|RIGHT), part & (TOP|VCENTER|BOTTOM));
    }
    
    public static void scroll(JComponent c, int horizontal, int vertical)
    {
        Rectangle visible = c.getVisibleRect();
        Rectangle bounds = c.getBounds();
        
        switch (vertical)
        {
            case TOP:     visible.y = 0; break;
            case VCENTER: visible.y = (bounds.height - visible.height) / 2; break;
            case BOTTOM:  visible.y = bounds.height - visible.height; break;
        }

        switch (horizontal)
        {
            case LEFT:    visible.x = 0; break;
            case HCENTER: visible.x = (bounds.width - visible.width) / 2; break;
            case RIGHT:   visible.x = bounds.width - visible.width; break;
        }

        c.scrollRectToVisible(visible);
    }


    /*---------------------------------------------------------------
      Scrolling with bias.
    */
    public static void scroll(JComponent c, Rectangle r, int bias)
    {
        scroll(c, r, bias, bias);
    }

    public static void scroll(JComponent c, Rectangle r, int horizontalBias, int verticalBias)
    {
        Rectangle visible = c.getVisibleRect(),
            dest = new Rectangle(r);

        if (dest.width > visible.width)
        {
            if (horizontalBias == VIEWPORT)
            {
                // leave as is
            }
            else if (horizontalBias == UNCHANGED)
            {
                if (dest.x <= visible.x && dest.x + dest.width >= visible.x + visible.width)
                {
                    dest.x = visible.x;
                    dest.width = visible.width;
                }
            }
            else
            {
                if (horizontalBias == CENTER)
                    dest.x += (dest.width - visible.width) / 2;
                else if (horizontalBias == LAST)
                    dest.x += dest.width - visible.width;
                
                dest.width = visible.width;
            }
        }
        
        if (dest.height > visible.height)
        {
            if (verticalBias == VIEWPORT)
            {
                // leave as is
            }
            else if (verticalBias == UNCHANGED)
            {
                if (dest.y <= visible.y && dest.y + dest.height >= visible.y + visible.height)
                {
                    dest.y = visible.y;
                    dest.height = visible.height;
                }
            }
            else
            {
                if (verticalBias == CENTER)
                    dest.y += (dest.height - visible.height) / 2;
                else if (verticalBias == LAST)
                    dest.y += dest.height - visible.height;
                
                dest.height = visible.height;
            }
        }
        
        if (!visible.contains(dest))
            c.scrollRectToVisible(dest);
    }
 

    /*--------------------------------------------------------
      One-direction scrolling.
    */

    public static void scrollHorizontally(JComponent c, Rectangle r)
    {
        scrollHorizontally(c, r.x, r.x + r.width);
    }

    public static void scrollHorizontally(JComponent c, int from, int to)
    {
        Rectangle visible = c.getVisibleRect();

        if (visible.x <= from && visible.x + visible.width >= to)
            return;
        
        visible.x = from;
        visible.width = to - from;
        
        c.scrollRectToVisible(visible);
    }

    public static void scrollHorizontally(JComponent c, Rectangle r, int bias)
    {
        scrollHorizontally(c, r.x, r.x + r.width, bias);
    }

    public static void scrollHorizontally(JComponent c, int from, int to, int bias)
    {
        Rectangle visible = c.getVisibleRect(),
            dest = new Rectangle(visible);
            
        dest.x = from;
        dest.width = to - from;
        
        if (dest.width > visible.width)
        {
            if (bias == VIEWPORT)
            {
                // leave as is
            }
            else if (bias == UNCHANGED)
            {
                if (dest.x <= visible.x && dest.x + dest.width >= visible.x + visible.width)
                {
                    dest.x = visible.x;
                    dest.width = visible.width;
                }
            }
            else
            {
                if (bias == CENTER)
                    dest.x += (dest.width - visible.width) / 2;
                else if (bias == LAST)
                    dest.x += dest.width - visible.width;
                
                dest.width = visible.width;
            }
        }
        
        if (!visible.contains(dest))
            c.scrollRectToVisible(dest);
    }



    public static void scrollVertically(JComponent c, Rectangle r)
    {
        scrollVertically(c, r.y, r.y + r.height);
    }

    public static void scrollVertically(JComponent c, int from, int to)
    {
        Rectangle visible = c.getVisibleRect();

        if (visible.y <= from && visible.y + visible.height >= to)
            return;
        
        visible.y = from;
        visible.height = to - from;
        
        c.scrollRectToVisible(visible);
    }

    public static void scrollVertically(JComponent c, Rectangle r, int bias)
    {
        scrollVertically(c, r.y, r.y + r.height, bias);
    }

    public static void scrollVertically(JComponent c, int from, int to, int bias)
    {
        Rectangle visible = c.getVisibleRect(),
            dest = new Rectangle(visible);
            
        dest.y = from;
        dest.height = to - from;
        
        if (dest.height > visible.height)
        {
            if (bias == VIEWPORT)
            {
                // leave as is
            }
            else if (bias == UNCHANGED)
            {
                if (dest.y <= visible.y && dest.y + dest.height >= visible.y + visible.height)
                {
                    dest.y = visible.y;
                    dest.height = visible.height;
                }
            }
            else
            {
                if (bias == CENTER)
                    dest.y += (dest.height - visible.height) / 2;
                else if (bias == LAST)
                    dest.y += dest.height - visible.height;
                
                dest.height = visible.height;
            }
        }
        
        if (!visible.contains(dest))
            c.scrollRectToVisible(dest);
    }

    /*----------------------------------------------------------
      Centering.
    */

    public static void center(JComponent c, Rectangle r, boolean withInsets)
    {
        Rectangle visible = c.getVisibleRect();

             visible.x = r.x - (visible.width - r.width) / 2;
        visible.y = r.y - (visible.height - r.height) / 2;

        Rectangle bounds = c.getBounds();
        Insets i = withInsets ? EMPTY_INSETS : c.getInsets();
        bounds.x = i.left;
        bounds.y = i.top;
        bounds.width -= i.left + i.right;
        bounds.height -= i.top + i.bottom;
        
        if (visible.x < bounds.x)
            visible.x = bounds.x;
        
        if (visible.x + visible.width > bounds.x + bounds.width)
            visible.x = bounds.x + bounds.width - visible.width;
        
        if (visible.y < bounds.y)
            visible.y = bounds.y;
        
        if (visible.y + visible.height > bounds.y + bounds.height)
            visible.y = bounds.y + bounds.height - visible.height;
        
        c.scrollRectToVisible(visible);
    }

    
    public static void centerHorizontally(JComponent c, Rectangle r, boolean withInsets)
    {
        centerHorizontally(c, r.x, r.x + r.width, withInsets);
    }

    public static void centerHorizontally(JComponent c, int from, int to, boolean withInsets)
    {
        Rectangle bounds = c.getBounds();
        Insets i = withInsets ? EMPTY_INSETS : c.getInsets();
        bounds.x = i.left;
        bounds.y = i.top;
        bounds.width -= i.left + i.right;
        bounds.height -= i.top + i.bottom;
        
        Rectangle visible = c.getVisibleRect();
        
        visible.x = from - (visible.width + from - to) / 2;
        
        if (visible.x < bounds.x)
            visible.x = bounds.x;
        
        if (visible.x + visible.width > bounds.x + bounds.width)
            visible.x = bounds.x + bounds.width - visible.width;
        
        c.scrollRectToVisible(visible);
    }
    
    public static void centerVertically(JComponent c, Rectangle r, boolean withInsets)
    {
        centerVertically(c, r.y, r.y + r.height, withInsets);
    }

    public static void centerVertically(JComponent c, int from, int to, boolean withInsets)
    {
        Rectangle bounds = c.getBounds();
        Insets i = withInsets ? EMPTY_INSETS : c.getInsets();
        bounds.x = i.left;
        bounds.y = i.top;
        bounds.width -= i.left + i.right;
        bounds.height -= i.top + i.bottom;
        
        Rectangle visible = c.getVisibleRect();
        
        visible.y = from - (visible.height + from - to) / 2;
        
        if (visible.y < bounds.y)
            visible.y = bounds.y;
        
        if (visible.y + visible.height > bounds.y + bounds.height)
            visible.y = bounds.y + bounds.height - visible.height;
        
        c.scrollRectToVisible(visible);
    }

    /*-----------------------------------------------------------
      Visibility.
    */

    public static boolean isVisible(JComponent c, Rectangle r)
    {
        return c.getVisibleRect().contains(r);
    }
    
    public static boolean isHorizontallyVisible(JComponent c, int from, int to)
    {
        Rectangle visible = c.getVisibleRect();
        
        return visible.x <= from
            && visible.x + visible.width >= to;
    }


    public static boolean isHorizontallyVisible(JComponent c, Rectangle r)
    {
        Rectangle visible = c.getVisibleRect();
        
        return visible.x <= r.x
            && visible.x + visible.width >= r.x + r.width;
    }


    public static boolean isVerticallyVisible(JComponent c, int from, int to)
    {
        Rectangle visible = c.getVisibleRect();
        
        return visible.y <= from
            && visible.y + visible.height >= to;
    }


    public static boolean isVerticallyVisible(JComponent c, Rectangle r)
    {
        Rectangle visible = c.getVisibleRect();
        
        return visible.y <= r.y
            && visible.y + visible.height >= r.y + r.height;
    }
}