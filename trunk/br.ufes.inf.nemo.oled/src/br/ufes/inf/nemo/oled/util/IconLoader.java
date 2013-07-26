/**
 * Copyright 2011 NEMO (http://nemo.inf.ufes.br/en)
 *
 * This file is part of OLED (OntoUML Lightweight BaseEditor).
 * OLED is based on TinyUML and so is distributed under the same
 * licence terms.
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
 * @author Wei-ju Wu
 * @author Antognoni Albuquerque
 * @version 0.1
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
		PACKAGE,
		ADD,
		REMOVE,
		GREEN_LIGHT,
		RED_LIGHT,
		GREY_LIGHT,
		ARROW_UP,
		COPYRIGHTS,		
		ARROW_DOWN,
		ANTIPATTERN,
		DERIVERELATIONS,
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
		urlMap.put(IconType.MOUSE_POINTER, "resources/br/ufes/inf/nemo/oled/ui/mousepointer.png");
		urlMap.put(IconType.CLASS, "resources/br/ufes/inf/nemo/oled/ui/class.png");
		urlMap.put(IconType.ASSOCIATION, "resources/br/ufes/inf/nemo/oled/ui/association.png");
		urlMap.put(IconType.ASSOCIATION_ARROW, "resources/br/ufes/inf/nemo/oled/ui/association-arrow.png");
		
		urlMap.put(IconType.AGGREGATION, "resources/br/ufes/inf/nemo/oled/ui/aggregation.png");
		urlMap.put(IconType.AGGREGATION_C, "resources/br/ufes/inf/nemo/oled/ui/aggregation-c.png");
		urlMap.put(IconType.AGGREGATION_M, "resources/br/ufes/inf/nemo/oled/ui/aggregation-m.png");
		urlMap.put(IconType.AGGREGATION_Q, "resources/br/ufes/inf/nemo/oled/ui/aggregation-q.png");
		urlMap.put(IconType.ANNOTATION, "resources/br/ufes/inf/nemo/oled/ui/annotation.png"); //CLEANUP
		
		urlMap.put(IconType.COMPOSITION, "resources/br/ufes/inf/nemo/oled/ui/composition.png");
		urlMap.put(IconType.INHERITANCE, "resources/br/ufes/inf/nemo/oled/ui/inheritance.png");
		urlMap.put(IconType.DERIVATION, "resources/br/ufes/inf/nemo/oled/ui/derivation.png");
		urlMap.put(IconType.RULE, "resources/br/ufes/inf/nemo/oled/ui/rule.png");
		
		urlMap.put(IconType.OUTPUT, "resources/br/ufes/inf/nemo/oled/ui/output.png");
		urlMap.put(IconType.ERROR, "resources/br/ufes/inf/nemo/oled/ui/error.png");
		urlMap.put(IconType.WARNING, "resources/br/ufes/inf/nemo/oled/ui/warning.png");
		urlMap.put(IconType.OCLEDITOR, "resources/br/ufes/inf/nemo/oled/ui/ocleditor.png");
		
		urlMap.put(IconType.AUTO_SELECTION, "resources/br/ufes/inf/nemo/oled/ui/completion.png");		
		urlMap.put(IconType.VALIDATE, "resources/br/ufes/inf/nemo/oled/ui/verify.png");		
		urlMap.put(IconType.VERIFY_SETTINGS, "resources/br/ufes/inf/nemo/oled/ui/verifysettings.png");
		urlMap.put(IconType.VERIFY_FILE, "resources/br/ufes/inf/nemo/oled/ui/verifyfile.png");		
		urlMap.put(IconType.VERIFY, "resources/br/ufes/inf/nemo/oled/ui/validate.png");
		urlMap.put(IconType.PARSE, "resources/br/ufes/inf/nemo/oled/ui/check.png");
		
		urlMap.put(IconType.VIEW, "resources/br/ufes/inf/nemo/oled/ui/view.png");
		urlMap.put(IconType.DERIVERELATIONS, "resources/br/ufes/inf/nemo/oled/ui/derive-relations.png");
		urlMap.put(IconType.GENERATE_ALLOY, "resources/br/ufes/inf/nemo/oled/ui/alloy.png");
		urlMap.put(IconType.ANTIPATTERN, "resources/br/ufes/inf/nemo/oled/ui/antipattern.png");
		urlMap.put(IconType.GENERATE_OWL, "resources/br/ufes/inf/nemo/oled/ui/owl.png");
		urlMap.put(IconType.GENERATE_OWL_SETTINGS, "resources/br/ufes/inf/nemo/oled/ui/owlsettings.png");
		urlMap.put(IconType.GENERATE_SBVR, "resources/br/ufes/inf/nemo/oled/ui/sbvr.png");
		urlMap.put(IconType.GENERATE_TEXT, "resources/br/ufes/inf/nemo/oled/ui/text.png");
		urlMap.put(IconType.RECTILINEAR, "resources/br/ufes/inf/nemo/oled/ui/rectilinear.png");
		urlMap.put(IconType.STRAIGHT, "resources/br/ufes/inf/nemo/oled/ui/straight.png");
		urlMap.put(IconType.NOTE, "resources/br/ufes/inf/nemo/oled/ui/note.png");
		urlMap.put(IconType.NOTE_CONNECTOR, "resources/br/ufes/inf/nemo/oled/ui/note-connector.png");
		
		urlMap.put(IconType.WINDOW, "resources/br/ufes/inf/nemo/oled/ui/window.png");
		urlMap.put(IconType.PALETTE_OPEN, "resources/br/ufes/inf/nemo/oled/ui/palette_open.png");
		urlMap.put(IconType.PALETTE_CLOSED, "resources/br/ufes/inf/nemo/oled/ui/palette_closed.png");
		
		urlMap.put(IconType.NEW, "resources/br/ufes/inf/nemo/oled/ui/new.png");
		urlMap.put(IconType.OPEN, "resources/br/ufes/inf/nemo/oled/ui/open.png");
		urlMap.put(IconType.SAVE, "resources/br/ufes/inf/nemo/oled/ui/save.png");
		urlMap.put(IconType.CUT, "resources/br/ufes/inf/nemo/oled/ui/cut.png");
		urlMap.put(IconType.COPY, "resources/br/ufes/inf/nemo/oled/ui/copy.png");
		urlMap.put(IconType.PASTE, "resources/br/ufes/inf/nemo/oled/ui/paste.png");
		urlMap.put(IconType.DELETE, "resources/br/ufes/inf/nemo/oled/ui/delete.png");
		urlMap.put(IconType.UNDO, "resources/br/ufes/inf/nemo/oled/ui/undo.png");
		urlMap.put(IconType.REDO, "resources/br/ufes/inf/nemo/oled/ui/redo.png");
		urlMap.put(IconType.ABOUT, "resources/br/ufes/inf/nemo/oled/ui/about.png");
		urlMap.put(IconType.COPYRIGHTS, "resources/br/ufes/inf/nemo/oled/ui/copyrights.png");
		
		urlMap.put(IconType.IMPORT, "resources/br/ufes/inf/nemo/oled/ui/import.png");
		urlMap.put(IconType.EXPORT, "resources/br/ufes/inf/nemo/oled/ui/export.png");
		urlMap.put(IconType.PICTURE, "resources/br/ufes/inf/nemo/oled/ui/picture.png");
		
		urlMap.put(IconType.BACKGROUND_WELCOME, "resources/br/ufes/inf/nemo/oled/ui/bgwelcome.png");
		urlMap.put(IconType.HEAD_WELCOME, "resources/br/ufes/inf/nemo/oled/ui/headwelcome.png");
		urlMap.put(IconType.NEW_PROJECT, "resources/br/ufes/inf/nemo/oled/ui/newproject.png");
		urlMap.put(IconType.OPEN_PROJECT, "resources/br/ufes/inf/nemo/oled/ui/openproject.png");
		urlMap.put(IconType.LEARN_ONTOUML, "resources/br/ufes/inf/nemo/oled/ui/learnontouml.png");
		urlMap.put(IconType.COMMUNITY, "resources/br/ufes/inf/nemo/oled/ui/community.png");
		urlMap.put(IconType.NEXT, "resources/br/ufes/inf/nemo/oled/ui/next.png");			
		urlMap.put(IconType.PACKAGE, "resources/br/ufes/inf/nemo/oled/ui/package.png");
		
		urlMap.put(IconType.ADD, "resources/br/ufes/inf/nemo/oled/ui/add.png");
		urlMap.put(IconType.REMOVE, "resources/br/ufes/inf/nemo/oled/ui/remove.png");
		urlMap.put(IconType.GREEN_LIGHT, "resources/br/ufes/inf/nemo/oled/ui/greenlight.png");
		urlMap.put(IconType.RED_LIGHT, "resources/br/ufes/inf/nemo/oled/ui/redlight.png");
		urlMap.put(IconType.GREY_LIGHT, "resources/br/ufes/inf/nemo/oled/ui/greylight.png");
		urlMap.put(IconType.ARROW_UP, "resources/br/ufes/inf/nemo/oled/ui/arrowup.png");
		urlMap.put(IconType.ARROW_DOWN, "resources/br/ufes/inf/nemo/oled/ui/arrowdown.png");

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
