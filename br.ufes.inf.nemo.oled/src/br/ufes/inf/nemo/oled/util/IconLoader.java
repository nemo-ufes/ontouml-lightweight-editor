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
package br.ufes.inf.nemo.oled.util;

import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 * This class accesses images for icons from the class path.
 *
 * @author Wei-ju Wu, Antognoni Albuquerque, John Guerson
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
		TOOLBOX,
		REDO, 
		ERROR,
		WARNING,
		OUTPUT,
		OCLEDITOR,
		ABOUT, 
		ANNOTATION, //CLEANUP
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
		DIAGNOSTIC,
		VALIDATE, 
		VERIFY_SETTINGS,
		VERIFY, 
		VERIFY_FILE,
		GENERATE_ALLOY,
		PARSE,
		VIEW,
		RECTILINEAR, 
		STRAIGHT, 
		DERIVATION, 
		RULE, 
		WINDOW,		
		AUTO_SELECTION,
		PALETTE_OPEN, 
		PALETTE_CLOSED, 
		GENERATE_OWL_SETTINGS,
		GENERATE_OWL,
		GENERATE_SBVR,
		GENERATE_TEXT,
		EA,
		IMPORT, 
		EXPORT, 
		UML,
		PICTURE,
		BACKGROUND_WELCOME,
		HEAD_WELCOME,
		NEW_PROJECT,
		NEW_DIAGRAM,
		OPEN_PROJECT,
		ISSUE_REPORT,		
		NEXT,
		PACKAGE,
		ADD,
		REMOVE,
		GREEN_LIGHT,
		RED_LIGHT,
		EXCLUDE,
		GREY_LIGHT,
		ARROW_UP,
		COPYRIGHTS,		
		ARROW_DOWN,
		ANTIPATTERN,
		PART_WHOLE_VALIDATION,
		DERIVERELATIONS,
		NEMO,		
		DIRECT,
		PATTERN,
		FIND,
		STATISTICS
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
		urlMap.put(IconType.MOUSE_POINTER, "resources/icons/x16/mousepointer.png");
		
		urlMap.put(IconType.CLASS, "resources/icons/x16/tree/class.png");
		urlMap.put(IconType.PATTERN, "resources/icons/x16/sitemap.png");
		urlMap.put(IconType.ASSOCIATION, "resources/icons/x16/tree/association.png");
		urlMap.put(IconType.ASSOCIATION_ARROW, "resources/icons/x16/tree/association-arrow.png");		
		urlMap.put(IconType.AGGREGATION, "resources/icons/x16/tree/componentof.png");
		urlMap.put(IconType.AGGREGATION_C, "resources/icons/x16/tree/subcollectionof.png");
		urlMap.put(IconType.AGGREGATION_M, "resources/icons/x16/tree/memberof.png");
		urlMap.put(IconType.AGGREGATION_Q, "resources/icons/x16/tree/subquantityof.png");
		urlMap.put(IconType.ANNOTATION, "resources/icons/x16/tree/annotation.png");				
		urlMap.put(IconType.INHERITANCE, "resources/icons/x16/tree/generalization.png");
		urlMap.put(IconType.DERIVATION, "resources/icons/x16/tree/derivation.png");		
		urlMap.put(IconType.RULE, "resources/icons/x16/tree/rule.png");
		urlMap.put(IconType.NOTE, "resources/icons/x16/tree/note.png");
		urlMap.put(IconType.NOTE_CONNECTOR, "resources/icons/x16/tree/note-connector.png");
		
		urlMap.put(IconType.FIND, "resources/icons/x16/find.png");
		urlMap.put(IconType.STATISTICS, "resources/icons/x16/diagnostic.png");
		
		urlMap.put(IconType.OUTPUT, "resources/icons/x16/monitor.png");
		urlMap.put(IconType.ERROR, "resources/icons/x16/cross_octagon.png");
		urlMap.put(IconType.WARNING, "resources/icons/x16/exclamation_octagon_fram.png");
		urlMap.put(IconType.OCLEDITOR, "resources/icons/x16/text-editor.png");
				
		urlMap.put(IconType.VERIFY, "resources/icons/x16/spellcheck.png");
		urlMap.put(IconType.PARSE, "resources/icons/x16/accept.png");

		urlMap.put(IconType.WINDOW, "resources/icons/window.png");
		urlMap.put(IconType.NEMO, "resources/icons/window16.png");
		
		urlMap.put(IconType.PALETTE_OPEN, "resources/icons/x16/palette_open.png");
		urlMap.put(IconType.PALETTE_CLOSED, "resources/icons/x16/palette_closed.png");
		
		urlMap.put(IconType.EA, "resources/icons/x16/ea.jpg");		
		urlMap.put(IconType.UML, "resources/icons/x16/eclipse.gif");
		urlMap.put(IconType.RECTILINEAR, "resources/icons/x16/rectilinear.png");
		urlMap.put(IconType.STRAIGHT, "resources/icons/x16/straight.png");
				
		urlMap.put(IconType.ANTIPATTERN, "resources/icons/antipattern16.png");
		urlMap.put(IconType.PART_WHOLE_VALIDATION, "resources/icons/x16/wall_break.png");
		
		urlMap.put(IconType.GENERATE_ALLOY, "resources/icons/x16/alloy/alloy.png");
				
		urlMap.put(IconType.GENERATE_OWL, "resources/icons/x16/sw-cube.png");
		urlMap.put(IconType.GENERATE_OWL_SETTINGS, "resources/icons/x16/sw-cube.png");
		
		urlMap.put(IconType.GENERATE_SBVR, "resources/icons/x16/world.png");
		
		urlMap.put(IconType.GENERATE_TEXT, "resources/icons/x16/glossary.png");
	
		urlMap.put(IconType.NEW, "resources/icons/x16/page_2.png");
		urlMap.put(IconType.OPEN, "resources/icons/x16/folder.png");
		urlMap.put(IconType.SAVE, "resources/icons/x16/disk.png");
		urlMap.put(IconType.CUT, "resources/icons/x16/cut.png");
		urlMap.put(IconType.COPY, "resources/icons/x16/page_2_copy.png");
		urlMap.put(IconType.PASTE, "resources/icons/x16/page_paste.png");		
		urlMap.put(IconType.DELETE, "resources/icons/x16/cross.png");
		urlMap.put(IconType.EXCLUDE, "resources/icons/x16/bin_closed.png");		
		urlMap.put(IconType.UNDO, "resources/icons/x16/arrow_undo.png");
		urlMap.put(IconType.REDO, "resources/icons/x16/arrow_redo.png");		
		urlMap.put(IconType.ABOUT, "resources/icons/x16/exclamation.png");
		urlMap.put(IconType.TOOLBOX, "resources/icons/x16/hammer_screwdriver.png");
		
		urlMap.put(IconType.EXPORT, "resources/icons/x16/page_white_get.png");
		urlMap.put(IconType.IMPORT, "resources/icons/x16/page_white_put.png");
		
		urlMap.put(IconType.PICTURE, "resources/icons/x16/image_1.png");

		urlMap.put(IconType.ARROW_UP, "resources/icons/x16/arrow_up.png");
		urlMap.put(IconType.ARROW_DOWN, "resources/icons/x16/arrow_down.png");

		urlMap.put(IconType.GREEN_LIGHT, "resources/icons/x16/greenlight.png");
		urlMap.put(IconType.RED_LIGHT, "resources/icons/x16/redlight.png");
		urlMap.put(IconType.GREY_LIGHT, "resources/icons/x16/greylight.png");
		
		urlMap.put(IconType.BACKGROUND_WELCOME, "resources/icons/bgwelcome.png");
		urlMap.put(IconType.HEAD_WELCOME, "resources/icons/headwelcome.png");
				
		urlMap.put(IconType.ISSUE_REPORT, "resources/icons/x16/link.png");		
		urlMap.put(IconType.NEXT, "resources/icons/x16/next.png");		
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
