/**
 * Copyright 2007 Wei-ju Wu
 *
 * This file is part of ontouml.
 *
 * ontouml is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * ontouml is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with ontouml; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 */
package br.inf.ufes.nemo.oled.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * This class accesses images for icons from the class path.
 *
 * @author Wei-ju Wu
 * @version 1.0
 */
public final class IconLoader {

	/**
	 * This enum type lists the available icon types.
	 */
	public enum IconType {
		NEW, 
		OPEN, 
		SAVE, 
		CUT, 
		COPY, 
		PASTE, 
		DELETE, 
		UNDO, 
		REDO, 
		ABOUT, 
		MOUSE_POINTER, 
		CLASS, 
		ASSOCIATION, 
		ASSOCIATION_ARROW, 
		AGGREGATION, 
		AGGREGATION_C, 
		AGGREGATION_M, 
		AGGREGATION_Q, 
		COMPOSITION, 
		INHERITANCE, 
		NOTE, 
		NOTE_CONNECTOR, 
		SHOW_OUTPUT,
		VALIDATE, 
		VERIFY, 
		VERIFY_FILE,
		VIEW,
		RECTILINEAR, 
		STRAIGHT, 
		DERIVATION, 
		RULE, 
		WINDOW, 
		PALETTE_OPEN, 
		PALETTE_CLOSED, 
		GENERATE_OWL, 
		IMPORT, 
		EXPORT, 
		PICTURE,
		BACKGROUND_WELCOME,
		HEAD_WELCOME,
		NEW_PROJECT,
		OPEN_PROJECT,
		LEARN_ONTOUML,
		COMMUNITY,
		NEXT,
		PACKAGE
	}

	private Map<IconType, String> urlMap = new HashMap<IconType, String>();
	private Map<IconType, Icon> iconMap = new HashMap<IconType, Icon>();
	private Map<String, IconType> iconTypeMap = new HashMap<String, IconType>();

	private static IconLoader instance = new IconLoader();

	/**
	 * Private constructor.
	 */
	private IconLoader() {
		for (IconType iconType : IconType.values()) {
			iconTypeMap.put(iconType.toString(), iconType);
		}
		urlMap.put(IconType.MOUSE_POINTER, "resources/br/inf/ufes/nemo/oled/ui/mousepointer.png");
		urlMap.put(IconType.CLASS, "resources/br/inf/ufes/nemo/oled/ui/class.png");
		urlMap.put(IconType.ASSOCIATION, "resources/br/inf/ufes/nemo/oled/ui/association.png");
		urlMap.put(IconType.ASSOCIATION_ARROW, "resources/br/inf/ufes/nemo/oled/ui/association-arrow.png");
		
		urlMap.put(IconType.AGGREGATION, "resources/br/inf/ufes/nemo/oled/ui/aggregation.png");
		urlMap.put(IconType.AGGREGATION_C, "resources/br/inf/ufes/nemo/oled/ui/aggregation-c.png");
		urlMap.put(IconType.AGGREGATION_M, "resources/br/inf/ufes/nemo/oled/ui/aggregation-m.png");
		urlMap.put(IconType.AGGREGATION_Q, "resources/br/inf/ufes/nemo/oled/ui/aggregation-q.png");
		
		urlMap.put(IconType.COMPOSITION, "resources/br/inf/ufes/nemo/oled/ui/composition.png");
		urlMap.put(IconType.INHERITANCE, "resources/br/inf/ufes/nemo/oled/ui/inheritance.png");
		urlMap.put(IconType.DERIVATION, "resources/br/inf/ufes/nemo/oled/ui/derivation.png");
		urlMap.put(IconType.RULE, "resources/br/inf/ufes/nemo/oled/ui/rule.png");
		
		urlMap.put(IconType.SHOW_OUTPUT, "resources/br/inf/ufes/nemo/oled/ui/output.png");
		urlMap.put(IconType.VALIDATE, "resources/br/inf/ufes/nemo/oled/ui/validate.png");
		urlMap.put(IconType.VERIFY, "resources/br/inf/ufes/nemo/oled/ui/verify.png");
		urlMap.put(IconType.VERIFY_FILE, "resources/br/inf/ufes/nemo/oled/ui/verifyfile.png");
		urlMap.put(IconType.VIEW, "resources/br/inf/ufes/nemo/oled/ui/view.png");
		urlMap.put(IconType.GENERATE_OWL, "resources/br/inf/ufes/nemo/oled/ui/owl.png");
		urlMap.put(IconType.RECTILINEAR, "resources/br/inf/ufes/nemo/oled/ui/rectilinear.png");
		urlMap.put(IconType.STRAIGHT, "resources/br/inf/ufes/nemo/oled/ui/straight.png");
		urlMap.put(IconType.NOTE, "resources/br/inf/ufes/nemo/oled/ui/note.png");
		urlMap.put(IconType.NOTE_CONNECTOR, "resources/br/inf/ufes/nemo/oled/ui/note-connector.png");
		
		urlMap.put(IconType.WINDOW, "resources/br/inf/ufes/nemo/oled/ui/window.png");
		urlMap.put(IconType.PALETTE_OPEN, "resources/br/inf/ufes/nemo/oled/ui/palette_open.png");
		urlMap.put(IconType.PALETTE_CLOSED, "resources/br/inf/ufes/nemo/oled/ui/palette_closed.png");
		
		urlMap.put(IconType.NEW, "resources/br/inf/ufes/nemo/oled/ui/new.png");
		urlMap.put(IconType.OPEN, "resources/br/inf/ufes/nemo/oled/ui/open.png");
		urlMap.put(IconType.SAVE, "resources/br/inf/ufes/nemo/oled/ui/save.png");
		urlMap.put(IconType.CUT, "resources/br/inf/ufes/nemo/oled/ui/cut.png");
		urlMap.put(IconType.COPY, "resources/br/inf/ufes/nemo/oled/ui/copy.png");
		urlMap.put(IconType.PASTE, "resources/br/inf/ufes/nemo/oled/ui/paste.png");
		urlMap.put(IconType.DELETE, "resources/br/inf/ufes/nemo/oled/ui/delete.png");
		urlMap.put(IconType.UNDO, "resources/br/inf/ufes/nemo/oled/ui/undo.png");
		urlMap.put(IconType.REDO, "resources/br/inf/ufes/nemo/oled/ui/redo.png");
		urlMap.put(IconType.ABOUT, "resources/br/inf/ufes/nemo/oled/ui/about.png");
		
		urlMap.put(IconType.IMPORT, "resources/br/inf/ufes/nemo/oled/ui/import.png");
		urlMap.put(IconType.EXPORT, "resources/br/inf/ufes/nemo/oled/ui/export.png");
		urlMap.put(IconType.PICTURE, "resources/br/inf/ufes/nemo/oled/ui/picture.png");
		
		urlMap.put(IconType.BACKGROUND_WELCOME, "resources/br/inf/ufes/nemo/oled/ui/bgwelcome.png");
		urlMap.put(IconType.HEAD_WELCOME, "resources/br/inf/ufes/nemo/oled/ui/headwelcome.png");
		urlMap.put(IconType.NEW_PROJECT, "resources/br/inf/ufes/nemo/oled/ui/newproject.png");
		urlMap.put(IconType.OPEN_PROJECT, "resources/br/inf/ufes/nemo/oled/ui/openproject.png");
		urlMap.put(IconType.LEARN_ONTOUML, "resources/br/inf/ufes/nemo/oled/ui/learnontouml.png");
		urlMap.put(IconType.COMMUNITY, "resources/br/inf/ufes/nemo/oled/ui/community.png");
		urlMap.put(IconType.NEXT, "resources/br/inf/ufes/nemo/oled/ui/next.png");
				
		urlMap.put(IconType.PACKAGE, "resources/br/inf/ufes/nemo/oled/ui/package.png");
		//urlMap.put(IconType.COMPONENT, "resources/br/inf/ufes/nemo/oled/uicomponent.png");
		//urlMap.put(IconType.DEPENDENCY, "resources/br/inf/ufes/nemo/oled/uidependency.png");
		//urlMap.put(IconType.INTERFACE_REALIZATION, "resources/br/inf/ufes/nemo/oled/uiinterface-realization.png");
		//urlMap.put(IconType.PALETTE_TITLE, "resources/br/inf/ufes/nemo/oled/uipalette_title.png");
		
	}

	/**
	 * Returns the singleton instance.
	 * @return the singleton instance
	 */
	public static IconLoader getInstance() { return instance; }

	/**
	 * Returns the icon for the specified icon type.
	 * @param type the icon type
	 * @return the icon
	 */
	public Icon getIcon(IconType type) {
		if (!iconMap.containsKey(type)) {
			String urlstr = urlMap.get(type);
			if (urlstr != null) {
				iconMap.put(type,
						new ImageIcon(getClass().getClassLoader().getResource(urlstr)));
			}
		}
		return iconMap.get(type);
	}

	/**
	 * Returns the icon for the specified icon type name.
	 * @param typeName the icon type name
	 * @return the icon
	 */
	public Icon getIcon(String typeName) {
		return getIcon(iconTypeMap.get(typeName));
	}

	/**
	 * Returns the icon for the specified icon type name.
	 * @param typeName the icon type name
	 * @return the icon
	 */
	public Image getImage(String typeName) {
		try {
			IconType type = iconTypeMap.get(typeName);
			return new ImageIcon(getClass().getClassLoader().getResource(urlMap.get(type))).getImage();
		} catch (Exception e) {
			return null;
		}
	}
}
