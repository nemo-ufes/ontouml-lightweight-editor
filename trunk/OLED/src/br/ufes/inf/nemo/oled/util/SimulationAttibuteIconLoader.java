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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;

import refontouml2alloy.bts.simulation.SimulationAttribute;

/**
 * This class accesses images for icons from the class path.
 *
 * @author Wei-ju Wu
 * @author Antognoni Albuquerque
 * @version 0.1
 */
public final class SimulationAttibuteIconLoader {

	private Map<SimulationAttribute, String> urlMap = new HashMap<SimulationAttribute, String>();
	private Map<SimulationAttribute, Icon> iconMap = new HashMap<SimulationAttribute, Icon>();
	private Map<String, SimulationAttribute> attributeIconMap = new HashMap<String, SimulationAttribute>();
	private static SimulationAttibuteIconLoader instance = new SimulationAttibuteIconLoader();

	/**
	 * Private constructor.
	 */
	private SimulationAttibuteIconLoader() {
		for (SimulationAttribute item : SimulationAttribute.values()) {
			attributeIconMap.put(item.toString(), item);
		}
	
		urlMap.put(SimulationAttribute.COLOR_INHERIT, "resources/br/ufes/inf/nemo/oled/ui/simulation/magic.gif");
		urlMap.put(SimulationAttribute.COLOR_BLACK, "resources/br/ufes/inf/nemo/oled/ui/simulation/black.gif");
		urlMap.put(SimulationAttribute.COLOR_BLUE, "resources/br/ufes/inf/nemo/oled/ui/simulation/cornflowerblue.gif");
		urlMap.put(SimulationAttribute.COLOR_YELLOW, "resources/br/ufes/inf/nemo/oled/ui/simulation/gold.gif");
		urlMap.put(SimulationAttribute.COLOR_GRAY, "resources/br/ufes/inf/nemo/oled/ui/simulation/lightgray.gif");
		urlMap.put(SimulationAttribute.COLOR_GREEN, "resources/br/ufes/inf/nemo/oled/ui/simulation/limegreen.gif");
		urlMap.put(SimulationAttribute.COLOR_RED, "resources/br/ufes/inf/nemo/oled/ui/simulation/palevioletred.gif");
		urlMap.put(SimulationAttribute.COLOR_WHITE, "resources/br/ufes/inf/nemo/oled/ui/simulation/white.gif");
		
		urlMap.put(SimulationAttribute.SHAPE_INHERIT, "resources/br/ufes/inf/nemo/oled/ui/simulation/box.gif");
		urlMap.put(SimulationAttribute.SHAPE_BOX, "resources/br/ufes/inf/nemo/oled/ui/simulation/box.gif");
		urlMap.put(SimulationAttribute.SHAPE_CIRCLE, "resources/br/ufes/inf/nemo/oled/ui/simulation/circle.gif");
		urlMap.put(SimulationAttribute.SHAPE_DIAMOND, "resources/br/ufes/inf/nemo/oled/ui/simulation/diamond.gif");
		urlMap.put(SimulationAttribute.SHAPE_DOUBLECIRCLE, "resources/br/ufes/inf/nemo/oled/ui/simulation/doublecircle.gif");
		urlMap.put(SimulationAttribute.SHAPE_DOUBLEOCTAGON, "resources/br/ufes/inf/nemo/oled/ui/simulation/doubleoctagon.gif");
		urlMap.put(SimulationAttribute.SHAPE_EGG, "resources/br/ufes/inf/nemo/oled/ui/simulation/egg.gif");
		urlMap.put(SimulationAttribute.SHAPE_ELLISPSE, "resources/br/ufes/inf/nemo/oled/ui/simulation/ellipse.gif");
		urlMap.put(SimulationAttribute.SHAPE_HEXAGON, "resources/br/ufes/inf/nemo/oled/ui/simulation/hexagon.gif");
		urlMap.put(SimulationAttribute.SHAPE_HOUSE, "resources/br/ufes/inf/nemo/oled/ui/simulation/house.gif");
		urlMap.put(SimulationAttribute.SHAPE_INVHOUSE, "resources/br/ufes/inf/nemo/oled/ui/simulation/invhouse.gif");
		urlMap.put(SimulationAttribute.SHAPE_INVTRAPEZIUM, "resources/br/ufes/inf/nemo/oled/ui/simulation/invtrapezium.gif");
		urlMap.put(SimulationAttribute.SHAPE_INVTRIANGLE, "resources/br/ufes/inf/nemo/oled/ui/simulation/invtriangle.gif");
		urlMap.put(SimulationAttribute.SHAPE_MCIRCLE, "resources/br/ufes/inf/nemo/oled/ui/simulation/Mcircle.gif");
		urlMap.put(SimulationAttribute.SHAPE_MDIAMOND, "resources/br/ufes/inf/nemo/oled/ui/simulation/Mdiamond.gif");
		urlMap.put(SimulationAttribute.SHAPE_MSQUARE, "resources/br/ufes/inf/nemo/oled/ui/simulation/Msquare.gif");
		urlMap.put(SimulationAttribute.SHAPE_OCTAGON, "resources/br/ufes/inf/nemo/oled/ui/simulation/octagon.gif");
		urlMap.put(SimulationAttribute.SHAPE_PARALLELOGRAM, "resources/br/ufes/inf/nemo/oled/ui/simulation/parallelogram.gif");
		urlMap.put(SimulationAttribute.SHAPE_TRAPEZIUM, "resources/br/ufes/inf/nemo/oled/ui/simulation/trapezium.gif");
		urlMap.put(SimulationAttribute.SHAPE_TRIANGLE, "resources/br/ufes/inf/nemo/oled/ui/simulation/triangle.gif");
		urlMap.put(SimulationAttribute.SHAPE_TRIPLEOCTAGON, "resources/br/ufes/inf/nemo/oled/ui/simulation/tripleoctagon.gif");
		
		urlMap.put(SimulationAttribute.STYLE_INHERIT, "resources/br/ufes/inf/nemo/oled/ui/simulation/solid.gif");
		urlMap.put(SimulationAttribute.STYLE_BOLD, "resources/br/ufes/inf/nemo/oled/ui/simulation/bold.gif");
		urlMap.put(SimulationAttribute.STYLE_DASHED, "resources/br/ufes/inf/nemo/oled/ui/simulation/dashed.gif");
		urlMap.put(SimulationAttribute.STYLE_DOTTED, "resources/br/ufes/inf/nemo/oled/ui/simulation/dotted.gif");
		urlMap.put(SimulationAttribute.STYLE_SOLID, "resources/br/ufes/inf/nemo/oled/ui/simulation/solid.gif");

	}

	public List<SimulationAttribute> getColorSimulationAttributes()
	{
		List<SimulationAttribute> list = new ArrayList<SimulationAttribute>();
		
		list.add(SimulationAttribute.COLOR_INHERIT);
		list.add(SimulationAttribute.COLOR_BLUE);
		list.add(SimulationAttribute.COLOR_YELLOW);
		list.add(SimulationAttribute.COLOR_GRAY);
		list.add(SimulationAttribute.COLOR_GREEN);
		list.add(SimulationAttribute.COLOR_RED);
		list.add(SimulationAttribute.COLOR_WHITE);
		list.add(SimulationAttribute.COLOR_BLACK);
		
		return list;
	}
	
	public List<SimulationAttribute> getShapeSimulationAttributes()
	{
		List<SimulationAttribute> list = new ArrayList<SimulationAttribute>();
		
		list.add(SimulationAttribute.SHAPE_INHERIT);
		list.add(SimulationAttribute.SHAPE_BOX);
		list.add(SimulationAttribute.SHAPE_CIRCLE);
		list.add(SimulationAttribute.SHAPE_DIAMOND);
		list.add(SimulationAttribute.SHAPE_DOUBLECIRCLE);
		list.add(SimulationAttribute.SHAPE_DOUBLEOCTAGON);
		list.add(SimulationAttribute.SHAPE_EGG);
		list.add(SimulationAttribute.SHAPE_ELLISPSE);
		list.add(SimulationAttribute.SHAPE_HEXAGON);
		list.add(SimulationAttribute.SHAPE_HOUSE);
		list.add(SimulationAttribute.SHAPE_INVHOUSE);
		list.add(SimulationAttribute.SHAPE_INVTRAPEZIUM);
		list.add(SimulationAttribute.SHAPE_INVTRIANGLE);
		list.add(SimulationAttribute.SHAPE_MCIRCLE);
		list.add(SimulationAttribute.SHAPE_MDIAMOND);
		list.add(SimulationAttribute.SHAPE_MSQUARE);
		list.add(SimulationAttribute.SHAPE_OCTAGON);
		list.add(SimulationAttribute.SHAPE_PARALLELOGRAM);
		list.add(SimulationAttribute.SHAPE_TRAPEZIUM);
		list.add(SimulationAttribute.SHAPE_TRIANGLE);
		list.add(SimulationAttribute.SHAPE_TRIPLEOCTAGON);
			
		return list;
	}
	
	public List<SimulationAttribute> getStyleSimulationAttributes()
	{
		List<SimulationAttribute> list = new ArrayList<SimulationAttribute>();
		
		list.add(SimulationAttribute.STYLE_INHERIT);
		list.add(SimulationAttribute.STYLE_BOLD);
		list.add(SimulationAttribute.STYLE_DASHED);
		list.add(SimulationAttribute.STYLE_DOTTED);
		list.add(SimulationAttribute.STYLE_SOLID);
		
		return list;
	}
	
	/**
	 * Returns the singleton instance.
	 * @return the singleton instance
	 */
	public static SimulationAttibuteIconLoader getInstance() { return instance; }

	/**
	 * Returns the icon for the specified icon type.
	 * @param attribute the icon type
	 * @return the icon
	 */
	public Icon getIcon(SimulationAttribute attribute) {
		if (!iconMap.containsKey(attribute)) {
			String urlstr = urlMap.get(attribute);
			if (urlstr != null) {
				iconMap.put(attribute,
						new ImageIcon(getClass().getClassLoader().getResource(urlstr)));
			}
		}
		return iconMap.get(attribute);
	}

	/**
	 * Returns the icon for the specified icon type name.
	 * @param typeName the icon type name
	 * @return the icon
	 */
	public Icon getIcon(String typeName) {
		return getIcon(attributeIconMap.get(typeName));
	}

	/**
	 * Returns the icon for the specified icon type name.
	 * @param typeName the icon type name
	 * @return the icon
	 */
	public Image getImage(String typeName) {
		try {
			SimulationAttribute type = attributeIconMap.get(typeName);
			return new ImageIcon(getClass().getClassLoader().getResource(urlMap.get(type))).getImage();
		} catch (Exception e) {
			return null;
		}
	}
}
